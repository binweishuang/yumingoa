package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.ResumeDAO;
import runix.persistent.model.Resume;

public class ResumeDAOImpl extends BaseDAO implements ResumeDAO {

	public List queryResumes(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Resume.queryResumes",condition,offset,limit);
	}


	public int queryCountResumes(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Resume.queryCountResumes",condition);
	}


	public Resume queryResumeById(String id)throws Exception {
		return (Resume)getSqlMapClientTemplate().queryForObject("Resume.queryResumeById",id);
	}


	public void execDeleteResumeById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Resume.execDeleteResumeById",id);
	}


	public void execInsertResume(Resume resume)throws Exception {
		getSqlMapClientTemplate().insert("Resume.execInsertResume",resume);
	}


	public void execUpdateResume(Resume resume)throws Exception {
		getSqlMapClientTemplate().update("Resume.execUpdateResume",resume);
	}


	public void execDeleteResumeByRelateId(String id) throws Exception {
		getSqlMapClientTemplate().delete("Resume.execDeleteResumeByRelateId",id);
	}


	public Resume queryResumeById2(String id) throws Exception {
		return (Resume)getSqlMapClientTemplate().queryForObject("Resume.queryResumeById2",id);
	}


}
