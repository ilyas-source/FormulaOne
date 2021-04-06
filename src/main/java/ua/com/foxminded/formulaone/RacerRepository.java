package ua.com.foxminded.formulaone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.NoSuchElementException;
import static java.util.stream.Collectors.*;

public class RacerRepository {

	public List<Racer> getRacers(String startLogFileName, String endLogFileName, String abbrFileName)
			throws IOException {
		List<String> startTimes = Files.readAllLines(Paths.get(startLogFileName));
		List<String> endTimes = Files.readAllLines(Paths.get(endLogFileName));

		return Files.readAllLines(Paths.get(abbrFileName)).stream().map(this::createRacerFromString).peek(racer -> {
			LocalDateTime startTime = parseLapInfo(startTimes, racer);
			LocalDateTime endTime = parseLapInfo(endTimes, racer);
			Duration lapDuration = Duration.between(startTime, endTime);
			racer.setBestLapTime(lapDuration);
		}).collect(toList());
	}

	public Racer createRacerFromString(String string) {
		String[] params = string.split("_");
		return new Racer(params[0], params[1], params[2]);
	}

	private LocalDateTime parseLapInfo(List<String> list, Racer racer) {
		return parseTimeDateFromString(list.stream().filter(line -> line.startsWith(racer.getAbbreviation())).findAny()
				.orElseThrow(() -> new NoSuchElementException("No such element")).substring(3));
	}

	private LocalDateTime parseTimeDateFromString(String dateTime) {
		return LocalDateTime.parse(dateTime, DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS"));
	}
}
