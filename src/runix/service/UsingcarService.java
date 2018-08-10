package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Usingcar;

public interface UsingcarService {

	public List getUsingcars(Map condition,int offset,int limit)throws Exception;

	public int getCountUsingcars(Map condition)throws Exception;

	public Usingcar getUsingcarById(String id)throws Exception;

	public void removeUsingcarById(String id)throws Exception;

	public void saveUsingcar(Usingcar usingcar)throws Exception;

	public void modifyUsingcar(Usingcar usingcar)throws Exception;

	/**
	 * ycr 2013-12-23 查看页面根据id查询用车申请
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getUsingcarByIdForView(String id) throws Exception;
	

}
