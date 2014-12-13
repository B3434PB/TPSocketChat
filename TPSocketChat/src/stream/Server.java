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
  
	//Repertorier l ensemble des clients connectes au serveur
	public static LinkedList<ClientThread> clients= new LinkedList<ClientThread>();
	
	
 	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/
	public static void main(String args[]){ 
        ServerSocket listenSocket;
        PrintWriter out = null;
        BufferedReader in = null;
        int nbClients=1;
        
	    //On verifie qu on juste un numero de port en parametre    
	  	if (args.length != 1) {
	          System.out.println("Usage: java EchoServer <EchoServer port>");
	          System.exit(1);
	  	}
		try 
		{
			listenSocket = new ServerSocket(Integer.parseInt(args[0])); //port 
			
			
			System.out.println("Server ready..."); 
			
			while (true) 
			{
				//Attends qu un client se connecte
				Socket clientSocket = listenSocket.accept();
				System.out.println("Le client n° "+nbClients+" est connecté.");
	    		nbClients++;
	    		
				//On instancie un flux de sortie vers le client qui vient de se connecter
				out = new PrintWriter (clientSocket.getOutputStream());
				out.println("Vous etes connectés au server : "+InetAddress.getLocalHost());
				
				//Pour vider le buffer
				out.flush();
				
				
				//Lorsqu un client souhaite se connecter au server on creer un objet clientThread
				//pour communiquer avec le client
				ClientThread ct = new ClientThread (clientSocket, nbClients);
				
				//On ajoute le client a la liste de clients
				clients.add(ct);
				
				//On affiche tous les clients
				
				
				if (clients.size()!=0)
				{
					//System.out.println("Ajout !");
					
					ListIterator<ClientThread> iter = Server.clients.listIterator();
				    while (iter.hasNext())
				    {
				    	System.out.println("Connexion from:" +iter.next().getClientSocket().getInetAddress());
				    }//while
				}//if
				
				//On met le statut du thread a ready
				ct.start();
			}//while
	    }//try
		catch (Exception e) 
		{
			System.err.println("Error in EchoServer:" + e);
	    }
	}//main
	
  }//class

