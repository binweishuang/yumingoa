package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.DossierDAO;
import runix.service.DossierService;
import runix.persistent.model.Dossier;

public class DossierServiceImpl  implements DossierService {
	private DossierDAO dossierDAO;


	public List getDossiers(Map condition,int offset,int limit)throws Exception {
		return dossierDAO.queryDossiers(condition,offset,limit);
	}


	public int getCountDossiers(Map condition)throws Exception {
		return dossierDAO.queryCountDossiers(condition);
	}


	public Dossier getDossierById(String id)throws Exception {
		return dossierDAO.queryDossierById(id);
	}


	public void removeDossierById(String id)throws Exception {
		 dossierDAO.execDeleteDossierById(id);
	}


	public void saveDossier(Dossier dossier)throws Exception {
		 dossierDAO.execInsertDossier(dossier);
	}


	public void modifyDossier(Dossier dossier)throws Exception {
		 dossierDAO.execUpdateDossier(dossier);
	}


	public void setDossierDAO(DossierDAO dossierDAO){
		this.dossierDAO=dossierDAO;
	}


	public List getDossierList() throws Exception {
		return dossierDAO.queryDossierList();
	}


	public void removeArchiveByDossierId(String dossierId) throws Exception {
		dossierDAO.execDeleteArchiveByDossierId(dossierId);
	}


	public Dossier getDossierByIdForView(String id) throws Exception {
		return dossierDAO.queryDossierByIdForView(id);
	}

}
