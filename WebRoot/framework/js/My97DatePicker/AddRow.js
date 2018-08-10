//查找document中的对象
function findObj(theObj, theDoc){
	var p, i, foundObj;
	if(!theDoc) theDoc = document;
	if( (p = theObj.indexOf("?")) > 0 && parent.frames.length)
	{ 
	    theDoc = parent.frames[theObj.substring(p+1)].document;    
	    theObj = theObj.substring(0,p); 
	} 
	if(!(foundObj = theDoc[theObj]) && theDoc.all) 
	    foundObj = theDoc.all[theObj]; 
	
	for (i=0; !foundObj && i < theDoc.forms.length; i++)     
	    foundObj = theDoc.forms[i][theObj]; 
	
	for(i=0; !foundObj && theDoc.layers && i < theDoc.layers.length; i++)     
	    foundObj = findObj(theObj,theDoc.layers[i].document); 
	    
	if(!foundObj && document.getElementById) 
	    foundObj = document.getElementById(theObj);    
	
	return foundObj;
}
function DeleteSignRow(rowid) {// 删除指定行
 				
	rowid="signFrame"+rowid;
	var signFrame = findObj("signFrame", document);
	var signItem = findObj(rowid, document);
	// 获取将要删除的行的Index
	var rowIndex = signItem.rowIndex; 
	
	// 删除指定Index的行
	signFrame.deleteRow(rowIndex);

}
