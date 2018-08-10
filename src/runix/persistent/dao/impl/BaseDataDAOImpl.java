package runix.persistent.dao.impl;

import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.BaseDataDAO;
import runix.persistent.model.BaseData;
import runix.persistent.model.FlowPeople;
import runix.persistent.model.MessageCenter;

public class BaseDataDAOImpl extends BaseDAO implements BaseDataDAO {

	/**
	 * 根据数据类型找到对应的列表
	 * 
	 * @author SY
	 * @time 2013-11-26 9:54
	 * @param datatype
	 * @return
	 * @throws Exception
	 */
	public List queryBaseByDatatype(String datatype) throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"BaseData.queryBaseByDatatype", datatype);
	}

	public List queryBaseDatas(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"BaseData.queryBaseDatas", condition, offset, limit);
	}

	public int queryCountBaseDatas(Map condition) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BaseData.queryCountBaseDatas", condition);
	}

	public BaseData queryBaseDataById(String id) throws Exception {
		return (BaseData) getSqlMapClientTemplate().queryForObject(
				"BaseData.queryBaseDataById", id);
	}

	public void execDeleteBaseDataById(String id) throws Exception {
		getSqlMapClientTemplate().delete("BaseData.execDeleteBaseDataById", id);
	}

	public void execInsertBaseData(BaseData baseData) throws Exception {
		getSqlMapClientTemplate().insert("BaseData.execInsertBaseData",
				baseData);
	}

	public void execUpdateBaseData(BaseData baseData) throws Exception {
		getSqlMapClientTemplate().update("BaseData.execUpdateBaseData",
				baseData);
	}

	public String querySequence() throws Exception {
		return getSqlMapClientTemplate().queryForObject(
				"BaseData.querySequence").toString();
	}

	public List queryBaseDataTypes() throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"BaseData.queryBaseDataTypes");
	}

	public int queryCheckDataCode(String dataCode) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"BaseData.queryCheckDataCode", dataCode);
	}

	public List queryBaseDataTypesById(String id) throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"BaseData.queryBaseDataTypesById", id);
	}


	public String queryDataNameByCode(String datacode) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("BaseData.queryDataNameByCode",datacode);
	}

	public String queryNextPerson(Map condition) throws Exception {
		return (String)getSqlMapClientTemplate().queryForObject("BaseData.queryNextPerson",condition);
	}

	public FlowPeople queryFlowPeople(Map condition) throws Exception {
		return (FlowPeople) getSqlMapClientTemplate().queryForObject(
				"BaseData.queryFlowPeople", condition);
	}

	public void execUpdateFlowPeople(FlowPeople flowPeople) throws Exception {
		getSqlMapClientTemplate().update("BaseData.execUpdateFlowPeople",
				flowPeople);
	}

	public List queryFlowPeopleOpinions(Map condition) throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"BaseData.queryFlowPeopleOpinions", condition);
	}


}
