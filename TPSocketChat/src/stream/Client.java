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



public class Client {

	//static String pseudo;
	//static String couplePseudo;

  /**
  *  main method
  *  accepts a connection, receives a message from client then sends an echo to the client
  **/
    public static void main(String[] args) throws IOException {

    	
        Socket echoSocket = null;
        PrintStream socOut = null;
        BufferedReader stdIn = null;
        BufferedReader socIn = null;
        
        if (args.length < 2) {
			System.out.println("Usage: java ClientEnvoi <Serveur host> <Serveur port>");
			System.exit(1);
		}


        try 
        {
      	    // creation socket ==> connexion
      	    echoSocket = new Socket(args[0],new Integer(args[1]).intValue());
      	    
      	    //Classe BufferedReader permet de lire les caracteres a partir d un flux tamponne
      	    //Lire ce que le server ecrit
		    socIn = new BufferedReader(new InputStreamReader(echoSocket.getInputStream())); 
		    
		    //Permet d ajouter a un flux un texte de types primitifs et de chaine de caracteres
		    socOut= new PrintStream(echoSocket.getOutputStream());
		    
		    //Lire ce que le systeme ecrit
		    stdIn = new BufferedReader(new InputStreamReader(System.in));
		    
		    
		    
        } 
        catch (UnknownHostException e) 
        {
            System.err.println("Don't know about host:" + args[0]);
            System.exit(1);
        } 
        catch (IOException e) 
        {
            System.err.println("Couldn't get I/O for "+ "the connection to:"+ args[0]);
            System.exit(1);
        }
        IHMLogin fenetreLogin = new IHMLogin();
        while(!fenetreLogin.getPseudosEntres()){
        	System.out.println("");
        }
        
        String couplePseudo=fenetreLogin.getPseudoEntre()+fenetreLogin.getEmplacementLogin().getText();
		Server.historiqueParBinome.put(couplePseudo, new ArrayList<String>());
        ClientThread ct = new ClientThread(socIn, fenetreLogin.getPseudoEntre(), fenetreLogin.getEmplacementLogin().getText());
        System.out.println(couplePseudo);
        fenetreLogin.dispose();
        ct.start();
        
        /* Pseudo */
        //System.out.println("Veuillez entrer votre pseudo: ");
		//pseudo = stdIn.readLine();		
        
		/* Debut du chat */
        String lineClient;

        while (true) 
        {
        	//On lit et on affiche ce que le server envoie
        	//lineServer=socIn.readLine();
        	//System.out.println(lineServer);
        	
        	//On lit ce qu on ecrit
        	
        	lineClient=stdIn.readLine();
        	//System.out.println(pseudo+" : "+lineClient);
        	socOut.println(ct.getPseudoClient()+" : "+lineClient);
        	
        	
        	if (lineClient.equals(".")) break;
        	//socOut.println(lineClient);
        	//System.out.println("echo: " + socIn.readLine());
        }
		socOut.close();
		socIn.close();
		stdIn.close();
		echoSocket.close();
    }
    
}