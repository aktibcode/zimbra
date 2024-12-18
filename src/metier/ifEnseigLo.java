package metier;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

import net.proteanit.sql.DbUtils;

import com.mysql.jdbc.Statement;
import com.toedter.calendar.JDateChooser;

import connec.ConnexionMySql;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.SwingConstants;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JScrollBar;
import javax.swing.border.TitledBorder;
import javax.swing.border.LineBorder;

import java.awt.Color;
import java.awt.event.ContainerAdapter;
import java.awt.event.ContainerEvent;


public class ifEnseigLo extends JInternalFrame {
	/*
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	
	private JTable table_2;
	@SuppressWarnings("rawtypes")
	private JComboBox comClasse;
	private JScrollPane scrollPane;
	private JLabel lblClasse;
	private JLabel lblNewLabel_3;
	@SuppressWarnings("rawtypes")
	private JComboBox comMatiere;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JTextField textid;
	private JButton btnEditerListe;
	private JComboBox comboLog;
	private JTextField textClas;
	private JTextField textMat;
	private JTextField txIDEnv;
	private JComboBox comboAnso;
	private JTextField idDel;
	
	
	
	public JButton getBtnSupprimer() {
		return btnSupprimer;
	}

	public void setBtnSupprimer(JButton btnSupprimer) {
		this.btnSupprimer = btnSupprimer;
	}

	@SuppressWarnings({ "rawtypes" })
	public ifEnseigLo(){
		getContentPane().setFont(new Font("Times New Roman", getContentPane().getFont().getStyle(), getContentPane().getFont().getSize()));

	setTitle("Gestion des Enseignants");
	setBounds(64, 128, 948, 487);
	setClosable(true);
	setIconifiable(true);
	setMaximizable(true);
	setFrameIcon(new ImageIcon("D:\\GETIONECOLE\\img\\img2\\user-icon.png"));
	getContentPane().setLayout(null);
	JPanel pan = new JPanel();
	pan.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Informations sur l'Enseignant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pan.setBounds(20, 163, 899, 231);
    getContentPane().add(pan, BorderLayout.CENTER);
	pan.setLayout(null);
	cnx = ConnexionMySql.ConnexiomBd();

	
	
	//
	
	btnAjouter = new JButton("Ajouter");
	btnAjouter.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			
			if(checkInput() == true &&  !txIDEnv.getText().toString().equals("") && !comboAnso.getSelectedItem().toString().equals("Choix")){
				
				String sql = "insert into enseignantel ( cours, nomEns, clsEns, idEns) values ( ?,? , ?,?)";
				//String Date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				try {
						//
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, comMatiere.getSelectedItem().toString());
						prepared.setString(2,comboLog.getSelectedItem().toString());
						prepared.setString(3, comClasse.getSelectedItem().toString());
						prepared.setInt(4, Integer.parseInt(txIDEnv.getText().toString()));
						prepared.executeUpdate();
						JOptionPane.showMessageDialog(null, "Enregistrement Reussit !!!");
						//initialisation();
						updateTable();
						} catch (SQLException e ) {
						JOptionPane.showMessageDialog(null, "Verifier si les données sont toutes introduites ");
			           }
					
				String sql2 = "insert into matparclasse ( idMClsEns , crTitre, clsNom, anne_scol) values ( ?,? , ?,?)";
				String num = txIDEnv.getText().toString();
				String no = textid.getText().toString();
				try {
					//
					prepared = cnx.prepareStatement(sql2);
					prepared.setInt(1, Integer.parseInt(no));
					prepared.setString(2, comMatiere.getSelectedItem().toString());
					prepared.setString(3, comClasse.getSelectedItem().toString());
					prepared.setString(4,comboAnso.getSelectedItem().toString());
					
					
					prepared.executeUpdate();
					
					initialisation();
					updateTable();
					} catch (SQLException e ) {
					//JOptionPane.showMessageDialog(null, "Erreur"+e);if(checkInput() == false)
		           }
				
					}else {
						JOptionPane.showMessageDialog(null, "L'un des champs ou plusieurs Champs sont vide :)");
				    }
			}
		
			
	});
	
			btnAjouter.setBounds(20, 405, 164, 23);
			this.getContentPane().add(btnAjouter);
			
			btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			
			if(!txIDEnv.getText().toString().equals("") && !textid.getText().toString().equals("")){
				try {
					String sql =" update enseignantel set nomEns =?, clsEns = ?,  cours =?,idEns = ?  where idMEns =?";
					
					prepared = cnx.prepareStatement(sql);
					prepared.setString(1, comboLog.getSelectedItem().toString());
					prepared.setString(2, comClasse.getSelectedItem().toString());
					prepared.setString(3, comMatiere.getSelectedItem().toString());
					prepared.setInt(4, Integer.parseInt(txIDEnv.getText()));
					prepared.setInt(5, Integer.parseInt(textid.getText()));
					prepared.executeUpdate();
					JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
					updateTable();
					initialisation();
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}else{
				JOptionPane.showMessageDialog(null, "Selectionnez un enseignant avant d'éffectuer cette opération !!! ");
			}
			
		}
	});
	btnModifier.setBounds(191, 405, 164, 23);
	this.getContentPane().add(btnModifier);
	btnSupprimer = new JButton("Supprimer");
	btnSupprimer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
            String sql = "DELETE FROM `enseignantel` WHERE `idMEns`=?";
			if(!textid.getText().equals("")){
				try{
					prepared = cnx.prepareStatement(sql);
					int id = Integer.parseInt(idDel.getText());
					prepared.setInt(1, id);
					prepared.executeUpdate();
					
					JOptionPane.showMessageDialog(null, "Enseignant supprimé ");
					updateTable();
					initialisation();
					
				}catch(SQLException ex){
					
					JOptionPane.showMessageDialog(null, "Erreur "+ex);
				}
			}else{
				
				JOptionPane.showMessageDialog(null, "Choisissez l'Enseignant à supprimer ");
			}
		
		}
	});
	btnSupprimer.setBounds(363, 405, 164, 23);
	this.getContentPane().add(btnSupprimer);
	
	btnEditerListe = new JButton("Editer Liste :");
	btnEditerListe.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			impListeEns p = new impListeEns();
			p.setVisible(true);
		}
	});
	btnEditerListe.setBounds(537, 405, 164, 23);
	getContentPane().add(btnEditerListe);
	
	JPanel panel = new JPanel();
	panel.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Entrez les Informations de l'enseignant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	panel.setBounds(72, 11, 795, 141);
	getContentPane().add(panel);
	panel.setLayout(null);
	
	
		comClasse = new JComboBox();
		comClasse.setBounds(11, 110, 164, 20);
		panel.add(comClasse);
		comClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		
		lblClasse = new JLabel("Classe en Charge :");
		lblClasse.setHorizontalAlignment(SwingConstants.CENTER);
		lblClasse.setBounds(10, 82, 165, 17);
		panel.add(lblClasse);
		lblClasse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		lblNewLabel_3 = new JLabel("Matière Enseigner :");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setBounds(214, 82, 163, 17);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		comMatiere = new JComboBox();
		comMatiere.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(!comMatiere.getSelectedItem().equals("Choix")){
					String sql = "select cr_id from cours where cr_titre = '"+comMatiere.getSelectedItem().toString()+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						if(resultat.next()){
							textMat.setText(resultat.getString("cr_id"));
							
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
					}
				
					
					}else{
						textMat.setText("");
					}
			}
		});
		comMatiere.setBounds(213, 110, 164, 20);
		panel.add(comMatiere);
		comMatiere.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		textid = new JTextField();
		
		
		
		textid.setBounds(421, 22, 158, 20);
		panel.add(textid);
		textid.setHorizontalAlignment(SwingConstants.CENTER);
		textid.setEnabled(false);
		textid.setEditable(false);
		textid.setColumns(10);
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setBounds(621, 109, 164, 23);
		panel.add(btnActualiser);
		
		comboLog = new JComboBox();
		comboLog.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String sql = "select id_user from user where user ='"+comboLog.getSelectedItem().toString()+"'";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					if(resultat.next()){
						txIDEnv.setText(resultat.getString("id_user"));
						textid.setText(resultat.getString("id_user"));
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erreur ici "+e.getMessage());
				}
				

			}
		});
		comboLog.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboLog.setBounds(147, 51, 164, 20);
		panel.add(comboLog);
		
		JLabel lblChoixDuLogin = new JLabel("Choix du Login :");
		lblChoixDuLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblChoixDuLogin.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblChoixDuLogin.setBounds(147, 23, 164, 17);
		panel.add(lblChoixDuLogin);
		
		textClas = new JTextField();
		textClas.setHorizontalAlignment(SwingConstants.CENTER);
		textClas.setEnabled(false);
		textClas.setEditable(false);
		textClas.setColumns(10);
		textClas.setBounds(185, 110, 23, 20);
		panel.add(textClas);
		
		textMat = new JTextField();
		textMat.setHorizontalAlignment(SwingConstants.CENTER);
		textMat.setEnabled(false);
		textMat.setEditable(false);
		textMat.setColumns(10);
		textMat.setBounds(387, 110, 23, 20);
		panel.add(textMat);
		
		txIDEnv = new JTextField();
		txIDEnv.setText("");
		
		txIDEnv.setHorizontalAlignment(SwingConstants.CENTER);
		txIDEnv.setEnabled(false);
		txIDEnv.setEditable(false);
		txIDEnv.setColumns(10);
		txIDEnv.setBounds(421, 51, 158, 20);
		panel.add(txIDEnv);
		
		comboAnso = new JComboBox();
		comboAnso.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAnso.setBounds(421, 110, 171, 20);
		panel.add(comboAnso);
		
		JLabel lblAnneScolaire = new JLabel("Ann\u00E9e Scolaire :");
		lblAnneScolaire.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnneScolaire.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblAnneScolaire.setBounds(422, 82, 170, 17);
		panel.add(lblAnneScolaire);
		
		idDel = new JTextField();
		idDel.setFont(new Font("Tahoma", Font.PLAIN, 5));
		idDel.setHorizontalAlignment(SwingConstants.CENTER);
		idDel.setEnabled(false);
		idDel.setEditable(false);
		idDel.setColumns(10);
		idDel.setBounds(543, 24, 34, 18);
		panel.add(idDel);
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				comClasse.setSelectedItem("Choix");
				comMatiere.setSelectedItem("Choix");
				comboLog.setSelectedItem("Choix");
				txIDEnv.setText("");
				textid.setText("");
				
				
				
			}
		});
		comClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if(!comClasse.getSelectedItem().equals("Choix")){
				String sql = "select id_classe from classe where cls_nom = '"+comClasse.getSelectedItem().toString()+"'";
				try {
					prepared = cnx.prepareStatement(sql);
					resultat = prepared.executeQuery();
					if(resultat.next()){
						textClas.setText(resultat.getString("id_classe"));
						
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					System.out.println("Erreur ici "+e.getMessage());
				}
			
				
				}else{
					textClas.setText("");
				}
			}
				
		});

		//// Tableau de données ///////
		//////////////////////////////
			scrollPane = new JScrollPane();
			scrollPane.setBounds(10, 24, 881, 180);
			pan.add(scrollPane);
			table_2 = new JTable();
			
				table_2.addMouseListener(new MouseAdapter() {
						@Override
						public void mouseClicked(MouseEvent arg0) {
						
					   /////////////
					    int ligne = table_2.getSelectedRow();
					    String id = table_2.getModel().getValueAt(ligne, 0).toString();
						String sql = "select * from  enseignantel where idMEns ='"+id+"' ";
						
						System.out.println("Le numero introduite est ="+id);
						try {
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
							
								if(resultat.next()){
								idDel.setText(resultat.getString("idMEns"));
								comboLog.setSelectedItem(resultat.getString("nomEns"));
								
								}
								
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						int ligne2 = table_2.getSelectedRow();
					    String id2 = table_2.getModel().getValueAt(ligne, 1).toString();
						
						String sql2 = "select * from  enseignantel where cours ='"+id2+"' ";
						try {
							prepared = cnx.prepareStatement(sql2);
							resultat = prepared.executeQuery();
							
								if(resultat.next()){
								comMatiere.setSelectedItem(resultat.getString("cours"));
								}
								
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
						int ligne3 = table_2.getSelectedRow();
					    String id3 = table_2.getModel().getValueAt(ligne, 2).toString();
						
						String sql3 = "select * from  enseignantel where clsEns ='"+id3+"' ";
						try {
							prepared = cnx.prepareStatement(sql3);
							resultat = prepared.executeQuery();
							
								if(resultat.next()){
								comClasse.setSelectedItem(resultat.getString("clsEns"));
								}
								
							
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					    
					    
					    }
					    
					});
		
		updateTable();
		scrollPane.setViewportView(table_2);
		
		remplirClasse();
		remplirMatiere();
		remplirLogin();
		BindComboCour();
    }

	public boolean checkInput(){
		if(comClasse.getSelectedItem().toString() == null ||comMatiere.getSelectedItem().toString() == null || comboLog.getSelectedItem().toString() == null
				){
			JOptionPane.showMessageDialog(null, "Remplissez les champs ou Choisissez un Login");
			return false;
		}else{
		return true;
		}
		
	}
	
	public void initialisation(){
		comClasse.setSelectedItem("Choix");
		comMatiere.setSelectedItem("Choix");
		comboLog.setSelectedItem("Choix");
		txIDEnv.setText("");
		textid.setText("");	
		}

    public void updateTable(){
	String sql = "SELECT idMEns, cours,clsEns , nomEns FROM enseignantel ";
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			
			table_2.setModel(DbUtils.resultSetToTableModel(resultat));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

@SuppressWarnings("unchecked")
public void remplirClasse(){
	String sql = "select * from classe where coef_classe != '0' ";
	
	try {
		prepared = cnx.prepareStatement(sql);
		resultat = prepared.executeQuery();
		while(resultat.next()){
			String classe = resultat.getString("cls_nom").toString();
			comClasse.addItem(classe);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

public void remplirLogin(){
	String sql = "select * from user ";
	
	try {
		prepared = cnx.prepareStatement(sql);
		resultat = prepared.executeQuery();
		while(resultat.next()){
			String classe = resultat.getString("user").toString();
			comboLog.addItem(classe);
		}
		
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}

@SuppressWarnings("unchecked")
public void remplirMatiere(){
	String sql = "select * from cours ";
	
	try {
		prepared = cnx.prepareStatement(sql);
		resultat = prepared.executeQuery();
		while(resultat.next()){
			String matiere = resultat.getString("cr_titre").toString();
			comMatiere.addItem(matiere);
		}
		
	} catch (SQLException e) {
		
		e.printStackTrace();
	}
	
}

public void BindComboCour(){
	
	String sql = "SELECT anne_courant FROM annee ";
	
	try {
		
		prepared = cnx.prepareStatement(sql);
		resultat = prepared.executeQuery();
	    while(resultat.next()){
	    	comboAnso.addItem(resultat.getString(1));
	    }
	
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
}
}
