package ua.com.foxminded.formulaone;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.junit.jupiter.api.Test;

class BoardTest {

	private static final String CR = System.lineSeparator();

	@Test
	void givenFilenames_onBoardBuild_thenGetString() throws IOException {
		String startLogFileName = "src/resources/start.log";
		String endLogFileName = "src/resources/end.log";
		String abbrFileName = "src/resources/abbreviations.txt";

		Board board = new Board();
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

		String actual = board.build(startLogFileName, endLogFileName, abbrFileName);

		assertEquals(expected.toString(), actual);
	}

}