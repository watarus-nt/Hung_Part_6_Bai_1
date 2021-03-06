package utilities;


import model.People;
import model.TMAEmployee;

import javax.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by WataruS on 10/22/2016.
 */
public class JsonHandlers {
    private Path filePath;
    private JsonObject mainJsonObject;
    private JsonArray jsonArray;
    private JsonObjectBuilder objectBuilder;
    private JsonArrayBuilder arrayBuilder;

    public JsonHandlers(String filePath) {
        this.setFilePath(filePath);
        createFile();
        objectBuilder = Json.createObjectBuilder();
    }


    public JsonArray getJsonArray() {
        return jsonArray;
    }

    public void setJsonArray(JsonArray jsonArray) {
        this.jsonArray = jsonArray;
    }

    public JsonObject getMainJsonObject() {
        return mainJsonObject;
    }

    public void setMainJsonObject(JsonObject mainJsonObject) {
        this.mainJsonObject = mainJsonObject;
    }

    public Path getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = Paths.get(filePath);
    }

    public Boolean isFileExist() {
        return Files.exists(this.filePath);
    }

    public void createFile(){
        if (!isFileExist()){
            try {
                Files.createFile(getFilePath());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public Boolean isFileEmpty(){
        Boolean result = false;
        if (isFileExist()){
            File file = new File(String.valueOf(this.filePath));

            if (file.length() == 0) {
              result = true;
            }
        }
        return  result;
    }

    public JsonObject get(People people) {
        JsonObject result = null;
        //iterator through each element in jsonArray
        for (int i=0;i<jsonArray.size();i++){
            /*since each employee is stored in an array and we don't know exactly the key
            when iterating, need to use a foreach here to get out the key (ust run 1 since there's just 1 key for each element)*/
            for (String key : jsonArray.getJsonObject(i).keySet()){
                if (jsonArray.getJsonObject(i).getJsonArray(key) != null){
                    JsonObject jsonObject = jsonArray.getJsonObject(i).getJsonArray(key).getJsonObject(0);

                    //if peopleID from input people object == peopleID from employeeeJsonOjbect --> return employeeJsonObject
                    if (people.getPeopleID() == jsonObject.getInt("peopleID")){
                        if (isSamePerson(people, jsonObject)){
                            result = jsonObject;
                        }
                    }
                }
            }
        }
        return result;
    }
    
    public void add(People people) throws IOException {
        if (people instanceof TMAEmployee){
            TMAEmployee employee = (TMAEmployee) people;

            addToFile(employee.toJson().build().toString());

        }
    }
    
    public void remove(People people){
        if (people instanceof TMAEmployee){
            TMAEmployee employee = (TMAEmployee) people;
            removeFromFile(employee.toJson().build().toString());
        }
    }
    
    public void modify(People oldPeole, People newPeople){
        if ((oldPeole instanceof TMAEmployee) && (newPeople instanceof  TMAEmployee)){
            TMAEmployee oldEmployee = (TMAEmployee) oldPeole;
            TMAEmployee newEmployee = (TMAEmployee) newPeople;

            modifyInFile(oldEmployee.toJson().build().toString(),
                         newEmployee.toJson().build().toString());
        }
    }


    public void getEmployeeFromJson() {
        InputStream fis = null;
        JsonObject jsonObject = null;
        JsonArray jsonArray = null;

        try {
            fis = new FileInputStream(String.valueOf(getFilePath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //create JsonReader object
        JsonReader jsonReader = Json.createReader(fis);
        //get JsonObject from JsonReader
        this.mainJsonObject = jsonReader.readObject();
//            jsonArray = jsonReader.readArray();
        this.jsonArray = this.mainJsonObject.getJsonArray("Employees");
        //we can close IO resource and JsonReader now
        jsonReader.close();
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
//        return jsonArray;
    }

    public void convertAllEmployeesToJson(List<TMAEmployee> tmaEmployeeList) {
        this.objectBuilder = Json.createObjectBuilder();
        this.arrayBuilder = Json.createArrayBuilder();

        //Convert each employee to json object and add to jsonArray variable
        for (TMAEmployee employee : tmaEmployeeList) {
            this.arrayBuilder.add(employee.toJson());
        }

        objectBuilder.add("Employees", arrayBuilder);
        this.mainJsonObject = this.objectBuilder.build();


        //put jsonArray to finalJsonObject to create json object with all employees
//        mainJsonObject.put("Employees", jsonArray);
    }

    public TMAEmployee parseJsonToTMAEMployee(JsonObject jsonObject) {

        String name = jsonObject.getString("name");

        String gender = jsonObject.getString("sex");
        String phone = jsonObject.getString("phone");
        String description = jsonObject.getString("description");
        String team = jsonObject.getString("team");
        int relativeID = jsonObject.getInt(("relativeID"));

        return new TMAEmployee(relativeID, name, phone, gender.toUpperCase(), description, team);

    }

    //clean class varaibles before using in case we re-use this class before again before stopping the program
    public void clean(){
        this.mainJsonObject.clear();
        this.jsonArray.clear();
    }


    public Boolean isSamePerson(People people, JsonObject jsonObject){
        Boolean result = false;

        if (people instanceof TMAEmployee){
            TMAEmployee employee = (TMAEmployee) people;
            if (employee.getTmaEmployeeID() == jsonObject.getInt("employeeID")){
                result = true;
            }
        }

        return result;
    }

    public void writeToFile(){
        OutputStream os = null;
        try {
            os = new FileOutputStream(String.valueOf(getFilePath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JsonWriter jsonWriter = Json.createWriter(os);
        jsonWriter.writeObject(this.mainJsonObject);

        jsonWriter.close();
    }

    private void addToFile(String addString){
        File newJsonFile = new File(String.valueOf(getFilePath()));
        String search = "]}";  // <- changed to work with String.replaceAll()

        //file reading
        FileReader fr = null;
        try {
            fr = new FileReader(newJsonFile);
            String s;

            BufferedReader br = new BufferedReader(fr);

            String newJson = "";
            while ((s = br.readLine()) != null) {

                newJson = s.substring(0,s.lastIndexOf(search)) + "," + addString + "]}";
                // do something with the resulting line
            }

            FileWriter fw = new FileWriter(newJsonFile);
            fw.write(newJson);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void removeFromFile(String removeString){
        File newJsonFile = new File(String.valueOf(getFilePath()));

        //file reading
        FileReader fr = null;
        try {
            fr = new FileReader(newJsonFile);
            String s;

            BufferedReader br = new BufferedReader(fr);

            String  newJsonString = "";
            while ((s = br.readLine()) != null) {
                if (s.contains(removeString)){
                    String subString1 = s.substring(0,s.indexOf(removeString));
                    String subString2 = s.substring(s.indexOf(removeString),s.length()).replace(removeString,"");
                    if (subString1.equals("{[")) {
                        newJsonString = subString1 + subString2.substring(1, subString2.length());
                    } else if (subString2.equals("]}")) {
                        newJsonString = subString1.substring(0,subString1.length()-1) + subString2;
                    } else {
                        newJsonString = subString1 + subString2.substring(1, subString2.length());
                    }
                }

            }

            FileWriter fw = new FileWriter(newJsonFile);
            fw.write(newJsonString);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void modifyInFile(String stringToReplace, String newString) {
        File newJsonFile = new File(String.valueOf(getFilePath()));

        //file reading
        FileReader fr = null;
        try {
            fr = new FileReader(newJsonFile);
            String s;

            BufferedReader br = new BufferedReader(fr);

            String  newJsonString = "";
            while ((s = br.readLine()) != null) {
                if (s.contains(stringToReplace)){
                    newJsonString = s.replace(stringToReplace, newString);
                }

            }

            FileWriter fw = new FileWriter(newJsonFile);
            fw.write(newJsonString);
            fw.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showFileContent(){
        File newJsonFile = new File(String.valueOf(getFilePath()));

        //file reading
        FileReader fr = null;
        try {
            fr = new FileReader(newJsonFile);
            String s;

            BufferedReader br = new BufferedReader(fr);

            String  newJsonString = "";
            while ((s = br.readLine()) != null) {
                System.out.println(s);

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

//        return null;
}


//removed
/*    public void convertEmployeeToJson(JsonArrayBuilder jsonArrayBuilder, TMAEmployee tmaEmployee) {

        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        JsonArrayBuilder arrayBuilder = addPersonDetails(tmaEmployee);

        objectBuilder.add(tmaEmployee.getName(), arrayBuilder);

        jsonArrayBuilder.add(objectBuilder);

    }

    public JsonArrayBuilder addPersonDetails(TMAEmployee tmaEmployee){
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        JsonObjectBuilder objectBuilder = Json.createObjectBuilder();
        objectBuilder.add("peopleID", tmaEmployee.getPeopleID()); // "id":123
        objectBuilder.add("employeeID", tmaEmployee.getTmaEmployeeID());
        objectBuilder.add("relativeID", tmaEmployee.getRelativeID());
        objectBuilder.add("name", tmaEmployee.getName());
        objectBuilder.add("phone", tmaEmployee.getPhoneNumber());
        objectBuilder.add("sex", tmaEmployee.getGender());
        objectBuilder.add("description", tmaEmployee.getDescription());
        objectBuilder.add("team", tmaEmployee.getTeam());

        jsonArrayBuilder.add(objectBuilder);
        return jsonArrayBuilder;
    }*/


