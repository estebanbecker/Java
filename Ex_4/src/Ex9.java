public class Ex9 {
    
    public static void main (String[] args) {

        System.out.println("Number of value passed to te program: " + args.length);

        if(args.length == 0) {
            System.out.println("No value passed to the program");
        } else {
            int min= Integer.parseInt(args[0]);
            int max= Integer.parseInt(args[0]);
            int sum=0;

            for (int i=1; i<args.length; i++) {
                int value= Integer.parseInt(args[i]);
                if (value < min) {
                    min= value;
                }
                if (value > max) {
                    max= value;
                }
                sum+= value;
            }

            System.out.println("Min: " + min);
            System.out.println("Max: " + max);
            System.out.println("Average: " + (float)sum/args.length);
            
        }
    }
}
