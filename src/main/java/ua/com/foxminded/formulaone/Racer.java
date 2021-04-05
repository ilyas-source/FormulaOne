package ua.com.foxminded.formulaone;

import java.time.Duration;
import java.time.LocalDateTime;

public class Racer {
	private String name;
	private String teamName;
	private String abbreviation;
	private Duration bestLapTime;

	public Racer(String abbreviation, String name, String teamName) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.teamName = teamName;
	}

	public String getName() {
		return name;
	}

	public String getTeamName() {
		return teamName;
	}

	public String getAbbreviation() {
		return abbreviation;
	}

	public Duration getBestLapTime() {
		return bestLapTime;
	}

	public void setBestLapTime(Duration bestLapTime) {
		this.bestLapTime = bestLapTime;
	}

	@Override
	public String toString() {

		return this.abbreviation + ": " + this.teamName + ", " + this.name + ": " + this.bestLapTime;
	}
}