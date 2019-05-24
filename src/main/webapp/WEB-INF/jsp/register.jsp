<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>注册</title>
<%--<script type="text/javascript" src="js/jquery-1.x.js"> </script>--%>
</head>

<body>
 <div style="margin-left:280px;">
      <img src="images/logo.gif"/>
   </div>
   
   <div style="margin-left:280px;width:900px;height:155px;">
   <div style="width:900px;height:20px;background:#99CCCC">
   <a href="${ctx}/login">返回登陆</a>
   </div>
   <div style="margin-left:150px;margin-top:20px;width:500px;height:250px;border:2px solid #99CCCC">
      <form action="UserServlet?flag=register" method="post" onsubmit="return check();">
		<div style="width:500px;height:30px;background:#99CCCC"><label style="margin-left:20px;margin-top:20px;color:white;">新用户注册</label></div>
	    <table width="90%" cellspacing="15" cellpadding="0">
		  
		  <tr>
		    <td width="25%">用户名：</td>
			<td onkeyup="CanRegister();" width="50%"><input type="text" name="userName" id="userName"/></td>
			<td width="25%"><span id="in_username" style="color: red;  display: none;font-size: 12px;position: absolute;margin-top:-10px;padding-right:100px">此用户已存在</span></td>
		  </tr>
		  <tr>
		    <td>密码：</td>
			<td><input type="password" name="userPassword" id="userPassword"/></td>
		  </tr>
		  <tr>
		    <td>确认密码：</td>
			<td><input type="password" name="surePassword" id="surePassword"/></td>
		  </tr>
		  <tr>
		    <td></td>
			<td ><button type="submit" id="sure" ><img src="images/button_register.gif"></button></td>
		  </tr>
		
		</table>
	  
	  </form>
   </div>
   
   <div style="margin-top:20px;width:900px;">
     <hr/>
	
   </div>
   </div>
</body>
</html>
<script type="text/javascript">
 function check(){
 var userName = $("#userName").val().replace(/\s/g, "");
 var userPassword = $("#userPassword").val().replace(/\s/g, "");
 var surePassword = $("#surePassword").val().replace(/\s/g, "");
 var flag = false;
	if(userName!=null&&userName!=""&&userPassword!=null&&userPassword!=""&&surePassword!=null&&surePassword!=""){
		 if(userPassword==surePassword){
			 flag =  true;
		 }else{
			 flag =  false;
			 alert("两次输入的密码不一致");
		 }
	 }else{
		 alert("注册内容不能为空");
	 }
	 return flag; 
 } 

 function CanRegister(){
	  var userName = $("#userName").val();
	 $.ajax({
		 type : "post",  //请求方式,get,post等
         dataType:'json',//response返回数据的格式
         async : true,  //同步请求  
         url : 'UserServlet',  //需要访问的servlet地址
         data : 'flag=isexist&username='+userName,  //传递到后台servlet的参数,使用getParamater接受
         success:function(data){
        	 var flag = 0;
        	 if(data['exist']==true){
        		 /* alert("此用户已存在"); */
        		 $("#in_username").show();
        		 $("#sure").attr("disabled", true); 
        	 }else{
        		 $("#in_username").hide();
        		 $("#sure").attr("disabled", false); 
        	 }
         },error:function(data){
        	 alert("注册失败");
         }
		 
		 
	 })
 };
 
 
 
</script>


