package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.FolderDAO;
import runix.persistent.model.Folder;

public class FolderDAOImpl extends BaseDAO implements FolderDAO {

	public List queryFolders(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Folder.queryFolders",condition,offset,limit);
	}


	public int queryCountFolders(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Folder.queryCountFolders",condition);
	}


	public Folder checkID(String id) throws Exception {
		return (Folder) getSqlMapClientTemplate().queryForObject("Folder.checkID",id);
	}

	
	public Folder queryFolderById(String id)throws Exception {
		return (Folder)getSqlMapClientTemplate().queryForObject("Folder.queryFolderById",id);
	}


	public void execDeleteFolderById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Folder.execDeleteFolderById",id);
	}


	public void execInsertFolder(Folder folder)throws Exception {
		getSqlMapClientTemplate().insert("Folder.execInsertFolder",folder);
	}


	public void execUpdateFolder(Folder folder)throws Exception {
		getSqlMapClientTemplate().update("Folder.execUpdateFolder",folder);
	}



}
