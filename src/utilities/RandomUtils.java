package utilities;

import common.Global;

import java.util.*;

/**
 * Created by WataruS on 10/22/2016.
 */
public class RandomUtils {
    private static int randomCounter = 0; //use to get element in Description ENUM
    private static int randomName = 0; //use to get random ENUM element

    private static List<Integer> randomDescriptionIndexSource;
    private static List<Integer> randomMaleFirstNameIndexSource;
    private static List<Integer> randomFemaleFirstNameIndexSource;
    private static List<Integer> randomLastNameIndexSource;
    private static List<Global.LastName> lastNameList;
    private static List<Global.MaleFirstName> maleFirstNameList;
    private static List<Global.FemaleFirstName> femaleFirstNameList;
    private static List<Global.Description> descriptionList;
    private static Random rd;

    public RandomUtils() {

        randomDescriptionIndexSource = generateIntegerList(Global.Description.values().length);
        //assume that number of Male First name = number of Female First name = number of Last name
        randomMaleFirstNameIndexSource = generateIntegerList(Global.MaleFirstName.values().length);
        randomFemaleFirstNameIndexSource = generateIntegerList(Global.FemaleFirstName.values().length);
        randomLastNameIndexSource = generateIntegerList(Global.LastName.values().length);
        lastNameList = Arrays.asList(Global.LastName.values());
        maleFirstNameList = Arrays.asList(Global.MaleFirstName.values());
        femaleFirstNameList = Arrays.asList(Global.FemaleFirstName.values());
        rd = new Random();

    }


    public static String randomDescription() {

        descriptionList = Arrays.asList(Global.Description.values());

        if (randomCounter >= descriptionList.size()) {
            randomCounter = 0;
            Collections.shuffle(randomDescriptionIndexSource);
        }

        String result = descriptionList.get(randomDescriptionIndexSource.get(randomCounter)).name();
        randomCounter++;
        return result;
    }

    public static String randomName(String gender) {
        String result = "";

        //if randomName equal or greater than number of Last name, reset it to 0 and shuff according list
        //so we can have new set of random names
        if (randomName >= lastNameList.size()) {
            randomName = 0;
            Collections.shuffle(randomMaleFirstNameIndexSource);
            Collections.shuffle(randomFemaleFirstNameIndexSource);
            Collections.shuffle(randomMaleFirstNameIndexSource);
        }
        if (Global.isInEnum(gender.toUpperCase(), Global.Gender.class)) {

            if (gender.toLowerCase().equals("male")) {
                result = maleFirstNameList.get(randomMaleFirstNameIndexSource.get(randomName)) + " " + lastNameList.get(randomLastNameIndexSource.get(randomName));
            } else {
                result = femaleFirstNameList.get(randomMaleFirstNameIndexSource.get(randomName)) + " " + lastNameList.get(randomFemaleFirstNameIndexSource.get(randomName));
            }
        }

        randomName++;
        return result;
    }

    public static String randomCompany(){
        return Global.Company.values()[rd.nextInt(Global.Relationship.values().length)].name();
    }

    public static String randomRelativeRole(){
        return Global.RelativeRole.values()[rd.nextInt(Global.RelativeRole.values().length)].name();
    }

    //this method will generate an List of integer numbers from 0 --> size
    //before returning, output list will be shuffed so we can use it to get random element out of ENUM list
    public static List<Integer> generateIntegerList(int size) {
        List<Integer> list = new ArrayList<Integer>();
        for (int i = 0; i < size; i++) {
            list.add(i);
        }
        Collections.shuffle(list);
        return list;
    }
}
