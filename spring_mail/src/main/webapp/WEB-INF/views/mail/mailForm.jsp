<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>메일 쓰기</title>
</head>
<body>
	<form action="send" method="post" enctype="multipart/form-data">
		받는 사람 : <input type="email" name="receiber"> <br/>
		보내는 사람: <input type="email" name="sender"> <br/>
		제목 : <input type="text" name="title"> <br/>
		내용 : <textArea type="content" row="10" cols="50"></textArea> <br/>
		첨부파일 :<input type="file" name="file"> <br/>
		<input type="submit" name="메일보내기"> 
	</form>
</body>
</html>