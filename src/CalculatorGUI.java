import javax.swing.*;       // Swing is the library from which the GUI will be built upon
import javax.swing.border.Border;       // The Border around objects in Swing

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;          
import java.util.Arrays;
import java.text.DecimalFormat;     // To round a number to a certain number of decimal places

public class CalculatorGUI implements java.awt.event.ActionListener {

    JFrame frame;       // To create a main window where components like labels, buttons, textfields are added to create a GUI
	JTextField textfield1, textfield2;      // Areas where text is displayed on the GUI
    JPanel panel, panel_2, panel_3;     // To provide a container class to attach components like JButton
    JButton zero, one, two, three, four, five, six, seven, eight, nine, 
            add, divide, multiply, minus, del, ac, equalto, dot, multiply_tenx, 
            ans_button, left_bracket, right_bracket, x_variable, sin_function, 
            cos_function, tan_function, arctan_function, arcsin_function, 
            arccos_function, log_function, log_ten_function, ln_function, factorial, exponential, square_number,
            reciprocate_function, square_root_function, calc_function, shift, alpha, left_direction, right_direction; // To create buttons by which the user can interact with the GUI
    JButton num_buttons[] = {zero, one, two, three, four, five, six, seven, eight, nine};
    char[] superscript = {'⁰', '¹', '²', '³', '⁴', '⁵', '⁶', '⁷', '⁸', '⁹'};
    char[] subscript = {'₀', '₁', '₂', '₃', '₄', '₅', '₆', '₇', '₈', '₉'};
    String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    int num_operations = 0;     // keeps track of the number of operations on the program
    Double result = 0d;     // keeps the value of the result of the most recent calculation          
    Double prev_result = 0d;        // assigned when dealing with "ANS"

    public static void main(String[] args) {
        new CalculatorGUI();
    }

    // important symbols: 𝓍
    CalculatorGUI() {
        frame = new JFrame();
        frame.setTitle("Scientific Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // Action that should take place when the frame/window is closed  
        frame.setResizable(false);
        frame.setSize(360, 690);
        frame.setLayout(null);

        ImageIcon image = new ImageIcon("src/logo.png");
        frame.setIconImage(image.getImage());       // set Image as the Icon of the program
        frame.getContentPane().setBackground(Color.BLACK);

        Font font1 = new Font("Arial", Font.BOLD, 20);
        Font font2 = new Font("Arial", Font.BOLD, 10);
 
        textfield1 = new JTextField();
        textfield1.setBounds(25, 45, 300, 30);
        textfield1.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        textfield1.setVisible(true);        // determines if the textfield is visible 
        textfield1.setFont(font1);      // determines the font to be used by the textfield
        textfield1.setBackground(Color.WHITE);      // sets the background of the textfield to be white
        textfield1.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.GRAY));      // Determine the size and color of the border
        textfield1.getCaret().setVisible(true);     // set the caret of the textfield to be visible

        textfield2 = new JTextField();
        textfield2.setBounds(25, 110, 300, 30);
        textfield2.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        textfield2.setVisible(true);        // determines if the textfield is visible
        textfield2.setFont(font1);      // determines the font to be used by the textfield
        textfield2.setBackground(Color.WHITE);      // sets the background of the textfield to be white
        textfield2.setBorder(BorderFactory.createMatteBorder(0, 10, 10, 10, Color.GRAY));      // Determine the size and color of the border
        textfield2.setHorizontalAlignment(JTextField.RIGHT);        // Sets the alignment of the text on the textfield to the right

        JTextField textfield3 = new JTextField();
        textfield3.setBounds(25, 33, 300, 12);
        textfield3.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        textfield3.setVisible(true);        // determines if the textfield is visible
        textfield3.setFont(font2);      // determines the font to be used by the textfield
        textfield3.setBackground(Color.GRAY);      // sets the background of the textfield to be gray
        textfield3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));      // Determine the size and color of the border
        textfield3.setText("   S");
        textfield3.setForeground(Color.BLACK);
        frame.add(textfield3);

        JTextField textfield4 = new JTextField();
        textfield4.setBounds(25, 75, 300, 35);
        textfield4.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        textfield4.setVisible(true);        // determines if the textfield is visible
        textfield4.setFont(font1);      // determines the font to be used by the textfield
        textfield4.setBackground(Color.WHITE);      // sets the background of the textfield to be white
        textfield4.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.GRAY));      // Determine the size and color of the border
        frame.add(textfield4);

        panel = new JPanel();
        panel.setBounds(20,450,300,170);
        panel.setBackground(Color.black);  
        panel.setLayout(new GridLayout(4, 5, 10, 13));

        for(int i = 0; i < num_buttons.length; i++){
            num_buttons[i] = new JButton(Integer.toString(i));
            num_buttons[i].setBounds(0, 100, 85, 50);
            num_buttons[i].addActionListener(this);
            num_buttons[i].setBackground(Color.WHITE);
            num_buttons[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        }
        // Shift function of number 7
        JTextField constants = new JTextField();
        constants.setBounds(28, 438, 37, 12);
        constants.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        constants.setVisible(true);        // determines if the textfield is visible
        constants.setFont(font2);      // determines the font to be used by the textfield
        constants.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        constants.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        constants.setText("CONST");
        constants.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(constants);

        add = new JButton("+");
        add.setBounds(100, 100, 85, 50);
        add.addActionListener(this);
        add.setBackground(Color.WHITE);
        add.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        divide = new JButton("÷");
        divide.setBounds(100, 100, 85, 50);
        divide.addActionListener(this);
        divide.setBackground(Color.WHITE);
        divide.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        // Shift function of divide
        JTextField combination = new JTextField();
        combination.setBounds(287, 484, 33, 12);
        combination.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        combination.setVisible(true);        // determines if the textfield is visible
        combination.setFont(font2);      // determines the font to be used by the textfield
        combination.setBackground(Color.BLACK);      // sets the background of the textfield to be black
        combination.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        combination.setText("nCr");
        combination.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(combination);

        minus = new JButton("-");
        minus.setBounds(100, 100, 85, 50);
        minus.addActionListener(this);
        minus.setBackground(Color.WHITE);
        minus.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        multiply = new JButton("x");
        multiply.setBounds(100, 100, 85, 50);
        multiply.addActionListener(this);
        multiply.setBackground(Color.WHITE);
        multiply.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        // Shift function of multiply
        JTextField permutation = new JTextField();
        permutation.setBounds(223, 484, 33, 12);
        permutation.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        permutation.setVisible(true);        // determines if the textfield is visible
        permutation.setFont(font2);      // determines the font to be used by the textfield
        permutation.setBackground(Color.BLACK);      // sets the background of the textfield to be black
        permutation.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        permutation.setText("nPr");
        permutation.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(permutation);

        equalto = new JButton("=");
        equalto.setBounds(100, 100, 85, 50);
        equalto.addActionListener(this);
        equalto.setBackground(Color.WHITE);
        equalto.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        del = new JButton("DEL");
        del.setBounds(100, 100, 85, 50);
        del.addActionListener(this);
        del.setBackground(new Color(70, 90, 177));
        del.setForeground(Color.WHITE);
        del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        ac = new JButton("AC");
        ac.setBounds(100, 100, 85, 50);
        ac.addActionListener(this);
        ac.setBackground(new Color(70, 90, 177));
        ac.setForeground(Color.WHITE);
        ac.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        dot = new JButton(".");
        dot.setBounds(100, 100, 85, 50);
        dot.addActionListener(this);
        dot.setBackground(Color.WHITE);
        dot.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));

        multiply_tenx = new JButton("x10ⁿ");
        multiply_tenx.setBounds(100, 100, 85, 50);
        multiply_tenx.addActionListener(this);
        multiply_tenx.setBackground(Color.WHITE);
        multiply_tenx.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        // Shift function of multiply tenx button
        JTextField pie_symbol = new JTextField();
        pie_symbol.setBounds(145, 573, 33, 12);
        pie_symbol.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        pie_symbol.setVisible(true);        // determines if the textfield is visible
        pie_symbol.setFont(font2);      // determines the font to be used by the textfield
        pie_symbol.setBackground(Color.BLACK);      // sets the background of the textfield to be black 
        pie_symbol.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        pie_symbol.setText("π");
        pie_symbol.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(pie_symbol);

        ans_button = new JButton("ANS");
        ans_button.setBounds(100, 100, 85, 50);
        ans_button.addActionListener(this);
        ans_button.setBackground(Color.WHITE);
        ans_button.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        // Shift function of ans button
        JTextField percentage_symbol = new JTextField();
        percentage_symbol.setBounds(227, 573, 33, 12);
        percentage_symbol.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        percentage_symbol.setVisible(true);        // determines if the textfield is visible
        percentage_symbol.setFont(font2);      // determines the font to be used by the textfield
        percentage_symbol.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        percentage_symbol.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        percentage_symbol.setText("%");
        percentage_symbol.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(percentage_symbol);

        panel.add(num_buttons[7]); panel.add(num_buttons[8]); panel.add(num_buttons[9]); panel.add(del); panel.add(ac); 
        panel.add(num_buttons[4]); panel.add(num_buttons[5]); panel.add(num_buttons[6]); panel.add(multiply); panel.add(divide);
        panel.add(num_buttons[1]); panel.add(num_buttons[2]); panel.add(num_buttons[3]); panel.add(add); panel.add(minus); 
        panel.add(num_buttons[0]); panel.add(dot); panel.add(multiply_tenx); panel.add(ans_button); panel.add(equalto);

        panel_2 = new JPanel();
        panel_2.setBounds(20,253,300,170);
        panel_2.setBackground(Color.green);
        panel_2.setLayout(new GridLayout(4, 6, 10, 10));
        
        left_bracket = new JButton("(");
        left_bracket.setBounds(100, 100, 85, 85);
        left_bracket.addActionListener(this);
        left_bracket.setBackground(new Color(32, 32, 32));
        left_bracket.setForeground(Color.WHITE);
        left_bracket.setBorder(BorderFactory.createLineBorder(Color.black));

        right_bracket = new JButton(")");
        right_bracket.setBounds(100, 100, 85, 85);
        right_bracket.addActionListener(this);
        right_bracket.setBackground(new Color(32, 32, 32));
        right_bracket.setForeground(Color.WHITE);
        right_bracket.setBorder(BorderFactory.createLineBorder(Color.black));

        x_variable = new JButton("𝓍");
        x_variable.setBounds(100, 100, 85, 85);
        x_variable.addActionListener(this);
        x_variable.setBackground(new Color(32, 32, 32));
        x_variable.setForeground(Color.WHITE);
        x_variable.setBorder(BorderFactory.createLineBorder(Color.black));

        sin_function = new JButton("sin");
        sin_function.setBounds(100, 100, 85, 85);
        sin_function.addActionListener(this);
        sin_function.setBackground(new Color(32, 32, 32));
        sin_function.setForeground(Color.WHITE);
        sin_function.setBorder(BorderFactory.createLineBorder(Color.black));
        // Shift function of sin
        JTextField arcsin_function = new JTextField();
        arcsin_function.setBounds(98, 287, 33, 12);
        arcsin_function.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        arcsin_function.setVisible(true);        // determines if the textfield is visible
        arcsin_function.setFont(font2);      // determines the font to be used by the textfield
        arcsin_function.setBackground(Color.BLACK);      // sets the background of the textfield to be white
        Border border_2 = BorderFactory.createLineBorder(Color.BLACK); 
        arcsin_function.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        arcsin_function.setText("arcsin");
        arcsin_function.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(arcsin_function);

        cos_function = new JButton("cos");
        cos_function.setBounds(100, 100, 85, 85);
        cos_function.addActionListener(this);
        cos_function.setBackground(new Color(32, 32, 32));
        cos_function.setForeground(Color.WHITE);
        cos_function.setBorder(BorderFactory.createLineBorder(Color.black));
        // Shift function of cos
        JTextField arccos_function = new JTextField();
        arccos_function.setBounds(175, 287, 38, 12);
        arccos_function.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        arccos_function.setVisible(true);        // determines if the textfield is visible
        arccos_function.setFont(font2);      // determines the font to be used by the textfield
        arccos_function.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        arccos_function.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        arccos_function.setText("arccos");
        arccos_function.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(arccos_function);
        

        tan_function = new JButton("tan");
        tan_function.setBounds(100, 100, 85, 85);
        tan_function.addActionListener(this);
        tan_function.setBackground(new Color(32, 32, 32));
        tan_function.setForeground(Color.WHITE);
        tan_function.setBorder(BorderFactory.createLineBorder(Color.black));        
        // Shift function of tan
        JTextField arctan_function = new JTextField();
        arctan_function.setBounds(252, 287, 38, 12);
        arctan_function.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        arctan_function.setVisible(true);        // determines if the textfield is visible
        arctan_function.setFont(font2);      // determines the font to be used by the textfield
        arctan_function.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        arctan_function.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        arctan_function.setText("arctan");
        arctan_function.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(arctan_function);

        log_function = new JButton("log₍ₐ₎(n)");
        log_function.setBounds(100, 100, 85, 85);
        log_function.addActionListener(this);
        log_function.setBackground(new Color(32, 32, 32));
        log_function.setForeground(Color.WHITE);
        log_function.setBorder(BorderFactory.createLineBorder(Color.black));

        log_ten_function = new JButton("log₁₀(n)");
        log_ten_function.setBounds(100, 100, 85, 85);
        log_ten_function.addActionListener(this);
        log_ten_function.setBackground(new Color(32, 32, 32));
        log_ten_function.setForeground(Color.WHITE);
        log_ten_function.setBorder(BorderFactory.createLineBorder(Color.black));

        ln_function = new JButton("ln(n)");
        ln_function.setBounds(100, 100, 85, 85);
        ln_function.addActionListener(this);
        ln_function.setBackground(new Color(32, 32, 32));
        ln_function.setForeground(Color.WHITE);
        ln_function.setBorder(BorderFactory.createLineBorder(Color.black));        
        // Shift function of ln(n)
        JTextField eulers_number = new JTextField();
        eulers_number.setBounds(21, 287, 38, 12);
        eulers_number.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        eulers_number.setVisible(true);        // determines if the textfield is visible
        eulers_number.setFont(font2);      // determines the font to be used by the textfield
        eulers_number.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        eulers_number.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        eulers_number.setText("eⁿ");
        eulers_number.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(eulers_number);
        

        exponential = new JButton("𝓍ⁿ");
        exponential.setBounds(100, 100, 85, 85);
        exponential.addActionListener(this);
        exponential.setBackground(new Color(32, 32, 32));
        exponential.setForeground(Color.WHITE);
        exponential.setBorder(BorderFactory.createLineBorder(Color.black));

        square_number = new JButton("𝓍²");
        square_number.setBounds(100, 100, 85, 85);
        square_number.addActionListener(this);
        square_number.setBackground(new Color(32, 32, 32));
        square_number.setForeground(Color.WHITE);
        square_number.setBorder(BorderFactory.createLineBorder(Color.black));

        reciprocate_function = new JButton("𝓍⁻¹");
        reciprocate_function.setBounds(100, 100, 85, 85);
        reciprocate_function.addActionListener(this);
        reciprocate_function.setBackground(new Color(32, 32, 32));
        reciprocate_function.setForeground(Color.WHITE);
        reciprocate_function.setBorder(BorderFactory.createLineBorder(Color.black));
        // Shift function of reciprocate function
        JTextField factorial = new JTextField();
        factorial.setBounds(175, 332, 33, 12);
        factorial.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        factorial.setVisible(true);        // determines if the textfield is visible
        factorial.setFont(font2);      // determines the font to be used by the textfield
        factorial.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        factorial.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        factorial.setText("n!");
        factorial.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(factorial);

        square_root_function = new JButton(")");
        square_root_function.setBounds(100, 100, 85, 85);
        square_root_function.addActionListener(this);
        square_root_function.setBackground(Color.BLACK);
        square_root_function.setBorder(BorderFactory.createLineBorder(Color.black));

        calc_function = new JButton("CALC");
        calc_function.setBounds(100, 100, 85, 85);
        calc_function.addActionListener(this);
        calc_function.setBackground(new Color(32, 32, 32));
        calc_function.setForeground(Color.WHITE);
        calc_function.setBorder(BorderFactory.createLineBorder(Color.black));

        panel_2.add(calc_function); panel_2.add(x_variable); panel_2.add(log_function); panel_2.add(log_ten_function); panel_2.add(ln_function); 
        panel_2.add(sin_function); panel_2.add(cos_function); panel_2.add(tan_function); panel_2.add(left_bracket); 
        panel_2.add(right_bracket); panel_2.add(reciprocate_function); panel_2.add(square_number); panel_2.add(exponential);

        panel_3 = new JPanel();
        panel_3.setBounds(20,150,300,70);
        panel_3.setBackground(Color.green);
        panel_3.setLayout(new GridLayout(1, 1));

        left_direction = new JButton("<");
        left_direction.setBounds(100, 100, 85, 85);
        left_direction.addActionListener(this);
        left_direction.setBackground(new Color(192, 192, 192));
        left_direction.setForeground(Color.WHITE);
        left_direction.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        right_direction = new JButton(">");
        right_direction.setBounds(100, 100, 85, 85);
        right_direction.addActionListener(this);
        right_direction.setBackground(new Color(192, 192, 192));
        right_direction.setForeground(Color.WHITE);
        right_direction.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        left_direction = new JButton("<");
        left_direction.setBounds(100, 100, 85, 85);
        left_direction.addActionListener(this);
        left_direction.setBackground(new Color(192, 192, 192));
        left_direction.setForeground(Color.WHITE);
        left_direction.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        right_direction = new JButton(">");
        right_direction.setBounds(100, 100, 85, 85);
        right_direction.addActionListener(this);
        right_direction.setBackground(new Color(192, 192, 192));
        right_direction.setForeground(Color.WHITE);
        right_direction.setBorder(BorderFactory.createLineBorder(Color.black, 5));

        panel_3.add(left_direction); panel_3.add(right_direction);

        frame.add(panel);
        frame.add(panel_2);
        frame.add(panel_3);
        frame.add(textfield1);
        frame.add(textfield2);
        frame.setVisible(true);
        panel.setVisible(true);
         
    }
    

    boolean multiply_tenx_pressed = false;
    boolean is_square_button_pressed = false;
    boolean is_exponential_button_pressed = false;
    int num_of_times_square_button_pressed = 0;
    int num_of_times_exponential_button_pressed = 0;
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < num_buttons.length; i++) {
            if(e.getSource() == num_buttons[i]) {
                int index = textfield1.getCaretPosition();
                System.out.println(index);
                String temp = textfield1.getText();

                // for log of other numbers...
                /*if(temp.length() >= 3 &&  temp.substring(temp.length()-3, temp.length()).equals("log")) {
                    textfield1.setText(temp.concat(String.valueOf(subscript[i])));
                }*/

                // to display a number on the screen  
                if(multiply_tenx_pressed == true) {
                    textfield1.setText(temp.concat(String.valueOf(superscript[i])));
                }
                else {
                    if(index >= temp.length()) {
                        textfield1.setText(temp.substring(0, index) + Integer.toString(i));
                    }
                    else {
                        textfield1.setText(temp.substring(0, index) + Integer.toString(i) + temp.substring(index, temp.length()));
                        textfield1.setCaretPosition(index+1);
                    }
                }
                textfield1.getCaret().setVisible(true);
            }    
        }

        // direction buttons
        if(e.getSource() == left_direction) {
            if(!((textfield1.getText().equals("")) || textfield1.getCaretPosition() == 0)) {
                textfield1.setCaretPosition(textfield1.getCaretPosition()-1);
            }
            // display caret
            textfield1.getCaret().setVisible(true);
        }
        if(e.getSource() == right_direction) {
            if(!((textfield1.getText().equals("")) || textfield1.getCaretPosition() == textfield1.getText().length())) {
                textfield1.setCaretPosition(textfield1.getCaretPosition()+1);
            }
            // display caret
            textfield1.getCaret().setVisible(true);
        }

        if(e.getSource() == dot) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("."));
        }

        if(e.getSource() == add) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            // to display "+" on the screen
            textfield1.setText(textfield1.getText().concat("+"));
            multiply_tenx_pressed = false;
        }

        if(e.getSource() == minus) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            // to display "+" on the screen
            textfield1.setText(textfield1.getText().concat("-"));
            multiply_tenx_pressed = false;
        }

        if(e.getSource() == multiply) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            // to display "x" on the screen
            textfield1.setText(textfield1.getText().concat("x"));
            multiply_tenx_pressed = false;
        }

        if(e.getSource() == divide) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            // to display "x" on the screen
            textfield1.setText(textfield1.getText().concat("÷"));
            multiply_tenx_pressed = false;
        }

        if(e.getSource() == del) {
            textfield1.setText(textfield1.getText().substring(0, textfield1.getText().length()-1));
            textfield2.setText("");
            num_operations = 0;
        }

        if(e.getSource() == ac) {
            textfield1.setText("");
            textfield2.setText("");
            num_operations = 0;
            multiply_tenx_pressed = false;
            num_of_times_square_button_pressed = 0;
        }

        if(e.getSource() == left_bracket) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("("));
        }

        if(e.getSource() == right_bracket) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat(")"));
        }

        if(e.getSource() == reciprocate_function) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("()⁻¹"));
            textfield1.getCaret().setVisible(true);
        }

        if(e.getSource() == multiply_tenx) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            multiply_tenx_pressed = true;
            textfield1.setText(textfield1.getText().concat("x10⁽⁾"));
        }

        if(e.getSource() == square_number) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            String temp = textfield1.getText();
            if((temp.equals("") || !(Arrays.asList(numbers).contains(temp.substring(temp.length()-1, temp.length())))) && !(temp.equals("ANS"))) {
                textfield1.setText(textfield1.getText().concat("()²"));
            }
            else textfield1.setText(textfield1.getText().concat("²"));
            textfield1.getCaret().setVisible(true);
            is_square_button_pressed = true;
            num_of_times_square_button_pressed += 1;
            
        }

        if(e.getSource() == exponential) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            String temp = textfield1.getText();
            if(temp.equals("") || !(Arrays.asList(numbers).contains(temp.substring(temp.length()-1, temp.length())))) {
                textfield1.setText(textfield1.getText().concat("()⁽⁾"));
            }
            else textfield1.setText(textfield1.getText().concat("⁽⁾"));
            textfield1.getCaret().setVisible(true);
            is_exponential_button_pressed = true;
            num_of_times_exponential_button_pressed += 1;
            
        }

        if(e.getSource() == sin_function) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("sin("));
            textfield1.getCaret().setVisible(true);
        }

        if(e.getSource() == cos_function) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("cos("));
            textfield1.getCaret().setVisible(true);
        }

        if(e.getSource() == tan_function) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("tan("));
            textfield1.getCaret().setVisible(true);
        }

        if(e.getSource() == arctan_function) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("arctan("));
            textfield1.getCaret().setVisible(true);
        }

        if(e.getSource() == log_function) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("log₍₎()"));
        }

        if(e.getSource() == log_ten_function) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("log₁₀("));
        }

        if(e.getSource() == equalto) {
            String text = textfield1.getText();
            ArrayList<String> problem = new ArrayList<String>(text.length());
            int t = 0;

            // Trigonometric functions
            if(text.contains("sin(")) {
                int n = text.indexOf("sin(");
                String num = "";
                int i = 0;
                while(n+5+i < text.length() && text.substring(n+4+i, n+5+i) != ")") {
                    num += text.substring(n+4+i, n+5+i);
                    i++;   
                }
                final DecimalFormat df = new DecimalFormat("0.00000");
                String temp = String.valueOf(df.format(functions.sin(Double.valueOf(num))));
                text = text.replace(text.substring(n,n+5+i), temp);
            }
            if(text.contains("cos(")) {
                int n = text.indexOf("cos(");
                String num = "";
                int i = 0;
                while(n+5+i < text.length() && text.substring(n+4+i, n+5+i) != ")") {
                    num += text.substring(n+4+i, n+5+i);
                    i++;   
                }
                final DecimalFormat df = new DecimalFormat("0.00000");
                String temp = String.valueOf(df.format(functions.cos(Double.valueOf(num))));
                text = text.replace(text.substring(n,n+5+i), temp);
            }
            if(text.contains("arctan(")) {
                int n = text.indexOf("arctan(");
                String temp = "";
                String num = "";
                int i = 0;
                while(n+8+i < text.length() && text.substring(n+7+i, n+8+i) != ")") {
                    num += text.substring(n+7+i, n+8+i);
                    i++;   
                }
                final DecimalFormat df = new DecimalFormat("0.00000");
                if(Double.valueOf(num) < 0){
                    temp = "-";
                }
                temp += String.valueOf(df.format(functions.arctan(Double.valueOf(num))));
                text = text.replace(text.substring(n,n+8+i), temp);
            }
            if(text.contains("tan(") ) {
                int n = text.indexOf("tan(");
                String num = "";
                int i = 0;
                while(n+5+i < text.length() && text.substring(n+4+i, n+5+i) != ")") {
                    num += text.substring(n+4+i, n+5+i);
                    i++;   
                }
                final DecimalFormat df = new DecimalFormat("0.00000");
                String temp = String.valueOf(df.format(functions.tan(Double.valueOf(num))));
                text = text.replace(text.substring(n,n+5+i), temp);
            }

            //reciprocate function
            while(text.contains(")⁻¹")) {
                int j = text.indexOf(")⁻¹");
                int n = j;
                String number = "";
                while(!(text.substring(n-1, n).equals("("))) {
                    n--;
                }
                number = text.substring(n, j);
                String temp = String.valueOf(functions.reciprocal(Double.valueOf(number)));
                text = text.replace(text.substring(n-1, number.length()+n+3), temp);
            } 

            // square nuber 
            while(is_square_button_pressed == true && num_of_times_square_button_pressed != 0) {
                if(text.contains("ANS²")) {
                    int o = text.indexOf("ANS²");
                    text = text.replace(text.substring(o, o+3), String.valueOf(result));
                }
                int j = text.indexOf("²");
                int n = j;
                // dot is causing some issues here
                if(Arrays.asList(numbers).contains(text.substring(n-1, n)) && !(Arrays.asList(superscript).contains(text.substring(n-1))) || Arrays.asList(superscript).contains(text.substring(n+1))){
                    String number = "";
                    while(n != 0 && Arrays.asList(numbers).contains(text.substring(n-1, n))) {
                        n--;
                    }
                    number = text.substring(n, j);
                    String temp = String.valueOf(functions.square(Double.valueOf(number)));
                    text = text.replaceFirst(text.substring(n, number.length()+n+1), temp);
                    num_of_times_square_button_pressed--;
                } 
                if(text.contains(")²")) {
                    j = text.indexOf(")²");
                    n = j;
                    String number = "";
                    while(!(text.substring(n-1, n).equals("("))) {
                        n--;
                    }
                    number = text.substring(n, j);
                    String temp = String.valueOf(functions.square(Double.valueOf(number)));
                    // issue here when problem is duplicated
                    text = text.replaceFirst(text.substring(n-1, number.length()+n+2), temp);
                    num_of_times_square_button_pressed--;
                }
            }

            // Exponentiation
            while(is_exponential_button_pressed == true && num_of_times_exponential_button_pressed != 0) {
                int j = text.indexOf("⁽");
                int n = j;
                int p = j;
                if(Arrays.asList(numbers).contains(text.substring(n-1, n)) && !(Arrays.asList(superscript).contains(text.substring(n-1)) || Arrays.asList(superscript).contains(text.substring(n+1)))){
                    String number = "";
                    while(n != 0 && Arrays.asList(numbers).contains(text.substring(n-1, n))) {
                        n--;
                    }
                    while(p != 0 && Arrays.asList(superscript).contains(text.substring(p+1, p+2))) {
                        p++;
                    }
                    number = text.substring(n, j);
                    String exponent = text.substring(j+1, p+1);
                    String temp = String.valueOf(functions.power(Double.valueOf(number), Double.valueOf(exponent)));
                    text = text.replaceFirst(text.substring(n, p+2), temp);
                    num_of_times_exponential_button_pressed--;
                } 
                if(text.contains(")⁽")) {
                    j = text.indexOf(")⁽");
                    n = j;
                    String number = "";
                    while(!(text.substring(n-1, n).equals("("))) {
                        n--;
                    }
                    number = text.substring(n, j);
                    String temp = String.valueOf(functions.square(Double.valueOf(number)));
                    // issue here when problem is duplicated
                    text = text.replaceFirst(text.substring(n-1, number.length()+n+2), temp);
                    num_of_times_exponential_button_pressed--;
                }
            }


            if(text.contains("-")) {
                // Include more operators later
                if(text.indexOf("-") != 0 && !(text.substring(text.indexOf("-")-1, text.indexOf("-")).equals("x"))) {
                    text = text.replaceAll("-", "+-"); // to allow negative numbers to be distinct from positive numbers
                }
            }    
            if(text.contains("x10") && multiply_tenx_pressed == true) {
                int n = text.indexOf("x10");
                if(!(text.substring(n-1, n).equals("x"))) {
                    text = text.replace("x10", "xx10");
                }
            }
        
            // For loop to add each number and operator into an array to distinguish different operations(BODMAS)
            for(int i = 0; i < text.length(); i++) {
                if(text.charAt(i) == '÷' || text.charAt(i) == 'x' || text.charAt(i) == '+' || i == text.length()-1) {
                    if(i == text.length()-1) {
                        problem.add(text.substring(t, i+1));
                    }
                    else if(i != t) problem.add(text.substring(t, i));
                    if(text.charAt(i) == 'x') {
                        problem.add("x");
                        if(multiply_tenx_pressed == true && text.substring(i+1, i+4).equals("x10")) {
                            String temp_num = ""; 
                            while(!( i+4 >= text.length() || text.charAt(i+4) == '÷' || text.charAt(i+4) == 'x' || text.charAt(i+4) == '+')) {
                                temp_num += String.valueOf(new String(superscript).indexOf(text.charAt(i+4)));
                                i +=1;
                            }
                            if(temp_num == "") temp_num = "1";
                            temp_num = String.valueOf(Math.pow(10, Float.valueOf(temp_num)));
                            problem.add(temp_num);
                            i += 3;
                        }
                    }
                    if(text.charAt(i) == '÷') {
                        problem.add("÷");
                    }
                    if(text.charAt(i) == '+') {
                        problem.add("+");
                    }
                    t = i+1;
                }
            }

            // IF statement to check if there's no operator in the problem given
            if(!(problem.contains("÷") || problem.contains("+") || problem.contains("x"))) {
                // Set result to the number in the problem
                result = Double.valueOf(problem.get(0));
            }

            // While loop to check if division is required to take place in the operation
            while(problem.contains("÷")) {
                int z = problem.indexOf("÷");
                if(problem.get(0).equals("ANS")) {
                    prev_result = result;
                    problem.set(0, String.valueOf(prev_result));
                }
                result = Double.valueOf(problem.get(z-1)) / Double.valueOf(problem.get(z+1));
                problem.set(z-1, Double.toString(result));
                problem.remove(z); problem.remove(z);
            }

            // While loop to check if multiplication is required to take place in the operation
            while(problem.contains("x")) {
                int z = problem.indexOf("x");
                if(problem.get(0).equals("ANS")) {
                    prev_result = result;
                    problem.set(0, String.valueOf(prev_result));
                }
                result = Double.valueOf(problem.get(z-1)) * Double.valueOf(problem.get(z+1));
                problem.set(z-1, Double.toString(result));
                problem.remove(z); problem.remove(z);
            }

            // While loop to check if addition is required to take place in the operation
            while(problem.contains("+")) {
                int z = problem.indexOf("+");
                if(problem.get(0).equals("ANS")) {
                    prev_result = result;
                    problem.set(0, String.valueOf(prev_result));
                }
                result = Double.valueOf(problem.get(z-1)) + Double.valueOf(problem.get(z+1));
                problem.set(z-1, Double.toString(result));
                problem.remove(z); problem.remove(z);
            }
            
            num_operations = 1;
            String result_in_string = Double.toString(result);
            if(result_in_string.contains("E")) {
                int temp_location = result_in_string.indexOf("E");
                String temp_exponential = result_in_string.substring(temp_location+1, result_in_string.length());
                for(int i = 0; i < temp_exponential.length(); i++) {
                    String j = temp_exponential.substring(i, i+1);
                    temp_exponential = temp_exponential.replaceFirst(j, String.valueOf(superscript[Integer.valueOf(String.valueOf(j))]));
                }
                result_in_string = result_in_string.substring(0, temp_location+1);
                result_in_string += temp_exponential;
                result_in_string = result_in_string.replace("E", "x10");
            }
            textfield2.setText(result_in_string);
            multiply_tenx_pressed = false;
            textfield1.getCaret().setVisible(false);
        }
    }
}

