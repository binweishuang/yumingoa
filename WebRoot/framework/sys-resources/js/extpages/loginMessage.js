
/**
 * 列表中的数据项
 */
var recordType = Ext.data.Record.create([{
	name : 'sysmsg'
}]);

/**
 * 列表中的数据
 */
var store = new Ext.data.Store({
	proxy : new Ext.data.HttpProxy({
		url : 'initExt.action?pages='+pageSize
	}),

	// set up the JsonReader
	reader : new Ext.data.JsonReader({
		root : 'root',
		totalProperty : 'totalProperty',
		id : 'messageid'
	}, recordType)
});

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
	this.columns = [new Ext.grid.RowNumberer(), {
		id : 'messageid',
		header : "公告信息",
		width : '100%',
		sortable : true,
		dataIndex : 'sysmsg'
	}];


	myGridPanel.superclass.constructor.call(this, {
		layout : 'fit',
		viewConfig : {
			emptyText : '没有记录'
		},
		bbar : new Ext.PagingToolbar({
			pageSize : pageSize,// 每页20条记录
			store : this.store,
			displayInfo : true,
			displayMsg : '显示第 {0} 条到 {1} 条记录，一共 {2} 条',
			emptyMsg : "没有记录"
		})
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
				title : '息',
				msg : "请至少选择一行,然后再进行操作!",
				buttons : Ext.MessageBox.OK,
				icon : Ext.MessageBox.INFO
			});
		} else {
			return records
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