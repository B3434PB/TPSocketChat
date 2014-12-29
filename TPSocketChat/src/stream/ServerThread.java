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

	
	ServerThread (Socket s) {
		this.clientSocket = s;
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
    		socIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

            String lineServer;
    		while (true) 
    		{
    			
    			
	    		//On lit ce que le client écrit
    			lineServer = socIn.readLine();
    			
    			if(lineServer.startsWith("%insert%"))
    			//code pour ajouter un client connecté
    			{
    				String name="";
    				for(int i=8;i<lineServer.length();i++)
    				{
    					name+=lineServer.charAt(i);
    				}
    				if(Server.clients.contains(name)==false)
    				{
    					Server.clients.add(name);
    				}
    			}
    			else if(lineServer.startsWith("%remove%"))
    			//code pour retirer un client déconnecté
    			{
    				String name="";
    				for(int i=8;i<lineServer.length();i++)
    				{
    					name+=lineServer.charAt(i);
    				}
    				Server.clients.remove(name);
    			}
    			else if(lineServer.startsWith("%quit%")==false)
    			//Il s'agit d'un message
    			{
    				//On ajoute le message a la conversasion commune a tous les clients
      	          	Server.messages.add(lineServer);
    			}

  	          	//On envoie le message à tous les clients
  	          	Server.EnvoyerMessage(lineServer);
  	          	
  
	    		
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
	
	
	public void Envoyer(String message)
	{
		try {
			PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
			socOut.println(message);
			socOut.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}