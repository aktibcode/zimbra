package connec;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entite.anneScolaire;
import entite.classeEL;
import entite.eleveEL;
import entite.eleveELE;
import entite.enseignantEL;
import entite.inscriptionEL;
import entite.matParClas;
import entite.matiereEL;
import entite.matiereELType;

public class Donne {
	//Connection conn = null;
	public ArrayList<eleveEL> getData2(String clsID){
		ArrayList<eleveEL> list = new ArrayList<eleveEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT elv_id, nomEtprenom,elv_nom, elv_prenom, Date_de_naissance, Lieu_de_naissance,"
					+ "Nom_du_pere, Nom_de_la_mere, Quartier,  nomEtprenom, elv_cls, annee_scolaire FROM eleve WHERE elv_cls = '"+clsID+"'");
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
						rs.getString("annee_scolaire"),
						rs.getString("elv_cls")
						);
						list.add(elv);
						System.out.println("code executer 2");
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
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////////////
	/////////////////////////////////////////////////////////////////////////////////////////////////
	
	
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
	
	
	
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * @param nomEtprenom
	 * @return
	 * 
	 * METHODE de Recuperation des données Notes
	 * 
	 * 
	 * 
	 */
	
	public ArrayList<eleveEL> getData3(String nomprenom){
		ArrayList<eleveEL> list = new ArrayList<eleveEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT elv_id, nomEtprenom,elv_nom, elv_prenom, Date_de_naissance, Lieu_de_naissance,"
					+ "Nom_du_pere, Nom_de_la_mere, Quartier, nomEtprenom, elv_cls, anne_scolaire FROM eleve WHERE nomEtprenom = '"+nomprenom+"'");
			eleveEL  elv;
			System.out.println("code executer 3");
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
						System.out.println("code executer 4");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	public ArrayList<eleveELE> getData32(String nomprenom, String cls, String an){
		ArrayList<eleveELE> list = new ArrayList<eleveELE>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		
		//
		
		try {
			st = con.createStatement();
			
			rs = st.executeQuery("SELECT distinct elv_id , elv_nom, elv_prenom, Date_de_naissance, Lieu_de_naissance, Nom_du_pere, "
					+ "Nom_de_la_mere, nomEtprenom , anne_scolaire, Quartier, elv_cls "
					+ "FROM classe as c, eleve as e WHERE e.elv_cls  = '"+cls+"' and e.anne_scolaire='"+an+"' and e.nomEtprenom = '"+nomprenom+"'");
			
			/*
			 SELECT distinct elv_id , elv_nom, elv_prenom, Date_de_naissance, Lieu_de_naissance, Nom_du_pere, Nom_de_la_mere, nomEtprenom , anne_scolaire, Quartier, elv_cls 
FROM classe as c, eleve as e WHERE e.elv_cls  = '"+cls+"' and e.anne_scolaire='"+an+"' and e.nomEtprenom = '"+nomprenom+"'
			  
			 * 
			 * 
			 * */
			
			eleveELE  elv;
			System.out.println("code executer 3");
			while(rs.next()){
				elv = new eleveELE (
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
						System.out.println("code executer 4");
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	
	public ArrayList<anneScolaire> getData5(String anScolaire){
				
				ArrayList<anneScolaire> list = new ArrayList<anneScolaire>();
				Connection con = ConnexionMySql.ConnexiomBd();
				Statement st;
				ResultSet rs;
				try {
					st = con.createStatement();
					rs = st.executeQuery("SELECT * from annee WHERE anne_courant = '"+anScolaire+"'");
					anneScolaire  ins;
					System.out.println("code executer");
					while(rs.next()){
						ins = new anneScolaire (
								rs.getString("anne_courant")
								);
								list.add(ins);
								
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				return list;
		}
		
	
	public ArrayList<classeEL> getData6(String anS, int IDelv){
				
				ArrayList<classeEL> list = new ArrayList<classeEL>();
				Connection con = ConnexionMySql.ConnexiomBd();
				Statement st;
				ResultSet rs;
				try {
					st = con.createStatement();
					rs = st.executeQuery("SELECT id_recu, nom_cls, nom_prenom, elv_id, Montant_Versé, Reste_à_Versé, Date_Versement, Année_Scolaire from inscription WHERE Année_Scolaire = '"+anS+"'"
							+ " and elv_id = '"+IDelv+"'");
					classeEL  ins;
					System.out.println("code executer");
					while(rs.next()){
						ins = new classeEL(
								rs.getInt("id_recu"),
								rs.getString("nom_cls"),
								rs.getString("nom_prenom"),
								rs.getInt("elv_id"),
								rs.getDouble("Montant_Versé"),
								rs.getDouble("Reste_à_Versé"),
								rs.getString("Date_Versement"),
								rs.getString("Année_Scolaire")
								);
								list.add(ins);
								
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		
		return list;
		
		
	}
	
	public ArrayList<matiereEL> getData(String  cours){
		ArrayList<matiereEL> list = new ArrayList<matiereEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT  cr_id, cr_titre, cr_coef FROM cours WHERE cr_titre = '"+cours+"'");
			matiereEL  cou;
			System.out.println("code executer");
			while(rs.next()){
				cou = new matiereEL(
						rs.getInt("cr_id"),
						rs.getString("cr_titre"),
						rs.getInt("cr_coef")								
						);
						list.add(cou);
						System.out.println("code executer 2");
			}
			
					
					
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	public ArrayList<matiereELType> getDataType(){
		ArrayList<matiereELType> list = new ArrayList<matiereELType>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT  id_ty , titre FROM type_matiere ");
			matiereELType  cou;
			System.out.println("code executer");
			while(rs.next()){
				cou = new matiereELType(
						rs.getInt("id_ty"),
						rs.getString("titre")								
						);
						list.add(cou);
						System.out.println("code executer 2");
			}
			
					
					
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
	
	
	
	
	
	
	
	
//////////////////////// LYCEE GENERAL //////////////////////////////	
	
	public ArrayList<matParClas> getDataM(String  cls, int idE, String an){
		ArrayList<matParClas> list = new ArrayList<matParClas>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT  idMClsEns, crTitre, clsNom, anne_scol FROM matparclasse "
					+ "WHERE clsNom = '"+cls+"' and idMClsEns = '"+idE+"'  and anne_scol= '"+an+"'");
			matParClas  cou;
			System.out.println("code executer");
			while(rs.next()){
				cou = new matParClas(
						rs.getInt("idMClsEns"),
						rs.getString("crTitre"),
						rs.getString("clsNom"),
						rs.getString("anne_scol")								
						);
						list.add(cou);
						System.out.println("code executer 2");
			}
			
				
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

	
	public ArrayList<matParClas> getDataMLTClas(String  cls, int idE, String an){
		ArrayList<matParClas> list = new ArrayList<matParClas>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT  idMClsEns, crTitre, clsNom, anne_scol FROM matparclasseltc "
					+ "WHERE clsNom = '"+cls+"' and idMClsEns = '"+idE+"'  and anne_scol= '"+an+"'");
			matParClas  cou;
			System.out.println("code executer");
			while(rs.next()){
				cou = new matParClas(
						rs.getInt("idMClsEns"),
						rs.getString("crTitre"),
						rs.getString("clsNom"),
						rs.getString("anne_scol")								
						);
						list.add(cou);
						System.out.println("code executer 2");
			}
			
				
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
//////////////////////////////////////////////////////////////////////////
	
	public ArrayList<matiereEL> getDataMLTC(String  cours){
		ArrayList<matiereEL> list = new ArrayList<matiereEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT  cr_id, cr_titre, cr_coef FROM coursltc WHERE cr_titre = '"+cours+"'");
			matiereEL  cou;
			System.out.println("code executer");
			while(rs.next()){
				cou = new matiereEL(
						rs.getInt("cr_id"),
						rs.getString("cr_titre"),
						rs.getInt("cr_coef")								
						);
						list.add(cou);
						System.out.println("code executer 2");
			}
			
					
					
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}
	
	
/////////////////////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	public ArrayList<enseignantEL> getDataEn(int  idEns){
		ArrayList<enseignantEL> list = new ArrayList<enseignantEL>();
		Connection con = ConnexionMySql.ConnexiomBd();
		Statement st;
		ResultSet rs;
		
		try {
			st = con.createStatement();
			rs = st.executeQuery("SELECT  idMEns, cours, nomEns, clsEns, idEns FROM enseignantEL WHERE idEns = '"+idEns+"'");
			enseignantEL  cou;
			System.out.println("code executer");
			while(rs.next()){
				cou = new enseignantEL(
						rs.getInt("idMEns"),
						rs.getString("cours"),
						rs.getString("nomEns"),
						rs.getString("clsEns"),
						rs.getInt("idEns")
						);
						//list.add(cou);
						System.out.println("code executer 2");
			}
			
					
					
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
		
	}

}
