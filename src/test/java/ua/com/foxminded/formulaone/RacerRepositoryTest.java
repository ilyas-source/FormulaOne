package ua.com.foxminded.formulaone;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.time.Duration;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

public class RacerRepositoryTest {

	RacerRepository repository = new RacerRepository();

	@Test
	void givenStreams_onGetRacers_thenGetList() throws IOException {
		List<Racer> expected = new ArrayList<Racer>();

		expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		expected.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));
		expected.sort(Comparator.comparing(Racer::getBestLapTime));

		Stream<String> startLog = Stream.of("SVF2018-05-24_12:02:58.917", "DRR2018-05-24_12:14:12.054",
				"LHM2018-05-24_12:18:20.125");

		Stream<String> endLog = Stream.of("LHM2018-05-24_12:19:32.585", "DRR2018-05-24_12:15:24.067",
				"SVF2018-05-24_12:04:03.332");

		Stream<String> abbreviations = Stream.of("DRR_Daniel Ricciardo_RED BULL RACING TAG HEUER",
				"SVF_Sebastian Vettel_FERRARI", "LHM_Lewis Hamilton_MERCEDES");

		List<Racer> actual = repository.getRacers(startLog, endLog, abbreviations);
		actual.sort(Comparator.comparing(Racer::getBestLapTime));

		assertEquals(expected, actual);
	}

	@Test
	void givenEmptyStartLog_onGetRacers_thenThrowsException() {
		Stream<String> startLog = Stream.of("");
		Stream<String> endLog = Stream.of("MES2018-05-24_12:05:58.778", "RGH2018-05-24_12:06:27.441");
		Stream<String> abbreviations = Stream.of("MES_Marcus Ericsson_SAUBER FERRARI",
				"RGH_Romain Grosjean_HAAS FERRARI");

		assertThrows(StringIndexOutOfBoundsException.class, () -> {
			repository.getRacers(startLog, endLog, abbreviations);
		});
	}

	@Test
	void givenDifferentSizes_getRacers_thenNullPointerException() {
		Stream<String> startLog = Stream.of("MES2018-05-24_12:04:45.513");
		Stream<String> endLog = Stream.of("MES2018-05-24_12:05:58.778", "RGH2018-05-24_12:06:27.441");
		Stream<String> abbreviations = Stream.of("MES_Marcus Ericsson_SAUBER FERRARI",
				"RGH_Romain Grosjean_HAAS FERRARI");

		assertThrows(NullPointerException.class, () -> {
			repository.getRacers(startLog, endLog, abbreviations);
		});
	}

}
