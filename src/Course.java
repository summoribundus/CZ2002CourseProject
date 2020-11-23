/**
 * Represents a course that opens to students
 * @author Ye Ziyuan
 * @version 1.0
 * @since 2020-11-22
 */

import java.util.*;
import java.io.Serializable;

public class Course implements Serializable{
	/**
	 * Course name
	 * Example: Algorithm
	 */
	private String courseName;

	/**
	 * Course code
	 * Example: CZ2001
	 */
	private String courseCode;

	/**
	 * School this course belongs
	 * Example: SCSE
	 */
	private String ofSchool;

	/**
	 * Number of indices course offer
	 */
	private int numOfIndex;

	/**
	 * Type of course this course can be registered as
	 * Example: GER-PE
	 */
	private List<CourseType> availableType;

	/**
	 * All available index of course
	 * Key: index number
	 * Value: index object
	 */
	private Map<Integer, Index> indexInfo;

	/**
	 * Total vacancy for this course
	 */
	private int totalVacancy;

	/**
	 * Number of courses in total
	 */
	private static int numOfCourses = 0;

	/**
	 * AU of this course
	 */
	private int AU;

	/**
	 * Creates a new course
	 * @param cName course name
	 * @param cCode course code
	 * @param ofSchool the school this course belongs
	 * @param cAvailableType course type this course can be registered as
	 * @param au au of this course
	 */
	public Course(String cName, String cCode, String ofSchool, List<CourseType> cAvailableType, int au) {
		courseName = cName;
		courseCode = cCode;
		this.ofSchool = ofSchool;
		numOfIndex = 0;
		indexInfo = new HashMap<>();
		totalVacancy = 0;
		numOfCourses++;
		availableType = cAvailableType;
		AU = au;
	}

	//-------------------------------------to String method----------------------------------

	/**
	 * Used for printing course object
	 * @return a string representation of course object
	 */
	public String toString(){
		return courseName + ", " + courseCode + ", " + ofSchool;
	}

	// ---------------------------------------get method--------------------------------------

	/**
	 * Get the name of the course
	 * @return course name
	 */
	public String getCourseName(){
		return courseName;
	}

	/**
	 * Get the au of the course
	 * @return au
	 */
	public int getAU(){
		return AU;
	}

	/**
	 * Get course code of the course
	 * @return course code
	 */
	public String getCourseCode(){
		return courseCode;
	}

	/**
	 * Get school this course belongs to
	 * @return school name
	 */
	public String getCourseSchool(){
		return ofSchool;
	}

	/**
	 * Get all indices under the course
	 * @return all indices
	 */
	public List<Index>  getAllIndex(){
		return new ArrayList<>(indexInfo.values());
	}

	/**
	 * Get number of the indices this course provides
	 * @return number of the indices
	 */
	public int getNumOfIndex(){
		return numOfIndex;
	}

	/**
	 * Get number of total vacancies left in the course
	 * @return number of vacancies
	 */
	public int getTotalVac(){
		return totalVacancy;
	}

	/**
	 * Get all course type this course can be registered as
	 * @return possible course type
	 */
	public List<CourseType> getTypes(){
		return availableType;
	}

	/**
	 * Get total number of courses
	 * @return total number of courses
	 */
	public static int getNumOfCourses(){
		return numOfCourses;
	}

	/**
	 * Get a specific index of this course
	 * @param indexNumber index number
	 * @return Index object requested
	 */
	public Index getIndex(int indexNumber) {
		return indexInfo.get(indexNumber);
	}


	//-------------------------------------set method----------------------------------------

	/**
	 * Set new course name of this course
	 * @param nowCname new course name
	 */
	public void setCourseName(String nowCname){
		courseName = nowCname;
	}

	/**
	 * Set new au of this course
	 * @param au new au points
	 */
	public void setAU(int au){
		AU = au;
	}

	/**
	 * Set new course code of this course
	 * @param nowCcode new course code
	 */
	public void setCourseCode(String nowCcode){
		courseCode = nowCcode;
	}

	/**
	 * Set new school of this course
	 * @param nowSchool new school
	 */
	public void setCourseSchool(String nowSchool){
		ofSchool = nowSchool;
	}

	/**
	 * Set new possible types of this course
	 * @param availableTypes list of available types
	 */
	public void setAvailableType(List<CourseType> availableTypes) {
		this.availableType.addAll(availableTypes);
	}

	//-------------------------------- changing index --------------------------------------

	/**
	 * Add a new index to this course
	 * @param indexNo index number
	 * @param vac number of vacancies
	 * @param timeSlot timeslot this index takes
	 * @return true if the index is added
	 */
	public boolean addIndex(int indexNo, int vac, Timetable timeSlot){
		if (indexInfo.containsKey(indexNo)) return false;
		Index newIndex = new Index(indexNo, vac, this, timeSlot);
		indexInfo.put(indexNo, newIndex);
		numOfIndex++;
		totalVacancy+=newIndex.getCapacity();
		return true;
	}

	/**
	 * Delete a index under this course
	 * Refuse to delete the index index does not exist or if any students are currently enrolled in the index
	 * @param indexNo index number
	 * @return true if index is deleted
	 */
	public boolean deleteIndex(int indexNo) {
		if (!indexInfo.containsKey(indexNo)) return false;
		Index toDeleteIndex = indexInfo.get(indexNo);
		if (toDeleteIndex.getNumRegistered() > 0) return false;
		indexInfo.remove(indexNo);
		numOfIndex--;
		totalVacancy-=toDeleteIndex.getCapacity();
		return true;
	}

	/**
	 * Update the index's capacity
	 * Fail to update if index does not exist or new capacity smaller than number of students registered
	 * @param indexNo index number
	 * @param capacity new capacity
	 * @return true if the index is updated successfully
	 */
	public boolean updateIndex(int indexNo, int capacity) {
		if (!indexInfo.containsKey(indexNo)) {
			System.out.println("The index does not exist.");
			return false;
		}
		Index toUpdate = indexInfo.get(indexNo);
		int oldCapacity = toUpdate.getCapacity();
		if (capacity < toUpdate.getNumRegistered()) return false;
		toUpdate.setCapacity(capacity);
		totalVacancy += (capacity - oldCapacity);
		return true;

	}

	/**
	 * Get vacancy of a specific index
	 * @param indexNumber index number
	 * @return -1 if index does not exist otherwise, return vacancy of that index
	 */
	public int getIndexVacancy(int indexNumber){
		if (!containIndex(indexNumber)) return -1;
		return indexInfo.get(indexNumber).getVacancies();
	}

	//---------------Stud and Index---------------------------------------

	/**
	 * Add a student to a index
	 * @param stud student to be added
	 * @param indexNumber index number
	 * @return true if student is added successfully
	 */
	public boolean addStudentToIndex(Student stud, int indexNumber) {
		if (!containIndex(indexNumber)) return false;
		Index index = indexInfo.get(indexNumber);
		return (index.addStudent(stud));
	}

	/**
	 * Delete a student from a index
	 * @param stud student to be deleted
	 * @param indexNumber index number
	 * @return true if student is deleted successfully
	 */
	public boolean deleteStudentFromIndex(Student stud, int indexNumber) {
		if (!containIndex(indexNumber)) return false;
		Index index = indexInfo.get(indexNumber);
		return (index.deleteStudent(stud));
	}

	/**
	 * Swap student from one index to another index
	 * Swap is successful if:
	 * 1. Two index number are valid
	 * 2. New index has vacancy
	 * 3. New index does not clash with student's time table
	 * @param stud student
	 * @param indexNumber1 index number to drop
	 * @param indexNumber2 index number to add
	 * @return true if swapping is successful
	 */
	public boolean swapIndex (Student stud, int indexNumber1, int indexNumber2) {
		if (!containIndex(indexNumber1)) return false;
		if (!containIndex(indexNumber2)) return false;
		boolean success = false;
		Index index1 = indexInfo.get(indexNumber1);
		Index index2 = indexInfo.get(indexNumber2);
		if (index2.getVacancies() > 0) {
			success = index1.deleteStudent(stud);
			stud.getTimetable().printTimeTable();
			if (success){
				if (!stud.getTimetable().checkClash(index2.getClasses()))
					index2.addStudent(stud);
				else {
					index1.addStudent(stud);
					success = false;
				}
			}
		}
		return success;
	}

	/**
	 * Swap two students' indices
	 * Swap is successful if:
	 * 1. Two index number are valid
	 * 2. Student1 is registered in index number1 and Student2 is registered in index number2
	 * 3. index number1 does not clash with student2's time table and index number2 does not clash with student1's time table
	 * @param indexNumber1 index number of Student1
	 * @param indexNumber2 index number of Student2
	 * @param stud1 student1
	 * @param stud2 student2
	 * @return true if swapping is successful
	 */
	public boolean swapIndexWithAnotherOne(int indexNumber1, int indexNumber2, Student stud1, Student stud2){
		if (!containIndex(indexNumber1)) return false;
		if (!containIndex(indexNumber2)) return false;

		boolean successful = false;
		Index index1 = indexInfo.get(indexNumber1);
		Index index2 = indexInfo.get(indexNumber2);
		if (index1.deleteStudentWithReserve(stud1))
		{
			if(index2.deleteStudentWithReserve(stud2)) {
				if (!stud1.getTimetable().checkClash(index2.getClasses()) &&
						!stud2.getTimetable().checkClash(index1.getClasses())) {
					index1.addStudent(stud2);
					index2.addStudent(stud1);
					successful = true;
				} else {
					index1.addStudent(stud1);
					index2.addStudent(stud2);
				}
			}
			else
				index1.addStudent(stud1);
		}

		return successful;
	}

	/**
	 * Check whether course contains this index
	 * @param indexNumber index number
	 * @return true if index number belongs to this course
	 */
	private boolean containIndex(int indexNumber) {
		return indexInfo.containsKey(indexNumber);
	}


	// -----------------------------for admin to modify CourseType -----------------------------

	/**
	 * Add a new course type this course can be registered as
	 * Course type is added successfully if it not already added
	 * @param courseType course type
	 * @return true if course type is added successfully
	 */
	public boolean addCourseType(CourseType courseType) {
		if (availableType.contains(courseType)) return false;
		availableType.add(courseType);
		System.out.println("Course Type added successfully.");
		return true;
	}

	/**
	 * Delete a course type this course can be registered as
	 * Course type is deleted successfully if it exist as a available type currently
	 * @param courseType course type
	 * @return true if course type is deleted successfully
	 */
	public boolean deleteCourseType(CourseType courseType) {
		if (!availableType.contains(courseType)) return false;
		availableType.remove(courseType);
		System.out.println("Course Type deleted successfully.");
		return true;
	}
	//------------------------------for printing timetable when index added or deleted------------

	/**
	 * Print time table of a specific index
	 * @param indexNo index number
	 */
	public void printIndexTimeTable(int indexNo){
		this.getIndex(indexNo).printTimetable();
	}

	//------------------------------for initialization -------------------------------------------
	/**
	 * For initialization of index (Initialization only)
	 */
	public void initializeIndex() {
		for (Index i : indexInfo.values()) {
			i.initialize();
		}
	}

	/**
	 * Add students registered to index registered list in index
	 * (Initialization only)
	 * @param student student
	 * @param numOfIndex index number
	 */
	public void addRegisteredStudents(Student student, int numOfIndex) {
		Index index = indexInfo.get(numOfIndex);
		index.addStudentsToRegistered(student);
	}

	/**
	 * Add students in wait list to waiting list in index
	 * (Initialization only)
	 * @param student
	 * @param numOfIndex
	 */
	public void addWaitListStudents(Student student, int numOfIndex) {
		Index index = indexInfo.get(numOfIndex);
		index.addStudentsToWaitList(student);
	}
}
