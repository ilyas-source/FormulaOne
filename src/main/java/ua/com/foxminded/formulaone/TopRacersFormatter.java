package ua.com.foxminded.formulaone;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.List;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.IconifyAction;

public class TopRacersFormatter {

	private static final String CR = System.lineSeparator();

	public String format(List<Racer> racers, int topRacersNumber) {
		StringBuilder result = new StringBuilder();

		int maxTeamNameLength = racers.stream().mapToInt(racer -> racer.getTeamName().length()).max().getAsInt();
		int maxRacerNameLength = racers.stream().mapToInt(racer -> racer.getName().length()).max().getAsInt();
		int totalLineLength = maxRacerNameLength + maxTeamNameLength + 18;

		racers.sort(Comparator.comparing(Racer::getBestLapTime));

		int[] index = { 1 };

		racers.forEach(racer -> {
			result.append(generateElement(racer, maxRacerNameLength, maxTeamNameLength, index[0]++));
			if (index[0] == topRacersNumber + 1) {
				result.append(repeatChar(totalLineLength, '-')).append(CR);
			}
		});

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
