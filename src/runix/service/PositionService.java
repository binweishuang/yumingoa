package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Position;

public interface PositionService {

	public List getPositions(Map condition,int offset,int limit)throws Exception;

	public int getCountPositions(Map condition)throws Exception;

	public Position getPositionById(String id)throws Exception;

	public void removePositionById(String id)throws Exception;

	public void savePosition(Position position)throws Exception;

	public void modifyPosition(Position position)throws Exception;

	/**
	 * 添加重名验证
	 * @param name
	 * @return
	 */
	public List<Position> findByName(String name);

	/**
	 * ycr 2013-11-27
	 * 获取所有职位
	 * @return
	 * @throws Exception
	 */
	public List getALlPositions()throws Exception;
	
	/**
	 * 修改重名验证
	 * @param position
	 * @return
	 */
	public List<Position> checkEdit(Position position);
	
	/**
	 * 权限查询所有的职位
	 * @return
	 */
	public List<Position> findAllPosition();

}
