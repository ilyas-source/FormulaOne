package ua.com.foxminded.formulaone;

import static org.junit.jupiter.api.Assertions.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import org.junit.jupiter.api.Test;

class RacerRepositoryTest {

	RacerRepository repository = new RacerRepository();

	@Test
	void givenFilenames_onGetRacers_shouldReturnListOfRacers() throws IOException, URISyntaxException {
		List<Racer> expected = new LinkedList<Racer>();

		expected.add(new Racer("SVF", "Sebastian Vettel", "FERRARI", Duration.parse("PT1M4.415S")));
		expected.add(new Racer("DRR", "Daniel Ricciardo", "RED BULL RACING TAG HEUER", Duration.parse("PT1M12.013S")));
		expected.add(new Racer("LHM", "Lewis Hamilton", "MERCEDES", Duration.parse("PT1M12.46S")));

		List<Racer> actual = repository.getRacers("teststart.log", "testend.Log", "testabbreviations.txt");
		actual.sort(Comparator.comparing(Racer::getBestLapTime));

		assertEquals(expected, actual);
	}

	@Test
	void givenDifferentSizes_getRacers_ShouldThrowNullPointerException() {
		assertThrows(NullPointerException.class, () -> {
			repository.getRacers("teststart.log", "testend.Log", "abbreviations.txt");
		});
	}

}
