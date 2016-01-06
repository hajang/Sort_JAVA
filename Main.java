/**
 * Created by Ha on 16. 1. 5..
 */

public class Main {
    private static long start;
    private static long end;

    public static void timer_start(){
        start = System.currentTimeMillis();
    }

    public static void timer_stop(){
        end = System.currentTimeMillis();
    }

    public static void print_time(String message){
        System.out.println(message + " time elapsed : " + (end - start) / 1000.0 ) ;
    }

    public static void main(String[] args) {
        int size = 10000;
        int data[] = new int[size];
        for(int i = 0; i < size; i++){
            data[i] = size - i;
        }

        Sort s = new Sort(data);
        timer_start();

        // 선택정렬 수행
//        s.selectionsort();

        // 삽입정렬 수행
//        s.insertionsort();

        // 퀵정렬 수행

        s.quicksort();
        timer_stop();
        print_time("not opt");

        timer_start();
        s.quicksortoptimized();

        // 병합 정렬 수행
//        s.mergesort();


        timer_stop();
        print_time("yes opt");

        //결과 출력
//        s.print();


        Employee e[] = {new Employee("Gildong", "Hong"), new Employee("Malja", "Kim"), new Employee("Mandoo", "Jung")};
        Employee.sortEmployee(e);

        for(int i = 0; i < e.length; i++)
            e[i].print();
    }
}
