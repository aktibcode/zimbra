package metier;
import connec.Donne;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.codehaus.groovy.testng.TestNgRunner;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import connec.ConnexionMySql;
import entite.classeEL;
import entite.eleveEL;
import entite.inscriptionEL;
import entite.matiereEL;

import com.toedter.calendar.JDateChooser;

public class ifGest_InscEleve extends JInternalFrame {


	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	@SuppressWarnings("rawtypes")
	private JComboBox comboClasse;
	@SuppressWarnings("rawtypes")
	private JComboBox comboEleve;
	@SuppressWarnings("rawtypes")
	private JComboBox comboNumero;
	private JLabel lblNumeroEleve;
	private JLabel lblChoisissezUnEleve;
	private JLabel lblChoix;
	private JTable table;
	private JComboBox comboAnSco;
	private String no1;
	private String no2;
	private String notEx;
	private String idelv;
	private int elvID;
	private double moy_Gen;
	private double moyCls;
	private double noEx;
	protected String idnote;
	private JButton btnFermer;
	private JLabel lblMontantScolarit;
	private JComboBox comboBox;
	private JTextField textFieldSum;
	private JTextField textFieldSumClas;
	private JLabel lblMontantDeLeleve;
	private JLabel lblTotalVersPour;
	private JTextField textFieldRest;
	private JLabel lblResteVers;
	private JTextField textFieldMontant;
	private JLabel lblMontantVers;
	private JDateChooser dateChooser;
	private JLabel lblRecuN;
	private JTextField textFieldN_Recu;
	private JButton btnAjouter;
	private JComboBox comboRecu;
	private JButton btnEditionBulletin;
	private JPanel panel_1;
	private JPanel panel;


	public ifGest_InscEleve() {
		setTitle("Gestion des Inscriptions ");
		setBounds(300, 128, 999, 474);
		setMaximizable(false);
		setResizable(false);
		setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		setVisible(true);
		cnx = ConnexionMySql.ConnexiomBd();
		getContentPane().setLayout(null);

		panel = new JPanel();
		panel.setBounds(10, 11, 545, 214);
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Infos relative pour le re\u00E7u", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		getContentPane().add(panel);
		panel.setLayout(null);
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Ann\u00E9e Scolaire et Scolarit\u00E9", TitledBorder.LEADING, TitledBorder.TOP, null, Color.BLACK));
		panel_1.setBounds(565, 11, 408, 190);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);

		lblChoix = new JLabel("Choisissez un Classe:");
		lblChoix.setBounds(10, 68, 155, 27);
		panel.add(lblChoix);
		lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoix.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

		comboClasse = new JComboBox();
		comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboClasse.setBounds(10, 102, 155, 27);
		panel.add(comboClasse);

		comboEleve = new JComboBox();
		comboEleve.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboEleve.setBounds(179, 102, 155, 27);
		panel.add(comboEleve);

		comboNumero = new JComboBox();
		comboNumero.setBounds(349, 102, 155, 27);
		panel.add(comboNumero);

		lblChoisissezUnEleve = new JLabel("Choisissez un Eleve:");
		lblChoisissezUnEleve.setBounds(178, 68, 155, 27);
		panel.add(lblChoisissezUnEleve);
		lblChoisissezUnEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoisissezUnEleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

		lblNumeroEleve = new JLabel("Numero Eleve :");
		lblNumeroEleve.setBounds(348, 68, 155, 27);
		panel.add(lblNumeroEleve);
		lblNumeroEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroEleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

		textFieldRest = new JTextField();
		textFieldRest.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRest.setEditable(false);
		textFieldRest.setColumns(10);
		textFieldRest.setBounds(179, 171, 151, 32);
		panel.add(textFieldRest);

		textFieldSum = new JTextField();
		textFieldSum.setBounds(349, 171, 155, 32);
		panel.add(textFieldSum);
		textFieldSum.setEditable(false);
		textFieldSum.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSum.setColumns(10);

		lblMontantDeLeleve = new JLabel("Montant de l'Eleve :");
		lblMontantDeLeleve.setBounds(349, 137, 155, 26);
		panel.add(lblMontantDeLeleve);
		lblMontantDeLeleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantDeLeleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

		lblResteVers = new JLabel("Reste \u00E0 Vers\u00E9 :");
		lblResteVers.setHorizontalAlignment(SwingConstants.CENTER);
		lblResteVers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblResteVers.setBounds(179, 138, 151, 26);
		panel.add(lblResteVers);

		textFieldMontant = new JTextField();
		
		
		if(!textFieldRest.getText().toString().equals(false)){
			textFieldMontant.addMouseListener(new MouseAdapter() {
				@Override
				public void mousePressed(MouseEvent arg0) {
					
					System.out.println("Vous saississez dans le champs montant");
					btnAjouter.setEnabled(true);
					//textFieldRest.setText("");
					btnEditionBulletin.setEnabled(true);
					remplirTableauANC();
				}
				
				
				
			});
		}
		
		
		
		textFieldMontant.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMontant.setColumns(10);
		textFieldMontant.setBounds(10, 171, 155, 32);
		panel.add(textFieldMontant);

		lblMontantVers = new JLabel("Montant Vers\u00E9 :");
		lblMontantVers.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantVers.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblMontantVers.setBounds(10, 138, 155, 26);
		panel.add(lblMontantVers);
		
		lblRecuN = new JLabel("Recu N\u00B0:");
		lblRecuN.setHorizontalAlignment(SwingConstants.CENTER);
		lblRecuN.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblRecuN.setBounds(10, 24, 76, 27);
		panel.add(lblRecuN);
		
		textFieldN_Recu = new JTextField();
		textFieldN_Recu.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldN_Recu.setEditable(false);
		textFieldN_Recu.setColumns(10);
		textFieldN_Recu.setBounds(77, 24, 73, 27);
		panel.add(textFieldN_Recu);
		
		
		
		lblMontantScolarit = new JLabel("Montant Scolarit\u00E9 :");
		lblMontantScolarit.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantScolarit.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblMontantScolarit.setBounds(209, 23, 175, 26);
		panel_1.add(lblMontantScolarit);

		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboBox.setBounds(210, 59, 174, 27);
		panel_1.add(comboBox);

		textFieldSumClas = new JTextField();
		textFieldSumClas.setEditable(false);
		textFieldSumClas.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldSumClas.setColumns(10);
		textFieldSumClas.setBounds(209, 147, 175, 32);
		panel_1.add(textFieldSumClas);

		lblTotalVersPour = new JLabel("Total Vers\u00E9 Pour la Classe  :");
		lblTotalVersPour.setHorizontalAlignment(SwingConstants.CENTER);
		lblTotalVersPour.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblTotalVersPour.setBounds(209, 108, 175, 26);
		panel_1.add(lblTotalVersPour);

		dateChooser = new JDateChooser();
		dateChooser.setDateFormatString("d/MM/yyyy");
		dateChooser.setBounds(24, 147, 159, 32);
		panel_1.add(dateChooser);
		
		
		
		
		comboRecu = new JComboBox();
		comboRecu.setBounds(156, 24, 73, 27);
		panel.add(comboRecu);
		comboEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboNumero.removeAllItems();
				Donne mk = new Donne();
				ArrayList<eleveEL> list2 = mk.getData3(comboEleve.getSelectedItem().toString());
				for(int j = 0; j < list2.size(); j++ ){
					comboNumero.addItem(list2.get(j).getElv_id());

					System.out.println("Requete executer ");
				
				}
				btnAjouter.setEnabled(true);
				//textFieldMontant.setText("");
				//textFieldRest.setText("");
				remplirTableauANC();
			}
		});
		comboClasse.addActionListener(new ActionListener() {

			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				comboEleve.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));

				comboBox.removeAllItems();
				comboNumero.removeAllItems();
				//comboEleve.setSelectedItem("");
				if(comboClasse.getSelectedItem().equals(false && comboBox.equals(false))){
					System.out.println("Aucune");
					comboEleve.removeAllItems();
					comboBox.removeAllItems();
				}else{
					Donne mk = new Donne();
					ArrayList<eleveEL> list = mk.getData2(comboClasse.getSelectedItem().toString());
					ArrayList<inscriptionEL> list2 = mk.getData4(comboClasse.getSelectedItem().toString());
					for(int i = 0; i < list.size(); i++ ){
						comboEleve.addItem(list.get(i).getNomEtprenom());

					}
					for(int j = 0; j < list2.size(); j++){
						comboBox.addItem(list2.get(j).getMontant());
					}




				}
				
				btnAjouter.setEnabled(true);
				//updateTable2();
			}


		});

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(24, 234, 932, 139);
		getContentPane().add(scrollPane);

		//btnAjouter.setEnabled(true);
        btnAjouter = new JButton("Ajouter");
		btnAjouter.setBounds(10, 394, 125, 23);
		btnAjouter.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {

				if(checkput()){
					
					
					
						Double t = Double.parseDouble(textFieldMontant.getText().toString());
						Double M_S = Double.parseDouble(comboBox.getSelectedItem().toString());

						if(t>0){

							if(textFieldRest.getText().toString().equals("") ){
								
								Double R = M_S - t ;
								PreparedStatement ps;
								try {
									ps = cnx.prepareStatement("INSERT into inscription ( nom_cls, nom_prenom, elv_id,"
											+ "Montant_Versé, Reste_à_Versé, Date_Versement, Année_Scolaire) "
											+ "values(?,?,?,?,?,?,?)");

									ps.setString(1, comboClasse.getSelectedItem().toString());
									ps.setString(2, comboEleve.getSelectedItem().toString());
									ps.setString(3, comboNumero.getSelectedItem().toString());
									ps.setDouble(4, t);
									ps.setDouble(5, R);
									ps.setString(6, ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().toString());
									ps.setString(7, comboAnSco.getSelectedItem().toString());
									ps.executeUpdate();
									JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
									//&& !textFieldSum.getText().toString().equals("0.0")
									
									
									
								} catch (SQLException e) {
									// TODO Auto-generated catch block  && t <= Double.parseDouble(textFieldRest.getText().toString()) !textFieldRest.getText().toString().equals("0.0")
									//&&  						Double Cal = Double.parseDouble(textFieldRest.getText().toString());
									e.printStackTrace();
								}
								
								remplirTableauANC();
								
								
								int imp1 = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
								if(imp1 == 0){
									try {
									JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiement.jrxml");
									HashMap vNomEleve = new HashMap();
									vNomEleve.put("Pideleve", comboNumero.getSelectedItem().toString());
									vNomEleve.put("anSco", comboAnSco.getSelectedItem().toString());
									vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText()));
									vNomEleve.put("numRec", Integer.parseInt(textFieldN_Recu.getText()));
									JasperReport jr = JasperCompileManager.compileReport(jd);
									JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  cnx);
									JasperViewer jv = new JasperViewer(jp, false);
									jv.setVisible(true);		
									} catch (Exception e) {
										// TODO: handle exception
									}
								}
									
								
							}else if(t <= Double.parseDouble(comboBox.getSelectedItem().toString()) &&  t <= Double.parseDouble(textFieldRest.getText().toString())){
								Double R = Double.parseDouble(textFieldRest.getText().toString()) - t ;
								if(R != 0 || R == 0){	

									PreparedStatement ps;
									try {
										ps = cnx.prepareStatement("INSERT into inscription ( nom_cls, nom_prenom, elv_id,"
												+ "Montant_Versé, Reste_à_Versé, Date_Versement, Année_Scolaire) "
												+ "values(?,?,?,?,?,?,?)");

										ps.setString(1, comboClasse.getSelectedItem().toString());
										ps.setString(2, comboEleve.getSelectedItem().toString());
										ps.setString(3, comboNumero.getSelectedItem().toString());
										ps.setDouble(4, t);
										ps.setDouble(5, R);
										ps.setString(6, ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().toString());
										ps.setString(7, comboAnSco.getSelectedItem().toString());
										ps.executeUpdate();
										JOptionPane.showMessageDialog(null, "Enregistrement reussit 22222 VVVVVV ");
										
										int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
										if(imp == 0){
											try {
												
											
											JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiement.jrxml");
											HashMap vNomEleve = new HashMap();
											
											vNomEleve.put("PidEleve", Integer.parseInt(comboNumero.getSelectedItem().toString()));
											vNomEleve.put("anSco", comboAnSco.getSelectedItem().toString());
											vNomEleve.put("montantVerse", Double.parseDouble(textFieldMontant.getText()));
											vNomEleve.put("numRec", Integer.parseInt(textFieldN_Recu.getText()));
											JasperReport jr = JasperCompileManager.compileReport(jd);
											JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  cnx);
											JasperViewer jv = new JasperViewer(jp, false);
											jv.setVisible(true);
											} catch (Exception e) {
												// TODO: handle exception
											}
											
										}
									} catch (SQLException e) {
										
										e.printStackTrace();
									}
									
								remplirTableauANC();	
								}
							}else if( t > Double.parseDouble(comboBox.getSelectedItem().toString()) ){
								JOptionPane.showMessageDialog(null, "Ce montant est au dessus des Frais de Scolarité");
							}else if(Double.parseDouble(textFieldRest.getText().toString()) < t && !textFieldSum.getText().toString().equals("0.0") 
									&& t <  Double.parseDouble(comboBox.getSelectedItem().toString())){
								JOptionPane.showMessageDialog(null, "CET ELEVE MONTANT EST SUPERIEUR AU RESTE");
							//	init();
							}    

						}else{
							JOptionPane.showMessageDialog(null, "Vous avez introduit une valeur négative ");
						}
						System.out.println("Aucune execution du code");
					
				}else{
					JOptionPane.showMessageDialog(null, "Certains champs sont incompletes !!! ");
				}
				
			}
		});
		getContentPane().add(btnAjouter);

	    btnEditionBulletin = new JButton("Edition Bulletin :");
		btnEditionBulletin.setBounds(164, 394, 125, 23);
		btnEditionBulletin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
				
				if(checkput()){
				int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
				if(imp == 0){
					JasperDesign jd;
					try {
						jd = JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/DrecuPaiement.jrxml");
					
					HashMap vNomEleve = new HashMap();
					
					vNomEleve.put("PidEleve", Integer.parseInt(comboNumero.getSelectedItem().toString()));
					vNomEleve.put("anSco", comboAnSco.getSelectedItem().toString());
					vNomEleve.put("montantVerse", Double.parseDouble(textFieldSum.getText()));
					vNomEleve.put("reste", Double.parseDouble(textFieldRest.getText()));
					vNomEleve.put("numRec", Integer.parseInt(textFieldN_Recu.getText()));
					JasperReport jr = JasperCompileManager.compileReport(jd);
					JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  cnx);
					JasperViewer jv = new JasperViewer(jp, false);
					jv.setVisible(true);	
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					}
				//init();
				
				}
				
				textFieldMontant.setText("");
				//textFieldRest.setText("");
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Selectionner une ligne dans le tableau");
				}
				
			}
		});
		getContentPane().add(btnEditionBulletin);

		btnFermer = new JButton("Fermer");
		btnFermer.setBounds(320, 394, 125, 23);
		btnFermer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ifGest_InscEleve.this.dispose();
			}
		});
		getContentPane().add(btnFermer);

		

		JLabel lblChoissezLeSemestre = new JLabel("Ann\u00E9e Scolaire :");
		lblChoissezLeSemestre.setBounds(24, 23, 159, 26);
		panel_1.add(lblChoissezLeSemestre);
		lblChoissezLeSemestre.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoissezLeSemestre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));

		comboAnSco = new JComboBox();
		comboAnSco.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAnSco.setBounds(24, 59, 159, 27);
		panel_1.add(comboAnSco);
		comboAnSco.addActionListener(new ActionListener() {
			JComboBox comboAnScol;

			public void actionPerformed(ActionEvent arg0) {

				btnAjouter.setEnabled(true);

				////////////////////////////////////////////////
				/////remplir le tableau
				///////////////////////////////////////////////
				remplirTableauANC();
				
				try {

					String sql2 = " select Min(Reste_à_Versé) , Sum(Montant_Versé) from inscription where  elv_id= '"+comboNumero.getSelectedItem().toString()+"' "
							+ " and  Année_Scolaire= '"+comboAnSco.getSelectedItem().toString()+"' and nom_cls = '"+comboClasse.getSelectedItem().toString()+"'";
					try {

						prepared = cnx.prepareStatement(sql2);
						resultat = prepared.executeQuery();
						if(resultat.next()){
							textFieldRest.setText(String.valueOf(resultat.getDouble("Min(Reste_à_Versé)")));
							textFieldSum.setText(String.valueOf(resultat.getDouble("Sum(Montant_Versé)")));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+e);

					}
				} catch (Exception e) {
					// TODO: handle exception
				}
				
				
				
				try {


					String sql3 = " select id_recu, Min(Reste_à_Versé) as Reste from inscription where  elv_id= '"+comboNumero.getSelectedItem().toString()+"' "
							+ " and  Année_Scolaire= '"+comboAnSco.getSelectedItem().toString()+"' and nom_cls = '"+comboClasse.getSelectedItem().toString()+"'";
					try {

						prepared = cnx.prepareStatement(sql3);
						resultat = prepared.executeQuery();
						if(resultat.next()){
							textFieldN_Recu.setText(resultat.getString("id_recu"));
							textFieldRest.setText(String.valueOf(resultat.getDouble("Reste")));
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+e);

					}
				
				
				comboRecu.removeAllItems();
				Donne mk = new Donne();
				ArrayList<classeEL> list2 = mk.getData6(comboAnSco.getSelectedItem().toString(), Integer.parseInt(comboNumero.getSelectedItem().toString()));
				for(int j = 0; j < list2.size(); j++ ){
					comboRecu.addItem(list2.get(j).getId_recu());

					System.out.println("Requete executer ");
				
				}
				} catch (Exception e) {
					// TODO: handle exception
				}
				textFieldMontant.setText("");
				//textFieldRest.setText("");

			}
		});
		
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
                comboAnSco.getSelectedItem().equals("");
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				int idRE = Integer.parseInt(id);
                
				String sql = "select * from inscription where id_recu ='"+idRE+"'";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					if(resultat.next()){
						
						textFieldSum.setText(resultat.getString("Montant_Versé"));
						textFieldRest.setText(String.valueOf(resultat.getDouble("Reste_à_Versé")));
						
					}

				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erreur ici "+e.getMessage());
				}
				JOptionPane.showMessageDialog(null, id);
				comboRecu.setSelectedItem(id);
				textFieldN_Recu.setText(id);
				btnAjouter.setEnabled(false);	
				


			}
		});
		scrollPane.setViewportView(table);

		BindCombo();
		BindComboCour();
		//updateTable2();

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


	public void BindComboCour(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT anne_courant FROM annee ");
			while(rs.next()){
				comboAnSco.addItem(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}



	public boolean checkput(){
		if(comboClasse.getSelectedItem().toString().equals("Choix")  
				|| comboEleve.getSelectedItem().toString().equals("Choix") 
				|| comboNumero.getSelectedItem().toString().equals("")){
			return false;
		}	return true;

	}

	public void init(){
		comboClasse.setSelectedItem("Choix");
		comboEleve.setSelectedItem("Choix");
		comboNumero.setSelectedItem("Choix");
		comboAnSco.setSelectedItem("Choix");
		textFieldMontant.setText("");
		//textFieldRest.setText("");

	}
	
	
	public void remplirTableauANC(){
		String sql = " select * from inscription where  Année_Scolaire= '"+comboAnSco.getSelectedItem().toString()+"' "
				+ " and nom_prenom = '"+comboEleve.getSelectedItem().toString()+"' and nom_cls = '"+comboClasse.getSelectedItem().toString()+"'";
		try {

			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			System.out.println("Erreur 1 "+e);

		}
		
		
		try {
			String sql2 = " select Min(Reste_à_Versé) from inscription where  elv_id= '"+comboNumero.getSelectedItem().toString()+"' "
					+ " and  Année_Scolaire= '"+comboAnSco.getSelectedItem().toString()+"' and nom_cls = '"+comboClasse.getSelectedItem().toString()+"'";
			try {

				prepared = cnx.prepareStatement(sql2);
				resultat = prepared.executeQuery();
				if(resultat.next()){
					textFieldRest.setText(resultat.getString("Min(Reste_à_Versé)"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+e);

			}
			
			} catch (Exception e) {
			System.out.println("Pour Reste "+e);
			JOptionPane.showMessageDialog(null, "VOUS DEVRIEZ CHOISIR UN ELEVE POUR CONTINUER  !!! ");
			}
		
		
		String sql2 = " select Max(id_recu) from inscription where  elv_id= '"+comboNumero.getSelectedItem().toString()+"' "
				+ " and  Année_Scolaire= '"+comboAnSco.getSelectedItem().toString()+"' and nom_cls = '"+comboClasse.getSelectedItem().toString()+"'";
		try {

			prepared = cnx.prepareStatement(sql2);
			resultat = prepared.executeQuery();
			if(resultat.next()){
				textFieldN_Recu.setText(resultat.getString("Max(id_recu)"));
			
			
			System.out.println(resultat.getString("Max(id_recu)"));}
		} catch (SQLException e) {
			// TODO Auto-generated catch block 
			System.out.println("Erreur 1 "+e);

		}
		
	}
}
