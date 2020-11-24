
import java.util.List;

/**
 * A controller class which handles login
 *
 */
public class LoginController {

    /**
     *  create private objects
     */
    private List loginList;

    /**
     * Create constructor
     * @param loginData login data
     */
    public LoginController(List loginData){
        this.loginList = loginData;
    }

//-----------------for studentController and AdminController-----------------

    /**
     * A method to check the password with password.dat
     * @param userName1 username
     * @param password password
     * @param num domain
     * @return true if success
     */
    public boolean checkPassword(String userName1 , String password,int num){
        Object tobeChecked = new Login(userName1, password, num);
        for (Object pass: loginList){
            Login p = (Login) pass;
            if (p.equals(tobeChecked))
                return true;
        }
        return false;
    }

}



