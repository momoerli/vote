<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>
<!DOCTYPE html>
<html>
<head>

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

.bottom_div { /* 进度条底部div */
	width: 400px;
	height: 17px;
	border: 1px solid #98AFB7;
	border-radius: 5px;
	margin-top: 10px;
}

.top_div { /* 进度条顶部div */
	width: 0px;
	height: 17px;
	background: #5EC4EA;
}
</style>
</head>
<body>
	<div style="margin-left: 280px;">
		<img src="${ctx}/images/logo.gif" />
	</div>

	<div style="margin-left: 280px; width: 900px; height: 155px;">
		<div style="width: 900px; height: 25px; background: #eeeeee">

			<label>您好，管理员${user.user_name}</label> <label
				style="margin-left: 40px"><img src="${ctx}/images/new.gif" /><a
				href="${ctx}/">返回列表</a></label>
			<%-- 		<c:if test="${user.user_type==1}">
		<label style="margin-left:40px"><img src="images/addnew.gif"/><a href="javascript:void(0)" style="color:grey" title="">添加新投票</a></label>
		<label style="margin-left:40px"><img src="images/edit.gif"/><a href="#">维护</a></label>	
		</c:if> 
	    <input type="text" name="search" style="margin-left:180px"/>
		<input type="button" value="搜索" />--%>
		</div>


		<table>
			<c:forEach items="${infolist}" var="info" varStatus="index">
				<c:if test="${index.count==1}">
					<p
						style="font-size: 20px; padding-top: 20px; padding-left: 20%; font-weight: bold">${info.vote_title}</p>
				</c:if>
				<tr>
					<td>${index.count}.</td>
					<td>${info.choose_info}</td>
					<td><div class="bottom_div">
							<div id="bar${index.count}" class="top_div">
								<center>${Math.round((info.choose_num/info.ticket_sum)*100)}%</center>
							</div>
						</div></td>
					<td><p style="font-size: 12px">票数：${info.choose_num}</p></td>
				</tr>
				<input type="hidden" id="choose_num${index.count}"
					value="${info.choose_num}" />
				<input type="hidden" id="ticket_sum${index.count}"
					value="${info.ticket_sum}" />
			</c:forEach>

		</table>
		<input type="button" value="返回" onclick="Toadmin()" />




		<p>当前在线人数:${count}</p>
		<div style="margin-top: 20px; width: 900px;">
			<hr />
			
		</div>

	</div>


</body>
</html>

<script>
<!-- 进度条  使用了jquery的animate动画效果 -->
	$(function() {
		setTimeout(function() {
			var count = $
			{
				infocount
			}
			;
			for (var i = 1; i <= count; i++) {
				var choose_num = $("#choose_num" + i + "").val();
				var ticket_sum = $("#ticket_sum" + i + "").val();
				var num = Math.round((choose_num / ticket_sum) * 100);
				var a = num;
				console.info(num);
				$("#bar" + i).animate({
					width : num + '%'
				}, 1000);
			}
		}, 50);
	})

	function Toadmin() {
		window.location.href = "${ctx}/";
	}
</script>