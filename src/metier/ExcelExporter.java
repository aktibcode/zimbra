package metier;

import java.io.*;

import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;

import jxl.*;
import jxl.write.*;
import jxl.write.Label;


public class ExcelExporter {
	
	ExcelExporter() {
		// TODO Auto-generated constructor stub
	}
	
	
	void fillData( JTable table, File file){
		try {
			
			WritableWorkbook wb = Workbook.createWorkbook(file);
			WritableSheet sheet1 = wb.createSheet("First Sheet", 0);
				TableModel model = table.getModel();
			for (int i = 0; i < model.getColumnCount(); i++) {
				Label column = new Label(i, 0, model.getColumnName(i));
				sheet1.addCell(column);
			}
			int k=0;
			for (int i = 0; i < model.getRowCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					
					Label row = new Label(j, i+1, model.getValueAt(i, j).toString());
					sheet1.addCell(row);
									
				}
			}
			wb.write();
			wb.close();
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	/*public void exportTable(JTable table, File file) throws IOException{
		
		TableModel model = table.getModel();
		FileWriter out = new FileWriter(file);
		BufferedWriter bw = new BufferedWriter(out);
			for(int i= 0; i<model.getColumnCount(); i++){
				bw.write(model.getColumnName(i)+"\t");
			}
		bw.write("\n");
		
			for (int i = 0; i < model.getColumnCount(); i++) {
				for (int j = 0; j < model.getColumnCount(); j++) {
					bw.write(model.getValueAt(i, j).toString()+"\t");
				}
				bw.write("\n");
			}
		bw.close();
			System.out.print("Write out to"+file);
			
		
	}*/
	
	
	

}
 