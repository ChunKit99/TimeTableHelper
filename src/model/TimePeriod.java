package model;

import java.time.LocalTime;

public class TimePeriod {

    public boolean isAvailabe = true;
    public LocalTime timeStart;
    public LocalTime timeEnd;
    public Course course;

    public TimePeriod() {

    }

    public TimePeriod(LocalTime timeStart, LocalTime timeEnd) {
        super();
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;

    }

    public boolean isInRange(Course courseToAdd) {
        Boolean isNowInRange = (!timeStart.isBefore(courseToAdd.getSection().getSubject().getTimeStart()))
                && (timeEnd.isBefore(courseToAdd.getSection().getSubject().getTimeEnd()) || timeEnd.equals(courseToAdd.getSection().getSubject().getTimeEnd()));
        return isNowInRange;
    }

    public boolean setCourseDetail(Course courseToAdd) {
        if (isAvailabe) {
            this.course = courseToAdd;
            isAvailabe = false;
        } else {
            if (courseToAdd.equals(course)) {//same course for add
                //skip
            } else {
                System.out.println("\nFailed To Add, This Time Slot is Added Before!");
                //System.out.println("Day of Week: " + (courseToAdd.getSection().getSubject().getDayWeek() + 1));
                System.out.println("Day of Week: " + (courseToAdd.getSection().getSubject().getDayName()));
                System.out.println("Time :" + timeStart.toString() + " - " + timeEnd.toString());
                System.out.println("Course Added: " + course.displayFull());
                System.out.println("Current to Add: " + courseToAdd.displayFull());
                System.out.println("");
                return false;
            }
        }
        return true;
    }

}
