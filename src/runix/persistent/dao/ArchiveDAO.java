package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Archive;

public interface ArchiveDAO {

	public List queryArchives(Map condition,int offset,int limit)throws Exception;

	public int queryCountArchives(Map condition)throws Exception;

	public Archive queryArchiveById(String id)throws Exception;

	public void execDeleteArchiveById(String id)throws Exception;

	public void execInsertArchive(Archive archive)throws Exception;

	public void execUpdateArchive(Archive archive)throws Exception;
	
	/**
	 * ycr 2013-12-16 详细页面查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Archive queryArchiveByIdForView(String id)throws Exception;

}
