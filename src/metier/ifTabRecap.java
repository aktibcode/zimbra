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

public class ifTabRecap extends JInternalFrame {
	private JTextField Recettes;
	private JTextField Depenses;
	Connection cnx =null;
	private JComboBox comboAn;
	private JCheckBox chckbxRecette;
	private JCheckBox chckbxDepenses;
	
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	Statement st = null;
	private JTable table;
	private JTextField textNum;
	/**
	 * Launch the application.
	 */
	
	/**
	 * Create the frame.
	 */
	public ifTabRecap() {
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
		
		/*
		 * 
		 * RECHERCHE D'UNE LIGNE DANS LE TABLEAU 
		 * 
		 * 04-08-2018
		 * 
		 * 
		 * */
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int ligne = table.getSelectedRow();
			    String id = table.getModel().getValueAt(ligne, 0).toString();
			    textNum.setText(id);
				
				
			}
		});
		
		
		/*
		 * 
		 * FIN RECHERCHE D'UNE LIGNE DANS LE TABLEAU 
		 * 
		 * 04-08-2018
		 * 
		 * 
		 * */
		scrollPane.setViewportView(table);
		
		JButton btnImp = new JButton("Imprimer :");
		btnImp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxDepenses.isSelected()){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
					if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/dep.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("annee",comboAn.getSelectedItem().toString());
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , cnx);
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);	
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						}
				}
				
				if(chckbxRecette.isSelected()){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce recu.", "Message", JOptionPane.YES_NO_OPTION);
					if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/rec.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("param",comboAn.getSelectedItem().toString());
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , cnx);
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);	
						} catch (JRException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						}
				}
			}
		});
		btnImp.setBounds(837, 11, 105, 32);
		panel.add(btnImp);
		
		JButton btnSupp = new JButton("Supprimer :");
		btnSupp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "DELETE FROM `recettes` WHERE id_recette=?";
				String sql2 = "DELETE FROM `depense` WHERE id_depense=?";
				if(!textNum.getText().equals("") &&  chckbxRecette.isSelected()){
					try{
						prepared = cnx.prepareStatement(sql);
						int id = Integer.parseInt(textNum.getText());
						prepared.setInt(1, id);
						prepared.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Vous avez suppimé la Ligne N°: "+id);
				//------------------------------updateTable();
						
						
					}catch(SQLException ex){
						
						JOptionPane.showMessageDialog(null, "Erreur "+ex);
					}
					recette();
					textNum.setText("0");
				}else if(!textNum.getText().equals("") &&  chckbxDepenses.isSelected()){
					try{
						prepared = cnx.prepareStatement(sql2);
						int id = Integer.parseInt(textNum.getText());
						prepared.setInt(1, id);
						prepared.executeUpdate();
						
						JOptionPane.showMessageDialog(null, "Vous avez suppimé la Ligne N°: "+id);
				//------------------------------updateTable();
						
						
					}catch(SQLException ex){
						
						JOptionPane.showMessageDialog(null, "Erreur "+ex);
					}
					depenses();
					textNum.setText("0");
					
				} else if(textNum.getText().equals("0")){
					
					JOptionPane.showMessageDialog(null, "Veillez choisir une ligne avant cette opération ");
				}/**/
			}
		});
		btnSupp.setBounds(837, 54, 105, 32);
		panel.add(btnSupp);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 14, 228, 85);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		comboAn = new JComboBox();
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(chckbxRecette.isSelected()){
				String sql = "select id_recette, Libelle, Nom_Elv, cls_nom, Montant, reste_a_paye, Date from recettes "
						+ "where anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'";
				try {
					
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException ex) {
					// TODO Auto-generated catch block 
					System.out.println("Erreur 1 "+ex);
				
					}
				remplTable();
				}else if(chckbxDepenses.isSelected()){
					String sql = "select id_depense, Libelle,Montant_Dep,  Date , autres_libelles, Ref_Facture from depense "
							+ "where anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'";
					try {
						
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException ex) {
						// TODO Auto-generated catch block 
						System.out.println("Erreur 1 "+ex);
					
						
						}
					remplTable2();
				}
				
			}
		});
		comboAn.setBounds(95, 11, 118, 29);
		panel_1.add(comboAn);
		
		chckbxRecette = new JCheckBox("Recettes :");
		chckbxRecette.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				
				recette();
			}
		});
		chckbxRecette.setBounds(10, 48, 90, 23);
		panel_1.add(chckbxRecette);
		
		chckbxDepenses = new JCheckBox("Depenses :");
		chckbxDepenses.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				depenses();
				
			}
		});
		chckbxDepenses.setBounds(123, 48, 90, 23);
		panel_1.add(chckbxDepenses);
		
		JLabel lblNewLabel = new JLabel("Ann\u00E9e :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 82, 30);
		panel_1.add(lblNewLabel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(248, 14, 579, 85);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Recettes :");
		lblNewLabel_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setBounds(10, 11, 75, 27);
		panel_2.add(lblNewLabel_1);
		
		Recettes = new JTextField();
		Recettes.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 13));
		Recettes.setEnabled(false);
		Recettes.setHorizontalAlignment(SwingConstants.CENTER);
		Recettes.setBounds(87, 12, 185, 27);
		panel_2.add(Recettes);
		Recettes.setColumns(10);
		
		Depenses = new JTextField();
		Depenses.setFont(new Font("Tahoma", Font.BOLD, 13));
		Depenses.setEnabled(false);
		Depenses.setHorizontalAlignment(SwingConstants.CENTER);
		Depenses.setColumns(10);
		Depenses.setBounds(359, 12, 185, 27);
		panel_2.add(Depenses);
		
		JLabel lblDepenses = new JLabel("Depenses :");
		lblDepenses.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepenses.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDepenses.setBounds(282, 11, 75, 27);
		panel_2.add(lblDepenses);
		
		JLabel lblRecette = new JLabel("N\u00B0 Recette :");
		lblRecette.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblRecette.setBounds(10, 47, 75, 27);
		panel_2.add(lblRecette);
		
		textNum = new JTextField();
		textNum.setText("0");
		textNum.setHorizontalAlignment(SwingConstants.CENTER);
		textNum.setEditable(false);
		textNum.setEnabled(false);
		textNum.setBounds(87, 49, 86, 25);
		panel_2.add(textNum);
		textNum.setColumns(10);
		BindComboan();
	}
	
	
	
	
	public void remplTable(){
		String sql = "select sum(Montant) from recettes where anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			if(resultat.next()){
				
				Recettes.setText(String.valueOf(resultat.getInt("Sum(Montant)")));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
	}
	
	public void remplTable2(){
		String sql = "select sum(Montant_Dep) from depense where anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			if(resultat.next()){
				
				Depenses.setText(String.valueOf(resultat.getInt("Sum(Montant_Dep)")));
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
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
	
	public void recette(){
		if(chckbxRecette.isSelected()){
			chckbxDepenses.setEnabled(false);
			String sql = "select id_recette, Libelle, Nom_Elv, cls_nom, Montant, reste_a_paye, Date from recettes "
					+ "where anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'";
			try {
				
				prepared = cnx.prepareStatement(sql);
				resultat = prepared.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException ex) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+ex);
			
				
				}
			remplTable();
			
		}else{
			chckbxDepenses.setEnabled(true);
			
			String sql = "select id_recette, Libelle, Nom_Elv, cls_nom, Montant, reste_a_paye, Date from recettes "
					+ "where anne_scolaire =  '0'";
			try {
				
				prepared = cnx.prepareStatement(sql);
				resultat = prepared.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException ex) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+ex);
			
				}
			
				Recettes.setText("");
			
		}
	}
	
	public void depenses(){
		if(chckbxDepenses.isSelected()){
			chckbxRecette.setEnabled(false);
			String sql = "select id_depense, Libelle,Montant_Dep,  Date , autres_libelles , Ref_Facture from depense "
					+ "where anne_scolaire ='"+comboAn.getSelectedItem().toString()+"'";
			try {
				
				prepared = cnx.prepareStatement(sql);
				resultat = prepared.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException ex) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+ex);
			
				}
			remplTable2();
		}else{
			chckbxRecette.setEnabled(true);
			String sql = "select id_depense, Libelle,Montant_Dep,  Date , autres_libelles from depense "
					+ "where anne_scolaire ='0'";
			try {
				
				prepared = cnx.prepareStatement(sql);
				resultat = prepared.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException ex) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+ex);
			
				}
			Depenses.setText("");
		}
	}
}
