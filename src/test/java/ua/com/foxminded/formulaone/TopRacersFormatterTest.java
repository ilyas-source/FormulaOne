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

		expected.append("1. Sebastian Vettel | FERRARI                   | 01:04.415").append(CR);
		expected.append("2. Daniel Ricciardo | RED BULL RACING TAG HEUER | 01:12.013").append(CR);
		expected.append("-----------------------------------------------------------").append(CR);
		expected.append("3. Valtteri Bottas  | MERCEDES                  | 01:12.434").append(CR);

		List<Racer> racers = new ArrayList<Racer>();

		racers.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		racers.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		racers.add(new Racer("VBM", "Valtteri Bottas", "MERCEDES", Duration.parse("PT1M12.434S")));

		TopRacersFormatter formatter = new TopRacersFormatter();

		String actual = formatter.format(racers, 2);
		assertEquals(expected.toString(), actual);
	}
}
