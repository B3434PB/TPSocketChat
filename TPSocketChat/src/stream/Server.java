


/***
 * EchoServer
 * Example of a TCP server
 * Date: 10/01/04
 * Authors:
 */
package stream;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.ListIterator;


public class Server  {
  
	public static LinkedList<ClientThread> clients= new LinkedList<ClientThread>();
	
	
 	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/
       public static void main(String args[]){ 
        ServerSocket listenSocket;
        //clients = new LinkedList<ClientThread>();
        
  	if (args.length != 1) {
          System.out.println("Usage: java EchoServer <EchoServer port>");
          System.exit(1);
  	}
	try {
		listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port 
		System.out.println("Server ready..."); 
		while (true) {
			//Attends qu un client se connecte
			Socket clientSocket = listenSocket.accept();
			System.out.println("Connexion from:" + clientSocket.getInetAddress());
			
			//Lorsqu un client souhaite se connecter au server on creer un objet clientThread
			ClientThread ct = new ClientThread (clientSocket);
			
			//On ajoute le client a la liste de client
			clients.add(ct);
			
			//On affiche tous les clients
			
			
			if (clients.size()!=0)
			{
				//System.out.println("Ajout !");
				
				ListIterator<ClientThread> iter = Server.clients.listIterator();
			    while (iter.hasNext())
			    {
			    	System.out.println(iter.next().getClientSocket().getInetAddress());
			    }
			}
			
			ct.start();
		}
        } catch (Exception e) {
            System.err.println("Error in EchoServer:" + e);
        }
      }
  }