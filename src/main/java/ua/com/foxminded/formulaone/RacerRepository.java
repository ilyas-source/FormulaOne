package ua.com.foxminded.formulaone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.sound.midi.Soundbank;

import static java.util.stream.Collectors.*;

public class RacerRepository {

	public List<Racer> getRacers(String startLogFileName, String endLogFileName, String abbrFileName)
			throws IOException {
		Map<String, LocalDateTime> startTimes = Files.readAllLines(Paths.get(startLogFileName)).stream()
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> parseTimeDateFromString(s)));

		Map<String, LocalDateTime> endTimes = Files.readAllLines(Paths.get(endLogFileName)).stream()
				.collect(Collectors.toMap(s -> s.substring(0, 3), s -> parseTimeDateFromString(s)));

		return Files.readAllLines(Paths.get(abbrFileName)).stream().map(this::createRacerFromString).peek(racer -> {
			racer.setBestLapTime(
					Duration.between(startTimes.get(racer.getAbbreviation()), endTimes.get(racer.getAbbreviation())));
		}).collect(toList());
	}

	public Racer createRacerFromString(String string) {
		String[] params = string.split("_");
		return new Racer(params[0], params[1], params[2]);
	}

	private LocalDateTime parseTimeDateFromString(String dateTime) {
		return LocalDateTime.parse(dateTime.substring(3), DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS"));
	}
}
