package metier;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;

import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JPasswordField;

import connec.ConnexionMySql;
import entite.Parametres;

import java.awt.Color;
import javax.swing.UIManager;


public class FenPrincipale extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtnom;
	private JPasswordField passd;
	
	public JTextField getTxtnom() {
		return txtnom;
	}

	public void setTxtnom(JTextField txtnom) {
		this.txtnom = txtnom;
	}
	
	
	
	public JTextField getTextcat() {
		return textcat;
	}

	public void setTextcat(JTextField textcat) {
		this.textcat = textcat;
	}



	ResultSet rs = null;
	ResultSet rs2 = null;
	Statement st = null;
	Statement st2 = null;
	private JTextField mat;
	private JTextField textIdEns;
	private JTextField textcat;
	/**
	 * Launch the application.
	 * Password: 1234
	 * User: isidore 
	 * 
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					
					FenPrincipale fr = new FenPrincipale();
					fr.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public FenPrincipale() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 972, 559);
		contentPane = new JPanel();
		contentPane.setBackground(UIManager.getColor("Button.shadow"));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setLocationRelativeTo(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.highlight"));
		panel.setBounds(10, 11, 274, 499);
		contentPane.add(panel);
		panel.setLayout(null);
		
		Panel panel_1 = new Panel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(0, 0, 274, 186);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblLogin = new JLabel("USERNAME :");
		lblLogin.setBounds(0, 23, 120, 26);
		panel_1.add(lblLogin);
		lblLogin.setHorizontalAlignment(SwingConstants.CENTER);
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		txtnom = new JTextField();
		txtnom.setBounds(130, 23, 133, 26);
		panel_1.add(txtnom);
		txtnom.setColumns(10);
		
		JLabel lblPassword = new JLabel("PASSWORD :");
		lblPassword.setBounds(0, 71, 120, 26);
		panel_1.add(lblPassword);
		lblPassword.setHorizontalAlignment(SwingConstants.CENTER);
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 16));
		
		passd = new JPasswordField();
		passd.setBounds(130, 74, 133, 25);
		panel_1.add(passd);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(10, 129, 110, 33);
		panel_1.add(btnNewButton);
		
		JButton btnCancel = new JButton("Cancel");
		btnCancel.setBounds(154, 129, 110, 33);
		panel_1.add(btnCancel);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(286, 11, 657, 20);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		mat = new JTextField();
		mat.setBounds(315, 11, 113, 20);
		contentPane.add(mat);
		mat.setEnabled(false);
		mat.setEditable(false);
		mat.setColumns(10);
		
		textIdEns = new JTextField();
		textIdEns.setBounds(434, 11, 113, 20);
		contentPane.add(textIdEns);
		textIdEns.setEnabled(false);
		textIdEns.setEditable(false);
		textIdEns.setColumns(10);
		
		textcat = new JTextField();
		textcat.setEnabled(false);
		textcat.setEditable(false);
		textcat.setColumns(10);
		textcat.setBounds(551, 11, 113, 20);
		contentPane.add(textcat);
		btnCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				try {
					
				
				int flag = 1;
				
				try {
					ConnexionMySql p = new ConnexionMySql(); 
			        Connection con = p.ConnexiomBd();
			        //System.out.println("connection is "+con);
					st = con.createStatement();
					rs = st.executeQuery("SELECT * FROM user");
					
					
					while(rs.next()){
						//rs.getString(1)== txtnom.getText()  ;
						if(rs.getString(2).equals(txtnom.getText().toString()) && rs.getString(3).equals(passd.getText())){
							//if(true){
							flag = 0;
							mat.setText(String.valueOf(rs.getString("matEnsg")));
							textcat.setText(rs.getString("cat_user"));
							textIdEns.setText(String.valueOf(rs.getInt("id_user")));
							System.out.println(String.valueOf(rs.getString("matEnsg")));
							System.out.println(rs.getString("cat_user"));
							break;
						
						}
						
						
						
					}
					
					if(rs.getString("cat_user").equals("enseignant")){
					st2 = con.createStatement();
					rs2 = st2.executeQuery("select * FROM enseignantel ");
					while(rs2.next()){
						//rs.getString(1)== txtnom.getText()  ;
						//rs2.getString("nomEns").equals(txtnom.getText().toString())
						if(rs2.getString(3).equals(txtnom.getText().toString())){
						flag = 0;	
						textIdEns.setText(String.valueOf(rs2.getInt("idMEns")));
						System.out.println(String.valueOf(rs2.getInt("idMEns")));
						System.out.println(String.valueOf(rs2.getString("nomEns")));
						System.out.println(String.valueOf(txtnom.getText().toString()));
						break;
						}
						
						
					}
					}
					
					
					
					if(flag==0){
						
						
						if( rs.getString(4).equals("Directeur") || rs.getString(4).equals("admin")){
							FenMenu f = new FenMenu();
							FenPrincipale.this.dispose();
							f.setVisible(true);
							f.getTextFieldUser().setText(txtnom.getText().toString());
							f.getTextFieldMat().setText(String.valueOf(rs.getString("matEnsg")));
							f.getTextField().setText(String.valueOf(rs.getInt("id_user")));
							f.getCat().setText(String.valueOf(rs.getString("cat_user")));							
						}else if(rs.getString(4).equals("enseignant") && rs2.getString(3).equals(txtnom.getText().toString())){
							FenMenu f = new FenMenu();
							f.getFchEleve().setEnabled(true);
							f.getFchCaisse().setEnabled(false);
							f.getFchAdm().setEnabled(false);
							f.getINFO().setEnabled(false);
							f.getParam().setEnabled(false);
							f.getPARAMETRES().setEnabled(false);
							f.getItOr().setEnabled(false);;
							FenPrincipale.this.dispose();
							f.setVisible(true);
							f.getTextFieldUser().setText(txtnom.getText());
							f.getTextFieldMat().setText(String.valueOf(rs.getString("matEnsg")));
							f.getTextField().setText(String.valueOf(rs.getInt("id_user")));
							f.getCat().setText(String.valueOf(rs.getString("cat_user")));
						}else if(rs.getString(4).equals("tresorier") || rs.getString(4).equals("intendant")){
							FenMenu f = new FenMenu();
							f.getFchCaisse().setEnabled(true);
							f.getFchAdm().setEnabled(false);
							f.getFchEleve().setEnabled(false);
							f.getPARAMETRES().setEnabled(false);
							FenPrincipale.this.dispose();
							f.setVisible(true);
							f.getTextFieldUser().setText(txtnom.getText());
							f.getTextFieldUser().setText(txtnom.getText());
							f.getTextFieldMat().setText(String.valueOf(rs.getString("matEnsg")));
							f.getCat().setText(String.valueOf(rs.getString("cat_user")));
						}else if(rs.getString(4).equals("secretaire")){
							FenMenu f = new FenMenu();
							f.getItErgCoursEL().setEnabled(false);
							f.getFchCaisse().setEnabled(false);
							f.getParam().setEnabled(false);
							f.getPARAMETRES().setEnabled(false);
							FenPrincipale.this.dispose();
							f.setVisible(true);
							f.getTextFieldUser().setText(txtnom.getText());
							f.getTextFieldUser().setText(txtnom.getText());
							f.getTextFieldMat().setText(String.valueOf(rs.getString("matEnsg")));
							f.getCat().setText(String.valueOf(rs.getString("cat_user")));
						}else if(rs.getString(4).equals("gestionnaire")){
							FenMenu f = new FenMenu();
							f.getItErgCoursEL().setEnabled(false);
							//f.getFchCaisse().setEnabled(false);
							f.getFchAdm().setEnabled(true);
							f.getFchEleve().setEnabled(true);
							f.getPARAMETRES().setEnabled(false);
							FenPrincipale.this.dispose();
							f.getTextField().setText(String.valueOf(rs.getInt("id_user")));
							f.getFchCaisse().setEnabled(false);
							f.getParam().setEnabled(true);
							f.getPARAMETRES().setEnabled(false);
							f.setVisible(true);
							f.getTextFieldUser().setText(txtnom.getText());
							f.getTextFieldUser().setText(txtnom.getText());
							f.getTextFieldMat().setText(String.valueOf(rs.getString("matEnsg")));
							f.getCat().setText(textcat.getText());
							f.getCat().setText(String.valueOf(rs.getString("cat_user")));
							
							
						}
						
						
						else {
							JOptionPane.showMessageDialog(null, "Aucun Profil correspondant");
						}
					
						
						
					}else{
						JOptionPane.showMessageDialog(null, "Veillez entrez les bonnes informations");
					}
					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "Utilisateurs sans Profil !!! ");;
				}
				
				//System.exit(0);
				} catch (Exception e) {
					e.getStackTrace();
				}
			}
			
		});
	}
}
