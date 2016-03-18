package DB;

import inscriptions.*;
import InterfaceUtilisateur.Interface;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import utilitaires.ligneDeCommande.ActionListe;

public class Req {

	public static ResultSet rs;
	public static String req;
	public static Statement smt;
	public static Connection con;


	public Req()
	{

	}

	///////////////////////////////////////////////////////////////////////////////////////////////
	// ////////////////////////////////Fonctions EXE///////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////

	public static void addPers(String nom, String prenom,String mail)
	{

		String createP = "call insertPersonne('" + nom + "','" + prenom + "','" + mail + "');";
		Base.connexionExe(createP);
	}

	public static void addEqui(String nom)
	{
		String createE = "call insertEquipe('" + nom + "');";
		Base.connexionExe(createE);
	}

	public static void addComp(String nom, LocalDate dateCloture,Boolean enEquipe)
	{
		String createC  = "Insert into Competition (nomCompetition,dateCloture,enEquipe) "
				+ "values ('"+ nom +"','"+dateCloture+"','"+ enEquipe + "');";
		Base.connexionExe(createC);
	}
	
	public static void addToCompetition(int idComp, int idCand )
	{
		
	}
	
	////////////////////////////////
	
	
	public static void suppPers(String nom, String prenom,String mail)
	{
		String suppP  = "Delete from Personne where nomPersonne = '"+ nom + "') and prenom = '"+ prenom +"';";
		Base.connexionExe(suppP);
	}
	
	public static void suppComp(String nom, LocalDate dateCloture, boolean enEquipe)
	{
		String suppComp  = "Delete from Competition where nomCompetition = '"+ nom + "') and dateClotureprenom = '"+ dateCloture +"';";
		Base.connexionExe(suppComp);
	}
	
	public static void suppCand(String nom)
	{
		String suppC  = "Delete from Candidat where nom = '"+ nom + "';";
		Base.connexionExe(suppC);
	}
	
	public static void suppEqui(String nom)
	{
		String suppE  = "Delete from Equipe where nom = '"+ nom + "';";
		Base.connexionExe(suppE);
	}
	
	
///////////////////////////////////////
	
	
	public static void modifPren(String oldPrenom, String prenom)
	{
		String modifPers  = "UPDATE Personne SET prenomPersonne = '"+ prenom + "' "
				+ "WHERE nomPersonne ='"+oldPrenom+"';";
		DB.Base.connexionExe(modifPers);
	}
	
	
//	public static void addCand(String nom)
//	{
//		String createCand  = "Insert into Candidat (nom) "
//				+ "values ('"+ nom +"');";
//		Base.connexionExe(createCand);
//	}



	///////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////Fonctions Query//////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////////////////////////



	public static void chargeEquipes(Inscriptions inscriptions)
	{
		try {
			req = "Select * from Equipe;";
			con = DB.Base.connexion();
			smt = con.createStatement();
			rs =smt.executeQuery(req);
			while (rs.next())
				System.out.println("coucou :!!ChargeEquipe!!!!!!!!");
				inscriptions.createEquipe(rs.getString("nomEquipe"));
		}
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
		
	public static void chargePersonnes(Inscriptions inscriptions)
	{
		try {
			req = "Select * from personne;";
			con = DB.Base.connexion();
			smt = con.createStatement();
			rs =smt.executeQuery(req);
			while (rs.next())
			{
				System.out.println("coucou :!!ChargePersonnes!!!!!!!!");
				inscriptions.createPersonne(rs.getString("nomPersonne"), rs.getString("prenomPersonne"),rs.getString("mail"));
			}
		}	
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
	
	
	public static void chargeCompet(Inscriptions inscriptions)
	{
		try {
			req = "Select * from Competition;";
			con = DB.Base.connexion();
			smt = con.createStatement();
			rs =smt.executeQuery(req);
			while (rs.next())
				System.out.println("coucou :!!ChargeCompet!!!!!!!!");
				inscriptions.createCompetition(rs.getString("nomCompetition"), rs.getDate("dateCloture").toLocalDate(),rs.getBoolean("enEquipe"));
		}
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
	
	
	
	
	
	
//	public static ArrayList<String> affEqui() throws SQLException
//	{
//		ArrayList<String> a = new ArrayList<>();
//		try {
//			req = "Select * from Equipe;";
//			String b;
//			con = DB.Base.connexion();
//			smt = con.createStatement();
//			rs =smt.executeQuery(req);
//
//
//			while (rs.next())
//			{
//				b = rs.getString("nomEquipe");
//				a.add(b);
//
//			}
//
//			//			for (String value : a)
//			//		{
//			//			System.out.println(value);
//			//		}	
//		}
//		catch (Exception e) {
//			// gestion des exceptions
//			System.out.println( e.getMessage() );
//		}
//		return a;
//
//
//	}


}
