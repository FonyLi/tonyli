package com.tjli.test.socket.udp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.InterruptedIOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Client {
	
	public Client()
	{
		try
		{
			// 1. 构造UDP DatagramSocket对象
			DatagramSocket socket = new DatagramSocket();
		
			// 2。指定timeout时间，防止进入无限等待状态
			socket.setSoTimeout(1000);
		
			BufferedReader line = new BufferedReader(new InputStreamReader(System.in));
			
			String sl = line.readLine();
			// 3. 构造收发的报文对象
			DatagramPacket sendPacket = new DatagramPacket(sl.getBytes(),
					sl.length(), InetAddress.getByName("127.0.0.1"), 12346);
			
			
			byte[] receive = new byte[30];
			DatagramPacket receivePacket =
			    new DatagramPacket(receive, 30);
		
			// 4.指定尝试的次数
			int tries = 0;
			boolean receivedResponse = false;
			 do
			{
				socket.send(sendPacket);
				try
				{
					socket.receive(receivePacket);
			 
					if(!receivePacket.getAddress().equals(InetAddress.getByName("127.0.0.1")))
					{
						throw new IOException("Received packet from an unknown source");
					}
					receivedResponse = true;
					
					System.out.println(receive.toString());
				}
				catch(InterruptedIOException e)
				{
					tries += 1;
					System.out.println("Timed out, " + (20 - tries) + "");
				}
			}while((!receivedResponse) && (tries < 10));
		
			// 根据是否接收到报文进行反馈
			if(receivedResponse)
			{
				System.out.println("Received: " + new String(receivePacket.getData()));
			}
			else
			{
				System.out.println("No response -- giving up.");
			}
		
			// 5. 关闭socket
			socket.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void main(String[] args) {
		new Client();
	}

}
