package business;


import model.People;
import model.TMAEmployee;
import utilities.JsonHandlers;
import utilities.RandomPhoneNumberList;
import utilities.RandomUtils;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.io.IOException;
import java.util.*;

/**
 * Created by WataruS on 10/22/2016.
 */
public class TMAEmployeeManager {

    private List<TMAEmployee> tmaEmployeeList;
    private HashMap<String, TMAEmployee> employeeHashMap;

    public TMAEmployeeManager(List<TMAEmployee> tmaEmployeeList) {
        this.tmaEmployeeList.clear();
        this.tmaEmployeeList = tmaEmployeeList;
    }

    public TMAEmployeeManager(){
        this.tmaEmployeeList = new ArrayList<TMAEmployee>();
        clean();


    }
     /*clear the list before using it to prevent old data may still exists (if we create other set of random employees
        again before stop the programe) */
    public void clean(){
        this.tmaEmployeeList.clear();
        People.setCounter(0);
        TMAEmployee.setTmaCounter(0);
    }

    public TMAEmployeeManager(int number){
        this.tmaEmployeeList = new ArrayList<TMAEmployee>();
        clean();
        RandomPhoneNumberList randomPhoneNumbers = new RandomPhoneNumberList(number);
        RandomUtils randomUtils = new RandomUtils();

        for (int i=0;i<number;i++){
            TMAEmployee employee= new TMAEmployee();
            this.tmaEmployeeList.add(employee);
        }
    }

    public List<TMAEmployee> getTmaEmployeeList() {
        return tmaEmployeeList;
    }


    public void setTmaEmployeeList(List<TMAEmployee> tmaEmployeeList) {
        this.tmaEmployeeList = tmaEmployeeList;
    }

    public Boolean isEixst(TMAEmployee tmaEmployee){
        boolean result = false;
        if (this.tmaEmployeeList.size() >= 0){
            if (tmaEmployee.getTmaEmployeeID() < this.tmaEmployeeList.size()) {
                result = true;
            }
        } else {
            result = true;
        }

        return result;
    }

    public void add(TMAEmployee tmaEmployee) {
        if (! isEixst(tmaEmployee)){
            this.tmaEmployeeList.add(tmaEmployee);
        }
    }

    public void remove(TMAEmployee tmaEmployee) {
        if (isEixst(tmaEmployee)) {
            this.tmaEmployeeList.remove(tmaEmployee.getTmaEmployeeID());
        }
    }

    public void modify(TMAEmployee tmaEmployee) {
        if (isEixst(tmaEmployee)){
            this.tmaEmployeeList.set(tmaEmployee.getTmaEmployeeID(), tmaEmployee);
        }
    }

    public void getEmployeesFromJsonFile(JsonHandlers jh) {
        //clear current tmaEmployeeList to store new list of employees from json file
        this.tmaEmployeeList.clear();
        //initial employeeHashMap to store new list of employees from json File
        employeeHashMap = new HashMap<String, TMAEmployee>();

        //read json file and get json array which contain all employees
        JsonArray jsonEmployees = jh.getJsonArray();

        //iterate through json array to get each employee and inital corresponding TMAEmployee object
        for (int i=0;i< jsonEmployees.size();i++){
            /*since each employee is stored in an array and we don't know exactly the key
            when iterating, need to use a foreach here to get out the key (ust run 1 since there's just 1 key for each element)*/
            for (String key : jsonEmployees.getJsonObject(i).keySet()){
                if (jsonEmployees.getJsonObject(i).getJsonArray(key) != null){
                    JsonObject jsonObject = jsonEmployees.getJsonObject(i).getJsonArray(key).getJsonObject(0);

                    TMAEmployee employee = jh.parseJsonToTMAEMployee(jsonObject);

                    this.tmaEmployeeList.add(employee);
                    //put employee to hashmap, since name may be duplicated, hashmap key will be name + employeeID
                    employeeHashMap.put(employee.getName().toLowerCase() + "_" + employee.getTmaEmployeeID(), employee);

                }
            }
        }
    }

    public LinkedHashMap<Integer, TMAEmployee> searchEmployee(String name){
        LinkedHashMap<Integer, TMAEmployee>found = new LinkedHashMap<>();
        Set<String> keyset = employeeHashMap.keySet();

        if (keyset.size() <= 0){
            return null;
        } else {
            for (String key : keyset){
                if (key.contains(name.toLowerCase())){
                    TMAEmployee emp = employeeHashMap.get(key);
                    found.put(emp.getTmaEmployeeID(), emp);
                }
            }
        }
        return found;
    }


    public void writeEmployeesToJson(JsonHandlers jh) throws IOException {
        //clear class variable in JsonHandlers before continue to remove old data from previous run
//        jh.clean();
        //convert  Employees to json
        jh.convertAllEmployeesToJson(this.tmaEmployeeList);
        jh.writeToFile();
    }

    public String toString(){
        String result = "";
        for (TMAEmployee emp : tmaEmployeeList){
            result += emp.toString() + "\n";
        }
        return result;
    }
}
