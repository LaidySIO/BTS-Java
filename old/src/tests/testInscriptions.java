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
import java.util.SortedSet;
import org.junit.Test;
import inscriptions.*;

public class testInscriptions {
	Inscriptions inscriptions = Inscriptions.getInscriptions();
	//Ajout d'inscription
	Competition competition = inscriptions.createCompetition("JeMenFoutisme", null, false);
	Personne personne = inscriptions.createPersonne("Jane-Jacques", "Patrol", "test@test.com");

	//Test GetCompetitions
	@Test
	public void testGetCompetitions() {
		Set<Competition> lesCompetitions = inscriptions.getCompetitions();
		//On a ajout� une comp�tition, il suffit de v�rifier que ce n'est pas vide
		assertTrue(!lesCompetitions.isEmpty());
		
	}

	//Test GetCandidats
	@Test
	public void testGetCandidats() {
		Set<Candidat> lesCandidats = inscriptions.getCandidats();
		//On a ajout� un candidat, il suffit de v�rifier que ce n'est pas vide
		assertTrue(!lesCandidats.isEmpty());
	}

	///Test CreateCompetition
	@Test
	public void testCreateCompetition() {
		Competition competition = inscriptions.createCompetition("Kamoulox", null, false);
		assertNotNull(competition);
	}

	//Test CreatePersonne
	@Test
	public void testCreatePersonne() {
		Personne personne = inscriptions.createPersonne("Barney", "Stinson", "b.stinson@test.com");
		assertNotNull(personne);
	}

	//Test CreateEquipe
	@Test
	public void testCreateEquipe() {
		Equipe yolo = inscriptions.createEquipe("PSG - Paris Sous Giraffes");
		assertNotNull(yolo);
	}

	@Test
	public void testRemoveCompetition() {
		SortedSet<Competition> competitions = inscriptions.getCompetitions();
		Competition TheCompet = inscriptions.createCompetition("Levage de Coude", null, false);
		TheCompet.delete();
		//On v�rifie que ce n'est pas vide / Contient bien la donnn�e inscrite( ici c'est Levage de Coude"
		assertTrue(!competitions.contains(TheCompet));
	}

	@Test
	public void testRemoveCandidat() {
		SortedSet<Candidat> candidats = inscriptions.getCandidats();
		personne.delete();
		//On v�rifie que ce n'est pas vide
		assertTrue(!candidats.contains(personne));
	}

	//Inscriptions / Sauvegarde
}
