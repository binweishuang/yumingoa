package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Folder;

public interface FolderService {

	public List getFolders(Map condition,int offset,int limit)throws Exception;

	public int getCountFolders(Map condition)throws Exception;

	public Folder getFolderById(String id)throws Exception;

	public void removeFolderById(String id)throws Exception;

	public void saveFolder(Folder folder)throws Exception;

	public void modifyFolder(Folder folder)throws Exception;
	
	public Folder checkID(String id) throws Exception;
}
