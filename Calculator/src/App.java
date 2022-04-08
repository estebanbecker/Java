
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
                        if(pressedEqual) {
                            firstNumber = result;
                            pressedEqual = false;
                        }
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation);
                            secondNumber = 0;
                        }
                        operation = "+";
                        pressedNumber = false;
                        jtf.setText(String.valueOf(firstNumber));
                        break;
                    case "-":
                        if(pressedEqual) {
                            firstNumber = result;
                            pressedEqual = false;
                        }
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation);
                            secondNumber = 0;
                        }
                        operation = "-";
                        pressedNumber = false;
                        jtf.setText(String.valueOf(firstNumber));
                        break;
                    case "*":
                        if(pressedEqual) {
                            firstNumber = result;
                            pressedEqual = false;
                        }
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation);
                            secondNumber = 0;
                        }
                        operation = "*";
                        pressedNumber = false;
                        jtf.setText(String.valueOf(firstNumber));
                        break;
                    case "/":
                        if(pressedEqual) {
                            firstNumber = result;
                            pressedEqual = false;
                        }
                        if(secondNumber != 0) {
                            firstNumber = MakeOperation(firstNumber, secondNumber, operation);
                            secondNumber = 0;
                        }
                        operation = "/";
                        jtf.setText(String.valueOf(firstNumber));
                        pressedNumber = false;
                        break;
                    case "=":
                        if(pressedNumber == false){
                            secondNumber = firstNumber;
                            pressedNumber = true;
                        }
                        if(pressedEqual == false) {
                            result = MakeOperation(firstNumber, secondNumber, operation);
                            pressedEqual = true;
                        }else{
                            result = MakeOperation(firstNumber,result , operation);
                        }
                        jtf.setText(String.valueOf(result));
                        secondNumber = 0;

                        break;
                    case "C":
                        firstNumber = 0;
                        secondNumber = 0;
                        result = 0;
                        operation = "";
                        pressedNumber = true;
                        pressedEqual = false;
                        jtf.setText(String.valueOf(firstNumber));
                        break;
                    default:
                        if(pressedEqual) {
                            firstNumber = 0;
                            secondNumber = 0;
                            result = 0;
                            operation = "";
                            pressedNumber = true;
                            pressedEqual = false;
                            jtf.setText(String.valueOf(firstNumber));
                        }
                        
                        if (pressedNumber == true) {
                            firstNumber = firstNumber * 10 + Integer.parseInt(e.getActionCommand());
                        }else{
                            secondNumber = firstNumber;
                            firstNumber = Integer.parseInt(e.getActionCommand());
                            pressedNumber = true;
                        }
                        pressedEqual = false;
                        jtf.setText(String.valueOf(firstNumber));
                        break;
                }
    		}
            private String operation = "";
            private int firstNumber=0;
            private int secondNumber=0;
            private boolean pressedNumber=true;
            private boolean pressedEqual=false;
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
                        return (int)(secondNumber / firstNumber);
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