package ua.com.foxminded.formulaone;

import static org.junit.jupiter.api.Assertions.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

public class TopRacersFormatterTest {

	private static final String CR = System.lineSeparator();

	@Test
	void givenRacersList_onFormat_ThenGetString() {
		StringBuilder expected = new StringBuilder();
		expected.append("1. Sebastian Vettel  | FERRARI                   | 01:04.415").append(CR);
		expected.append("2. Daniel Ricciardo  | RED BULL RACING TAG HEUER | 01:12.013").append(CR);
		expected.append("3. Valtteri Bottas   | MERCEDES                  | 01:12.434").append(CR);
		expected.append("4. Lewis Hamilton    | MERCEDES                  | 01:12.460").append(CR);
		expected.append("5. Stoffel Vandoorne | MCLAREN RENAULT           | 01:12.463").append(CR);
		expected.append("6. Kimi Raikkonen    | FERRARI                   | 01:12.639").append(CR);
		expected.append("7. Fernando Alonso   | MCLAREN RENAULT           | 01:12.657").append(CR);
		expected.append("8. Sergey Sirotkin   | WILLIAMS MERCEDES         | 01:12.706").append(CR);
		expected.append("9. Charles Leclerc   | SAUBER FERRARI            | 01:12.829").append(CR);
		expected.append("10. Sergio Perez     | FORCE INDIA MERCEDES      | 01:12.848").append(CR);
		expected.append("11. Romain Grosjean  | HAAS FERRARI              | 01:12.930").append(CR);
		expected.append("12. Pierre Gasly     | SCUDERIA TORO ROSSO HONDA | 01:12.941").append(CR);
		expected.append("13. Carlos Sainz     | RENAULT                   | 01:12.950").append(CR);
		expected.append("14. Esteban Ocon     | FORCE INDIA MERCEDES      | 01:13.028").append(CR);
		expected.append("15. Nico Hulkenberg  | RENAULT                   | 01:13.065").append(CR);
		expected.append("------------------------------------------------------------").append(CR);
		expected.append("16. Brendon Hartley  | SCUDERIA TORO ROSSO HONDA | 01:13.179").append(CR);
		expected.append("17. Marcus Ericsson  | SAUBER FERRARI            | 01:13.265").append(CR);
		expected.append("18. Lance Stroll     | WILLIAMS MERCEDES         | 01:13.323").append(CR);
		expected.append("19. Kevin Magnussen  | HAAS FERRARI              | 01:13.393").append(CR);

		List<Racer> racers = new ArrayList<Racer>();

		racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		racers.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));
		racers.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
		racers.add(new Racer("SVM", "Stoffel Vandoorne", "MCLAREN RENAULT", Duration.parse("PT1M12.463S")));
		racers.add(new Racer("KRF", "Kimi Raikkonen", "FERRARI", Duration.parse("PT1M12.639S")));
		racers.add(new Racer("FAM", "Fernando Alonso", "MCLAREN RENAULT", Duration.parse("PT1M12.657S")));
		racers.add(new Racer("SSW", "Sergey Sirotkin", "WILLIAMS MERCEDES", Duration.parse("PT1M12.706S")));
		racers.add(new Racer("CLS", "Charles Leclerc", "SAUBER FERRARI", Duration.parse("PT1M12.829S")));
		racers.add(new Racer("SPF", "Sergio Perez", "FORCE INDIA MERCEDES", Duration.parse("PT1M12.848S")));
		racers.add(new Racer("RGH", "Romain Grosjean", "HAAS FERRARI", Duration.parse("PT1M12.93S")));
		racers.add(new Racer("PGS", "Pierre Gasly", "SCUDERIA TORO ROSSO HONDA", Duration.parse("PT1M12.941S")));
		racers.add(new Racer("CSR", "Carlos Sainz", "RENAULT", Duration.parse("PT1M12.95S")));
		racers.add(new Racer("EOF", "Esteban Ocon", "FORCE INDIA MERCEDES", Duration.parse("PT1M13.028S")));
		racers.add(new Racer("NHR", "Nico Hulkenberg", "RENAULT", Duration.parse("PT1M13.065S")));
		racers.add(new Racer("BHS", "Brendon Hartley", "SCUDERIA TORO ROSSO HONDA", Duration.parse("PT1M13.179S")));
		racers.add(new Racer("MES", "Marcus Ericsson", "SAUBER FERRARI", Duration.parse("PT1M13.265S")));
		racers.add(new Racer("LSW", "Lance Stroll", "WILLIAMS MERCEDES", Duration.parse("PT1M13.323S")));
		racers.add(new Racer("KMH", "Kevin Magnussen", "HAAS FERRARI", Duration.parse("PT1M13.393S")));

		TopRacersFormatter formatter = new TopRacersFormatter();

		String actual = formatter.format(racers);
		assertEquals(expected.toString(), actual);
	}

}
