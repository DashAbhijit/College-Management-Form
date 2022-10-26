package com.csmtech.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.csmtech.model.Registration;

public interface RegistrationRepository extends JpaRepository<Registration, Integer> {

	
	@Query("From Registration where branch.college.collegeId=:id")
	List<Registration> getAllCollegeByName(Integer id);

	@Transactional
	@Modifying
	@Query("Update Registration set isDelete='Yes' where registrationId=:registrationId")
	void deleteRegistrationById(Integer registrationId);

	@Query("From Registration where isDelete='No'")
	List<Registration> getAllRegistration();

	Registration getDocumentDetailsByRegistrationId(Integer registrationId);

	@Query("From Registration Where registrationId=:registrationId")
	Registration downloadDetailsById(Integer registrationId);

	@Query("from Registration where college.collegeId=:cid")
	List<Registration> findDetailsByCollegeName(Integer cid);

	@Query("from Registration where college.collegeId=:cid and branch.branchId=:bid")
	List<Registration> findDetailsByCollegeNameAndBranchName(Integer cid, Integer bid);
}

