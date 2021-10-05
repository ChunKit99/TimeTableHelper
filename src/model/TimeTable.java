package model;

import java.util.ArrayList;

public class TimeTable {

    public Day[] days = new Day[5];

    public TimeTable() {
        days = createDays();
    }

    public void dropAllCourse() {
        days = null;
        days = createDays();
        System.out.println("Done Reset!");
    }

    public boolean assignTimeTable(ArrayList<Course> courseToAdd) {
        for (int i = 0; i < courseToAdd.size(); i++) {//loop the course to add
            int dayIndex = courseToAdd.get(i).getSection().getSubject().getDayWeek();
            for (int j = 0; j < days[dayIndex].timePeriod.length; j++) {//loop period in a day
                if (days[dayIndex].timePeriod[j].isInRange(courseToAdd.get(i))) {//in range
                    if (days[dayIndex].timePeriod[j].setCourseDetail(courseToAdd.get(i))) {
                    } else {//fail to add because clash time
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public void showTimeTable() {
        for (int i = 0; i < 5; i++) {
            System.out.println(days[i].name);
            for (int j = 0; j < days[i].numOfSlot; j++) {
                if(days[i].timePeriod[j].course!=null){
                    System.out.println(days[i].timePeriod[j].timeStart + " - " + days[i].timePeriod[j].timeEnd + " "
                        + days[i].timePeriod[j].course);
                }else{
                    System.out.println(days[i].timePeriod[j].timeStart + " - " + days[i].timePeriod[j].timeEnd);
                }
            }
        }
    }

    public Day[] createDays2() {//if Study from Sunday to Thursday
        Day[] day = new Day[5];
        day[0] = new Day("Sunday");
        day[1] = new Day("Monday");
        day[2] = new Day("Tuesday");
        day[3] = new Day("Wednesday");
        day[4] = new Day("Thursday");
        return day;
    }
    
    public Day[] createDays() {//if Study from Monday to Friday
        Day[] day = new Day[5];
        day[0] = new Day("Monday");
        day[1] = new Day("Tuesday");
        day[2] = new Day("Wednesday");
        day[3] = new Day("Thursday");
        day[4] = new Day("Friday");
        return day;
    }
}
