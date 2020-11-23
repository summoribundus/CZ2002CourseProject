import java.util.Scanner;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
public class AdminBoundary {
    private AdminCourseController acc;
    private AdminStudentController asc;
    private Scanner sc;

    public AdminBoundary(AdminCourseController acc, AdminStudentController asc){
        this.acc = acc;
        this.asc = asc;
        this.sc = new Scanner(System.in);
    }


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
                default: System.out.println("Re-enter the choice..................In valid choice!");
            }
        }
    }


    public void checkVacancy(){
        System.out.println ("---------------Check available slots for an index number ---------------");
        System.out.print("Enter Course Code: ");
        String code = (sc.nextLine()).toUpperCase();
        System.out.print("Enter Index Number: ");
        int index = sc.nextInt();
        acc.checkVacByAdmin(code, index);
    }

    public void printByIndex(){
        System.out.println ("---------------Printing students by Index ---------------");
        System.out.print("Enter Course Code: ");
        String code = (sc.nextLine()).toUpperCase();
        System.out.print("Enter Index Number: ");
        int index = sc.nextInt();
        acc.printStudentsByIndexNumber(code, index);
    }

    public void printByCourse(){
        System.out.println ("---------------Printing students by Course ---------------");
        System.out.print("Enter Course Code: ");
        String code = (sc.nextLine()).toUpperCase();
        System.out.println("---------------Students in "+code+"---------------");
        acc.printStudentByCourse(code);

    }

}
