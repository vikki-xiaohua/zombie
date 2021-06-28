import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Stream;

public class InputHelper {
    public static String[] readFile(String filePath) throws IOException {
        try (BufferedReader reader = Files.newBufferedReader(Path.of(filePath))) {
            Stream<String> lines = reader.lines();
            return lines.toArray(String[]::new);
        }
    }
}
