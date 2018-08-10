package runix.persistent.dao.impl;
import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.DossierDAO;
import runix.persistent.model.Dossier;

public class DossierDAOImpl extends BaseDAO implements DossierDAO {

	public List queryDossiers(Map condition,int offset,int limit)throws Exception {
		return getSqlMapClientTemplate().queryForList("Dossier.queryDossiers",condition,offset,limit);
	}


	public int queryCountDossiers(Map condition)throws Exception {
		return (Integer)getSqlMapClientTemplate().queryForObject("Dossier.queryCountDossiers",condition);
	}


	public Dossier queryDossierById(String id)throws Exception {
		return (Dossier)getSqlMapClientTemplate().queryForObject("Dossier.queryDossierById",id);
	}


	public void execDeleteDossierById(String id)throws Exception {
		getSqlMapClientTemplate().delete("Dossier.execDeleteDossierById",id);
	}


	public void execInsertDossier(Dossier dossier)throws Exception {
		getSqlMapClientTemplate().insert("Dossier.execInsertDossier",dossier);
	}


	public void execUpdateDossier(Dossier dossier)throws Exception {
		getSqlMapClientTemplate().update("Dossier.execUpdateDossier",dossier);
	}


	public List queryDossierList() throws Exception {
		return getSqlMapClientTemplate().queryForList("Dossier.queryDossierList");
	}


	public void execDeleteArchiveByDossierId(String dossierId) throws Exception {
		getSqlMapClientTemplate().delete("Dossier.execDeleteArchiveByDossierId",dossierId);
	}


	public Dossier queryDossierByIdForView(String id) throws Exception {
		return (Dossier)getSqlMapClientTemplate().queryForObject("Dossier.queryDossierByIdForView",id);
	}


}
