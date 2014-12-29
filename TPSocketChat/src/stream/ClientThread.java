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
	private IHMChat frame;
	private boolean done=false;

	
	
	ClientThread (BufferedReader aSocIn, IHMChat aFrame) {
		this.socIn = aSocIn;
		frame=aFrame;

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
	    			lineClient = socIn.readLine();
	    			
	    			if(lineClient.startsWith("%insert%"))
        			//code pour ajouter un client connecté
        			{
        				String name="";
        				for(int i=8;i<lineClient.length();i++)
        				{
        					name+=lineClient.charAt(i);
        				}
        				if (frame.listeModelClients.contains(name)==false)
        				{
        					frame.listeModelClients.addElement(name);
        				}
        				
        			}
        			else if(lineClient.startsWith("%remove%"))
        			//code pour retirer un client déconnecté
        			{
        				String name="";
        				for(int i=8;i<lineClient.length();i++)
        				{
        					name+=lineClient.charAt(i);
        				}
        				frame.listeModelClients.removeElement(name);
        				
        			}
        			else if(lineClient.startsWith("%quit%"))
            		//Gérer le probleme de rafraichissement de la JList lorsque qu'un client est sélectionné et quitte la conversation
            		{
        				String name="";
        				for(int i=6;i<lineClient.length();i++)
        				{
        					name+=lineClient.charAt(i);
        				}
        				if(name.equals(frame.client))
        				//On arrête le flux
        				{
        					done=true;
        					//socIn.close();
        					System.out.println("socIn closed..."+done);
        				}
        				else if(name.equals(frame.destinataire))
        				{
        					frame.conversation.append("\r");
        					frame.destinataire="ALL";
        					frame.listeModelClients.set(0,"ALL");
        				}
            		}
        			else 
        			//Il s'agit d'un message
        			{
        				/* On extrait le nom du destinataire, la position de son premier caratère et dernier caractère */
        				int posBegin;
        				for(posBegin=0;posBegin<lineClient.length();posBegin++)
        				{
        					if(lineClient.charAt(posBegin)=='>')
        					{
        						break;
        					}
        				}
        				posBegin+=2;
        				int posEnd=posBegin;
        				String destinataire="";
        				while(posEnd<lineClient.length() && lineClient.charAt(posEnd)!=' ')
        				//On extrait le nom
        				{
        					destinataire+=lineClient.charAt(posEnd);
        					posEnd++;
        				}
        				
        				if(destinataire.equals(frame.client))
        				//Le destinataire correspond à ce thread client
        				{
        					String newLine=lineClient.substring(0, posBegin)+"Me"+lineClient.substring(posEnd);
        					frame.conversation.append(newLine+"\n");
        				}
        				else
        				{
        					frame.conversation.append(lineClient+"\n");
        				}
    	    			
          	          	
        			}
    			
	    			
    			
    	    	  	}//try
    		    	catch (Exception e) 
    		    	{
    		        	System.err.println("Error in EchoServer:" + e); 
    		        }//catch
	    		
    		}//while
    	
    }//main
	
	
	public boolean getDone()
	{
		return done;
	}
	

	
  
}