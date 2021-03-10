package com.alma.common;

import java.util.HashMap;

public class BackEndATM {

    private Long id;
    private HashMap<String, Long> bills;

    public BackEndATM(Long id, HashMap<String, Long> bills) {
        this.id = id;
        this.bills = bills;
    }

    public BackEndATM() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public HashMap<String, Long> getBills() {
        return bills;
    }

    public void setBills(HashMap<String, Long> bills) {
        this.bills = bills;
    }

}
