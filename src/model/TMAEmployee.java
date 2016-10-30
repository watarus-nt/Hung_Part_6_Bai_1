package model;


import common.Global;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import java.util.Random;

/**
 * Created by WataruS on 10/22/2016.
 */
public class TMAEmployee extends People {
    private static int tmaCounter = 0;
    private String team;
    private int tmaEmployeeID;
    private int relativeID;

    public TMAEmployee(String name, String phoneNumber, String gender, String description, String team) {
        super(name, phoneNumber, gender, description);
        setTeam(team);
        tmaCounter++;
        setTmaEmployeeID(tmaCounter);
        setRelativeID(0);
    }

    public TMAEmployee(int relativeID, String name, String phoneNumber, String gender, String description, String team) {
        super(name, phoneNumber, gender, description);
        setTeam(team);
        tmaCounter++;
        setTmaEmployeeID(tmaCounter);
        setRelativeID(relativeID);
    }

    public TMAEmployee(){
        super("MALE");
        tmaCounter++;
        setTmaEmployeeID(tmaCounter);
        setRandomTeam();
        setRelativeID(0);
    }

    public static int getTmaCounter() {
        return tmaCounter;
    }

    public static void setTmaCounter(int tmaCounter) {
        TMAEmployee.tmaCounter = tmaCounter;
    }

    public int getRelativeID() {
        return relativeID;
    }

    public void setRelativeID(int relativeID) {
        this.relativeID = relativeID;
    }

    public int getTmaEmployeeID() {
        return tmaEmployeeID;
    }

    public void setTmaEmployeeID(int tmaEmployeeID) {
        this.tmaEmployeeID = tmaEmployeeID;
    }

    public String getTeam() {
        return team;
    }

    public void setTeam(String team) {
        if (Global.isInEnum(team, Global.TMAWorkingTeam.class)){
            this.team = team;
        } else {
            this.team = "NOT_DEFINE";
        }
    }

    public void setRandomTeam(){
        Random rd = new Random();
        Global.TMAWorkingTeam[] workingTeams = Global.TMAWorkingTeam.values();
        this.team = workingTeams[rd.nextInt(workingTeams.length)].name();
    }



    @Override
    public String toString() {
        return super.toString() + " - EmployeeID: "
                + getTmaEmployeeID() + " - Team: " + getTeam() + " - RelativeID: " + getRelativeID();
    }

    @Override
    public JsonObject toJson() {
        JsonObjectBuilder empBuilder = Json.createObjectBuilder();
        empBuilder.add("peopleID", getPeopleID());
        empBuilder.add("employeeID", getTmaEmployeeID());
        empBuilder.add("relativeID", getRelativeID());
        empBuilder.add("sex", getGender());
        empBuilder.add("team", getTeam());
        empBuilder.add("phone", getPhoneNumber());
        empBuilder.add("description", getDescription());

        return empBuilder.build();
    }


}
