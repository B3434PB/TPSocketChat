/***
 * ServerThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */
/**
 * Package contenant l'ensemble des classes
 */
package stream;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.ListIterator;

/**
 * Processus serveur associ� � un Client
 * @author Papin Bayart
 *
 */
public class ServerThread extends Thread 
{
	/**
	 * Socket associ�e au client
	 */
	private Socket clientSocket;
	
	/**
	 * Constructeur de ServerThread
	 * @param s : Socket associ�e au client
	 */
	ServerThread (Socket s) {
		this.clientSocket = s;
	}

 	/**
  	* Receives a request from client then sends an echo to the client
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
    			
    			
	    		//On lit ce que le client �crit
    			lineServer = socIn.readLine();
    			
    			if(lineServer.startsWith("%insert%"))
    			//code pour ajouter un client connect�
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
    			//code pour retirer un client d�connect�
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

  	          	//On envoie le message � tous les clients
  	          	Server.EnvoyerMessage(lineServer);
  	          	
  
	    		
    		}//while
    	}//try
    	catch (Exception e) 
    	{
        	System.err.println("Error in EchoServer:" + e); 
        }//catch
    }//main
	
	
	/**
	 * Accesseur � clientSocket
	 * @return Socket clientSocket
	 */
	Socket getClientSocket()
	{
		return clientSocket;
	}
	
	/**
	 * Envoie le message en param�tre � un Client sous forme de flux.
	 * @param message : message envoy� par un client
	 */
	public void Envoyer(String message)
	{
		try {
			PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
			socOut.println(message);
			socOut.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}