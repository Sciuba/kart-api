package br.com.sciuba.converter;

import java.time.LocalTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import br.com.sciuba.model.Lap;
import br.com.sciuba.model.Pilot;


public class FileToObject {

	public final static String HOUR = "(\\d{2}):(\\d{2}):(\\d{2}).(\\d{3})";
	public final static String PILOT_ID = "( \\d{3} )";
	public final static String PILOT_NAME = "([a-zA-Z][.][a-zA-Z]+)";
	public final static String LAP = "(( \\d{1} )|( \\d{1}\t))";
	public final static String LAP_TIME = "(( \\d{1}:\\d{2}.\\d{3})|(\t\t\\d{1}:\\d{2}.\\d{3}))";
	public final static String LAP_AVERAGE = "(\\d{2},(?:\\d{3}|\\d{2}))";
	
	public static String convertData(String data, String regex) throws Exception {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(data);
		if (matcher.find()) {
			return matcher.group().trim();
		}else {
			throw new Exception("Convert line Fail: "+data);
		}
		
	}
	
	public static Lap convert(String lineData) throws Exception {
		
		Lap lap = new Lap();
	    String hour = convertData(lineData, HOUR);
	    String pilotId = convertData(lineData, PILOT_ID);
	    String pilotName = convertData(lineData, PILOT_NAME);
	    String lapNumber = convertData(lineData, LAP);
	    String lapTime = convertData(lineData, LAP_TIME);
	    String averageLapSpeed = convertData(lineData, LAP_AVERAGE);

	    lap.setHour(LocalTime.parse(hour));
	    lap.setPilot(new Pilot(Long.parseLong(pilotId), pilotName));
	    lap.setLapNumber(Long.parseLong(lapNumber));
	    lap.setAverageLapSpeed(new Double(averageLapSpeed.replace(",", ".")));
	    lap.setTimeLap(LocalTime.parse("00:0" + lapTime));

	    return lap;
	}

}
