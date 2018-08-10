package runix.service.impl;

import java.util.List;
import java.util.Map;

import runix.persistent.dao.PositionDAO;
import runix.service.PositionService;
import runix.persistent.model.Position;

public class PositionServiceImpl implements PositionService {
	private PositionDAO positionDAO;

	public List getPositions(Map condition,int offset,int limit)throws Exception {
		return positionDAO.queryPositions(condition,offset,limit);
	}

	public int getCountPositions(Map condition)throws Exception {
		return positionDAO.queryCountPositions(condition);
	}

	public Position getPositionById(String id)throws Exception {
		return positionDAO.queryPositionById(id);
	}

	public void removePositionById(String id)throws Exception {
		 positionDAO.execDeletePositionById(id);
	}

	public void savePosition(Position position)throws Exception {
		 positionDAO.execInsertPosition(position);
	}

	public void modifyPosition(Position position)throws Exception {
		 positionDAO.execUpdatePosition(position);
	}
	
	/**
	 * 添加验证
	 * @author luqj
	 */
	public List<Position> findByName(String name){     
		return positionDAO.findByName(name);  
	}

	/**
	 * 修改验证
	 * @author luqj
	 */
	public List<Position> checkEdit(Position position){
		return positionDAO.checkEdit(position);
	}
	
	/**
	 * 权限查询所有的职位
	 * @author luqj
	 */
	public List<Position> findAllPosition(){
		return positionDAO.getAllPositions();
	}
	
	public void setPositionDAO(PositionDAO positionDAO){
		this.positionDAO=positionDAO;
	}

	public List getALlPositions() throws Exception {
		return positionDAO.queryAllPositions();
	}

}
