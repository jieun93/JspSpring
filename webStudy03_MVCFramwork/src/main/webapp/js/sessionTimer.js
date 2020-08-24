/**
 * 제이쿼리 함수로 바꾸는거  
 */
// div의 id sessionTimer
// 기본 화면에 뿌려지는거 

$.fn.sessionTimer = function (timeout){
	
	console.log(this);
	//1번
	const self = this;
    const initTime = timeout;
    var  messageOutputId = null;
    
   //3번
    // 화면에 나타낼 시간을 보여주는거 
   function formatting(timeValue) {
      let min = Math.trunc(timeValue/60); // 분을 나타내는거 , math.trunc:숫자의 정수 부분 
      let sec = timeValue % 60;	// 초를 나타내는거 
      return min + ":" + sec;
   }
   // 2번
   // 초기값 설정 
   function init() {
      msgArea = self.find("#msgArea").hide(); /* 기본으로메세지가안보이도록  */
      timeValue = initTime;
      if(messageOutputId){
         clearTimeout(messageOutputId);
      }
      //  60초가 남았을 떄 메세지를 보여주는거 
      messageOutputId = setTimeout(function() {
         msgArea.show();
      }, (initTime - 60) * 1000);
   }
   
   //4번
   init(); // 초기값 설정한거 보여지는거 
   
   var timerArea = self.find("#timerArea");
//    div 만드는거 
   if(!timerArea || timerArea.length==0){
//	   엘리먼트 존재하지 않으면
	   timerArea =  $("<div>").prop({id : "timerArea"});
	   self.prepend(timerArea);
   }
   
   // 버튼을 클릭했을 떄 
   // msgArea에서 class 'msgBtn'를 찾아 클릭하면 
   var msgBtn = msgArea.find(".msgBtn").on("click", function() {
      let btnId = $(this).prop("id");
      // 클릭함 id가   yesBtn 이면 init를 불름 
      if(btnId == "yesBtn"){
         init();
      }
      msgArea.hide(); // 메세지 창을 숨김 
   });

   //setInterval : 일정시간 마다 함수를 실행한다. 
   var timerId = setInterval(function() {
      if(--timeValue <= 0){ // 시간이 0보다 작으면 
         clearInterval(timerId);  // clearInterval: setInterval반복되는검 멈추게 한다. 
      }else{
         timerArea.text(formatting(timeValue));
      }
   },1000);
}