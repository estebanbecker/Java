import java.util.Calendar;

public class ex7 {
    
    public static void main(String[] args) {
        int month = Calendar.getInstance().get(Calendar.MONTH);

        switch (month) {
            case Calendar.JANUARY:
                System.out.println("January");
                break;
            case Calendar.FEBRUARY:
                System.out.println("February");
                break;
            case Calendar.MARCH:
                System.out.println("March");
                break;
            case Calendar.APRIL:
                System.out.println("April");
                break;
            case Calendar.MAY:
                System.out.println("May");
                break;
            case Calendar.JUNE:
                System.out.println("June");
                break;
            case Calendar.JULY:
                System.out.println("July");
                break;
            case Calendar.AUGUST:
                System.out.println("August");
                break;
            case Calendar.SEPTEMBER:
                System.out.println("September");
                break;
            case Calendar.OCTOBER:  
                System.out.println("October");
                break;
            case Calendar.NOVEMBER:
                System.out.println("November");
                break;
            case Calendar.DECEMBER:
                System.out.println("December");
                break;

        }
        
    }
}

