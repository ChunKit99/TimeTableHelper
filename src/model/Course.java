
package model;

/**
 *
 * @author Liew Chun Kit
 */
public class Course {

	private CourseDetail courseDetail;
	private Section section;

	public Course(CourseDetail courseDetail, Section section) {
		this.courseDetail = courseDetail;
		this.section = section;
	}

	public Course() {

	}
	
	//Time table display
	public String display() {
		if(courseDetail.isExam()) {
			return courseDetail.getID() + " " + courseDetail.getName() + " Section " + section.id + " Final Exam: "
					+ courseDetail.getDateOfExam().toString() + " "+ courseDetail.getDayTime();
		}else {
			return courseDetail.getID() + " " + courseDetail.getName() + " Section " + section.id + " Final Exam: NO";
		}
		
	}
	
	//error display when assign to time table
	public String displayFull() {
		if(courseDetail.isExam()) {
			return "" + courseDetail.getID() + " " + courseDetail.getName() + " Section " + section.id + " "
					+ section.getSubject().getType() + " " + section.getSubject().getTimeStart() + " To "
					+ section.getSubject().getTimeEnd() + " Final Exam: " + courseDetail.getDateOfExam().toString()
					+ " " + courseDetail.getDayTime();
		}else {
			return "" + courseDetail.getID() + " " + courseDetail.getName() + " Section " + section.id + " "
					+ section.getSubject().getType() + " " + section.getSubject().getTimeStart() + " To "
					+ section.getSubject().getTimeEnd();
		}	
	}

	@Override
	public String toString() {
		return "" + courseDetail.getID() + " " + courseDetail.getName() + " Section " + section.id + " "
				+ section.getSubject().getType();
	}

	public CourseDetail getCourseDetail() {
		return courseDetail;
	}

	public Section getSection() {
		return section;
	}

	public void setCourseDetail(CourseDetail courseDetail) {
		this.courseDetail = courseDetail;
	}

	public void setSection(Section section) {
		this.section = section;
	}

}
