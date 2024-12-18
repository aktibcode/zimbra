package metier;

import java.awt.EventQueue;
import java.io.File;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import connec.ConnexionMySql;
import connec.Donne;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import net.proteanit.sql.DbUtils;
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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ifRecette extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	private JTextField textFieldVerse;
	private JTextField textFieldReste;
	private JTextField textFieldMontant;
	private JComboBox comboAn;
	private JComboBox comboLib;
	private JComboBox comboNomEl;
	private JComboBox comboClasse;
	private JComboBox comboSco;
	private JComboBox comboTenu;
	private JTextField textFieldDate;
	private JButton btnEnrg;
	private JButton btnRimprimer;
	private JTextField textField;
	private JTextField textFieldId;
	private JTextField idClasse;
	private JComboBox comboTeeS;
	
	public ifRecette() {
		setIconifiable(true);
		setClosable(true);
		
		setTitle("Gestion des Recettes ");
		setBounds(300, 128, 999, 480);
		setFrameIcon(new ImageIcon(".\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		setVisible(true);
		cnx = ConnexionMySql.ConnexiomBd();
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255), 2), "Informations sur l'Entr\u00E9e", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 963, 310);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choix Libelle :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(17, 139, 118, 31);
		panel.add(lblNewLabel);
		
		JLabel lblChoixNomEleve = new JLabel("Choix Nom Eleve :");
		lblChoixNomEleve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblChoixNomEleve.setBounds(17, 193, 119, 31);
		panel.add(lblChoixNomEleve);
		
		JLabel lblChoixClasse = new JLabel("Choix Classe :");
		lblChoixClasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixClasse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblChoixClasse.setBounds(17, 87, 118, 31);
		panel.add(lblChoixClasse);
		
		comboLib = new JComboBox();
		comboLib.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboLib.getSelectedItem().equals("Autres") ){
					comboSco.setEnabled(false);
					comboTenu.setEnabled(false);
					comboNomEl.setEnabled(false);
					btnEnrg.setEnabled(true);
					comboTeeS.setEnabled(false);
					btnRimprimer.setEnabled(false);
					textFieldReste.setText("");
					textFieldVerse.setText("");
					//comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Aucune"}));
					//comboClasse.setSelectedItem("Aucune");
					textField.setEnabled(true);
				}else if(comboLib.getSelectedItem().equals("Frais des Tenues")){
					comboSco.setEnabled(false);
					comboTenu.setEnabled(true);
					comboNomEl.setEnabled(true);
					comboTeeS.setEnabled(false);
					textField.setEnabled(false);
				}else if(comboLib.getSelectedItem().equals("Frais Inscription")){
					comboTenu.setEnabled(false);
					comboSco.setEnabled(true);
					comboNomEl.setEnabled(true);
					comboTeeS.setEnabled(false);
					textField.setEnabled(false);
				}else if(comboLib.getSelectedItem().equals("Frais Tee_Shirt")){
					comboTenu.setEnabled(false);
					comboSco.setEnabled(false);
					comboTeeS.setEnabled(true);
					comboNomEl.setEnabled(true);
					textField.setEnabled(false);
				}
				else if(comboLib.getSelectedItem().equals("Choix") ){
					comboSco.setEnabled(false);
					comboTenu.setEnabled(false);
					comboNomEl.setEnabled(false);
					textField.setEnabled(false);
				}
				
				remplirTableauANC();
			}
		});
		comboLib.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboLib.setBounds(134, 140, 344, 31);
		panel.add(comboLib);
		
		comboNomEl = new JComboBox();
		comboNomEl.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(comboLib.getSelectedItem().toString().equals("Autres")){
					textFieldReste.setText("");
				}else{
				//remplirTableauANC();
					}
				Donne mk = new Donne();
				ArrayList<eleveEL> list2 = mk.getData3(comboNomEl.getSelectedItem().toString());
				for(int j = 0; j < list2.size(); j++ ){
					textFieldId.setText(String.valueOf(list2.get(j).getElv_id()));
					System.out.println("Requete executer "+String.valueOf(list2.get(j).getElv_id()));
					System.out.println("-----------------------------");
					System.out.println(textFieldId.getText());
					System.out.println("-----------------------------");
					remplirTableauANC();
				}
				
			}
		});
		comboNomEl.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboNomEl.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboNomEl.setBounds(134, 193, 344, 31);
		panel.add(comboNomEl);
		
		comboClasse = new JComboBox();
		comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix", "Aucune"}));
		comboClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comboNomEl.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
				//comboSco.setModel(new DefaultComboBoxModel(new String[] {"0.0"}));
				//comboTenu.setModel(new DefaultComboBoxModel(new String[] {"0.0"}));
				comboLib.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
				if(!comboClasse.getSelectedItem().equals("Choix")){
					comboNomEl.setEnabled(true);
				}/*if(comboClasse.getSelectedItem().equals("Aucune")){
					comboLib.setModel(new DefaultComboBoxModel(new String[] {"Autres"}));
				}*/
				
				if(comboNomEl.getSelectedItem().equals(false) && comboLib.getSelectedItem().equals("Choix")){
					System.out.println("Aucune");
					comboNomEl.removeAllItems();
					comboSco.removeAllItems();
					comboTenu.removeAllItems();
					comboTeeS.removeAllItems();
				}else{
					MethodeRecetteDepense mk = new MethodeRecetteDepense();
					ArrayList<eleveEL> list = mk.getData2(comboClasse.getSelectedItem().toString(), comboAn.getSelectedItem().toString());
					ArrayList<inscriptionEL> list2 = mk.getData4(comboClasse.getSelectedItem().toString());
					
					
					
					for(int i = 0; i < list.size(); i++ ){
						comboNomEl.addItem(list.get(i).getNomEtprenom());
						

					}
					for(int j = 0; j < list2.size(); j++){
						comboSco.addItem(list2.get(j).getMontant());
						
						String com = comboSco.getSelectedObjects().toString();
						//idClasse.setText(String.valueOf(list2.get(j).getMontant()));
						//comboSco.setSelectedItem(l);
						comboSco.setSelectedItem(list2.get(j).getMontant());
						comboSco.setSelectedItem(com);
					}
					for(int k = 0; k < list2.size(); k++){
						comboTenu.addItem(list2.get(k).getMontant_Tenue());
						
						comboTenu.setSelectedItem(list2.get(k).getMontant_Tenue());
						String com = comboTenu.getSelectedObjects().toString();
						comboTenu.setSelectedItem(com);
					}
					
					for(int l = 0; l < list2.size(); l++){
						comboTeeS.addItem(list2.get(l).getMontant_TeeShirt());
						
						comboTeeS.setSelectedItem(list2.get(l).getMontant_TeeShirt());
						String com = comboTeeS.getSelectedObjects().toString();
						comboTeeS.setSelectedItem(com);
					}

					RubEntree();
				}
				
				
				remplirTableauANC();
				
				
			}
		});
		comboClasse.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboClasse.setBounds(134, 88, 112, 31);
		panel.add(comboClasse);
		
		JLabel lblMontantScolarit = new JLabel("Montant Scolarit\u00E9 :");
		lblMontantScolarit.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMontantScolarit.setBounds(504, 87, 147, 31);
		panel.add(lblMontantScolarit);
		
		comboSco = new JComboBox();
		comboSco.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboSco.setBounds(649, 87, 262, 31);
		panel.add(comboSco);
		
		JLabel lblMontantTenue = new JLabel("Montant Tenue :");
		lblMontantTenue.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantTenue.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMontantTenue.setBounds(504, 141, 147, 31);
		panel.add(lblMontantTenue);
		
		comboTenu = new JComboBox();
		comboTenu.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboTenu.setBounds(649, 141, 262, 31);
		panel.add(comboTenu);
		
		JLabel lblChoixAnne = new JLabel("Choix Ann\u00E9e :");
		lblChoixAnne.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixAnne.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblChoixAnne.setBounds(17, 33, 118, 31);
		panel.add(lblChoixAnne);
		
		
		
		JLabel lblSommeVers = new JLabel("Somme Vers\u00E9 :");
		lblSommeVers.setHorizontalAlignment(SwingConstants.CENTER);
		lblSommeVers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblSommeVers.setBounds(519, 247, 132, 31);
		panel.add(lblSommeVers);
		
		textFieldMontant = new JTextField();
		textFieldMontant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(textFieldReste.getText().toString().equals("")){
					btnEnrg.setEnabled(true);
					btnRimprimer.setEnabled(false);
				}else if(textFieldReste.getText().toString().equals("0") && comboLib.getSelectedItem().toString().equals("Autres")){
					btnEnrg.setEnabled(false);
					btnRimprimer.setEnabled(true);
				}else if(!textFieldReste.getText().toString().equals("")){
					btnEnrg.setEnabled(true);
					btnRimprimer.setEnabled(true);
				}else if(comboLib.getSelectedItem().toString().equals("Autres")){
					btnEnrg.setEnabled(true);
					btnRimprimer.setEnabled(false);
				}
			}
		});
		
		textFieldMontant.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMontant.setColumns(10);
		textFieldMontant.setBounds(651, 248, 260, 31);
		panel.add(textFieldMontant);
		
		JLabel lblDateVersement = new JLabel("Date Versement :");
		lblDateVersement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateVersement.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDateVersement.setBounds(519, 33, 132, 31);
		panel.add(lblDateVersement);
		
		textFieldDate = new JTextField();
		textFieldDate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textFieldDate.setEditable(false);
		textFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
		//textFieldDate.setText(String.format("%tT", new Date(%dd, %mm, %yyyy)));//String.format("%tT", new Date());
		textFieldDate.setBounds(649, 34, 262, 31);
		panel.add(textFieldDate);
		Date d = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		String dt = ft.format(d);
		textFieldDate.setText(dt);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 255), 2), "Informations sur les Versements ant\u00E9rieurs", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 332, 686, 107);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMontantVers = new JLabel("Montant Vers\u00E9 :");
		lblMontantVers.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantVers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMontantVers.setBounds(10, 49, 121, 31);
		panel_1.add(lblMontantVers);
		
		textFieldVerse = new JTextField();
		textFieldVerse.setEditable(false);
		textFieldVerse.setForeground(new Color(0, 0, 255));
		textFieldVerse.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		textFieldVerse.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldVerse.setBounds(132, 41, 194, 44);
		panel_1.add(textFieldVerse);
		textFieldVerse.setColumns(10);
		
		JLabel lblRestePayer = new JLabel("Reste \u00E0 Payer :");
		lblRestePayer.setHorizontalAlignment(SwingConstants.CENTER);
		lblRestePayer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblRestePayer.setBounds(349, 49, 121, 31);
		panel_1.add(lblRestePayer);
		
		textFieldReste = new JTextField();
		textFieldReste.setEditable(false);
		textFieldReste.setForeground(Color.DARK_GRAY);
		textFieldReste.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		textFieldReste.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldReste.setColumns(10);
		textFieldReste.setBounds(471, 41, 205, 44);
		panel_1.add(textFieldReste);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(703, 337, 270, 102);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		btnEnrg = new JButton("Enregistrer :");
		btnEnrg.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(checkInput()){
					
					
					try {
						ConnexionMySql p = new ConnexionMySql();
						p.ConnexiomBd();
					
					Double t = Double.parseDouble(textFieldMontant.getText().toString());
					if(t>0){
						
						
						
						if(textFieldReste.getText().toString().equals("") && textFieldVerse.getText().toString().equals("")){
							
							
							/////////////////////////////////////////////////////////////////////////////
							/////////////////    1ERE INSTRUCTION INTERNE          /////////////////
							/////////////////////////////////////////////////////////////////////////////
							if( comboLib.getSelectedItem().equals("Frais Inscription") 
									&& !comboNomEl.getSelectedItem().equals("") && !comboClasse.getSelectedItem().equals("Aucune")
									){//|| !comboSco.getSelectedItem().equals("0.0")
								
								Double M_S = Double.parseDouble(comboSco.getSelectedItem().toString());
								Double R = M_S - t ;
											if(R >= 0){
												String txt = "Aucune";
											PreparedStatement ps;
											try {
												/*ps = cnx.prepareStatement("INSERT into recettes (id_elv, anne_scolaire, Libelle, Nom_Elv,"
														+ "cls_nom, Montant, reste_a_paye, Montant_cls_ins, Montant_cls_tenu, Date, autres_libelles) "
														+ "values(?,?,?,?,?,?,?,?,?,?,?)");*/
												
												ps = cnx.prepareStatement("INSERT into recettes (id_elv, anne_scolaire, Libelle, Nom_Elv,"
														+ "cls_nom, Montant, reste_a_paye, Montant_cls_ins, Montant_cls_tenu, Montant_Tee_Shirt, Date, autres_libelles) "
														+ "values(?,?,?,?,?,?,?,?,?,?,?,?)");
												
												ps.setInt(1, Integer.parseInt(textFieldId.getText().toString()));
												ps.setString(2, comboAn.getSelectedItem().toString());
												ps.setString(3, comboLib.getSelectedItem().toString());
												ps.setString(4, comboNomEl.getSelectedItem().toString());
												ps.setString(5, comboClasse.getSelectedItem().toString());
												ps.setDouble(6, t);
												ps.setDouble(7, R);
												ps.setDouble(8, Double.parseDouble(comboSco.getSelectedItem().toString()));
												ps.setDouble(9, Double.parseDouble(comboTenu.getSelectedItem().toString()));
												ps.setDouble(10, Double.parseDouble(comboTeeS.getSelectedItem().toString()));
												ps.setString(11, textFieldDate.getText().toString());
												ps.setString(12, txt);
												ps.executeUpdate();
												JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
												
												int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
												if(imp == 0){
													//JasperDesign jd;
													//JasperReport jf;
													try {
														JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiement5.jrxml");
														HashMap vNomEleve = new HashMap();
														
														vNomEleve.put("PidEleve", Integer.parseInt(textFieldId.getText().toString()));
														vNomEleve.put("anSco", comboAn.getSelectedItem().toString());
														vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText().toString()));
														vNomEleve.put("nomElv", comboNomEl.getSelectedItem().toString());
														vNomEleve.put("libelle", comboLib.getSelectedItem().toString());
														vNomEleve.put("montantScola", Double.parseDouble(comboSco.getSelectedItem().toString()));
														JasperReport jr = JasperCompileManager.compileReport(jd);
														JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
														JasperViewer jv = new JasperViewer(jp, false);
														jv.setVisible(true);	
													} catch (JRException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													
													}
												
												//&& !textFieldSum.getText().toString().equals("0.0")
												} catch (SQLException e) {
													JOptionPane.showMessageDialog(null, "Certaines informations manquent. Verifier et continuer !!!");
											}
									init();
											}else{
												if(R < 0 && comboSco.getSelectedItem().toString().equals("0.0")){
												System.out.println("Negative 001");
												JOptionPane.showMessageDialog(null, "Choississez la valeur de la Scolarité N° 1 Scolarite ");
												}else if(R < 0 || !comboSco.getSelectedItem().toString().equals("0.0")){
													JOptionPane.showMessageDialog(null, "Cette valeur est superieur au frais de la Scolarité N° 1 Scolarite");
												}
											}
											
											
							}else 
							/////////////////////////////////////////////////////////////////////////////
							/////////////////    FIN 1ERE INSTRUCTION INTERNE          /////////////////
							/////////////////////////////////////////////////////////////////////////////

							
							/////////////////////////////////////////////////////////////////////////////
							/////////////////    2EME INSTRUCTION INTERNE          /////////////////
							/////////////////////////////////////////////////////////////////////////////
							if(comboLib.getSelectedItem().equals("Frais des Tenues") && !comboNomEl.getSelectedItem().equals("")
									 && !comboClasse.getSelectedItem().equals("Aucune") 
									 ){//|| !comboTenu.getSelectedItem().equals("0.0") 
								Double M_S2 = Double.parseDouble(comboTenu.getSelectedItem().toString());
								Double R = M_S2 - t ;
								remplirTableauANC();
										if(R >= 0){
											String txt = "Aucune";
											PreparedStatement ps;
											try {
												ps = cnx.prepareStatement("INSERT into recettes (id_elv, anne_scolaire, Libelle, Nom_Elv,"
														+ "cls_nom, Montant, reste_a_paye, Montant_cls_ins, Montant_cls_tenu, Montant_Tee_Shirt, Date, autres_libelles) "
														+ "values(?,?,?,?,?,?,?,?,?,?,?,?)");
												
												ps.setInt(1, Integer.parseInt(textFieldId.getText().toString()));
												ps.setString(2, comboAn.getSelectedItem().toString());
												ps.setString(3, comboLib.getSelectedItem().toString());
												ps.setString(4, comboNomEl.getSelectedItem().toString());
												ps.setString(5, comboClasse.getSelectedItem().toString());
												ps.setDouble(6, t);
												ps.setDouble(7, R);
												ps.setDouble(8, Double.parseDouble(comboSco.getSelectedItem().toString()));
												ps.setDouble(9, Double.parseDouble(comboTenu.getSelectedItem().toString()));
												ps.setDouble(10, Double.parseDouble(comboTeeS.getSelectedItem().toString()));
												ps.setString(11, textFieldDate.getText().toString());
												ps.setString(12, txt);
												ps.executeUpdate();
												JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
												
												int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
												if(imp == 0){
													//JasperDesign jd;
													//JasperReport jf;
													try {
														JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiement5.jrxml");
														HashMap vNomEleve = new HashMap();
														
														vNomEleve.put("PidEleve", Integer.parseInt(textFieldId.getText().toString()));
														vNomEleve.put("anSco", comboAn.getSelectedItem().toString());
														vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText().toString()));
														vNomEleve.put("nomElv", comboNomEl.getSelectedItem().toString());
														vNomEleve.put("libelle", comboLib.getSelectedItem().toString());
														vNomEleve.put("montantScola", Double.parseDouble(comboTenu.getSelectedItem().toString()));
														JasperReport jr = JasperCompileManager.compileReport(jd);
														JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
														JasperViewer jv = new JasperViewer(jp, false);
														jv.setVisible(true);	
													} catch (JRException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													
													}
												//&& !textFieldSum.getText().toString().equals("0.0")
												} catch (SQLException e) {
												JOptionPane.showMessageDialog(null, "Certaines informations manquent. Verifier et continuer !!!");
											}
											
											
											init();
											
										}else{
											if(R < 0 && comboTenu.getSelectedItem().toString().equals("0.0")){
											System.out.println("Negative 001");
											JOptionPane.showMessageDialog(null, "Choississez la valeur de la Tenue N° 1 Tenue");
											}else if(R < 0 || !comboTenu.getSelectedItem().toString().equals("0.0")){
												JOptionPane.showMessageDialog(null, "Cette valeur est superieur au frais de la Tenue N° 1 Tenue");
											}
										}
										
										
							}
							
							
							
							if( comboLib.getSelectedItem().equals("Frais Tee_Shirt") 
									&& !comboNomEl.getSelectedItem().equals("") && !comboClasse.getSelectedItem().equals("Aucune")
									){//|| !comboSco.getSelectedItem().equals("0.0")
								
								Double M_S = Double.parseDouble(comboTeeS.getSelectedItem().toString());
								Double R = M_S - t ;
											if(R >= 0){
												String txt = "Aucune";
											PreparedStatement ps;
											try {
												ps = cnx.prepareStatement("INSERT into recettes (id_elv, anne_scolaire, Libelle, Nom_Elv,"
														+ "cls_nom, Montant, reste_a_paye, Montant_cls_ins, Montant_cls_tenu, Montant_Tee_Shirt, Date, autres_libelles) "
														+ "values(?,?,?,?,?,?,?,?,?,?,?,?)");
												
												ps.setInt(1, Integer.parseInt(textFieldId.getText().toString()));
												ps.setString(2, comboAn.getSelectedItem().toString());
												ps.setString(3, comboLib.getSelectedItem().toString());
												ps.setString(4, comboNomEl.getSelectedItem().toString());
												ps.setString(5, comboClasse.getSelectedItem().toString());
												ps.setDouble(6, t);
												ps.setDouble(7, R);
												ps.setDouble(8, Double.parseDouble(comboSco.getSelectedItem().toString()));
												ps.setDouble(9, Double.parseDouble(comboTenu.getSelectedItem().toString()));
												ps.setDouble(10, Double.parseDouble(comboTeeS.getSelectedItem().toString()));
												ps.setString(11, textFieldDate.getText().toString());
												ps.setString(12, txt);
												ps.executeUpdate();
												JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
												
												int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
												if(imp == 0){
													//JasperDesign jd;
													//JasperReport jf;
													try {
														JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiement5.jrxml");
														HashMap vNomEleve = new HashMap();
														
														vNomEleve.put("PidEleve", Integer.parseInt(textFieldId.getText().toString()));
														vNomEleve.put("anSco", comboAn.getSelectedItem().toString());
														vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText().toString()));
														vNomEleve.put("nomElv", comboNomEl.getSelectedItem().toString());
														vNomEleve.put("libelle", comboLib.getSelectedItem().toString());
														vNomEleve.put("montantScola", Double.parseDouble(comboSco.getSelectedItem().toString()));
														JasperReport jr = JasperCompileManager.compileReport(jd);
														JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
														JasperViewer jv = new JasperViewer(jp, false);
														jv.setVisible(true);	
													} catch (JRException e) {
														// TODO Auto-generated catch block
														e.printStackTrace();
													}
													
													}
												
												//&& !textFieldSum.getText().toString().equals("0.0")
												} catch (SQLException e) {
													JOptionPane.showMessageDialog(null, "Certaines informations manquent. Verifier et continuer !!!");
											}
									init();
											}else{
												if(R < 0 && comboSco.getSelectedItem().toString().equals("0.0")){
												System.out.println("Negative 001");
												JOptionPane.showMessageDialog(null, "Choississez la valeur de la Scolarité N° 1 Scolarite ");
												}else if(R < 0 || !comboSco.getSelectedItem().toString().equals("0.0")){
													JOptionPane.showMessageDialog(null, "Cette valeur est superieur au frais de la Scolarité N° 1 Scolarite");
												}
											}
											
											
							}
							/////////////////////////////////////////////////////////////////////////////
							/////////////////    FIN 2EME INSTRUCTION INTERNE          /////////////////
							/////////////////////////////////////////////////////////////////////////////
						
							
							remplirTableauANC();	
						}else 
						if(!textFieldReste.getText().toString().equals("") && !textFieldVerse.getText().toString().equals("")){
							
							
							
							/////////////////////////////////////////////////////////////////////////////
							/////////////////    1ERE INSTRUCTION INTERNE AVEC RESTE    /////////////////
							/////////////////////////////////////////////////////////////////////////////
														
							if(comboLib.getSelectedItem().equals("Frais Inscription") && !textFieldReste.getText().toString().equals("")
									&& !comboNomEl.getSelectedItem().equals("")
									 && !comboClasse.getSelectedItem().equals("Aucune")
									){//|| !comboSco.getSelectedItem().toString().equals("0.0")
								Double M_S = Double.parseDouble(textFieldReste.getText().toString());
								Double R = M_S - t ;
										if(R>=0){
											String txt = "Aucune";
											PreparedStatement ps;
										try {
											ps = cnx.prepareStatement("INSERT into recettes (id_elv, anne_scolaire, Libelle, Nom_Elv,"
													+ "cls_nom, Montant, reste_a_paye, Montant_cls_ins, Montant_cls_tenu, Montant_Tee_Shirt, Date, autres_libelles) "
													+ "values(?,?,?,?,?,?,?,?,?,?,?,?)");
											
											ps.setInt(1, Integer.parseInt(textFieldId.getText().toString()));
											ps.setString(2, comboAn.getSelectedItem().toString());
											ps.setString(3, comboLib.getSelectedItem().toString());
											ps.setString(4, comboNomEl.getSelectedItem().toString());
											ps.setString(5, comboClasse.getSelectedItem().toString());
											ps.setDouble(6, t);
											ps.setDouble(7, R);
											ps.setDouble(8, Double.parseDouble(comboSco.getSelectedItem().toString()));
											ps.setDouble(9, Double.parseDouble(comboTenu.getSelectedItem().toString()));
											ps.setDouble(10, Double.parseDouble(comboTeeS.getSelectedItem().toString()));
											ps.setString(11, textFieldDate.getText().toString());
											ps.setString(12, txt);
											ps.executeUpdate();
											JOptionPane.showMessageDialog(null, "Enregistrement reussit DU RESTE-1 ");
											//&& !textFieldSum.getText().toString().equals("0.0")
											} catch (SQLException e) {
												JOptionPane.showMessageDialog(null, "Certaines informations manquent. Verifier et continuer !!!");
												
										}
										int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
										if(imp == 0){
											//JasperDesign jd;
											//JasperReport jf;
											try {
												JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiement5.jrxml");
												HashMap vNomEleve = new HashMap();
												
												vNomEleve.put("PidEleve", Integer.parseInt(textFieldId.getText().toString()));
												vNomEleve.put("anSco", comboAn.getSelectedItem().toString());
												vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText().toString()));
												vNomEleve.put("nomElv", comboNomEl.getSelectedItem().toString());
												vNomEleve.put("libelle", comboLib.getSelectedItem().toString());
												vNomEleve.put("montantScola", Double.parseDouble(comboSco.getSelectedItem().toString()));
												JasperReport jr = JasperCompileManager.compileReport(jd);
												JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
												JasperViewer jv = new JasperViewer(jp, false);
												jv.setVisible(true);	
											} catch (JRException e) {
												// TODO Auto-generated catch block
												e.printStackTrace();
											}
											
											}
										init();
										}else{
											if(R < 0 && comboSco.getSelectedItem().toString().equals("0.0")  && !textFieldReste.getText().equals("0")){
											System.out.println("Negative 001"+textFieldReste.getText().toString());
											JOptionPane.showMessageDialog(null, "Choississez la valeur de la Scolarité N° 2 Scolarité");
											}else /*if(R < 0 || !comboSco.getSelectedItem().toString().equals("0.0")  && !textFieldReste.getText().equals("0")){
												JOptionPane.showMessageDialog(null, "Valeur superieur au Reste des frais de Scoalrité N° 2 Scolarité");
											}else*/ if(textFieldReste.getText().equals("0") || R == 0){
												JOptionPane.showMessageDialog(null, "Eleve ayant tout payer les Frais de Scolarité");
											}else{
												JOptionPane.showMessageDialog(null, "Vous avez entrez une valeur superieur au reste des Frais de Scolarité");
											}
											init();
										}
										
									
							}else 
								
							
							if(comboLib.getSelectedItem().equals("Frais des Tenues") && !textFieldReste.getText().toString().equals("")
									&& !comboNomEl.getSelectedItem().equals("")
									 && !comboClasse.getSelectedItem().equals("Aucune")
									){//|| !comboTenu.getSelectedItem().toString().equals("0.0")
								Double M_S2 = Double.parseDouble(textFieldReste.getText().toString());
								Double R = M_S2 - t ;
								if(R>=0){
									String txt = "Aucune";
									PreparedStatement ps;
									try {
										ps = cnx.prepareStatement("INSERT into recettes (id_elv, anne_scolaire, Libelle, Nom_Elv,"
												+ "cls_nom, Montant, reste_a_paye, Montant_cls_ins, Montant_cls_tenu, Montant_Tee_Shirt, Date, autres_libelles) "
												+ "values(?,?,?,?,?,?,?,?,?,?,?,?)");
										
										ps.setInt(1, Integer.parseInt(textFieldId.getText().toString()));
										ps.setString(2, comboAn.getSelectedItem().toString());
										ps.setString(3, comboLib.getSelectedItem().toString());
										ps.setString(4, comboNomEl.getSelectedItem().toString());
										ps.setString(5, comboClasse.getSelectedItem().toString());
										ps.setDouble(6, t);
										ps.setDouble(7, R);
										ps.setDouble(8, Double.parseDouble(comboSco.getSelectedItem().toString()));
										ps.setDouble(9, Double.parseDouble(comboTenu.getSelectedItem().toString()));
										ps.setDouble(10, Double.parseDouble(comboTeeS.getSelectedItem().toString()));
										ps.setString(11, textFieldDate.getText().toString());
										ps.setString(12, txt);
										ps.executeUpdate();
										JOptionPane.showMessageDialog(null, "Enregistrement reussit DU RESTE-2 ");
										//&& !textFieldSum.getText().toString().equals("0.0")
										} catch (SQLException e) {
											JOptionPane.showMessageDialog(null, "Certaines informations manquent. Verifier et continuer !!!");
											
									}
									int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
									if(imp == 0){
										//JasperDesign jd;
										//JasperReport jf;
										try {
											JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiement5.jrxml");
											HashMap vNomEleve = new HashMap();
											
											vNomEleve.put("PidEleve", Integer.parseInt(textFieldId.getText().toString()));
											vNomEleve.put("anSco", comboAn.getSelectedItem().toString());
											vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText().toString()));
											vNomEleve.put("nomElv", comboNomEl.getSelectedItem().toString());
											vNomEleve.put("libelle", comboLib.getSelectedItem().toString());
											vNomEleve.put("montantScola", Double.parseDouble(comboTenu.getSelectedItem().toString()));
											JasperReport jr = JasperCompileManager.compileReport(jd);
											JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
											JasperViewer jv = new JasperViewer(jp, false);
											jv.setVisible(true);	
										} catch (JRException e) {
											// TODO Auto-generated catch block
											e.printStackTrace();
										}
										
										}
									init();
								}else{
									if(R < 0 && comboTenu.getSelectedItem().toString().equals("0.0")){
									System.out.println("Negative 001");
									JOptionPane.showMessageDialog(null, "Choississez la valeur de la Tenue N° 2 Tenue");
									}else  if(textFieldReste.getText().equals("0") || R == 0){
										JOptionPane.showMessageDialog(null, "Eleve ayant tout payer les Frais des Tenues");
									}else{
										JOptionPane.showMessageDialog(null, "Vous avez entrez une valeur superieur au reste des Frais des Tenues");
									}
									init();
								}
								
							}
							
							else
								if(comboLib.getSelectedItem().equals("Frais Tee_Shirt") && !textFieldReste.getText().toString().equals("")
										&& !comboNomEl.getSelectedItem().equals("")
										 && !comboClasse.getSelectedItem().equals("Aucune")
										){//|| !comboSco.getSelectedItem().toString().equals("0.0")
									Double M_S = Double.parseDouble(textFieldReste.getText().toString());
									Double R = M_S - t ;
											if(R>=0){
												String txt = "Aucune";
												PreparedStatement ps;
											try {
												ps = cnx.prepareStatement("INSERT into recettes (id_elv, anne_scolaire, Libelle, Nom_Elv,"
														+ "cls_nom, Montant, reste_a_paye, Montant_cls_ins, Montant_cls_tenu, Montant_Tee_Shirt, Date, autres_libelles) "
														+ "values(?,?,?,?,?,?,?,?,?,?,?,?)");
												
												ps.setInt(1, Integer.parseInt(textFieldId.getText().toString()));
												ps.setString(2, comboAn.getSelectedItem().toString());
												ps.setString(3, comboLib.getSelectedItem().toString());
												ps.setString(4, comboNomEl.getSelectedItem().toString());
												ps.setString(5, comboClasse.getSelectedItem().toString());
												ps.setDouble(6, t);
												ps.setDouble(7, R);
												ps.setDouble(8, Double.parseDouble(comboSco.getSelectedItem().toString()));
												ps.setDouble(9, Double.parseDouble(comboTenu.getSelectedItem().toString()));
												ps.setDouble(10, Double.parseDouble(comboTeeS.getSelectedItem().toString()));
												ps.setString(11, textFieldDate.getText().toString());
												ps.setString(12, txt);
												ps.executeUpdate();
												JOptionPane.showMessageDialog(null, "Enregistrement reussit DU RESTE-1 ");
												//&& !textFieldSum.getText().toString().equals("0.0")
												} catch (SQLException e) {
													JOptionPane.showMessageDialog(null, "Certaines informations manquent. Verifier et continuer !!!");
													
											}
											int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
											if(imp == 0){
												//JasperDesign jd;
												//JasperReport jf;
												try {
													JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiement5.jrxml");
													HashMap vNomEleve = new HashMap();
													
													vNomEleve.put("PidEleve", Integer.parseInt(textFieldId.getText().toString()));
													vNomEleve.put("anSco", comboAn.getSelectedItem().toString());
													vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText().toString()));
													vNomEleve.put("nomElv", comboNomEl.getSelectedItem().toString());
													vNomEleve.put("libelle", comboLib.getSelectedItem().toString());
													vNomEleve.put("montantScola", Double.parseDouble(comboSco.getSelectedItem().toString()));
													JasperReport jr = JasperCompileManager.compileReport(jd);
													JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
													JasperViewer jv = new JasperViewer(jp, false);
													jv.setVisible(true);	
												} catch (JRException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
												}
												
												}
											init();
											}else{
												if(R < 0 && comboSco.getSelectedItem().toString().equals("0.0")  && !textFieldReste.getText().equals("0")){
												System.out.println("Negative 001"+textFieldReste.getText().toString());
												JOptionPane.showMessageDialog(null, "Choississez la valeur de la Scolarité N° 2 Scolarité");
												}else /*if(R < 0 || !comboSco.getSelectedItem().toString().equals("0.0")  && !textFieldReste.getText().equals("0")){
													JOptionPane.showMessageDialog(null, "Valeur superieur au Reste des frais de Scoalrité N° 2 Scolarité");
												}else*/ if(textFieldReste.getText().equals("0") || R == 0){
													JOptionPane.showMessageDialog(null, "Eleve ayant tout payer les Frais de Scolarité");
												}else{
													JOptionPane.showMessageDialog(null, "Vous avez entrez une valeur superieur au reste des Frais de Scolarité");
												}
												init();
											}
											
										
								}
								
					remplirTableauANC();
					
				}
						
						
				if(comboLib.getSelectedItem().toString().equals("Autres") && !textField.getText().toString().equals("")
								|| comboClasse.getSelectedItem().equals("Aucune")){
							if(comboClasse.getSelectedItem().equals("Aucune") && !textField.getText().toString().equals("")
									){
								Double R = 0.0;
								String elv = "Aucun";
								String cls = "Aucune";
								String MTINS = "0.0";
								String MTTEN = "0.0";
								int ID = 983 ;
								PreparedStatement ps;
								try {
									ps = cnx.prepareStatement("INSERT into recettes (id_elv, anne_scolaire, Libelle, Nom_Elv,"
											+ "cls_nom, Montant, reste_a_paye, Montant_cls_ins, Montant_cls_tenu, Date, autres_libelles) "
											+ "values(?,?,?,?,?,?,?,?,?,?,?)");
									ps.setInt(1, ID);
									ps.setString(2, comboAn.getSelectedItem().toString());
									ps.setString(3, comboLib.getSelectedItem().toString());
									ps.setString(4, elv);
									ps.setString(5,cls);
									ps.setDouble(6, t);
									ps.setDouble(7, R);
									ps.setDouble(8, Double.parseDouble(MTINS));
									ps.setDouble(9, Double.parseDouble(MTTEN));
									ps.setString(10, textFieldDate.getText().toString());
									ps.setString(11, textField.getText().toString());
									ps.executeUpdate();
									JOptionPane.showMessageDialog(null, "Enregistrement reussit 3333 ");
									//&& !textFieldSum.getText().toString().equals("0.0")
									} catch (SQLException e) {
									e.printStackTrace();
								}
								init();
								ifDepense f = new ifDepense();
								f.remplirTableauANC();
							}else {
								if(!comboClasse.getSelectedItem().equals("Aucune")){
									JOptionPane.showMessageDialog(null, "Il n'y a pa de classe pour ce type d'encaissement");
								}else if(!comboLib.getSelectedItem().equals("Aucune") && !comboNomEl.getSelectedItem().equals("")){
									JOptionPane.showMessageDialog(null, "Veillez revoir la proceduire de saisie SVP.");
								}else if(textField.getText().toString().equals("")){
									JOptionPane.showMessageDialog(null, "Entrez le Libellé dans le champs vide ");
								}
							}
							//BindComboCour();
							comboAn.setSelectedItem("Choix");
						}
						
						
						/////////////////////////////////////////////////////////////////////////////
						/////////////////    FIN 1ERE INSTRUCTION INTERNE           /////////////////
						/////////////////////////////////////////////////////////////////////////////
												
						/////////////////////////////////////////////////////////////////////////////
						/////////////////    2EME INSTRUCTION INTERNE               /////////////////
						/////////////////////////////////////////////////////////////////////////////
						
						/////////////////////////////////////////////////////////////////////////////
						/////////////////   FIN 2EME INSTRUCTION INTERNE           /////////////////
						/////////////////////////////////////////////////////////////////////////////
						
						
						System.out.println(" Entrer reste 11 "+textFieldReste.getText().toString());
					
					
					
					}else{
						if(t.equals("")){
							JOptionPane.showMessageDialog(null, "Veillez entrez la valeur du montant ");
						}else{

							System.out.println("VOUS AVEZ SAISSI UNE VALEUR NEGATIVE");
							JOptionPane.showMessageDialog(null, "DONNEE NEGATIVE CHANGEZ SVP !!!! ");
						}
					}
					
					
					} catch (Exception e) {
						// TODO: handle exception
						
						if(comboSco.getSelectedItem().toString().equals("Choix") || comboTenu.getSelectedItem().toString().equals("Choix")){
							JOptionPane.showMessageDialog(null, "UN CHAMPS N'EST PAS CHOISIT OU MAUVAISE SAISI !!!");
						}else{
							System.out.println("VS AVEZ ENTREZ DES DONNEES INVALIDEES ");
							JOptionPane.showMessageDialog(null, "DONNEE INVALIDES OU INFORMATIONS INCOMPLETE VERIFIEZ SVP !!!! ");
						}
						System.out.println("VSvsssssss ");
					}
					//init();
				}
				
			}
		});
		btnEnrg.setBounds(65, 11, 150, 33);
		panel_2.add(btnEnrg);
		
		comboAn = new JComboBox();
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(comboAn.getSelectedItem().equals(false) || comboClasse.getSelectedItem().equals(false)){
					System.out.println("Aucune");
					comboNomEl.removeAllItems();
					comboNomEl.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
				}else{
					//comboNomEl.removeAllItems();!comboClasse.getSelectedItem().equals("Choix")
				MethodeRecetteDepense mk = new MethodeRecetteDepense();
				if(true){
					
				
						
					
					ArrayList<eleveEL> list = mk.getData(comboAn.getSelectedItem().toString(), comboClasse.getSelectedItem().toString());
					for(int i = 0; i < list.size(); i++ ){
						comboNomEl.addItem(list.get(i).getNomEtprenom());
						
					}
					
						
						//BindCombo();
					
					
				}else{
					System.out.println("PASSAGE");
				}
				
				
				//comboClasse.removeAllItems();
				comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
				if(comboClasse.getSelectedItem().equals("Choix")){
					comboNomEl.setEnabled(false);
				}
				BindCombo();
				//remplirTableauANC();
			  }
			}
		});
		comboAn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboAn.setBounds(134, 34, 112, 31);
		panel.add(comboAn);
		
		textField = new JTextField();
		textField.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textField.setText("");
				
			}
		});
		textField.setHorizontalAlignment(SwingConstants.CENTER);
		textField.setColumns(10);
		textField.setBounds(134, 248, 258, 31);
		panel.add(textField);
		
		JLabel lblEntrezAutre = new JLabel("Entrez Autre :");
		lblEntrezAutre.setHorizontalAlignment(SwingConstants.CENTER);
		lblEntrezAutre.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblEntrezAutre.setBounds(10, 247, 122, 31);
		panel.add(lblEntrezAutre);
		
		textFieldId = new JTextField();
		textFieldId.setText("0");
		textFieldId.setEnabled(false);
		textFieldId.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldId.setBounds(485, 241, 47, -17);
		panel.add(textFieldId);
		textFieldId.setColumns(10);
		
		idClasse = new JTextField();
		idClasse.setEnabled(false);
		idClasse.setEditable(false);
		idClasse.setBounds(256, 87, 34, 31);
		panel.add(idClasse);
		idClasse.setColumns(10);
		
		JLabel lblMontantTeeshirt = new JLabel("Montant Tee_Shirt :");
		lblMontantTeeshirt.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantTeeshirt.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMontantTeeshirt.setBounds(504, 193, 147, 31);
		panel.add(lblMontantTeeshirt);
		
		comboTeeS = new JComboBox();
		comboTeeS.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboTeeS.setBounds(649, 193, 262, 31);
		panel.add(comboTeeS);
		
		
		btnRimprimer = new JButton("R\u00E9imprimer :");
		btnRimprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					ConnexionMySql p = new ConnexionMySql();
					p.ConnexiomBd();
				
				int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous réimprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
				if(imp == 0){
					//JasperDesign jd;
					//JasperReport jf;
					if(comboLib.getSelectedItem().toString().equals("Frais Inscription")){
						try {
						JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiementDupli.jrxml");
						HashMap vNomEleve = new HashMap();
						
						vNomEleve.put("PidEleve", Integer.parseInt(textFieldId.getText().toString()));
						vNomEleve.put("anSco", comboAn.getSelectedItem().toString());
						vNomEleve.put("dateVer", textFieldDate.getText().toString());
						vNomEleve.put("nomElv", comboNomEl.getSelectedItem().toString());
						vNomEleve.put("libelle", comboLib.getSelectedItem().toString());
						vNomEleve.put("montantScola", Double.parseDouble(comboSco.getSelectedItem().toString()));
						JasperReport jr = JasperCompileManager.compileReport(jd);
						JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
						JasperViewer jv = new JasperViewer(jp, false);
						jv.setVisible(true);	
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					}else if(comboLib.getSelectedItem().toString().equals("Frais des Tenues")){
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiementDupli.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("PidEleve", Integer.parseInt(textFieldId.getText().toString()));
							vNomEleve.put("anSco", comboAn.getSelectedItem().toString());
							vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText().toString()));
							vNomEleve.put("nomElv", comboNomEl.getSelectedItem().toString());
							vNomEleve.put("libelle", comboLib.getSelectedItem().toString());
							vNomEleve.put("montantScola", Double.parseDouble(comboSco.getSelectedItem().toString()));
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);	
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}else {
						
						
					}
					
					
					
					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
			}
		});
		btnRimprimer.setBounds(65, 58, 150, 33);
		panel_2.add(btnRimprimer);
		BindComboCour();
		BindCombo();
		RubEntree();
		remplirTableauANC();
	}
	
	
	public boolean checkInput(){//||textFieldMontant.getText().equals("")
		if(textFieldDate.getText().equals("")  || comboAn.getSelectedItem().equals("Choix") 
				||comboClasse.getSelectedItem().equals("Choix")
				|| comboLib.getSelectedItem().equals("Choix")){
			JOptionPane.showMessageDialog(null, "IL Y A DES CHAMPS NON SELECTIONNES ");
			return false;
		}else{
		return true;
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
	
	public void BindCombo(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT cls_nom FROM classe ");
			while(rs.next()){
				comboClasse.addItem(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void RubEntree(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT Lib_rub_ent FROM rub_entree ");
			while(rs.next()){
				comboLib.addItem(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void init(){
		//textFieldDate.setText("");
		textFieldMontant.setText("");
	}
	
	
	public void remplirTableauANC(){
		   
		   
			 
		  if(!textFieldId.getText().equals("0") && !comboClasse.getSelectedItem().equals("Aucune") 
				  && !comboNomEl.getSelectedItem().equals("Choix")){
		
			  String sql21 = " select  Sum(Montant) from recettes where   id_elv  = '"+Integer.parseInt(textFieldId.getText())+"' "
					+ "and recettes.cls_nom = '"+comboClasse.getSelectedItem().toString()+"'"
					+ " and Libelle = '"+comboLib.getSelectedItem().toString()+"' and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'";
			
			try {

				prepared = cnx.prepareStatement(sql21);
				resultat = prepared.executeQuery();
				if(resultat.next()){
					
					textFieldVerse.setText(resultat.getString("Sum(Montant)"));
					//textFieldVerse.setText(String.valueOf(resultat.getDouble("Sum(Montant)")));
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+e);

			}
			
			
			String sql24= " select Min(reste_a_paye) from recettes where  id_elv = '"+Integer.parseInt(textFieldId.getText())+"' "
					+ "and recettes.cls_nom = '"+comboClasse.getSelectedItem().toString()+"'"
					+ " and Libelle = '"+comboLib.getSelectedItem().toString()+"'and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'";
					
			
			try {

				prepared = cnx.prepareStatement(sql24);
				resultat = prepared.executeQuery();
				if(resultat.next()){
					textFieldReste.setText(resultat.getString("Min(reste_a_paye)"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+e);
			}
		  }
		
	}
}
