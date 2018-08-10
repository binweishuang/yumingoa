package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.OfficialDocumentDAO;
import runix.persistent.model.OfficialDocument;

public class OfficialDocumentDAOImpl extends BaseDAO implements OfficialDocumentDAO {

	public List queryOfficialDocuments(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("OfficialDocument.queryOfficialDocuments",condition,offset,limit);
	}


	public int queryCountOfficialDocuments(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("OfficialDocument.queryCountOfficialDocuments",condition);
	}


	public OfficialDocument queryOfficialDocumentById(String id)throws Exception {
		return (OfficialDocument)getSqlMapClientTemplate().queryForObject("OfficialDocument.queryOfficialDocumentById",id);
	}


	public void execDeleteOfficialDocumentById(String id)throws Exception {
		getSqlMapClientTemplate().delete("OfficialDocument.execDeleteOfficialDocumentById",id);
	}


	public void execInsertOfficialDocument(OfficialDocument officialDocument)throws Exception {
		getSqlMapClientTemplate().insert("OfficialDocument.execInsertOfficialDocument",officialDocument);
	}


	public void execUpdateOfficialDocument(OfficialDocument officialDocument)throws Exception {
		getSqlMapClientTemplate().update("OfficialDocument.execUpdateOfficialDocument",officialDocument);
	}


	public OfficialDocument queryOfficialDocumentByIdForView(String id)
			throws Exception {
		return (OfficialDocument)getSqlMapClientTemplate().queryForObject("OfficialDocument.queryOfficialDocumentByIdForView",id);
	}


	public List queryOfficialDocumentByIdForCheck(String id) throws Exception {
		return getSqlMapClientTemplate().queryForList("OfficialDocument.queryOfficialDocumentByIdForCheck",id);
	}


}
