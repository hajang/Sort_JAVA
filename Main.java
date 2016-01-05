/**
 * Created by Ha on 16. 1. 5..
 */

public class Main {

    public static void main(String[] args) {
        int size = 10000;
        int data[] = new int[size];
        for(int i = 0; i < size; i++){
            data[i] = size - i;
        }

        Sort s = new Sort(data);
        long start = System.currentTimeMillis(); // 시간 측정 시작


        // 선택정렬 수행
//        s.selectionsort();

        // 삽입정렬 수행
//        s.insertionsort();

        // 퀵정렬 수행
//        s.quicksort();

        // 병합 정렬 수행
        s.mergesort();


        long end = System.currentTimeMillis(); // 시간 측정 종료
        System.out.println("time elapsed : " + (end - start) / 1000.0 ) ;

        //결과 출력
//        s.print();
    }
}
