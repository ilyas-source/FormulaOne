package ua.com.foxminded.formulaone;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

public class TopRacersFormatter {

	private static final String CR = System.lineSeparator();
	private static final int CUTOFF = 15;

	public String format(List<Racer> racers) {
		StringBuilder result = new StringBuilder();

		int maxTeamNameLength = racers.stream().mapToInt(racer -> racer.getTeamName().length()).max().getAsInt();
		int maxRacerNameLength = racers.stream().mapToInt(racer -> racer.getName().length()).max().getAsInt();

		racers.sort(Comparator.comparing(Racer::getBestLapTime));

		int[] index = { 1 };

		racers.forEach(
				racer -> result.append(generateElement(racer, maxRacerNameLength, maxTeamNameLength, index[0]++)));

		return result.toString();
	}

	public String generateElement(Racer racer, int maxRacerNameLength, int maxTeamNameLength, int index) {
		StringBuilder result = new StringBuilder();

		LocalTime time = LocalTime.ofNanoOfDay(racer.getBestLapTime().toNanos());
		String timeOutput = time.format(DateTimeFormatter.ofPattern("mm:ss.SSS"));

		int spacesName = maxRacerNameLength - racer.getName().length();
		int spacesTeam = maxTeamNameLength - racer.getTeamName().length();

		if (index > 9) {
			spacesName--;
		}

		String line = String.format("%d. %s%s | %s%s | %s", index, racer.getName(), repeatChar(spacesName, ' '),
				racer.getTeamName(), repeatChar(spacesTeam, ' '), timeOutput);

		result.append(line).append(CR);
		if (index == CUTOFF) {
			result.append(repeatChar(line.length(), '-')).append(CR);
		}
		return result.toString();
	}

	private String repeatChar(int length, char ch) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			builder.append(ch);
		}

		return builder.toString();
	}

}
