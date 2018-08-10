/**
 * 静态Array数据，给下拉列表使用
 * 
 * @type {Array}
 */
var actives = [["不可用", "0"], ["可用", "1"]];
/**
 * 静态数据存储器
 */
var activesStore = new Ext.data.SimpleStore({
	fields : ["name", "code"],
	data : actives
});

/**
 * 列表中对应的方法
 * 
 * @param {}
 *            value
 * @return {String}
 */
var renderActive = function(value) {
	if (value == "0") {
		return "不可用";
	} else if (value == "1") {
		return "可用";
	} else {
		return "";
	}
};

/**
 * 列表中的数据项
 */
var recordType = Ext.data.Record.create([{
	name : 'projectid'
}, {
	name : 'projectname'
}, {
	name : 'serverurl'
}, {
	name : 'isactive'
}, {
	name : 'sort'
}]);

/**
 * 列表中的数据
 */
var store = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'queryExt.action?pages='+pageSize
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'projectid'
	}, recordType)
});
/*-------------------开始新增窗体--------------------*/
/**
 * 定义新增form
 */
var addFormPanel = function() {
	this.projectid = {
		xtype : 'textfield',
		fieldLabel : '项目编码',
		id : 'projectid',
		name : 'projectid',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.projectname = {
		xtype : 'textfield',
		fieldLabel : '项目名称',
		id : 'projectname',
		name : 'projectname',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.isactive = {
		xtype : "combo",
		fieldLabel : '是否可用',
		id : 'isactive_id',
		name : 'isactive',
		hiddenName : "isactive",
		displayField : "name",
		valueField : "code",
		store : activesStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	};
	this.serverurl = {
		xtype : 'textarea',
		fieldLabel : '访问地址',
		height : 100,
		id : 'serverurl',
		name : 'serverurl'
	};
	this.sort = {
		xtype : 'textarea',
		fieldLabel : '序号',
		height : 100,
		id : 'sort',
		name : 'sort'
	};
	addFormPanel.superclass.constructor.call(this, {
		bodyStyle : 'padding:5px 5px 0',
		frame : true,
		monitorValid : true,
		labelAlign : "right",
		defaults : {
			width : 200
		},
		items : [this.projectid, this.projectname, this.isactive, this.serverurl, this.sort]
	});
}
/**
 * 让addFormPanel继承FormPanel
 * 
 * @class addFormPanel
 * @extends Ext.form.FormPanel
 */
Ext.extend(addFormPanel, Ext.form.FormPanel);
/**
 * 新增window
 */
var addWindow = function() {
	var _win = this;
	this.formPanel = new addFormPanel();
	var _form = this.formPanel.getForm();
	addWindow.superclass.constructor.call(this, {
		title : '项目信息',
		width : 400,
		height : 300,
		resizable : true,
		plain : false,
		border : false,
		modal : true,
		autoScroll : true,
		layout : 'fit',
		// closeAction : 'hide',//因为IE的问题，默认的关闭命令为distroy
		items : this.formPanel,

		buttons : [{
			text : '保存',
			iconCls : 'save',
			formBind : true,
			handler : function() {
				
				if (_form.isValid()) {
					_form.submit({
						waitTitle : '请稍候',
						waitMsg : '正在与服务器交互,请稍候...',
						url : 'insertExt.action',
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
						success : function(form1, action) {
							var json = action.response.responseText;
							var o = eval("(" + json + ")");
							Ext.MessageBox.show({
								title : '成功',
								msg : o.message,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.INFO
							});
							store.reload();
							// 因为在IE下使用有问题，所以只能distroy窗体，打开时new一个新的窗体
							if (_win) {
								_win.destroy();
							}
							// _win.hide();//因为在IE下使用有问题，所以不能隐藏窗体，在FF下一切正常
						}
					});
				}
			}
		}, {
			text : '关闭',
			iconCls : 'close',
			handler : function() {
				// 因为在IE下使用有问题，所以只能distroy窗体，打开时new一个新的窗体
				if (_win) {
					_win.destroy();
				}
				// _win.hide();//因为在IE下使用有问题，所以不能隐藏窗体，在FF下一切正常
			},
			tooltip : '关闭窗口'
		}]
	});
}
/**
 * addWindow继承Ext.Window
 * 
 * @class addWindow
 * @extends Ext.Window
 */
Ext.extend(addWindow, Ext.Window);
/*-------------------结束新增窗体--------------------*/

/*-------------------开始编辑窗体--------------------*/
/**
 * 定义编辑form
 */
var editFormPanel = function() {
	this.projectid = {
		xtype : 'textfield',
		fieldLabel : '项目编码',
		id : 'projectid',
		name : 'projectid',
		allowBlank : false,
		blankText : "该项必须填写",
		readOnly : true
	};
	this.projectname = {
		xtype : 'textfield',
		fieldLabel : '项目名称',
		id : 'projectname',
		name : 'projectname',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.isactive = {
		xtype : "combo",
		fieldLabel : '是否可用',
		id : 'isactive_id',
		name : 'isactive',
		hiddenName : "isactive",
		displayField : "name",
		valueField : "code",
		store : activesStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	};
	this.serverurl = {
		xtype : 'textarea',
		fieldLabel : '访问地址',
		height : 100,
		id : 'serverurl',
		name : 'serverurl'
	};
	this.sort = {
		xtype : 'textarea',
		fieldLabel : '序号',
		height : 100,
		id : 'sort',
		name : 'sort'
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
			id : 'projectid'
		}, ['projectid', 'projectname', 'isactive', 'serverurl', 'sort']),
		items : [this.projectid, this.projectname, this.isactive, this.serverurl, this.sort]
	});
}
/**
 * 编辑window
 */
var editWindow = function() {
	var _win = this;
	this.formPanel = new editFormPanel();
	var _form = this.formPanel.getForm();
	editWindow.superclass.constructor.call(this, {
		title : '项目信息',
		width : 400,
		height : 300,
		resizable : true,
		plain : false,
		border : false,
		modal : true,
		autoScroll : true,
		layout : 'fit',
		// closeAction : 'hide',//因为IE的问题，默认的关闭命令为distroy
		items : this.formPanel,

		buttons : [{
			text : '保存',
			iconCls : 'save',
			formBind : true,
			handler : function() {
				
				if (_form.isValid()) {
					_form.submit({
						waitTitle : '请稍候',
						waitMsg : '正在与服务器交互,请稍候...',
						url : 'updateExt.action',
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
						success : function(form1, action) {
							var json = action.response.responseText;
							var o = eval("(" + json + ")");
							Ext.MessageBox.show({
								title : '成功',
								msg : o.message,
								buttons : Ext.MessageBox.OK,
								icon : Ext.MessageBox.INFO
							});
							store.reload();
							// 因为在IE下使用有问题，所以只能distroy窗体，打开时new一个新的窗体
							if (_win) {
								_win.destroy();
							}
							// _win.hide();//因为在IE下使用有问题，所以不能隐藏窗体，在FF下一切正常
						}
					});
				}
			}
		}, {
			text : '关闭',
			iconCls : 'close',
			handler : function() {
				// 因为在IE下使用有问题，所以只能distroy窗体，打开时new一个新的窗体
				if (_win) {
					_win.destroy();
				}
				// _win.hide();//因为在IE下使用有问题，所以不能隐藏窗体，在FF下一切正常
			},
			tooltip : '关闭窗口'
		}]
	});
}
/**
 * 重载编辑form加载方法
 * 
 * @class editFormPanel
 * @extends Ext.form.FormPanel
 */
Ext.extend(editFormPanel, Ext.form.FormPanel, {
	loadData : function(id) {
		var url = 'updateInitExt.action';
		this.getForm().load({
			url : url,
			params : {id : id},
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
/**
 * editWindow继承Ext.Window,并重载loadData方法
 * 
 * @class editWindow
 * @extends Ext.Window
 */
Ext.extend(editWindow, Ext.Window, {
	loadData : function(id) {
		this.formPanel.loadData(id);
	}
});
/*-------------------结束编辑窗体--------------------*/


/*------------------开始数据Drid--------------------------*/
/**
 * 数据GridPanel
 */
var myGridPanel = function() {
	// var editWin = new
	// editWindow();//编辑window,因为在IE下使用有问题，所以在这里不new一个新窗体，把new新窗体放在了按钮的操作里
	// var addWin = new addWindow();//新增window
	var _grid = this;
	this.store = store;
	this.store.load({
		params : {
			start : 0,
			limit : pageSize
		}
	});// 默认加载5条记录，从第1条开始
	var sm = new Ext.grid.CheckboxSelectionModel({
		singleSelect : false
	});
	this.columns = [new Ext.grid.RowNumberer(), {
		id : 'projectid',
		header : "项目编码",
		width : 150,
		sortable : true,
		dataIndex : 'projectid'
	}, {
		header : "项目名称",
		width : 150,
		sortable : true,
		dataIndex : 'projectname'
	}, {
		header : "是否可用",
		width : 80,
		sortable : true,
		dataIndex : 'isactive',
		renderer : renderActive
	}, {
		header : "访问地址",
		width : 300,
		sortable : true,
		dataIndex : 'serverurl'
	}, sm];

	/*------------开始建一个deleteForm，用于给delete操作提供表单提交的方法-------------*/
	var deleteFormDiv = Ext.DomHelper.insertHtml('afterEnd', document.body,
			'<div id="' + Ext.id() + '" style="visibility: hidden;"> </div>');// 在html中插入deleteFormDiv

	var deleteForm = new Ext.form.FormPanel({
		items : {}
	});

	deleteForm.render(deleteFormDiv);
	
	/*------------结束建一个deleteForm，用于给delete操作提供表单提交的方法-------------*/

	myGridPanel.superclass.constructor.call(this, {
		layout : 'fit',
		sm : sm,
		viewConfig : {
			emptyText : '没有记录'
		},
		bbar : new Ext.PagingToolbar({
			pageSize : pageSize,// 每页20条记录
			store : this.store,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		}),
		tbar : [
				{
					text : '添加',
					tooltip : '添加',
					iconCls : 'add',
					handler : function() {
						var addWin = new addWindow();// 为了在IE中能正常显示window,在这里new一个window,因为在关闭window时是使用distroy
						addWin.formPanel.getForm().reset();
						addWin.show();

					}
				},
				'-',
				{
					text : '编辑',
					tooltip : '编辑',
					iconCls : 'edit',
					handler : function() {
						var record = _grid.getSelected();
						if (record != null) {
							var editWin = new editWindow();// 为了在IE中能正常显示window,在这里new一个window,因为在关闭window时是使用distroy
							editWin.show();
							var record = _grid.getSelected();
							editWin.record = record;
							editWin.loadData(record.data.projectid);
						}
					}
				},
				'-',
				{
					text : '删除',
					tooltip : '删除',
					iconCls : 'del',
					handler : function() {
						var records = _grid.getSelections();
//						var records = _grid.getSelected();
						if (records) {
							Ext.MessageBox.confirm('提示', '您真的要删除这些吗?', function(btn) {
								if (btn == 'yes') {
									var ids = "";
//									var ids = records.data.deptid;
									for (var i = 0; i < records.length; i++) {
										var record = records[i];
										if (record.data.projectid) {
											ids += record.data.projectid + ',';
										} else {
											
										}
									}
									deleteForm.getForm().submit({
										waitTitle : '请稍候',
										waitMsg : '正在与服务器交互,请稍候...',
										url : 'deleteExt.action',
										params : {id : ids},
										failure : function(form, action) {
											var json = action.response.responseText;
											var o = eval("(" + json + ")");
											Ext.MessageBox.show({
												title : '错误',
												msg : o.message,
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.ERROR
											});
											_grid.store.reload();
										},
										success : function(form, action) {
											var json = action.response.responseText;
											var o = eval("(" + json + ")");
											Ext.MessageBox.show({
												title : '成功',
												msg : o.message,
												buttons : Ext.MessageBox.OK,
												icon : Ext.MessageBox.INFO
											});
											_grid.store.reload();
										}
									});
								}
							});
						}
					}
				}]
	});
}

/**
 * myGridPanel继承Ext.gridGridPanel，并重载getSelected和getSelections
 * 
 * @class myGridPanel
 * @extends Ext.grid.GridPanel
 */
Ext.extend(myGridPanel, Ext.grid.GridPanel, {
	getSelected : function() {
		var record = this.getSelectionModel().getSelected();
		if (record == null) {
			Ext.MessageBox.show({
				title : '信息',
				msg : "请选择一行,然后再进行操作!",
				buttons : Ext.MessageBox.OK,
				icon : Ext.MessageBox.INFO
			});
		} else {
			return record;
		}
	},
	getSelections : function() {
		var records = this.getSelectionModel().getSelections();
		if (records.length < 1) {
			Ext.MessageBox.show({
				title : '信息',
				msg : "请至少选择一行,然后再进行操作!",
				buttons : Ext.MessageBox.OK,
				icon : Ext.MessageBox.INFO
			});
		} else {
			return records;
		}
	}
});
/*------------------结束数据Drid--------------------------*/
Ext.BLANK_IMAGE_URL = "../extjs/resources/images/default/s.gif";
Ext.onReady(function() {
	Ext.QuickTips.init();
	// Ext.form.Field.prototype.msgTarget = 'under';//错误提示显示的位置
	new Ext.Viewport({
		layout : 'fit',
		items : new myGridPanel()
	});
});