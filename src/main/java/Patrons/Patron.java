package Patrons;

public class Patron{

    // instance variables
    public String name;
    private String phoneNumber;
    

    public Patron(String name, String phoneNumber) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        
    }

    // getters

    public String getName() {
        return name;
    }

   

    public String getPhoneNumber() {
        return phoneNumber;
    }


    // setters
    
    public void setName(String name) {
        this.name = name;
    }


    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
    
    public String toString() {
        return "Name: " + name  + ", Phone Number: " + phoneNumber ;
    }


   


}
