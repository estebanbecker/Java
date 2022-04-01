import java.awt.FlowLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JFrame;

public class G6 {

    public static void createAndShowGUI() {

    	MyActionListener2 myActionListener = new MyActionListener2(); // one object is enough
    	
    	JFrame jf = new JFrame("My First Frame");
        jf.setLayout(new FlowLayout());

        JButton jb = new JButton("Hello!");
        jb.addActionListener(myActionListener);
        jf.getContentPane().add(jb);

        JButton jb2 = new JButton("How are you?");
        jb2.addActionListener(myActionListener);
        jf.getContentPane().add(jb2);
        
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



class MyActionListener2 implements java.awt.event.ActionListener {

	public void actionPerformed(ActionEvent e) {
		System.out.println("Button = "+e.getActionCommand());
	}
	
}