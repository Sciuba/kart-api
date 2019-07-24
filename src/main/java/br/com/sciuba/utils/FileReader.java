package br.com.sciuba.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import br.com.sciuba.model.Lap;

public class FileReader {
	
	public List<Lap> read(String fileName) throws IOException, URISyntaxException {
		
		//BufferedReader file = new BufferedReader(new InputStreamReader(KartMain.class.getClassLoader().getResourceAsStream(fileName)));
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		Files.lines(path, Charset.defaultCharset()).forEach(System.out::println);
		
		return null;
		
	}

}
