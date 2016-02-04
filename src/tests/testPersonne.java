package tests;

/*
 * R�capitulatif des assert jUnit les plus utilis�s:
 * assertEquals() V�rifier l'�galit� de deux valeurs de type primitif ou objet (en utilisant la m�thode equals()). 
 *    Il existe de nombreuses surcharges de cette m�thode pour chaque type primitif, pour un objet de type Object et 
 *    pour un objet de type String.
 * assertFalse( )V�rifier que la valeur fournie en param�tre est fausse
 * assertNull() V�rifier que l'objet fourni en param�tre soit null
 * assertNotNull() V�rifier que l'objet fourni en param�tre ne soit pas null
 * assertSame() V�rifier que les deux objets fournis en param�tre font r�f�rence � la m�me entit�

 * Exemples :
 * assertSame("Les deux objets sont identiques", obj1, obj2);
 * assertTrue("Les deux objets sont identiques ", obj1 == obj2);
 *
 * assertNotSame()
 * V�rifier que les deux objets fournis en param�tre ne font pas r�f�rence � la m�me entit�
 *
 * assertTrue()
 * V�rifier que la valeur fournie en param�tre est vraie
 * 
 * Pour le reste des assert : http://junit.sourceforge.net/javadoc/org/junit/Assert.html
 */

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;
import inscriptions.*;

public class testPersonne {

	// Test d'inscription
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne personne = inscriptions.createPersonne("Jannot", "Marcel", "test@foxtrop.com");

	// Test GetPrenom
	@Test
	public void testGetPrenom() {
		assertEquals("Marcel", personne.getPrenom());
	}

	// Test SetPrenom
	@Test
	public void testSetPrenom() {
		personne.setPrenom("LaPoulette");
		assertEquals("LaPoulette", personne.getPrenom());
	}

	// Test GetMail
	@Test
	public void testGetMail() {
		assertEquals("test@foxtrop.com", personne.getMail());
	}

	// Test SetMail
	@Test
	public void testSetMail() {
		personne.setMail("poupoutoux@foxtrop.com");
		assertEquals("poupoutoux@foxtrop.com", personne.getMail());
	}

	// Test GetEquipes
	@Test
	public void testGetEquipes() {
		Equipe yolo = inscriptions.createEquipe("Yolo!");
		yolo.add(personne);
		Set<Equipe> equipes = personne.getEquipes();
		assertTrue(equipes.contains(yolo));
	}

	@Test
	public void testDelete() {
		Set<Equipe> equipes = personne.getEquipes();
		personne.delete();
		assertTrue(equipes.isEmpty());
	}
}
