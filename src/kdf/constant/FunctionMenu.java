package kdf.constant;

/**
 * 
 * 类描述/Class Description：
 * 功能菜单对应的菜单resourceid
 * 为了对无菜单，但必须使用权限控制的地方使用，在IndexAction类中会在session中存一个名为FUNC的非菜单权限组List
 * 在要对非菜单权限控制的地方使用这里定义好的常量和FUNC中的值比较，如果有，代表此用户有些权限
 * List funcs = (List)ServletActionContext.getContext().getSession().get("FUNC");
 *	if(funcs.contains(FunctionMenu.FUNCTION_BIZ_ENT_DEL)) {
 *		doIt(); //有权限，
 *	}
 *
 * 负责人/principal:管俊
 *
 * 修改记录/revision：
 * 日期：		修改人：		修改说明：
 *
 */
public interface FunctionMenu {
	/**
	 * 客户删除功能
	 */
	public static String FUNCTION_BIZ_ENT_DEL = "FUNCTION.BIZ.ENT.DEL";
}
