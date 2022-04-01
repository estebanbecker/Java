import javax.swing.JFrame;

public class G1 {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("My First Frame");

        jf.pack();
        jf.setVisible(true);
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

	
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() { createAndShowGUI(); }
        });        
    }

}