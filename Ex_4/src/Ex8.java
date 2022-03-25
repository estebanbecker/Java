import java.util.Calendar;

public class Ex8 {

    private static String list_month[]= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    public static void main(String[] args) {
        int month = Calendar.getInstance().get(Calendar.MONTH);

        System.out.println(list_month[month]);
    }
}
