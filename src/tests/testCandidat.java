package tests;

import static org.junit.Assert.*;
import java.util.Set;
import org.junit.Test;

import inscriptions.Competition;
import inscriptions.Inscriptions;
import inscriptions.Personne;

/*
 * Récapitulatif jUnit:
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
 */



public class testCandidat {
	
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Personne pers = inscriptions.createPersonne("Jacqueline", "Ferdinand", "test@test.com");
	Personne persBis = inscriptions.createPersonne("Jacqueline", "Ferdinand", "test@test.com");
	
	@Test
	public void candidatTest() {
		assertNotNull("Instance créée", pers);
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
	 * Les compétitions.
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