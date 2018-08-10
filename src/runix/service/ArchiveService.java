package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Archive;

public interface ArchiveService {

	public List getArchives(Map condition,int offset,int limit)throws Exception;

	public int getCountArchives(Map condition)throws Exception;

	public Archive getArchiveById(String id)throws Exception;

	public void removeArchiveById(String id)throws Exception;

	public void saveArchive(Archive archive)throws Exception;

	public void modifyArchive(Archive archive)throws Exception;
	/**
	 * ycr 2013-12-16 详细页面查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Archive getArchiveByIdForView(String id)throws Exception;


}
