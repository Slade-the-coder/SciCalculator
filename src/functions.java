class functions{
    
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
        for(int i = 0; i < 15; i++){
            double numerator = Math.pow(-1, i);
            numerator *= Math.pow(angle, 2*i+1);
            double denominator = functions.factorial(2*i+1);
            result += numerator/denominator;
        }
        return result;
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

    public static double integration(double a, double b) {
        double ans = 0;
        double x;
        // ans = ((b-a)/6)*(())
        return ans;
    }
}