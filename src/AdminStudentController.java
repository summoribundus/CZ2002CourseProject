import java.util.Map;
import java.time.*;
import java.util.Date;
import java.text.SimpleDateFormat;

class AdminStudentController{
    private Map<String, Student> studentMap;
    public AdminStudentController(Map<String, Student> map) {
        this.studentMap = map;
    }

    public boolean checkExistingStudent(String matric){
        return studentMap.containsKey(matric);
    }

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