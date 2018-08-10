package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.SetAttend;

public interface SetAttendDAO {

	public List querySetAttends(Map condition,int offset,int limit)throws Exception;

	public int queryCountSetAttends(Map condition)throws Exception;

	public SetAttend querySetAttendById(String id)throws Exception;

	public void execDeleteSetAttendById(String id)throws Exception;

	public void execInsertSetAttend(SetAttend setAttend)throws Exception;

	public void execUpdateSetAttend(SetAttend setAttend)throws Exception;

	public SetAttend querySetAttend()throws Exception;
}
