/*------------------开始包含用户窗口------------------------*/
var roleUserStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'queryIncludeUserExt.action?pages='+rolePageSize
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'userid'
	}, new Ext.data.Record.create([{
			name : 'userid'
		}, {
			name : 'username'
		}, {
			name : 'companyname'
		}])
	)
});
var roleGridPanel = function() {
	var _grid = this;
	this.store = roleUserStore;
	//为了在翻页后，查询条件还有用，必须使用下面的内容
	this.store.on("beforeload",function(t){
        t.baseParams = {
            id : Ext.getCmp("roleid").getValue()
        };
    });
//	this.store.load({
//		params : {
//			start : 0,
//			limit : 10
//		}
//	});//此时不加载，是因为在加载时需要为这个store传递变量参数
	var sm = new Ext.grid.CheckboxSelectionModel({
		singleSelect : false
	});
	this.columns = [new Ext.grid.RowNumberer(), {
		id : 'userid',
		header : "用户账号",
		width : 150,
		sortable : true,
		dataIndex : 'userid'
	}, {
		header : "用户姓名",
		width : 150,
		sortable : true,
		dataIndex : 'username'
	}, {
		header : "所属公司",
		width : 150,
		sortable : true,
		dataIndex : 'companyname'
	}, sm];
	roleGridPanel.superclass.constructor.call(this, {
		id : 'roleGrid',
		title : '包含的用户',
		layout : 'fit',
		sm : sm,
		viewConfig : {
			emptyText : '没有记录'
		},
		bbar : new Ext.PagingToolbar({
			pageSize : rolePageSize,// 每页20条记录
			store : this.store,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		}),
		tbar : [
				{
					text : '删除',
					tooltip : '从此角色下删除用户',
					iconCls : 'del',
					handler : function() {
						var records = _grid.getSelections();
//						var records = _grid.getSelected();
						if (records) {
							Ext.MessageBox.confirm('提示', '您真的确定为这些用户删除此角色吗?', function(btn) {
								if (btn == 'yes') {
									var ids = "";
									var rid = Ext.getCmp("roleid").getValue();
//									var ids = records.data.userid;
									for (var i = 0; i < records.length; i++) {
										var record = records[i];
										if (record.data.userid) {
											ids += record.data.userid + ',';
										} else {
//									 		_grid.store.remove(record);
										}
									}	
									//这里使用ajax调用
									Ext.Ajax.request({
										url:"deleteUserExt.action",
										params:{id:ids,roleid:rid},
										success: function(response,options) {
				//							var result = Ext.decode(response.responseText);
				//							var result = response.responseText;
											var json = response.responseText;
											var o = eval("("+ json+ ")");
											if(o.success) {
				//                        		Ext.Msg.alert('操作', '恭喜你,信息删除成功!');
												Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.INFO});
				                        		roleUserStore.reload({
													params : {
														start : 0,
														limit : rolePageSize,
														id : rid
													}
												});
												noRoleUserStore.reload({
													params : {
														start : 0,
														limit : rolePageSize,
														id : rid
													}
												});
											} else {
				//								Ext.Msg.alert('错误',result);
												Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.ERROR});
											}
										}
									})
								}								
							});
						}
					}
				},new Ext.form.Hidden({//加这个隐藏域是为了在后面的查询中，从这个里面取到父窗体的带过来的值
					id : 'roleid',
					name : 'roleid'
				})
				]
	});
}
Ext.extend(roleGridPanel, Ext.grid.GridPanel, {
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

var noRoleUserStore = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'queryUncludeUserExt.action?pages='+rolePageSize
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'userid'
	}, new Ext.data.Record.create([{
			name : 'userid'
		}, {
			name : 'username'
		}, {
			name : 'companyname'
		}])
	)
});
var noRoleGridPanel = function() {
	var _grid = this;
	this.store = noRoleUserStore;
	//为了在翻页后，查询条件还有用，必须使用下面的内容
	this.store.on("beforeload",function(t){
        t.baseParams = {
            id : Ext.getCmp("roleid").getValue(),
            userid_q : Ext.getCmp("userid_q").getValue(),
            username_q : Ext.getCmp("username_q").getValue(),
            companyid_q : Ext.getCmp("companyid_q").getValue(),
            deptid_q : Ext.getCmp("deptid_q").getValue()
        };
    });
//	this.store.load({
//		params : {
//			start : 0,
//			limit : 10
//		}
//	});// 默认加载5条记录，从第1条开始
	var sm = new Ext.grid.CheckboxSelectionModel({
		singleSelect : false
	});
	this.columns = [new Ext.grid.RowNumberer(), {
		id : 'userid',
		header : "用户账号",
		width : 150,
		sortable : true,
		dataIndex : 'userid'
	}, {
		header : "用户姓名",
		width : 150,
		sortable : true,
		dataIndex : 'username'
	}, {
		header : "所属公司",
		width : 150,
		sortable : true,
		dataIndex : 'companyname'
	}, sm];
	noRoleGridPanel.superclass.constructor.call(this, {
		id : 'noRoleGrid',
		title : '未包含的用户',
		layout : 'fit',
		sm : sm,
		viewConfig : {
			emptyText : '没有记录'
		},
		bbar : new Ext.PagingToolbar({
			pageSize : rolePageSize,// 每页20条记录
			store : this.store,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		}),
		tbar : [
				{
					text : '添加',
					tooltip : '向此角色下添加用户',
					iconCls : 'add',
					handler : function() {
						var records = _grid.getSelections();
//						var records = _grid.getSelected();
						if (records) {
							Ext.MessageBox.confirm('提示', '您真的确定为这些用户添加此角色吗?', function(btn) {
								if (btn == 'yes') {
									var ids = "";
									var rid = Ext.getCmp("roleid").getValue();
									for (var i = 0; i < records.length; i++) {
										var record = records[i];
										if (record.data.userid) {
											ids += record.data.userid + ',';
										} else {
//									 		_grid.store.remove(record);
										}
									}									
								
								//这里使用ajax调用
									Ext.Ajax.request({
										url:"insertUserExt.action",
										params:{id:ids,roleid:rid},
										success: function(response,options) {
				//							var result = Ext.decode(response.responseText);
				//							var result = response.responseText;
											var json = response.responseText;
											var o = eval("("+ json+ ")");
											if(o.success) {
				//                        		Ext.Msg.alert('操作', '恭喜你,信息删除成功!');
												Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.INFO});
				                        		roleUserStore.reload({
													params : {
														start : 0,
														limit : rolePageSize,
														id : rid
													}
												});
												noRoleUserStore.reload({
													params : {
														start : 0,
														limit : rolePageSize,
														id : rid
													}
												});
											} else {
				//								Ext.Msg.alert('错误',result);
												Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.ERROR});
											}
										}
									})
								}
							});
						}
					}
				}
				,'->',// 右对齐
				'-', '账号:', new Ext.form.TextField({
					id : 'userid_q',
					name : 'userid_q',
					width : 80
				}), 
				'-', '姓名:', new Ext.form.TextField({
					id : 'username_q',
					name : 'username_q',
					width : 80
				}), '-', '公司:', new Ext.form.ComboBox({
					id : 'companyid_q',
					name : "companyid_q",
					width : 80,
					displayField : "companyname",
					valueField : "companyid",
					store : companyStore,
					typeAhead : true,
				//	mode : "local",
					triggerAction : "all",
					emptyText : "请选择...",
					readOnly : true,
					selectOnFocus : true
				}), 
				'-', '部门:', new Ext.form.ComboBox({
					id : 'deptid_q',
					name : "deptid_q",
					width : 80,
					displayField : "deptname",
					valueField : "deptid",
					store : deptStore,
					typeAhead : true,
					mode : "local",
					triggerAction : "all",
					emptyText : "请选择...",
					readOnly : true,
					selectOnFocus : true
				}), 
				'-', {
					text : '查询',
					tooltip : '查询',
					iconCls : 'search',
					handler : function doSelect() {
								var userid = Ext.getCmp("userid_q").getValue();
								var username = Ext.getCmp("username_q").getValue();
								var companyid = Ext.getCmp("companyid_q").getValue();
								var deptid = Ext.getCmp("deptid_q").getValue();
								var roleid = Ext.getCmp("roleid").getValue();
								noRoleUserStore.reload({
									params : {// params表示要传递到后台的参数
										start : 0,
										limit : rolePageSize,
										command : 'queryUserExt.action',
										userid_q : userid,// form表单值 : 值
										username_q : username,
										companyid_q : companyid,
										deptid_q : deptid,
										id : roleid
									}
								});
							}
				}]
	});
}
Ext.extend(noRoleGridPanel, Ext.grid.GridPanel, {
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

var userWindow = function(roleid,rolename) {
	var _win = this;
	this.roleGrid = new roleGridPanel();
	this.noRoleGrid = new noRoleGridPanel();
	userWindow.superclass.constructor.call(this, {
		title : '角色--'+rolename+'('+roleid+')包含的用户',
		width : 600,
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
            items: [this.roleGrid,this.noRoleGrid]
		}],
		buttons : [{
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
Ext.extend(userWindow, Ext.Window);

/*------------------结束包含用户窗口------------------------*/