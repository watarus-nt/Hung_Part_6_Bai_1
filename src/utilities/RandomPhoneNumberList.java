package utilities;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by WataruS on 10/22/2016.
 */
public class RandomPhoneNumberList {

    private static List<Integer> randomPhoneNumberList;
    private static Integer randomPhoneNumber;

    public RandomPhoneNumberList(int size){
        randomPhoneNumberList = randomPhoneNumber(size);
    }

    public static Integer getRandomPhoneNumber(int index) {
        setRandomPhoneNumber(index);
        return randomPhoneNumber;
    }

    private static void setRandomPhoneNumber(int index) {
        randomPhoneNumber = randomPhoneNumberList.get(index);
    }

    private List<Integer> getRandomPhoneNumberList() {
        return randomPhoneNumberList;
    }

    private void setRandomPhoneNumberList(List<Integer> randomPhoneNumberList) {
        this.randomPhoneNumberList = randomPhoneNumberList;
    }

    private List<Integer> randomPhoneNumber(int size){

        int startNumber = 10000000;
        List<Integer> list = new ArrayList<Integer>();
        for (int j=0;j<size;j++){
            list.add(startNumber + j);
        }
        Collections.shuffle(list);
        /* since people ID and employee ID start from 1, add a null element to position 0 of this list
        so we can get phone number from position 1 towards
        */

        list.add(0, null);
        return list;
    }
}
