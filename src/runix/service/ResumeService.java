package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Resume;

public interface ResumeService {

	public List getResumes(Map condition,int offset,int limit)throws Exception;

	public int getCountResumes(Map condition)throws Exception;

	public Resume getResumeById(String id)throws Exception;

	public void removeResumeById(String id)throws Exception;

	public void saveResume(Resume resume)throws Exception;

	public void modifyResume(Resume resume)throws Exception;
	/**
	 * 根据招聘id删除简历
	 * @param id
	 * @throws Exception
	 */
	public void removeResumeByRelateId(String id)throws Exception;
	
	public Resume getResumeById2(String id)throws Exception;

}
