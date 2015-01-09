/**
 * Package contenant l'ensemble des classes
 */
package stream;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JTextField;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JButton;
/**
 * Interface graphique du serveur pour entrer le port du serveur
 * @author Papin Bayart
 *
 */
 
public class IHMClientSettings extends JFrame {

	/**
	 * Numéro de port du serveur
	 */
	private String port="";
	/**
	 * Adresse de l'hote
	 */
	private String host="";
	/**
	 * Contient hostField et hostLabel
	 */
	private JPanel pan;
	/**
	 * Contient portField et portLabel
	 */
	private JPanel pan2;
	/**
	 * Contient boutonConnect
	 */
	private JPanel pan3;
	/**
	 * Contient errors
	 */
	private JPanel pan4;
	/**
	 * Emplacement pour entrer le numéro de port
	 */
	private JTextField portField;
	/**
	 * Texte demandant d'entrer le numéro de port
	 */
	private JLabel portLabel;
	/**
	 * Emplacement pour entrer l'adresse de l'hôte
	 */
	private JTextField hostField;
	/**
	 * Texte demandant d'entrer l'adresse de l'hôte
	 */
	private JLabel hostLabel;
	/**
	 * Permet de se connecter
	 */
	public JButton boutonConnect;
	/**
	 * IHM activée ou non
	 */
	private boolean active=true;
	/**
	 * Texte cotenant les erreurs
	 */
	private JLabel errors;
	
	/**
	 * Constructeur de IHMClientSettings
	 */
	public IHMClientSettings() {
		this.setTitle("Settings");
		this.setSize(400, 200);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		  
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
		this.getContentPane().setLayout(boxLayout);
		
		/* Adresse IP serveur */
		pan = new JPanel();
		pan.setBackground(Color.LIGHT_GRAY);
		this.add(pan);
		hostLabel=new JLabel("Server host : ");
		pan.add(hostLabel);
		
		hostField = new JTextField("");
		hostField.setPreferredSize(new Dimension(80, 25));
		hostField.setVisible(true);
		pan.add(hostField);
		
		/* Port du serveur */
		pan2 = new JPanel();
		pan2.setBackground(Color.LIGHT_GRAY);
		this.add(pan2);
		portLabel=new JLabel("Server port : ");
		pan2.add(portLabel);
		
		portField = new JTextField("");
		portField.setPreferredSize(new Dimension(80, 25));
		portField.setVisible(true);
		pan2.add(portField);
		
		/* bouton connect */
		pan3 = new JPanel();
		pan3.setBackground(Color.LIGHT_GRAY);
		this.add(pan3);
		boutonConnect = new JButton("OK");
		boutonConnect.setPreferredSize(new Dimension(80, 32));
		pan3.add(boutonConnect);
		
		/* Message d'erreur */
		pan4 = new JPanel();
		pan4.setBackground(Color.LIGHT_GRAY);
		this.add(pan4);
		errors = new JLabel();
		errors.setForeground(Color.RED);
		pan4.add(errors);
		
		
		boutonConnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				 if(portField.getText().length()==0 || hostField.getText().length()==0)
				{
					errors.setText("No server port or no server host !");
				}
				else
				{
					port=portField.getText();
					host=hostField.getText();
					errors.setText("");
					active=false;
				}
			}
		});
		
		this.getContentPane().add(pan);
		this.getContentPane().add(pan2);
		this.getContentPane().add(pan3); 
		this.getContentPane().add(pan4);
		this.setVisible(true);
	}
	
	/**
	 * Accesseur à port
	 * @return String port
	 */
	public String getPort()
	{
		return port;
	}
	
	/**
	 * Accesseur à host
	 * @return String host
	 */
	public String getHost()
	{
		return host;
	}
	
	/**
	 * Accesseur à activite
	 * @return boolean activite
	 */
	public boolean getActivite()
	{
		return active;
	}

}