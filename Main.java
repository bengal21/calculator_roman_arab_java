import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    static char operator;
    static int firstDigit, secondDigit, resultSumArab, resultSumRoman;
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println
                ("Введите два числа и один оператор (+, -, /, *): ");
        String userInput = scanner.nextLine();
        int lenthOfInput = userInput.length();
        char[] operatorChar = new char[lenthOfInput];
        for (int i = 0; i < lenthOfInput; i++) {
            operatorChar[i] = userInput.charAt(i);
            if (operatorChar[i] == '+') {
                operator = '+';
            }
            if (operatorChar[i] == '-') {
                operator = '-';
            }
            if (operatorChar[i] == '*') {
                operator = '*';
            }
            if (operatorChar[i] == '/') {
                operator = '/';
            }

        }
        String[] operatorsArray = {"+", "-", "*", "/"};
        String op = Character.toString(operator);
        boolean validOperator = Arrays.asList(operatorsArray).contains(op);
        if (validOperator != true) throw new IllegalArgumentException
                ("Формат математической операции не удовлетворяет заданию" +
                        " - два операнда и один оператор (+, -, /, *)");

        String oper_charString = String.valueOf(operatorChar);
        String[] arrayOfInputWoutOper = oper_charString.split("[+-/*]");
        if (arrayOfInputWoutOper.length > 2) throw new IllegalArgumentException
                ("Формат математической операции не удовлетворяет заданию" +
                        " - два операнда и один оператор (+, -, /, *)");
        String arabOrRomanFirst = arrayOfInputWoutOper[0].trim();
        String arabOrRomanSecond = arrayOfInputWoutOper[1].trim();
        String[] RomanDigits = {"O", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X", "XI", "XII", "XIII", "XIV", "XV", "XVI", "XVII", "XVIII", "XIX", "XX",
                "XXI", "XXII", "XXIII", "XXIV", "XXV", "XXVI", "XXVII", "XXVIII", "XXIX", "XXX", "XXXI", "XXXII", "XXXIII", "XXXIV", "XXXV", "XXXVI", "XXXVII", "XXXVIII", "XXXIX", "XL",
                "XLI", "XLII", "XLIII", "XLIV", "XLV", "XLVI", "XLVII", "XLVIII", "XLIX", "L", "LI", "LII", "LIII", "LIV", "LV", "LVI", "LVII", "LVIII", "LIX", "LX",
                "LXI", "LXII", "LXIII", "LXIV", "LXV", "LXVI", "LXVII", "LXVIII", "LXIX", "LXX",
                "LXXI", "LXXII", "LXXIII", "LXXIV", "LXXV", "LXXVI", "LXXVII", "LXXVIII", "LXXIX", "LXXX",
                "LXXXI", "LXXXII", "LXXXIII", "LXXXIV", "LXXXV", "LXXXVI", "LXXXVII", "LXXXVIII", "LXXXIX", "XC",
                "XCI", "XCII", "XCIII", "XCIV", "XCV", "XCVI", "XCVII", "XCVIII", "XCIX", "C"};

        boolean validDigitOne = Arrays.asList(RomanDigits).contains(arabOrRomanFirst);
        boolean validDigitTwo = Arrays.asList(RomanDigits).contains(arabOrRomanSecond);
        if (validDigitOne ^ validDigitTwo)
            {throw new NumberFormatException("Используются одновременно разные системы счисления!");
        }

        if (validDigitOne){
            firstDigit = Arrays.asList(RomanDigits).indexOf(arabOrRomanFirst);
            if (firstDigit > 10) throw new IllegalArgumentException("Вводимое число должно быть не больше X!");
        }

        if (validDigitTwo){
            secondDigit = Arrays.asList(RomanDigits).indexOf(arabOrRomanSecond);
            if (secondDigit > 10) throw new IllegalArgumentException("Вводимое число должно быть не больше X!");
        }
// конечный вывод римских
        if (validDigitOne & validDigitTwo){
            resultSumRoman = calc(firstDigit, secondDigit, operator);
            if (resultSumRoman < 0) throw new IllegalArgumentException("В римской системе нет отрицательных чисел!");
            System.out.println(RomanDigits[resultSumRoman]);
// конечный вывод римских
        }
// конечный вывод арабских
        else {
            int arabFirst = Integer.parseInt(arabOrRomanFirst);
            int arabSecond = Integer.parseInt(arabOrRomanSecond);
            resultSumArab = calc(arabFirst, arabSecond, operator);
            System.out.println(resultSumArab);
// конечный вывод арабских
        }
    }
    public static int calc(int digitOne, int digitTwo, char operatorMath) {
        if (digitOne > 10 | digitTwo > 10){throw new IllegalArgumentException("Вводимое число должно быть не больше 10!");}
        int result = 0;
        switch (operatorMath) {
            case '+':
                result = digitOne + digitTwo;
                break;
            case '-':
                result = digitOne - digitTwo;
                break;
            case '*':
                result = digitOne * digitTwo;
                break;
            case '/':
                try {
                    result = digitOne / digitTwo;
                } catch (ArithmeticException | InputMismatchException e) {
                    System.out.println("Exception : " + e);
                    System.out.println("Ответ возможен только в целых числах!");
                    break;
                }
                break;
        }
        return result;
    }
}