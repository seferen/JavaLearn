package src.Main.Java;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Stream;

/**
 * Добрая Зинаида и летние каникулы
 * Создать словарь (Map<String, Date>) и занести в него десять записей по принципу: "фамилия" - "дата рождения".
 * Удалить из словаря всех людей, родившихся летом.
 *
 *
 * Требования:
 * 1. Программа не должна выводить текст на экран.
 * 2. Программа не должна считывать значения с клавиатуры.
 * 3. Метод createMap() должен создавать и возвращать словарь HashMap с типом элементов String, Date состоящий из 10 записей.
 * 4. Метод removeAllSummerPeople() должен удалять из словаря всех людей, родившихся летом.
 */

/* 
Добрая Зинаида и летние каникулы
*/

public class Task0816 {
    public static HashMap<String, Date> createMap() throws ParseException {
        DateFormat df = new SimpleDateFormat("MMMMM d yyyy", Locale.ENGLISH);
        HashMap<String, Date> map = new HashMap<String, Date>();
        map.put("Stallone", df.parse("JUNE 1 1980"));
        map.put("Mike", df.parse("MARCH 1 1980"));
        map.put("Jack", df.parse("SEPTEMBER 1 1980"));
        map.put("Alex", df.parse("FEBRUARY 1 1980"));
        map.put("Nick", df.parse("JANUARY 1 1980"));
        map.put("Richard", df.parse("JUNE 1 1980"));
        map.put("Smith", df.parse("JULY 1 1980"));
        map.put("Bradley", df.parse("JUNE 1 1980"));
        map.put("Sapronov", df.parse("JUNE 1 1980"));
        map.put("Nukem", df.parse("AUGUST 1 1980"));

       return map; //напишите тут ваш код
    }

    public static void removeAllSummerPeople(HashMap<String, Date> map) {
        SimpleDateFormat sf = new SimpleDateFormat("MM");
        HashMap<String, Date> copy = new HashMap<>(map);

        for (Map.Entry<String, Date> element : copy.entrySet()){
            int s = Integer.parseInt(sf.format(element.getValue()));
            if (s > 5 && s < 9){
                map.remove(element.getKey());
            }
        }

    }



    public static void main(String[] args) throws ParseException {

       



    }
}
