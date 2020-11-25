import java.util.Scanner;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
The interface (console) of administrators after login. 
<p>
Admin users may choose actions to perform. 
<p>
This class calls methods in respective controller classes.
*/
public class AdminBoundary {
    /**
	 * The controller class object for AdminCourseController
	 */
    private AdminCourseController acc;
    /**
	 * The controller class object for AdminStudentController
	 */
    private AdminStudentController asc;
    /**
	 * The scanner object to accept user input. 
	 */
    private Scanner sc;

    /**
     * Constructor of the AdminBoundaryClass. The two controller class objects should be passed in while the scanner object is created inside the constructor. 
     * @param acc the AdminCourseController object
     * @param asc the AdminStudentController object
     */
    public AdminBoundary(AdminCourseController acc, AdminStudentController asc){
        this.acc = acc;
        this.asc = asc;
        this.sc = new Scanner(System.in);
    }


    /**
	 * Print the menu of actions. Then the function listens for the user's input that represents the action they wish to perform. 
	 * After the user enters a choice, the function calls respective method.   
	 */
    public void printMenu(){
        System.out.println("-------------------\n    Admin Menu     \n-------------------");
        System.out.println("1. Edit student access period");
        System.out.println("2. Add student");
        System.out.println("3. Add course");
        System.out.println("4. Update Course");
        System.out.println("5. Check vacancy by Index Number");
        System.out.println("6. Print Student list by Index number");
        System.out.println("7. Print Student list by Course");
        while (true){
            System.out.println("----------------------------------------");
            System.out.println("Enter choice (Admin): (Enter 0 to stop)");
            int ch = sc.nextInt();
            sc.nextLine();
            if (ch == 0){
                break;
            }
            switch(ch){
                case 1: this.editAcessPeriod();
                        break;
                case 2: this.addStudent();
                        break;
                case 3: this.addCourse();
                        break;
                case 4: this.updateCourse();
                        break;
                case 5: this.checkVacancy();
                        break;
                case 6: this.printByIndex();
                        break;
                case 7: this.printByCourse();
                        break;
                default: System.out.println("Invalid choice");
            }

        }
    }

    /**
     * The method for editing access period of a student. 
     * It asks for user input of the matric number of a student and the new access period. 
     * If the matric number entered by the user is not valid, the function informs the user that student does not exist. 
     */
    public void editAcessPeriod(){
        
        System.out.println ("---------------Editing access period---------------");
        System.out.print("Enter Matric No: ");
        String matricNo = (sc.nextLine()).toUpperCase();
        if(asc.checkExistingStudent(matricNo)){
            System.out.print("Enter new Access period :\n Format: yyyy/MM/dd HH:mm-yyyy/MM/dd HH:mm\n");
            String access = sc.nextLine();
            asc.editAccessPeriod(matricNo, access);
            
        }
        else{
            System.out.println("Student does not exist!");
        }
        
    }

    /**
     * The method for adding a student.
     * It asks for user input of the information required for a student. 
     * Then, with the data provided, the function calls the addStudent method of the asc object. 
     * Any exceptions will be printed. 
     */
    public void addStudent(){
        try{
            System.out.println ("---------------Adding a new Student---------------");
            boolean res = false;
            System.out.print("Enter Name: ");
            String name = sc.nextLine();
            System.out.print("Enter Matric No: ");
            String matric = (sc.nextLine()).toUpperCase();
            System.out.print("Enter nationality: ");
            String nation = sc.nextLine();
            System.out.print("Enter valid email address: ");
            String email = sc.nextLine();

            System.out.print("Enter gender (Male/Female): ");
            String gender = sc.nextLine();
            if (name.equals("") || matric.equals("") || nation.equals("") || email.equals("")
                    || !(gender.toUpperCase().equals("MALE") || gender.toUpperCase().equals("FEMALE"))) {
                System.out.println("Invalid data entry. Re-enter everything");
            } else if (gender.toUpperCase().equals("MALE"))
                asc.addStudent(name, '1', nation, matric,email);
            else
                asc.addStudent(name, '0', nation, matric,email);
        }
        catch(Exception e){
            System.out.println(e.getMessage());
        }
        
    }


    /**
     * The method for adding a course.
     * It asks for user input of the information required for a course. 
     * Then, with the data provided, the function calls the addCourse method of the acc object. 
     * The method informs the user when an error occurs and asks the user to input a valid course type. 
     */
    public void addCourse(){
        try{
            System.out.println ("---------------Adding a new Course---------------");
            System.out.print("Enter Course Name: ");
            String Name = sc.nextLine();
            System.out.print("Enter Course Code: ");
            String code = (sc.nextLine()).toUpperCase();
            System.out.print("Enter Course School: ");
            String school = sc.nextLine();
            List <CourseType> availableType = new ArrayList<CourseType>();

            System.out.print("Enter available course types seprated by , (no spaces): ");
            String str = sc.nextLine();

            if (Name.equals("") || code.equals("") || school.equals(""))
                throw new IllegalArgumentException();

            String[] arr = str.split(",");
            for (String st: arr){
                availableType.add(CourseType.valueOf(st));
            }

            System.out.print("Enter AU: ");
            int AU = sc.nextInt();
            acc.addCourse(Name, code, school, availableType, AU);
        }
        catch (Exception e){
            System.out.println("Data Entry Invalid");
        }
    }

    /**
     * The method for updating information of a course.
     * <p>
     * It asks the user to choose the fields to update by entering number(s). 
     * Then, the method asks the user to enter information regarding the course to update and the new information. 
     * <p>
     * The method calls methods in the acc to modify information of the course. 
     * The method informs the user when an error occurs and asks the user to input a valid course type. 
     */
    public void updateCourse(){
        System.out.println ("---------------Updating Courses---------------");
        System.out.println("1. Update Course Name");
        System.out.println("2. Update Course School");
        System.out.println("3. Update Course Index vacancy");
        System.out.println("4. Add new index to Course");
        System.out.println("5. Delete index from course");
        System.out.println("6. Add new course type to Course");
        System.out.println("7. Delete course type from course");
        System.out.println("8. Update Course Code");
        String code;
        int index;
        boolean flag = true;
        while (flag){
            System.out.println("Enter choice");
            int ch = sc.nextInt();
            sc.nextLine();
        switch(ch){
                case 1: try{
                        System.out.print("Enter Course Code: ");
                        code = sc.nextLine();
                        System.out.print("Enter new Course Name: ");
                        String name = sc.nextLine();
                        acc.updateCourseName(code, name);
                        flag = false;
                        }
                        catch(Exception e){
                            System.out.println("Enter valid course code....");
                            flag = true;
                        }   
                        break;
                case 2: try{
                        System.out.print("Enter Course Code: ");
                        code = (sc.nextLine()).toUpperCase();
                        System.out.print("Enter new Course School: ");
                        String name = sc.nextLine();
                        acc.updateCourseSchool(code, name);
                        flag = false;
                        }
                        catch(Exception e){
                            flag = true;
                            System.out.println("Enter valid course code....");
                        }   
                        break;
                case 3:try{
                        System.out.print("Enter Course Code: ");
                        code = (sc.nextLine()).toUpperCase();
                        System.out.print("Enter Index Number: ");
                        index = sc.nextInt();
                        System.out.print("Enter new Vacancy: ");
                        int vac = sc.nextInt();
                        acc.updateCourseIndexVacancy(code, index, vac );
                        flag = false;
                        }
                        catch(Exception e){
                            flag = true;
                            System.out.println("Enter valid vacancy");
                        }   
                        break;
                case 4: try{
                        System.out.print("Enter Course Code: ");
                        code = (sc.nextLine()).toUpperCase();
                        System.out.print("Enter new Index number: ");
                        int newIndex  = sc.nextInt();
                        System.out.print("Enter new Index number capacity: ");
                        int cap  = sc.nextInt();
                        System.out.print("Enter Timetable (Time slot Format : Mon;8:30-10:30;SEM;All/Odd/Even;HWLAB3 ) \n Enter timeslots seprated by comma (no spaces)\n");
                        String str = sc.nextLine();
                        String[] time = str.split(",");
                        List<String> timeslots = Arrays.asList(time);
                        acc.addCourseIndex(code, newIndex, cap,timeslots);
                        flag = false;
                        }
                        catch(Exception e){
                            flag = true;
                            System.out.println("Enter valid capacity");
                        }   
                        break;
                case 5:try{
                        System.out.print("Enter Course Code: ");
                        code = (sc.nextLine()).toUpperCase();
                        System.out.print("Enter Index Number to be deleted: ");
                        index = sc.nextInt();
                        acc.deleteCourseIndex(code, index);
                        flag = false;
                        }
                        catch(Exception e){
                            flag =true;
                            System.out.println("Enter valid course code.......");
                        }   
                        break;
                case 6:try{
                    System.out.print("Enter Course Code: ");
                    code = (sc.nextLine()).toUpperCase();
                    System.out.print("Enter new Course type to be added: ");
                    String name = sc.nextLine();
                    acc.addCourseType(code, CourseType.valueOf(name));
                    flag = false;
                    }
                    catch(Exception e){
                        System.out.println("Enter valid course type........");
                        flag = true;
                    }   
                    break;
                case 7:try{
                    System.out.print("Enter Course Code: ");
                    code = (sc.nextLine()).toUpperCase();
                    System.out.print("Enter Course type to be deleted: ");
                    String name = sc.nextLine();
                    acc.deleteCourseType(code, CourseType.valueOf(name));
                    flag = false;
                    }
                    catch(Exception e){
                        System.out.println("Enter valid course type......");
                        flag = true;
                    }   
                    break;
                case 8: try{
                    System.out.print("Enter Course Code: ");
                    code = (sc.nextLine()).toUpperCase();
                    System.out.print("Enter new Course Code: ");
                    String newCode = (sc.nextLine()).toUpperCase();
                    acc.updateCourseCode(code, newCode);
                    flag = false;
                    }
                    catch(Exception e){
                        System.out.println("Enter valid course code that exists");
                        flag = true;
                    }
		break;
                default: System.out.println("Re-enter the choice..................In valid choice!");
            }
        }
    }


    /**
     * The method for checking vacancy in a course
     * It asks the user to enter course code of the course to check on. 
     * Then, the function calls the checkVacByAdmin method of the acc object. 
     */
    public void checkVacancy(){
        System.out.println ("---------------Check available slots for an index number ---------------");
        System.out.print("Enter Course Code: ");
        String code = (sc.nextLine()).toUpperCase();
        System.out.print("Enter Index Number: ");
        int index = sc.nextInt();
        acc.checkVacByAdmin(code, index);
    }

    /**
     * The method for printing students by index number. 
     * It asks the user to enter course code and index number they wish to print the student list for. 
     * Then, the function calls the printStudentsByIndexNumber method of the acc object. 
     */
    public void printByIndex(){
        System.out.println ("---------------Printing students by Index ---------------");
        System.out.print("Enter Course Code: ");
        String code = (sc.nextLine()).toUpperCase();
        System.out.print("Enter Index Number: ");
        int index = sc.nextInt();
        acc.printStudentsByIndexNumber(code, index);
    }

    /**
     * The method for printing students by course. 
     * It asks the user to enter course code they wish to print the student list for. 
     * Then, the function calls the printStudentsByCourse method of the acc object. 
     */
    public void printByCourse(){
        System.out.println ("---------------Printing students by Course ---------------");
        System.out.print("Enter Course Code: ");
        String code = (sc.nextLine()).toUpperCase();
        System.out.println("---------------Students in "+code+"---------------");
        acc.printStudentByCourse(code);

    }

}
