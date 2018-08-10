package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.OfficialDocument;

public interface OfficialDocumentService {

	public List getOfficialDocuments(Map condition,int offset,int limit)throws Exception;

	public int getCountOfficialDocuments(Map condition)throws Exception;

	public OfficialDocument getOfficialDocumentById(String id)throws Exception;

	public void removeOfficialDocumentById(String id)throws Exception;

	public void saveOfficialDocument(OfficialDocument officialDocument)throws Exception;

	public void modifyOfficialDocument(OfficialDocument officialDocument)throws Exception;

	public OfficialDocument getOfficialDocumentByIdForView(String id)throws Exception;
	
	/**
	 * ycr 2013-12-30 审核页面根据id查询公文
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getOfficialDocumentByIdForCheck(String id)throws Exception;
}
