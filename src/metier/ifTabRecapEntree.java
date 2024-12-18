package metier;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
//import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import connec.ConnexionMySql;

import java.awt.Font;
//import java.io.BufferedWriter;
import java.io.File;
/*import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;*/
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
/*import java.text.NumberFormat;
import java.util.Set;
import java.util.TreeMap;
import java.util.logging.Logger;*/




import javax.swing.JButton;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
//import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
//import net.sf.jasperreports.j2ee.servlets.Xml4SwfServlet;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
/*import javax.swing.table.TableModel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;*/

import javax.swing.JFormattedTextField;

import java.text.Format;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


public class ifTabRecapEntree extends JInternalFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	@SuppressWarnings("rawtypes")
	private JComboBox comboAn;
	private JCheckBox chckbxRecette;
	private JCheckBox chckbxDepenses;
	
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Statement st = null;
	private JTable table;
	private JFormattedTextField formattedTextField;
	/**
	 * Launch the application.
	 */
	private JButton btnRechercher;
	private JCheckBox chckbxTotalit;
	private JCheckBox chckbxImpaye;
	private JComboBox comboClasse;
	private JCheckBox checkBxTeeS;
	private JComboBox comboClasse_LTC;
	
	/**
	 * Create the frame.
	 */
	public ifTabRecapEntree() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				chckbxTotalit.setEnabled(true);
			}
		});
		setClosable(true);
		setTitle("Recaputilatif ");
		setBounds(300, 128, 982, 551);
		setFrameIcon(new ImageIcon(".\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		setVisible(true);
		cnx = ConnexionMySql.ConnexiomBd();
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(0, 0, 0));
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 255)), "LISTE DES VERSEMENTS RECUS", TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 132, 952, 378);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 29, 802, 337);
		panel.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		
		JButton btnImp = new JButton("Imprimer :");
		btnImp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnexionMySql p = new ConnexionMySql();
				ConnexionMySql.ConnexiomBd();
				if(chckbxRecette.isSelected()){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
					if(chckbxTotalit.isSelected() ){
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais Inscription";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionEgal1.jrxml");
								HashMap<String, Object> vNomEleve = new HashMap<String, Object>();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , ConnexionMySql.ConnexiomBd());
								/**/
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else if(chckbxImpaye.isSelected() ){
						
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais Inscription";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionEgalDif1.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else if(chckbxRecette.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais Inscription";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionClasseT.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else{
						if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						String nomDep = "Frais Inscription";
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionClasse.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
							vNomEleve.put("pLibelle",nomDep);
							vNomEleve.put("pClasse",comboClasse.getSelectedItem().toString());
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);	
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						}
					}
					
				}
				
				
				//////////////////////////////////////////////////////////
				if(chckbxDepenses.isSelected() ){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
					if(chckbxTotalit.isSelected() ){
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais des Tenues";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionEgalTenu.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								//vNomEleve.put("pClasse",comboClasse.getSelectedItem().toString());
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else if(chckbxImpaye.isSelected()){
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais des Tenues";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionEgalTenuDif.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else if(chckbxDepenses.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais des Tenues";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionClasseT.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else{
						if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						String nomDep = "Frais des Tenues";
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionClasse.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
							vNomEleve.put("pLibelle",nomDep);
							vNomEleve.put("pClasse",comboClasse.getSelectedItem().toString());
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);	
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						}
					
					
					
					
					}
					
				}
				
				
				/////////////////////////////////////////////////////////////////
				
				if(checkBxTeeS.isSelected()){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
					if(chckbxTotalit.isSelected() ){
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais Tee_Shirt";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionEgal1.jrxml");
								HashMap<String, Object> vNomEleve = new HashMap<String, Object>();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , ConnexionMySql.ConnexiomBd());
								/**/
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else if(chckbxImpaye.isSelected() ){
						
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais Tee_Shirt";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionEgalDif1.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else if(checkBxTeeS.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
						if(imp == 0){
							//JasperDesign jd;
							//JasperReport jf;
							String nomDep = "Frais Tee_Shirt";
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionClasseT.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
								vNomEleve.put("pLibelle",nomDep);
								
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							}
					}else{
						if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						String nomDep = "Frais Tee_Shirt";
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscriptionClasse.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("anSco",comboAn.getSelectedItem().toString());
							vNomEleve.put("pLibelle",nomDep);
							vNomEleve.put("pClasse",comboClasse.getSelectedItem().toString());
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);	
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						}
					}
					
				}
			
				
			}
		});
		btnImp.setBounds(822, 21, 118, 32);
		panel.add(btnImp);
		
		JButton btnExporter = new JButton("Exporter :");
		btnExporter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					JFileChooser chooser = new JFileChooser();
					chooser.setCurrentDirectory( new File("."+File.separator));
					
					
					int reponse = chooser.showDialog(chooser, "Enregistrer Sous");
					
					if(reponse == JFileChooser.APPROVE_OPTION){
						String fichier = chooser.getSelectedFile().toString();
						ExcelExporter exp = new ExcelExporter();
						exp.fillData(table, new File(fichier+".xls"));
						JOptionPane.showMessageDialog(null, "Data save at "+ " Successfull",
								"Message", JOptionPane.INFORMATION_MESSAGE);
						
					}
				
				
				
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				
			}
		});
		btnExporter.setBounds(822, 68, 118, 32);
		panel.add(btnExporter);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new LineBorder(Color.MAGENTA));
		panel_1.setBounds(10, 14, 286, 107);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		comboAn = new JComboBox();
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				if(chckbxRecette.isSelected() && !comboClasse.getSelectedItem().equals("Choix")){
					formattedTextField.setEnabled(true);
					chckbxTotalit.setEnabled(true);
				String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='"+chckbxRecette.getText().toString()+"' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
								+ "and `cls_nom` = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				
				}else 
					
				if(chckbxDepenses.isSelected() && !comboClasse.getSelectedItem().equals("Choix")){
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='"+chckbxDepenses.getText().toString()+"' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'   "
								+ "and `cls_nom` = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}
				else 
					
					if(checkBxTeeS.isSelected() && !comboClasse.getSelectedItem().equals("Choix")){
						
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='"+checkBxTeeS.getText().toString()+"' "
							+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'   "
									+ "and `cls_nom` = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv`";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						}
					}
				else 
					
				if(chckbxRecette.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
					formattedTextField.setEnabled(true);
					chckbxTotalit.setEnabled(true);
				String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='"+chckbxRecette.getText().toString()+"' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}else if(chckbxDepenses.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='"+chckbxDepenses.getText().toString()+"' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}else if(checkBxTeeS.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='"+checkBxTeeS.getText().toString()+"' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}
			}
		});
		comboAn.setBounds(95, 11, 118, 29);
		panel_1.add(comboAn);
		
		chckbxRecette = new JCheckBox("Frais Inscription");
		chckbxRecette.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chckbxRecette.isSelected() && !chckbxImpaye.isSelected() && !chckbxTotalit.isSelected() 
						&& comboClasse.getSelectedItem().toString().equals("Choix")){
					chckbxDepenses.setEnabled(false);
					
					////////////////////////////////////////////////////////////////////////////
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,Min(Date) as Date_Dernier_Vers,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
				}else if(chckbxRecette.isSelected() && !chckbxImpaye.isSelected() && !chckbxTotalit.isSelected() 
						&& !comboClasse.getSelectedItem().toString().equals("Choix")){
					chckbxDepenses.setEnabled(false);
					
					////////////////////////////////////////////////////////////////////////////
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,Min(Date) as Date_Dernier_Vers,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' and  `cls_nom`= '"+comboClasse.getSelectedItem().toString()+"'group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
				}else {
					
					chckbxDepenses.setEnabled(true);
					chckbxTotalit.setSelected(false);
					chckbxImpaye.setSelected(false);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='AUCUN' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					
				}
				if(chckbxRecette.isSelected() && chckbxTotalit.isSelected()){
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,Min(`reste_a_paye`) as Reste , "
							+ " `cls_nom`, Min(Date) as Date_Dernier_Vers,`Montant_cls_ins` as Montant_Scolairite ,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`)=`Montant_cls_ins` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
				}
				if(!chckbxRecette.isSelected()){
					chckbxDepenses.setEnabled(true);
				}
				
			}
		});
		/////////////////////////////////////////////////////////////////////////////////
		//////////////////////////////// 26022019 ///////////////////////////////////////
		/////////////////////////////////////////////////////////////////////////////////
		chckbxRecette.setBounds(10, 48, 135, 23);
		panel_1.add(chckbxRecette);
		
		chckbxDepenses = new JCheckBox("Frais des Tenues");
		chckbxDepenses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxDepenses.isSelected() && !chckbxTotalit.isSelected() && !chckbxImpaye.isSelected()){
					chckbxRecette.setEnabled(false);
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,Min(Date) as Date_Dernier_Vers,`cls_nom`,`anne_scolaire`, Date FROM `recettes` WHERE `Libelle`='Frais des Tenues' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					
				}else {
					//chckbxRecette.setEnabled(true);
					chckbxRecette.setEnabled(false);
					chckbxTotalit.setSelected(false);
					chckbxImpaye.setSelected(false);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,Min(Date) as Date_Dernier_Vers,`cls_nom`,`anne_scolaire`, Date FROM `recettes` WHERE `Libelle`='AUCUN' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					
				}
				
				if(chckbxDepenses.isSelected() && chckbxTotalit.isSelected()){
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_tenu` as Montant_Tenue, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`, Min(Date) as Date_Dernier_Vers,`anne_scolaire`, Date FROM `recettes` WHERE `Libelle`='Frais des Tenues' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`)=`Montant_cls_tenu` "
									+ "order by Date";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
				}
				if(!chckbxDepenses.isSelected()){
					chckbxRecette.setEnabled(true);
				}
			}
		});
		chckbxDepenses.setBounds(147, 48, 133, 23);
		panel_1.add(chckbxDepenses);
		
		JLabel lblNewLabel = new JLabel("Ann\u00E9e :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 82, 30);
		panel_1.add(lblNewLabel);
		
		checkBxTeeS = new JCheckBox("Frais Tee_Shirt");
		checkBxTeeS.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				if(checkBxTeeS.isSelected() && !chckbxImpaye.isSelected() && !chckbxTotalit.isSelected() 
						&& comboClasse.getSelectedItem().toString().equals("Choix")){
					chckbxDepenses.setEnabled(false);
					chckbxRecette.setEnabled(false);
					////////////////////////////////////////////////////////////////////////////
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,Min(Date) as Date_Dernier_Vers,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Tee_Shirt' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
				}else if(checkBxTeeS.isSelected() && !chckbxImpaye.isSelected() && !chckbxTotalit.isSelected() 
						&& !comboClasse.getSelectedItem().toString().equals("Choix")){
					chckbxDepenses.setEnabled(false);
					chckbxRecette.setEnabled(false);
					
					////////////////////////////////////////////////////////////////////////////
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,Min(Date) as Date_Dernier_Vers ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Tee_Shirt' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' and  `cls_nom`= '"+comboClasse.getSelectedItem().toString()+"'group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
				}else {
					
					chckbxDepenses.setEnabled(true);
					chckbxRecette.setEnabled(true);
					chckbxTotalit.setSelected(false);
					chckbxImpaye.setSelected(false);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,Min(Date) as Date_Dernier_Vers,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='AUCUN' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					
				}
				if(checkBxTeeS.isSelected() && chckbxTotalit.isSelected()){
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,Min(`reste_a_paye`) as Reste ,Min(Date) as Date_Dernier_Vers, "
							+ " `cls_nom`,`Montant_cls_ins` as Montant_Scolairite ,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Tee_Shirt' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`)=`Montant_cls_ins` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
				}
				if(!checkBxTeeS.isSelected()){
					chckbxDepenses.setEnabled(true);
					chckbxRecette.setEnabled(true);
				}
				
			}
		});
		checkBxTeeS.setBounds(10, 77, 135, 23);
		panel_1.add(checkBxTeeS);
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				comboClasse.setEnabled(true);
				 comboClasse_LTC.setEnabled(true);

			}
		});
		panel_2.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				 comboClasse.setEnabled(true);
				 comboClasse_LTC.setEnabled(true);
			}
		});
		panel_2.setBorder(new LineBorder(Color.RED));
		panel_2.setBounds(306, 14, 519, 107);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		chckbxTotalit = new JCheckBox("Totalit\u00E9 :");
		chckbxTotalit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxTotalit.isSelected()){
					formattedTextField.setEnabled(false);
					if(chckbxRecette.isSelected() && chckbxTotalit.isSelected()){
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_ins` as Scolarité, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
									+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`)=`Montant_cls_ins` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					}else if(chckbxDepenses.isSelected() && chckbxTotalit.isSelected()) {
						
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_tenu` as Montant_Tenue, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais des Tenues' "
								+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
										+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`)=`Montant_cls_tenu` ";
						try {
							
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException ex) {
							// TODO Auto-generated catch block 
							System.out.println("Erreur 1 "+ex);
						
							
							}
					}
					else if(checkBxTeeS.isSelected() && chckbxTotalit.isSelected()) {
						
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_Tee_Shirt` as Montant_Tee_Shirt, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Tee_Shirt' "
								+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
										+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`)=`Montant_Tee_Shirt` ";
						try {
							
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException ex) {
							// TODO Auto-generated catch block 
							System.out.println("Erreur 1 "+ex);
						
							
							}
					}
					
					chckbxImpaye.setSelected(false);
				}else{
					
					chckbxDepenses.setEnabled(true);
					chckbxRecette.setEnabled(true);
					chckbxImpaye.setEnabled(true);
					chckbxDepenses.setSelected(false);
					chckbxRecette.setSelected(false);
					chckbxImpaye.setSelected(false);
					//comboClasse.setSelectedItem("Choix");
					formattedTextField.setEnabled(true);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='AUCUN' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
									+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					comboClasse.setSelectedItem("Choix");
					
					
				}
				
			}
		});
		chckbxTotalit.setBounds(402, 19, 77, 23);
		panel_2.add(chckbxTotalit);
		
		JLabel lblNewLabel_1 = new JLabel("Entrez une Tranche de Montant :");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 16, 235, 27);
		panel_2.add(lblNewLabel_1);
		
		formattedTextField = new JFormattedTextField((Format) null);
		formattedTextField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				if(true){
					chckbxDepenses.setEnabled(false);
					
					////////////////////////////////////////////////////////////////////////////
					
				}else{
					chckbxTotalit.setEnabled(true);
					chckbxImpaye.setEnabled(true);
					
				}
				
			}
		});
		
		formattedTextField.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		formattedTextField.setHorizontalAlignment(SwingConstants.CENTER);
		formattedTextField.setBounds(255, 16, 141, 29);
		panel_2.add(formattedTextField);
		
		chckbxImpaye = new JCheckBox("Non Totalit\u00E9 :");
		chckbxImpaye.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxImpaye.isSelected()){
					formattedTextField.setEnabled(false);
					if(chckbxRecette.isSelected() && chckbxImpaye.isSelected()){
						chckbxTotalit.setSelected(false);
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_ins` as Scolarité, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'"
									+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`) != `Montant_cls_ins` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					}else if(chckbxDepenses.isSelected() && chckbxImpaye.isSelected()) {
						//chckbxTotalit.setEnabled(false);
						chckbxTotalit.setSelected(false);
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_tenu` as Montant_Tenue, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais des Tenues'  "
								+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
										+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`) != `Montant_cls_tenu` ";
						try {
							
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException ex) {
							// TODO Auto-generated catch block 
							System.out.println("Erreur 1 "+ex);
						
							
							}
					}else if(checkBxTeeS.isSelected() && chckbxImpaye.isSelected()) {
						//chckbxTotalit.setEnabled(false);
						chckbxTotalit.setSelected(false);
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_Tee_Shirt` as Montant_Tee_Shirt, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Tee_Shirt'  "
								+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
										+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`) != `Montant_Tee_Shirt` ";
						try {
							
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException ex) {
							// TODO Auto-generated catch block 
							System.out.println("Erreur 1 "+ex);
						
							
							}
					}
					
					else {
						chckbxDepenses.setSelected(false);;
						chckbxRecette.setSelected(false);
						checkBxTeeS.setSelected(false);
					}
					//chckbxTotalit.setSelected(false);
					
				}else if(chckbxDepenses.isSelected() && chckbxRecette.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
					
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='AUCUN' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
									+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					
				}else{
					//comboClasse.setSelectedItem("Choix");
					chckbxDepenses.setEnabled(true);
					chckbxRecette.setEnabled(true);
					chckbxTotalit.setEnabled(true);
					chckbxDepenses.setSelected(false);
					chckbxRecette.setSelected(false);
					chckbxTotalit.setSelected(false);
					formattedTextField.setEnabled(true);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='AUCUN' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
									+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1&&&& "+ex);
					
						
						}
					comboClasse.setSelectedItem("Choix");
					
					
				}
			}
		});
		chckbxImpaye.setBounds(402, 45, 111, 23);
		panel_2.add(chckbxImpaye);
		
		comboClasse = new JComboBox();
		comboClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 comboClasse_LTC.setEnabled(false);
				if(chckbxRecette.isSelected() && !comboClasse.getSelectedItem().equals("Choix")){
					formattedTextField.setEnabled(true);
					//chckbxTotalit.setEnabled(true);
					if(chckbxTotalit.isSelected()){
						chckbxImpaye.setEnabled(false);
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_ins` as Scolarité, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
								+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
										+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`) =`Montant_cls_ins` ";
						try {
							
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException ex) {
							// TODO Auto-generated catch block 
							System.out.println("Erreur 1 "+ex);
						
							
							}
						
						
					}else if(chckbxImpaye.isSelected()){
						chckbxTotalit.setEnabled(false);
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_ins` as Scolarité, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'"
									+ " and cls_nom = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`) != `Montant_cls_ins` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					}else{
						
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
									+ "and `cls_nom` = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv`";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						}
						
						
					}
				
				
				
				}else if(chckbxDepenses.isSelected() && !comboClasse.getSelectedItem().equals("Choix")){
					chckbxTotalit.setEnabled(true);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais des Tenues' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'   "
								+ "and `cls_nom` = '"+comboClasse.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}else if(chckbxRecette.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
					formattedTextField.setEnabled(true);
					chckbxTotalit.setEnabled(true);
				String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}else if(chckbxDepenses.isSelected() && comboClasse.getSelectedItem().equals("Choix")){
					chckbxTotalit.setEnabled(true);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais des Tenues' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}
				
			
				
			}
		});
		comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboClasse.setBounds(127, 52, 128, 23);
		panel_2.add(comboClasse);
		
		JLabel lblChoixClasse = new JLabel("Choix Classe :");
		lblChoixClasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixClasse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblChoixClasse.setBounds(10, 73, 117, 23);
		panel_2.add(lblChoixClasse);
		
		comboClasse_LTC = new JComboBox();
		comboClasse_LTC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				/*////////////////////////////////////////////
							DEBUT CODE LTC
				////////////////////////////////////////////
				 * */
				 comboClasse.setEnabled(false);
				if(chckbxRecette.isSelected() && !comboClasse_LTC.getSelectedItem().equals("Choix")){
					formattedTextField.setEnabled(true);
					//chckbxTotalit.setEnabled(true);
					if(chckbxTotalit.isSelected()){
						chckbxImpaye.setEnabled(false);
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_ins` as Scolarité, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
								+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
										+ " and cls_nom = '"+comboClasse_LTC.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`) =`Montant_cls_ins` ";
						try {
							
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException ex) {
							// TODO Auto-generated catch block 
							System.out.println("Erreur 1 "+ex);
						
							
							}
						
						
					}else if(chckbxImpaye.isSelected()){
						chckbxTotalit.setEnabled(false);
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé,`Montant_cls_ins` as Scolarité, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'"
									+ " and cls_nom = '"+comboClasse_LTC.getSelectedItem().toString()+"' group by `id_elv` having sum(`Montant`) != `Montant_cls_ins` ";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					}else{
						
						String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
								+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
							+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
									+ "and `cls_nom` = '"+comboClasse_LTC.getSelectedItem().toString()+"' group by `id_elv`";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						}
						
						
					}
				
				
				
				}else if(chckbxDepenses.isSelected() && !comboClasse.getSelectedItem().equals("Choix")){
					chckbxTotalit.setEnabled(true);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais des Tenues' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'   "
								+ "and `cls_nom` = '"+comboClasse_LTC.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}else if(chckbxRecette.isSelected() && comboClasse_LTC.getSelectedItem().equals("Choix")){
					formattedTextField.setEnabled(true);
					chckbxTotalit.setEnabled(true);
				String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}else if(chckbxDepenses.isSelected() && comboClasse_LTC.getSelectedItem().equals("Choix")){
					chckbxTotalit.setEnabled(true);
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais des Tenues' "
						+ "and anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv`";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				}
				
				
				/*////////////////////////////////////////////
							FIN DE CODE LTC
				////////////////////////////////////////////
				 * */
			}
		});
		comboClasse_LTC.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboClasse_LTC.setBounds(265, 52, 128, 23);
		panel_2.add(comboClasse_LTC);
		
		btnRechercher = new JButton("Rechercher :");
		btnRechercher.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxRecette.isSelected()){
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
						+ "Min(`reste_a_paye`) as Reste ,`Montant_cls_ins` as Scolarite,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais Inscription' "
						+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` having Montant_Versé <= '"+formattedTextField.getText()+"'";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					
					}
				}else if(chckbxDepenses.isSelected()){
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
							+ "Min(`reste_a_paye`) as Reste ,`Montant_cls_tenu` as Montant_Tenue ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='Frais des Tenues' "
							+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` having Montant_Versé <= '"+formattedTextField.getText()+"'";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
				}else {
					String sql = "select distinct `id_elv`, `Nom_Elv`, sum(`Montant`) as Montant_Versé, "
						+ "Min(`reste_a_paye`) as Reste ,`cls_nom`,`anne_scolaire` FROM `recettes` WHERE `Libelle`='AUCUN' "
						+ " and  anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' group by `id_elv` having Montant_Versé <= '"+formattedTextField.getText()+"'";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					
					}
				}
				
			}
		});
		btnRechercher.setBounds(835, 46, 114, 32);
		getContentPane().add(btnRechercher);
		BindComboan();
		BindCombo();
		BindCombolLTC();
	}
	
	
	private String getCellValue(int x, int y){
		
		return table.getValueAt(x, y).toString();
	}
	
	public void BindComboan(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			resultat = st.executeQuery("SELECT anne_courant FROM annee ");
		    while(resultat.next()){
		    	comboAn.addItem(resultat.getString(1));
		    
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void BindCombo(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {

			st = con.createStatement();
			resultat = st.executeQuery("SELECT cls_nom FROM classe ");
			while(resultat.next()){
				comboClasse.addItem(resultat.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void BindCombolLTC(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {

			st = con.createStatement();
			resultat = st.executeQuery("SELECT cls_nom FROM classeltc ");
			while(resultat.next()){
				comboClasse_LTC.addItem(resultat.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
