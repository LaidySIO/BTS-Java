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
import java.util.SortedSet;
import inscriptions.*;
import org.junit.Test;

import inscriptions.Inscriptions;

public class testEquipe {
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Equipe plop = inscriptions.createEquipe("Plop blob");
	//Ajout personnnes
	Personne personnes = inscriptions.createPersonne("Patrick", "Manchot", "test@test.com");
	Personne people = inscriptions.createPersonne("Marin", "Dodouce", "testdouce@test.com");
	Personne treople = inscriptions.createPersonne("Popol", "Quidur", "testquidur@test.com");
	Personne qreople = inscriptions.createPersonne("DaBro", "Breaf", "testbreaf@test.com");
	
	
	//Test GetMembres de l'equipe
	@Test
	public void testGetMembres() {
		SortedSet<Personne> lesMembres = plop.getMembres();
		plop.add(personnes);
		plop.add(people);
		plop.add(treople);
		plop.add(qreople);
		
		assertEquals(lesMembres, plop.getMembres());
	}

	//Test AddPersonne, pratiquement comme le précédent
	@Test
	public void testAddpersonne() {
		SortedSet<Personne> lesMembres = plop.getMembres();
		plop.add(personnes);
		plop.add(people);
		plop.add(treople);
		plop.add(qreople);
		
		assertTrue(lesMembres.contains(personnes) && lesMembres.contains(people) && lesMembres.contains(treople) && lesMembres.contains(qreople));
	}

	//Test RemovePersonne
	@Test
	public void testRemovePersonnesonne() {
		SortedSet<Personne> membres = plop.getMembres();
		plop.add(personnes);
		plop.add(people);
		plop.add(treople);
		plop.add(qreople);
		
		plop.remove(personnes);
		plop.remove(people);
		plop.remove(treople);
		
		// Si l'Affirmation est fausse, le résultat est correct
		assertTrue(!membres.contains(personnes));
	}
}
