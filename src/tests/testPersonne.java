package tests;

/*
 * Récapitulatif des assert jUnit les plus utilisés:
 * assertEquals() Vérifier l'égalité de deux valeurs de type primitif ou objet (en utilisant la méthode equals()). 
 *    Il existe de nombreuses surcharges de cette méthode pour chaque type primitif, pour un objet de type Object et 
 *    pour un objet de type String.
 * assertFalse( )Vérifier que la valeur fournie en paramètre est fausse
 * assertNull() Vérifier que l'objet fourni en paramètre soit null
 * assertNotNull() Vérifier que l'objet fourni en paramètre ne soit pas null
 * assertSame() Vérifier que les deux objets fournis en paramètre font référence à la même entité

 * Exemples :
 * assertSame("Les deux objets sont identiques", obj1, obj2);
 * assertTrue("Les deux objets sont identiques ", obj1 == obj2);
 *
 * assertNotSame()
 * Vérifier que les deux objets fournis en paramètre ne font pas référence à la même entité
 *
 * assertTrue()
 * Vérifier que la valeur fournie en paramètre est vraie
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
