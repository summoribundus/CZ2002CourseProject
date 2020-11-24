import java.util.Map;
import java.time.*;
import java.text.SimpleDateFormat;

/**
 * A controller class which operates necessary backend functions for student boundary
 *
 *
 */
public class StudentController {
    /**
     * Declare private objects
     */
    private Map<String, Student> studentMap;
    private StudentCourseController scc;

    /**
     * Create a studentcontroller constructor
     * @param studentMap
     * @param scc
     */
    public StudentController(Map<String, Student> studentMap, StudentCourseController scc){
        this.studentMap = studentMap;
        this.scc = scc;
    }

    /**
     * A method to notify student for student swaping
     * @param matric
     * @param otp
     * @return
     */
    public boolean notifyStudent(String matric, String otp){
        if (studentMap.containsKey(matric)==false){
            System.out.println("peer does not exist!!");
            return false;
        }
        else{
            Student peer = studentMap.get(matric);
            String subject = "Peer Swap OTP";
            String content = " Dear Student,\nYour OTP for peer swap is " + otp;
            Notifier notifier = new Email();
            notifier.notify(subject, content, peer.getStudentEmail());
            return true;
        }
    }

    /**
     * A method to add Course by calling course
     * @param matric
     * @param courseCode
     * @param indexNumber
     */
    public void addCourse(String matric, String courseCode, int indexNumber){
        if (!studentMap.containsKey(matric)){
            System.out.println("Student does not exist!!");
        }
        else{
            Student s = studentMap.get(matric);
            if (s.getCoursesRegistered().containsKey(courseCode) || s.getCoursesWaitlist().containsKey(courseCode)){
                System.out.println("Course "+courseCode+" already added");
            }
            else{
                scc.addStudent(courseCode, indexNumber, s);
            }

        }
    }

    /**
     * A method to drop Course by calling course
     * @param matric
     * @param courseCode
     */
    public void dropCourse(String matric, String courseCode){
        if (studentMap.containsKey(matric)==false){
            System.out.println("Student does not exist!!");
        }
        else{
            Student s = studentMap.get(matric);
            try{
                if (s.getCoursesRegistered().containsKey(courseCode) == false)
                    throw new Exception("You are not registered for the course");
                int indexNumber = s.getIndexforCoursesRegistered(courseCode);
                scc.dropStudent(courseCode, indexNumber, s);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }

        }
    }

    /**
     * A method to change index by calling course
     * @param matric
     * @param courseCode
     * @param newIndex
     */
    public void changeIndex(String matric, String courseCode, int newIndex){
        if (studentMap.containsKey(matric)==false){
            System.out.println("Student does not exist!!");
        }
        else{
            Student s = studentMap.get(matric);
            try{
                if (s.getCoursesRegistered().containsKey(courseCode) == false)
                    throw new Exception("You are not registered for the course");
                int oldIndex = s.getIndexforCoursesRegistered(courseCode);
                System.out.println(oldIndex);
                scc.swapIndexStudent(courseCode, oldIndex, newIndex, s);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * A method to swap index with another student by calling course
     * @param sMatric
     * @param peerMatric
     * @param courseCode
     */
    public void swapIndexWithStudent(String sMatric, String peerMatric, String courseCode){
        if (studentMap.containsKey(sMatric)==false || studentMap.containsKey(peerMatric)== false){
            System.out.println("Peer does not exist!!");
        }
        else{
            Student stud = studentMap.get(sMatric);
            Student peer = studentMap.get(peerMatric);
            try{
                if (stud.getCoursesRegistered().containsKey(courseCode) == false)
                    throw new Exception("You are not registered for the course");
                int index = stud.getIndexforCoursesRegistered(courseCode);
                if (peer.getCoursesRegistered().containsKey(courseCode) == false)
                    throw new Exception("Your peer is not registered for the course");
                int peerIndex = peer.getIndexforCoursesRegistered(courseCode);
                scc.swapWithAnotherStud(courseCode, index, peerIndex, stud, peer);
            }
            catch(Exception e){
                System.out.println(e.getMessage());
            }


        }
    }

    /**
     * A method to show course vancies by calling course
     * @param courseCode
     * @param index
     */
    public void showVacancies(String courseCode, int index){
        scc.getVacancies(courseCode, index);
    }

    /**
     * A method to print course registered 
     * @param matric
     */
    public void printCoursesRegistered(String matric){
        if (studentMap.containsKey(matric)==false){
            System.out.println("Student does not exist!!");
        }
        else{
            Student s = studentMap.get(matric);
            System.out.println("----------\n Courses Registered\n----------");
            for (String courseCode : (s.getCoursesRegistered()).keySet()){
                s.printIndexTimeTable(courseCode);
            }

        }
    }

    /**
     * A method to check Allowed period of student
     * @param matricNo
     * @return
     */
    public boolean checkAllowedPeriod(String matricNo) {

        Student student = studentMap.get(matricNo);
        LocalDateTime[] accessPeriod = student.getAccessPeriod();
//        System.out.println(accessPeriod);
        LocalDateTime now = LocalDateTime.now();
        System.out.println(now);
        if (now.isAfter(accessPeriod[0])&&now.isBefore(accessPeriod[1])) {
            return true;
        } else {
            return false;
        }
    }
}

