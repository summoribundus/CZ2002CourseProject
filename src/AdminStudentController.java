import java.util.Map;
import java.time.*;
import java.util.Date;
import java.text.SimpleDateFormat;

/**
Controller class used by the admin users to modify students through the admin boundary class
*/
class AdminStudentController{
    /**
	 * The hashmap that contains the student id as the key and the student object as the value. 
	 */
    private Map<String, Student> studentMap;
    /**
     * Constructor of the AdminStudentController class. 
     * @param map The hashmap that contains the student id and objects. 
     */
    public AdminStudentController(Map<String, Student> map) {
        this.studentMap = map;
    }

    /**
     * Checks whether a student exists with the matric number (id)
     * @param matric A string that contains the student's matric number that is to be checked. 
     */
    public boolean checkExistingStudent(String matric){
        return studentMap.containsKey(matric);
    }

    /**
     * Adds a student with his/her basic information. 
     * <p>
     * The function first checks if a student with that matric number exists. 
     * @param name The name of the student.
     * @param gender The gender of the student.
     * @param nation The nationality of the student.
     * @param matric The matric number of the student.
     * @param emailId The Email id of the student.
     */
    public void addStudent (String name, char gender, String nation, String matric, String emailId){
        if (studentMap.containsKey(matric)){
            System.out.println("Matric No: " + matric + " already exist.");
        }
        else {
            Student s = new Student(name, gender, nation, matric, emailId);
            studentMap.put(matric, s);

            System.out.println("----------\nStudent List:\n----------");
            for (String key: studentMap.keySet()){
                s = studentMap.get(key);
                System.out.println(s.getStudentMatric()+" Name: "+s.getStudentName());
            }
            System.out.println("----------");
        }
    }

    /**
     * Modifies the access period of a student. 
     * <p>
     * it first checks if the student with the given matric number exists. 
     * <p>
     * After that, it checks whether the string format is valid. 
     * @param matricNo The matric number of the student.
     * @param newAccessPeriod The modified access period. it is given in a string of a specific format, which is "yyyy/MM/dd HH:mm-yyyy/MM/dd HH:mm"
     */
    public void editAccessPeriod(String matricNo, String newAccessPeriod){
        try{
            if (studentMap.containsKey(matricNo)==false){
                System.out.println("Matric No: does not exist");
            }
            else{
                Student s = studentMap.get(matricNo);
                String arr[] = newAccessPeriod.split("-");
                SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd HH:mm");
                Date opening = parser.parse(arr[0]);
                Date closing = parser.parse(arr[1]);
                LocalDateTime openingTime = LocalDateTime.ofInstant(opening.toInstant(),ZoneId.systemDefault());
                LocalDateTime closingTime = LocalDateTime.ofInstant(closing.toInstant(),ZoneId.systemDefault());
                LocalDateTime[] access = {openingTime,closingTime};
                if (access[1].isBefore(access[0]))
                    throw new Exception("Please check the access period you entered....\nInvalid access period!\nAttempt failed");
                s.setAccessPeriod(access);
            }

        }
        catch (Exception e){
            System.out.println(e.getMessage());
            System.out.println("Choose option 1 again to re-enter the details!");
        }

    }

}
