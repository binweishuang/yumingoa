package runix.persistent.dao;

import java.util.List;
import java.util.Map;

import runix.persistent.model.BaseUser;
import runix.persistent.model.Position;

public interface PositionDAO {

	/**
	 * 添加重名验证
	 * @param name
	 * @return
	 */
	public List<Position> findByName(String name);

	public List queryAllPositions()throws Exception;

	/**
	 * 修改重名验证
	 */
	public List<Position> checkEdit(Position position); 
	
	public List queryPositions(Map condition, int offset, int limit)
			throws Exception;

	public int queryCountPositions(Map condition) throws Exception;

	public Position queryPositionById(String id) throws Exception;

	public void execDeletePositionById(String id) throws Exception;

	public void execInsertPosition(Position position) throws Exception;

	public void execUpdatePosition(Position position) throws Exception;

	/**
	 * 获取所有的职位
	 * 
	 * @author wangfq
	 * @return
	 */
	public List<Position> getAllPositions();
}

