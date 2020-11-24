
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * A entity class for student
 *
 */
public class AllStudent extends FileHandle{
    private Map<String, Student> studentMap;
    public AllStudent(){}

    /**
     * A method to serialize file
     */
    public void serializeToFile() {
        try {
            if (studentMap != null) {
                FileOutputStream fileOut =
                        new FileOutputStream("Student.ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(studentMap);
                out.close();
                fileOut.close();
                System.out.printf("Serialized data is saved in Student.ser\n");
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * A method to deserialize from file
     */
    public void deserializeFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("Student.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            studentMap =(Map<String, Student>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            studentMap = new HashMap<String, Student>();
        } catch (ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * A method to get student map
     * @return map containing all students
     */
    public Map<String, Student> getStudentMap() {
        return studentMap;
    }

    /**
     * A method to set student map
     * @param studentMap map containing all students
     */
    public void setMap(Map<String, Student> studentMap) {
        this.studentMap = studentMap;
    }

    /**
     * A method to check access period
     * @param s string representing access period
     * @return start and end access period
     */
    private LocalDateTime[] parseToAccessPeriod(String s) {
        try {
            String arr[] = s.split("-");
            SimpleDateFormat parser = new SimpleDateFormat("yyyy/MM/dd HH:mm");
            Date opening = parser.parse(arr[0]);
            Date closing = parser.parse(arr[1]);
            LocalDateTime openingTime = LocalDateTime.ofInstant(opening.toInstant(), ZoneId.systemDefault());
            LocalDateTime closingTime = LocalDateTime.ofInstant(closing.toInstant(), ZoneId.systemDefault());
            LocalDateTime[] access = {openingTime, closingTime};
            return access;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Data for fast testing only
     * @param args
     */
    public static void main(String[] args) {
        AllStudent allStudent = new AllStudent();
        allStudent.studentMap = new HashMap<>();
        allStudent.studentMap.put("U123451A", new Student("Elsa", 'F', "Singapore", "U123451A", "summoribundus@gmail.com"));
        allStudent.studentMap.put("U234562B", new Student("Ellen", 'M', "China", "U234562B", "ziyuan1029@gmail.com"));
        allStudent.studentMap.put("U345673C", new Student("Agnes", 'F', "Korea", "U345673C", "summoribundus@gmail.com"));
        allStudent.studentMap.put("U456784D", new Student("Victor", 'M', "Japan", "U456784D", "ziyuan1029@gmail.com"));
        allStudent.studentMap.put("U123455E", new Student("Skye", 'F', "America", "U123455E", "summoribundus@gmail.com"));
        allStudent.studentMap.put("U123456F", new Student("Nico", 'F', "Italy", "U123456F", "summoribundus@gmail.com"));
        allStudent.studentMap.put("U234567G", new Student("Mark", 'M', "France", "U234567G", "ziyuan1029@gmail.com"));
        allStudent.studentMap.put("U345678H", new Student("Lucas", 'M', "Spain", "U345678H", "summoribundus@gmail.com"));
        allStudent.studentMap.put("U456789I", new Student("Yeats", 'M', "Brazil", "U456789I", "ziyuan1029@gmail.com"));
        allStudent.studentMap.put("U123451J", new Student("Hellen", 'F', "India", "U123451J", "summoribundus@gmail.com"));
        allStudent.studentMap.put("U123452K", new Student("Harry", 'M', "Malaysia", "U123452K", "ziyuan1029@gmail.com"));
        allStudent.studentMap.put("U234563L", new Student("Potter", 'M', "Mexico", "U234563L", "summoribundus@gmail.com"));
        allStudent.studentMap.put("U345674M", new Student("Marx", 'M', "Mongolia", "U345674M", "ziyuan1029@gmail.com"));
        allStudent.studentMap.put("U456785N", new Student("Mill", 'M', "Argentina", "U456785N", "summoribundus@gmail.com"));
        allStudent.studentMap.put("U123456O", new Student("Alex", 'M', "Congo", "U123456O", "ziyuan1029@gmail.com"));

        allStudent.studentMap.get("U123451A").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U234562B").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U345673C").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U456784D").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U123455E").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U123456F").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U234567G").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U345678H").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U456789I").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U123451J").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U123452K").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U234563L").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U345674M").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U456785N").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));
        allStudent.studentMap.get("U123456O").setAccessPeriod(allStudent.parseToAccessPeriod("2020/11/21 12:00-2020/12/01 12:00"));


        allStudent.studentMap.get("U123451A").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U234562B").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U345673C").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U456784D").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U123455E").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U123456F").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U234567G").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U345678H").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U456789I").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.studentMap.get("U123451J").getCoursesRegistered().put("CZ2001", 12345);
        allStudent.serializeToFile();

    }
}