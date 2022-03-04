package model;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;
import configuration.ConfigGlobal;

public class ServerGame {
	ServerSocket ss;
	private int numPlayer;
	private ServerSideConnexion player1, player2;

	public static void main(String[] args)
	{
		ServerGame server = new ServerGame();
		server.acceptConnexion();
	}
	
	public ServerGame() {
		numPlayer = 0;

		try {
			ss = new ServerSocket(ConfigGlobal.NO_PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void acceptConnexion()
	{
		try
		{
			while(numPlayer < 2)
			{
				System.out.println("Waiting for player...");
				Socket s = ss.accept();
				System.out.println("Player " + s.getInetAddress().getHostAddress() + " connected");
				
				numPlayer++;
				ServerSideConnexion ssc = new ServerSideConnexion(s, numPlayer);
				
				if(numPlayer == 1)
				{
					player1 = ssc;
				}
				else
				{
					player2 = ssc;
				}
				
				Thread t = new Thread(ssc);
				t.start();
			}
			
			System.out.println("2 joueurs maximum. Bye !");
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}
	}

	public class ServerSideConnexion implements Runnable {
		private Socket socket;
		private InputStreamReader dataIn;
		private OutputStreamWriter dataOut;
		private BufferedWriter bw;
		private int playerId;

		public ServerSideConnexion(Socket s, int id) {
			socket = s;
			playerId = id;

			try {
				dataIn = new InputStreamReader(socket.getInputStream());
				dataOut = new OutputStreamWriter(socket.getOutputStream());
				
				bw = new BufferedWriter(dataOut);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				bw.write(String.valueOf(playerId));
				bw.flush();
				
				socket.close();
			
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}
}