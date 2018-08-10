package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.FolderDAO;
import runix.service.FolderService;
import runix.persistent.model.Folder;

public class FolderServiceImpl  implements FolderService {
	private FolderDAO folderDAO;


	public List getFolders(Map condition,int offset,int limit)throws Exception {
		return folderDAO.queryFolders(condition,offset,limit);
	}


	public Folder checkID(String id) throws Exception {
		Folder folder =folderDAO.checkID(id);
		return folder;
	}
	
	public int getCountFolders(Map condition)throws Exception {
		return folderDAO.queryCountFolders(condition);
	}


	public Folder getFolderById(String id)throws Exception {
		return folderDAO.queryFolderById(id);
	}


	public void removeFolderById(String id)throws Exception {
		 folderDAO.execDeleteFolderById(id);
	}


	public void saveFolder(Folder folder)throws Exception {
		 folderDAO.execInsertFolder(folder);
	}


	public void modifyFolder(Folder folder)throws Exception {
		 folderDAO.execUpdateFolder(folder);
	}


	public void setFolderDAO(FolderDAO folderDAO){
		this.folderDAO=folderDAO;
	}



}
