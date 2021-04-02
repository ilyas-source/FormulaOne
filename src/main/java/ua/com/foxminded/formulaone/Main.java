package ua.com.foxminded.formulaone;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		String startLogFileName = "src/resources/start.log";
		String endLogFileName = "src/resources/end.log";
		String abbrFileName = "src/resources/abbreviations.txt";

		Board board = new Board();
		System.out.println(board.build(startLogFileName, endLogFileName, abbrFileName));
	}

}
