package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.EmailDAO;
import runix.persistent.model.Email;

public class EmailDAOImpl extends BaseDAO implements EmailDAO {

	public List queryEmails(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Email.queryEmails",condition,offset,limit);
	}


	public int queryCountEmails(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Email.queryCountEmails",condition);
	}


	public Email queryEmailById(String id)throws Exception {
		return (Email)getSqlMapClientTemplate().queryForObject("Email.queryEmailById",id);
	}


	public void execDeleteEmailById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Email.execDeleteEmailById",id);
	}


	public void execInsertEmail(Email email)throws Exception {
		getSqlMapClientTemplate().insert("Email.execInsertEmail",email);
	}


	public void execUpdateEmail(Email email)throws Exception {
		getSqlMapClientTemplate().update("Email.execUpdateEmail",email);
	}


	public Email queryEmailById2(String id) throws Exception {
		return (Email)getSqlMapClientTemplate().queryForObject("Email.queryEmailById2",id);
	}


}
