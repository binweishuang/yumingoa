package runix.persistent.dao;

import java.util.List;
import java.util.Map;
import runix.persistent.model.BaseData;
import runix.persistent.model.FlowPeople;
import runix.persistent.model.MessageCenter;

public interface BaseDataDAO {

	public List queryBaseDatas(Map condition, int offset, int limit)
			throws Exception;

	public int queryCountBaseDatas(Map condition) throws Exception;

	public BaseData queryBaseDataById(String id) throws Exception;

	public void execDeleteBaseDataById(String id) throws Exception;

	public void execInsertBaseData(BaseData baseData) throws Exception;

	public void execUpdateBaseData(BaseData baseData) throws Exception;

	/**
	 * 查询序列的值
	 * 
	 * @return
	 * @throws Exception
	 */
	public String querySequence() throws Exception;

	/**
	 * 根据数据类型找到对应的数据类型
	 * 
	 * @author SY
	 * @time 2013-11-25 15:51
	 * @param datatype
	 * @return
	 * @throws Exception
	 */
	public List queryBaseByDatatype(String datatype)throws Exception;
	/**
	 * 查询基础数据类型列表
	 * @return
	 * @author ycr 2013-11-25
	 * @throws Exception
	 */
	public List queryBaseDataTypes() throws Exception;
	/**
	 * 根据数据代码查询数据库中是否已存在
	 * @return
	 * @author ycr 2013-11-25
	 * @throws Exception
	 */
	public int queryCheckDataCode(String dataCode)throws Exception;
	/**
	 * 根据id查询基础数据类型列表
	 * @return
	 * @author ycr 2013-11-25
	 * @throws Exception
	 */
	public List queryBaseDataTypesById(String id)throws Exception;
	/**
	 * 根据代码查询基础数据名称
	 * @return
	 * @author ycr 2013-12-03
	 * @throws Exception
	 */
	public String queryDataNameByCode(String datacode)throws Exception;
	
	/**
	 * ycr 2013-12-19 获取下一个审核人
	 * @param relateId
	 * @return
	 * @throws Exception
	 */
	public String queryNextPerson( Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-30 获取当前审核人节点信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public  FlowPeople queryFlowPeople(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-30 修改审核人节点信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public void execUpdateFlowPeople(FlowPeople flowPeople)throws Exception;
	
	/**
	 * ycr 2013-12-31 查询审核意见
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List queryFlowPeopleOpinions(Map condition)throws Exception;
}



