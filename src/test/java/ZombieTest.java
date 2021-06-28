import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.NoSuchFileException;
import java.util.List;


public class ZombieTest {
    @Test
    public void test_file_not_exist() {
        Assertions.assertThrows(NoSuchFileException.class, () -> {
            InputHelper.readFile("not-existing.txt");
        });
    }

    @Test
    public void test_filePath_empty() {
        Assertions.assertThrows(UncheckedIOException.class, () -> {
            InputHelper.readFile("");
        });
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/test/java/test_1.txt"})
    public void test_regular_work_case_1(String filePath) throws IOException {
        List<int[]> expected = List.of(new int[]{1, 1}, new int[]{2, 1}, new int[]{3, 2}, new int[]{3, 1});
        Zombie instance = new Zombie(filePath);
        instance.zombieMovies();

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertArrayEquals(expected.get(i), instance.zombies_positions.get(i));
        }
    }

    @ParameterizedTest
    @ValueSource(strings = {"src/test/java/test_2.txt"})
    public void test_regular_work_case_2(String filePath) throws IOException {
        List<int[]> expected = List.of(new int[]{3, 0}, new int[]{2, 1}, new int[]{1, 0}, new int[]{0, 0});
        Zombie instance = new Zombie(filePath);
        instance.zombieMovies();

        for (int i = 0; i < expected.size(); i++) {
            Assertions.assertArrayEquals(expected.get(i), instance.zombies_positions.get(i));
        }

    }


    @ParameterizedTest
    @ValueSource(strings = {"src/test/java/test_3.txt"})
    public void test_inpput_data_format_exception(String filePath) {
        Assertions.assertThrows(NumberFormatException.class, () -> {
            InputHelper.readFile(filePath);
            Zombie instance = new Zombie(filePath);
            instance.zombieMovies();
        });
    }

}
