<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="root" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
	<title>이미지 보여주기</title>
</head>
<body>
    <c:if test="${not empty myImg}">
    <div id="result" contentEditable="true">
        <h1>결과입니다.</h1>
        <p>되니??</p>
        <img src="data:image/png;base64,${myImg}" alt="Image" style="width:30%;height:30%;" />
    </div>
    </c:if>
</body>
</html>