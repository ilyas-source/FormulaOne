package ua.com.foxminded.formulaone;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class Main {

	public static void main(String[] args) throws IOException, URISyntaxException {
		RacerRepository repository = new RacerRepository();
		TopRacersFormatter formatter = new TopRacersFormatter();

		List<Racer> racers = repository.getRacers("start.log", "end.log", "abbreviations.txt");
		System.out.println(formatter.format(racers, 15));
	}
}