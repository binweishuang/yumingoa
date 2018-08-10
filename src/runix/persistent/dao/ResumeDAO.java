package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Resume;

public interface ResumeDAO {

	public List queryResumes(Map condition,int offset,int limit)throws Exception;

	public int queryCountResumes(Map condition)throws Exception;

	public Resume queryResumeById(String id)throws Exception;

	public void execDeleteResumeById(String id)throws Exception;

	public void execInsertResume(Resume resume)throws Exception;

	public void execUpdateResume(Resume resume)throws Exception;
	/**
	 * 根据招聘id删除简历
	 * @param id
	 * @throws Exception
	 */
	public void execDeleteResumeByRelateId(String id)throws Exception;

	public Resume queryResumeById2(String id)throws Exception;
}
