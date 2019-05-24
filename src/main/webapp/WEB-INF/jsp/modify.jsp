<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-1.x.js">
	
</script>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>  --%>
<style type="text/css">
#choice2 {
	color: #99CCFF;
}

p {
	font-size: 8px;
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

			<label>您好，管理员${user.user_name}</label> <label
				style="margin-left: 40px"><img src="images/new.gif" /><a
				href="admin.jsp">返回列表</a></label>
			<c:if test="${user.user_type==1}">
				<label style="margin-left: 40px"><img
					src="images/addnew.gif" /><a href="#">添加新投票</a></label>
				<label style="margin-left: 40px"><img src="images/edit.gif" /><a
					href="#">维护</a></label>
			</c:if>
			<input type="text" name="search" style="margin-left: 180px" /> <input
				type="button" value="搜索" />
		</div>

		<form action="VoteServlet?flag=modify&vote_id=${vote_id}"
			method="post" onsubmit="return check();">
			<table id="modify_table">
				<c:forEach items="${infolist}" var="info" varStatus="index">
					<c:if test="${index.count==1}">
						<tr>
							<td>标题:</td>
							<td><input type="text" value="${info.vote_title}" name="vote_title"/></td>
							<%-- <td><input type="hidden" value="${info.vote_title}"
								name="vote_title" /></td> --%>
						</tr>
					</c:if>
					<tr>
						<td>${index.count}.</td>
						<td><input type="text" value="${info.choose_info}"
							id="line${index.count}" name="line" /></td>
						<td><a href="javascript:delRow(${index.count})"
							class="del_class">删除</a></td>
						<td><input type="hidden" value="${info.temp_id}" name="id" /></td>
					</tr>


					<!-- 用于计算list的长度，  若使用fn 则要先导入 -->
					<%-- <%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %> --%>
					<%-- <input type="hidden" value="${fn:length(infolist)}" id="listlength"/> --%>
					<c:if test="${index.last}">
						<input type="hidden" value="${index.count}" id="listlength" />
					</c:if>
					<!-- 用于计算list的长度 -->
				</c:forEach>
				<!-- 用于存储选项剩余数量，在修改时给后台做对照  -->
				<input type="hidden" value="" name="num" id="nowlength" />
				<!-- 用于存储选项剩余数量，在修改时给后台做对照  -->

			</table>
			<input type="submit" value="确定" /> <input type="button" value="增加选项"
				onclick="addRow()" /> <input type="button" value="返回"
				onclick="Toadmin()" /> <input type="button" value="删除"
				onclick="deleteinfo()" />
		</form>

		<p>当前在线人数:${count}</p>
		

	</div>


</body>
</html>

<script>
	$(function() {

		window.nowlength = $("#listlength").val()/* 用于计算选项最大数量，在修改时给后台做对照 */
	})

	function delRow(index) {
		var Maxlength = $("#listlength").val();
		$("#line" + index).parent().parent().remove();
		nowlength--;
		$("#nowlength").val(nowlength);

		if ($("#modify_table input:text").length == 2) {/* 用于计算是否要隐藏 删除按钮 */
			$(".del_class").css('display', 'none');
		}
	}

	function Toadmin() {
		window.location.href = "admin.jsp";
	}
	function deleteinfo() {
		if (confirm("是否确认删除？")) {
			window.location.href = "VoteServlet?flag=deleteinfo&vote_id=" + $
			{
				vote_id
			}
			;
		} else {
			history.go(0);
		}
	}

	function addRow() {
		nowlength++;
		var newRow = '<tr><td>'
				+ nowlength
				+ '.</td><td><input type="text" id="line'+nowlength+'" name="line"/></td><td><a href="javascript:delRow('
				+ nowlength + ')" class="del_class">删除</a></td>';
		$("#modify_table").append(newRow);
		$("#nowlength").val(nowlength);
		$("#listlength").val(nowlength);
	}

	function check() {
		var flag = true;
		$("input[name='line']").each(function() {
			if ($(this).val() == "") {
				flag = false;
				alert("选项内容不能为空");
			}
		});
		return flag;
	}
</script>