package metier;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import connec.ConnexionMySql;

import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

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

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.DefaultComboBoxModel;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ifReimpRecu extends JInternalFrame {
	private JTextField numRec;
	Connection cnx =null;
	private JCheckBox chckbxInscription;
	private JCheckBox chckbxTenues;
	
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Statement st = null;
	private JTable table;
	private JTextField textFieldNum;
	/**
	 * Launch the application.
	 */
	private JComboBox comboAn;
	
	/**
	 * Create the frame.
	 */
	public ifReimpRecu() {
		setClosable(true);
		setTitle("Recaputilatif ");
		setBounds(300, 128, 984, 435);
		setFrameIcon(new ImageIcon(".\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		setVisible(true);
		cnx = ConnexionMySql.ConnexiomBd();
		getContentPane().setLayout(null);
		
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 135, 952, 251);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 825, 251);
		panel.add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter(){
			public void mouseClicked(MouseEvent arg0){
				int ligne = table.getSelectedRow();
			    String id = table.getModel().getValueAt(ligne, 0).toString();
				numRec.setText(id);
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnImp = new JButton("Imprimer :");
		btnImp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxInscription.isSelected()){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
					if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiementDupli.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("PidRecette",Integer.parseInt(numRec.getText().toString()));
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , cnx);
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);	
						} catch (JRException e) {
							JOptionPane.showMessageDialog(null, "Aucun numero de recu correspondant à celui démandé");
						}
						numRec.setText("0");
						}
				}
				
				if(chckbxTenues.isSelected()){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
					if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/recuPaiementDupli2.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("PidRecette",Integer.parseInt(numRec.getText().toString()));
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , cnx);
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);	
						} catch (JRException e) {
							JOptionPane.showMessageDialog(null, "Aucun numero de recu correspondant à celui démandé");
						}
						numRec.setText("0");
						}
				}
			}
		});
		btnImp.setBounds(837, 11, 105, 32);
		panel.add(btnImp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 14, 553, 85);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		chckbxInscription = new JCheckBox("Frais Inscription");
		chckbxInscription.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if(chckbxInscription.isSelected() && !comboAn.getSelectedItem().equals("Choix")){
					chckbxTenues.setEnabled(false);
					try {
						
					
					String sql = "select id_recette,  Nom_Elv, cls_nom, Montant, reste_a_paye, Date from recettes "
							+ "where anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and id_elv = '"+Integer.parseInt(textFieldNum.getText())+"' and Libelle='"+chckbxInscription.getText()+"'";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					System.out.println("Le libelle est : "+chckbxInscription.getText());
					
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Entrez un numero SVP !");
						chckbxTenues.setEnabled(true);
						chckbxInscription.setSelected(false);
					}
				}else{
					chckbxTenues.setEnabled(true);
					comboAn.setSelectedItem("Choix");
				}
				
			}
		});
		chckbxInscription.setBounds(44, 48, 123, 23);
		panel_1.add(chckbxInscription);
		
		chckbxTenues = new JCheckBox("Frais des Tenues");
		chckbxTenues.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(chckbxTenues.isSelected()&& !comboAn.getSelectedItem().equals("Choix")){
					chckbxInscription.setEnabled(false);
					
					try {
						
					
					String sql = "select id_recette,  Nom_Elv, cls_nom, Montant, reste_a_paye, Date from recettes "
							+ "where anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and id_elv = '"+Integer.parseInt(textFieldNum.getText())+"' and Libelle='"+chckbxTenues.getText()+"'";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						}
					
					} catch (Exception e) {
						JOptionPane.showMessageDialog(null, "Entrez un numero SVP !");
						chckbxInscription.setEnabled(true);
						chckbxTenues.setSelected(false);
					}
				}else{
					chckbxInscription.setEnabled(true);
					comboAn.setSelectedItem("Choix");
				}
			}
		});
		chckbxTenues.setBounds(197, 48, 131, 23);
		panel_1.add(chckbxTenues);
		
		JLabel Numero = new JLabel("Entrez Num :");
		Numero.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		Numero.setHorizontalAlignment(SwingConstants.CENTER);
		Numero.setBounds(10, 11, 123, 30);
		panel_1.add(Numero);
		
		textFieldNum = new JTextField();
		textFieldNum.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent arg0) {
				comboAn.setSelectedItem("Choix");
			}
		});
		textFieldNum.setBackground(Color.WHITE);
		textFieldNum.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldNum.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		textFieldNum.setColumns(10);
		textFieldNum.setBounds(143, 14, 185, 27);
		panel_1.add(textFieldNum);
		
		comboAn = new JComboBox();
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(chckbxInscription.isSelected()){
					String sql = "select id_recette,  Nom_Elv, cls_nom, Montant, reste_a_paye, Date from recettes "
							+ "where anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' and Libelle = '"+chckbxInscription.getText()+"'"
									+ "and id_elv = '"+Integer.parseInt(textFieldNum.getText().toString())+"'";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						}
					//remplTable();
					}else if(chckbxTenues.isSelected()){
						String sql = "select id_recette,  Nom_Elv, cls_nom, Montant, reste_a_paye, Date from recettes "
								+ "where anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' and Libelle = '"+chckbxTenues.getText()+"'"
								+ "and id_elv = '"+Integer.parseInt(textFieldNum.getText().toString())+"'";
						try {
							
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException ex) {
							// TODO Auto-generated catch block 
							System.out.println("Erreur 1 "+ex);
						
							
							}
						
					}else{
						
						try {
							
						String sql2 = "select id_recette, Libelle, Nom_Elv, cls_nom, Montant, reste_a_paye, Date from recettes "
								+ "where anne_scolaire ='"+comboAn.getSelectedItem().toString()+ "' and id_elv = '"+Integer.parseInt(textFieldNum.getText().toString())+"'";
						try {
							
							prepared = cnx.prepareStatement(sql2);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException ex) {
							// TODO Auto-generated catch block 
							System.out.println("Erreur 1 "+ex);
						
							}
						chckbxTenues.setEnabled(true);
						chckbxTenues.setSelected(false);
						chckbxInscription.setEnabled(true);
						chckbxInscription.setSelected(false);
						
						
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						
					}
				
				
			}
		});
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.setBounds(425, 11, 118, 29);
		panel_1.add(comboAn);
		
		JLabel label = new JLabel("Ann\u00E9e :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		label.setBounds(340, 11, 82, 30);
		panel_1.add(label);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(599, 14, 359, 110);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("R\u00E9\u00E7u N\u00B0 :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(203, 11, 75, 27);
		panel_2.add(lblNewLabel_1);
		
		numRec = new JTextField();
		numRec.setText("0");
		numRec.setEditable(false);
		numRec.setEnabled(false);
		numRec.setBackground(Color.WHITE);
		numRec.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		numRec.setHorizontalAlignment(SwingConstants.CENTER);
		numRec.setBounds(280, 12, 69, 27);
		panel_2.add(numRec);
		numRec.setColumns(10);
		BindComboan();
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
}
