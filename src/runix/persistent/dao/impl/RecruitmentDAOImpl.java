package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.RecruitmentDAO;
import runix.persistent.model.Recruitment;

public class RecruitmentDAOImpl extends BaseDAO implements RecruitmentDAO {

	public List queryRecruitments(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Recruitment.queryRecruitments",condition,offset,limit);
	}


	public int queryCountRecruitments(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Recruitment.queryCountRecruitments",condition);
	}


	public Recruitment queryRecruitmentById(String id)throws Exception {
		return (Recruitment)getSqlMapClientTemplate().queryForObject("Recruitment.queryRecruitmentById",id);
	}


	public void execDeleteRecruitmentById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Recruitment.execDeleteRecruitmentById",id);
	}


	public void execInsertRecruitment(Recruitment recruitment)throws Exception {
		getSqlMapClientTemplate().insert("Recruitment.execInsertRecruitment",recruitment);
	}


	public void execUpdateRecruitment(Recruitment recruitment)throws Exception {
		getSqlMapClientTemplate().update("Recruitment.execUpdateRecruitment",recruitment);
	}


	public List queryAllRecruitments() throws Exception {
		return getSqlMapClientTemplate().queryForList("Recruitment.queryAllRecruitments");
	}
	
	public List queryRecruitmentsCount(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Recruitment.queryRecruitmentsCount",condition,offset,limit);
	}


	public int queryCountRecruitmentsCount(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Recruitment.queryCountRecruitmentsCount",condition);
	}


	public List queryRecruitmentByIdForCheck(String id) throws Exception {
		return getSqlMapClientTemplate().queryForList("Recruitment.queryRecruitmentByIdForCheck",id);
	}

}
