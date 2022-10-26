package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.model.Registration;
import com.csmtech.repository.RegistrationRepository;

@Service
public class RegistrationServiceImpl implements RegistrationService{

	@Autowired
	private RegistrationRepository registrationRepository;
	 
	@Override
	public Registration saveAllRegistrationDetails(Registration registration) {
		
		return registrationRepository.save(registration);
	}

	@Override
	public List<Registration> getAllRegistration() {
		
		return registrationRepository.getAllRegistration();
	}

	@Override
	public void deleteRegistrationById(Integer registrationId) {
		
		 registrationRepository.deleteRegistrationById(registrationId);
	}

	@Override
	public Registration updateRegistrationById(Integer registrationId) {
		
		return registrationRepository.findById(registrationId).get();
	}

	@Override
	public List<Registration> listAll() {
		
		return registrationRepository.findAll();
	}

	@Override
	public Registration getDocumentDetailsByRegistrationId(Integer registrationId) {
		
		return registrationRepository.getDocumentDetailsByRegistrationId(registrationId);
	}

	@Override
	public Registration downloadDetailsById(Integer registrationId) {
		
		return registrationRepository.downloadDetailsById(registrationId);
	}

	@Override
	public List<Registration> findDetailsByCollegeName(Integer cid) {
		
		return registrationRepository.findDetailsByCollegeName(cid);
	}

	@Override
	public List<Registration> findDetailsByCollegeNameAndBranchName(Integer cid, Integer bid) {
		
		return registrationRepository.findDetailsByCollegeNameAndBranchName(cid, bid);
	}

	
}

