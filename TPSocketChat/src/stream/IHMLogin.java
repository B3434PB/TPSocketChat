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



public class IHMLogin extends JFrame 
{
	private JPanel pan;
	private JPanel pan2;
	private JPanel pan3;
	private JPanel pan4;
	private JPanel pan5;
	private JTextField emplacementLogin;
	private JLabel demandeLogin;
	private JTextField portField;
	private JLabel portLabel;
	private JTextField hostField;
	private JLabel hostLabel;
	public JButton boutonConnect;
	private JButton boutonAllUser;
	private String pseudo="";
	private boolean active=true;
	private JLabel errors;
	private String listePseudos[];
	private int nbPseudos;

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
		pan5 = new JPanel();
		pan5.setBackground(Color.LIGHT_GRAY);
		this.add(pan5);
		errors = new JLabel();
		errors.setForeground(Color.RED);
		pan5.add(errors);
		
		
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
		this.getContentPane().add(pan5); 
		this.setVisible(true);
	  }
	
	
	
	public String getPseudo()
	{
		return pseudo;
	}
	
	
	
	public boolean getActivite()
	{
		return active;
	}
	
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