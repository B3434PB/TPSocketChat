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
 * Interface graphique du serveur pour entrer le port du serveur
 * @author Papin Bayart
 */

public class IHMServer extends JFrame 
{
	/**
	 * Contient demandePort et emplacementPort
	 */
	private JPanel pan;
	/**
	 * Contient boutonActiver
	 */
	private JPanel pan2;
	/**
	 * Contient errors
	 */
	private JPanel pan3;
	/**
	 * Contient boutonNonPerm et boutonPerm
	 */
	private JPanel pan4;
	/**
	 * Emplacement pour entrer le numéro de port
	 */
	private JTextField emplacementPort;
	/**
	 * Texte demandant d'entrer le numéro de port
	 */
	private JLabel demandePort;
	/**
	 * Bouton permettant de lancer le server
	 */
	public JButton boutonActiver;
	/**
	 * Bouton permettant d'utiliser l'historique permanent
	 */
	public JButton boutonPerm;
	/**
	 * Bouton permettant d'utiliser l'historique non permanent
	 */
	public JButton boutonNonPerm;
	/**
	 * Texte contenant les erreurs
	 */
	private JLabel errors;
	/**
	 * Chaine de caractères contenant le numéro de port
	 */
	private String portNumber="";
	/**
	 * IHM activée ou non
	 */
	private boolean activite=true;
	/**
	 * Historique permanent utilisé ou non
	 */
	private boolean permanent=false;
	
	/**
	 * Constructeur de IHMServer
	 */
	public IHMServer()
	{
		this.setTitle("Port number");
		this.setSize(500, 160);
	    this.setLocationRelativeTo(null);
	    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
		BoxLayout boxLayout = new BoxLayout(this.getContentPane(), BoxLayout.Y_AXIS); // top to bottom
		this.getContentPane().setLayout(boxLayout);
		
		
		/* Rentrer le port */
		pan = new JPanel();
		pan.setBackground(Color.LIGHT_GRAY);
		this.add(pan);

		this.setVisible(true);
		
		demandePort=new JLabel("Server port : ");
		demandePort.setPreferredSize(new Dimension(175, 25));
		pan.add(demandePort);
		
		emplacementPort = new JTextField("");
		emplacementPort.setPreferredSize(new Dimension(150, 25));
		emplacementPort.setVisible(true);
		pan.add(emplacementPort);
		
		/* Historique */
		pan4 = new JPanel();
		pan4.setBackground(Color.LIGHT_GRAY);
		this.add(pan4);
		boutonPerm = new JButton("PERMANENT");
		boutonPerm.setPreferredSize(new Dimension(200, 32));
		pan4.add(boutonPerm);
		boutonNonPerm = new JButton("NON PERMANENT");
		boutonNonPerm.setPreferredSize(new Dimension(200, 32));
		pan4.add(boutonNonPerm);
		
		/* bouton ok */
		pan2 = new JPanel();
		pan2.setBackground(Color.LIGHT_GRAY);
		this.add(pan2);
		boutonActiver = new JButton("OK");
		boutonActiver.setPreferredSize(new Dimension(80, 32));
		pan2.add(boutonActiver);
		
		pan3 = new JPanel();
		pan3.setBackground(Color.LIGHT_GRAY);
		this.add(pan3);
		errors = new JLabel();
		errors.setForeground(Color.RED);
		pan3.add(errors);
		
		boutonPerm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				permanent=true;
				
			}
		});
		
		boutonNonPerm.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				permanent=false;
				
			}
		});
		
		boutonActiver.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				if(emplacementPort.getText().length()==0)
				{
					errors.setText("Port number must be positive !");
				}
				else
				{
					portNumber=emplacementPort.getText();
					activite=false;
				}
			}
		});
		
		emplacementPort.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent event) {
				if(emplacementPort.getText().length()==0)
				{
					errors.setText("Port number must be positive !");
				}
				else
				{
					portNumber=emplacementPort.getText();
					activite=false;
				}
			}
		});
		
		this.getContentPane().add(pan);
		this.getContentPane().add(pan4);
		this.getContentPane().add(pan2);
		this.getContentPane().add(pan3); 
		this.setVisible(true);
	  }
	
	/**
	 * Accesseur à portNumber
	 * @return String portNumber
	 */
	public String getPortNumber()
	{
		return portNumber;
	}
	
	/**
	 * Accesseur à activite
	 * @return boolean activite
	 */
	public boolean getActivite()
	{
		return activite;
	}
	
	/**
	 * Accesseur à permanent
	 * @return boolean permanent
	 */
	public boolean getPermanent()
	{
		return permanent;
	}
}
