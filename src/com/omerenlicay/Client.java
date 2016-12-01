package com.omerenlicay;

import java.io.*;
import java.net.Socket;

public class Client {

	
	
	public static void main(String[] args) {
		Socket istemciSocket=null;
		DataInputStream is=null;
		PrintStream os=null;
		DataInputStream inputLine=null;
		
		try {
			
			istemciSocket=new Socket("127.0.0.1", 1234);
			os=new PrintStream(istemciSocket.getOutputStream());
			is=new DataInputStream(istemciSocket.getInputStream());
			inputLine= new DataInputStream(new BufferedInputStream(System.in));
			
		}catch(Exception e)
		{
			System.err.println(e);
		}
		if(istemciSocket!=null && is!=null && os!=null){
		try{
			
			String line;
			os.println(inputLine.readLine());
			while((line=is.readLine())!=null)
			{
				System.out.println(line);
				if(line.indexOf("ACK")!=-1){
					break;
				}
				os.println(inputLine.readLine());
			}
			os.close();
			is.close();
			istemciSocket.close();
			
		}catch(Exception e)
		{
			System.err.println(e);
		}
		}
			
	}
}
