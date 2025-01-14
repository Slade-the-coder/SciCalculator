import java.util.ArrayList;

class functions extends CalculatorGUI{
    
    public static int log(double base, double num) {
        int i = 0;
        while(num != 1) {
            if(num < base) {
                num = num*base;
                i--;
            }
            else {
                num = num/base;
                i++;
            } 
        }
        return(i);
    }

    public static double tentothex(double num) {
        double x = 1;
        if(num >= 0) {
            for(int i = 0; i < num; i++){
                x = 10*x;
            }
        }
        else {
            for(double i = num; i < 0; i++){
                x = 0.1*x;
            }
        }
        return x;
    }

    public static double sin(double angle) {
        while(angle < -360 || angle > 360){
            if(angle < 0){
                angle += 360;
            }
            else angle -= 360;
        }
        angle = angle*(3.1415926535/180);
        double result = 0;
        for(int i = 0; i < 15; i++) {
            double numerator = Math.pow(-1, i);
            numerator *= Math.pow(angle, 2*i+1);
            double denominator = factorial(2*i+1);
            result += numerator/denominator;
        }
        return result;
    }

    public static double arcsin(double value) {
        double angle = 0;
        for(int i = 0; i < 50; i++) {
            double numerator = factorial(2*i);
            numerator *= Math.pow(value, 2*i + 1);
            double denominator = Math.pow(4, i)*Math.pow(factorial(i), 2)*(2*i+1);
            angle += numerator/denominator;
        }
        return angle;
    }

    public static double cos(double angle) {
        while(angle < -180 || angle > 180){
            if(angle < 0){
                angle += 360;
            }
            else angle -= 360;
        }
        angle = angle*(3.1415926535/180);
        double result = 0;
        for(int i = 0; i < 15; i++) {
            double numerator = Math.pow(-1, i);
            numerator *= Math.pow(angle, 2*i);
            double denominator = functions.factorial(2*i);
            result += numerator/denominator;
        }
        return result;
    }

    public static double tan(double angle) {
        double result = 0;
        if(angle >= 180 || angle <= 0) {
            while(angle <= 0) {
                angle += 180;
            }
            while(angle >= 180) {
                angle -= 180;
            }
        }
        if(angle > 90) {
            result = -tan(90-(angle - 90));
            return result;
        }
        if(angle > 45) {
            result = 1/tan(90-angle);
            return result;
        }
        if(angle > 22.5) {
            result = (2*tan(angle/2))/(1-(tan(angle/2)*tan(angle/2)));
            return result;
        }
        angle = angle*(3.1415926535/180);
        result = angle + ((angle*angle*angle)/3) + ((2*angle*angle*angle*angle*angle)/15) + ((17*angle*angle*angle*angle*angle*angle*angle)/315);
        return result;
    }

    public static double arctan(double value) {
        double result = 0;
        if(value < 0){
            value = value*-1;
        }
        if(value > 1){
           result =  (3.1415926535/2) - arctan(1/value);
           if(value <= 0.267949) return result;
        }
        if(value > 0.267949){
            result = (3.1415926535/6) + arctan((1.73205*value-1)/(1.73205+value));
            return result;
        }
        result = value - ((value*value*value)/3) + ((value*value*value*value*value)/5);
        
        return result;
    }

    /*public static double log(double value) {
        double result = 0;
        double temp = value;
        double multiplier = 0;
        if(temp < 1 || temp >= 10) {
            while(temp < 1 || temp >= 10) {
                temp /= 10;
                multiplier++;
            }
            result = log(temp) + multiplier;
            return result;
        }
        if(temp < 1 || temp >= 3.16227766) {
            result = 2*log(Math.sqrt(temp));
            return result;
        }
        result = m
        
        return result;
    } */

    public static double factorial(int number) {
        double result = 1;
        for(int i = number; i > 1; i--) {
            result *= i;
        }
        return result;
    }

    public static double reciprocal(double number) {
        return 1/number;
    }

    public static double square(double number) {
        return number*number;
    }

    public static double power(double number, double exponent) {
        return number*number;
    }

    public static double integration(double a, double b) {
        double ans = 0;
        double x;
        // ans = ((b-a)/6)*(())
        return ans;
    }

    public static String basic(String temp_text, ArrayList<String> problem) {
        int t = 0;
        if(temp_text.contains("-")) {
            // Include more operators later
            if(temp_text.indexOf("-") != 0 && !(temp_text.substring(temp_text.indexOf("-")-1, temp_text.indexOf("-")).equals("x"))) {
                temp_text = temp_text.replaceAll("-", "+-");        // to allow negative numbers to be distinct from positive numbers
            }
        }    
        if(temp_text.contains("x10") && multiply_ten_to_n_pressed == true) {
            int n = temp_text.indexOf("x10");
            if(!(temp_text.substring(n-1, n).equals("x"))) {
                temp_text = temp_text.replace("x10", "xx10");
            }
        }
    
        // For loop to add each number and operator into an array to distinguish different operations(BODMAS)
        for(int i = 0; i < temp_text.length(); i++) {
            if(temp_text.charAt(i) == '÷' || temp_text.charAt(i) == 'x' || temp_text.charAt(i) == '+' || i == temp_text.length()-1) {
                if(i == temp_text.length()-1) {
                    problem.add(temp_text.substring(t, i+1));
                }
                else if(i != t) problem.add(temp_text.substring(t, i));
                if(temp_text.charAt(i) == 'x') {
                    problem.add("x");
                    if(multiply_ten_to_n_pressed == true && temp_text.substring(i+1, i+4).equals("x10")) {
                        String temp_num = ""; 
                        while(!( i+4 >= temp_text.length() || temp_text.charAt(i+4) == '÷' || temp_text.charAt(i+4) == 'x' || temp_text.charAt(i+4) == '+')) {
                            temp_num += String.valueOf(new String(superscript).indexOf(temp_text.charAt(i+4)));
                            i +=1;
                        }
                        if(temp_num == "") temp_num = "1";
                        temp_num = String.valueOf(Math.pow(10, Float.valueOf(temp_num)));
                        problem.add(temp_num);
                        i += 3;
                    }
                }
                if(temp_text.charAt(i) == '÷') {
                    problem.add("÷");
                }
                if(temp_text.charAt(i) == '+') {
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
        
        //
        num_operations = 1;
        String result_in_string = Double.toString(result);
        if(result_in_string.contains("E")) {
            int temp_location = result_in_string.indexOf("E");
            if(result_in_string.substring(temp_location+1, temp_location+2).equals("-")) {
                result_in_string = result_in_string.replace("E-", "E⁻");
                temp_location++;
            }
            String temp_exponential = result_in_string.substring(temp_location+1, result_in_string.length());

            for(int i = 0; i < temp_exponential.length(); i++) {
                String j = temp_exponential.substring(i, i+1);
                temp_exponential = temp_exponential.replaceFirst(j, String.valueOf(superscript[Integer.valueOf(String.valueOf(j))]));
            }
            result_in_string = result_in_string.substring(0, temp_location+1);
            result_in_string += temp_exponential;
            result_in_string = result_in_string.replace("E", "x10");
        }
        multiply_ten_to_n_pressed = false;
        textfield1.getCaret().setVisible(false);
        problem.clear();
        return result_in_string;
    }
}