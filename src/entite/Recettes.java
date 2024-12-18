package entite;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Recettes {
	Statement st = null;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	String combo;
	
	private void Rece(){
		String sql = "select Sum(Montant_Versé) as Recette From inscription where Année_Scolaire = '"+combo+"'";
		
		try {
			if(resultat.next()){
				resultat.getDouble("Recette");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
	}
}
