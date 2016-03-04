package ihm;

import java.awt.*;
import java.awt.event.*;
import java.net.ConnectException;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

public class FenetrePrincipale extends JFrame {

	private static final long serialVersionUID = 3563094549912370491L;
	JButton[] jButtons;

	public FenetrePrincipale() {
		this.setSize(700, 500);
		this.setTitle("INSCRIPTIONS SPORTIVES PAR M2L");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(new FlowLayout());
		jButtons = new JButton[3];
		jButtons[0] = new JButton("PERSONNES");
		jButtons[1] = new JButton("EQUIPES");
		jButtons[2] = new JButton("COMPETITIONS");
		
		jButtons[0].setLayout(new FlowLayout(FlowLayout.LEFT));
		jButtons[1].setLayout(new FlowLayout(FlowLayout.CENTER));
		jButtons[2].setLayout(new FlowLayout(FlowLayout.RIGHT));
		jButtons[0].addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				JFrame MP = new MenuPersonne();
				MP.setVisible(true);
			}
		});
		jButtons[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame MP = new MenuPersonne();
				MP.setVisible(true);
			}
		});
		jButtons[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFrame MP = new MenuPersonne();
				MP.setVisible(true);
			}
		});
		JButton exit = new JButton("QUITTER");
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		for (int i = 0; i < 3; i++) {
			this.getContentPane().add(jButtons[i]);
		}
		this.add(exit);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public static void main(String[] args) {
		FenetrePrincipale Accueil = new FenetrePrincipale();
	}
}
