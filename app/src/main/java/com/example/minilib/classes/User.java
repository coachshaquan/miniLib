package com.example.minilib.classes;

public class User{

    /*     Class variables for User objects.    */
    private String fName;
    private String lName;
    private int pin;
    private boolean isAdministrator;


    /*      Constructors for User objects.    */
    public User(String fName, String lName, int pin) {
        this.fName = fName;
        this.lName = lName;
        this.pin = pin;
        this.isAdministrator = false;
    }

    public User(String fName, String lName, int pin, boolean isAdministrator) {
        this.fName = fName;
        this.lName = lName;
        this.pin = pin;
        this.isAdministrator = isAdministrator;
    }

    /*      Getter methods for User objects.    */
    public String getFName() {
        return this.fName;
    }

    public String getLName() {
        return this.lName;
    }

    public int getPin() {
        return this.pin;
    }

    public boolean isAdministrator() {
        return isAdministrator;
    }

    /*      Setter methods for User objects.    */
    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public void setAdministrator(boolean administrator) {
        isAdministrator = administrator;
    }

}
