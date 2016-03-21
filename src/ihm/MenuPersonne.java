package ihm;
import inscriptions.*;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

public class MenuPersonne extends JFrame {
	//private static final long serialVersionUID = 3563094549912370491L;
	JButton [] jButtons;
	public MenuPersonne()
	{
        this.setVisible(true);
        this.setTitle("MENU PERSONNE");
        this.setSize(700, 600);
        this.getContentPane().setLayout(new FlowLayout());
        List<Personne> personnes = new ArrayList<>(Inscriptions.getInscriptions(false).getPersonnes());
        
        TableModel dataModel = new AbstractTableModel() {
            public int getColumnCount() { return 3; }
            public int getRowCount() { return personnes.size();}
            public Object getValueAt(int row, int col){Personne personne = personnes.get(row);
            if (personne == null)
            {
            	System.out.println("Achtung ! Une personne nulle !");
            	return null;
            }
            switch(col)
            	{
            		case 0 : return personne.getPrenom();
            		case 1 : return personne.getNom();
            		case 2 : return personne.getMail();
            		default : return null;
            	}
            }
        };
        JTable table = new JTable(dataModel);
        JScrollPane scrollpane = new JScrollPane(table);

        jButtons = new JButton [6];
        
        jButtons[0] = new JButton("AFFICHER");
        jButtons[0].setLayout(new FlowLayout(FlowLayout.LEFT));
        jButtons[1] = new JButton("CREER");
        jButtons[1].setLayout(new FlowLayout(FlowLayout.CENTER));
        jButtons[2] = new JButton("MODIFIER");
        jButtons[2].setLayout(new FlowLayout(FlowLayout.RIGHT));
        jButtons[3] = new JButton("SUPPRIMER");
        jButtons[4] = new JButton("PRECEDENT");
        jButtons[5] = new JButton("QUITTER");
        
		table.setLayout(new FlowLayout(FlowLayout.CENTER));
		
        for (int i = 0; i < 6; i++){
			this.getContentPane().add(jButtons[i]);
		}
       this.add(table);
       table.setVisible(true);
	}

//	public static void main (String [] agrs)
//	{
//		
//	}
}
