/**
 * 定义编辑form
 */
var editFormPanel = function() {
	this.tip = {
		xtype : 'htmleditor',
		fieldLabel : '内容',
		height : 200,
		width : 500,
		id : 'tip',
		name : 'tip'
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
			id : 'tip'
		}, ['tip']),
		items : [this.tip]
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
		var url = 'updateInitExt.action';
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
			},
			success : function(form, action) {
				var json = action.response.responseText;
				var o = eval("(" + json + ")");
				Ext.get("show").dom.innerHTML = o.message;
			}
		});
		
	}
});
var editForm = new editFormPanel();
var conentPanel = new Ext.Panel({
	items:[{
		xtype:"panel",
       	title:"首页公告",
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
				var ids = Ext.getCmp("tip").getValue();
					//这里使用ajax调用
					Ext.Ajax.request({
						url:"saveExt.action",
						params:{tip:ids},
						waitTitle : '请稍候',
						waitMsg : '正在与服务器交互,请稍候...',
						success: function(response,options) {
//							var result = Ext.decode(response.responseText);
//							var result = response.responseText;
							var json = response.responseText;
							var o = eval("("+ json+ ")");
							if(o.success) {
//                        		Ext.Msg.alert('操作', '恭喜你,信息删除成功!');								
								Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.INFO});
								Ext.get("show").dom.innerHTML = ids;
                        		
							} else {
//								Ext.Msg.alert('错误',result);
								Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.ERROR});
							}
						}
					})
			}
		}]
	}]
});

/*------------------结束数据Drid--------------------------*/
Ext.BLANK_IMAGE_URL = "../extjs/resources/images/default/s.gif";
Ext.onReady(function() {
	Ext.QuickTips.init();
	// Ext.form.Field.prototype.msgTarget = 'under';//错误提示显示的位置
	new Ext.Viewport({
//		layout : 'fit',
		renderTo : 'tip',
		autoScroll : true,
		items : conentPanel
	});
	editForm.loadData();
});