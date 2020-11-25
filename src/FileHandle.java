
/**
FileHandle is an abstract class that is inherited by several fileIO handling classes regarding different type of data stored in files.
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
