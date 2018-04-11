package bio;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class PlainOioClient {
	public static void main(String args[]) throws Exception {
	    
		final PlainOioClient client = new PlainOioClient();
		
		for(int i=0;i<1000;i++) {
			new Thread(new Runnable() {
				
				public void run() {
		          try {
					  client.client(8088);
					} catch (Exception e) {
						e.printStackTrace();
					}
			   }
			}).start();
		}		
	}
	
	public void client(int port) throws Exception {
		// 要连接的服务端IP地址和端口
	    String host = "127.0.0.1"; 
	    // 与服务端建立连接
	    Socket socket = new Socket(host, port);
	    // 建立连接后获得输出流
	    OutputStream outputStream = socket.getOutputStream();
	    String message="你好  yiwangzhibujian";
	    socket.getOutputStream().write(message.getBytes("UTF-8"));
	    //已经发送数据完毕，后续只能接受数据
	    socket.shutdownOutput();
	    
	    InputStream input = socket.getInputStream();
	    StringBuilder str = new StringBuilder();
	    byte[] b = new byte[1000];
	    int len;
	    while((len=input.read(b))!=-1) {
	    	str.append(new String(b,0,len,"UTF-8"));
	    }
	    System.out.println("get message from server:"+str.toString());
	    
	    input.close();
	    outputStream.close();
	    socket.close();
	}
}
