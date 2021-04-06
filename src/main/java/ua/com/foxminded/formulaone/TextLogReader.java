package ua.com.foxminded.formulaone;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class TextLogReader {

	private String folderPath;

	public TextLogReader(String folderPath) {
		this.folderPath = folderPath;
	}

	public Stream<String> getStartLog() throws IOException {
		return readFile(folderPath + "/start.log");
	}

	public Stream<String> getEndLog() throws IOException {
		return readFile(folderPath + "/end.log");
	}

	public Stream<String> getAbbreviations() throws IOException {
		return readFile(folderPath + "/abbreviations.txt");
	}

	private Stream<String> readFile(String fileName) throws IOException {
		return Files.lines(Paths.get(fileName));
	}
}
