package it.omicron.esercizio.controller;



import java.util.List;

import it.omicron.esercizio.MenuContent;
import it.omicron.esercizio.MenuNode;
import it.omicron.esercizio.read.FoglioExcel;
import it.omicron.esercizio.read.LettoreFile;

public class Controllore {
	
	private static int maxProfondita = 0;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//C:\Users\mmoll
		
		LettoreFile l1 = new LettoreFile("..//properties.txt");
		String[] arr = new String[2];
		arr = l1.lettoreInput().split(",");
		
		String input = arr[0];
		String output = arr[1];
		
		LettoreFile l = new LettoreFile(input);
		String jsonString = l.leggi();
		
		MenuContent menuc = l.traduci(jsonString);
		
		calcoloProfonditaMax(menuc.getNodes(), 0);
		
		FoglioExcel foglio = new FoglioExcel(output, maxProfondita);
		
		foglio.createFoglio(menuc.getNodes(), menuc.getVersion());
		
		
		
	}
	
	public static void calcoloProfonditaMax(List<MenuNode> list, int currentDepth) {

		if (currentDepth > maxProfondita) {
			maxProfondita = currentDepth;
		}
	
		for (MenuNode currentNode : list) {
			// verifico se è una foglia
			// se è foglia continuo il ciclo
			// else richiamo il metodo calcolaProfodtamacaumentando la currentDepth
			currentNode.setProfondita(currentDepth);
			if (currentNode.getNodes() != null && !currentNode.getNodes().isEmpty()) {
				
				calcoloProfonditaMax(currentNode.getNodes(), currentDepth+1);
			}

		}

	}

} 
