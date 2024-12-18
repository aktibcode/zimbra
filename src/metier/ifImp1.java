package metier;

import java.awt.BorderLayout;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

public class ifImp1 extends JInternalFrame {
	Connection cnx =null;
	
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	private JPanel contentPane;
	private JComboBox comboSemestre;
	private JComboBox comboAn;
	private JComboBox comboClasse;
	private JButton button;
	
	
	public ifImp1() {
		
		setIconifiable(true);
		
		setClosable(true);
		setTitle("Imprimer Proc\u00E8s Verbal : ");
		setBounds(450, 250, 497, 179);
		//setBounds(300, 128, 818, 209);
		setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(0, 0, 481, 138);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Impression Proc\u00E8s Verbal Exames", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		JLabel label = new JLabel("Selection Classe :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label.setBounds(183, 42, 128, 25);
		panel_1.add(label);
		
		comboClasse = new JComboBox();
		comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		
		comboClasse.setBounds(183, 76, 128, 20);
		panel_1.add(comboClasse);
		
		comboSemestre = new JComboBox();
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboSemestre.setBounds(335, 76, 132, 20);
		panel_1.add(comboSemestre);
		
		JLabel label_2 = new JLabel("Selection Semestre :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_2.setBounds(335, 42, 132, 25);
		panel_1.add(label_2);
		
		JLabel label_3 = new JLabel("Ann\u00E9e :");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_3.setBounds(27, 42, 128, 25);
		panel_1.add(label_3);
		
		comboAn = new JComboBox();
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.setBounds(27, 76, 128, 20);
		panel_1.add(comboAn);
		
		button = new JButton("Imprimer");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ConnexionMySql p = new ConnexionMySql();
				p.ConnexiomBd();
				if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
					if(!comboAn.getSelectedItem().equals("Choix")){
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/pv.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("An", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("Cla", comboClasse.getSelectedItem().toString());
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
					if(!comboAn.getSelectedItem().equals("Choix")){
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/pv2.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("An", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("Cla", comboClasse.getSelectedItem().toString());
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
					
					if(!comboAn.getSelectedItem().equals("Choix")){
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/pv3.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("An", comboAn.getSelectedItem().toString());
							//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
							vNomEleve.put("Cla", comboClasse.getSelectedItem().toString());
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
		button.setBounds(183, 107, 128, 23);
		panel_1.add(button);
		button.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
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
	
	
}


