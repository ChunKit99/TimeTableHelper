package model;

import java.time.LocalDate;

public class CourseDetail {

    public String ID;//code 
    private String name;//name course
    private boolean isExam = true;
    private int creditHour;
    private LocalDate dateOfExam;
    private String dayTime;

    public CourseDetail() {

    }

    public CourseDetail(String iD, String name, int creditHour, LocalDate dateOfExam, String dayTime) {
        super();
        ID = iD;
        this.name = name;
        this.creditHour = creditHour;
        this.dateOfExam = dateOfExam;
        this.dayTime = dayTime;
    }
    public CourseDetail(String iD, String name, int creditHour) {
        super();
        ID = iD;
        this.name = name;
        this.creditHour = creditHour;
        isExam = false;
    }

    public boolean isExam() {
		return isExam;
	}

	public void setExam(boolean isExam) {
		this.isExam = isExam;
	}

	public String getDayTime() {
		return dayTime;
	}

	public void setDayTime(String dayTime) {
		this.dayTime = dayTime;
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
