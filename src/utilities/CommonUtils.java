package utilities;

import java.util.Scanner;

/**
 * Created by WataruS on 10/22/2016.
 */
public class CommonUtils {
    public static boolean isNumber(String string) {
        try {
            Integer.parseInt(string);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    //this method will check whether user input anything before pressing enter
    public static String validateInputString(Scanner sc) {
        boolean isValid = false;
        String text = sc.nextLine();
        do {
            if ((text.trim().length()) > 0){
                isValid = true;
            }
            else {
                System.out.println("You've just inputted white spaces or just pressed Enter only.");
                System.out.print("Please input again: ");
                text = sc.nextLine();
            }
        }while (!isValid);
        return  text;
    }

    public static String validateInputPhoneNumber(Scanner sc){
        boolean isValid = false;
        String number = validateInputString(sc);
        do {
            if (((number.trim().length()) == 10) || ((number.trim().length()) == 11)){
                isValid = true;
            }
            else {
                System.out.println("Phone number should be 10 or 11 digits long.");
                System.out.print("Please input again: ");
                number = validateInputString(sc);
            }
        }while (!isValid);
        return  number;
    }

    public static int validateInputNumber(Scanner sc){
        boolean isValid = false;
        String number = validateInputString(sc);
        do {
            if (isNumber(number)){
                isValid = true;
            }
            else {
                System.out.println("Please input a number");
                System.out.print("Input again: ");
                number = validateInputString(sc);
            }
        }while (!isValid);
        return Integer.parseInt(number);
    }


    public static Boolean validateString(String text) {
        return  (text.trim().length() > 0 );
    }


}
