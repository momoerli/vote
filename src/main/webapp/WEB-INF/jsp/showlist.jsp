<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>投票信息展示</title>
</head>
<body>
	<table border="1" >
		<tr>
			<th>投票ID</th>
			<th>投票标题</th>
			<th>选项数量</th>
			<th>投票总数</th>
			<th>发起人</th>
		</tr>
		<c:forEach items="${sessionScope.showlist}" var="slist">
			<tr >
				<td><c:out value="${slist.vote_id}"></c:out></td>
				<td><c:out value="${slist.vote_title}"></c:out></td>
				<td><c:out value="${slist.vote_sum}"></c:out></td>
				<td><c:out value="${slist.choose_count}"></c:out></td>
				<td><c:out value="${slist.user_name}"></c:out></td>
			</tr>
	
		</c:forEach>
	</table>

</body>
</html>