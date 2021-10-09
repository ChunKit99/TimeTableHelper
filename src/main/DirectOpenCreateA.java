
package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
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

public class DirectOpenCreateA {

    static Scanner sc = new Scanner(System.in);
    String category = "A";
    ArrayList<CourseDetail> myCourse = new ArrayList<CourseDetail>();// save course detail from txt
	ArrayList<Section> sectionToAdd = new ArrayList<Section>();// save course section from txt
	ArrayList<Course> courseToAdd = new ArrayList<Course>();// save course(lecture and lab) will add into time table
	ArrayList<Course> allcourse = new ArrayList<Course>();// save all course that able to add, combine sectionToAdd and
															// myCourse
	ArrayList<Course> buffercourse = new ArrayList<Course>();// temp save single course taken from allcourse base on
																// course code
    ArrayList<Subject> subjectToAdd = new ArrayList<Subject>();
    TimeTable myTimeTable = new TimeTable(category);
    boolean isErrorFileCourseDetail = false, isErrorFileCourseSection = false;
	int totalCredit = 0;
    public static void main(String[] args) {
        DirectOpenCreateA myTimeTableApp = new DirectOpenCreateA();
        myTimeTableApp.loadFile();
		myTimeTableApp.checkFormatFileLoaded();
        myTimeTableApp.createCourseToAdd();
        //assign time table
        if (myTimeTableApp.assignCourseTimeTable()) {
            //show time table
            System.out.println("\nTime Table:\n");
            myTimeTableApp.myTimeTable.showTimeTable();
            myTimeTableApp.exportFile();
        }

    }

    public void loadFile() {
	    String javaCourseDetail = "CourseDetail.txt";
	    String javaCourseSection = "CourseSection.txt";
		try {
			System.out.println("");
			System.out.println("Searching \"CourseDetail.txt\"...");
			File f1 = new File(javaCourseDetail);
			if(!f1.exists()) {
				System.out.println("\"CourseDetail.txt\" not found...");
				System.out.println("Please Prepare \"CourseDetail.txt\" And Try Again.");
				System.exit(0);
			}
			System.out.println("\"CourseDetail.txt\"found...");
			System.out.println("");
			System.out.println("Searching \"CourseSection.txt\"...");
			File f2 = new File(javaCourseSection);
			if(!f2.exists()) {
				System.out.println("\"CourseSection.txt\" not found...");
				System.out.println("Please Prepare \"CourseSection.txt\" And Try Again.");
				System.exit(0);
			}
			System.out.println("\"CourseSection.txt\"found...");
			System.out.println("\nLoading...");
			Scanner dataReader = new Scanner(f1);
			Scanner dataReader2 = new Scanner(f2);
			
			while (dataReader.hasNextLine()) {
				String fileData = dataReader.nextLine();
				String str[] = fileData.split(" ");
				if (str.length != 6) {//total length = 6
					if(str.length == 4) {//code(0) name(1) credit(2) y/n(3) date(4) AM/PM(5)
						if(str[3].equals("N")) {//no exam
							addCourseDetail(str[0], str[1], Integer.parseInt(str[2]));
						}else {// should have exam but only have 4 part
							System.out.println("\nWarning Course Detail!");
							System.out.println("Error at Line :" + fileData);
							isErrorFileCourseDetail = true;
						}
					}
				} else {//str.length == 6
					addCourseDetail(str[0], str[1], Integer.parseInt(str[2]), LocalDate.parse(str[4]), str[5]);//skip str[3], it's indexExam
				}
			}
			dataReader.close();
			
			while (dataReader2.hasNextLine()) {
				String fileData = dataReader2.nextLine();
				String str[] = fileData.split(" ");
				if (str.length != 6) {// means format wrong
					System.out.println("\nWarning Course Section!");
					System.out.println("Error at Line :" + fileData);
					isErrorFileCourseSection = true;
				} else {
					addSection(str[0], str[1], Integer.parseInt(str[2]), Integer.parseInt(str[3]),
							Integer.parseInt(str[4]), Integer.parseInt(str[5]));
				}
			}
			dataReader2.close();
			
			
		} catch (FileNotFoundException exception) {
			System.out.println("Unexcpected error occurred!");
			exception.printStackTrace();
			System.exit(0);
		}
	}

	public void checkFormatFileLoaded() {
		if (isErrorFileCourseDetail || isErrorFileCourseSection) {// check format txt
			if (isErrorFileCourseDetail) {
				System.out.println("\nFormat File Course Detail Wrong!");
			}
			if (isErrorFileCourseSection) {
				System.out.println("Format File Course Section Wrong!");
			}
			System.out.println("This Program Will End, Please Check And Make Sure Format Correct.");
			System.exit(0);
		} else {// all format correct
			if (myCourse.size() == 0 || sectionToAdd.size() == 0) {// check empty content
				if (myCourse.size() == 0) {
					System.out.println("Empty Course Detail!");
				}
				if (sectionToAdd.size() == 0) {
					System.out.println("Empty Course Section!");
				}
				System.out.println(
						"This Program Will End, Please Check And Make Sure Not Empty Content For Course Detail and Course Section.");
				System.exit(0);
			} else {// all format correct and not empty content
				System.out.println(myCourse.size() +" Course Loaded...");
				System.out.println(sectionToAdd.size() +" Section Loaded...");
				System.out.println("Loaded Sucessfully!");
				System.out.println("\nWelcome To Time Table Helper!!\n");
			}
	
		}
	}

	public void addCourseDetail(String codeCourse, String nameCourse, int creditHour) {
		myCourse.add(new CourseDetail(codeCourse, nameCourse, creditHour));
	}
	
	public void addCourseDetail(String codeCourse, String nameCourse, int creditHour, LocalDate date, String dayTime) {
		myCourse.add(new CourseDetail(codeCourse, nameCourse, creditHour, date, dayTime));
	}

    //type: 1 == Lecture 2 == Lab
    public void addSection(String sectionID, String codeCourse, int type, int day, int start, int end) {
        sectionToAdd.add(new Section(sectionID, new Subject(codeCourse, type, day, LocalTime.of(start, 0), LocalTime.of(end, 0), category)));
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

    public boolean assignCourseTimeTable() {
        if (courseToAdd.isEmpty()) {//empty
            //skip
			System.out.println("Empty List, Fail To Assign!");
            return false;
        } else {
        	System.out.println("Assigning Time Table...");
            if (myTimeTable.assignTimeTable(courseToAdd)) {
                System.out.println("Done Assign!");
                return true;
            } else {
				System.out.println("Fail To Assign! Please Try Other Combination of Section.");
                return false;
            }
        }
    }

	public void exportFile() {
		try {
			File myObj = new File("MyTimeTable.txt");
			System.out.println("\nCreating File...");
			if (myObj.createNewFile()) {
				System.out.println("File created: " + myObj.getName());
				writeFile();
				System.out.println("\nDone Export! ");
				System.out.println("File Path: " + myObj.getAbsolutePath());
			} else {
				System.out.println("But File already exists.");
					System.out.println("\nDeleting...");
					removeFile();
					exportFile();
			}
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}

	public void removeFile() {
		File myObj = new File("MyTimeTable.txt");
		if (myObj.delete()) {
			System.out.println("Deleted the file: " + myObj.getName());
		} else {
			System.out.println("Failed to delete the file.");
		}
	}

	public void writeFile() {
		try {
			FileWriter myWriter = new FileWriter("MyTimeTable.txt");
			myWriter.write(myTimeTable.returnShowTimeTable());
			myWriter.close();
		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}
	}
  
}
