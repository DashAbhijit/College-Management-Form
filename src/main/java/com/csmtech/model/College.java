package com.csmtech.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table(name="college")
public class College {


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="college_id")
	private Integer collegeId;
	
	@Column(name="college_name")
	private String collegeName;
	
	@OneToMany(mappedBy = "college")
	private List<Branch> branch;

	public Integer getCollegeId() {
		return collegeId;
	}

	public void setCollegeId(Integer collegeId) {
		this.collegeId = collegeId;
	}

	public String getCollegeName() {
		return collegeName;
	}

	public void setCollegeName(String collegeName) {
		this.collegeName = collegeName;
	}


	public List<Branch> getBranch() {
		return branch;
	}

	public void setBranch(List<Branch> branch) {
		this.branch = branch;
	}

	@Override
	public String toString() {
		return "College [collegeId=" + collegeId + ", collegeName=" + collegeName + "]";
	}

	
	
	
	
}
