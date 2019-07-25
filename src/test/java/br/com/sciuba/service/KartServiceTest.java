package br.com.sciuba.service;

import br.com.sciuba.model.Lap;
import br.com.sciuba.model.Pilot;
import org.junit.Test;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.SortedSet;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class KartServiceTest {

    private KartService kartService = new KartService();

    @Test
    public void finalResults() {
        Map<Long, SortedSet<Lap>> pilotsLaps = kartService.findByPilotLapsAsc(createMockList());
        SortedSet<Lap> allLapsAsc = kartService.findAllLapsAsc(pilotsLaps);
        Map<Long, String> pilotsPositionDiff = kartService.findByPilotsPositionDiff(allLapsAsc);
        Map<Long, Lap> pilotsBestLap = kartService.findByPilotBestLap(pilotsLaps);
        Map<Long, LocalTime> totalRaceTime = kartService.totalRaceTime(pilotsLaps);
        Map<Long, Double> averagePilotSpeed = kartService.averagePilotSpeed(pilotsLaps);
        Lap bestLap = kartService.findBestLap(pilotsBestLap);

        assertTrue(pilotsLaps.size() > 0);
        assertTrue(allLapsAsc.size() > 0);
        assertTrue(pilotsPositionDiff.size() > 0);
        assertTrue(pilotsBestLap.size() > 0);
        assertTrue(totalRaceTime.size() > 0);
        assertTrue(averagePilotSpeed.size() > 0);
        assertNotNull(bestLap);
    }

    public List<Lap> createMockList(){
        List<Lap> list = new ArrayList<>();

        Lap lap1 = new Lap();
        Pilot pilot1 = new Pilot();
        pilot1.setId(38L);
        pilot1.setName("F.MASSA");
        lap1.setPilot(pilot1);
        lap1.setHour(LocalTime.parse("23:49:08.277", DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
        lap1.setLapNumber(Long.parseLong("1"));
        lap1.setTimeLap(LocalTime.parse("00:0" + "1:02.852", DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
        lap1.setAverageLapSpeed(Double.parseDouble("44,275".replace(",",".")));

        list.add(lap1);

        Lap lap2 = new Lap();
        Pilot pilot2 = new Pilot();
        pilot2.setId(33L);
        pilot2.setName("R.BARRICHELLO");
        lap2.setPilot(pilot2);
        lap2.setHour(LocalTime.parse("23:49:10.858", DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
        lap2.setLapNumber(Long.parseLong("1"));
        lap2.setTimeLap(LocalTime.parse("00:0" + "1:04.352", DateTimeFormatter.ofPattern("HH:mm:ss.SSS")));
        lap2.setAverageLapSpeed(Double.parseDouble("43,243".replace(",",".")));

        return list;
    }

}
