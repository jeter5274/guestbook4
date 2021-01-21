<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<body>
		<form action="${pageContext.request.contextPath}/guestbook/add" method="post">
			<table border="1">
				<tr>
					<td>이름</td>
					<td><input type="text" name="name" value=""></td>
					<td>비밀번호</td>
					<td><input type="password" name="password" value=""></td>
				</tr>
				<tr>
					<td colspan="4"> <textarea name="content"></textarea> </td>
				</tr>
				<tr>
					<td colspan="4"> <button type="submit">확인</button></td>
				</tr>
			</table>
		</form>
		<br>

		<c:forEach items="${guestbookList}" var="gbVo"> <%-- ${guestbookList} -> ${requestScope.guestbookList} --%>
			<table border="1">
				<tr>
					<td>${gbVo.no}</td>
					<td>${gbVo.name}</td>
					<td>${gbVo.reg_date}</td>
					<td><a href="${pageContext.request.contextPath}/guestbook/deleteForm?no=${gbVo.no}">삭제</a></td>
				</tr>
				<tr>
					<td colspan="4"> ${gbVo.content} </td>

				</tr>
			</table>
			<br>
		</c:forEach>

	</body>
</html>