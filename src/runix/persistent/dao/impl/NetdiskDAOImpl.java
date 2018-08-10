package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.NetdiskDAO;
import runix.persistent.model.Netdisk;

public class NetdiskDAOImpl extends BaseDAO implements NetdiskDAO {

	public List queryNetdisks(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Netdisk.queryNetdisks",condition,offset,limit);
	}
	public List queryNetdisks1(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Netdisk.queryNetdisks1",condition,offset,limit);
	}

	public int queryCountNetdisks(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Netdisk.queryCountNetdisks",condition);
	}
	public int queryCountNetdisks1(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Netdisk.queryCountNetdisks1",condition);
	}

	public Netdisk queryNetdiskById(String id)throws Exception {
		return (Netdisk)getSqlMapClientTemplate().queryForObject("Netdisk.queryNetdiskById",id);
	}

	public void execDeleteNetdiskById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Netdisk.execDeleteNetdiskById",id);
	}

	public void execDeleteNetdiskByPath(String path) throws Exception{
		getSqlMapClientTemplate().delete("Netdisk.execDeleteNetdiskByPath",path);
	}
	public void execInsertNetdisk(Netdisk netdisk)throws Exception {
		getSqlMapClientTemplate().insert("Netdisk.execInsertNetdisk",netdisk);
	}


	public void execUpdateNetdisk(Netdisk netdisk)throws Exception {
		getSqlMapClientTemplate().update("Netdisk.execUpdateNetdisk",netdisk);
	}
	
	//更具文件ID删除此文件夹下的所有文件
	public void execDeleteAllNetdisk(String folderID) throws Exception {
		getSqlMapClientTemplate().delete("Netdisk.execDeleteAllNetdisk",folderID);
	}
	
	public List findAll(String folderID) throws Exception{
		return getSqlMapClientTemplate().queryForList("Netdisk.queryNetdisksByFolderID",folderID);
	}

}
