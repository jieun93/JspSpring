<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>   
    <nav id="sidebarMenu" class="col-md-3 col-lg-2 d-md-block bg-light sidebar collapse">
      <div class="sidebar-sticky pt-3">
        <ul class="nav flex-column">
          <li class="nav-item">
            <a class="nav-link active" href="<%=request.getContextPath()%>">
              <span data-feather="home"></span>
              Home(Welcome) <span class="sr-only">(current)</span>
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=STANDARDJSP">
              <span data-feather="file"></span>
              STANDARDJSP
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=CALCULATE">
              <span data-feather="shopping-cart"></span>
            	  계산기
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=CALENDAR">
              <span data-feather="users"></span>
            	  달력
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=SESSIONTIMER">
              <span data-feather="bar-chart-2"></span>
            	  세션타이머
            </a>
          </li>
          <li class="nav-item">
            <a class="nav-link" href="<%=request.getContextPath()%>?command=CONTEXTBROWSING">
              <span data-feather="bar-chart-2"></span>
            	 파일
            </a>
          </li>
          <li>
          
		    <c:set var="userList" value="${applicationScope.userList}"/>
		    <c:if test="${not empty userList }">
			    <ul id="userList">
					<c:forEach items="${userList}" var="user">
						<c:choose>
							<c:when test="${user eq authUser }">
								<li class ="me" data-email="${user.mem_mail}">${user.mem_name}</li>
							</c:when>
							<c:otherwise>
								<li data-email="${user.mem_mail}">${user.mem_name}</li>
							</c:otherwise>
						</c:choose>
						
					
					</c:forEach>
				</ul>
		    </c:if>
          </li>
          <li>
				<c:if test="${not empty menuList }">
					<c:forEach items="${menuList }" var="menu">
						<li><a href="${cPath}${menu.url}">${menu.text }</a></li>
					</c:forEach>
				</c:if>          
          
          </li>
        </ul>
      </div>
    </nav>

<script type="text/javascript">
	var userListArea = $("ul#userList").on("click", "li:not(.me)", function(event){
		let email = $(this).data("email");
		alert(email);
	});
	
	const protocol = location.protocol=="http:"?"ws:":"wss:";
	const domain = location.hostname;
	const port = location.port != "" ? ":"+location.port : "";
	const address = protocol+"//"+domain+port+"${cPath}/pushMessage";
	$("#url").val(address);
	var ws = null;
	
		ws = new WebSocket(address);
		ws.onopen = function(event){
			console.log(event);
			
		}
		ws.onclose = function(event){
			console.log(event);
			
		}
		ws.onmessage = function(event){
			let userList  = event.data;
			userList =  JSON.parse(userList);
			console.log(userList);
			let liTags = [];
					for(let i=0; i<userList.length; i++){
					let user = userList[i];					 	
					let liTag = $("<li>").text(user.mem_name).data("email",user.mem_mail);
					if(user.mem_id == "${authUser.mem_id}"){
						liTag.addClass("me");
					}
					liTags.push(liTag);
				}
				userListArea.html(liTags);
			
			
		}
		ws.onerror = function(event){
			console.log(event);
			
		}
	
	
	
// 	setInterval(function(){
// 		$.ajax({
// 			url : "${cPath}/getUserList.do",
// 			dataType : "json",
// 			success : function(resp) {
// 				let liTags = [];
// 				$.each(resp, function(idx, user){
// 					let liTag = $("<li>").text(user.mem_name).data("email",user.mem_mail);
// 					if(user.mem_id == "${authUser.mem_id}"){
// 						liTag.addClass("me");
// 					}
// 					liTags.push(liTag);
// 				});
// 				userListArea.html(liTags);
// 			},
// 			error : function(errorResp) {
// 				console.log(errorResp.status + ":" + errorResp.responseText);
// 			}
// 		});
// 	}, 3000);
	
</script>
    
    
    
    
    
    
    
    