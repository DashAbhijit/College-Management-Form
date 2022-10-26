package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.model.College;
import com.csmtech.repository.CollegeRepository;

@Service
public class CollegeServiceImpl implements CollegeService {


	@Autowired
	private CollegeRepository collegeRepository;
	
	@Override
	public List<College> getAllCollege() {
		
		return collegeRepository.findAll();
	}

}
