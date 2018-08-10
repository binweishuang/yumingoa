function selectDate(obj){
	var showx = event.screenX - event.offsetX - 4 - 10 ;
	var showy = event.screenY - event.offsetY -168;
	var newWINwidth = 210 + 4 + 18;
	retval = window.showModalDialog(getContextPath() + "/framework/common/date.htm", "", "dialogWidth:197px; dialogHeight:232px; dialogLeft:"+showx+"px; dialogTop:"+showy+"px; status:no; directories:yes;scrollbars:no;Resizable=no; " );
	if( retval != null )
	{
		var objValue=eval(obj);
		objValue.value = retval;
	}
}