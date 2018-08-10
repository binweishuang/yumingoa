package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.OfficialDocumentDAO;
import runix.service.OfficialDocumentService;
import runix.persistent.model.OfficialDocument;

public class OfficialDocumentServiceImpl  implements OfficialDocumentService {
	private OfficialDocumentDAO officialDocumentDAO;


	public List getOfficialDocuments(Map condition,int offset,int limit)throws Exception {
		return officialDocumentDAO.queryOfficialDocuments(condition,offset,limit);
	}


	public int getCountOfficialDocuments(Map condition)throws Exception {
		return officialDocumentDAO.queryCountOfficialDocuments(condition);
	}


	public OfficialDocument getOfficialDocumentById(String id)throws Exception {
		return officialDocumentDAO.queryOfficialDocumentById(id);
	}


	public void removeOfficialDocumentById(String id)throws Exception {
		 officialDocumentDAO.execDeleteOfficialDocumentById(id);
	}


	public void saveOfficialDocument(OfficialDocument officialDocument)throws Exception {
		 officialDocumentDAO.execInsertOfficialDocument(officialDocument);
	}


	public void modifyOfficialDocument(OfficialDocument officialDocument)throws Exception {
		 officialDocumentDAO.execUpdateOfficialDocument(officialDocument);
	}


	public void setOfficialDocumentDAO(OfficialDocumentDAO officialDocumentDAO){
		this.officialDocumentDAO=officialDocumentDAO;
	}


	public OfficialDocument getOfficialDocumentByIdForView(String id)
			throws Exception {
		return officialDocumentDAO.queryOfficialDocumentByIdForView(id);
	}


	public List getOfficialDocumentByIdForCheck(String id) throws Exception {
		return officialDocumentDAO.queryOfficialDocumentByIdForCheck(id);
	}

}
