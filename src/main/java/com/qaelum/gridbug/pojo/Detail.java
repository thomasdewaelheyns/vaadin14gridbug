package com.qaelum.gridbug.pojo;

/**
 * Created by Thomas on 22/04/2020.
 */
public class Detail {

    private String property1;
    private String property2;

    public Detail(String property1, String property2) {
        this.property1 = property1;
        this.property2 = property2;
    }

    public String getProperty1() {
        return property1;
    }

    public void setProperty1(String property1) {
        this.property1 = property1;
    }

    public String getProperty2() {
        return property2;
    }

    public void setProperty2(String property2) {
        this.property2 = property2;
    }
}
