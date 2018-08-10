/*common function by xing2k@gmail.com*/

//2进制转换为10进制
function Bin2Dec(binString){
	return parseInt(binString,2);
}
//10进制转换为2进制
function Dec2Bin(decString){
	var str='';
	decString=parseInt(decString);
	if(decString<0){
		return;
	}else{
		while(decString!=0){
	  	str=(decString%2)+str;
			decString = Math.floor(decString/2);
		}
		return str;
	}
}

//判断一个数字的某位是否为1
//注意位是从右往左的，:)
function isEnabled(number,point){
	var bitmask = (0x01 << point-1);
	if (0 != (bitmask & number)){
		return true;
	}else{
		return false;
	}
}

function getLeft(obj){
	var left=obj.offsetLeft;
	while (obj.tagName !="BODY"){
		obj=obj.offsetParent;
		left+=obj.offsetLeft;
	}
	return left;
}
function getTop(obj){
	var top=obj.offsetTop;
	while (obj.tagName !="BODY"){
		obj=obj.offsetParent;
		top+=obj.offsetTop;
	}
	return top;
}

function convertCurrency(currencyDigits) { 
// Constants: 
    var MAXIMUM_NUMBER = 99999999999.99; 
    // Predefine the radix characters and currency symbols for output: 
    var CN_ZERO = "零"; 
    var CN_ONE = "壹"; 
    var CN_TWO = "贰"; 
    var CN_THREE = "叁"; 
    var CN_FOUR = "肆"; 
    var CN_FIVE = "伍"; 
    var CN_SIX = "陆"; 
    var CN_SEVEN = "柒"; 
    var CN_EIGHT = "捌"; 
    var CN_NINE = "玖"; 
    var CN_TEN = "拾"; 
    var CN_HUNDRED = "佰"; 
    var CN_THOUSAND = "仟"; 
    var CN_TEN_THOUSAND = "万"; 
    var CN_HUNDRED_MILLION = "亿"; 
    var CN_SYMBOL = ""; 
    var CN_DOLLAR = "元"; 
    var CN_TEN_CENT = "角"; 
    var CN_CENT = "分"; 
    var CN_INTEGER = "整"; 
    var CN_FU="";
     
// Variables: 
    var integral;    // Represent integral part of digit number. 
    var decimal;    // Represent decimal part of digit number. 
    var outputCharacters;    // The output result. 
    var parts; 
    var digits, radices, bigRadices, decimals; 
    var zeroCount; 
    var i, p, d; 
    var quotient, modulus; 
     
// Validate input string: 
    currencyDigits = currencyDigits.toString();    
    if (currencyDigits.substr(0,1) == "-"){
    	currencyDigits = currencyDigits.substr(1);
    	CN_FU = "负";
    }
    if (currencyDigits == "") { 
       // alert("Empty input!"); 
        return ""; 
    } 
    if (currencyDigits.match(/[^,.\d]/) != null) { 
        alert("Invalid characters in the input string!"); 
        return ""; 
    } 
    if ((currencyDigits).match(/^((\d{1,3}(,\d{3})*(.((\d{3},)*\d{1,3}))?)|(\d+(.\d+)?))$/) == null) { 
        alert("Illegal format of digit number!"); 
        return ""; 
    } 
     
// Normalize the format of input digits: 
    currencyDigits = currencyDigits.replace(/,/g, "");    // Remove comma delimiters. 
    currencyDigits = currencyDigits.replace(/^0+/, "");    // Trim zeros at the beginning. 
    // Assert the number is not greater than the maximum number. 
    if (Number(currencyDigits) > MAXIMUM_NUMBER) { 
        alert("Too large a number to convert!"); 
        return ""; 
    } 
     
// Process the coversion from currency digits to characters: 
    // Separate integral and decimal parts before processing coversion: 
    parts = currencyDigits.split("."); 
    if (parts.length > 1) { 
        integral = parts[0]; 
        decimal = parts[1]; 
        // Cut down redundant decimal digits that are after the second. 
        decimal = decimal.substr(0, 2); 
    } 
    else { 
        integral = parts[0]; 
        decimal = ""; 
    } 
    // Prepare the characters corresponding to the digits: 
    digits = new Array(CN_ZERO, CN_ONE, CN_TWO, CN_THREE, CN_FOUR, CN_FIVE, CN_SIX, CN_SEVEN, CN_EIGHT, CN_NINE); 
    radices = new Array("", CN_TEN, CN_HUNDRED, CN_THOUSAND); 
    bigRadices = new Array("", CN_TEN_THOUSAND, CN_HUNDRED_MILLION); 
    decimals = new Array(CN_TEN_CENT, CN_CENT); 
    // Start processing: 
    outputCharacters = ""; 
    // Process integral part if it is larger than 0: 
    if (Number(integral) > 0) { 
        zeroCount = 0; 
        for (i = 0; i < integral.length; i++) { 
            p = integral.length - i - 1; 
            d = integral.substr(i, 1); 
            quotient = p / 4; 
            modulus = p % 4; 
            if (d == "0") { 
                zeroCount++; 
            } 
            else { 
                if (zeroCount > 0) 
                { 
                    outputCharacters += digits[0]; 
                } 
                zeroCount = 0; 
                outputCharacters += digits[Number(d)] + radices[modulus]; 
            } 
            if (modulus == 0 && zeroCount < 4) { 
                outputCharacters += bigRadices[quotient]; 
            } 
        } 
        outputCharacters += CN_DOLLAR; 
    } 
    // Process decimal part if there is: 
    if (decimal != "") { 
        for (i = 0; i < decimal.length; i++) { 
            d = decimal.substr(i, 1); 
            if (d != "0") { 
                outputCharacters += digits[Number(d)] + decimals[i]; 
            } 
        } 
    } 
    // Confirm and return the final output string: 
    if (outputCharacters == "") { 
        outputCharacters = CN_ZERO + CN_DOLLAR; 
    } 
    if (decimal == "") { 
       // outputCharacters += CN_INTEGER; 
    } 
    
   if (outputCharacters.substr(outputCharacters.length-1) == "元"){
    	outputCharacters = outputCharacters + "整";
    }
    
    outputCharacters = CN_FU + CN_SYMBOL + outputCharacters; 
    return outputCharacters; 
} 


//四舍五入，保留两位小数位数
 function myRound(a_Num , a_Bit) 
  { 
  	return( Math.round(a_Num * Math.pow (10 , a_Bit)) / Math.pow(10 , a_Bit)) ; 
  } 
  function FormatNum(a_Num,a_Bit)
  {
  	
  	if (a_Num == '')
  	{
		return '';	
  	}
  	var temp='';
  	var pointIndex=0;
  	var tempLength=0;
  	var tempData=0;
  	
  	tempData=myRound(a_Num , a_Bit) ;
  	//a_Num=
  	
  	temp=tempData.toString();
  	pointIndex=temp.indexOf('.')
  	tempLength=temp.length;
  	
  	if (pointIndex != -1)
  	{
  		if ((tempLength - pointIndex) == 2)
  		{
  			temp+='0';
  		}
  	}
  	else
  	{
  		temp+='.00'
  	}
  	return temp;
  }

function asc(c){
	var i;
	var num="0123456789";
	var flag=false;
		for(i=0;i<num.length;i++){
			if(c==num.charAt(i)){
				flag=true;
			}
		}
	return flag;
}
function checkNum(val){
	var i=0;
	var flag=true;
	for(i=0;i<val.length;i++){
		var ch=val.charAt(i);
		if(!asc(ch)){
			flag=false;
		}
	}
	return flag;
}	
function doMove(operation)
{
	var p,pc;
	p = 1;
	pc = 100;
	p = document.form1.page.value;
	pc = document.form1.pageCount.value;
  	//document.form1.go.value = operation;

	if(operation=="firstPage"){
		if(parseInt(p)<=1){
			return ;
		}else{
	  	document.form1.page.value = 1;
		}
	}else if(operation=="prePage"){
	  if(parseInt(p)<=1)
			return ;
		else
			document.form1.page.value = --p;
	}else if(operation=="nextPage"){
		if(parseInt(p)>=parseInt(pc))
			return ;
		else
			document.form1.page.value = ++p;
	}else if(operation=="lastPage"){
		if(parseInt(p) == parseInt(pc)){
			return ;
		}else{
			document.form1.page.value = pc;
		}
	}else if(operation=="toPage"){
		if(document.form1.toPage.value!=""){
			if(!checkNum(document.form1.toPage.value) || parseInt(document.form1.toPage.value)<1 || parseInt(document.form1.toPage.value)>pc){
				alert("请输入正确的页码！");
				document.form1.toPage.value = "";
				return ;
			}else{
				document.form1.page.value = document.form1.toPage.value;
			}
		}else{
			return ;
		}
	}
	document.form1.submit();
}

/*
*凭证预览
*relative_path,相对于站点根目录tms的路径，与selectDate的相对路径一个意思
*title 打开IE窗口的标题
*pzz 凭证字
*zzsj 制证时间
*kjqj 会计区间
*fjs 附件数
*xmlnrmc 存储bigtext表中xml的内容名称
*del  删除bigtext中xml内容，del为1删除，其他不删除
*

*
*例如从/tms/gl_jsps/gl_pz/gl_mxpz/pagequery.jsp调用该函数
*
*<SCRIPT LANGUAGE="JavaScript" src="../../../sys-resources/js/lib.js"></SCRIPT>
*preview('../../../','凭证模板预览','2','','','0','GL.PZMB.wxd.20060404200133','');
*/
function preview(relative_path,title,pzz,zzsj,kjqj,fjs,xmlnrmc,del)
		{
			var param = "K_NR="+xmlnrmc+"&title="+title+"&pzz="+pzz+"&zzsj="+zzsj+"&kjqj="+kjqj+"&fjs="+fjs+"&del="+del;
			window.open(relative_path+'gl_jsps/gl_pz/gl_mxpz/pzDetail.jsp?actionName=doSearchDetail&'+param,'pzpre','top=30,left=30,width=900,height=560,location=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no');
			//window.open(path+'gl_jsps/gl_pz/gl_mxpz/pzDetail.jsp?actionName=doSearchDetail&'+param,'_blank','top=30,left=30,width=900,height=560,location=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no');
		}

/*
*凭证打印(打印当前登录账套、期间，指定流水号的凭证)
*relative_path,相对于站点根目录tms的路径，与selectDate的相对路径一个意思
*title 打开IE窗口的标题
*lsh  需要打印的凭证流水号，多个时逗号隔开
*/
function pzprint(relative_path,lsh)
{
	var param = "lsh="+lsh;
	if(!confirm("是否打印凭证?")) return;
	window.open(relative_path+'gl_jsps/gl_pz/gl_pzdy/lshquery.jsp?actionName=doMyQueryLsh&'+param,'pzdy','top=0,left=0,width=1024,height=768,location=no,resizable=yes,scrollbars=yes,status=no,toolbar=no,menubar=no');
	
}


/*关联方设置*/
function GLFSet(obj,val)
{
	var tmpvalue;
	for (i=0;i<obj.length;i++)
	{
		tmpvalue = obj.options[i].value;
		tmpvalue = tmpvalue.substr(3,2);		
		if (tmpvalue==val)
		{
			obj[i].selected = true;
			return;
		}
	}
}