package ua.com.foxminded.formulaone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.function.Consumer;

public class Board {

	private static final String CR = System.lineSeparator();

	public String build(String startLogFileName, String endLogFileName, String abbrFileName) throws IOException {
		List<String> abbreviationsList = Files.readAllLines(Paths.get(abbrFileName));
		List<String> startList = Files.readAllLines(Paths.get(startLogFileName));
		List<String> endList = Files.readAllLines(Paths.get(endLogFileName));

		StringBuilder result = new StringBuilder();
		Parser parser = new Parser();

		int maxTeamNameLength = parser.parseRacers(abbreviationsList).stream()
				.mapToInt(racer -> racer.getTeamName().length()).max().getAsInt();
		int maxRacerNameLength = parser.parseRacers(abbreviationsList).stream()
				.mapToInt(racer -> racer.getName().length()).max().getAsInt();

		parser.generateLapsInfo(startList, endList, abbreviationsList).sorted().forEachOrdered(new Consumer<Lap>() {
			int index = 1;

			@Override
			public void accept(Lap lap) {
				result.append(generateElement(lap, maxRacerNameLength, maxTeamNameLength, index));
				index++;
			}
		});

		return result.toString();
	}

	public String generateElement(Lap lap, int maxRacerNameLength, int maxTeamNameLength, int index) {
		StringBuilder result = new StringBuilder();

		LocalTime time = LocalTime.ofNanoOfDay(lap.getDuration().toNanos());
		String timeOutput = time.format(DateTimeFormatter.ofPattern("mm:ss.SSS"));

		int spacesName = maxRacerNameLength - lap.getRacer().getName().length();
		int spacesTeam = maxTeamNameLength - lap.getRacer().getTeamName().length();

		if (index > 9) {
			spacesName--;
		}

		String line = String.format("%d. %s%s | %s%s | %s", index, lap.getRacer().getName(),
				repeatChar(spacesName, ' '), lap.getRacer().getTeamName(), repeatChar(spacesTeam, ' '), timeOutput);

		result.append(line).append(CR);
		if (index == 15) {
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
