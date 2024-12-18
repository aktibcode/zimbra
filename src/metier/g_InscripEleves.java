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
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.SwingConstants;
import javax.swing.JComboBox;
import javax.swing.JTextField;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import net.proteanit.sql.DbUtils;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.table.DefaultTableModel;
import javax.swing.JButton;

import connec.ConnexionMySql;
import connec.Donne;
import entite.eleveEL;
import entite.matiereEL;
import entite.noteEL;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class g_InscripEleves extends JInternalFrame {
	
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	ResultSet rs = null;
	Statement st = null;
	private JTextField note1;
	private JTextField note2;
	private JTextField noteExamen;
	private JComboBox comboClasse;
	private JComboBox comboEleve;
	private JComboBox comboNumero;
	private JLabel lblNumeroEleve;
	private JLabel lblChoisissezUnEleve;
	private JLabel lblChoix;
	private JLabel lblNote;
	private JLabel lblNote_1;
	private JLabel lblNoteExamen;
	private JComboBox comboCours;
	private JTable table;
	private JComboBox comboSemestre;
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
protected String idnote;
	private JTextField num1;
	
	
	public g_InscripEleves() {
		setTitle("Gestion des Notes ");
		setBounds(300, 128, 999, 474);
		setClosable(true);
		setIconifiable(true);
		setMaximizable(false);
		setResizable(false);
	    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		setVisible(true);
		cnx = ConnexionMySql.ConnexiomBd();
		
		
		lblChoix = new JLabel("Choisissez un Classe:");
		lblChoix.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoix.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblChoix.setBounds(10, 25, 155, 27);
		getContentPane().add(lblChoix);
		
		comboClasse = new JComboBox();
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
					Donne mk = new Donne();
					ArrayList<eleveEL> list = mk.getData2(comboClasse.getSelectedItem().toString());
					for(int i = 0; i < list.size(); i++ ){
						comboEleve.addItem(list.get(i).getNomEtprenom());
					}
				}
			}
			
		});
		comboClasse.setBounds(10, 63, 155, 27);
		getContentPane().add(comboClasse);
		
		comboEleve = new JComboBox();
		comboEleve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				comboNumero.removeAllItems();
				Donne mk = new Donne();
				ArrayList<eleveEL> list2 = mk.getData3(comboEleve.getSelectedItem().toString());
				for(int j = 0; j < list2.size(); j++ ){
					comboNumero.addItem(list2.get(j).getElv_id());
					
				}
				
			}
		});
		
		comboEleve.setBounds(175, 63, 155, 27);
		getContentPane().add(comboEleve);
		
		comboNumero = new JComboBox();
		comboNumero.setBounds(340, 63, 155, 27);
		getContentPane().add(comboNumero);
		
		lblChoisissezUnEleve = new JLabel("Choisissez un Eleve:");
		lblChoisissezUnEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoisissezUnEleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblChoisissezUnEleve.setBounds(175, 25, 155, 27);
		getContentPane().add(lblChoisissezUnEleve);
		
		lblNumeroEleve = new JLabel("Numero Eleve :");
		lblNumeroEleve.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumeroEleve.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNumeroEleve.setBounds(340, 25, 155, 27);
		getContentPane().add(lblNumeroEleve);
		
		note1 = new JTextField();
		note1.setBounds(340, 153, 155, 27);
		getContentPane().add(note1);
		note1.setColumns(10);
		
		note2 = new JTextField();
		note2.setColumns(10);
		note2.setBounds(505, 153, 155, 27);
		getContentPane().add(note2);
		
		noteExamen = new JTextField();
		noteExamen.setColumns(10);
		noteExamen.setBounds(670, 153, 155, 27);
		getContentPane().add(noteExamen);
		
		lblNote = new JLabel("Note 1:");
		lblNote.setHorizontalAlignment(SwingConstants.CENTER);
		lblNote.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNote.setBounds(340, 115, 155, 27);
		getContentPane().add(lblNote);
		
		lblNote_1 = new JLabel("Note 2:");
		lblNote_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNote_1.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNote_1.setBounds(505, 115, 155, 27);
		getContentPane().add(lblNote_1);
		
		lblNoteExamen = new JLabel("Note Examen:");
		lblNoteExamen.setHorizontalAlignment(SwingConstants.CENTER);
		lblNoteExamen.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblNoteExamen.setBounds(670, 115, 155, 27);
		getContentPane().add(lblNoteExamen);
		
		comboCours = new JComboBox();
		comboCours.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboCours.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				comboCoef.removeAllItems();
				Donne mk = new Donne();
				ArrayList<matiereEL> list2 = mk.getData(comboCours.getSelectedItem().toString());
				for(int j = 0; j < list2.size(); j++ ){
					comboCoef.addItem(list2.get(j).getCr_coef());
					
				}
			}
		});
		comboCours.setBounds(10, 153, 155, 27);
		getContentPane().add(comboCours);
		
		JLabel lblChoisissezMatire = new JLabel("Choisissez Mati\u00E8re:");
		lblChoisissezMatire.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoisissezMatire.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblChoisissezMatire.setBounds(10, 115, 155, 27);
		getContentPane().add(lblChoisissezMatire);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 214, 963, 149);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
				int ligne = table.getSelectedRow();
				String id = table.getModel().getValueAt(ligne, 0).toString();
				
				String sql = "select * from note where note_id ='"+id+"'";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
                            
										
					if(resultat.next()){
						
					comboSemestre.setSelectedItem(resultat.getString("Semestre"));
					comboClasse.setSelectedItem(resultat.getString("classe"));
					comboEleve.setSelectedItem(resultat.getString("nom_et_prenom"));
					comboCours.setSelectedItem(resultat.getString("matiere"));
					comboCoef.setSelectedItem(resultat.getString("coef"));
					note1.setText((String.valueOf(resultat.getDouble("note1"))));
					note2.setText((String.valueOf(resultat.getDouble("note2"))));
					noteExamen.setText((String.valueOf(resultat.getDouble("note_Examen")))); 
					comboNumero.setSelectedItem(resultat.getString("id_elv"));
					num1.setText((String.valueOf(resultat.getInt("note_id"))));
					updateTable2();
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erreur ici "+e.getMessage());
				}
				
				
				
			}
		});
		scrollPane.setViewportView(table);
		
		JButton btnAjouter = new JButton("Ajouter");
		btnAjouter.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent arg0) {
				

				if(checkput()){
					try {
					//if(){}else{}
					no1= note1.getText();
					no2= note2.getText();
					notEx= noteExamen.getText();
					noEx = Double.parseDouble(noteExamen.getText());
					moyCls = (Double.parseDouble(no1)+Double.parseDouble(no2))/2;
					moy_Gen = ((noEx+moyCls)/2)*(Double.parseDouble(comboCoef.getSelectedItem().toString()));
					
					idelv = comboNumero.getSelectedItem().toString();
					elvID = Integer.parseInt(idelv);
					Double note1_ = Double.parseDouble(no1);
					Double note2_ = Double.parseDouble(no2);

					
						PreparedStatement ps = cnx.prepareStatement("INSERT into note (Semestre, classe, nom_et_prenom,"
								+ "matiere, coef, note1, note2, note_Examen, moy_cls, moy_Gen, id_elv) values(?,?,?,?,?,?,?,?,?,?,?)");
						ps.setString(1, comboSemestre.getSelectedItem().toString());
						ps.setString(2, comboClasse.getSelectedItem().toString());
						ps.setString(3, comboEleve.getSelectedItem().toString());
						ps.setString(4, comboCours.getSelectedItem().toString());
						ps.setString(5, comboCoef.getSelectedItem().toString());
						ps.setDouble(6, note1_);
						ps.setDouble(7, note2_);
						ps.setDouble(8, noEx);
						ps.setDouble(9, moyCls);
						ps.setDouble(10, moy_Gen);
						ps.setInt(11, elvID);
						ps.executeUpdate();
						JOptionPane.showMessageDialog(null, "Enregistrement reussit ");
						init();
						updateTable2();
					} catch (SQLException e) {
						JOptionPane.showMessageDialog(null, "Verifier si le Semmestre est choisit ///");
					}
				}else{
					JOptionPane.showMessageDialog(null, "Certains champs sont incompletes !!! ");
				}
				
			}
		});
		btnAjouter.setBounds(10, 394, 125, 23);
		getContentPane().add(btnAjouter);
		
		JButton btnModifier = new JButton("Modifier");
		btnModifier.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(checkput()){
				no1= note1.getText();
				
				no2= note2.getText();
				notEx= noteExamen.getText();
				noEx = Double.parseDouble(noteExamen.getText());
				moyCls = (Double.parseDouble(no1)+Double.parseDouble(no2))/2;
				moy_Gen = ((noEx+moyCls)/2)*(Double.parseDouble(comboCoef.getSelectedItem().toString()));
				
				idnote = num1.getText().toString();
				elvID = Integer.parseInt(idnote);
				Double note1_ = Double.parseDouble(no1);
				Double note2_ = Double.parseDouble(no2);
				
				//resultat = prepared.executeQuery();
				//String not_id = num.setText(String.valueOf(resultat.getInt("note_id")));
				
				
				try {
				String sql =" update note set  Semestre = ?  , classe = ? , nom_et_prenom = ?, matiere= ?,"
						+ "coef= ?, note1= ?, note2= ?, note_Examen = ?, moy_cls = ? , moy_Gen = ? where note_id =?";
					
					prepared = cnx.prepareStatement(sql);
				    prepared.setString(1, comboSemestre.getSelectedItem().toString());
					prepared.setString(2, comboClasse.getSelectedItem().toString());
					prepared.setString(3, comboEleve.getSelectedItem().toString());
					prepared.setString(4, comboCours.getSelectedItem().toString());
					prepared.setString(5, comboCoef.getSelectedItem().toString());
					prepared.setDouble(6, note1_);
					prepared.setDouble(7, note2_);
					prepared.setDouble(8, noEx);
					prepared.setDouble(9, moyCls);
					prepared.setDouble(10, moy_Gen);
					prepared.setInt(11, elvID);
					prepared.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
					updateTable2();
					init();
				}catch(Exception e){
					
					
				}
				}else{
					JOptionPane.showMessageDialog(null, "Vérifié vos données");
				}
			}
		});
		btnModifier.setBounds(161, 394, 125, 23);
		getContentPane().add(btnModifier);
		
		JButton btnSupprimer = new JButton("Supprimer");
		btnSupprimer.setBounds(312, 394, 125, 23);
		getContentPane().add(btnSupprimer);
		
		comboSemestre = new JComboBox();
		comboSemestre.setModel(new DefaultComboBoxModel(new String[] {"Choix", "Semestre 1", "Semestre 2", "Semestre 3", "Semestre 4"}));
		comboSemestre.setBounds(818, 11, 155, 27);
		getContentPane().add(comboSemestre);
		
		lblCoefficientMatire = new JLabel("Coefficient Mati\u00E8re:");
		lblCoefficientMatire.setHorizontalAlignment(SwingConstants.CENTER);
		lblCoefficientMatire.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 14));
		lblCoefficientMatire.setBounds(175, 115, 155, 27);
		getContentPane().add(lblCoefficientMatire);
		
		comboCoef = new JComboBox();
		comboCoef.setBounds(175, 153, 155, 27);
		getContentPane().add(comboCoef);
		
		JButton btnEditionBulletin = new JButton("Edition Bulletin :");
		btnEditionBulletin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				impBull c = new impBull();
				c.setVisible(true);
			}
		});
		btnEditionBulletin.setBounds(465, 394, 125, 23);
		getContentPane().add(btnEditionBulletin);
		
		num1 = new JTextField();
		num1.setEnabled(false);
		num1.setEditable(false);
		num1.setBounds(818, 66, 86, 20);
		getContentPane().add(num1);
		num1.setColumns(10);
		BindCombo();
		BindComboCour();
		updateTable2();

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
	
	
	public void updateTable2(){
		String sql = "select  * from note";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			} catch (SQLException e) {
			// TODO Auto-generated catch block 
			System.out.println("Erreur 1 "+e);
		}
     }
	
	
	public boolean checkput(){
		if(note1 == null  || note2== null || noteExamen == null){
			return false;
		}else{
			
			try{
				Float.parseFloat(note1.getText());
				//Float.parseFloat(no1);
				Float.parseFloat(note2.getText());
//				Float.parseFloat(no2);
//				Float.parseFloat(notEx);
				Float.parseFloat(noteExamen.getText());
				Integer.parseInt(comboNumero.getSelectedItem().toString());
				return true;
			}catch(Exception e){
				return false;
			}
			
		}
	}
	
	public void init(){
		comboClasse.setSelectedItem("Choix");
		comboCours.setSelectedItem("Choix");
		comboEleve.setSelectedItem("Choix");
		comboNumero.setSelectedItem("Choix");
		comboSemestre.setSelectedItem("Choix");
		note1.setText("");
		note2.setText("");
		noteExamen.setText("");
		num1.setText("");
		
	}
}
