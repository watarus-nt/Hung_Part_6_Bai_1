package main;

import model.TMAEmployee;
import utilities.JsonHandlers;
import utilities.RandomPhoneNumberList;
import utilities.RandomUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranmanhhung on 11/1/2016.
 */
public class test {
    public static void main(String[] args) {
        JsonHandlers jh = new JsonHandlers("employees.json");
        RandomPhoneNumberList randomPhoneNumberList = new RandomPhoneNumberList(10);
        RandomUtils randomUtils = new RandomUtils();
        List<TMAEmployee> tmaEmployeeList = new ArrayList<>();
        TMAEmployee e1 = new TMAEmployee();
        TMAEmployee e2 = new TMAEmployee();
        TMAEmployee e3 = new TMAEmployee();

        tmaEmployeeList.add(e1);
        tmaEmployeeList.add(e2);
        tmaEmployeeList.add(e3);

        jh.convertAllEmployeesToJson(tmaEmployeeList);

    }
}
