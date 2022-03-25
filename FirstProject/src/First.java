import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class First {
    public static void main( String[] args ) {


        Date date = new Date();  // New Object that represents the current time (date) is created.



        DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm");

        //Print the current date
        System.out.println(df.format(date));

        /* The merged text, which consists of the text and the int value, which is AUTOMATICALLY converted to a string, will be written on the screen */

    }
}
