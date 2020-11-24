//import java.sql.Time;
import java.util.Date;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
Represents a "slot" that a course may take up in the timetable. 
<p>
Contains necessary information regarding the classtype (lectures, tutorials, etc. ), opening and closing date, venue, and so on. 
@author Group3_SS6_CZ2002
@version 1.0
@since 2020-11-22
*/
public class Timeslot implements Serializable{
    /**
	 * The date that the timeslot opens
	 */
    private Date opening;
    /**
	 * The date that the timeslot closes
	 */
    private Date closing;
    /**
	 * The venue that the course in the timeslot takes place. 
	 */
    private String venue;
    /**
     * Remarks contains information on if the timeslot is on odd weeks, even weeks, or all weeks. 
     */
    private timeType remarks;
    /**
     * The classtype specifies whether the class is a lecture, tutorial, or something else. 
     */
    private String classType;
    /**
     * The constructor of the Timeslot class. 
     * <p>
     * The opening and closing date will be set via the setTimeSlot() method first, followed by other attributes. 
     * @param timeslot A string containing the opening and closing date of the time slot. 
     * @param format A string that contains the format of timeslot input. 
     * @param classType A string that specifies the class type. 
     * @param remarks A string that specifies whether the class is on odd weeks, even weeks, or both. 
     * @param ven A string that specifies the venue. 
     */
    public Timeslot(String timeslot, String format,String classType, String remarks, String ven ){
        setTimeSlot(timeslot, format);
        this.venue = ven;
        this.classType = classType;
        this.remarks = timeType.valueOf(remarks);
        this.venue = ven;
    }
    /**
     * Get the opening date of the timeslot.
     * @return opening date
     */
    public Date getOpening (){
        return this.opening;
    }

    /**
     * Get the closing date of the timeslot.
     * @return closing date
     */
    public Date getClosing(){
        return this.closing;
    }

    /**
     * Get the venue of the timeslot.
     * @return venue
     */
    public String getVenue(){
        return this.venue;
    }

    /**
     * Get the class type of the class occupying the class type.
     * @return class type
     */
    public String getClassType(){
        return this.classType;
    }

    /**
     * Get the remarks of the timeslot.
     * @return remarks (All/Odd/Even)
     */
    public timeType getRemarks(){
        return this.remarks;
    }


    /**
     * Set the opening and closing date of the time slot. 
     * @param timeslot A string that contains the opening and closing date in a specific format. 
     * @param format A string that specifies the format of the timeslot input. 
     */
    private void setTimeSlot(String timeslot, String format){
        // "8:30-10:30" Time format
        String[] times = timeslot.split("-");
        SimpleDateFormat parser = new SimpleDateFormat(format);
        // try catch block for ParseException
        try{
            this.opening = parser.parse(times[0]);
            this.closing = parser.parse(times[1]);
        }
        catch (ParseException e){
            System.out.println(e.getMessage());
        }


    }

    /**
     * Check if there is any clash between the current timeslot object and another Timeslot object. 
     * @param t The other Timeslot object to be checked against.
     * @return true if clash
     */
    public boolean checkClash(Timeslot t){
        if (this.remarks.compareTo(t.getRemarks())== 0 || this.remarks.compareTo(timeType.All)==0
                || t.getRemarks().compareTo(timeType.All)==0){
            if (this.opening.compareTo(t.getClosing())<0 && this.closing.compareTo(t.getOpening())>0)
                return true;
            else
                return false;
        }
        else{
            return false;
        }

    }

    /**
     * Check if the current timeslot object is identical to another Timeslot object. 
     * @param t The other Timeslot object to be checked against.
     * @return if equal
     */
    public boolean isEqual(Timeslot t){
        if (this.opening.compareTo(t.getOpening())==0 && this.closing.compareTo(t.getClosing())==0
                && this.venue == t.getVenue()&& this.remarks == t.getRemarks()&& this.classType == t.getClassType())
            return true;
        else
            return false;
    }

    /**
     * Print the information of the timeslot. 
     */
    public void printTimeslot(){
        String time = opening.getHours()+":"+opening.getMinutes()+"-"+closing.getHours()+":"+closing.getMinutes();
        String venue = this.venue;
        String remarks = new String();
        if (this.remarks == timeType.All)
            remarks = "Wk 2-13";
        if (this.remarks == timeType.Odd)
            remarks = "Wk 1,3,5,7,11,13";
        if (this.remarks == timeType.Even)
            remarks = "Wk 2,4,6,8,10,12";
        String classType = this.classType;

        System.out.println("-------------------------------------");
        System.out.println("Time: "+time+" ;Remarks: "+remarks);
        System.out.println("Class Type: "+classType+" ;Venue: "+venue);
        System.out.println("-------------------------------------");
        System.out.println();



    }

}
