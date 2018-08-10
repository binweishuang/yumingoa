package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.DutyMessageDAO;
import runix.service.DutyMessageService;
import runix.persistent.model.DutyMessage;

/**
 * 值班信息service实现类
 */
public class DutyMessageServiceImpl  implements DutyMessageService {
	private DutyMessageDAO dutyMessageDAO;


	/**
	 * 按条件查询值班信息列表
	 * @author SY
	 * @time 2013-12-02
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getDutyMessages(Map condition,int offset,int limit)throws Exception {
		return dutyMessageDAO.queryDutyMessages(condition,offset,limit);
	}

	/**
	 * 按条件查询值班信息数量
	 * @author SY
	 * @time 2013-12-02
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountDutyMessages(Map condition)throws Exception {
		return dutyMessageDAO.queryCountDutyMessages(condition);
	}

	/**
	 * 根据ID查询值班信息
	 * @author SY
	 * @time 2013-12-02
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public DutyMessage getDutyMessageById(String id)throws Exception {
		return dutyMessageDAO.queryDutyMessageById(id);
	}

	/**
	 * 根据ID查询值班信息和值班人名称
	 * @author SY
	 * @time 2013-12-02 16:44
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getDutyMessageAndNameById(String id)throws Exception{
		return dutyMessageDAO.queryDutyMessageAndNameById(id);
	}

	/**
	 * 删除值班信息
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void removeDutyMessageById(String id)throws Exception {
		 dutyMessageDAO.execDeleteDutyMessageById(id);
	}

	/**
	 * 添加值班信息
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void saveDutyMessage(DutyMessage dutyMessage)throws Exception {
		 dutyMessageDAO.execInsertDutyMessage(dutyMessage);
	}

	/**
	 * 修改值班信息
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void modifyDutyMessage(DutyMessage dutyMessage)throws Exception {
		 dutyMessageDAO.execUpdateDutyMessage(dutyMessage);
	}


	public void setDutyMessageDAO(DutyMessageDAO dutyMessageDAO){
		this.dutyMessageDAO=dutyMessageDAO;
	}

}
