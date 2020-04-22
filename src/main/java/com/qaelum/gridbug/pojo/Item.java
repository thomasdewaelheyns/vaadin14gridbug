package com.qaelum.gridbug.pojo;

import java.util.ArrayList;

/**
 * Created by Thomas on 22/04/2020.
 */
public class Item {

    private String name;
    private ArrayList<Detail> details = new ArrayList<>();

    public Item(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Detail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<Detail> details) {
        this.details = details;
    }
}
