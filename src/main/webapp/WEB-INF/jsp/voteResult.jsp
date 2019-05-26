
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>投票结果</title>
</head>
<body>
<script>

if(${flag == 0} ){
    alert('投票失败'); history.go(-1);
}else{
    var r=confirm('是否查看投票结果');
    if(r==true) {
        window.location.href='${ctx}/vote/showVote?vote_id=${vote_id}';
    }else{
        window.location.href='${ctx}/';
    }
}



</script>
</body>
</html>
