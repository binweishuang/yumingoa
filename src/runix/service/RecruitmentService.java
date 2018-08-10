package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Recruitment;

public interface RecruitmentService {

	public List getRecruitments(Map condition,int offset,int limit)throws Exception;

	public int getCountRecruitments(Map condition)throws Exception;

	public Recruitment getRecruitmentById(String id)throws Exception;

	public void removeRecruitmentById(String id)throws Exception;

	public void saveRecruitment(Recruitment recruitment)throws Exception;

	public void modifyRecruitment(Recruitment recruitment)throws Exception;
	/**
	 * 获取审核通过的招聘信息
	 * @return
	 * @throws Exception
	 */
	public List getAllRecruitments()throws Exception;
	/**
	 * 查询统计
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int  getCountRecruitmentsCount(Map condition)throws Exception;
	public List getRecruitmentsCount(Map condition,int offset,int limit)throws Exception;
	
	/**
	 * ycr 2013-12-30 审核页面根据id查询招聘信息
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public List getRecruitmentByIdForCheck(String id)throws Exception;
	
}
