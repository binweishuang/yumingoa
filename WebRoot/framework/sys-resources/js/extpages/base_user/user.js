/**
 * 页面中的下拉列表，动态数据
 */
var companyStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'queryCompanyExt.action'
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'companyid',
		fields : ['companyid','companyname']
	})
});

var deptStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'queryDeptExt.action'
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'deptid',
		fields : ['deptid','deptname']
	})
});

var dutyStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'queryDutyExt.action'
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'dutyid',
		fields : ['dutyid','dutyname']
	})
});

var roleStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'queryRoleExt.action'
	}),
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'roleid',
		fields : ['roleid','rolename']
	})
});

/**
 * 列表中对应的方法
 * 
 * @param {}
 *            value
 * @return {String}
 */
var renderLocked = function(value) {
	if (value == "0") {
		return "未锁定";
	} else if (value == "1") {
		return "已锁定";
	} else {
		return "";
	}
};

/**
 * 列表中的数据项
 */
var recordType = Ext.data.Record.create([{
	name : 'userid'
}, {
	name : 'username'
}, {
	name : 'ip'
}, {
	name : 'phone'
}, {
	name : 'email'
}, {
	name : 'deptid'
}, {
	name : 'dutyid'
}, {
	name : 'companyid'
}, {
	name : 'locked'
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
		id : 'userid'
	}, recordType)
});
var companyidComboBox = new Ext.form.ComboBox({
	fieldLabel : '所属公司',
		id : 'companyid_id',
		name : 'companyid',
		hiddenName : "companyid",
		displayField : "companyname",
		valueField : "companyid",
		store : companyStore,
		typeAhead : true,
//		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
});
companyidComboBox.on('select',function(comboBox){
	var value = comboBox.getValue();
	deptStore.load({params:{id:value}});
	dutyStore.load({params:{id:value}});
});

/*-------------------开始新增窗体--------------------*/
/**
 * 定义新增form
 */
var addFormPanel = function() {
	this.userid = {
		xtype : 'textfield',
		fieldLabel : '用户账号',
		id : 'userid',
		name : 'userid',
		allowBlank : false,
		blankText : "该项必须填写",
		vtype : 'alphanum',
		vtypeText : '必须输入字母或数字'
	};
	this.username = {
		xtype : 'textfield',
		fieldLabel : '用户姓名',
		id : 'username',
		name : 'username',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.companyid = {
		xtype : "combo",
		fieldLabel : '所属公司',
		id : 'companyid_id',
		name : 'companyid',
		hiddenName : "companyid",
		displayField : "companyname",
		valueField : "companyid",
		store : companyStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	};
	this.deptid = {
		xtype : "combo",
		fieldLabel : '所属部门',
		id : 'deptid_id',
		name : 'deptid',
		hiddenName : "deptid",
		displayField : "deptname",
		valueField : "deptid",
		store : deptStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	}
	this.dutyid = {
		xtype : "combo",
		fieldLabel : '职务',
		id : 'dutyid_id',
		name : 'dutyid',
		hiddenName : "dutyid",
		displayField : "dutyname",
		valueField : "dutyid",
		store : dutyStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	}
	this.ip = {
		xtype : 'textfield',
		fieldLabel : 'IP地址',
		id : 'ip',
		name : 'ip',
		regex : /\b(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])\b/,//正则表达试，必须用两个"/"包括
		regexText : '必须为IP地址,格式如:255.255.255.255'
	};
	this.phone = {
		xtype : 'textfield',
		fieldLabel : '联系电话',
		id : 'phone',
		name : 'phone'
	};
	this.email = {
		xtype : 'textfield',
		fieldLabel : '电子邮箱',
		id : 'email',
		name : 'email',
		vtype : 'email',
		vtypeText : '必须为电子邮件地址,格式如:user@domain.com'
	};
	this.roles = {
		xtype:'multicombo',
		width:200,
		store:roleStore,
		valueField :"roleid",
		displayField: "rolename",
		labelSeparator:':',
		displaySeparator:';',
		valueSeparator:',',
		mode: 'local',
//		value:'BIZ,test_role',
		forceSelection: true,
		hiddenName:'roles',
		editable: true,
		triggerAction: 'all',
		emptyText:'请选择...',
		fieldLabel: '所属角色'
	};
	addFormPanel.superclass.constructor.call(this, {
		id : 'addFormPanel_id',
		bodyStyle : 'padding:5px 5px 0',
		frame : true,
		monitorValid : true,
		labelAlign : "right",
		defaults : {
			width : 200
		},
		items : [this.userid, this.username, this.companyid, this.deptid, this.dutyid, this.ip, this.phone, this.email, this.roles]
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
		title : '用户信息',
		width : 400,
		height : 350,
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
	this.userid = {
		xtype : 'textfield',
		fieldLabel : '用户账号',
		id : 'userid',
		name : 'userid',
		allowBlank : false,
		blankText : "该项必须填写",
		vtype : 'alphanum',
		vtypeText : '必须输入字母或数字',
		readOnly : true
	};
	this.username = {
		xtype : 'textfield',
		fieldLabel : '用户姓名',
		id : 'username',
		name : 'username',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.companyid = {
		xtype : "combo",
		fieldLabel : '所属公司',
		id : 'companyid_id',
		name : 'companyid',
		hiddenName : "companyid",
		displayField : "companyname",
		valueField : "companyid",
		store : companyStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	};
	this.deptid = {
		xtype : "combo",
		fieldLabel : '所属部门',
		id : 'deptid_id',
		name : 'deptid',
		hiddenName : "deptid",
		displayField : "deptname",
		valueField : "deptid",
		store : deptStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	}
	this.dutyid = {
		xtype : "combo",
		fieldLabel : '职务',
		id : 'dutyid_id',
		name : 'dutyid',
		hiddenName : "dutyid",
		displayField : "dutyname",
		valueField : "dutyid",
		store : dutyStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	}
	this.ip = {
		xtype : 'textfield',
		fieldLabel : 'IP地址',
		id : 'ip',
		name : 'ip',
		regex : /\b(([01]?\d?\d|2[0-4]\d|25[0-5])\.){3}([01]?\d?\d|2[0-4]\d|25[0-5])\b/,//正则表达试，必须用两个"/"包括
		regexText : '必须为IP地址,格式如:255.255.255.255'
	};
	this.phone = {
		xtype : 'textfield',
		fieldLabel : '联系电话',
		id : 'phone',
		name : 'phone'
	};
	this.email = {
		xtype : 'textfield',
		fieldLabel : '电子邮箱',
		id : 'email',
		name : 'email',
		vtype : 'email',
		vtypeText : '必须为电子邮件地址,格式如:user@domain.com'
	};
	this.pwd = {
		xtype : 'hidden',
		id : 'pwd',
		name : 'pwd'
	}
	this.locked = {
		xtype : 'hidden',
		id : 'locked',
		name : 'locked'
	};
	this.roles = {
		xtype:'multicombo',
		width:200,
		store:roleStore,
		valueField :"roleid",
		displayField: "rolename",
		labelSeparator:':',
		displaySeparator:';',
		valueSeparator:',',
		mode: 'local',
//		value:'1,2',
		forceSelection: true,
		hiddenName:'roles',
		editable: true,
		triggerAction: 'all',
		emptyText:'请选择...',
		fieldLabel: '所属角色'
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
		}, ['userid', 'username', 'companyid', 'deptid', 'dutyid', 'ip', 'phone', 'email', 'pwd', 'locked', 'roles']),
		items : [this.userid, this.username, this.companyid, this.deptid, this.dutyid, this.ip, this.phone, this.email, this.pwd, this.locked, this.roles]
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
		title : '用户信息',
		width : 400,
		height : 350,
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

/*------------------开始查询使用的表单和查询方法-------------------*/
var userid_q = new Ext.form.TextField({
	name : 'userid_q',
	fieldLabel : '账号',
	width : 120
});

var username_q = new Ext.form.TextField({
	name : 'username_q',
	fieldLabel : '姓名',
	width : 120
});
var companyid_q = new Ext.form.ComboBox({
	name : "companyid_q",
	fieldLabel : '公司',
	width : 120,
	displayField : "companyname",
	valueField : "companyid",
	store : companyStore,
	typeAhead : true,
//	mode : "local",
	triggerAction : "all",
	emptyText : "请选择...",
//	readOnly : true,
	selectOnFocus : true
});
companyid_q.on('select',function(comboBox){
	var value = comboBox.getValue();
	deptStore.load({params:{id:value}});
	dutyStore.load({params:{id:value}});
});
var deptid_q = new Ext.form.ComboBox({
	name : "deptid_q",
	fieldLabel : '部门',
	width : 120,
	displayField : "deptname",
	valueField : "deptid",
	store : deptStore,
	typeAhead : true,
	mode : "local",
	triggerAction : "all",
	emptyText : "请选择...",
//	readOnly : true,
	selectOnFocus : true
});
var dutyid_q = new Ext.form.ComboBox({
	name : "dutyid_q",
	fieldLabel : '职务',
	width : 120,
	displayField : "dutyname",
	valueField : "dutyid",
	store : dutyStore,
	typeAhead : true,
	mode : "local",
	triggerAction : "all",
	emptyText : "请选择...",
//	readOnly : true,
	selectOnFocus : true
});
function doSelect() {
	var userid = userid_q.getValue();
	var username = username_q.getValue();
	var companyid = companyid_q.getValue();
	var deptid = deptid_q.getValue();
	var dutyid = dutyid_q.getValue();
	store.reload({
		params : {// params表示要传递到后台的参数
			start : 0,
			limit : pageSize,
			command : 'queryExt.action',
			userid_q : userid,// form表单值 : 值
			username_q : username,
			companyid_q : companyid,
			deptid_q : deptid,
			dutyid_q : dutyid
		}
	});
}
/*------------------结束开始查询使用的表单和查询方法-------------------*/

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
	//为了在翻页后，查询条件还有用，必须使用下面的内容
	this.store.on("beforeload",function(t){
        t.baseParams = {
            userid_q : userid_q.getValue(),
            username_q : username_q.getValue(),
            companyid_q : companyid_q.getValue(),
            deptid_q : deptid_q.getValue(),
            dutyid_q : dutyid_q.getValue()
        };
    });

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
		id : 'userid',
		header : "用户账号",
		width : 100,
		sortable : true,
		dataIndex : 'userid'
	}, {
		header : "用户姓名",
		width : 100,
		sortable : true,
		dataIndex : 'username'
	}, {
		header : "IP地址",
		width : 100,
		sortable : true,
		dataIndex : 'ip'
	}, {
		header : "联系电话",
		width : 100,
		sortable : true,
		dataIndex : 'phone'
	}, {
		header : "电子邮箱",
		width : 150,
		sortable : true,
		dataIndex : 'email'
	}, {
		header : "所属公司",
		width : 120,
		sortable : true,
		dataIndex : 'companyid'
	}, {
		header : "所属部门",
		width : 120,
		sortable : true,
		dataIndex : 'deptid'
	}, {
		header : "职务",
		width : 120,
		sortable : true,
		dataIndex : 'dutyid'
	}, {
		header : "是否锁定",
		width : 80,
		sortable : true,
		dataIndex : 'locked',
		renderer : renderLocked
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
//		layout : 'fit',
		region : 'center',
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
						companyStore.load();
						Ext.getCmp('companyid_id').on('select',function(comboBox){
							var value = comboBox.getValue();
							deptStore.load({params:{id:value}});
							dutyStore.load({params:{id:value}});
						});
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
							editWin.loadData(record.data.userid);
							companyStore.load();
							deptStore.load({params:{id:record.data.companyid}});
							dutyStore.load({params:{id:record.data.companyid}});
							
							Ext.getCmp("companyid_id").on('select',function(comboBox){
								var value = comboBox.getValue();
								deptStore.load({params:{id:value}});
								dutyStore.load({params:{id:value}});
							});
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
//									var ids = records.data.userid;
									for (var i = 0; i < records.length; i++) {
										var record = records[i];
										if (record.data.userid) {
											ids += record.data.userid + ',';
										} else {
//									 		_grid.store.remove(record);
										}
									}
									deleteForm.getForm().submit({
										waitTitle : '请稍候',
										waitMsg : '正在与服务器交互,请稍候...',
										url : 'deleteExt.action',
										params:{id:ids},
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
				},
				'-',
				{
					text : '重置密码',
					tooltip : '重置密码',
					iconCls : 'resetpwd',
					handler : function() {
						var records = _grid.getSelections();
//						var records = _grid.getSelected();
						if (records) {
							Ext.MessageBox.confirm('提示', '您真的要重置这些用户的密码吗?', function(btn) {
								if (btn == 'yes') {
									var ids = "";
//									var ids = records.data.userid;
									for (var i = 0; i < records.length; i++) {
										var record = records[i];
										if (record.data.userid) {
											ids += record.data.userid + ',';
										} else {
//									 		_grid.store.remove(record);
										}
									}
									deleteForm.getForm().submit({
										waitTitle : '请稍候',
										waitMsg : '正在与服务器交互,请稍候...',
										url : 'resetPwdExt.action',
										params:{id:ids},
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
				},
				'-',
				{
					text : '锁定/解锁',
					tooltip : '根据用户的锁定状态,锁定或解锁用户',
					iconCls : 'lock',
					handler : function() {
						var records = _grid.getSelections();
//						var records = _grid.getSelected();
						if (records) {
							Ext.MessageBox.confirm('提示', '您真的要锁定或解锁这些用户吗?', function(btn) {
								if (btn == 'yes') {
									var ids = "";
									var locks = "";
//									var ids = records.data.userid;
									for (var i = 0; i < records.length; i++) {
										var record = records[i];
										if (record.data.userid) {
											ids += record.data.userid + ',';
											
											var temp = "";
											if(record.data.locked==1) temp = "0";
											else if(record.data.locked==0) temp = "1";
											else temp="0";
											locks += temp + ',';
										} else {
//									 		_grid.store.remove(record);
										}
									}
									deleteForm.getForm().submit({
										waitTitle : '请稍候',
										waitMsg : '正在与服务器交互,请稍候...',
										url : 'lockExt.action',
										params:{id:ids,lock:locks},
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
				}
//				,
//				'->',// 右对齐
//				'-', '账号:',userid_q , '-', '姓名:',username_q , '-',
//				'公司:',companyid_q , '-', '部门:',deptid_q , '-', '职务:',dutyid_q , {
//					text : '查询',
//					tooltip : '查询',
//					iconCls : 'search',
//					handler : doSelect
//				}
				]
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

var searchPanel = new Ext.FormPanel({
	title:"检索条件",
	region:"east",
	split: true,
	height:60,
	width:200,
//	bodyStyle : 'padding:5px 5px 0',
	collapsible:true,
	labelAlign : "right",
	labelWidth : 40,
	buttonAlign : "center",
//	frame : true,	
//	baseCls : "search_background",
	items : [userid_q, username_q, companyid_q, deptid_q,dutyid_q],
	tbar : [{
		text : '查询',
		tooltip : '查询',
		iconCls : 'search',
		handler : doSelect
	},'-','->','请点击"查询"进行检索']
	
});

Ext.BLANK_IMAGE_URL = "../extjs/resources/images/default/s.gif";
Ext.onReady(function() {
	Ext.QuickTips.init();
	// Ext.form.Field.prototype.msgTarget = 'under';//错误提示显示的位置
	new Ext.Viewport({
		layout : 'border',
		items : [new myGridPanel(),searchPanel]
	});
	roleStore.load();
});
