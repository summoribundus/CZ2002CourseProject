import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
/**
 * A login entity class
 *
 */
class AllLogin extends FileHandle{
	/**
	 * Create private object
	 */
	private List passwordList;

	/**
	 * Create constructor
	 */
	public AllLogin(){
	}

	/**
	 * A method to get list
	 * @return
	 */
	public List getList(){
		return passwordList;
	}

	/**
	 * A method to set login list
	 * @param lst
	 */
	public void setList(List<Login> lst){
		this.passwordList = lst;
	}

	/**
	 * A method to deserialize from password.dat file
	 */
	public void deserializeFromFile(){
		List oDetails = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream("password.dat");
			in = new ObjectInputStream(fis);
			oDetails = (List) in.readObject();
			in.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		this.passwordList = oDetails;

	}

	/**
	 * A method to serialize from password.dat file
	 */
	public void serializeToFile(){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try {
			fos = new FileOutputStream("password.dat");
			out = new ObjectOutputStream(fos);
			out.writeObject(this.passwordList);
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
		Login l1 = new Login("U123451A","1234a",1);
		Login l2 = new Login ("U234562B","1234b",1);
		Login l3 = new Login ("U345673C","1234c",1);
		Login l4 = new Login("U456784D","1234d",1);
		Login l5 = new Login ("U123455E","1234e",1);
		Login l6 = new Login ("U123456F","1234f",1);
		Login l7 = new Login ("U234567G","1234g",1);
		Login l8 = new Login ("U345678H","1234h",1 );
		Login l9 = new Login ("U456789I","1234i",1);
		Login l10 = new Login ("U123451J","1234j",1);
		Login l11 = new Login ("U123452K","1234k",1);
		Login l12 = new Login ("U234563L","1234l",1);
		Login l13 = new Login ("U345674M","1234m",1);
		Login l14 = new Login ("U456785N","1234n",1);
		Login l15 = new Login ("U123456O","1234o",1);
		Login l16 = new Login("Ong4829","good782",2);
		Login arr[] = {l1,l2,l3, l4,l5,l6, l7,l8,l9, l10,l11,l12, l13, l14,l15, l16};
		List<Login> ll = Arrays.asList(arr);
		FileHandle al = new AllLogin();
		((AllLogin)al).setList(ll);
		al.serializeToFile();
	}
}