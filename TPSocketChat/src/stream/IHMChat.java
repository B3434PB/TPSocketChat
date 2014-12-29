package stream;

import java.awt.*;
import java.awt.event.*;
import java.io.PrintStream;

import javax.swing.*;
import javax.swing.event.*;


public class IHMChat extends JFrame 
{
	 
	JPanel panHaut;
	JPanel panBas;
	JScrollPane messages;
	JScrollPane clients;
	JList listeClients;
	JTextField textFieldClient;
	JButton boutonSent;
	String client;
	String destinataire;
	JTextArea conversation;
	JLabel espace;
	JButton boutonQuit;
	private boolean active=true;
	PrintStream socOut;
	static DefaultListModel listeModelClients = new DefaultListModel();
	
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
		  //panHaut.setPreferredSize(new Dimension (800, 100));
		  this.add(panBas);
		  //Définition de sa couleur de fond
		  //panHaut.setBackground(Color.RED);        
		 
		  
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

	  public boolean getActiveClient()
	  {
		  return active;
	  }

}


