package metier;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Panel;
import java.awt.Point;
import java.awt.TextField;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JMenuBar;

import java.awt.Font;

import javax.swing.JMenu;

import java.awt.Insets;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JMenuItem;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.Toolkit;

import javax.swing.ImageIcon;

import java.awt.Dialog.ModalExclusionType;

import javax.swing.JToolBar;

import connec.ConnexionMySql;
import connec.Donne;

import java.awt.SystemColor;
import java.awt.Label;
import java.awt.ScrollPane;
import java.awt.Canvas;

import javax.swing.JLayeredPane;
import javax.swing.SwingConstants;

import java.awt.FlowLayout;

import javax.swing.BoxLayout;

import java.awt.Window.Type;

//import org.eclipse.wb.swing.FocusTraversalOnArray;


import entite.enseignantEL;

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.LayoutManager;
import javax.swing.UIManager;

public class FenMenu extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static int frameCountY=1;
	private static int frameCountX=1;

	private JPanel contentPane;
	private TextField nomEle;
	private TextField prenomEle;
	@SuppressWarnings("rawtypes")
	private JComboBox combo;
	
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JMenuBar menuBar;
	private TextField dateNaisEle;
	private TextField lieuNaisEle;
	private TextField nomPereEle;
	private TextField nomMereEle;
	private TextField quartierEle;
	private JMenuItem itErgNotEL;
	private JMenu fchEleve;
	private JMenuItem imprimer;
	private JMenu fchEnsg;
	private JMenuItem itErgEsg;
	private JMenuItem itErgELE;
	private JMenuItem itErgELELTC;
	private JMenuItem itErgCoursEL;
	private JMenuItem itErgSemEL;
	private JMenuItem itErgClasse;
	private JMenuItem itErgClasseLTC;
	private JMenuItem impBull;
	private JMenu fchCaisse;
	private JMenuItem itGestIns;
	private JMenuItem itIns;
	private JMenuItem itInsA;
	private JMenuItem itInsValidation;
	private JMenu param;
	private JMenuItem annee;
	private JMenuItem recette;
	private JMenuItem coef;
	private JMenu INFO;
	private JMenu PARAMETRES;
	private JMenu fchAdm;
	private JTextField textFieldNom;
	private JTextField textFieldUser;
	private JLabel lblBienvenueMr;
	private JMenuItem itGesUti;
	private JTextField textFieldMat;
	private JLabel label;
	private JLabel lblEnseignantDe;
	private JLabel lblIdenseignant;
	private JTextField textField;
	private JTextField cat;
	private JLabel Cat;
	private JMenuItem reimp;
	private JMenuItem ReimpRecu;
	private JMenuItem mntmEditionPv;
	private JMenuItem itOr;
	private JMenuItem recetteLTC;
	private JMenuItem itMPas;
	private JMenuItem mntmGestionNotesLtc;
	private JMenuItem itGesUtiLTC;
	private JMenuItem itErgEsgLTC;
	private JMenuItem mntmGestionCoursLtc;
	private JMenuItem mntmEditionBulletinsLtc;
	
	
	
	public JTextField getTextField() {
		return textField;
	}


	public void setTextField(JTextField textField) {
		this.textField = textField;
	}


	public JTextField getTextFieldNom() {
		return textFieldNom;
	}


	public void setTextFieldNom(JTextField textFieldNom) {
		this.textFieldNom = textFieldNom;
	}

	
	public JTextField getTextFieldUser() {
		return textFieldUser;
	}


	public void setTextFieldUser(JTextField textFieldUser) {
		this.textFieldUser = textFieldUser;
	}


	/**
	 * Launch the application.
	 * A retirer avant compilation
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FenMenu frame = new FenMenu();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
		
		
	
	
	
	public JMenuItem getItErgSemEL() {
		return itErgSemEL;
	}


	public void setItErgSemEL(JMenuItem itErgSemEL) {
		this.itErgSemEL = itErgSemEL;
	}


	public JMenuItem getItErgClasse() {
		return itErgClasse;
	}


	public void setItErgClasse(JMenuItem itErgClasse) {
		this.itErgClasse = itErgClasse;
	}


	public void setItErgNotEL(JMenuItem itErgNotEL) {
		this.itErgNotEL = itErgNotEL;
	}


	public void setItErgEsg(JMenuItem itErgEsg) {
		this.itErgEsg = itErgEsg;
	}


	public void setItErgCoursEL(JMenuItem itErgCoursEL) {
		this.itErgCoursEL = itErgCoursEL;
	}


	public JMenuItem getItErgNotEL() {
		return itErgNotEL;
	}


	public JMenuItem getItErgEsg() {
		return itErgEsg;
	}


	public JMenuItem getItErgELE() {
		return itErgELE;
	}


	public JMenuItem getItErgCoursEL() {
		return itErgCoursEL;
	}


	public JMenuItem getItGestIns() {
		return itGestIns;
	}
	
	

	public JTextField getCat() {
		return cat;
	}


	public void setCat(JTextField cat) {
		this.cat = cat;
	}


	public JMenuItem getItOr() {
		return itOr;
	}


	public void setItOr(JMenuItem itOr) {
		this.itOr = itOr;
	}


	/**
	 * Create the frame.
	 */
	public FenMenu() {
		
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\img\\main.png"));
		
		////////////////////////////////////////////////
		/// PARAMETRES DE LA FENETRES
		///////////////////////////////////////////////
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 773, 484);
		setSize(1600, 800);
		this.setLocationRelativeTo(null);
		/**/
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 773, 484);
		this.setSize(1600, 800);
		this.setLocationRelativeTo(null);
		menuBar = new JMenuBar();
		menuBar.setEnabled(false);
		menuBar.setBorderPainted(false);
		menuBar.setBackground(Color.CYAN);
		menuBar.setMargin(new Insets(0, 15, 5, 10));
		menuBar.setFont(new Font("Times New Roman", Font.BOLD, 15));
		setJMenuBar(menuBar);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.controlHighlight);
		contentPane.setBorder(new EmptyBorder(10, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		cnx = ConnexionMySql.ConnexiomBd();
		JDesktopPane desktopPane = new JDesktopPane();
		desktopPane.setForeground(new Color(204, 255, 255));
		desktopPane.setBackground(Color.GRAY);
		contentPane.add(desktopPane);
		desktopPane.setLayout(null);
		
		JToolBar toolBar = new JToolBar();
		toolBar.setFont(new Font("Segoe UI", Font.BOLD | Font.ITALIC, 12));
		contentPane.add(toolBar, BorderLayout.SOUTH);
		JPanel panel_1 = new JPanel();
		toolBar.add(panel_1);
		panel_1.setLayout(new FlowLayout(FlowLayout.LEFT, 5, 5));
		JLabel lblTestMachine = new JLabel("ISI_School_Application");
		panel_1.add(lblTestMachine);
		
		label = new JLabel("                    ");
		panel_1.add(label);
		
		lblBienvenueMr = new JLabel("Bienvenue Mr : ");
		lblBienvenueMr.setHorizontalAlignment(SwingConstants.RIGHT);
		panel_1.add(lblBienvenueMr);
		
		textFieldUser = new JTextField();
		textFieldUser.setEditable(false);
		textFieldUser.setHorizontalAlignment(SwingConstants.LEFT);
		panel_1.add(textFieldUser);
		textFieldUser.setColumns(10);
		
		lblEnseignantDe = new JLabel("Enseignant de : ");
		panel_1.add(lblEnseignantDe);
		
		textFieldMat = new JTextField();
		textFieldMat.setHorizontalAlignment(SwingConstants.LEFT);
		textFieldMat.setEditable(false);
		panel_1.add(textFieldMat);
		textFieldMat.setColumns(10);
		
		lblIdenseignant = new JLabel("ID-Ens : ");
		panel_1.add(lblIdenseignant);
		
		textField = new JTextField();
		textField.setHorizontalAlignment(SwingConstants.LEFT);
		textField.setEditable(false);
		textField.setColumns(10);
		panel_1.add(textField);
		
		Cat = new JLabel("Cat\u00E9gorie : ");
		panel_1.add(Cat);
		
		cat = new JTextField();
		cat.setHorizontalAlignment(SwingConstants.LEFT);
		cat.setEditable(false);
		cat.setColumns(10);
		panel_1.add(cat);
		
		
		
		
		
		// FIN DES PARAMETRES GENERAUX 1574, 704
		////////////////////////////////////////////
		
		
				/////////////////////////////////////////
				// INTERNAL FRAME POUR ADMINISTRATION
				////////////////////////////////////////
				
				fchAdm = new JMenu("ADMINISTRATION");
				fchAdm.setIcon(new ImageIcon(".\\img\\b_home.png"));
				menuBar.add(fchAdm);
				
				itErgEsg = new JMenuItem("GESTION ENSEIGNANTS");
				itErgEsg.setIcon(new ImageIcon(".\\img\\users-on.png"));
				fchAdm.add(itErgEsg);
				itErgEsg.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				ifEnseigLo C =new ifEnseigLo();
				
				if(cat.getText().equals("gestionnaire")){
					C.getBtnSupprimer().setEnabled(false);
					desktopPane.add(C);
					C.setVisible(true);
				}else{
					desktopPane.add(C);
					C.setVisible(true);
				}
				
				
				}
				});
				
				itErgEsgLTC = new JMenuItem("GESTION ENSEIGNANTS LTC");
				itErgEsgLTC.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ifEnseigLo2LTC C =new ifEnseigLo2LTC();
						
						if(cat.getText().equals("gestionnaire")){
							C.getBtnSupprimer().setEnabled(false);
							desktopPane.add(C);
							C.setVisible(true);
						}else{
							desktopPane.add(C);
							C.setVisible(true);
						}
						
					}
				});
				itErgEsgLTC.setBackground(UIManager.getColor("List.selectionBackground"));
				fchAdm.add(itErgEsgLTC);
				
				itErgELE = new JMenuItem("GESTION ELEVES");
				itErgELE.setIcon(new ImageIcon(".\\img\\b_usradd.png"));
				fchAdm.add(itErgELE);
				itErgELE.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				ifEleve2 a = new ifEleve2();
				
				if(cat.getText().equals("gestionnaire")){
					a.getBtnSupprimer().setEnabled(false);
					a.getCatEnsEle().setText(cat.getText());
					desktopPane.add(a);
					a.setVisible(true);
				}else{
					desktopPane.add(a);
					a.setVisible(true);/**/
				}
				
				
				}
				});
				
				itErgELELTC = new JMenuItem("GESTION ELEVES LTC");
				itErgELELTC.setBackground(UIManager.getColor("MenuItem.selectionBackground"));
				itErgELELTC.setIcon(new ImageIcon(".\\img\\b_usradd.png"));
				fchAdm.add(itErgELELTC );
				itErgELELTC.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				ifEleveLTC a = new ifEleveLTC();
				
				if(cat.getText().equals("gestionnaire")){
					a.getBtnSupprimer().setEnabled(false);
					a.getCatEnsEle().setText(cat.getText());
					desktopPane.add(a);
					a.setVisible(true);
				}else{
					desktopPane.add(a);
					a.setVisible(true);/**/
				}
				
				
				}
				});
				
				itErgCoursEL = new JMenuItem("GESTION COURS");
				itErgCoursEL.setIcon(new ImageIcon(".\\img\\b_group.png"));
				fchAdm.add(itErgCoursEL);
				itErgCoursEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				ifCours f = new ifCours();
				desktopPane.add(f);
				f.setVisible(true);
				
				}
				});
				
				mntmGestionCoursLtc = new JMenuItem("GESTION COURS LTC");
				mntmGestionCoursLtc.setBackground(UIManager.getColor("List.selectionBackground"));
				mntmGestionCoursLtc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						ifCoursLTC f = new ifCoursLTC();
						desktopPane.add(f);
						f.setVisible(true);
					}
				});
				fchAdm.add(mntmGestionCoursLtc);
				
				
				
				
				
				impBull = new JMenuItem("EDITION BULLETINS");
				impBull.setIcon(new ImageIcon(".\\img\\b_bul.png"));
				fchAdm.add(impBull);
				impBull.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				/*impBull f = new impBull();
				//desktopPane.add(f);
				f.setVisible(true);*/
				
				ifImp C =new ifImp();
				desktopPane.add(C);
				C.setVisible(true);
				
				
				}
				});
				
				
				itMPas = new JMenuItem("MODIFIER MOT DE PASS");
				itMPas.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ifModifPas f = new ifModifPas();
						f.getTextFieldID().setText(textField.getText().toString());
						f.getTextLog().setText(textFieldUser.getText().toString());
						desktopPane.add(f);
						f.setVisible(true);
					}
				});
				
				mntmEditionBulletinsLtc = new JMenuItem("EDITION BULLETINS LTC");
				mntmEditionBulletinsLtc.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {

						ifImpLTC C =new ifImpLTC();
						desktopPane.add(C);
						C.setVisible(true);
					}
				});
				mntmEditionBulletinsLtc.setBackground(UIManager.getColor("List.selectionBackground"));
				fchAdm.add(mntmEditionBulletinsLtc);
				fchAdm.add(itMPas);
				
				
				

				mntmEditionPv = new JMenuItem("P.V EXAMENS");
				fchAdm.add(mntmEditionPv);
				mntmEditionPv.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						ifImp1 C =new ifImp1();
						desktopPane.add(C);
						C.setVisible(true);
					}
					
				});
				
				
		
		/////////////////////////////////////////////////
		// MENU ELEVES ET INTERNALFRAME POUR ELEVE    ///
		/////////////////////////////////////////////////
			fchEleve = new JMenu("ENSEIGNANTS");
			fchEleve.setIcon(new ImageIcon(".\\img\\user-icon.png"));
			menuBar.add(fchEleve);
			
				
		    itErgNotEL = new JMenuItem("GESTION NOTES");
			itErgNotEL.setIcon(new ImageIcon(".\\img\\b_edit.png"));
			fchEleve.add(itErgNotEL);
			//fchEleve.setEnabled(false);
			itErgNotEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					
					
					if(cat.getText().equals("enseignant Prof Principal") || cat.getText().equals("admin")|| cat.getText().equals("gestionnaire")){
						ifNoteElevesProfPrin f = new ifNoteElevesProfPrin();
						FenMenu ft = new FenMenu();
						System.out.println("Reussit ");
						f.getComboCours().setSelectedItem("Choix");
						f.getTextCat().setText(cat.getText());
						
						
						if(textFieldMat.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							f.getComboCours().setSelectedItem(textFieldMat.getText().toString());
							f.getComboCours().setEnabled(true);
							
						}
						
						if(textField.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							
							f.getTextIDEns().setText(textField.getText().toString());
							Donne mDon = new Donne();
							ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(textField.getText().toString()));
							
							for(int i = 0; i < list3.size(); i++ ){
								f.getComboClasse().addItem(list3.get(i).getNomCls());
							}
								
							for (int j = 0; j < list3.size(); j++) {
									f.getComboCours().addItem(list3.get(j).getEnsCours());
							}
								
								
						}
						if(cat.getText().equals("gestionnaire")){
	                       
	                        f.getSupprimer().setEnabled(false);
	                        
						}
						
						desktopPane.add(f);
						f.setVisible(true);
						
					}else {
						
						ifNoteEleves f = new ifNoteEleves();
						FenMenu ft = new FenMenu();
						System.out.println("Reussit ");
						f.getComboCours().setSelectedItem("Choix");
						f.getTextCat().setText(cat.getText());
						
						
						if(textFieldMat.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							f.getComboCours().setSelectedItem(textFieldMat.getText().toString());
							f.getComboCours().setEnabled(true);
							
						}
						
						if(textField.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							
							f.getTextIDEns().setText(textField.getText().toString());
							Donne mDon = new Donne();
							ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(textField.getText().toString()));
							
							for(int i = 0; i < list3.size(); i++ ){
								f.getComboClasse().addItem(list3.get(i).getNomCls());
							}
								
							for (int j = 0; j < list3.size(); j++) {
									f.getComboCours().addItem(list3.get(j).getEnsCours());
							}
								
								
						}
						if(cat.getText().equals("gestionnaire")){
	                       
	                        f.getSupprimer().setEnabled(false);
	                        
						}
						
						desktopPane.add(f);
						f.setVisible(true);
						
						
						
						
						
					}
					
					
				
				}
			});
			
			itOr = new JMenuItem("ORIENTATION");
			itOr.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					

					if(cat.getText().equals("enseignant Prof Principal") || cat.getText().equals("admin")|| cat.getText().equals("gestionnaire")){
						ifNoteElevesOrientation f = new ifNoteElevesOrientation();
						//FenMenu ft = new FenMenu();
						System.out.println("Reussit ");
						f.getComboCours().setSelectedItem("Choix");
						f.getTextCat().setText(cat.getText());
						
						
						if(textFieldMat.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							f.getComboCours().setSelectedItem(textFieldMat.getText().toString());
							f.getComboCours().setEnabled(true);
							
						}
						
						if(textField.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							
							f.getTextIDEns().setText(textField.getText().toString());
							Donne mDon = new Donne();
							ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(textField.getText().toString()));
							
							for(int i = 0; i < list3.size(); i++ ){
								f.getComboClasse().addItem(list3.get(i).getNomCls());
							}
								
							for (int j = 0; j < list3.size(); j++) {
									f.getComboCours().addItem(list3.get(j).getEnsCours());
							}
								
								
						}
						if(cat.getText().equals("gestionnaire")){
	                       
	                       
	                        
						}
						
						desktopPane.add(f);
						f.setVisible(true);
						
					}else {
						
						ifNoteEleves f = new ifNoteEleves();
						FenMenu ft = new FenMenu();
						System.out.println("Reussit ");
						f.getComboCours().setSelectedItem("Choix");
						f.getTextCat().setText(cat.getText());
						
						
						if(textFieldMat.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							f.getComboCours().setSelectedItem(textFieldMat.getText().toString());
							f.getComboCours().setEnabled(true);
							
						}
						
						if(textField.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							
							f.getTextIDEns().setText(textField.getText().toString());
							Donne mDon = new Donne();
							ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(textField.getText().toString()));
							
							for(int i = 0; i < list3.size(); i++ ){
								f.getComboClasse().addItem(list3.get(i).getNomCls());
							}
								
							for (int j = 0; j < list3.size(); j++) {
									f.getComboCours().addItem(list3.get(j).getEnsCours());
							}
								
								
						}
						if(cat.getText().equals("gestionnaire")){
	                       
	                        f.getSupprimer().setEnabled(false);
	                        
						}
						
						desktopPane.add(f);
						f.setVisible(true);
						
						
						
						
						
					}
					
					
				
				
					
				}
			});
			
			
			
			
			mntmGestionNotesLtc = new JMenuItem("GESTION NOTES LTC");
			mntmGestionNotesLtc.setBackground(UIManager.getColor("List.selectionBackground"));
			mntmGestionNotesLtc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
					
					
					
					if(cat.getText().equals("enseignant Prof Principal") || cat.getText().equals("admin")|| cat.getText().equals("gestionnaire")){
						ifNoteElevesProfPrinLTC f = new ifNoteElevesProfPrinLTC();
						FenMenu ft = new FenMenu();
						System.out.println("Reussit ");
						f.getComboCours().setSelectedItem("Choix");
						f.getTextCat().setText(cat.getText());
						
						
						if(textFieldMat.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							f.getComboCours().setSelectedItem(textFieldMat.getText().toString());
							f.getComboCours().setEnabled(true);
							
						}
						
						if(textField.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							
							f.getTextIDEns().setText(textField.getText().toString());
							Donne mDon = new Donne();
							ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(textField.getText().toString()));
							
							for(int i = 0; i < list3.size(); i++ ){
								f.getComboClasse().addItem(list3.get(i).getNomCls());
							}
								
							for (int j = 0; j < list3.size(); j++) {
									f.getComboCours().addItem(list3.get(j).getEnsCours());
							}
								
								
						}
						if(cat.getText().equals("gestionnaire")){
	                       
	                        f.getSupprimer().setEnabled(false);
	                        
						}
						
						desktopPane.add(f);
						f.setVisible(true);
						
					}else {
						
						ifNoteElevesLTC f = new ifNoteElevesLTC();
						FenMenu ft = new FenMenu();
						System.out.println("Reussit ");
						f.getComboCours().setSelectedItem("Choix");
						f.getTextCat().setText(cat.getText());
						
						
						if(textFieldMat.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							f.getComboCours().setSelectedItem(textFieldMat.getText().toString());
							f.getComboCours().setEnabled(true);
							
						}
						
						if(textField.getText().equals("")){
							/*f.getComboCours().setSelectedItem("CONDUITE");
							f.getComboCours().setEnabled(false);*/
						}else{
							
							f.getTextIDEns().setText(textField.getText().toString());
							Donne mDon = new Donne();
							ArrayList<enseignantEL> list3 = mDon.getDataEn(Integer.parseInt(textField.getText().toString()));
							
							for(int i = 0; i < list3.size(); i++ ){
								f.getComboClasse().addItem(list3.get(i).getNomCls());
							}
								
							for (int j = 0; j < list3.size(); j++) {
									f.getComboCours().addItem(list3.get(j).getEnsCours());
							}
								
								
						}
						if(cat.getText().equals("gestionnaire")){
	                       
	                        f.getSupprimer().setEnabled(false);
	                        
						}
						
						desktopPane.add(f);
						f.setVisible(true);
						
						
						
						
						
					}
					
					
				
				}
			});
			fchEleve.add(mntmGestionNotesLtc);
			fchEleve.add(itOr);
			
			imprimer = new JMenuItem("LISTE ELEVE");
			imprimer.setIcon(new ImageIcon(".\\img\\b_usrlist.png"));
			fchEleve.add(imprimer);
			imprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Imprimer f = new Imprimer();
					//
					f.setVisible(true);
					
				}
			});
			
			
			
		
		////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////														////////////////////
		////////////////														////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		fchCaisse = new JMenu("TRESORERIE");
		fchCaisse.setIcon(new ImageIcon(".\\img\\b_tre.gif"));
		menuBar.add(fchCaisse);
		
		itIns = new JMenuItem("BILAN INSCRIPTION PAR CLASSE ");
		itIns.setIcon(new ImageIcon(".\\img\\b_bila.gif"));
		fchCaisse.add(itIns);
		itIns.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				//BilanClasse G = new BilanClasse();
				ifTabRecapEntree G = new  ifTabRecapEntree();
				desktopPane.add(G);
				G.setVisible(true);
				
			}
		});
		
		itInsA = new JMenuItem("BILAN INSCRIPTION ANNUEL ");
		itInsA.setIcon(new ImageIcon(".\\img\\b_liste.gif"));
		fchCaisse.add(itInsA);
		itInsA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ifTabRecap G = new ifTabRecap();
				desktopPane.add(G);
				G.setVisible(true);
				
			}
		});
		
		
		////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////														////////////////////
		////////////////														////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		
		param = new JMenu("CAISSE");
		param.setIcon(new ImageIcon(".\\img\\param.png"));
		menuBar.add(param);
		
		
		
		recette = new JMenuItem("GESTION DES RECETTES ");
		recette.setIcon(new ImageIcon(".\\img\\an.gif"));
		param.add(recette);
		recette.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ifRecette G = new ifRecette();
				desktopPane.add(G);
				G.setVisible(true);
				
			}
		});
		
		
		recetteLTC = new JMenuItem("GESTION DES RECETTES LTC ");
		recetteLTC.setBackground(UIManager.getColor("List.selectionBackground"));
		recetteLTC.setIcon(new ImageIcon(".\\img\\an.gif"));
		param.add(recetteLTC);
		recetteLTC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ifRecetteLTC G = new ifRecetteLTC();
				desktopPane.add(G);
				G.setVisible(true);
				
			}
		});
		
		coef = new JMenuItem("GESTION DES DEPENSES ");
		coef.setIcon(new ImageIcon(".\\img\\coef.gif"));
		param.add(coef);
		
		ReimpRecu = new JMenuItem("REIMPRESSION RECU");
		ReimpRecu.setIcon(new ImageIcon(".\\img\\img2\\cmsprinting.gif"));
		param.add(ReimpRecu);
		ReimpRecu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ifReimpRecu R = new ifReimpRecu();
				desktopPane.add(R);
				R.setVisible(true);
			}
		});
		
		coef.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				
				if(cat.getText().equals("admin") || cat.getText().equals("Directeur")){
					ifDepense G = new ifDepense();
					desktopPane.add(G);
					G.setVisible(true);
				}else{
					ifDepenseGest G = new ifDepenseGest();
					desktopPane.add(G);
					G.setVisible(true);
				}
				
				
			}
		});
		
		
		
		/*reimp = new JMenuItem("R\u00E9impression\r\n");
		reimp.setIcon(new ImageIcon(".\\img\\img2\\cmsprinting.gif"));
		param.add(reimp);
		reimp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ifReimRecu T = new ifReimRecu();
				desktopPane.add(T);
				T.setVisible(true);
			}
		});
		
		*/
		
		
		////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////														////////////////////
		////////////////														////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////
		
		PARAMETRES = new JMenu("PARAMETRE ?");
		PARAMETRES.setIcon(new ImageIcon(".\\img\\s_process.png"));
		menuBar.add(PARAMETRES);
		
		annee = new JMenuItem("Gestion Ann\u00E9e Scolaire");
		annee.setIcon(new ImageIcon(".\\img\\coef.gif"));
		PARAMETRES.add(annee);
		annee.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ifAnneeScolaire G = new ifAnneeScolaire();
				desktopPane.add(G);
				G.setVisible(true);
				
			}
		});
		
		itErgSemEL = new JMenuItem("Gestion des Semestre");
		itErgSemEL .setIcon(new ImageIcon(".\\img\\b_group.png"));
		PARAMETRES.add(itErgSemEL);
		itErgSemEL.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
							ifSemestre f = new ifSemestre();
							desktopPane.add(f);
							f.setVisible(true);
				
				}
		});
		
		
		
		
		itErgClasse = new JMenuItem("Gestion des Classe ");
		itErgClasse .setIcon(new ImageIcon(".\\img\\b_edit.png"));
		PARAMETRES.add(itErgClasse);
		
		itGesUti = new JMenuItem("Gestion des Utilisateurs");
		itGesUti.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
					ifUsers f = new ifUsers();
					desktopPane.add(f);
					f.setVisible(true);
			}
		});
		
		
		itErgClasseLTC = new JMenuItem("Gestion Classe LTC");
		itErgClasseLTC .setIcon(new ImageIcon(".\\img\\b_edit.png"));
		PARAMETRES.add(itErgClasseLTC);
		
		
		PARAMETRES.add(itErgClasseLTC);
		itErgClasseLTC.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		ifClasse2 f = new ifClasse2();
		desktopPane.add(f);
		f.setVisible(true);
		
		}
		});
		
		PARAMETRES.add(itGesUti);
		
		itGesUtiLTC = new JMenuItem("Gestion des Utilisateurs LTC");
		itGesUtiLTC.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ifUsersLTC f = new ifUsersLTC();
				desktopPane.add(f);
				f.setVisible(true);
			}
		});
		PARAMETRES.add(itGesUtiLTC);
		itErgClasse.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
		ifClasse f = new ifClasse();
		desktopPane.add(f);
		f.setVisible(true);
		
		}
		});
		

		
		////////////////////////////////////////////////////////////////////////////////////////////
		/////////////////														////////////////////
		////////////////														////////////////////
		////////////////////////////////////////////////////////////////////////////////////////////

		
		
		INFO = new JMenu("INFO ?");
		INFO.setIcon(new ImageIcon(".\\img\\user-icon.png"));
		menuBar.add(INFO);
		
		JMenuItem mntmAPropos = new JMenuItem("A Propos ");
		INFO.add(mntmAPropos);
		
		JPanel panel = new JPanel();
		menuBar.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JButton btnNewButton = new JButton("Deconnecter");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				FenMenu.this.dispose();
				FenPrincipale h = new FenPrincipale();
				h.setVisible(true);
				
				
				
			}
		});
		btnNewButton.setBackground(new Color(153, 180, 209));
		panel.add(btnNewButton, BorderLayout.EAST);
		
		

 }
	
	
	

	public JTextField getTextFieldMat() {
		return textFieldMat;
	}


	public void setTextFieldMat(JTextField textFieldMat) {
		this.textFieldMat = textFieldMat;
	}


	public JMenu getINFO() {
		return INFO;
	}


	public void setINFO(JMenu iNFO) {
		INFO = iNFO;
	}


	public JMenu getPARAMETRES() {
		return PARAMETRES;
	}


	public void setPARAMETRES(JMenu pARAMETRES) {
		PARAMETRES = pARAMETRES;
	}


	public JMenu getParam() {
		return param;
	}

	public void setParam(JMenu param) {
		this.param = param;
	}

	public JMenu getFchEleve() {
		return fchEleve;
	}

	public void setFchEleve(JMenu fchEleve) {
		this.fchEleve = fchEleve;
	}

	public JMenu getFchEnsg() {
		return fchEnsg;
	}

	public void setFchEnsg(JMenu fchEnsg) {
		this.fchEnsg = fchEnsg;
	}

	public JMenu getFchCaisse() {
		return fchCaisse;
	}

	public void setFchCaisse(JMenu fchCaisse) {
		this.fchCaisse = fchCaisse;
	}

	public JMenu getFchAdm() {
		return fchAdm;
	}

	public void setFchAdm(JMenu fchAdm) {
		this.fchAdm = fchAdm;
	}
}
