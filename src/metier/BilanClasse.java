package metier;

import javax.swing.JFrame;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import java.awt.Color;

import javax.swing.border.LineBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Font;
import java.sql.*;
import java.util.HashMap;

import javax.swing.JComboBox;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

import connec.ConnexionMySql;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class BilanClasse extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JButton btnImprimer;
	ResultSet rs = null;
	Statement st = null;
	private JComboBox comboAn;
	private JComboBox comboRub;
	private JComboBox comboImp;
	
	public BilanClasse() {
		setResizable(false);
		setTitle("Edition BILAN");
		setBounds(300, 128, 576, 206);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnexionMySql p = new ConnexionMySql();
				p.ConnexiomBd();
				try {
					JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/BilanInscriptionClasse.jrxml");
					HashMap para = new HashMap();
					para.put("classe", comboImp.getSelectedItem().toString());
					para.put("anSco", comboAn.getSelectedItem().toString()); 
					para.put("pLibelle", comboRub.getSelectedItem().toString());
					JasperReport jr = JasperCompileManager.compileReport(jd);
					JasperPrint jp =JasperFillManager.fillReport(jr, para, p.ConnexiomBd());
					JasperViewer jv = new JasperViewer(jp, false);
					jv.setVisible(true);
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnImprimer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(new LineBorder(new Color(255, 0, 255), 2), "Visualisez Le Bilan Par Rubrique", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addGroup(groupLayout.createSequentialGroup()
							.addContainerGap()
							.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 549, GroupLayout.PREFERRED_SIZE))
						.addGroup(groupLayout.createSequentialGroup()
							.addGap(229)
							.addComponent(btnImprimer, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(21)
					.addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 101, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(btnImprimer)
					.addContainerGap(30, Short.MAX_VALUE))
		);
		panel_1.setLayout(null);
		
		comboAn = new JComboBox();
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboAn.setBounds(10, 59, 122, 20);
		panel_1.add(comboAn);
		
		JLabel label = new JLabel("Selection Ann\u00E9e :");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label.setBounds(10, 23, 114, 25);
		panel_1.add(label);
		
		comboRub = new JComboBox();
		comboRub.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboRub.setBounds(150, 59, 243, 20);
		panel_1.add(comboRub);
		
		comboImp = new JComboBox();
		comboImp.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		comboImp.setBounds(414, 59, 122, 20);
		panel_1.add(comboImp);
		
		JLabel label_1 = new JLabel("Selection Classe :");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		label_1.setBounds(414, 23, 114, 25);
		panel_1.add(label_1);
		
		JLabel lblSelectionRubrique = new JLabel("Selection Rubrique :");
		lblSelectionRubrique.setHorizontalAlignment(SwingConstants.CENTER);
		lblSelectionRubrique.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSelectionRubrique.setBounds(150, 23, 243, 25);
		panel_1.add(lblSelectionRubrique);
		remplirClasse();
		BindComboCour();
		Libelle();
		getContentPane().setLayout(groupLayout);
		setVisible(true);
		
}
	
	public void BindComboCour(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT anne_courant FROM annee ");
			while(rs.next()){
				comboAn.addItem(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public void remplirClasse(){
		
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {
			st = con.createStatement();
			rs = st.executeQuery("select cls_nom from classe ");
			while(rs.next()){
				comboImp.addItem(rs.getString("cls_nom"));
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void Libelle(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();

		try {

			st = con.createStatement();
			rs = st.executeQuery("SELECT Lib_rub_ent FROM rub_entree ");
			while(rs.next()){
				comboRub.addItem(rs.getString("Lib_rub_ent"));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
