package br.com.sciuba.utils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import br.com.sciuba.converter.FileConverter;
import br.com.sciuba.model.Lap;

public class FileReader {
	
	public List<Lap> read(String fileName) throws IOException, URISyntaxException {
		
		List<Lap> listLaps = new ArrayList<>();
		
		Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
		AtomicInteger count = new AtomicInteger(0);
		Files.lines(path, StandardCharsets.ISO_8859_1).forEach(l->{
			if(count.get() == 0) {
				count.set(1);
			}else {
				try {
					listLaps.add(FileConverter.convert(l));
				} catch (Exception e) {
					System.out.println(e.getMessage());
				}
			}
		});
		
		return listLaps;
		
	}

}
