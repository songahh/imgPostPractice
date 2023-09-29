<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>이미지 보여주기</title>
</head>
<body>
    <h1>결과입니다.</h1>
    <div>
        <img src="data:image/jpeg;base64,${java.util.Base64.getEncoder().encodeToString(myImg)}" alt="Image" />
    </div>
</body>
</html>