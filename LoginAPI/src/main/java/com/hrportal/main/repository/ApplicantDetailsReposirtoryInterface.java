package com.hrportal.main.repository;

import java.util.List;

import com.hrportal.main.domain.ApplicantDetails;
import com.hrportal.main.domain.EmployeeDetails;

public interface ApplicantDetailsReposirtoryInterface {

	public boolean addNewApplicantByJobId(ApplicantDetails applicantDetails);
	
	public List<ApplicantDetails> getAllApplicantDetails();
	
	public List<ApplicantDetails> getApplicantDetailForHrByJobId(String jobId);
	
	public ApplicantDetails updateApplicantStatusForHrByApplicantId(ApplicantDetails applicantDetails);
}
