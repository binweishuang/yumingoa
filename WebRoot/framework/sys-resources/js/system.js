/**
	日期选择
**/
function selectDate(obj){
	var showx = event.screenX - event.offsetX - 4 - 10 ;
	var showy = event.screenY - event.offsetY -168;
	var newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog(getFrameworkPath() + "/common/date.htm", "", "dialogWidth:197px; dialogHeight:232px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; " );
	if( retval !== null )
	{
		var objValue=eval(obj);
		objValue.value = retval;
	}
}
/*
	得到topFrame中form1窗体中指定元素的值
*/
function getTopFrameValue(el)
{
	var tf = parent.frames["topFrame"];
	if( tf )
	{
		var frm = tf.form1;
		if( frm )
		{
			var ctrl = frm.elements[el];
			if( ctrl )
			{
				return ctrl.value;
			}
		}
	}
	return "";
}


/**
	设置离开保存标识
**/
function setExitConfirmFlag(bConfirm  /*1 or 0*/)
{
	parent.frames["topFrame"].form1.exitconfirm.value=bConfirm;		
}

/*
	设置离开保存提示标识
*/
function set_exit_confirm(contextPath, url, target)
{
	if( "1" == getTopFrameValue("exitconfirm") )
	{
		if( confirm("在离开当前页面前，请确认数据已经保存，是否要离开当前页面？") )
		{
			parent.frames[target].location.replace(contextPath + url);
			return false;
		}
		else
		{
			return false;
		}
	}
	setExitConfirmFlag("1");		
	parent.frames[target].location.replace(contextPath + url);
	return false;
	
}
	
/**
	打开页面时检查数据是否保存
**/
function exit_confirm(contextPath, url, target)
{
	if( "1" == getTopFrameValue("exitconfirm") && "mainFrame" == target )
	{
		if( confirm("在离开当前页面前，请确认数据已经保存，是否要离开当前页面？") )
		{
			setExitConfirmFlag("0");
			parent.frames[target].location.replace(contextPath + url);
			return false;
		}
	}
	else
	{
		parent.frames[target].location.replace(contextPath + url);
	}
	return false;
}


/**
	根据给定的json字符串生成javascript对象
	@param json字符串
	@return  javascript对象
**/
function parseJsonString(json)
{
	return eval('(' + json + ')');
}

/**
	打开模态对话框
	@param url 打开URL，相对于跟目录，以 / 开头
	@param wd 对话框宽度
	@param ht 对话框高度
	@return 对话框返回值
**/
function showDialog(url, wd, ht)
{
	var winFeatures = "";
	if( wd && ht )
	{	
		winFeatures = "dialogHeight:" + ht + "px; dialogWidth:" + wd + "px;status:no; help:no; scroll:no";
	}
	return window.showModalDialog(getFrameworkPath() + url,"", winFeatures);
}

/*
	错误信息对话框
	@param 错误信息
*/
function showErrorDialog(title)
{
	var w = 180 + title.length * 4;
	showDialog(getFrameworkPath()+"/common/dialog_info.jsp?type=dialog_err&title=" + title,w,130);
}

/*
	提示信息信息对话框
	@param 错误信息
*/
function showTipDialog(title)
{
	var w = 180 + title.length * 4;
	showDialog(getFrameworkPath()+"/common/dialog_info.jsp?type=dialog_exc&title=" + title,w,130);
}

/*
	confirm替换对话框
	@param 确认信息
*/
function confirm2(title)
{
	var w = 240 + title.length * 4;
	return showDialog(getFrameworkPath()+"/common/dialog_dbl.jsp?type=dialog_quest&title=" + title,w,130);	
}

/**
	公司选择
**/
function selectOneCompany()
{
	return showDialog("/base_jsps/base_company/selectonecompany.dlg?actionName=doPage", 600,550);
}

/**
	部门选择
**/
function selectOneDepartment()
{
	return showDialog("/base_jsps/base_department/selectonedepartment.dlg?actionName=doPage", 600,550);
}

/**
	账户选择
**/
function selectOneUser()
{
	return showDialog("/base_jsps/base_user/selectoneuser.dlg?actionName=doPage", 600,550);
}

/*
	显示进度或提示栏
*/
function showTip(dTip){
		if(typeof(progressMask)=="undefined"){
			var objStr="<div id=\"progressMask\" class=\"progressMask\"></div>";
			var newobj=document.createElement(objStr);
			document.body.insertBefore(newobj);
		}
		progressMask.innerHTML="";
		progressMask.style.display="block";
		dTip.style.display="block";	
		dTip.style.left=(document.body.clientWidth-dTip.clientWidth)/2;
		dTip.style.top=(document.body.clientHeight-dTip.clientHeight)/2;
	}

/*
	关闭进度或提示栏
*/
function hideTip(dTip){
	progressMask.style.display="none";
	if(typeof(dTip)!="undefined"){
		dTip.style.display="none";
	}
}

function showHelp(id)
{
	var feature = "'height=600, width=800, top=50, left=150, toolbar=no, menubar=no, scrollbars=yes, resizable=yes,location=n o, status=no'";
	window.open(getFrameworkPath() + "/common/help/help.jsp?id=" + id, "帮助系统", feature);
	return false;
}

/**
	选择对话框
	@param frm 当前Form
	@param name 目标输入框名称
	@param dialogId 查询页面编码
	@param w  对话框宽度
	@param h  对话框高度
**/
function selectDialog(frm, name, dialogId, w, h)
{
    		var ret = showDialog('page.dlg?actionName=doPage&id=' + dialogId,w,h);
    		if(typeof ret == "string" && ret == "-1")
    		{
    			frm.elements[name + "_label"].value = "";
    			frm.elements[name].value = "";
    		}
    		else if( ret )
    		{
    			frm.elements[name + "_label"].value = ret.returnlabel;
    			frm.elements[name].value = ret.returnid;
    		}
}

/**
	判断用户是否选择了记录
**/
function selectSomeThing(ret)
{
	if( typeof ret == "undefined" )
	{
		return false;
	}
	if(typeof ret == "string" && ret == "-1")
	{
		return false;
	}
	if( ret != null )
	{
		return true;
	}
	else
	{
		return false;
	}
}
function userSelect4Wf(bizMethod)
{
	return showDialog("/sychworkflow_jsps/console/userselect.jsp?actionName=doInit&wf_bizmethod=" + bizMethod, 600,550);
}

function __queryClose()
{
	event.returnValue = "如果数据未保存，更改数据可能会丢失";
}

function queryClose()
{
	window.attachEvent("onbeforeunload", __queryClose);
}


var isAllScreen = false;
function changeWin(allScreen){
	if(!allScreen){
	    window.parent.document.getElementById("frmSet").rows="0,0,*,0";//0,8,*,30
		window.parent.document.getElementById("mainfrm").cols = "0,6,*";
		document.getElementById("screenQ").style.display = "none";
	    document.getElementById("screenH").style.display = "inline";
	}else{
	    window.parent.document.getElementById("frmSet").rows="62,32,*,28";//58,8,*,30
		window.parent.document.getElementById("mainfrm").cols = "176,6,*";//184,8,*
		document.getElementById("screenQ").style.display = "inline";
		document.getElementById("screenH").style.display = "none";
		
	}
	isAllScreen = !isAllScreen;
}