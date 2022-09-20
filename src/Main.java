import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        String buffer = "";

        try{
            int symbol = 0;
            while((symbol = System.in.read()) != (int)'\n'){
                buffer = buffer + (char)symbol;
            }
            System.out.println(calc(buffer));
        }catch (Exception ex){
            System.out.println(ex);
        }

    }
    public static String calc(String input) throws Exception{

        char operator = '?';
        int operatorPos = -1;
        String firstOperand = "";
        String secondOperand = "";

        int firstInt = 0;
        int secondInt = 0;
        int result = 0;

        boolean romanFirst = false;
        boolean arabicFirst = false;
        boolean romanSecond = false;
        boolean arabicSecond = false;

        if(checkOperator('+', input))operator = '+';
        if(checkOperator('-', input)){if(operator == '?'){operator = '-';}else{throw new Exception("Более одного оператора");}}
        if(checkOperator('*', input)){if(operator == '?'){operator = '*';}else{throw new Exception("Более одного оператора");}}
        if(checkOperator('/', input)){if(operator == '?'){operator = '/';}else{throw new Exception("Более одного оператора");}}

        if(operator == '?')throw new Exception("Не установлен оператор");

        operatorPos = input.indexOf(operator);
        firstOperand = input.substring(0, operatorPos).trim();
        secondOperand = input.substring(operatorPos + 1).trim();

        try {
            firstInt = Integer.parseInt(firstOperand);
            arabicFirst = true;
        }catch(Exception ex) {

        }

        try {
            firstInt = makeArabic(firstOperand);
            romanFirst = true;
        }catch(Exception ex) {

        }

        try {
            secondInt = Integer.parseInt(secondOperand);
            arabicSecond = true;
        }catch(Exception ex) {

        }

        try {
            secondInt = makeArabic(secondOperand);
            romanSecond = true;
        }catch(Exception ex) {

        }

        if((arabicFirst && romanSecond) || (romanFirst && arabicSecond))throw new Exception("Операнды разного счисления");
        if(firstInt < 1 || secondInt < 1 || firstInt > 10 || secondInt > 10)throw new Exception("Операнды вне диапазона от 1 до 10");

        switch (operator){
            case '+': result = firstInt + secondInt; break;
            case '-': result = firstInt - secondInt; break;
            case '*': result = firstInt * secondInt; break;
            case '/': result = firstInt / secondInt; break;
        }

        if(romanFirst && romanSecond)return makeRoman(result);
        return "" + result;

    }

    private static boolean checkOperator(char operator, String str) throws Exception {
        int operatorPos = -1;
        operatorPos = str.indexOf(operator);
        if(operatorPos != str.lastIndexOf(operator))throw new Exception("Более одного оператора");
        return operatorPos != -1 ? true : false;
    }

    private static String makeRoman(int arabic) throws Exception{

        if (arabic < 1)throw new Exception("Римское число меньше 1");

        int buffer = 0;
        String romanNum = "";

        buffer = arabic / 10;
        if(buffer == 10){
            romanNum = "C";
        } else if (buffer == 9) {
            romanNum = "XC";
        } else if (buffer >= 5) {
            romanNum = "L" + "X".repeat(buffer - 5);
        } else if (buffer == 4) {
            romanNum = "XL";
        }else{
            romanNum = "X".repeat(buffer);
        }

        buffer = arabic % 10;
        if(buffer == 9){
            romanNum = romanNum + "IX";
        } else if (buffer >= 5) {
            romanNum = romanNum + "V" + "I".repeat(buffer - 5);
        } else if (buffer == 4) {
            romanNum = romanNum + "IV";
        }else{
            romanNum = romanNum + "I".repeat(buffer);
        }

        return romanNum;

    }

    private static int makeArabic(String roman) throws Exception{
        switch (roman){
            case "I": return 1;
            case "II": return 2;
            case "III": return 3;
            case "IV": return 4;
            case "V": return 5;
            case "VI": return 6;
            case "VII": return 7;
            case "VIII": return 8;
            case "IX": return 9;
            case "X": return 10;
        }
        throw new Exception("Римское число не из диапазона от 1 до 10 или не существует");
    }

}