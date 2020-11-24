/**
 * Represent indices within course.
 * @author Li Rui
 * @version 1.0
 * @since 2020-11-22
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.io.Serializable;
import java.util.LinkedList;

public class Index implements Comparable<Index>, Serializable{
    /**
     * Students who have registered the index
     */
    private transient List<Student> registeredStudents;

    /**
     * Students who are currently waiting for vacancies
     */
    private transient LinkedList<Student> waitingListStudents;

    /**
     * indexNumber: Index Number of the course
     * cnt: Number of registered students
     * available: Vacancies
     * capacity: Maximum number of students this index could hold
     */
    private int indexNumber, cnt, available, capacity;

    /**
     * The course this index belongs to
     */
    private Course course;

    /**
     * Notifier for sending notification if a student is added to registered successfully
     */
    private static Notifier notifier;

    /**
     * Timeslot the index occupies
     */
    private Timetable timeSlot;

    /**
     * Creates a new index with index number, capacity, the course it belongs and timeslot
     * @param number Index Number
     * @param capacity Index Capacity
     * @param course The course the index belongs to
     * @param timeSlot Timeslot the index occupies
     */
    public Index(int number, int capacity, Course course, Timetable timeSlot ) {
        this.course = course;
        indexNumber = number;
        this.capacity = capacity;
        registeredStudents = new ArrayList<>();
        waitingListStudents = new LinkedList<>();
        cnt = 0;
        available = capacity;
        this.timeSlot = timeSlot;
        if (notifier == null) notifier = new Email();
    }

    //------------------------------Service provided for course----------------------
    /**
     * Add to student to registered list if there's vacancy, otherwise add it to waiting list
     * Student is added successfully if there is no clash between index and student's time table
     * @param student Student who wants to register to this course
     * @return true if the student is added successfully
     */
    public boolean addStudent(Student student) {
        if (student == null) return false;
        if (student.getTimetable().checkClash(this.timeSlot)) return false;
        if (available == 0) {
            waitingListStudents.add(student);
            System.out.println("Added to wait list");
            student.addtoCoursesWaitlisted(this,this.course.getAU());
            student.getTimetable().addTimetable(this.timeSlot);
            return true;
        }
        registeredStudents.add(student);
        student.addtoCoursesRegistered(this,this.course.getAU());
        student.getTimetable().addTimetable(this.timeSlot);
        cnt++;
        available--;
        return true;
    }

    /**
     * Remove student from registered list if registered otherwise remove it from waiting list
     * Deletion is successful if student is either in registered list or waiting list
     * If student is deleted from registered list, students in waiting list is registered automatically
     * @param student student
     * @return true if student is deleted successfully
     */
    public boolean deleteStudent(Student student) {
        if (student == null) return false;
        boolean res = registeredStudents.remove(student);
        if (res) {
            cnt--; available++;
            adminWaitlistStudent();
            student.delfromCoursesRegistered(this,this.course.getAU());
            student.getTimetable().delTimetable(this.timeSlot);
            return true;
        }
        else {
            if (waitingListStudents.contains(student)){
                waitingListStudents.remove(student);
                student.getTimetable().delTimetable(this.timeSlot);
                student.delfromCoursesWaitlisted(this,this.course.getAU());
                return true;
            }
        }
        return false;
    }

    /**
     * Delete students with out add students in wait list to registered list automatically
     * Deletion is successful if student is registered in index
     * (Used for swapping index)
     * @param student student
     * @return true if student is deleted successfully
     */
    public boolean deleteStudentWithReserve(Student student){
        if (student == null) return false;
        boolean res = registeredStudents.remove(student);
        if (res) {
            cnt--; available++;
            student.delfromCoursesRegistered(this,this.course.getAU());
            student.getTimetable().delTimetable(this.timeSlot);
            return true;
        }
        return false;
    }

    /**
     * Print index time table
     */
    public void printTimetable(){
        this.getClasses().printTimeTable();
    }

    /**
     * Print all registered students in the index
     */
    public void printRegisteredStudents() {
        System.out.println("---------Registered students under index: " + indexNumber +"--------");
        for (Student student : registeredStudents) {
            System.out.println(student);
        }
    }

    //---------------------------Getter-----------------------------------
    /**
     * Get number of vacancies in index
     * @return number of vacancies
     */
    public int getVacancies() {
        return available;
    }

    /**
     * Get number of students who registered in this index
     * @return number of registered students
     */
    public int getNumRegistered() {
        return cnt;
    }

    /**
     * Get number of students who are in waiting list
     * @return number of students in waiting list
     */
    public int getNumWaitList() {
        return waitingListStudents.size();
    }

    /**
     * Get the students who have registered the course
     * @return list of successfully registered students
     */
    public List<Student> getRegisteredStudents() {
        return registeredStudents;
    }

    /**
     * Get index number
     * @return index number
     */
    public int getIndexNumber() {return indexNumber;}

    /**
     * Get capacity of the index
     * @return index capacity
     */
    public int getCapacity() {return capacity;}

    /**
     * Get course code of the course this index belongs
     * @return course code
     */
    public String getCourseCode(){return this.course.getCourseCode();}

    /**
     * Get timeslots this index occupies
     * @return timetable of this index
     */
    public Timetable getClasses(){
        return this.timeSlot;
    }



    //---------------------------------Setter------------------------------------
    /**
     * Set the capacity of this index
     * @param capacity new capacity
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
        available = capacity - cnt;
        adminWaitlistStudent();
    }

    /**
     * Set a new index number of this index
     * @param indexNumber new index number
     */
    public void setIndexNumber(int indexNumber) {this.indexNumber = indexNumber;}

    /**
     * Set a new list of registered students
     * @param registeredStudents new list of registered students
     */
    public void setRegisteredStudents(List<Student> registeredStudents) {
        this.registeredStudents = registeredStudents;
    }

    /**
     * Set a new list of wait list students
     * @param waitingListStudents list of students waiting
     */
    public void setWaitingListStudents(LinkedList<Student> waitingListStudents) {
        this.waitingListStudents = waitingListStudents;
    }


    /**
     * The string used when printing index object
     * @return string representation of index object
     */
    @Override
    public String toString() {
        return "Index: " + indexNumber + " capacity: " + capacity
                + " registered students: " + cnt + " available vacancies: "  + available;
    }

    /**
     * Check whether two index objects are equal
     * Two index object are identical if they have same index number
     * @param o Index object to be compared
     * @return result of comparing index
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Index index = (Index) o;
        return indexNumber == index.indexNumber;
    }

    /**
     * Hash by index number
     * @return hash value of index number
     */
    @Override
    public int hashCode() {
        return Objects.hash(indexNumber);
    }

    /**
     * Compare to index object by its index number
     * Used for sorting
     * @param T Index object
     * @return 1 for larger, 0 for equal, -1 for smaller
     */
    @Override
    public int compareTo(Index T) {
        return Integer.compare(indexNumber, T.indexNumber);
    }



    //---------------------------------------Helper Method--------------------------------
    /**
     * Add students in waiting list to registered if there are vacancy
     * Send notification to students email if they are added
     */
    private void adminWaitlistStudent() {
        String subject = "Course registration result";
        while (available > 0 && !waitingListStudents.isEmpty()) {
            Student student = waitingListStudents.removeFirst();
            student.delfromCoursesWaitlisted(this, course.getAU());
            registeredStudents.add(student);
            student.addtoCoursesRegistered(this,this.course.getAU());
            String content = "Dear " + student.getStudentName() + "\n\nYou have been added to course " + course.getCourseCode() + ", index: " + indexNumber + " successfully. ";
            notifier.notify(subject, content, student.getStudentEmail());
            System.out.println("Student " + student.getStudentName() + " added to course " + course.getCourseCode() + ", index: " + indexNumber + ". ");
            cnt++; available--;
        }
    }


    //------------------------------------For Initialization----------------------------------

    /**
     * Add students who have already registered to register list
     * @param student student
     */
    public void addStudentsToRegistered(Student student) {
        cnt++;
        available--;
        registeredStudents.add(student);
    }

    /**
     * Add students who recorded in wait list to wait list
     * @param student student
     */
    public void addStudentsToWaitList(Student student) {
        waitingListStudents.addLast(student);
    }

    /**
     * Initialize objects
     */
    public void initialize() {
        registeredStudents = new ArrayList<>();
        waitingListStudents = new LinkedList<>();
        cnt = 0;
        available = capacity;
        if (notifier == null) notifier = new Email();
    }
}
