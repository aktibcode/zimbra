package entite;
import connec.ConnexionMySql;
import connec.ConnexionMySql.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class MethodeRecetteDepense {
	Connection conn = null;
	
	public ArrayList<eleveEL> getData2(String clsID, String anSco){
		ArrayList<eleveEL> list = new ArrayList<eleveEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT elv_id, elv_nom, elv_prenom, Date_de_naissance, Lieu_de_naissance,"
					+ "Nom_du_pere, Nom_de_la_mere, Quartier,  nomEtprenom,  anne_scolaire, elv_cls FROM eleve WHERE elv_cls = '"+clsID+"' "
							+ "and anne_scolaire = '"+anSco+"' order by nomEtprenom ");
			eleveEL  elv;
			System.out.println("code executer");
			while(rs.next()){
				elv = new eleveEL (
						rs.getInt("elv_id"),
						rs.getString("elv_nom"),
						rs.getString("elv_prenom"),
						rs.getString("Date_de_naissance"),
						rs.getString("Lieu_de_naissance"),
						rs.getString("Nom_du_pere"),
						rs.getString("Nom_de_la_mere"),
						rs.getString("Quartier"),
						
						rs.getString("nomEtprenom"),
						rs.getString("anne_scolaire"),
						rs.getString("elv_cls")
						);
						list.add(elv);
						System.out.println("code executer 2"+elv);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public ArrayList<eleveEL> getData(String clsID3, String ansco){
		ArrayList<eleveEL> list = new ArrayList<eleveEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT elv_id, elv_nom, elv_prenom, Date_de_naissance, Lieu_de_naissance,"
					+ "Nom_du_pere, Nom_de_la_mere, Quartier,  nomEtprenom,  anne_scolaire, elv_cls FROM eleve WHERE anne_scolaire = '"+clsID3+"' "
							+ "and elv_cls ='"+ansco+"'");
			eleveEL  elv;
			System.out.println("code executer");
			while(rs.next()){
				elv = new eleveEL (
						rs.getInt("elv_id"),
						rs.getString("elv_nom"),
						rs.getString("elv_prenom"),
						rs.getString("Date_de_naissance"),
						rs.getString("Lieu_de_naissance"),
						rs.getString("Nom_du_pere"),
						rs.getString("Nom_de_la_mere"),
						rs.getString("Quartier"),
						
						rs.getString("nomEtprenom"),
						rs.getString("anne_scolaire"),
						rs.getString("elv_cls")
						);
						list.add(elv);
						System.out.println("code executer 2"+elv);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public ArrayList<inscriptionEL> getData4(String clsID){
		
		ArrayList<inscriptionEL> list = new ArrayList<inscriptionEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT cls_nom, Montant_cls, Montant_tenue , montant_TeeShirt , coef_classe from classe WHERE cls_nom = '"+clsID+"'");
			inscriptionEL  ins;
			System.out.println("code executer");
			while(rs.next()){
				ins = new inscriptionEL (
						rs.getString("cls_nom"),
						rs.getDouble("Montant_cls"),
						rs.getDouble("Montant_tenue"),
						rs.getDouble("montant_TeeShirt"),
						rs.getInt("coef_classe")
						);
						list.add(ins);
						System.out.println("code executer 2");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
public ArrayList<inscriptionEL> getData4LTC(String clsID){
		
		ArrayList<inscriptionEL> list = new ArrayList<inscriptionEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT cls_nom, Montant_cls, Montant_tenue , montant_TeeShirt , coef_classe from classeltc WHERE cls_nom = '"+clsID+"'");
			inscriptionEL  ins;
			System.out.println("code executer");
			while(rs.next()){
				ins = new inscriptionEL (
						rs.getString("cls_nom"),
						rs.getDouble("Montant_cls"),
						rs.getDouble("Montant_tenue"),
						rs.getDouble("montant_TeeShirt"),
						rs.getInt("coef_classe")
						);
						list.add(ins);
						System.out.println("code executer 2");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	}
	
	

}
