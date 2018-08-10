package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.BaseDataDAO;
import runix.service.BaseDataService;
import runix.persistent.model.BaseData;
import runix.persistent.model.FlowPeople;

public class BaseDataServiceImpl  implements BaseDataService {
	private BaseDataDAO baseDataDAO;

	/**
	 * 根据数据类型找到对应的列表
	 * @author SY
	 * @time 2013-11-25 17:33
	 * @param datatype
	 * @return
	 * @throws Exception
	 */
	public List getqBaseByDatatype(String datatype)throws Exception{
		return baseDataDAO.queryBaseByDatatype(datatype);
	}
	
	public List getBaseDatas(Map condition,int offset,int limit)throws Exception {
		return baseDataDAO.queryBaseDatas(condition,offset,limit);
	}


	public int getCountBaseDatas(Map condition)throws Exception {
		return baseDataDAO.queryCountBaseDatas(condition);
	}


	public BaseData getBaseDataById(String id)throws Exception {
		return baseDataDAO.queryBaseDataById(id);
	}


	public void removeBaseDataById(String id)throws Exception {
		 baseDataDAO.execDeleteBaseDataById(id);
	}


	public void saveBaseData(BaseData baseData)throws Exception {
		 baseDataDAO.execInsertBaseData(baseData);
	}


	public void modifyBaseData(BaseData baseData)throws Exception {
		 baseDataDAO.execUpdateBaseData(baseData);
	}


	public void setBaseDataDAO(BaseDataDAO baseDataDAO){
		this.baseDataDAO=baseDataDAO;
	}


	public String getSequence() throws Exception {
		return baseDataDAO.querySequence();
	}


	public List getBaseDataTypes() throws Exception {
		return baseDataDAO.queryBaseDataTypes();
	}


	public int checkDataCode(String dataCode) throws Exception {
		return baseDataDAO.queryCheckDataCode(dataCode);
	}


	public List getBaseDataTypesById(String id) throws Exception {
		return baseDataDAO.queryBaseDataTypesById(id);
	}

	public String getDataNameByCode(String datacode) throws Exception {
		return baseDataDAO.queryDataNameByCode(datacode);
	}

	public String getNextPerson(Map condition) throws Exception {
		return baseDataDAO.queryNextPerson(condition);
	}

	public FlowPeople getFlowPeople(Map condition) throws Exception {
		return baseDataDAO.queryFlowPeople(condition);
	}

	public void modifyFlowPeople(FlowPeople flowPeople) throws Exception {
		 baseDataDAO.execUpdateFlowPeople(flowPeople);
	}

	public List getFlowPeopleOpinions(Map condition) throws Exception {
		return baseDataDAO.queryFlowPeopleOpinions(condition);
	}

}
