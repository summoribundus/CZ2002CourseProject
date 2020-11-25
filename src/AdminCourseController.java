/**
 * Controller class providing functionalities for admin
 */

import java.util.List;
import java.util.Map;

public class AdminCourseController {
    /**
     * Map containing all course
     * Key: course code
     * Value: course object
     */
    private Map<String, Course> map;

    /**
     * Create a controller
     * @param map map containing all courses
     */
    public AdminCourseController(Map<String, Course> map) {
        this.map = map;
    }

    //------------------------------for admin-----------------------------------

    /**
     * Update course name
     * Update is successful if course code is valid
     * @param courseCode course code
     * @param newName new course name
     * @return true if update is successful
     */
    public boolean updateCourseName(String courseCode, String newName) {
        if (!checkCodeString(courseCode)) return false;
        Course course = map.get(courseCode);
        course.setCourseName(newName);
        System.out.println("Course name updated successfully.");
        return true;
    }

    /**
     * Update course code
     * Update is successful if course code is valid
     * @param courseCode course code
     * @param newCode new course code
     * @return true if update is successful
     */
    public boolean updateCourseCode(String courseCode, String newCode) {
        if (!checkCodeString(courseCode)) return false;
        Course course = map.get(courseCode);
        course.setCourseCode(newCode);
        System.out.println("Course code updated successfully.");
        return true;
    }

    /**
     * Update school this course belongs to
     * Update is successful if course code is valid
     * @param courseCode course code
     * @param newSchool new school this course belongs to
     * @return true if update is successful
     */
    public boolean updateCourseSchool(String courseCode, String newSchool) {
        if (!checkCodeString(courseCode)) return false;
        Course course = map.get(courseCode);
        course.setCourseSchool(newSchool);
        System.out.println("Course school updated successfully.");
        return true;
    }

    /**
     * Update a course index capacity
     * Update is successful if:
     * 1. course code is valid
     * 2. course update index capacity successfully
     * @param courseCode course code
     * @param indexNumber index number
     * @param newVac new capacity
     * @return true if update is successful
     */
    public boolean updateCourseIndexVacancy(String courseCode, int indexNumber, int newVac) {
        if (!checkCodeString(courseCode)) return false;
        Course course = map.get(courseCode);
        boolean successful;
        successful = course.updateIndex(indexNumber, newVac);
        if (successful) {
            System.out.println(course.getCourseCode() + " " + indexNumber + " now has a new capacity of " + newVac);
            return true;
        } else {
            System.out.println("Failed.");
            return false;
        }
    }

    /**
     * Add a new index to course
     * Index is added successfully if:
     * 1. course code is valid
     * 2. course add index successfully
     * 3. timeslot format is correct
     * @param courseCode course code
     * @param newIndex new index number
     * @param capacity new capcity
     * @param timeslt List of String of index timeslot
     * @return true of index is added successfully
     */
    public boolean addCourseIndex(String courseCode, int newIndex, int capacity, List<String> timeslt) {
        if (!checkCodeString(courseCode)) return false;
        try {
            Course course = map.get(courseCode);
            Timetable timeSlot = new Timetable();
            timeSlot.addSlots(timeslt);
            boolean res = course.addIndex(newIndex, capacity, timeSlot);
            if (res) {
                System.out.println("Index: " + newIndex + " added.");
                return true;
            } else {
                System.out.println("Failed.");
                return false;
            }}
            catch(Exception e){
                System.out.println("Adding Index failed!...Timetable in wrong format");
                return false;
            }
        }

    /**
     * Delete a index from a course
     * Deletion is successful if:
     * 1. course code is valid
     * 2. course deletion of index is successful
     * @param courseCode course code
     * @param indexNumber index number
     * @return true if index is deleted successfully
     */
        public boolean deleteCourseIndex (String courseCode,int indexNumber){
            if (!checkCodeString(courseCode)) return false;
            Course course = map.get(courseCode);
            boolean res = course.deleteIndex(indexNumber);
            if (res) System.out.println("Index: " + indexNumber + " deleted.");
            else System.out.println("Failed.");
            return res;
        }

    /**
     * Add a new course
     * Adding is successful if course does not exist yet
     * @param courseName course name
     * @param courseCode course code
     * @param school school the course belongs to
     * @param availableType possible course type students can registered as
     * @param au au point
     * @return true if adding is successful
     */
        public boolean addCourse (String courseName, String courseCode, String school, List < CourseType > availableType,
        int au){
            Course course = new Course(courseName, courseCode, school, availableType, au);
            if (map.containsKey(courseCode)) {
                System.out.println("courseCode " + courseCode + " has already exist.");
                return false;
            } else {
                map.put(courseCode, course);
                printAllCourse();
            }
            return true;
        }

    /**
     * Print vacancy of a specific index
     * @param courseCode course code
     * @param indexNumber index number
     */
    public void checkVacByAdmin (String courseCode,int indexNumber){
            int res = getVacByIndex(courseCode, indexNumber);
            if (res > 0)
                System.out.println("course: " + courseCode + ", index: " + indexNumber + " has " + res + "vacancies.");
        }

    /**
     * Print all vacancies of a course
     * @param courseCode cours code
     */
    public void checkAllVacByAdmin (String courseCode){
            printAllVacancies(courseCode);
        }

    /**
     * Add a new course type that students can registered as to a course
     * Adding is successful if:
     * 1. course code is valid
     * 2. course adding course type is successful
     * @param courseCode course code
     * @param CourseTypeToAdd course type
     * @return true if adding is successful
     */
        public boolean addCourseType (String courseCode, CourseType CourseTypeToAdd){
            if (!checkCodeString(courseCode)) return false;
            Course course = map.get(courseCode);
            boolean res = course.addCourseType(CourseTypeToAdd);
            if (!res) System.out.println("Course type already exist.");
            return res;
        }

    /**
     * Delete a course type from a course
     * Deletion is successful if:
     * 1. course code is valid
     * 2. course deleting course type is successful
     * @param courseCode course code
     * @param CourseType course type to be deleted
     * @return true if course type is deleted successfully
     */
        public boolean deleteCourseType (String courseCode, CourseType CourseType){
            if (!checkCodeString(courseCode)) return false;
            Course course = map.get(courseCode);
            boolean res = course.deleteCourseType(CourseType);
            if (!res) System.out.println("Course type does not exist.");
            return res;
        }

    /**
     * Print all students registered in a course index
     * @param courseCode course code
     * @param indexNumber index number
     */
    public void printStudentsByIndexNumber (String courseCode,int indexNumber){
            if (!checkCodeString(courseCode)) return;
            Course course = map.get(courseCode);
            Index index = course.getIndex(indexNumber);
            if (index == null) System.out.println(courseCode + " does not contain index " + indexNumber);
            else {
                List<Student> students = index.getRegisteredStudents();
                for (Student s : students) {
                    System.out.println(s.getStudentMatric()+" Name: "+s.getStudentName());
                }
            }
        }

    /**
     * Print all students registered in a course
     * @param courseCode course code
     */
    public void printStudentByCourse (String courseCode){
            if (!checkCodeString(courseCode)) return;
            Course course = map.get(courseCode);
            for (Index in : course.getAllIndex()) {
                printStudentsByIndexNumber(courseCode, in.getIndexNumber());
            }
        }

        //------------------------------helper function------------------------------

    /**
     * Print all indices to check their vacancy
     * @param courseCode course code
     */
        private void printAllVacancies(String courseCode){
            if (!checkCodeString(courseCode)) return;
            Course course = map.get(courseCode);
            List<Index> indexes = course.getAllIndex();
            for (Index i : indexes) {
                System.out.println(i);
            }
        }

    /**
     * Get vacancy of a course index
     * @param courseCode course code
     * @param indexNumber index number
     * @return -1 if course code invalid or index number does not belong to course, otherwise return vacancy
     */
        private int getVacByIndex (String courseCode,int indexNumber){
            if (!checkCodeString(courseCode)) return -1;
            Course course = map.get(courseCode);
            int res = course.getIndexVacancy(indexNumber);
            if (res < 0) System.out.println("Index number " + indexNumber + " does not belong to " + courseCode + ".");
            return res;
        }

    /**
     * Check whether course code is valid
     * @param courseCode course code
     * @return true if course code exists
     */
    private boolean checkCodeString(String courseCode){
            Course course = map.get(courseCode);
            if (course == null) {
                System.out.println("Invalid Course Code.");
                return false;
            }
            return true;
        }

    /**
     * Print all available courses
     */
    private void printAllCourse() {
        for (Map.Entry<String, Course> entry : map.entrySet()) {
            String s = entry.getKey() + " " + entry.getValue().getCourseName();
            System.out.println(s);
        }
    }
}
