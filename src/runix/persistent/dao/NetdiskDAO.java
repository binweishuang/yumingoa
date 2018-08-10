package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Netdisk;

public interface NetdiskDAO {

	public List<Netdisk> queryNetdisks(Map<String,String> condition,int offset,int limit)throws Exception;

	public int queryCountNetdisks(Map<String,String> condition)throws Exception;
	
	public List<Netdisk> queryNetdisks1(Map<String,String> condition,int offset,int limit)throws Exception;

	public int queryCountNetdisks1(Map<String,String> condition)throws Exception;

	public Netdisk queryNetdiskById(String id)throws Exception;

	public void execDeleteNetdiskById(String id)throws Exception;
	
	public void execDeleteNetdiskByPath(String path)throws Exception;
	
	public void execInsertNetdisk(Netdisk netdisk)throws Exception;

	public void execUpdateNetdisk(Netdisk netdisk)throws Exception;

	public void execDeleteAllNetdisk(String id) throws Exception;
	
	//更具文件夹ID查询该文件夹下面的所有文件
	public List<Netdisk> findAll (String folderID) throws Exception;
}
