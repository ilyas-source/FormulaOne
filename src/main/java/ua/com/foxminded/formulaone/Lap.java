package ua.com.foxminded.formulaone;

import java.time.Duration;
import java.time.LocalDateTime;

public class Lap implements Comparable<Lap> {
	private LocalDateTime startDateTime;
	private LocalDateTime endDateTime;
	private Duration duration;
	private Racer racer;

	public Lap(LocalDateTime startDateTime, LocalDateTime endDateTime, Duration duration, Racer racer) {
		this.startDateTime = startDateTime;
		this.endDateTime = endDateTime;
		this.racer = racer;
		this.duration = duration;
	}

	public Racer getRacer() {
		return racer;
	}

	public Duration getDuration() {
		return duration;
	}

	@Override
	public int compareTo(Lap lap) {
		if (lap == null) {
			return -1;
		}

		return this.duration.compareTo(lap.duration);
	}
}