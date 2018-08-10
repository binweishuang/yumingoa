package runix.persistent.model;

import java.util.Map;

import java.util.Date;
import kdf.persistent.model.BaseModel;

public class BaseUser implements BaseModel {
	private String userId;
	private String username;
	private String password;
	private String name;

	@Override
	public String toString() {
		return "BaseUser [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", name=" + name + ", sex=" + sex
				+ ", org=" + org + ", deptId=" + deptId + ", positionId="
				+ positionId + ", state=" + state + ", birthdate=" + birthdate
				+ ", age=" + age + ", email=" + email + ", phone=" + phone
				+ ", address=" + address + ", flag=" + flag + "]";
	}

	private String sex;
	private String org;
	private String deptId;
	private String positionId;
	private String state;
	private Date birthdate;
	private String age;
	private String email;
	private String phone;
	private String address;
	private String flag;
	private String rolerank;//权限级别
	private String title;//用户权限

	public Map toMap() {
		return null;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getSex() {
		return sex;
	}

	public void setOrg(String org) {
		this.org = org;
	}

	public String getOrg() {
		return org;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getState() {
		return state;
	}

	public void setBirthdate(Date birthdate) {
		this.birthdate = birthdate;
	}

	public Date getBirthdate() {
		return birthdate;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getAge() {
		return age;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone() {
		return phone;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getAddress() {
		return address;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFlag() {
		return flag;
	}

	public String getRolerank() {
		return rolerank;
	}

	public void setRolerank(String rolerank) {
		this.rolerank = rolerank;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
