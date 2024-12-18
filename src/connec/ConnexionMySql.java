package connec;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import entite.anneScolaire;
import entite.classeEL;
import entite.eleveEL;
import entite.inscriptionEL;
import entite.matiereEL;
import entite.noteEL;

/**
 * @author Isidore
 *
 */
public class ConnexionMySql {
	Connection conn = null;
	
	
	public static Connection ConnexiomBd(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			//Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/heritier", "root", "");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3307/heritier", "root", "");
			return conn;
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Le Serveur est arrêté, veillez le redemarré"+e);
			////192.168.1.4:3306/heritier", "isidore", "Daniel17//localhost/test18102018
			return null;
		}
		
	}
	
	public static void main(String[] args) {
		ConnexionMySql n = new ConnexionMySql();
		
		n.toString();
		System.out.println(n.toString());
	}
			

}
