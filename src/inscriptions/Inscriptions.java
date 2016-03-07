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
import java.util.Date;
import java.util.SortedSet;
import java.util.TreeSet;

import DB.Req;

/**
 * Point d'entrée dans l'application, un seul objet de type Inscription permet
 * de gérer les compétitions, candidats (de type equipe ou personne) ainsi que
 * d'inscrire des candidats à  des compétition.
 */

// TODO implémenter toutes les méthodes avec des if (db)
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
		// this.db = db;
	}

	/**
	 * Retourne les compétitions.
	 * 
	 * @return
	 */
	/**
	 * Retourne toutes les personnes.
	 * 
	 * @return
	 */

	public SortedSet<Personne> getPersonnes() {
		SortedSet<Personne> personnes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Personne)
				personnes.add((Personne) c);
		return Collections.unmodifiableSortedSet(personnes);
	}

	/**
	 * Retourne toutes les équipes.
	 * 
	 * @return
	 */

	public SortedSet<Equipe> getEquipes() {
		SortedSet<Equipe> equipes = new TreeSet<>();
		for (Candidat c : getCandidats())
			if (c instanceof Equipe)
				equipes.add((Equipe) c);
		return Collections.unmodifiableSortedSet(equipes);
	}

	public SortedSet<Competition> getCompetitions() {
		if (db) {

			String afficheComp = "Select * from Competition;";
			ResultSet res = DB.Base.connexionQuery(afficheComp);
			SortedSet<Competition> tab = new TreeSet<Competition>();
			// Competition competition = new Competition(this,
			// res.getString("nomCompetition"),
			// res.getString("dateCloture")dateCloture, enEquipe);
			try {

				Competition test;
				System.out.println("avant");
				while (res.next()) {
					test = new Competition(this,
							res.getString("nomCompetition"),
							LocalDate.parse(res.getString("dateCloture")),
							res.getBoolean("enEquipe"));
					if (test.getDateCloture() == null)
						System.out.println("achtung ! la date est nulle !!!!");
					else
						System.out.println("OK");
					tab.add(test);
				}
				System.out.println("après");

				return tab;

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				// System.out.println( e.getMessage() );
			}
			return tab;
		}

		else {
			return Collections.unmodifiableSortedSet(competitions);
		}

	}

	/**
	 * Retourne tous les candidats (personnes et équipes confondues).
	 * 
	 * @return
	 */

	public SortedSet<Candidat> getCandidats() {
		// DB.Req.affEqui();
		return Collections.unmodifiableSortedSet(candidats);
	}

	/**
	 * Crée une compétition. Ceci est le seul moyen, il n'y a pas de
	 * constructeur public dans {@link Competition}.
	 * 
	 * @param nom
	 * @param dateCloture
	 * @param enEquipe
	 * @return
	 */

	public Competition createCompetition(String nom, LocalDate dateCloture, boolean enEquipe) {
		//TODO corriger la requête
/*		if (db){
		int intEnEquipe = (enEquipe) ? 1 : 0;
		String createComp = "Insert into competition (nomCompetition,dateCloture,enEquipe) values ('"
				+ nom + "','" + dateCloture + "','" + intEnEquipe + "');";
		DB.Base.connexionExe(createComp);
		}
		*/
//		else{
			Competition competition = new Competition(this, nom, dateCloture,
					enEquipe);
			competitions.add(competition);
			return competition;
//		}
		
	}

	/**
	 * Crée un Candidat de type Personne. Ceci est le seul moyen, il n'y a pas
	 * de constructeur public dans {@link Personne}.
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */

	public Personne createPersonne(String nom, String prenom, String mail) {
		if (db)
			DB.Req.addPers(nom, prenom, mail);
		Personne personne = new Personne(this, nom, prenom, mail);
		candidats.add(personne);
		return personne;

	}

	/**
	 * Crée un Candidat de type équipe. Ceci est le seul moyen, il n'y a pas de
	 * constructeur public dans {@link Equipe}.
	 * 
	 * @param nom
	 * @param prenom
	 * @param mail
	 * @return
	 */

	public Equipe createEquipe(String nom) {

		Equipe equipe = new Equipe(this, nom);
		candidats.add(equipe);
		return equipe;
	}

	/**
	 * supprime une compétition de la liste des compétitions
	 * 
	 * @param competition
	 */

	void remove(Competition competition) {
		// String removeC = "delete from competition where dateCloture = "+
		// competition.getDateCloture()
		// +" and nom = '"+competition.getNom()+"';";
		// DB.Base.connexionExe(removeC);
		competitions.remove(competition);
	}

	/**
	 * supprime un candidat de la liste des candidats qu'il soit Personne ou
	 * Equipe
	 * 
	 * @param candidat
	 */

	void remove(Candidat candidat) {
		// String removeCand =
		// "delete from competition where nom = "+candidat.getNom()+"';";
		// DB.Base.connexionExe(removeCand);
		candidats.remove(candidat);
	}

	/**
	 * Retourne l'unique instance de cette classe. Crée cet objet s'il n'existe
	 * déjà .
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
	 * d'une exécution ultérieure du programme.
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
		LocalDate localDate = LocalDate.of(2016, 03, 05);
		Competition flechettes = inscriptions.createCompetition(
				"Mondial de fléchettes", localDate, false);
		Personne tony = inscriptions.createPersonne("Tony", "Dent de plomb",
				"azerty"), boris = inscriptions.createPersonne("Boris",
				"le Hachoir", "ytreza");
		flechettes.add(tony);
		Equipe lesManouches = inscriptions.createEquipe("Les Manouches");
		lesManouches.add(boris);
		lesManouches.add(tony);
		// System.out.println(inscriptions);
		// lesManouches.delete();
		// System.out.println(inscriptions);
		try {
			inscriptions.sauvegarder();
		} catch (IOException e) {
			System.out.println("Sauvegarde impossible." + e);
		}
	}
}
