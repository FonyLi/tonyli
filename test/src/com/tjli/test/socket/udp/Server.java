package com.tjli.test.socket.udp;

import java.net.DatagramPacket;
import java.net.DatagramSocket;

public class Server {

	public Server() {
		
		try
		{

			DatagramSocket socket = new DatagramSocket(12346);
	

			
			byte[] receive = new byte[1024];
			DatagramPacket packet = new DatagramPacket(receive , 1024);
	
			while(true)
			{

				socket.receive(packet);
				System.out.println("Handling client at " + packet.getAddress().getHostAddress()
				    + " on port " + packet.getPort());

				
				receive[0] = 't';
				receive[1] = 'j';
				receive[2] = 'l';
				receive[3] = 'i';
				socket.send(packet);
				packet.setLength(1024);
			}
		}
		catch(Exception e)
		{
			
		}
	}
	
	public static void main(String[] args) {
		new Server();
	}
}
