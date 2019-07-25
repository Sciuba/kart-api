package br.com.sciuba.service;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;

import br.com.sciuba.model.Lap;
import br.com.sciuba.to.KartResultTO;


public class KartService {

	public List<KartResultTO> finalResults(List<Lap> listLaps) {

		List<KartResultTO> result = new ArrayList<>();
		
		Map<Long, SortedSet<Lap>> pilotsLaps = findByPilotLapsAsc(listLaps);
		SortedSet<Lap> allLapsAsc = findAllLapsAsc(pilotsLaps);
		Map<Long, String> pilotsPositionDiff = findByPilotsPositionDiff(allLapsAsc);
		Map<Long, Lap> pilotsBestLap = findByPilotBestLap(pilotsLaps);
				
		Lap bestLap = findBestLap(pilotsBestLap);
		
		return result;

	}

	public Map<Long, SortedSet<Lap>> findByPilotLapsAsc(List<Lap> listLaps) {

		Map<Long, SortedSet<Lap>> map = new TreeMap<>();
		Set<Long> ids = new LinkedHashSet<>();

		listLaps.forEach(lap -> ids.add(lap.getPilot().getId()));

		ids.forEach(id -> {
			List<Lap> pilotLaps = listLaps.stream().filter(lap -> 
				id.equals(lap.getPilot().getId())).collect(Collectors.toList());
			SortedSet<Lap> sorted = new TreeSet<>(Comparator.comparing(Lap::getLapNumber));
			sorted.addAll(pilotLaps);
			map.put(id, sorted);
		});

		return map;
	}

	public SortedSet<Lap> findAllLapsAsc(Map<Long, SortedSet<Lap>> pilotsLaps){
		SortedSet<Lap> list = new TreeSet<>(Comparator.comparing(Lap::getHour));
		pilotsLaps.forEach((s, laps) -> list.add(laps.last()));
		return list;
	}
	
	public Map<Long, String> findByPilotsPositionDiff(SortedSet<Lap> listLap) {
	    
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.SSS");
		Map<Long, String> map = new HashMap<>();

	    AtomicInteger i = new AtomicInteger(1);
	    AtomicReference<LocalTime> winner = new AtomicReference<>();

	    listLap.forEach(lap -> {
	      if (i.get() == 1) {
	    	  winner.set(lap.getHour());
	    	  map.put(lap.getPilot().getId(), "  " + sdf.format(lap.getHour()));
	      } else {
	        Duration between = Duration.between(winner.get(), lap.getHour());
	        map.put(lap.getPilot().getId(), "+ " + LocalTime.ofNanoOfDay(between.toNanos()).format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));	        
	      }
	      i.set(i.get()+1);
	    });
	    
	    return map;
	}
	
	public Map<Long, Lap> findByPilotBestLap(Map<Long, SortedSet<Lap>> pilotsLaps) {
	    Map<Long, Lap> bestLap = new HashMap<>();

	    pilotsLaps.forEach((l, logLaps) -> {
	      SortedSet<Lap> sortbestLap = new TreeSet<>(Comparator.comparing(Lap::getTimeLap));
	      sortbestLap.addAll(logLaps);
	      bestLap.put(l, sortbestLap.first());
	    });

	    return bestLap;
	}
	
	public static Map<Long, LocalTime> totalRaceTime(Map<Long, SortedSet<Lap>> pilotsLaps) {
	    Map<Long, LocalTime> map = new HashMap<>();

	    pilotsLaps.forEach((id, laps) -> {
	      SortedSet<Lap> sorted = new TreeSet<>(Comparator.comparing(Lap::getHour));
	      sorted.addAll(laps);

	      LocalTime init = LocalTime.ofNanoOfDay(ChronoUnit.NANOS.between(sorted.first().getTimeLap(), sorted.first().getHour()));
	      LocalTime end = sorted.last().getHour();
	      map.put(id, LocalTime.ofNanoOfDay(ChronoUnit.NANOS.between(init, end)));
	    });

	    return map;
	  }
	
	public Lap findBestLap(Map<Long, Lap> pilotsBestLap) {
		SortedSet<Lap> sortBestLap = new TreeSet<>(Comparator.comparing(Lap::getTimeLap));
		pilotsBestLap.forEach((l, laps) -> sortBestLap.add(laps));
		
		return sortBestLap.first();
	}

}
