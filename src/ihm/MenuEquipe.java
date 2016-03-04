package ihm;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuEquipe extends JFrame {
	
	JButton [] jButons;
	
	public MenuEquipe()
	{
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new FlowLayout());
        
        jButons= new JButton [6];
        
        jButons[0] = new JButton("AFFICHER");
        jButons[1] = new JButton("CREER");
        jButons[2] = new JButton("MODIFIER");
        jButons[3] = new JButton("SUPPRIMER");
        jButons[4] = new JButton("PRECEDENT");
        jButons[5] = new JButton("QUITTER");
        
        jButons[0].setLayout(new FlowLayout(FlowLayout.LEFT));
		jButons[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		jButons[2].setLayout(new FlowLayout(FlowLayout.RIGHT));
		
        for (int i = 0; i < 6; i++){
			this.getContentPane().add(jButons[i]);
		}
        
	}

	public static void main (String [] agrs)
	{
		
	}
}
