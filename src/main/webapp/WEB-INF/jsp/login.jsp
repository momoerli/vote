
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="common.jsp" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

   <script type="text/javascript">
	$(function() {
		//当用户名密码为空时 红字提示
		$(document).ready(function() {
			$("#username").blur(function() {
				if ($("#username").val() == "") {
					$("#in_username").show();
					
				}

			});
			$("#password").blur(function() {
				if ($("#password").val() == "") {
					$("#in_password").show();
				}

			});
			$("#username").focus(function() {
				$("#in_username").hide();
			});

			$("#password").focus(function() {
				$("#in_password").hide();
			});

		});
	});
	
	function check(){
		var username= $("#username").val();
		var userpassword = $("#password").val();
		
		if( username== ""||username == null||userpassword==""||userpassword==null){
			alert("帐户名密码不能为空！");
			return false;
		}else{
			return true;
		}
	}
	
	
	
	
</script>
	
<% 	
	String un = "";
	String pw = "";
	Cookie cookie[] = request.getCookies();
	if(cookie!=null){
		for(Cookie c:cookie){
			if("username".equals(c.getName())){
			un=c.getValue();
		}
			if("userpassword".equals(c.getName())){
				pw=c.getValue();
			}
	}
	}
	

%>
</head>
<body>

   <div style="margin-left:280px;">
      <img src="images/logo.gif"/>
   </div>
   
   <div style="margin-left:280px;margin-top:10px;width:900px;height:155px;background:#99CCFF;"/>
      <img src="images/voteBanner.jpg"/>
	  <form action="${ctx}/doLogin" method="post" style="margin-left:480px;margin-top:-170px;border:1px solid gray;width:420px;height:180px;background-color:white;" onsubmit="return check()">
		  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
		  <label style="margin-left:20px;margin-top:20px">用户登录</label>&nbsp;&nbsp;<img src="images/arrow_down.gif"/>
		  <hr/>
		  <table width="90%" cellspacing="15" cellpadding="0" >
		     <tr>
			    <td width="25%">用户名：</td>
				<td width="50%"><input type="text" name="username" id="username" class="required" value="<%=un %>" /></td>
				<td width="30%"><span id="in_username" style="color: red;  display: none;font-size: 12px;position: absolute;margin-top:-10px;padding-right:100px">用户名不能为空</span></td>
			 </tr>
			
			 <tr>
			    <td width="25%">密 码：</td>
				<td width="50%"><input type="password" name="password" id="password" class="required" value="<%=pw %>" /></td>
				<td width="30%">
					<p id="in_password" style="color: red;  display: none;font-size: 12px;position: absolute;margin-top:-10px;padding-right:100px">密码不能为空 </p>
					<c:choose>
						<c:when test="${empty tips}">
						</c:when>
						<c:otherwise>
							<span style="color: red;"> 用户名或密码错误 </span>
						</c:otherwise>
					</c:choose>
				</td>



			 </tr>
			 <tr>
			   <td width="25%"></td>
			   <td  style="font-size:13px" width="55%"><input type="submit" id="send" value="登录">&nbsp;&nbsp;
			   <a href="${ctx}/register">注册</a>
			   <input type="checkbox" name="save_info" />记住密码
			   </td>
			 </tr>
		  </table>
	  
	  </form>
	 </div>


</body>


</html>