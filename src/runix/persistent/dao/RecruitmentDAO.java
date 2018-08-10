package runix.persistent.dao;
import java.util.List;
import java.util.Map;
import runix.persistent.model.Recruitment;

public interface RecruitmentDAO {

	public List queryRecruitments(Map condition,int offset,int limit)throws Exception;

	public int queryCountRecruitments(Map condition)throws Exception;

	public Recruitment queryRecruitmentById(String id)throws Exception;

	public void execDeleteRecruitmentById(String id)throws Exception;

	public void execInsertRecruitment(Recruitment recruitment)throws Exception;

	public void execUpdateRecruitment(Recruitment recruitment)throws Exception;
	/**
	 * 获取审核通过的招聘信息
	 * @return
	 * @throws Exception
	 */
	public List queryAllRecruitments()throws Exception;
	/**
	 * 查询统计
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public List queryRecruitmentsCount(Map condition,int offset,int limit)throws Exception;
	public int queryCountRecruitmentsCount(Map condition)throws Exception;
	
	/**
	 * ycr 2013-12-30 审核页面根据id查询招聘信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List queryRecruitmentByIdForCheck(String id)throws Exception;

}
