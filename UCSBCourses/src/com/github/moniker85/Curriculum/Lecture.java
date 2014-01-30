/**The class for the UCSB Lectures
 * All setters except for enrolledCount and enrolledMax take strings.
 */

package com.github.moniker85.Curriculum;

import java.util.List;
import java.util.ArrayList;

public class Lecture {
	//Member Variables
	private String courseAbbr;
	private String courseName;
	private String status;
	private String enrollCode;
	private String instructor;
	private String days;
	private String time;
	private String location;
	private int enrolledCount;
	private int enrolledMax;
	private String fullTitle;
	private String description;
	private String preReq;
	private String college;
	private String units;
	private String grading;
	public List<Section> sections = new ArrayList<Section>();
	
	//No Argument Constructor
	public Lecture() {	}
	
	//Setters
	public void setCourseAbbr(String theCourseAbbr) {
		this.courseAbbr = theCourseAbbr;
	}
	
	public void setCourseName(String theCourseName) {
		this.courseName = theCourseName;
	}
	
	public void setStatus(String theStatus) {
		this.status = theStatus;
	}
	
	public void setEnrollCode(String theEnrollCode) {
		this.enrollCode = theEnrollCode;
	}
	
	public void setInstructor(String theInstructor) {
		this.instructor = theInstructor;
	}
	
	public void setDays(String theDays) {
		this.days = theDays;
	}
	
	public void setTime(String theTime) {
		this.time = theTime;
	}
	
	public void setLocation(String theLocation) {
		this.location = theLocation;
	}
	
	public void setEnrolledCount (int theEnRolledCount) {
		this.enrolledCount = theEnRolledCount;
	}
	
	public void setEnrolledMax (int theEnRolledMax) {
		this.enrolledMax = theEnRolledMax;
	}
	
	public void setEnrolled(String theEnRolled) {
		String [] temp = theEnRolled.replaceAll("\\s", "").split("/");
		this.enrolledCount = Integer.parseInt(temp[0]);
		this.enrolledMax = Integer.parseInt(temp[1]);
	}
	
	public void setFullTitle(String theFullTitle) {
		this.fullTitle = theFullTitle;
	}
	
	public void setDescription(String theDescription) {
		this.description = theDescription;
	}
	
	public void setPreReq(String thePreReq) {
		this.preReq = thePreReq;
	}
	
	public void setCollege(String theCollege) {
		this.college = theCollege;
	}
	
	public void setUnits(String theUnits) {
		this.units = theUnits;
	}
	
	public void setGrading(String theGrading) {
		this.grading = theGrading;
	}
	
	
	//Getters
	public String getCourseAbbr() { return this.courseAbbr; }
	
	public String getCourseName() { return this.courseName; }
	
	public String getStatus() { return this.status; }
	
	public String getEnrollCode() { return this.enrollCode; }
	
	public String getInstructor() { return this.instructor; }
	
	public String getDays() { return this.days; }
	
	public String getTime() { return this.time; }
	
	public String getLocation() { return this.location; }
	
	public int getEnrolledCount() { return this.enrolledCount; }
	
	public int getEnrolledMax() { return this.enrolledMax; }
	
	public String getEnrolled() {return "" + this.enrolledCount + " / " + this.enrolledMax;}
	
	public String getFullTitle() { return this.fullTitle; }
	
	public String getDescription() { return this.description; }
	
	public String getPreReq() { return this.preReq; }
	
	public String getCollege() { return this.college; }
	
	public String getUnits() { return this.units; }
	
	public String getGrading() { return this.grading; }
	

	@Override
	public String toString() {
        String result = "Course Name:			" + this.courseName + "\n"
                	+ 	"Course Abbreviation:		" + this.courseAbbr + "\n"
        			+	"Full Title:			" + this.fullTitle + "\n"
        			+ 	"Description:			" + this.description + "\n"
        			+ 	"PreReq(s):			" + this.preReq + "\n"
        			+ 	"College:			" + this.college + "\n"
        			+	"Units:				" + this.units + "\n"
        			+	"Grading:			" + this.grading + "\n"
        			+ 	"Lecture Status:			" + this.status + "\n"
        			+ 	"Enroll Code:			" + this.enrollCode + "\n"	
        			+ 	"Instructor:			" + this.instructor + "\n"
                	+ 	"Lecture Days:			" + this.days + "\n"
                	+ 	"Lecture Time:			" + this.time + "\n"
                	+ 	"Lecture Location:		" + this.location + "\n"
                	+ 	"Enrolled Count:			" + this.enrolledCount + "\n"
        			+ 	"Enrolled Max:			" + this.enrolledMax + "\n\n";
        if (!(sections.size() == (0))) {
        	result += "Sections\n--------\n";
        	for (int i = 0; i < this.sections.size(); i++) {
        		result += "Section " + i + "\n" + sections.get(i).toString();
        	}
        }
        return result;
    }
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {	}
}
