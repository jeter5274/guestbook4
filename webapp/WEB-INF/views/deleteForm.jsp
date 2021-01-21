<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	
	<body>
		<c:if test="${delResult == 0}"> <%-- ${requestScope.delResult == 0} -> ${delResult == 0} --%>
			비밀번호를 잘 못 입력하셨습니다. 다시 입력해주세요.
		</c:if>
		
		<form action="${pageContext.request.contextPath}/guestbook/delete" method="post">
			비밀번호 <input type="text" name="password">
			<input type="hidden" name="no" value="${param.no}"> 
			<button type="submit">확인</button><br>
			
			<a href="${pageContext.request.contextPath}/guestbook/addList">메인으로 돌아가기</a>
		</form>
	</body>
</html>