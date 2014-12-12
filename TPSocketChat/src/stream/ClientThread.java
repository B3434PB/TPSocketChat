
/***
 * ClientThread
 * Example of a TCP server
 * Date: 14/12/08
 * Authors:
 */
package stream;

import java.io.*;
import java.net.*;
import java.util.ListIterator;

public class ClientThread extends Thread {
	
	private Socket clientSocket;
	
	ClientThread (Socket s) {
		this.clientSocket = s;
	}

 	/**
  	* receives a request from client then sends an echo to the client
  	* @param clientSocket the client socket
  	**/
	public void run() {
    	  try {
    		BufferedReader socIn = null;
    		
    		socIn = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));    
    		
    		PrintStream socOut = new PrintStream(clientSocket.getOutputStream());
    		
    		while (true) {
    		  String line = socIn.readLine();
    		  
    		  
    		  ListIterator<ClientThread> iter = Server.clients.listIterator();
			    while (iter.hasNext())
			    {
			    	//socOut = iter.next().getClientSocket().getOutputStream();
			    	System.out.println(iter.next().getClientSocket().getInetAddress());
			    }
    		  socOut.println(line);
    		}
    	} catch (Exception e) {
        	System.err.println("Error in EchoServer:" + e); 
        }
       }
	
	public Socket getClientSocket()
	{
		return clientSocket;
	}
  
  }