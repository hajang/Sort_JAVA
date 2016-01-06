import java.util.Comparator;

/**
 * Created by Ha on 16. 1. 6..
 */

public class EmployeeNameComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {

        // 성 비교
        int ret = o2.surname.compareToIgnoreCase(o1.surname);

        // 성이 같은 경우 이름을 비교
        if(ret == 0)
            ret = o2.givenname.compareToIgnoreCase(o1.givenname);

        return ret;
    }
}
