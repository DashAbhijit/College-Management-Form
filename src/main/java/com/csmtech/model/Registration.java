package com.csmtech.model;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


@Entity
@Table(name="registration")
public class Registration {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="registration_id")
	private Integer registrationId;
	
	@Column(name="applicant_name")
	private String applicantName;
	
	@Column(name="email_id")
	private String emailId;
	
	@Column(name="mobile_no")
	private String mobileNo;
	
	private String gender;
	
	private Date dob;
	
	@Column(name="image_name")
	private String imageName;
	
	@Column(name="image_path")
	private String imagePath;
	
	private Integer age;
	
    @Transient
    //transient indicates that captcha data is not stored in database and also means that something which is used in UI not used in database
	private String captcha;
    
	/*
	 * @Transient private String hidden;
	 * 
	 * @Transient private String image;
	 */
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="branch_id")
	private Branch branch;
	
	
	@ManyToOne(cascade= CascadeType.ALL)
	@JoinColumn(name="college_id")
	private College college;
	
	@Column(name="is_delete")
	private String isDelete;
	

	public Integer getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(Integer registrationId) {
		this.registrationId = registrationId;
	}

	public String getApplicantName() {
		return applicantName;
	}

	public void setApplicantName(String applicantName) {
		this.applicantName = applicantName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dateOfBirth) {
		this.dob = dateOfBirth;
	}

	public String getImagePath() {
		return imagePath;
	}

	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}
	
	

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getIsDelete() {
		return isDelete;
	}

	public void setIsDelete(String isDelete) {
		this.isDelete = isDelete;
	}

	
	
	public String getImageName() {
		return imageName;
	}

	public void setImageName(String imageName) {
		this.imageName = imageName;
	}

	
	public College getCollege() {
		return college;
	}

	public void setCollege(College college) {
		this.college = college;
	}

	
	
	public String getCaptcha() {
		return captcha;
	}

	public void setCaptcha(String captcha) {
		this.captcha = captcha;
	}



	@Override
	public String toString() {
		return "Registration [registrationId=" + registrationId + ", applicantName=" + applicantName + ", emailId="
				+ emailId + ", mobileNo=" + mobileNo + ", gender=" + gender + ", dob=" + dob + ", imageName="
				+ imageName + ", imagePath=" + imagePath + ", age=" + age + ", branch=" + branch + ", college="
				+ college + ", isDelete=" + isDelete + "]";
	}

	
	

	

	
	
}
