package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Netdisk;

public interface NetdiskService {

	public List<Netdisk> getNetdisks(Map<String,String> condition,int offset,int limit)throws Exception;

	public int getCountNetdisks(Map<String,String> condition)throws Exception;
	public List<Netdisk> getNetdisks1(Map<String,String> condition,int offset,int limit)throws Exception;

	public int getCountNetdisks1(Map<String,String> condition)throws Exception;

	public Netdisk getNetdiskById(String id)throws Exception;

	public void removeNetdiskById(String id)throws Exception;
	
	public void removeNetdiskByPath(String path)throws Exception;

	public void saveNetdisk(Netdisk netdisk)throws Exception;

	public void modifyNetdisk(Netdisk netdisk)throws Exception;

	public void deleteAllNetdiskByFolderID(String folderID) throws Exception;
	
	public List<Netdisk> findAll(String folderID) throws Exception;
}
