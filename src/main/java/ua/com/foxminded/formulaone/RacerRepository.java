package ua.com.foxminded.formulaone;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class RacerRepository {

	private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

	public List<Racer> getRacers(String startLogFileName, String endLogFileName, String abbreviationsFileName)
			throws IOException, URISyntaxException {
		URL startTimesUrl = Thread.currentThread().getContextClassLoader().getResource(startLogFileName);
		URL endTimesUrl = Thread.currentThread().getContextClassLoader().getResource(endLogFileName);
		URL abbreviationsUrl = Thread.currentThread().getContextClassLoader().getResource(abbreviationsFileName);

		Stream<String> startTimesData = Files.lines(Paths.get(startTimesUrl.toURI()));
		Stream<String> endTimesData = Files.lines(Paths.get(endTimesUrl.toURI()));
		Stream<String> abbreviations = Files.lines(Paths.get(abbreviationsUrl.toURI()));

		Map<String, LocalDateTime> startTimes = collectDatesTimes(startTimesData);
		Map<String, LocalDateTime> endTimes = collectDatesTimes(endTimesData);

		try {
			return abbreviations.map(a -> createRacer(a, startTimes, endTimes)).collect(toList());
		} finally {
			startTimesData.close();
			endTimesData.close();
			abbreviations.close();
		}
	}

	private Racer createRacer(String abbreviationLine, Map<String, LocalDateTime> startTimes,
			Map<String, LocalDateTime> endTimes) {
		String[] racerData = abbreviationLine.split("_");
		Racer racer = new Racer(racerData[0], racerData[1], racerData[2]);

		LocalDateTime startTime = startTimes.get(racer.getAbbreviation());
		LocalDateTime endTime = endTimes.get(racer.getAbbreviation());
		racer.setBestLapTime(Duration.between(startTime, endTime));

		return racer;
	}

	private LocalDateTime parseDateTime(String dateTime) {
		return LocalDateTime.parse(dateTime.substring(3), FORMATTER);
	}

	private Map<String, LocalDateTime> collectDatesTimes(Stream<String> dateTimeLog) {
		return dateTimeLog.collect(toMap(s -> s.substring(0, 3), s -> parseDateTime(s)));
	}
}
