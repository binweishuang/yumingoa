package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.NetmeetingDAO;
import runix.service.NetmeetingService;
import runix.persistent.model.Netmeeting;

public class NetmeetingServiceImpl  implements NetmeetingService {
	private NetmeetingDAO netmeetingDAO;


	public List getNetmeetings(Map condition,int offset,int limit)throws Exception {
		return netmeetingDAO.queryNetmeetings(condition,offset,limit);
	}


	public int getCountNetmeetings(Map condition)throws Exception {
		return netmeetingDAO.queryCountNetmeetings(condition);
	}


	public Netmeeting getNetmeetingById(String id)throws Exception {
		return netmeetingDAO.queryNetmeetingById(id);
	}


	public void removeNetmeetingById(String id)throws Exception {
		 netmeetingDAO.execDeleteNetmeetingById(id);
	}


	public void saveNetmeeting(Netmeeting netmeeting)throws Exception {
		 netmeetingDAO.execInsertNetmeeting(netmeeting);
	}


	public void modifyNetmeeting(Netmeeting netmeeting)throws Exception {
		 netmeetingDAO.execUpdateNetmeeting(netmeeting);
	}


	public void setNetmeetingDAO(NetmeetingDAO netmeetingDAO){
		this.netmeetingDAO=netmeetingDAO;
	}

}
