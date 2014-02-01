package com.github.moniker85.RateMyProfessors;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class RateMyProfessor {
	String URL;
	Instructor prof;
	List <String> profList;
	
	public RateMyProfessor() {
		URL = "";
		prof = null;
		profList = null;
	}
	
	public List<String> search(String theProfessor) throws IOException{
		prof = new Instructor();
		prof.setNameWithInitials(theProfessor);
		Document doc = null;
        Elements profNum = null, content = null, links = null;
        
        URL = "http://www.ratemyprofessors.com/SelectTeacher.jsp?searchName="+prof.getLastName()+"&search_submit1=Search&sid=1077";
        doc = Jsoup.connect(URL).get();
        
        profNum = doc.select("span.profNum");
        if (profNum.text().equals("(Showing 0 to 0 of 0 results)"))
        {
        	prof = null;
        	return profList;
        }
        else {
        	profList = new ArrayList<String>();
        	content = doc.select("div#ratingTable");
        	links = content.get(0).children();
        	Element link;
        
        	for (int i = 1; i < links.size(); i++) {
        		link = links.get(i);
        		profList.add(link.child(3).child(0).text() + " (" +  link.child(4).text() + ")"   );
        	}
        	return profList;
        }
	}
	
	public void setProfessor(String theProfessor) throws IOException {
		String linkHref = "", quality = "", helpfulness = "", clarity = "", easiness = "";
		int index = profList.indexOf(theProfessor);

		Document doc = Jsoup.connect(URL).get();
		Elements links = doc.select("div#ratingTable").get(0).children();
		Element link = links.get(index+1);
		linkHref = link.child(3).child(0).absUrl("href");
		doc = Jsoup.connect(linkHref).get();
        
        quality = doc.select("li#quality").get(0).children().get(0).children().get(0).text();
        helpfulness = doc.select("li#helpfulness").get(0).children().get(0).children().get(0).text();
        clarity = doc.select("li#clarity").get(0).children().get(0).children().get(0).text();
        easiness = doc.select("li#easiness").get(0).children().get(0).children().get(0).text();
        
        prof.setClarity(Double.parseDouble(clarity));
        prof.setEasiness(Double.parseDouble(easiness));
        prof.setHelpfulness(Double.parseDouble(helpfulness));
        prof.setQuality(Double.parseDouble(quality));
        prof.setDepartment(link.child(4).text());
        prof.setFullName(link.child(3).child(0).text());
       
        if (prof.getFirstInitial().equals("")) {
        	prof.setFirstInitial("N/A");
        }
        if (prof.getSecondInitial().equals("")) {
        	prof.setSecondInitial("N/A");
        }
	}
	
	public Instructor getProfessor() {
		return this.prof;
	}
	
	
	public static void main(String[] args) throws IOException {
		Scanner ans = new Scanner(System.in);
		String userAns = "";
		RateMyProfessor test = new RateMyProfessor();
		
		System.out.println("Enter the last name of the professor");
		List <String> theList = test.search(ans.nextLine());
		if (theList == null) {
			System.out.println("We couldn't find that professor");
			ans.close();
			return;
		}
		else if (theList.size() == 1) {
			userAns = theList.get(0);
		}
		else {
			for (String theProfList : theList) {
				System.out.println(theProfList);
			}
			System.out.println("Which Professor do you mean?");
			userAns = ans.nextLine();
			while (!theList.contains(userAns)) {
				System.out.println("Please enter the name and department exactly");
				userAns = ans.nextLine();
			}
			ans.close();
		}
		System.out.println("Showing results for " + userAns);
		
		test.setProfessor(userAns);
		Instructor theInstructor = test.getProfessor();
		if (theInstructor.equals(null)) { 
			System.out.println("Something went terribly wrong...");
		}
		else {
			System.out.println(theInstructor.toString());
		}
	}

}
