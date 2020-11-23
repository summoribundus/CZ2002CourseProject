/**
 * Controller class providing functionalities for students
 * @author Li Rui, Ye Ziyuan (names not listed in order)
 * @version 1.0
 * @since 2020-11-22
 */

import java.util.List;
import java.util.Map;

public class StudentCourseController {
    /**
     * Map containing all course
     * Key: course code
     * Value: course object
     */
    private Map<String, Course> map;

    /**
     * Create a controller
     * @param map map containing all courses
     */
    public StudentCourseController(Map<String, Course> map) {
        this.map = map;
    }

    //------------------------------for student----------------------------------

    /**
     * Add a student to a course and index
     * A student is added to a course index successfully if:
     * 1.course code is valid
     * 2.student's total au smaller than 21 after adding this course
     * 3.course add student operation is successful
     * @param courseCode course code
     * @param indexNumber index number
     * @param student student
     * @return true if course is added successfully
     */
    public boolean addStudent(String courseCode, int indexNumber, Student student) {
        if (!checkCodeString(courseCode)) return false;
        Course course = map.get(courseCode);
        if (student.getAU() + course.getAU() < 21){
            if (course.addStudentToIndex(student, indexNumber)) {
                System.out.println("Student " + student.getStudentName() + " added to " + courseCode +".");
                System.out.println("The following timetable was added");
                course.printIndexTimeTable(indexNumber);
                return true;
            }
            else {
                System.out.println("Failed. Please recheck your entries");
                return false;
            }
        }
        else{
            System.out.println("AU limit exceeded");
            return false;
        }
    }

    /**
     * Drop student from a course index
     * Student is dropped successfully if:
     * 1. course code is valid
     * 2. course deletion action is successful
     * @param courseCode course code
     * @param indexNumber index number
     * @param student student
     * @return true if student is dropped successfully
     */
    public boolean dropStudent(String courseCode, int indexNumber, Student student) {
        if (!checkCodeString(courseCode)) return false;
        Course course = map.get(courseCode);
        if (course.deleteStudentFromIndex(student, indexNumber)) {
            System.out.println("Student " + student.getStudentName() + " deleted from " + courseCode + ".");
            System.out.println("The following timetable was deleted");
            course.printIndexTimeTable(indexNumber);
            return true;
        }
        else {
            System.out.println("Failed. Please recheck your entries");
            return false;
        }
    }

    /**
     * Swap student from one index to another index
     * Swapping is successful if:
     * 1. course code is valid
     * 2. course swapping action is successful
     * @param courseCode course code
     * @param indexNumber1 index number to drop
     * @param indexNumber2 index number to add
     * @param student student
     * @return true if swapping is successful
     */
    public boolean swapIndexStudent(String courseCode, int indexNumber1, int indexNumber2, Student student) {
        if (!checkCodeString(courseCode)) return false;
        Course course = map.get(courseCode);
        boolean res = course.swapIndex(student, indexNumber1, indexNumber2);
        if (res) {
            System.out.println("Course " + courseCode + ": Student " + student.getStudentName() + ", index " + indexNumber1 + " swapped to index " + indexNumber2 + ".");
            return true;
        }
        else {
            System.out.println("Failed. Please recheck your entries");
            return false;
        }

    }

    /**
     * Swap student index between student1 and student2
     * Swapping is true if:
     * 1. course code is valid
     * 2. course swapping operation is successful
     * @param courseCode course code
     * @param indexNumber1 index number student1 currently registered
     * @param indexNumber2 index number student1 currently registered
     * @param stud1 student1
     * @param stud2 student2
     * @return true if swapping is successful
     */
    public boolean swapWithAnotherStud(String courseCode, int indexNumber1, int indexNumber2 , Student stud1, Student stud2)
    {
        if (!checkCodeString(courseCode)) return false;
        Course course = map.get(courseCode);
        if (course.swapIndexWithAnotherOne(indexNumber1, indexNumber2 , stud1, stud2)) {
            System.out.println("Course " + courseCode + ": Student " + stud1.getStudentName() + ", index " + indexNumber1 + " swapped with student " + stud2.getStudentName() + ", index " + indexNumber2 + ".");
            return true;
        }
        else {
            System.out.println("Failed.");
            return false;
        }
    }

    /**
     * print vacancy of a specific index
     * @param courseCode course code
     * @param indexNumber index number
     */
    public void getVacancies(String courseCode, int indexNumber) {
        int vac = getVacByIndex(courseCode, indexNumber);
        if (vac < 0){
            System.out.println("Index number " + indexNumber + " does not belong to " + courseCode + ".");
        }
        else{
            System.out.println("-------------------------------");
            System.out.println("Course code: "+courseCode);
            System.out.println("Index: "+indexNumber);
            System.out.println("Vacancies: "+vac);
            System.out.println("-------------------------------");
        }
    }

    /**
     * print all vacancies in one course
     * @param courseCode course code
     */
    public void printAllVacancies(String courseCode) {
        if (!checkCodeString(courseCode)) return;
        Course course = map.get(courseCode);
        List<Index> indexes = course.getAllIndex();
        for (Index i : indexes) {
            System.out.println(i);
        }
    }

    //------------------------------helper function------------------------------

    /**
     * Get vacancy of a specific index
     * @param courseCode course code
     * @param indexNumber index number
     * @return -1 if course code is not valid or index does not belong to course, otherwise return vacancy
     */
    private int getVacByIndex(String courseCode, int indexNumber) {
        if (map.containsKey(courseCode)){
            Course course = map.get(courseCode);
            int res = course.getIndexVacancy(indexNumber);
            return res;
        }
        else{
            System.out.println("Invalid course code.");
            return -1;
        }
    }

    /**
     * Check whether course code is valid
     * @param courseCode course code
     * @return true of course code is valid
     */
    private boolean checkCodeString(String courseCode) {
        Course course = map.get(courseCode);
        if (course == null) {
            System.out.println("Invalid Course Code.");
            return false;
        }
        return true;
    }

    /**
     * Add student already registered to register list in index
     * (Initialization only)
     * @param student student
     * @param courseCode course code
     * @param indexNumber index number
     */
    public void addStudentsToRegisteredList(Student student, String courseCode, int indexNumber) {
        Course course = map.get(courseCode);
        course.addRegisteredStudents(student, indexNumber);
    }

    /**
     * Add student already in wait list to waiting list
     * (Initialization only)
     * @param student student
     * @param courseCode course code
     * @param indexNumber index number
     */
    public void addStudentsToWaitList(Student student, String courseCode, int indexNumber) {
        Course course = map.get(courseCode);
        course.addWaitListStudents(student, indexNumber);
    }
}
