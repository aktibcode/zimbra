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
import javax.swing.JButton;

import connec.ConnexionMySql;
import connec.Donne;
import entite.MethodeRecetteDepense;
import entite.eleveEL;
import entite.eleveELE;
import entite.enseignantEL;
import entite.matParClas;
import entite.matiereEL;
import entite.noteEL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.ResultSetMetaData;

import java.awt.Color;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.UIManager;


@SuppressWarnings("unused")
public class ifNoteElevesProfPrin extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	private JTextField note1;
	private JTextField note2;
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




	private JTextField noteExamen;
	
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
	private JLabel lblNote;
	private JLabel lblNote_1;
	private JLabel lblNoteExamen;
	private JComboBox comboCours;
	private JTable table;
	private JLabel lblCoefficientMatire;
	private JComboBox comboCoef;
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
	private JTextField DS2;
	private JTextField DS1;
	private JLabel lblSemestre;
	private JComboBox comboSemestre;
	private JComboBox comboAn;
	private JLabel label_1;
	private JLabel lblNewLabel;
	
	
	
	public JButton getSupprimer() {
		return Supprimer;
	}



	public void setSupprimer(JButton supprimer) {
		Supprimer = supprimer;
	}



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



	public JComboBox getComboCoef() {
		return comboCoef;
	}



	public void setComboCoef(JComboBox comboCoef) {
		this.comboCoef = comboCoef;
	}



	public JComboBox getComboAn() {
		return comboAn;
	}



	public void setComboAn(JComboBox comboAn) {
		this.comboAn = comboAn;
	}
	private JTextField coefClasse;
	private JButton Modifier;
	private JButton btnEditionRelevDe;
	private JButton Supprimer;
	private JTextField tx;
	private JTextField textIDEns;
	private JLabel idCls;
	private JTextField textCat;
	private JTable tablemoy;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textTotalEleve;
	private JTextField textTotalEleveCompo;
	private JLabel lblEffectifTotalDes_1;
	private JButton btnValider;
	private JLabel lbNum;
	
	
	
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



	public ifNoteElevesProfPrin() {
		setIconifiable(true);
		///etClosed(true);
		setClosable(true);
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				init();
			}
		});
		//setClosed(true);
		setTitle("Gestion des Notes ");
		setBounds(300, 30, 999, 681);
		setMaximizable(true);
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
		comboNumero.setBounds(654, 55, 56, 27);
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
		lblSemestre.setBounds(718, 10, 80, 27);
		panel.add(lblSemestre);
		
		comboSemestre = new JComboBox();
		comboSemestre.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try {
				
				if(!comboEleve.getSelectedItem().equals("Choix") || comboEleve.getSelectedItem().equals("")){
					if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
						Remplissage();
						RemplissageMoy();
						nb();
						//desactiveValidation();
					}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
						Remplissage2();
						RemplissageMoy();
						nb();
						//desactiveValidation();
					}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre")){
						Remplissage3();
						RemplissageMoy();
						nb();
						//desactiveValidation();
					}
				}else{
					JOptionPane.showMessageDialog(null, "Veillez Selectionnez un Eleve avant toute opération");
				}
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Veillez Selectionnez une Classe pour Commencer les traitements ");
				}
			desactiveValidation();
			init();
			
				
			}
		});
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboSemestre.setBounds(798, 11, 155, 27);
		panel.add(comboSemestre);
		
		comboAn = new JComboBox();
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comboClasse.setSelectedItem("Choix");;
				Donne mDon = new Donne();
				ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(textIDEns.getText().toString()));
				/**/
				for(int i = 0; i < list3.size(); i++ ){
					//comboClasse.addItem(list3.get(i).getNomCls());
					}
					
					for (int j = 0; j < list3.size(); j++) {
						//comboCours.addItem(list3.get(j).getEnsCours());
					}
					
					Remplissage();
					Remplissage2();
					Remplissage3();
					RemplissageMoy();
				desactiveValidation();
				//f.BindClasse();
				//BindComboClasse();
				BindComboClasse();
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
		lblNewLabel.setBounds(868, 89, 56, -7);
		panel.add(lblNewLabel);
		
		coefClasse = new JTextField();
		coefClasse.setEnabled(false);
		coefClasse.setEditable(false);
		coefClasse.setBounds(798, 55, 60, 27);
		panel.add(coefClasse);
		coefClasse.setColumns(10);
		
		JLabel lblCoef = new JLabel("Coef :");
		lblCoef.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoef.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblCoef.setBounds(748, 55, 50, 27);
		panel.add(lblCoef);
		
		tx = new JTextField();
		tx.setEnabled(false);
		tx.setEditable(false);
		tx.setBounds(922, 58, 31, 24);
		panel.add(tx);
		tx.setColumns(10);
		
		idCls = new JLabel("");
		idCls.setBounds(309, 55, 31, 27);
		panel.add(idCls);
		
		lbNum = new JLabel("");
		lbNum.setHorizontalAlignment(SwingConstants.CENTER);
		lbNum.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lbNum.setBounds(720, 95, 50, -13);
		panel.add(lbNum);
		comboEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboNumero.removeAllItems();
				lbNum.setText("");
				Donne mk = new Donne();
				ArrayList<eleveELE> list2 = mk.getData32(comboEleve.getSelectedItem().toString(),
						comboClasse.getSelectedItem().toString(), comboAn.getSelectedItem().toString());
				for(int j = 0; j < list2.size(); j++ ){
					comboNumero.addItem(list2.get(j).getElv_id());
					lbNum.setText(String.valueOf(list2.get(j).getElv_id()));
					String id = comboNumero.getSelectedItem().toString();
					System.out.println("Requete executer ");
					
				//updateTable2();
					
					if (comboSemestre.getSelectedItem().equals("1er Trimestre")) {
						Remplissage();
						RemplissageMoy();
						//desactiveValidation();
					}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
						Remplissage2();
						RemplissageMoy();
						//desactiveValidation();
					}else {
						Remplissage3();
						RemplissageMoy();
						//desactiveValidation();
					}
				}
				RemplissageMoy();
				desactiveValidation();
				init();
				
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
					FenMenu f = new FenMenu();
					//String t = tx.gett;
					//System.out.println("Le num est"+ t);
					Donne mDon = new Donne();
					ArrayList<eleveEL> list = mk.getData2(comboClasse.getSelectedItem().toString(), comboAn.getSelectedItem().toString());
					ArrayList<matParClas> list2 = mDon.getDataM(comboClasse.getSelectedItem().toString(), 
							Integer.parseInt(textIDEns.getText().toString()), comboAn.getSelectedItem().toString());
					//ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(t));
					for(int i = 0; i < list.size(); i++ ){
						comboEleve.addItem(list.get(i).getNomEtprenom());
						
					}
					
					for(int i = 0; i < list2.size(); i++ ){
						//comboCours.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
						comboCours.addItem(list2.get(i).getCrTitre());
						comboCours.setSelectedItem(list2.get(i).getCrTitre());
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
					
					nb();
					
				}
				desactiveValidation();
				RemplissageMoy();//updateTable2();
			init();
			//
			//BindComboCour();
			}
			
			
		});
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Infos Relatives \u00E0 la Mati\u00E8re", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 100, 963, 93);
		getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		lblNote = new JLabel("Note 1:");
		lblNote.setBounds(304, 29, 103, 27);
		panel_1.add(lblNote);
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		lblNote.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		lblNote_1 = new JLabel("Note 2:");
		lblNote_1.setBounds(437, 29, 103, 27);
		panel_1.add(lblNote_1);
		lblNote_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNote_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		lblNoteExamen = new JLabel("Note Examen:");
		lblNoteExamen.setBounds(831, 29, 103, 27);
		panel_1.add(lblNoteExamen);
		lblNoteExamen.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoteExamen.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		JLabel lblChoisissezMatire = new JLabel("Choisissez Mati\u00E8re:");
		lblChoisissezMatire.setBounds(10, 27, 155, 27);
		panel_1.add(lblChoisissezMatire);
		lblChoisissezMatire.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoisissezMatire.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		lblCoefficientMatire = new JLabel("Coef Mati\u00E8re:");
		lblCoefficientMatire.setBounds(184, 28, 91, 27);
		panel_1.add(lblCoefficientMatire);
		lblCoefficientMatire.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoefficientMatire.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		note1 = new JTextField();
		note1.setBounds(304, 56, 103, 27);
		panel_1.add(note1);
		note1.setColumns(10);
		
		note2 = new JTextField();
		note2.setBounds(437, 56, 102, 27);
		panel_1.add(note2);
		note2.setColumns(10);
		
		noteExamen = new JTextField();
		noteExamen.setBounds(831, 56, 103, 27);
		panel_1.add(noteExamen);
		noteExamen.setColumns(10);
		
		comboCours = new JComboBox();
		comboCours.setBounds(10, 56, 155, 27);
		panel_1.add(comboCours);
		comboCours.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		
		comboCoef = new JComboBox();
		comboCoef.setBounds(185, 56, 90, 27);
		panel_1.add(comboCoef);
		
		DS2 = new JTextField();
		DS2.setColumns(10);
		DS2.setBounds(701, 56, 103, 27);
		panel_1.add(DS2);
		
		JLabel lblDs = new JLabel("DS-2:");
		lblDs.setHorizontalAlignment(SwingConstants.CENTER);
		lblDs.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDs.setBounds(701, 29, 103, 27);
		panel_1.add(lblDs);
		
		JLabel lblDs2 = new JLabel("DS-1:");
		lblDs2.setHorizontalAlignment(SwingConstants.CENTER);
		lblDs2.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblDs2.setBounds(568, 29, 103, 27);
		panel_1.add(lblDs2);
		
		DS1 = new JTextField();
		DS1.setColumns(10);
		DS1.setBounds(568, 56, 103, 27);
		panel_1.add(DS1);
		comboCours.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comboCoef.removeAllItems();
				Donne mk = new Donne();
				ArrayList<matiereEL> list2 = mk.getData(comboCours.getSelectedItem().toString());
				
				
				for(int j = 0; j < list2.size(); j++ ){
					comboCoef.addItem(list2.get(j).getCr_coef());
					
				}
				if(comboCours.getSelectedItem().equals("CONDUITE")){
					note1.setEditable(false);
					note2.setEditable(false);
					DS1.setEditable(false);
					DS2.setEditable(false);
				}else{
					note1.setEditable(true);
					note2.setEditable(true);
					DS1.setEditable(true);
					DS2.setEditable(true);
					Remplissage();
					Remplissage2();
					Remplissage3();
				}
				init();
				note1.setText("");
				note2.setText("");
				DS1.setText("");
				DS2.setText("");
				noteExamen.setText("");
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 193, 963, 175);
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
						note1.setText((String.valueOf(resultat.getDouble("note1"))));
						note2.setText((String.valueOf(resultat.getDouble("note2"))));
						DS1.setText((String.valueOf(resultat.getDouble("DS_1"))));
						DS2.setText((String.valueOf(resultat.getDouble("DS_2"))));
						noteExamen.setText((String.valueOf(resultat.getDouble("note_Examen")))); 
						Modifier.setEnabled(true);
						note1.setEnabled(true);
						note2.setEnabled(true);
						DS1.setEnabled(true);
						DS2.setEnabled(true);
						noteExamen.setEnabled(true);
						}else {
							System.out.println("Aucun envoie vers les textfields");
							Modifier.setEnabled(false);
							note1.setEnabled(false);
							note2.setEnabled(false);
							DS1.setEnabled(false);
							DS2.setEnabled(false);
							noteExamen.setEnabled(false);
							
						}
						System.out.println("Click effectué et num est "+table.getSelectedRow() + "  "+id );
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
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
						note1.setText((String.valueOf(resultat.getDouble("note1"))));
						note2.setText((String.valueOf(resultat.getDouble("note2"))));
						DS1.setText((String.valueOf(resultat.getDouble("DS_1"))));
						DS2.setText((String.valueOf(resultat.getDouble("DS_2"))));
						noteExamen.setText((String.valueOf(resultat.getDouble("note_Examen")))); 
						
						Modifier.setEnabled(true);
						note1.setEnabled(true);
						note2.setEnabled(true);
						DS1.setEnabled(true);
						DS2.setEnabled(true);
						noteExamen.setEnabled(true);
						}else {
							System.out.println("Aucun envoie vers les textfields");
							Modifier.setEnabled(false);
							Modifier.setEnabled(false);
							note1.setEnabled(false);
							note2.setEnabled(false);
							DS1.setEnabled(false);
							DS2.setEnabled(false);
							noteExamen.setEnabled(false);
							
						}
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
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
						note1.setText((String.valueOf(resultat.getDouble("note1"))));
						note2.setText((String.valueOf(resultat.getDouble("note2"))));
						DS1.setText((String.valueOf(resultat.getDouble("DS_1"))));
						DS2.setText((String.valueOf(resultat.getDouble("DS_2"))));
						noteExamen.setText((String.valueOf(resultat.getDouble("note_Examen")))); 
						
						Modifier.setEnabled(true);
						note1.setEnabled(true);
						note2.setEnabled(true);
						DS1.setEnabled(true);
						DS2.setEnabled(true);
						noteExamen.setEnabled(true);
						}else {
							System.out.println("Aucun envoie vers les textfields");
							Modifier.setEnabled(false);
							Modifier.setEnabled(false);
							note1.setEnabled(false);
							note2.setEnabled(false);
							DS1.setEnabled(false);
							DS2.setEnabled(false);
							noteExamen.setEnabled(false);
							
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
		panel_2.setBounds(10, 379, 543, 38);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton Ajouter = new JButton("Ajouter");
		Ajouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(checkput()){
					try {
					String sql = "select note.matiere from note where note.id_elv = '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' and "
							+ "note.Semestre = '"+comboSemestre.getSelectedItem().toString()+"' "
									+ " and note.classe = '"+comboClasse.getSelectedItem().toString()+"'";
					
					
					String sql2 = "select  note2.matiere from note2 where  note2.id_elv = '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' and "
									+ "note2.Semestre = '"+comboSemestre.getSelectedItem().toString()+"'and "
											+ " note2.classe = '"+comboClasse.getSelectedItem().toString()+"'";
					
					String sql3 = "select  note3.matiere from note3 where  note3.id_elv = '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' and "
							+ "note3.Semestre = '"+comboSemestre.getSelectedItem().toString()+"'and "
									+ " note3.classe = '"+comboClasse.getSelectedItem().toString()+"'";
					
					
					
						int flag = 1;
						if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
		                   while(resultat.next()){
		                    		if(resultat.getString(1).equals(comboCours.getSelectedItem().toString())){
		    						//if(true){
		    							flag = 0;
		    							System.out.println("Forceeeee");
		    							break;
		    						}else {
		    							System.out.println("GGGGGGGGGGG");
		    						}
		                    	
		                    	
		                    }
		                   
						}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
							prepared = cnx.prepareStatement(sql2);
							resultat = prepared.executeQuery();
		                   while(resultat.next()){
		                    		if(resultat.getString(1).equals(comboCours.getSelectedItem().toString())){
		    						//if(true){
		    							flag = 0;
		    							System.out.println("Forceeeee");
		    							break;
		    						}else {
		    							System.out.println("GGGGGGGGGGG");
		    						}
		                    	
		                    		
		                    }
		                   	
						}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre")){
							prepared = cnx.prepareStatement(sql3);
							resultat = prepared.executeQuery();
		                   while(resultat.next()){
		                    		if(resultat.getString(1).equals(comboCours.getSelectedItem().toString())){
		    						//if(true){
		    							flag = 0;
		    							System.out.println("Forceeeee");
		    							break;
		    						}else {
		    							System.out.println("GGGGGGGGGGG");
		    						}
		                    	
		                    		
		                    }
		                  	
						}
						
	                    if(flag==0){
	                    	JOptionPane.showMessageDialog(null, "Cet eleve à deja une note pour cette matière");
	                    	init();
	                    }else{
	                    	
	                    	if(comboCours.getSelectedItem().equals("CONDUITE") ){
	                    		
	                    		try {
	                    			
	                    			
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	                    			
	                    			if (isNumeric()) {
										
									
		                    		if (noteExamen.getText().toString().equals("")) {
										JOptionPane.showMessageDialog(null, "Entrez une Valeur Valide (NOMBRE) ");
										init();
									}
		                    		
		                    		if(Double.parseDouble(noteExamen.getText()) <= 20 ){
										
										if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
											PreparedStatement ps;
											try {
												Double note1 = 0.0;
												Double note2 = 0.0;
												Double DS1 = 0.0;
												Double DS2 = 0.0;
												Double mcls = 0.0;
												Double mclGen = Double.parseDouble(noteExamen.getText().toString());
												
												
												ps = cnx.prepareStatement("INSERT into note (anne_scolaire, Semestre, classe,"
														+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
														+ "moy_Gen, id_elv, coefClasse) "
														+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

												ps.setString(1, comboAn.getSelectedItem().toString());
												ps.setString(2, comboSemestre.getSelectedItem().toString());
												ps.setString(3, comboClasse.getSelectedItem().toString());
												ps.setString(4, comboEleve.getSelectedItem().toString());
												ps.setString(5, comboCours.getSelectedItem().toString());
												ps.setString(6, comboCoef.getSelectedItem().toString());
												ps.setDouble(7, note1);
												ps.setDouble(8, note1);
												ps.setDouble(9, DS1);
												ps.setDouble(10, DS2);
												ps.setDouble(11, mclGen);
												ps.setDouble(12, mcls);
												ps.setDouble(13, mclGen);
												ps.setString(14, comboNumero.getSelectedItem().toString());
												ps.setInt(15, Integer.parseInt(coefClasse.getText().toString()));
												ps.executeUpdate();
												init();
												JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
												//&& !textFieldSum.getText().toString().equals("0.0")
												} catch (SQLException e) {
												e.printStackTrace();
											}
											Remplissage();
											RemplissageMoy();
											init();
										}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
											PreparedStatement ps;
											try {
												Double note1 = 0.0;
												Double note2 = 0.0;
												Double DS1 = 0.0;
												Double DS2 = 0.0;
												Double mcls = 0.0;
												Double mclGen = Double.parseDouble(noteExamen.getText().toString());
												
												ps = cnx.prepareStatement("INSERT into note2 (anne_scolaire, Semestre, classe,"
														+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
														+ "moy_Gen, id_elv , coefClasse) "
														+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?, ?)");

												ps.setString(1, comboAn.getSelectedItem().toString());
												ps.setString(2, comboSemestre.getSelectedItem().toString());
												ps.setString(3, comboClasse.getSelectedItem().toString());
												ps.setString(4, comboEleve.getSelectedItem().toString());
												ps.setString(5, comboCours.getSelectedItem().toString());
												ps.setString(6, comboCoef.getSelectedItem().toString());
												ps.setDouble(7, note1);
												ps.setDouble(8, note1);
												ps.setDouble(9, DS1);
												ps.setDouble(10, DS2);
												ps.setDouble(11, mclGen);
												ps.setDouble(12, mcls);
												ps.setDouble(13, mclGen);
												ps.setString(14, comboNumero.getSelectedItem().toString());
												ps.setInt(15, Integer.parseInt(coefClasse.getText().toString()));
												ps.executeUpdate();
												init();
												JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
												//&& !textFieldSum.getText().toString().equals("0.0")
												} catch (SQLException e) {
												e.printStackTrace();
											}
											Remplissage2();
											//RemplissageMoy();
											
										}else{
											PreparedStatement ps;
											try {
												Double note1 = 0.0;
												Double note2 = 0.0;
												Double DS1 = 0.0;
												Double DS2 = 0.0;
												Double mcls = 0.0;
												Double mclGen = Double.parseDouble(noteExamen.getText().toString());
												
												ps = cnx.prepareStatement("INSERT into note3 (anne_scolaire, Semestre, classe,"
														+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
														+ "moy_Gen, id_elv, coefClasse) "
														+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

												ps.setString(1, comboAn.getSelectedItem().toString());
												ps.setString(2, comboSemestre.getSelectedItem().toString());
												ps.setString(3, comboClasse.getSelectedItem().toString());
												ps.setString(4, comboEleve.getSelectedItem().toString());
												ps.setString(5, comboCours.getSelectedItem().toString());
												ps.setString(6, comboCoef.getSelectedItem().toString());
												ps.setDouble(7, note1);
												ps.setDouble(8, note1);
												ps.setDouble(9, DS1);
												ps.setDouble(10, DS2);
												ps.setDouble(11, mclGen);
												ps.setDouble(12, mcls);
												ps.setDouble(13, mclGen);
												ps.setString(14, comboNumero.getSelectedItem().toString());
												ps.setInt(15, Integer.parseInt(coefClasse.getText().toString()));
												
												ps.executeUpdate();
												JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
												init();
												//&& !textFieldSum.getText().toString().equals("0.0")
												} catch (SQLException e) {
												System.out
														.println("Enregistrement non reussit");
												JOptionPane.showMessageDialog(null, "Veillez Choissir les informations adéquates !!!");
											}
											Remplissage3();
											//RemplissageMoy();
											
										}
										
										}else{
											JOptionPane.showMessageDialog(null, "Vous avez entré une valeur plus grande que la moyenne. Modifiez SVP !!!");
											
										}
	                    			}
	////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		                    		init();
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
		                    	init();
	                    	
	                    	}else{
	                    		
	                    		
							try {
							if(  Double.parseDouble(note1.getText()) <= 20 && Double.parseDouble(note2.getText()) <= 20 
	                				&& Double.parseDouble(DS1.getText()) <= 20 && Double.parseDouble(DS2.getText()) <= 20
	                				&& Double.parseDouble(noteExamen.getText()) <= 20){
								
								
	                			if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
	                				PreparedStatement ps;
	    							try {
	    								Double mcls = ((Double.parseDouble(note1.getText().toString()))+
	    										(Double.parseDouble(note2.getText().toString()))+
	    										(Double.parseDouble(DS1.getText().toString())) + (Double.parseDouble(DS2.getText().toString())))/4;
	    								//Double mclGen = mcls*(Double.parseDouble(comboCoef.getSelectedItem().toString()));
	    								//Double mclGen = (mcls + Double.parseDouble(noteExamen.getText().toString()))/2;
	    								
	    								Double mclGen = (
	    										((Double.parseDouble(note1.getText().toString()))+
	    										(Double.parseDouble(note2.getText().toString())))/2 
	    										+ ((Double.parseDouble(DS1.getText().toString())) + 
	    											(Double.parseDouble(DS2.getText().toString())))/ 2
	    											+ Double.parseDouble(noteExamen.getText().toString())
	    										)/3;
	    								
	    								
	    								ps = cnx.prepareStatement("INSERT into note (anne_scolaire, Semestre, classe,"
	    										+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
	    										+ "moy_Gen, id_elv, coefClasse) "
	    										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	    								ps.setString(1, comboAn.getSelectedItem().toString());
	    								ps.setString(2, comboSemestre.getSelectedItem().toString());
	    								ps.setString(3, comboClasse.getSelectedItem().toString());
	    								ps.setString(4, comboEleve.getSelectedItem().toString());
	    								ps.setString(5, comboCours.getSelectedItem().toString());
	    								ps.setString(6, comboCoef.getSelectedItem().toString());
	    								ps.setDouble(7, Double.parseDouble(note1.getText().toString()));
	    								ps.setDouble(8, Double.parseDouble(note2.getText().toString()));
	    								ps.setDouble(9, Double.parseDouble(DS1.getText().toString()));
	    								ps.setDouble(10, Double.parseDouble(DS2.getText().toString()));
	    								ps.setDouble(11, Double.parseDouble(noteExamen.getText().toString()));
	    								ps.setDouble(12, mcls);
	    								ps.setDouble(13, mclGen);
	    								ps.setString(14, comboNumero.getSelectedItem().toString());
	    								ps.setInt(15, Integer.parseInt(coefClasse.getText().toString()));
										
	    								ps.executeUpdate();
	    								init();
	    								JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
	    								//&& !textFieldSum.getText().toString().equals("0.0")
	    								} catch (SQLException e) {
	    									JOptionPane.showMessageDialog(null, "Enregistrement non éffectuer. Verifié vos choix. ");
	    							}
	    							 Remplissage();
	    							 init();
	                			}
	                			else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
	                				PreparedStatement ps;
	    							try {
	    								Double mcls = ((Double.parseDouble(note1.getText().toString()))+
	    										(Double.parseDouble(note2.getText().toString()))+
	    										(Double.parseDouble(DS1.getText().toString())) + (Double.parseDouble(DS2.getText().toString())))/4;
	    								//Double mclGen = (mcls + Double.parseDouble(noteExamen.getText().toString()))/2;
	    								Double mclGen = (
	    										((Double.parseDouble(note1.getText().toString()))+
	    										(Double.parseDouble(note2.getText().toString())))/2 
	    										+ ((Double.parseDouble(DS1.getText().toString())) + 
	    											(Double.parseDouble(DS2.getText().toString())))/ 2
	    											+ Double.parseDouble(noteExamen.getText().toString())
	    										)/3;
	    								
	    								ps = cnx.prepareStatement("INSERT into note2 (anne_scolaire, Semestre, classe,"
	    										+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
	    										+ "moy_Gen, id_elv,coefClasse) "
	    										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	    								ps.setString(1, comboAn.getSelectedItem().toString());
	    								ps.setString(2, comboSemestre.getSelectedItem().toString());
	    								ps.setString(3, comboClasse.getSelectedItem().toString());
	    								ps.setString(4, comboEleve.getSelectedItem().toString());
	    								ps.setString(5, comboCours.getSelectedItem().toString());
	    								ps.setString(6, comboCoef.getSelectedItem().toString());
	    								ps.setDouble(7, Double.parseDouble(note1.getText().toString()));
	    								ps.setDouble(8, Double.parseDouble(note2.getText().toString()));
	    								ps.setDouble(9, Double.parseDouble(DS1.getText().toString()));
	    								ps.setDouble(10, Double.parseDouble(DS2.getText().toString()));
	    								ps.setDouble(11, Double.parseDouble(noteExamen.getText().toString()));
	    								ps.setDouble(12, mcls);
	    								ps.setDouble(13, mclGen);
	    								ps.setString(14, comboNumero.getSelectedItem().toString());
	    								ps.setInt(15, Integer.parseInt(coefClasse.getText().toString()));
										
	    								ps.executeUpdate();
	    								init();
	    								JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
	    								//&& !textFieldSum.getText().toString().equals("0.0")
	    								} catch (SQLException e) {
	    								e.printStackTrace();
	    							}
	    							Remplissage2();
	    							init();
	                			}else {
	                				PreparedStatement ps;
	    							try {
	    								Double mcls = ((Double.parseDouble(note1.getText().toString()))+
	    										(Double.parseDouble(note2.getText().toString()))+
	    										(Double.parseDouble(DS1.getText().toString())) + (Double.parseDouble(DS2.getText().toString())))/4;
	    								//Double mclGen = mcls*(Double.parseDouble(comboCoef.getSelectedItem().toString()));
	    								//Double mclGen = (mcls + Double.parseDouble(noteExamen.getText().toString()))/2;
	    								Double mclGen = (
	    										((Double.parseDouble(note1.getText().toString()))+
	    										(Double.parseDouble(note2.getText().toString())))/2 
	    										+ ((Double.parseDouble(DS1.getText().toString())) + 
	    											(Double.parseDouble(DS2.getText().toString())))/ 2
	    											+ Double.parseDouble(noteExamen.getText().toString())
	    										)/3;
	    								
	    								ps = cnx.prepareStatement("INSERT into note3 (anne_scolaire, Semestre, classe,"
	    										+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
	    										+ "moy_Gen, id_elv,coefClasse) "
	    										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

	    								ps.setString(1, comboAn.getSelectedItem().toString());
	    								ps.setString(2, comboSemestre.getSelectedItem().toString());
	    								ps.setString(3, comboClasse.getSelectedItem().toString());
	    								ps.setString(4, comboEleve.getSelectedItem().toString());
	    								ps.setString(5, comboCours.getSelectedItem().toString());
	    								ps.setString(6, comboCoef.getSelectedItem().toString());
	    								ps.setDouble(7, Double.parseDouble(note1.getText().toString()));
	    								ps.setDouble(8, Double.parseDouble(note2.getText().toString()));
	    								ps.setDouble(9, Double.parseDouble(DS1.getText().toString()));
	    								ps.setDouble(10, Double.parseDouble(DS2.getText().toString()));
	    								ps.setDouble(11, Double.parseDouble(noteExamen.getText().toString()));
	    								ps.setDouble(12, mcls);
	    								ps.setDouble(13, mclGen);
	    								ps.setString(14, comboNumero.getSelectedItem().toString());
	    								ps.setInt(15, Integer.parseInt(coefClasse.getText().toString()));
										
	    								ps.executeUpdate();
	    								init();
	    								JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
	    								//&& !textFieldSum.getText().toString().equals("0.0")
	    								} catch (SQLException e) {
	    								e.printStackTrace();
	    							}
	    							Remplissage3();
	    							init();
	                			}
	                			
	                			 RemplissageMoy();
	                    	
	                    
							init();
	                			}else{
	                				JOptionPane.showMessageDialog(null, "Vous avez entré une valeur plus grande que la moyenne. Modifiez SVP !!!");
	                				
	                			}
	                    	} catch (Exception e) {
	                    		JOptionPane.showMessageDialog(null,"Verifier votre saisie SVP !!! ");
							}
							init();
	                    	}
	                    }
	               } catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
					}
					nb();
					//desactiveValidation();
				}else{
					JOptionPane.showMessageDialog(null,"Erreur sur les Choix. Verifier et continuer votre saisie SVP !!! ");
				}
				
				
				
			}
		});
		Ajouter.setBounds(10, 11, 90, 23);
		panel_2.add(Ajouter);
		
		Modifier = new JButton("Modifier");
		Modifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
				
				Double mc = Double.parseDouble(noteExamen.getText().toString());
				Double mcls = ((Double.parseDouble(note1.getText().toString()))+
						(Double.parseDouble(note2.getText().toString()))+
						(Double.parseDouble(DS1.getText().toString())) + (Double.parseDouble(DS2.getText().toString())))/4;
				Double mclGen = (mcls + Double.parseDouble(noteExamen.getText().toString()))/2;
				
				if(comboSemestre.getSelectedItem().equals("1er Trimestre") && !lblNewLabel.getText().equals("0")){
					
					if(comboCours.getSelectedItem().equals("CONDUITE")){
						try {
							
							String sql =" update note set  note1 = ?  , note2 = ? , DS_1 = ?, DS_2 = ?, note_Examen= ? , moy_Gen = ?"
									+ "where note_id = '"+Integer.parseInt(lblNewLabel.getText())+"'";
							Double n = 0.0 ;
							prepared = cnx.prepareStatement(sql);
							prepared.setDouble(1, n);
							prepared.setDouble(2, n);
							prepared.setDouble(3, n);
							prepared.setDouble(4, n);
							prepared.setDouble(5, mc);
							prepared.setDouble(6, mc);
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
							
						} catch (Exception e) {
							System.out.println("erreur de 1 : "+ e);
						}
					}else {
						
						try {
							String sql =" update note set  note1 = ?  , note2 = ? , DS_1 = ?, DS_2 = ?, note_Examen= ? , moy_cls=? , moy_Gen=?"
									+ "where note_id = '"+Integer.parseInt(lblNewLabel.getText())+"'";
							
							prepared = cnx.prepareStatement(sql);
							prepared.setDouble(1, Double.parseDouble(note1.getText().toString()));
							prepared.setDouble(2, Double.parseDouble(note2.getText().toString()));
							prepared.setDouble(3, Double.parseDouble(DS1.getText().toString()));
							prepared.setDouble(4, Double.parseDouble(DS2.getText().toString()));
							prepared.setDouble(5, Double.parseDouble(noteExamen.getText().toString()));
							prepared.setDouble(6, mcls);
							prepared.setDouble(7, mclGen);
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
							
						} catch (Exception e) {
							System.out.println("erreur 1 de : "+ e);
						}
					}
					Remplissage();
					 RemplissageMoy();
					lblNewLabel.setText("0");
				}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre") && !lblNewLabel.getText().equals("0") ){
					if(comboCours.getSelectedItem().equals("CONDUITE")){
						
						try {
							
							String sql =" update note2 set  note1 = ?  , note2 = ? , DS_1 = ?, DS_2 = ?, note_Examen= ?, moy_Gen=?"
									+ "where note_id = '"+Integer.parseInt(lblNewLabel.getText())+"'";
							Double n = 0.0 ;
							prepared = cnx.prepareStatement(sql);
							prepared.setDouble(1, n);
							prepared.setDouble(2, n);
							prepared.setDouble(3, n);
							prepared.setDouble(4, n);
							prepared.setDouble(5, mc);
							prepared.setDouble(6, mc);
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
						} catch (Exception e) {
							System.out.println("erreur 2 de : "+ e);
						}
						
					}else {
						try {
							String sql =" update note2 set  note1 = ?  , note2 = ? , DS_1 = ?, DS_2 = ?, note_Examen= ?, moy_cls=?, moy_Gen=? "
									+ " where note_id = '"+Integer.parseInt(lblNewLabel.getText())+"'";
							
							prepared = cnx.prepareStatement(sql);
							prepared.setDouble(1, Double.parseDouble(note1.getText().toString()));
							prepared.setDouble(2, Double.parseDouble(note2.getText().toString()));
							prepared.setDouble(3, Double.parseDouble(DS1.getText().toString()));
							prepared.setDouble(4, Double.parseDouble(DS2.getText().toString()));
							prepared.setDouble(5, Double.parseDouble(noteExamen.getText().toString()));
							prepared.setDouble(6, mcls);
							prepared.setDouble(7, mclGen);
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
						} catch (Exception e) {
							System.out.println("erreur 2 de : "+ e);
						}
						
					}
					Remplissage2();
					RemplissageMoy();
					lblNewLabel.setText("0");
				}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre") && !lblNewLabel.getText().equals("0")){
					
					if(comboCours.getSelectedItem().equals("CONDUITE")){
						try {
							
							String sql =" update note3 set  note1 = ?  , note2 = ? , DS_1 = ?, DS_2 = ?, note_Examen= ?, moy_Gen=?"
									+ "where note_id = '"+Integer.parseInt(lblNewLabel.getText())+"'";
							Double n = 0.0 ;
							prepared = cnx.prepareStatement(sql);
							prepared.setDouble(1, n);
							prepared.setDouble(2, n);
							prepared.setDouble(3, n);
							prepared.setDouble(4, n);
							prepared.setDouble(5, mc);
							prepared.setDouble(6, mc); 
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
						} catch (Exception e) {
							System.out.println("erreur 3 de : "+ e);
						}
					}else {
						try {
							String sql =" update note3 set  note1 = ?  , note2 = ? , DS_1 = ?, DS_2 = ?, note_Examen= ? , moy_cls=?, moy_Gen=?"
									+ "where note_id = '"+Integer.parseInt(lblNewLabel.getText())+"'";
							prepared = cnx.prepareStatement(sql);
							prepared.setDouble(1, Double.parseDouble(note1.getText().toString()));
							prepared.setDouble(2, Double.parseDouble(note2.getText().toString()));
							prepared.setDouble(3, Double.parseDouble(DS1.getText().toString()));
							prepared.setDouble(4, Double.parseDouble(DS2.getText().toString()));
							prepared.setDouble(5, Double.parseDouble(noteExamen.getText().toString()));
							prepared.setDouble(6, mcls);
							prepared.setDouble(7, mclGen);
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
						} catch (Exception e) {
							System.out.println("erreur 3 de : "+ e);
							e.printStackTrace();
						}
						
					}
					Remplissage3();
					 RemplissageMoy();
					lblNewLabel.setText("0");
				}else {
					JOptionPane.showMessageDialog(null, "Selectionnez une ligne dans le tableau avant d'appliquer la modification ");
				}
				init();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Veillez choisir une matière que vous voulez modifier"+e);
				}
			}
		});
		Modifier.setBounds(110, 11, 90, 23);
		panel_2.add(Modifier);
		
		Supprimer = new JButton("Supprimer");
		Supprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				//String sql = "delete from eleve where elv_id=?";
				
				String sql = "DELETE FROM `note` WHERE `note_id` = ? " ;
				String sql1 = "DELETE FROM `note2` WHERE `note_id` = ? " ;	
				String sql2 = "DELETE FROM `note3` WHERE `note_id` = ? " ;
					//String test = lb
				//JOptionPane.showMessageDialog(null, "Note supprimer ");
					
					if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
						
						try{
							prepared = cnx.prepareStatement(sql);
							int id = Integer.parseInt(lblNewLabel.getText());
							prepared.setInt(1, id);
							prepared.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "Note supprimer ");
					//------------------------------updateTable();
							//initialisation();
							Remplissage();							
						}catch(SQLException ex){
							
							JOptionPane.showMessageDialog(null, "Erreur "+ex);
						}
					}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
						//String sql1 = "DELETE FROM NOTE where note_id = ? " ;
						try{
							prepared = cnx.prepareStatement(sql1);
							int id = Integer.parseInt(lblNewLabel.getText());
							prepared.setInt(1, id);
							prepared.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "Note supprimer ");
					//------------------------------updateTable();
							//initialisation();
							Remplissage2();
						}catch(SQLException ex){
							
							JOptionPane.showMessageDialog(null, "Erreur "+ex);
						}
						
					}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre")){
						//String sql2 = "DELETE FROM NOTE where note_id = ? " ;
						try{
							prepared = cnx.prepareStatement(sql2);
							int id = Integer.parseInt(lblNewLabel.getText());
							prepared.setInt(1, id);
							prepared.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "Note supprimer ");
					//------------------------------updateTable();
							//initialisation();
							Remplissage3();
						}catch(SQLException ex){
							
							JOptionPane.showMessageDialog(null, "Erreur "+ex);
						}
						
					}				
					
					
					if(!lblNewLabel.getText().equals("")){}else{
					
					JOptionPane.showMessageDialog(null, "Choisissez Une Note à supprimer ");
				}
				
				
				
			}
		});
		Supprimer.setBounds(210, 11, 108, 23);
		panel_2.add(Supprimer);
		
		btnEditionRelevDe = new JButton("Edition Relev\u00E9 de Notes :");
		btnEditionRelevDe.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comboAn.getSelectedItem().equals("Choix") && !comboClasse.getSelectedItem().equals("Choix") 
						&& !comboCours.getSelectedItem().equals("Choix")){
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous imprimer ce relevé.", "Message", JOptionPane.YES_NO_OPTION);
					if(imp == 0){
						//JasperDesign jd;
						//JasperReport jf;
						ConnexionMySql p = new ConnexionMySql();
						p.ConnexiomBd();
						if(comboSemestre.getSelectedItem().equals("1er Trimestre") == true){
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/releve1.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
								vNomEleve.put("pAn", comboAn.getSelectedItem().toString());
								vNomEleve.put("pMat", comboCours.getSelectedItem().toString());
								vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/releve2.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
								vNomEleve.put("pAn", comboAn.getSelectedItem().toString());
								vNomEleve.put("pMat", comboCours.getSelectedItem().toString());
								vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);	
							} catch (JRException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre")){
							try {
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/releve3.jrxml");
								HashMap vNomEleve = new HashMap();
								
								vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
								vNomEleve.put("pAn", comboAn.getSelectedItem().toString());
								vNomEleve.put("pMat", comboCours.getSelectedItem().toString());
								vNomEleve.put("pSem", comboSemestre.getSelectedItem().toString());
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
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
		btnEditionRelevDe.setBounds(328, 11, 202, 23);
		panel_2.add(btnEditionRelevDe);
		
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
		
		JPanel panel_3 = new JPanel();
		panel_3.setForeground(Color.PINK);
		panel_3.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2), "Moyenne Actuelle de l'El\u00E8ve", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(64, 64, 64)));
		panel_3.setBounds(10, 428, 963, 137);
		getContentPane().add(panel_3);
		panel_3.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 21, 943, 105);
		panel_3.add(scrollPane_1);
		
		tablemoy = new JTable();
		scrollPane_1.setViewportView(tablemoy);
		
		JPanel panel_4 = new JPanel();
		panel_4.setLayout(null);
		panel_4.setBounds(10, 567, 963, 48);
		getContentPane().add(panel_4);
		
		btnValider = new JButton("Valider des Notes pour Impression :");
		btnValider.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				int flag = 1 ;
							
				String sqlTo = "SELECT  * FROM `validationtotal` where Annee = '"+comboAn.getSelectedItem().toString()+"' "
						+ "and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ";
					
					try {
						prepared = cnx.prepareStatement(sqlTo);
						resultat = prepared.executeQuery();

						while(resultat.next()){
                    		if(resultat.getString(4).equals(comboSemestre.getSelectedItem().toString())
                    				&& resultat.getString(2).equals(comboClasse.getSelectedItem().toString())
                    				&& resultat.getString(9).equals(comboAn.getSelectedItem().toString())
                    				){
    						//if(true){resultat.getString(3).equals(lbNum.getText().toString())   				&& 
    							flag = 0;
    							System.out.println("Selection validation ");
    							break;
    						}else {
    							System.out.println("Non selection validation");
    						}
                    	
                    	
                    }
					
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
				String sql = "SELECT  * FROM `validation` where Annee = '"+comboAn.getSelectedItem().toString()+"' "
						+ "and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ";
					
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();

						while(resultat.next()){
                    		if(resultat.getString(4).equals(comboSemestre.getSelectedItem().toString())
                    				&& resultat.getString(2).equals(comboClasse.getSelectedItem().toString())
                    				&& resultat.getString(9).equals(comboAn.getSelectedItem().toString())
                    				){
    						//if(true){resultat.getString(3).equals(lbNum.getText().toString())   				&& 
    							flag = 0;
    							System.out.println("Selection validation ");
    							break;
    						}else {
    							System.out.println("Non selection validation");
    						}
                    	
                    	
                    }
					
					
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
                   
					String sql2 = "SELECT  * FROM `validation2` where Annee = '"+comboAn.getSelectedItem().toString()+"' "
							+ "and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ";
						
						try {
							prepared = cnx.prepareStatement(sql2);
							resultat = prepared.executeQuery();

							while(resultat.next()){
	                    		if(resultat.getString(4).equals(comboSemestre.getSelectedItem().toString())
	                    				&& resultat.getString(2).equals(comboClasse.getSelectedItem().toString())
	                    				&& resultat.getString(9).equals(comboAn.getSelectedItem().toString())
	                    				){
	    						//if(true){resultat.getString(3).equals(lbNum.getText().toString())   				&& 
	    							flag = 0;
	    							System.out.println("Selection validation ");
	    							break;
	    						}else {
	    							System.out.println("Non selection validation");
	    						}
	                    	
	                    	
	                    }
						
						
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						
						String sql3 = "SELECT  * FROM `validation3` where Annee = '"+comboAn.getSelectedItem().toString()+"' "
								+ "and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ";
							
							try {
								prepared = cnx.prepareStatement(sql3);
								resultat = prepared.executeQuery();

								while(resultat.next()){
		                    		if(resultat.getString(4).equals(comboSemestre.getSelectedItem().toString())
		                    				&& resultat.getString(2).equals(comboClasse.getSelectedItem().toString())
		                    				&& resultat.getString(9).equals(comboAn.getSelectedItem().toString())
		                    				){
		    						//if(true){resultat.getString(3).equals(lbNum.getText().toString())   				&& 
		    							flag = 0;
		    							System.out.println("Selection validation ");
		    							break;
		    						}else {
		    							System.out.println("Non selection validation");
		    						}
		                    	
		                    	
		                    }
							
							
							} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
							
							
							
							String sq1stock = "SELECT  * FROM `stockage` where anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and Classe = '"+comboClasse.getSelectedItem().toString()+"' ";
								
								try {
									prepared = cnx.prepareStatement(sq1stock);
									resultat = prepared.executeQuery();

									while(resultat.next()){
			                    		if(resultat.getString(3).equals(comboClasse.getSelectedItem().toString())
			                    				&& resultat.getString(7).equals(comboAn.getSelectedItem().toString())
			                    				){
			    						//if(true){resultat.getString(3).equals(lbNum.getText().toString())   				&& 
			    							flag = 0;
			    							System.out.println("Selection validation ");
			    							break;
			    						}else {
			    							System.out.println("Non selection validation");
			    						}
			                    	
			                    	
			                    }
								
								
								} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
					
								
								String sq1stock1 = "SELECT  * FROM `stockage1` where anne_scolaire = '"+comboAn.getSelectedItem().toString()+"' "
										+ "and Classe = '"+comboClasse.getSelectedItem().toString()+"' ";
									
									try {
										prepared = cnx.prepareStatement(sq1stock1);
										resultat = prepared.executeQuery();

										while(resultat.next()){
				                    		if(resultat.getString(3).equals(comboClasse.getSelectedItem().toString())
				                    				&& resultat.getString(10).equals(comboAn.getSelectedItem().toString())
				                    				){
				    						//if(true){resultat.getString(3).equals(lbNum.getText().toString())   				&& 
				    							flag = 0;
				    							System.out.println("Selection validation ");
				    							break;
				    						}else {
				    							System.out.println("Non selection validation");
				    						}
				                    	
				                    	
				                    }
									
									
									} catch (SQLException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
										/**/
					
					validationTables(flag);
                   
					if(flag == 0){
						PreparedStatement ps;
						if(comboSemestre.getSelectedItem().toString().equals("1er Trimestre")){
							
							
							try {
								ps = cnx.prepareStatement("DELETE FROM `validationtotal` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
										+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Initialisation 11111 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into validationtotal (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
										+ " `Annee`, `coefClasse` , `MOY`, rang1 ) "
										+ "  "
										+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
										+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve` , (select @rank:=0 ) p "
										+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
												+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

								ps.executeUpdate();
								init();
										//JOptionPane.showMessageDialog(null, "Mise à Jour reussit 1111 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("DELETE FROM `stockage` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"' and `anne_scolaire` ='"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Initialisation 11111 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into stockage ( Num, nomEtprenom, Classe, M1, M2, M3, anne_scolaire, elv_id ) "
										+ "select distinct moyeleve.Num, note.nom_et_prenom, moyeleve.Classe, moyeleve.MOY as M1, moyeleve1.MOY as M2, moyeleve2.MOY as M3, anne_scolaire , id_elv  "
										+ "from moyeleve, moyeleve1, moyeleve2, note "
										+ "where moyeleve.Classe = '" +comboClasse.getSelectedItem().toString()+"' "
										+ "and moyeleve.Num = moyeleve1.Num "
										+ "and moyeleve1.Num = moyeleve2.Num and moyeleve2.Num = id_elv   "
										+ "and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Insertion Reussit Continuez SVP .... ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							
							
							
						}else if(comboSemestre.getSelectedItem().toString().equals("2ème Trimestre")){
							try {
								ps = cnx.prepareStatement("DELETE FROM `validationtotal` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
										+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								   //JOptionPane.showMessageDialog(null, "Initialisation 2222 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into validationtotal (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
										+ " `Annee`, `coefClasse` , `MOY`, rang2 ) "
										+ "  "
										+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
										+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve1` , (select @rank:=0 ) p "
										+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
												+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Mise à Jour reussit 2222 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							
							try {
								ps = cnx.prepareStatement("DELETE FROM `stockage` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"' and `anne_scolaire` ='"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Initialisation 11111 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into stockage ( Num, nomEtprenom, Classe, M1, M2, M3, anne_scolaire, elv_id ) "
										+ "select distinct moyeleve.Num, nomEtprenom, moyeleve.Classe, moyeleve.MOY as M1, moyeleve1.MOY as M2, moyeleve2.MOY as M3, anne_scolaire , elv_id "
										+ "from moyeleve, moyeleve1, moyeleve2, eleve "
										+ "where moyeleve.Classe = '" +comboClasse.getSelectedItem().toString()+"' "
										+ "and moyeleve.Num = moyeleve1.Num "
										+ "and moyeleve1.Num = moyeleve2.Num and moyeleve2.Num = elv_id  "
										+ "and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Insertion Reussit Continuez SVP .... ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
						} else if (comboSemestre.getSelectedItem().toString().equals("3ème Trimestre")){
							try {
								ps = cnx.prepareStatement("DELETE FROM `validationtotal` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
										+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Initialisation 3333 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into validationtotal (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
										+ " `Annee`, `coefClasse` , `MOY`, rang3 ) "
										+ "  "
										+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
										+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve2` , (select @rank:=0 ) p "
										+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
												+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Mise à Jour reussit 3333 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							
							try {
								ps = cnx.prepareStatement("DELETE FROM `stockage` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"' and `anne_scolaire` ='"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								JOptionPane.showMessageDialog(null, "Initialisation Stockage 1111 2 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into stockage ( Num, nomEtprenom, Classe, M1, M2, M3, anne_scolaire, elv_id ) "
										+ "select distinct moyeleve.Num, nomEtprenom, moyeleve.Classe, moyeleve.MOY as M1, moyeleve1.MOY as M2, moyeleve2.MOY as M3, anne_scolaire , elv_id "
										+ "from moyeleve, moyeleve1, moyeleve2, eleve "
										+ "where moyeleve.Classe = '" +comboClasse.getSelectedItem().toString()+"' "
										+ "and moyeleve.Num = moyeleve1.Num "
										+ "and moyeleve1.Num = moyeleve2.Num and moyeleve2.Num = elv_id  "
										+ "and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Insertion Reussit Continuez SVP .... ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							
							
							
							
							
							
							
							
							
							try {
								ps = cnx.prepareStatement("DELETE FROM `stockage1` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
										+ " and `anne_scolaire` = '"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Initialisation 11111 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement(""
										/*+ "INSERT INTO `stockage1`(`Num`, `nomEtprenom`, `Classe`, `M1`, `rang1`, `M2`, `rang2`, `M3`, `rang3`, `anne_scolaire`, `elv_id` , `Num2`, `Num3`,"
										+ "`Nums`, `valClasse`, `val2Classe`, `val3Classe`, `stocClasse`)"
										+ " select distinct validation.Num , stockage.nomEtprenom, validation.classe, stockage.M1, validation.rang1, stockage.M2, validation2.rang2, stockage.M3, "
										+ "validation3.rang3, stockage.anne_scolaire, stockage.Num, validation2.Num, validation3.Num,"
										+ " stockage.Num, validation.Classe, validation2.Classe, validation3.Classe, stockage.Classe "
										+ "from validation, validation2, validation3, stockage "
										+ "where validation.`Classe`= stockage.Classe and validation2.Classe = validation3.Classe "
										+ "and  validation2.Classe= validation.`Classe` and stockage.Num = validation.Num and validation2.Num = validation3.Num "
										+ "and validation3.Num = validation.Num and stockage.anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
												+ "and stockage.Classe='" +comboClasse.getSelectedItem().toString()+"'");
								
								
								*/
										
										+ "INSERT INTO `stockage1`(`Num`, `nomEtprenom`, `Classe`, `M1`, `rang1`, `M2`, `rang2`, `M3`, `rang3`, `anne_scolaire`, `elv_id` , `Num2`, `Num3`,"
										+ "`Nums`, `valClasse`, `val2Classe`, `val3Classe`, `stocClasse`, `anne_scolaireV1`,`anne_scolaireV2`,`anne_scolaireV3`)"
										
										
										+ " select distinct validation.Num , stockage.nomEtprenom, validation.classe, stockage.M1, validation.rang1, stockage.M2, validation2.rang2, stockage.M3, "
										+ "validation3.rang3, stockage.anne_scolaire, stockage.Num, validation2.Num, validation3.Num,"
										+ " stockage.Num, validation.Classe, validation2.Classe, validation3.Classe, stockage.Classe, validation.Annee,"
										+ "validation2.Annee, validation3.Annee "
										+ "from validation, validation2, validation3, stockage "
										+ "where validation.`Classe`= stockage.Classe and validation2.Classe = validation3.Classe "
										+ "and  validation2.Classe= validation.`Classe` and stockage.Num = validation.Num and validation2.Num = validation3.Num "
										+ "and validation3.Num = validation.Num and stockage.anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
												+ "and stockage.Classe='" +comboClasse.getSelectedItem().toString()+"'"
														+ " and validation.Classe='" +comboClasse.getSelectedItem().toString()+"'"
														+ " and validation2.Classe='" +comboClasse.getSelectedItem().toString()+"'"
														+ " and validation3.Classe='" +comboClasse.getSelectedItem().toString()+"'"
														+ " and validation.Annee='" +comboAn.getSelectedItem().toString()+"' "
														+ " and validation2.Annee='" +comboAn.getSelectedItem().toString()+"' "
														+ " and validation3.Annee='" +comboAn.getSelectedItem().toString()+"' ")
														;


								ps.executeUpdate();
								init();
								JOptionPane.showMessageDialog(null, "Insertion Reussit Continuez SVP .... ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							/**/
							
							
							
						}
						
						
					}else {
						PreparedStatement ps;
						if(comboSemestre.getSelectedItem().toString().equals("1er Trimestre")){
							try {
								ps = cnx.prepareStatement("DELETE FROM `validationtotal` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
										+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								JOptionPane.showMessageDialog(null, "Initialisation 1111 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into validationtotal (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
										+ " `Annee`, `coefClasse` , `MOY`, rang1 ) "
										+ "  "
										+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
										+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve` , (select @rank:=0 ) p "
										+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
												+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Mise à Jour reussit 1111 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							
							
							try {
								ps = cnx.prepareStatement("insert into stockage ( Num, nomEtprenom, Classe, M1, M2, M3, anne_scolaire, elv_id ) "
										+ ""
										+ "select distinct moyeleve.Num, note.nom_et_prenom, moyeleve.Classe, moyeleve.MOY as M1, moyeleve1.MOY as M2, moyeleve2.MOY as M3, anne_scolaire , id_elv  "
										+ "from moyeleve, moyeleve1, moyeleve2, note "
										+ "where moyeleve.Classe = '" +comboClasse.getSelectedItem().toString()+"' "
										+ "and moyeleve.Num = moyeleve1.Num "
										+ "and moyeleve1.Num = moyeleve2.Num and moyeleve2.Num = id_elv   "
										+ "and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Insertion Reussit Continuez SVP .... ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							
							
							
						}else if(comboSemestre.getSelectedItem().toString().equals("2ème Trimestre")){
							try {
								ps = cnx.prepareStatement("DELETE FROM `validationtotal` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
										+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Initialisation 2222 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into validationtotal (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
										+ " `Annee`, `coefClasse` , `MOY`, rang2 ) "
										+ "  "
										+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
										+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve1` , (select @rank:=0 ) p "
										+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
												+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Mise à Jour reussit 2222 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
						} else if (comboSemestre.getSelectedItem().toString().equals("3ème Trimestre")){
							try {
								ps = cnx.prepareStatement("DELETE FROM `validationtotal` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
										+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Initialisation 3333 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into validationtotal (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
										+ " `Annee`, `coefClasse` , `MOY`, rang3 ) "
										+ "  "
										+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
										+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve2` , (select @rank:=0 ) p "
										+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
												+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Mise à Jour reussit 3333 ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							try {
								ps = cnx.prepareStatement("insert into stockage ( Num, nomEtprenom, Classe, M1, M2, M3, anne_scolaire, elv_id ) "
										+ "select distinct moyeleve.Num, note.nom_et_prenom, moyeleve.Classe, moyeleve.MOY as M1, moyeleve1.MOY as M2, moyeleve2.MOY as M3, anne_scolaire , id_elv  "
										+ "from moyeleve, moyeleve1, moyeleve2, note "
										+ "where moyeleve.Classe = '" +comboClasse.getSelectedItem().toString()+"' "
										+ "and moyeleve.Num = moyeleve1.Num "
										+ "and moyeleve1.Num = moyeleve2.Num and moyeleve2.Num = id_elv   "
										+ "and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								//JOptionPane.showMessageDialog(null, "Insertion Reussit Continuez SVP .... ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							
							
							
							try {
								ps = cnx.prepareStatement(""
										+ "INSERT INTO `stockage1`(`Num`, `nomEtprenom`, `Classe`, `M1`, `rang1`, `M2`, `rang2`, `M3`, `rang3`, `anne_scolaire`, `elv_id` , `Num2`, `Num3`,"
										+ "`Nums`, `valClasse`, `val2Classe`, `val3Classe`, `stocClasse`)"
										+ " select distinct validation.Num , stockage.nomEtprenom, validation.classe, stockage.M1, validation.rang1, stockage.M2, validation2.rang2, stockage.M3, "
										+ "validation3.rang3, stockage.anne_scolaire, stockage.Num, validation2.Num, validation3.Num,"
										+ " stockage.Num, validation.Classe, validation2.Classe, validation3.Classe, stockage.Classe "
										+ "from validation, validation2, validation3, stockage "
										+ "where validation.`Classe`= stockage.Classe and validation2.Classe = validation3.Classe "
										+ "and  validation2.Classe= validation.`Classe` and stockage.Num = validation.Num and validation2.Num = validation3.Num "
										+ "and validation3.Num = validation.Num and stockage.anne_scolaire ='"+comboAn.getSelectedItem().toString()+"' "
												+ "and stockage.Classe='" +comboClasse.getSelectedItem().toString()+"'");

								ps.executeUpdate();
								init();
								JOptionPane.showMessageDialog(null, "Insertion Reussit Continuez SVP .... ");
								} catch (SQLException e) {
								e.printStackTrace();
								}
							/**/
						}
						
					}
					
					
					
					/**/
				
				
			}
		});
		btnValider.setBounds(350, 8, 256, 23);
		panel_4.add(btnValider);
		
		JLabel label = new JLabel("ID de Monsieur");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD, 13));
		label.setBounds(619, 42, 104, -11);
		panel_4.add(label);
		
		textField = new JTextField();
		textField.setEnabled(false);
		textField.setEditable(false);
		textField.setColumns(10);
		textField.setBounds(733, 49, 65, -17);
		panel_4.add(textField);
		
		JLabel label_2 = new JLabel("CAT :");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Tahoma", Font.BOLD, 13));
		label_2.setBounds(803, 42, 54, -11);
		panel_4.add(label_2);
		
		textField_1 = new JTextField();
		textField_1.setEnabled(false);
		textField_1.setEditable(false);
		textField_1.setColumns(10);
		textField_1.setBounds(855, 42, 98, -11);
		panel_4.add(textField_1);
		
		JLabel lblEffectifTotalDes = new JLabel("Effectif total des \u00E9l\u00E8ves de la classe :");
		lblEffectifTotalDes.setHorizontalAlignment(SwingConstants.CENTER);
		lblEffectifTotalDes.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblEffectifTotalDes.setBounds(648, 379, 269, 15);
		getContentPane().add(lblEffectifTotalDes);
		
		textTotalEleve = new JTextField();
		textTotalEleve.setEnabled(false);
		textTotalEleve.setBackground(Color.WHITE);
		textTotalEleve.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTotalEleve.setText("0");
		textTotalEleve.setHorizontalAlignment(SwingConstants.CENTER);
		textTotalEleve.setColumns(10);
		textTotalEleve.setBounds(910, 379, 63, 15);
		getContentPane().add(textTotalEleve);
		
		textTotalEleveCompo = new JTextField();
		textTotalEleveCompo.setBackground(Color.WHITE);
		textTotalEleveCompo.setFont(new Font("Tahoma", Font.BOLD, 11));
		textTotalEleveCompo.setEnabled(false);
		textTotalEleveCompo.setEditable(false);
		textTotalEleveCompo.setHorizontalAlignment(SwingConstants.CENTER);
		textTotalEleveCompo.setText("0");
		textTotalEleveCompo.setColumns(10);
		textTotalEleveCompo.setBounds(910, 402, 63, 15);
		getContentPane().add(textTotalEleveCompo);
		
		lblEffectifTotalDes_1 = new JLabel("Effectif total des \u00E9l\u00E8ves ayant des notes :");
		lblEffectifTotalDes_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblEffectifTotalDes_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblEffectifTotalDes_1.setBounds(648, 405, 269, 17);
		getContentPane().add(lblEffectifTotalDes_1);
		//BindCombo();
		//BindComboCour();
		BindCombo2();
		BindComboan();
		desactiveValidation();
		//BindComboCour();
		//updateTable2();
		init();
		//gest();
	}
	
	private boolean isNumeric(){
	
		
		try {
			Integer.parseInt(noteExamen.getText().toString());
			System.out.println("Entrée numerique");
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
			rs = st.executeQuery("SELECT distinct clsEns FROM enseignantel where idEns = '"+Integer.parseInt(textIDEns.getText().toString())+"'");
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
		    	comboCours.addItem(rs.getString(1));
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
			//rs = st.executeQuery("SELECT `cls_nom` FROM classe where coef_classe != '0' ");
			rs = st.executeQuery("SELECT DISTINCT clsEns FROM `enseignantel`");
			//SELECT DISTINCT clsEns FROM `enseignantel` WHERE nomEns = 'samba
		    while(rs.next()){
		    	 comboClasse.addItem(rs.getString(1));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	
	/////////////////////////////////////////////////////////////////////////////////////
	public boolean checkput(){
		if(note1 == null  || note2== null  || DS1 == null  || DS2== null || comboCours.getSelectedItem() == null  ||
				comboAn.getSelectedItem() == null || comboSemestre.getSelectedItem() == null || 
						comboEleve.getSelectedItem().equals("Choix")){
			return false;
		} else{
			return true;
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
		/**/
		note1.setText("0.0");
		note2.setText("0.0");
		DS1.setText("0.0");
		DS2.setText("0.0");
		noteExamen.setText("0.0");
				
	}
	
	
	
	
	public void Remplissage(){
		System.out.println(Integer.parseInt(comboNumero.getSelectedItem().toString()));
		String sql = "select  note_id, nom_et_prenom ,  matiere, note1, note2,  DS_1, DS_2, note_Examen , classe "
		+ " from note where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
		
				+ " and classe = '"+comboClasse.getSelectedItem().toString()+"'"
				+ "and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'"
						+ "and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' "
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
	
	

	public void RemplissageMoy(){
		
		if(comboSemestre.getSelectedItem().toString().equals("1er Trimestre")){
			String sql2 = "SELECT `Num`,`Nom`,`Semestre`, `SCoef`, Classe ,`SMG`, `SMGC`, `MOY`, `Annee`, `coefClasse` FROM `moyeleve` WHERE  "
					+ " `Annee` = '"+comboAn.getSelectedItem().toString()+"' "
							+ " AND  Classe = '"+comboClasse.getSelectedItem().toString()+"'"
									+ " AND Semestre = '"+comboSemestre.getSelectedItem().toString()+"'";
			//and `Annee` = '"+comboAn.getSelectedItem().toString()+"' `Num` = '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"'
			try {
				
				prepared = cnx.prepareStatement(sql2);
				resultat = prepared.executeQuery();
				tablemoy.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+e);
			
				}
		}else if(comboSemestre.getSelectedItem().toString().equals("2ème Trimestre")){
			
			String sql3 = "SELECT `Num`,`Nom`,`Semestre`, `SCoef`, Classe ,`SMG`, `SMGC`, `MOY`, `Annee`, `coefClasse` FROM `moyeleve1` WHERE  "
					+ " `Annee` = '"+comboAn.getSelectedItem().toString()+"' "
							+ " AND  Classe = '"+comboClasse.getSelectedItem().toString()+"'"
									+ " AND Semestre = '"+comboSemestre.getSelectedItem().toString()+"'";
			
			try {
				
				prepared = cnx.prepareStatement(sql3);
				resultat = prepared.executeQuery();
				tablemoy.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+e);
			
				}
			
		}
		if(comboSemestre.getSelectedItem().toString().equals("3ème Trimestre")){
			 
			String sql = "SELECT `Num`,`Nom`,`Semestre`, `SCoef`, Classe ,`SMG`, `SMGC`, `MOY`, `Annee`, `coefClasse` FROM `moyeleve2` WHERE "
			
					+ " `Annee` = '"+comboAn.getSelectedItem().toString()+"' "
							+ " AND  `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
									+ " AND `Semestre` = '"+comboSemestre.getSelectedItem().toString()+"' ";//AND Semestre = '"+comboSemestre.getSelectedItem().toString()+"'
			
			try {
				
				prepared = cnx.prepareStatement(sql);
				resultat = prepared.executeQuery();
				tablemoy.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e) {
				// TODO Auto-generated catch block 
				System.out.println("Erreur 1 "+e);
			
				}
			
		}
		
		
		
		
	}
	
	
	
	
	
	
	public void Remplissage2(){
		
		System.out.println(Integer.parseInt(comboNumero.getSelectedItem().toString()));
		String sql = "select  note_id, nom_et_prenom ,  matiere, note1, note2,  DS_1, DS_2, note_Examen , classe "
		+ " from note2 where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
				
				+ " and classe = '"+comboClasse.getSelectedItem().toString()+"'"
				+ "and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'"
						+ "and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' "
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
		String sql = "select  note_id, nom_et_prenom ,  matiere, note1, note2,  DS_1, DS_2, note_Examen , classe "
		+ " from note3 where  id_elv= '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' "
			
				+ " and classe = '"+comboClasse.getSelectedItem().toString()+"'"
				+ "and anne_scolaire = '"+comboAn.getSelectedItem().toString()+"'"
						+ "and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' "
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
	
	public boolean gest(){
		BindComboCour();
		if(textCat.getText().toString().equals("gestionnaire")){
			Supprimer.setEnabled(false);
		}
		
		return true ;
	}
	
	
	public void nb(){
		
		
		
		String sqlT = "select count(`elv_id`) from `eleve` as e, classe as c "
				+ "WHERE  e.`elv_cls` = '"+comboClasse.getSelectedItem().toString()+"'"
						+ " and c.id_classe = e.`elv_cls` and e.anne_scolaire='"+comboAn.getSelectedItem().toString()+"'";
		
		/*select count(`elv_id`) from `eleve` as e, classe as c WHERE  e.`elv_cls` = '"+comboClasse.getSelectedItem().toString()+"' 
		 and c.id_classe = e.`elv_cls` and e.anne_scolaire='"+comboAn.getSelectedItem().toString()+"'
		 * SELECT `elv_id`, `elv_nom`, `elv_prenom`, `sexe`, `Date_de_naissance`, `Lieu_de_naissance`, `Nom_du_pere`, 
		 * `Nom_de_la_mere`, `Quartier`, `Adresse`, `image`, `nomEtprenom`, `elv_cls`, `anne_scolaire`, `cat_eleve` FROM `eleve`
		 * 
		 * 
		 * */
			try {
				prepared = cnx.prepareStatement(sqlT);
				resultat = prepared.executeQuery();
				while (resultat.next()) {
					
				textTotalEleve.setText(String.valueOf(resultat.getInt("count(`elv_id`)")));
				//lblCy.setText(String.valueOf(resultat.getInt("id_cycle")));
		
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if(comboSemestre.getSelectedItem().toString().equals("1er Trimestre")){
				
				
				String sqlTNote = "select count(Num) from moyeleve "
						+ " WHERE  Classe = '"+comboClasse.getSelectedItem().toString()+"'  and Annee ='"+comboAn.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sqlTNote );
						resultat = prepared.executeQuery();
						while (resultat.next()) {
							
						textTotalEleveCompo.setText(String.valueOf(resultat.getInt("count(Num)")));
						//lblCy.setText(String.valueOf(resultat.getInt("id_cycle")));
				
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			}else if(comboSemestre.getSelectedItem().toString().equals("2ème Trimestre")){
				
				String sqlTNote = "select count(Num) from moyeleve1 "
						+ " WHERE  Classe = '"+comboClasse.getSelectedItem().toString()+"' and Annee ='"+comboAn.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sqlTNote);
						resultat = prepared.executeQuery();
						while (resultat.next()) {
							
						textTotalEleveCompo.setText(String.valueOf(resultat.getInt("count(Num)")));
						//lblCy.setText(String.valueOf(resultat.getInt("id_cycle")));
				
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
				
			}else if(comboSemestre.getSelectedItem().toString().equals("3ème Trimestre")){
				
				String sqlTNote = "select count(Num) from moyeleve2 "
						+ " WHERE  Classe = '"+comboClasse.getSelectedItem().toString()+"' and Annee ='"+comboAn.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sqlTNote );
						resultat = prepared.executeQuery();
						while (resultat.next()) {
							
						textTotalEleveCompo.setText(String.valueOf(resultat.getInt("count(Num)")));
						//lblCy.setText(String.valueOf(resultat.getInt("id_cycle")));
				
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					
			}
		
	}
	
	public void desactiveValidation(){
		
		int t = Integer.parseInt(textTotalEleve.getText().toString());
		int t1 = Integer.parseInt(textTotalEleveCompo.getText().toString());
		
		if(t == t1 && t!= 0 && t1!= 0){
			//btnValider.setEnabled(true);
		}else{
			//btnValider.setEnabled(false);
		}
	}
	
	
	public void validationTables(int flag){
		
		////////////////////////////////////////////************************** 1er Trimestre *******************************////////////////////////////////////////////////////
		
		 
		
		if(flag == 0){
			PreparedStatement ps;
			if(comboSemestre.getSelectedItem().toString().equals("1er Trimestre")){
				try {
					ps = cnx.prepareStatement("DELETE FROM `validation` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
							+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Initialisation 11111 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
				try {
					ps = cnx.prepareStatement("insert into validation (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
							+ " `Annee`, `coefClasse` , `MOY`, rang1 ) "
							+ "  "
							+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
							+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve` , (select @rank:=0 ) p "
							+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Mise à Jour reussit 1111 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			}else if(comboSemestre.getSelectedItem().toString().equals("2ème Trimestre")){
				try {
					ps = cnx.prepareStatement("DELETE FROM `validation2` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
							+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Initialisation 2222 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
				try {
					ps = cnx.prepareStatement("insert into validation2 (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
							+ " `Annee`, `coefClasse` , `MOY`, rang2 ) "
							+ "  "
							+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
							+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve1` , (select @rank:=0 ) p "
							+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Mise à Jour reussit 2222 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			} else if (comboSemestre.getSelectedItem().toString().equals("3ème Trimestre")){
				try {
					ps = cnx.prepareStatement("DELETE FROM `validation3` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
							+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Initialisation 3333 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
				try {
					ps = cnx.prepareStatement("insert into validation3(`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
							+ " `Annee`, `coefClasse` , `MOY`, rang3 ) "
							+ "  "
							+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
							+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve2` , (select @rank:=0 ) p "
							+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Mise à Jour reussit 3333 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			}
			
			
		}else {
			PreparedStatement ps;
			if(comboSemestre.getSelectedItem().toString().equals("1er Trimestre")){
				try {
					ps = cnx.prepareStatement("DELETE FROM `validation` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
							+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Initialisation 1111 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
				try {
					ps = cnx.prepareStatement("insert into validation (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
							+ " `Annee`, `coefClasse` , `MOY`, rang1 ) "
							+ "  "
							+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
							+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve` , (select @rank:=0 ) p "
							+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Mise à Jour reussit 1111 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			}else if(comboSemestre.getSelectedItem().toString().equals("2ème Trimestre")){
				try {
					ps = cnx.prepareStatement("DELETE FROM `validation2` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
							+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Initialisation 2222 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
				try {
					ps = cnx.prepareStatement("insert into validation2 (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
							+ " `Annee`, `coefClasse` , `MOY`, rang2 ) "
							+ "  "
							+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
							+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve1` , (select @rank:=0 ) p "
							+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Mise à Jour reussit 2222 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			} else if (comboSemestre.getSelectedItem().toString().equals("3ème Trimestre")){
				try {
					ps = cnx.prepareStatement("DELETE FROM `validation3` WHERE `Classe` = '"+comboClasse.getSelectedItem().toString()+"'"
							+ " and `Annee` = '"+comboAn.getSelectedItem().toString()+"' and Semestre = '"+comboSemestre.getSelectedItem().toString()+"'");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Initialisation 3333 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
				try {
					ps = cnx.prepareStatement("insert into validation3 (`Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, "
							+ " `Annee`, `coefClasse` , `MOY`, rang3 ) "
							+ "  "
							+ " SELECT `Nom`, `Classe`, `Num`, `Semestre`, `SCoef`, `SMG`, `SMGC`, `Annee`, "
							+ "`coefClasse` , `MOY`, @rank :=@rank+1 as rang FROM `moyeleve2` , (select @rank:=0 ) p "
							+ "WHERE  Annee = '"+comboAn.getSelectedItem().toString()+"' "
									+ "and Classe = '" +comboClasse.getSelectedItem().toString()+"'  and Semestre = '"+comboSemestre.getSelectedItem().toString()+"' ORDER BY MOY desc ");

					ps.executeUpdate();
					init();
					JOptionPane.showMessageDialog(null, "Mise à Jour reussit 3333 ");
					} catch (SQLException e) {
					e.printStackTrace();
					}
				
			}
			
		}
		
		
	}
}
