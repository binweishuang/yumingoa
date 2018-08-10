package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.ArchiveDAO;
import runix.persistent.model.Archive;

public class ArchiveDAOImpl extends BaseDAO implements ArchiveDAO {

	public List queryArchives(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Archive.queryArchives",condition,offset,limit);
	}


	public int queryCountArchives(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Archive.queryCountArchives",condition);
	}


	public Archive queryArchiveById(String id)throws Exception {
		return (Archive)getSqlMapClientTemplate().queryForObject("Archive.queryArchiveById",id);
	}


	public void execDeleteArchiveById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Archive.execDeleteArchiveById",id);
	}


	public void execInsertArchive(Archive archive)throws Exception {
		getSqlMapClientTemplate().insert("Archive.execInsertArchive",archive);
	}


	public void execUpdateArchive(Archive archive)throws Exception {
		getSqlMapClientTemplate().update("Archive.execUpdateArchive",archive);
	}


	public Archive queryArchiveByIdForView(String id) throws Exception {
		return (Archive)getSqlMapClientTemplate().queryForObject("Archive.queryArchiveByIdForView",id);
	}


}
