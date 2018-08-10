var datastat = "";
var xmlDoc = null;
var xmlDoc_ = null;
var defaultXmlFile = "commonquery_xml.xml";

//
function init() {

	//
    if (search1.sql_mb.length > 0) {
    
    	//
        if (search1.sql_mb.options[search1.sql_mb.selectedIndex].value != "") {
        	
        	if(tempUid == "")
	        	//
	            initData("mbxml.jsp?MB_ZJ=" + search1.sql_mb.options[search1.sql_mb.selectedIndex].value); 
	        else{
	        	//
	            initData("mbxml.jsp?MB_ZJ=" + tempUid);    
	            
	            tempUid = "";        
            }
        } else {
        
        	if(tempUid == "")
	        	//
            	initData(defaultXmlFile);
            else{
	        	//
	            initData("mbxml.jsp?MB_ZJ=" + tempUid);  
	            
	            tempUid = "";   
	        }
            
        }
        //alert(tempUid);
    }    
}

//
function initData(url) {
    xmlDoc_ = new ActiveXObject("Msxml.DOMDocument");
    xmlDoc_.onreadystatechange = parseXMLFile;
    xmlDoc_.load(url);
}

//
function parseXMLFile() {
    var state = xmlDoc_.readyState;
    if (state == 4) {
        var err = xmlDoc_.parseError;
        if (err.errorCode != 0) {
            //alert("XML\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd!");
            return false;
        } else {
            xmlDoc = xmlDoc_.childNodes[1];
            
            //
            getOtherData();
            
            //
            makeTable();
            
            //
            initFocusEvent();
        }
    }
}

//
function getOtherData() {
	
	//
    var topic, order;
    
    //Title
    titles = xmlDoc.attributes.getNamedItem("titles").value;
    if ("undefined" != typeof (search1.titles)) {
        if (titles == "") {
            doCheck(search1.titles);
        } else {
            SelectTitles(titles);
        }
    }
    
    //Order
    order = xmlDoc.attributes.getNamedItem("order").value;
    if ("undefined" != typeof (search1.order)) {
        SelectCheckBox(search1.order, order);
    }
    
    //Topic
    topic = xmlDoc.attributes.getNamedItem("topic").value;
    if ("undefined" != typeof (search1.topic)) {
        SelectDrowDownList(search1.topic, topic);
        if (topic != "") {
            OrderChange2(search1.topic);
        }
    }
}

//
function doCheck(obj) {
	
	//
    var flag = false;
    
    //
    flag = obj.checked;
    
    //
    var input = window.selField.getElementsByTagName("input");
    
    //
    if (input != null) {
    
    	//
        for (var i = 0; i < input.length; i++) {
        
        	//
            if (input[i].type == "checkbox" && !input[i].disabled) {
            
            	//
                input[i].checked = flag;
            }
        }
    }
}

//
function UnClick(obj) {

	//
    if (obj.checked == false) {
    
    	//
        search1.titles.checked = false;
    }
    
    //
    SaveXML();
}

//
function SelectTitles(titles) {

	//
    var title = titles.split(",");
    
    //
    var input = window.selField.getElementsByTagName("input");
    if ((input.length > 0) && (title.length > 1)) {
    
    	//
        for (var i = 0; i < input.length; i++) {
        	
        	//
            input[i].checked = false;
        }
        for (var j = 0; j < title.length; j++) {
        
        	//
            for (var i = 0; i < input.length; i++) {
            
            	//
                if (input[i].name == title[j]) {
                    input[i].checked = true;
                }
            }
        }
    }
}

//
function SelectCheckBox(obj, val) {

	//
    for (i = 0; i < obj.length; i++) {
    
    	//
        if (obj[i].value == val) {
            obj[i].checked = true;
        }
    }
}

//
function SelectDrowDownList(obj, val) {
    for (i = 0; i < obj.length; i++) {
        if (obj.options[i].value == val) {
            obj[i].selected = true;
        }
    }
}

//
function OrderChange2(obj) {

	//
    if (obj.value != "") {
    
    	//
        for (var j = 0; j < search1.order.length; j++) {
        
        	//
            search1.order[j].disabled = false;
        }
    } else {
    
    	//
        for (var j = 0; j < search1.order.length; j++) {
        
        	//
            search1.order[j].disabled = true;
        }
    }
}

//
function OrderChange(obj) {

	//
    OrderChange2(obj);
    
    //
    SaveXML();
}

//
function round(num, n) {

	//
    var isN = isNaN(parseFloat(num));
    
    //
    return isN ? "0" : parseFloat(num).toFixed(n);
}

//
function makeTable() {

	//
    var tableStr = "";
    
    //
    tableStr += "<table class=\"main_table\">";
    
    //
    var i = 0;
    
    //
    for (i = 0; i < xmlDoc.childNodes.length; i++) {
    
    	//
        tableStr += makeRow(i + 1, xmlDoc.childNodes[i]);
    }
    
    //
    for (var j = i; j < 4; j++) {
    
    	//
        tableStr += makeNullRow(j + 1);
    }
    
    //
    tableStr += "</table>";
    
    //
    tbl1.innerHTML = tableStr;
}

//
function makeNullRow(index) {

	//
    var rowStr = "";
    
    //
    rowStr += "<tr id=\"pztableRow\" class=\"white\"><td style=\"width:20px;height:30px\" align=center nowrap index=\"" + index + "\"><b>" + index + "</b></td>";
    
    //
    rowStr += "<td style=\"width:30px;\" >&nbsp;</td>";	
	
	//
    rowStr += "<td style=\"width:100px;\">&nbsp;</td>";
    
    //
    rowStr += "<td style=\"width:45px;\">&nbsp;</td>";
    
    //
    rowStr += "<td>&nbsp;</td>";
    
    //
    rowStr += "<td style=\"width:50px\">&nbsp;</td>";
    
    //
    return rowStr;
}

//
function makeRow(index, Node) {

	//
    var cx_zd, cx_zdmc, cx_czf, cx_sz, cx_zdlx, cx_lj, table, k_gdz, k_dtsz;
    
    //
    cx_zd = Node.attributes.getNamedItem("cx_zd").value;
    
    //
    cx_zdmc = Node.attributes.getNamedItem("cx_zdmc").value;
    
    //
    cx_czf = Node.attributes.getNamedItem("cx_czf").value;
    
    //
    cx_sz = Node.attributes.getNamedItem("cx_sz").value;
    
    //
    cx_zdlx = Node.attributes.getNamedItem("cx_zdlx").value;
    
    //
    //table = Node.attributes.getNamedItem("table").value;
    
    //
    k_gdz = Node.attributes.getNamedItem("k_gdz").value;
    
    //
    //k_dtsz = Node.attributes.getNamedItem("k_dtsz").value;
    
    //
    if (Node.attributes.getNamedItem("cx_lj") == null) {
    
    	//
        var arrAttr = xmlDoc_.createNode(2, "cx_lj", "");
        
        //
        Node.attributes.setNamedItem(arrAttr);
        
        //
        Node.attributes.getNamedItem("cx_lj").value = "AND";
    }
    
    //
    cx_lj = Node.attributes.getNamedItem("cx_lj").value;
    
    //
    var rowStr = "";
    
    //
    rowStr += "<input type=\"hidden\" name=\"cx_zd\" value=\"" + cx_zd + "\">";
    
    //
    rowStr += "<input type=\"hidden\" name=\"cx_zdlx\" value=\"" + cx_zdlx + "\">";
    
    //
    rowStr += "<input type=\"hidden\" name=\"cx_czf\" value=\"" + cx_czf + "\">";
    
    //
    rowStr += "<input type=\"hidden\" name=\"cx_zdmc\" value=\"" + cx_zdmc + "\">";
    
    //
    rowStr += "<input type=\"hidden\" name=\"cx_lj\" value=\"" + cx_lj + "\">";
    
    //
    //rowStr += "<input type="hidden" name="table" value="" + table + "">";
    
    //
    rowStr += "<tr id=\"pztableRow\" class=\"white\"><td style=\"width:20px;height:30px\" nowrap align=center index=\"" + index + "\"><b>" + index + "</b></td>";
    
    //
    if (index == 1) {
    
    	//
        rowStr += "<td style=\"width:30px;\" > </td>";
    } else {
    
    	//
        rowStr += "<td style=\"width:30px;\" >" + cx_lj + "</td>";
    }
    
    //
    rowStr += "<td style=\"width:100px;\">" + cx_zdmc + "</td>";
    
    //
    rowStr += "<td style=\"width:45px;\">" + cx_czf + "</td>";
    
    //
    if (cx_zdlx == "string") {
    
    	//
        if (k_gdz != "") {
        	
        	//
            rowStr += "<td>" + getHtmlSelectByArray(k_gdz, cx_sz, "cx_sz", "pzInput") + "</td>";
        } else {
        
        	//
            rowStr += "<td><input id=\"pztable_Zy\" name=\"cx_sz\" class=\"pzInput\" type=\"text\" value=\"" + cx_sz + "\"></td>";
        }
    } else {
    
    	//
        rowStr += "<td><input id=\"pztable_Zy\" name=\"cx_sz\" class=\"pzInput\" type=\"text\" value=\"" + cx_sz + "\" " + getRow(cx_zdlx) + " ></td>";
    }
    
    //
    rowStr += "<td style=\"width:50px\"><a href=\"#\" onclick=\"delData(" + (index - 1) + ")\">\u5220\u9664</a></td>";
        
    //
    rowStr == "</tr>";
    
    //
    return rowStr;
}

//
function SelData(choice, sel_index, obj, classtype) {

	//
    var Str = "<select name='" + obj + "' class='" + classtype + "'>";
    Str += "";
    var choices = choice.split(",");
    for (i = 0; i < choices.length; i++) {
        Str += "<option";
        var value = choices[i].split("|");
        var str1 = value[1];
        Str += " value=" + value[0];
        if ((sel_index != "") && (typeof (sel_index) != "undefined") && (value[0] == sel_index)) {
            Str += " selected";
        }
        Str += ">" + str1 + "</option>";
    }
    return Str;
}

//
function getHtmlSelectByArray(objName, val, name, classtype) {
    var obj = objName.split(",");
    var str = "<SELECT NAME='" + name + "' class='" + classtype + "' readonly>";
    var n = 1;
    for (var i = 0; i < obj.length - 1; i++) {
        if (obj[i] == val) {
            str += "<OPTION VALUE=" + obj[i] + " selected>" + obj[i + 1] + "</OPTION>";
        } else {
            str += "<OPTION VALUE=" + obj[i] + " >" + obj[i + 1] + "</OPTION>";
        }
        i++;
        n++;
    }
    str += "</SELECT>";
    return str;
}

//
function getRow(cx_zdlx) {
	
	//
    var sReturn = "";
    if (cx_zdlx == "money") {
        sReturn += "onchange=\"javascript:if(!isFloat(this.value)){this.value=0;alert('\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd\ufffd!')}else{this.value=round(this.value,2)}\"";
    }
    if (cx_zdlx == "date") {
        sReturn += "readonly onclick=\"javascript:selectDate(this)\"";
    }
    return sReturn;
}

//
function delData(index) {
    datastat = "";
    ot = setInterval("delLine(" + index + ")", 10);
}

//
function delLine(index) {
	
	//
    if (datastat == "complete") {
        window.clearInterval(ot);
        
        //
        focusNextLine(index);
        
        //
        return;
    }
    if (datastat == "") {
    
    	//
        datastat = "working";
        
        //
        var arrNodesToRemove = xmlDoc.childNodes[index];
        
        //
        xmlDoc.removeChild(arrNodesToRemove);
        
        //
        makeTable();
        
        //
        initFocusEvent();
        
        //
        datastat = "complete";
    }
}

//
function focusNextLine(index) {

	//
    if ("undefined" == typeof (search1.cx_sz)) {
        return;
    }
    if (search1.cx_sz.length == null) {
        search1.cx_sz.focus();
    } else {
        if (index >= search1.cx_sz.length) {
            search1.cx_sz[search1.cx_sz.length - 1].focus();
        } else {
            search1.cx_sz[index].focus();
        }
    }
}

//
function initFocusEvent() {

	//
    currentNode = null;
    
    //
    currentEditObj = null;
    
    //
    for (i = 0; i < document.all.length; i++) {
    
    	//
        if ((document.all[i].className == "pzInput") || (document.all[i].className == "pzInput2")) {
        
        	//
            document.all[i].onfocus = doFocus;
            
            //
            document.all[i].onblur = doBlur;
        }
    }
    
    //
    focus1Line();
}

//
function focus1Line() {

	//
    if ("undefined" == typeof (search1.cx_sz)) {
    
    	//
        return;
    }
    
    //
    if (search1.cx_sz.length == null) {
    
    	//
        search1.cx_sz.focus();
    } else {
    
    	//
        search1.cx_sz[0].focus();
    }
}

//
function doFocus() {

	//
    var EL = event.srcElement;
    
    //
    if (EL == currentEditObj) {
    
    	//
        return;
    }
    
    //
    if (EL.className == "pzInput") {
    
		//
        if (currentEditObj != null) {
        
        	//
            var sTD = currentEditObj.parentElement;
            
            //
            var sTR = sTD.parentElement;
            
            //
            sTR.style.backgroundColor = "white";
            
            //
            for (i = 0; i < sTR.childNodes.length; i++) {
            
            	//
                if (typeof (sTR.childNodes[i].childNodes[0]) != "undefined" && typeof (sTR.childNodes[i].childNodes[0].style) != "undefined") {
                    
                    //
                    sTR.childNodes[i].childNodes[0].style.backgroundColor = "white";
                    
                    //
                    sTR.childNodes[i].childNodes[0].style.border = "1px solid white";
                }
            }
            
            //
            currentEditObj.style.border = "1px solid white";
            
            //
            currentEditObj.style.color = "black";
        }
        
		//
        currentEditObj = EL;
        
        //
        makeSelect(EL);
    }
}

//
function makeSelect(obj) {

	//
    var sTD = obj.parentElement;
    
    //
    var sTR = sTD.parentElement;
    
    //
    var obj_;
    
    //
    currentIndex = sTR.childNodes[0].index;
    
    //
    currentNode = xmlDoc.childNodes[currentIndex - 1];
    
    //
    pztableRow[currentIndex - 1].style.backgroundColor = "yellow";
    
    //
    for (i = 0; i < pztableRow[currentIndex - 1].childNodes.length; i++) {
    
   		//
        obj_ = pztableRow[currentIndex - 1].childNodes[i].childNodes[0];
        
   		//
        if (typeof (obj_) != "undefined" && typeof (obj_.style) != "undefined") {
        
   			//
            obj_.style.backgroundColor = "yellow";
            
   			//
            obj_.style.border = "1px solid yellow";
        }
    }
    
	//
    obj.style.borderBottom = "1px dotted blue";
    
	//
    obj.style.color = "blue";
}


//
function doBlur() {

	//
    var EL = event.srcElement;
    
	//
    if ((EL.className == "pzInput") || (EL.className == "pzInput2")) {
    
    	//
        synData(EL);
    }
}

//
function synData(obj) {
	
	//
    if (obj.name == "cx_sz") {
    
		//
        node = xmlDoc.childNodes[currentIndex - 1];
        
        //
        node.attributes.getNamedItem("cx_sz").value = obj.value;
    }
}

//
function focusLastLine() {
	//
    if ("undefined" == typeof (search1.cx_sz)) {
        return;
    }
    
    //
    if (search1.cx_sz.length == null) {
    
    	//
        search1.cx_sz.focus();
    } else {
    
    	//
        search1.cx_sz[search1.cx_sz.length - 1].focus();
    }
}

//
function insertData() {
	
	//
    datastat = "";
    
    //
    ot = setInterval("insertLine()", 10);
}

//
function firstChoice() {

	//
    __invokeListener("doFieldChange", __buildPostData(document.getElementById("search1")), catchInvoke);
	
    //doChange(search1.K_ZD.value);
}

//
function catchInvoke(ret) {

	//
    var jsonObject = eval("(" + ret + ")");
	    
    //
    if (jsonObject) {
        sel1Span.innerHTML = jsonObject.selspan1;
        sel2Span.innerHTML = jsonObject.selspan2;
    }
}

//
function SaveXML() {

	//
    if ("undefined" != typeof (search1.topic)) {
    
    	//
        xmlDoc.attributes.getNamedItem("topic").value = search1.topic.value;
    }
	
	//
    var str = "";
    
    //
    var input = window.selField.getElementsByTagName("input");
    
    //
    if (input.length > 0) {
    
    	//
        for (var i = 0; i < input.length; i++) {
        
           //
            if (input[i].checked == true) {
           
           		//
                str += input[i].name + ",";
            }
        }
        
        //
        xmlDoc.attributes.getNamedItem("titles").value = str;
    }
    
    //
    if ("undefined" != typeof (search1.order)) {
    
    	//
        obj = search1.order;
        
    	//
        str = "";
        
    	//
        for (i = 0; i < obj.length; i++) {
        
        	//
            if (obj[i].checked == true) {
            
            	//
                str = obj[i].value;
            }
        }
        
        //
        xmlDoc.attributes.getNamedItem("order").value = str;
    }
	
	//
	//updateBask(K_LYID);
}

//
function doAdd() {
	
	//
    var v = document.all.K_ZD.value;
    
    //
    var input = window.selField.getElementsByTagName("input");
    
    //
    for (var i = 0; i < input.length; i++) {
    	
    	//
        if (input[i].name == v) {
            input[i].checked = true;
        }
    }
    
    //
    insertData();
}

//
function insertLine() {

	//
    if (datastat == "complete") {
    
    	//
        window.clearInterval(ot);
        
        //
        focusLastLine();
        
        //
        return;
    }
    
    //
    if (datastat == "") {
    
    	//
        datastat = "working";
        
        //
        var arrAttr = new Array();
                
		//Create attributes
        arrAttr[0] = xmlDoc_.createNode(2, "cx_zd", "");
        arrAttr[1] = xmlDoc_.createNode(2, "cx_zdmc", "");
        arrAttr[2] = xmlDoc_.createNode(2, "cx_czf", "");
        arrAttr[3] = xmlDoc_.createNode(2, "cx_sz", "");
        arrAttr[4] = xmlDoc_.createNode(2, "cx_zdlx", "");
        arrAttr[5] = xmlDoc_.createNode(2, "cx_lj", "");
        arrAttr[6] = xmlDoc_.createNode(2, "k_gdz", "");
        		
		//create node	
        var newNode = xmlDoc_.createNode(1, "datarow", "");
        
        //
        for (i = 0; i < arrAttr.length; i++) {
        
        	//
            newNode.attributes.setNamedItem(arrAttr[i]);
        }
        
        //
        newNode.attributes.getNamedItem("cx_zd").value = search1.K_ZD.value;
        newNode.attributes.getNamedItem("cx_zdmc").value = search1.K_ZD.options[search1.K_ZD.selectedIndex].text;
        newNode.attributes.getNamedItem("cx_czf").value = search1.cx1.value;
        newNode.attributes.getNamedItem("cx_sz").value = search1.cx2.value;
        newNode.attributes.getNamedItem("cx_zdlx").value = search1.cx3.value;
        newNode.attributes.getNamedItem("cx_lj").value = search1.cx4.options[search1.cx4.selectedIndex].value;
        newNode.attributes.getNamedItem("k_gdz").value = search1.k_gdz.value;
        
		//
        currNode = xmlDoc.insertBefore(newNode, xmlDoc.childNodes[xmlDoc.childNodes.length]);
                
        //
        makeTable();
        
        //
        initFocusEvent();
        
        //
        datastat = "complete";
        
        //
        SaveXML();
    }
}


//
function getSQL_SEL() {

	//
    var sel = "";
    
    //
    var selname = "";
            
    //
    var input = window.selField.getElementsByTagName("input");
    
    //
    if (input != null) {
    
    	//
        for (var i = 0; i < input.length; i++) {
        
        	//
            if ((input[i].type == "checkbox" && input[i].checked && input[i].disabled != true) || (input[i].type == "hidden")) {
                
                //
                sel += input[i].name + ",";		
                
                //
                //selname	+= input[i].name + ",";     
            }
        }
    }
    
    //
    sel = sel.substring(0, sel.length - 1);   
    
    //
    //selname = selname.substrng(0, selname.length - 1);
    
    //
    //alert(sel);
    
    //
    search1.sql_select.value = sel;
    
    //
    //search1.sql_namestr.value = selname;
}

//
function getSQL_ORDERBY() {

	//
    var order = "";
    
    //
    if (search1.topic.value != "") {
    
    	//
        order += search1.topic.value;
        
        //
        var obj = search1.all.order;
        
        //
        for (var i = 0; i < obj.length; i++) {
        
        	//
            if (obj[i].checked == true) {
            	
            	//
                order += " " + obj[i].value;
            }
        }
    }
    
    //
    //alert(order);
    
    //
    search1.sql_orderby.value = order;
}

//
function doQuery() {
	
	//
	search1.temptemplate.value = xmlDoc.xml;
	
	//
	getSQL_SEL();

	//getSQL_WHERE();
	
	//
    getSQL_ORDERBY();
    
    //
    search1.action = "commonqueryshow.jsp?actionName=doPage&";
    
    //
    search1.submit();
	
	
}


//
function DelTemplate(MB_ZJ){

	//
	if (MB_ZJ != ""){
		
		//
		if (confirm("\u662f\u5426\u8981\u5220\u9664\u6a21\u677f!")){
		
			//
			mb_hidden.location.href = "mb_save.jsp?actionName=doDelete&MB_ZJ=" + MB_ZJ;
		}
	}
	else{
	    alert("\u672a\u9009\u62e9\u6a21\u7248!");
	}
}

//
function SaveTemplate(t){	

	//
	SaveXML();
	
	//
	ret = window.showModalDialog("mb_save_frame.jsp?MB_FLMC=" + t, window, "scroll:no;resizable:no;help:no;status:no;dialogHeight:130px;dialogWidth:194px;");
}

//
function UpdateTemplate(t, MB_ZJ, MB_MC){
	//
    if (MB_ZJ != ""){
		if (confirm("\u8bf7\u786e\u5b9a\u662f\u5426\u4fee\u6539\u5f53\u524d\u6a21\u7248!")){
		
			//
			SaveXML();			
			
			//
	        ret = window.showModalDialog("mb_save_frame.jsp?actionName=doUpdate&MB_FLMC=" + t + "&MB_ZJ=" + MB_ZJ + "&MB_MC=" + MB_MC, window, "scroll:no;resizable:no;help:no;status:no;dialogHeight:130px;dialogWidth:194px;");
		}
	}
	else{
	    alert("\u672a\u9009\u62e9\u6a21\u7248!");
	}
}

///////////////////////////////////////////////////////////////////////////////////////
///////////////////////////////////////////////////////////////////////////////////////
//
function doChange(value) {
    var allAttritutes = document.all.allAttritutes;
    var attributes = allAttritutes.getElementsByTagName("datarow");
    if (attributes != null) {
        for (var i = 0; i < attributes.length; i++) {
	       
	        //
            if (value == attributes[i].getElementsByTagName("k_zd")[0].firstChild.nodeValue) {
                if ((attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue == "text") || (attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue == "num")) {
                    sel1Span.innerHTML = null;
                    sel2Span.innerHTML = null;
                    sel1Span.innerHTML = "<SELECT name='cx1'><OPTION VALUE='=' selected> = </OPTION><OPTION VALUE='\u5305\u542b'> \u5305\u542b </OPTION><OPTION VALUE='&lt;&gt;'> <> </OPTION></SELECT>";
                    if (attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue != "") {
                        sel2Span.innerHTML = "<input type=text name=cx2 value='" + attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue + "' class='main_Input'>";
                    } else {
                        sel2Span.innerHTML = "<input type=text name=cx2 value='' class='main_Input' >";
                    }
                    sel2Span.innerHTML += "<input type='hidden' name='cx3' value='" + attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='table' value='" + attributes[i].getElementsByTagName("k_sql")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_gdz' value='" + attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_dtsz' value='" + attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue + "' >";
	               //sel2Span.innerHTML+=K_SFBT;
                }
                if (attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue == "money") {
                    sel1Span.innerHTML = null;
                    sel2Span.innerHTML = null;
                    sel1Span.innerHTML = "<SELECT name='cx1'><OPTION VALUE='=' selected> = </OPTION><OPTION VALUE='&gt;'> > </OPTION><OPTION VALUE='&lt;'> < </OPTION><OPTION VALUE='&lt;&gt;'> <> </OPTION></SELECT>";
                    if (attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue != "") {
                        sel2Span.innerHTML = "<input type=text name=cx2 style='text-align:right' value='" + attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue + "' class='main_Input' class='main_Input'onChange=\"this.value=round(this.value,2)\">";
                    } else {
                        sel2Span.innerHTML = "<input type=text name=cx2 style='text-align:right' value='' class='main_Input'onChange=\"this.value=round(this.value,2)\">";
                    }
                    sel2Span.innerHTML += "<input type='hidden' name='cx3' value='" + attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='table' value='" + attributes[i].getElementsByTagName("k_sql")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_gdz' value='" + attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_dtsz' value='" + attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue + "' >";
	               //sel2Span.innerHTML+=K_SFBT;
                }
                if (attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue == "date") {
                    sel1Span.innerHTML = null;
                    sel2Span.innerHTML = null;
                    sel1Span.innerHTML = "<SELECT name='cx1'><OPTION VALUE='=' selected> = </OPTION><OPTION VALUE='&gt;'> > </OPTION><OPTION VALUE='&lt;'> < </OPTION><OPTION VALUE='&lt;&gt;'> <> </OPTION></SELECT>";
                    if (attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue != "") {
                        sel2Span.innerHTML = "<input type=text name=cx2 value='" + attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue + "' class='main_Input' onClick=\"selectDate('../../',this)\" readonly>";
                    } else {
                        sel2Span.innerHTML = "<input type=text name=cx2 value='' class='main_Input' onClick=\"selectDate('../../',this)\" readonly >";
                    }
                    sel2Span.innerHTML += "<input type='hidden' name='cx3' value='" + attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='table' value='" + attributes[i].getElementsByTagName("k_sql")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_gdz' value='" + attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_dtsz' value='" + attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue + "' >";
	               //sel2Span.innerHTML+=K_SFBT;
                }
                if (attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue == "select") {
                    sel1Span.innerHTML = null;
                    sel2Span.innerHTML = null;
                    sel1Span.innerHTML = "<SELECT name='cx1'><OPTION VALUE='=' selected> = </OPTION><OPTION VALUE='\u5305\u542b'> \u5305\u542b </OPTION><OPTION VALUE='&lt;&gt;'> <> </OPTION></SELECT>";
                    if (attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue != "") {
                        sel2Span.innerHTML = SelData(attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue, attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue, "cx2", "main_Input");
                    } else {
                        sel2Span.innerHTML = getHtmlSelectByArray(attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue, attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue, "cx2", "main_Input");
                    }
                    sel2Span.innerHTML += "<input type='hidden' name='cx3' value='" + attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='table' value='" + attributes[i].getElementsByTagName("k_sql")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_gdz' value='" + attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_dtsz' value='" + attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue + "' >";
	               //sel2Span.innerHTML+=K_SFBT;
                }
                if (attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue == "textarea") {
                    sel1Span.innerHTML = null;
                    sel2Span.innerHTML = null;
                    sel1Span.innerHTML = "<SELECT name='cx1'><OPTION VALUE='=' selected> = </OPTION><OPTION VALUE='\u5305\u542b'> \u5305\u542b </OPTION><OPTION VALUE='&lt;&gt;'> <> </OPTION></SELECT>";
                    if (attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue != "") {
                        sel2Span.innerHTML = "<input type=textarea name=cx2 value='" + attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue + "' class='main_Input'>";
                    } else {
                        sel2Span.innerHTML = "<input type=textarea name=cx2 value='' class='main_Input'>";
                    }
                    sel2Span.innerHTML += "<input type='hidden' name='cx3' value='" + attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='table' value='" + attributes[i].getElementsByTagName("k_sql")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_gdz' value='" + attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_dtsz' value='" + attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue + "' >";
	               //sel2Span.innerHTML+=K_SFBT;
                }
                if (attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue == "interface_text") {
                    sel1Span.innerHTML = null;
                    sel2Span.innerHTML = null;
                    sel1Span.innerHTML = "<SELECT name='cx1'><OPTION VALUE='=' selected> = </OPTION><OPTION VALUE='\u5305\u542b'> \u5305\u542b </OPTION><OPTION VALUE='&lt;&gt;'> <> </OPTION></SELECT>";
                    if (attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue != "") {
                        sel2Span.innerHTML = "<input type=text name=cx2 value='" + attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue + "' class='main_Input'>";
                    } else {
                        sel2Span.innerHTML = "<input type=text name=cx2 value='' class='main_Input'>";
                    }
                    sel2Span.innerHTML += "<input type='hidden' name='cx3' value='" + attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='table' value='" + attributes[i].getElementsByTagName("k_sql")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<img src='../../sys-resources/images/folder_find.gif' onclick=\"" + attributes[i].getElementsByTagName("k_jk")[0].firstChild.nodeValue + "\" style='CURSOR: hand' alt='\u9009\u62e9" + attributes[i].getElementsByTagName("k_zdmc")[0].firstChild.nodeValue + "' align='absmiddle'>";
                    sel2Span.innerHTML += "<input type='hidden' name='k_gdz' value='" + attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_dtsz' value='" + attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue + "' >";
	               //sel2Span.innerHTML+=K_SFBT;
                }
                if (attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue == "interface_select") {
                    sel1Span.innerHTML = null;
                    sel2Span.innerHTML = null;
                    sel1Span.innerHTML = "<SELECT name='cx1'><OPTION VALUE='=' selected> = </OPTION><OPTION VALUE='\u5305\u542b'> \u5305\u542b </OPTION><OPTION VALUE='&lt;&gt;'> <> </OPTION></SELECT>";
                    if (attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue != "") {
                        sel2Span.innerHTML = getHtmlSelectByArray(attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue, attributes[i].getElementsByTagName("k_mrz")[0].firstChild.nodeValue);
                    } else {
                        sel2Span.innerHTML = "<input type=text name=cx2 value='' class='main_Input'>";
                    }
                    sel2Span.innerHTML += "<input type='hidden' name='cx3' value='" + attributes[i].getElementsByTagName("k_lx")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='table' value='" + attributes[i].getElementsByTagName("k_sql")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<img src='../../sys-resources/images/folder_find.gif' onclick=\"" + attributes[i].getElementsByTagName("k_jk")[0].firstChild.nodeValue + "\" style='CURSOR: hand' align='absmiddle'>";
                    sel2Span.innerHTML += "<input type='hidden' name='k_gdz' value='" + attributes[i].getElementsByTagName("k_gdz")[0].firstChild.nodeValue + "' >";
                    sel2Span.innerHTML += "<input type='hidden' name='k_dtsz' value='" + attributes[i].getElementsByTagName("k_dtsz")[0].firstChild.nodeValue + "' >";
	               //sel2Span.innerHTML+=K_SFBT;
                }
            }
        }
    }
}

//
function doFocus2(obj) {
    var EL = obj;
    if (EL == currentEditObj) {
        return;
    }
    if (EL.className == "pzInput") {
		//????????????????????
        if (currentEditObj != null) {
            var sTD = currentEditObj.parentElement;
            var sTR = sTD.parentElement;
            sTR.style.backgroundColor = "white";
            for (i = 0; i < sTR.childNodes.length; i++) {
                if (typeof (sTR.childNodes[i].childNodes[0]) != "undefined" && typeof (sTR.childNodes[i].childNodes[0].style) != "undefined") {
                    sTR.childNodes[i].childNodes[0].style.backgroundColor = "white";
                    sTR.childNodes[i].childNodes[0].style.border = "1px solid white";
                }
            }
            currentEditObj.style.border = "1px solid white";
            currentEditObj.style.color = "black";
        }
		//????????????????
        currentEditObj = EL;
        makeSelect(EL);
    }
}

//
function getSaveXML(key) {
    for (var i = 0; i < parent.parent.ARR_D_SEARCH_KEY.length; i++) {
        if (parent.parent.ARR_D_SEARCH_KEY[i] == key) {
            return parent.parent.ARR_D_SEARCH_XML[i];
        }
    }
    return null;
}

//
function updateBask(key) {
	
	//
    for (var i = 0; i < parent.parent.ARR_D_SEARCH_KEY.length; i++) {
	
		//
        if (parent.parent.ARR_D_SEARCH_KEY[i] == key) {
            parent.parent.ARR_D_SEARCH_XML[i] = xmlDoc_;
            return false;
        }
    }
    var n = parent.parent.ARR_D_SEARCH_KEY.length;
    parent.parent.ARR_D_SEARCH_KEY[n] = key;
    parent.parent.ARR_D_SEARCH_XML[n] = xmlDoc_;
}

//
function Add1Value4SelectDrowDownListN(obj, val, text, n) {
    for (i = 0; i < obj.length; i++) {
        if (obj.options[i].value == val) {
            return;
        }
    }
    var myOption = document.createElement("OPTION");
    myOption.text = text;
    myOption.value = val;
    obj.options.add(myOption, n);
}

