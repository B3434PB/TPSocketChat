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
	public JTextField emplacementLogin;
	private JLabel demandeLogin;
	private JButton boutonOk;
	

	public IHMLogin()
	{
		this.setTitle("Login");
		this.setSize(375, 115);
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
		
		boutonOk.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				Client.pseudo=emplacementLogin.getText();
				
				if(emplacementLogin.getText().length()==0) // il faudra aussi faire en sorte que 2 personnes ne puissent pas avoir le même pseudo
				{
					System.out.println("Votre pseudo doit être composé d'au moins 1 caractère");
				}
				else 
				{
					dispose();
				}
			}
		});
		pan.add(boutonOk);

		this.setContentPane(pan); 
	  }
	
}