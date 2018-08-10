package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Dossier;

public interface DossierDAO {

	public List queryDossiers(Map condition,int offset,int limit)throws Exception;

	public int queryCountDossiers(Map condition)throws Exception;

	public Dossier queryDossierById(String id)throws Exception;

	public void execDeleteDossierById(String id)throws Exception;

	public void execInsertDossier(Dossier dossier)throws Exception;

	public void execUpdateDossier(Dossier dossier)throws Exception;
	
	public List queryDossierList()throws Exception;
	/**
	 * ycr 2013-12-13 根据案卷id删除档案
	 * @param dossierId
	 * @throws Exception
	 */
	public void execDeleteArchiveByDossierId(String dossierId)throws Exception;
	/**
	 *  ycr 2013-12-13 详细页面查询
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public Dossier queryDossierByIdForView(String id)throws Exception;
}
