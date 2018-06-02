package com.sgrh.entities;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParkingDetails {
	/*@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;*/
	@Column
	private String name;
	@Column
	private String empcode;
	@Id
	private String passNo;
	@Column
	private String mobileNo;
	@Column
	private String category;
	@Column
	private String address;
	@Column
	private String cModel;
	@Column
	private String cMake;
	@Column
	private String cReg;
	@Column
	private Date issueDate;
	
	@Column(name="creation_time")
	private Date creationDate;
	
	private Date validDate;
	/*
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}*/
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmpcode() {
		return empcode;
	}
	public void setEmpcode(String empcode) {
		this.empcode = empcode;
	}
	public String getPassNo() {
		return passNo;
	}
	public void setPassNo(String passNo) {
		this.passNo = passNo;
	}
	public String getMobileNo() {
		return mobileNo;
	}
	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getcModel() {
		return cModel;
	}
	public void setcModel(String cModel) {
		this.cModel = cModel;
	}
	public String getcMake() {
		return cMake;
	}
	public void setcMake(String cMake) {
		this.cMake = cMake;
	}
	public String getcReg() {
		return cReg;
	}
	public void setcReg(String cReg) {
		this.cReg = cReg;
	}
	public Date getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
	public Date getValidDate() {
		return validDate;
	}
	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}
	
	public Date getCreationDate() {
		return creationDate;
	}
	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
}
