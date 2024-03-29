package model;

import java.time.LocalTime;

public class Subject {

    public String id;//code subject, exmple ABC1234
    private String type;//"Lecture" or "Lab"
    private int dayWeek;
    private String dayName = "";
    private LocalTime timeStart;
    private LocalTime timeEnd;

    public Subject() {

    }

    public Subject(String id, String type, int dayWeek, LocalTime timeStart, LocalTime timeEnd) {
        super();
        this.id = id;
        this.type = type;
        this.dayWeek = dayWeek;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
    }

    public Subject(String id, int typeIndex, int dayWeek, LocalTime timeStart, LocalTime timeEnd, String category) {
        super();
        this.id = id;
        if (typeIndex == 1) {
            this.type = "Lecture";
        } else {
            this.type = "Lab";
        }
        this.dayWeek = dayWeek;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.dayName = findDayName(category);
    }
    
    public String getDayName() {
    	return dayName;
    }
    
    public void setDayName(String dayName) {
    	this.dayName = dayName;
    }
    
    public String findDayName(String category) {
        String day = "";
        int index;
        if(category.equals("A")){
        	index = dayWeek;
        }else {//cat B
        	index = dayWeek+1;
        }
        switch (index) {
        	case 0:
        		day = "Sunday";
        		break;
            case 1:
                day = "Monday";
                break;
            case 2:
                day = "Tuesday";
                break;
            case 3:
                day = "Wednesday";
                break;
            case 4:
                day = "Thursday";
                break;
            case 5:
                day = "Friday";
                break; 
        }
        return day;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getDayWeek() {
        return dayWeek;
    }

    public void setDayWeek(int dayWeek) {
        this.dayWeek = dayWeek;
    }

    public LocalTime getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(LocalTime timeStart) {
        this.timeStart = timeStart;
    }

    public LocalTime getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(LocalTime timeEnd) {
        this.timeEnd = timeEnd;
    }

}
