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

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class ifCoursLTC extends JInternalFrame {
	private JTable table;
	private JTextField textTitre;
	private JTextField textCoef;

	/**
	 * Launch the application.
	 */
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JTextField textID;
	private JComboBox comTypeMa;
	private JTextField textidTy;

	public ifCoursLTC() {
			setTitle("Gestion des Cours ");
			setBounds(500, 128, 458, 442);
			setClosable(true);
			setIconifiable(true);
			setMaximizable(false);
			setResizable(false);
		    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
			getContentPane().setLayout(null);
			getContentPane().setLayout(null);
			setVisible(true);
			JPanel pan = new JPanel();
			pan.setBounds(10, 124, 431, 199);
			getContentPane().add(pan, BorderLayout.CENTER);
			pan.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 431, 199);
			pan.add(scrollPane);
			cnx = ConnexionMySql.ConnexiomBd();
			
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					String sql = "select * from coursltc where cr_id ='"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						
						if(resultat.next()){
							textID.setText(String.valueOf(resultat.getInt("cr_id")));
							textTitre.setText(resultat.getString("cr_titre"));
							textCoef.setText(String.valueOf(resultat.getInt("cr_coef")));
							
						}
						
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			});
			
			updateTable();
			scrollPane.setViewportView(table);
			getContentPane().setLayout(null);
			
			JButton bntAjouter = new JButton("Ajouter");
			bntAjouter.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql = "insert into coursltc (cr_titre, cr_coef, id_ty) values (?, ?, ? )";
					//String Date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					try {
							//
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, textTitre.getText().toString());
							prepared.setString(2, textCoef.getText().toString());
							prepared.setInt(3, Integer.parseInt(textidTy.getText().toString()));
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Enregistrement reussit :)");
							
							updateTable();
							} catch (SQLException e ) {
							JOptionPane.showMessageDialog(null, "Vous avez entrez une matière existante ou pas de matière. ");
				           }
					textTitre.setText("");
					textCoef.setText("");
					textID.setText("");
				}
			});
			bntAjouter.setBounds(10, 349, 125, 23);
			getContentPane().add(bntAjouter);
			
			JButton btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						String sql =" update coursltc set  cr_titre = ?  , cr_coef = ?  where cr_id = ?";
						
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textTitre.getText().toString());
						prepared.setString(2, textCoef.getText().toString());
						prepared.setString(3, textID.getText().toString());
						prepared.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
						updateTable();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e);
					}
				}
			});
			btnModifier.setBounds(161, 349, 125, 23);
			getContentPane().add(btnModifier);
			
			JButton btnSupprimer = new JButton("Supprimer");
			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql = "delete from coursltc where cr_id = ?";
					if(!textID.getText().equals("")){
						try{
							prepared = cnx.prepareStatement(sql);
							int id = Integer.parseInt(textID.getText());
							prepared.setInt(1, id);
							prepared.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "cours supprimer ");
					//------------------------------updateTable();
							updateTable();
							
						}catch(SQLException ex){
							
							JOptionPane.showMessageDialog(null, "Erreur "+ex);
						}
					}else{
						
						JOptionPane.showMessageDialog(null, "Choisissez le cours à supprimer ");
					}
				}
			});
			btnSupprimer.setBounds(312, 349, 125, 23);
			getContentPane().add(btnSupprimer);
			
			JLabel lblNomDeLa = new JLabel("Nom de la Mati\u00E8re :");
			lblNomDeLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblNomDeLa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblNomDeLa.setBounds(21, 11, 132, 23);
			getContentPane().add(lblNomDeLa);
			
			JLabel lblCoefMatire = new JLabel("Coef  Mati\u00E8re :");
			lblCoefMatire.setHorizontalAlignment(SwingConstants.CENTER);
			lblCoefMatire.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblCoefMatire.setBounds(31, 53, 132, 23);
			getContentPane().add(lblCoefMatire);
			
			textTitre = new JTextField();
			textTitre.setBounds(155, 11, 194, 22);
			getContentPane().add(textTitre);
			textTitre.setColumns(10);
			
			textCoef = new JTextField();
			textCoef.setColumns(10);
			textCoef.setBounds(155, 54, 194, 22);
			getContentPane().add(textCoef);
			
			textID = new JTextField();
			textID.setEnabled(false);
			textID.setHorizontalAlignment(SwingConstants.CENTER);
			textID.setBounds(369, 12, 46, 22);
			getContentPane().add(textID);
			textID.setColumns(10);
			
			JLabel lblTypeMatire = new JLabel("Type  Mati\u00E8re :");
			lblTypeMatire.setHorizontalAlignment(SwingConstants.CENTER);
			lblTypeMatire.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblTypeMatire.setBounds(21, 87, 132, 23);
			getContentPane().add(lblTypeMatire);
			
			comTypeMa = new JComboBox();
			comTypeMa.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
			comTypeMa.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					if (!comTypeMa.getSelectedItem().toString().equals("Choix")) {
						
					
					String sql = "select  * from type_matiereltc where titre = '"+comTypeMa.getSelectedItem()+"'";
					try {
							
							prepared = cnx.prepareStatement(sql);
							resultat = prepared.executeQuery();
						    while(resultat.next()){
						    	textidTy.setText(String.valueOf(resultat.getInt(1)));
						    	
						    }
						
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						textidTy.setText("");
					}
				}
			});
			comTypeMa.setBounds(155, 87, 194, 23);
			getContentPane().add(comTypeMa);
			
			textidTy = new JTextField();
			textidTy.setHorizontalAlignment(SwingConstants.CENTER);
			textidTy.setEnabled(false);
			textidTy.setColumns(10);
			textidTy.setBounds(369, 87, 46, 22);
			getContentPane().add(textidTy);
			updateTy();
		
		}
	
	
	
	
	public void updateTable(){
		
		String sql = "select  * from coursltc";
		
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
	
	public void updateTy(){
		
		String sql = "select  * from type_matiereltc";
	try {
			
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
		    while(resultat.next()){
		    	comTypeMa.addItem(resultat.getString("titre"));
		    }
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}		
}
