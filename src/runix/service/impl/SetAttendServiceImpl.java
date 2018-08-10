package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.SetAttendDAO;
import runix.service.SetAttendService;
import runix.persistent.model.SetAttend;

public class SetAttendServiceImpl  implements SetAttendService {
	private SetAttendDAO setAttendDAO;


	public List getSetAttends(Map condition,int offset,int limit)throws Exception {
		return setAttendDAO.querySetAttends(condition,offset,limit);
	}


	public int getCountSetAttends(Map condition)throws Exception {
		return setAttendDAO.queryCountSetAttends(condition);
	}


	public SetAttend getSetAttendById(String id)throws Exception {
		return setAttendDAO.querySetAttendById(id);
	}


	public void removeSetAttendById(String id)throws Exception {
		 setAttendDAO.execDeleteSetAttendById(id);
	}


	public void saveSetAttend(SetAttend setAttend)throws Exception {
		 setAttendDAO.execInsertSetAttend(setAttend);
	}


	public void modifySetAttend(SetAttend setAttend)throws Exception {
		 setAttendDAO.execUpdateSetAttend(setAttend);
	}


	public void setSetAttendDAO(SetAttendDAO setAttendDAO){
		this.setAttendDAO=setAttendDAO;
	}


	public SetAttend getSetAttend() throws Exception {
		return setAttendDAO.querySetAttend();
	}

}
