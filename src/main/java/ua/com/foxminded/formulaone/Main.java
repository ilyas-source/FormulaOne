package ua.com.foxminded.formulaone;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		URL url = Thread.currentThread().getContextClassLoader().getResource("start.log");
		TextLogReader reader = new TextLogReader(new File(url.toURI()).getParent());
		RacerRepository repository = new RacerRepository();
		TopRacersFormatter formatter = new TopRacersFormatter();

		List<Racer> racers = repository.getRacers(reader.getStartLog(), reader.getEndLog(), reader.getAbbreviations());
		System.out.println(formatter.format(racers, 15));
	}
}
