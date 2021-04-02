package ua.com.foxminded.formulaone;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Parser {

	public Racer createRacerFromString(String string) {
		String[] params = string.split("_");
		return new Racer(params[0], params[1], params[2]);
	}

	public List<Racer> parseRacers(List<String> abbreviations) {
		Parser parser = new Parser();
		return abbreviations.stream().map(parser::createRacerFromString).collect(Collectors.toList());
	}

	public LocalDateTime parseTimeDateFromString(String dateTime) {
		return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS"));
	}

	public LocalDateTime parseLapInfo(List<String> list, Racer racer) {
		return parseTimeDateFromString(list.stream().filter(line -> line.startsWith(racer.getAbbreviation())).findAny()
				.orElseThrow(() -> new NoSuchElementException("No element in start.log")).substring(3));
	}

	public Stream<Lap> generateLapsInfo(List<String> startList, List<String> endList, List<String> abbreviationsList) {
		return parseRacers(abbreviationsList).stream().map(racer -> {
			LocalDateTime startTime = parseLapInfo(startList, racer);
			LocalDateTime endTime = parseLapInfo(endList, racer);

			Duration lapDuration = Duration.between(startTime, endTime);

			return new Lap(startTime, endTime, lapDuration, racer);
		});
	}

}
