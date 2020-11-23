import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
Handles all the files and IO related to administrators. 
It is a subclass of the Filehandle class.  
@author Group3_SS6_CZ2002
@version 1.0
@since 2020-11-22
*/
class AllAdmins extends FileHandle{
    /**
     * This is a list that stores the admin information read from the .dat file into admin objects.  
     * The content of the list is written to the files after operations are done. 
     */
    private List<Admin> adminList;

    /**
     * Constructor of the AllAdmins class. 
     */
    public AllAdmins(){

    }

    /**
     * Gets the full adminList
     */
    public List<Admin> getList(){
        return adminList;
    }

    /**
     * File-reading function. The file name for the admin file should be admin.dat
     * If the process fails, stacktrace will be printed to show what went wrong. 
     * The adminList attribute holds the data read from the file. 
     */
     public void deserializeFromFile(){
        List<Admin> oDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("admin.dat");
			in = new ObjectInputStream(fis);
			oDetails = (List<Admin>) in.readObject();
			in.close();
		} catch (IOException | ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		this.adminList = oDetails;
		
    }

    /**
     * File-writing function. The file name for the admin file should be admin.dat
     * If the process fails, stacktrace will be printed to show what went wrong. 
     * The adminList attribute holds the data to be written to the file. 
     */
    public void serializeToFile(){
        FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("admin.dat");
			out = new ObjectOutputStream(fos);
			out.writeObject(this.adminList);
			out.close();
		//	System.out.println("Object Persisted");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
    }

	/**
	 * Data for fast testing only
	 * @param args
	 */
	public static void main(String[] args) {
		AllAdmins allAdmins = new AllAdmins();
		allAdmins.adminList = new ArrayList<>();
		allAdmins.adminList.add(new Admin("Ong4829"));
		allAdmins.serializeToFile();
	}

}
