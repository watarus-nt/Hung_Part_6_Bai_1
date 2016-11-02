package main;

import business.TMAEmployeeManager;
import model.TMAEmployee;
import utilities.CommonUtils;
import utilities.JsonHandlers;

import java.io.*;
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

        TMAEmployee e1 = new TMAEmployee();

        System.out.println("*****************Employee e1*****************");
        System.out.println(e1.toJson().build());
        System.out.println("");

        System.out.println("*****************The second employee in json file*****************");
        jh.getEmployeeFromJson();
        TMAEmployee e2 = tmaEmployeeManager.getTmaEmployeeList().get(1);
        System.out.println(jh.get(e2));
        System.out.println("");

        jh.add(e1);

        jh.remove(e1);

        System.out.println("*****************File before modifying*****************");
        jh.showFileContent();
        System.out.println("");

        jh.modify(tmaEmployeeManager.getTmaEmployeeList().get(1), e1);

        System.out.println("*****************File after modifying*****************");
        jh.showFileContent();

    }
}
