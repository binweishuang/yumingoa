package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Email;

public interface EmailDAO {

	public List queryEmails(Map condition,int offset,int limit)throws Exception;

	public int queryCountEmails(Map condition)throws Exception;

	public Email queryEmailById(String id)throws Exception;

	public void execDeleteEmailById(String id)throws Exception;

	public void execInsertEmail(Email email)throws Exception;

	public void execUpdateEmail(Email email)throws Exception;
	
	public Email queryEmailById2(String id)throws Exception;

}
