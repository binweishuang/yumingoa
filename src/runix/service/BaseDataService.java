package runix.service;

import java.util.List;
import java.util.Map;

import runix.persistent.model.BaseData;
import runix.persistent.model.FlowPeople;

public interface BaseDataService {

	public List getBaseDatas(Map condition,int offset,int limit)throws Exception;

	public int getCountBaseDatas(Map condition)throws Exception;

	public BaseData getBaseDataById(String id)throws Exception;

	public void removeBaseDataById(String id)throws Exception;

	public void saveBaseData(BaseData baseData)throws Exception;

	public void modifyBaseData(BaseData baseData)throws Exception;

	/**
	 * 查询序列的值
	 * @return
	 * @author ycr 2013-11-25
	 * @throws Exception
	 */
	public String getSequence() throws Exception;
	
	/**
	 * 根据数据类型找到对应的列表
	 * @author SY
	 * @time 2013-11-25 17:33
	 * @param datatype
	 * @return
	 * @throws Exception
	 */
	public List getqBaseByDatatype(String datatype)throws Exception;

	/**
	 * 查询基础数据类型列表
	 * @return
	 * @author ycr 2013-11-25
	 * @throws Exception
	 */
	public List getBaseDataTypes() throws Exception;
	/**
	 * 根据数据代码查询数据库中是否已存在
	 * @return
	 * @author ycr 2013-11-25
	 * @throws Exception
	 */
	public int checkDataCode(String dataCode)throws Exception;
	/**
	 * 根据id查询基础数据类型列表
	 * @return
	 * @author ycr 2013-11-25
	 * @throws Exception
	 */
	public List getBaseDataTypesById( String id)throws Exception;
	/**
	 * 根据代码查询基础数据名称
	 * @return
	 * @author ycr 2013-12-03
	 * @throws Exception
	 */
	public String getDataNameByCode(String datacode)throws Exception;
	
	/**
	 * ycr 2013-12-19 获取下一个审核人
	 * @param relateId
	 * @return
	 * @throws Exception
	 */
	public String getNextPerson(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-30 获取当前审核人节点信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public FlowPeople getFlowPeople(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-30 修改审核人节点信息
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public void modifyFlowPeople(FlowPeople flowPeople)throws Exception;
	
	/**
	 * ycr 2013-12-31 查询审核意见
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List getFlowPeopleOpinions(Map condition)throws Exception;

}
