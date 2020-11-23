import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
class AllAdmins extends FileHandle{
    private List<Admin> adminList;

    public AllAdmins(){

    }

    public List<Admin> getList(){
        return adminList;
    }

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