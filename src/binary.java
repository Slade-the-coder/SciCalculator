public class binary  extends CalculatorGUI{
    public static void main(String[] args) {
        double num = 2;
        System.out.println(getbinary(num));
    }

    public static String getbinary(double num) {
        String binary_num = "";
        double i = 0;
        boolean good = true;
        if(num == 0) {
            binary_num = "0";
            return binary_num;
        }
        while(good) {
            i++;
            if(Math.pow(2, i) > num) {
                i = i-1;
                good = false;
            }
        }
        while(num > 0) {
            if(i == -1) {
                binary_num += ".";
            }
            if(num >= Math.pow(2, i)) {
                num -= Math.pow(2, i);
                binary_num += "1";
            }
            else binary_num += "0";
            if(num == 0 && i > 0) {
                while(i != 0) {
                    binary_num += "0";
                    i--;
                }
            }
            i--;
        }
        return binary_num;
    }
}
