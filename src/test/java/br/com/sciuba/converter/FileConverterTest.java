package br.com.sciuba.converter;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class FileConverterTest {
	
	public final  String HOUR = "(\\d{2}):(\\d{2}):(\\d{2}).(\\d{3})";
	public final  String PILOT_ID = "( \\d{3} )";
	public final  String PILOT_NAME = "([a-zA-Z][.][a-zA-Z]+)";
	public final  String LAP = "(( \\d{1} )|( \\d{1}\t))";
	public final  String LAP_TIME = "(( \\d{1}:\\d{2}.\\d{3})|(\t\t\\d{1}:\\d{2}.\\d{3}))";
	public final  String LAP_AVERAGE = "(\\d{2},(?:\\d{3}|\\d{2}))";
	
	@Test
	public void convert() throws Exception {
		
		String content = "23:50:17.472      023 – M.WEBBER                          2		1:04.805                        42,941";
		
		String hour = FileConverter.convertData(content, HOUR);
	    String pilotId = FileConverter.convertData(content, PILOT_ID);
	    String pilotName = FileConverter.convertData(content, PILOT_NAME);
	    String lapNumber = FileConverter.convertData(content, LAP);
	    String lapTime = FileConverter.convertData(content, LAP_TIME);
	    String averageLapSpeed = FileConverter.convertData(content, LAP_AVERAGE);
	    
	    assertEquals(hour, "23:50:17.472");
	    assertEquals(pilotId, "023");
	    assertEquals(pilotName, "M.WEBBER");
	    assertEquals(lapNumber, "2");
	    assertEquals(averageLapSpeed, "42,941");
		
	}

}
