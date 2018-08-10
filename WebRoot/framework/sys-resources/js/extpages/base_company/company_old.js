var key;//主键
Ext.BLANK_IMAGE_URL = "../extjs/resources/images/default/s.gif";

	/* 对应数据项 */
	var MyRecord = Ext.data.Record.create([{
		name : 'companyid'
	}, {
		name : 'companyname'
	}, {
		name : 'grade'
	}, {
		name : 'shortname'
	}]);
	/* 从action得到数据 */
	var dataProxy = new Ext.data.HttpProxy({
		url : 'queryExt.action'
	});
	
	/* json数据读取 */
	var theReader = new Ext.data.JsonReader({
		totalProperty : 'totalProperty', // 数据总条数，分页用
		root : 'root',
		id : 'companyid'
	}, MyRecord);
	
	/* 数据封装 */
	var store = new Ext.data.Store({
		proxy : dataProxy,
		reader : theReader
	});
	
	/* 设置行checkbox */
	var sm = new Ext.grid.CheckboxSelectionModel({
		dataIndex : 'companyid',
		singleSelect : false
	});
	
	var renderGrade = function(value) {
		if(value=="0") {
			return "省公司";
		} else if(value=="1") {
			return "市公司";
		} else if(value=="2") {
			return "县公司";
		} else {
			return "";
		}
	};
	//设置列
	var cm = new Ext.grid.ColumnModel([
		new Ext.grid.RowNumberer(),// 自动行号
		sm, {
			id : 'companyid',
			header : "公司编码",
			width : 200,
			sortable : true,
			dataIndex : 'companyid'
		}, {
			header : "公司名称",
			width : 200,
			sortable : true,
			dataIndex : 'companyname'
		}, {
			header : "公司级别",
			width : 200,
			sortable : true,
			dataIndex : 'grade',
			renderer : renderGrade
		}, {
			header : "公司简称",
			width : 200,
			sortable : true,
			dataIndex : 'shortname'
		// ,editor: new Ext.form.TextField({//可编辑grid输入框
		// allowBlank: false
		// })
		}
	]);
	
	//查询使用的表单
	var companyid_q = new Ext.form.TextField({
		name : 'companyid_q',
		width : 80
	});

	var companyname_q = new Ext.form.TextField({
		name : 'companyname_q',
		width : 80
	});
	
	var grades = [["省公司", "0"], ["市公司", "1"], ["县公司", "2"]];
	var gradeStore = new Ext.data.SimpleStore({
		fields : ["name", "code"],
		data : grades
	});
	var grade_q = new Ext.form.ComboBox({
		name : "grade_q",
		width : 80,
		displayField : "name",
		valueField : "code",
		store : gradeStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		emptyText : "请选择...",
		selectOnFocus : true
	});
	/* 可编辑grid */
	var grid = new Ext.grid.EditorGridPanel({		
		//renderTo : 'datagrid',//对就页面上的div
		layout : 'fit',
		store : store,
		cm : cm,
		sm : sm,
		stripeRows : true,
//		autoExpandColumn: 'shortname',
//		height : 400,
//		width : Ext.get("datagrid").getComputedWidth(),
		title : '公司信息',
		loadMask : {
			msg : '正在加载数据，请稍侯...'
		},
		clicksToEdit : 1,//点击几次可以修改
		bbar : new Ext.PagingToolbar({
			pageSize : 5,
			store : store,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		}),
		tbar : [{
			text : '添加',
			tooltip : '添加',
			iconCls : 'add',
			handler : doAdd
		},  '-', {
			text : '修改',
			tooltip : '修改',
			iconCls : 'edit',
			handler : doEdit
		},  '-', {
			text : '删除',
			tooltip : '删除',
			iconCls : 'del',
			handler : doDel
		},	'->',// 右对齐
			'-','公司编码:',companyid_q, 
			'-','公司名称:',companyname_q, 
			'-','公司级别:',grade_q,
		{
			text : '查询',
			tooltip : '查询',
			iconCls : 'search',
			handler : doSelect
		}]

	});
	store.load({params : {start : 0,limit : 5}});//数据加载，从0开始，取5条数据
	//grid.getSelectionModel().selectFirstRow();//选择grid的第一行
	grid.addListener('cellclick', cellclick);//为grid添加单击行的监听
	//选择行的方法，得到主键的值
	function cellclick(grid, rowIndex, columnIndex, e) {
		var record = grid.getStore().getAt(rowIndex); // Get the Record
		var fieldName = grid.getColumnModel().getDataIndex(columnIndex);
		key = record.get("companyid")
	}
	//在窗口发生变化时，对grid的大小进行调整
	window.onresize = function() {
		grid.setWidth(0);
		grid.setWidth(Ext.get("datagrid").getComputedWidth());
	}
	//查询方法
	function doSelect() {
		var companyid = companyid_q.getValue();
		var companyname = companyname_q.getValue();
		var grade = grade_q.getValue();
		store.reload({
			params : {
				start : 0,
				limit : 5,
				command : 'queryExt.action',
				companyid_q : companyid,//form表单值 : 值
				companyname_q : companyname,
				grade_q : grade
			}
		});
	}
	//删除方法
	function doDel() {
		var _grid = grid;
		var records = _grid.getSelected();
		alert(records.data.companyname);
		if (!key) {
			Ext.Msg.alert("警告", "请选择一行,然后再进行操作!");
		} else {
			Ext.Msg.confirm('提示', '您真的要删除吗?('+key+')', function(btn) {
				if (btn == "yes") {
					Ext.Ajax.request({
						url:"deleteExt.action",
						params:{id:key},
						success: function(response,options) {
//							var result = Ext.decode(response.responseText);
//							var result = response.responseText;
							var json = response.responseText;
							var o = eval("("+ json+ ")");
							if(o.success) {
//                        		Ext.Msg.alert('操作', '恭喜你,信息删除成功!');
								Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.INFO});
                        		store.reload();
							} else {
//								Ext.Msg.alert('错误',result);
								Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.ERROR});
							}
						}
					})
				} else {
				}
			});
		}
	}
	//添加方法
	var win;
	function doAdd() {
		Ext.QuickTips.init();
		Ext.form.Field.prototype.msgTarget = 'side';
		var baseCompanyForm = new Ext.FormPanel({
			// id:'form1',
			labelWidth : 75, // label settings here cascade unless overridden
			// url:'edit.jsp',
			frame : true,
			style : 'margin:auto',
			monitorValid : true,// 把有formBind:true的按钮和验证绑定
			title : '公司信息',
			bodyStyle : 'padding:5px 5px 0',
			width : 350,
			defaults : {
				width : 230
			},
			defaultType : 'textfield',
			labelAlign : "right",
			items : [{
				fieldLabel : '公司编码',
				id : 'companyid',
				name : 'companyid',
				allowBlank : false,
				blankText : "该项必须填写"
			}, {
				fieldLabel : '公司名称',
				id : 'companyname',
				name : 'companyname',
				allowBlank : false,
				blankText : "该项必须填写"
			}, {
				xtype : "combo",
				fieldLabel : '公司级别',
				id : 'grade_id',
				name : 'grade',
				hiddenName : "grade",
				displayField : "name",
				valueField : "code",
				store : gradeStore,
				typeAhead : true,
				mode : "local",
				triggerAction : "all",
				emptyText : "请选择...",
				selectOnFocus : true
			}, {
				fieldLabel : '公司简称',
				id : 'shortname',
				name : 'shortname'
			}, {
				fieldLabel : '电话',
				id : 'phone',
				name : 'phone'
			}, {
				fieldLabel : '地址',
				id : 'address',
				name : 'address'
			}, {
				fieldLabel : '传真',
				id : 'fax',
				name : 'fax'
			}, {
				fieldLabel : '邮编',
				id : 'postcode',
				name : 'postcode'
			}],
			buttons : [{
				text : '保存',
				formBind : true,
				handler : function() {
					if (baseCompanyForm.form.isValid()) {
						this.disabled = true;
						baseCompanyForm.form.doAction('submit', {
							url : 'insertExt.action',
							method : 'post',
							waitTitle : '请稍候',
							waitMsg : '正在与服务器交互,请稍候...',
							params : '',
							success : function() {
								// if (action.result.info == 'handlesuccess') {
								win.hide();
								Ext.Msg.alert('操作', '恭喜你,信息保存成功！');
								// Ext.Msg.show({title:'操作',msg:'操作成功！',buttons:Ext.Msg.OK});
//								document.location.reload();
								store.reload();
								this.disabled = false;
								// }
							},
							failure : function() {
								Ext.Msg.alert('操作', '很遗憾,信息保存失败！');
								this.disabled = false;
							}
						});
					}
				}
			}, {
				text : '重置',
				handler : function() {
					baseCompanyForm.form.reset();
				}
			}]
		});
		var tabs = new Ext.TabPanel({
			region : 'center',
			margins : '3 3 3 0',
			activeTab : 0,
			defaults : {
				autoScroll : true
			},
			items : [baseCompanyForm]
		});
		win = new Ext.Window({
			closable : true,
			width : 400,
			height : 350,
			modal : true,
			title : "添加公司信息",
			// border:true,
			closeAction : 'hide',
			plain : true,
			layout : 'border',
			items : [tabs]
		});
		win.show(this);
	}
	//修改方法
	var wina;
	function doEdit() {
		if (!key) {
			Ext.MessageBox.alert("警告", "请选择一行,然后再进行操作!");
		} else {
			Ext.QuickTips.init();
			Ext.form.Field.prototype.msgTarget = 'side';
			var baseCompanyForm = new Ext.FormPanel({
				// id:'form1',
				labelWidth : 75, // label settings here cascade unless
									// overridden
				// url:'edit.jsp',
				frame : true,
				style : 'margin:auto',
				// renderTo:"baseCompanyForm",
				monitorValid : true,// 把有formBind:true的按钮和验证绑定
				title : '公司信息',
				bodyStyle : 'padding:5px 5px 0',
				width : 350,
				defaults : {
					width : 230
				},
				defaultType : 'textfield',
				labelAlign : "right",
				items : [{
					fieldLabel : '公司编码',
					id : 'companyid',
					name : 'companyid',
					allowBlank : false,
					blankText : "该项必须填写"
				}, {
					fieldLabel : '公司名称',
					id : 'companyname',
					name : 'companyname',
					allowBlank : false,
					blankText : "该项必须填写"
				}, {
					xtype : "combo",
					fieldLabel : '公司级别',
					id : 'grade_id',
					name : 'grade',
					hiddenName : "grade",
					displayField : "name",
					valueField : "code",
					store : gradeStore,
					typeAhead : true,
					mode : "local",
					triggerAction : "all",
					emptyText : "请选择...",
					selectOnFocus : true
				}, {
					fieldLabel : '公司简称',
					id : 'shortname',
					name : 'shortname'
				}, {
					fieldLabel : '电话',
					id : 'phone',
					name : 'phone'
				}, {
					fieldLabel : '地址',
					id : 'address',
					name : 'address'
				}, {
					fieldLabel : '传真',
					id : 'fax',
					name : 'fax'
				}, {
					fieldLabel : '邮编',
					id : 'postcode',
					name : 'postcode'
				}],
				reader : new Ext.data.JsonReader( {
					root : 'root',
					successProperty : 'success',
					totalProperty : 'totalProperty',
					id : 'companyid'
				}, ['companyid', 'companyname', 'grade', 'shortname', 'phone', 'address', 'fax', 'postcode']),
				buttons : [{
					text : '保存',
					formBind : true,
					handler : function() {
						if (baseCompanyForm.form.isValid()) {
							this.disabled = true;
							baseCompanyForm.form.doAction('submit', {
								url : 'updateExt.action',
								method : 'post',
								waitTitle : '请稍候',
								waitMsg : '正在与服务器交互,请稍候...',
								params : '',
								success : function() {
									// if (action.result.info ==
									// 'handlesuccess') {
									wina.hide();
									Ext.Msg.alert('操作', '恭喜你,信息保存成功！');
									// Ext.Msg.show({title:'操作',msg:'操作成功！',buttons:Ext.Msg.OK});
//									document.location.reload();
									store.reload();
									this.disabled = false;
									// }
								},
								failure : function() {
									Ext.Msg.alert('操作', '很遗憾,信息保存失败！');
									this.disabled = false;
								}
							});
						}
					}
				}, {
					text : '重置',
					handler : function() {
						baseCompanyForm.form.reset();
					}
				}]
			});
			var tabs = new Ext.TabPanel({
				region : 'center',
				margins : '3 3 3 0',
				activeTab : 0,
				defaults : {
					autoScroll : true
				},
				items : [baseCompanyForm]
			});
			wina = new Ext.Window({
				closable : true,
				width : 400,
				height : 350,
				modal : true,
				title : "修改公司信息",
				// border:true,
				closeAction : 'hide',
				plain : true,
				layout : 'fit',
				items : [baseCompanyForm]
			});
			Ext.onReady(function() {
				baseCompanyForm.form.load({
					waitTitle : "请稍候",
					waitMsg : "正在加载表单数据，请稍候...",
					url : 'updateInitExt.action?id=' + key,
					success : function(action, form) {
					},
					failure : function(action, form) {
						Ext.Msg.alert('操作', '很遗憾,数据读取失败！');
//						alert("读取数据失败！");
					}
				});
			});
			wina.show(this);
		}
	}
Ext.onReady(function() {
	Ext.QuickTips.init();
	new Ext.Viewport( {
		layout : 'fit',
		items : grid
	});
});