package ua.com.foxminded.formulaone;

import java.time.Duration;

public class Racer {
	private String name;
	private String teamName;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	private String abbreviation;
	private Duration bestLapTime;

	public Racer(String abbreviation, String name, String teamName) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.teamName = teamName;
	}

	public Racer(String abbreviation, String name, String teamName, Duration bestLapTime) {
		this.abbreviation = abbreviation;
		this.name = name;
		this.teamName = teamName;
		this.bestLapTime = bestLapTime;
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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((abbreviation == null) ? 0 : abbreviation.hashCode());
		result = prime * result + ((bestLapTime == null) ? 0 : bestLapTime.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((teamName == null) ? 0 : teamName.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Racer other = (Racer) obj;
		if (abbreviation == null) {
			if (other.abbreviation != null)
				return false;
		} else if (!abbreviation.equals(other.abbreviation))
			return false;
		if (bestLapTime == null) {
			if (other.bestLapTime != null)
				return false;
		} else if (!bestLapTime.equals(other.bestLapTime))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (teamName == null) {
			if (other.teamName != null)
				return false;
		} else if (!teamName.equals(other.teamName))
			return false;
		return true;
	}
}