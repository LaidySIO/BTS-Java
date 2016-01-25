package tests;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;

/*
 * R�capitulatif jUnit:
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
 */



public class testCandidat {
	
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne pers = inscriptions.createPersonne("Jacqueline", "Ferdinand", "test@test.com");
	Personne persBis = inscriptions.createPersonne("Jacqueline", "Ferdinand", "test@test.com");
	
	@Test
	public void candidatTest() {
		assertNotNull("Instance cr��e", pers);
	}

	@Test
	public void testGetNom() {
		assertEquals("Jacqueline", pers.getNom());
	}

	/*
	 * Le nom du candidat.
	 */
	
	@Test
	public void testSetNom() {
		pers.setNom("John");
		assertEquals("John", pers.getNom());
	}

	/*
	 * Les comp�titions.
	 */
	
	@Test
	public void testGetCompetitions() {
		Set<Competition> Competitions = pers.getCompetitions();
		Competition first = inscriptions.createCompetition("Concours de saut en travers", null, false);
		Competition second = inscriptions.createCompetition("Concours de saut en travers", null, false);
		first.add(pers);
		second.add(pers);
		
		assertEquals(Competitions, pers.getCompetitions());
	}

	@Test
	public void testAdd() {
		Set<Competition> Competitions = pers.getCompetitions();
		Competition testCompet = inscriptions.createCompetition("Concours de saut en travers", null, false);
		testCompet.add(pers);
		
		assertTrue(Competitions.contains(testCompet));
	}

	@Test
	public void testRemove() {
		Set<Competition> Competitions = pers.getCompetitions();
		Competition testCompet = inscriptions.createCompetition("Concours de saut en travers", null, false);
		testCompet.add(pers);
		testCompet.remove(pers);
		
		assertFalse(Competitions.contains(testCompet));
	}

	@Test
	public void testDelete() {
		Set<Competition> Competitions = pers.getCompetitions();
		Competition testCompet = inscriptions.createCompetition("Concours du lapin le plus grand", null, false);
		testCompet.add(pers);
		pers.delete();
	
		assertTrue(Competitions.isEmpty());
	}

	@Test
	public void testCompareTo() {
		assertTrue(pers.compareTo(persBis) == 0);
	}

	@Test
	public void testToString() {
		System.out.println("Pas besoin de tester");
	}

}