<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
var websocket = null;  
//判断当前浏览器是否支持WebSocket  
if('WebSocket' in window){  
    websocket = new WebSocket("ws://localhost:8080/zg-web/websocket");  
}  
else{  
    alert('您的浏览器不支持 websocket！')  
}  
      
//连接发生错误的回调方法  
websocket.onerror = function(){  
    setMessageInnerHTML("error");  
};  
      
//连接成功建立的回调方法  
websocket.onopen = function(event){  
    alert("链接成功,欢迎加入聊天室！");  
}  
      
//接收到消息的回调方法  
websocket.onmessage = function(event){  
    setMessageInnerHTML(event.data);  
}  
      
//连接关闭的回调方法  
websocket.onclose = function(){  
    var username=document.getElementById('username').value;  
    setMessageInnerHTML(getNowFormatDate()+" "+username+" 退出了聊天室！");  
}  
      
//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口，server端会抛异常。  
window.onbeforeunload = function(){  
    websocket.close();  
}  
      
//将消息显示在网页上  
function setMessageInnerHTML(innerHTML){  
    document.getElementById('message').innerHTML += innerHTML + '';  
}  
      
//关闭连接  
function closeWebSocket(){  
    websocket.close();  
}  
      
//发送消息  
function send(){  
    var username=document.getElementById('username').value;  
    var message = document.getElementById('text').value;  
    websocket.send(getNowFormatDate()+" "+username+"说: "+message+"<br/>");  
    //发送消息后，发送消息框自动清空
    document.getElementById('text').value="";  
}  
     
function getNowFormatDate() {  
    var myDate = new Date();  
    myDate.getYear();        //获取当前年份(2位)  
    myDate.getFullYear();    //获取完整的年份(4位,1970-????)  
    myDate.getMonth();       //获取当前月份(0-11,0代表1月)  
    myDate.getDate();        //获取当前日(1-31)  
    myDate.getDay();         //获取当前星期X(0-6,0代表星期天)  
    myDate.getTime();        //获取当前时间(从1970.1.1开始的毫秒数)  
    myDate.getHours();       //获取当前小时数(0-23)  
    myDate.getMinutes();     //获取当前分钟数(0-59)  
    myDate.getSeconds();     //获取当前秒数(0-59)  
    myDate.getMilliseconds();    //获取当前毫秒数(0-999)  
    myDate.toLocaleDateString();     //获取当前日期  
    var mytime=myDate.toLocaleTimeString();     //获取当前时间  
   return myDate.toLocaleString( );        //获取日期与时间  
  }     
</script>
</head>
<body>
    <div width="200px" height="900px">
       <div style="border:1px solid blue;width:50%;height:300px;" id="message">
          
       </div>
       <div>
               用户名：<input type="text"  id="username"/><br/>
	        <textarea  style="margin: 0px; height: 100px; width: 948px;" id="text"></textarea><br/>
	        <input type="button" value="发送" onclick="send()"/>
       </div>
    </div>
</body>
</html>