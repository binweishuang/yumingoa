
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
 * 静态Array数据，给下拉列表使用
 * 
 * @type {Array}
 */
var target = [["工作区内", "mainFrame"], ["新窗口", "_blank"]];
/**
 * 静态数据存储器
 */
var targetStore = new Ext.data.SimpleStore({
	fields : ["name", "code"],
	data : target
});
var unvisible = [["可用", "0"], ["不可用", "1"]];
/**
 * 静态数据存储器
 */
var unvisibleStore = new Ext.data.SimpleStore({
	fields : ["name", "code"],
	data : unvisible
});
var istop = [["否", "0"], ["是", "1"]];
/**
 * 静态数据存储器
 */
var istopStore = new Ext.data.SimpleStore({
	fields : ["name", "code"],
	data : istop
});

/*-------------------开始新增窗体--------------------*/
/**
 * 定义新增form
 */
var addFormPanel = function() {
	this.title_textfield = {
		xtype : 'textfield',
		fieldLabel : '标题',
		id : 'title',
		name : 'title',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.resourceid = {
		xtype : 'textfield',
		fieldLabel : '编码',
		id : 'resourceid',
		name : 'resourceid',
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.url = {
		xtype : 'textarea',
		fieldLabel : '对应URL',
		id : 'url',
		name : 'url',
		height : 100
	};
	this.target = {
		xtype : "combo",
		fieldLabel : '页面打开区域',
		id : 'target_id',
		name : 'target',
		hiddenName : "target",
		displayField : "name",
		valueField : "code",
		store : targetStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		emptyText : "请选择...",
		selectOnFocus : true,
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.unvisible = {
		xtype : "combo",
		fieldLabel : '是否可用',
		id : 'unvisible_id',
		name : 'unvisible',
		hiddenName : "unvisible",
		displayField : "name",
		valueField : "code",
		store : unvisibleStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		emptyText : "请选择...",
		selectOnFocus : true,
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.istop = {
		xtype : "combo",
		fieldLabel : '是否一级菜单',
		id : 'istop_id',
		name : 'istop',
		hiddenName : "istop",
		displayField : "name",
		valueField : "code",
		store : istopStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		emptyText : "请选择...",
		selectOnFocus : true,
		allowBlank : true,
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
		selectOnFocus : true,
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.sortid = {
		xtype : 'numberfield',
		fieldLabel : '序号',
		id : 'sortid',
		name : 'sortid',
		allowBlank : false,
		blankText : "该项必须填写,并且必须为整数"
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
		fieldLabel: '允许访问的角色'
	};
	this.parentid = new Ext.ux.ComboBoxCheckTree({
		fieldLabel : '父权限',
		id : 'parentid_id',
		name : 'parentid',
		hiddenName : 'parentid',
		allowBlank : false,
		blankText : "该项必须填写",
		width : 300,
		height : 300,
		tree : panentTree = new Ext.tree.TreePanel({
			id : 'tree_parentid',
			height:100,
			useArrows:true,//是否使用箭头图标
			checkModel: 'single',   //cascade selection
			onlyLeafCheckable: false,//all nodes with checkboxes
			animate: true,
			rootVisible: true,
			autoScroll:true,
			loader: new Ext.tree.TreeLoader({dataUrl:'queryParentTreeExt.action',   
	        baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI } }),
	   	 	root : new Ext.tree.AsyncTreeNode({id:'-1',text:'BaseV3'})

		}),	
		// define which node's value will be submited
		//all
		//folder
		//leaf
		selectValueModel:'all'	
	});
	this.restype = {
		fieldLabel : '权限类型',
		xtype : 'textfield',
		id : 'restype',
		name : 'restype',
		readOnly : true
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
		items : [this.title_textfield, this.resourceid, this.url, this.target, this.unvisible, this.istop, this.projectid, this.sortid, this.roles,this.parentid,this.restype]
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
		title : '菜单信息',
		width : 400,
		height : 450,
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
							
							// 因为在IE下使用有问题，所以只能distroy窗体，打开时new一个新的窗体
							if (_win) {
								_win.destroy();
							}
							tree.root.reload();//树重新加载
							Ext.getCmp("resources").setValue("");
							Ext.getCmp("resourcesname").setValue("");
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
	this.title_textfield = {//因为和form的title属性重名
		xtype : 'textfield',
		fieldLabel : '标题',
		id : 'title',
		name : 'title',
		allowBlank : false,
		blankText : "该项必须填写"
	};
	this.resourceid = {
		xtype : 'textfield',
		fieldLabel : '编码',
		id : 'resourceid',
		name : 'resourceid',
		readOnly : true,
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.url = {
		xtype : 'textarea',
		fieldLabel : '对应URL',
		id : 'url',
		name : 'url',
		height : 100
	};
	this.target = {
		xtype : "combo",
		fieldLabel : '页面打开区域',
		id : 'target_id',
		name : 'target',
		hiddenName : "target",
		displayField : "name",
		valueField : "code",
		store : targetStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		emptyText : "请选择...",
		selectOnFocus : true,
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.unvisible = {
		xtype : "combo",
		fieldLabel : '是否可用',
		id : 'unvisible_id',
		name : 'unvisible',
		hiddenName : "unvisible",
		displayField : "name",
		valueField : "code",
		store : unvisibleStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		emptyText : "请选择...",
		selectOnFocus : true,
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.istop = {
		xtype : "combo",
		fieldLabel : '是否一级菜单',
		id : 'istop_id',
		name : 'istop',
		hiddenName : "istop",
		displayField : "name",
		valueField : "code",
		store : istopStore,
		typeAhead : true,
		mode : "local",
		triggerAction : "all",
		emptyText : "请选择...",
		selectOnFocus : true,
		allowBlank : true,
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
		selectOnFocus : true,
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.sortid = {
		xtype : 'numberfield',
		fieldLabel : '序号',
		id : 'sortid',
		name : 'sortid',
		allowBlank : false,
		blankText : "该项必须填写,并且必须为整数"
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
		fieldLabel: '允许访问的角色'
	};
	this.parentid = {
		xtype : 'textfield',
		fieldLabel : '父权限',
		id : 'parentid',
		name : 'parentid',
		readOnly : true,
		allowBlank : true,
		blankText : "该项必须填写"
	};
	this.restype = {
		fieldLabel : '权限类型',
		xtype : 'textfield',
		id : 'restype',
		name : 'restype',
		readOnly : true
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
			id : 'resourceid'
		}, ['title', 'resourceid', 'url', 'target', 'unvisible', 'istop', 'projectid', 'sortid', 'roles','parentid','restype']),
		items : [this.title_textfield, this.resourceid, this.url, this.target, this.unvisible, this.istop, this.projectid, this.sortid, this.roles,this.parentid,this.restype]
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
		title : '菜单信息',
		width : 400,
		height : 450,
		resizable : true,
		plain : false,
		border : false,
		modal : true,
		autoScroll : true,
		layout : 'fit',
		// closeAction : 'hide',//因为IE的问题，默认的关闭命令为destory
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
							
							// 因为在IE下使用有问题，所以只能distroy窗体，打开时new一个新的窗体
							if (_win) {
								_win.destroy();
							}
							tree.root.reload();//树重新加载
							Ext.getCmp("resources").setValue("");
							Ext.getCmp("resourcesname").setValue("");
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
	loadData : function(url) {
//		var url = 'updateInitExt.action';
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
/**
 * editWindow继承Ext.Window,并重载loadData方法
 * 
 * @class editWindow
 * @extends Ext.Window
 */
Ext.extend(editWindow, Ext.Window, {
	loadData : function(url) {
		this.formPanel.loadData(url);
	}
});
/*-------------------结束编辑窗体--------------------*/
var Tree = Ext.tree;//定义树

var treeLoader = new Tree.TreeLoader({
    dataUrl:"queryExt.action",
    baseAttrs: { uiProvider: Ext.ux.TreeCheckNodeUI }
});
/**
 * 左边树窗体
 */
var tree = new Tree.TreePanel({
//  	el:'tree-div',
    useArrows:true,//是否使用箭头图标
    split: true,
 	/*
 	 * single : 单选
 	 * multiple : 多选
 	 * cascade : 级联选中所有父结点和子结点
 	 * parentCascade : 级联选中所有父结点
 	 * childCascade : 级联选中所有子结点
 	 */
	checkModel: 'cascade',   //cascade selection
	onlyLeafCheckable: false,//all nodes with checkboxes
	animate: true,
	rootVisible: true,
	autoScroll:true,
	ddScroll:true,
	containerScroll : true,
    loader: treeLoader,
    region:"west",
    width : 300
});
//tree.render();//如果tree使用el，则要用这个方法来渲染
/**
 * 设置树的根节点
 */
var root = new Tree.AsyncTreeNode({
    text: 'BaseV3',
    draggable:false,
    id:'-1'
});
tree.setRootNode(root);
root.expand();

//为树加上click的方法
tree.on('click',function(node, e){  
//	if(!node.isLeaf()){ return false; }
	e.stopEvent();
	projectStore.load();
	var editWin = new editWindow();
	editWin.show();
	editWin.loadData(node.attributes.hrefTarget);//此时把hrefTarget中的url放到form的url中当加载地址
});

tree.on('check',function(node, checked) {
	if(!node.isLeaf()){ return false; }
	select("resources", "resourcesname", node, checked);
});
var toolBar = new Ext.Toolbar({
	items:[{
		text : '添加',
		tooltip : '添加',
		iconCls : 'add',
		handler : function() {
			var addWin = new addWindow();// 为了在IE中能正常显示window,在这里new一个window,因为在关闭window时是使用distroy
			addWin.formPanel.getForm().reset();
			addWin.show();
			Ext.getCmp("tree_parentid").on('check',function(node, checked) {
				Ext.getCmp("restype").setValue(node.attributes.hrefTarget);
			});
		}
	},
	'-',
	{
		text : '删除',
		tooltip : '删除',
		iconCls : 'del',
		handler : function() {
			Ext.MessageBox.confirm('提示', '您真的确定删除这此权限吗?', function(btn) {
				if (btn == 'yes') {
					var ids = Ext.getCmp("resources").getValue();
					//这里使用ajax调用
					Ext.Ajax.request({
						url:"deleteExt.action",
						params:{id:ids},
						success: function(response,options) {
//							var result = Ext.decode(response.responseText);
//							var result = response.responseText;
							var json = response.responseText;
							var o = eval("("+ json+ ")");
							if(o.success) {
//                        		Ext.Msg.alert('操作', '恭喜你,信息删除成功!');
								Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.INFO});
								tree.root.reload();//树重新加载
								Ext.getCmp("resources").setValue("");
								Ext.getCmp("resourcesname").setValue("");
                        		
							} else {
//								Ext.Msg.alert('错误',result);
								Ext.MessageBox.show( {title : '操作',msg : o.message,buttons : Ext.MessageBox.OK,icon : Ext.MessageBox.ERROR});
							}
						}
					})
				}
			});
		}
	}]
});  
var conentPanel = new Ext.Panel({
	region:"center",
	items:[{
		xtype:"panel",
       	title:"已选的权限",
       	scripts:true,
       	layout : 'form',
       	labelAlign : "right",
		region : 'center',
		items : [{
			xtype : 'hidden',
			fieldLabel : '权限编码',
			id : 'resources',
			name : 'resources',
			width : '300',
			height : '200'
		},{
			xtype : 'textarea',
			fieldLabel : '权限名称',
			id : 'resourcesname',
			name : 'resourcesname',
			width : '300',
			height : '400' 
		}]
	}]
});
Ext.BLANK_IMAGE_URL = "../extjs/resources/images/default/s.gif";
Ext.onReady(function() {	
	Ext.QuickTips.init();
	// Ext.form.Field.prototype.msgTarget = 'under';//错误提示显示的位置
	new Ext.Viewport({
		enableTabScroll:true,
		layout:"border",
		layoutConfig : {
			animate : true
		},
		items:[
			{
				xtype : 'panel',
				region : 'north',
			    collapsible:true,
			    split: false,
			    border: false,
			    height:26,
				items : toolBar
			},tree,
			conentPanel
		]
	});
	roleStore.load();
});
// 处理选择后的回填，传入的参数为页面上文本域的id，比如：sp_id:回填部门编号的文本域id；sp:回填部门名称的文本域id,node，checked是tree的check事件回调函数的默认参数
function select(id_id, name_id, node, checked){
    var val_d = Ext.getDom(id_id).value;
    var val_d_n = Ext.getDom(name_id).value;
    
    if (checked) {// 如果选中就累加，否则取消就减掉        
        if (val_d == null || val_d == '' || val_d == '0') {
            if (name_id == 'su') {//如果是选择人员，则只写叶子节点
                if (node.leaf) {
                    Ext.getDom(id_id).value = node.id;
                    Ext.getDom(name_id).value = node.text;
                }
            }
            else {
            	Ext.getDom(id_id).value = node.id;
                    Ext.getDom(name_id).value = node.text;
            }
        }
        else {
            if (val_d.indexOf(node.text) < 0) {
                Ext.getDom(id_id).value = val_d + ',' + node.id;
                Ext.getDom(name_id).value = val_d_n + ',' +
                node.text;
            }
            
        }
    }
    else {// 减去已有的而又不选的
        if (val_d != null && val_d != '' && val_d != '0') {
            var new_val_d = '';
            var new_val_d_n = '';
            if (val_d.indexOf(node.id) > -1) {// 如果文本框不为空并且有重复的，则相减
                var d_a = val_d.split(',');
                var d_n_a = val_d_n.split(',');
                for (i = 0; i < d_a.length; i++) {
                    if (d_a[i] != node.id) {
                        if (new_val_d == null ||
                        new_val_d == '') {
                            new_val_d = d_a[i];
                            new_val_d_n = d_n_a[i];
                        }
                        else {
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