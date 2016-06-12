package DB;

import inscriptions.Competition;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.SortedSet;

import com.mysql.jdbc.ResultSetMetaData;
import com.mysql.jdbc.SQLError;

public class Base {
	
	public static Connection con;
	public static Class c;
	
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
	
	public static Connection connexion () throws ClassNotFoundException
	{
		
		try{
				c = Class.forName("com.mysql.jdbc.Driver") ;
				Driver pilote = (Driver)c.newInstance() ;
				DriverManager.registerDriver(pilote);
				String protocole =  "jdbc:mysql:" ;
				String ip =  "localhost" ;  
				String port =  "3306" ; 
			
				String nomBase =  "inscription" ;  
				String conString = protocole +  "//" + ip +  ":"
						+ "" + port +  "/" + nomBase + "?autoReconnect=true&useSSL=false" ;
				String nomConnexion =  "root" ;  
				String motDePasse =  "donald971" ;
				con = DriverManager.getConnection(
						conString, nomConnexion, motDePasse) ;
					
		}
		catch (Exception e) {
			System.out.println( e.getMessage() );
		}
		return con;
	}

	public static void connexionExe (String req)  
	{
		try {
			connexion();
			String sql = req  ;
			Statement smt = con.createStatement() ;
			smt.executeUpdate(sql) ;
		}  catch (SQLException | ClassNotFoundException  e) {
			((SQLException) e).getErrorCode();
			
			System.out.println( e.getMessage() );
		
		}
		
	}
	


	public static ResultSet connexionQuery (String req)
	{
		Connection con = null;
		try {
			connexion();
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
			try {
				con.close();
			} catch (Exception e) {
				
				
			}
		}
		return null;
	}

	public static void main(String[] args) throws SQLException {
		// TODO Auto-generated method stub
		//inscriptions.Inscriptions test1 = inscriptions.Inscriptions.getInscriptions();
		//inscriptions.Personne test = new Personne(); 
		//String nom = "TITI";
		//Base.connexionQuery("Select * from candidat ;");
		//Base.connexionExe("insert into candidat (nom) values ('LALA')");
		//test1.createPersonne("MIMI", "LALA","MAil");
		//System.out.print(DB.Req.affEqui());
		//System.out.println(DB.Req.affequipe());
		
	}

}
