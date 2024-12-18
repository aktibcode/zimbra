package metier;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;

import java.awt.Font;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.JTextComponent;
import javax.swing.JButton;

import connec.ConnexionMySql;
import connec.Donne;
import entite.MethodeRecetteDepense;
import entite.eleveEL;
import entite.eleveELE;
import entite.enseignantEL;
import entite.matParClas;
import entite.matiereEL;
import entite.matiereELType;
import entite.noteEL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.ResultSetMetaData;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;


@SuppressWarnings("unused")
public class ifNoteElevesOrientation extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	public JComboBox getComboCours() {
		return comboCours;
	}



	public void setComboCours(JComboBox comboCours) {
		this.comboCours = comboCours;
	}
	
	
	
	
	public JTextField getTextCat() {
		return textCat;
	}



	public void setTextCat(JTextField textCat) {
		this.textCat = textCat;
	}
	
	//private JTextField noteExamen;
	@SuppressWarnings("rawtypes")
	private JComboBox comboClasse;
	@SuppressWarnings("rawtypes")
	private JComboBox comboEleve;
	@SuppressWarnings("rawtypes")
	private JComboBox comboNumero;
	private JLabel lblNumeroEleve;
	private JLabel lblChoisissezUnEleve;
	private JLabel lblChoix;
	private JComboBox comboCours;
	private JTable table;
	private String no1;
	private String no2;
	private String notEx;
	private String idelv;
	private int elvID;
	private double moy_Gen;
	private double moyCls;
	private double noEx;
    private String idnote;
	private JPanel panel_1;
	private JLabel lblSemestre;
	private JComboBox comboSemestre;
	private JComboBox comboAn;
	private JLabel label_1;
	private JLabel lblNewLabel;
	
	
	


	public JComboBox getComboClasse() {
		return comboClasse;
	}



	public void setComboClasse(JComboBox comboClasse) {
		this.comboClasse = comboClasse;
	}



	public JComboBox getComboEleve() {
		return comboEleve;
	}



	public void setComboEleve(JComboBox comboEleve) {
		this.comboEleve = comboEleve;
	}



	public JComboBox getComboNumero() {
		return comboNumero;
	}



	public void setComboNumero(JComboBox comboNumero) {
		this.comboNumero = comboNumero;
	}



	


	public JComboBox getComboAn() {
		return comboAn;
	}



	public void setComboAn(JComboBox comboAn) {
		this.comboAn = comboAn;
	}
	private JTextField coefClasse;
	private JButton btnEditionRelevDe;
	private JTextField tx;
	private JTextField textIDEns;
	private JLabel idCls;
	private JTextField textCat;
	private JTextComponent textEffectif;
	private JTextField textMax;
	private JTextField textMin;
	private JLabel label0;
	private JLabel label;
	private JLabel label_3;
	private JLabel label_2;
	private JLabel label_5;
	private JLabel label_4;
	private ResultSet rs2;
	private JTextField textFieldMax;
	private JTextField textFieldMin;
	private JTextField textFieldEff;
	private JButton btnImpressionConseilDorientation;
	
	
	
	public JTextField getTx() {
		return tx;
	}



	public void setTx(JTextField tx) {
		this.tx = tx;
	}



	public JTextField getTextIDEns() {
		return textIDEns;
	}



	public void setTextIDEns(JTextField textIDEns) {
		this.textIDEns = textIDEns;
	}



	public ifNoteElevesOrientation() {
		setClosable(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				init();
			}
		});
		//setClosed(true);
		setTitle("Gestion des Notes ");
		setBounds(300, 128, 999, 540);
		setMaximizable(false);
		setResizable(false);
	    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		setVisible(true);
		cnx = ConnexionMySql.ConnexiomBd();
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Infos relative \u00E0 l'Eleve", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(10, 11, 963, 93);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		
		lblChoix = new JLabel("Choisissez un Classe:");
		lblChoix.setBounds(148, 21, 155, 27);
		panel.add(lblChoix);
		lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoix.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		comboClasse = new JComboBox();
		comboClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboClasse.setBounds(148, 55, 155, 27);
		panel.add(comboClasse);
		
		comboEleve = new JComboBox();
		comboEleve.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboEleve.setToolTipText("");
		comboEleve.setBounds(344, 55, 300, 27);
		panel.add(comboEleve);
		
		comboNumero = new JComboBox();
		comboNumero.setBounds(654, 55, 66, 27);
		panel.add(comboNumero);
		comboNumero.setModel(new DefaultComboBoxModel(new String[] {"0"}));
		
		lblChoisissezUnEleve = new JLabel("Choisissez un Eleve:");
		lblChoisissezUnEleve.setBounds(343, 21, 301, 27);
		panel.add(lblChoisissezUnEleve);
		lblChoisissezUnEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoisissezUnEleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		lblNumeroEleve = new JLabel("N\u00B0 Eleve :");
		lblNumeroEleve.setBounds(653, 21, 67, 27);
		panel.add(lblNumeroEleve);
		lblNumeroEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroEleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		lblSemestre = new JLabel("Semestre :");
		lblSemestre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemestre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblSemestre.setBounds(727, 54, 80, 27);
		panel.add(lblSemestre);
		
		comboSemestre = new JComboBox();
		comboSemestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
					
				
				if(!comboEleve.getSelectedItem().equals("Choix") || comboEleve.getSelectedItem().equals("")){
					if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
						Remplissage();
					}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
						Remplissage2();
					}else {
						Remplissage3();
					}
				
				}else{
					JOptionPane.showMessageDialog(null, "Veillez Selectionnez un Eleve avant toute opération");
				}
				
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Veillez Selectionnez une Classe pour Commencer les traitements ");
				}
					
			init();
			MaxMoy();
			Effectif();
				
			}
		});
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboSemestre.setBounds(806, 55, 147, 27);
		panel.add(comboSemestre);
		
		comboAn = new JComboBox();
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comboClasse.setSelectedItem("Choix");;
				Donne mDon = new Donne();
				ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(textIDEns.getText().toString()));
				
				for(int i = 0; i < list3.size(); i++ ){
					//comboClasse.addItem(list3.get(i).getNomCls());
					}
					
					for (int j = 0; j < list3.size(); j++) {
						//comboCours.addItem(list3.get(j).getEnsCours());
					}
					
					
				
				
				//f.BindClasse();
				//BindComboClasse();
					/*Remplissage();
					Remplissage2();
					Remplissage3();*/
			}
		});
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.setBounds(11, 55, 122, 27);
		panel.add(comboAn);
		
		label_1 = new JLabel("Ann\u00E9e:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		label_1.setBounds(10, 21, 122, 27);
		panel.add(label_1);
		
		lblNewLabel = new JLabel("0");
		lblNewLabel.setBounds(868, 55, -42, 27);
		panel.add(lblNewLabel);
		
		coefClasse = new JTextField();
		coefClasse.setEnabled(false);
		coefClasse.setEditable(false);
		coefClasse.setBounds(798, 105, 60, -23);
		panel.add(coefClasse);
		coefClasse.setColumns(10);
		
		JLabel lblCoef = new JLabel("Coef :");
		lblCoef.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoef.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblCoef.setBounds(718, 105, 80, -23);
		panel.add(lblCoef);
		
		tx = new JTextField();
		tx.setEnabled(false);
		tx.setEditable(false);
		tx.setBounds(922, 105, 31, -23);
		panel.add(tx);
		tx.setColumns(10);
		
		idCls = new JLabel("");
		idCls.setBounds(309, 55, 31, 27);
		panel.add(idCls);
		comboEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboNumero.removeAllItems();
				Donne mk = new Donne();
				ArrayList<eleveELE> list2 = mk.getData32(comboEleve.getSelectedItem().toString(),
						comboClasse.getSelectedItem().toString(), comboAn.getSelectedItem().toString());
				for(int j = 0; j < list2.size(); j++ ){
					comboNumero.addItem(list2.get(j).getElv_id());
					String id = comboNumero.getSelectedItem().toString();
					System.out.println("Requete executer ");
					
				//updateTable2();
					
					if (comboSemestre.getSelectedItem().equals("1er Trimestre")) {
						Remplissage();
					}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
						Remplissage2();
					}else {
						Remplissage3();
					}
				}
				/*Remplissage();
				Remplissage2();
				Remplissage3();*/	
				init();
				Effectif();
				
			}
		});
		comboClasse.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				comboEleve.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
				comboCours.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));	
				//comboNumero.removeAllItems();
				//comboEleve.setSelectedItem("");
				if(comboClasse.getSelectedItem().equals(false)){
					System.out.println("Aucune");
					comboEleve.removeAllItems();
					comboCours.removeAllItems();
					
				}else{
					//ConnexionMySql mk = new ConnexionMySql();
					MethodeRecetteDepense mk = new MethodeRecetteDepense();
					Donne mkl = new Donne();
					FenMenu f = new FenMenu();
					//String t = tx.gett;
					//System.out.println("Le num est"+ t);
					Donne mDon = new Donne();
					ArrayList<eleveEL> list = mk.getData2(comboClasse.getSelectedItem().toString(), comboAn.getSelectedItem().toString());
					ArrayList<matiereELType> list3 = mkl.getDataType();
					ArrayList<matParClas> list2 = mDon.getDataM(comboClasse.getSelectedItem().toString(), 
							Integer.parseInt(textIDEns.getText().toString()), comboAn.getSelectedItem().toString());
					//ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(t));
					for(int i = 0; i < list.size(); i++ ){
						comboEleve.addItem(list.get(i).getNomEtprenom());
						
					}
					
					for(int i = 0; i < list3.size(); i++ ){
						//comboCours.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
						comboCours.addItem(list3.get(i).getTitre());
						comboCours.setSelectedItem(list3.get(i).getTitre());
						String com = comboCours.getSelectedObjects().toString();
						comboCours.setSelectedItem(com);
						
					}
					
					/*for(int i = 0; i < list2.size(); i++ ){
						comboCours.addItem(list2.get(i).getCrTitre());
						
					}*/
					
					String sql = "SELECT coef_classe FROM classe WHERE cls_nom = '"+comboClasse.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						
						if(resultat.next()){
							coefClasse.setText(String.valueOf(resultat.getInt("coef_classe")));
							
						}
						
						
						
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
				/*Remplissage();
				Remplissage2();
				Remplissage3()*/;//updateTable2();
			init();
			Effectif();
			//
			//BindComboCour();
			}
			
			
		});
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Infos Relatives \u00E0 la Mati\u00E8re", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 115, 963, 108);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblChoisissezMatire = new JLabel("Choisissez Types Mati\u00E8res :");
		lblChoisissezMatire.setBounds(10, 27, 199, 27);
		panel_1.add(lblChoisissezMatire);
		lblChoisissezMatire.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoisissezMatire.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		comboCours = new JComboBox();
		comboCours.setBounds(10, 56, 199, 27);
		panel_1.add(comboCours);
		comboCours.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		
		textFieldMax = new JTextField();
		textFieldMax.setEnabled(false);
		textFieldMax.setEditable(false);
		textFieldMax.setBounds(242, 70, 156, -19);
		panel_1.add(textFieldMax);
		textFieldMax.setColumns(10);
		
		textFieldMin = new JTextField();
		textFieldMin.setEnabled(false);
		textFieldMin.setEditable(false);
		textFieldMin.setColumns(10);
		textFieldMin.setBounds(429, 79, 156, -28);
		panel_1.add(textFieldMin);
		
		textFieldEff = new JTextField();
		textFieldEff.setEditable(false);
		textFieldEff.setEnabled(false);
		textFieldEff.setColumns(10);
		textFieldEff.setBounds(618, 79, 156, -28);
		panel_1.add(textFieldEff);
		comboCours.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
			
				Donne mk = new Donne();
				ArrayList<matiereELType> list2 = mk.getDataType();
				for(int j = 0; j < list2.size(); j++ ){
					
					
				}
				
				
				if (comboSemestre.getSelectedItem().equals("1er Trimestre")) {
					Remplissage();
				}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
					Remplissage2();
				}else {
					Remplissage3();
				}
				init();
				MaxMoy();
				Effectif();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 241, 963, 38);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			private JLabel num;
			
			@Override
			public void mouseClicked(MouseEvent arg0) {
				//String sd = "SET @p := '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"'";
				//System.out.println(sd);
				if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					lblNewLabel.setText(id);
					String sql = "select * from note where note_id ='"+id+"' and matiere = '"+comboCours.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
	                            
							//matiere, coef, 				
						if(resultat.next()){
						lblNewLabel.setText((String.valueOf(resultat.getInt("note_id"))));
						//comboCours.setSelectedItem(resultat.getString("matiere"));
						//comboCoef.setSelectedItem(resultat.getString("coef"));
						
						}else {
							
							
						}
						System.out.println("Click effectué et num est "+table.getSelectedRow() + "  "+id );
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						//System.out.println("Erreur ici "+e.getMessage());
						e.printStackTrace();
					}
					
				}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					lblNewLabel.setText(id);
					String sql = "select * from note2 where note_id ='"+id+"' and matiere = '"+comboCours.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
	                            
							//matiere, coef, 				
						if(resultat.next()){
						lblNewLabel.setText((String.valueOf(resultat.getInt("note_id"))));
						//comboCours.setSelectedItem(resultat.getString("matiere"));
						//comboCoef.setSelectedItem(resultat.getString("coef"));
						
						}else {
							System.out.println("Aucun envoie vers les textfields");
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre")){
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					
					String sql = "select * from note3 where note_id ='"+id+"' and matiere = '"+comboCours.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
	                            
							//matiere, coef, 				
						if(resultat.next()){
							lblNewLabel.setText((String.valueOf(resultat.getInt("note_id"))));
						//comboCours.setSelectedItem(resultat.getString("matiere"));
						//comboCoef.setSelectedItem(resultat.getString("coef"));
						
						}else {
							System.out.println("Aucun envoie vers les textfields");
						
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
					}
				}
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 451, 963, 48);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		btnEditionRelevDe = new JButton("Impression Bulletin d'Orientation :");
		btnEditionRelevDe.addActionListener(new ActionListener() {
			private JTextComponent textFieldNum;
			private JTextComponent textMax;
			private JTextComponent textMin;
			private JTextComponent textEffectif;

			public void actionPerformed(ActionEvent arg0) {
				if(!comboAn.getSelectedItem().equals("Choix") && !comboClasse.getSelectedItem().equals("Choix") 
						&& !comboCours.getSelectedItem().equals("Choix")){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce relevé.", "Message", JOptionPane.YES_NO_OPTION);
					if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						ConnexionMySql p = new ConnexionMySql();
						p.ConnexiomBd();
						if(comboSemestre.getSelectedItem().equals("3ème Trimestre") && !comboClasse.getSelectedItem().equals("2nd/U")){
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP2/Blan32TTOR.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("pID", Integer.parseInt(comboNumero.getSelectedItem().toString()));
								vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
								vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
								//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
								vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
								vNomEleve.put("pMax", Double.parseDouble(textFieldMax.getText().toString()));
								vNomEleve.put("pMin", Double.parseDouble(textFieldMin.getText().toString()));
								vNomEleve.put("pEff", Integer.parseInt(textFieldEff.getText().toString()));
								
								
								///////// Moyennes Spécifiques ///////////////
								
								
								vNomEleve.put("p1", Double.parseDouble(label0.getText().toString()));
								vNomEleve.put("p2", Double.parseDouble(label.getText().toString()));
								vNomEleve.put("p3", Double.parseDouble(label_3.getText().toString()));
								//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
								vNomEleve.put("p4", Double.parseDouble(label_2.getText().toString()));
								vNomEleve.put("p5", Double.parseDouble(label_5.getText().toString()));
								vNomEleve.put("p6", Double.parseDouble(label_4.getText().toString()));
								
								
								
								
								/////////  Fin Moyenne Spécifiques ////////////////
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre") && comboClasse.getSelectedItem().equals("2nd/U")){
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP2/Blan32TTOR3eme.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("pID", Integer.parseInt(comboNumero.getSelectedItem().toString()));
								vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
								vNomEleve.put("pAnSco", comboAn.getSelectedItem().toString());
								//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
								vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
								vNomEleve.put("pMax", Double.parseDouble(textFieldMax.getText().toString()));
								vNomEleve.put("pMin", Double.parseDouble(textFieldMin.getText().toString()));
								vNomEleve.put("pEff", Integer.parseInt(textFieldEff.getText().toString()));
								
								
								///////// Moyennes Spécifiques ///////////////
								
								
								vNomEleve.put("p1", Double.parseDouble(label0.getText().toString()));
								vNomEleve.put("p2", Double.parseDouble(label.getText().toString()));
								vNomEleve.put("p3", Double.parseDouble(label_3.getText().toString()));
								//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
								vNomEleve.put("p4", Double.parseDouble(label_2.getText().toString()));
								vNomEleve.put("p5", Double.parseDouble(label_5.getText().toString()));
								vNomEleve.put("p6", Double.parseDouble(label_4.getText().toString()));
								
								
								
								
								/////////  Fin Moyenne Spécifiques ////////////////
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else {
							JOptionPane.showMessageDialog(null, "PAS DE NOTE POUR CETTE MATIERE");
						}
						
						
						}
				}else{
					JOptionPane.showMessageDialog(null, "Choisir une Classe et une Matière pour imprimer le relévé de Notes");
				}
				
			}
		});
		
		btnEditionRelevDe.setBounds(94, 14, 233, 23);
		panel_2.add(btnEditionRelevDe);
		
		
		btnImpressionConseilDorientation = new JButton("Impression Conseil d'Orientation :");
		btnImpressionConseilDorientation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboAn.getSelectedItem().equals("Choix") && !comboClasse.getSelectedItem().equals("Choix") ){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce relevé.", "Message", JOptionPane.YES_NO_OPTION);
					if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						ConnexionMySql p = new ConnexionMySql();
						p.ConnexiomBd();
						
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/dbIMP2/conseilOrientation.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("pAn", comboAn.getSelectedItem().toString());
								//vNomEleve.put("sCoef", Integer.parseInt(textFieldNum.getText()));
								vNomEleve.put("pCls", comboClasse.getSelectedItem().toString());
								
								
								/////////  Fin Moyenne Spécifiques ////////////////
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve ,  p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						/*if(comboSemestre.getSelectedItem().equals("3ème Trimestre") && !comboClasse.getSelectedItem().equals("2nd/U")){}else {
							
						}*/
						
						
						}
				}
				
				
			}
		});
		btnImpressionConseilDorientation.setBounds(629, 14, 233, 23);
		panel_2.add(btnImpressionConseilDorientation);
		
		JLabel idEnsC = new JLabel("ID de Monsieur");
		idEnsC.setHorizontalAlignment(SwingConstants.CENTER);
		idEnsC.setFont(new Font("Tahoma", Font.BOLD, 13));
		idEnsC.setBounds(619, 42, 104, -11);
		panel_2.add(idEnsC);
		
		textIDEns = new JTextField();
		textIDEns.setEnabled(false);
		textIDEns.setEditable(false);
		textIDEns.setBounds(733, 49, 65, -17);
		panel_2.add(textIDEns);
		textIDEns.setColumns(10);
		
		JLabel lblCat = new JLabel("CAT :");
		lblCat.setHorizontalAlignment(SwingConstants.CENTER);
		lblCat.setFont(new Font("Tahoma", Font.BOLD, 13));
		lblCat.setBounds(803, 42, 54, -11);
		panel_2.add(lblCat);
		
		textCat = new JTextField();
		textCat.setEnabled(false);
		textCat.setEditable(false);
		textCat.setColumns(10);
		textCat.setBounds(855, 42, 98, -11);
		panel_2.add(textCat);
		
		
		
		JLabel lblMoyerTrimestre = new JLabel("Moy. 1er Trimestre :");
		lblMoyerTrimestre.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoyerTrimestre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblMoyerTrimestre.setBounds(201, 302, 164, 27);
		getContentPane().add(lblMoyerTrimestre);
		
		JLabel lblMoyerTrimestre_1 = new JLabel("Moy. 2\u00E8me Trimestre :");
		lblMoyerTrimestre_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoyerTrimestre_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblMoyerTrimestre_1.setBounds(353, 302, 164, 27);
		getContentPane().add(lblMoyerTrimestre_1);
		
		JLabel lblMoymeTrimestre = new JLabel("Moy. 3\u00E8me Trimestre :");
		lblMoymeTrimestre.setHorizontalAlignment(SwingConstants.CENTER);
		lblMoymeTrimestre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblMoymeTrimestre.setBounds(511, 302, 164, 27);
		getContentPane().add(lblMoymeTrimestre);
		
		JLabel lblMatScientifique = new JLabel("Mat Scientifique :");
		lblMatScientifique.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatScientifique.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblMatScientifique.setBounds(94, 340, 144, 27);
		getContentPane().add(lblMatScientifique);
		
		JLabel lblMatLitteraire = new JLabel("Mat Litteraire :");
		lblMatLitteraire.setHorizontalAlignment(SwingConstants.CENTER);
		lblMatLitteraire.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblMatLitteraire.setBounds(94, 396, 144, 27);
		getContentPane().add(lblMatLitteraire);
		
		label0 = new JLabel("");
		label0.setBackground(Color.ORANGE);
		label0.setForeground(Color.BLACK);
		label0.setHorizontalAlignment(SwingConstants.CENTER);
		label0.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label0.setBounds(221, 340, 144, 27);
		getContentPane().add(label0);
		
		label = new JLabel("");
		label.setBackground(Color.PINK);
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label.setBounds(221, 397, 144, 27);
		getContentPane().add(label);
		
		label_2 = new JLabel("");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label_2.setBounds(363, 396, 139, 27);
		getContentPane().add(label_2);
		
		label_3 = new JLabel("");
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label_3.setBounds(363, 339, 139, 27);
		getContentPane().add(label_3);
		
		label_4 = new JLabel("");
		label_4.setHorizontalAlignment(SwingConstants.CENTER);
		label_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label_4.setBounds(536, 396, 139, 27);
		getContentPane().add(label_4);
		
		label_5 = new JLabel("");
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		label_5.setBounds(536, 339, 139, 27);
		getContentPane().add(label_5);
		//BindCombo();
		//BindComboCour();
		BindCombo2();
		BindComboan();
		//BindComboCour();
		//updateTable2();
		init();
		MaxMoy();
		Effectif();
		//gest();
	}
	
	private boolean isNumeric(){
	
		
		try {
			
		}
		catch (NumberFormatException e) {
			JOptionPane.showMessageDialog(null, "Verifier les Choix puis continuer !!!");
			System.out.println("Entrée non numerique");
			return false;
		
		}
		System.out.println("Entrée cccccccccc");
		return true;
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
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////////
	public void BindClasse(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT clsEns FROM enseignantel where idEns = '"+Integer.parseInt(textIDEns.getText().toString())+"'");
		    while(rs.next()){
		    	comboClasse.addItem(rs.getString(1));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	
	public void BindComboCour(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT cr_titre FROM cours ");
		    while(rs.next()){
		    	//comboCours.addItem(rs.getString(1));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	//////////////////////////////////////////////////////////////////////////////////////
	
	
	public void BindComboClasse(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT `cls_nom` FROM classe where coef_classe != '0' ");
		    while(rs.next()){
		    	comboClasse.addItem(rs.getString(1));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	
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
	
	public void BindComboan(){
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
	
	public void init(){
				
	}
	
	
	
	
	public void Remplissage(){
		System.out.println(Integer.parseInt(comboNumero.getSelectedItem().toString()));
		String sql = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout.classe, sum(Moy_coef)/sum(coef) as Moy "
		+ " from `tout`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
				+  "and titre='"+comboCours.getSelectedItem().toString()+"' and type_matiere.id_ty = cours.id_ty "
				+ " and tout.classe = '"+comboClasse.getSelectedItem().toString()+"'"
				+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere"
						
				;
			
		try {
			
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			} catch (SQLException e) {
			// TODO Auto-generated catch block 
			System.out.println("Erreur 1 "+e);
		
			}
	}
	
	
	public void Remplissage2(){
		
		System.out.println(Integer.parseInt(comboNumero.getSelectedItem().toString()));
		String sql = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout2.classe, sum(Moy_coef)/sum(coef) as Moy "
				+ " from `tout2`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
						+  "and titre='"+comboCours.getSelectedItem().toString()+"' and type_matiere.id_ty = cours.id_ty "
						+ " and tout2.classe = '"+comboClasse.getSelectedItem().toString()+"'"
						+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere"
								
						;
			
		try {
			
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			} catch (SQLException e) {
			// TODO Auto-generated catch block 
			System.out.println("Erreur 1 "+e);
		
			}
	}
	
	public void Remplissage3(){
		System.out.println(Integer.parseInt(comboNumero.getSelectedItem().toString()));
		String sql = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout3.classe, sum(Moy_coef)/sum(coef) as Moy "
				+ " from `tout3`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
						+  "and titre='"+comboCours.getSelectedItem().toString()+"' and type_matiere.id_ty = cours.id_ty "
						+ " and tout3.classe = '"+comboClasse.getSelectedItem().toString()+"'"
						+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere";
		
		

		try {
			
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			} catch (SQLException e) {
			// TODO Auto-generated catch block 
			System.out.println("Erreur 1 "+e);
		
			}
		
		
			////////////////////////////// 1ER TRIMESTRE  //////////////////////////////////					
		try {				
			
		String sql2 = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout.classe, sum(Moy_coef)/sum(coef) as Moy "
		+ " from `tout`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
				+  "and titre='Matières Littéraires' and type_matiere.id_ty = cours.id_ty "
				+ " and tout.classe = '"+comboClasse.getSelectedItem().toString()+"'"
				+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere";
		
		
		String sql1 = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout.classe, sum(Moy_coef)/sum(coef) as Moy "
				+ " from `tout`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
						+  "and titre='Matières Scientifiques' and type_matiere.id_ty = cours.id_ty "
						+ " and tout.classe = '"+comboClasse.getSelectedItem().toString()+"'"
						+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere"
								
						;
		
			st = cnx.createStatement();
			rs = st.executeQuery(sql1);
			
		    while(rs.next()){
		    label0.setText(rs.getString(9));
		    
		    }
		    
		    rs2 = st.executeQuery(sql2);
		    while(rs2.next()){
			   
			    label.setText(rs2.getString(9));
			}
		
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
			////////////////////////////// 2EME TRIMESTRE  //////////////////////////////////
		try {				
			
			String sql2 = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout2.classe, sum(Moy_coef)/sum(coef) as Moy "
			+ " from `tout2`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
					+  "and titre='Matières Littéraires' and type_matiere.id_ty = cours.id_ty "
					+ " and tout2.classe = '"+comboClasse.getSelectedItem().toString()+"'"
					+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere";
			
			
			String sql1 = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout2.classe, sum(Moy_coef)/sum(coef) as Moy "
					+ " from `tout2`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
							+  "and titre='Matières Scientifiques' and type_matiere.id_ty = cours.id_ty "
							+ " and tout2.classe = '"+comboClasse.getSelectedItem().toString()+"'"
							+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere"
									
							;
			
				st = cnx.createStatement();
				rs = st.executeQuery(sql1);
				
			    while(rs.next()){
			    label_3.setText(rs.getString(9));
			    
			    }
			    
			    rs2 = st.executeQuery(sql2);
			    while(rs2.next()){
				   
				    label_2.setText(rs2.getString(9));
				}
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
				//////////////////////////////  3EME TRIMESTRE  //////////////////////////////////
			
			try {				
			
			String sql2 = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout3.classe, sum(Moy_coef)/sum(coef) as Moy "
			+ " from `tout3`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
					+  "and titre='Matières Littéraires' and type_matiere.id_ty = cours.id_ty "
					+ " and tout3.classe = '"+comboClasse.getSelectedItem().toString()+"'"
					+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere";
			
			
			String sql1 = "select distinct `id_elv`,`nom_et_prenom`, `coef`,`moy_Gen`, `Moy_Coef`, cours.id_ty , type_matiere.id_ty , tout3.classe, sum(Moy_coef)/sum(coef) as Moy "
					+ " from `tout3`, cours, type_matiere where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
							+  "and titre='Matières Scientifiques' and type_matiere.id_ty = cours.id_ty "
							+ " and tout3.classe = '"+comboClasse.getSelectedItem().toString()+"'"
							+ " and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' and cr_titre = matiere"
									
							;
			
				st = cnx.createStatement();
				rs = st.executeQuery(sql1);
				
			    while(rs.next()){
			    label_5.setText(rs.getString(9));
			    
			    }
			    
			    rs2 = st.executeQuery(sql2);
			    while(rs2.next()){
				   
				    label_4.setText(rs2.getString(9));
				}
			
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		
		
		
	}
	
	public boolean gest(){
		BindComboCour();
		if(textCat.getText().toString().equals("gestionnaire")){
			
		}
		
		return true ;
	}
	
	public void MaxMoy(){
	
	if(comboSemestre.getSelectedItem().toString().equals("3ème Trimestre")){
		try {

			st = cnx.createStatement();
			rs = st.executeQuery("SELECT MAX(MOY), MIN(MOY)FROM moyeleve2 WHERE Classe= '"+comboClasse.getSelectedItem()+"' "
					+ "and Annee='"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");
			while(rs.next()){
				textFieldMax.setText(String.valueOf(rs.getDouble(1)));
				textFieldMin.setText(String.valueOf(rs.getDouble(2)));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
					
				textFieldEff.setText(String.valueOf(resultat.getInt("count(elv_id)")));
				//lblCy.setText(String.valueOf(resultat.getInt("id_cycle")));
		
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
}
