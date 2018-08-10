package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.RecruitmentDAO;
import runix.service.RecruitmentService;
import runix.persistent.model.Recruitment;

public class RecruitmentServiceImpl  implements RecruitmentService {
	private RecruitmentDAO recruitmentDAO;


	public List getRecruitments(Map condition,int offset,int limit)throws Exception {
		return recruitmentDAO.queryRecruitments(condition,offset,limit);
	}


	public int getCountRecruitments(Map condition)throws Exception {
		return recruitmentDAO.queryCountRecruitments(condition);
	}


	public Recruitment getRecruitmentById(String id)throws Exception {
		return recruitmentDAO.queryRecruitmentById(id);
	}


	public void removeRecruitmentById(String id)throws Exception {
		 recruitmentDAO.execDeleteRecruitmentById(id);
	}


	public void saveRecruitment(Recruitment recruitment)throws Exception {
		 recruitmentDAO.execInsertRecruitment(recruitment);
	}


	public void modifyRecruitment(Recruitment recruitment)throws Exception {
		 recruitmentDAO.execUpdateRecruitment(recruitment);
	}


	public void setRecruitmentDAO(RecruitmentDAO recruitmentDAO){
		this.recruitmentDAO=recruitmentDAO;
	}


	public List getAllRecruitments() throws Exception {
		return recruitmentDAO.queryAllRecruitments();
	}

	public List getRecruitmentsCount(Map condition,int offset,int limit)throws Exception {
		return recruitmentDAO.queryRecruitmentsCount(condition,offset,limit);
	}


	public int getCountRecruitmentsCount(Map condition)throws Exception {
		return recruitmentDAO.queryCountRecruitmentsCount(condition);
	}


	public List getRecruitmentByIdForCheck(String id) throws Exception {
		return recruitmentDAO.queryRecruitmentByIdForCheck(id);
	}
}
