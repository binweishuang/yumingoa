package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.UsingcarDAO;
import runix.service.UsingcarService;
import runix.persistent.model.Usingcar;

public class UsingcarServiceImpl  implements UsingcarService {
	private UsingcarDAO usingcarDAO;


	public List getUsingcars(Map condition,int offset,int limit)throws Exception {
		return usingcarDAO.queryUsingcars(condition,offset,limit);
	}


	public int getCountUsingcars(Map condition)throws Exception {
		return usingcarDAO.queryCountUsingcars(condition);
	}


	public Usingcar getUsingcarById(String id)throws Exception {
		return usingcarDAO.queryUsingcarById(id);
	}


	public void removeUsingcarById(String id)throws Exception {
		 usingcarDAO.execDeleteUsingcarById(id);
	}


	public void saveUsingcar(Usingcar usingcar)throws Exception {
		 usingcarDAO.execInsertUsingcar(usingcar);
	}


	public void modifyUsingcar(Usingcar usingcar)throws Exception {
		 usingcarDAO.execUpdateUsingcar(usingcar);
	}


	public void setUsingcarDAO(UsingcarDAO usingcarDAO){
		this.usingcarDAO=usingcarDAO;
	}


	public List getUsingcarByIdForView(String id) throws Exception {
		return usingcarDAO.queryUsingcarByIdForView(id) ;
	}

}
