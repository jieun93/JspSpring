<%@page import="java.io.InputStream"%>
<%@page import="java.io.FileOutputStream"%>
<%@page import="java.io.FileInputStream"%>
<%@page import="java.io.File"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>05/applicationDesc.jsp</title>
</head>
<body>
	<h4>ServletContext application</h4>
	<pre>
		Context : ex)Context Aware Computing  상황인식컴퓨터이론
		ServletContext : 하나의 서블릿을 기준으로 현재 서블릿의 제어권을 소유한 어플리케이션과 컨테이너에 대한 정보를 캡슐화 ,싱글턴
		현재의 객체 주소 : <%=application.hashCode()%> ,<%=getServletContext().hashCode()%>	
		1. MIME 확보, <%=application.getMimeType("B000210202001301017213.jpg") %>
		2. context와 server에 대한 정보 확보
			서버에 대한 경로 :<%=application.getContextPath() %>
			서버에 대한 정보 :<%=application.getServerInfo() %>
			서블릿 스팩:<%=application.getMajorVersion() %>.<%=application.getMinorVersion() %>
		3.logging (로그데이터를 기록하는거, 서버를 위한 데이터 , 클라이언트에 노출을 시키면 안됨 , 디버깅, 튜닝시 사용, 휘발성 데이터)
			 로그를 기록을 할려면 가공할 메세지 형식이 필요하다. 
			 1. 어디에 기록할지 append  2. 어떤형식  layout 3. 누가 기록 logger 4. 어떤이벤트로 기록할것인지 leber?
			클라이언트에 출력이 안됨  =><%application.log("입의 기록 로그");%>
  ***** 4. 웹 리소스 확보  (!!!가장중요!!!) 
  
	  		 fileSystemPath 	getRealPath(url)
	  		 InputStream     getResourceAsStream(url)
	  		 
			 resource : 자원의 검색 방법 
				1) file system resource : d:/contents/a.jpg, 파일 시스템의 루트부터 시작되는 절대 경로를 통해 접근
				2) class path resource : kr.or.ddit.servlet01.ContentServlet
						클래스 패스 이후의 qualified name 의 형태로 접근.
				3) web resource : http://localhost/webStudy01/images/a.jpg
						웹 상에서 URL의 형태로 접근.
					<%
					//파일의 가져와서 05폴더에 붙이고 싶을때
					// I/O 작업을해야한다. 
					//1. 파일객체를 생성해야한다.
					// url을 기술을 할떄 절대경로를 사용
					//  서버에서 사용할떄ㅑ는  request.getContextPath() 가 필요없다. 
					String url ="/images/B000210202001301017213.jpg";
					String realPath = application.getRealPath(url); // url을 넘겨주면 진짜주소를 주겠다
					File imgFile = new File(realPath);
					out.println(realPath);
					out.println(imgFile.getAbsolutePath()); // 파일시스템의 절대경로
					// 서버사이드 절대 경로 
					File saveFolder = new File(application.getRealPath("/05"));
					File saveFile = new File(saveFolder, "B000210202001301017213.jpg");
					try(
// 						FileInputStream fis = new FileInputStream(imgFile);
						InputStream fis = application.getResourceAsStream(url);// 한번에 입력스트림 넣는거 
						FileOutputStream fos = new FileOutputStream(saveFile);
					){
						 byte[] buffer = new byte[1024];
						 int cnt = -1;
						while((cnt = fis.read(buffer))!= -1){
							fos.write(buffer, 0, cnt);
						}
					}
						
					
					%>	
					
	</pre>
	<img src="<%=request.getContextPath()%>/05/B000210202001301017213.jpg"/>

</body>
</html>