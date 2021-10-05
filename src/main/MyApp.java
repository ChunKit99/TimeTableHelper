
package main;

/**
 * direct create time table, if repeat time happen will fail to create
 *
 * @author Liew Chun Kit
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;

public class MyApp {

    static Scanner sc = new Scanner(System.in);
    int maxCredit = 19;
    ArrayList<CourseDetail> myCourse = new ArrayList<CourseDetail>();
    ArrayList<Course> courseToAdd = new ArrayList<Course>();
    ArrayList<Course> allcourse = new ArrayList<Course>();
    ArrayList<Course> buffercourse = new ArrayList<Course>();
    ArrayList<Subject> subjectToAdd = new ArrayList<Subject>();
    ArrayList<Section> sectionToAdd = new ArrayList<Section>();
    TimeTable myTimeTable = new TimeTable();

    public static void main(String[] args) {
        MyApp myTimeTableApp = new MyApp();
        //CourseDetail(String iD, String name, int creditHour, LocalDate dateOfExam)
        //Subject(String name, String type, int dayWeek, LocalTime timeStart, LocalTime timeEnd)
        myTimeTableApp.addCourseDetail("AAA1234", "Math", 3, 2021, 3, 13);
        myTimeTableApp.addCourseDetail("BBB1234", "Sains", 3, 2021, 3, 14);
        myTimeTableApp.addCourseDetail("CCC1234", "BM", 3, 2021, 3, 15);
        myTimeTableApp.addCourseDetail("DDD1234", "BI", 3, 2021, 3, 16);

        myTimeTableApp.addSection("01", "AAA1234", 1, 0, 14, 17);
        myTimeTableApp.addSection("01A", "AAA1234", 0, 0, 8, 11);

        myTimeTableApp.addSection("01", "BBB1234", 1, 1, 14, 17);
        myTimeTableApp.addSection("01A", "BBB1234", 0, 1, 8, 11);

        myTimeTableApp.addSection("01", "CCC1234", 1, 2, 14, 17);
        myTimeTableApp.addSection("01A", "CCC1234", 0, 2, 8, 11);

        myTimeTableApp.addSection("01", "DDD1234", 1, 3, 14, 17);
        myTimeTableApp.addSection("01A", "DDD1234", 0, 4, 8, 11);

        if (myTimeTableApp.getCurrentCreditHour() <= myTimeTableApp.maxCredit) {//check whether excess max credit hour
            myTimeTableApp.createCourseToAdd();
            //assign time table
            if (myTimeTableApp.assignCourseTimeTable()) {
                //show time table
                System.out.println("\nCourse To Add:\n");
                myTimeTableApp.showAllCourseToAddDetail();
                System.out.println("\nTime Table:\n");
                myTimeTableApp.myTimeTable.showTimeTable();
            }
        } else {
            System.out.println("Your Credit excess 19(max available credit)!!");
        }

    }

    public void showAllCourseToAddDetail() {
        System.out.println("Total Credit Hour: " + getCurrentCreditHour());
        for (int i = 0; i < courseToAdd.size(); i++) {
            System.out.println(courseToAdd.get(i).display());
        }
    }

    public void addCourseDetail(String codeCourse, String nameCourse, int creditHour, int year, int month, int date) {
        myCourse.add(new CourseDetail(codeCourse, nameCourse, creditHour, LocalDate.of(year, Month.of(month), date)));
    }

    //type: 1 == Lecture 2 == Lab
    public void addSection(String sectionID, String codeCourse, int type, int day, int start, int end) {
        sectionToAdd.add(new Section(sectionID, new Subject(codeCourse, type, day, LocalTime.of(start, 0), LocalTime.of(end, 0))));
    }

    public void createCourseToAdd() {
        for (int i = 0; i < myCourse.size(); i++) {//creating courseToAdd
            for (int j = 0; j < sectionToAdd.size(); j++) {
                if (myCourse.get(i).ID.equals(sectionToAdd.get(j).getSubject().id)) {
                    courseToAdd.add(new Course(myCourse.get(i), sectionToAdd.get(j)));
                }
            }
        }
    }

    public int getCurrentCreditHour() {
        int totalCredit = 0;
        for (CourseDetail cd : myCourse) {
            totalCredit += cd.getCreditHour();
        }
        return totalCredit;
    }

    public boolean assignCourseTimeTable() {
        if (courseToAdd.isEmpty()) {//empty
            //skip
            System.out.println("Empty List, Please Add Course");
            return false;
        } else {
            if (myTimeTable.assignTimeTable(courseToAdd)) {
                System.out.println("Done Assign!");
                return true;
            } else {
                System.out.println("Fail To Assign!");
                return false;
            }
        }
    }

}
