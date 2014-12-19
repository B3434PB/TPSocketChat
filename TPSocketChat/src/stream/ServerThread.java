/***
 * ServerThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */
package stream;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class ServerThread extends Thread 
{
	
	private Socket clientSocket;
	private int numero;

	
	ServerThread (Socket s, int num) {
		this.clientSocket = s;
		this.numero = num;
	}

 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	public void run() 
	{
    	  try 
    	  {
    		BufferedReader socIn=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
    		PrintStream socOut=new PrintStream(clientSocket.getOutputStream());
    		socIn = new BufferedReader(
        			new InputStreamReader(clientSocket.getInputStream()));

            String lineServer;
    		while (true) 
    		{
    			
	    		
	    		//On lit et on affiche ce que le server envoie
	        	//lineServer=socIn.readLine();
	        	//socOut.println(lineServer);
	    		
    			lineServer = socIn.readLine();
    			
    			//On ajoute le message a la conversasion commune a tous les clients
  	          	Server.messages.add(lineServer);
    			
  	          Server.EnvoyerMessage(lineServer);
	    		
	          	//On envoie le message aux autres clients
	    		  
	    		  /*ListIterator<ServerThread> iter = Server.clients.listIterator();
				    while (iter.hasNext())
				    {
				    	//socOut = iter.next().getClientSocket().getOutputStream();
				    	//System.out.println(iter.next().getClientSocket().getInetAddress());
				    }*/
	    		  
	    		
    		}//while
    	}//try
    	catch (Exception e) 
    	{
        	System.err.println("Error in EchoServer:" + e); 
        }//catch
    }//main
	
	
	Socket getClientSocket()
	{
		return clientSocket;
	}
	
	int getNum()
	{
		return numero;
	}
	
	public void Envoyer(String message)
	{
		try {
			PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
			socOut.println(message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}