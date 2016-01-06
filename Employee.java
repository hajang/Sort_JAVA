import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Ha on 16. 1. 6..
 */

/*
    표준 라이브러리의 정렬 루틴을 이용하여 회사 전화번호부처럼 성(surname) 알파벳순
    그리고 이름(givenname) 알파벳순으로 정렬하라.
 */


public class Employee {
    public String givenname;
    public String surname;

    public Employee(String givenname, String surname) {
        this.givenname = givenname;
        this.surname = surname;
    }

    public static void sortEmployee(Employee []e){
        Arrays.sort(e, new EmployeeNameComparator());
    }

    public void print(){
        System.out.println(surname + " " + givenname);
    }
}

