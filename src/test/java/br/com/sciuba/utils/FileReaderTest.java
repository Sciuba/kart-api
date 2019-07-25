package br.com.sciuba.utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.transform.sax.SAXTransformerFactory;

import org.junit.Test;

import br.com.sciuba.converter.FileConverter;
import br.com.sciuba.model.Lap;

public class FileReaderTest {
	
	@Test
	public void read() throws IOException {
		
		List<Lap> listLaps = new ArrayList<>();
		
		String content ="Hora                               Piloto             Nº Volta   Tempo Volta       Velocidade média da volta\n" +
	            	 	"23:49:08.277      038 – F.MASSA                           1     1:02.852                        44,275\n" +
	            	 	"23:49:10.858      033 – R.BARRICHELLO                     1     1:04.352                        43,243";
		
		Path path = Files.createTempFile("logRace", "");
		File file = path.toFile();
		
		Files.write(path, content.getBytes(StandardCharsets.ISO_8859_1));
		
		file.deleteOnExit();
		
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
		
		assertTrue(listLaps.size() == 2);
		
	}
	
}
