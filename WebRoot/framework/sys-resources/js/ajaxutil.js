var theForm = null;
var jsonObject = null;

function refreshRefSelect(s)
{
	//DWRUtil.useLoadingMessage("正在提交请求,请稍后......");
	var refEl = s.ref;
	
	if( refEl == undefined )
	{
		return;
	}
	var refSelect = $(refEl);
	
	if( refSelect )
	{
		var attrs = refSelect.attributes;
		var postData = __buildPostData(refSelect.form);
		SelectCtrlUtilAjaxLet.getOptions(refEl, attrs, postData, __refreshRefSelect);
	}
}

function __refreshRefSelect(ret)
{
	var jsonObject =  eval('(' + ret + ')');
	var id = jsonObject.refid;
	var defaultOpts = jsonObject.defaultOptions;
	var defaulValue = jsonObject.defaultValue;
	
	var selCtrl = $(id);
	if( selCtrl )
	{
		for(var i = selCtrl.options.length - 1; i >= defaultOpts; i-- )
		{
			selCtrl.options.remove(i);
		}
		var opts = jsonObject.options;
		var len = opts.length;
		for(var i = 0 ; i < len; i++ )
		{
			var opt = opts[i];
			
			var optCtrl = new Option(opt[1], opt[0]);
			selCtrl.options[selCtrl.options.length] = optCtrl;
		}
		
		if( defaulValue != undefined )
		{
			DWRUtil.setValue(id, defaulValue);
		}
		if( selCtrl.initvalue && selCtrl.initvalue.length > 0 )
		{
			DWRUtil.setValue(id, unescape(selCtrl.initvalue));
		}
		
		refreshRefSelect(selCtrl);
	}
}


function __buildPostData(frm) {
      var postData = "";
      for (var i=0; i<frm.elements.length; i++) {
      	 var hc = frm.elements[i];
         if (hc.tagName == "INPUT") {
            if (hc.type == "text" || hc.type == "password"
                || hc.type == "hidden") {
	            	if( postData.length > 0 )
	            	{
	            		postData += "&";
	            	}
	               postData += hc.name + "=" + escape(hc.value);
            }
            else if (hc.type == "checkbox") {
               if (hc.checked) {
	               	if( postData.length > 0 )
	            	{
	            		postData += "&";
	            	}
                  	postData += hc.name + "=" + escape(hc.value);
               }
            }
            else if (hc.type == "radio") {
               if (hc.checked) {
               		if( postData.length > 0 )
	            	{
	            		postData += "&";
	            	}
                  	postData += hc.name + "=" + escape(hc.value);
               }
            }
         }   
        else if (hc.tagName == "TEXTAREA") 
        {
        	if( postData.length > 0 )
            	{
            		postData += "&";
            	}
	        postData += hc.name + "=" + escape(hc.value);
        }
        else if (hc.tagName == "SELECT") {
        	if( hc.type == "select-one" )
        	{
	            	if( hc.selectedIndex >= 0 )
	            	{
	            		if( postData.length > 0 )
    				{
    					postData += "&";
    				}
		            	postData += hc.name + "=" + escape(hc.options[hc.selectedIndex].value);
	        	}
	        }
		else
		{
			for (var j = 0; j < hc.length; j++) {
				var opt = hc.options[j];
				if (opt.selected) {
					var optValue = opt.value;
					if (!optValue && !('value' in opt))
					{
						optValue = opt.text;
					}
					if( postData.length > 0 )
					{
			    			postData += "&";
			    		}
					postData += hc.name + "=" + escape(optValue);
				}
    			}
		}
         }
      }
      return postData;
   }
   
function __inArray(val, ary)
{
	var len = ary.length;
	for (var i = 0 ; i < len; i++)
	{
		if( ary[i] == val )
		{
			return true;
		}	
	}
	return false;
}
   
function __setTheForm() {
	if( !theForm )
	{
		return;
	}
	for (var i=0; i<theForm.elements.length; i++) {
	 var hc = theForm.elements[i];
	 if( hc.name == "" )
	 {
	 		continue;
	 }
	 if (hc.tagName == "INPUT") {
		if (hc.type == "text" || hc.type == "password"
					|| hc.type == "hidden") {
		 	hc.value = theValue(hc.name);  
		}
		else if (hc.type == "checkbox") {
			var val = theValue(hc.name);
			if( typeof val == "string" )
			{
				if( hc.value == val )
				{
					hc.checked = true;
				}
			}
			else
			{
				if( __inArray(hc.value , val) )
				{
					hc.checked = true;
				}
			}
		
		}
		else if (hc.type == "radio") {
			var val = theValue(hc.name);
			if( typeof val == "string" )
			{
				if( hc.value == val )
				{
					hc.checked = true;
				}
			}
			else
			{
				if( __inArray(hc.value , val) )
				{
					hc.checked = true;
				}
			}
		}
	 }   
	else if (hc.tagName == "TEXTAREA") 
	{
		hc.value = theValue(hc.name);  
	}
	else if (hc.tagName == "SELECT") {
		var val = theValue(hc.name);
		if( hc.type == "select-one" )
		{
			var oldSelectedIndex = hc.selectedIndex ;
			for (var j = 0; j < hc.length; j++) {
				var opt = hc.options[j];
				if (opt.value == val ) {
					opt.selected = true;
					if( j != oldSelectedIndex )
					{
						if( hc.ref )
			    			{
			    				hc.onchange();
			    			}
					}
					break;
				}
			}
			
	        }
		else
		{
			for (var j = 0; j < hc.length; j++) {
				var opt = hc.options[j];
				opt.selected = false;
				if( typeof val == "string" )
				{
					if (opt.value == val ) {
						opt.selected = true;
					}
				}
				else
				{
					if (__inArray(opt.value, val) ) {
						opt.selected = true;
					}
				}
			}
		}
	 }
	}
}
   
function __parseJsonObject(express)
{
	jsonObject =  eval('(' + express + ')');
	if( jsonObject.redirect)
   	{
   		window.location.replace(jsonObject.redirect);
   	}
	
	var msgHtml = jsonObject.exp_messages_html;
	if( msgHtml )
	{
		var msgDiv = document.getElementById("exp.messages");
		if( msgDiv )
		{
			msgDiv.innerHTML = msgHtml;
		}
	}
	
	__setTheForm();
	return jsonObject;
}
   
function __invokeListener(actionName, formData, calFunc)
{
	DWRUtil.useLoadingMessage("正在提交请求,请稍后......");
	AjaxLet.invokeListener(__exp_path,actionName, formData, calFunc);
}
   
function __catchDoPost(ret)
{
	jsonObject = __parseJsonObject(ret);
	try
	{
	   	if( catchInvoke != null )
	   	{
	   		if( typeof catchInvoke == 'function' )
	   		{
	   			catchInvoke(jsonObject);
	   		}
	   	}
	}
	catch(e)
	{
	}
}

/**
	获取Ajax调用后返回值中某个属性值
**/
function theValue(name)
{
	var reg = /\./g;
	var value = "";
	try
	{
		value = eval("jsonObject." + name.replace(reg, "_").toLowerCase());
	}
	catch(e)
	{
		alert(e);
		return "";
	}
	if( value )
	{
		if( typeof value == "string" )
		{
			return value;
		}
		else
		{
			return value[value.length-1];
		}
	}
	else
	{
		return "";
	}
}

/**
	调用当前listener
**/
function invokeListener(actionName, formData)
{
	__invokeListener(actionName, formData, __catchDoPost);
}

/**
	提交当前Form，Form名称为form1
**/
function doPost(actionName)
{
	doPostTheForm(actionName, "form1");
}
 
/**
	提交指定Form
**/  
function doPostTheForm(actionName, formName)
{
	theForm = document.getElementById(formName);
	__invokeListener(actionName, __buildPostData(theForm), __catchDoPost);
}

/**
	Reset给定Form
**/
function resetForm(tForm)
{
	tForm.reset();
	for (var i=0; i<tForm.elements.length; i++) {
	 	if (tForm.elements[i].tagName == "SELECT") {
	    		if( tForm.elements[i].ref )
	    		{
	    			tForm.elements[i].onchange();
	    		}
	 	}	
	}
}

function refreshSelect(){
	var allSelect = document.getElementsByTagName("select")
	for(var i=0; i<allSelect.length; i++){
		if( allSelect[i].ref ){
			allSelect[i].onchange();
		}
	}
}

//window.attachEvent("onload",refreshSelect);


//////////////////兼容老系统的exp:options 实现，不建议使用////
function objectEval(text)
{
    // eval() breaks when we use it to get an object using the { a:42, b:'x' }
    // syntax because it thinks that { and } surround a block and not an object
    // So we wrap it in an array and extract the first element to get around
    // this.
    // The regex = [start of line][whitespace]{[stuff]}[whitespace][end of line]
    text = text.replace(/\n/g, " ");
    text = text.replace(/\r/g, " ");
    if (text.match(/^\s*\{.*\}\s*$/))
    {
        text = "[" + text + "][0]";
    }

    return eval(text);
}

function getRefOptions(s)
{
	var sValue = s.value;
	var refEl = s.ref;
	
	if( refEl == undefined )
	{
		return;
	}
	var refSelect = $(refEl);
	
	if( refSelect )
	{
		var dataSource = refSelect.dataSource;
		if( dataSource == undefined )
		{
			dataSource = '';
		}
		var sqlid = refSelect.sqlid;
		if( sqlid == undefined )
		{
			return;
		}
		
		var formBean = new Array();
		var params = s.params;
		if( params != undefined )
		{
			var arrayParams = params.split(";");
			for(var i = 0 ; i< arrayParams.length; i++ )
			{
				var ctrl = $(arrayParams[i]);
				if(ctrl)
				{
					formBean[formBean.length] = ctrl.name + ":" + DWRUtil.getValue(ctrl);
				}
			}
		}	
		
		formBean[formBean.length] = s.name + ":" + sValue;
		Options.getOptions(refreshOptions, refEl, dataSource, sqlid, formBean);
	}
}

function refreshOptions(ret)
{
	var id = ret[0];
	var opts = ret[1];
	DWRUtil.removeAllOptions(id);
	var option1 = $(id).option1;
	if( option1 != undefined )
	{
		DWRUtil.addOptions(id, objectEval("{" + option1 + "}"));
	}
	DWRUtil.addOptions(id, objectEval(opts));
	var selValue = $(id).selectedvalue;
	if( selValue != undefined )
	{
		DWRUtil.setValue(id, selValue);
	}
	
	getRefOptions($(id));
}
////////////////////////////////////////////////////////////////////////////
   
   
   
