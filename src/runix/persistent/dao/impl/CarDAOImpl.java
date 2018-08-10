package runix.persistent.dao.impl;

import java.util.List;
import java.util.Map;

import kdf.persistent.dao.BaseDAO;
import runix.persistent.dao.CarDAO;
import runix.persistent.model.Car;

public class CarDAOImpl extends BaseDAO implements CarDAO {
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
			int offset, int limit) throws Exception {
		return getSqlMapClientTemplate().queryForList("Car.queryCars",
				condition, offset, limit);
	}

	/**
	 * 获取所有符合条件的车辆纪录
	 * 
	 * @author wangfq
	 * @param condition
	 * @return
	 * @throws Exception
	 */
	public int queryCountCars(Map<String, Object> condition) throws Exception {
		return (Integer) getSqlMapClientTemplate().queryForObject(
				"Car.queryCountCars", condition);
	}

	public Car queryCarById(String id) throws Exception {
		return (Car) getSqlMapClientTemplate().queryForObject(
				"Car.queryCarById", id);
	}

	public void execDeleteCarById(String id) throws Exception {
		getSqlMapClientTemplate().delete("Car.execDeleteCarById", id);
	}

	public void execInsertCar(Car car) throws Exception {
		getSqlMapClientTemplate().insert("Car.execInsertCar", car);
	}

	public void execUpdateCar(Car car) throws Exception {
		getSqlMapClientTemplate().update("Car.execUpdateCar", car);
	}

	public List queryAllCars() throws Exception {
		return getSqlMapClientTemplate().queryForList("Car.queryAllCars");
	}

	public String queryDriverByCarId(String id) throws Exception {
		return (String) getSqlMapClientTemplate().queryForObject(
				"Car.queryDriverByCarId", id);
	}

}
