/**
 * Package contenant l'ensemble des classes
 */
package stream;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.event.*;

/**
 * Interface graphique du chat
 * @author Papin Bayart
 *
 */

public class IHMChat extends JFrame 
{
	/**
	 * Contient messages et clients
	 */
	JPanel panHaut;
	/**
	 * Contient boutonSent, espace, boutonQuit, textFieldClient
	 */
	JPanel panBas;
	/**
	 * Contient conversation
	 */
	JScrollPane messages;
	/**
	 * Contient textFieldClients
	 */
	JScrollPane clients;
	/**
	 * Contient les pseudos
	 */
	JList listeClients;
	/**
	 * Contenant les pseudos
	 */
	JTextField textFieldClient;
	/**
	 * Permet d'envoyer un message
	 */
	JButton boutonSent;
	/**
	 * Nom d'un client
	 */
	String client;
	/**
	 * Nom du destinataire selectionne
	 */
	String destinataire;
	/**
	 * Anciens messages
	 */
	JTextArea conversation;
	/**
	 * Utilité ésthetique seulement
	 */
	JLabel espace;
	/**
	 * Permet de quitter le chat
	 */
	JButton boutonQuit;
	/**
	 * Chat active ou non
	 */
	private boolean active=true;
	/**
	 * Flux pour communiquer avec le server
	 */
	PrintStream socOut;
	/**
	 * Attribut de la JList contenant les Clients
	 */
	static DefaultListModel listeModelClients = new DefaultListModel();
	
	/**
	 * Constructeur de IHMChat
	 * @param aClient : pseudo du client
	 * @param aSocOut : flux permettant l'échange entre serveur et client
	 */
	  public IHMChat(String aClient, PrintStream aSocOut)
	  {
		  client=aClient;
		  socOut=aSocOut;
		  this.setTitle("Welcom "+aClient+" to B3434's chat !");
		  this.setSize(800, 500);
	      this.setLocationRelativeTo(null);
	      this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);             
		  
		  BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
		  this.getContentPane().setLayout(boxLayout);
		  panHaut = new JPanel();
		  panHaut.setLayout( new BorderLayout() );
		  panHaut.setPreferredSize(new Dimension (800, 400));
		  this.add(panHaut);
		  panBas = new JPanel();
		  panBas.setLayout( new FlowLayout() );
		  this.add(panBas);      
		 
		  
		  /* PanHaut */
		  // Ajout de la liste de clients
		  //listeModelClients.addElement("ALL");
		  listeClients = new JList (listeModelClients);
		  listeClients.setPreferredSize(new Dimension(100, 400));
		  clients= new JScrollPane();
		  clients.getViewport().add( listeClients );
		  panHaut.add( clients, BorderLayout.WEST );
		  //Ajout de la conversation
		  conversation = new JTextArea(5,20);
		  conversation.setEditable(false);

		  messages = new JScrollPane();
		  messages.getViewport().add( conversation );  
		  panHaut.add( messages, BorderLayout.CENTER );
		  
		    
		  /* PanBas */
		  textFieldClient = new JTextField("");
		  textFieldClient.setPreferredSize(new Dimension(300, 25));
		  panBas.add( textFieldClient );
		  
		  boutonSent = new JButton("Sent");
		  panBas.add( boutonSent );
		  
		  espace = new JLabel("             ");
		  boutonQuit = new JButton("QUIT");
		  panBas.add(espace);
		  panBas.add(boutonQuit);
		  
		  //Par défaut le destinataire est "ALL"
		  destinataire = "ALL";
		  
		  //On prévient notre JFrame que notre JPanel sera son content pane  
		  this.getContentPane().add(panHaut);
		  this.getContentPane().add(panBas); 
		  this.setVisible(true);
		  
		  /* Listeners */
		  //On récupère le nom du destinataire du message
		  listeClients.addListSelectionListener(new ListSelectionListener() {
			    public void valueChanged(ListSelectionEvent event) {
			        if (!event.getValueIsAdjusting()){
			            JList source = (JList)event.getSource();
			            destinataire = source.getSelectedValue().toString();
			        }
			    }
			});
		  
		  
		  boutonSent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(textFieldClient.getText().length()!=0 && destinataire.equals(client)==false)
					{
						socOut.print(client+" > "+destinataire+" : "+textFieldClient.getText()+"\n");
						textFieldClient.setText("");
					}
				}
		  });
		  
		  boutonQuit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					socOut.println("%quit%"+client);
					socOut.flush();
					active=false;
				}
		  });
		  
		  textFieldClient.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if(textFieldClient.getText().length()!=0 && destinataire.equals(client)==false)
					{
						socOut.print(client+" > "+destinataire+" : "+textFieldClient.getText()+"\n");
						textFieldClient.setText("");
					}
				}
		  });
		  
	  }

	  /**
	   * Acceseur à active
	   * @return boolean active
	   */
	  public boolean getActiveClient()
	  {
		  return active;
	  }

}


