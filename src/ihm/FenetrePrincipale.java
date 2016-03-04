package ihm;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class FenetrePrincipale extends JFrame {
	public FenetrePrincipale()
	{
		super("INSCRIPTIONS SPORTIVES PAR M2L");
		
		WindowListener l = new WindowAdapter()
		{

			public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}
		};

			addWindowListener(l);
			setSize(700, 500);
			setVisible(true);
	}
	
	public static void main(String [] args){
		JFrame Accueil = new FenetrePrincipale();
		}

}
