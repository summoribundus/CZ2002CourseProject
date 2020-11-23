//package Entities;
import java.io.Serializable;

public class Admin implements Serializable {
    // idea can make student and admin inherit a class called account
    private String username_ID;
    private String name;
    private int age;
    private String emailID;
    private String gender;
    private String nationality;
    private String contact;

    public Admin(String adminID){
        this.username_ID=adminID;
        this.name = "";
        this.age = 0;
        this.emailID = "";
        this.gender = "";
        this.nationality = "";
        this.contact = "";
    }

    public Admin(String adminId, String name, int age, String emailId, String gender, String nationality, String contact){
        this.username_ID=adminId;
        this.name = name;
        this.age = age;
        this.emailID = emailId;
        this.gender = gender;
        this.nationality = nationality;
        this.contact = contact;

    }
    //---------------------------------getter methods--------------------------------------------------------------
    public String getUsername(){
        return this.username_ID;
    }

    public String getPassword(){
        //return this.password;
        return "";
    }

    public String getName(){
        return this.name;
    }

    public int  getAge(){
        return this.age;
    }

    public String getEmaiId(){
        return this.emailID;
    }

    public String getNationality(){
        return this.nationality;
    }

    public String getGender(){
        return this.gender;
    }

    public String getContact(){
        return this.contact;
    }

    //---------------------------------setter methods--------------------------------------------------------------
    public void setContact(String contact){
        this.contact = contact;
    }

    public void setEmailId(String emailId){
        this.emailID = emailId;
    }

    @Override
//---------------------------------equals method---------------------------------------------------------------
    public boolean equals(Object o) {
        if (o instanceof Admin) {
            Admin p = (Admin)o;
            return (this.getUsername()==(p.getUsername()));
        }
        return false;
    }



}



