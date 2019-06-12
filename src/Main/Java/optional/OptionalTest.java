package src.Main.Java.optional;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class OptionalTest {
    public static void main(String[] args) throws IOException {
        String contents = new String(
                Files.readAllBytes(
                        Paths.get("test.txt")
                ), StandardCharsets.UTF_8
        );

        List<String> wordList = Arrays.asList(contents.split("\\PL+"));

        /**
         * Использование Optional в случае ессли значение существует выдает первое попавшееся, в случае если нет, выдает
         * "No words" очень удобно замещает тернарные операторы в потоках.
         */
        Optional<String> optionalValue =
                wordList.stream()
                        .filter(s -> s.contains("It"))
                        .findFirst();
        System.out.println(optionalValue.orElse(("No words")) + " contains It's");

        Optional<String> optionalString =
                Optional.empty();
        String result = optionalString.orElse("N/A");
        System.out.println("result: " + result);

        /**
         * Использование Optional в случае если существует выводит значение, в случае если значения не существует
         * выполняет функцию по поиску значения по умалчанию.
         */

        result =
                optionalString.orElseGet(() ->
                        Locale.getDefault().getDisplayName());
        System.out.println("result: " + result);

        /**
         * использование Optional в случае если значение существует выводит значение, в ином случае выбрасывает
         * Exception
         */

        try {
            result =
                    optionalString.orElseThrow(IllegalStateException::new);
            System.out.println("result: " + result);

        } catch (Throwable t) {
            t.printStackTrace();
        }

        /**
         * Испльзование Optional в случае присутствия значения выполняет функцию указанную в лямбда выражении
         */

        optionalValue =
                wordList.stream()
                        .filter(s -> s.contains("It's"))
                        .findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + " contains It"));

        Set<String> results = new HashSet<>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added =
                optionalValue.map(results::add);
        System.out.println(added);

        System.out.println(inverse(4.0)
                .flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0)
                .flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0.0)
                .flatMap(OptionalTest::squareRoot));

        Optional<Double> result2 = Optional.of(-4.0)
                .flatMap(OptionalTest::inverse)
                .flatMap(OptionalTest::squareRoot);
        System.out.println(result2);

    }

    public static Optional<Double> inverse(Double x) {
        return x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }
}
