package ua.com.foxminded.formulaone;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");

		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file not found");
		}

		String startLogFileName = properties.getProperty("startLogFileName");
		String endLogFileName = properties.getProperty("endLogFileName");
		String abbrFileName = properties.getProperty("abbrFileName");

		RacerRepository racerRepositor = new RacerRepository();
		TopRacersFormatter formatter = new TopRacersFormatter();

		List<Racer> racers = racerRepositor.getRacers(startLogFileName, endLogFileName, abbrFileName);
		System.out.println(formatter.format(racers));
	}

}
