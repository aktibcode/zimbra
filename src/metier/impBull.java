package metier;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import connec.ConnexionMySql;
import connec.Donne;
import entite.MethodeRecetteDepense;
import entite.eleveEL;
import entite.inscriptionEL;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;

import javax.swing.JTextField;

public class impBull extends JFrame {
	
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	private JPanel contentPane;
	private JComboBox comboEleve;
	private JComboBox comboClasse;
	private JComboBox comboSemestre;
	private JTextField textFieldNum;
	private JComboBox comboAn;
	private JTextField textFieldMoy;

	public impBull() {
		setBounds(300, 128, 818, 209);
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Impression Bulletin Eleve par Classe", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel label = new JLabel("Selection Classe :");
		label.setBounds(183, 42, 128, 25);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		textFieldNum = new JTextField();
		textFieldNum.setEnabled(false);
		textFieldNum.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		textFieldNum.setEditable(false);
		textFieldNum.setBounds(643, 11, 119, 20);
		panel.add(textFieldNum);
		textFieldNum.setColumns(10);
		
		
		comboClasse = new JComboBox();
		comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboClasse.setBounds(183, 76, 128, 20);
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
				
				//ConnexionMySql mk2 = new ConnexionMySql();
				Donne mk = new Donne();
				ArrayList<inscriptionEL> list = mk.getData4(comboClasse.getSelectedItem().toString());
				for(int j = 0; j < list.size(); j++ ){
				textFieldNum.setText(String.valueOf(list.get(j).getCoef_classe()));
					
				}
			}
			
			
		});
		
		
		JButton button = new JButton("Imprimer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConnexionMySql p = new ConnexionMySql();
				p.ConnexiomBd();
				if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
					if(!comboEleve.getSelectedItem().equals("Choix")){
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/BlanID.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
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
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/BlanT.jrxml");
							HashMap vNomEleve = new HashMap();
							
							//vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
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
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/BlanID2.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
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
						JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/Blan2.jrxml");
						HashMap vNomEleve = new HashMap();
						
						//vNomEleve.put("pMoy1", Integer.parseInt(textFieldMoy.getText().toString()));
						vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
						vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
						vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
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
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/BlanID3.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
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
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/Blan3.jrxml");
							HashMap vNomEleve = new HashMap();
							
							//vNomEleve.put("pID", Integer.parseInt(textFieldNum.getText().toString()));
							vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
							vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
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
		button.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		GroupLayout gl_contentPane = new GroupLayout(contentPane);
		gl_contentPane.setHorizontalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addGroup(gl_contentPane.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_contentPane.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel, GroupLayout.DEFAULT_SIZE, 772, Short.MAX_VALUE))
						.addGroup(gl_contentPane.createSequentialGroup()
							.addGap(335)
							.addComponent(button, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap())
		);
		gl_contentPane.setVerticalGroup(
			gl_contentPane.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_contentPane.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(button)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		panel.setLayout(null);
		panel.add(label);
		panel.add(comboClasse);
		
		comboEleve = new JComboBox();
		comboEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				textFieldNum.setText("");;
				Donne mk = new Donne();
				ArrayList<eleveEL> list2 = mk.getData3(comboEleve.getSelectedItem().toString());
				for(int j = 0; j < list2.size(); j++ ){
					textFieldNum.setText(String.valueOf(list2.get(j).getElv_id()));
					System.out.println("Requete executer ");
					//Remplissage();
					
					
					
				//updateTable2();	
				}/**/
			}
		});
		comboEleve.setBounds(340, 76, 211, 20);
		panel.add(comboEleve);
		
		JLabel lblSelectionEleve = new JLabel("Selection Eleve :");
		lblSelectionEleve.setBounds(340, 42, 211, 25);
		lblSelectionEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectionEleve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		panel.add(lblSelectionEleve);
		
		comboSemestre = new JComboBox();
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboSemestre.setBounds(583, 76, 132, 20);
		panel.add(comboSemestre);
		
		JLabel lblSelectionSemestre = new JLabel("Selection Semestre :");
		lblSelectionSemestre.setBounds(583, 42, 132, 25);
		lblSelectionSemestre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectionSemestre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		panel.add(lblSelectionSemestre);
		
		JLabel lblAnne = new JLabel("Ann\u00E9e :");
		lblAnne.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnne.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblAnne.setBounds(27, 42, 128, 25);
		panel.add(lblAnne);
		
		comboAn = new JComboBox();
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.setBounds(27, 76, 128, 20);
		panel.add(comboAn);
		
		textFieldMoy = new JTextField();
		textFieldMoy.setEditable(false);
		textFieldMoy.setBounds(463, 12, 128, 20);
		panel.add(textFieldMoy);
		textFieldMoy.setColumns(10);
		

		contentPane.setLayout(gl_contentPane);
		BindCombo();
		BindCombo2();
		BindComboCour();
		//coef_classe();
	}
	
	public void BindCombo(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT cls_nom, coef_classe FROM classe ");
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
}
