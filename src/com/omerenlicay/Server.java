package com.omerenlicay;

import java.io.DataInputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	
	private static ServerSocket socket;
	
	
	public static void main(String[] args) {
		 String line;
		 
		try{
			
			socket = new ServerSocket(1234);
			Socket client=socket.accept();
			
			PrintWriter pout=new PrintWriter(client.getOutputStream(),true);
			DataInputStream is=new DataInputStream(client.getInputStream());
			
			
			while(true)
			{
				line=is.readLine();
				pout.println(line);
			}
			
		} catch (Exception e) {
			
			System.err.println("Hata: "+e);
		}
	}

}
