package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.DutyMessage;

/**
 * 值班信息DAO接口
 */
public interface DutyMessageDAO {

	/**
	 * 按条件查询值班信息列表
	 * @author SY
	 * @time 2013-12-02 16:40
	 * @param condition 查询条件
	 * @param offset 当前页数
	 * @param limit 每页显示记录数
	 * @return
	 * @throws Exception
	 */
	public List queryDutyMessages(Map condition,int offset,int limit)throws Exception;

	/**
	 * 按条件查询值班信息数量
	 * @author SY
	 * @time 2013-12-02
	 * @param condition 查询条件
	 * @return
	 * @throws Exception
	 */
	public int queryCountDutyMessages(Map condition)throws Exception;

	/**
	 * 根据ID查询值班信息
	 * @author SY
	 * @time 2013-12-02
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public DutyMessage queryDutyMessageById(String id)throws Exception;

	/**
	 * 根据ID查询值班信息和值班人名称
	 * @author SY
	 * @time 2013-12-02 16:42
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryDutyMessageAndNameById(String id)throws Exception;
	
	/**
	 * 删除值班信息
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void execDeleteDutyMessageById(String id)throws Exception;

	/**
	 * 添加值班信息
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void execInsertDutyMessage(DutyMessage dutyMessage)throws Exception;

	/**
	 * 修改值班信息
	 * @author SY
	 * @time 2013-12-02
	 * @throws Exception
	 */
	public void execUpdateDutyMessage(DutyMessage dutyMessage)throws Exception;


}
