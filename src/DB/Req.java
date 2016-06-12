package DB;

import inscriptions.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class Req {

	public static ResultSet rs;
	public static String req;
	public static Statement smt;
	public static Connection con;
	static boolean db = true;
	static Inscriptions inscription;
	static int cpt = 0;

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

	public static void addComp(String nom, LocalDate dateCloture,Boolean enEquipes)
	{
//		Suite au problème d'insertion avec les valeurs true et false, les valeurs 
//		1 et 0 seront insérés dans la bdd, enEquipe etant de type tinyint.
		int enEquipe;
		if (enEquipes == true){
			enEquipe = 1;
		}
			
		else {
			enEquipe = 0;
			
		}	
			
		String createC  = "Call insertCompet ('"+ nom +"','"+dateCloture+"','"+ enEquipe + "');";
		Base.connexionExe(createC);
	}
	
	public static void addToCompetition(String nom, String nomcand )
	{
		String getcomp,getcand;
		int idcand, idcomp;
		
		getcomp = "call getidcomp("+ nom+")";
		getcand = "call getidcand("+ nomcand+")";
		ResultSet rs = Base.connexionQuery(getcomp);
		ResultSet rs2 = Base.connexionQuery(getcand);
		try {
			idcand = rs2.getInt(0);
			idcomp = rs.getInt(0);
			String req = "call addtocompete("+ idcand +","+ idcomp+")";
			DB.Base.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	public static void addToEquipe(Personne personne, Equipe equipe )
	{
		String getpers,getequi;
		int idpers =0;
		int idequi =0;
		
		getpers = "call getidpers('"+ personne.getNom()+"','"+personne.getPrenom()+"'"
				+ ",'"+personne.getMail()+"')";
		getequi = "call getidequi("+ equipe.getNom()+")";
		ResultSet rs = Base.connexionQuery(getpers);
		ResultSet rs2 = Base.connexionQuery(getequi);
		try {
			while (rs.next())
			{
				idpers = rs.getInt("idPersonne");
			}
			while (rs2.next())
			{
				idequi = rs2.getInt("idEquipe");
			}
			
			String req = "call addtoequipe("+ idequi +","+ idpers+")";
			DB.Base.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	////////////////////////////////
	
	
	public static void suppPers(String nom, String prenom,String mail)
	{
		String idpers = "call getidpers('"+ nom+"','"+prenom+"','"+mail+"')";
		int idPers= 0;
		
		try {	
			
		rs = Base.connexionQuery(idpers);
		while (rs.next())
		{
			idPers = rs.getInt("idPersonne");
		}
			
			String req = "call suppers('"+ idPers +"','"+nom+"')";
			DB.Base.connexionExe(req);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void suppComp(String nom)
	{
	
		String idcomp = "call getidcomp('"+ nom+"')";
		int idComp = 0;
		
		try {
			rs = Base.connexionQuery(idcomp);
			while (rs.next())
			{
				idComp = rs.getInt("idCompetition");
			}
			
//			System.out.println("idComp");
			String req = "call suppcompete('"+ idComp +"','"+nom+"')";
			DB.Base.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public static void suppCand(String nom)
	{

		String nomcand = "call getidcand('"+ nom+"')";
		int idcand = 0;

		ResultSet rs = Base.connexionQuery(nomcand);
		
		try {
			rs = Base.connexionQuery(nomcand);
			while (rs.next())
			{
				idcand = rs.getInt("idCandidat");
			}	
			String req = "call suppcand('"+ idcand +"','"+nom+"')";
			DB.Base.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void suppEqui(String nomequi)
	{
		String getequi = "call getidequi('"+ nomequi+"')";
		int idequi = 0;
		try {
			
			rs = Base.connexionQuery(getequi);
			while (rs.next())
			{
				idequi = rs.getInt("idEquipe");
			}
			
			String req = "call suppequi('"+ idequi +"','"+ nomequi+"')";
			DB.Base.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void suppInsc(String nom,String nomcand)
	{
		String getcomp,getcand;
		int idcand, idcomp;
		getcomp = "call getidcomp("+ nom+")";
		getcand = "call getidcand("+ nomcand+")";
		ResultSet rs = Base.connexionQuery(getcomp);
		ResultSet rs2 = Base.connexionQuery(getcand);
		try {
			idcand = rs2.getInt(0);
			idcomp = rs.getInt(0);
			String req = "call supptocompete("+ idcand +","+ idcomp+")";
			DB.Base.connexionExe(req);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
///////////////////////////////////////
	
	
	public static void modifPren(String oldPrenom, String prenom,Personne personne )
	{
		String modifprenom;
		int id;
		modifprenom = "call getidpers('"+ personne.getNom()+"','"+personne.getPrenom()+"','"+personne.getMail()
				+"')";
		rs = Base.connexionQuery(modifprenom);
		try {
		id = rs.getInt(0);
		String modifPers  = "call modifprenom("+id+",'"+ prenom +"','"+oldPrenom+"';";
		DB.Base.connexionExe(modifPers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void modifnom(String oldnom, String nom,Personne personne )
	{
		String modifnom;
		int id = 0;
		modifnom = "call getidpers('"+ personne.getNom()+"','"+personne.getPrenom()+"','"+personne.getMail()
				+"')";
		
		try {
		rs = Base.connexionQuery(modifnom);
		while (rs.next())
		{
			id = rs.getInt("idPersonne");
		}
		
		String modifPers  = "call modifprenom("+id+",'"+ nom +"','"+oldnom+"';";
		DB.Base.connexionExe(modifPers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	public static void modifmail(String oldmail, String mail,Personne personne )
	{
		String modifmail;
		int id;
		modifmail= "call getidpers('"+ personne.getNom()+"',"
				+ "'"+personne.getPrenom()+"','"+personne.getMail()+"')";
		rs = Base.connexionQuery(modifmail);
		try {
		id = rs.getInt(0);
		String modifPers  = "call modifprenom("+id+",'"+ mail +"','"+oldmail+"';";
		DB.Base.connexionExe(modifPers);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	

	public static void modifequipe(String oldnom, String nom,Equipe equipe )
	{
		String modifnom;
		int id;
		modifnom = "call getidpers('"+ equipe.getNom() +"')";
		rs = Base.connexionQuery(modifnom);
		try {
		id = rs.getInt(0);
		String modifequi  = "call modifprenom("+id+",'"+ nom +"','"+oldnom+"';";
		DB.Base.connexionExe(modifequi);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void modifcomp(String oldnom, String nom, Competition competition )
	{
		String modifnom;
		int id;
		modifnom = "call getidcomp('"+ competition.getNom() +"')";
		rs = Base.connexionQuery(modifnom);
		try {
		id = rs.getInt(0);
		String modifcomp  = "call modifnomcomp("+id+",'"+ oldnom +"','"+nom+"';";
		DB.Base.connexionExe(modifcomp);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
	
	
	public static void modifdate(LocalDate newdate,Competition competition )
	{
		String modifdate;
		int id;
		modifdate = "call getidcomp('"+ competition.getNom() +"')";
		rs = Base.connexionQuery(modifdate);
		try {
		id = rs.getInt(0);
		String modifDate  = "call modifdate("+id+",'"+ competition.getDateCloture() +"','"+newdate+"';";
		DB.Base.connexionExe(modifDate);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
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
//			System.out.println("Charge equipe!!!!!!!!!!!!!!!");
			
			String req = "Select * from Equipe;";
			con = DB.Base.connexion();
			smt = con.createStatement();
			rs =smt.executeQuery(req);
			while (rs.next())
				inscriptions.createEquipe(rs.getString("nomEquipe"),false, null);
//				System.out.println("Charge equipe");
				
		}
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
		System.err.println("Chargement des equipes effectué");
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
				
//				System.out.println("coucou :!!ChargePersonnes!!!!!!!!");
				inscriptions.createPersonne(rs.getString("nomPersonne"), rs.getString("prenomPersonne"),rs.getString("mail"),false);
			}
		}	
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
		System.err.println("Chargement des Personnes effectué");
	}
	
//	public static void afficheComp()
//	{
//		try {
//			req = "Select * from competition;";
//			con = DB.Base.connexion();
//			smt = con.createStatement();
//			rs =smt.executeQuery(req);
//			while (rs.next())
//			{
//				cpt ++;
//				System.out.println(cpt + " - " + rs.getString("nomcompetition"));
//				//inscriptions.createPersonne(rs.getString("nomPersonne"), rs.getString("prenomPersonne"),rs.getString("mail"));
//			}
//			cpt = 0;
//		}	
//		catch (Exception e) {
//			// gestion des exceptions
//			System.out.println( e.getMessage() );
//		}
//	}
	public static void chargeCompet(Inscriptions inscriptions)
	{
		try {
			req = "Select * from Competition;";
			con = DB.Base.connexion();
			smt = con.createStatement();
			rs =smt.executeQuery(req);
			while (rs.next())
				inscriptions.createCompetition(rs.getString("nomCompetition"), rs.getDate("dateCloture").toLocalDate(),rs.getBoolean("enEquipe"),false);
//				System.out.println("coucou :!!ChargeCompetitionCompet!!!!!!!!");
				
		}
		catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
		System.err.println("Chargement des Competitions effectué");
	}
	
	
	
	
	



}
