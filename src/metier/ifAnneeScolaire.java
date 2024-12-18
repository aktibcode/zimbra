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


public class ifAnneeScolaire extends JInternalFrame {
	private JTable table;
	private JTextField textTitre;

	/**
	 * Launch the application.
	 */
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JTextField textFieldID;

	public ifAnneeScolaire() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textTitre.setText("");
			}
		});
			setTitle("Gestion Année Scolaire ");
			setBounds(550, 180, 398, 338);
			setClosable(true);
			setIconifiable(true);
			setMaximizable(false);
			setResizable(false);
		    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
			getContentPane().setLayout(null);
			getContentPane().setLayout(null);
			setVisible(true);
			JPanel pan = new JPanel();
			pan.setBounds(65, 126, 254, 137);
			getContentPane().add(pan, BorderLayout.CENTER);
			pan.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 254, 126);
			pan.add(scrollPane);
			cnx = ConnexionMySql.ConnexiomBd();
			
			
			table = new JTable();
			table.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					int ligne = table.getSelectedRow();
					String id = table.getModel().getValueAt(ligne, 0).toString();
					System.out.println(id);
					String sql = "select * from annee where id_annee = '"+Integer.parseInt(id)+"'";
					
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						
						if(resultat.next()){
							textFieldID.setText(String.valueOf(resultat.getString("id_annee")));
							textTitre.setText(String.valueOf(resultat.getString("anne_courant")));
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
					String sql = "insert into annee (anne_courant) values (?)";
					//String Date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					try {
							//
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, textTitre.getText().toString());
							/*prepared.setString(2, textCoef.getText().toString());*/
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Enregistrement reussit :)");
							
							updateTable();
							} catch (SQLException e ) {
							JOptionPane.showMessageDialog(null, "Vous avez entrez un Année Scolaire Existante. ");
				           }
					textTitre.setText("");
					//textCoef.setText("");
					
				}
			});
			bntAjouter.setBounds(10, 274, 102, 23);
			getContentPane().add(bntAjouter);
			
			JButton btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					int i = JOptionPane.showConfirmDialog(null, "Voulez-vous Modifier cette Année.", "Cela pourra causer la perte des Données", JOptionPane.YES_NO_OPTION);
					
					if(i==0){
						try {
							String sql =" update annee set anne_courant = ? where id_annee = ? ";
							
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, textTitre.getText().toString());
							prepared.setString(2, textFieldID.getText().toString());
							;
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
							updateTable();
						} catch (Exception e) {
							// TODO: handle exception
							JOptionPane.showMessageDialog(null, "Verifier les Liens avec cette ANNEE");;
						}
					}
					
				}
			});
			btnModifier.setBounds(136, 274, 102, 23);
			getContentPane().add(btnModifier);
			
			JButton btnSupprimer = new JButton("Supprimer");
			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql = "delete from annee where anne_courant = ?";
					int i = JOptionPane.showConfirmDialog(null, "Voulez-vous Supprimer cette Année.", "Message", JOptionPane.YES_NO_OPTION);
					
					if(i==0){
						try{
							prepared = cnx.prepareStatement(sql);
							String id = textTitre.getText().toString();
							prepared.setString(1, id);
							prepared.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "Année supprimer ");
					//------------------------------updateTable();
							updateTable();
							
						}catch(SQLException ex){
							
							JOptionPane.showMessageDialog(null, "Les Liens avec Cette ANNEE doivent d'abord être supprimer avant");
						}
					}
						
					/*if(!textID.getText().equals("")){
					}else{
						
						JOptionPane.showMessageDialog(null, "Choisissez le cours à supprimer ");
					}*/
				}
			});
			btnSupprimer.setBounds(265, 274, 104, 23);
			getContentPane().add(btnSupprimer);
			
			JLabel lblNomDeLa = new JLabel("Entrez Ann\u00E9e :");
			lblNomDeLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblNomDeLa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblNomDeLa.setBounds(10, 37, 119, 23);
			getContentPane().add(lblNomDeLa);
			
			JLabel lblCoefMatire = new JLabel("Exemple : XXXX-YYYY");
			lblCoefMatire.setHorizontalAlignment(SwingConstants.CENTER);
			lblCoefMatire.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblCoefMatire.setBounds(10, 79, 359, 23);
			getContentPane().add(lblCoefMatire);
			
			textTitre = new JTextField();
			textTitre.setBounds(133, 38, 186, 22);
			getContentPane().add(textTitre);
			textTitre.setColumns(10);
			
			textFieldID = new JTextField();
			textFieldID.setEnabled(false);
			textFieldID.setEditable(false);
			textFieldID.setBounds(330, 39, 39, 20);
			getContentPane().add(textFieldID);
			textFieldID.setColumns(10);
		
		}
	
	
	
	
	public void updateTable(){
		
		String sql = "select  * from annee";
		
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(resultat));
			} catch (SQLException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
	}
}
