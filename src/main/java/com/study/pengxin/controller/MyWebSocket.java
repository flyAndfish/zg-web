package com.study.pengxin.controller;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;  
     
//该注解用来指定一个URI，客户端可以通过这个URI来连接到WebSocket。类似Servlet的注解mapping。无需在web.xml中配置。  
@ServerEndpoint("/websocket")  
public class MyWebSocket {  
  // 静态变量，用来记录当前在线连接数。应该把它设计成线程安全的。  
  private static int onlineCount = 0;  
     
  // concurrent包的线程安全Set，用来存放每个客户端对应的MyWebSocket对象。若要实现服务端与单一客户端通信的话，可以使用Map来存放，其中Key可以为用户标识  
  private static CopyOnWriteArraySet<MyWebSocket> webSocketSet = new CopyOnWriteArraySet<MyWebSocket>();  
     
  // 与某个客户端的连接会话，需要通过它来给客户端发送数据  
  private Session session;  
  @OnOpen  
  public void onOpen(Session session) {  
    this.session = session;  
    webSocketSet.add(this); // 加入set中  
    addOnlineCount(); // 在线数加1  
    System.out.println("有新连接加入！当前在线人数为" + getOnlineCount());  
  }  
     
  /** 
   * 连接关闭调用的方法 
   */  
  @OnClose  
  public void onClose() {  
    webSocketSet.remove(this); // 从set中删除  
    subOnlineCount(); // 在线数减1  
    System.out.println("有一连接关闭！当前在线人数为" + getOnlineCount());  
  }  
     
  @OnMessage  
  public void onMessage(String message, Session session) {  
    System.out.println("来自客户端的消息:" + message);  
     
    // 群发消息  
    for (MyWebSocket item : webSocketSet) {  
      try {  
        item.sendMessage(message);  
      } catch (IOException e) {  
        e.printStackTrace();  
        continue;  
      }  
    }  
  }  
  @OnError  
  public void onError(Session session, Throwable error) {  
    System.out.println("发生错误");  
    error.printStackTrace();  
  }  
  public void sendMessage(String message) throws IOException {  
    this.session.getBasicRemote().sendText(message);  
    // this.session.getAsyncRemote().sendText(message);  
  }        
  public static synchronized int getOnlineCount() {  
    return onlineCount;  
  }         
  public static synchronized void addOnlineCount() {  
    MyWebSocket.onlineCount++;  
  }      
  public static synchronized void subOnlineCount() {  
    MyWebSocket.onlineCount--;  
  }  
}  