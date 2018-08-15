package com.example.tazo.testforfirebase;

/**
 * Created by Tazo on 2018-08-15.
 */

public class Qtqt {
    public String name;
    public double age;
    public String address;
    public Qtqt(){};
    public Qtqt(String name, double age, String address){
        this.name = name;
        this.age = age;
        this.address = address;
    };
    public String getName(){return name;}
    public double getAge(){return age;}
    public String getAddress(){return address;}
}
