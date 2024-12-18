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
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.export.DocxExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.io.File;


public class Imprimer extends JFrame {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	Connection cnx =null;
	ResultSet rs = null;
	Statement st = null;
	private JComboBox comboImp;
	private JButton btnImprimer;
	private JComboBox comboAn;
	private JComboBox comboCat;
	
	public Imprimer() {
		setResizable(false);
		setTitle("Edition Liste Eleve");
		setBounds(300, 128, 587, 170);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Impression Liste par classe", 
				TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnexionMySql p = new ConnexionMySql();
				p.ConnexiomBd();
				if(!comboCat.getSelectedItem().toString().equals("Aucun") ){
				try {
					
					JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/liste_eleve.jrxml");
					HashMap para = new HashMap();
					para.put("para", comboImp.getSelectedItem().toString());
					para.put("cat", comboCat.getSelectedItem().toString());
					para.put("anSco", comboAn.getSelectedItem().toString());
					JasperReport jr = JasperCompileManager.compileReport(jd);
					JasperPrint jp =JasperFillManager.fillReport(jr, para, p.ConnexiomBd());
					JasperViewer jv = new JasperViewer(jp, false);
					jv.setVisible(true);
					
				} catch (JRException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				}else {
					try {
						
						JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"/imp/liste_eleveGen.jrxml");
						HashMap para = new HashMap();
						para.put("para", comboImp.getSelectedItem().toString());
						
						para.put("anSco", comboAn.getSelectedItem().toString());
						JasperReport jr = JasperCompileManager.compileReport(jd);
						JasperPrint jp =JasperFillManager.fillReport(jr, para, p.ConnexiomBd());
						JasperViewer jv = new JasperViewer(jp, false);
						jv.setVisible(true);
						
					} catch (JRException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}				
			}
		});
		btnImprimer.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		GroupLayout groupLayout = new GroupLayout(getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 492, Short.MAX_VALUE)
						.addComponent(btnImprimer, Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE))
					.addContainerGap())
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(Alignment.TRAILING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 97, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnImprimer)
					.addGap(21))
		);
		
		JLabel lblSelectionClasse = new JLabel("Selection Classe :");
		lblSelectionClasse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSelectionClasse.setHorizontalAlignment(SwingConstants.CENTER);
		
		

		comboImp = new JComboBox();
		comboImp.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		
		comboAn = new JComboBox();
		comboAn.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		
		JLabel anSco = new JLabel("Selection Ann\u00E9e :");
		anSco.setHorizontalAlignment(SwingConstants.CENTER);
		anSco.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		comboCat = new JComboBox();
		comboCat.setModel(new DefaultComboBoxModel(new String[] {"Choix"}));
		
		JLabel cat = new JLabel("Selection Cat\u00E9gorie :");
		cat.setHorizontalAlignment(SwingConstants.CENTER);
		cat.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addContainerGap()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(anSco, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addGap(10)
							.addComponent(comboAn, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(18)
							.addComponent(cat, GroupLayout.DEFAULT_SIZE, 133, Short.MAX_VALUE)
							.addGap(10)
							.addComponent(comboCat, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addContainerGap())
						.addGroup(Alignment.TRAILING, gl_panel.createSequentialGroup()
							.addComponent(lblSelectionClasse, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
							.addPreferredGap(ComponentPlacement.UNRELATED)
							.addComponent(comboImp, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
							.addGap(151))))
		);
		gl_panel.setVerticalGroup(
			gl_panel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel.createSequentialGroup()
					.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_panel.createSequentialGroup()
							.addGap(3)
							.addComponent(comboCat, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_panel.createSequentialGroup()
							.addGroup(gl_panel.createParallelGroup(Alignment.LEADING)
								.addComponent(anSco, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addGroup(gl_panel.createSequentialGroup()
									.addGap(3)
									.addComponent(comboAn, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
								.addComponent(cat, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE))
							.addGap(18)
							.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
								.addComponent(lblSelectionClasse, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(comboImp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))))
					.addContainerGap(11, Short.MAX_VALUE))
		);
		panel.setLayout(gl_panel);
		getContentPane().setLayout(groupLayout);
		setVisible(true);
		BindCombo();
		BindComboan();
		BindComboCat();
		}
	public void BindCombo(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT cls_nom, coef_classe FROM classe ");
		    while(rs.next()){
		    	comboImp.addItem(rs.getString(1));
		    
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public void BindComboan(){
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
	
	public void BindComboCat(){
		ConnexionMySql mk = new ConnexionMySql();
		Connection con = mk.ConnexiomBd();
		
		try {
			
			st = con.createStatement();
			rs = st.executeQuery("SELECT nom_categorie FROM type_eleve ");
		    while(rs.next()){
		    	comboCat.addItem(rs.getString(1));
		    
		    }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
