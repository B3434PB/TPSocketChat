package stream;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;

public class IHMChat extends JFrame 
{
	JPanel panHaut;
	JPanel panBas;
	JLabel messages;
	JList clients;
	  public IHMChat()
	  {
		  this.setTitle("Welcom to B3434's chat !");
		  this.setSize(800, 500);
	      this.setLocationRelativeTo(null);
	      //this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);             
		  this.setVisible(true);
		  panHaut = new JPanel();
		    //Définition de sa couleur de fond
		    //panHaut.setBackground(Color.LIGHT_GRAY);        
		    //On prévient notre JFrame que notre JPanel sera son content pane
		    this.add(panHaut);
		    messages = new JLabel("Coucou");
		    panHaut.add(messages);
		    messages.setPreferredSize(new Dimension(400, 400));
		    //messages.setText("HELLO !");
		    messages.setBackground(Color.GRAY);
		    messages.setVisible(true);
		    //panBas = new JPanel();
		    
		  this.setContentPane(panHaut);
	  }
}
//private IHMChat fenetreChat;


//fenetreChat = new IHMChat();
