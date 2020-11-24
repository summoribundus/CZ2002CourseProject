import java.security.MessageDigest;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
/**
 * A Login entity class
 *
 *
 */
class Login implements Serializable{
    // association with username
    // encryption and checking takes place

    /**
     * Declare private object hashed password
     */
    private String hashed_pass;
    // primary key
    /**
     * Declare private object username
     */
    private String username;


    /**
     * Declare private object domain which domain = 1 for student and domain = 2 for admin
     */
    private int domain;

    /**
     * Create Login constructor
     */
    public Login(){ this.hashed_pass = ""; this.username = "";}


    /**
     * Create Login constructor
     * @param username
     * @param raw
     * @param domain
     */
    public Login(String username, String raw, int domain){
        this.username = username;
        this.hashed_pass = hash(raw);
        this.domain = domain;

    }

    /**
     * A method to set user name
     * @param username
     */
    public void setUsername(String username){
        this.username = username;
    }

    /**
     * A method to set password
     * @param password
     */
    public void setPassword(String password){
        this.hashed_pass = hash(password);
    }

    /**
     * A method to set domain
     * @param domain
     */
    public void setDomain(int domain){
        this.domain = domain;
    }

    /**
     * A method to get domain
     * @return
     */
    public int getDomain(){
        return this.domain;
    }

    /**
     * A method to get password
     * @return
     */
    public String getPassword(){
        return this.hashed_pass;
    }

    /**
     * A method to get user name
     * @return
     */
    public String getUsername(){
        return this.username;
    }

    /**
     * A method to hash the password
     * @param raw
     * @return
     */
    private String hash(String raw){
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(raw.getBytes(StandardCharsets.UTF_8));
            return (bytesToHex(encodedhash));
        }
        catch(Exception e){
            throw new RuntimeException(e);
        }
    }


    /**
     * A method to convert byte to hex
     * @param hash
     * @return
     */
    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if(hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }
    // can use it for polymorphism and LSP
    /**
     * A method to check credentials for login
     */
    @Override
    public boolean equals(Object p1){
        Login p2 = (Login) p1;
        if (this.hashed_pass.compareTo(p2.getPassword()) == 0 && (this.username.toUpperCase()).compareTo(p2.getUsername().toUpperCase())==0 && this.domain == p2.getDomain())
            return true;
        else
            return false;
    }

}