/**
 * Created by Ha on 16. 1. 5..
 */

/*
    page 232 부터
    정렬 알고리즘
                  최선    최악    평균       특징
    1. 선택 정렬    N^2    N^2    N^2       제자리 정렬, 불안정, 원소를 맞바꾸는 회수가 최대 n-1 때문에 데이터 원소를 움직이는 게 비교 작업에 비해 비싼 경우 좋음
    2. 삽입 정렬     N     N^2    N^2       제자리 정렬, 안정적, 리스트가 이미 정렬 되어 있을 때 N, 소량의 데이터 집함 처리에 강함
    3. 퀵  정렬   NlogN   N^2    NlogN     제자리 아님, 불안정, pivot을 어떤 것을 고르냐에 따라 성능이 달리지지만 평균 NlogN
    4. 병합 정렬   NlogN  NlogN  NlogN      제자리 아님, 안정적, N 추가 메모리 필요, 데이터 집합이 메모리에 한번에 올리기에 어려울때, 정렬 시간의 상한을 철저히 지켜야 할때
 */

public class Sort {
    private int data[];

    public Sort(int[] data) {
        this.data = data;
    }

    // 각 위치에 남은 숫자들중 가장 작은 값을 차례로 찾아서 넣는 선택 정렬
    public void selectionsort(){
        for(int i = 0; i < data.length; i++){
            int min_index = findMinIndex(i);
            if(i != min_index) // min_index가 현재 위치라면 바꿀 필요가 없다
                swap(i, min_index);
        }
    }

    // data 배열에서 start 오른쪽에서 가장 작은 값의 위치를 반환하는 함수
    // 선택 정렬에 사용된다
    private int findMinIndex(int start){
        int min = data[start];
        int min_index = start;
        for(int i = start; i < data.length; i++){
            if(data[i] < min) {
                min = data[i];
                min_index = i;
            }
        }

        return min_index;
    }

    // 삽입정렬 wrapper
    public void insertionsort(){
        insertionsort(0, data.length - 1);
    }

    // 이미 정렬된 원소들 사이에 제 위치를 찾아 삽입하는 삽입 정렬
    private void insertionsort(int start, int end){
        for(int i = start + 1; i <= end; i++){
            int cur_data = data[i]; // 현재 데이터

            for(int j = 0; j < i; j++){
                if(data[j] > cur_data){
                    // j 위치부터 i-j 길이 만큼 j 위치로 옮김
                    System.arraycopy(data, j, data, j+1, i-j);
                    data[j] = cur_data; // 옮기고 난후 j위치에 현재 데이터 삽입(제 위치에 찾아 들어옴)
                    break;
                }
            }

        }
    }


    // 퀵정렬 wrapper
    public void quicksort(){
        quicksort(0, data.length - 1);
    }

    // 퀵정렬
    private void quicksort(int s, int e){
        if(s >= e) // 같은 위치이므로 정렬 안해도 됨
            return;

        // pivot 은 제일 처음 원소
        int pivot = data[s];

        // 리스트를 양쪽에서 순회하는 itrator
        int start = s + 1, end = e;

        while(start <= end){
            // 왼쪽 데이터가 pivot보다 작다면 계속 해서 start 증가(start 와 end가 겹치지 않을 동안)
            while(start <= end && pivot > data[start])
                start++;

            // 오른쪽 데이터가 pivot보다 크다면 계속 해서 end 감소(start 와 end가 겹치지 않을 동안)
            while(start <= end && pivot < data[end])
                end--;

            // 두 iterator가 겹치지 않았다면 데이터 맞교환
            if(start <= end)
                swap(start, end);
            else // 두 iterator가 겹친경우(지나친경우) pivot과 중간 맞교환
                swap(s, end);
        }

        quicksort(s,end - 1); // 왼쪽 반 퀵정렬
        quicksort(end + 1, e); // 오른쪽 반 퀵정렬
    }

    // 데이터 교환 함수
    private void swap(int a, int b){
        int temp = data[a];
        data[a] = data[b];
        data[b] = temp;
    }

    // 병합 정렬 wrapper
    public void mergesort(){
        mergesort(0, data.length - 1);
    }

    // 병합 정렬
    public void mergesort(int start, int end){
        if( start >= end) return; // 정렬할 원소개수가 0 혹은 1일때는 이미 정렬된 것

        // 정렬할 데이터집합이 아주 작은 경우 삽입정렬 효율이 좋기 때문에
        // 길이 10 이하 리스트는 삽입정렬 수행으로 최적화
        // ps. 안해도 잘 동작함. 하지만 데이터 집합이 커질수록 성능차이가 확연하게 남.
        // 500000개 test시 134초 vs 43초
        if(end - start < 10) {
            insertionsort(start, end);
            return;
        }

        // 가운데 선택
        int mid = (start + end) / 2;

        // 분할과 정복으로 양쪽 병합정렬 수행
        mergesort(start, mid);
        mergesort(mid + 1, end);

        // 정렬된 양쪽을 합침
        merge(start, mid, mid + 1, end);
    }

    private void merge(int left, int left_end, int right, int right_end){
        int buf[] = new int[data.length];
        int buf_index = 0;

        while(left <= left_end && right <= right_end) {

            // 왼쪽 오른쪽중 더 작은 것을 결과에 추가하고 해당 쪽의 인덱스 증가
            if (data[left] <= data[right])
                buf[buf_index++] = data[left++];
            else
                buf[buf_index++] = data[right++];
        }

        // 왼쪽 배열이 모두 동났다면 오른쪽 배열의 나머지를 결과에 순서대로 추가
        while(right < right_end)
            buf[buf_index++] = data[right++];

        // 오른쪽 배열이 모두 동났다면 왼쪽 배열의 나머지를 결과에 순서대로 추가
        while(left < left_end)
            buf[buf_index++] = data[left++];
    }


    // 데이터 출력 함수
    public void print(){
        for(int i = 0; i < data.length; i++)
            System.out.print(data[i] + " ");
        System.out.println();

    }
}
