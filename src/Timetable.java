
import java.util.HashMap;
import java.util.List;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

/*We can add printTimeTable function as an extra feature */


class Timetable implements Serializable{
    private Map<String, List<Timeslot>> schedule = new HashMap<>();

    public Timetable(){
        initSchedule();
    }

    public void initSchedule(){

        for(DayOfWeek dw : DayOfWeek.values()){
            List<Timeslot> entries = new ArrayList <Timeslot> ();
            this.schedule.put(dw.toString().toLowerCase(),entries);
        }


    }

    public Map<String, List<Timeslot>> getSchedule(){
        return this.schedule;
    }
    //-------------time table clash------------------------------------
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
    public void addTimetable(Timetable t1){
        for (Map.Entry<String, List<Timeslot>> entry : (t1.getSchedule()).entrySet()) {
            String t1key = entry.getKey();
            List<Timeslot> t1slots = entry.getValue();
            for (Timeslot t1slot: t1slots){
                (this.schedule.get(t1key)).add(t1slot);
            }
        }
    }

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
    public void addSlots(List<String> slots){
        for (String slot: slots){
            this.addSlot(slot);
        }
    }

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
