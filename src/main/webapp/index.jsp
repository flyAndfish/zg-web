<%@page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%  
String path = request.getContextPath();  
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";  
%>  
<html>
<head>
<base href="<%=basePath%>"> 
</head>
<body>
<h2>首页</h2>
<script>
  function jump(){
	  window.location.href="chat/client";
  }
</script>
<div>
   <input type="button" value="聊天系统" onclick="jump()"/>
</div>
</body>
</html>
