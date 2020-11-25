//package sce.cz2002.yxy;

import javax.management.ObjectName;
import java.util.*;
import java.io.Serializable;
import java.time.*;

/**
 * A entity class which declares student information
 */
public class Student implements Serializable{

    /**
     *  Declare local variables Name
     */
    private String Name;
    /**
     *  Declare local variables Username
     */
    private String Username;
    /**
     *  Declare local variables Gender
     */
    private char Gender;
    /**
     *  Declare local variables Nationality
     */
    private String Nationality;
    /**
     *  Declare local variables MatricNo
     */
    private String MatricNo;
    /**
     *  Declare local variables password
     */
    private String Password;
    /**
     *  Declare local variables StudentEmail
     */
    private String StudentEmail;
    /**
     *  Declare local variables CourseRegistered
     */
    private Map<String, Integer> CoursesRegistered;
    /**
     *  Declare local variables CoursesWaitlist
     */
    private Map<String, Integer> CoursesWaitlist;
    /**
     *  Declare local variables sumAU
     */
    private int SumAU = 0;
    /**
     *  Declare local variables AccessPeriod
     */
    private LocalDateTime[] AccessPeriod;
    /**
     *  Declare local variables Timetable
     */
    private Timetable timeSlot;



    /**
     * Create Student constructors
     * @param name name
     * @param gender gender
     * @param nationality nationality
     * @param matric matric number
     * @param email email address
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
     * @return student name
     */

    public String getStudentName() {
        return Name;
    }

    /**
     * A method to get user name
     * @return user name
     */
    public String getStudentUsername() {
        return Username;
    }

    /**
     * A method to get student matric
     * @return matric number
     */
    public String getStudentMatric() {
        return MatricNo;
    }

    /**
     * A method to get student password
     * @return student password
     */
    public String getStudentPassword() {
        return Password;
    }

    /**
     * A method to get course registered
     * @return registered course
     */
    public Map<String, Integer> getCoursesRegistered() {
        return CoursesRegistered;
    }

    /**
     * A method to get student email
     * @return email address
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
     * @return course in wait list
     */
    public Map<String, Integer> getCoursesWaitlist() {
        return CoursesWaitlist;
    }

    /**
     * A method to get AU
     * @return au point
     */
    public int getAU(){
        return this.SumAU;
    }

    /**
     * A method to set access period
     * @param accessperiod access period
     */
    public void setAccessPeriod(LocalDateTime[] accessperiod) {
        AccessPeriod = accessperiod;
    }

    /**
     * A method to get access period
     * @return access period of student
     */
    public LocalDateTime[] getAccessPeriod() {
        return AccessPeriod;
    }

    /**
     * A method to add course registered
     * @param i index
     * @param courseAU au of course
     */
    public void addtoCoursesRegistered(Index i, int courseAU){
        this.CoursesRegistered.put(i.getCourseCode(),i.getIndexNumber());
        this.SumAU = this.SumAU + courseAU;
    }

    /**
     * A method to add course wait list
     * @param i index
     * @param courseAU au of course
     */
    public void addtoCoursesWaitlisted(Index i, int courseAU){
        this.CoursesWaitlist.put(i.getCourseCode(),i.getIndexNumber());
        this.SumAU = this.SumAU + courseAU;
    }

    /**
     * A method to delete from course registered
     * @param i index
     * @param courseAU au of the course
     */
    public void delfromCoursesRegistered( Index i, int courseAU){
        this.CoursesRegistered.remove(i.getCourseCode());
        this.SumAU = this.SumAU - courseAU;
    }

    /**
     * A method to delete from course wait list
     * @param i index
     * @param courseAU au of the course
     */
    public void delfromCoursesWaitlisted(Index i, int courseAU){
        this.CoursesWaitlist.remove(i.getCourseCode());
        this.SumAU = this.SumAU - courseAU;
    }

    /**
     * A method to get time table
     * @return student's timetable
     */
    public Timetable getTimetable() {
        return this.timeSlot;
    }

    /**
     * A method to print index timetable
     * @param courseCode course code
     */
    public void printIndexTimeTable(String courseCode){
        Integer i = this.CoursesRegistered.get(courseCode);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
        System.out.println("Course code: "+courseCode);
        System.out.println("Index number: "+i);
        System.out.println("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%");
    }

    /**
     * A method to get index from course registered
     * @param courseCode course code
     * @return index number of the course registered
     */
    public int getIndexforCoursesRegistered(String courseCode){
        return this.CoursesRegistered.get(courseCode);
    }

    /**
     * A method to get index from course wait list
     * @param courseCode course code
     * @return index number of the course in wait list
     */
    public int getIndexforCoursesWaitlisted(String courseCode){
        return this.CoursesWaitlist.get(courseCode);
    }
}
