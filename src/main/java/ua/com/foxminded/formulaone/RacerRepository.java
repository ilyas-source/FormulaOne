package ua.com.foxminded.formulaone;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;
import java.util.stream.Stream;

public class RacerRepository {

	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

	public List<Racer> getRacers(Stream<String> startLog, Stream<String> endLog, Stream<String> abbreviations) {
		Map<String, LocalDateTime> startTimes = collectDatesTimes(startLog);
		Map<String, LocalDateTime> endTimes = collectDatesTimes(endLog);

		return abbreviations.map(a -> createRacer(a, startTimes, endTimes)).collect(toList());
	}

	private Racer createRacer(String string, Map<String, LocalDateTime> startTimes,
			Map<String, LocalDateTime> endTimes) {
		String[] params = string.split("_");
		Racer racer = new Racer(params[0], params[1], params[2]);

		LocalDateTime startTime = startTimes.get(racer.getAbbreviation());
		LocalDateTime endTime = endTimes.get(racer.getAbbreviation());
		racer.setBestLapTime(Duration.between(startTime, endTime));

		return racer;
	}

	private LocalDateTime parseDateTime(String dateTime) {
		return LocalDateTime.parse(dateTime.substring(3), formatter);
	}

	private Map<String, LocalDateTime> collectDatesTimes(Stream<String> dateTimeLog) {
		return dateTimeLog.collect(toMap(s -> s.substring(0, 3), s -> parseDateTime(s)));
	}
}
