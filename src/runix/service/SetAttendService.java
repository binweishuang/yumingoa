package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.SetAttend;

public interface SetAttendService {

	public List getSetAttends(Map condition,int offset,int limit)throws Exception;

	public int getCountSetAttends(Map condition)throws Exception;

	public SetAttend getSetAttendById(String id)throws Exception;

	public void removeSetAttendById(String id)throws Exception;

	public void saveSetAttend(SetAttend setAttend)throws Exception;

	public void modifySetAttend(SetAttend setAttend)throws Exception;
	/*
	 * ycr 2013-11-26
	 * 查询是否已有考情设置
	 */
	public SetAttend getSetAttend() throws Exception;


}
