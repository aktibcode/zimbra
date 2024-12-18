package metier;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;

import javax.swing.ImageIcon;
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
import entite.matiereEL;
import entite.noteEL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import com.mysql.jdbc.ResultSetMetaData;

import java.awt.Color;


@SuppressWarnings("unused")
public class ifNoteEleves2 extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	private JTextField note1;
	private JTextField note2;
	private JTextField noteExamen;
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
	private JButton btnSup;
	
	
	
	
	public JButton getBtnSup() {
		return btnSup;
	}



	public void setBtnSup(JButton btnSup) {
		this.btnSup = btnSup;
	}



	public ifNoteEleves2() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				init();
			}
		});
		//setClosed(true);
		setTitle("Gestion des Notes ");
		setBounds(300, 128, 999, 492);
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
		comboEleve.setBounds(317, 55, 237, 27);
		panel.add(comboEleve);
		
		comboNumero = new JComboBox();
		comboNumero.setBounds(564, 55, 155, 27);
		panel.add(comboNumero);
		comboNumero.setModel(new DefaultComboBoxModel(new String[] {"48"}));
		
		lblChoisissezUnEleve = new JLabel("Choisissez un Eleve:");
		lblChoisissezUnEleve.setBounds(316, 21, 238, 27);
		panel.add(lblChoisissezUnEleve);
		lblChoisissezUnEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoisissezUnEleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		lblNumeroEleve = new JLabel("Numero Eleve :");
		lblNumeroEleve.setBounds(563, 21, 155, 27);
		panel.add(lblNumeroEleve);
		lblNumeroEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroEleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		
		lblSemestre = new JLabel("Semestre :");
		lblSemestre.setHorizontalAlignment(SwingConstants.CENTER);
		lblSemestre.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblSemestre.setBounds(708, 11, 80, 27);
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
				
			}
		});
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboSemestre.setBounds(798, 11, 155, 27);
		panel.add(comboSemestre);
		
		comboAn = new JComboBox();
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.setBounds(11, 55, 122, 27);
		panel.add(comboAn);
		
		label_1 = new JLabel("Ann\u00E9e:");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		label_1.setBounds(10, 21, 122, 27);
		panel.add(label_1);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(779, 61, 78, 21);
		panel.add(lblNewLabel);
		comboEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboNumero.removeAllItems();
				Donne mk = new Donne();
				ArrayList<eleveEL> list2 = mk.getData3(comboEleve.getSelectedItem().toString());
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
				init();
				
			}
		});
		comboClasse.addActionListener(new ActionListener() {
			
			@SuppressWarnings("unchecked")
			public void actionPerformed(ActionEvent arg0) {
				comboEleve.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
					
				comboNumero.removeAllItems();
				//comboEleve.setSelectedItem("");
				if(comboClasse.getSelectedItem().equals(false)){
					System.out.println("Aucune");
					comboEleve.removeAllItems();
				}else{
					//ConnexionMySql mk = new ConnexionMySql();
					MethodeRecetteDepense mk = new MethodeRecetteDepense();
					ArrayList<eleveEL> list = mk.getData2(comboClasse.getSelectedItem().toString(), comboAn.getSelectedItem().toString());
					for(int i = 0; i < list.size(); i++ ){
						comboEleve.addItem(list.get(i).getNomEtprenom());
						
					}
					
				}
				//updateTable2();
			init();}
			
			
		});
		
		panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Infos Relatives \u00E0 la Mati\u00E8re", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(10, 115, 963, 108);
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
				}
				init();
			}
		});
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 241, 963, 149);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			private JLabel num;

			@Override
			public void mouseClicked(MouseEvent arg0) {
				if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					lblNewLabel.setText(id);
					String sql = "select * from note where note_id ='"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
	                            
							//matiere, coef, 				
						if(resultat.next()){
							
						lblNewLabel.setText((String.valueOf(resultat.getInt("note_id"))));
						comboCours.setSelectedItem(resultat.getString("matiere"));
						comboCoef.setSelectedItem(resultat.getString("coef"));
						note1.setText((String.valueOf(resultat.getDouble("note1"))));
						note2.setText((String.valueOf(resultat.getDouble("note2"))));
						DS1.setText((String.valueOf(resultat.getDouble("DS_1"))));
						DS2.setText((String.valueOf(resultat.getDouble("DS_2"))));
						noteExamen.setText((String.valueOf(resultat.getDouble("note_Examen")))); 
						
						}
						System.out.println("Click effectué et num est "+table.getSelectedRow() + "  "+id );
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
					}
					
				}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					lblNewLabel.setText(id);
					String sql = "select * from note2 where note_id ='"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
	                            
							//matiere, coef, 				
						if(resultat.next()){
						lblNewLabel.setText((String.valueOf(resultat.getInt("note_id"))));
						comboCours.setSelectedItem(resultat.getString("matiere"));
						comboCoef.setSelectedItem(resultat.getString("coef"));
						note1.setText((String.valueOf(resultat.getDouble("note1"))));
						note2.setText((String.valueOf(resultat.getDouble("note2"))));
						DS1.setText((String.valueOf(resultat.getDouble("DS_1"))));
						DS2.setText((String.valueOf(resultat.getDouble("DS_2"))));
						noteExamen.setText((String.valueOf(resultat.getDouble("note_Examen")))); 
						
						}
						System.out.println("Click effectué et num est "+table.getSelectedRow() + "  "+id );
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
					}
				}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre")){
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					
					String sql = "select * from note3 where note_id ='"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
	                            
							//matiere, coef, 				
						if(resultat.next()){
							lblNewLabel.setText((String.valueOf(resultat.getInt("note_id"))));
						comboCours.setSelectedItem(resultat.getString("matiere"));
						comboCoef.setSelectedItem(resultat.getString("coef"));
						note1.setText((String.valueOf(resultat.getDouble("note1"))));
						note2.setText((String.valueOf(resultat.getDouble("note2"))));
						DS1.setText((String.valueOf(resultat.getDouble("DS_1"))));
						DS2.setText((String.valueOf(resultat.getDouble("DS_2"))));
						noteExamen.setText((String.valueOf(resultat.getDouble("note_Examen")))); 
						
						}
						System.out.println("Click effectué et num est "+table.getSelectedRow() + "  "+id );
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
					}
				}
				
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(10, 401, 963, 48);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JButton button = new JButton("Ajouter");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(checkput()){
					String sql = "select note.matiere from note where note.id_elv = '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' and "
							+ "note.Semestre = '"+comboSemestre.getSelectedItem().toString()+"'  and note.classe = '"+comboClasse.getSelectedItem().toString()+"'";
					
					
					String sql2 = "select  note2.matiere from note2 where  note2.id_elv = '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' and "
									+ "note2.Semestre = '"+comboSemestre.getSelectedItem().toString()+"'and "
											+ " note2.classe = '"+comboClasse.getSelectedItem().toString()+"'";
					
					String sql3 = "select  note3.matiere from note3 where  note3.id_elv = '"+Integer.parseInt(comboNumero.getSelectedItem().toString())+"' and "
							+ "note3.Semestre = '"+comboSemestre.getSelectedItem().toString()+"'and "
									+ " note3.classe = '"+comboClasse.getSelectedItem().toString()+"'";
					
					
					try {
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
	                    	
	                    	if(comboCours.getSelectedItem().equals("CONDUITE")){
	                    		
								
								
		                    	if(Double.parseDouble(noteExamen.getText()) <= 20){
		                    		
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
													+ "moy_Gen, id_elv) "
													+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
											ps.setDouble(11, Double.parseDouble(noteExamen.getText().toString()));
											ps.setDouble(12, mcls);
											ps.setDouble(13, mclGen);
											ps.setString(14, comboNumero.getSelectedItem().toString());
											ps.executeUpdate();
											JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
											//&& !textFieldSum.getText().toString().equals("0.0")
											} catch (SQLException e) {
											e.printStackTrace();
										}
										Remplissage();
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
													+ "moy_Gen, id_elv) "
													+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
											ps.setDouble(11, Double.parseDouble(noteExamen.getText().toString()));
											ps.setDouble(12, mcls);
											ps.setDouble(13, mclGen);
											ps.setString(14, comboNumero.getSelectedItem().toString());
											ps.executeUpdate();
											JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
											//&& !textFieldSum.getText().toString().equals("0.0")
											} catch (SQLException e) {
											e.printStackTrace();
										}
										Remplissage2();
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
													+ "moy_Gen, id_elv) "
													+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
											ps.setDouble(11, Double.parseDouble(noteExamen.getText().toString()));
											ps.setDouble(12, mcls);
											ps.setDouble(13, mclGen);
											ps.setString(14, comboNumero.getSelectedItem().toString());
											ps.executeUpdate();
											JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
											//&& !textFieldSum.getText().toString().equals("0.0")
											} catch (SQLException e) {
											e.printStackTrace();
										}
										Remplissage3();
		                    		}
		                			    
		                    	
		                    
								init();
		                			}else{
		                				JOptionPane.showMessageDialog(null, "Vous avez entré une valeur plus grande que la moyenne. Modifiez SVP !!!");
		                				
		                			}
	                    	
	                    	
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
	    								Double mclGen = (mcls + Double.parseDouble(noteExamen.getText().toString()))/2;
	    								ps = cnx.prepareStatement("INSERT into note (anne_scolaire, Semestre, classe,"
	    										+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
	    										+ "moy_Gen, id_elv) "
	    										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
	    								ps.executeUpdate();
	    								JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
	    								//&& !textFieldSum.getText().toString().equals("0.0")
	    								} catch (SQLException e) {
	    								e.printStackTrace();
	    							}
	    							 Remplissage();
	                			}
	                			else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
	                				PreparedStatement ps;
	    							try {
	    								Double mcls = ((Double.parseDouble(note1.getText().toString()))+
	    										(Double.parseDouble(note2.getText().toString()))+
	    										(Double.parseDouble(DS1.getText().toString())) + (Double.parseDouble(DS2.getText().toString())))/4;
	    								Double mclGen = (mcls + Double.parseDouble(noteExamen.getText().toString()))/2;
	    								ps = cnx.prepareStatement("INSERT into note2 (anne_scolaire, Semestre, classe,"
	    										+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
	    										+ "moy_Gen, id_elv) "
	    										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
	    								ps.executeUpdate();
	    								JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
	    								//&& !textFieldSum.getText().toString().equals("0.0")
	    								} catch (SQLException e) {
	    								e.printStackTrace();
	    							}
	    							Remplissage2();
	                			}else {
	                				PreparedStatement ps;
	    							try {
	    								Double mcls = ((Double.parseDouble(note1.getText().toString()))+
	    										(Double.parseDouble(note2.getText().toString()))+
	    										(Double.parseDouble(DS1.getText().toString())) + (Double.parseDouble(DS2.getText().toString())))/4;
	    								//Double mclGen = mcls*(Double.parseDouble(comboCoef.getSelectedItem().toString()));
	    								Double mclGen = (mcls + Double.parseDouble(noteExamen.getText().toString()))/2;
	    								ps = cnx.prepareStatement("INSERT into note3 (anne_scolaire, Semestre, classe,"
	    										+ "nom_et_prenom, matiere, coef, note1, note2, DS_1, DS_2, note_Examen, moy_cls, "
	    										+ "moy_Gen, id_elv) "
	    										+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

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
	    								ps.executeUpdate();
	    								JOptionPane.showMessageDialog(null, "Enregistrement reussit 11111 ");
	    								//&& !textFieldSum.getText().toString().equals("0.0")
	    								} catch (SQLException e) {
	    								e.printStackTrace();
	    							}
	    							Remplissage3();
	                				
	                			}
	                			
	                			
	                    	
	                    
							init();
	                			}else{
	                				JOptionPane.showMessageDialog(null, "Vous avez entré une valeur plus grande que la moyenne. Modifiez SVP !!!");
	                				
	                			}
	                    	} catch (Exception e) {
	                    		JOptionPane.showMessageDialog(null,"Verifier votre saisie SVP !!! ");
							}
	                    ////
	                    	}
	                    }
	               } catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
					}
				}
				
				
				
			}
		});
		button.setBounds(10, 11, 125, 23);
		panel_2.add(button);
		
		JButton button_1 = new JButton("Modifier");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					
				
				Double mc = Double.parseDouble(noteExamen.getText().toString());
				Double mcls = ((Double.parseDouble(note1.getText().toString()))+
						(Double.parseDouble(note2.getText().toString()))+
						(Double.parseDouble(DS1.getText().toString())) + (Double.parseDouble(DS2.getText().toString())))/4;
				Double mclGen = (mcls + Double.parseDouble(noteExamen.getText().toString()))/2;
				
				if(comboSemestre.getSelectedItem().equals("1er Trimestre")){
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
					
				}else if(comboSemestre.getSelectedItem().equals("2ème Trimestre")){
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
				}else if(comboSemestre.getSelectedItem().equals("3ème Trimestre")){
					
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
						}
						
					}
					Remplissage3();
				}
				init();
				} catch (Exception e) {
					JOptionPane.showMessageDialog(null, "Veillez choisir une matière que vous voulez modifier");
				}
			}
		});
		button_1.setBounds(161, 11, 125, 23);
		panel_2.add(button_1);
		
		btnSup = new JButton("Supprimer");
		btnSup.setBounds(312, 11, 125, 23);
		panel_2.add(btnSup);
		
		JButton btnEditionRelevDe = new JButton("Edition Relev\u00E9 de Notes :");
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
						try {
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/releve1.jrxml");
							HashMap vNomEleve = new HashMap();
							
							vNomEleve.put("pClasse", comboClasse.getSelectedItem().toString());
							vNomEleve.put("pAn", comboAn.getSelectedItem().toString());
							vNomEleve.put("pMat", comboCours.getSelectedItem().toString());
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
		});
		btnEditionRelevDe.setBounds(465, 11, 153, 23);
		panel_2.add(btnEditionRelevDe);
		
		JButton button_4 = new JButton("Fermer");
		button_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ifNoteEleves2.this.dispose();
			}
		});
		button_4.setBounds(648, 11, 125, 23);
		panel_2.add(button_4);
		BindCombo();
		BindComboCour();
		BindCombo2();
		BindComboan();
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
			rs = st.executeQuery("SELECT cr_titre FROM cours ");
		    while(rs.next()){
		    	comboCours.addItem(rs.getString(1));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public boolean checkput(){
		if(note1 == null  || note2== null || noteExamen == null || DS1 == null  || DS2== null || comboCours.getSelectedItem() == null){
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
		/*comboClasse.setSelectedItem("Choix");
		comboCours.setSelectedItem("Choix");
		comboEleve.setSelectedItem("Choix");
		comboNumero.setSelectedItem("Choix");
		comboSemestre.setSelectedItem("Choix");*/
		note1.setText("");
		note2.setText("");
		DS1.setText("");
		DS2.setText("");
		noteExamen.setText("");
				
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
}
