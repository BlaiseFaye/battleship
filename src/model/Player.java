package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import configuration.ConfigGlobal;

public class Player  {
	private int id;
	private ClientSideConnexion csc;
	
	public Player()
	{	
		this.connectServer();
	}
	
	public void connectServer()
	{
		csc = new ClientSideConnexion();
	}
	
	public int getId()
	{
		return this.id;
	}
	
	private class ClientSideConnexion {
		private Socket socket;
		private InputStreamReader dataIn;
		private OutputStreamWriter dataOut;
		
		public ClientSideConnexion()
		{
			try
			{
				socket = new Socket("192.168.10.104", ConfigGlobal.NO_PORT);
				dataIn = new InputStreamReader(socket.getInputStream());
				dataOut = new OutputStreamWriter(socket.getOutputStream());
				
				BufferedReader br = new BufferedReader(dataIn);
				BufferedWriter bw = new BufferedWriter(dataOut);
				
				id = Integer.parseInt(br.readLine());
				
				socket.close();
			} catch(IOException e)
			{
				e.printStackTrace();
			}
		}
	}
}
