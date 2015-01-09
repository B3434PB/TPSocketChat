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
import java.util.ArrayList;
import java.util.ListIterator;
import java.util.TreeMap;

import javax.swing.DefaultListModel;
import javax.swing.ListModel;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.File;


public class Server  {
  
	//Repertorier l ensemble des clients connectes au serveur
	static LinkedList<ServerThread> threads;
	static ArrayList<String> messages;
	static ArrayList<String> clients;
	private static String port="";
 	/**
  	* main method
	* @param EchoServer port
  	* 
  	**/
	public static void main(String args[]){ 
        ServerSocket listenSocket;
        PrintWriter out = null;
        BufferedReader in = null;
        
        threads = new LinkedList<ServerThread>();
        clients = new ArrayList<String>();
        clients.add("ALL");
        messages= new ArrayList<String>();
        
        IHMServer settings = new IHMServer();
        
        while (settings.getActivite())
        {
        	System.out.print("");
        }
        port=settings.getPortNumber();
        boolean permanent=settings.getPermanent();
        settings.dispose();
	    
		try 
		{
			listenSocket = new ServerSocket(Integer.parseInt(port)); //port 
			
			
			System.out.println("Server ready..."); 
			
			while (true) 
			{
				//Attends qu un client se connecte
				Socket clientSocket = listenSocket.accept();
	    		
				//On instancie un flux de sortie vers le client qui vient de se connecter
				out = new PrintWriter (clientSocket.getOutputStream());
				
				/* On affiche tous les clients connectés */
				if (clients.isEmpty()==false)
				{
					out.println(clients.size());
					ListIterator<String> iter = Server.clients.listIterator();
				    while (iter.hasNext())
				    {
				    	//socOut = iter.next().getClientSocket().getOutputStream();
				    	out.println("%insert%"+iter.next());
				    }
				}
				
				/* On affiche l'historique */
				if (permanent)
				{
					
				}
				else
				{
					if (messages.isEmpty()==false)
					{
						ListIterator<String> iter = Server.messages.listIterator();
					    while (iter.hasNext())
					    {
					    	//socOut = iter.next().getClientSocket().getOutputStream();
					    	out.println(iter.next());
					    }
					}
				}
				
				out.println("Server > Me : You are connected to "+InetAddress.getLocalHost()+" ! ");
				
				//Pour vider le buffer
				out.flush();
				
				
				//Lorsqu un client souhaite se connecter au server on creer un objet ServerThread
				//pour communiquer avec le client
				ServerThread ct = new ServerThread (clientSocket);
				threads.add(ct);
				
				
				//On met le statut du thread a ready
				ct.start();
			}//while
	    }//try
		catch (Exception e) 
		{
			System.err.println("Error in EchoServer:" + e);
			e.printStackTrace();
	    }
	}//main
	
	static public void EnvoyerMessage(String message)
    {
 	   for(int i = 0; i < threads.size(); i++)
 	   {
 		   threads.get(i).Envoyer(message);
 	   }
    }
	
	
  }//class