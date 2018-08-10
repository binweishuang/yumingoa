package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Folder;

public interface FolderDAO {

	public List queryFolders(Map condition,int offset,int limit)throws Exception;

	public int queryCountFolders(Map condition)throws Exception;

	public Folder queryFolderById(String id)throws Exception;

	public void execDeleteFolderById(String id)throws Exception;

	public void execInsertFolder(Folder folder)throws Exception;

	public void execUpdateFolder(Folder folder)throws Exception;

	public Folder checkID(String id) throws Exception;
}
