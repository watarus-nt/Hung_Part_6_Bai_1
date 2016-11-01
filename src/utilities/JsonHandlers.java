package utilities;

import model.People;
import model.TMAEmployee;

import javax.json.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by WataruS on 10/22/2016.
 */
public class JsonHandlers {
    private Path filePath;
    private JsonObject mainJsonObject;
    private JsonObjectBuilder jsonBuilder;

    public JsonHandlers(String filePath) {
        this.setFilePath(filePath);
        if (!isFileEmpty()){
            mainJsonObject = getAll();
        }
        jsonBuilder = Json.createObjectBuilder();
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

    public void add(Object object) {

    }

    public List<JsonObject> get(People people) {
        List<JsonObject> result = new ArrayList<>();

        for (String key : this.mainJsonObject.keySet()) {
            if (key.equals(people.getName())){
                result.add(this.mainJsonObject.getJsonObject(key));
            }
        }
        return result;
    }

    public JsonObject getAll() {
        InputStream fis = null;
        JsonObject jsonObject = null;

        try {
            fis = new FileInputStream(String.valueOf(getFilePath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        //create JsonReader object
        JsonReader jsonReader = Json.createReader(fis);
        //get JsonObject from JsonReader
        jsonObject = jsonReader.readObject();

        //we can close IO resource and JsonReader now
        jsonReader.close();
        try {
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    public void remove(Object object) {

    }

    public void modify(Object object) {

    }


    public void convertAllEmployeesToJson(List<TMAEmployee> tmaEmployeeList) {

        //Convert each employee to json object and add to jsonArray variable
        for (TMAEmployee employee : tmaEmployeeList) {
            convertEmployeeToJson(this.jsonBuilder, employee);
        }

//        jsonBuilder.add("Employees", jsonBuilder);
        this.mainJsonObject = this.jsonBuilder.build();


        //put jsonArray to finalJsonObject to create json object with all employees
//        mainJsonObject.put("Employees", jsonArray);
    }

    public void convertEmployeeToJson(JsonObjectBuilder jsonBuilder, TMAEmployee tmaEmployee) {
        JsonObjectBuilder employeeJsonObject = Json.createObjectBuilder();

        employeeJsonObject.add("peopleID", tmaEmployee.getPeopleID()); // "id":123
        employeeJsonObject.add("employeeID", tmaEmployee.getTmaEmployeeID());
        employeeJsonObject.add("relativeID", tmaEmployee.getRelativeID());
        employeeJsonObject.add("name", tmaEmployee.getName());
        employeeJsonObject.add("phone", tmaEmployee.getPhoneNumber());
        employeeJsonObject.add("sex", tmaEmployee.getGender());
        employeeJsonObject.add("description", tmaEmployee.getDescription());
        employeeJsonObject.add("team", tmaEmployee.getTeam());

        jsonBuilder.add(tmaEmployee.getName(), employeeJsonObject);

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

//        return null;
}


