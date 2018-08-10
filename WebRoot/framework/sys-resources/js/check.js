function round( num, n )
{
   var isN = isNaN(parseFloat(num));
   return isN?"0.00":parseFloat(num).toFixed(n);
}

function round1( num, n )
{
   var isN = isNaN(parseFloat(num));
   return isN?"0":parseFloat(num).toFixed(n);
}
/*
报错函数
*/
function message( obj, defaultValue, tag )
{
    var objstr = eval(obj);
    if ( tag == "int" )
    {
        alert("数据必须为整数型，请检查！");
    }
    else if ( tag == "num" )
    {
        alert("数据必须为数值型，请检查！");
    }
    else if ( tag == "date" )
    {
        alert("数据必须为日期型，请检查！");
    }
    else
    {
        alert("数据不符合要求，请检查！");
    }

    if ( defaultValue != null )
    {
        obj.value = defaultValue;
    }
    else
    {
        obj.value = "";
    }
    obj.focus();
}
/*

用于判断一个字符串所包含的字符是否都是整型

*/
function isAllInt( obj, defaultValue, length )
{
    var string = obj.value;
    if ( (length != null) && (length != "") )
    {
        if ( string.length > length )
        {
            alert("数值的长度必须小于" + length);
            if ( defaultValue != null )
            {
                obj.value = defaultValue;
            }
            return false;
        }
    }
    for ( var i = 0; i < string.length; i++ )
    {
        var integer = parseInt(string.substring(i, i + 1));
        if ( isNaN(integer) )
        {
            message(obj, defaultValue, "int");
            if ( defaultValue != null )
            {
                obj.value = defaultValue;
            }
            return false;
        }
    }
}
/*
isInteger: 用于判断一个数字型字符串是否为整型，
还可判断是否是正整数或负整数，返回值为true或false
obj：需要判断的对象
sign: 若要判断是正负数是使用，是正用'+'，负'-'，不用则表示不作判断
nullOrNot：表示判断对象是否允许为空，允许用'true'，不允许用'false'，不传参数表示允许为空
defaultValue:表示缺省值，当数据不符合要求时，用此缺省值代替
*/
function isInteger( obj, defaultValue, length, sign, nullOrNot )
{
    var integer;
    var string = obj.value;
    if ( (length != null) && (length != "") )
        if ( string.length > length )
        {
            alert("数值的长度必须小于" + length);
            if ( defaultValue != null )
            {
                obj.value = defaultValue;
            }
            return false;
        }
    if ( nullOrNot == false )
    {
        if ( string == null )
        {
            message(obj, defaultValue, "int");
            return false;
        }
    }
    if ( (sign != null) && (sign != '-') && (sign != '+') )
    {
        alert('isInteger(obj,string,sign)的参数出错：\nsign为null或"-"或"+"');
        if ( defaultValue != null )
        {
            obj.value = defaultValue;
        }
        return false;
    }
    integer = parseInt(string);
    if ( isNaN(integer) )
    {
        message(obj, defaultValue, "int");
        return false;
    }
    else
    {
        if ( integer.toString().length == string.length )
        {
            if ( (sign == null) || (sign == '-' && integer <= 0) || (sign == '+' && integer >= 0) )
            {
                return true;
            }
            else
            {
                message(obj, defaultValue, "int");
                return false;
            }
        }
        else
        {
            message(obj, defaultValue, "int");
            return false;
        }
    }
}

/*
isNumber: 用于判断一个数字型字符串是否为数值型，
还可判断是否是正数或负数，返回值为true或false
obj：需要判断的对象
sign: 若要判断是正负数是使用，是正用'+'，负'-'，不用则表示不作判断
nullOrNot：表示判断对象是否允许为空，允许用'true'，不允许用'false'，不传参数表示允许为空
defaultValue:表示缺省值，当数据不符合要求时，用此缺省值代替
*/
function isNumber( obj, defaultValue, sign, nullOrNot )
{
    var number;
    var string = obj.value;
    if ( nullOrNot == false )
    {
        if ( string == null )
        {
            message(obj, defaultValue, "num");
            return false;
        }
    }
    if ( (sign != null) && (sign != '-') && (sign != '+') )
    {
        alert('isNumber(obj,string,sign)的参数出错：\nsign为null或"-"或"+"');
        return false;
    }
    number = new Number(string);
    if ( isNaN(number) )
    {
        message(obj, defaultValue, "num");
        return false;
    }
    else if ( (sign == null) || (sign == '-' && number <= 0) || (sign == '+' && number >= 0) )
    {
        return true;
    }
    else
    {
        message(obj, defaultValue, "num");
        return false;
    }
}

/*
isDate: 用于判断一个字符串是否是日期格式的字符串
返回值为true或false
obj：需要判断的对象
dilimeter ： 日期的分隔符，缺省值为'-'
nullOrNot：表示判断对象是否允许为空，允许用'true'，不允许用'false'，不传参数表示允许为空
defaultValue:表示缺省值，当数据不符合要求时，用此缺省值代替
*/
function isDate( obj, defaultValue, dateString, dilimeter, nullOrNot )
{
    var dateString = obj.value;
    if ( nullOrNot == false )
    {
        if (dateString == null )
        {
            message(obj, defaultValue, "date");
            return false;
        }
    }
    if ( dilimeter == '' || dilimeter == null )
        dilimeter = '-';
    var tempy = '';
    var tempm = '';
    var tempd = '';
    var tempArray;
    if ( dateString.length < 8 && dateString.length > 10 )
    {
        message(obj, defaultValue, "date");
        return false;
    }
    tempArray = dateString.split(dilimeter);
    if ( tempArray.length != 3 )
    {
        message(obj, defaultValue, "date");
        return false;
    }
    if ( tempArray[0].length == 4 )
    {
        tempy = tempArray[0];
        tempd = tempArray[2];
    }
    else
    {
        tempy = tempArray[2];
        tempd = tempArray[1];
    }
    tempm = tempArray[1];
    var tDateString = tempy + '/' + tempm + '/' + tempd + ' 8:0:0';
    //加八小时是因为我们处于东八区
    var tempDate = new Date(tDateString);
    if ( isNaN(tempDate) )
    {
        message(obj, defaultValue, "date");
        return false;
    }
    //modify by lulili parseInt(tempd,10)
    if ( ((tempDate.getUTCFullYear()).toString() == tempy) && (tempDate.getMonth() == parseInt(tempm,10) - 1) && (tempDate.getDate() == parseInt(tempd,10)) )
    {
        return true;
    }
    else
    {
        message(obj, defaultValue, "date");
        return false;
    }
}

/*
specialString: 用于判断一个字符串是否含有或不含有某些字符
obj：需要判断的对象
compare ： 比较的字符串(基准字符串)
belongOrNot： true或false，“true”表示string的每一个字符都包含在compare中，
“false”表示string的每一个字符都不包含在compare中
nullOrNot：表示判断对象是否允许为空，允许用'true'，不允许用'false'，不传参数表示允许为空
defaultValue:表示缺省值，当数据不符合要求时，用此缺省值代替
*/
function specialString( obj, compare, belongOrNot, defaultValue, nullOrNot )
{
    var string = obj.value;
    if ( nullOrNot == false )
    {
        if ( string == null )
        {
            message(obj, defaultValue, "str");
            return false;
        }
    }
    if ( (string == null) || (compare == null) || ((belongOrNot != null) && (belongOrNot != true) && (belongOrNot != false)) )
    {
        alert('function SpecialString(string,compare,belongOrNot)参数错误');
        return false;
    }
    if ( belongOrNot == null || belongOrNot == true )
    {
        for ( var i = 0; i < string.length; i++ )
        {
            if ( compare.indexOf(string.charAt(i)) == -1 )
            {
                message(obj, defaultValue, "str");
                return false
            }
        }
        return true;
    }
    else
    {
        for ( var i = 0; i < string.length; i++ )
        {
            if ( compare.indexOf(string.charAt(i)) != -1 )
            {
                message(obj, defaultValue, "str");
                return false
            }
        }
        return true;
    }
}

/*
判断是否是钱的形式
obj：需要判断的对象
errMsg：报错信息
defaultValue:表示缺省值，当数据不符合要求时，用此缺省值代替
*/
function isMoney( obj, defaultValue, errMsg )
{

    var moneyValue = obj.value;
    strRef = "1234567890.";
    if ( parseFloat(moneyValue) == "NaN" || parseFloat(moneyValue) < 0 )
    {
        alert("金额不能为负!");
        moneyValue = defaultValue;
        return false;
    }
    if ( moneyValue.indexOf('0') == 0&&moneyValue.length>1 )
    {
        if ( moneyValue.indexOf('0.') != 0 )
        {
            alert("整数金额不能以0开头！");
             obj.value= defaultValue;
            return false;
        }
    }
    for ( i = 0; i < moneyValue.length; i++ )
    {
        tempChar = moneyValue.substring(i, i + 1);
        if ( strRef.indexOf(tempChar, 0) == -1 )
        {
            message(obj, defaultValue);
            //			if (errMsg == null || errMsg =="")
            //				alert("数据不符合要求,请检查");
            //			else
            //				alert(errMsg);
            //			if(obj.type=="text")
            //				obj.focus();
            return false;
        }
        else
        {
            tempLen = moneyValue.indexOf(".");
            if ( tempLen != -1 )
            {
                strLen = moneyValue.substring(tempLen + 1, moneyValue.length);
                if ( strLen.length > 2 )
                {
                    message(obj, defaultValue);
                    //				    if (errMsg == null || errMsg =="")
                    //						alert("数据不符合要求,请检查");
                    //					else
                    //						alert(errMsg);
                    //					if(obj.type=="text")
                    //						obj.focus();
                    return false;
                }
            }
        }
    }
    return true;
}

/*
四舍五入方法
参数：
obj：对象
len：四舍五入到小数点后第几位
*/
function roundit( obj, len )
{
    var objValue = obj.value;
    var change;
    var lg = objValue.indexOf(".")
    if ( lg != -1 )
    {
        if ( lg + len + 1 >= objValue.length )
        {
            change = objValue.substring(0, lg) + objValue.substring(lg + 1, objValue.length)
        }
        else
        {
            change = objValue.substring(0, lg) + objValue.substring(lg + 1, lg + len + 1) + "." + objValue.substring(lg + len + 1, objValue.length);
        }
        change = parseFloat(change) + 0.5;
        change = Math.floor(change);
        change = change.toString();
        if ( change.length < lg + len )
        {
            var jj = lg + len - change.length;
            if ( objValue < 1 )
            {
                change = "0" + change;
                for ( var i = 1; i < jj; i++ )
                {
                    change = change + "0";
                }
            }
            else
            {
                for ( var ii = 0; ii < jj; ii++ )
                {
                    change = change + "0";
                }
            }
        }
        if ( change.length > lg + len + 1 )
        {
            change = change.substring(0, lg) + "." + change.substring(lg, lg + len + 1);
        }
        else
        {
            change = change.substring(0, lg) + "." + change.substring(lg, change.length);
        }
    }
    obj.value = change;
}

/*
页面定位
*/
function scrollToY( height )
{
    //scrolly=document.body.scrollTop;
    window.scroll(0, height);
}

/*
整型数值校验
*/
function isInteger2( a )
{
    var b = /^[1-9]\d*$/;
    if ( !b.test(a.value) )
    {
        alert("非法数值，请重输！");
        a.value = "";
        return false;
    }
    return true;
}

/*
金额校验
*/
function isMoney2( a )
{
    var b = /^(-)?(\d)*(\.(\d){2})?$/;
    if ( !b.test(a.value) )
    {
        alert("非法金额，请重输！");
        a.value = "";
        return false;
    }
    return true;
}