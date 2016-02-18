package DB;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.jdbc.ResultSetMetaData;

public class Base {
	
	
	public Base()
	{
		
	}
	
	public static String[] getNomsColonnes(ResultSet resultat) throws SQLException{ 
		   ResultSetMetaData metadata = (ResultSetMetaData) resultat.getMetaData(); 
		   String[] noms = new String[metadata.getColumnCount()]; 
		   for(int i = 0; i < noms.length; i++){ 
		      String nomColonne = metadata.getColumnName(i+1); 
		      noms[i] = nomColonne; 
		   } 
		   return noms; 
		}

	public static void connexionExe (String req)
	{
		try {
			// chargement de la classe par son nom
			Class c = Class.forName("com.mysql.jdbc.Driver") ;
			Driver pilote = (Driver)c.newInstance() ;
			// enregistrement du pilote auprès du DriverManager
			DriverManager.registerDriver(pilote);
			// Protocole de connexion
			String protocole =  "jdbc:mysql:" ;
			// Adresse IP de l’hôte de la base et port
			String ip =  "localhost" ;  // dépend du contexte
			String port =  "3306" ;  // port MySQL par défaut
			// Nom de la base ;
			String nomBase =  "inscriptions" ;  // dépend du contexte
			// Chaîne de connexion
			String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase + "?autoReconnect=true&useSSL=false" ;
			// Identifiants de connexion et mot de passe
			String nomConnexion =  "root" ;  // dépend du contexte
			String motDePasse =  "donald971" ;  // dépend du contexte
			// Connexion
			Connection con = DriverManager.getConnection(
					conString, nomConnexion, motDePasse) ;
			// Envoi d’un requête générique
			String sql = req  ;
			Statement smt = con.createStatement() ;
			/*ResultSet rs = */smt.executeUpdate(sql) ;
		}  catch (Exception e) {
			// gestion des exceptions
			System.out.println( e.getMessage() );
		}
	}
	//}


	public static ResultSet connexionQuery (String req)
	{
		try {
			// chargement de la classe par son nom
			Class c = Class.forName("com.mysql.jdbc.Driver") ;
			Driver pilote = (Driver)c.newInstance() ;
			// enregistrement du pilote auprès du DriverManager
			DriverManager.registerDriver(pilote);
			// Protocole de connexion
			String protocole =  "jdbc:mysql:" ;
			// Adresse IP de l’hôte de la base et port
			String ip =  "localhost" ;  // dépend du contexte
			String port =  "3306" ;  // port MySQL par défaut
			// Nom de la base ;
			String nomBase =  "inscriptions" ;  // dépend du contexte
			// Chaîne de connexion
			String conString = protocole +  "//" + ip +  ":" + port +  "/" + nomBase + "?autoReconnect=true&useSSL=false" ;
			// Identifiants de connexion et mot de passe
			String nomConnexion =  "root" ;  // dépend du contexte
			String motDePasse =  "donald971" ;  // dépend du contexte
			// Connexion
			Connection con = DriverManager.getConnection(
					conString, nomConnexion, motDePasse) ;
			// Envoi d’un requête générique
			String sql = req  ;
			Statement smt = con.createStatement() ;
			ResultSet rs = smt.executeQuery(sql) ;
			return rs;
			
		}
		catch (Exception e) {
		// gestion des exceptions
		System.out.println( e.getMessage() );
		}
		

		
		finally
		{
			return null;
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		inscriptions.Inscriptions test1 = new inscriptions.Inscriptions();
		//inscriptions.Personne test = new Personne(); 
		String nom = "TITI";
		Base.connexionQuery("Select * from candidat ;");
		Base.connexionExe("insert into candidat (nom) values ('LALA')");
		test1.createPersonne("MIMI", "LALA","MAil");

	}

}
