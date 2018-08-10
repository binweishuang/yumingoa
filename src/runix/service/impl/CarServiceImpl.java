package runix.service.impl;

import java.util.List;
import java.util.Map;

import runix.persistent.dao.CarDAO;
import runix.service.CarService;
import runix.persistent.model.Car;

public class CarServiceImpl implements CarService {
	private CarDAO carDAO;

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
			int offset, int limit) throws Exception {
		return carDAO.queryCars(condition, offset, limit);
	}

	/**
	 * 获取所有符合条件的车辆纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int getCountCars(Map<String, Object> condition) throws Exception {
		return carDAO.queryCountCars(condition);
	}

	public Car getCarById(String id) throws Exception {
		return carDAO.queryCarById(id);
	}

	public void removeCarById(String id) throws Exception {
		carDAO.execDeleteCarById(id);
	}

	public void saveCar(Car car) throws Exception {
		carDAO.execInsertCar(car);
	}

	public void modifyCar(Car car) throws Exception {
		carDAO.execUpdateCar(car);
	}

	public void setCarDAO(CarDAO carDAO) {
		this.carDAO = carDAO;
	}

	public List getAllCars() throws Exception {
		return carDAO.queryAllCars();
	}

	public String getDriverByCarId(String id) throws Exception {
		return carDAO.queryDriverByCarId(id);
	}

}
