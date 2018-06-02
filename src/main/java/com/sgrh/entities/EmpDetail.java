package com.sgrh.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="EmpMast")
public class EmpDetail {
	@Column(name="EmpName")
	private String fname;
	@Column(name="EmpSurName")
	private String lname;
	@Id
	private String empcode;
	@Column(name="LocalPhone")
	private String mobileNo;
	/*@Column
	private String category;*/
	@Column(name="LocalAddress")
	private String address;
	
	public String getFName() {
		return fname;
	}
	public void setFName(String name) {
		this.lname = name;
	}
	public String getLName() {
		return lname;
	}
	public void setLName(String name) {
		this.lname = name;
	}
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	/*public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}*/
	
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
}
