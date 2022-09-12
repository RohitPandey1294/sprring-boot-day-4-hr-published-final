package com.hrportal.main.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hrportal.main.domain.ApplicantDetails;

@Repository
public class ApplicantDetailsRepositoryIMPL implements ApplicantDetailsReposirtoryInterface {

	private static final String INSERT_NEW_APPLICANT = "insert into applicant_details (applicant_id,applicant_first_name,applicant_last_name,doc_id,job_id,highest_qualification,\r\n"
			+ "technical_skills_1,technical_skills_2,technical_skills_3,gender,contact_no,date_of_birth,passout,status)"
			+ "values(applicant_id_seq.NEXTVAL,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	private static final String SELECT_ALL_APPLICANT = "select * from applicant_details";

	private static final String GET_APPLICANT_DETAILS_FOR_HR_BY_JOBID = "select \r\n"
			+ "jd.job_id,jd.project_id,jd.technical_skills_1,jd.technical_skills_2,jd.technical_skills_3,jd.required_candidates,jd.remaining_budget,jd.status,jd.employee_id as mgr_employee_id,\r\n"
			+ "ad.applicant_id,ad.applicant_first_name,ad.applicant_last_name,ad.doc_id,ad.job_id,ad.highest_qualification,ad.technical_skills_1,ad.technical_skills_2,ad.technical_skills_3,ad.gender,ad.contact_no,ad.date_of_birth,ad.passout,ad.status\r\n"
			+ "from applicant_details ad JOIN job_description jd\r\n" + "ON\r\n"
			+ "jd.technical_skills_1=ad.technical_skills_1 and\r\n"
			+ "jd.technical_skills_2=ad.technical_skills_2 and\r\n"
			+ "jd.technical_skills_3=ad.technical_skills_3 and\r\n" + "jd.job_id=?";
	
	private static final String UPDATE_APPLICANT_STATUS_FOR_HR_BY_APPLICANTID="update applicant_details set status='hired' where applicant_id=?";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public boolean addNewApplicantByJobId(ApplicantDetails applicantDetails) {
		Object[] params = { applicantDetails.getApplicantFirstName(), applicantDetails.getApplicantLastName(),
				applicantDetails.getDocumentsDetails().getDocId(), applicantDetails.getJobDescription().getJobId(),
				applicantDetails.getHighestQualification(), applicantDetails.getTechnicalskills1(),
				applicantDetails.getTechnicalskills2(), applicantDetails.getTechnicalskills3(),
				applicantDetails.getGender(), applicantDetails.getContactNo(), applicantDetails.getDateOfBirth(),
				applicantDetails.getPassout(), applicantDetails.getStatus() };
		int result = jdbcTemplate.update(INSERT_NEW_APPLICANT, params);
		if (result > 0)
			return true;
		return false;
	}

	@Override
	public List<ApplicantDetails> getAllApplicantDetails() {
		List<ApplicantDetails> allapplicantDetails = jdbcTemplate.query(SELECT_ALL_APPLICANT,
				new ApplicantDetailsRowMapper());
		return allapplicantDetails;
	}

	@Override
	public List<ApplicantDetails> getApplicantDetailForHrByJobId(String jobId) {

		return this.jdbcTemplate.query(GET_APPLICANT_DETAILS_FOR_HR_BY_JOBID, new ApplicantDetailsRowMapper(), jobId);
	}

	@Override
	public ApplicantDetails updateApplicantStatusForHrByApplicantId(ApplicantDetails applicantDetails) {
		this.jdbcTemplate.update(UPDATE_APPLICANT_STATUS_FOR_HR_BY_APPLICANTID, applicantDetails.getApplicantId());
		return applicantDetails;
	}

}
