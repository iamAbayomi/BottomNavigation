package com.appdot.qoutesapp3;

/**
 * Created by Abayomi on 9/16/2018.
 */

public class Dessert {
    private String name;
    private String description;

    public Dessert() {
    }

    public Dessert( String description) {
        //this.name = name;
        this.description = description;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name) {

        this.name = name;

    }

    public String getDescription() {

        return description;
    }

    public void setDescription(String description) {

        this.description = description;
    }
}
