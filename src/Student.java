//package sce.cz2002.yxy;

import javax.management.ObjectName;
import java.util.*;
import java.io.Serializable;
import java.time.*;

/**
 * A entity class which declares student information
 *
 */
public class Student implements Serializable{
    private String Name;
    private String Username;
    private char Gender;
    private String Nationality;
    private String MatricNo;
    private String Password;
    private String StudentEmail;
    private Map<String, Integer> CoursesRegistered;
    private Map<String, Integer> CoursesWaitlist;
    //private Map<>
    private int SumAU = 0;
    private LocalDateTime[] AccessPeriod;
    private Timetable timeSlot;



    /**
     * Create Student constructors
     * @param name
     * @param gender
     * @param nationality
     * @param matric
     * @param email
     */
    public Student(String name, char gender, String nationality, String matric, String email) {
        Name = name;
        Gender = gender;
        Nationality = nationality;
        MatricNo = matric;
        this.StudentEmail = email;
        timeSlot = new Timetable();
        CoursesRegistered = new HashMap<>();
        CoursesWaitlist = new HashMap<>();
    }

    /**
     * A method to get student name
     * @return
     */

    public String getStudentName() {
        return Name;
    }

    /**
     * A method to get user name
     * @return
     */
    public String getStudentUsername() {
        return Username;
    }

    /**
     * A method to get student matric
     * @return
     */
    public String getStudentMatric() {
        return MatricNo;
    }

    public String getStudentPassword() {
        return Password;
    }

    /**
     * A method to get course registered
     * @return
     */
    public Map<String, Integer> getCoursesRegistered() {
        return CoursesRegistered;
    }

    /**
     * A method to get student email
     * @return
     */
    public String getStudentEmail(){ return StudentEmail;}

    /**
     * A method to print time
     */
    public void printTime() {
        CoursesRegistered.entrySet().forEach(entry->{System.out.println(entry.getKey()); });

    }

    /**
     * A method to get course wait list
     * @return
     */
    public Map<String, Integer> getCoursesWaitlist() {
        return CoursesWaitlist;
    }

    /**
     * A method to get AU
     * @return
     */
    public int getAU(){
        return this.SumAU;
    }

    /**
     * A method to set access period
     * @param accessperiod
     */
    public void setAccessPeriod(LocalDateTime[] accessperiod) {
        AccessPeriod = accessperiod;
    }

    /**
     * A method to get access period
     * @return
     */
    public LocalDateTime[] getAccessPeriod() {
        return AccessPeriod;
    }

    /**
     * A method to add course registered
     * @param i
     * @param courseAU
     */
    public void addtoCoursesRegistered(Index i, int courseAU){
        this.CoursesRegistered.put(i.getCourseCode(),i.getIndexNumber());
        this.SumAU = this.SumAU + courseAU;
    }

    /**
     * A method to add course wait list
     * @param i
     * @param courseAU
     */
    public void addtoCoursesWaitlisted(Index i, int courseAU){
        this.CoursesWaitlist.put(i.getCourseCode(),i.getIndexNumber());
        this.SumAU = this.SumAU + courseAU;
    }

    /**
     * A method to delete from course registered
     * @param i
     * @param courseAU
     */
    public void delfromCoursesRegistered( Index i, int courseAU){
        this.CoursesRegistered.remove(i.getCourseCode());
        this.SumAU = this.SumAU - courseAU;
    }

    /**
     * A method to delete from course wait list
     * @param i
     * @param courseAU
     */
    public void delfromCoursesWaitlisted(Index i, int courseAU){
        this.CoursesWaitlist.remove(i.getCourseCode());
        this.SumAU = this.SumAU - courseAU;
    }

    /**
     * A method to get time table
     * @return
     */
    public Timetable getTimetable() {
        return this.timeSlot;
    }

    /**
     * A method to print index timetable
     * @param courseCode
     */
    public void printIndexTimeTable(String courseCode){
        Integer i = this.CoursesRegistered.get(courseCode);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("Course code: "+courseCode);
        System.out.println("Index number: "+i);
//        System.out.println("Index time table is as follows");
//        System.out.println();
//        i.printTimetable();
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }

    /**
     * A method to get index from course registered
     * @param courseCode
     * @return
     */
    public int getIndexforCoursesRegistered(String courseCode){
        return this.CoursesRegistered.get(courseCode);
    }

    /**
     * A method to get index from course wait list
     * @param courseCode
     * @return
     */
    public int getIndexforCoursesWaitlisted(String courseCode){
        return this.CoursesWaitlist.get(courseCode);
    }
}
