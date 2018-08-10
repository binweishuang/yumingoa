var rolePageSize = 10;//查看包含人员窗口中的分页大小
var companyStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : '../baseUser/queryCompanyExt.action'
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
		url : '../baseUser/queryDeptExt.action'
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'deptid',
		fields : ['deptid','deptname']
	})
});
/**
 * 页面中的下拉列表，动态数据
 */
var projectStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'queryProjectExt.action'
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'projectid',
		fields : ['projectid','projectname']
	})
});
/**
 * 列表中的数据项
 */
var recordType = Ext.data.Record.create([{
	name : 'roleid'
}, {
	name : 'rolename'
}, {
	name : 'projectid'
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
		id : 'roleid'
	}, recordType)
});

/*-------------------开始新增窗体--------------------*/
/**
 * 定义新增form
 */
var addFormPanel = function() {
	this.roleid = {
		xtype : 'textfield',
		fieldLabel : '角色编码',
		id : 'roleid',
		name : 'roleid',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.rolename = {
		xtype : 'textfield',
		fieldLabel : '角色名称',
		id : 'rolename',
		name : 'rolename',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.projectid = {
		xtype : "combo",
		fieldLabel : '所属项目',
		id : 'projectid_id',
		name : 'projectid',
		hiddenName : "projectid",
		displayField : "projectname",
		valueField : "projectid",
		store : projectStore,
		typeAhead : true,
//		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	};
	this.resources = {
		xtype : 'hidden',
		id : 'resources',
		name : 'resources'
	};
	this.resourcesname = {
		xtype : 'textarea',
		fieldLabel : '已选权限',
		id : 'resourcesname',
		name : 'resourcesname',
		height : 180
	};
	//这是下拉多选树，这里暂时不用
//	this.resources = new Ext.ux.ComboBoxCheckTree({
//		fieldLabel : '权限列表',
//		width : 300,
//		height : 400,
//		tree : {
//			xtype:'treepanel',
//			height:100,
//			useArrows:true,//是否使用箭头图标
//			checkModel: 'cascade',   //cascade selection
//			onlyLeafCheckable: false,//all nodes with checkboxes
//			animate: true,
//			rootVisible: true,
//			autoScroll:true,
//			loader: new Ext.tree.TreeLoader({dataUrl:'queryResourcesExt.action',   
//	        baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
//	   	 	root : new Ext.tree.AsyncTreeNode({id:'KF',text:'root'})
//		},		
//		// define which node's value will be submited
//		//all
//		//folder
//		//leaf
//		selectValueModel:'leaf'		
//	});
	addFormPanel.superclass.constructor.call(this, {
		title : '角色信息',
		bodyStyle : 'padding:5px 5px 0',
		frame : true,
		monitorValid : true,
		labelAlign : "right",
		defaults : {
			width : 200
		},
		items : [this.roleid, this.rolename, this.projectid,this.resources,this.resourcesname]
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
		title : '角色信息',
		width : 400,
		height : 400,
		resizable : true,
		plain : false,
		border : false,
		modal : true,
		autoScroll : true,
		layout : 'fit',
		// closeAction : 'hide',//因为IE的问题，默认的关闭命令为distroy
		items : [{
			xtype:'tabpanel',
			border: false,
            activeTab: 0,
            region: "center",
            //height: '200',
            tabPosition: "top",
            frame: true,
            items: [this.formPanel,
            {
            	xtype:'treepanel',
            	id : 'roleTree',
            	title: '权限信息',
			    useArrows:true,//是否使用箭头图标
			    checkModel: 'cascade',   //cascade selection
				onlyLeafCheckable: false,//all nodes with checkboxes
				animate: true,
				rootVisible: true,
				autoScroll:true,
				loader: new Ext.tree.TreeLoader({dataUrl:'queryResourcesExt.action',   
			    baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
			 	root : new Ext.tree.AsyncTreeNode({id:'-1',text:'BaseV3'})
            }]
		}],

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
	this.roleid = {
		xtype : 'textfield',
		fieldLabel : '角色编码',
		id : 'roleid',
		name : 'roleid',
		allowBlank : false,
		blankText : "该项必须填写",
		readOnly : true
	};
	this.rolename = {
		xtype : 'textfield',
		fieldLabel : '角色名称',
		id : 'rolename',
		name : 'rolename',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.projectid = {
		xtype : "combo",
		fieldLabel : '所属项目',
		id : 'projectid_id',
		name : 'projectid',
		hiddenName : "projectid",
		displayField : "projectname",
		valueField : "projectid",
		store : projectStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		readOnly : true,
		emptyText : "请选择...",
		selectOnFocus : true
	};
	this.resources = {
		xtype : 'hidden',
		id : 'resources',
		name : 'resources'
	}
	this.resourcesname = {
		xtype : 'textarea',
		fieldLabel : '已选权限',
		id : 'resourcesname',
		name : 'resourcesname',
		height : 200
	}
	// 调用formpanel的父类的构造器
	editFormPanel.superclass.constructor.call(this, {
		title : '角色信息',
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
			id : 'roleid'
		}, ['roleid', 'rolename', 'projectid','resources','resourcesname']),
		items : [this.roleid, this.rolename, this.projectid, this.resources, this.resourcesname]
	});
}
/**
 * 编辑window
 */
var editWindow = function(roleid) {
	var _win = this;
	this.formPanel = new editFormPanel();
	var _form = this.formPanel.getForm();
	var roleUrl = "queryResourcesExt.action?id="+roleid;
	editWindow.superclass.constructor.call(this, {
		title : '角色信息',
		width : 400,
		height : 400,
		resizable : true,
		plain : false,
		border : false,
		modal : true,
		autoScroll : true,
		layout : 'fit',
		// closeAction : 'hide',//因为IE的问题，默认的关闭命令为distroy
		items : [{
			xtype:'tabpanel',
			border: false,
            activeTab: 0,
            region: "center",
            //height: '200',
            tabPosition: "top",
            frame: true,
            items: [this.formPanel,
            {
            	xtype:'treepanel',
            	id : 'roleTree',
            	title: '权限信息',
			    useArrows:true,//是否使用箭头图标
			    checkModel: 'cascade',   //cascade selection
				onlyLeafCheckable: false,//all nodes with checkboxes
				animate: true,
				rootVisible: true,
				autoScroll:true,
				loader: new Ext.tree.TreeLoader({dataUrl:roleUrl,   
			    	baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
			 	root : new Ext.tree.AsyncTreeNode({id:'-1',text:'BaseV3'})
            }]
		}],

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
		id : 'roleid',
		header : "角色编码",
		width : 200,
		sortable : true,
		dataIndex : 'roleid'
	}, {
		header : "角色名称",
		width : 200,
		sortable : true,
		dataIndex : 'rolename'
	}, {
		header : "所属项目",
		width : 200,
		sortable : true,
		dataIndex : 'projectid'
//		renderer : renderGrade
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
						Ext.getCmp("roleTree").on("check", function(node, checked){
							if(!node.isLeaf()){ return false; }
						    select("resources", "resourcesname", node, checked)
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
							var editWin = new editWindow(record.data.roleid);//加了参数，是为了让tree的地址带参数 为了在IE中能正常显示window,在这里new一个window,因为在关闭window时是使用distroy
							editWin.show();
							Ext.getCmp("roleTree").on("check", function(node, checked){
								if(!node.isLeaf()){ return false; }
							    select("resources", "resourcesname", node, checked)
							});
							var record = _grid.getSelected();
							editWin.record = record;
							editWin.loadData(record.data.roleid);
							roleidForTree = record.data.roleid;
							projectStore.load();//手动的从后台读取数据，这是因为在读取时要显示出下拉列表所选中的内容
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
										if (record.data.roleid) {
											ids += record.data.roleid + ',';
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
				},
				'-',
				{
					text : '包含用户',
					tooltip : '此角色包含的用户,可以继续添加用户',
					iconCls : 'users',
					handler : function() {
						var record = _grid.getSelected();
						if (record != null) {
							var userWin = new userWindow(record.data.roleid,record.data.rolename);// 为了在IE中能正常显示window,在这里new一个window,因为在关闭window时是使用distroy
							userWin.show();
							Ext.getCmp("companyid_q").on('select',function(comboBox){
								var value = comboBox.getValue();
								deptStore.load({params:{id:value}});
							});
							Ext.getCmp("roleid").setValue(record.data.roleid);//为了窗体查询，所以把父窗体中的值放在打开窗口的隐藏域里
							roleUserStore.load({
								params : {
									start : 0,
									limit : rolePageSize,
									id : record.data.roleid
								}
							});
							noRoleUserStore.load({
								params : {
									start : 0,
									limit : rolePageSize,
									id : record.data.roleid
								}
							});

						}
					}
				}
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



Ext.BLANK_IMAGE_URL = "../extjs/resources/images/default/s.gif";
Ext.onReady(function() {
	Ext.QuickTips.init();
	// Ext.form.Field.prototype.msgTarget = 'under';//错误提示显示的位置
	new Ext.Viewport({
		layout : 'fit',
		items : new myGridPanel()
	});
});

// 处理选择后的回填，传入的参数为页面上文本域的id，比如：sp_id:回填部门编号的文本域id；sp:回填部门名称的文本域id,node，checked是tree的check事件回调函数的默认参数
function select(id_id, name_id, node, checked) {
    var val_d = Ext.getDom(id_id).value;
    var val_d_n = Ext.getDom(name_id).value;
    
    if (checked) {// 如果选中就累加，否则取消就减掉  
            
        if (val_d == null || val_d == '' || val_d == '0') {
            if (name_id == 'su') {//如果是选择人员，则只写叶子节点
                if (node.leaf) {
                    Ext.getDom(id_id).value = node.id;
                    Ext.getDom(name_id).value = node.text;
                }
            } else {
            	Ext.getDom(id_id).value = node.id;
                    Ext.getDom(name_id).value = node.text;
            }
        } else {
            if (val_d.indexOf(node.id) < 0) {
                Ext.getDom(id_id).value = val_d + ',' + node.id;
                Ext.getDom(name_id).value = val_d_n + ',' +
                node.text;
            }            
        }
    } else {// 减去已有的而又不选的
        if (val_d != null && val_d != '' && val_d != '0') {
            var new_val_d = '';
            var new_val_d_n = '';
            if (val_d.indexOf(node.id) > -1) {// 如果文本框不为空并且有重复的，则相减
                var d_a = val_d.split(',');
                var d_n_a = val_d_n.split(',');
                for (i = 0; i < d_a.length; i++) {
                    if (d_a[i] != node.id) {
                        if (new_val_d == null || new_val_d == '') {
                            new_val_d = d_a[i];
                            new_val_d_n = d_n_a[i];
                        } else {
                            new_val_d = new_val_d + ',' +
                            d_a[i];
                            new_val_d_n = new_val_d_n + ',' +
                            d_n_a[i];
                        }
                    }                    
                }
                Ext.getDom(id_id).value = new_val_d;
                Ext.getDom(name_id).value = new_val_d_n;
                
            }
        }        
    }    
};