package structural.composite.apply;

import javax.swing.*;


public class SwingExample {

    /**
     * JFrame -> Composite
     * <p>
     * JTextField ,JButton 등등 -> Leaf
     * <p>
     * java.awt.Component -> Component
     */

    public static void main(String[] args) {
        JFrame frame = new JFrame();


        JTextField textField = new JTextField();
        textField.setBounds(200, 200, 200, 40);
        frame.add(textField);

        JButton button = new JButton("click");
        button.setBounds(200, 100, 60, 40);
        button.addActionListener(e -> textField.setText("Hello Swing"));
        frame.add(button);

        frame.setSize(600, 400);
        frame.setLayout(null);
        frame.setVisible(true);


    }


}
