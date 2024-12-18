package metier;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;

import javax.swing.DefaultComboBoxModel;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import connec.ConnexionMySql;

public class impListeEns extends JFrame {
	
	
	Connection cnx =null;
	ResultSet rs = null;
	Statement st = null;
	private JPanel contentPane;
	private JButton btnImprimer;
	private JComboBox comboImp;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public impListeEns() {
		setResizable(false);
		setTitle("Edition Liste Enseignant");
		setBounds(300, 128, 363, 187);
		setLocationRelativeTo(null);
		getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(new LineBorder(new Color(0, 0, 0), 2, true), "Liste Enseignant par classe", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		
		btnImprimer = new JButton("Imprimer");
		btnImprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ConnexionMySql p = new ConnexionMySql();
				p.ConnexiomBd();
				try {
					JasperDesign jd =JRXmlLoader.load(new File("").getAbsolutePath()+"./imp/liste_enseignant.jrxml");
					HashMap para = new HashMap();
					para.put("pEnse", comboImp.getSelectedItem().toString());
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
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(Alignment.LEADING, groupLayout.createSequentialGroup()
					.addContainerGap()
					.addGroup(groupLayout.createParallelGroup(Alignment.TRAILING)
						.addComponent(btnImprimer, GroupLayout.PREFERRED_SIZE, 100, GroupLayout.PREFERRED_SIZE)
						.addComponent(panel, GroupLayout.PREFERRED_SIZE, 338, GroupLayout.PREFERRED_SIZE))
					.addContainerGap(16, Short.MAX_VALUE))
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addContainerGap()
					.addComponent(panel, GroupLayout.PREFERRED_SIZE, 109, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(btnImprimer)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		
		JLabel lblSelectionClasse = new JLabel("Selection Classe :");
		lblSelectionClasse.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 12));
		lblSelectionClasse.setHorizontalAlignment(SwingConstants.CENTER);

		comboImp = new JComboBox();
		
		GroupLayout gl_panel = new GroupLayout(panel);
		gl_panel.setHorizontalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addContainerGap()
						.addComponent(lblSelectionClasse, GroupLayout.PREFERRED_SIZE, 114, GroupLayout.PREFERRED_SIZE)
						.addPreferredGap(ComponentPlacement.UNRELATED)
						.addComponent(comboImp, GroupLayout.PREFERRED_SIZE, 122, GroupLayout.PREFERRED_SIZE)
						.addContainerGap(70, Short.MAX_VALUE))
			);

			gl_panel.setVerticalGroup(
				gl_panel.createParallelGroup(Alignment.LEADING)
					.addGroup(gl_panel.createSequentialGroup()
						.addGap(28)
						.addGroup(gl_panel.createParallelGroup(Alignment.BASELINE)
							.addComponent(lblSelectionClasse, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
							.addComponent(comboImp, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addContainerGap(103, Short.MAX_VALUE))
			);
			panel.setLayout(gl_panel);
			getContentPane().setLayout(groupLayout);
			setVisible(true);
			BindCombo();
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

}
