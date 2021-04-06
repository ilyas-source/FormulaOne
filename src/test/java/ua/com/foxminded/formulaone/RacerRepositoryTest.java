package ua.com.foxminded.formulaone;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Properties;

import org.junit.jupiter.api.Test;

public class RacerRepositoryTest {

	@Test
	void givenFilenames_onGetRacers_thenGetList() throws IOException {
		Properties properties = new Properties();
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.properties");

		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file not found");
		}

		String startLogFileName = properties.getProperty("testStartLogFileName");
		String endLogFileName = properties.getProperty("testEndLogFileName");
		String abbrFileName = properties.getProperty("testAbbrFileName");

		RacerRepository racerRepositor = new RacerRepository();
		List<Racer> expected = new ArrayList<Racer>();

		expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		expected.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
		expected.sort(Comparator.comparing(Racer::getBestLapTime));

		List<Racer> actual = racerRepositor.getRacers(startLogFileName, endLogFileName, abbrFileName);
		actual.sort(Comparator.comparing(Racer::getBestLapTime));

		assertEquals(expected, actual);
	}

}
