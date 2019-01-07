import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Парсер реквестов
 * Считать с консоли URL-ссылку.
 * Вывести на экран через пробел список всех параметров (Параметры идут после ? и разделяются &, например, lvl=15).
 * URL содержит минимум 1 параметр.
 * Выводить параметры нужно в той же последовательности, в которой они представлены в URL.
 * Если присутствует параметр obj, то передать его значение в нужный метод alert.
 * alert(double value) - для чисел (дробные числа разделяются точкой)
 * alert(String value) - для строк
 * Обрати внимание на то, что метод alert необходимо вызывать ПОСЛЕ вывода списка всех параметров на экран.
 *
 * Пример 1
 *
 * Ввод:
 * http://javarush.ru/alpha/index.html?lvl=15&view&name=Amigo
 *
 * Вывод:
 * lvl view name
 *
 * Пример 2
 *
 * Ввод:
 * http://javarush.ru/alpha/index.html?obj=3.14&name=Amigo
 *
 * Вывод:
 * obj name
 * double: 3.14
 *
 *
 * Требования:
 * 1. Программа должна считывать с клавиатуры только одну строку.
 * 2. Класс Solution не должен содержать статические поля.
 * 3. Программа должна выводить данные на экран в соответствии с условием.
 * 4. Программа должна вызывать метод alert с параметром double в случае, если значение параметра obj может быть 
 * корректно преобразовано в число типа double.
 * 5. Программа должна вызывать метод alert с параметром String в случае, если значение параметра obj НЕ может быть 
 * корректно преобразовано в число типа double.
 */

/* 
Парсер реквестов
*/

public class Task1527 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String adress = br.readLine();
        String param = adress.substring(adress.indexOf("?") + 1);
        String[] razbivka = param.split("&");
        ArrayList<String> parametres = new ArrayList<>();
        ArrayList<String> znach = new ArrayList<>();
        for (int i = 0; i < razbivka.length; i++){
            if (razbivka[i].contains("=")){
                parametres.add(razbivka[i].substring(0, razbivka[i].lastIndexOf("=")));
                znach.add(razbivka[i].substring(razbivka[i].indexOf("=")+1));
            }
            else {parametres.add(razbivka[i]);
            znach.add(null);
            }

        }
        String result = "";
        for (String s : parametres){   
            result = result + s + " ";
        }
        System.out.println(result);
        for (int i = 0; i < parametres.size(); i++){
            if (parametres.get(i).equals("obj")){
                try{
                    alert(Double.parseDouble(znach.get(i)));
                }catch (Exception e){
                    alert(znach.get(i));
                }
            }
        }        
    }

    public static void alert(double value) {
        System.out.println("double " + value);
    }

    public static void alert(String value) {
        System.out.println("String " + value);
    }
}
