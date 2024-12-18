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


public class ifClasse extends JInternalFrame {
	private JTable table;
	private JTextField textTitre;

	/**
	 * Launch the application.
	 */
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JTextField textFieldID;
	private JTextField textFIns;
	private JTextField textFTen;
	private JTextField textFCoef;

	public ifClasse() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textTitre.setText("");
			}
		});
			setTitle("Gestion Classe");
			setBounds(550, 180, 532, 338);
			setClosable(true);
			setIconifiable(true);
			setMaximizable(false);
			setResizable(false);
		    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
			getContentPane().setLayout(null);
			getContentPane().setLayout(null);
			setVisible(true);
			JPanel pan = new JPanel();
			pan.setBounds(10, 137, 496, 126);
			getContentPane().add(pan, BorderLayout.CENTER);
			pan.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(0, 0, 496, 126);
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
					String sql = "select * from classe where id_classe = '"+Integer.parseInt(id)+"'";
					
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						
						if(resultat.next()){
							textFieldID.setText(String.valueOf(resultat.getString("id_classe")));
							textTitre.setText(String.valueOf(resultat.getString("cls_nom")));
							textFIns.setText(String.valueOf(resultat.getDouble("Montant_cls")));
							textFTen.setText(String.valueOf(resultat.getDouble("Montant_tenue")));
							textFCoef.setText(String.valueOf(resultat.getInt("coef_classe")));
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
					String sql = "insert into classe (cls_nom, Montant_cls, Montant_Tenue, coef_classe) values (?,?,?,?)";
					//String Date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
					try {
							//
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, textTitre.getText().toString());
							prepared.setDouble(2, Double.parseDouble(textFIns.getText().toString()));
							prepared.setDouble(3, Double.parseDouble(textFTen.getText().toString()));
							prepared.setInt(4, Integer.parseInt(textFCoef.getText().toString()));
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Enregistrement reussit :)");
							
							updateTable();
							} catch (SQLException e ) {
							JOptionPane.showMessageDialog(null, "Vous avez entrez une Classe Existante. ");
				           }
					textTitre.setText("");
					textFCoef.setText("");
					textFTen.setText("");
					textFIns.setText("");
					
				}
			});
			bntAjouter.setBounds(83, 274, 102, 23);
			getContentPane().add(bntAjouter);
			
			JButton btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					try {
						String sql =" update classe set cls_nom = ?, Montant_cls = ?, Montant_tenue = ?, coef_classe = ? where id_classe = ? ";
						
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textTitre.getText().toString());
						prepared.setDouble(2, Double.parseDouble(textFIns.getText().toString()));
						prepared.setDouble(3, Double.parseDouble(textFTen.getText().toString()));
						prepared.setInt(4, Integer.parseInt(textFCoef.getText().toString()));
						prepared.setInt(5, Integer.parseInt(textFieldID.getText().toString()));
						;
						prepared.executeUpdate();
						JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
						updateTable();
					} catch (Exception e) {
						// TODO: handle exception
						System.out.println(e);
					}
					textTitre.setText("");
					textFCoef.setText("");
					textFTen.setText("");
					textFIns.setText("");
				}
			});
			btnModifier.setBounds(209, 274, 102, 23);
			getContentPane().add(btnModifier);
			
			JButton btnSupprimer = new JButton("Supprimer");
			btnSupprimer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					String sql = "delete from classe where id_classe = ?";
					  int i = JOptionPane.showConfirmDialog(null, "Voulez vous supprimer", "ERREUR", JOptionPane.YES_NO_OPTION);
					  if(i==0){
						 try{
							prepared = cnx.prepareStatement(sql);
							String id = textFieldID.getText().toString();
							prepared.setString(1, id);
							prepared.executeUpdate();
							
							JOptionPane.showMessageDialog(null, "Classe supprimer ");
					//------------------------------updateTable();
							updateTable();
							
						}catch(SQLException ex){
							
							JOptionPane.showMessageDialog(null, "Verifier les Liens avant de Supprimer ");
						} 
					  }
						
						updateTable();
						textTitre.setText("");
						textFCoef.setText("");
						textFTen.setText("");
						textFIns.setText("");
				}
			});
			btnSupprimer.setBounds(338, 274, 104, 23);
			getContentPane().add(btnSupprimer);
			
			JLabel lblNomDeLa = new JLabel("Entrez Classe :");
			lblNomDeLa.setHorizontalAlignment(SwingConstants.CENTER);
			lblNomDeLa.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblNomDeLa.setBounds(10, 11, 135, 23);
			getContentPane().add(lblNomDeLa);
			
			textTitre = new JTextField();
			textTitre.setBounds(155, 11, 96, 22);
			getContentPane().add(textTitre);
			textTitre.setColumns(10);
			
			textFieldID = new JTextField();
			textFieldID.setEnabled(false);
			textFieldID.setEditable(false);
			textFieldID.setBounds(467, 13, 39, 20);
			getContentPane().add(textFieldID);
			textFieldID.setColumns(10);
			
			JLabel lblEntrezMontant = new JLabel("Frais Insc. :");
			lblEntrezMontant.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntrezMontant.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblEntrezMontant.setBounds(10, 45, 135, 23);
			getContentPane().add(lblEntrezMontant);
			
			textFIns = new JTextField();
			textFIns.setColumns(10);
			textFIns.setBounds(155, 45, 96, 22);
			getContentPane().add(textFIns);
			
			JLabel lblFraisTenues = new JLabel("Frais Tenues :");
			lblFraisTenues.setHorizontalAlignment(SwingConstants.CENTER);
			lblFraisTenues.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblFraisTenues.setBounds(10, 79, 135, 23);
			getContentPane().add(lblFraisTenues);
			
			textFTen = new JTextField();
			textFTen.setColumns(10);
			textFTen.setBounds(155, 79, 96, 22);
			getContentPane().add(textFTen);
			
			JLabel lblCoefClasse = new JLabel("Coef Classe :");
			lblCoefClasse.setHorizontalAlignment(SwingConstants.CENTER);
			lblCoefClasse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			lblCoefClasse.setBounds(10, 109, 135, 23);
			getContentPane().add(lblCoefClasse);
			
			textFCoef = new JTextField();
			textFCoef.setColumns(10);
			textFCoef.setBounds(155, 109, 96, 22);
			getContentPane().add(textFCoef);
		
		}
	
	
	
	
	public void updateTable(){
		
		String sql = "select  * from classe";
		
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
