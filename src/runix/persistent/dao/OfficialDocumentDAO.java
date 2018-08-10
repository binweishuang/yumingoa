package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.OfficialDocument;

public interface OfficialDocumentDAO {

	public List queryOfficialDocuments(Map condition,int offset,int limit)throws Exception;

	public int queryCountOfficialDocuments(Map condition)throws Exception;

	public OfficialDocument queryOfficialDocumentById(String id)throws Exception;

	public void execDeleteOfficialDocumentById(String id)throws Exception;

	public void execInsertOfficialDocument(OfficialDocument officialDocument)throws Exception;

	public void execUpdateOfficialDocument(OfficialDocument officialDocument)throws Exception;

	public OfficialDocument queryOfficialDocumentByIdForView(String id)throws Exception;
	
	/**
	 * ycr 2013-12-30 审核页面根据id查询公文
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryOfficialDocumentByIdForCheck(String id)throws Exception;
}
