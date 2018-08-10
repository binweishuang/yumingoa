package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.DutyMessage;

/**
 * 值班信息service接口
 */
public interface DutyMessageService {

	/**
	 * 按条件查询值班信息列表
	 * @author SY
	 * @time  2013-12-02
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getDutyMessages(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询值班信息数量
	 * @author SY
	 * @time  2013-12-02
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountDutyMessages(Map condition)throws Exception;

	/**
	 * 根据ID查询值班信息
	 * @author SY
	 * @time  2013-12-02
	 * @return 返回值班信息实体
	 * @throws Exception
	 */
	public DutyMessage getDutyMessageById(String id)throws Exception;
	
	/**
	 * 根据ID查询值班信息和值班人名称
	 * @author SY
	 * @time 2013-12-02 16:44
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getDutyMessageAndNameById(String id)throws Exception;

	/**
	 * 删除值班信息
	 * @author SY
	 * @time  2013-12-02
	 * @throws Exception
	 */
	public void removeDutyMessageById(String id)throws Exception;

	/**
	 * 添加值班信息
	 * @author SY
	 * @time  2013-12-02
	 * @throws Exception
	 */
	public void saveDutyMessage(DutyMessage dutyMessage)throws Exception;

	/**
	 * 修改值班信息
	 * @author SY
	 * @time  2013-12-02
	 * @throws Exception
	 */
	public void modifyDutyMessage(DutyMessage dutyMessage)throws Exception;


}
