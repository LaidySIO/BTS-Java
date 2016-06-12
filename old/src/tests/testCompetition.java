package tests;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Set;
import java.util.SortedSet;

import org.junit.Test;

import inscriptions.*;

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

public class testCompetition {

	Inscriptions inscriptions = Inscriptions.getInscriptions();
	Competition competition = inscriptions.createCompetition("Balalaia", LocalDate.now(), false);
	Competition competrition = inscriptions.createCompetition("Visionnage de Cassettes", LocalDate.now(), true);
	Personne personne = inscriptions.createPersonne("Maria", "pilaf", "test@test.com");
	
	//Test GetNom
	@Test
	public void testGetNom() {
		assertEquals("Visionnage de Cassettes", competition.getNom());
	}

	//Test DateCloture
	@Test
	public void testGetDateCloture() {
	   
		// ????
	}
	
	public void testEstEnEquipe() {
		// ????
	}

	@Test
	public void ouvert() {
		// ????
	}
	
	//Test GetCandidats
	@Test
	public void testGetCandidats() {
		Set<Candidat> candidats = competition.getCandidats();
		competition.add(personne);
		assertEquals(candidats, competition.getCandidats());
	}

	//Test AddPersonne
	@Test
	public void testAddPersonne() {
		Set<Candidat> candidats = competition.getCandidats();
		competition.add(personne);
		assertTrue(candidats.contains(personne));
	}

	//Test AddEquipe
	@Test
	public void testAddEquipe() {
		Set<Candidat> candidats = competrition.getCandidats();
		Equipe fly = inscriptions.createEquipe("Fly - Forces lib�r�es yaourt");
		competrition.add(fly);
		assertTrue(candidats.contains(fly));
	}

	//Test Remove
	@Test
	public void testRemove() {
		Set<Candidat> candidats = competition.getCandidats();
		competition.add(personne);
		competition.remove(personne);
		
		assertTrue(!candidats.contains(personne));
	}

	//Test Delete
	@Test
	public void testDelete() {
		SortedSet<Competition> competitionDel = inscriptions.getCompetitions();
		competition.delete();
		
		assertTrue(!competitionDel.contains(competition));
	}

	//Test Compare
	@Test
	public void testCompareTo() {
		// On cr�� une nouvelle comp�tition
		Competition competitionQuis = inscriptions.createCompetition("Visionnage de Cassettes", null, true);
		//Et on compare a la comp�tition d�j� cr��e plus haut
		assertTrue(competrition.compareTo(competitionQuis) == 0);
	}
	
	public void testToString() {

		// ????
	}
}
