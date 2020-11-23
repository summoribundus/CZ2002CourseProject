//package Entities;
import java.io.Serializable;
/**
Represents an administrator of the system.
It stores the basic information of the administrator. 
@author Group3_SS6_CZ2002
@version 1.0
@since 2020-11-22
*/
public class Admin implements Serializable {
    // idea can make student and admin inherit a class called account
    /**
	 * The username ID of the admin user
	 */
    private String username_ID;
    /**
     * The real name of the admin user
     */
    private String name;
    /**
     * The age of the admin user
     */
    private int age;
    /**
     * The ID of the Email of the admin user
     */
    private String emailID;
    /**
     * The gender of the admin user
     */
    private String gender;
    /**
     * The nationality of the admin user
     */
    private String nationality;
    /**
     * The contact details of the admin user
     */
    private String contact;

    /**
     * A constructor of the Admin class
     * An admin object may be created with only the ID of the new administrator
     * The other fields of the information of the administrator will be left blank
     * @param adminID The ID of the new administrator user is provided as a string. 
     */
    public Admin(String adminID){
        this.username_ID=adminID;
        this.name = "";
        this.age = 0;
        this.emailID = "";
        this.gender = "";
        this.nationality = "";
        this.contact = "";
    }

    /**
     * Another constructor of the Admin class
     * An admin object may be created with all the information of the new administrator
     * @param adminID The ID of the new administrator is provided as a string.
     * @param name The name of the new administrator is provided as a string.
     * @param age The age of the new administrator is provided as an integer. 
     * @param emailId The email ID of the new administrator is provided as a string.
     * @param gender The gender of the new administrator is provided as a string.
     * @param nationality The nationality of the new administrator is provided as a string.
     * @param contact The contact details of the new administrator is provided as a string.
     */
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
    /**
     * Gets the username ID of the administrator
     */
    public String getUsername(){
        return this.username_ID;
    }
    /**
     * The password should not be revealed. An empty string is returned. 
     */
    public String getPassword(){
        //return this.password;
        return "";
    }
    /**
     * Gets the real name of the administrator
     */
    public String getName(){
        return this.name;
    }
    /**
     * Gets the age of the administrator
     */
    public int  getAge(){
        return this.age;
    }
    /**
     * Gets the Email ID of the administrator
     */
    public String getEmaiId(){
        return this.emailID;
    }
    /**
     * Gets the nationality of the administrator
     */
    public String getNationality(){
        return this.nationality;
    }
    /**
     * Gets the gender of the administrator
     */
    public String getGender(){
        return this.gender;
    }
    /**
     * Gets the contact details of the administrator
     */
    public String getContact(){
        return this.contact;
    }

    //---------------------------------setter methods--------------------------------------------------------------
    /**
     * Modify the contact details of the administrator
     * @param contact the new contact information should be passed to the method in a string. 
     */
    public void setContact(String contact){
        this.contact = contact;
    }
    /**
     * Modify the Email id of the administrator
     * @param contact the new Email id should be passed to the method in a string. 
     */
    public void setEmailId(String emailId){
        this.emailID = emailId;
    }

    
//---------------------------------equals method---------------------------------------------------------------
    /**
     * This method overrides the
     * Compares two objects. The method returns true if the objects to be compared are both admin objects and are equivalent.
     * The two conditions listed above are tested according to their sequence shown in the current documentation. 
     * @param o The object to be compared will be passed to the method. Since it is not known whether it is an admin object, it is passed in
     *          as object type. 
     */
    @Override
    public boolean equals(Object o) {
        if (o instanceof Admin) {
            Admin p = (Admin)o;
            return (this.getUsername()==(p.getUsername()));
        }
        return false;
    }



}



