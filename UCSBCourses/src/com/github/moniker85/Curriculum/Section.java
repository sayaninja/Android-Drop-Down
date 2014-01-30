package com.github.moniker85.Curriculum;

public class Section {
	//Member Variables
	private Lecture mainLecture;
	private String status;
	private String enrollCode;
	private String instructor;
	private String days;
	private String time;
	private String location;
	private int enrolledCount;
	private int enrolledMax;
		
	//No Argument Constructor
	public Section() {	}
		
	//Setters
	public void setMainLecture (Lecture theLecture) {
		this.mainLecture = theLecture;
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
		
	//Getters
	public Lecture getMainLecture() {return this.mainLecture; }
	
	
	public String getStatus() { return this.status; }
	
	public String getEnrollCode() { return this.enrollCode; }
	
	public String getInstructor() { return this.instructor; }
	
	public String getDays() { return this.days; }
	
	public String getTime() { return this.time; }
	
	public String getLocation() { return this.location; }
	
	public int getEnrolledCount() { return this.enrolledCount; }
	
	public int getEnrolledMax() { return this.enrolledMax; }
	
	public String getEnrolled() { return "" + this.enrolledCount + " / " + this.enrolledMax; }
	
	@Override
	public String toString() {
        String result = "Main Lecture:			" + this.getMainLecture().getCourseName() + "\n"
        			+ 	"Section Status:			" + this.status + "\n"
        			+ 	"Enroll Code: 			" + this.enrollCode + "\n"	
        			+ 	"Teaching Assistant: 		" + this.instructor + "\n"
                	+ 	"Section Days: 			" + this.days + "\n"
                	+ 	"Section Time: 			" + this.time + "\n"
                	+ 	"Section Location: 		" + this.location + "\n"
                	+ 	"Enrolled Count: 		" + this.enrolledCount + "\n"
        			+ 	"Enrolled Max:			" + this.enrolledMax + "\n\n";
        return result;
    }

	/**
	 * @param args
	 */
	public static void main(String[] args) {	}
}
