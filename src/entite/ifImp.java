package entite;

import java.awt.BorderLayout;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;

import connec.ConnexionMySql;
import connec.Donne;

import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JTextField;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.DefaultComboBoxModel;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import entite.MethodeRecetteDepense;
import entite.eleveEL;
import entite.inscriptionEL;

public class ifImp extends JInternalFrame {
	Connection cnx =null;
	private JTextField textFieldNum;
	private JTextField textField_1;
	
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	private JPanel contentPane;
	private JComboBox comboSemestre;
	private JComboBox comboAn;
	private JComboBox comboClasse;
	private JComboBox comboEleve;
	private JButton button;
	private JTextField textMax;
	private JTextField textMin;
	private JTextField textEffectif;
	
	
	public ifImp() {
		
		setIconifiable(true);
		
		setClosable(true);
		setTitle("Imprimer Bulletin : ");
		setBounds(450, 250, 756, 179);
		//setBounds(300, 128, 818, 209);
		setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 742, 138);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Impression Bulletin Eleve par Classe", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		textFieldNum = new JTextField();
		textFieldNum.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textFieldNum.setEnabled(false);
		textFieldNum.setEditable(false);
		textFieldNum.setColumns(10);
		textFieldNum.setBounds(601, 44, 110, -13);
		panel_1.add(textFieldNum);
		
		JLabel label = new JLabel("Selection Classe :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label.setBounds(183, 42, 128, 25);
		panel_1.add(label);
		
		comboClasse = new JComboBox();
		comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboEleve.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
				
				//comboNumero.removeAllItems();
				//comboEleve.setSelectedItem("");
				if(comboClasse.getSelectedItem().equals(false)){
					System.out.println("Aucune");
					comboEleve.removeAllItems();
				}else{
					//ConnexionMySql mk = new ConnexionMySql();
					MethodeRecetteDepense km = new  MethodeRecetteDepense();
					ArrayList<eleveEL> list = km.getData2(comboClasse.getSelectedItem().toString(), comboAn.getSelectedItem().toString());
					for(int i = 0; i < list.size(); i++ ){
						comboEleve.addItem(list.get(i).getNomEtprenom());
					}
					
			    }
				MaxMoy();
				Effectif();
				//ConnexionMySql mk2 = new ConnexionMySql();
				Donne mk = new Donne();
				ArrayList<inscriptionEL> list = mk.getData4(comboClasse.getSelectedItem().toString());
				for(int j = 0; j < list.size(); j++ ){
				textFieldNum.setText(String.valueOf(list.get(j).getCoef_classe()));
					
				}
			}
			
			
		});
		comboClasse.setBounds(183, 76, 128, 20);
		panel_1.add(comboClasse);
		
		comboEleve = new JComboBox();
		comboEleve.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldNum.setText("");;
				Donne mk = new Donne();
				ArrayList<eleveELE> list2 = mk.getData32(comboEleve.getSelectedItem().toString() , 
						comboClasse.getSelectedItem().toString(), comboAn.getSelectedItem().toString());
				for(int j = 0; j < list2.size(); j++ ){
					textFieldNum.setText(String.valueOf(list2.get(j).getElv_id()));
					System.out.println("Requete executer ");
					//Remplissage();
					
					
					
				//updateTable2();	
				}/**/
			}
		});
		comboEleve.setBounds(321, 76, 252, 20);
		panel_1.add(comboEleve);
		
		JLabel label_1 = new JLabel("Selection Eleve :");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_1.setBounds(340, 42, 211, 25);
		panel_1.add(label_1);
		
		comboSemestre = new JComboBox();
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboSemestre.setBounds(583, 76, 132, 20);
		panel_1.add(comboSemestre);
		
		JLabel label_2 = new JLabel("Selection Semestre :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_2.setBounds(583, 42, 132, 25);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Ann\u00E9e :");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_3.setBounds(27, 42, 128, 25);
		panel_1.add(label_3);
		
		comboAn = new JComboBox();
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				MaxMoy();
				Effectif();
			}
		});
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.setBounds(27, 76, 128, 20);
		panel_1.add(comboAn);
		
		textField_1 = new JTextField();
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(463, 45, 110, -13);
		panel_1.add(textField_1);
		
		button = new JButton("Imprimer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConnexionMySql p = new ConnexionMySql();
				p.ConnexiomBd();
				if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
					if(!comboEleve.getSelectedItem().equals("Choix")){
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP/BlanID.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
							vNomEleve.put("pMax", Double.parseDouble(textMax.getText().toString()));
							vNomEleve.put("pMin", Double.parseDouble(textMin.getText().toString()));
							vNomEleve.put("pEff", Integer.parseInt(textEffectif.getText().toString()));
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);
							
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP/BlanT.jrxml");
							HashMap vNomEleve = new HashMap();
							
							//vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
							vNomEleve.put("pMax", Integer.parseInt(textMax.getText().toString()));
							vNomEleve.put("pMin", Integer.parseInt(textMin.getText().toString()));
							vNomEleve.put("pEff", Integer.parseInt(textEffectif.getText().toString()));
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);
							
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
					if(!comboEleve.getSelectedItem().equals("Choix")){
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP/BlanID2.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
							vNomEleve.put("pMax", Integer.parseInt(textMax.getText().toString()));
							vNomEleve.put("pMin", Integer.parseInt(textMin.getText().toString()));
							vNomEleve.put("pEff", Integer.parseInt(textEffectif.getText().toString()));
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);
							
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{

						
						
						
						try {
						JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP/Blan2.jrxml");
						HashMap vNomEleve = new HashMap();
						
						//vNomEleve.put("pMoy1", Integer.parseInt(textFieldMoy.getText().toString()));
						vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
						vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
						vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
						vNomEleve.put("pMax", Integer.parseInt(textMax.getText().toString()));
						vNomEleve.put("pMin", Integer.parseInt(textMin.getText().toString()));
						vNomEleve.put("pEff", Integer.parseInt(textEffectif.getText().toString()));
						//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
						JasperReport jr = JasperCompileManager.compileReport(jd);
						JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
						JasperViewer jv = new JasperViewer(jp, false);
						jv.setVisible(true);
						
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}
					
				}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre")){
					
					if(!comboEleve.getSelectedItem().equals("Choix")){
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP/BlanID3.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
							vNomEleve.put("pMax", Integer.parseInt(textMax.getText().toString()));
							vNomEleve.put("pMin", Integer.parseInt(textMin.getText().toString()));
							vNomEleve.put("pEff", Integer.parseInt(textEffectif.getText().toString()));
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);
							
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else{
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP/Blan3.jrxml");
							HashMap vNomEleve = new HashMap();
							
							//vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
							vNomEleve.put("pMax", Integer.parseInt(textMax.getText().toString()));
							vNomEleve.put("pMin", Integer.parseInt(textMin.getText().toString()));
							vNomEleve.put("pEff", Integer.parseInt(textEffectif.getText().toString()));
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);
							
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
					
				}else {
					JOptionPane.showMessageDialog(null, "Vous devez choisir un Trimestre");
				}
				
				
			}
		});
		button.setBounds(307, 104, 100, 23);
		panel_1.add(button);
		button.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		textMax = new JTextField();
		textMax.setEditable(false);
		textMax.setColumns(10);
		textMax.setBounds(28, 45, 110, -13);
		panel_1.add(textMax);
		
		textMin = new JTextField();
		textMin.setHorizontalAlignment(SwingConstants.CENTER);
		textMin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textMin.setEnabled(false);
		textMin.setEditable(false);
		textMin.setColumns(10);
		textMin.setBounds(166, 44, 110, -13);
		panel_1.add(textMin);
		
		textEffectif = new JTextField();
		textEffectif.setEditable(false);
		textEffectif.setColumns(10);
		textEffectif.setBounds(321, 45, 110, -13);
		panel_1.add(textEffectif);
		setVisible(true);
		cnx = ConnexionMySql.ConnexiomBd();
		BindCombo();
		BindCombo2();
		BindComboCour();
	}

	public void BindCombo(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT cls_nom , coef_classe FROM classe where coef_classe != '0' ");
		    while(rs.next()){
		    	comboClasse.addItem(rs.getString(1));
		    
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void BindCombo2(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT Semestre FROM semestre ");
		    while(rs.next()){
		    	comboSemestre.addItem(rs.getString(1));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void BindComboCour(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT anne_courant FROM annee ");
			while(rs.next()){
				comboAn.addItem(rs.getString(1));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void MaxMoy(){
		
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT MAX(MOY), MIN(MOY)FROM moyeleve WHERE Classe= '"+comboClasse.getSelectedItem()+"' "
					+ "and Annee='"+comboAn.getSelectedItem().toString()+"'");
			while(rs.next()){
				textMax.setText(String.valueOf(rs.getString(1)));
				textMin.setText(String.valueOf(rs.getString(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void Effectif(){
		
		String sqlT = "select count(elv_id) from eleve as e, classe as c, annee as a "
				+ "WHERE  e.elv_cls = '"+comboClasse.getSelectedItem().toString()+"' and c.cls_nom = e.elv_cls "
				+ "and e.anne_scolaire='"+comboAn.getSelectedItem().toString()+"' and e.anne_scolaire=a.anne_courant "
						+ " ";
			try {
				prepared = cnx.prepareStatement(sqlT);
				resultat = prepared.executeQuery();
				while (resultat.next()) {
					
				textEffectif.setText(String.valueOf(resultat.getInt("count(elv_id)")));
				//lblCy.setText(String.valueOf(resultat.getInt("id_cycle")));
		
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	
}


