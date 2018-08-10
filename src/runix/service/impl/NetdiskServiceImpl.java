package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.NetdiskDAO;
import runix.service.NetdiskService;
import runix.persistent.model.Netdisk;

/**
 * 网络会议service实现类
 */
public class NetdiskServiceImpl  implements NetdiskService {
	private NetdiskDAO netdiskDAO;

	/**
	 * 按条件查询网络会议列表
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @param offset 当前页
	 * @param limit 查询数量
	 * @return
	 * @throws Exception
	 */
	public List getNetdisks(Map condition,int offset,int limit)throws Exception {
		return netdiskDAO.queryNetdisks(condition,offset,limit);
	}
	public List getNetdisks1(Map condition,int offset,int limit)throws Exception {
		return netdiskDAO.queryNetdisks1(condition,offset,limit);
	}

	/**
	 * 按条件查询网络会议列数量
	 * @author SY
	 * @time 
	 * @param condition 查询条件
	 * @throws Exception
	 */
	public int getCountNetdisks(Map condition)throws Exception {
		return netdiskDAO.queryCountNetdisks(condition);
	}
	public int getCountNetdisks1(Map condition)throws Exception {
		return netdiskDAO.queryCountNetdisks1(condition);
	}
	/**
	 * 根据文件夹ID删除此文件夹下的所有文件
	 * @author 孟理
	 */
	public void deleteAllNetdiskByFolderID(String folderID) throws Exception {
		netdiskDAO.execDeleteAllNetdisk(folderID);
	}

	/**
	 * 根据id查询网络会议
	 * @author SY
	 * @time 
	 * @return 返回实体对象
	 * @throws Exception
	 */
	public Netdisk getNetdiskById(String id)throws Exception {
		return netdiskDAO.queryNetdiskById(id);
	}

	/**
	 * 删除网络文件
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void removeNetdiskById(String id)throws Exception {
		 netdiskDAO.execDeleteNetdiskById(id);
	}
	
	public void removeNetdiskByPath(String path)throws Exception {
		 netdiskDAO.execDeleteNetdiskByPath(path);
	}
	
	public void deleteAll(String folderID)throws Exception {
		 netdiskDAO.execDeleteAllNetdisk(folderID);
	}
	
	/**
	 *  根据文件夹ID查询出所有文件，以便删除
	 *  @author 孟理
	 */
	public List<Netdisk> findAll(String folderID) throws Exception {
		return netdiskDAO.findAll(folderID);
	}


	/**
	 * 添加网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void saveNetdisk(Netdisk netdisk)throws Exception {
		 netdiskDAO.execInsertNetdisk(netdisk);
	}

	/**
	 * 修改网络会议
	 * @author SY
	 * @time 
	 * @throws Exception
	 */
	public void modifyNetdisk(Netdisk netdisk)throws Exception {
		 netdiskDAO.execUpdateNetdisk(netdisk);
	}


	public void setNetdiskDAO(NetdiskDAO netdiskDAO){
		this.netdiskDAO=netdiskDAO;
	}

}
