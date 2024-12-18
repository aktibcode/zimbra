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


public class BilanAnnuel extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	PreparedStatement prepared = null;
	ResultSet resultat = null;
	private JComboBox comboImp;
	private JButton btnImprimer;
	private JComboBox comboBoxAn;
	ResultSet rs = null;
	Statement st = null;
	
	public BilanAnnuel() {
		setResizable(false);
		setTitle("Edition BILAN");
		setBounds(300, 128, 335, 187);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "BILAN ANNUEL", 
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnexionMySql p = new ConnexionMySql();
				p.ConnexiomBd();
				try {
					JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/bilanInscription.jrxml");
					HashMap para = new HashMap();
					para.put("anSco", comboImp.getSelectedItem().toString());
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
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 303, Short.MAX_VALUE)
					.addGap(136))
				.addGroup(groupLayout.createSequentialGroup()
					.addGap(107)
					.addComponent(btnImprimer, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(238, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 88, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
					.addComponent(btnImprimer)
					.addContainerGap())
		);
		
		JLabel lblSelectionClasse = new JLabel("Selection Année :");
		lblSelectionClasse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSelectionClasse.setHorizontalAlignment(SwingConstants.CENTER);
		
		

		comboImp = new JComboBox();
		BindComboCour();
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
					.addContainerGap(24, Short.MAX_VALUE)
					.addComponent(lblSelectionClasse, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
					.addGap(18)
					.addComponent(comboImp, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
					.addGap(23))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGap(27)
					.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
						.addComponent(comboImp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
						.addComponent(lblSelectionClasse, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(35, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
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
				comboImp.addItem(rs.getString(1));
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}

