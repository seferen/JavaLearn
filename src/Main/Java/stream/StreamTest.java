package src.Main.Java.stream;

import java.io.IOException;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

public class StreamTest {
    public static void main(String[] args) {
        /**
         * Выделение потока данных и последующая его обработка
         */
        try {
            String contents = Files.readString(Paths.get("test.txt"));
            List<String> list = Arrays.asList(contents.split("\\PL+"));

            list.stream()
                    .filter((x) -> x.length() >= 2)
                    .forEach(System.out::println);

        } catch (IOException e) {
            e.printStackTrace();
        }



    }
}
