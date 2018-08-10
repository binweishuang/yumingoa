function rowClickColor(obj)
{
	var parentObj = obj.parentElement;
	var rowNum = 1;
	var tag = parentObj.tagName.toUpperCase();
	if(tag == "TABLE" || tag == "TBODY") {
		rowNum = parentObj.rows.length;
	}
	var m = obj.sectionRowIndex;
	for (var i=0; i<rowNum; i++) {
		if (i == m) {
			obj.style.backgroundColor = "#FFFF00";
		} else {
		 	if (i%2 == 0) {
				parentObj.rows(i).style.backgroundColor = "#F8F8F8";
		 	} else {
				parentObj.rows(i).style.backgroundColor = "#FFFFFF";
		 	}
		}
	}
}
function resetRowColor(obj)
{
	var parentObj = obj.parentElement;
	var rowNum = 1;
	var tag = parentObj.tagName.toUpperCase();
	if(tag == "TABLE" || tag == "TBODY") {
		rowNum = parentObj.rows.length;
	}
	for (var i=0; i<rowNum; i++) {
		 	if (i%2 == 0) {
		 		parentObj.rows(i).style.backgroundColor = "#F8F8F8";
		 	} else {
		 		parentObj.rows(i).style.backgroundColor = "#FFFFFF";
		 	}
	}
}
