public class Ex11 {

    public void printHello(int i) {
        System.out.println("Hello " + i);
    }
    public static void main (String[] args) {
        //Depending on the value provided from the command line, your program should create the exact number of objects.

        //Each object should print short hello message on the screen, including the number of already created objects.

        int numberOfObjects = Integer.parseInt(args[0]);

        for (int i=0; i<numberOfObjects; i++) {
            new Ex11().printHello(i+1);
        }

    }


}


