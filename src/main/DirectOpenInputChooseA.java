
package main;

/**
 * 1. base on subject name, select section of Lecture + select lab, can skip repeat time after choosing; 
 * 2. the course will select from top to down, top is most important
 * 3. suitable for lab + lecture mode or lecture only, if no lab will auto skip
 * 4. 1 lecture section, have 2 lab section, lab name must contain lecture name
 * 5. directly choose course after java -jar xxx.jar(No enter path, same directory have txt)
 * 6. Cat A = johor kedah kelantan terenganu
 * 7. (Current in Use)Cat B = Other than Cat A 
 * @author Liew Chun Kit
 */
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

import model.*;

public class DirectOpenInputChooseA {

	static Scanner sc = new Scanner(System.in);
	String category = "A";
	ArrayList<CourseDetail> myCourse = new ArrayList<CourseDetail>();// save course detail from txt
	ArrayList<Section> sectionToAdd = new ArrayList<Section>();// save course section from txt
	ArrayList<Course> courseToAdd = new ArrayList<Course>();// save course(lecture and lab) will add into time table
	ArrayList<Course> allcourse = new ArrayList<Course>();// save all course that able to add, combine sectionToAdd and
															// myCourse
	ArrayList<Course> buffercourse = new ArrayList<Course>();// temp save single course taken from allcourse base on
																// course code
	ArrayList<Section> buffersection = new ArrayList<Section>();// temp save lecture section from buffercourse
	ArrayList<Section> buffersection2 = new ArrayList<Section>();// temp save lab section from buffercourse base on
																	// lecture section
	ArrayList<Integer> indexAvailableToSelect = new ArrayList<Integer>();// to temp store available index section

	TimeTable myTimeTable = new TimeTable(category);
	boolean isErrorFileCourseDetail = false, isErrorFileCourseSection = false;
	int totalCredit = 0, totalSubjectRegister = 0;

	public static void main(String[] args) {
		DirectOpenInputChooseA myTimeTableApp = new DirectOpenInputChooseA();
		int indexSectionLecture, indexSectionLab;
		myTimeTableApp.loadFile();
		myTimeTableApp.checkFormatFileLoaded();
		myTimeTableApp.createAllCourse();
		for (int i = 0; i < myTimeTableApp.myCourse.size(); i++) {
			myTimeTableApp.clearAllBuffer();
			myTimeTableApp.createSingleCourseBuffer(myTimeTableApp.myCourse.get(i).getID());
			myTimeTableApp.createLectureBuffer();
			System.out.println(
					"\n" + myTimeTableApp.myCourse.get(i).getID() + " " + myTimeTableApp.myCourse.get(i).getName());
			System.out.println("Lecture:");
			int numAvailableLecture = 0;
			myTimeTableApp.indexAvailableToSelect.clear();
			for (int j = 0; j < myTimeTableApp.buffersection.size(); j++) {
				if (myTimeTableApp.isDisplaySection(myTimeTableApp.buffersection.get(j))) {
					numAvailableLecture++;
					myTimeTableApp.indexAvailableToSelect.add(j);
					System.out.println("Index (" + j + "): " + " Session: " + myTimeTableApp.buffersection.get(j).id
							+ " " + myTimeTableApp.buffersection.get(j).getSubject().getDayName() + " "
							+ myTimeTableApp.buffersection.get(j).getSubject().getTimeStart() + " "
							+ myTimeTableApp.buffersection.get(j).getSubject().getTimeEnd());
				}
			}
			System.out.println("");
			// ready to know which lecture section need to add
			if (numAvailableLecture == 0) {// means no available list for lecture
				System.out.println("\nEmpty Availble Lecture Section: " + myTimeTableApp.myCourse.get(i).getID() + " "
						+ myTimeTableApp.myCourse.get(i).getName()
						+ "\nYou Need To Choose Another Section For Previous Course: "
						+ myTimeTableApp.myCourse.get(i - 1).getID() + " "
						+ myTimeTableApp.myCourse.get(i - 1).getName());
			} else {
				System.out.println("Enter index of Lecture Course " + myTimeTableApp.indexAvailableToSelect.toString());
				indexSectionLecture = sc.nextInt();
				while (!myTimeTableApp.indexAvailableToSelect.contains(indexSectionLecture)) {
					System.out.println("\nWarning! The index " + indexSectionLecture + " are not available to select!");
					System.out.println(
							"Enter index of Lecture Course " + myTimeTableApp.indexAvailableToSelect.toString());
					indexSectionLecture = sc.nextInt();
				}
				Section sectionLecturetoAdd = myTimeTableApp.buffersection.get(indexSectionLecture);
				myTimeTableApp.courseToAdd.add(new Course(myTimeTableApp.myCourse.get(i), sectionLecturetoAdd));
				myTimeTableApp.totalCredit += myTimeTableApp.myCourse.get(i).getCreditHour();// add credit hour
				myTimeTableApp.totalSubjectRegister += 1;// add number subject
				// get the index of lecture session, use it to find lab
				myTimeTableApp.createLabBuffer(sectionLecturetoAdd.id);
				int numLabAvailable = 0;
				boolean labDisplayStart = true;
				myTimeTableApp.indexAvailableToSelect.clear();
				for (int k = 0; k < myTimeTableApp.buffersection2.size(); k++) {
					if (myTimeTableApp.isDisplaySection(myTimeTableApp.buffersection2.get(k))) {
						numLabAvailable++;
						if (labDisplayStart) {
							System.out.println("\nLab:");
							labDisplayStart = false;
						}
						System.out
								.println("Index (" + k + "): " + " Session: " + myTimeTableApp.buffersection2.get(k).id
										+ " " + myTimeTableApp.buffersection2.get(k).getSubject().getDayName() + " "
										+ myTimeTableApp.buffersection2.get(k).getSubject().getTimeStart() + " "
										+ myTimeTableApp.buffersection2.get(k).getSubject().getTimeEnd());
						myTimeTableApp.indexAvailableToSelect.add(k);
					}
				}
				if (!myTimeTableApp.buffersection2.isEmpty()) {// there have match lab form the lecture select
					if (numLabAvailable == 0) {// means no time lab section is available
						System.out.println("\nEmpty Availble Lab Section For " + myTimeTableApp.myCourse.get(i).getID()
								+ " " + myTimeTableApp.myCourse.get(i).getName()
								+ "\nYou Need To Choose Another Section For Previous Course "
								+ myTimeTableApp.myCourse.get(i - 1).getID() + " "
								+ myTimeTableApp.myCourse.get(i - 1).getName());
						// remove last courseToAdd because lab section of the lecture course crash time;
						myTimeTableApp.courseToAdd.remove(myTimeTableApp.courseToAdd.size() - 1);
						// remove previous added credit hour
						myTimeTableApp.totalCredit -= myTimeTableApp.myCourse.get(i).getCreditHour();
						myTimeTableApp.totalSubjectRegister -= 1;//// remove previous add number subject
					} else {
						System.out.println(
								"\nEnter index of Lab Course " + myTimeTableApp.indexAvailableToSelect.toString());
						indexSectionLab = sc.nextInt();
						while (!myTimeTableApp.indexAvailableToSelect.contains(indexSectionLab)) {
							System.out.println(
									"\nWarning! The index " + indexSectionLab + " are not available to select!");
							System.out.println(
									"Enter index of Lab Course " + myTimeTableApp.indexAvailableToSelect.toString());
							indexSectionLab = sc.nextInt();
						}
						Section sectionLabtoAdd = myTimeTableApp.buffersection2.get(indexSectionLab);
						myTimeTableApp.courseToAdd.add(new Course(myTimeTableApp.myCourse.get(i), sectionLabtoAdd));
					}
				}
			}
		}
		System.out.println("");
		// assign time table
		if (myTimeTableApp.assignCourseTimeTable()) {
			// show time table
			myTimeTableApp.showAllCourseToAddDetail();
		}
		System.out.println("Thanks For Using. Bye!!");
	}

	public void loadFile() {
		String pathCourseDetail = "CourseDetail.txt";
		String pathCourseSection = "CourseSection.txt";

		Scanner dataReader1;
		Scanner dataReader2;
		try {
			System.out.println("");
			System.out.println("Searching \"CourseDetail.txt\"...");
			// load and search course detail
			File f1 = new File(pathCourseDetail);
			if (f1.exists()) {// file exist, direct open
				System.out.println("\"CourseDetail.txt\" found...");
				dataReader1 = new Scanner(f1);
			} else {// file not exist need to input path
				System.out.println("\"CourseDetail.txt\" not found...");
				System.out.println("");
				System.out.println("Please Enter Path of Course Detail:");
				pathCourseDetail = sc.nextLine();
				String javaCourseDetail = pathCourseDetail.replace("\\", "/");
				f1 = new File(javaCourseDetail);
				if (!f1.exists()) {// the path of file not exist
					System.out.println("\nSorry, This File is Not Exist!");
					System.out.println("Please Try Agian.");
					System.exit(0);
				}
				System.out.println("Course Detail found...");
				dataReader1 = new Scanner(f1);
			}
			System.out.println("");
			System.out.println("Searching \"CourseSection.txt\"...");
			File f2 = new File(pathCourseSection);
			if (f2.exists()) {// exist
				System.out.println("\"CourseSection.txt\" found...");
				dataReader2 = new Scanner(f2);
			} else {// not exist
				System.out.println("\"CourseSection.txt\" not found...");
				System.out.println("");
				System.out.println("Please Enter Path of Course Section:");
				pathCourseSection = sc.nextLine();
				String javaCourseSection = pathCourseSection.replace("\\", "/");
				f2 = new File(javaCourseSection);
				if (!f2.exists()) {// the path of file not exist
					System.out.println("\nSorry, This File is Not Exist!");
					System.out.println("Please Try Agian.");
					System.exit(0);
				}
				System.out.println("Course Section found...");
				dataReader2 = new Scanner(f2);
			}

			System.out.println("\nLoading...");
			while (dataReader1.hasNextLine()) {
				String fileData = dataReader1.nextLine();
				String str[] = fileData.split(" ");
				if (str.length != 6) {// total lenth = 6
					if (str.length == 4) {// code(0) name(1) credit(2) y/n(3) date(4) AM/PM(5)
						if (str[3].equals("N")) {// no exam
							addCourseDetail(str[0], str[1], Integer.parseInt(str[2]));
						} else {// should have exam but only have 4 part
							System.out.println("\nWarning Course Detail!");
							System.out.println("Error at Line :" + fileData);
							isErrorFileCourseDetail = true;
						}
					}
				} else {// str.length == 6
					addCourseDetail(str[0], str[1], Integer.parseInt(str[2]), LocalDate.parse(str[4]), str[5]);// skip
																												// str[3],
																												// it's
																												// indexExam
				}
			}
			dataReader1.close();
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
		System.out.println("");
		if (isErrorFileCourseDetail || isErrorFileCourseSection) {// check format txt
			if (isErrorFileCourseDetail) {
				System.out.println("Format File Course Detail Wrong!");
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
				System.out.println(myCourse.size() + " Course Loaded...");
				System.out.println(sectionToAdd.size() + " Section Loaded...");
				System.out.println("\nLoaded Sucessfully!");
				System.out.println("\nWelcome To Time Table Helper!!");
			}
		}
	}

	public void addCourseDetail(String codeCourse, String nameCourse, int creditHour) {
		myCourse.add(new CourseDetail(codeCourse, nameCourse, creditHour));
	}

	public void addCourseDetail(String codeCourse, String nameCourse, int creditHour, LocalDate date, String dayTime) {
		myCourse.add(new CourseDetail(codeCourse, nameCourse, creditHour, date, dayTime));
	}

	/*
	 * type: 1 == Lecture 2 == Lab
	 */
	public void addSection(String sectionID, String codeCourse, int type, int day, int start, int end) {
		sectionToAdd.add(new Section(sectionID,
				new Subject(codeCourse, type, day, LocalTime.of(start, 0), LocalTime.of(end, 0), category)));
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

	public void createSingleCourseBuffer(String id) {
		for (int j = 0; j < allcourse.size(); j++) {
			if (allcourse.get(j).getCourseDetail().getID().equals(id)) {
				buffercourse.add(allcourse.get(j));
			}
		}
	}

	public void createLectureBuffer() {
		for (int j = 0; j < buffercourse.size(); j++) {
			if (buffercourse.get(j).getSection().getSubject().getType().equals("Lecture")) {
				buffersection.add(buffercourse.get(j).getSection());
			}
		}
	}

	public void createLabBuffer(String id) {
		for (int k = 0; k < buffercourse.size(); k++) {
			if (buffercourse.get(k).getSection().id.contains(id)) {
				if (buffercourse.get(k).getSection().getSubject().getType().equals("Lab")) {
					buffersection2.add(buffercourse.get(k).getSection());
				}
			}
		}
	}

	public void clearAllBuffer() {
		buffercourse.clear();
		buffersection.clear();
		buffersection2.clear();
	}

	public boolean isDisplaySection(Section sectionToCheck) {
		boolean isDisplay = true;
		// searching all course in courseToAdd to compare sectionToCheck
		for (int k = 0; k < courseToAdd.size(); k++) {
			// check same/repeat day of week from courseToAdd
			if (courseToAdd.get(k).getSection().getSubject().getDayWeek() == sectionToCheck.getSubject().getDayWeek()) {
				// same day
				// if timestart tocheck equal or after timestart toadd(not before) then true.
				// if timestart tocheck before timeend toadd then true(not same not after).
				// both true will not display(set isdisplay to false).
				// what happen if time start tocheck same time end toadd, since timestart
				// tocheck before timeend toadd will false, finaly not true and skip the if
				if ((!sectionToCheck.getSubject().getTimeStart()
						.isBefore(courseToAdd.get(k).getSection().getSubject().getTimeStart()))
						&& (sectionToCheck.getSubject().getTimeStart()
								.isBefore(courseToAdd.get(k).getSection().getSubject().getTimeEnd()))) {
					isDisplay = false;
				}
			}
		}
		return isDisplay;
	}

	public boolean assignCourseTimeTable() {
		if (courseToAdd.isEmpty()) {
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

	public void showAllCourseToAddDetail() {
		System.out.println("\nTime Table:\n");
		myTimeTable.showTimeTable();
		System.out.println("");
		showCurrentCourseToAdd();
		System.out.println("");
		System.out.println("Total Credit Hour Added: " + totalCredit);
		System.out.println("Number Subject Should Add: " + myCourse.size());
		System.out.println("Number Subject Added: " + totalSubjectRegister);
		System.out.print("Is Done To Add All Subject?: ");
		if (myCourse.size() != totalSubjectRegister) {
			System.out.println("No!!");
			System.out.println("");
			System.out.println("Please Try Other Combination Of Section.");
			System.out.println("");
		} else {
			System.out.println("Yes.");
			System.out.println("");
			System.out.println("Congraturation! All Course added.");
			System.out.println("");

			checkExportTimeTableFile();

		}

	}

	public void showCurrentCourseToAdd() {
		System.out.println("Course To Add:");
		for (int i = 0; i < courseToAdd.size(); i++) {
			System.out.println(courseToAdd.get(i).display());
		}
	}

	public void checkExportTimeTableFile() {
		String opt;
		System.out.println("Time Table Export:");
		System.out.println("Export the Time Table in txt?(Y/n)");
		System.out.println("Default Yes to export unless enter \"n\"");
		System.out.println("Press Enter To Export");

		sc.nextLine();
		opt = sc.nextLine();
		if (opt.equals("n") || opt.equals("N")) {
			System.out.println("\nSkip export...");
		} else {
			System.out.println("\nExporting...");
			exportFile();
		}
	}

	public void exportFile() {
		String opt;
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
				System.out.println("\nRemove the old time table?(Y/n)");
				System.out.println("Default Yes to remove unless enter \"n\"");
				System.out.println("Press Enter To Delete");
				opt = sc.nextLine();
				if (opt.equals("n") || opt.equals("N")) {
					System.out.println("\nNot nRemove the old time table.");
				} else {
					System.out.println("\nDeleting...");
					removeFile();
					exportFile();
				}
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
