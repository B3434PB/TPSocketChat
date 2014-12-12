
/***
 * EchoClient
 * Example of a TCP client 
 * Date: 10/01/04
 * Authors:
 */
package stream;

import java.io.*;
import java.net.*;
import java.util.LinkedList;
import java.util.ListIterator;



public class Client {

	static LinkedList<String> messages;
	
	
  /**
  *  main method
  *  accepts a connection, receives a message from client then sends an echo to the client
  **/
    public static void main(String[] args) throws IOException {

        Socket echoSocket = null;
        PrintStream socOut = null;
        BufferedReader stdIn = null;
        BufferedReader socIn = null;
        
        messages = new LinkedList<String>();


        try {
      	    // creation socket ==> connexion
      	    echoSocket = new Socket(args[0],new Integer(args[1]).intValue());
		    socIn = new BufferedReader(
		    		          new InputStreamReader(echoSocket.getInputStream())); 
		    
		    socOut= new PrintStream(echoSocket.getOutputStream());
		    stdIn = new BufferedReader(new InputStreamReader(System.in));
		    
		    
		    
        } catch (UnknownHostException e) {
            System.err.println("Don't know about host:" + args[0]);
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Couldn't get I/O for "
                               + "the connection to:"+ args[0]);
            System.exit(1);
        }
                             
        String line;
        while (true) {
        	line=stdIn.readLine();
        	
        	//On ajoute le message a la liste de message de tous les client
        	messages.add(line);
        	
        	if (messages.size()!=0)
			{
				//System.out.println("Ajout !");
				
				
			    socOut.println(messages.getLast());
			    
			}
        	
        	if (line.equals(".")) break;
        	socOut.println(line);
        	System.out.println("echo: " + socIn.readLine());
        }
      socOut.close();
      socIn.close();
      stdIn.close();
      echoSocket.close();
    }
}


