package model;

import java.time.LocalDate;

public class CourseDetail {

    public String ID;//code 
    private String name;//name course
    private int creditHour;
    private LocalDate dateOfExam;

    public CourseDetail() {

    }

    public CourseDetail(String iD, String name, int creditHour, LocalDate dateOfExam) {
        super();
        ID = iD;
        this.name = name;
        this.creditHour = creditHour;
        this.dateOfExam = dateOfExam;
    }

    public String getID() {
        return ID;
    }

    public void setID(String iD) {
        ID = iD;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCreditHour() {
        return creditHour;
    }

    public void setCreditHour(int creditHour) {
        this.creditHour = creditHour;
    }

    public LocalDate getDateOfExam() {
        return dateOfExam;
    }

    public void setDateOfExam(LocalDate dateOfExam) {
        this.dateOfExam = dateOfExam;
    }

}
