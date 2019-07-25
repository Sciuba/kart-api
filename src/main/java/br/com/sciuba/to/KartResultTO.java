package br.com.sciuba.to;

import java.time.LocalTime;

import br.com.sciuba.model.Lap;
import br.com.sciuba.model.Pilot;

public class KartResultTO {
	private Integer position;
	private Pilot pilot;
	private Long numberOfCompleteLaps;
	private LocalTime totalTime;
	
	private LocalTime bestLap;
	private Lap betterLapOfRace;
	private Double averageSpeed;
	private String positionDiff;
	
	
	public String getPositionDiff() {
		return positionDiff;
	}
	public void setPositionDiff(String positionDiff) {
		this.positionDiff = positionDiff;
	}
	public Integer getPosition() {
		return position;
	}
	public void setPosition(Integer position) {
		this.position = position;
	}
	public Pilot getPilot() {
		return pilot;
	}
	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}
	public Long getNumberOfCompleteLaps() {
		return numberOfCompleteLaps;
	}
	public void setNumberOfCompleteLaps(Long numberOfCompleteLaps) {
		this.numberOfCompleteLaps = numberOfCompleteLaps;
	}
	public LocalTime getTotalTime() {
		return totalTime;
	}
	public void setTotalTime(LocalTime totalTime) {
		this.totalTime = totalTime;
	}
	public LocalTime getBestLap() {
		return bestLap;
	}
	public void setBestLap(LocalTime bestLap) {
		this.bestLap = bestLap;
	}
	public Lap getBetterLapOfRace() {
		return betterLapOfRace;
	}
	public void setBetterLapOfRace(Lap betterLapOfRace) {
		this.betterLapOfRace = betterLapOfRace;
	}
	public Double getAverageSpeed() {
		return averageSpeed;
	}
	public void setAverageSpeed(Double averageSpeed) {
		this.averageSpeed = averageSpeed;
	}
	
	
	
}
