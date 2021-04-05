package ua.com.foxminded.formulaone;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Main {

	public static void main(String[] args) throws IOException {

		Properties properties = new Properties();
		String propertiesFileName = "config.properties";
		InputStream inputStream = Main.class.getClassLoader().getResourceAsStream(propertiesFileName);

		if (inputStream != null) {
			properties.load(inputStream);
		} else {
			throw new FileNotFoundException("property file '" + propertiesFileName + "' not found");
		}

		String startLogFileName = properties.getProperty("startLogFileName");
		String endLogFileName = properties.getProperty("endLogFileName");
		String abbrFileName = properties.getProperty("abbrFileName");

		Board board = new Board();
		System.out.println(board.build(startLogFileName, endLogFileName, abbrFileName));
	}

}
