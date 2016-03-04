package ihm;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class MenuCompetition extends JFrame {
	
	JButton [] jButtons;
	public MenuCompetition()
	{
        this.setVisible(true);
        this.setTitle("MENU COMPETITION");
        this.setSize(300, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jButtons = new JButton [6];
        
        jButtons[0] = new JButton("AFFICHER");
        jButtons[1] = new JButton("CREER");
        jButtons[2] = new JButton("MODIFIER");
        jButtons[3] = new JButton("SUPPRIMER");
        jButtons[4] = new JButton("PRECEDENT");
        jButtons[5] = new JButton("QUITTER");
        
        jButtons[0].setLayout(new FlowLayout(FlowLayout.LEFT));
		jButtons[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		jButtons[2].setLayout(new FlowLayout(FlowLayout.RIGHT));
		
		for (int i = 0; i < 6; i++)
			this.getContentPane().add(jButtons[i]);
	}
		
	public static void main (String [] agrs)
	{
		
	}
}
