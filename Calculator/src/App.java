
import java.awt.BorderLayout;

import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class App {

    public static void createAndShowGUI() {
        JFrame jf = new JFrame("Calculator");

        //Create a graphical user interface (GUI) for a calculator

        JTextField jtf = new JTextField("0");
        jtf.setHorizontalAlignment(JTextField.RIGHT);
        jtf.setEditable(false);
        jf.getContentPane().add(jtf,BorderLayout.NORTH);

        //Create action listener
        ActionListener myActionListener = new ActionListener() {
    		public void actionPerformed(ActionEvent e) {
                switch (e.getActionCommand()) {
                    case "+":
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation);
                            secondNumber = 0;
                        }
                        operation = "+";
                        pressedNumber = false;
                        jtf.setText("+");
                        break;
                    case "-":
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation);
                            secondNumber = 0;
                        }
                        operation = "-";
                        pressedNumber = false;
                        jtf.setText("-");
                        break;
                    case "*":
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation);
                            secondNumber = 0;
                        }
                        operation = "*";
                        pressedNumber = false;
                        jtf.setText("*");
                        break;
                    case "/":
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation);
                            secondNumber = 0;
                        }
                        operation = "/";
                        jtf.setText("/");
                        pressedNumber = false;
                        break;
                    case "=":
                        result = MakeOperation(firstNumber,secondNumber, operation);
                        jtf.setText(String.valueOf(result));
                        secondNumber = result;
                        break;
                    case "C":
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        operation = "";
                        pressedNumber = true;
                        jtf.setText(String.valueOf(firstNumber));
                        break;
                    default:
                        if (pressedNumber == true) {
                            firstNumber = firstNumber * 10 + Integer.parseInt(e.getActionCommand());
                        }else{
                            secondNumber = firstNumber;
                            firstNumber = Integer.parseInt(e.getActionCommand());
                            pressedNumber = true;
                        }
                        jtf.setText(String.valueOf(firstNumber));
                        break;
                }
    		}
            private String operation = "";
            private int firstNumber=0;
            private int secondNumber=0;
            private boolean pressedNumber=true;
            private int result=0;
            

            private int MakeOperation (int firstNumber, int secondNumber, String operation) {
                switch (operation) {
                    case "+":
                        return firstNumber + secondNumber;
                    case "-":
                        return secondNumber - firstNumber;
                    case "*":
                        return firstNumber * secondNumber;
                    case "/":
                        return secondNumber / firstNumber;
                    default:
                        return 0;
                }
            }
    	};



        //Create a GridLayout manager with four rows and four columns
        GridLayout grid = new GridLayout(4,4);
        JPanel jp = new JPanel(grid);


        //Create buttons
        String list_button[] = {"1","2","3","+","4","5","6","-","7","8","9","*","0","=","/","C"};

        for (int i = 0; i < list_button.length; i++) {
            JButton jb = new JButton(list_button[i]);
            jb.addActionListener(myActionListener);
            jp.add(jb);
        }
        
        //Add panel to the frame
        jf.getContentPane().add(jp,BorderLayout.CENTER);

        

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