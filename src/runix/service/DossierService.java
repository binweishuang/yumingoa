package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Dossier;

public interface DossierService {

	public List getDossiers(Map condition,int offset,int limit)throws Exception;

	public int getCountDossiers(Map condition)throws Exception;

	public Dossier getDossierById(String id)throws Exception;

	public void removeDossierById(String id)throws Exception;

	public void saveDossier(Dossier dossier)throws Exception;

	public void modifyDossier(Dossier dossier)throws Exception;
	
	public List getDossierList()throws Exception;
	/**
	 * ycr 2013-12-13 根据案卷id删除档案
	 * @param dossierId
	 * @throws Exception
	 */
	public void removeArchiveByDossierId(String dossierId)throws Exception;
	/**
	 * ycr 2013-12-13 详细页面查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Dossier getDossierByIdForView(String id)throws Exception;


}
