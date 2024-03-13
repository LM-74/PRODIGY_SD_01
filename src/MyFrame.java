import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MyFrame extends JFrame implements ActionListener {
    JTextField textField;
    JButton button;
    JComboBox comboBox;
    JLabel label;
    JLabel printLabel;
    String[] units = {"Celsius °C", "Fahrenheit °F", "Kelvin °K"};
    MyFrame() {
        ImageIcon image = new ImageIcon("Prodigy InfoTech Logo.png");
        textField = new JTextField();
        textField.setBounds(20, 20, 200, 40);
        textField.setBackground(Color.WHITE);
        textField.setForeground(Color.BLACK);
        textField.setFont(new Font("Arial", Font.PLAIN, 17));
        textField.setCaretColor(Color.BLACK);
        textField.setPreferredSize(new Dimension(150, 30));
        textField.setText("0");

        button = new JButton();
        button.setFocusable(false);
        button.setPreferredSize(new Dimension(100,30));
        button.addActionListener(this);
        button.setText("Convert");
        button.setForeground(Color.BLACK);
        button.setBackground(Color.WHITE);
        button.setFont(new Font("Consolas", Font.BOLD, 16));


        comboBox = new JComboBox(units);
        comboBox.addActionListener(this);
        label = new JLabel();
        label.setText("Enter Temperature:");

        textField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (e.getKeyChar() != KeyEvent.VK_BACK_SPACE && (e.getKeyChar() < '0' || e.getKeyChar() > '9') && (e.getKeyCode() < 37 || e.getKeyCode() > 40))
                    e.consume();
            }
        });

        printLabel = new JLabel();
        printLabel.setPreferredSize(new Dimension(400, 50));
        printLabel.setHorizontalTextPosition(JLabel.CENTER);
        printLabel.setVerticalAlignment(JLabel.CENTER);
        printLabel.setVerticalTextPosition(JLabel.TOP);
        printLabel.setHorizontalAlignment(JLabel.CENTER);
        printLabel.setFont(new Font("Sanserif", Font.BOLD, 16));

        this.setTitle("Temperature Converter");
        this.add(label);
        this.add(textField);
        this.add(comboBox);
        this.add(button);
        this.add(printLabel);
        this.getContentPane().setBackground(new Color(0xF08B2E));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        FlowLayout flow = new FlowLayout();
        flow.setVgap(10);
        this.setLayout(flow);
        this.setSize(new Dimension(400, 200));
        this.setIconImage(image.getImage());
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int unit = Integer.parseInt(String.valueOf(comboBox.getSelectedIndex()));
        double temp;
        try {
            temp = Double.parseDouble(textField.getText());
        } catch (Exception NumberFormatException) {
            temp = 0;
        }
        double F = 0, K = 0, C = 0;
        if (unit == 0) {
            C = temp;
            F = convertCtoF(temp);
            K = convertCtoK(temp);
        }
        else if (unit == 1) {
            F = temp;
            C = convertFtoC(temp);
            K = convertFtoK(temp);
        }
        else if (unit == 2) {
            K = temp;
            C = convertKtoC(temp);
            F = convertKtoF(temp);
        }


        printLabel.setText("Celsius: " + (Math.round(C * 100)/100) + " Fahrenheit: " + (Math.round(F * 100)/100) + " Kelvin: " + (Math.round(K * 100)/100));
    }
    public double convertCtoF(double temp) {
        return 1.8 * temp + 32;
    }
    public double convertCtoK(double temp) {
        return temp + 273.15;
    }
    public double convertFtoC(double temp) {
        return (temp - 32) / 1.8;
    }
    public double convertFtoK(double temp) {
        return (temp - 32) / 1.8 + 273.15;
    }
    public double convertKtoC(double temp) {
        return temp - 273.15;
    }

    public double convertKtoF(double temp) {
        return (temp - 273.15) * 1.8 + 32;
    }
}
