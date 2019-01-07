
import java.io.*;
import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * В файле хранятся координаты вершин четырехугольника в порядке обхода фигуры по часовой стрелке в виде:
 *
 * <координата x1> <координата y1>
 *
 * <координата x2> <координата y2>
 *
 * <координата x3> <координата y3>
 *
 * <координата x4> <координата y4>
 *
 * Считаем, что полученные из файла вершины гарантированно образуют выпуклый четырехугольник.
 * Написать программу, которая считывает координаты из файла. При запуске ждет от пользователя
 * ввода координат некой точки и выводит один из четырех возможных результатов:
 *
 * точка внутри четырехугольника
 *
 * точка лежит на сторонах четырехугольника
 *
 * точка - вершина четырехугольника
 *
 * точка снаружи четырехугольника
 */

public class Test2 {
    private static class Point{
        int x;
        int y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    private static boolean vershina(Point[] pointlist, Point point){
        for (int i = 0; i < pointlist.length; i++) {
            if (pointlist[i].x == point.x && pointlist[i].y == point.y) return true;
        }
        return false;
    }

    private static String prinadlegnost(Point[] pointlist, Point M){
        String s = "";
        DecimalFormat df = new DecimalFormat("#,####");
        double result = 0;


        for (int i = 0; i < pointlist.length; i++) {
            Point A = null;
            Point B = null;
            if (i == 3){
                A = pointlist[3];
                B = pointlist[0];
            }
            else {
                A = pointlist[i];
                B = pointlist[i+1];
            }
            Point vectorMA = new Point(A.x - M.x, A.y - M.y);
            Point vectorMB = new Point(B.x - M.x, B.y - M.y);

            double imnozh = vectorMA.x * vectorMB.x + vectorMA.y * vectorMB.y;
            double module1 = Math.sqrt(Math.pow((double) vectorMA.x, 2) + Math.pow((double) vectorMA.y, 2));
            double module2 = Math.sqrt(Math.pow((double) vectorMB.x, 2) + Math.pow((double) vectorMB.y, 2));
            double znam = module1 * module2;
            double cos = imnozh / znam;



            double cos1 =(double) Math.round((Math.acos(cos) * 180 / Math.PI) * 100) /100;
            if (cos1 == 180){
                s= "точка лежит на сторонах четырехугольника";
                break;
            }
            result += cos1;
            if (result == 360) s = "точка внутри четырехугольника";

            else s = "точка снаружи четырехугольника";
        }


        return s;
    }
    public static void main(String[] args){

        String fileName = null;
        File file = null;
        Point[] chetirehugol = null;
        try(BufferedReader stroka = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {
                System.out.println("Введите полный путь к файлу с координатами выпуклого четырехугольника:");
                fileName = stroka.readLine();

                if (fileName.equals("exit")) {
                    stroka.close();
                    System.exit(0);
                }

                file = new File(fileName);
                if (file.isFile()) break;
                else {
                    System.out.println("Введено неворное имя файла, пожалуйста введите повторно");
                }
            }
        }
        catch (IOException e){}



        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            chetirehugol = new Point[4];
            int i = 0;
            while (br.ready()) {
                String s = br.readLine();
                String s1 = s.split("> <")[0].substring(1);
                String s2 = s.split("> <")[1].substring(0, s.split("> <")[1].length() - 1);
                chetirehugol[i] = new Point(Integer.parseInt(s1), Integer.parseInt(s2));
                i++;
            }
        }
        catch (IOException e){}


        Scanner scanner = new Scanner(System.in);


        System.out.println("Введите координату X:");
        int x = scanner.nextInt();
        System.out.println("Введите координату Y:");
        int y = scanner.nextInt();
        Point point = new Point(x, y);
        if (vershina(chetirehugol, point)){
            System.out.println("точка - вершина четырехугольника");

        }
        else {
            System.out.println(prinadlegnost(chetirehugol, point));
        }
    }
}
