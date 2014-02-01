package com.github.moniker85.Curriculum;

/**This is the 95% complete version of the UCSB Curriculum course search.
 * I say 95% because I am working on removing a feature from the program.
 * See All classes works most of the time.  But when I tried to See All classes for the 
 * Mathematics department, the program froze up and I had to restart my computer.
 * I don't trust it anymore...
 * 
 * This package relies on HtmlUnit, so make sure you have all of those .jars added to your build path
 */

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.gargoylesoftware.htmlunit.FailingHttpStatusCodeException;
import com.gargoylesoftware.htmlunit.WebClient;
import com.gargoylesoftware.htmlunit.html.DomElement;
import com.gargoylesoftware.htmlunit.html.HtmlElement;
import com.gargoylesoftware.htmlunit.html.HtmlOption;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.gargoylesoftware.htmlunit.html.HtmlSelect;

public class CourseSearch {
	private ArrayList<String> deptOptions = new ArrayList<String>();
	private ArrayList<String> quarterOptions = new ArrayList<String>();
	private ArrayList<String> gradLevelOptions = new ArrayList<String>();
	private ArrayList<String> courseOptions = new ArrayList<String>();
	private ArrayList<Integer> courseOptionsIndex = new ArrayList<Integer>();

	private String course = "", dept = "", gradLevel = "", quarter = "";
	private int courseIndex = 0, deptIndex = 0, quarterIndex = 0, gradLevelIndex;
	
	private Lecture theLecture = null;
	private ArrayList<Lecture> allLectures = null;
	
	private String url = "https://my.sa.ucsb.edu/public/curriculum/coursesearch.aspx";
    private WebClient webClient = new WebClient();
	private HtmlPage page = null;
	private HtmlSelect selectField = null;
	private DomElement courseChoice = null;
	private List<HtmlOption> options = null;
	private List<DomElement> courses = null;

	
	//No Argument Constructor
	public CourseSearch () {
		webClient.getOptions().setThrowExceptionOnScriptError(false);
	    webClient.getOptions().setThrowExceptionOnFailingStatusCode(false);
	    webClient.getOptions().setCssEnabled(false);
	    webClient.getOptions().setJavaScriptEnabled(false);
	}


	//Setters for variables
	public void setCourse(String theCourse) {
		if (courseOptions.contains(theCourse) && !(theCourse.equals("No Classes Found"))) {
			this.courseIndex = courseOptionsIndex.get(courseOptions.indexOf(theCourse));
			this.course = theCourse;
		}
		else {
			this.courseIndex = -50;
			this.course = "";
		}
	}

	public void setDept(String theDept) {
		if (deptOptions.contains(theDept)) {
			this.deptIndex = this.deptOptions.indexOf(theDept);
			this.dept = theDept;
		}
		else {
			this.dept = "";
		}
	}
	
	public void setGradLevel(String theGradLevel) {
		if (gradLevelOptions.contains(theGradLevel)) {
			this.gradLevelIndex = this.gradLevelOptions.indexOf(theGradLevel);
			this.gradLevel = theGradLevel;
		}
		else {
			this.gradLevel = "";
		}
	}
	
	public void setQuarter(String theQuarter) {
		if (quarterOptions.contains(theQuarter)) {
			this.quarterIndex = this.quarterOptions.indexOf(theQuarter);
			this.quarter = theQuarter;
		}
		else {
			this.quarter = "";
		}
	}
	
	
	//Getters for variables
	public String getCourse() {
		return this.course;
	}
	
	public String getDept() {
		return this.dept;
	}
	
	public String getGradLevel() {
		return this.gradLevel;
	}
	
	public String getQuarter() {
		return this.quarter;
	}
	
	
	//Getters for Options
	public List<String> getCourseOptions() throws IOException {
		if (this.loadClassList()) {
			try {
				courses = page.getElementsByIdAndOrName("CourseTitle");
				DomElement courseName;
				int increment = 2;
				String temp = "";
            
				for (int i = 0; i < courses.size(); i++)
				{
					int k = 1;
           	 
					String[] courseAbbr = courses.get(i).getTextContent().trim().split(" ",10);
					while (courseAbbr[k].equals("")) {	  k++;	}
           	 
					String courseAbbr2 = courseAbbr[0] + " " + courseAbbr[k].split(" ")[0].trim();
           	 
					courseName = courses.get(i).getNextElementSibling();
					if (!courseName.getTextContent().trim().isEmpty()) {	
						if (temp.equals(courseName.getTextContent().trim())) {
							temp = courseName.getTextContent().trim();
							courseOptions.add(courseAbbr2 + " (" + temp + ") - Class " + increment);
							courseOptionsIndex.add(i);
							increment++;
						}
						else {
							temp = courseName.getTextContent().trim();
							courseOptions.add(courseAbbr2 + " (" + temp + ")");
							courseOptionsIndex.add(i);
							increment = 2;
						}
					}
				}
			} catch ( FailingHttpStatusCodeException e1 )
			{
				System.out.println( "FailingHttpStatusCodeException thrown:" + e1.getMessage() );
				e1.printStackTrace();
			}
			catch( Exception e )
			{
				System.out.println( "General exception thrown:" + e.getMessage() );
				e.printStackTrace();
			}  
		}
		if (courseOptions.size() == 0) {
			courseOptions.add("No Classes Found");
		}
		return courseOptions;
	}
	
	public List<String> getDeptOptions() throws IOException {
        try {
        	page = webClient.getPage(url);
        	
        	selectField = (HtmlSelect) page.getElementById("pageContent_courseList");
            options = selectField.getOptions();
            
            for(HtmlOption option : options) {
            	deptOptions.add(option.getText());
            }
        } catch ( FailingHttpStatusCodeException e1 ) {
        	System.out.println( "FailingHttpStatusCodeException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch ( MalformedURLException e1 ) {
        	System.out.println( "MalformedURLException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch ( IOException e1 ) {
        	System.out.println( "IOException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch( Exception e ) {
        	System.out.println( "General exception thrown:" + e.getMessage() );
        	e.printStackTrace();
        }
        return deptOptions;
	}
	
	public List<String> getGradLevelOptions() throws IOException { 
        try {
        	page = webClient.getPage(url);
        	
            selectField = (HtmlSelect) page.getElementById("pageContent_dropDownCourseLevels");
            options = selectField.getOptions();
            
            for(HtmlOption option : options) {
            	gradLevelOptions.add(option.getText());
            }
        } catch ( FailingHttpStatusCodeException e1 ) {
        	System.out.println( "FailingHttpStatusCodeException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch ( MalformedURLException e1 ) {
        	System.out.println( "MalformedURLException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch ( IOException e1 ) {
        	System.out.println( "IOException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch( Exception e ) {
        	System.out.println( "General exception thrown:" + e.getMessage() );
        	e.printStackTrace();
        }
        return gradLevelOptions;
	}
	
	public List<String> getQuarterOptions() throws IOException {
        try {
        	page = webClient.getPage(url);
        	
            selectField = (HtmlSelect) page.getElementById("pageContent_quarterList");
            options = selectField.getOptions();
            
            for(HtmlOption option : options) {
            	quarterOptions.add(option.getText());
            }
        } catch ( FailingHttpStatusCodeException e1 ) {
        	System.out.println( "FailingHttpStatusCodeException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch ( MalformedURLException e1 ) {
        	System.out.println( "MalformedURLException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch ( IOException e1 ) {
        	System.out.println( "IOException thrown:" + e1.getMessage() );
        	e1.printStackTrace();
        } catch( Exception e ) {
        	System.out.println( "General exception thrown:" + e.getMessage() );
        	e.printStackTrace();
        }
        return quarterOptions;
	}
	
	
	//Helper Functions
	private boolean loadClassList() throws IOException {
		if (this.dept.equals("") || this.quarter.equals("") || this.gradLevel.equals("")) {
			return false;
		}
		else {
			try {
	        	this.page = webClient.getPage(url);
				HtmlElement theElement = null;
				
				selectField = (HtmlSelect) this.page.getElementById("pageContent_courseList");
				options = selectField.getOptions();
				selectField.setSelectedAttribute(options.get(deptIndex), true );
				
				selectField = (HtmlSelect) this.page.getElementById("pageContent_quarterList");
				options = selectField.getOptions();
				selectField.setSelectedAttribute(options.get(quarterIndex), true );
				
				selectField = (HtmlSelect) this.page.getElementById("pageContent_dropDownCourseLevels");
				options = selectField.getOptions();
				selectField.setSelectedAttribute(options.get(gradLevelIndex), true );
			
				theElement = (HtmlElement) this.page.getElementById("pageContent_searchButton");               
				this.page = theElement.click();
				System.out.println(theElement.getTextContent());
				theElement = (HtmlElement) this.page.getElementById("pageContent_repeaterSearchResults_HyperLinkPrimaryCourse_0");
				if (theElement == null) {
					return false;
				}
				this.page = theElement.click();
				return true;
			} catch ( FailingHttpStatusCodeException e1 ) {
	        	System.out.println( "FailingHttpStatusCodeException thrown:" + e1.getMessage() );
	        	e1.printStackTrace();
	        } catch ( MalformedURLException e1 ) {
	        	System.out.println( "MalformedURLException thrown:" + e1.getMessage() );
	        	e1.printStackTrace();
	        } catch ( IOException e1 ) {
	        	System.out.println( "IOException thrown:" + e1.getMessage() );
	        	e1.printStackTrace();
	        } catch( Exception e ) {
	        	System.out.println( "General exception thrown:" + e.getMessage() );
	        	e.printStackTrace();
	        }
			return false;
		}
	}
	
	//Getter for Selected Lecture
	public Lecture getLecture() {
		return this.theLecture;
	}
	
	//Choose which setter to use
	public boolean setLectures() {
		if (courseIndex == -50) {
			return false;
		}
		else {
			this.theLecture = this.performResult(courseIndex);
			return true;
		}
	}
	
	private Lecture performResult(int index) {
		Lecture lect = new Lecture();
		courseChoice = courses.get(index);
		DomElement theCourseName = courseChoice.getNextElementSibling();
		for (int i = index; i < courses.size(); i++) {               		 
			DomElement courseName = courses.get(i).getNextElementSibling();
			if ( theCourseName.getTextContent().trim().equals(courseName.getTextContent().trim()) || courseName.getTextContent().trim().equals("")) {	 
				int k = 1;
	               			 
				String[] courseAbbr = courses.get(i).getTextContent().trim().split(" ",10);
				while (courseAbbr[k].equals("")) {	  k++;	}
	               			 
				String courseAbbr2 = courseAbbr[0] + " " + courseAbbr[k].split(" ")[0].trim();
	               			 
				DomElement status = courseName.getNextElementSibling();
				DomElement enrollCode = status.getNextElementSibling();
				DomElement instructor = enrollCode.getNextElementSibling();
				DomElement days = instructor.getNextElementSibling();
				DomElement time = days.getNextElementSibling();
				DomElement location = time.getNextElementSibling();
				DomElement enrolled = location.getNextElementSibling();
	                  		 
				if (i == index) {
					DomElement fullTitle = page.getElementById("pageContent_repeaterSearchResults_labelTitle_" + index);
					DomElement description = page.getElementById("pageContent_repeaterSearchResults_labelDescription_" + index);
					DomElement preRequisite = page.getElementById("pageContent_repeaterSearchResults_labelPreReqComment_" + index);
					DomElement college = page.getElementById("pageContent_repeaterSearchResults_labelCollege_" + index);
					DomElement units = page.getElementById("pageContent_repeaterSearchResults_labelUnits_" + index);
					DomElement grading = page.getElementById("pageContent_repeaterSearchResults_labelQuarter_" + index);
					
					lect.setCourseName(courseName.getTextContent().trim() + " (" + courseAbbr2 + ")");
					if (lect.getCourseName().equals("")) { lect.setCourseName("N/A"); }
					
					lect.setCourseAbbr(courseAbbr2);
					if (lect.getCourseAbbr().equals("")) { lect.setCourseAbbr("N/A"); }
					
					lect.setStatus(status.getTextContent().trim());
					if (lect.getStatus().equals("")) { lect.setStatus("N/A"); }
					
					lect.setEnrollCode(enrollCode.getTextContent().trim().split(" ")[0]);
					if (lect.getEnrollCode().equals("")) { lect.setEnrollCode("N/A"); }
					
					lect.setInstructor(instructor.getTextContent().trim());
					if (lect.getInstructor().equals("")) { lect.setInstructor("N/A"); }
					
					lect.setDays(days.getTextContent().trim());
					if (lect.getDays().equals("")) { lect.setDays("N/A"); }
					
					lect.setTime(time.getTextContent().trim());
					if (lect.getTime().equals("")) { lect.setTime("N/A"); }
					
					lect.setLocation(location.getTextContent().trim());
					if (lect.getLocation().equals("")) { lect.setLocation("N/A"); }
					
					lect.setEnrolled(enrolled.getTextContent().trim());
					if (lect.getEnrolled().equals("")) { lect.setEnrolled("N/A"); }
					
	                
					lect.setFullTitle(fullTitle.getTextContent().trim());
					if (lect.getFullTitle().equals("")) { lect.setFullTitle("N/A"); }
					
					lect.setDescription(description.getTextContent().trim());
					if (lect.getDescription().equals("")) { lect.setDescription("N/A"); }
					
					lect.setPreReq(preRequisite.getTextContent().trim());
					if (lect.getPreReq().equals("")) { lect.setPreReq("N/A"); }
					
					lect.setCollege(college.getTextContent().trim());
					if (lect.getCollege().equals("")) { lect.setCollege("N/A"); }
					
					lect.setUnits(units.getTextContent().trim());
					if (lect.getUnits().equals("")) { lect.setUnits("N/A"); }
					
					lect.setGrading(grading.getTextContent().trim());
					if (lect.getGrading().equals("")) { lect.setGrading("N/A"); }
				}
				else {
					Section sect = new Section();
					
					sect.setMainLecture(lect);
					sect.setDays(days.getTextContent().trim());
					if (sect.getDays().equals("")) { sect.setDays("N/A"); }
					sect.setEnrollCode(enrollCode.getTextContent().trim().split(" ")[0]);
					if (sect.getEnrollCode().equals("")) { sect.setEnrollCode("N/A"); }
					sect.setEnrolled(enrolled.getTextContent().trim());
					if (sect.getEnrolled().equals("")) { sect.setEnrolled("N/A"); }
					sect.setInstructor(instructor.getTextContent().trim());
					if (sect.getInstructor().equals("")) { sect.setInstructor("N/A"); }
					sect.setLocation(location.getTextContent().trim());
					if (sect.getLocation().equals("")) { sect.setLocation("N/A"); }
					sect.setStatus(status.getTextContent().trim());
					if (sect.getStatus().equals("")) { sect.setStatus("N/A"); }
					sect.setTime(time.getTextContent().trim());
					if (sect.getTime().equals("")) { sect.setTime("N/A"); }
					
					lect.sections.add(sect);
				}
			}
			else {
				courseIndex = i;
				break;	
			}
		}
		return lect;
	}

	
	public static void main(String[] args) throws IOException {
		Scanner ans = new Scanner(System.in);
		CourseSearch test = new CourseSearch();
		
		for (String theOption : test.getDeptOptions()) {
			System.out.println(theOption);
		}
		System.out.println("Select a Department");
		test.setDept(ans.nextLine());
		System.out.println("You seleceted " + test.getDept());
		
		for (String theOption: test.getQuarterOptions()) {
			System.out.println(theOption);
		}
		System.out.println("Select a Quarter");
		test.setQuarter(ans.nextLine());
		System.out.println("You selected " + test.getQuarter());
		
		for (String theOption : test.getGradLevelOptions()) {
			System.out.println(theOption);
		}
		System.out.println("Select a Graduate Level");
		test.setGradLevel(ans.nextLine());
		System.out.println("You selected " + test.getGradLevel());
		
		for (String theOption : test.getCourseOptions()) {
			System.out.println(theOption);
		}
		System.out.println("Select a Course");
		test.setCourse(ans.nextLine());
		System.out.println("You selected " + test.getCourse());
		
		ans.close();
		
		boolean finish = test.setLectures();
		
		if (finish) {
			System.out.println(test.getLecture().toString());
		}
		else {
				System.out.println("We could not find any classes");
				return;
		}
	}
}