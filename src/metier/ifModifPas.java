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


public class ifModifPas extends JInternalFrame {
	private JTextField textLog;

	/**
	 * Launch the application.
	 */
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JTextField textFieldID;
	private JTextField textPass;

	public ifModifPas() {
		getContentPane().addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				textLog.setText("");
			}
		});
			setTitle("Modifier Mot de Pass");
			setBounds(550, 180, 756, 138);
			setClosable(true);
			setIconifiable(true);
			setMaximizable(false);
			setResizable(false);
		    setFrameIcon(new ImageIcon("D:\\Gestion_Ecole\\img\\img2\\user-icon.png"));
			getContentPane().setLayout(null);
			getContentPane().setLayout(null);
			setVisible(true);
			cnx = ConnexionMySql.ConnexiomBd();
			
			
			
			
			getContentPane().setLayout(null);
			
			JButton btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					
					if(!textFieldID.getText().toString().isEmpty()){
						
						
						try {
							String sql =" update user set user = ?, passwd = ? where id_user = ? ";
							
							prepared = cnx.prepareStatement(sql);
							prepared.setString(1, textLog.getText().toString());
							prepared.setString(2, textPass.getText().toString());
							prepared.setInt(3, Integer.parseInt(textFieldID.getText().toString()));
							prepared.executeUpdate();
							JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
							
						} catch (Exception e) {
							// TODO: handle exception
							System.out.println(e);
						}
						textLog.setText("");
						textPass.setText("");
						textFieldID.setText("");
						}
					
					
				}
			});
			btnModifier.setBounds(310, 71, 102, 23);
			getContentPane().add(btnModifier);
			
			JPanel panel = new JPanel();
			panel.setBounds(10, 13, 726, 47);
			getContentPane().add(panel);
			panel.setLayout(null);
			
			JLabel lblNom = new JLabel("Nom Utilisateur :");
			lblNom.setBounds(19, 13, 144, 15);
			panel.add(lblNom);
			lblNom.setHorizontalAlignment(SwingConstants.CENTER);
			lblNom.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			
			textLog = new JTextField();
			textLog.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			textLog.setHorizontalAlignment(SwingConstants.CENTER);
			textLog.setEditable(false);
			textLog.setBounds(168, 5, 188, 32);
			panel.add(textLog);
			textLog.setColumns(10);
			
			JLabel lblEntrezPas = new JLabel("Modifier Mot de Pass :");
			lblEntrezPas.setBounds(379, 14, 144, 15);
			panel.add(lblEntrezPas);
			lblEntrezPas.setHorizontalAlignment(SwingConstants.CENTER);
			lblEntrezPas.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
			
			textPass = new JTextField();
			textPass.setHorizontalAlignment(SwingConstants.CENTER);
			textPass.setBounds(528, 7, 188, 30);
			panel.add(textPass);
			textPass.setColumns(10);
			
			textFieldID = new JTextField();
			textFieldID.setBounds(644, 71, -44, 23);
			getContentPane().add(textFieldID);
			textFieldID.setEnabled(false);
			textFieldID.setEditable(false);
			textFieldID.setColumns(10);
			
		
		}

	public JTextField getTextLog() {
		return textLog;
	}

	public void setTextLog(JTextField textLog) {
		this.textLog = textLog;
	}

	public JTextField getTextFieldID() {
		return textFieldID;
	}

	public void setTextFieldID(JTextField textFieldID) {
		this.textFieldID = textFieldID;
	}
	
	
	
	

}
