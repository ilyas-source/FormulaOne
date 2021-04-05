package ua.com.foxminded.formulaone;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.ArrayList;
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

		String startLogFileName = properties.getProperty("startLogFileName");
		String endLogFileName = properties.getProperty("endLogFileName");
		String abbrFileName = properties.getProperty("abbrFileName");

		RacerRepository racerRepositor = new RacerRepository();
		List<Racer> expected = new ArrayList<Racer>();

		expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		expected.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));
		expected.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
		expected.add(new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT", Duration.parse("PT1M12.463S")));
		expected.add(new Racer("KRF", "Kimi Raikkonen", "FERRARI", Duration.parse("PT1M12.639S")));
		expected.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", Duration.parse("PT1M12.657S")));
		expected.add(new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES", Duration.parse("PT1M12.706S")));
		expected.add(new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI", Duration.parse("PT1M12.829S")));
		expected.add(new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES", Duration.parse("PT1M12.848S")));
		expected.add(new Racer("RGH", "Romain Grosjean", "HAAS FERRARI", Duration.parse("PT1M12.93S")));
		expected.add(new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", Duration.parse("PT1M12.941S")));
		expected.add(new Racer("CSR", "Carlos Sainz", "RENAULT", Duration.parse("PT1M12.95S")));
		expected.add(new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES", Duration.parse("PT1M13.028S")));
		expected.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT", Duration.parse("PT1M13.065S")));
		expected.add(new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", Duration.parse("PT1M13.179S")));
		expected.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", Duration.parse("PT1M13.265S")));
		expected.add(new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", Duration.parse("PT1M13.323S")));
		expected.add(new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI", Duration.parse("PT1M13.393S")));

		List<Racer> actual = racerRepositor.getRacers(startLogFileName, endLogFileName, abbrFileName);

		assertEquals(expected, actual);
	}

}
