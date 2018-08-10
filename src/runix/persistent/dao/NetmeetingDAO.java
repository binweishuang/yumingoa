package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Netmeeting;

/**
 * 网络会议DAO接口
 */
public interface NetmeetingDAO {

	/**
	 * 查询网络会议列表
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return 
	 * @throws Exception
	 */
	public List queryNetmeetings(Map condition,int offset,int limit)throws Exception;

	/**
	 * 查询网络会议数量
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int queryCountNetmeetings(Map condition)throws Exception;

	/**
	 * 根据ID查询网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public Netmeeting queryNetmeetingById(String id)throws Exception;

	/**
	 * 删除网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execDeleteNetmeetingById(String id)throws Exception;

	/**
	 * 添加网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execInsertNetmeeting(Netmeeting netmeeting)throws Exception;

	/**
	 * 修改网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void execUpdateNetmeeting(Netmeeting netmeeting)throws Exception;


}
