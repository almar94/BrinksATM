package com.alma.common;

public enum Bills_name {


    Bill_20("20"), Bill_50("50"), Bill_100("100"), Bill_200("200");
    String name;

    Bills_name(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
