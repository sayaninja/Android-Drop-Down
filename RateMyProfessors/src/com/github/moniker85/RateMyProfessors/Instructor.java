package com.github.moniker85.RateMyProfessors;

public class Instructor {
	private String lastName;
	private String nameWithInitials, firstInitial, secondInitial;
	private String fullName;
	private String department;
	
	private double quality, helpfulness, clarity, easiness;
	
	
	//No Argument Constructor
	public Instructor() {
		
	}

	//Setters
	public void setLastName(String theLastName) {
		this.lastName = theLastName;
	}
	
	public void setNameWithInitials(String theNameWithInitials) {
		String [] name = theNameWithInitials.split(" ",5);
		
		if (name.length == 1) {
			this.setLastName(name[0]);
			this.firstInitial = "";
			this.secondInitial = "";
		}
		else {
			this.lastName = name[0];
			if (name[1].length() == 1) {
				this.firstInitial = name[1];
				if (name.length == 2) {
					this.firstInitial = name[1];
					this.secondInitial = "";
				}
				else {
					this.secondInitial = name[2];
				}
			}
			else {
				this.lastName += " " + name[1];
				if (name.length == 3) {
					this.firstInitial = name[2];
					this.secondInitial = "";
				}
				else if (name.length == 4) {
					this.firstInitial = name[2];
					this.secondInitial = name[3];
				}
				else {
					this.firstInitial = "";
					this.secondInitial = "";
				}
			}
		}
		this.nameWithInitials = theNameWithInitials;
	}
	
	public void setFirstInitial(String theFirstInitial) {
		this.firstInitial = theFirstInitial;
	}
	
	public void setSecondInitial(String theSecondInitial) {
		this.secondInitial = theSecondInitial;
	}
	
	public void setFullName(String theFullName) {
		this.fullName = theFullName;
	}
	
	public void setDepartment(String theDepartment) {
		this.department = theDepartment;
	}
	
	public void setQuality(double theQuality) {
		this.quality = theQuality;
	}

	public void setHelpfulness(double theHelpfulness) {
		this.helpfulness = theHelpfulness;
	}
	
	public void setClarity(double theClarity) {
		this.clarity = theClarity;
	}
	
	public void setEasiness(double theEasiness) {
		this.easiness = theEasiness;
	}
	
	
	//Getters
	public String getLastName() {
		return this.lastName;
	}
	
	public String getNameWithInitials() {
		return this.nameWithInitials;
	}
	
	public String getFirstInitial() {
		return this.firstInitial;
	}
	
	public String getSecondInitial() {
		return this.secondInitial;
	}
	
	public String getFullName() {
		return this.fullName;
	}
	
	public String getDepartment() {
		return this.department;
	}
	
	public double getQuality() {
		return this.quality;
	}
	
	public double getHelpfulness() {
		return this.helpfulness;
	}
	
	public double getClarity() {
		return this.clarity;
	}
	
	public double getEasiness() {
		return this.easiness;
	}

	//ToString
	@Override
	public String toString() {
		String result = "Full Name:			" + this.fullName + "\n"
					+	"First Initial			" + this.firstInitial + "\n"
					+	"Second Initial			" + this.secondInitial + "\n"
					+	"Last Name			" + this.lastName + "\n"
					+	"Name with Initials		" + this.nameWithInitials + "\n"
					+	"Department			" + this.department + "\n"
					+	"Quality				" + this.quality + "\n"
					+	"Helpfulness			" + this.helpfulness + "\n"
					+	"Clarity				" + this.clarity + "\n"
					+	"Easiness			" + this.easiness + "\n";
		return result;
	}

	//Overriding .equals Later
}