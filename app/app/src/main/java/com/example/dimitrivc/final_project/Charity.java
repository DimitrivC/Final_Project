package com.example.dimitrivc.final_project;

/**
 * Created by DimitrivC on 14-6-2018.
 */

public class Charity {

    public String charityName;
    public Float expectedUtility;
    public Integer value1;
    public Integer value2;
    public Integer value3;
    public Integer value4;
    public Float probability1;
    public Float probability2;
    public Float probability3;
    public Float probability4;

    // default constructor for Firebase
    public Charity(){}

    public Charity(String aCharityName, Float aExpectedUtility,
                   Integer aValue1, Integer aValue2, Integer aValue3, Integer aValue4,
                   Float aProbability1, Float aProbability2, Float aProbability3, Float aProbability4){

        this.charityName = aCharityName;
        this.expectedUtility = aExpectedUtility;
        this.value1 = aValue1;
        this.value2 = aValue2;
        this.value3 = aValue3;
        this.value4 = aValue4;
        this.probability1 = aProbability1;
        this.probability2 = aProbability2;
        this.probability3 = aProbability3;
        this.probability4 = aProbability4;

    }

}
