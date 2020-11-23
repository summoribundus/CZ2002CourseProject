/**
 * Responsible for serialize and deserialize all courses
 * @author Li Rui, Ye Ziyuan (names not listed in order)
 * @version 1.0
 * @since 2020-11-22
 */
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
public class  AllCourse extends FileHandle{
    /**
     * Map containing all course
     * Key: course code
     * Value: course object
     */
    private Map<String, Course> map;

    /**
     * Default constructor
     */
    public AllCourse() {}

    /**
     * Serialize map containing all course object to file
     */
    public void serializeToFile() {
        try {
            if (map != null) {
                FileOutputStream fileOut =
                    new FileOutputStream("courseInfo.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(map);
            out.close();
            fileOut.close();
            System.out.printf("Serialized data is saved in courseInfo.ser");
            }
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    /**
     * Deserialize all course object from file
     */
    public void deserializeFromFile() {
        try {
            FileInputStream fileIn = new FileInputStream("courseInfo.ser");
            ObjectInputStream in = new ObjectInputStream(fileIn);
            map = (Map<String, Course>) in.readObject();
            in.close();
            fileIn.close();
        } catch (IOException e) {
            map = new HashMap<String, Course>();
        } catch (ClassNotFoundException i) {
            i.printStackTrace();
        }
    }

    /**
     * Get the map containing all course information
     * @return course map
     */
    public Map<String, Course> getMap() {
        return map;
    }

    /**
     * Set the map containing all course information
     * @param map course map
     */
    public void setMap(Map<String, Course> map) {
        this.map = map;
    }

    public void initialize() {
        for (Course course : map.values()) {
            course.initializeIndex();
        }
    }

    /**
     * Data for fast testing only
     * @param args
     */
    public static void main(String[] args) {
        AllCourse allCourse = new AllCourse();
        allCourse.map = new HashMap<>();
        List<CourseType> list = new ArrayList<>();
        list.add(CourseType.CORE);
        list.add(CourseType.UE);
        allCourse.map.put("CZ2001", new Course("Algorithms", "CZ2001", "SCSE", list, 3));
        list = new ArrayList<>();
        list.add(CourseType.CORE);
        list.add(CourseType.GER_PE_STS);
        allCourse.map.put("CZ2002", new Course("Object Oriented Design and Programming", "CZ2002", "SCSE", list, 3));
        list = new ArrayList<>();
        list.add(CourseType.CORE);
        list.add(CourseType.GER_PE_LA);
        list.add(CourseType.UE);
        allCourse.map.put("HE9091", new Course("Principle of Economics", "HE9091", "SSS", list, 3));
        list = new ArrayList<>();
        list.add(CourseType.CORE);
        list.add(CourseType.UE);
        allCourse.map.put("MH1200", new Course("Linear Algebra", "MH1200", "SPMS", list, 4));

        Timetable timetable = new Timetable();
        List<String> times = new ArrayList<>();
        times.add("Mon;8:30-10:30;LEC;All;LT1");
        times.add("Tue;11:30-12:30;TUT;All;TR19");
        times.add("Fri;14:30-16:30;SEM;Odd;HWLAB3");
        timetable.addSlots(times);
        allCourse.map.get("CZ2001").addIndex(12345, 10, timetable);
        timetable = new Timetable();
        times = new ArrayList<>();
        times.add("Mon;8:30-10:30;LEC;All;LT2A");
        times.add("Fri;9:30-10:30;TUT;All;TR8");
        times.add("Wed;8:30-8:30;SEM;Even;HWLAB1");
        timetable.addSlots(times);
        allCourse.map.get("CZ2001").addIndex(34567, 10, timetable);
        timetable = new Timetable();
        times = new ArrayList<>();
        times.add("Mon;12:30-13:30;LEC;All;LT2A");
        times.add("Wed;9:30-10:30;TUT;All;TR8");
        times.add("Tue;15:30-17:30;SEM;Odd;HWLAB1");
        timetable.addSlots(times);
        allCourse.map.get("CZ2002").addIndex(23456, 10, timetable);
        timetable = new Timetable();
        times = new ArrayList<>();
        times.add("Mon;12:30-13:30;LEC;All;LT2A");
        times.add("THURS;10:30-11:30;TUT;All;TR8");
        times.add("Wed;13:30-15:30;SEM;Even;HWLAB1");
        timetable.addSlots(times);
        allCourse.map.get("CZ2002").addIndex(45678, 10, timetable);

        Timetable timetable_9091_i1 = new Timetable();
        List<String> times_9091_i1 = new ArrayList();
        times_9091_i1.add("Mon;8:30-10:30;LEC;All;SSLT19");
        times_9091_i1.add("Tue;11:30-12:30;SEM;All;SR10");
        timetable_9091_i1.addSlots(times_9091_i1);
        allCourse.map.get("HE9091").addIndex(12341, 10, timetable_9091_i1);
        Timetable timetable_9091_i2 = new Timetable();
        List<String> times_9091_i2 = new ArrayList();
        times_9091_i2 .add("Mon;8:30-10:30;LEC;All;SSLT19");
        times_9091_i2 .add("Fri;13:30-14:30;SEM;All;SR4");
        timetable_9091_i2.addSlots(times_9091_i2);
        allCourse.map.get("HE9091").addIndex(23452, 10, timetable_9091_i2);
        Timetable timetable_9091_i3 = new Timetable();
        List<String> times_9091_i3 = new ArrayList();
        times_9091_i3.add("Mon;14:30-16:30;LEC;All;SSLT18");
        times_9091_i3.add("THURS;13:30-14:30;SEM;All;SR6");
        timetable_9091_i3.addSlots(times_9091_i3);
        allCourse.map.get("HE9091").addIndex(34563, 10, timetable_9091_i3);

        Timetable timetable_1200_i1 = new Timetable();
        List<String> times_1200_i1 = new ArrayList();
        times_1200_i1.add("Wed;12:30-13:30;LEC;All;SSLT27");
        times_1200_i1.add("Mon;8:30-9:30;LEC;All;SSLT22");
        times_1200_i1.add("Tue;9:30-10:30;TUT;All;SPMSTR7");
        timetable_1200_i1.addSlots(times_1200_i1);
        allCourse.map.get("MH1200").addIndex(45674, 10, timetable_1200_i1);

        Timetable timetable_1200_i2 = new Timetable();
        List<String> times_1200_i2 = new ArrayList();
        times_1200_i2.add("Wed;12:30-13:30;LEC;All;SSLT27");
        times_1200_i2.add("Mon;8:30-9:30;LEC;All;SSLT22");
        times_1200_i2.add("Fri;15:30-16:30;TUT;All;SPMSTR11");
        timetable_1200_i2.addSlots(times_1200_i2);
        allCourse.map.get("MH1200").addIndex(56785, 10, timetable_1200_i2);

        Timetable timetable_1200_i3 = new Timetable();
        List<String> times_1200_i3 = new ArrayList();
        times_1200_i3.add("Wed;12:30-13:30;LEC;All;SSLT27");
        times_1200_i3.add("Mon;8:30-9:30;LEC;All;SSLT22");
        times_1200_i3.add("THURS;8:30-9:30;TUT;All;SPMSTR5");
        timetable_1200_i3.addSlots(times_1200_i3);
        allCourse.map.get("MH1200").addIndex(12348, 10, timetable_1200_i3);

        allCourse.serializeToFile();
    }
    
}