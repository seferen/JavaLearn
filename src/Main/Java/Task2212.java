package src.Main.Java;

/**
 * �������� ������ ��������
 * ����� checkTelNumber ������ ���������, �������� �� �������� telNumber �������� ������� ��������.
 *
 * �������� ����������:
 * 1) ���� ����� ���������� � '+', �� �� �������� 12 ����
 * 2) ���� ����� ���������� � ����� ��� ����������� ������, �� �� �������� 10 ����
 * 3) ����� ��������� 0-2 ������ '-', ������� �� ����� ���� ������
 * 4) ����� ��������� 1 ���� ������ '(' � ')' , ������ ���� ��� ����, �� ��� ����������� ����� ������ '-'
 * 5) ������ ������ �������� ����� 3 �����
 * 6) ����� �� �������� ����
 * 7) ����� ������������� �� �����
 *
 * �������:
 * +380501234567 - true
 * +38(050)1234567 - true
 * +38050123-45-67 - true
 * 050123-4567 - true
 * +38)050(1234567 - false
 * +38(050)1-23-45-6-7 - false
 * 050���4567 - false
 * 050123456 - false
 * (0)501234567 - false
 *
 *
 * ����������:
 * 1. ����� checkTelNumber ������ ���������� �������� ���� boolean.
 * 2. ����� checkTelNumber ������ ���� ���������.
 * 3. ����� checkTelNumber ������ ��������� ���� �������� ���� String.
 * 4. ����� checkTelNumber ������ ��������� ��������� ���������� ������ �������� ����������� ��� � �������� ���������.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/*
�������� ������ ��������
*/
public class Task2212 {
    public static boolean checkTelNumber(String telNumber) {

        if(telNumber == null || telNumber.isEmpty()) return false;

            Pattern p = Pattern.compile("\\d");
            Matcher find = p.matcher(telNumber);
            int count = 0;
            while (find.find()) count++;
            if ((telNumber.split("")[0].equals("+") && count == 12) || count == 10) {
                if (telNumber.matches("^\\+?\\d+\\(?\\d{3}\\)?(\\d+-\\d){0,2}\\d+")) return true;
            }


        return false;
    }

    public static void main(String[] args) {
        System.out.println(checkTelNumber("+380501234567")); //     - true
        System.out.println(checkTelNumber("+38(050)1234567"));//   - true
        System.out.println(checkTelNumber("+38050123-45-67"));//         - true
        System.out.println(checkTelNumber("050123-4567"));// - true
        System.out.println(checkTelNumber("+38)050(1234567")); //     - false
        System.out.println(checkTelNumber("+38(050)1-23-45-6-7"));//        - false
        System.out.println(checkTelNumber("050���4567"));// - false
        System.out.println(checkTelNumber("050123456")); //- false
        System.out.println(checkTelNumber("(0)501234567"));// - false
        System.out.println(checkTelNumber(""));
        System.out.println(checkTelNumber(null));

    }
}
