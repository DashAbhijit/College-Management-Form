package com.csmtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.csmtech.model.Branch;
import com.csmtech.repository.BranchRepository;

@Service
public class BranchServiceImpl implements BranchService {

	@Autowired
	private BranchRepository branchRepository;
	
	
	@Override
	public List<Branch> allBranchList(Integer collegeId) {
		
		return branchRepository.allBranchList(collegeId);
	}


	@Override
	public List<Branch> allBranchList() {
		
		return branchRepository.findAll();
	}


}
