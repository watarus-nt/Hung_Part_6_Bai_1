package model;


import common.Global;
import utilities.CommonUtils;
import utilities.RandomPhoneNumberList;
import utilities.RandomUtils;

import java.util.Random;

/**
 * Created by WataruS on 10/22/2016.
 */
public class oldPeopleClass {
    private static int counter = 0;
    private String name;
    private String phoneNumber;
    private int peopleID;
    private String description;
    private String gender;

    public oldPeopleClass(String name, String phoneNumber, String gender, String description) {
        counter++;
        setName(name);
        setPhoneNumber(phoneNumber);
        setPeopleID(counter);
        setDescription(description);
        setGender(gender);

    }

    public oldPeopleClass(String name, String phoneNumber, String gender) {
        counter++;
        setName(name);
        setPhoneNumber(phoneNumber);
        setPeopleID(counter);
        setRandomDescription();
        setGender(gender);

    }


    public oldPeopleClass(String name, String phoneNumber) {
        this(name, phoneNumber, null);
    }


    public oldPeopleClass(String gender) {
        counter++;
        setPeopleID(counter);
        setGender(gender);
//        setRandomGender();
        setRandomName(getGender());
        setRandomDescription();
        setRandomPhoneNumber();

    }

    public static int getCounter() {
        return counter;
    }


    public static void setCounter(int counter) {
        oldPeopleClass.counter = counter;
    }

    public int getPeopleID() {
        return peopleID;
    }

    public void setPeopleID(int peopleID) {
        this.peopleID = peopleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRandomName(String gender) {
        this.name = RandomUtils.randomName(gender);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        if (CommonUtils.isNumber(phoneNumber) && (10 <= phoneNumber.length())) {
            this.phoneNumber = phoneNumber;
        } else {
            this.phoneNumber = "NOT_DEFINE";
        }
    }

    public void setRandomPhoneNumber() {
        this.phoneNumber = "09" + String.valueOf(RandomPhoneNumberList.getRandomPhoneNumber(this.peopleID));
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getGender() {
        return gender.toLowerCase();
    }

    public void setGender(String gender) {
        if (Global.isInEnum(gender, Global.Gender.class)) {
            this.gender = gender.toLowerCase();
        } else {
            this.gender = "NOT_DEFINE";
        }
    }

    public void modifyName(String newValue) {
        setName(newValue);
    }

    public void modifyGender(String newValue) {
        setGender(newValue);
    }

    public void modifyPhoneNumber(String newValue) {
        setPhoneNumber(newValue);
    }

    public void modifyDescription(String newValue) {
        setDescription(newValue);
    }

    public void setRandomGender() {
        Random rd = new Random();
        Global.Gender[] genders = Global.Gender.values();
        this.gender = genders[rd.nextInt(genders.length)].name().toLowerCase();
    }
}