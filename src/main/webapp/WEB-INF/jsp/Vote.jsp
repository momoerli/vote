<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.x.js">
	
</script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
#choice2 {
	color: #99CCFF;
}

p {
	font-size: 12px;
}

a {
	text-decoration: none;
	color: #3399FF;
}

a:hover {
	text-decoration: underline;
}
</style>
</head>
<body>
	<div style="margin-left: 280px;">
		<img src="images/logo.gif" />
	</div>

	<div style="margin-left: 280px; width: 900px; height: 155px;">
		<div style="width: 900px; height: 25px; background: #eeeeee">

			<label>您好，管理员${user.user_name}</label> 
			<label style="margin-left: 40px">
				<img src="images/new.gif" />
				<a href="admin.jsp">返回列表</a>
			</label>
			<c:if test="${user.user_type==1}">
				<label style="margin-left: 40px">
					<img src="images/addnew.gif" />
					<a href="#">添加新投票</a>
				</label>
<!-- 				<label style="margin-left: 40px"><img src="images/edit.gif" /> -->
<!-- 					<a href="#">维护</a> -->
<!-- 				</label> -->
			</c:if>
			<input type="text" name="search" style="margin-left: 180px" /> <input
				type="button" value="搜索" />
		</div>




		<!-- 投票界面 -->
		<div id="vote_interface" >
			<form action="VoteServlet?flag=Vote" method="post">
				<table>
					<c:forEach items="${infolist}" var="info" varStatus="index">
						<c:if test="${index.count==1}">
							<input type="hidden" name="vote_type" value="${info.vote_type}" />
							<input type="hidden" name="vote_id" value="${info.vote_id}" />
							<tr>
								<p style="font-size: 18px">${info.vote_title}</p>
							</tr>
							<tr>
								<p>共有${infocount}个选项，已有${info.vote_sum}个网友参与了投票</p>
							</tr>
						</c:if>
						<tr>
							<td>${index.count}.</td>
							<!-- 多选框 -->
							<c:if test="${info.vote_type==1}">
								<td><input type="checkbox" name="${index.count}" value="${info.temp_id}" />${info.choose_info}</td>
							</c:if>
							<!-- 单选框 -->
							<c:if test="${info.vote_type==0}">
								<td><input type="radio" name="checkb" value="${info.temp_id}" />${info.choose_info}</td>
							</c:if>
							<td><p>已投票人数:${info.choose_num}</p></td>
						</tr>
					</c:forEach>
				</table>
				<input type="submit" value="提交" /> <input type="reset" value="重置" />
			</form>
		</div>
		<!-- 投票界面 -->
		<div style="margin-top: 50px; width: 900px; position: fixed; bottom: 0;">
			<p>当前在线人数:${count}</p>
			<hr />
		</div>

	</div>

	<script type="text/javascript">
		/*   $(document).ready(function(){
		window.location.href="VoteServlet?flag=showlist";   
		 }); */

		var rowCount = 1;
		$(function(e) {
			$("#list_div table tr:even").css("background-color", "#FFFFCC");

		});

		function addInfo() {
			$("#addinfo_div").css('display', 'inline');
			$("#list_div").css('display', 'none');
		}
		function addRow() {
			rowCount++;
			var newRow = '<tr id="option'+rowCount+'"><td>选项'
					+ rowCount
					+ '&nbsp;:&nbsp;<input type="text" name="option'+rowCount+'" style="width:200px" /><a href="#" onclick=delRow('
					+ rowCount + ')>&nbsp;删除</a></td></tr>';
			$("#addinfo_tbody").append(newRow);
			$("#rowc").val(rowCount);
		}
		function delRow(rowIndex) {
			$("#option" + rowIndex).remove();
			rowCount--;
			$("#rowc").val(rowCount);
		}
	</script>

</body>
</html>