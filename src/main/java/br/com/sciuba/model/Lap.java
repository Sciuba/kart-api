package br.com.sciuba.model;

import java.time.LocalTime;

public class Lap {

	private LocalTime hour;
	private Long LapNumber;
	private LocalTime timeLap;
	private Double averageLapSpeed;
	private Pilot pilot;

	public LocalTime getHour() {
		return hour;
	}

	public void setHour(LocalTime hour) {
		this.hour = hour;
	}

	public Long getLapNumber() {
		return LapNumber;
	}

	public void setLapNumber(Long lapNumber) {
		LapNumber = lapNumber;
	}

	public LocalTime getTimeLap() {
		return timeLap;
	}

	public void setTimeLap(LocalTime timeLap) {
		this.timeLap = timeLap;
	}

	public Double getAverageLapSpeed() {
		return averageLapSpeed;
	}

	public void setAverageLapSpeed(Double averageLapSpeed) {
		this.averageLapSpeed = averageLapSpeed;
	}

	public Pilot getPilot() {
		return pilot;
	}

	public void setPilot(Pilot pilot) {
		this.pilot = pilot;
	}

	@Override
	public String toString() {
		return "Lap [hour=" + hour + ", LapNumber=" + LapNumber + ", timeLap=" + timeLap + ", averageLapSpeed="
				+ averageLapSpeed + ", pilot=" + pilot + "]";
	}

}
