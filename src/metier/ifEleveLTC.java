package metier;
import groovy.ui.Console;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.TextField;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
























import javax.swing.text.MaskFormatter;

import net.proteanit.sql.DbUtils;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import com.toedter.calendar.JDateChooser;

import connec.ConnexionMySql;

import javax.swing.SwingConstants;

import java.awt.Font;

import javax.swing.border.TitledBorder;
import javax.swing.JFormattedTextField;
import javax.swing.JTextArea;

import java.awt.TextArea;

import javax.swing.DropMode;
import javax.swing.ListSelectionModel;
import javax.swing.UIManager;




/**
 * @author Isidore
 *
 */
@SuppressWarnings("unused")
public class ifEleveLTC extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	
	Connection cnx =null;
	PreparedStatement prepared = null;
	PreparedStatement prepared1 = null;
	ResultSet resultat = null;
	ResultSet resultat2 = null;
	
	
	private JTable table;
	private TextField idEle;
	private TextField nomEle;
	private TextField prenomEle;
	private TextField lieuNaisEle;
	private TextField nomPereEle;
	private TextField nomMereEle;
	private TextField quartierEle;
	private String st = null ;
	@SuppressWarnings("rawtypes")
	private JComboBox combo;
	private JTextField idEle1;

	private JComboBox comboAnSco;

	private JComboBox comboStat;

	private TextField textFieldSexe;

	private TextField textFieldAdresse;

	private TextField textField;

	private JLabel classe;

	private JFormattedTextField dateF;
	private JTextField idFiche;

	private JButton btnFiche;

	private JButton btnEditionListe;

	private JButton btnFerm;

	private JLabel lbTotal;

	private JButton btnActualiser;

	private JButton btnAjouter;

	private JButton btnModifier;

	private JButton btnSupprimer;

	private TextArea depart;

	private JButton Recherche;
	private JTextField catEnsEle;
	private JTextField textRechNom;
	private JTextField textExclut;
	
	
	
	
	
	public JTextField getCatEnsEle() {
		return catEnsEle;
	}

	public void setCatEnsEle(JTextField catEnsEle) {
		this.catEnsEle = catEnsEle;
	}

	@SuppressWarnings({ "rawtypes" })
	public ifEleveLTC() {
		getContentPane().setBackground(UIManager.getColor("MenuItem.selectionBackground"));
		setClosable(true);
		setIconifiable(true);
		setResizable(true);
		setMaximizable(true);
	
			setTitle("Gestion des Eleves ");
			setBounds(64, 128, 1354, 537);
		    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
			getContentPane().setLayout(null);
			
			JPanel panel = new JPanel();
			panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Infos relatives \u00E0 l'\u00E9l\u00E8ves", TitledBorder.LEADING, TitledBorder.TOP, null, null));
			panel.setBounds(20, 11, 1307, 171);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblAdresse = new JLabel("Adresse :");
			lblAdresse.setHorizontalAlignment(SwingConstants.CENTER);
			lblAdresse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			lblAdresse.setBounds(808, 45, 53, 20);
			panel.add(lblAdresse);
			
			JLabel lblAnne = new JLabel("Ann\u00E9e  :");
			lblAnne.setBounds(808, 80, 53, 23);
			panel.add(lblAnne);
			lblAnne.setHorizontalAlignment(SwingConstants.CENTER);
			lblAnne.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
			//gest();
			comboAnSco = new JComboBox();
			comboAnSco.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String cls = combo.getSelectedItem().toString();
					
					//System.out.println(cls);
					String sql = "select  elv_id, elv_nom, elv_prenom , sexe, elv_cls,  Date_de_naissance, Lieu_de_naissance, "
							+ "Nom_du_pere, Nom_de_la_mere, Quartier, anne_scolaire from eleve where elv_cls = '"+cls+"'"
									+ "and anne_scolaire ='"+comboAnSco.getSelectedItem().toString()+"'";
					
					try {
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							table.setModel(DbUtils.resultSetToTableModel(resultat));
					} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
					}
					classe.setText(combo.getSelectedItem().toString());
					
					String sqlT = "select count(elv_id) from eleve where elv_cls = '"+cls+"' and anne_scolaire ='"+comboAnSco.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sqlT);
						resultat = prepared.executeQuery();
						while (resultat.next()) {
							
							lbTotal.setText(String.valueOf(resultat.getInt("count(elv_id)")));
						}
						
						
						
					} catch (Exception e) {
						// TODO: handle exception
					}
					
				}
			});
			comboAnSco.setBounds(871, 81, 164, 22);
			panel.add(comboAnSco);
			comboAnSco.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
			
			textFieldAdresse = new TextField();
			textFieldAdresse.setColumns(10);
			textFieldAdresse.setBounds(869, 45, 164, 20);
			panel.add(textFieldAdresse);
			
			
			
			JLabel JLEleve = new JLabel("Nom Eleve :");
			JLEleve.setHorizontalAlignment(SwingConstants.CENTER);
			JLEleve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			JLEleve.setBounds(10, 45, 89, 20);
			panel.add(JLEleve);
			
			JLabel JprenomEleve = new JLabel("Prenom Eleve :");
			JprenomEleve.setHorizontalAlignment(SwingConstants.CENTER);
			JprenomEleve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			JprenomEleve.setBounds(275, 46, 89, 20);
			panel.add(JprenomEleve);
			
			JLabel JlieuNai = new JLabel("Lieu Naiss :");
			JlieuNai.setHorizontalAlignment(SwingConstants.CENTER);
			JlieuNai.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			JlieuNai.setBounds(536, 46, 73, 20);
			panel.add(JlieuNai);
			
			JLabel JnomPere = new JLabel("Nom Pere :");
			JnomPere.setHorizontalAlignment(SwingConstants.CENTER);
			JnomPere.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			JnomPere.setBounds(10, 83, 89, 20);
			panel.add(JnomPere);
			
			JLabel JnomMere = new JLabel("Nom Mere :");
			JnomMere.setHorizontalAlignment(SwingConstants.CENTER);
			JnomMere.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			JnomMere.setBounds(275, 88, 89, 20);
			panel.add(JnomMere);
			
			JLabel Jquartier = new JLabel("Quartier :");
			Jquartier.setHorizontalAlignment(SwingConstants.CENTER);
			Jquartier.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			Jquartier.setBounds(536, 88, 73, 20);
			panel.add(Jquartier);
			
			
			btnActualiser = new JButton("Actualiser");
			btnActualiser.setBounds(808, 141, 128, 20);
			panel.add(btnActualiser);
			
			JLabel JdateNai = new JLabel("Date Naiss :");
			JdateNai.setHorizontalAlignment(SwingConstants.CENTER);
			JdateNai.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			JdateNai.setBounds(275, 129, 89, 20);
			panel.add(JdateNai);
			
			JLabel NiveauEleve = new JLabel("Classe Eleve :");
			NiveauEleve.setHorizontalAlignment(SwingConstants.CENTER);
			NiveauEleve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			NiveauEleve.setBounds(10, 129, 89, 20);
			panel.add(NiveauEleve);
			
			
			
			/// Fin Methode
			////////////////////////////////
				btnActualiser.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				initialisation();
				
			}
				});
				combo = new JComboBox();
				combo.setBounds(105, 128, 83, 20);
				panel.add(combo);
				combo.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
				/// IDENTIFIANT pour recherche
				
				
				
				
				JButton btnInserezPhoto =  new JButton("Inserez Photo");
				btnInserezPhoto.setBounds(1045, 141, 143, 20);
				panel.add(btnInserezPhoto);
				
				idEle1 = new JTextField();
				idEle1.setHorizontalAlignment(SwingConstants.CENTER);
				idEle1.setBounds(946, 141, 43, 20);
				panel.add(idEle1);
				idEle1.setEnabled(false);
				idEle1.setEditable(false);
				idEle1.setColumns(10);
				
				JLabel lblSexe = new JLabel("Sexe :");
				lblSexe.setHorizontalAlignment(SwingConstants.CENTER);
				lblSexe.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				lblSexe.setBounds(536, 129, 73, 20);
				panel.add(lblSexe);
				
				comboStat = new JComboBox();
				comboStat.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						if(comboStat.getSelectedItem().equals("D") || textExclut.getText() == "Exclut"){
							btnAjouter.setEnabled(false);
							btnModifier.setEnabled(false);
							btnSupprimer.setEnabled(false);

						}else{
							btnAjouter.setEnabled(true);
							btnModifier.setEnabled(true);
							gest();
						}
						//initialisation();
					}
				});
				comboStat.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
				comboStat.setBounds(873, 110, 83, 20);
				panel.add(comboStat);
				
				JLabel lblStatut = new JLabel("Statut :");
				lblStatut.setHorizontalAlignment(SwingConstants.CENTER);
				lblStatut.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				lblStatut.setBounds(808, 110, 63, 20);
				panel.add(lblStatut);
				
				nomEle = new TextField();
				nomEle.setBounds(105, 45, 164, 20);
				panel.add(nomEle);
				nomEle.setColumns(10);
				
				quartierEle = new TextField();
				quartierEle.setBounds(609, 88, 164, 20);
				panel.add(quartierEle);
				quartierEle.setColumns(10);
				
				nomPereEle = new TextField();
				nomPereEle.setBounds(105, 83, 164, 20);
				panel.add(nomPereEle);
				nomPereEle.setColumns(10);
				
				nomMereEle = new TextField();
				nomMereEle.setBounds(366, 88, 164, 20);
				panel.add(nomMereEle);
				nomMereEle.setColumns(10);
				
				lieuNaisEle = new TextField();
				lieuNaisEle.setBounds(609, 46, 164, 20);
				panel.add(lieuNaisEle);
				lieuNaisEle.setColumns(10);
				
				textFieldSexe = new TextField();
				textFieldSexe.setColumns(10);
				textFieldSexe.setBounds(609, 129, 164, 20);
				panel.add(textFieldSexe);
				
				prenomEle = new TextField();
				prenomEle.setBounds(366, 46, 164, 20);
				panel.add(prenomEle);
				prenomEle.setColumns(10);
				
				JLabel lblNumEleve = new JLabel("N\u00B0 Eleve :");
				lblNumEleve.setHorizontalAlignment(SwingConstants.CENTER);
				lblNumEleve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
				lblNumEleve.setBounds(10, 19, 89, 20);
				panel.add(lblNumEleve);
				
				textField = new TextField();
				textField.setText("%%");
				textField.setFont(new Font("Dialog", Font.BOLD | Font.ITALIC, 12));
				textField.setColumns(10);
				textField.setBounds(105, 19, 196, 20);
				panel.add(textField);
				
				Recherche = new JButton("Rechecher par N\u00B0  :");
				
				Recherche.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						
						int flag = 1;
						
						if(!textField.getText().equals("")){
							
						
						
						String sql2 ="Select elv_id, elv_nom, elv_prenom, sexe, elv_cls, Date_de_naissance, Lieu_de_naissance, Nom_du_pere, Nom_de_la_mere, "
								+ "Quartier, Adresse,  nomEtprenom, anne_scolaire, cat_eleve from eleve where  elv_id = ?";
							
						  try {
							  
						prepared = cnx.prepareStatement(sql2);
						prepared.setInt(1, Integer.parseInt(textField.getText().toString()));
						//System.out.println( rechEleve.getText().toString());
						resultat = prepared.executeQuery();
						
						if(resultat.next()){
							
							flag = 0;
							String elv_nom = resultat.getString("elv_nom");
							String elv_prenom = resultat.getString("elv_prenom");
							String sexe = resultat.getString("sexe");
							String elv_cls = resultat.getString("elv_cls");
							String Date_de_naissance = resultat.getString("Date_de_naissance");
							String Nom_du_pere = resultat.getString("Nom_du_pere");
							String Nom_de_la_mere = resultat.getString("Nom_de_la_mere");
							String Quartier = resultat.getString("Quartier");
							String Adresse = resultat.getString("Adresse");
							String anne_scolaire = resultat.getString("anne_scolaire");
							String Lieu_de_naissance = resultat.getString("Lieu_de_naissance");
							String cat_eleve = resultat.getString("cat_eleve");
							idFiche.setText(resultat.getString("elv_id"));
							idEle1.setText(resultat.getString("elv_id"));
							nomEle.setText(elv_nom);
							prenomEle.setText(elv_prenom);
							textFieldSexe.setText(sexe);
							combo.setSelectedItem(elv_cls);
							dateF.setText(Date_de_naissance);
							nomPereEle.setText(Nom_du_pere);
							nomMereEle.setText(Nom_de_la_mere); 
							quartierEle.setText(Quartier);
							textFieldAdresse.setText(Adresse);//prepared.setString(11, textFieldAdresse.getText().toString());
							comboAnSco.setSelectedItem(anne_scolaire);
							lieuNaisEle.setText(Lieu_de_naissance);
							comboStat.setSelectedItem(cat_eleve);
							
							}else{
								System.out.println("Sortie");
								nomEle.setText("");
								prenomEle.setText("");
								combo.setSelectedItem("Choix");
								//dateChooser.setDateFormatString("");
								lieuNaisEle.setText("");
								nomPereEle.setText("");
								nomMereEle.setText("");
								quartierEle.setText("");
								//lblPhoto.setIcon(null);
								//lblPhoto.revalidate();
							}
						/*System.out.println( resultat.getString("elv_nom"));
						if(nomEle.getText().toString() == resultat.getString("elv_nom")){
						
						
						}*/
						} catch (SQLException ex) {
							ex.printStackTrace();
							
						}
						
						
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						
						
						
						
						
						
						
						
						
						
						
						}else{
							
						}

						if(flag!=0){
							JOptionPane.showMessageDialog(null, "Ce Numero n'existe pas ou Veillez donner un numero SVP !");
						}	
						gest();
					}
				});
				//textField.setText("");
				Recherche.setIcon(null);
				Recherche.setBounds(307, 18, 153, 23);
				panel.add(Recherche);
				
				classe = new JLabel("");
				classe.setFont(new Font("Tahoma", Font.PLAIN, 11));
				classe.setForeground(Color.LIGHT_GRAY);
				classe.setEnabled(false);
				classe.setBounds(190, 128, 79, 20);
				panel.add(classe);
				
				
				
				try{
					MaskFormatter tel = new MaskFormatter("##-##-####");
				dateF = new JFormattedTextField(tel);
				dateF.setBounds(366, 129, 164, 20);
				panel.add(dateF);
				
				idFiche = new JTextField();
				idFiche.setEnabled(false);
				idFiche.setEditable(false);
				idFiche.setBounds(999, 141, 43, 20);
				panel.add(idFiche);
				idFiche.setColumns(10);
				
				JButton btnRechecherParNom = new JButton("Rechecher par NOM  :");
				btnRechecherParNom.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int flag = 1;
						
						if(!textField.getText().equals("")){
							
						
						
						String sql2 ="Select elv_id, elv_nom, elv_prenom, sexe, elv_cls, Date_de_naissance, Lieu_de_naissance, Nom_du_pere, Nom_de_la_mere, "
								+ "Quartier, Adresse,  nomEtprenom, anne_scolaire, cat_eleve from eleve where  nomEtprenom like ? ";
							
						  try {
							  
						prepared = cnx.prepareStatement(sql2);
						prepared.setString(1, textField.getText().toString());
						//System.out.println( rechEleve.getText().toString());
						resultat = prepared.executeQuery();
						
						table.setModel(DbUtils.resultSetToTableModel(resultat));
						/*System.out.println( resultat.getString("elv_nom"));
						if(nomEle.getText().toString() == resultat.getString("elv_nom")){
						
						
						}*/
						} catch (SQLException ex) {
							ex.printStackTrace();
							
						}
						
						
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
						}else{
							
						}

						if(flag!=0){
							//JOptionPane.showMessageDialog(null, "Ce Numero n'existe pas ou Veillez donner un numero SVP !");
						}	
						gest();
						
					}
				});
				
				textExclut = new JTextField();
				textExclut.setHorizontalAlignment(SwingConstants.CENTER);
				textExclut.setFont(new Font("Tahoma", Font.BOLD, 40));
				textExclut.setForeground(new Color(255, 0, 0));
				textExclut.setEditable(false);
				textExclut.setBounds(1063, 19, 217, 112);
				panel.add(textExclut);
				textExclut.setColumns(10);
				btnRechecherParNom.setBounds(470, 18, 173, 23);
				panel.add(btnRechecherParNom);
				}catch(ParseException e){
					e.printStackTrace();
				
				}
				btnInserezPhoto.addActionListener(new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						recup();
					}
				});
				combo.addActionListener(new ActionListener() {
					
					
									
					
					public void actionPerformed(ActionEvent arg0) {
						String cls = combo.getSelectedItem().toString();
						if (idEle1.getText().equals("") || !combo.getSelectedItem().equals("")) {
							
							
							//System.out.println(cls);
							String sql = "select  elv_id, elv_nom, elv_prenom , sexe, elv_cls,  Date_de_naissance, Lieu_de_naissance, "
									+ "Nom_du_pere, Nom_de_la_mere, Quartier, anne_scolaire from eleve where elv_cls = '"+cls+"'"
											+ "and anne_scolaire ='"+comboAnSco.getSelectedItem().toString()+"'";
							
							try {
									prepared = cnx.prepareStatement(sql);
									resultat = prepared.executeQuery();
									table.setModel(DbUtils.resultSetToTableModel(resultat));
							} catch (SQLException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
							}
							classe.setText(combo.getSelectedItem().toString());
							
						}else{
							JOptionPane.showMessageDialog(null, "Vous êtes sur le point de modifier le Niveau d'un élève !!!! ");
						}
						
						String sqlT = "select count(elv_id) from eleve where elv_cls = '"+cls+"' "
								+ "and anne_scolaire ='"+comboAnSco.getSelectedItem().toString()+"'";
						try {
							prepared = cnx.prepareStatement(sqlT);
							resultat = prepared.executeQuery();
							while (resultat.next()) {
								
					lbTotal.setText(String.valueOf(resultat.getInt("count(elv_id)")));
							}
							
							
							
						} catch (Exception e) {
							// TODO: handle exception
						}
						
						
						//initialisation();
					}
				});
			JPanel pan = new JPanel();
			pan.setBounds(10, 205, 1323, 224);
			getContentPane().add(pan, BorderLayout.CENTER);
			pan.setLayout(null);
			getContentPane().setLayout(null);
			setVisible(true);
			cnx = ConnexionMySql.ConnexiomBd();
			
			

			/// Boutons pour interface
			//////////////////////////
			btnAjouter = new JButton("Ajouter");
			btnAjouter.setBounds(57, 440, 89, 23);
			getContentPane().add(btnAjouter);
			
			btnModifier = new JButton("Modifier");
			btnModifier.setBounds(173, 440, 94, 23);
			getContentPane().add(btnModifier);
			
			btnSupprimer = new JButton("supprimer");
			btnSupprimer.setBounds(292, 440, 98, 23);
			getContentPane().add(btnSupprimer);;

			
			
			//// Tableau de données ///////
			//////////////////////////////
			JScrollPane scrollPane = new JScrollPane();
			
			scrollPane.setBounds(10, 5, 1303, 199);
			pan.add(scrollPane);
			
			table = new JTable();
			table.setFillsViewportHeight(true);
			table.setSurrendersFocusOnKeystroke(true);
			table.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
			
			
			//-----------------------            updateTable();
			
			table.addMouseListener(new MouseAdapter(){
				public void mouseClicked(MouseEvent arg0){
					
					int ligne = table.getSelectedRow();
				    
					String id = table.getModel().getValueAt(ligne, 0).toString();
					table.getSelectionModel().setSelectionInterval(0, ligne);
					table.getSelectionModel();
					String sql2 ="Select elv_id, elv_nom, elv_prenom, sexe, elv_cls, Date_de_naissance, Lieu_de_naissance, Nom_du_pere, Nom_de_la_mere, "
							+ "Quartier, Adresse,  nomEtprenom, anne_scolaire, cat_eleve from eleve where  elv_id = ?";
						
					  try {
						  
					prepared = cnx.prepareStatement(sql2);
					prepared.setInt(1, Integer.parseInt(id));
					//System.out.println( rechEleve.getText().toString());
					resultat = prepared.executeQuery();
					int flag = 1;
					if(resultat.next()){
						
						flag = 0;
						String elv_nom = resultat.getString("elv_nom");
						String elv_prenom = resultat.getString("elv_prenom"); 
						String sexe = resultat.getString("sexe");
						String elv_cls = resultat.getString("elv_cls");
						String Date_de_naissance = resultat.getString("Date_de_naissance");
						String Nom_du_pere = resultat.getString("Nom_du_pere");
						String Nom_de_la_mere = resultat.getString("Nom_de_la_mere");
						String Quartier = resultat.getString("Quartier");
						String Adresse = resultat.getString("Adresse");
						String anne_scolaire = resultat.getString("anne_scolaire");
						String Lieu_de_naissance = resultat.getString("Lieu_de_naissance");
						String cat_eleve = resultat.getString("cat_eleve");
						
						idFiche.setText(resultat.getString("elv_id"));
						idEle1.setText(resultat.getString("elv_id"));
						//System.out.println("elv_prenom");
						//nomEle.getText().toString();
						nomEle.setText(elv_nom);
						prenomEle.setText(elv_prenom);
						textFieldSexe.setText(sexe);
						combo.setSelectedItem(elv_cls);
						dateF.setText(Date_de_naissance);
						nomPereEle.setText(Nom_du_pere);
						nomMereEle.setText(Nom_de_la_mere); 
						quartierEle.setText(Quartier);
						textFieldAdresse.setText(Adresse);//prepared.setString(11, textFieldAdresse.getText().toString());
						comboAnSco.setSelectedItem(anne_scolaire);
						lieuNaisEle.setText(Lieu_de_naissance);
							comboStat.setSelectedItem(cat_eleve);
							//textExclut.setText(cat_eleve);
							//System.out.println(textExclut.getText());
						
					
						
						
						
						
						}else{
							System.out.println("Sortie");
							nomEle.setText("");
							prenomEle.setText("");
							combo.setSelectedItem("Choix");
							//dateChooser.setDateFormatString("");
							lieuNaisEle.setText("");
							nomPereEle.setText("");
							nomMereEle.setText("");
							quartierEle.setText("");
							//lblPhoto.setIcon(null);
							//lblPhoto.revalidate();
						}
					/*System.out.println( resultat.getString("elv_nom"));
					if(nomEle.getText().toString() == resultat.getString("elv_nom")){
					
					
					}*/
					} catch (SQLException ex) {
						ex.printStackTrace();
						
					}
					  if(comboStat.getSelectedItem().equals("Exclut")){
						textExclut.setText("Exclut");
						btnAjouter.setEnabled(false);
						btnModifier.setEnabled(false);
						combo.setEnabled(false);
						comboAnSco.setEnabled(false);
						comboStat.setEnabled(false);
						}else{
							textExclut.setText("");
							btnAjouter.setEnabled(true);
							btnModifier.setEnabled(true);
							combo.setEnabled(true);
							comboAnSco.setEnabled(true);
							comboStat.setEnabled(true);
						}
						
					
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
					
					
					
					
					
					
					
					
					
					
					
					
					
					

				}
			});
			scrollPane.setViewportView(table);
			
			
			
			////////////////////////////////////
			/*
			 * 
			 * 
			 * 
			 * 
			 * */
			
			btnEditionListe = new JButton("Edition Liste ");
			
			btnEditionListe.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ConnexionMySql p = new ConnexionMySql();
					p.ConnexiomBd();
					try {
						if(!combo.getSelectedItem().equals("Choix") && !comboStat.getSelectedItem().equals("D")){
							if(comboStat.getSelectedItem().equals("Aucun")){
									int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous Imprimer toute la liste de la Classe ?", "Message", JOptionPane.YES_NO_OPTION);
								
								try {
									JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/liste_eleveGen.jrxml");
									HashMap para = new HashMap();
									para.put("anSco", comboAnSco.getSelectedItem().toString());
									para.put("para", combo.getSelectedItem().toString());
									para.put("cat", comboStat.getSelectedItem().toString());
									JasperReport jr = JasperCompileManager.compileReport(jd);
									JasperPrint jp =JasperFillManager.fillReport(jr, para, p.ConnexiomBd());
									JasperViewer jv = new JasperViewer(jp, false);
									jv.setVisible(true);						
								} catch (JRException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}else if(comboStat.getSelectedItem().equals("N")){
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/liste_eleve.jrxml");
								HashMap para = new HashMap();
								para.put("cat", comboStat.getSelectedItem().toString());
								para.put("para", combo.getSelectedItem().toString());
								para.put("anSco", comboAnSco.getSelectedItem().toString());
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, para, p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);
							}else if(comboStat.getSelectedItem().equals("A")){
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/liste_eleve.jrxml");
								HashMap para = new HashMap();
								para.put("cat", comboStat.getSelectedItem().toString());
								para.put("para", combo.getSelectedItem().toString());
								para.put("anSco", comboAnSco.getSelectedItem().toString());
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, para, p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);
							}else if(comboStat.getSelectedItem().equals("R")){
								JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/liste_eleve.jrxml");
								HashMap para = new HashMap();
								para.put("cat", comboStat.getSelectedItem().toString());
								para.put("para", combo.getSelectedItem().toString());
								para.put("anSco", comboAnSco.getSelectedItem().toString());
								JasperReport jr = JasperCompileManager.compileReport(jd);
								JasperPrint jp =JasperFillManager.fillReport(jr, para, p.ConnexiomBd());
								JasperViewer jv = new JasperViewer(jp, false);
								jv.setVisible(true);
							}else
							{
								JOptionPane.showMessageDialog(null, "Veillez Selectionner un Statut et Classe avant Impression !!!");
							}/**/
							
						}
						if(combo.getSelectedItem().equals("Choix") && comboStat.getSelectedItem().equals("D")){
							
							JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/liste_eleveDep.jrxml");
							HashMap para = new HashMap();
							para.put("cat", comboStat.getSelectedItem().toString());
							/*para.put("para", combo.getSelectedItem().toString());
							para.put("anSco", comboAnSco.getSelectedItem().toString());*/
							JasperReport jr = JasperCompileManager.compileReport(jd);
							JasperPrint jp =JasperFillManager.fillReport(jr, para, p.ConnexiomBd());
							JasperViewer jv = new JasperViewer(jp, false);
							jv.setVisible(true);
							
						}
					
						
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
						
					}
				}
			});
			
			btnEditionListe.setBounds(20, 474, 126, 23);
			getContentPane().add(btnEditionListe);
			
			
			
			btnFerm = new JButton("Fermer");
			btnFerm.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				 ifEleveLTC.this.dispose();
				 
				}
			});
			btnFerm.setBounds(292, 474, 126, 23);
			getContentPane().add(btnFerm);
			
			btnFiche = new JButton("Edition Fiche");
			btnFiche.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					ConnexionMySql p = new ConnexionMySql();
					
					int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous Reimprimer la Fiche ?", "Message", JOptionPane.YES_NO_OPTION);
					if(imp == 0 && !textField.getText().toString().equals("") && !combo.getSelectedItem().toString().equals("Choix")){
					try {
						JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/ficheInscription.jrxml");
						HashMap vNomEleve = new HashMap();
						
						vNomEleve.put("pIdElv", Integer.parseInt(textField.getText().toString()));
						vNomEleve.put("pClas", combo.getSelectedItem().toString());
						JasperReport jr = JasperCompileManager.compileReport(jd);
						JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
						JasperViewer jv = new JasperViewer(jp, false);
						jv.setVisible(true);/**/	
						
						initialisation();
						
						
					} catch (JRException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Veillez Selectionnez un Eleve pour reimprimer sa fiche d'inscription");
						initialisation();
						e.printStackTrace();
					}
				}else if(imp == 0 && !idFiche.getText().equals("") && !combo.getSelectedItem().toString().equals("Choix")){
					try {
						JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/ficheInscription.jrxml");
						HashMap vNomEleve = new HashMap();
						
						vNomEleve.put("pIdElv", Integer.parseInt(idFiche.getText().toString()));
						vNomEleve.put("pClas", combo.getSelectedItem().toString());
						JasperReport jr = JasperCompileManager.compileReport(jd);
						JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
						JasperViewer jv = new JasperViewer(jp, false);
						jv.setVisible(true);/**/	
						
						initialisation();
						
						
					} catch (JRException e) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null, "Veillez Selectionnez un Eleve pour reimprimer sa fiche d'inscription");
						initialisation();
						
					}
				}else				
				{
					JOptionPane.showMessageDialog(null, "Veillez Selectionnez un élève avant !!!");
				}
					
				}
			});
			btnFiche.setBounds(156, 474, 126, 23);
			getContentPane().add(btnFiche);
			
			lbTotal = new JLabel("");
			lbTotal.setFont(lbTotal.getFont().deriveFont(lbTotal.getFont().getStyle() | Font.BOLD, lbTotal.getFont().getSize() + 1f));
			lbTotal.setBounds(1267, 440, 61, 23);
			getContentPane().add(lbTotal);
			
			JLabel lblEffectifEleve = new JLabel("EFFECTIF ELEVE :");
			lblEffectifEleve.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 16));
			lblEffectifEleve.setHorizontalAlignment(SwingConstants.CENTER);
			lblEffectifEleve.setBounds(1098, 440, 169, 23);
			getContentPane().add(lblEffectifEleve);
			
			JLabel lblMotifDpart = new JLabel("Motif D\u00E9part :");
			lblMotifDpart.setHorizontalAlignment(SwingConstants.CENTER);
			lblMotifDpart.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
			lblMotifDpart.setBounds(434, 440, 106, 23);
			getContentPane().add(lblMotifDpart);
			
			depart = new TextArea();
			depart.setCaretPosition(12);
			depart.setColumns(12);
			depart.setSelectionStart(12);
			depart.setSelectionEnd(12);
			depart.setRows(12);
			depart.setBounds(546, 437, 237, 60);
			getContentPane().add(depart);
			
			JButton btnNewButton = new JButton("Depart");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(comboStat.getSelectedItem().equals("D")){
						
						String nomETprenom = nomEle.getText() +" "+ prenomEle.getText();
						ConnexionMySql p = new ConnexionMySql();
						int imp = JOptionPane.showConfirmDialog(null, "Voulez-vous effectuer un départ ? ", "Message", JOptionPane.YES_NO_OPTION);
						if( imp == 0  && !textFieldAdresse.getText().equals("") &&
								!comboAnSco.getSelectedItem().toString().equals("") && !textFieldSexe.getText().toString().equals("") ){
							imp = 0;
							
							//JasperDesign jd;
							//JasperReport jf;
							
							String sql = "insert into depart ( elv_nom, elv_prenom, sexe, elv_cls, Date_de_naissance"
									+ ",Lieu_de_naissance, Nom_du_pere, Nom_de_la_mere,"
									+ "Quartier, Adresse,  nomEtprenom, anne_scolaire, cat_eleve, raison_depart) values (?, ?,?, ?,?, ?, ?, ? ,?,?,?,?,?,?)";
							try {
									//
									prepared = cnx.prepareStatement(sql);
									//FileInputStream istm = new FileInputStream(new File(st));
									prepared.setString(1, nomEle.getText().toString());
									prepared.setString(2, prenomEle.getText().toString());
									prepared.setString(3, textFieldSexe.getText().toString());
									prepared.setString(4, combo.getSelectedItem().toString());
									prepared.setString(5, dateF.getText().toString());
									prepared.setString(6, lieuNaisEle.getText().toString());
									prepared.setString(7, nomPereEle.getText().toString());
									prepared.setString(8, nomMereEle.getText().toString());
									prepared.setString(9, quartierEle.getText().toString());
									prepared.setString(10, textFieldAdresse.getText().toString());
									prepared.setString(11, nomETprenom);
									prepared.setString(12, comboAnSco.getSelectedItem().toString());
									prepared.setString(13, comboStat.getSelectedItem().toString());
									prepared.setString(14, depart.getText().toString());
									prepared.execute();
							
									String sql2 = "delete from eleve where elv_id=?";
									if(!idEle1.getText().equals("") ){
										try{
											prepared = cnx.prepareStatement(sql2);
											int id = Integer.parseInt(idEle1.getText());
											prepared.setInt(1, id);
											prepared.executeUpdate();
											initialisation();
										}catch(SQLException ex){
											JOptionPane.showMessageDialog(null, "Erreur "+ex);
										}
									}
							initialisation();
							} catch (SQLException e ) {
							e.printStackTrace();
							
							
				           }
							
							
							
							}else {
								JOptionPane.showMessageDialog(null, "Certains Champs sont vides. Modifiez puis poursuivre l'Opération ");
						    }
						
						
					}
				}
			});
			btnNewButton.setBounds(789, 440, 169, 23);
			getContentPane().add(btnNewButton);
			
			JButton btnNewButton_1 = new JButton("Visualisez les D\u00E9parts");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String cls = combo.getSelectedItem().toString();
					if (idEle1.getText().equals("") || !combo.getSelectedItem().equals("")) {
						
						
						//System.out.println(cls);
						String sql = "select elv_id, elv_nom, elv_prenom ,  Date_de_naissance, Lieu_de_naissance, elv_cls, "
								+ "Nom_du_pere, Nom_de_la_mere, Quartier, anne_scolaire, raison_depart from depart where cat_eleve = 'D'";
						
						try {
								prepared = cnx.prepareStatement(sql);
								resultat = prepared.executeQuery();
								table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
						classe.setText(combo.getSelectedItem().toString());
						//initialisation();
					}else{
						JOptionPane.showMessageDialog(null, "Vous êtes sur le point de modifier le Niveau d'un élève !!!! ");
					}
				}
			});
			btnNewButton_1.setBounds(789, 474, 169, 23);
			getContentPane().add(btnNewButton_1);
			
			catEnsEle = new JTextField();
			catEnsEle.setEditable(false);
			catEnsEle.setEnabled(false);
			catEnsEle.setBounds(1155, 499, 144, -3);
			getContentPane().add(catEnsEle);
			catEnsEle.setColumns(10);
			
			JLabel lblEntrezNomDun = new JLabel("Entrez Nom d'un ELEVE :");
			lblEntrezNomDun.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntrezNomDun.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 11));
			lblEntrezNomDun.setBounds(960, 474, 144, 23);
			getContentPane().add(lblEntrezNomDun);
			
			textRechNom = new JTextField();
			textRechNom.setHorizontalAlignment(SwingConstants.CENTER);
			textRechNom.setBounds(1108, 475, 219, 20);
			getContentPane().add(textRechNom);
			textRechNom.setColumns(10);
			
			
				
			// FIN INTERNAL FRAME 1
			/////////////////////////////////////////////
			
			
				
			/// Methode pour Ajout dans la BD
			////////////////////////////////	&& st!= null	
				btnAjouter.addActionListener(new ActionListener() {
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String nomETprenom = nomEle.getText() +" "+ prenomEle.getText();
						gest();
						if(checkInput() ){
							if(textField.getText().toString().equals("")){
								
								
								
										
										ConnexionMySql p = new ConnexionMySql();
										int imp = JOptionPane.showConfirmDialog(null, "Poursuivre l'Enregistrement ? ", "Message", JOptionPane.YES_NO_OPTION);
										if( imp == 0 && textField.getText().toString().equals("") && !textFieldAdresse.getText().equals("") &&
												!comboAnSco.getSelectedItem().toString().equals("") && !textFieldSexe.getText().toString().equals("") ){
											//imp = 0;
											
											//JasperDesign jd;
											//JasperReport jf;
											
											String sql = "insert into eleve ( elv_nom, elv_prenom, sexe, elv_cls, Date_de_naissance"
													+ ",Lieu_de_naissance, Nom_du_pere, Nom_de_la_mere,"
													+ "Quartier, Adresse,  nomEtprenom, anne_scolaire, cat_eleve) values (?, ?,?, ?,?, ?, ?, ? ,?,?,?,?,?)";
											try {
													//
													prepared = cnx.prepareStatement(sql);
													//FileInputStream istm = new FileInputStream(new File(st));
													prepared.setString(1, nomEle.getText().toString());
													prepared.setString(2, prenomEle.getText().toString());
													prepared.setString(3, textFieldSexe.getText().toString());
													prepared.setString(4, combo.getSelectedItem().toString());
													prepared.setString(5, dateF.getText().toString());
													prepared.setString(6, lieuNaisEle.getText().toString());
													prepared.setString(7, nomPereEle.getText().toString());
													prepared.setString(8, nomMereEle.getText().toString());
													prepared.setString(9, quartierEle.getText().toString());
													prepared.setString(10, textFieldAdresse.getText().toString());
													prepared.setString(11, nomETprenom);
													prepared.setString(12, comboAnSco.getSelectedItem().toString());
													prepared.setString(13, comboStat.getSelectedItem().toString());
													prepared.execute();
											//prepared.setBlob(11, istm);
											String cls = combo.getSelectedItem().toString();
											System.out.println(cls);
											
											String sql2 = "select  elv_id, elv_nom, elv_prenom , elv_cls,  Date_de_naissance, Lieu_de_naissance, "
													+ "Nom_du_pere, Nom_de_la_mere, Quartier, anne_scolaire from eleve where elv_cls = '"+cls+"'";
											try {
													prepared = cnx.prepareStatement(sql2);
													resultat = prepared.executeQuery();
													table.setModel(DbUtils.resultSetToTableModel(resultat));
											} catch (SQLException e) {
													// TODO Auto-generated catch block
													e.printStackTrace();
											}
											
											/*try {
											} catch (JRException e) {
												// TODO Auto-generated catch block
												initialisation();
												e.printStackTrace();
											}*/
												try {
													String der = "select max(elv_id) from eleve";
													prepared = cnx.prepareStatement(der);
													resultat = prepared.executeQuery();
													while(resultat.next()){
														int tex = resultat.getInt("max(elv_id)");
														idFiche.setText(String.valueOf(tex));
														
													}
													
												} catch (Exception e) {
													// TODO: handle exception
												}
												
												/*JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/ficheInscription.jrxml");
												HashMap vNomEleve = new HashMap();
												
												vNomEleve.put("pIdElv", Integer.parseInt(idFiche.getText().toString()));
												vNomEleve.put("pClas", combo.getSelectedItem().toString());
												JasperReport jr = JasperCompileManager.compileReport(jd);
												JasperPrint jp =JasperFillManager.fillReport(jr, vNomEleve , p.ConnexiomBd());
												JasperViewer jv = new JasperViewer(jp, false);
												jv.setVisible(true);	
												
												*/
												
												
											
								//---------------------------updateTable();
											initialisation();
											} catch (SQLException e ) {
											JOptionPane.showMessageDialog(null, "Revoyez votre procedure d'enregistrement SVP !!!");
											
											
								           }
											
											
											
											}else {
												JOptionPane.showMessageDialog(null, "Certains Champs sont vides. Modifiez puis poursuivre l'Opération ");
										    }
										
										
										
										
										
										
							}else if(!textField.getText().toString().equals("")){
								JOptionPane.showMessageDialog(null, "Veillez Cliquer sur l'Option de Recherche N° SVP ");
							}
							
							
								//lblPhoto.setIcon(null);
								st = null;
								}/*else {
							    	JOptionPane.showMessageDialog(null, "Veillez selectionne l'image a nouveau :) pour confirmation");
							    }*/
						
							}
				});
			
			/// Methode pour Supprimer dans la BD
			////////////////////////////////	
				
				btnSupprimer.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						String sql = "delete from eleve where elv_id=?";
						if(!idEle1.getText().equals("")){
							try{
								prepared = cnx.prepareStatement(sql);
								int id = Integer.parseInt(idEle1.getText());
								prepared.setInt(1, id);
								prepared.executeUpdate();
								
								JOptionPane.showMessageDialog(null, "Eleve supprimer ");
						//------------------------------updateTable();
								initialisation();
								
							}catch(SQLException ex){
								
								JOptionPane.showMessageDialog(null, "Erreur "+ex);
							}
						}else{
							
							JOptionPane.showMessageDialog(null, "Choisissez l'Eleve à supprimer ");
						}
					}
				});
				
				
				/// Fin Methode
				////////////////////////////////
			
				
				/// Methode pour Modifier dans la BD
				////////////////////////////////
				
				btnModifier.addActionListener(new ActionListener() {
					
					@Override
					public void actionPerformed(ActionEvent arg0) {
						gest();
						int imp = JOptionPane.showConfirmDialog(null, "Poursuivre l'Enregistrement ? ", "Message", JOptionPane.YES_NO_OPTION);
						
						if(imp==0){
							
							if(st == null ){
								try {
									
									if(idEle1.getText().equals("") || combo.getSelectedItem().equals("")){
										JOptionPane.showMessageDialog(null, "Choisissez une classe ensuite selectionnez un élève avant toute action");
									}else{
										
										String nomETprenom = nomEle.getText() +" "+ prenomEle.getText();
									String sql =" update eleve set  elv_nom = ?  , elv_prenom = ? , sexe = ? , elv_cls = ?, Date_de_naissance= ?,"
										+ "Lieu_de_naissance= ?, Nom_du_pere= ?, Nom_de_la_mere= ?, Quartier = ?, nomEtprenom =?, anne_scolaire=? ,"
										+ " cat_eleve = ? where elv_id =?";
									
									prepared = cnx.prepareStatement(sql);
									prepared.setString(1, nomEle.getText().toString());
									prepared.setString(2, prenomEle.getText().toString());
									prepared.setString(3, textFieldSexe.getText().toString());
									prepared.setString(4, combo.getSelectedItem().toString());
									prepared.setString(5, dateF.getText().toString());
									prepared.setString(6, lieuNaisEle.getText().toString());
									prepared.setString(7, nomPereEle.getText().toString());
									prepared.setString(8, nomMereEle.getText().toString());
									prepared.setString(9, quartierEle.getText().toString());
									prepared.setString(10, nomETprenom);
									
									prepared.setString(11, comboAnSco.getSelectedItem().toString());
									prepared.setString(12, comboStat.getSelectedItem().toString());
									prepared.setInt(13, Integer.parseInt(idEle1.getText()));
									
									prepared.executeUpdate();
									
									
									String sql2 = "UPDATE `validationtotal` SET `Nom`= ? WHERE Num = ?  ";
									prepared = cnx.prepareStatement(sql2);
									prepared.setString(1, nomETprenom);
									prepared.setInt(2, Integer.parseInt(idEle1.getText()));
									prepared.executeUpdate();
									
									String sql3 = "UPDATE `note` SET `nom_et_prenom`=? WHERE `id_elv` = ?";
									prepared = cnx.prepareStatement(sql3);
									prepared.setString(1, nomETprenom);
									prepared.setInt(2, Integer.parseInt(idEle1.getText()));
									prepared.executeUpdate();
									
									String sql4 = "UPDATE `note2` SET `nom_et_prenom`=? WHERE `id_elv` = ?";
									prepared = cnx.prepareStatement(sql4);
									prepared.setString(1, nomETprenom);
									prepared.setInt(2, Integer.parseInt(idEle1.getText()));
									prepared.executeUpdate();
									
									String sql5 = "UPDATE `note3` SET `nom_et_prenom`=? WHERE `id_elv` = ?";
									prepared = cnx.prepareStatement(sql5);
									prepared.setString(1, nomETprenom);
									prepared.setInt(2, Integer.parseInt(idEle1.getText()));
									prepared.executeUpdate();
									
									
									JOptionPane.showMessageDialog(null, "Enregistrement reussit !!! ");
							//-------------------------------updateTable();
									}
									
									
									//initialisation();
									
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
								
								
								}else{
									try {
										String nomETprenom = nomEle.getText() +" "+ prenomEle.getText();
										String sql =" update eleve set  elv_nom = ?  , elv_prenom = ? , sexe = ? , elv_cls = ?, Date_de_naissance= ?,"
											+ "Lieu_de_naissance= ?, Nom_du_pere= ?, Nom_de_la_mere= ?, Quartier = ?,  nomEtprenom =?  where elv_id =?";
										
										prepared = cnx.prepareStatement(sql);
										prepared.setString(1, nomEle.getText().toString());
										prepared.setString(2, prenomEle.getText().toString());
										prepared.setString(3, textFieldSexe.getText().toString());
										prepared.setString(4, combo.getSelectedItem().toString());
										prepared.setString(5, dateF.getText().toString());
										prepared.setString(6, lieuNaisEle.getText().toString());
										prepared.setString(7, nomPereEle.getText().toString());
										prepared.setString(8, nomMereEle.getText().toString());
										prepared.setString(9, quartierEle.getText().toString());
										prepared.setString(10, nomETprenom);
										prepared.setInt(11, Integer.parseInt(idEle1.getText()));
										
										//prepared.setInt(10, Integer.parseInt(idEle.getText()));
										prepared.executeUpdate();
										
										
										String sql2 = "UPDATE `validationtotal` SET `Nom`= ? WHERE Num = ? ";
										prepared = cnx.prepareStatement(sql2);
										prepared.setString(1, nomETprenom);
										prepared.setInt(2, Integer.parseInt(idEle1.getText()));
										prepared.executeUpdate();
										
										
										
										String sql3 = "UPDATE `note` SET `nom_et_prenom`=? WHERE `id_elv` = ?";
										prepared = cnx.prepareStatement(sql3);
										prepared.setString(1, nomETprenom);
										prepared.setInt(2, Integer.parseInt(idEle1.getText()));
										prepared.executeUpdate();
										
										String sql4 = "UPDATE `note2` SET `nom_et_prenom`=? WHERE `id_elv` = ?";
										prepared = cnx.prepareStatement(sql4);
										prepared.setString(1, nomETprenom);
										prepared.setInt(2, Integer.parseInt(idEle1.getText()));
										prepared.executeUpdate();
										
										String sql5 = "UPDATE `note3` SET `nom_et_prenom`=? WHERE `id_elv` = ?";
										prepared = cnx.prepareStatement(sql5);
										prepared.setString(1, nomETprenom);
										prepared.setInt(2, Integer.parseInt(idEle1.getText()));
										prepared.executeUpdate();
										
										
										JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
										//initialisation();
							//-------------------------------------updateTable();
										
									} catch (Exception e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
									
								}
							
						}else{
							JOptionPane.showMessageDialog(null, "Annulation de la Modification !!! ");
						}
						
						
						//////////////////////////////////////////////////////////////////////////////////////////////////////////////
						nomEle.setText("");
						prenomEle.setText("");
						combo.setSelectedItem("");
						dateF.setText("");
						lieuNaisEle.setText("");
						nomPereEle.setText("");
						nomMereEle.setText("");
						quartierEle.setText("");
						textField.setText("");
						//lblPhoto.setIcon(null);
						//lblPhoto.revalidate();
						textFieldAdresse.setText("");
						//comboAnSco.setSelectedItem("Choix");
						//combo.setSelectedItem("Choix");
						comboStat.setSelectedItem("Choix");
						////////////////////////////////////////////////////////////////////////////////////////////////////////////
						String cls = combo.getSelectedItem().toString();
						System.out.println(cls);
						String sql2 = "select  elv_id, elv_nom, elv_prenom , sexe, elv_cls,  Date_de_naissance, Lieu_de_naissance, "
								+ "Nom_du_pere, Nom_de_la_mere, Quartier, anne_scolaire from eleve where elv_cls = '"+cls+"' and anne_scolaire = '"
								+comboAnSco.getSelectedItem().toString()+"'";
						
						try {
								prepared = cnx.prepareStatement(sql2);
								resultat = prepared.executeQuery();
								table.setModel(DbUtils.resultSetToTableModel(resultat));
						} catch (SQLException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
						}
						
						
						idFiche.setText("");
						idEle1.setText("");
					
						
					}
				});
				remplirClasse();
				BindComboCour();
				Bin();
				gest();
		}
	
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}

	public void setBtnSupprimer(JButton btnSupprimer) {
		this.btnSupprimer = btnSupprimer;
	}

	public void recup(){

		JFileChooser filechooser = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("IMAGE", "jpeg","png","gif");
		filechooser.addChoosableFileFilter(filter);
		int resultat = filechooser.showSaveDialog(null);
		
		if(resultat == JFileChooser.APPROVE_OPTION)
		{
			filechooser.setCurrentDirectory(new File("F://Images//icones"));
			File selectionImage = filechooser.getSelectedFile();
			String path = selectionImage.getAbsolutePath();
			//lblPhoto.setIcon(ResizeImage(path,null));
			st = path;
			}
			else if(resultat== JFileChooser.CANCEL_OPTION){
				
				JOptionPane.showMessageDialog(null, "Aucune Image Selectionnée !!! ");
			}
	}
	/*
	private ImageIcon ResizeImage(String ImagePath, byte[] imge) {
			ImageIcon monImage = null;
			if(ImagePath != null){
				monImage = new ImageIcon(ImagePath);
			}else{
				monImage = new ImageIcon(imge);
			}
			java.awt.Image img = monImage.getImage();
			java.awt.Image newImage = img.getScaledInstance(lblPhoto.getWidth(), lblPhoto.getHeight(), Image.SCALE_SMOOTH);
			ImageIcon image = new ImageIcon(newImage);
			
			//return image;
	}
*/
	//	
	
	public void updateTable(){
			
			String sql = "select  elv_id, elv_nom, elv_prenom , sexe, elv_cls,  Date_de_naissance, Lieu_de_naissance, "
											+ "Nom_du_pere, Nom_de_la_mere, Quartier, anne_scolaire from eleve";
			
			try {
				prepared = cnx.prepareStatement(sql);
				resultat = prepared.executeQuery();
				table.setModel(DbUtils.resultSetToTableModel(resultat));
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	public boolean checkInput(){
		if(nomEle.getText().equals("") || prenomEle.getText().equals("") 
				|| combo.getSelectedItem().equals("") ||  lieuNaisEle.getText().equals("")
				|| nomPereEle.getText().equals("") || nomMereEle.getText().equals("")
				|| quartierEle.getText().equals("") ){
			}
		return true;
	}
	
	public boolean nop(){
		if(nomEle.getText().equals(true)){
			
		}
		return true;
	}
	
	
	public void initialisation(){
		nomEle.setText("");
		prenomEle.setText("");
		combo.setSelectedItem("");
		dateF.setText("");
		lieuNaisEle.setText("");
		nomPereEle.setText("");
		nomMereEle.setText("");
		quartierEle.setText("");
		textField.setText("");
		//lblPhoto.setIcon(null);
		//lblPhoto.revalidate();
		textFieldAdresse.setText("");
		//comboAnSco.setSelectedItem("Choix");
		//combo.setSelectedItem("Choix");
		//comboStat.setSelectedItem("Choix");
		textFieldSexe.setText("");
		textFieldSexe.setText("");
		
	}
	
	@SuppressWarnings("unchecked")
	public void remplirClasse(){
		String sql = "select * from classeltc ";
		
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next()){
				String classe = resultat.getString("cls_nom").toString();
				combo.addItem(classe);
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void BindComboCour(){
		
		String sql = "SELECT anne_courant FROM annee ";
		
		try {
			
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
		    while(resultat.next()){
		    	comboAnSco.addItem(resultat.getString(1));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Bin(){
		
		String sql = "SELECT nom_categorie FROM type_eleve ";
		
		try {
			
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
		    while(resultat.next()){
		    	comboStat.addItem(resultat.getString(1));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public boolean gest(){
			
			if(catEnsEle.getText().toString().equals("gestionnaire")){
				btnSupprimer.setEnabled(false);
			}
			
			return true ;
	}
}


