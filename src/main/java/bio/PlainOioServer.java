package bio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

public class PlainOioServer {
	public void serve(int port) throws IOException {
		System.out.println("server 启动");
        final ServerSocket socket = new ServerSocket(port);     //1
        System.out.println("socket 创建");
        try {
            for (;;) {
                final Socket clientSocket = socket.accept();    //2
                System.out.println("Accepted connection from " + clientSocket);

                new Thread(new Runnable() {                        //3
                    public void run() {
                        OutputStream out;
                        try {
                            out = clientSocket.getOutputStream();
                            out.write("Hi!\r\n".getBytes(Charset.forName("UTF-8")));     
                            Thread.sleep(5000);
                            out.flush();
                            clientSocket.close();                //5

                        } catch (Exception e) {
                            e.printStackTrace();
                            try {
                                clientSocket.close();
                            } catch (IOException ex) {
                                // ignore on close
                            }
                        }
                    }
                }).start();                                        //6
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	public static void main(String[] args) {
		final PlainOioServer server = new PlainOioServer();
	    try {
			server.serve(8088);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
						
		
	}
}
