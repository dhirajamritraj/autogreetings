package com.autogreetings.model;

import java.io.File;
import java.sql.Date;

public class Employee {

	private String name;
	private String empID;
	private Date doj;
	private Date dob;
	private String emailID;
	private File empImg;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmpID() {
		return empID;
	}
	public void setEmpID(String empID) {
		this.empID = empID;
	}
	public Date getDoj() {
		return doj;
	}
	public void setDoj(Date doj) {
		this.doj = doj;
	}
	public Date getDob() {
		return dob;
	}
	public void setDob(Date dob) {
		this.dob = dob;
	}
	public String getEmailID() {
		return emailID;
	}
	public void setEmailID(String emailID) {
		this.emailID = emailID;
	}
	public File getEmpImg() {
		return empImg;
	}
	public void setEmpImg(File empImg) {
		this.empImg = empImg;
	}
	
	
}
