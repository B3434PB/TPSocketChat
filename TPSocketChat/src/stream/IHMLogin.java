package stream;
import javax.swing.JFrame;
import javax.swing.JPanel;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JButton;

import java.awt.event.*;



public class IHMLogin extends JFrame 
{
	private JPanel pan;
	private JTextField emplacementLogin;
	private JLabel demandeLogin;
	public JButton boutonOk;
	private JButton boutonAllUser;
	private boolean premiereFenetre=true;
	private String pseudoEntre;
	private boolean pseudosEntres=false;
	

	public IHMLogin()
	{
		//premiereFenetre=true;
		this.setTitle("Login");
		this.setSize(400, 145);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		this.setVisible(true);
		  
		//Instanciation d'un objet JPanel
		pan = new JPanel();
		pan.setPreferredSize(new Dimension(375, 115));
		pan.setBackground(Color.LIGHT_GRAY);
		this.add(pan);

		//On prévient notre JFrame que notre JPanel sera son content pane
		              
		this.setVisible(true);
		
		demandeLogin=new JLabel("Veuillez entrer votre pseudo");
		demandeLogin.setPreferredSize(new Dimension(175, 25));
		pan.add(demandeLogin);
		
		emplacementLogin = new JTextField("");
		emplacementLogin.setPreferredSize(new Dimension(150, 25));
		emplacementLogin.setVisible(true);
		pan.add(emplacementLogin);
		
		boutonOk = new JButton("OK");
		boutonOk.setPreferredSize(new Dimension(53, 32));
		pan.add(boutonOk);
		
		boutonAllUser = new JButton("Avec tous les utilisateurs");
		boutonAllUser.setPreferredSize(new Dimension(350, 32));
		boutonAllUser.setVisible(false);
		pan.add(boutonAllUser);
		
		
		
		boutonOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(premiereFenetre) //premiere fenetre
				{
					if(emplacementLogin.getText().length()==0) // il faudra aussi faire en sorte que 2 personnes ne puissent pas avoir le même pseudo
					{
						System.out.println("Votre pseudo doit être composé d'au moins 1 caractère");
					}
					else 
					{
						pseudoEntre=emplacementLogin.getText();
						emplacementLogin.setText("");
						demandeLogin.setPreferredSize(new Dimension(200, 25));
						demandeLogin.setText("Avec qui voulez vous dialoguer ?");
						pan.setPreferredSize(new Dimension(400, 145));
						boutonAllUser.setVisible(true);
						premiereFenetre=false;
	
					}
				}
				else  //2° fenetre
				{
					
					pseudosEntres=true;
				}
			}
		});
		
		/*
		boutonAllUser.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				
				if(emplacementLogin.getText().length()==0)
				{
				}
				else 
				{
					dispose();
				}
			}
		});
		*/
		
		this.setContentPane(pan); 
	  }
	
	
	public JButton getBoutonOk()
	{
		return boutonOk;
	}
	
	public JTextField getEmplacementLogin()
	{
		return emplacementLogin;
	}
	
	public String getPseudoEntre()
	{
		return pseudoEntre;
	}
	
	public boolean getPseudosEntres()
	{
		return pseudosEntres;
	}
}