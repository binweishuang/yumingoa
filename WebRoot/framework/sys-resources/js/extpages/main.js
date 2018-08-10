Ext.BLANK_IMAGE_URL = getFrameworkPath()+'/extjs/resources/images/default/s.gif';
/**
 * 头
 */
var titlePanel = new Ext.Panel({
//	title:"Base",
//	contentEl: 'north-div',
//	contentEl: 'header_000',
	region:"north",
	height:53
//	collapsible:true,
//	html:'<iframe id="otheriframe" scrolling="auto" frameborder="0" width="100%" height="100%" src="top.jsp"></iframe>'
});
/**
 * 主窗体
 */
var conentPanel = new Ext.TabPanel({
	region:"center",
	activeTab:0,
	items:[{
		xtype:"panel",
       	title:"首页",
       	scripts:true,
       	html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src="ext.action"></iframe>'
	}]
});

var Tree = Ext.tree;//定义树
/**
 * 左边树窗体
 */
var tree = new Tree.TreePanel({
//  el:'tree-div',
    useArrows:true,//是否使用箭头图标
    autoScroll:true,
    animate:true,
    enableDD:false,//是否可以拖拽
    containerScroll: true, 
    //--以下这段是直接使用Panel的参数，这样tree就可以直接放在Viewport的items中了
    region:"west",
    width:200,
    collapsible:true,
    title:"菜单",
    split: true,
    border: true,
    //---------------------------
    loader: new Tree.TreeLoader({
        dataUrl:"indexJsonAction.action"
    })
});
//tree.render();//如果tree使用el，则要用这个方法来渲染
/**
 * 设置树的根节点
 */
var root = new Tree.AsyncTreeNode({
    text: 'KDF',
    draggable:false,
    id:'EXT'
});
tree.setRootNode(root);
root.expand();
//tree.expandAll();//展开所有节点
/**
 * 添加tabpanel的方法，用于在点击树时，在主窗体中添加一个tab
 * @param {} id
 * @param {} url
 */
var addTab = function(id,url){   
    conentPanel.add({id:id,title:id,   
//		autoLoad:url,   
    	html:'<iframe scrolling="auto" frameborder="0" width="100%" height="100%" src=' +url+'></iframe>',   
    	scripts:true,closable:true}).show();   
}  
//为树加上click的方法
tree.on('click',function(node, e){  
	if(!node.isLeaf()){ return false; }
	e.stopEvent();
	addTab(node.attributes.text,node.attributes.hrefTarget);   
});

Ext.onReady(function(){
	new Ext.Viewport({
		enableTabScroll:true,
		layout:"border",
		items:[titlePanel,tree,conentPanel]
	});
});