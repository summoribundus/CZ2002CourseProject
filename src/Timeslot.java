//import java.sql.Time;
import java.util.Date;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Timeslot implements Serializable{
    private Date opening;
    private Date closing;
    private String venue;
    private timeType remarks;
    private String classType;
    public Timeslot(String timeslot, String format,String classType, String remarks, String ven ){
        setTimeSlot(timeslot, format);
        this.venue = ven;
        this.classType = classType;
        this.remarks = timeType.valueOf(remarks);
        this.venue = ven;
    }
    public Date getOpening (){
        return this.opening;
    }

    public Date getClosing(){
        return this.closing;
    }

    public String getVenue(){
        return this.venue;
    }

    public String getClassType(){
        return this.classType;
    }

    public timeType getRemarks(){
        return this.remarks;
    }


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

    public boolean isEqual(Timeslot t){
        if (this.opening.compareTo(t.getOpening())==0 && this.closing.compareTo(t.getClosing())==0
                && this.venue == t.getVenue()&& this.remarks == t.getRemarks()&& this.classType == t.getClassType())
            return true;
        else
            return false;
    }

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
