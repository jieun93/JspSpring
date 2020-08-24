/**
 * $.fn.
 */
//!!!!!!!!!!중요!!!!!!!!!!!!

//비동기식 방법으로 선택된 폴더의 파일을 보여주는거

$.fn.browse =function(obj){
	
	 const  browsingURL = obj.browsingURL; // 바뀌지 않는거
	 const eleClass =  obj.eleClass;
	 const processURL = obj.processURL;
	 const browseTag = this;
	 
	 
	 var commandProcess = function(obj){
//		 console.log(obj);
		 // 비동기 요청 발생
		$.ajax({
			url : processURL ,
			data : {
				command:obj.command,
				file:obj.file.prop("id"),
				dest: obj.dest? obj.dest.prop("id"):"" // 삭제할시에는 이부분이 필요없다. 
			},
			method : "post",
			dataType : "json",// Accept:application/json, Content-Type:application/json
			success : function(resp) {
				console.log(resp);
				if(resp.status == 200){
					// 명령이 처리완료되는거ㅏ 
					// 갱신이 되는곳 
					obj.src.find("."+eleClass).trigger("refresh");
					// 2번도 갱신
					if(obj.dest){
						obj.desc.find("."+eleClass).trigger("refresh");
					}
					
				}else{
					alert(resp.message);
				}
			},
			error : function(errorResp){
				console.log(errorResp.status+ ":" +errorResp.responseText);
			}
			
		});
		 
	 }
	
	var toggler = this.on("click", "."+eleClass, function(){
		let liTag = $(this).parent();
		let caretTag = $(this);
		let base = liTag.prop("id");
		let already = $(this).next("ul.nested:first");
		if(already && already.length>0){
			liTag.find(".nested:first").toggleClass("active");
			caretTag.toggleClass("caret-down");
			return;	
		}

	// 비동기 요청 발생 
	// refresh 에서 작동 	
  		$.ajax({
  			url :browsingURL,
			data : {
				base:base
			},
			method : "get",
			dataType : "json",// Accept:application/json, Content-Type:application/json
			success : function(resp) {
				console.log(resp);
				if(!resp) return;
				let ulTag = $("<ul>").addClass("nested")
									 .sortable({
										 items:">li.file"
										 ,connectWith:".folder>.nested"
											 , receive:function(event, ui){
//												 console.log(event);
//												 console.log(ui);
//												 console.log($(this).parent("li").prop("id"));
												 // 컨트롤을 누르면 복사 아니면 이동
												 let dest = $(this).parent("li");
												 let src = ui.sender.parent("li");
												 let commandObj = {
														 command : event.ctrlKey ? "COPY":"MOVE"
															 ,file : ui.item
															 ,dest : dest
															 ,src  : src
												 }
												 commandProcess(commandObj);
											 }
									 });
									 
				$(resp).each(function(idx, wrapper){
					let liTag = $("<li>").prop({
						"id" : wrapper.id,
						"class" : wrapper.clzName
						});
					if(wrapper.directory){
						liTag.html($("<span>").addClass(eleClass).text(wrapper.name));
					}else{
						liTag.text(wrapper.name).addClass("dropdown-item")
						.on("click",function(){// 선택한게 active 클래스를 가지고 있는지 확인
							if(!$(this).hasClass("active")){ //다른 li에 있는 active를 지워준다.
								browseTag.find("li.file").removeClass("active"); // 브라우징 영역만 트래버싱한다.
							}
							$(this).toggleClass("active");
						}); //행에다가 css
					}
					ulTag.append(liTag);
				});
				caretTag.after(ulTag);
				liTag.find(".nested:first").toggleClass("active");  
				caretTag.toggleClass("caret-down");
			},
			error : function(errorResp){
				console.log(errorResp.status + ":"+errorResp.responseText);
			}
  		
  		});
  	// 클릭이벤트핸들러의 마지막 부분 // 특정폴더를 갱신하는거 , 파일복사,이동  성공을하면 실행하는거 
	}).on("refresh","."+eleClass, function(){
		let already = $(this).next("ul.nested:first");
		already.remove();
		// 리프레쉬의 이벤트를  클릭이벤트로 바꾸는거 
		$(this).trigger("click");
	});
	
	// 이벤트는 모든곳에서 발생된다. 키를 처리하는거
	// 키를 눌렀다 땠을 때 
	$(document).on("keyup", function(event){
//		console.log(event);
		if(event.keyCode==46){ // 파일을 삭제하는거 
			// 어떤파일을 삭제할건지 , 현재 active상태듸 li태그를 확인해야 한다.
			 let file = browseTag.find("li.active");
			 // 파일 존재 
			 if(file.length>0 && confirm("삭제하시겠습니까?")){
				 let commandObject = {
					command : "DELETE",
					file : file,
					src : file.parents("li.folder:first") //  파일이 속해있는 부모를 찾아서 갱신해줘야 한다.parents 조상들을 다 불러옴 
					
				 };
				 commandProcess(commandObject);
			 }
		}
		
		
	});
	
	return this; // 체인구조에서 꼭 필요하다. 
}
















