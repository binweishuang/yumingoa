package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.MatterDAO;
import runix.service.MatterService;
import runix.persistent.model.Matter;

public class MatterServiceImpl  implements MatterService {
	private MatterDAO matterDAO;


	public List getMatters(Map condition,int offset,int limit)throws Exception {
		return matterDAO.queryMatters(condition,offset,limit);
	}


	public int getCountMatters(Map condition)throws Exception {
		return matterDAO.queryCountMatters(condition);
	}


	public Matter getMatterById(String id)throws Exception {
		return matterDAO.queryMatterById(id);
	}


	public void removeMatterById(String id)throws Exception {
		 matterDAO.execDeleteMatterById(id);
	}


	public void saveMatter(Matter matter)throws Exception {
		 matterDAO.execInsertMatter(matter);
	}


	public void modifyMatter(Matter matter)throws Exception {
		 matterDAO.execUpdateMatter(matter);
	}


	public void setMatterDAO(MatterDAO matterDAO){
		this.matterDAO=matterDAO;
	}


	public void removeMatterByRelateId(String relateId) throws Exception {
		 matterDAO.execDeleteMatterByRelateId(relateId);
	}


	public List getMatters2(Map condition, int offset, int limit)
			throws Exception {
		return matterDAO.queryMatters2(condition,offset,limit);
	}


	public int getCountMatters2(Map condition) throws Exception {
		return matterDAO.queryCountMatters2(condition);
	}

	public List getMatters3(Map condition, int offset, int limit)
		throws Exception {
	     return matterDAO.queryMatters3(condition,offset,limit);
	}
	
	
	public int getCountMatters3(Map condition) throws Exception {
	  return matterDAO.queryCountMatters3(condition);
   }
}
