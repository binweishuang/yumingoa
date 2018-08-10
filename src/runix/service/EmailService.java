package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Email;

public interface EmailService {

	public List getEmails(Map condition,int offset,int limit)throws Exception;

	public int getCountEmails(Map condition)throws Exception;

	public Email getEmailById(String id)throws Exception;

	public void removeEmailById(String id)throws Exception;

	public void saveEmail(Email email)throws Exception;

	public void modifyEmail(Email email)throws Exception;

	public Email getEmailById2(String id)throws Exception;
}
