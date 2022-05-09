package logs;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvLogger {
    private String filePath = "src/test/resources/";
    private final List<double[]> results;

    public CsvLogger(String fileName, List<double[]> results) {
        this.filePath = filePath + fileName;
        this.results = results;
    }

    public void log() {
        String csvString = "";

        try {
            Files.deleteIfExists(Paths.get(this.filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (PrintStream printStream = new PrintStream(new FileOutputStream(filePath, true))) {
            char CSV_SEPARATOR = ' ';
            for (double[] result: results) {
                csvString = String.format("%f%s %f\n", result[0], CSV_SEPARATOR, result[1]);
                printStream.print(csvString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
