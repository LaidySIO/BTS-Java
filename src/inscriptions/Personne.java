package inscriptions;
import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Set;
import java.util.TreeSet;



/**
 * Représente une personne physique pouvant s'inscrire à une compétition.
 */

public class Personne extends Candidat
{
	private static final long serialVersionUID = 4434646724271327254L;
	private String prenom, mail;
	private Set<Equipe> equipes;
	
	Personne(Inscriptions inscriptions, String nom, String prenom, String mail)
	{
		super(inscriptions, nom);
		this.prenom = prenom;
		this.mail = mail;
		equipes = new TreeSet<>();
	}
	
	public static String aff()
	{
		 
		String afficheper = "Select * from Personne;";
		ResultSet rs = DB.Base.connexionQuery(afficheper);
		try {
			String s="";
			while(rs.next()) {
				s += rs.getString("nomPersonne")+ " "+ rs.getString("prenomPersonne")+"\n";
			return s;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			//System.out.println( e.getMessage() );
		}
		return "";
	}
	/**
	 * Retourne le prénom de la personne.
	 * @return 
	 * @return
	 */
	

	public String getPrenom()
	{
		String afficheprenom = "Select prenomPersonne from Personne "
				+ "where prenomPersonne ='"+ prenom+"';";
		ResultSet rs = DB.Base.connexionQuery(afficheprenom);
		try {
			while(rs.next()) {
				return  rs.getString("prenomPersonne");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		return prenom;
	}

	/**
	 * Modifie le prénom de la personne.
	 * @param prenom
	 */
	
	public void setPrenom(String prenom)
	{
		String setP  = "UPDATE Personne SET prenomPersonne = '"+ prenom + "' "
				+ "WHERE nomPersonne ='"+ this.mail+"';";
		DB.Base.connexionExe(setP);

		this.prenom = prenom;
	}

	/**
	 * Retourne l'adresse électronique de la personne.
	 * @return
	 */
	
	public String getMail()
	{
		String affichemail = "Select mail from Personne; "
				+ "where prenomPersonne ='"+ prenom+"';";
		ResultSet rs = DB.Base.connexionQuery(affichemail);
		try {
			while(rs.next()) {
				return  rs.getString("mail");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println( e.getMessage() );
		}
		return mail;
	}

	/**
	 * Modifie l'adresse électronique de la personne.
	 * @param mail
	 */
	
	public void setMail(String mail)
	{
		String setM  = "UPDATE Personne SET mail = '"+ mail + "' "
				+ "WHERE nomPersonne ='"+ this.mail+"';";
		DB.Base.connexionExe(setM);

		this.prenom = prenom;
		this.mail = mail;
	}

	/**
	 * Retoure les équipes dont cette personne fait partie.
	 * @return
	 */
	
	public Set<Equipe> getEquipes()
	{
		return Collections.unmodifiableSet(equipes);
	}
	
	boolean add(Equipe equipe)
	{
		return equipes.add(equipe);
	}

	boolean remove(Equipe equipe)
	{
		return equipes.remove(equipe);
	}
	
	@Override
	public void delete()
	{
		super.delete();
		for (Equipe e : equipes)
			e.remove(this);
	}
	
	@Override
	public String toString()
	{
		return super.toString() + " membre de " + equipes.toString();
	}
}
