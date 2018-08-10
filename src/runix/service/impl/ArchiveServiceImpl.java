package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.ArchiveDAO;
import runix.service.ArchiveService;
import runix.persistent.model.Archive;

public class ArchiveServiceImpl  implements ArchiveService {
	private ArchiveDAO archiveDAO;


	public List getArchives(Map condition,int offset,int limit)throws Exception {
		return archiveDAO.queryArchives(condition,offset,limit);
	}


	public int getCountArchives(Map condition)throws Exception {
		return archiveDAO.queryCountArchives(condition);
	}


	public Archive getArchiveById(String id)throws Exception {
		return archiveDAO.queryArchiveById(id);
	}


	public void removeArchiveById(String id)throws Exception {
		 archiveDAO.execDeleteArchiveById(id);
	}


	public void saveArchive(Archive archive)throws Exception {
		 archiveDAO.execInsertArchive(archive);
	}


	public void modifyArchive(Archive archive)throws Exception {
		 archiveDAO.execUpdateArchive(archive);
	}


	public void setArchiveDAO(ArchiveDAO archiveDAO){
		this.archiveDAO=archiveDAO;
	}


	public Archive getArchiveByIdForView(String id) throws Exception {
		return archiveDAO.queryArchiveByIdForView(id);
	}

}
