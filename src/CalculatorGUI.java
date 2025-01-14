import javax.swing.*;       // Swing is the library from which the GUI will be built upon
import javax.swing.border.Border;       // The Border around objects in Swing

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;          
import java.util.Arrays;
import java.util.regex.Pattern;
import java.text.DecimalFormat;     // To round a number to a certain number of decimal places

public class CalculatorGUI implements java.awt.event.ActionListener {

    JFrame frame;       // To create a main window where components like labels, buttons, textfields are added to create a GUI
	static JTextField textfield1;      // Areas where text is displayed on the GUI
    JTextField textfield2;
    JTextField textfield3;
    JPanel panel, panel_2, panel_3;     // To provide a container class to attach components like JButton
    JButton zero, one, two, three, four, five, six, seven, eight, nine, 
            add, divide, multiply, minus, del, ac, equalto, dot, multiply_ten_to_n, 
            ans_button, left_bracket, right_bracket, x_variable, square_root, sin_function, 
            cos_function, tan_function, arctan_function, arcsin_function, 
            arccos_function, sto_button, log_function, log_ten_function, ln_function, factorial, exponential, square_number,
            reciprocate_function, square_root_function, abs, calc_function, shift, alpha, left_direction, right_direction, menu, setupButton; // To create buttons by which the user can interact with the GUI
    JButton num_buttons[] = {zero, one, two, three, four, five, six, seven, eight, nine};   
    static char[] superscript = {'⁰', '¹', '²', '³', '⁴', '⁵', '⁶', '⁷', '⁸', '⁹'};
    char[] subscript = {'₀', '₁', '₂', '₃', '₄', '₅', '₆', '₇', '₈', '₉'};
    String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
    static int num_operations = 0;     // keeps track of the number of operations on the program
    static Double result = 0d;     // keeps the value of the result of the most recent calculation          
    static Double prev_result = 0d;        // assigned when dealing with "ANS"
    String trig_x;      // To keep track on what type of x-value is being used by the trigonometric functions

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

        Font font1 = new Font("Calibri", Font.BOLD, 20);
        Font font2 = new Font("Calibri", Font.BOLD, 12);
 
        // First TextField, to display input entered by the user through the buttons
        textfield1 = new JTextField();  
        textfield1.setBounds(25, 45, 300, 30);
        textfield1.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        textfield1.setVisible(true);        // determines if the textfield is visible 
        textfield1.setFont(font1);      // determines the font to be used by the textfield
        textfield1.setBackground(Color.WHITE);      // sets the background of the textfield to be white
        textfield1.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.GRAY));      // Determine the size and colour of the border
        textfield1.getCaret().setVisible(true);     // set the caret of the textfield to be visible

        // Second TextField, to display the solution to the problem entered into the calculator
        textfield2 = new JTextField();
        textfield2.setBounds(25, 110, 300, 33);
        textfield2.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        textfield2.setVisible(true);        // determines if the textfield is visible
        textfield2.setFont(font1);      // determines the font to be used by the textfield
        textfield2.setBackground(Color.WHITE);      // sets the background of the textfield to be white
        textfield2.setBorder(BorderFactory.createMatteBorder(0, 10, 10, 10, Color.GRAY));      // Determine the size and colour of the border
        textfield2.setHorizontalAlignment(JTextField.RIGHT);        // Sets the alignment of the text on the textfield to the right

        // Third TextField, to display indications as to if the shift button has been clicked or what type of 𝓍-value is being used by the trigonometric functions
        textfield3 = new JTextField();      // assigning a variable to a new object of JTextField
        textfield3.setBounds(25, 33, 300, 12);      // Determines the dimensions of the textfield and where it will be on the Contentpane 
        textfield3.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        textfield3.setVisible(true);        // determines if the textfield is visible
        textfield3.setFont(font2);      // determines the font to be used by the textfield
        textfield3.setBackground(Color.GRAY);      // sets the background of the textfield to be gray
        textfield3.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.GRAY));      // Determine the size and colour of the border
        textfield3.setForeground(Color.BLACK);
        frame.add(textfield3);      // Adds the textfield to the Contentpane

        // Fourth TextField, just used as an empty white space to block the black space between the first TextField and the second TextField
        JTextField textfield4 = new JTextField();       // assigning a variable to a new object of JTextField
        textfield4.setBounds(25, 75, 300, 35);      // Determines the dimensions of the textfield and where it will be on the Contentpane 
        textfield4.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        textfield4.setVisible(true);        // determines if the textfield is visible
        textfield4.setFont(font1);      // determines the font to be used by the textfield
        textfield4.setBackground(Color.WHITE);      // sets the background of the textfield to be white
        textfield4.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 10, Color.GRAY));      // Determine the size and colour of the border
        frame.add(textfield4);      // Adds the textfield to the Contentpane

        panel = new JPanel();       // assigning a variable to a new object of JPanel
        panel.setBounds(20,450,300,170);        // Determines the dimensions of the panel and where it will be on the Contentpane(window content)
        panel.setBackground(Color.black);       // Sets the background of the panel to black
        panel.setLayout(new GridLayout(4, 5, 10, 13));      // Determines the layout of the panel and the space between buttons

        for(int i = 0; i < num_buttons.length; i++){
            num_buttons[i] = new JButton(Integer.toString(i));      // assigning a variable to a new object of JButton
            num_buttons[i].setBounds(0, 100, 85, 50);       // Determines the dimensions of the button and where it will be on the Contentpane
            num_buttons[i].addActionListener(this);     // Adds an actionlistener to the button which monitors when the button is clicked
            num_buttons[i].setBackground(Color.WHITE);      // Sets the colour of the button to white
            num_buttons[i].setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));     // Determines the border of the buttons, it's dimensions and colour
        }
        // Shift function of number 7
        JTextField constants = new JTextField();
        constants.setBounds(28, 438, 37, 12);       // // Determines the dimensions of the textfield and where it will be on the Contentpane(window content) 
        constants.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        constants.setVisible(true);        // determines if the textfield is visible
        constants.setFont(font2);      // determines the font to be used by the textfield
        constants.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        constants.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));      // Determine the size and colour of the border
        constants.setText("CONST");     // Sets the text of the textfield
        constants.setForeground(Color.getHSBColor(41, 26, 64));     // Sets the colour of the foreground, which is the colour of the text              
        frame.add(constants);       // Adds the textfield to the frame(Contentpane or window content)

        add = new JButton("+");     // assigns the variable to a new object of JButton with the text "+" as it's title
        add.setBounds(100, 100, 85, 50);        // Determines the dimensions of the button and where it will be on the Contentpane          
        add.addActionListener(this);     // Adds an actionlistener to the button which monitors when the button is clicked    
        add.setBackground(Color.WHITE);      // Sets the colour of the button to white
        add.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));     // Determines the border of the buttons, it's dimensions and colour

        divide = new JButton("÷");      // assigns the variable to a new object of JButton with the text "÷" as it's title
        divide.setBounds(100, 100, 85, 50);     // Determines the dimensions of the button and where it will be on the Contentpane
        divide.addActionListener(this);     // Adds an actionlistener to the button which monitors when the button is clicked
        divide.setBackground(Color.WHITE);    // Sets the colour of the button to white  
        divide.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));     // Determines the border of the buttons, it's dimensions and colour
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

        minus = new JButton("-");       // assigns the variable to a new object of JButton with the text "-" as it's title
        minus.setBounds(100, 100, 85, 50);      // Determines the dimensions of the button and where it will be on the Contentpane      
        minus.addActionListener(this);      // Adds an actionlistener to the button which monitors when the button is clicked
        minus.setBackground(Color.WHITE);       // Sets the colour of the button to white
        minus.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));      // Determines the border of the buttons, it's dimensions and colour

        multiply = new JButton("x");        // assigns the variable to a new object of JButton with the text "x" as it's title
        multiply.setBounds(100, 100, 85, 50);       // Determines the dimensions of the button and where it will be on the Contentpane
        multiply.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked
        multiply.setBackground(Color.WHITE);        // Sets the colour of the button to white
        multiply.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));       // Determines the border of the buttons, it's dimensions and colour
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

        equalto = new JButton("=");     // assigns the variable to a new object of JButton with the text "=" as it's title
        equalto.setBounds(100, 100, 85, 50);        // Determines the dimensions of the button and where it will be on the Contentpane
        equalto.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        equalto.setBackground(Color.WHITE);     // Sets the colour of the button to white
        equalto.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));        // Creates a Border around the button 

        del = new JButton("DEL");       // assigns the variable to a new object of JButton with the text "DEL" as it's title
        del.setBounds(100, 100, 85, 50);        // Determines the dimensions of the button and where it will be on the Contentpane
        del.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        del.setBackground(new Color(70, 90, 177));      // Sets the colour of the button to specific variant of blue
        del.setForeground(Color.WHITE);     // Set the colour of the text on the button to white     
        del.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));        // Creates a Border around the button

        ac = new JButton("AC");     // assigns the variable to a new object of JButton with the text "AC" as it's title
        ac.setBounds(100, 100, 85, 50);     // Determines the dimensions of the button and where it will be on the Contentpane
        ac.addActionListener(this);     // Adds an actionlistener to the button which monitors when the button is clicked
        ac.setBackground(new Color(70, 90, 177));       // Sets the colour of the button to specific variant of blue
        ac.setForeground(Color.WHITE);      // Set the colour of the text on the button to white
        ac.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));     // Creates a Border around the button

        dot = new JButton(".");     // assigns the variable to a new object of JButton with the text "." as it's title
        dot.setBounds(100, 100, 85, 50);        // Determines the dimensions of the button and where it will be on the Contentpane
        dot.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        dot.setBackground(Color.WHITE);     // Set the colour of the text on the button to white
        dot.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));        // Creates a Border around the button

        multiply_ten_to_n = new JButton("x10ⁿ");        // assigns the variable to a new object of JButton with the text "x10ⁿ" as it's title
        multiply_ten_to_n.setBounds(100, 100, 85, 50);      // Determines the dimensions of the button and where it will be on the Contentpane
        multiply_ten_to_n.addActionListener(this);      // Adds an actionlistener to the button which monitors when the button is clicked
        multiply_ten_to_n.setBackground(Color.WHITE);       // Set the colour of the text on the button to white
        multiply_ten_to_n.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));      // Creates a Border around the button
        // Shift function of multiply tenx button
        JTextField pie_symbol = new JTextField();
        pie_symbol.setBounds(145, 574, 33, 12);     // Determines the dimensions of the textfield and where it will be on the Contentpane
        pie_symbol.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        pie_symbol.setVisible(true);        // determines if the textfield is visible
        pie_symbol.setFont(font2);      // determines the font to be used by the textfield
        pie_symbol.setBackground(Color.BLACK);      // sets the background of the textfield to be black 
        pie_symbol.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        pie_symbol.setText("π");
        pie_symbol.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(pie_symbol);

        ans_button = new JButton("ANS");        // assigns the variable to a new object of JButton with the text "x10ⁿ" as it's title
        ans_button.setBounds(100, 100, 85, 50);     // Determines the dimensions of the button and where it will be on the Contentpane
        ans_button.addActionListener(this);     // Adds an actionlistener to the button which monitors when the button is clicked
        ans_button.setBackground(Color.WHITE);      // Set the colour of the text on the button to white
        ans_button.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));     // Creates a Border around the button
        // Shift function of ans button
        JTextField percentage_symbol = new JTextField();
        percentage_symbol.setBounds(227, 574, 33, 12);
        percentage_symbol.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        percentage_symbol.setVisible(true);        // determines if the textfield is visible
        percentage_symbol.setFont(font2);      // determines the font to be used by the textfield
        percentage_symbol.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        percentage_symbol.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        percentage_symbol.setText("%");
        percentage_symbol.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(percentage_symbol);

        // Add the buttons to the first panel
        panel.add(num_buttons[7]); panel.add(num_buttons[8]); panel.add(num_buttons[9]); panel.add(del); panel.add(ac); 
        panel.add(num_buttons[4]); panel.add(num_buttons[5]); panel.add(num_buttons[6]); panel.add(multiply); panel.add(divide);
        panel.add(num_buttons[1]); panel.add(num_buttons[2]); panel.add(num_buttons[3]); panel.add(add); panel.add(minus); 
        panel.add(num_buttons[0]); panel.add(dot); panel.add(multiply_ten_to_n); panel.add(ans_button); panel.add(equalto);

        panel_2 = new JPanel();     // assigning a variable to a new object of JPanel
        panel_2.setBounds(20,253,300,170);      // Determines the dimensions of the panel and where it will be on the Contentpane(window content)
        panel_2.setBackground(Color.black);     // Sets the background of the panel to black     
        panel_2.setLayout(new GridLayout(4, 4, 10, 10));        // Determines the layout of the panel and the space between buttons
        
        left_bracket = new JButton("(");        // assigns the variable to a new object of JButton with the text "(" as it's title
        left_bracket.setBounds(100, 100, 85, 85);       // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        left_bracket.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked
        left_bracket.setBackground(new Color(32, 32, 32));      // Sets the colour of the button to specific variant of black grey
        left_bracket.setForeground(Color.WHITE);        // Set the colour of the text on the button to white           
        left_bracket.setBorder(BorderFactory.createLineBorder(Color.black));        // Creates a black Border around the button

        right_bracket = new JButton(")");       // assigns the variable to a new object of JButton with the text ")" as it's title
        right_bracket.setBounds(100, 100, 85, 85);      // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        right_bracket.addActionListener(this);      // Adds an actionlistener to the button which monitors when the button is clicked
        right_bracket.setBackground(new Color(32, 32, 32));     // Sets the colour of the button to specific variant of black grey     
        right_bracket.setForeground(Color.WHITE);       // Set the colour of the text on the button to white       
        right_bracket.setBorder(BorderFactory.createLineBorder(Color.black));       // Creates a black Border around the button

        x_variable = new JButton("𝓍");      // assigns the variable to a new object of JButton with the text "𝓍" as it's title
        x_variable.setBounds(100, 100, 85, 85);     // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        x_variable.addActionListener(this);     // Adds an actionlistener to the button which monitors when the button is clicked
        x_variable.setBackground(new Color(32, 32, 32));        // Sets the colour of the button to specific variant of black grey
        x_variable.setForeground(Color.WHITE);      // Set the colour of the text on the button to white 
        x_variable.setBorder(BorderFactory.createLineBorder(Color.black));      // Creates a black Border around the button

        square_root = new JButton("√n");        // assigns the variable to a new object of JButton with the text "√n" as it's title
        square_root.setBounds(100, 100, 85, 85);         // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        square_root.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        square_root.setBackground(new Color(32, 32, 32));       // Sets the colour of the button to specific variant of black grey
        square_root.setForeground(Color.WHITE);     // Set the colour of the text on the button to white
        square_root.setBorder(BorderFactory.createLineBorder(Color.black));     // Creates a black Border around the button
        // Shift function of square root
        JTextField cube_root = new JTextField();
        cube_root.setBounds(175, 240, 33, 12);      // assigns the JTextField variable to a new object of JTextField
        cube_root.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        cube_root.setVisible(true);        // determines if the textfield is visible
        cube_root.setFont(font2);      // determines the font to be used by the textfield
        cube_root.setBackground(Color.BLACK);      // sets the background of the textfield to be white
        cube_root.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));      // Creates a black Border around the textfield
        cube_root.setText("³√n");       // Sets the textfield to have the text "³√n"
        cube_root.setForeground(Color.getHSBColor(41, 26, 64));     // Sets the colour of the text in the textfield to yellow     
        frame.add(cube_root);       // textfield is added to the Contentpane(window content)

        // To indicate the trigonometric default 𝓍-value is in degrees
        trig_x = "D"; 
        textfield3.setText("         "+trig_x);

        sin_function = new JButton("sin");      // assigns the variable to a new object of JButton with the text "sin" as it's title
        sin_function.setBounds(100, 100, 85, 85);       // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        sin_function.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked       
        sin_function.setBackground(new Color(32, 32, 32));      // Sets the colour of the button to specific variant of black grey
        sin_function.setForeground(Color.WHITE);        // Set the colour of the text on the button to white
        sin_function.setBorder(BorderFactory.createLineBorder(Color.black));        // Creates a black Border around the button
        // Shift function of sin
        JTextField arcsin_function = new JTextField();
        arcsin_function.setBounds(98, 287, 38, 12);
        arcsin_function.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        arcsin_function.setVisible(true);        // determines if the textfield is visible
        arcsin_function.setFont(font2);      // determines the font to be used by the textfield
        arcsin_function.setBackground(Color.BLACK);      // sets the background of the textfield to be white
        Border border_2 = BorderFactory.createLineBorder(Color.BLACK); 
        arcsin_function.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        arcsin_function.setText("arcsin");
        arcsin_function.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(arcsin_function);

        cos_function = new JButton("cos");      // assigns the variable to a new object of JButton with the text "cos" as it's title
        cos_function.setBounds(100, 100, 85, 85);       // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        cos_function.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked       
        cos_function.setBackground(new Color(32, 32, 32));      // Sets the colour of the button to specific variant of black grey
        cos_function.setForeground(Color.WHITE);        // Set the colour of the text on the button to white
        cos_function.setBorder(BorderFactory.createLineBorder(Color.black));        // Creates a black Border around the button
        // Shift function of cos
        JTextField arccos_function = new JTextField();
        arccos_function.setBounds(175, 287, 43, 12);
        arccos_function.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        arccos_function.setVisible(true);        // determines if the textfield is visible
        arccos_function.setFont(font2);      // determines the font to be used by the textfield
        arccos_function.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        arccos_function.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        arccos_function.setText("arccos");
        arccos_function.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(arccos_function);
        

        tan_function = new JButton("tan");      // assigns the variable to a new object of JButton with the text "tan" as it's title
        tan_function.setBounds(100, 100, 85, 85);       // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        tan_function.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked
        tan_function.setBackground(new Color(32, 32, 32));      // Sets the colour of the button to specific variant of black grey
        tan_function.setForeground(Color.WHITE);        // Set the colour of the text on the button to white
        tan_function.setBorder(BorderFactory.createLineBorder(Color.black));        // Creates a black Border around the button            
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

        sto_button = new JButton("STO");        // assigns the variable to a new object of JButton with the text "STO" as it's title
        sto_button.setBounds(100, 100, 85, 85);     // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        sto_button.addActionListener(this);     // Adds an actionlistener to the button which monitors when the button is clicked
        sto_button.setBackground(new Color(32, 32, 32));        // Sets the colour of the button to specific variant of black grey
        sto_button.setForeground(Color.WHITE);      // Set the colour of the text on the button to white
        sto_button.setBorder(BorderFactory.createLineBorder(Color.black));      // Creates a black Border around the button       

        log_function = new JButton("log₍ₐ₎(n)");        // assigns the variable to a new object of JButton with the text "log₍ₐ₎(n)" as it's title
        log_function.setBounds(100, 100, 85, 85);       // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        log_function.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked       
        log_function.setBackground(new Color(32, 32, 32));      // Sets the colour of the button to specific variant of black grey
        log_function.setForeground(Color.WHITE);        // Set the colour of the text on the button to white        
        log_function.setBorder(BorderFactory.createLineBorder(Color.black));        // Creates a black Border around the button

        log_ten_function = new JButton("log₁₀(n)");     // assigns the variable to a new object of JButton with the text "log₁₀(n)" as it's title
        log_ten_function.setBounds(100, 100, 85, 85);       // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        log_ten_function.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked
        log_ten_function.setBackground(new Color(32, 32, 32));      // Sets the colour of the button to specific variant of black grey      
        log_ten_function.setForeground(Color.WHITE);        // Set the colour of the text on the button to white           
        log_ten_function.setBorder(BorderFactory.createLineBorder(Color.black));        // Creates a black Border around the button        
        // Shift function of log₁₀(n)
        JTextField ten_raised_n = new JTextField();
        ten_raised_n.setBounds(252, 240, 38, 12);
        ten_raised_n.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        ten_raised_n.setVisible(true);        // determines if the textfield is visible
        ten_raised_n.setFont(font2);      // determines the font to be used by the textfield
        ten_raised_n.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        ten_raised_n.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        ten_raised_n.setText("10ⁿ");
        ten_raised_n.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(ten_raised_n);

        ln_function = new JButton("ln(n)");     // assigns the variable to a new object of JButton with the text "ln(n)" as it's title
        ln_function.setBounds(100, 100, 85, 85);        // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        ln_function.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        ln_function.setBackground(new Color(32, 32, 32));       // Sets the colour of the button to specific variant of black grey
        ln_function.setForeground(Color.WHITE);     // Set the colour of the text on the button to white
        ln_function.setBorder(BorderFactory.createLineBorder(Color.black));     // Creates a black Border around the button             
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
        

        exponential = new JButton("𝓍ⁿ");        // assigns the variable to a new object of JButton with the text "𝓍ⁿ" as it's title
        exponential.setBounds(100, 100, 85, 85);        // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        exponential.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        exponential.setBackground(new Color(32, 32, 32));       // Sets the colour of the button to specific variant of black grey
        exponential.setForeground(Color.WHITE);     // Set the colour of the text on the button to white     
        exponential.setBorder(BorderFactory.createLineBorder(Color.black));     // Creates a black Border around the button

        square_number = new JButton("𝓍²");      // assigns the variable to a new object of JButton with the text "𝓍²" as it's title
        square_number.setBounds(100, 100, 85, 85);      // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        square_number.addActionListener(this);      // Adds an actionlistener to the button which monitors when the button is clicked
        square_number.setBackground(new Color(32, 32, 32));     // Sets the colour of the button to specific variant of black grey
        square_number.setForeground(Color.WHITE);       // Set the colour of the text on the button to white       
        square_number.setBorder(BorderFactory.createLineBorder(Color.black));       // Creates a black Border around the button

        reciprocate_function = new JButton("𝓍⁻¹");      // assigns the variable to a new object of JButton with the text "𝓍⁻¹" as it's title
        reciprocate_function.setBounds(100, 100, 85, 85);       // Determines the dimensions of the button and where it will be on the Contentpane(window content)       
        reciprocate_function.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked
        reciprocate_function.setBackground(new Color(32, 32, 32));      // Sets the colour of the button to specific variant of black grey
        reciprocate_function.setForeground(Color.WHITE);        // Set the colour of the text on the button to white
        reciprocate_function.setBorder(BorderFactory.createLineBorder(Color.black));        // Creates a black Border around the button
        // Shift function of reciprocate function
        JTextField factorial = new JTextField();
        factorial.setBounds(21, 379, 33, 11);
        factorial.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        factorial.setVisible(true);        // determines if the textfield is visible
        factorial.setFont(font2);      // determines the font to be used by the textfield
        factorial.setBackground(Color.BLACK);      // sets the background of the textfield to be white 
        factorial.setBorder(BorderFactory.createCompoundBorder(border_2, BorderFactory.createEmptyBorder(0, 0, 0, 0)));
        factorial.setText("n!");
        factorial.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(factorial);

        calc_function = new JButton("CALC");        // assigns the variable to a new object of JButton with the text "CALC" as it's title
        calc_function.setBounds(100, 100, 85, 85);      // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        calc_function.addActionListener(this);      // Adds an actionlistener to the button which monitors when the button is clicked
        calc_function.setBackground(new Color(32, 32, 32));     // Sets the colour of the button to specific variant of black grey
        calc_function.setForeground(Color.WHITE);       // Set the colour of the text on the button to white
        calc_function.setBorder(BorderFactory.createLineBorder(Color.black));       // Creates a black Border around the button
        // Shift function of CALC
        JTextField solve_x = new JTextField();
        solve_x.setBounds(23, 242, 37, 12);
        solve_x.setEditable(false);      // determines if the user can edit the contents of the textfield with the use of a physical or on-screen keyboard
        solve_x.setVisible(true);        // determines if the textfield is visible
        solve_x.setFont(font2);      // determines the font to be used by the textfield
        solve_x.setBackground(Color.BLACK);      // sets the background of the textfield to be white
        solve_x.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 0, Color.BLACK));
        solve_x.setText("SOLVE");
        solve_x.setForeground(Color.getHSBColor(41, 26, 64));
        frame.add(solve_x);

        abs = new JButton("abs");       // assigns the variable to a new object of JButton with the text "abs" as it's title
        abs.setBounds(100, 100, 85, 85);        // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        abs.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        abs.setBackground(new Color(32, 32, 32));       // Sets the colour of the button to specific variant of black grey
        abs.setForeground(Color.WHITE);     // Set the colour of the text on the button to white
        abs.setBorder(BorderFactory.createLineBorder(Color.black));     // Creates a black Border around the button 

        // Add buttons to the second panel
        panel_2.add(calc_function); panel_2.add(x_variable); panel_2.add(square_root); panel_2.add(log_ten_function); panel_2.add(ln_function);  
        panel_2.add(sin_function); panel_2.add(cos_function); panel_2.add(tan_function); panel_2.add(log_function); panel_2.add(left_bracket); 
        panel_2.add(right_bracket); panel_2.add(sto_button); panel_2.add(reciprocate_function); panel_2.add(square_number); panel_2.add(exponential); panel_2.add(abs);

        panel_3 = new JPanel();     // assigning a variable to a new object of JPanel
        panel_3.setBounds(20,150,300,60);       // Determines the dimensions of the panel and where it will be on the Contentpane(window content)
        panel_3.setBackground(Color.black);     // Sets the background of the panel to black
        panel_3.setLayout(new GridLayout(1, 1));        // Determines the layout of the panel

        Font font3 = new Font("Arial", Font.BOLD, 24);

        left_direction = new JButton();     // assigns the variable to a new object of JButton
        left_direction.setFont(font3);      // sets the font of the text on the button
        left_direction.setText("<");        // sets the text "<" as the title of the button, so as to make the text bold
        left_direction.setBounds(100, 100, 85, 85);     // Determines the dimensions of the button and where it will be on the Contentpane(window content)     
        left_direction.addActionListener(this);     // Adds an actionlistener to the button which monitors when the button is clicked
        left_direction.setBackground(new Color(192, 192, 192));     // Sets the colour of the button to silver
        left_direction.setForeground(Color.WHITE);      // Set the colour of the text on the button to white
        left_direction.setBorder(BorderFactory.createLineBorder(Color.black, 5));       // Creates a black Border around the button 

        right_direction = new JButton();        // assigns the variable to a new object of JButton
        right_direction.setFont(font3);     // sets the font of the text on the button, so as to make the text bold
        right_direction.setText(">");       // sets the text ">" as the title of the button   
        right_direction.setBounds(100, 100, 85, 85);        // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        right_direction.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        right_direction.setBackground(new Color(192, 192, 192));        // Sets the colour of the button to silver
        right_direction.setForeground(Color.WHITE);     // Set the colour of the text on the button to white
        right_direction.setBorder(BorderFactory.createLineBorder(Color.black, 5));      // Creates a black Border around the button

        shift = new JButton("shift");       // assigns the variable to a new object of JButton with the text "shift" as it's title
        shift.setBounds(100, 100, 85, 85);      // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        shift.addActionListener(this);      // Adds an actionlistener to the button which monitors when the button is clicked
        shift.setBackground(new Color(192, 192, 192));      // Sets the colour of the button to silver      
        shift.setForeground(Color.getHSBColor(41, 26, 64));       // Set the colour of the text on the button to yellow
        shift.setBorder(BorderFactory.createLineBorder(Color.black, 5));        // Creates a black Border around the button        

        alpha = new JButton("alpha");       // assigns the variable to a new object of JButton with the text "alpha" as it's title
        alpha.setBounds(100, 100, 85, 85);      // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        alpha.addActionListener(this);      // Adds an actionlistener to the button which monitors when the button is clicked
        alpha.setBackground(new Color(192, 192, 192));      // Sets the colour of the button to silver 
        alpha.setForeground(Color.WHITE);       // Set the colour of the text on the button to white
        alpha.setBorder(BorderFactory.createLineBorder(Color.black, 5));        // Creates a black Border around the button        
        
        menu = new JButton("menu");     // assigns the variable to a new object of JButton with the text "menu" as it's title
        menu.setBounds(100, 100, 85, 85);       // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        menu.addActionListener(this);       // Adds an actionlistener to the button which monitors when the button is clicked
        menu.setBackground(new Color(192, 192, 192));       // Sets the colour of the button to silver
        menu.setForeground(Color.WHITE);        // Set the colour of the text on the button to white
        menu.setBorder(BorderFactory.createLineBorder(Color.black, 5));     // Creates a black Border around the button

        setupButton = new JButton("setup");     // assigns the variable to a new object of JButton with the text "menu" as it's title
        setupButton.setBounds(100, 100, 85, 85);        // Determines the dimensions of the button and where it will be on the Contentpane(window content)
        setupButton.addActionListener(this);        // Adds an actionlistener to the button which monitors when the button is clicked
        setupButton.setBackground(new Color(192, 192, 192));        // Sets the colour of the button to silver
        setupButton.setForeground(Color.WHITE);     // Set the colour of the text on the button to white
        setupButton.setBorder(BorderFactory.createLineBorder(Color.black, 5));      // Creates a black Border around the button

        // Add buttons to the second panel
        panel_3.add(shift); panel_3.add(alpha);panel_3.add(left_direction); panel_3.add(right_direction); panel_3.add(menu); panel_3.add(setupButton);

        // Add the panels to the frame 
        frame.add(panel); frame.add(panel_2); frame.add(panel_3);

        // Add the textfields to the frame
        frame.add(textfield1); frame.add(textfield2);

        // Set the frame to be visible
        frame.setVisible(true);

        // Set the panel to be visible
        panel.setVisible(true);
         
    }
    

    static boolean multiply_ten_to_n_pressed = false;
    boolean is_square_button_pressed = false;
    boolean is_exponential_button_pressed = false;      
    int num_of_times_square_button_pressed = 0;
    int num_of_times_exponential_button_pressed = 0;    
    boolean is_shift_pressed = false;
    String list_of_numbers = "0123456789";
    String list_of_numbers_and_operators = "0123456789+x÷-";
    
    @Override
    public void actionPerformed(ActionEvent e) {
        for(int i = 0; i < num_buttons.length; i++) {
            if(e.getSource() == num_buttons[i]) {       // Checks what number button has been clicked
                int index = textfield1.getCaretPosition();      // sets a variable to hold the value of the position where the caret is on the textfield
                String temp = textfield1.getText();     // sets a variable to hold the current text in the textfield

                // for log of other numbers...
                /*if(temp.length() >= 3 &&  temp.substring(temp.length()-3, temp.length()).equals("log")) {
                    textfield1.setText(temp.concat(String.valueOf(subscript[i])));
                }*/

                // to display a number on the screen  
                if(multiply_ten_to_n_pressed == true) {     // Checks if the "x10ⁿ" button has been clicked
                    textfield1.setText(temp.concat(String.valueOf(superscript[i])));        // add superscript numbers to the textfield
                }
                else {
                    if(index >= temp.length()) {        // Checks if the position of the caret is greater that the length of the current text in the textfield
                        textfield1.setText(temp.substring(0, index) + Integer.toString(i));     // Sets textfield to equal the current text in the textfield concatenated with the number clicked
                    }
                    else {
                        textfield1.setText(temp.substring(0, index) + Integer.toString(i) + temp.substring(index, temp.length()));      // Puts the number clicked in between the text in the textfield, where the caret is positioned
                        textfield1.setCaretPosition(index+1);       // Increments the position of the caret by one
                    }
                }
                textfield1.getCaret().setVisible(true);     // Sets the caret of the textfield to be visible
            }    
        }

        // direction buttons
        if(e.getSource() == left_direction) {       // Checks if the left direction button has been clicked
            if(!((textfield1.getText().equals("")) || textfield1.getCaretPosition() == 0)) {        // Checks if the textfield is not empty or the current caret position is 0
                textfield1.setCaretPosition(textfield1.getCaretPosition()-1);       // Set the position of the caret to one place to the left on the textfield
            }
            // display caret
            textfield1.getCaret().setVisible(true);     // Sets the caret to be visible
            textfield2.setText("");     // Sets the text in the solution textfield is be empty
        }
        if(e.getSource() == right_direction) {      // Checks if the right direction button has been clicked
            if(!((textfield1.getText().equals("")) || textfield1.getCaretPosition() == textfield1.getText().length())) {        // Checks if the textfield is not empty or the current caret position is the end of the text
                textfield1.setCaretPosition(textfield1.getCaretPosition()+1);       // Set the position of the caret to one place to the right on the textfield
            }
            // display caret
            textfield1.getCaret().setVisible(true);     // Sets the caret to be visible
            textfield2.setText("");     // Sets the text in the solution textfield is be empty
        }

        //shift
        if(e.getSource() == shift) {        // Checks if the shift button has been clicked                
            if(is_shift_pressed == false) {     // Checks if the shift button has been clicked previously
                textfield3.setText("   S     "+trig_x);     // Adds "   S     " to the textfield at a certain position
                is_shift_pressed = true;        // sets the variable to hold a true boolean value
            }
            else {
                textfield3.setText("         "+trig_x);     // Adds "         " to the textfield at a certain position     
                is_shift_pressed = false;       // sets the variable to hold a true boolean value
            }
        }

        if(e.getSource() == dot) {      // Checks if the dot button has been clicked      
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText("");        // Sets textfield1 to be empty
                textfield2.setText("");         // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one
            }
            textfield1.setText(textfield1.getText().concat("."));       // Adds "." to textfield1(also the textfield where the problem is written)
        }

        if(e.getSource() == add) {      // Checks if the add button has been clicked
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS"
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one
            }
            // to display "+" on the screen
            textfield1.setText(textfield1.getText().concat("+"));       // Adds the addition symbol "+" to textfield1
            multiply_ten_to_n_pressed = false;      // Sets the variable to hold a false boolean value
        }

        if(e.getSource() == minus) {        // Checks if the minus button has been clicked
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS"
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one
            }
            // to display "-" on the screen
            textfield1.setText(textfield1.getText().concat("-"));       // Adds the addition symbol "-" to textfield1
            multiply_ten_to_n_pressed = false;      // Sets the variable to hold a false boolean value 
        }

        if(e.getSource() == multiply && is_shift_pressed == false) {        // Checks if the minus button has been clicked and the shift button is false    
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText("ANS");     // Sets textfield1 to have text "ANS"
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one
            }
            // to display "x" on the screen
            textfield1.setText(textfield1.getText().concat("x"));       // Adds the addition symbol "x" to textfield1
            multiply_ten_to_n_pressed = false;      // Sets the variable to hold a false boolean value
        }
        if(e.getSource() == multiply && is_shift_pressed == true) {     // Checks if the minus button has been clicked and the shift button is true                  
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS"
                textfield2.setText("");     // Sets textfield2 to "" to make it empty     
                num_operations ++;      // Increments the number in the variable by one
            }
            // to display "P" on the screen
            textfield1.setText(textfield1.getText().concat("P"));       // Adds the addition symbol "P" to textfield1
            multiply_ten_to_n_pressed = false;      // Sets the variable to hold a false boolean value
            is_shift_pressed = false;       // Sets the variable to hold a false boolean value
        }

        if(e.getSource() == divide && is_shift_pressed == false) {      // Checks if the divide button has been clicked and the shift button is false      
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS"
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one      
            }
            // to display "÷" on the screen
            textfield1.setText(textfield1.getText().concat("÷"));       // Adds the addition symbol "÷" to textfield1
            multiply_ten_to_n_pressed = false;      // Sets the variable to hold a false boolean value
        }
        if(e.getSource() == divide && is_shift_pressed == true) {       // Checks if the divide button has been clicked and the shift button is true
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS"
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one
            }
            // to display "C" on the screen
            textfield1.setText(textfield1.getText().concat("C"));       // Adds the addition symbol "C" to textfield1
            multiply_ten_to_n_pressed = false;      // Sets the variable to hold a false boolean value
            is_shift_pressed = false;       // Sets the variable to hold a false boolean value
        }

        if(e.getSource() == del) {      // Checks if the del button has been clicked
            int temp = textfield1.getCaretPosition();       // stores the position of the caret on the textfield in the variable
            String usertext = textfield1.getText();     // stores the text inputted by the user into a variable
            textfield1.setText(usertext.substring(0, temp-1)+usertext.substring(temp, usertext.length()));      // Sets the text in the textfield with a character deleted
            textfield1.setCaretPosition(temp-1);        // Sets the position of the caret
            textfield2.setText("");     // Sets textfield2 to "" to make it empty
            num_operations = 0;     // Changes the value of the number of operations to zero
        }

        if(e.getSource() == ac) {       // Checks if the AC button has been clicked
            textfield1.setText("");     // Sets textfield1 to "" to make it empty 
            textfield2.setText("");     // Sets textfield2 to "" to make it empty
            num_operations = 0;     // Changes the value of the number of operations to zero 
            multiply_ten_to_n_pressed = false;      // Sets the boolean value of the variable to false
            num_of_times_square_button_pressed = 0;     // Sets the number of times the square button is pressed to zero
        }

        if(e.getSource() == ans_button && is_shift_pressed == false) {      // Checks if the ANS button has been clicked and if the shift button has been clicked
            textfield1.setText(textfield1.getText().concat("ANS"));     // Adds "ANS" to textfield1(which where the user input is displayed)
        }
        if(e.getSource() == ans_button && is_shift_pressed == true) {       // Checks if the ANS button has been clicked and if the shift button has been clicked
            textfield1.setText(textfield1.getText().concat("%"));       // Adds "ANS" to textfield1(which where the user input is displayed)
            is_shift_pressed = false;       // sets the boolean value of the variable for when the shift button is clicked, to false
        }

        if(e.getSource() == left_bracket) {     // Checks if the left bracket has been clicked
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1       
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS"     
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one
            }
            textfield1.setText(textfield1.getText().concat("("));       // Adds the left bracket symbol "(" to the textfield where user input is displayed(textfield1)
        }

        if(e.getSource() == right_bracket) {        // Checks if the right bracket has been clicked
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS" 
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one
            }
            textfield1.setText(textfield1.getText().concat(")"));       // Adds the left bracket symbol ")" to the textfield where user input is displayed(textxfield1)
        }

        if(e.getSource() == reciprocate_function) {     // Checks if the reciprocate button has been clicked
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS"      
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one
            }
            textfield1.setText(textfield1.getText().concat("()⁻¹"));        // Adds "()⁻¹" to the textfield where user input is displayed(textxfield1)
            textfield1.getCaret().setVisible(true);     // Sets the caret to be visible
        }

        if(e.getSource() == multiply_ten_to_n && is_shift_pressed == false) {       // Checks if the x10² button has been clicked
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {       // Checks if both the textfield1 and textfield2 have texts in them at the current moment and if the number of operations is 1
                textfield1.setText( "ANS");     // Sets textfield1 to have text "ANS" 
                textfield2.setText("");     // Sets textfield2 to "" to make it empty
                num_operations ++;      // Increments the number in the variable by one      
            }
            multiply_ten_to_n_pressed = true;       
            textfield1.setText(textfield1.getText().concat("x10⁽⁾"));
        }
        if(e.getSource() == multiply_ten_to_n && is_shift_pressed == true) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            multiply_ten_to_n_pressed = true;
            // if()        // add "x"
            textfield1.setText(textfield1.getText().concat("π"));
            is_shift_pressed = false;
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

        if(e.getSource() == sin_function && is_shift_pressed == false) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("sin("));
            textfield1.getCaret().setVisible(true);
        }
        if(e.getSource() ==  sin_function && is_shift_pressed == true) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("arcsin("));
            textfield1.getCaret().setVisible(true);
            is_shift_pressed = false;
        }

        if(e.getSource() == cos_function && is_shift_pressed == false) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("cos("));
            textfield1.getCaret().setVisible(true);
        }
        if(e.getSource() ==  cos_function && is_shift_pressed == true) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("arccos("));
            textfield1.getCaret().setVisible(true);
            is_shift_pressed = false;
        }

        if(e.getSource() == tan_function && is_shift_pressed == false) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("tan("));
            textfield1.getCaret().setVisible(true);
        }
        if(e.getSource() ==  tan_function && is_shift_pressed == true) {
            if(!(textfield1.getText().equals("")) && !(textfield2.getText().equals("")) && num_operations == 1) {
                textfield1.setText( "ANS");
                textfield2.setText("");
                num_operations ++;
            }
            textfield1.setText(textfield1.getText().concat("arctan("));
            textfield1.getCaret().setVisible(true);
            is_shift_pressed = false;
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
            textfield1.setText(textfield1.getText()+"log₁₀(");
        }

        // add square root here then natural log

        if(e.getSource() == equalto) {
            String text = textfield1.getText();
            ArrayList<String> problem = new ArrayList<String>(text.length());

            // change pie symbol to the pie constant
            text = text.replace("π","3.1415926535");

            // change percentage symbol in order to compute percentage
            text = text.replace("%","÷100");

            // Trigonometric functions
            if(text.contains("arcsin(") ) {
                int n = text.indexOf("arcsin(");
                String num = "";
                int i = 0;
                while(n+8+i < text.length() && text.substring(n+7+i, n+8+i) != ")") {
                    num += text.substring(n+7+i, n+8+i);
                    i++;   
                }
                final DecimalFormat df = new DecimalFormat("0.00000");
                String temp = String.valueOf(df.format(functions.arcsin(Double.valueOf(num))));
                if(trig_x.equals("D")){
                    temp = String.valueOf(Double.valueOf(temp) * (180/3.1415926535));
                }
                text = text.replace(text.substring(n,n+8+i), temp);
            }
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
            if(text.contains("arccos(") ) {
                int n = text.indexOf("arccos(");
                String num = "";
                int i = 0;
                while(n+8+i < text.length() && text.substring(n+7+i, n+8+i) != ")") {
                    num += text.substring(n+7+i, n+8+i);
                    i++;   
                }
                final DecimalFormat df = new DecimalFormat("0.00000");
                String temp = String.valueOf(df.format((3.1415926535/2)-functions.arcsin(Double.valueOf(num))));
                if(trig_x.equals("D")){
                    temp = String.valueOf(Double.valueOf(temp) * (180/3.1415926535));
                }
                text = text.replace(text.substring(n,n+8+i), temp);
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
                final DecimalFormat df = new DecimalFormat("0.0000");
                if(Double.valueOf(num) < 0){
                    temp = "-";
                }
                temp += String.valueOf(df.format(functions.arctan(Double.valueOf(num))));
                if(trig_x.equals("D")){
                    temp = String.valueOf(df.format(Double.valueOf(temp) * (180/3.1415926535)));
                }
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

            // Permutation
            int temp_num = text.indexOf("P");
            while(text.contains("P") && text.length() != 0 && list_of_numbers.contains(text.substring(temp_num-1, temp_num)) && list_of_numbers.contains(text.substring(temp_num+1, temp_num+2))) {
                int n = Integer.valueOf(text.substring(temp_num-1, temp_num));
                int r = Integer.valueOf(text.substring(temp_num+1, temp_num+2));
                double result = functions.factorial(n)/functions.factorial(n-r);
            } 

            // Bracket
            while(text.contains("(") && text.contains(")") && (text.indexOf("(") == 0 || list_of_numbers_and_operators.contains(text.substring(text.indexOf("(")-1, text.indexOf("("))))) {
                if(text.indexOf("(") != 0) {
                    String str_temp = text.substring(text.indexOf("(")-1, text.indexOf("("));
                    if(list_of_numbers.contains(str_temp)){
                        text = text.replaceFirst(str_temp, str_temp+"x");
                    }
                }
                int temp_1 = text.indexOf("(");
                int temp_2 = text.indexOf(")");
                String temp_problem = text.substring(temp_1+1, temp_2);
                text = text.replaceFirst("[(]"+Pattern.quote(temp_problem)+"[)]", functions.basic(temp_problem, problem));

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

            // square number 
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

            textfield2.setText(functions.basic(text, problem));
            

        }
        if(is_shift_pressed == false){
            textfield3.setText("         "+trig_x);
        }
    }
}

