
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*We can add printTimeTable function as an extra feature */

/**
Represents a timetable with courses registered by a student. 
@author Group3_SS6_CZ2002
@version 1.0
@since 2020-11-22
*/
class Timetable implements Serializable{
    /**
     * The schedule of classes are stored within this hashmap. 
     */
    private Map<String, List<Timeslot>> schedule = new HashMap<>();

    /**
     * Constructor of the Timetable class. 
     * It calls the initSchedule() method. 
     */
    public Timetable(){
        initSchedule();
    }

    /**
     * Initializes the schedule by filling the time entries in the hashmap with weekdays. 
     */
    public void initSchedule(){

        for(DayOfWeek dw : DayOfWeek.values()){
            List<Timeslot> entries = new ArrayList <Timeslot> ();
            this.schedule.put(dw.toString().toLowerCase(),entries);
        }


    }

    /**
     * Gets the schedule of the Timetable object. 
     */
    public Map<String, List<Timeslot>> getSchedule(){
        return this.schedule;
    }
    //-------------time table clash------------------------------------
    /**
     * Checks if there is any clash between the current Timetable and another Timetable. 
     * <p>
     * It calls the Timeslot objects stored inside the current hashmap to check against the Timeslots stored in the other hashmap.
     * <p> 
     * The Timeslot objects check the clashes through the checkClash() method in the Timeslot class. 
     */
    public boolean checkClash(Timetable t1){
        boolean flag = false;

        for (Map.Entry<String, List<Timeslot>> entry : (t1.getSchedule()).entrySet()) {
            String t1key = entry.getKey();
            List<Timeslot> t1slots = entry.getValue();
            List<Timeslot> slots = this.schedule.get(t1key);
            for (Timeslot t1slot: t1slots){
                for (Timeslot tslot: slots){
                    if (tslot.checkClash(t1slot)){
                        System.out.println("The following slots clash on "+t1key.toUpperCase());
                        System.out.println();
                        tslot.printTimeslot();
                        t1slot.printTimeslot();
                        flag = true;
                    }
                }
            }
        }
        return flag;

    }
    //------------updating student timetable (only for student class)--------------------------
    /**
     * Adds a Timetable to the current Timetable by combining the keys and values in the hashmaps. 
     * @param Contains the entries to be combined. 
     */
    public void addTimetable(Timetable t1){
        for (Map.Entry<String, List<Timeslot>> entry : (t1.getSchedule()).entrySet()) {
            String t1key = entry.getKey();
            List<Timeslot> t1slots = entry.getValue();
            for (Timeslot t1slot: t1slots){
                (this.schedule.get(t1key)).add(t1slot);
            }
        }
    }

    /**
     * Deletes entry from the current Timetable by removing the keys and values that are equal to the Timetable hashmap entries that are passed in.
     * @param t1 Contains the entries to be removed. 
     */
    public void delTimetable(Timetable t1){
        for (Map.Entry<String, List<Timeslot>> entry : (t1.getSchedule()).entrySet()) {
            String t1key = entry.getKey();
            List<Timeslot> t1slots = entry.getValue();
            List<Timeslot> slots = this.schedule.get(t1key);
            for (Timeslot t1slot: t1slots){
                for (int i = 0; i<slots.size();i++){
                    if (t1slot.equals(slots.get(i)))
                        this.schedule.get(t1key).remove(i);
                }
            }
        }
    }


    //-------------adding slots to timetable-----------------------------
    /**
     * Adds slots to a timetable. 
     * <p>
     * It calls the addSlot() method to add the slots one by one. 
     * @param slots A list that contains the slots to be added to the Timetable. 
     */
    public void addSlots(List<String> slots){
        for (String slot: slots){
            this.addSlot(slot);
        }
    }

    /**
     * Adds a slot to the schedule. 
     * <p>
     * The function parses the input of a "Mon;8:30-10:30;SEM;All;HWLAB3" format. 
     * <p>
     * It then constructs a new Timeslot object and stores it inside the schedule hashmap. 
     * @param slot A string of "Mon;8:30-10:30;SEM;All;HWLAB3" format that contains information on the Timeslot to be added. 
     */
    private void addSlot(String slot){


        // format : "Mon;8:30-10:30;SEM;All;HWLAB3"
        String[] arr = slot.split(";");
        String day = arr[0];
        String time = arr[1];
        String classType = arr[2];
        String remarks = arr[3];
        String venue = arr[4];
        Timeslot slt = new Timeslot(time,"HH:mm",classType,remarks,venue);
//        System.out.println(day.toLowerCase());

        (this.schedule.get(day.toLowerCase())).add(slt);


    }

    //-------------print Course/Index Timetable only----------------------------------------
    /**
     * Prints the schedule hashmap stored in the Timetable object. 
     */
    public void printTimeTable(){
        for (String day : this.schedule.keySet()) {
            if (this.schedule.get(day).size()!=0){
                System.out.println(day.toUpperCase()+": ");
                for (Timeslot tt: this.schedule.get(day)){
                    tt.printTimeslot();
                }
                System.out.println();
            }
        }
    }

}
