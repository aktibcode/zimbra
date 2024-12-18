package metier;

import java.awt.EventQueue;
import java.io.File;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Connection;
import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;

import connec.ConnexionMySql;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;
import javax.swing.text.MaskFormatter;

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

import javax.swing.JCheckBox;

/**
 * @author Isidore
 *
 */
public class ifDepense extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	private JTextField textFieldDepense;
	private JTextField textFieldMontant;
	private JComboBox comboAn;
	private JComboBox comboLib;
	private JTextField textFieldDate;
	private JTextField textFieldAutre;
	private JTextField textFieldAn;
	private JTextField textFieldrecette;
	private JTextField textFieldsolde;
	private JTextField textFieldRef;
	private JCheckBox chckbxMoDate;
	
	public ifDepense() {
		setIconifiable(true);
		setClosable(true);
		
		setTitle("Gestion des Depenses");
		setBounds(200, 150, 1182, 500);
		setFrameIcon(new ImageIcon(".\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		setVisible(true);
		cnx = ConnexionMySql.ConnexiomBd();
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255), 2), "Informations sur la Depense \u00E9ffectu\u00E9e", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel.setBounds(10, 11, 1146, 213);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Choix Libelle :");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setBounds(10, 87, 125, 31);
		panel.add(lblNewLabel);
		
		comboLib = new JComboBox();
		comboLib.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboLib.getSelectedItem().equals("Autres") ){
					//comboNomEnsg.setEnabled(false);
					textFieldAutre.setEnabled(false);
					textFieldAutre.setText("Autre Libellé");
					/*textFieldAutre.setEditable(false);*/
				}else if(comboLib.getSelectedItem().equals("Autres") ){
					textFieldAutre.setEditable(true);
					textFieldAutre.setText("Autre Libellé");
					textFieldAutre.setEnabled(true);
				} 
				remplirTableauANC();
				som();
				
			}
		});
		comboLib.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboLib.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboLib.setBounds(134, 88, 344, 31);
		panel.add(comboLib);
		
		JLabel lblChoixAnne = new JLabel("Choix Ann\u00E9e :");
		lblChoixAnne.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixAnne.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblChoixAnne.setBounds(10, 33, 125, 31);
		panel.add(lblChoixAnne);
		
		
		
		JLabel lblSommeVers = new JLabel("Montant :");
		lblSommeVers.setHorizontalAlignment(SwingConstants.CENTER);
		lblSommeVers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblSommeVers.setBounds(405, 143, 106, 31);
		panel.add(lblSommeVers);
		
		textFieldMontant = new JTextField();
		textFieldMontant.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				/*if(textFieldReste.getText().toString().equals("")){
					btnRimprimer.setEnabled(false);
					btnEnrg.setEnabled(true);
				}else if(textFieldReste.getText().toString().equals("0")){
					btnEnrg.setEnabled(false);
					btnRimprimer.setEnabled(true);
				}else if(!textFieldReste.getText().toString().equals("")){
					btnEnrg.setEnabled(true);
					btnRimprimer.setEnabled(true);
				}*/
			}
		});
		
		textFieldMontant.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldMontant.setColumns(10);
		textFieldMontant.setBounds(511, 144, 260, 31);
		panel.add(textFieldMontant);
		
		JLabel lblDateVersement = new JLabel("Date Op\u00E9ration :");
		lblDateVersement.setHorizontalAlignment(SwingConstants.CENTER);
		lblDateVersement.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDateVersement.setBounds(693, 23, 149, 23);
		panel.add(lblDateVersement);
		
		textFieldDate = new JTextField();
		textFieldDate.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		textFieldDate.setEditable(false);
		textFieldDate.setHorizontalAlignment(SwingConstants.CENTER);
		//textFieldDate.setText(String.format("%tT", new Date(%dd, %mm, %yyyy)));//String.format("%tT", new Date());
		textFieldDate.setBounds(856, 23, 262, 31);
		panel.add(textFieldDate);
		Date d = new Date();
		SimpleDateFormat ft = new SimpleDateFormat("dd-MM-yyyy");
		String dt = ft.format(d);
		textFieldDate.setText(dt);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 255), 2), "Infos Depense du jour et par ann\u00E9e Scolaire", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setBounds(10, 266, 963, 177);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblMontantVers = new JLabel("Depense du jour :");
		lblMontantVers.setHorizontalAlignment(SwingConstants.CENTER);
		lblMontantVers.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblMontantVers.setBounds(10, 49, 121, 31);
		panel_1.add(lblMontantVers);
		
		textFieldDepense = new JTextField();
		textFieldDepense.setEditable(false);
		textFieldDepense.setForeground(new Color(0, 0, 255));
		textFieldDepense.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		textFieldDepense.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldDepense.setBounds(141, 41, 275, 44);
		panel_1.add(textFieldDepense);
		textFieldDepense.setColumns(10);
		
		textFieldAn = new JTextField();
		textFieldAn.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAn.setForeground(new Color(153, 51, 102));
		textFieldAn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		textFieldAn.setEditable(false);
		textFieldAn.setColumns(10);
		textFieldAn.setBounds(144, 104, 272, 44);
		panel_1.add(textFieldAn);
		
		JLabel lblDepenseAnnuelle = new JLabel("Depense Annuelle :");
		lblDepenseAnnuelle.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepenseAnnuelle.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblDepenseAnnuelle.setBounds(10, 112, 133, 31);
		panel_1.add(lblDepenseAnnuelle);
		
		textFieldrecette = new JTextField();
		textFieldrecette.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldrecette.setForeground(new Color(0, 0, 51));
		textFieldrecette.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		textFieldrecette.setEditable(false);
		textFieldrecette.setColumns(20);
		textFieldrecette.setBounds(498, 41, 455, 44);
		panel_1.add(textFieldrecette);
		
		JLabel lblSolde = new JLabel("Recettes :");
		lblSolde.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolde.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblSolde.setBounds(417, 49, 81, 31);
		panel_1.add(lblSolde);
		
		JLabel lblSolde_1 = new JLabel("Solde :");
		lblSolde_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblSolde_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblSolde_1.setBounds(417, 112, 81, 31);
		panel_1.add(lblSolde_1);
		
		textFieldsolde = new JTextField();
		textFieldsolde.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldsolde.setForeground(new Color(0, 0, 51));
		textFieldsolde.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 17));
		textFieldsolde.setEditable(false);
		textFieldsolde.setColumns(10);
		textFieldsolde.setBounds(498, 104, 455, 44);
		panel_1.add(textFieldsolde);
		
		comboAn = new JComboBox();
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				remplirTableauANC();
				som();
			}
		});
		comboAn.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		comboAn.setBounds(134, 34, 112, 31);
		panel.add(comboAn);
		
		JLabel lblEntrezAutres = new JLabel("Entrez Autres :");
		lblEntrezAutres.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblEntrezAutres.setBounds(10, 143, 125, 31);
		panel.add(lblEntrezAutres);
		
		textFieldAutre = new JTextField();
		textFieldAutre.setText("Autres Libelles");
		textFieldAutre.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textFieldAutre.setText("");
			}
		});
		
		textFieldAutre.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldAutre.setBounds(135, 143, 260, 31);
		textFieldAutre.setColumns(10);
		panel.add(textFieldAutre);
		
		JLabel lblReffact = new JLabel("Ref-Fact :");
		lblReffact.setHorizontalAlignment(SwingConstants.CENTER);
		lblReffact.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblReffact.setBounds(770, 141, 106, 31);
		panel.add(lblReffact);
		
		textFieldRef = new JTextField();
		textFieldRef.setText("");
		textFieldRef.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRef.setColumns(10);
		textFieldRef.setBounds(876, 142, 260, 31);
		panel.add(textFieldRef);
		
		chckbxMoDate = new JCheckBox("Modifier date :");
		chckbxMoDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxMoDate.isSelected()){
					textFieldDate.setEditable(true);
				}else{
					textFieldDate.setEditable(false);
				}
			}
		});
		chckbxMoDate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		chckbxMoDate.setHorizontalAlignment(SwingConstants.CENTER);
		chckbxMoDate.setBounds(703, 41, 142, 23);
		panel.add(chckbxMoDate);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(986, 266, 170, 177);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JButton button = new JButton("Enregistrer :");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(checkInput()){
					
					try {
					
						
					Double t = Double.parseDouble(textFieldMontant.getText().toString());
					Double sd = Double.parseDouble(textFieldsolde.getText().toString());
					if(t>0){
					Double r =  sd - t ;
					
					if( r >= 0 && t!= 0.0 ){
						
						PreparedStatement ps;
						PreparedStatement ps2;
						if(!textFieldRef.getText().equals("")){
							
						if(comboLib.getSelectedItem().equals("Autres")){
							
							try {
								
								ps2 = cnx.prepareStatement("INSERT into depense (anne_scolaire, Libelle, Montant_Dep, Date, autres_libelles, Ref_Facture) "
										+ "values(?,?,?,?,?,?)");
								ps2.setString(1, comboAn.getSelectedItem().toString());
								ps2.setString(2,comboLib.getSelectedItem().toString());
								ps2.setInt(3, Integer.parseInt(textFieldMontant.getText().toString()));
								ps2.setString(4, textFieldDate.getText().toString());
								ps2.setString(5, textFieldAutre.getText().toString());
								ps2.setString(6, textFieldRef.getText().toString());
								ps2.executeUpdate();
								JOptionPane.showMessageDialog(null, "Enregistrement reussit 2222 ");
								} catch (SQLException e) {
									JOptionPane.showMessageDialog(null, "Cette reference de Facture ' "+textFieldRef.getText().toString()+"'"
									 		+ " existe déjà, ou revoyez vos saisies. ");
								 
							}
						}
						 if(!comboLib.getSelectedItem().equals("Autres")){
							 
								
								try {
								ps = cnx.prepareStatement("INSERT into depense (anne_scolaire, Libelle, Montant_Dep, Date,autres_libelles, Ref_Facture) "
										+ "values(?,?,?,?,?,?)");

								ps.setString(1, comboAn.getSelectedItem().toString());
								ps.setString(2, comboLib.getSelectedItem().toString());
								ps.setInt(3, Integer.parseInt(textFieldMontant.getText().toString()));
								ps.setString(4, textFieldDate.getText().toString());
								ps.setString(5, textFieldAutre.getText().toString());
								ps.setString(6, textFieldRef.getText().toString());
								ps.executeUpdate();
								JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
								} catch (SQLException e) {
								 System.out.println("erreur ici !"+ e);
								 JOptionPane.showMessageDialog(null, "Cette reference de Facture ' "+textFieldRef.getText().toString()+"'"
								 		+ " existe déjà, ou revoyez vos saisies. ");
								}
							
						}
						
					  }else{
						  JOptionPane.showMessageDialog(null, "Veillez donner la Reference de la Facture SVP !!!");
					  }
						
						
					}else{
						
						if(t==0){
							JOptionPane.showMessageDialog(null, "Vous avez entrer une valeur null !!!");
						}else{
							JOptionPane.showMessageDialog(null, "Vous avez entrer un Montant au delà du Solde !!!");
						}
						
					}
					}else{
						JOptionPane.showMessageDialog(null, "Erreur sur les données Introduites !!!");
					}
					} catch (Exception e) {
						System.out.println("Verifiez les données introduitent");
						JOptionPane.showMessageDialog(null, "Erreur sur les données Introduites !!!");
					}
				}else{
					//som();
				}
				remplirTableauANC();
				 init();
				 som();
				 
			}
		});
		button.setBounds(10, 56, 150, 33);
		panel_3.add(button);
		
		JButton button_1 = new JButton("R\u00E9imprimer :");
		button_1.setBounds(10, 103, 150, 33);
		panel_3.add(button_1);
		BindComboCour();
		RubDepense();
		remplirTableauANC();
		init();
		//som();
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
			e.printStackTrace();
		}

	}
	
	public void RubDepense(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT Lib_rub_dep FROM rub_depense ");
			while(rs.next()){
				comboLib.addItem(rs.getString(1));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}
	
	public void init(){
		textFieldMontant.setText("");
		textFieldRef.setText("");
		textFieldAutre.setText("");
	}
	
	
	public void remplirTableauANC(){
		
		
			String sql2 = " select  Sum(Montant_Dep) from depense where   anne_scolaire= '"+comboAn.getSelectedItem().toString()+"'";
			try {

				prepared = cnx.prepareStatement(sql2);
				resultat = prepared.executeQuery();
				if(resultat.next()){
					textFieldAn.setText(String.valueOf(resultat.getBigDecimal("Sum(Montant_Dep)")));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+e);
			}
			
			String sql = " select  Sum(Montant_Dep) from depense where   Date= '"+textFieldDate.getText().toString()+"' "
					+ " and anne_scolaire= '"+comboAn.getSelectedItem().toString()+"'";
			try {

				prepared = cnx.prepareStatement(sql);
				resultat = prepared.executeQuery();
				if(resultat.next()){
					
					textFieldDepense.setText(String.valueOf(resultat.getBigDecimal("Sum(Montant_Dep)")));
					//textFieldDepense.setText(String.valueOf(resultat.getFloat("Sum(Montant_Dep)")));
					
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 2 "+e);

			}
		

			String sql3 = " select  Sum(Montant) AS MONTANT from recettes where   anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'";
			try {

				prepared = cnx.prepareStatement(sql3);
				resultat = prepared.executeQuery();
				if(resultat.next()){
					
					//textFieldrecette.setText(String.valueOf(resultat.getDouble("MONTANT")));
					textFieldrecette.setText(String.valueOf(resultat.getBigDecimal("MONTANT")));
					//String t = String.valueOf(Float.parseFloat(textFieldrecette.getText()));
					//formattedTextField.setText(t);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 3 "+e);

			}
		
		
	}
	
	public boolean checkInput(){
		if(textFieldMontant.getText().equals("") && textFieldRef.getText().equals("")){
			JOptionPane.showMessageDialog(null, "CHAMPS NON INTRODUIT OU REF-FACTURE INCOMPLET ");
			return false;
		}else{
		return true;
		}
		
	}
	
	public void som(){
		
		
		 if(!textFieldAn.getText().toString().equals("") && !textFieldrecette.getText().toString().equals("")){
			 try {
		
		
				//textFieldsolde.setText("0.0"); resultat.getBigDecimal("MONTANT")
				String d = textFieldAn.getText().toString();
				String r = textFieldrecette.getText().toString();
				Double s = Double.parseDouble(r)-Double.parseDouble(d);
				//Float s3 = Int.parseFloat(r)-Float.parseFloat(d);
				int s4 = Integer.parseInt(r)-Integer.parseInt(d);
					//BigDecimal			
					if(s4>=0){
					textFieldsolde.setText(String.valueOf(s4));
					
					
				}else{
					JOptionPane.showMessageDialog(null, "Votre Solde Serais negatif ");
				}
			} catch (Exception e) {
				e.getStackTrace();
			}
			 
		}else{
			JOptionPane.showMessageDialog(null, "Verifiez si le montant ou les references de factures sont introduites Merci !!! ");
		}
		 
	}
}
