package src.Main.Java;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;

/**
 *В течение дня в банк заходят люди, у каждого человека есть время захода в банк и время выхода. Всего за день у банка
 * было N посетителей. Банк работает с 8:00 до 20:00. Человек посещает банк только один раз за день. Написать программу,
 * которая определяет периоды времени, когда в банке было максимальное количество посетителей. Входные данные о
 * посетителях программа должна получать из файла, формат файла - произвольный, файл высылается вместе с решением.
 */
/**
 * Программа принимает файл с данными о времени прихода и ухода клиента
 */

public class Test1 {

    private static class Prihod implements Comparable<Prihod>{
        Date date;
        String label;
        public Prihod(Date date, String label) {
            this.date = date;
            this.label = label;
        }
        @Override
        public int compareTo(Prihod o) {
            return date.compareTo(o.date);
        }
        @Override
        public String toString() {
            return "date=" + date +
                    ", label='" + label;
        }
    }
    public static void main(String[] args) throws ParseException {
        File file = null;
        try(BufferedReader stroka = new BufferedReader(new InputStreamReader(System.in))) {
            while (true) {
                System.out.println("Введите полный путь к файлу для расчета максимального количества посетителей:");
                String fileName = stroka.readLine();
                if (fileName.equals("exit")) {
                    stroka.close();
                    System.exit(0);
                }
                file = new File(fileName);
                if (file.isFile()) break;
                else System.out.println("Не найден путь к файлу повторите попытку");
            }
        }catch (IOException e){}
        SimpleDateFormat smp = new SimpleDateFormat("dd.MM.yyyy/HH-mm");
        SimpleDateFormat smp2 = new SimpleDateFormat("HH:mm");
        ArrayList<Prihod> list = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                String s = br.readLine();
                Date date1 = smp.parse(s.split(" ")[0]);
                Date date2 = smp.parse(s.split(" ")[1]);
                list.add(new Prihod(date1, "з"));
                list.add(new Prihod(date2, "в"));
            }
        }catch (IOException e){}

        Collections.sort(list);
        int max=0;
        int count = 0;
        for (Prihod prihod : list) {
            if (prihod.label.equals("з")){
                count++;
                if (max < count) max = count;
            }
            else count--;
        }
        LinkedHashMap<Date, Date> result = new LinkedHashMap<>();
        Date periodStart = null;
        for (Prihod prihod : list) {
            if (prihod.label.equals("з")){
                count++;
                if (count == max){
                    periodStart = prihod.date;
                    result.put(periodStart, null);
                }
            }
            else {
                if (count == max) result.put(periodStart, prihod.date);
                count--;
            }
        }
        int finalMax = max;
        result.forEach((x, y) -> {
            System.out.println("Начало периода: " + smp2.format(x) + " Конец периода: " + smp2.format(y)
                        + " Количество посетителей: " + finalMax);
            });
    }
}

