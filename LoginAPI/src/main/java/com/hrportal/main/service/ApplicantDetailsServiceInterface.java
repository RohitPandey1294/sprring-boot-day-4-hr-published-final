package com.hrportal.main.service;

import java.util.List;

import com.hrportal.main.domain.ApplicantDetails;
import com.hrportal.main.domain.JobDescription;

public interface ApplicantDetailsServiceInterface {
	public boolean addNewApplicantByJobId(ApplicantDetails applicantDetails);

	public List<ApplicantDetails> getAllApplicantDetails();
	
	
	public List<ApplicantDetails> getApplicantDetailForHrByJobId(String jobId);
	

	public ApplicantDetails updateApplicantStatusForHrByApplicantId(ApplicantDetails applicantDetails);
}
