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
		//On a ajouté une compétition, il suffit de vérifier que ce n'est pas vide
		assertTrue(!lesCompetitions.isEmpty());
		
	}

	//Test GetCandidats
	@Test
	public void testGetCandidats() {
		Set<Candidat> lesCandidats = inscriptions.getCandidats();
		//On a ajouté un candidat, il suffit de vérifier que ce n'est pas vide
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
		//On vérifie que ce n'est pas vide / Contient bien la donnnée inscrite( ici c'est Levage de Coude"
		assertTrue(!competitions.contains(TheCompet));
	}

	@Test
	public void testRemoveCandidat() {
		SortedSet<Candidat> candidats = inscriptions.getCandidats();
		personne.delete();
		//On vérifie que ce n'est pas vide
		assertTrue(!candidats.contains(personne));
	}

	//Inscriptions / Sauvegarde
}
