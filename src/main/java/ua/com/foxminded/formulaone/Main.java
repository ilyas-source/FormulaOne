package ua.com.foxminded.formulaone;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Stream;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		URL startTimesUrl = Thread.currentThread().getContextClassLoader().getResource("start.log");
		Stream<String> startTimes = Files.lines(Paths.get(startTimesUrl.toURI()));

		URL endTimesUrl = Thread.currentThread().getContextClassLoader().getResource("end.log");
		Stream<String> endTimes = Files.lines(Paths.get(endTimesUrl.toURI()));

		URL abbreviationsUrl = Thread.currentThread().getContextClassLoader().getResource("abbreviations.txt");
		Stream<String> abbreviations = Files.lines(Paths.get(abbreviationsUrl.toURI()));

		RacerRepository repository = new RacerRepository();
		TopRacersFormatter formatter = new TopRacersFormatter();

		List<Racer> racers = repository.getRacers(startTimes, endTimes, abbreviations);
		System.out.println(formatter.format(racers, 15));
	}
}