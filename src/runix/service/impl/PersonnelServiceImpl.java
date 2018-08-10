package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.PersonnelDAO;
import runix.service.PersonnelService;
import runix.persistent.model.Personnel;

public class PersonnelServiceImpl  implements PersonnelService {
	private PersonnelDAO personnelDAO;


	public List getPersonnels(Map condition,int offset,int limit)throws Exception {
		return personnelDAO.queryPersonnels(condition,offset,limit);
	}


	public int getCountPersonnels(Map condition)throws Exception {
		return personnelDAO.queryCountPersonnels(condition);
	}


	public Personnel getPersonnelById(String id)throws Exception {
		return personnelDAO.queryPersonnelById(id);
	}


	public void removePersonnelById(String id)throws Exception {
		 personnelDAO.execDeletePersonnelById(id);
	}


	public void savePersonnel(Personnel personnel)throws Exception {
		 personnelDAO.execInsertPersonnel(personnel);
	}


	public void modifyPersonnel(Personnel personnel)throws Exception {
		 personnelDAO.execUpdatePersonnel(personnel);
	}


	public void setPersonnelDAO(PersonnelDAO personnelDAO){
		this.personnelDAO=personnelDAO;
	}


	public Personnel getPersonnelByIdForView(String id) throws Exception {
		return  personnelDAO.queryPersonnelByIdForView(id);
	}


	public List getPersonnelByIdForCheck(String id) throws Exception {
		return personnelDAO.queryPersonnelByIdForCheck(id);
	}

}
