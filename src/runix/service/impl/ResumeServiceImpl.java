package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.ResumeDAO;
import runix.service.ResumeService;
import runix.persistent.model.Resume;

public class ResumeServiceImpl  implements ResumeService {
	private ResumeDAO resumeDAO;


	public List getResumes(Map condition,int offset,int limit)throws Exception {
		return resumeDAO.queryResumes(condition,offset,limit);
	}


	public int getCountResumes(Map condition)throws Exception {
		return resumeDAO.queryCountResumes(condition);
	}


	public Resume getResumeById(String id)throws Exception {
		return resumeDAO.queryResumeById(id);
	}


	public void removeResumeById(String id)throws Exception {
		 resumeDAO.execDeleteResumeById(id);
	}


	public void saveResume(Resume resume)throws Exception {
		 resumeDAO.execInsertResume(resume);
	}


	public void modifyResume(Resume resume)throws Exception {
		 resumeDAO.execUpdateResume(resume);
	}


	public void setResumeDAO(ResumeDAO resumeDAO){
		this.resumeDAO=resumeDAO;
	}


	public void removeResumeByRelateId(String id) throws Exception {
		resumeDAO.execDeleteResumeByRelateId(id);
	}


	public Resume getResumeById2(String id) throws Exception {
		return resumeDAO.queryResumeById2(id);
	}

}
