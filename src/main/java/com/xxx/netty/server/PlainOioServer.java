package com.xxx.netty.server;

import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.Charset;

/**
 * 
 * ����: YUGY 
 * ��Ŀ��netty
 * ��˵����Blocking networking without Netty
 * ���ڣ�2018��2��11��
 * ��ע��
 */
public class PlainOioServer {
	public void server(int port) throws Exception{
		//bind server to port
		final ServerSocket socket = new ServerSocket(port);
		try {
			while(true){
				//accept connection
				final Socket clientSocket = socket.accept();
				new Thread(new Runnable() {
					@Override
					public void run() {
						OutputStream out;
						try {
							out = clientSocket.getOutputStream();
							out.write("Hi!Yugy\r\n".getBytes(Charset.forName("UTF-8")));
							out.flush();
							//close connection once message written and flushed
							clientSocket.close();
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}).start();//start thread to begin handing
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			socket.close();
		}
	}
}
