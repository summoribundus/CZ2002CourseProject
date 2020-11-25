import java.util.Random;

import java.util.Scanner;

/**
 * This is a class for student boundary which displays menu
 */
public class Studentboundry {


    /**
     * Declare local variable matricNo
     */
    private String matricNo;
    /**
     * Declare local variable sc for StudentController
     */
    private StudentController sc;


    /**
     * Create StudentBoundary Constructor
     * @param matricNo matric number
     * @param sc student controller object
     */
    public Studentboundry(String matricNo, StudentController sc){
        this.matricNo = matricNo;
        this.sc = sc;
        if(this.sc.checkAllowedPeriod(matricNo)==true){
            showMenu(matricNo);
        }
        else{
            System.out.println("Access denied! Please recheck your access period");
        }
    }

    /**
     * A method to show the menu options for student
     * @param stud student
     */
    public void showMenu(String stud) {
        boolean flag = true;
        while (flag) {
            Scanner scanner = new Scanner(System.in);
            System.out.println("-------------------\n    Student Menu     \n-------------------");
            System.out.println("1. Add Course");
            System.out.println("2. Drop Course");
            System.out.println("3. Swap index of the student");
            System.out.println("4. Swap with another student");
            System.out.println("5. Show Vacancies");
            System.out.println("6. Check/Print Course registered");
            System.out.println("7. Exit");
            System.out.print("Enter a number: ");

            int selection = Integer.parseInt(scanner.next());
            switch (selection) {
                case 1:
                    System.out.println("Enter Course Code: ");
                    String code = (scanner.next()).toUpperCase();

                    System.out.println("Enter index number: "); //if index is a size of a array, use array.size()+1  I cannot find it
                    try {
                        int index = Integer.parseInt(scanner.next());
                        sc.addCourse(this.matricNo, code, index);
                        break;
                    } catch (Exception e) {
                        System.out.println("Invalid index. Back to the menu.");
                    }
                    break;

                case 2:
                    System.out.println("Enter Course Code: ");
                    String code1 = (scanner.next()).toUpperCase();

                    sc.dropCourse(this.matricNo, code1);
                    break;


                case 3:
                    System.out.println("Enter Course Code: ");
                    String code3 = (scanner.next()).toUpperCase();

                    System.out.println("Enter new index number: "); //if index is a size of a array, use array.size()+1  I cannot find it


                    try {
                        int index32 = Integer.parseInt(scanner.next());
                        sc.changeIndex(this.matricNo, code3, index32);
                    } catch (Exception e) {
                        System.out.println("Invalid index. Back to the menu.");
                    }
                    break;

                case 4:

                    System.out.println("Enter Course Code: ");
                    String code4 = (scanner.next()).toUpperCase();

                    System.out.println("Enter the peer's matric ID: "); //if index is a size of a array, use array.size()+1  I cannot find it
                    String peermatric = scanner.next();
                    // generating random otp
                    Random rn = new Random();
                    int number = rn.nextInt(999999);
                    String otp = String.format("%06d", number);
                    boolean notify = sc.notifyStudent(peermatric, otp);
                    if (notify) {
                        while (true) {
                            System.out.println("Enter the otp sent to your peer....");
                            String pass = scanner.next();
                            if (pass.compareTo(otp) == 0) {
                                sc.swapIndexWithStudent(this.matricNo, peermatric, code4);
                                break;
                            } else {
                                System.out.println("Wrong otp re-enter ....");
                            }
                        }
                    }

                    break;

                case 5:
                    System.out.println("Enter Course Code: ");
                    String code5 = (scanner.next()).toUpperCase();

                    System.out.println("Enter index number: "); //if index is a size of a array, use array.size()+1  I cannot find it
                    try {
                        int index5 = Integer.parseInt(scanner.next());
                        sc.showVacancies(code5, index5);
                    } catch (Exception e) {
                        System.out.println("Invalid index. Back to the menu.");
                    }
                    break;

                case 6:
                    sc.printCoursesRegistered(this.matricNo);
                    break;
                case 7:
                    flag = false;
                    break;

                default:
                    System.out.println("Invalid Input");
                    break;


            }

        }

    }
}
