
/**
FileHandle is an abstract class that is inherited by several fileIO handling classes regarding different type of data stored in files. 
@author Group3_SS6_CZ2002
@version 1.0
@since 2020-11-22
*/
public abstract class FileHandle {
	/**
	 * Abstract function for reading from file. 
	 */
	public void serializeToFile(){}
	/**
	 * Abstract function for writing to file. 
	 */
	public void deserializeFromFile() {}
}
