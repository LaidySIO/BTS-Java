package inscriptions;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.SortedSet;
import java.util.TreeSet;

import utilitaires.ligneDeCommande.Menu;
import DB.Req;
import InterfaceUtilisateur.Interface;

/**
 * Point d'entr�e dans l'application, un seul objet de type Inscription permet
 * de g�rer les comp�titions, candidats (de type equipe ou personne) ainsi que
 * d'inscrire des candidats � des comp�tition.
 */

// TODO impl�menter toutes les m�thodes avec des if (db)
public class Inscriptions implements Serializable {
	private static final long serialVersionUID = -3095339436048473524L;
	private static final String FILE_NAME = "Inscriptions.srz";
	private static Inscriptions inscriptions;
	public static boolean db = true;
	private SortedSet<Competition> competitions = new TreeSet<>();
	private SortedSet<Candidat> candidats = new TreeSet<>();

	private Inscriptions(boolean db) {
		Inscriptions.db = db;
		if (db) {
			Req.chargeEquipes(this);
			Req.chargePersonnes(this);
			Req.chargeCompet(this);
		}
	}

	public void setdb(boolean db) {
		System.out.println("chgt de valeur");
		this.db = db;
	}

	/**
	 * Retourne les comp�titions.
	 * 
	 * @return
	 */
	/**
	 * Retourne toutes les personnes.
	 * 
	 * @return
	 */	

	//String persList[] = new String[bdd. *xxx*()+1];
	
	//for (int i = 0; i < persList.length; i++) 
	//{
	//	persList[i] = bdd. *xxx*(i);
	//  return persList;
	//}
	// 

	public SortedSet<Personne> getPersonnes() {

	SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
		{
			if (c instanceof Personne)
				personnes.add((Personne) c);
		}
		return Collections.unmodifiableSortedSet(personnes);
}

	/**
	 * Retourne toutes les �quipes.
	 * 
	 * @return
	 */

	public SortedSet<Equipe> getEquipes()
	{

		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe)
				equipes.add((Equipe) c);
		return Collections.unmodifiableSortedSet(equipes);
	}

	
	public SortedSet<Competition> getCompetitions() {

		return Collections.unmodifiableSortedSet(competitions);
		 
	}

	/**
	 * Retourne tous les candidats (personnes et �quipes confondues).
	 * 
	 * @return
	 */


	public SortedSet<Candidat> getCandidats() {
		// DB.Req.affEqui();
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Cr�e une comp�tition. Ceci est le seul moyen, il n'y a pas de
	 * constructeur public dans {@link Competition}.
	 * 
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */

	
	public Competition createCompetition(String nom, 
			LocalDate dateCloture, boolean enEquipe,boolean bool)
	{
		if (bool){
			if (db)
		DB.Req.addComp(nom, dateCloture, enEquipe);
		System.out.println("La competition " +nom+  " a �t� rajout� � la communaut�" );
		}
		Competition competition = new Competition(this, nom, dateCloture, enEquipe);
		
		competitions.add(competition);
		return competition;

	}

	/**
	 * Cr�e un Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */

	public Personne createPersonne(String nom, String prenom, String mail, boolean bool) {
		if (bool){
			if(db)
			DB.Req.addPers(nom, prenom, mail);
			System.out.println("La personne " +prenom+ " " +nom+ " a �t� rajout� � la communaut�" );
		}
		Personne personne = new Personne(this, nom, prenom, mail);
		candidats.add(personne);
		return personne;

	}

	/**
	 * Cr�e un Candidat de type �quipe. Ceci est le seul moyen, il n'y a pas de
	 * constructeur public dans {@link Equipe}.
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */

	
//	public Equipe createEquipe(String nom, boolean bool)
//	{
//		System.out.println("test insert equipe au chargement");
//		if (bool) 
//			if(db)
//			DB.Req.addEqui(nom);
//		Equipe equipe = new Equipe(this, nom);
//		System.out.println("L'�quipe  " +nom+ " a �t� rajout� � la communaut�" );
//		candidats.add(equipe);
//		return equipe;
//		return createEquipe(nom, bool, null);
//	}
	
	public Equipe createEquipe (String nom, boolean bool,Personne entraineur)
	{
		
		if (bool) {
			if(db)
				DB.Req.addEqui(nom);
				System.out.println("L'�quipe " +nom+ " a �t� rajout�e � la communaut�");
		}
		Equipe equipe = new Equipe(this, nom, entraineur);
		candidats.add(equipe);
		return equipe;
	}

	/**
	 * supprime une comp�tition de la liste des comp�titions
	 * 
	 * @param competition
	 */

	
	void remove(Competition competition)
	{
		if (db)
			DB.Req.suppComp(competition.getNom());
		competitions.remove(competition);
	}

	/**
	 * supprime un candidat de la liste des candidats qu'il soit Personne ou
	 * Equipe
	 * 
	 * @param candidat
	 */

	void remove(Candidat candidat, boolean bool)
	{
		if (bool)
			if (db)
				DB.Req.suppCand(candidat.getNom());
			candidats.remove(candidat);
	}

	/**
	 * Retourne l'unique instance de cette classe. Cr�e cet objet s'il n'existe
	 * d�j�.
	 * 
	 * @return l'unique objet de type {@link Inscriptions}.
	 */

	public static Inscriptions getInscriptions(boolean db) {
		if (inscriptions == null) {
			if (!db)
				inscriptions = readObject();
			else {
				if (inscriptions == null)
					inscriptions = new Inscriptions(db);
			}
		}
		return inscriptions;
	}

	private static Inscriptions readObject() {
		ObjectInputStream ois = null;
		try {
			FileInputStream fis = new FileInputStream(FILE_NAME);
			ois = new ObjectInputStream(fis);
			return (Inscriptions) (ois.readObject());
		} catch (IOException | ClassNotFoundException e) {
			return null;
		} finally {
			try {
				if (ois != null)
					ois.close();
			} catch (IOException e) {
			}
		}
	}

	/**
	 * Sauvegarde le gestionnaire pour qu'il soit ouvert automatiquement lors
	 * d'une ex�cution ult�rieure du programme.
	 * 
	 * @throws IOException
	 */

	public void sauvegarder() throws IOException {
		ObjectOutputStream oos = null;
		try {
			FileOutputStream fis = new FileOutputStream(FILE_NAME);
			oos = new ObjectOutputStream(fis);
			oos.writeObject(this);
		} catch (IOException e) {
			throw e;
		} finally {
			try {
				if (oos != null)
					oos.close();
			} catch (IOException e) {
			}
		}
	}

	@Override
	public String toString() {
		return "Candidats : " + getCandidats().toString() + "\nCompetitions  "
				+ getCompetitions().toString();
	}

	public static void main(String[] args) {
		Inscriptions inscriptions = Inscriptions.getInscriptions(false);


		Interface.getMenuPrincipal().start();

		LocalDate localDate = LocalDate.of(2016, 03, 05);
		Competition flechettes = inscriptions.createCompetition(
				"Mondial de fl�chettes", localDate, false,false);
		Personne tony = inscriptions.createPersonne("Tony", "Dent de plomb",
				"azerty",false), boris = inscriptions.createPersonne("Boris",
				"le Hachoir", "ytreza",false);
		flechettes.add(tony);
		Equipe lesManouches = inscriptions.createEquipe("Les Manouches",false,null);
		lesManouches.add(boris);
		lesManouches.add(tony);

		try {
			inscriptions.sauvegarder();
		} catch (IOException e) {
			System.out.println("Sauvegarde impossible." + e);
		}
	}
}
