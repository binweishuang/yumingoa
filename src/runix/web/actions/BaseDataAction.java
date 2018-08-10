package runix.web.actions;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kdf.constant.SystemConfig;
import kdf.tools.IbatisPage;
import kdf.tools.PageException;
import kdf.tools.Pageable;
import kdf.web.action.BaseAction;

import org.apache.struts2.ServletActionContext;

import runix.persistent.model.BaseData;
import runix.service.BaseDataService;

public class BaseDataAction extends BaseAction{

	private boolean readonly;
	private String currentPage;
	private int pages=15;
	private List baseDataList;
	private String dataId_q;
	private String dataType_q;//查询条件
	private String dataName_q;//查询条件

	private String dataId;
	private String datatype;
	private String dataname;
	private String datacode;
	private String sortnum;
	private String remark;
	private String flag;
	private List dataTypes;//所有数据类型
	private String fileName;//备份数据库的文件名
	private String path;//备份数据库的路径
	private String messageStr;//返回页面信息




	private BaseDataService baseDataService;
	
	public String doQueryData(){
		
		return SUCCESS;
	}

	public String doQuery(){
		try {
			int currentPageInt = 1;
			String strCurrentPage = currentPage;
			if (strCurrentPage != null && !"".equals(strCurrentPage)) {
				try {
					currentPageInt = Integer.parseInt(strCurrentPage);
				} catch (NumberFormatException e) {
					e.printStackTrace();
					this.addActionError("please check! there is Exception");
				}
			}
			int offset = (currentPageInt-1) * pages;
			int limit = pages;

			Map condition = new HashMap();
			if (!"".equals(dataType_q) && null != dataType_q) {
				condition.put("datatype", dataType_q);
			}
			if (!"".equals(dataName_q) && null != dataName_q) {
				condition.put("dataname", "%"+dataName_q+"%");
			}
			
			
			int	 record = baseDataService.getCountBaseDatas(condition);
			List lst =baseDataService.getBaseDatas(condition, offset, limit);
			dataTypes = baseDataService.getBaseDataTypes();//获取所有数据类型列表
			Pageable pg = null;
			try {
				pg = new IbatisPage(lst, record, currentPageInt, pages);
			} catch (PageException e) {
				pg = null;
			}
			ServletActionContext.getRequest().setAttribute("pages", pg);
			baseDataList = pg.getResult();
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdateInit(){
		try {
			BaseData baseData =baseDataService.getBaseDataById(dataId_q);
			dataId = baseData.getDataId();
			datatype = baseData.getDatatype();
			dataname = baseData.getDataname();
			datacode = baseData.getDatacode();
			sortnum = baseData.getSortnum();
			remark = baseData.getRemark();
			flag = baseData.getFlag();
			readonly = true;
			dataTypes = baseDataService.getBaseDataTypes();//获取所有数据类型列表
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doUpdate(){
		try {
			BaseData baseData =baseDataService.getBaseDataById(dataId_q);
			baseData.setDataId(dataId);
			baseData.setDatatype(datatype);
			baseData.setDataname(dataname);
			baseData.setDatacode(datacode);
			baseData.setSortnum(sortnum);
			baseData.setRemark(remark);
			
			baseDataService.modifyBaseData(baseData);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doDelete(){
		try {
			baseDataService.removeBaseDataById(dataId_q);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsertInit(){
		try {
			readonly = false;
			dataTypes = baseDataService.getBaseDataTypes();//获取所有数据类型列表
			ServletActionContext.getRequest().setAttribute("readonly",readonly);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doInsert(){
		try {
			BaseData baseData = new BaseData();
			//dataId = baseDataService.getSequence();
			//baseData.setDataId(dataId);
			baseData.setDatatype(datatype);
			baseData.setDataname(dataname);
			baseData.setDatacode(datacode);
			baseData.setSortnum(sortnum);
			baseData.setRemark(remark);
			baseData.setFlag(flag);
			baseDataService.saveBaseData(baseData);
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}

	public String doBackupInit(){
		
		return SUCCESS;
	}
	public String doBackup(){
		try {
			
			 String driverclass = "oracle.jdbc.driver.OracleDriver";
			 String url = SystemConfig.BACKUP_DATABASE_URL;
			 String userName = "runixoa"; // 数据库帐号
			 String passWord = "runixoa"; // 登陆密码
		//	 String dataBaseName = "orcl"; // 需要备份的数据库名
			 
			 try{
			   Class.forName(driverclass); //加载驱动类的程序
			 } catch (ClassNotFoundException e) {
				e.printStackTrace();
			 }
			 StringBuffer exp=new StringBuffer("exp ");
			 exp.append(userName);
			 exp.append("/");
			 exp.append(passWord);
			 exp.append("@");
			 exp.append(url);
			// exp.append(dataBaseName);
			 exp.append(" file=");
			 
			 String maxIndex=path.substring(path.length()-1);
			 if("/".equals(maxIndex)||"\\".equals(maxIndex)){
				 exp.append(path);
			 }else{
				 path=path+"\\";
				 exp.append(path);
			 }
			 File file = new File(path);
			 if (!file.exists()) {
				 file.mkdir();
			 }
			 exp.append(fileName);
			 exp.append(".dmp");
			 System.out.println("开始备份........");
			 try {
				 System.out.println(exp.toString());
				 Process p=Runtime.getRuntime().exec(exp.toString());
				 InputStreamReader isr = new InputStreamReader(p.getErrorStream());
				 BufferedReader br = new BufferedReader(isr);
				 String line = null;
				 while ((line = br.readLine()) != null){
					 if(line.indexOf("错误")!=-1){
						 System.out.println("备份失败......");
						 messageStr= "数据库备份失败！";
						 break;
					 }else{
						 messageStr= "数据库备份成功！";
					 }
				 }
				 p.destroy();
				 p.waitFor();
				 this.addActionMessage(messageStr);
			 } catch (IOException e) {
				 System.out.println(e.getMessage());
			 } catch (InterruptedException e) {
				 System.out.println(e.getMessage());
			 }
			 
		} catch (Exception e) {
			e.printStackTrace();
			this.addActionError("please check! there is Exception");
		}
		return SUCCESS;
	}
	public void setReadonly(boolean readonly){
		this.readonly=readonly;
	}
	public boolean getReadonly(){
		return readonly;
	}

	public void setCurrentPage(String currentPage){
		this.currentPage=currentPage;
	}
	public String getCurrentPage(){
		return currentPage;
	}

	public void setPages(int pages){
		this.pages=pages;
	}
	public int getPages(){
		return pages;
	}

	public void setBaseDataList(List baseDataList){
		this.baseDataList=baseDataList;
	}
	public List getBaseDataList(){
		return baseDataList;
	}

	public void setDataId_q(String dataId_q){
		this.dataId_q=dataId_q;
	}
	public String getDataId_q(){
		return dataId_q;
	}

	public void setDataId(String dataId){
		this.dataId=dataId;
	}
	public String getDataId(){
		return dataId;
	}

	public void setDatatype(String datatype){
		this.datatype=datatype;
	}
	public String getDatatype(){
		return datatype;
	}

	public void setDataname(String dataname){
		this.dataname=dataname;
	}
	public String getDataname(){
		return dataname;
	}

	public void setDatacode(String datacode){
		this.datacode=datacode;
	}
	public String getDatacode(){
		return datacode;
	}

	public void setSortnum(String sortnum){
		this.sortnum=sortnum;
	}
	public String getSortnum(){
		return sortnum;
	}

	public void setRemark(String remark){
		this.remark=remark;
	}
	public String getRemark(){
		return remark;
	}

	public void setFlag(String flag){
		this.flag=flag;
	}
	public String getFlag(){
		return flag;
	}
	public String getDataType_q() {
		return dataType_q;
	}

	public void setDataType_q(String dataType_q) {
		this.dataType_q = dataType_q;
	}

	public String getDataName_q() {
		return dataName_q;
	}

	public void setDataName_q(String dataName_q) {
		this.dataName_q = dataName_q;
	}
	public void setBaseDataService(BaseDataService baseDataService){
		this.baseDataService=baseDataService;
	}
	public BaseDataService getBaseDataService(){
		return baseDataService;
	}
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public List getDataTypes() {
		return dataTypes;
	}

	public void setDataTypes(List dataTypes) {
		this.dataTypes = dataTypes;
	}
	public String getMessageStr() {
		return messageStr;
	}

	public void setMessageStr(String messageStr) {
		this.messageStr = messageStr;
	}

}
