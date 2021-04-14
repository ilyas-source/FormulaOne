package ua.com.foxminded.formulaone;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.*;

import java.io.IOException;
import java.lang.invoke.StringConcatFactory;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

import javax.management.loading.PrivateClassLoader;

public class RacerRepository {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");

    public List<Racer> getRacers(String startLogFileName, String endLogFileName, String abbreviationsFileName)
	    throws URISyntaxException, IOException {
	try (Stream<String> startTimesData = getStreamFromFile(startLogFileName);
		Stream<String> endTimesData = getStreamFromFile(endLogFileName);
		Stream<String> abbreviations = getStreamFromFile(abbreviationsFileName);) {
	    Map<String, LocalDateTime> startTimes = collectDatesTimes(startTimesData);
	    Map<String, LocalDateTime> endTimes = collectDatesTimes(endTimesData);
	    return abbreviations.map(a -> createRacer(a, startTimes, endTimes)).collect(toList());
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
	return dateTimeLog.collect(toMap(s -> s.substring(0, 3), this::parseDateTime));
    }

    private Stream<String> getStreamFromFile(String fileName) throws IOException, URISyntaxException {
	URL url = Thread.currentThread().getContextClassLoader().getResource(fileName);
	return Files.lines(Paths.get(url.toURI()));
    }
}
