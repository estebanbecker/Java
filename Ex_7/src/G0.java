import javax.swing.JFrame;

public class G0 {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("My First Frame");

        jf.pack();
        jf.setVisible(true);
    }

	
    public static void main(String[] args) {
        System.out.println("Before");
        
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { createAndShowGUI(); }
        });
        
        System.out.println("After");
    }

}