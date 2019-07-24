package br.com.sciuba;

import br.com.sciuba.utils.FileReader;

public class KartMain {
	
	public static void main(String[] args) {
		
		FileReader fileReader = new FileReader();
		try {
			fileReader.read("logRace");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
