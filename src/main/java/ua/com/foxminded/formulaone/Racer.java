package ua.com.foxminded.formulaone;

public class Racer {
	private String name;
	private String teamName;
	private String abbreviation;

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
}