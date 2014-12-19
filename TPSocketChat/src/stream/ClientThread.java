/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */
package stream;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.ListIterator;

public class ClientThread extends Thread 
{
	
	private BufferedReader socIn;

	//static LinkedList<String> messages= new LinkedList<String>();
	
	ClientThread (BufferedReader aSocIn) {
		this.socIn = aSocIn;
	}

 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	public void run() 
	{
    	 
    		//BufferedReader socIn=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
    		//PrintStream socOut=new PrintStream(clientSocket.getOutputStream());
    		
    		String lineClient;
    		while (true) 
    		{
    			 try 
    	    	  {
	    		
	    		//On lit et on affiche ce que le server envoie
	        	//lineServer=socIn.readLine();
	        	//socOut.println(lineServer);
	    		
    			lineClient = socIn.readLine();
    			//socOut.println("nouveau message");
    			System.out.println(lineClient);
    			
	    		//On ajoute le message a la conversasion commune a tous les clients
	          /*	messages.add(lineClient);
	          	
	          	if (messages.size()!=0)
	  			{
	          		
	  			    //System.out.println("fail : "+messages.getLast());
	  			    
	  			}*/
	    		
	          	//On envoie le message aux autres clients
	    		  
	    		  /*ListIterator<ClientThread> iter = Server.clients.listIterator();
				    while (iter.hasNext())
				    {
				    	//socOut = iter.next().getClientSocket().getOutputStream();
				    	//System.out.println(iter.next().getClientSocket().getInetAddress());
				    }*/
    	    	  	}//try
    		    	catch (Exception e) 
    		    	{
    		        	System.err.println("Error in EchoServer:" + e); 
    		        }//catch
	    		
    		}//while
    	
    }//main
	
	
	/*Socket getClientSocket()
	{
		return clientSocket;
	}*/
	
  
}