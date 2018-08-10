package runix.service.impl;

import java.util.List;
import java.util.Map;


import runix.persistent.dao.EmailDAO;
import runix.service.EmailService;
import runix.persistent.model.Email;

public class EmailServiceImpl  implements EmailService {
	private EmailDAO emailDAO;


	public List getEmails(Map condition,int offset,int limit)throws Exception {
		return emailDAO.queryEmails(condition,offset,limit);
	}


	public int getCountEmails(Map condition)throws Exception {
		return emailDAO.queryCountEmails(condition);
	}


	public Email getEmailById(String id)throws Exception {
		return emailDAO.queryEmailById(id);
	}


	public void removeEmailById(String id)throws Exception {
		 emailDAO.execDeleteEmailById(id);
	}


	public void saveEmail(Email email)throws Exception {
		 emailDAO.execInsertEmail(email);
	}


	public void modifyEmail(Email email)throws Exception {
		 emailDAO.execUpdateEmail(email);
	}


	public void setEmailDAO(EmailDAO emailDAO){
		this.emailDAO=emailDAO;
	}
	
	public Email getEmailById2(String id)throws Exception {
		return emailDAO.queryEmailById2(id);
	}

}
