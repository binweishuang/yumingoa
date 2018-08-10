package runix.persistent.dao;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Car;

public interface CarDAO {
	/**
	 * 获取所有符合条件的车辆
	 * 
	 * @author wangfq
	 * @param condition
	 * @param offset
	 * @param limit
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> queryCars(Map<String, Object> condition,
			int offset, int limit) throws Exception;

	/**
	 * 获取所有符合条件的车辆纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountCars(Map<String, Object> condition) throws Exception;

	public Car queryCarById(String id) throws Exception;

	public void execDeleteCarById(String id) throws Exception;

	public void execInsertCar(Car car) throws Exception;

	public void execUpdateCar(Car car) throws Exception;
	
	public List queryAllCars()throws Exception;
	
	/**
	 * ycr 2013-12-20 根据id得到司机
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String queryDriverByCarId(String id)throws Exception;

}
