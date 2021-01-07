package it.omicron.esercizio.read;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import com.google.gson.Gson;

import it.omicron.esercizio.MenuContent;

public class LettoreFile {
	
	private String path;
	
	public LettoreFile(String path) {
		this.path = path;
	}

	public String leggi() {
		String jsonString = "";
		try
        {
			jsonString = new String ( Files.readAllBytes( Paths.get(path) ) );
        } 
        catch (IOException e) 
        {
            e.printStackTrace();
        }
 
        return jsonString;
    }
		
	
	public MenuContent traduci(String jsonString) {
		Gson gson = new Gson();
		MenuContent menu = gson.fromJson(jsonString, MenuContent.class);
		return menu;
	}
	
	public String lettoreInput() {
		String inputOut = "";
		try {
			FileReader file = new FileReader(path);
			//BufferedReader lettore = new BufferedReader(file);
			Scanner lettore = new Scanner(file);
			inputOut = lettore.nextLine();
			while (lettore.hasNextLine()) {
				inputOut += "," + lettore.nextLine();
			}
			file.close();
			lettore.close();
		} catch (FileNotFoundException e) {
			System.out.println("File non trovato");

		} catch (IOException e) {
			e.printStackTrace();
		}
		return inputOut;
	}

}
