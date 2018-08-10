package kdf.tools.easyCode.config.struts.beanTableMsg;

import java.util.List;

public class BeanTableMsg {
	/**
	 * 表信息
	 */
	private String tableName;
	private List fieldList;
	private List fieldTypeList;
	/**
	 * bean类信息
	 */
	private String beanType;
	private String beanName;
	private List propertyList;
	private List propertyTypeList;
	
	//标明查询条件是配置的还是 自动的
	private String isConfig="false";
	private List configCondition;
	

	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public List getFieldList() {
		return fieldList;
	}
	public void setFieldList(List fieldList) {
		this.fieldList = fieldList;
	}
	public List getFieldTypeList() {
		return fieldTypeList;
	}
	public void setFieldTypeList(List fieldTypeList) {
		this.fieldTypeList = fieldTypeList;
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public List getPropertyList() {
		return propertyList;
	}
	public void setPropertyList(List propertyList) {
		this.propertyList = propertyList;
	}
	public List getPropertyTypeList() {
		return propertyTypeList;
	}
	public void setPropertyTypeList(List propertyTypeList) {
		this.propertyTypeList = propertyTypeList;
	}
	public String getBeanType() {
		return beanType;
	}
	public void setBeanType(String beanType) {
		this.beanType = beanType;
	}
	public String getIsConfig() {
		return isConfig;
	}
	public void setIsConfig(String isConfig) {
		this.isConfig = isConfig;
	}
	public List getConfigCondition() {
		return configCondition;
	}
	public void setConfigCondition(List configCondition) {
		this.configCondition = configCondition;
	}

}
