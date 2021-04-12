package ua.com.foxminded.formulaone;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import static java.util.Comparator.comparing;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;

public class TopRacersFormatter {

	private static final String CR = System.lineSeparator();

	public String format(List<Racer> racers, int topRacersNumber) {
		StringBuilder result = new StringBuilder();

		int maxRacerNameLength = getMaxFieldLength(racers, Racer::getName);
		int maxTeamNameLength = getMaxFieldLength(racers, Racer::getTeamName);

		AtomicInteger index = new AtomicInteger(0);
		String racerFormat = "%1$02d. %2$-" + maxRacerNameLength + "s | %3$-" + maxTeamNameLength + "s | ";
		racers.stream().sorted(comparing(Racer::getBestLapTime)).forEach(racer -> {
			String formattedRacer = formatRacer(racer, racerFormat, index.addAndGet(1));
			result.append(formattedRacer);
			if (index.get() == topRacersNumber) {
				result.append(repeatChar(formattedRacer.length() - 2, '-')).append(CR);
			}
		});

		return result.toString();
	}

	private int getMaxFieldLength(List<Racer> racers, Function<Racer, String> getter) {
		return racers.stream().mapToInt(racer -> getter.apply(racer).length()).max().getAsInt();
	}

	public String formatRacer(Racer racer, String racerFormat, int index) {
		LocalTime time = LocalTime.ofNanoOfDay(racer.getBestLapTime().toNanos());
		String timeOutput = time.format(DateTimeFormatter.ofPattern("mm:ss.SSS"));

		return String.format(racerFormat, index, racer.getName(), racer.getTeamName()) + timeOutput + CR;
	}

	private String repeatChar(int length, char ch) {
		StringBuilder builder = new StringBuilder();

		for (int i = 0; i < length; i++) {
			builder.append(ch);
		}

		return builder.toString();
	}
}
