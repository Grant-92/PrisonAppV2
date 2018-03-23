package com.example.grant.prisonappv2;

/**
 * Created by Grant on 23/03/2018.
 */


public class Prisoner {
    String id;
    String name;
    int age;
    String charge;
    String chargeDescription;
    int sentence;
    String seclevel;
    String pic_location;

    //Blank Constructor
    public Prisoner() {
    }


    /**
     * Parametrised constructor
     * @param id
     * @param name
     * @param age
     * @param charge
     * @param chargeDescription
     * @param sentence
     * @param seclevel
     * @param pic_location
     */
    public Prisoner(String id, String name, int age, String charge, String chargeDescription, int sentence, String seclevel, String pic_location) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.charge = charge;
        this.chargeDescription = chargeDescription;
        this.sentence = sentence;
        this.seclevel = seclevel;
        this.pic_location = pic_location;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    public String getChargeDescription() {
        return chargeDescription;
    }

    public void setChargeDescription(String chargeDescription) {
        this.chargeDescription = chargeDescription;
    }

    public int getSentence() {
        return sentence;
    }

    public void setSentence(int sentence) {
        this.sentence = sentence;
    }

    public String getseclevel() {
        return seclevel;
    }

    public void setseclevel(String seclevel) {
        seclevel = seclevel;
    }

    public String getPic_location() {
        return pic_location;
    }

    public void setPic_location(String pic_location) {
        this.pic_location = pic_location;
    }
}
