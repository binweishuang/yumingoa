package runix.persistent.dao.impl;

import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.PositionDAO;
import runix.persistent.model.BaseUser;
import runix.persistent.model.Position;

public class PositionDAOImpl extends BaseDAO implements PositionDAO {

	public List queryPositions(Map condition, int offset, int limit)
			throws Exception {
		return getSqlMapClientTemplate().queryForList(
				"Position.queryPositions", condition, offset, limit);
	}

	public int queryCountPositions(Map condition) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Position.queryCountPositions", condition);
	}

	public Position queryPositionById(String id) throws Exception {
		return (Position) getSqlMapClientTemplate().queryForObject(
				"Position.queryPositionById", id);
	}

	public void execDeletePositionById(String id) throws Exception {
		getSqlMapClientTemplate().delete("Position.execDeletePositionById", id);
	}

	public void execInsertPosition(Position position) throws Exception {
		getSqlMapClientTemplate().insert("Position.execInsertPosition",
				position);
	}

	public void execUpdatePosition(Position position) throws Exception {
		getSqlMapClientTemplate().update("Position.execUpdatePosition",
				position);
	}
	
	/**
	 * 添加重名验证
	 */

	public List<Position> findByName(String name){   
		return getSqlMapClientTemplate().queryForList("Position.queryPositionByName",name);
	}

	public List queryAllPositions() throws Exception {
		return getSqlMapClientTemplate().queryForList("Position.queryAllPositions");
	}

	/**
	 * 修改重名验证
	 */
	public List<Position> checkEdit(Position position){
		return getSqlMapClientTemplate().queryForList("Position.queryPositionCheckName",position);
	}
	
	/**
	 * 获取所有的职位
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Position> getAllPositions() {
		return getSqlMapClientTemplate().queryForList(
				"Position.getAllPositions");
	}
}

