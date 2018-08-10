package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Usingcar;

public interface UsingcarDAO {

	public List queryUsingcars(Map condition,int offset,int limit)throws Exception;

	public int queryCountUsingcars(Map condition)throws Exception;

	public Usingcar queryUsingcarById(String id)throws Exception;

	public void execDeleteUsingcarById(String id)throws Exception;

	public void execInsertUsingcar(Usingcar usingcar)throws Exception;

	public void execUpdateUsingcar(Usingcar usingcar)throws Exception;

	/**
	 * ycr 2013-12-23 查看页面根据id查询用车申请
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryUsingcarByIdForView(String id)throws Exception;

}
