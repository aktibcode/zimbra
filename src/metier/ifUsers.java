package metier;
import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JLabel;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.SwingConstants;
import javax.swing.JTextField;

import connec.ConnexionMySql;

import java.sql.*;

import net.proteanit.sql.DbUtils;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.table.DefaultTableModel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class ifUsers extends JInternalFrame {
	private JTable table;
	private JTextField textLog;

	/**
	 * Launch the application.
	 */
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JTextField textFieldID;
	private JTextField textPass;
	private JComboBox comboCat;
	private JComboBox comboCours;

	public ifUsers() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textLog.setText("");
			}
		});
			setTitle("Gestion Utilisateurs");
			setBounds(550, 180, 536, 403);
			setClosable(true);
			setIconifiable(true);
			setMaximizable(false);
			setResizable(false);
		    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
			getContentPane().setLayout(null);
			getContentPane().setLayout(null);
			setVisible(true);
			JPanel pan = new JPanel();
			pan.setBounds(10, 202, 496, 126);
			getContentPane().add(pan, BorderLayout.CENTER);
			pan.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 496, 130);
			pan.add(scrollPane);
			cnx = ConnexionMySql.ConnexiomBd();
			
			
			table = new JTable();
			table.setSurrendersFocusOnKeystroke(true);
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					System.out.println(id);
					String sql = "select * from user where id_user = '"+Integer.parseInt(id)+"'";
					
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						
						if(resultat.next()){
							textFieldID.setText(String.valueOf(resultat.getString("id_user")));
							textLog.setText(String.valueOf(resultat.getString("user")));
							textPass.setText(String.valueOf(resultat.getString("passwd")));
							comboCat.setSelectedItem(resultat.getString("cat_user"));
							comboCours.setSelectedItem(resultat.getString("matEnsg"));
							
						}
						
					} catch (Exception e) {
						//System.out.println(e);
					}
				}
			});
			
			updateTable();
			scrollPane.setViewportView(table);
			getContentPane().setLayout(null);
			
			JButton bntAjouter = new JButton("Ajouter");
			bntAjouter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(comboCat.getSelectedItem().equals("admin") || comboCat.getSelectedItem().equals("Directeur") || comboCat.getSelectedItem().equals("tresorier")
							|| comboCat.getSelectedItem().equals("intendant")|| comboCat.getSelectedItem().equals("secretaire")){
						String n = "NULL";
						String sql = "insert into user (user, passwd, cat_user, matEnsg) values (?,?,?,?)";
						
						try {
								//
								prepared = cnx.prepareStatement(sql);
								prepared.setString(1, textLog.getText().toString());
								prepared.setString(2, textPass.getText().toString());
								prepared.setString(3, comboCat.getSelectedItem().toString());
								prepared.setString(4, n);
								prepared.executeUpdate();
								JOptionPane.showMessageDialog(null, "Enregistrement reussit :)");
								
								updateTable();
								} catch (SQLException e ) {
								JOptionPane.showMessageDialog(null, "Vous avez entrez une Classe Existante. ");
					           }
						
						
					}else{
						String sql = "insert into user (user, passwd, cat_user, matEnsg) values (?,?,?,?)";
						
						try {
								//
								prepared = cnx.prepareStatement(sql);
								prepared.setString(1, textLog.getText().toString());
								prepared.setString(2, textPass.getText().toString());
								prepared.setString(3, comboCat.getSelectedItem().toString());
								prepared.setString(4, comboCours.getSelectedItem().toString());
								prepared.executeUpdate();
								JOptionPane.showMessageDialog(null, "Enregistrement reussit :)");
								
								updateTable();
								} catch (SQLException e ) {
								JOptionPane.showMessageDialog(null, "Vous avez entrez une Classe Existante. ");
					           }
					}
					
					
					textLog.setText("");
					textPass.setText("");
					comboCat.setSelectedItem("Choix");
					comboCours.setSelectedItem("Choix");
				}
			});
			bntAjouter.setBounds(81, 339, 102, 23);
			getContentPane().add(bntAjouter);
			
			JButton btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!textFieldID.getText().toString().isEmpty()){
						
					
					try {
						String sql =" update user set user = ?, passwd = ?, cat_user = ?, matEnsg = ? where id_user = ? ";
						
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textLog.getText().toString());
						prepared.setString(2, textPass.getText().toString());
						prepared.setString(3, comboCat.getSelectedItem().toString());
						prepared.setString(4, comboCours.getSelectedItem().toString());
						prepared.setInt(5, Integer.parseInt(textFieldID.getText().toString()));
						;
						prepared.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
						updateTable();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e);
					}
					textLog.setText("");
					textPass.setText("");
					comboCat.setSelectedItem("Choix");
					textFieldID.setText("");
					}
				}
			});
			btnModifier.setBounds(207, 339, 102, 23);
			getContentPane().add(btnModifier);
			
			JButton btnSupprimer = new JButton("Supprimer");
			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql = "delete from user where id_user = ?";
					  int i = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer", "ERREUR", JOptionPane.YES_NO_OPTION);
					  if(i==0){
						 try{
							prepared = cnx.prepareStatement(sql);
							String id = textFieldID.getText().toString();
							prepared.setString(1, id);
							prepared.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "Utilisateur supprimé ");
					//------------------------------updateTable();
							updateTable();
							
						}catch(SQLException ex){
							
							JOptionPane.showMessageDialog(null, "Verifier les Liens avant de Supprimer ");
						} 
					  }
						
						updateTable();
						textLog.setText("");
						textPass.setText("");
				}
			});
			btnSupprimer.setBounds(336, 339, 104, 23);
			getContentPane().add(btnSupprimer);
			
			JPanel panel = new JPanel();
			panel.setBounds(10, 13, 496, 178);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNom = new JLabel("Entrez Nom Utilisateur :");
			lblNom.setBounds(19, 13, 144, 15);
			panel.add(lblNom);
			lblNom.setHorizontalAlignment(SwingConstants.CENTER);
			lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			
			textLog = new JTextField();
			textLog.setBounds(168, 5, 188, 32);
			panel.add(textLog);
			textLog.setColumns(10);
			
			textFieldID = new JTextField();
			textFieldID.setBounds(400, 5, 86, 20);
			panel.add(textFieldID);
			textFieldID.setEnabled(false);
			textFieldID.setEditable(false);
			textFieldID.setColumns(10);
			
			JLabel lblEntrezPas = new JLabel("Entrez Mot de Pass :");
			lblEntrezPas.setBounds(19, 55, 144, 15);
			panel.add(lblEntrezPas);
			lblEntrezPas.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntrezPas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			
			textPass = new JTextField();
			textPass.setBounds(168, 48, 188, 30);
			panel.add(textPass);
			textPass.setColumns(10);
			
			JLabel lblCate = new JLabel("Cat\u00E9gorie Utilisateur :");
			lblCate.setBounds(19, 96, 144, 15);
			panel.add(lblCate);
			lblCate.setHorizontalAlignment(SwingConstants.CENTER);
			lblCate.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			
			comboCat = new JComboBox();
			comboCat.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if(!comboCat.getSelectedItem().equals("enseignant")){
						comboCours.setEnabled(false);
						comboCours.setSelectedItem("Choix");
						
					}else{
						comboCours.setEnabled(true);
					}
					
				}
			});
			comboCat.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
			comboCat.setBounds(168, 87, 188, 35);
			panel.add(comboCat);
			
			JLabel lblMatireEns = new JLabel("Mati\u00E8re Ens. :");
			lblMatireEns.setHorizontalAlignment(SwingConstants.CENTER);
			lblMatireEns.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblMatireEns.setBounds(19, 141, 144, 15);
			panel.add(lblMatireEns);
			
			comboCours = new JComboBox();
			comboCours.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
			comboCours.setBounds(168, 132, 188, 35);
			panel.add(comboCours);
			updateTableCombo();
			remplirMatiere();
		
		}
	
	
	
	
	public void updateTable(){
		
		String sql = "select  id_user, user, passwd, cat_user from user";
		
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
	
	public void updateTableCombo(){
		
		String sql = "select  categorie from cateuser";
		
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next()){
		    	comboCat.addItem(resultat.getString(1));
		    }
			} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
	
	public void remplirMatiere(){
		String sql = "select * from cours ";
		
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			while(resultat.next()){
				String matiere = resultat.getString("cr_titre").toString();
				comboCours.addItem(matiere);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
	}
}
