<%@page import="java.util.Arrays"%>
<%@page import="com.fasterxml.jackson.databind.ObjectMapper"%>
<%@page import="kr.or.ddit.Constants"%>
<%@page import="java.net.URLDecoder"%>
<%@page import="java.io.File"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
 // 쿠키 꺼내오는거 
 
 Cookie[] cookies = request.getCookies();
 String selectedJson =null;
 if(cookies!=null){
 	for(Cookie tmp : cookies){
 		if(Constants.SELECTEDFILE.equals(tmp.getName())){
 			selectedJson = URLDecoder.decode(tmp.getValue(),"UTF-8");
 		}
 	}
 }
 String[] savedArray = null;
		 
if(selectedJson != null){
	ObjectMapper mapper = new ObjectMapper(); 
	savedArray = mapper.readValue(selectedJson, String[].class);
}
 	
 
 
 %>   

<!DOCTYPE html>
<html>
   <head>
      <script
        src="https://code.jquery.com/jquery-3.5.1.min.js"
        integrity="sha256-9/aliU8dGd2tb6OSsuzixeV4y/faTqgFtohetphbbj0="
        crossorigin="anonymous">
      </script>
      
      <script type='text/javascript'>
      /* 0511과제 ==>	선택된 옵션이 그룹된 조건이 있어야 한다. 
      	현재 옵션이 이미지인지 동영상인지 바꿔야 하낟.
      	비디오 태크,소스 ,플레이 해줘야 한다.  */
      	
     // 	제이쿼리사용하는 부분
          $(function(){
        	  
             let resultArea = $("#resultArea"); // div에 사진을 넣을 공간을 불러 오는거 
             // form의 select에서 파일을 선택하면  해당하는  파일 가져오는거 
             $("select[name='filename']").on("change", function(event){
            	 // 선택학 파일을 filename에 담는거 
                let filename = $(this).val(); //let => 변수의 범위가 블럭안으로 제한이 된다.
				
				// 자바스크립에서는 기본으로 indexOf를 사용하면  -1 값을 보낸다. 
                if(filename.indexOf("jpg")>=0 || filename.indexOf("ico")>=0)
                {// 선택한 파일이름에서 jpg가 0보다 크거나 같으면 해당 파일 보여줌 
                	// 선택한 파일의 경로의 속성을 받아서 html에 붙여준다. 
	                let imgTag = $("<img>").attr({
	                 src:"<%=request.getContextPath()%>/fileStream.do?filename=" + filename
                	});
                 	resultArea.html(imgTag); 
                
                }
                else
                {// videotag랑 같으면 똑같이 html파일에 붙여준다. 
	                let videoTag = $("<video controls autoplay>").attr({
	                    src:"<%=request.getContextPath()%>/fileStream.do?filename=" + filename
	                  });
	                   resultArea.html(videoTag);
                }
                $("[name='filename']").trigger("change"); // 이벤트를 발생 시킬때 trigger
                
             });
          });
      
 
      </script>
      
      <style>
         h4{
            border : 1px solid black;
         }
      </style>
   </head>
   <body>
      <h4>current time(on server) : <%=new Date()%></h4>
      <form action='<%=request.getContextPath()%>/fileStream.do'>
         <select name='filename' multiple size="10">
         <%
         	//
            String contentPath = getServletContext().getInitParameter("contentsPath");
            // 불러오는 경로가 null이거나 비어 있으면 오류발생
            if(contentPath == null || contentPath.isEmpty()) throw new NullPointerException("contentPath파라미터 누락"); 
               File folder = new File(contentPath);
            
         	//폴더의 리스트를 배열로 받는거 
            String[] childArray = folder.list((dir, name) -> {
            	// 서버의 name을 파일의 mimetye을 반환한다. 
               String mime = application.getMimeType(name);
            	// mime이 null이거나 image 나 video로 시작하면  true반환 
               boolean result = (mime != null && mime.startsWith("image/") || mime.startsWith("video/"));
               return result;
            }); 
            //childArray(사진 파일이 있는 파일)의 길이만큼 for문 돌리기 
            for(String child : childArray){
            	boolean match = false;
            	if(savedArray != null){
            		Arrays.sort(savedArray);
            		match = Arrays.binarySearch(savedArray, child)>=0;
            	}
            	String selected = child.equals("selectedFile")?"fileCookie":""; //현재옵션이 이전에 있었던건지 확인해야 함 
               %>
               <option value='<%=child %>' <%=selected%>><%=child %></option>
               <%
            }
        
            
            
         %>
         
<!--          선택한 파일이 계속 남아 있도록  -->
<!--          쿠키가 유지될수 있도록 만들어주기 -->
<!--          쿠키를 어디에다가 저장하고 , 어디에서 저장된 쿠키를 복원할 것인가?? -->
<!--          // 쿠키를 저장하는거 쿠키는 클라이언트에서 저장을 해야 한다?? -->
         </select>
  		<input type='submit' value='submit'/>
      </form>
      <div id="resultArea">
      
      </div>
   </body>
</html>
      