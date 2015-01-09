/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */
package stream;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;

import javax.swing.DefaultListModel;



public class Client {
	
	//Booléen qui indique que l'on doit arrêter la connexion

  /**
  *  main method
  *  accepts a connection, receives a message from client then sends an echo to the client
  **/
    public static void main(String[] args) throws IOException {
    	
    	String pseudo=("");
    	String port=("");
    	String host=("");
        Socket echoSocket = null;
        PrintStream socOut = null;
        BufferedReader socIn = null;
        
        
        
        /* Port et host */
        IHMClientSettings fenetreSet = new IHMClientSettings();
        
        while (fenetreSet.getActivite())
        {
        	System.out.print("");
        }
        
        port=fenetreSet.getPort();
        host=fenetreSet.getHost();
        System.out.println("port : "+port+" , host : "+host);
        fenetreSet.dispose();

        try 
        {
      	    // creation socket ==> connexion
      	    echoSocket = new Socket(host,new Integer(port).intValue());
      	    
      	    //Classe BufferedReader permet de lire les caracteres a partir d un flux tamponne
      	    //Lire ce que le server ecrit
		    socIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); 
		    
		    //Permet d ajouter a un flux un texte de types primitifs et de chaine de caracteres
		    socOut= new PrintStream(echoSocket.getOutputStream());
		    
        } 
        catch (UnknownHostException e) 
        {
            System.err.println("Don't know about host:" + host);
            System.exit(1);
        } 
        catch (IOException e) 
        {
            System.err.println("Couldn't get I/O for "+ "the connection to:"+ port);
            System.exit(1);
        }
        
        String pseudos[]=new String[200];
		int nbPseudos=0;
		
        
        /* On récupère la liste des clients pour gérer l'unicité des pseudos */
        String line;
        try 
	  	  {
				 
	  		//On lit et on affiche ce que le server envoie
			int nbClients = Integer.parseInt(socIn.readLine());
			
			while(nbPseudos<nbClients)
			//code pour ajouter un client connecté
			{
				line = socIn.readLine();
				String name="";
				for(int i=8;i<line.length();i++)
				{
					name+=line.charAt(i);
				}
				pseudos[nbPseudos]=name;
				nbPseudos++;
				
			}
			
  	  	}//try
    	catch (Exception e) 
    	{
        	System.err.println("Error in EchoServer:" + e);
        	e.printStackTrace();
        }//catch
        
        IHMLogin fenetreLogin = new IHMLogin(pseudos, nbPseudos);
        
        /* Pseudo */
        //Tant que le client n'a pas rentré un pseudo correct, on attend
        while (fenetreLogin.getActivite())
        {
        	System.out.print("");
        	//System.out.println("Nombre de clients : "+Server.clients.length);
        }
        //On ajoute le client à la liste des clients
        pseudo=fenetreLogin.getPseudo();
        fenetreLogin.dispose();
        
        //Il faut renvoyer à tout le monde la liste des clients
        for(int i=0;i<nbPseudos;i++)
        {
        	socOut.println("%insert%"+pseudos[i]);
        }
        
        IHMChat fenetreChat = new IHMChat(pseudo,socOut);
        socOut.println("%insert%"+pseudo);
        socOut.print("Server > ALL : "+pseudo+" joined the conversation.\n");
        
        
        
        ClientThread ct = new ClientThread(socIn, fenetreChat);

        
        ct.start();
        
        //Client quitte la conversation
        while (fenetreChat.getActiveClient())
        {
        	System.out.print("");
        }
        socOut.println("%remove%"+pseudo);
        socOut.print("Server > ALL : "+pseudo+" just left the conversation.\n");
        try
        {
        	fenetreChat.dispose();
        	/*while (ct.getDone()==false)
        	//Tant que le thread n'a pas fini d'envoyer ses informations on attend
            {
            	System.out.print("");
            }*/
        	
        	while(ct.getDone()==true && socIn.read()!=(-1))
			//Tant qu'on a pas récupérer toutes les informations du canal
			{
				line=socIn.readLine();
				System.out.println("Still reading...");
			}
        	System.out.println("On ferme tout");
        	socOut.close();
			socIn.close();
			echoSocket.close();
        }
        catch(Exception e)
        {
        	e.printStackTrace();
        }
		
			
			
			
	}
    
    
}