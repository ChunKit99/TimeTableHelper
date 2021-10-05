
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

    public String display() {
        return courseDetail.getID() + " " + courseDetail.getName() + " Section " + section.id;
    }
    public String displayFull() {
        return "" + courseDetail.getID() + " " + courseDetail.getName() + " Section " + section.id
                + " " + section.getSubject().getType() + " " + section.getSubject().getTimeStart()
                + " To " + section.getSubject().getTimeEnd() + " Exam: " + courseDetail.getDateOfExam().toString();
    }
    @Override
    public String toString(){
        return "" + courseDetail.getID() + " " + courseDetail.getName() + " Section " + section.id
                + " " + section.getSubject().getType() + " Exam: " + courseDetail.getDateOfExam().toString();
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
