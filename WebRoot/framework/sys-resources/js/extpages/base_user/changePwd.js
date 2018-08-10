/**
 * 定义编辑form
 */
var editFormPanel = function() {
	this.userid = {
		xtype : 'textfield',
		fieldLabel : '当前用户',
		id : 'userid',
		name : 'userid',
		readOnly : true
	};
	this.newPwd = {
		xtype : 'textfield',
		inputType : 'password',
		fieldLabel : '新密码',
		id : 'newPwd',
		name : 'newPwd',
		allowBlank : false,
		blankText : "该项必须填写"
	}
	this.rePwd = {
		xtype : 'textfield',
		inputType : 'password',
		fieldLabel : '密码确认',
		id : 'rePwd',
		name : 'rePwd',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	// 调用formpanel的父类的构造器
	editFormPanel.superclass.constructor.call(this, {
		bodyStyle : 'padding:5px 5px 0',
		frame : true,
		monitorValid : true,
		labelAlign : "right",
		defaults : {
			width : 200
		},
		reader : new Ext.data.JsonReader({
			root : 'root',
			successProperty : 'success',
			totalProperty : 'totalProperty',
			id : 'userid'
		}, ['userid']),
		items : [this.userid,this.newPwd,this.rePwd]
	});
}
/**
 * 重载编辑form加载方法
 * 
 * @class editFormPanel
 * @extends Ext.form.FormPanel
 */
Ext.extend(editFormPanel, Ext.form.FormPanel, {
	loadData : function() {
		var url = 'changePwdInitExt.action';
		this.getForm().load({
			url : url,
//			params : {id : id},
			waitTitle : '请稍候',
			waitMsg : '正在加载表单数据，请稍候...',
			failure : function(form, action) {
				var json = action.response.responseText;
				var o = eval("(" + json + ")");
				Ext.MessageBox.show({
					title : '错误',
					msg : o.message,
					buttons : Ext.MessageBox.OK,
					icon : Ext.MessageBox.ERROR
				});
			}
		});
		
	}
});

var editForm = new editFormPanel();
var conentPanel = new Ext.Panel({
	items:[{
		xtype:"panel",
       	scripts:true,
       	autoScroll : true,
       	layout : 'form',
       	labelAlign : "right",
		region : 'center',
		items : editForm,
		buttons : [{
			text : '保存',
			iconCls : 'save',
			formBind : true,
			handler : function() {
				var newPwd = Ext.getCmp("newPwd").getValue();
				var rePwd = Ext.getCmp("rePwd").getValue();
				deleteForm.getForm().submit({
					url:"changePwdExt.action",
					params:{newPwd:newPwd,rePwd:rePwd},
					waitTitle : '请稍候',
					waitMsg : '正在与服务器交互,请稍候...',
					success : function(form, action) {
						var json = action.response.responseText;
						var o = eval("("+ json+ ")");
						if(o.success) {						
							Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.INFO});
                    		
						} else {
							Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.ERROR});
						}
					}
				})
			}
		}]
	}]
});

var deleteForm = new Ext.form.FormPanel({
	items : {}
});
/*------------------结束数据Drid--------------------------*/
Ext.BLANK_IMAGE_URL = "../extjs/resources/images/default/s.gif";
Ext.onReady(function() {
	Ext.QuickTips.init();
	
	var deleteFormDiv = Ext.DomHelper.insertHtml('afterEnd', document.body,
		'<div id="' + Ext.id() + '" style="visibility: hidden;"> </div>');// 在html中插入deleteFormDiv
	deleteForm.render(deleteFormDiv);
	
	new Ext.Viewport({
		layout : 'fit',
		renderTo : 'tip',
		autoScroll : true,
		items : conentPanel
	});
	editForm.loadData();
});