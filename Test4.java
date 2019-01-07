import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Считать из файла последовательность целых чисел. Вычислить 90 персентиль, медиану, максимальное, минимальное и
 * среднее значения. На вход программа получает файл с числами. Каждое число в файле находится на новой строке.
 * Вывод в консоль должен быть следующим:
 *
 * 90 percentile <значение>
 *
 * median <значение>
 *
 * average <значение>
 *
 * max <значение>
 *
 * min <значение>
 */




public class Test4 {
    private static void percentile(List<Integer> list){
        int result = (int) (list.size() * 0.9);
        System.out.println("90 percentile <"+ list.get(result - 1) + ">");
    }
    private static void median(List<Integer> list){
        double result = 0;
        if (list.size() % 2 != 0) result = list.get(list.size() / 2);
        else result = ((double) list.get((list.size()/2 - 1)) + list.get(list.size() / 2)) / 2;
        System.out.println("median <" + result + ">");
    }

    private static void average(List<Integer> list){
        int count = 0;
        for (Integer integer : list) {
            count += integer;
        }
        double result = (double) count / list.size();
        System.out.println("average <" + result + ">");
    }
    private static void max(List<Integer> list){
        System.out.println("max <" + list.get(list.size() - 1) + ">");
    }
    private static void min(List<Integer> list){
        System.out.println("min <" + list.get(0) + ">");
    }
    public static void main(String[] args) {
        System.out.println("Пожалуйста укажите полный путь к файлу для произведения расчетов:");
        String fileName = null;
        File file = null;
        try (BufferedReader stroka = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                fileName = stroka.readLine();
                if (fileName.equals("exit")) {
                    stroka.close();
                    System.exit(0);
                }
                file = new File(fileName);
                if (!file.isFile()) System.out.println("Введен неверный путь к файлу, пожалуйста повторите");
                else break;
            }
        }catch (IOException e){}
        List<Integer> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                try {
                    list.add(Integer.parseInt(br.readLine()));
                } catch (IOException e) {
                    continue;
                }
            }
        }
        catch (IOException e){}
        // сортируем список

        Collections.sort(list);
        percentile(list);
        median(list);
        average(list);
        max(list);
        min(list);
    }
}

