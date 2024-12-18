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


public class ifEnseig extends JInternalFrame {
	/*
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	
	private JTable table_2;
	private JTextField textNom;
	private JTextField textPrenom;
	@SuppressWarnings("rawtypes")
	private JComboBox comClasse;
	private JScrollPane scrollPane;
	private JLabel lblClasse;
	private JLabel lblNomEnsei;
	private Component lblPrenomEnsei;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	@SuppressWarnings("rawtypes")
	private JComboBox comMatiere;
	private JButton btnAjouter;
	private JButton btnModifier;
	private JButton btnSupprimer;
	private JTextField textid;
	private JDateChooser dateChooser;
	private JButton btnEditerListe;
	
	@SuppressWarnings({ "rawtypes" })
	public ifEnseig(){
		getContentPane().setFont(new Font("Times New Roman", getContentPane().getFont().getStyle(), getContentPane().getFont().getSize()));

	setTitle("Gestion des Enseignants");
	setBounds(64, 128, 1450, 487);
	setClosable(true);
	setIconifiable(true);
	setMaximizable(true);
	setFrameIcon(new ImageIcon("D:\\GETIONECOLE\\img\\img2\\user-icon.png"));
	getContentPane().setLayout(null);
	JPanel pan = new JPanel();
	pan.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255)), "Informations sur l'Enseignant", TitledBorder.LEADING, TitledBorder.TOP, null, null));
	pan.setBounds(20, 163, 1414, 231);
    getContentPane().add(pan, BorderLayout.CENTER);
	pan.setLayout(null);
	cnx = ConnexionMySql.ConnexiomBd();

	
	
	//// Tableau de données ///////
	//////////////////////////////
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 24, 1394, 180);
		pan.add(scrollPane);
		table_2 = new JTable();
			table_2.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
					int ligne = table_2.getSelectedRow();
				    String id = table_2.getModel().getValueAt(ligne, 0).toString();
					String sql = "select * from enseignant where ens_id ='"+id+"'";
					try {
						prepared = cnx.prepareStatement(sql);
						resultat = prepared.executeQuery();
						if(resultat.next()){
							textid.setText(resultat.getString("ens_id"));
							textNom.setText(resultat.getString("ens_nom"));
							textPrenom.setText(resultat.getString("ens_prenom"));
							comClasse.setSelectedItem(resultat.getString("ens_cls"));
							comMatiere.setSelectedItem(resultat.getString("ens_cour"));
							dateChooser.setDateFormatString(resultat.getString("Date_recrute"));
						}
						
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						System.out.println("Erreur ici "+e.getMessage());
					}
				    
				    
				    }
				    
				});
	
	updateTable();
	scrollPane.setViewportView(table_2);
	//
	
	btnAjouter = new JButton("Ajouter");
	btnAjouter.addActionListener(new ActionListener() {
		
		public void actionPerformed(ActionEvent arg0) {
			
			if(checkInput() == true){
				
				String sql = "insert into enseignant (ens_nom, ens_prenom, ens_cls, ens_cour, Date_recrute ) values (?, ? , ?,?,?)";
				//String Date = ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText();
				try {
						//
						prepared = cnx.prepareStatement(sql);
						prepared.setString(1, textNom.getText().toString());
						prepared.setString(2, textPrenom.getText().toString());
						prepared.setString(3, comClasse.getSelectedItem().toString());
						prepared.setString(4, comMatiere.getSelectedItem().toString());
						prepared.setString(5, ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().toString());
						prepared.executeUpdate();
						JOptionPane.showMessageDialog(null, "Enregistrement reussit :)");
						initialisation();
						updateTable();
						} catch (SQLException e ) {
						JOptionPane.showMessageDialog(null, "Verifier si les données sont toutes introduites ");
			           }
					
					}else if(checkInput() == false){
						JOptionPane.showMessageDialog(null, "L'un des champs ou plusieurs Champs sont vide :)");
				    }
			}
	});
	
			btnAjouter.setBounds(20, 405, 164, 23);
			this.getContentPane().add(btnAjouter);
			
			btnModifier = new JButton("Modifier");
			btnModifier.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
			try {
				String sql =" update enseignant set  ens_nom = ?  , ens_prenom = ? , ens_cls = ?, ens_cour = ?, Date_recrute= ?  where ens_id =?";
				
				prepared = cnx.prepareStatement(sql);
				prepared.setString(1, textNom.getText().toString());
				prepared.setString(2, textPrenom.getText().toString());
				prepared.setString(3, comClasse.getSelectedItem().toString());
				prepared.setString(4, comMatiere.getSelectedItem().toString());
				prepared.setString(5, ((JTextField) dateChooser.getDateEditor().getUiComponent()).getText().toString());
				prepared.setInt(6, Integer.parseInt(textid.getText()));
				prepared.executeUpdate();
				JOptionPane.showMessageDialog(null, "Modification reussit !!! ");
				updateTable();
				initialisation();
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	btnModifier.setBounds(191, 405, 164, 23);
	this.getContentPane().add(btnModifier);
	btnSupprimer = new JButton("Supprimer");
	btnSupprimer.addActionListener(new ActionListener() {
		public void actionPerformed(ActionEvent arg0) {
            String sql = "delete from enseignant where ens_id=?";
			if(!textid.getText().equals("")){
				try{
					prepared = cnx.prepareStatement(sql);
					int id = Integer.parseInt(textid.getText());
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
	panel.setBounds(360, 11, 781, 141);
	getContentPane().add(panel);
	panel.setLayout(null);
	
	
		comClasse = new JComboBox();
		comClasse.setBounds(11, 110, 164, 20);
		panel.add(comClasse);
		comClasse.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		
		lblClasse = new JLabel("Classe en Charge :");
		lblClasse.setBounds(10, 82, 134, 17);
		panel.add(lblClasse);
		lblClasse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		lblNomEnsei = new JLabel("Nom enseignant :");
		lblNomEnsei.setBounds(10, 19, 134, 17);
		panel.add(lblNomEnsei);
		lblNomEnsei.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		lblPrenomEnsei = new JLabel("Prenom Enseignant :");
		lblPrenomEnsei.setBounds(213, 22, 134, 17);
		panel.add(lblPrenomEnsei);
		lblPrenomEnsei.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		textNom = new JTextField();
		textNom.setBounds(10, 45, 164, 20);
		panel.add(textNom);
		textNom.setColumns(10);
		
		textPrenom = new JTextField();
		textPrenom.setBounds(211, 44, 164, 20);
		panel.add(textPrenom);
		textPrenom.setColumns(10);
		
		lblNewLabel_3 = new JLabel("Matière Enseigner :");
		lblNewLabel_3.setBounds(214, 82, 124, 17);
		panel.add(lblNewLabel_3);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		lblNewLabel_4 = new JLabel("Date recrutée :");
		lblNewLabel_4.setBounds(423, 22, 124, 17);
		panel.add(lblNewLabel_4);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		comMatiere = new JComboBox();
		comMatiere.setBounds(213, 110, 164, 20);
		panel.add(comMatiere);
		comMatiere.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		textid = new JTextField();
		textid.setBounds(600, 19, 158, 20);
		panel.add(textid);
		textid.setHorizontalAlignment(SwingConstants.CENTER);
		textid.setEnabled(false);
		textid.setEditable(false);
		textid.setColumns(10);
		
		dateChooser = new JDateChooser();
		dateChooser.setBounds(421, 44, 164, 20);
		panel.add(dateChooser);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		
		JButton btnActualiser = new JButton("Actualiser");
		btnActualiser.setBounds(421, 109, 164, 23);
		panel.add(btnActualiser);
		btnActualiser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				textNom.setText("");
				textPrenom.setText("");
				comClasse.setSelectedItem("Choix");
				comMatiere.setSelectedItem("Choix");
				dateChooser.setDateFormatString("");
				textid.setText("");
				
				
				
			}
		});
		comClasse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			
			}
		});
		remplirClasse();
		remplirMatiere();
    }

	public boolean checkInput(){
		if(textNom == null ||textPrenom == null ||comClasse == null ||comMatiere == null ||dateChooser == null ){
			JOptionPane.showMessageDialog(null, "Remplissez les champs");
			return false;
		}else{
		return true;
		}
		
	}
	
	public void initialisation(){
		textNom.setText("");
		textPrenom.setText("");
		comClasse.setSelectedItem("Choix");
		comMatiere.setSelectedItem("Choix");
		dateChooser.setDateFormatString("");
		textid.setText("");	
		}

    public void updateTable(){
	String sql = "select  ens_id, ens_cls, ens_nom, ens_prenom,ens_cour, Date_recrute from enseignant";
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
		String sql = "select * from classe ";
		
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
}
