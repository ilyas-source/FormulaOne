package ua.com.foxminded.formulaone;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.OptionalInt;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class TopRacersFormatter {

	private static final String CR = System.lineSeparator();
	private static final DateTimeFormatter TIME_FORMATTER = DateTimeFormatter.ofPattern("mm:ss.SSS");

	public String format(List<Racer> racers, int topRacersNumber) {
		StringBuilder result = new StringBuilder();

		int maxRacerNameLength = getMaxFieldLength(racers, Racer::getName);
		int maxTeamNameLength = getMaxFieldLength(racers, Racer::getTeamName);

		AtomicInteger index = new AtomicInteger(0);
		String racerFormat = "%1$02d. %2$-" + maxRacerNameLength + "s | %3$-" + maxTeamNameLength + "s | ";
		racers.stream() //
				.sorted(comparing(Racer::getBestLapTime)) //
				.forEach(racer -> result.append(formatRacer(racer, racerFormat, index.addAndGet(1), topRacersNumber)));

		return result.toString();
	}

	private int getMaxFieldLength(List<Racer> racers, Function<Racer, String> getter) {
		return racers.stream() //
				.map(getter::apply) //
				.mapToInt(String::length) //
				.max().orElse(0);
	}

	public String formatRacer(Racer racer, String racerFormat, int index, int topRacersNumber) {
		LocalTime time = LocalTime.ofNanoOfDay(racer.getBestLapTime().toNanos());
		String formattedTime = time.format(TIME_FORMATTER);
		StringBuilder result = new StringBuilder();
		result.append(String.format(racerFormat, index, racer.getName(), racer.getTeamName()) + formattedTime + CR);
		if (index == topRacersNumber) {
			result.append(repeatChar(result.length() - 2, '-')).append(CR);
		}
		return result.toString();
	}

	private String repeatChar(int length, char character) {
		StringBuilder result = new StringBuilder();

		for (int i = 0; i < length; i++) {
			result.append(character);
		}

		return result.toString();
	}
}
