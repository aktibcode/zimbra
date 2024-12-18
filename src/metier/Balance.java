package metier;

import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JLabel;

import connec.ConnexionMySql;
import entite.Recettes;

import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Balance extends JInternalFrame {
	Statement st = null;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	String combo;
	
	
	private JTextField textFieldRecette;
	private JComboBox comboAn;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	/**
	 * Create the frame.
	 */
	public Balance() {
		
		setTitle("Validation Recettes ");
		setBounds(300, 128, 999, 474);
		setMaximizable(false);
		setResizable(false);
	    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);
		setVisible(true);
		
		panel = new JPanel();
		panel.setBounds(10, 11, 544, 127);
		getContentPane().add(panel);
		panel.setLayout(null);
		
		textFieldRecette = new JTextField();
		textFieldRecette.setEditable(false);
		textFieldRecette.setHorizontalAlignment(SwingConstants.CENTER);
		textFieldRecette.setBounds(123, 84, 135, 29);
		panel.add(textFieldRecette);
		textFieldRecette.setColumns(10);
		cnx = ConnexionMySql.ConnexiomBd();
		
		JLabel lblNewLabel = new JLabel("RECETTES :");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 83, 103, 27);
		panel.add(lblNewLabel);
		
		JLabel lblAnnee = new JLabel("ANNEE :");
		lblAnnee.setHorizontalAlignment(SwingConstants.CENTER);
		lblAnnee.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 14));
		lblAnnee.setBounds(10, 32, 103, 27);
		panel.add(lblAnnee);
		
		comboAn = new JComboBox();
		comboAn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Rece();
				
			}
		});
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.setBounds(123, 33, 135, 29);
		panel.add(comboAn);
		BindComboCour();
		
	}
	private void Rece(){
		String sql = "select Sum(Montant_Versé) as Recette From inscription where Année_Scolaire = '"+comboAn.getSelectedItem().toString()+"'";
		
		try {
			prepared = cnx.prepareStatement(sql);
			resultat = prepared.executeQuery();
			if(resultat.next()){
				resultat.getDouble("Recette");
				textFieldRecette.setText(String.valueOf(resultat.getDouble("Recette")));
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
				comboAn.addItem(resultat.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
}
}