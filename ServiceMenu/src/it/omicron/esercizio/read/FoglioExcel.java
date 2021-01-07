package it.omicron.esercizio.read;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import it.omicron.esercizio.MenuNode;

public class FoglioExcel {

	private String path;
	private int profonditaMax;
	private int rowNum;

	public FoglioExcel(String path, int pr) {
		this.path = path;
		this.profonditaMax = pr;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}
	
	
	
	public void createFoglio(List<MenuNode> menu, String versione) {
		int i = 0;
		
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("Menu " + versione);
		
		CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setBold(true);
        style.setFont(font);
        
		XSSFRow row = sheet.createRow(0);
		
		
		
		//Aggiunger a questo array eventuali colonne aggiuntive
		List<String> colonne = new ArrayList<String>();
		colonne.add("ServiceId");
		colonne.add("NodeName");
		colonne.add("NodeType");
		colonne.add("GroupType");
		colonne.add("FlowType");
		colonne.add("ResourceId");
		//...
		int nc = profonditaMax + colonne.size() + 1;
		
		Cell[] celle = new Cell[nc];
		
		for(i = 0; i < nc-colonne.size(); i++) {
			celle[i] = row.createCell(i);
			celle[i].setCellValue(i);
		}
		
		int k = 0;
		
		for(i = nc - colonne.size(); i < celle.length; i ++) {
			celle[i] = row.createCell(i);
			celle[i].setCellValue(colonne.get(k));
			k++;
		}
		
		for(int in = 0; in < row.getLastCellNum(); in++ ) {
			row.getCell(in).setCellStyle(style);
		}
		
		rowNum++;
		
		riempiFoglio(sheet, menu);
		
		try {
			FileOutputStream out = new FileOutputStream(new File(path + "ServiceMenu.xlsx"));
			workbook.write(out);
			out.close();
			workbook.close();
			System.out.println("File creato correttamente");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch (IOException e) {
			e.printStackTrace();
		}
		

	}
	
	public void riempiFoglio(XSSFSheet sheet, List<MenuNode> menu) {
		int numeroCol = profonditaMax + 7;
		
		for(MenuNode m : menu) {
			XSSFRow nuovaRow = sheet.createRow(rowNum);
			Cell[] cella = new Cell[numeroCol];
			 for(int j = 0; j < numeroCol; j++) {
				 cella[j] = nuovaRow.createCell(j);
				 if(j == m.getProfondita()) {
					 cella[j].setCellValue("X");
				 }
				 if(m.getNodeType().equalsIgnoreCase("service") && j == profonditaMax+1) {
					 cella[j].setCellValue(m.getNodeId());
				 }
				 if(j == profonditaMax+2) {
					 cella[j].setCellValue(m.getNodeName());
				 }
				 if(j == profonditaMax+3) {
					 cella[j].setCellValue(m.getNodeType());
				 }
				 if(j == profonditaMax+4 && m.getGroupType() != null) {
					 cella[j].setCellValue(m.getGroupType());
				 }
				 if(j == profonditaMax+5 && m.getFlowType() != null) {
					 cella[j].setCellValue(m.getFlowType());
				 }
				 if(j == profonditaMax+6 && m.getResource() != null) {
					 cella[j].setCellValue(m.getResource().getId());
				 }
				 sheet.autoSizeColumn(j);
			 }
			 rowNum++;
			 if(m.getNodes() != null && !m.getNodes().isEmpty()) {
				 riempiFoglio(sheet, m.getNodes());
			 }
		}
		
		
	}
	
	
	

}
