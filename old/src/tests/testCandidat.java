package tests;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;

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



public class testCandidat {
	
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne personne = inscriptions.createPersonne("Jacqueline", "Ferdinand", "test@test.com");
	Personne people = inscriptions.createPersonne("Jacqueline", "Ferdinand", "test@test.com");
	
	//test GetNom
	@Test
	public void testGetNom() {
		assertEquals("Jacqueline", personne.getNom());
	}

	//Test SetNom
	@Test
	public void testSetNom() {
		personne.setNom("John");
		assertEquals("John", personne.getNom());
	}


	//Test Add
	@Test
	public void testAdd() {
		Set<Competition> Competitions = personne.getCompetitions();
		Competition testCompetition = inscriptions.createCompetition("Concours de saut en travers", null, false);
		testCompetition.add(personne);
		
		assertTrue(Competitions.contains(testCompetition));
	}
	
	//TestRemove, comme pour Add mais on retire la donnée ajoutée
	@Test
	public void testRemove() {
		Set<Competition> Competitions = personne.getCompetitions();
		Competition testCompetition = inscriptions.createCompetition("Concours de saut en travers", null, false);
		testCompetition.add(personne);
		testCompetition.remove(personne);
		
		assertFalse(Competitions.contains(testCompetition));
	}

	//Test Delete, comme précédemment, mais on efface la donnée ajoutée
	@Test
	public void testDelete() {
		Set<Competition> Competitions = personne.getCompetitions();
		Competition testCompetition = inscriptions.createCompetition("Concours de saut en travers", null, false);
		testCompetition.add(personne);
		personne.delete();
	
		assertTrue(Competitions.isEmpty());
	}
	
	//Test Compare
	@Test
	public void testCompareTo() {
		assertTrue(personne.compareTo(people) == 0);
	}

	//Test String
	@Test
	public void testToString() {
		// Autre méthode??
	}

}