
package main;

/**
 * 1. base on subject name, select section of Lecture + select lab, can skip repeat time after choosing; 
 * 2. the course will select from top to down, top is most important
 * 3. suitable for lab + lecture mode or lecture only, if no lab will auto skip
 * 4. 1 lecture section, have 2 lab section, lab name must contain lecture name
 *
 * @author Liew Chun Kit
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

import model.*;

public class MyApp2 {

    static Scanner sc = new Scanner(System.in);
    int maxCredit = 19;
    ArrayList<CourseDetail> myCourse = new ArrayList<CourseDetail>();
    ArrayList<Course> courseToAdd = new ArrayList<Course>();
    ArrayList<Course> allcourse = new ArrayList<Course>();
    ArrayList<Course> buffercourse = new ArrayList<Course>();
    ArrayList<Section> buffersection = new ArrayList<Section>();
    ArrayList<Section> buffersection2 = new ArrayList<Section>();
    ArrayList<Subject> subjectToAdd = new ArrayList<Subject>();
    ArrayList<Section> sectionToAdd = new ArrayList<Section>();
    TimeTable myTimeTable = new TimeTable();

    public static void main(String[] args) {
        MyApp2 myTimeTableApp = new MyApp2();
        int indexSectionLecture, indexSectionLab;

        System.out.println("Path of Course Detail:");
        String pathCourseDetail = sc.nextLine();
        System.out.println("Path of Course Section:");
        String pathCourseSection = sc.nextLine();

        String javaCourseDetail = pathCourseDetail.replace("\\", "/");
        String javaCourseSection = pathCourseSection.replace("\\", "/");
        try {
            File f1 = new File(javaCourseDetail);
            Scanner dataReader = new Scanner(f1);
            while (dataReader.hasNextLine()) {
                String fileData = dataReader.nextLine();
                String str[] = fileData.split(" ");
                myTimeTableApp.addCourseDetail(str[0], str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]));
            }
            dataReader.close();
            File f2 = new File(javaCourseSection);
            dataReader = new Scanner(f2);
            while (dataReader.hasNextLine()) {
                String fileData = dataReader.nextLine();
                String str[] = fileData.split(" ");
                myTimeTableApp.addSection(str[0], str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]), Integer.parseInt(str[4]), Integer.parseInt(str[5]));
            }
            dataReader.close();
        } catch (FileNotFoundException exception) {
            System.out.println("Unexcpected error occurred!");
            exception.printStackTrace();
        }

        if (myTimeTableApp.getCurrentCreditHour() <= myTimeTableApp.maxCredit) {//check whether excess max credit hour
            //creating allcourse
            myTimeTableApp.createAllCourse();
            //from allcourse, select courseToAdd
            //courseToAdd = select lecture section + lab section
            for (int i = 0; i < myTimeTableApp.myCourse.size(); i++) {
                myTimeTableApp.buffercourse.clear();
                myTimeTableApp.buffersection.clear();
                myTimeTableApp.buffersection2.clear();
                for (int j = 0; j < myTimeTableApp.allcourse.size(); j++) {
                    if (myTimeTableApp.allcourse.get(j).getCourseDetail().getID().equals(myTimeTableApp.myCourse.get(i).getID())) {
                        myTimeTableApp.buffercourse.add(myTimeTableApp.allcourse.get(j));
                    }
                }
                //buffercourse = course with match course detail
                //from buffercourse= get section with lecture
                for (int j = 0; j < myTimeTableApp.buffercourse.size(); j++) {
                    if (myTimeTableApp.buffercourse.get(j).getSection().getSubject().getType().equals("Lecture")) {
                        myTimeTableApp.buffersection.add(myTimeTableApp.buffercourse.get(j).getSection());
                    }
                }
                System.out.println("");
                System.out.println(myTimeTableApp.myCourse.get(i).getID() + " " + myTimeTableApp.myCourse.get(i).getName());
                System.out.println("Lecture:");
                int numAvailableLecture = 0;
                for (int j = 0; j < myTimeTableApp.buffersection.size(); j++) {
                    boolean checkJ = true;
                    //checkJ is check time of myTimeTableApp.buffersection.get(j) whether in range
                    for (int k = 0; k < myTimeTableApp.courseToAdd.size(); k++) {
                        //check same/repeat day of week from courseToAdd
                        if (myTimeTableApp.courseToAdd.get(k).getSection().getSubject().getDayWeek() == myTimeTableApp.buffersection.get(j).getSubject().getDayWeek()) {
                            //same day
                            //timestart buffersection in courseToAdd timestart timeend, false
                            if ((!myTimeTableApp.buffersection.get(j).getSubject().getTimeStart().isBefore(myTimeTableApp.courseToAdd.get(k).getSection().getSubject().getTimeStart()))
                                    && (myTimeTableApp.buffersection.get(j).getSubject().getTimeEnd().isBefore(myTimeTableApp.courseToAdd.get(k).getSection().getSubject().getTimeEnd())
                                    || myTimeTableApp.buffersection.get(j).getSubject().getTimeEnd().equals(myTimeTableApp.courseToAdd.get(k).getSection().getSubject().getTimeEnd()))) {
                                checkJ = false;
                            }

//                            if (myTimeTableApp.courseToAdd.get(k).getSection().getSubject().getTimeStart().compareTo(myTimeTableApp.buffersection.get(j).getSubject().getTimeStart()) == 0) {
//                                //only check same start time
//                                checkJ = false;
//                            }
                        }
                    }
                    if (checkJ) {
                        numAvailableLecture++;
                        System.out.println("Index (" + j + "): " + " Session: " + myTimeTableApp.buffersection.get(j).id + " " + myTimeTableApp.buffersection.get(j).getSubject().getDayName() + " " + myTimeTableApp.buffersection.get(j).getSubject().getTimeStart() + " " + myTimeTableApp.buffersection.get(j).getSubject().getTimeEnd());
                    }

                }
                System.out.println("");
                if (numAvailableLecture == 0) {//means no available list for lecture
                    System.out.println("\nEmpty Availble Lecture Section!! You Need To Choose Another Section For Previous Course");
                } else {
                    System.out.println("Enter index of Lecture Course");
                    indexSectionLecture = sc.nextInt();
                    Section sectionLecturetoAdd = myTimeTableApp.buffersection.get(indexSectionLecture);
                    myTimeTableApp.courseToAdd.add(new Course(myTimeTableApp.myCourse.get(i), sectionLecturetoAdd));
                    //get the index of lecture session, use it to find lab
                    for (int k = 0; k < myTimeTableApp.buffercourse.size(); k++) {
                        if (myTimeTableApp.buffercourse.get(k).getSection().id.contains(sectionLecturetoAdd.id)) {
                            if (myTimeTableApp.buffercourse.get(k).getSection().getSubject().getType().equals("Lab")) {
                                myTimeTableApp.buffersection2.add(myTimeTableApp.buffercourse.get(k).getSection());
                            }
                        }
                    }
                    int numLabAvailable = 0;
                    boolean labDisplayStart = true;
                    for (int k = 0; k < myTimeTableApp.buffersection2.size(); k++) {
                        boolean checkK = true;
                        for (int l = 0; l < myTimeTableApp.courseToAdd.size(); l++) {
                            //check same/repeat day of week from courseToAdd
                            if (myTimeTableApp.courseToAdd.get(l).getSection().getSubject().getDayWeek() == myTimeTableApp.buffersection2.get(k).getSubject().getDayWeek()) {
                                //same day
                                if ((!myTimeTableApp.buffersection.get(k).getSubject().getTimeStart().isBefore(myTimeTableApp.courseToAdd.get(k).getSection().getSubject().getTimeStart()))
                                        && (myTimeTableApp.buffersection.get(k).getSubject().getTimeEnd().isBefore(myTimeTableApp.courseToAdd.get(k).getSection().getSubject().getTimeEnd())
                                        || myTimeTableApp.buffersection.get(k).getSubject().getTimeEnd().equals(myTimeTableApp.courseToAdd.get(k).getSection().getSubject().getTimeEnd()))) {
                                    checkK = false;
                                }
//                                if (myTimeTableApp.courseToAdd.get(l).getSection().getSubject().getTimeStart().compareTo(myTimeTableApp.buffersection2.get(k).getSubject().getTimeStart()) == 0) {
//                                    //only check same start time
//                                    checkK = false;
//                                }
                            }
                        }
                        if (checkK) {
                            numLabAvailable++;
                            if (labDisplayStart) {
                                System.out.println("\nLab:");
                                labDisplayStart = false;
                            }
                            System.out.println("Index (" + k + "): " + " Session: " + myTimeTableApp.buffersection2.get(k).id + " " + myTimeTableApp.buffersection2.get(k).getSubject().getDayName() + " " + myTimeTableApp.buffersection2.get(k).getSubject().getTimeStart() + " " + myTimeTableApp.buffersection2.get(k).getSubject().getTimeEnd());
                        }

                    }
                    if (!myTimeTableApp.buffersection2.isEmpty()) {
                        if (numLabAvailable == 0) {//==0 means no list available 
                            System.out.println("\nEmpty Availble Lab Section!! You Need To Choose Another Section For Previous Course");
                            myTimeTableApp.courseToAdd.remove(myTimeTableApp.courseToAdd.size() - 1);
                        } else {
                            System.out.println("\nEnter index of Lab Course:");
                            indexSectionLab = sc.nextInt();
                            Section sectionLabtoAdd = myTimeTableApp.buffersection2.get(indexSectionLab);
                            myTimeTableApp.courseToAdd.add(new Course(myTimeTableApp.myCourse.get(i), sectionLabtoAdd));
                        }
                    }
                }
            }
            System.out.println("");
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

    public void createAllCourse() {
        for (int i = 0; i < myCourse.size(); i++) {
            for (int j = 0; j < sectionToAdd.size(); j++) {
                if (myCourse.get(i).getID().equals(sectionToAdd.get(j).getSubject().id)) {
                    allcourse.add(new Course(myCourse.get(i), sectionToAdd.get(j)));
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
