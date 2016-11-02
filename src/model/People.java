package model;


import common.Global;
import utilities.RandomPhoneNumberList;
import utilities.RandomUtils;

import javax.json.JsonObjectBuilder;
import java.util.Random;

/**
 * Created by WataruS on 10/22/2016.
 */
public abstract class People {
    private static int counter = 0;
    private String name;
    private String phoneNumber;
    private int peopleID;
    private String description;
    private String gender;
    private String company;

    public People(String gender) {
        counter++;
        setPeopleID(counter);
        setGender(gender);
//        setRandomGender();
        setRandomName(getGender());
        setRandomDescription();
        setRandomPhoneNumber();

    }


    public People(String name, String phoneNumber, String gender, String description) {
        counter++;
        setName(name);
        setPhoneNumber(phoneNumber);
        setPeopleID(counter);
        setDescription(description);
        setGender(gender);

    }

    public People(String name, String phoneNumber, String gender) {
        counter++;
        setName(name);
        setPhoneNumber(phoneNumber);
        setPeopleID(counter);
        setRandomDescription();
        setGender(gender);

    }


    public People(String name, String phoneNumber) {
        this(name, phoneNumber, null);
    }


    public static int getCounter() {
        return counter;
    }

    public static void setCounter(int counter) {
        People.counter = counter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getPeopleID() {
        return peopleID;
    }

    public void setPeopleID(int peopleID) {
        this.peopleID = peopleID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if (Global.isInEnum(gender, Global.Gender.class)) {
            this.gender = gender;
        } else {
            this.gender = "NOT_DEFINE";
        }
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        if (Global.isInEnum(company, Global.Company.class)){
            this.company = company;
        } else {
            this.company = "NOT_DEFINE";
        }
    }

    public void setRandomDescription() {
        String result = "";
        for (int i = 0; i < 3; i++) {
            if (i != 2) {
                result += RandomUtils.randomDescription().toLowerCase() + ", ";
            } else {
                result += RandomUtils.randomDescription().toLowerCase();
            }
        }
        this.description = result;
    }

    public void setRandomGender() {
        Random rd = new Random();
        Global.Gender[] genders = Global.Gender.values();
        this.gender = genders[rd.nextInt(genders.length)].name().toLowerCase();
    }

    public void setRandomPhoneNumber() {
        this.phoneNumber = "09" + String.valueOf(RandomPhoneNumberList.getRandomPhoneNumber(this.peopleID));
    }

    public void setRandomName(String gender) {
        this.name = RandomUtils.randomName(gender);
    }
    public void changeName(String newValue) {
        this.setName(newValue);
    }

    public void changeGender(String newValue) {
        this.setGender(newValue);
    }

    public void changePhoneNumber(String newValue) {
        this.setPhoneNumber(newValue);
    }

    public void changeDescription(String newValue) {
        this.setDescription(newValue);
    }

    public void changeCompany(String newValue) {
        this.setCompany(newValue);
    }


    @Override
    public String toString(){
        return "ID: " + getPeopleID() + " - Name: " + getName() + " - Phone Number: " + getPhoneNumber()
                + "- Gender: " + getGender() + " - Description: " + getDescription();
    }

    public abstract JsonObjectBuilder toJson();


}
