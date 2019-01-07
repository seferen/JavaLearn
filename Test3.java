import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * В магазине 5 касс, в каждый момент времени к кассе стоит очередь некоторой длины. Каждые 30 минут измеряется средняя
 * длина очереди в каждую кассу и для каждой кассы это значение (число вещественное) записывается в соответсвующий ей
 * файл (всего 5 файлов), магазин работает 8 часов в день. Рассматривается только один день. На момент запуска
 * приложения все значения уже находятся в файлах. Написать программу, которая по данным замеров определяет интервал
 * времени, когда в магазине было наибольшее количество посетителей за день.
 */

/**
 * В программу передаются полные пути к файлам через пробел
 */

public class Test3 {
    public static void main(String[] args) {
        File[] kassi = null;
        try(BufferedReader stroka = new BufferedReader(new InputStreamReader(System.in))){
            kassi = new File[5];
            for(int i = 0; i < kassi.length; i++){
                System.out.println("Пожалуйста введите полный путь к файлу от кассы №" + (i + 1));
                String fileName = stroka.readLine();
                if (fileName.equals("exit")){
                    stroka.close();
                    System.exit(0);
                }
                kassi[i] = new File(fileName);
                if (!kassi[i].isFile()){
                    System.out.println("Введен неверный путь к файлу:");
                    i--;
                }
            }
        }
        catch (IOException e){}

        LinkedHashMap<String, Double> list = new LinkedHashMap<>();


        for (int i = 0; i < kassi.length; i++) {
            try(BufferedReader br = new BufferedReader(new FileReader(kassi[i]))){
                int k = 1;//обрабатываем файл поочередно
                while (br.ready()){
                    if (list.containsKey(k + "е полчаса")){
                        list.replace(k + "е полчаса", list.get(k++ + "е полчаса") + Double.parseDouble(br.readLine()));
                        continue;
                    }
                    list.put(k++ + "е полчаса", Double.parseDouble(br.readLine()));
                }
            }
            catch (IOException e){}
        }

        String time = "";
        double max = 0;
        for (Map.Entry<String, Double> element : list.entrySet()) {
            if (element.getValue() > max){
                time = element.getKey();
                max = element.getValue();
            }
        }

        System.out.printf("Наибольшее количество клиентов за день было в %s. Количество клиентов %.3f\n", time, max);
    }

}
