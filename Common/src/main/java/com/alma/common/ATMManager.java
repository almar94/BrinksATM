package com.alma.common;

import java.util.ArrayList;
import java.util.HashMap;

public class ATMManager {


    public static int low = 500;
    public static Long high = 10000L;
    public static int answer;
    public static HashMap<String, Long> shortMap = new HashMap<>();

    public static boolean ATMCheck(HashMap<String, Long> hashMap) {
        for (long L : hashMap.values()) {
            if (L < low) {
                return true;
            }
        }
        return false;
    }


    public static HashMap<String, Long> ATMCheckShort(ArrayList<BackEndATM> atmLists) {
        for (BackEndATM atm : atmLists) {
            for (Bills_name bill : Bills_name.values()) {
                long bill_amount = atm.getBills().get(bill.name);
                shortMap.put(bill.name, Math.max(high - bill_amount, 0));
            }
        }
        return shortMap;
    }


    public static HashMap<String, Long> fill(HashMap<String, Long> fillBill) {
        for (Bills_name bill : Bills_name.values()) {
            fillBill.put(bill.name, high);

        }
        return fillBill;
    }


}
