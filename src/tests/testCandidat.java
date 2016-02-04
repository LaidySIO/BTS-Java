package tests;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;

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
	
	//TestRemove, comme pour Add mais on retire la donn�e ajout�e
	@Test
	public void testRemove() {
		Set<Competition> Competitions = personne.getCompetitions();
		Competition testCompetition = inscriptions.createCompetition("Concours de saut en travers", null, false);
		testCompetition.add(personne);
		testCompetition.remove(personne);
		
		assertFalse(Competitions.contains(testCompetition));
	}

	//Test Delete, comme pr�c�demment, mais on efface la donn�e ajout�e
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
		// Autre m�thode??
	}

}