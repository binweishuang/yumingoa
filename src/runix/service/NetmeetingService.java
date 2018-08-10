package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Netmeeting;

/**
 * 网络会议service接口
 */
public interface NetmeetingService {

	/**
	 * 按条件查询网络会议列表
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getNetmeetings(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询网络会议数量
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountNetmeetings(Map condition)throws Exception;

	/**
	 * 根据ID查询网络会议
	 * @author SY
	 * @time 
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public Netmeeting getNetmeetingById(String id)throws Exception;

	/**
	 * 删除网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void removeNetmeetingById(String id)throws Exception;

	/**
	 * 添加网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void saveNetmeeting(Netmeeting netmeeting)throws Exception;

	/**
	 * 修改网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void modifyNetmeeting(Netmeeting netmeeting)throws Exception;


}
