/**
 * Package contenant l'ensemble des classes
 */
package stream;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.*;
import java.util.ListIterator;

/**
 * IHM client pour entrer son login
 * @author Papin Bayart
 *
 */

public class IHMLogin extends JFrame 
{
	/**
	 * Contient demandeLogin et emplacement Login
	 */
	private JPanel pan;
	/**
	 * Contient boutonConnect
	 */
	private JPanel pan2;
	/**
	 * Contient errors
	 */
	private JPanel pan3;
	/**
	 * Emplacelemnt pour entrer son login
	 */
	private JTextField emplacementLogin;
	/**
	 * Texte demandant d'entrer son login
	 */
	private JLabel demandeLogin;
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
	 * Bouton qui permet de se connecter
	 */
	public JButton boutonConnect;
	/**
	 * Pseudo entré par l'utilisateur
	 */
	String pseudo="";
	/**
	 * IHM activée ou non
	 */
	private boolean active=true;
	/**
	 * Contient les messages d'erreur
	 */
	private JLabel errors;
	/**
	 * Liste des pseudos des clients
	 */
	private String listePseudos[];
	/**
	 * Nombre de clients dans le chat
	 */
	private int nbPseudos;

	
	/**
	 * Contructeur de IHMLogin
	 * @param tab : tableau contenant la liste des pseudos
	 * @param taille : nombre de pseudos
	 */
	public IHMLogin(String[] tab, int taille)
	{
		listePseudos=tab;
		nbPseudos=taille;
		
		this.setTitle("Login");
		this.setSize(350, 190);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		  
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
		this.getContentPane().setLayout(boxLayout);
		
		/* Login */
		pan = new JPanel();
		pan.setBackground(Color.LIGHT_GRAY);
		this.add(pan);
		demandeLogin=new JLabel("Veuillez entrer votre pseudo");
		demandeLogin.setPreferredSize(new Dimension(175, 25));
		pan.add(demandeLogin);
		
		emplacementLogin = new JTextField("");
		emplacementLogin.setPreferredSize(new Dimension(150, 25));
		emplacementLogin.setVisible(true);
		pan.add(emplacementLogin);
		
		
		
		/* bouton connect */
		pan2 = new JPanel();
		pan2.setBackground(Color.LIGHT_GRAY);
		this.add(pan2);
		boutonConnect = new JButton("Connect");
		boutonConnect.setPreferredSize(new Dimension(100, 32));
		pan2.add(boutonConnect);
		
		/* Message d'erreur */
		pan3 = new JPanel();
		pan3.setBackground(Color.LIGHT_GRAY);
		this.add(pan3);
		errors = new JLabel();
		errors.setForeground(Color.RED);
		pan3.add(errors);
		
		
		boutonConnect.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				if(emplacementLogin.getText().length()==0) // il faudra aussi faire en sorte que 2 personnes ne puissent pas avoir le même pseudo
				{
					errors.setText("Name must contain at least one character !");
				}
				else if(exist(emplacementLogin.getText())==true)
				{
					errors.setText("Name already chosen by someone else !");
				}
				else
				{
					pseudo=emplacementLogin.getText();
					errors.setText("");
					active=false;
				}
			}
		});
		
		emplacementLogin.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				if(emplacementLogin.getText().length()==0) // il faudra aussi faire en sorte que 2 personnes ne puissent pas avoir le même pseudo
				{
					errors.setText("Name must contain at least one character !");
				}
				else if(exist(emplacementLogin.getText())==true)
				{
					errors.setText("Name already chosen by someone else !");
				}
				else 
				{
					pseudo=emplacementLogin.getText();
					errors.setText("");
					active=false;
				}
			}
		});
		
		this.getContentPane().add(pan);
		this.getContentPane().add(pan2);
		this.getContentPane().add(pan3); 
		this.setVisible(true);
	  }
	
	
	/**
	 * Accesseur à pseudo
	 * @return String pseudo
	 */
	public String getPseudo()
	{
		return pseudo;
	}
	
	
	/**
	 * Accesseur à active
	 * @return boolean active
	 */
	public boolean getActivite()
	{
		return active;
	}
	
	/**
	 * retourne vrai si la chaine de caractères est présente dans listePseudos
	 * @param aName
	 * @return boolean
	 */
	public boolean exist (String aName)
	{
		    for(int i=0;i<nbPseudos;i++)
		    {
		    	if(listePseudos[i].equals(aName))
		    	{
		    		return true;
		    	}
		    }
		
		return false;
	}
}