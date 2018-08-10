package runix.service;

import java.util.List;
import java.util.Map;
import runix.persistent.model.Car;

public interface CarService {
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
	public List<Map<String, String>> getCars(Map<String, Object> condition,
			int offset, int limit) throws Exception;

	/**
	 * 获取所有符合条件的车辆纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountCars(Map<String, Object> condition) throws Exception;

	public Car getCarById(String id) throws Exception;

	public void removeCarById(String id) throws Exception;

	public void saveCar(Car car) throws Exception;

	public void modifyCar(Car car) throws Exception;
	
	public List getAllCars()throws Exception;
	
	/**
	 * ycr 2013-12-20 根据id得到司机
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public String getDriverByCarId(String id)throws Exception;

}
