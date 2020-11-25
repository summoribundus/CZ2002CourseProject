
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

/**
 * A login boundary class that displays login options
 *
 */
public class LoginBoundary {
    /**
     * A main Class which displays login UI
     * @param args
     */
    public static void main(String[] args) {
        /**
         * Scanner to scan the inputs
         */
        Scanner scanner = new Scanner(System.in);
        /**
         * Initialise AllCourse
         */
        FileHandle ac = new AllCourse();
        /**
         * Initialise AllStudent
         */
        FileHandle as = new AllStudent();
        /**
         * Initialise AllLogin
         */
        FileHandle al = new AllLogin();
        /**
         * Initialise AllAdmins
         */
        FileHandle ads = new AllAdmins();
        /**
         * call AllCourse deserialize method
         */
        ac.deserializeFromFile();
        /**
         * call AllStudent deserialize method
         */
        as.deserializeFromFile();
        /**
         * call AllLogin deserialize method
         */
        al.deserializeFromFile();
        /**
         * call AllAdmins deserialize method
         */
        ads.deserializeFromFile();

        /**
         * Initialise AllCourse with parse type
         */
        AllCourse allCourse = (AllCourse) ac;
        /**
         * call AllCourse initialize
         */
        allCourse.initialize();
        /**
         * Initialise AllStudent with parse type
         */
        AllStudent allStudent = (AllStudent) as;
        /**
         * Initialise StudentCourseController with parse and get the map
         */
        StudentCourseController studentCourseController = new StudentCourseController(((AllCourse)ac).getMap());

        /**
         * Initialise StudentController with parse and get the map
         */
        StudentController studentController = new StudentController(((AllStudent)as).getStudentMap(),studentCourseController);
        /**
         * Initialise AdminCourseController with parse and get the map
         */
        AdminCourseController adminCourseController = new AdminCourseController(((AllCourse)ac).getMap());
        /**
         * Initialise AdminStudentController with parse and get the map
         */
        AdminStudentController adminStudentController = new AdminStudentController(((AllStudent)as).getStudentMap());
        /**
         * Initialise LoginController with parse and get the map
         */
        LoginController loginController = new LoginController(((AllLogin)al).getList());

        /**
         * To check the student map data
         */
        for (Student student : allStudent.getStudentMap().values()) {
            for (Map.Entry<String, Integer> entry : student.getCoursesRegistered().entrySet()) {
                studentCourseController.addStudentsToRegisteredList(student, entry.getKey(), entry.getValue());
            }
            for (Map.Entry<String, Integer> entry : student.getCoursesWaitlist().entrySet()) {
                studentCourseController.addStudentsToWaitList(student, entry.getKey(), entry.getValue());
            }
        }

        /**
         * prints login interface
         */
        System.out.println("Select your domain: ");
        System.out.println("1. Student");
        System.out.println("2. Admin");

        boolean flg = true;
        /**
         * check the option input and do validation
         */
        while (flg){
            System.out.print("Select an option: (0 to stop) ");

            char num= scanner.next().charAt(0);

            if (num == '0') break;
            //try{
            if (num !='1' && num != '2') {
                System.out.println("Invalid input");
                continue;
            }
            System.out.print("Network username or new username: ");
            String userName = scanner.next();
            /* if you are using intellij or other IDE for testing use this part below this*/

            System.out.print("Password: ");
            String password = scanner.next();

            /*Console part which masked the password, this module only runs with command prompt

            Console console = System.console();
            char[] passwordArray = console.readPassword("Enter the password: ");
            String password = new String(passwordArray);*/

            int num_ = Integer.parseInt(String.valueOf(num));
            boolean flag = loginController.checkPassword(userName, password, num_);

            /**
             * Unsuccessful login
             */
            if (flag == false){
                System.out.println("Incorrect password/ username/ domain details.");
            }
            else{
                flg = false;
                if (num_ == 1){
                    Studentboundry studentboundry = new Studentboundry(userName,studentController);
                }
                else{
                    AdminBoundary adminBoundary = new AdminBoundary(adminCourseController,adminStudentController);
                    adminBoundary.printMenu();
                }
            }
        }
        /**
         * To serialize to file the file from AllCourse
         */
        ac.serializeToFile();
        /**
         * To serialize to file the file from AllStudent
         */
        as.serializeToFile();
        /**
         * To serialize to file the file from AllLogin
         */
        al.serializeToFile();
        /**
         * To serialize to file the file from AllAdmins
         */
        ads.serializeToFile();
    }
}
    
