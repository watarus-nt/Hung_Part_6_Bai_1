package main;

import business.TMAEmployeeManager;
import utilities.CommonUtils;
import utilities.JsonHandlers;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {
	// write your code here

        JsonHandlers jh = new JsonHandlers("employees.json");
        Scanner sc = new Scanner(System.in);

        System.out.println("How many employees you want to generate?");
        System.out.print("Input a number here: ");
        int number = CommonUtils.validateInputNumber(sc);


        TMAEmployeeManager tmaEmployeeManager = new TMAEmployeeManager(number);

        tmaEmployeeManager.writeEmployeesToJson(jh);
    }
}
