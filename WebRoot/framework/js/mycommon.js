
/*
* Common - 通用脚本函数
* Copyright (C) 2006 SinoSoft
* File Name: common.js
* 通用的脚本函数：包括脚本检测、字符串处理和通用后台处理的脚本函数
* File TrimDate:2006-07-21
*/

///////////////////////////////////////////////////////////////////////////////
//  字符、字符串检验处理
///////////////////////////////////////////////////////////////////////////////

/**
 * 判断字符串是否为空或空字符串
 */
function isNull(str) {
    if (str == null || str == "" || typeof str == "undefined")
        return true;
    return false;
}


/**
 * 检测字符串是否为数字或英文字母组成
 */
function isNumberOrAlphabet(str) {
    for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        if (c < 0x0030 || (c > 0x0039 && c < 0x0041) || (c > 0x005A && c < 0x0061) ||
            c > 0x007A)return false;
    }
    return true;
}

/**
 * 检测字符是否为汉字
 */
function isHanziChar(c) {
    var x = c.charCodeAt(0);
    return (x >= 0x4E00 && x <= 0x9FFF);
}

/**
 * 检测字符串是否由汉字组成
 */
function isHanzi(str) {
    for (var i = 0; i < str.length; i++)
        if (!isHanziChar(str.charAt(i)))return false;
    return true;
}

/**
 * 检测字符是否为半角字符
 */
function isHalfCornerChar(c) {
    var x = c.charCodeAt(0);
    return ((x <= 0x009F) || (x >= 0x00E0 && x <= 0x01FF && x != 0x00F7) ||
            (x >= 0xE000 && x <= 0xE7CF));
}

/**
 * 检测字符串是否由半角字符组成
 */
function isHalfCorner(str) {
    for (var i = 0; i < str.length; i++) {
        if (!isHalfCornerChar(str.charAt(i)))return false;
    }
    return true;
}

/*
用途：字符1是否以字符串2结束
输入：str1：字符串；str2：被包含的字符串
返回：如果通过验证返回true,否则返回false
*/

function isLastMatch(str1, str2) {
    var index = str1.lastIndexOf(str2);
    if (str1.length == index + str2.length)
        return true;

    return false;
}


/*
用途：字符1是否以字符串2开始
输入：str1：字符串；str2：被包含的字符串
返回：如果通过验证返回true,否则返回false
*/

function isFirstMatch(str1, str2) {
    var index = str1.indexOf(str2);
    if (index == 0)
        return true;

    return false;
}


/*
用途：字符1是包含字符串2
输入：str1：字符串；str2：被包含的字符串
返回：如果通过验证返回true,否则返回false
*/
function isMatch(str1, str2) {
    var index = str1.indexOf(str2);
    if (index == -1)
        return false;

    return true;
}


/**
 * 判断字符串是否为空，包括全角和半角空格
 */
function isBlank(str) {
    if (str == null || str == "")return true;
    var i = 0;
    while (str.charAt(i) == " " || str.charAt(i) == "　") i += 1;
    str = str.substring(i);
    i = str.length - 1;
    while (str.charAt(i) == " " || str.charAt(i) == "　") i -= 1;
    str = str.substring(0, i + 1);
    if (str == null || str == "")return true;
    return false;
}


/**
 * 去掉字符串前后的空格
 */
function trimSide(string) {
    var temp = "";
    var bl = false;
    //去掉前面部分的空格
    for (var i = 0; i < string.length; i++) {
        if (bl == true || string.charAt(i) != ' ') {
            temp = temp + string.charAt(i);
            bl = true;
        }
    }
    string = temp;
    temp = "";
    bl = false;

    //去掉后面的空格
    for (var i = string.length; i > 0; i--) {
        if (bl == true || string.charAt(i - 1) != ' ') {
            temp = string.charAt(i - 1) + temp;
            bl = true;
        }
    }
    return temp;
}

//去掉全部空格
function trim(string) {
    var temp = "";
    for (var i = 0; i < string.length; i++) {
        if (string.charAt(i) != ' ')
            temp = temp + string.charAt(i);
    }
    return temp;
}

/*检测是否有效的IP地址*/
function isIP(strIP) {
    var re = /^(\d+)\.(\d+)\.(\d+)\.(\d+)$/g //匹配IP地址的正则表达式

    if (re.test(strIP)) {
        if (RegExp.$1 < 256 && RegExp.$2 < 256 && RegExp.$3 < 256 && RegExp.$4 < 256) return true;
    }

    return false;
}

/*检测是否有效的手机号码*/
function checkMobile(s) {
    var regu = /^[1][3][0-9]{9}$/;
    var re = new RegExp(regu);

    if (re.test(s)) {
        return true;
    }
    else {
        return false;
    }
}


/**
 * 检测字符串是否为有效的EMAIL地址
 */
function isValidEmail(str) {
    if (str.indexOf("@") == -1 || str.indexOf(".") == -1)return false;
    if (str.indexOf("@") != str.lastIndexOf("@"))return false;
    var strDeny = '() < > @\\, ; :"[] ';
    var strLeft = str.substring(0, str.indexOf("@"));
    for (var i = 0; i < strLeft.length; i++) {
        if (strDeny.indexOf(strLeft.charAt(i)) > -1)return false;
    }
    var strRight = str.substring(str.indexOf("@") + 1);
    for (var i = 0; i < strRight.length; i++)
        if ("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ-.".
                indexOf(strRight.charAt(i)) == -1)return false;
    if (strRight.indexOf(".") == 0 || strRight.indexOf("-") == 0)return false;
    if (strRight.indexOf("-.") > -1 || strRight.indexOf(".-") > -1)return false;
    if (strRight.lastIndexOf(".") == strRight.length - 1)return false;
    if (strRight.lastIndexOf("-") == strRight.length - 1)return false;
    return true;
}

/**
 * 检测字符串是否为有效的英文字符
 */

function IsValidString(checkStr) {
    var checkOK =
            "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789_";
    var allValid = true;
    if (checkStr.length < 1) allValid = false;
    for (i = 0; i < checkStr.length; i++) {
        ch = checkStr.charAt(i);
        for (j = 0; j < checkOK.length; j++)
            if (ch == checkOK.charAt(j))break;
        if (j == checkOK.length) {
            allValid = false;
            break;
        }
    }
    return allValid;
}


/**
 * 检测字符是否为数字或英文字母
 * @param c 字符
 */
function isDigitOrAlphabetChar(c) {
    return ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z') ||
            (c >= 'A' && c <= 'Z'));
}


///////////////////////////////////////////////////////////////////////////////
//  数字和进制处理
///////////////////////////////////////////////////////////////////////////////

/**
 * 检测字符串是否为数字
 */
function isNumber(str) {
    for (var i = 0; i < str.length; i++)
        if (str.charCodeAt(i) < 0x0030 || str.charCodeAt(i) > 0x0039)return false;
    return true;
}

/*
报错函数
*/
function message(obj, defaultValue, tag)
{
    var objstr = eval(obj);
    if (tag == "int")
    {
        alert("数据必须为整数型,请检查！");
    }
    else if (tag == "num")
    {
        alert("数据必须为数值型,请检查！");
    }
    else if (tag == "date")
    {
        alert("数据必须为日期型,请检查！");
    }
    else
    {
        alert("数据不符合要求,请检查！");
    }

    if (defaultValue != null)
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
isInteger: 用于判断一个数字型字符串是否为整形，
还可判断是否是正整数或负整数，返回值为true或false
obj：需要判断的对象
sign: 若要判断是正负数是使用，是正用'+'，负'-'，不用则表示不作判断
nullOrNot：表示判断对象是否允许为空，允许用'true'，不允许用'false'，不传参数表示允许为空
defaultValue:表示缺省值，当数据不符合要求时，用此缺省值代替
*/
function isInteger(obj, defaultValue, length, sign, nullOrNot)
{
    var integer;
    var string = obj.value;
    if ((length != null) && (length != ""))
        if (string.length > length)
        {
            alert("数值的长度必须小于" + length);
            if (defaultValue != null)
            {
                obj.value = defaultValue;
            }
            return false;
        }
    if (nullOrNot == false)
    {
        if (string == null)
        {
            message(obj, defaultValue, "int");
            return false;
        }
    }
    if ((sign != null) && (sign != '-') && (sign != '+'))
    {
        alert('isInteger(obj,string,sign)的参数出错：\nsign为null或"-"或"+"');
        if (defaultValue != null)
        {
            obj.value = defaultValue;
        }
        return false;
    }
    integer = parseInt(string);
    if (isNaN(integer))
    {
        message(obj, defaultValue, "int");
        return false;
    }
    else if (integer.toString().length == string.length)
    {
        if ((sign == null) || (sign == '-' && integer <= 0) || (sign == '+' && integer >= 0))
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

/**
 * 数值型校验 精确到小数点后两位
 */
function checkNumber(obj, name, zeroFlag) {

    var str = obj.value;
    var flag = false;
    if (!zeroFlag) {            //sflag 判断是否可以为 0
        if (str == 0 || str == 0.0 || str == 0.00) {
            alert(name + "不能为 " + str + " !");
            obj.focus();
            return false;
        }
    }
    for (var i = 0; i < str.length; i++) {
        if (str.charAt(i) == ".") {
            flag = true;
        }
    }

    if (flag) {
        var re1 = /^0\.[0-9]{1,2}$/;
        var re2 = /^[1-9][0-9]{0,6}\.[0-9]{1,2}$/;
        if (re1.test(str) || re2.test(str)) {
            return true;
        }
        else {
            alert(name + "格式不对!");
            obj.focus();
            return false;
        }
    }
    else {
        var regu = /^[1-9][0-9]{0,6}$/;
        if (regu.test(str)) {
            return true;
        }
        else {
            alert(name + "格式不对!");
            obj.focus();
            return false;

        }
    }

    return true;
}

/**
 * 数值型校验 精确到小数点后两位
 */
function checkNumber_1(obj, name, zeroFlag) {
    var str = obj.value;
    if (trim(str) == "") {
        return true;
        //当没有值时，默认为0
    }
    var flag = false;
    if (!zeroFlag) {            //sflag 判断是否可以为 0
        if (str == 0 || str == 0.0 || str == 0.00) {
            alert(name + "不能为 " + str + " !");
            obj.focus();
            return false;
        }
    }
    for (var i = 0; i < str.length; i++) {
        if (str.charAt(i) == ".") {
            flag = true;
        }
    }

    if (flag) {
        var re1 = /^0\.[0-9]{1,2}$/;
        var re2 = /^[1-9][0-9]{0,6}\.[0-9]{1,2}$/;
        if (re1.test(str) || re2.test(str)) {
            return true;
        }
        else {
            alert(name + "格式不对!");
            obj.focus();
            return false;
        }
    }
    else {
        var regu = /^[1-9][0-9]{0,6}$/;
        if (regu.test(str)) {
            return true;
        }
        else {
            alert(name + "格式不对!");
            obj.focus();
            return false;

        }
    }

    return true;
}

/**
 * 正整数校验 不允许有正负数
 */
function checkInteger(obj) {
    var str = obj.value;
    var re1 = /^[0-9]$/
    var re2 = /^[1-9][0-9]{1,}$/;
    if (str.length > 1) {
        if (re2.test(str) == false) {
            alert('数据必须为整数！');
            obj.focus();
            return false;
        }
    }
    else {
        if (re1.test(str) == false) {
            alert('数据必须为整数！')
            obj.focus();
            return false;
        }
    }

    return true;
}

/**
 * 正整数校验 不允许有正负数
 */
function checkInteger(obj, obj_name) {
    var str = obj.value;
    var re1 = /^[0-9]$/
    var re2 = /^[1-9][0-9]{1,}$/;
    if (str.length > 1) {
        if (re2.test(str) == false) {
            alert(obj_name + '必须为整数！');
            obj.focus();
            return false;
        }
    }
    else {
        if (re1.test(str) == false) {
            alert(obj_name + '必须为整数！')
            obj.focus();
            return false;
        }
    }

    return true;
}
/**
 * 校验是否是正数
 */
function checkPlus(obj, name) {
    var str = obj.value;
    var re1 = /^[1-9]$/
    var re2 = /^[1-9][0-9]{1,}$/;
    if (str.length > 1) {
        if (re2.test(str) == false) {
            alert(name + '数据必须为正整数！');
            obj.focus();
            return false;
        }
    }
    else {
        if (re1.test(str) == false) {
            alert(name + '数据必须为正整数！')
            obj.focus();
            return false;
        }
    }
    return true;
}


/*
isNumber: 用于判断一个数字型字符串是否为数值型，
还可判断是否是正数或负数，返回值为true或false
obj：需要判断的对象
sign: 若要判断是正负数是使用，是正用'+'，负'-'，不用则表示不作判断
nullOrNot：表示判断对象是否允许为空，允许用'true'，不允许用'false'，不传参数表示允许为空
defaultValue:表示缺省值，当数据不符合要求时，用此缺省值代替
*/
function isNumber(obj, defaultValue, sign, nullOrNot)
{
    var number;
    var string = obj.value;
    if (nullOrNot == false)
    {
        if (string == null)
        {
            message(obj, defaultValue, "num");
            return false;
        }
    }
    if ((sign != null) && (sign != '-') && (sign != '+'))
    {
        alert('isNumber(obj,string,sign)的参数出错：\nsign为null或"-"或"+"');
        return false;
    }
    number = new Number(string);
    if (isNaN(number))
    {
        message(obj, defaultValue, "num");
        return false;
    }
    else if ((sign == null) || (sign == '-' && number <= 0) || (sign == '+' && number >= 0))
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
function isDate(obj, defaultValue, dateString, dilimeter, nullOrNot)
{
    var dateString = obj.value;
    if (nullOrNot == false)
    {
        if (string == null)
        {
            message(obj, defaultValue, "date");
            return false;
        }
    }
    if (dilimeter == '' || dilimeter == null)
        dilimeter = '-';
    var tempy = '';
    var tempm = '';
    var tempd = '';
    var tempArray;
    if (dateString.length < 8 && dateString.length > 10)
    {
        message(obj, defaultValue, "date");
        return false;
    }
    tempArray = dateString.split(dilimeter);
    if (tempArray.length != 3)
    {
        message(obj, defaultValue, "date");
        return false;
    }
    if (tempArray[0].length == 4)
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
    if (isNaN(tempDate))
    {
        message(obj, defaultValue, "date");
        return false;
    }
    if (((tempDate.getUTCFullYear()).toString() == tempy) && (tempDate.getMonth() == parseInt(tempm) - 1) && (tempDate.getDate() == parseInt(tempd)))
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
function specialString(obj, compare, belongOrNot, defaultValue, nullOrNot)
{
    var string = obj.value;
    if (nullOrNot == false)
    {
        if (string == null)
        {
            message(obj, defaultValue, "str");
            return false;
        }
    }
    if ((string == null) || (compare == null) || ((belongOrNot != null) && (belongOrNot != true) && (belongOrNot != false)))
    {
        alert('function SpecialString(string,compare,belongOrNot)参数错误');
        return false;
    }
    if (belongOrNot == null || belongOrNot == true)
    {
        for (var i = 0; i < string.length; i++)
        {
            if (compare.indexOf(string.charAt(i)) == -1)
            {
                message(obj, defaultValue, "str");
                return false
            }
        }
        return true;
    }
    else
    {
        for (var i = 0; i < string.length; i++)
        {
            if (compare.indexOf(string.charAt(i)) != -1)
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
function isMoney(obj, defaultValue, errMsg)
{
    var obj = eval(obj);
    strRef = "1234567890.";
    for (i = 0; i < obj.value.length; i++)
    {
        tempChar = obj.value.substring(i, i + 1);
        if (strRef.indexOf(tempChar, 0) == -1)
        {
            if (errMsg == null || errMsg == "")
                alert("数据不符合要求,请检查");
            else
                alert(errMsg);
            if (obj.type == "text")
                obj.focus();
            return false;
        }
        else
        {
            tempLen = obj.value.indexOf(".");
            if (tempLen != -1)
            {
                strLen = obj.value.substring(tempLen + 1, obj.value.length);
                if (strLen.length > 2)
                {
                    if (errMsg == null || errMsg == "")
                        alert("数据不符合要求,请检查");
                    else
                        alert(errMsg);
                    if (obj.type == "text")
                        obj.focus();
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
function roundit(obj, len)
{
    var objValue = obj.value;
    var change;
    var lg = objValue.indexOf(".")
    if (lg != -1)
    {
        if (lg + len + 1 >= objValue.length)
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
        if (change.length < lg + len)
        {
            var jj = lg + len - change.length;
            if (objValue < 1)
            {
                change = "0" + change;
                for (var i = 1; i < jj; i++)
                {
                    change = change + "0";
                }
            }
            else
            {
                for (var ii = 0; ii < jj; ii++)
                {
                    change = change + "0";
                }
            }
        }
        if (change.length > lg + len + 1)
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

/**
 * 检测字符串是否为有效的数字
 */
function IsValidNumber(checkStr) {
    checkStr = trimSide(checkStr);
    if (checkStr == "")return false;
    var checkOK = "0123456789";
    var allValid = true;
    if (checkStr.length < 1) allValid = false;
    for (i = 0; i < checkStr.length; i++) {
        ch = checkStr.charAt(i);
        for (j = 0; j < checkOK.length; j++)
            if (ch == checkOK.charAt(j))break;
        if (j == checkOK.length) {
            allValid = false;
            break;
        }
    }
    return allValid;
}


/**
 * 将二进制转换成十六进制
 */
function dechex(cval) {
    if (cval > 255) {
        cval = 255
    }
    ;
    hexascii = "0123456789ABCDEF";
    cval0 = Math.floor(cval / 16);
    cval1 = cval - (cval0 * 16);
    c1 = hexascii.charAt(cval0);
    c2 = hexascii.charAt(cval1);
    mystr = c1 + c2;
    return mystr;
}

/**
 * 将十六进制转换成二进制
 */
function hexdec(cval) {
    cval = cval.toUpperCase();
    var tval = 0;
    hexascii = "0123456789ABCDEF";
    for (c = 0; c < cval.length; c++) {
        mychar = cval.charAt(c);
        for (ch = 0; ch < 16; ch++) {
            if (mychar == hexascii.charAt(ch)) {
                tval = tval + ch;
                if (c < cval.length - 1) {
                    tval = tval * 16
                }
            }

        }

    }
    return tval;
}


/**
 * 检测字符串经过特殊符号转换后是否为有效的标准日期形式
 * 标准日期形式：年月日
 * @param str 需检验的字符串
 * @param sparator 用于分割字符串的字符
 */
function isDate(str, separator) {
    var strYear = "";
    var strMonth = "";
    var strDay = "";
    var iSeparatorCount = 0;
    for (var i = 0; i < str.length; i++) {
        var c = str.charAt(i);
        var cd = str.charCodeAt(i);
        if (cd >= 0x0030 && cd <= 0x0039) {
            switch (iSeparatorCount) {
                case 0:
                    strYear += c;
                    break;
                case 1:
                    strMonth += c;
                    break;
                case 2:
                    strDay += c;
                    break;
                default:
                    return false;
            }
        } else if (c == separator) {
            iSeparatorCount++;
            if (iSeparatorCount > 2)return false;
        } else
            return false;
    }
    if (strYear.length == 0 || strMonth.length == 0 || strDay.length == 0)return false;
    var iYear = parseInt(eval(strYear));
    var iMonth = parseInt(eval(strMonth));
    var iDay = parseInt(eval(strDay));
    //    if (iYear < 1900 || iYear > 2100)return false;
    if (iMonth < 1 || iMonth > 12)return false;
    if (iDay < 1)return false;
    var iFebDays = 28;
    if ((iYear % 400 == 0) || ((iYear % 4 == 0) && (iYear % 100 != 0))) iFebDays =
                                                                        29;
    switch (iMonth) {
        case 2:
            return (iDay <= iFebDays);
        case 4:
        case 6:
        case 9:
        case 11:
            return (iDay <= 30);
        default:
            return (iDay <= 31);
    }
}


/**
 * 在页面上输出系统当前时间
 */
function getCurrentTime() {
    today = new Date();
    year = today.getYear();
    month = today.getMonth() + 1;
    day = today.getDate();
    week = today.getDay();
    if (week == 0)
        week = "星期日"
    else if (week == 1)
        week = "星期一"
    else if (week == 2)
        week = "星期二"
    else if (week == 3)
        week = "星期三"
    else if (week == 4)
        week = "星期四"
    else if (week == 5)
        week = "星期五"
    else if (week == 6)
        week = "星期六"

    document.write("今日是" + year + "年" + month + "月" + day + "日　" + week)
}

function getStandardTodayDate()
{
    today = new Date();
    year = today.getYear();
    month = today.getMonth() + 1;
    day = today.getDate();
    if (month.length == 1)
    {
        month = "0" + month;
    }
    if (day.length == 1)
    {
        day = "0" + day;
    }
    return year + "-" + month + "-" + day;
}

///////////////////////////////////////////////////////////////////////////////
//  文件格式判断
///////////////////////////////////////////////////////////////////////////////

/**
 * 根据文件路径，判断该文件是否为视频类型文件
 */
function isVideo(path) {
    var suffix = ".RM,.AVI,.MPG,.MPEG,.WMV,.WAV,.MP3,.RMVB,.MIDI";
    if (path == null)return false;
    path = trim(path);
    var len = path.length;
    var tempSuffix = "";
    for (var i = len; i > 0; i--) {
        var c = path.charAt(i - 1);
        if (c == '.')break;
        else tempSuffix = c + tempSuffix;
    }
    tempSuffix = "." + tempSuffix.toUpperCase();
    if (suffix.indexOf(tempSuffix) > -1)return true;
    else return false;
}

/**
 * 根据文件路径，判断该文件是否为图片类型文件
 */
function isPicture(path) {
    var suffix = ".JPG,.GIF,.BMP,.TIF,.TIFF,.JPEG,.PNG";
    if (path == null)return false;
    path = trim(path);
    var len = path.length;
    var tempSuffix = "";
    for (var i = len; i > 0; i--) {
        var c = path.charAt(i - 1);
        if (c == '.')break;
        else tempSuffix = c + tempSuffix;
    }
    tempSuffix = "." + tempSuffix.toUpperCase();
    if (suffix.indexOf(tempSuffix) > -1)return true;
    else return false;
}


///////////////////////////////////////////////////////////////////////////////
//  窗口和对象处理
///////////////////////////////////////////////////////////////////////////////

/**
 * IFRAME的提交处理函数
 * @param frameName IFRAME的名称或ID
 * @param formName IFRAME指向的FORM名称或ID
 */
function submitIframe(frameName, formName) {
    document.frames(frameName).document.getElementById(formName).submit();
}

/**
 * FORM的提交处理函数
 * @param formName IFRAME指向的FORM名称或ID
 */
function submitForm(formName) {
    document.getElementById(formName).submit();
}

/**
 * 打开一个模式对话框
 */
function showmodalWindow(url) {
    var option = "scrollbars=1,resizable,status=0";
    window.open(url, "", option);
}

/**
 * 全屏打开窗口
 */
function fullwin(targeturl) {
    window.open(targeturl, "", "fullscreen,scrollbars");
}

/**
 * 打开一个新窗口
 * @param url    打开窗口的URL
 * @param width  打开窗口的宽度
 * @param height 打开窗口的高度
 */
function modalWindow(url, width, height) {
    var option = "Height=" + height + "px,scrollbars=0,Width=" + width +
                 "px,status=0,top=" + (screen.height - height) / 2 + ",left=" +
                 (screen.width - width) / 2;
    window.open(url, "", option);
}

/*20061010|wg|隐藏窗体*/
function modalWindow00(url, width, height) {
    var option = "Height=" + height + "px,scrollbars=0,Width=" + width +
                 "px,status=0,top=2000,left=2000";
    window.open(url, "", option);
}

/**
 * 根据弹出窗口的大小，获得窗口的居中位置
 */
function getPosition(width, height) {
    var leftPosition = 0;
    var screenWidth = screen.availWidth;
    if (screenWidth > width) {
        leftPosition = (screenWidth - width) / 2;
    }
    var topPosition = 0;
    var screenHeight = screen.availHeight;
    if (screenHeight > height) {
        topPosition = (screenHeight - height) / 2;
    }
    var returnValue = null;
{
    returnValue = "top=" + topPosition + ",left=" + leftPosition + ",width=" +
                  width + ",height=" + height;
}
    return returnValue;
}

/**
 * 打开一个模式对话框
 * @param url    打开窗口的URL
 * @param dialogWidth  打开窗口的宽度
 * @param dialogHeight 打开窗口的高度
 */
function modalDialog(url, dialogWidth, dialogHeight) {
    var parameter = "dialogWidth:" + dialogWidth + "em;dialogHeight:" +
                    dialogHeight + "em;status:0;scrolling:no;center:1;";
    returnValue = window.showModalDialog(url, modalDialog, parameter);
    return returnValue
}

/**
 * 打开一个模式对话框
 * @param url    打开窗口的URL
 * @param dialogWidth  打开窗口的宽度 - 像素
 * @param dialogHeight 打开窗口的高度 - 像素
 */
function modalDialogPx(url, dialogWidth, dialogHeight) {
    var parameter = "dialogWidth:" + dialogWidth + "px;dialogHeight:" +
                    dialogHeight + "px;status:0;scrolling:no;center:1;";
    returnValue = window.showModalDialog(url, modalDialog, parameter);
    return returnValue
}

/**
 * 打开人员选择或部门选择的模式对话框
 * @param url    打开窗口的URL 这里一般是JSP中的request.getContextPath();
 * @param it 对应页面 一般为this
 * @param obj id的保存对象名称
 * @param param url后的参数
 * 1为选择部门且只返回选中的第一个值
 * 2为选择部门返回选中所有值（以“，”拼接）
 * 3为选择人员且只返回选中的第一个值
 * 42为选择人员返回选中所有值（以“，”拼接）
 */

function setValue(url, it, obj, param) {
    var returnVal = new Array();
    var strTmp = it.value;
    var urlparam;
    if (param == '1') {
        urlparam = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&self=1&common=1';
    }
    if (param == '2') {
        urlparam = '/selectutil/selectgrp.jsp?getvaluetype=allparam&self=1&common=1';
    }
    if (param == '3') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=singleparam&self=1&common=1';
    }
    if (param == '4') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=allparam&self=1&common=1';
    }
    returnVal = modalDialog((url + urlparam), 50, 30);
    if (returnVal != null) {
        it.value = returnVal[0];
        var temp = document.getElementById(obj);
        temp.value = returnVal[1];
    }
}

/**
 * 打开人员选择或部门选择的模式对话框
 * @param url    打开窗口的URL 这里一般是JSP中的request.getContextPath();
 * @param it 对应页面 一般为this
 * @param obj id的保存对象名称 (保存选定用户id)
 * @param obj id的保存对象名称 (保存选定用户所属部门id)
 * @param param url后的参数
 * 1为选择部门且只返回选中的第一个值
 * 2为选择部门返回选中所有值（以“，”拼接）
 * 3为选择人员且只返回选中的第一个值
 * 42为选择人员返回选中所有值（以“，”拼接）
 */

function setUserValue(url, it, userId, grpId, param) {
    var returnVal = new Array();
    var strTmp = it.value;
    var urlparam;
    if (param == '3') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=singleparam';
    }
    if (param == '4') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=allparam';
    }
    returnVal = modalDialog((url + urlparam), 50, 30);
    if (returnVal != null) {
        it.value = returnVal[0];
        document.getElementById(userId).value = returnVal[1];
        document.getElementById(grpId).value = returnVal[3];
    }
}

/**
 * 打开人员选择或部门选择的模式对话框
 * @param url    打开窗口的URL 这里一般是JSP中的request.getContextPath();
 * @param it 对应页面 一般为this
 * @param obj id的保存对象名称
 * @param param url后的参数
 * 1为选择部门且只返回选中的第一个值
 * 2为选择部门返回选中所有值（以“，”拼接）
 * 3为选择人员且只返回选中的第一个值
 * 42为选择人员返回选中所有值（以“，”拼接）
 */

function setNameValue(url, it, obj, param) {
    var returnVal = new Array();
    var strTmp = it.value;
    var urlparam;
    if (param == '1') {
        urlparam = '/selectutil/selectgrp.jsp?getvaluetype=singleparam';
    }
    if (param == '2') {
        urlparam = '/selectutil/selectgrp.jsp?getvaluetype=allparam';
    }
    if (param == '3') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=singleparam';
    }
    if (param == '4') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=allparam';
    }
    returnVal = modalDialog((url + urlparam), 50, 30);
    if (returnVal != null) {
        it.value = returnVal[0];
        var temp = document.getElementById(obj);
        temp.value = returnVal[0];
    }
}

/**
 * 打开资产监管人员选择的模式对话框
 * @param url    打开窗口的URL 这里一般是JSP中的request.getContextPath();
 * @param it 对应页面 一般为this
 * @param obj id的保存对象名称
 * @param param url后的参数
 * 门返回选中所有值（以“，”拼接）
 * 改 因为需求变更 增加2个参数 人员分类和登陆用户ID 2007-3-1 rou
 *
 */

function setZCValue(url, userInfo, memberSort, userId) {
    var urlparam = '/zcjg/kjqj/user/selectuser.jsp?memberSort=' + memberSort + "&userId=" + userId;

    userInfo = modalDialogPx((url + urlparam), 600, 400);

    return userInfo;
}

/**
 * 打开人员选择或部门选择的模式对话框
 * @param url    打开窗口的URL 这里一般是JSP中的request.getContextPath();
 * @param it 对应页面 一般为this
 * @param obj id的保存对象名称
 * @param param url后的参数
 * 1为选择部门且只返回选中的第一个值
 * 2为选择部门返回选中所有值（以“，”拼接）
 * 3为选择人员且只返回选中的第一个值
 * 42为选择人员返回选中所有值（以“，”拼接）
 */

function setValueByGrpId(url, it, obj, param, grpId) {
    var returnVal = new Array();
    var strTmp = it.value;
    var urlparam;
    if (param == '1') {
        urlparam = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&deptId=' + grpId;
    }
    if (param == '2') {
        urlparam = '/selectutil/selectgrp.jsp?getvaluetype=allparam&deptId=' + grpId;
        ;
    }
    if (param == '3') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=singleparam&deptId=' + grpId;
        ;
    }
    if (param == '4') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=allparam&deptId=' + grpId;
        ;
    }


    returnVal = modalDialog((url + urlparam), 50, 30);
    if (returnVal != null) {
        it.value = returnVal[0];
        var temp = document.getElementById(obj);
        temp.value = returnVal[1];
    }
}

function setOrgValue(url, it, obj1, obj2, obj3, obj4, obj5) {
    var returnVal = new Array();
    var strTmp = it.value;
    var urlparam = '/selectutil/selectgrp.jsp?getvaluetype=singleparam';


    returnVal = modalDialog((url + urlparam), 50, 30);
    if (returnVal != null) {
        it.value = returnVal[0];
        var temp1 = document.getElementById(obj1);
        var temp2 = document.getElementById(obj2);
        var temp3 = document.getElementById(obj3);
        var temp4 = document.getElementById(obj4);
        var temp5 = document.getElementById(obj5);

        temp1.value = returnVal[1];
        temp2.value = returnVal[3];
        temp3.value = returnVal[4];
        temp4.value = returnVal[5];
        temp5.value = returnVal[6];

    }
}

/**
 * 选择子部门名称, deptId - 当前部门id
 */
function selectSubDeptName(context, nameObjId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);

    var url = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&deptId=' + deptId;
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        nameObj.value = returnVal[0];
    }
}


/**
 * 选择部门及其子部门名称, deptId - 当前部门id
 */
function selectGrpIdName(context, valueObjId, nameObjId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);

    var url = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&deptId=' + deptId + '&common=1';
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        nameObj.value = returnVal[0];
        valueObj.value = returnVal[1];
    }
}

/**
 * 选择子部门名称和编码, deptId - 当前部门id
 */
function selectSubDept(context, nameObjId, valueObjId, deptId, idObjectId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);
    var idObj = document.getElementById(idObjectId);
    var url = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&deptId=' + deptId + "&assets=1&self=1";
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (valueObj != null) {
            valueObj.value = returnVal[2];
        }
        if (idObj != null) {
            idObj.value = returnVal[1];
        }
    }
}

/**
 * 选择子部门名称和编码, deptId - 当前部门id
 */
function selectRoleDept(context, nameObjId, idObjectId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var idObj = document.getElementById(idObjectId);

    var url = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&deptId=' + deptId + '&common=1&self=1';
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (idObj != null) {
            idObj.value = returnVal[1];
        }
    }
    nameObj.focus();
}

function selectRoleDept2(context, nameObjId, idObjectId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var idObj = document.getElementById(idObjectId);

    var url = '/selectutil/selectgrp.jsp?getvaluetype=allparam&deptId=' + deptId + '&common=1&self=1';
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (idObj != null) {
            idObj.value = returnVal[1];
        }
    }
}





/**
 * 选择子部门用户, deptId - 当前部门
 */
function selectSubUser(context, nameObjId, valueObjId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);

    var url = '/selectutil/selectuser.jsp?getvaluetype=singleparam&deptId=' + deptId + '&assets=1&self=1';

    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (valueObj != null) {
            valueObj.value = returnVal[2];
        }
    }
}



/**
 * 选择子部门用户, deptId - 当前部门
 */
function selectSubUser2(context, nameObjId, valueObjId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);

    var url = '/selectutil/selectuser.jsp?getvaluetype=allparam&deptId=' + deptId + '&assets=1&self=1';

    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (valueObj != null) {
            valueObj.value = returnVal[2];
        }
    }
}
/**
 * 选择子部门用户, deptId - 当前部门
 */
function selectSubUser3(context, nameObjId, valueObjId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);

    var url = '/selectutil/selectuser.jsp?getvaluetype=allparam&deptId=' + deptId + '&assets=1&self=1';

    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (valueObj != null) {
            valueObj.value = returnVal[1];
        }
    }
}
/**
 * 获得所有专业知识依据，并返回专业分类的专业名和ID
 * treeflag 的宏定义在LawParaDefine中有定义
 * By Jack1982-2007-07-20 修改
 *
 * nameObjId专业分类名对象，idObjectId专业知识ID，itemidObj菜单下某条数据的PKid
 * treeflag宏定义 在类LawParaDefine中有定义
 * menuflag :true 菜单节点可选，false：菜单节点不可选(其中true和false是字符串)
 * 新增专业知识专用
 */
function selectLawCode(context, nameObjId, idObjectId, itemid, treeflag, menuflag,operType,studyType) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var idObj = document.getElementById(idObjectId);
    var itemidObj = document.getElementById(itemid)

    var url = '/selectutil/selectlawcode.jsp?getvaluetype=singleparam&treeflag=' + treeflag + "&menuflag=" + menuflag+"&operType="+operType+"&studyType="+studyType;
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (idObj != null) {
            idObj.value = returnVal[1];
        }
        if (itemidObj != null) {
            itemidObj.value = returnVal[2];
        }
    }
}


/**
 * 获得所有专业知识依据，并返回专业分类的专业名和ID  (可获得多个，也可获得单个)
 * treeflag 的宏定义在LawParaDefine中有定义
 * By mpl  2007-12-20 修改
 *
 * nameObjId专业分类名对象，idObjectId专业知识ID，itemidObj菜单下某条数据的PKid
 * treeflag宏定义 在类LawParaDefine中有定义
 * menuflag :true 菜单节点可选，false：菜单节点不可选(其中true和false是字符串)
 * getvaluetype 0 :选择单个；其他：选择多个；
 * allflag : 0:全部显示 ； 其他 ：只显示有试题的；
 */
function selectLawCodeNew(context, nameObjId, idObjectId, itemid, treeflag, menuflag , getvaluetype , allflag,operType,studyType) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var idObj = document.getElementById(idObjectId);
    var itemidObj = document.getElementById(itemid);
    if(getvaluetype == "0"){
        getvaluetype = "singleparam";
    }else{
        getvaluetype = "allparam";
    }
    var url = '/selectutil/selectlawcode.jsp?getvaluetype=' + getvaluetype + '&treeflag=' + treeflag + "&menuflag=" + menuflag + "&allflag=" + allflag+"&operType="+operType+"&studyType="+studyType;
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (idObj != null) {
            idObj.value = returnVal[1];
        }
        if (itemidObj != null) {
            itemidObj.value = returnVal[2];
        }
    }
}


function selectRolDeptMul(context, nameObjId, idObjectId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var idObj = document.getElementById(idObjectId);

    var url = '/selectutil/selectgrp.jsp?getvaluetype=allparam&deptId=' + deptId + '&common=1&self=1';
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (idObj != null) {
            idObj.value = returnVal[1];
        }
    }


}

/**
 * 选择子部门名称和编码, level
 */
function selectGrp(context, nameObjId, valueObjId, level) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);

    var url = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&level=' + level;
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (valueObj != null) {
            valueObj.value = returnVal[2];
        }
    }
}

/**
 * 选择子部门名称和编码, level
 */
function selectSubGrpInLevel(context, nameObjId, codeObjId, idObjId, level) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var codeObj = document.getElementById(codeObjId);
    var idObj = document.getElementById(idObjId);

    var url = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&level=' + level;
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (codeObj != null) {
            codeObj.value = returnVal[2];
        }
        if (idObj != null) {
            idObj.value = returnVal[1];
        }
    }
}

/**
 * 选择部门
 * context web路径
 * objName HTML对象名
 * type 返回值类型 name - 选择名称, id - 选择id
 */
function selectDept(context, objName, type) {
    var returnVal = new Array();
    var urlparam = '/selectutil/selectgrp.jsp?getvaluetype=singleparam';
    var obj = document.getElementById(objName);

    returnVal = modalDialog((context + urlparam), 50, 30);
    if (returnVal != null) {
        if (type == "name") {
            obj.value = returnVal[0];
        }
        else {
            obj.value = returnVal[1];
        }
    }
}

/**
 * 选择人员
 * context web路径
 * objName HTML对象名
 * type 返回值类型 name - 选择名称, id - 选择id
 */
function selectUser(context, objName, type) {
    var returnVal = new Array();
    var urlparam = '/selectutil/selectuser.jsp?getvaluetype=singleparam';
    var obj = document.getElementById(objName);

    returnVal = modalDialog((context + urlparam), 50, 30);
    if (returnVal != null) {
        if (type == "name") {
            obj.value = returnVal[0];
        }
        else {
            obj.value = returnVal[1];
        }
    }
}

/**
 * 选择子部门用户, deptId - 当前部门
 */
function selectSubUser(context, nameObjId, valueObjId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);

    var url = '/selectutil/selectuser.jsp?getvaluetype=singleparam&deptId=' + deptId + '&assets=1&self=1';

    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (valueObj != null) {
            valueObj.value = returnVal[2];
        }
    }
}

/**
 *
 */
function selectSubUserId(context, nameObjId, valueObjId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);

    var url = '/selectutil/selectuser.jsp?getvaluetype=singleparam&deptId=' + deptId + + '&assets=1&self=1';
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (valueObj != null) {
            valueObj.value = returnVal[1];
        }
    }
}

function selectSubUserIdYg(context, nameObjId, valueObjId, deptId) {
    var returnVal = new Array();
    var nameObj = document.getElementById(nameObjId);
    var valueObj = document.getElementById(valueObjId);

    var url = '/selectutil/selectuser.jsp?getvaluetype=singleparam&deptId=' + deptId;
    returnVal = modalDialog((context + url), 50, 30);

    if (returnVal != null) {
        if (nameObj != null) {
            nameObj.value = returnVal[0];
        }
        if (valueObj != null) {
            valueObj.value = returnVal[1];
        }
    }
}

/**
 * 打开武器库管理员选择或武器库选择的模式对话框
 * @param url    打开窗口的URL 这里一般是JSP中的request.getContextPath();
 * @param it 对应页面 一般为this
 * @param obj id的保存对象名称
 * @param param url后的参数
 * 1为选择武器库且只返回选中的第一个值
 * 2为选择武器库返回选中所有值（以“，”拼接）
 * 3为选择管理员且只返回选中的第一个值
 * 42为选择管理员返回选中所有值（以“，”拼接）
 */

function setStorageValue(url, it, obj, param, value) {
    var returnVal = new Array();
    var strTmp = it.value;
    var urlparam;
    if (param == '1') {
        urlparam = '/storageutil/selectstorage.jsp?storageId=' + value + '&getvaluetype=singleparam';
    }
    if (param == '2') {
        urlparam = '/storageutil/selectstorage.jsp?storageId=' + value + '&getvaluetype=allparam';
    }
    if (param == '3') {
        urlparam = '/storageutil/selectadmin.jsp?storageId=' + value + '&getvaluetype=singleparam';
    }
    if (param == '4') {
        urlparam = '/storageutil/selectadmin.jsp?storageId=' + value + '&getvaluetype=allparam';
    }


    returnVal = modalDialog((url + urlparam), 50, 30);
    if (returnVal != null) {
        it.value = returnVal[0];
        var temp = document.getElementById(obj);
        temp.value = returnVal[1];
    }
}


/**
 * 字典弹出选择框
 */
function dicPrompt(url, it, categoryid) {
    var strTmp = it.value;
    var urlparam = '/sysmanage/dicmanage/dicprompt.jsp?categoryid=' + categoryid;

    var returnVal = modalDialog((url + urlparam), 15, 16);
    if (returnVal != null) {
        it.value = returnVal;
    }
}

/**
 * 树形字典选择
 */
function dicTreeSelect(context, valueObjId, nameObjId, categoryid) {
    var urlparam = '/sysmanage/dicmanage/dicselectvalue.jsp?categoryid=' + categoryid;
    var returnVal = modalDialog((context + urlparam), 35, 30);

    if (returnVal != null) {
        var valueObj = document.getElementById(valueObjId);
        var nameObj = document.getElementById(nameObjId);

        valueObj.value = returnVal[0];
        nameObj.value = returnVal[1];
    }
}

/**
 * 树形字典选择
 * valueNums 要隐藏或者仅显示的字典项编号 {what a f request !!!}
 * type 仅显示或者隐藏
 */
function dicTreeSelectEx(context, valueObjId, nameObjId, categoryid, valueNums, type, deptId, unitId) {
    var urlparam = "";

    if (deptId == null) {
        deptId = "";
    }

    if (type == "show") {
        urlparam = '/sysmanage/dicmanage/dicselectvalue.jsp?categoryid=' + categoryid + "&show=" + valueNums + "&deptId=" + deptId;
    }
    else if (type == "hide") {
        urlparam = '/sysmanage/dicmanage/dicselectvalue.jsp?categoryid=' + categoryid + "&hide=" + valueNums + "&deptId=" + deptId;
    }
    else {
        urlparam = '/sysmanage/dicmanage/dicselectvalue.jsp?categoryid=' + categoryid + "&deptId=" + deptId;
    }

    var returnVal = modalDialog((context + urlparam), 35, 30);

    if (returnVal != null) {
        var valueObj = document.getElementById(valueObjId);
        var nameObj = document.getElementById(nameObjId);
        var unitObj = document.getElementById(unitId);
        if (valueObj != null) {
            valueObj.value = returnVal[0];
        }
        if (nameObj != null) {
            nameObj.value = returnVal[1];
        }
        if (unitObj != null) {
            unitObj.value = returnVal[2];
        }
    }
}

/**
 * 档案树形字典选择
 * 调用实例: onClick="dicTreeSelectArch('<%=request.getContextPath() %>', 'itemcode', 'miji', 3);"
 * @author jk
 * @param context
 *          工程跟路径,一般为"<%=request.getContextPath()%>"
 * @param valueObjId
 *          存放编号的页面元素Id
 * @param nameObjId
 *          存放名称的页面元素Id
 * @param categoryid
 *          字典类型,一般为宏定义
 * @param valueNums
 *          要隐藏或者仅显示的字典项编号
 * @param type
 *          "show" - 显示某一字典项及其子项 如: ("0201", "show"), "hide"-隐藏某些字典项,使用编号 ("0201, 0301", "hide")
 * @param deptId
 *          部门Id,用于设定部门-字典关联
 * @param ext
 *          字典项扩展属性,字符型
 */
function dicTreeSelectArch(context, valueObjId, nameObjId, categoryid, valueNums, type, deptId, ext) {
    var urlparam = "";

    if (deptId == null) {
        deptId = "";
    }

    if (type == "show") {
        urlparam = '/sysmanage/dicmanage/archives/arch_dicselectvalue.jsp?categoryid=' + categoryid + "&show=" + valueNums + "&deptId=" + deptId;
    }
    else if (type == "hide") {
        urlparam = '/sysmanage/dicmanage/archives/arch_dicselectvalue.jsp?categoryid=' + categoryid + "&hide=" + valueNums + "&deptId=" + deptId;
    }
    else {
        urlparam = '/sysmanage/dicmanage/archives/arch_dicselectvalue.jsp?categoryid=' + categoryid + "&deptId=" + deptId;
    }

    var returnVal = modalDialog((context + urlparam), 35, 30);

    if (returnVal != null) {
        var valueObj = document.getElementById(valueObjId);
        var nameObj = document.getElementById(nameObjId);
        var unitObj = document.getElementById(ext);
        if (valueObj != null) {
            valueObj.value = returnVal[0];
        }
        if (nameObj != null) {
            nameObj.value = returnVal[1];
        }
        if (unitObj != null) {
            unitObj.value = returnVal[2];
        }
    }
}

/**
 * 树形字典选择
 */
function dicTreeSelectByDept(context, valueObjId, nameObjId, categoryid, deptId, unitId) {
    var urlparam = '/sysmanage/dicmanage/dicselectvalue.jsp?categoryid=' + categoryid + "&deptId=" + deptId;
    var returnVal = modalDialog((context + urlparam), 35, 30);

    if (returnVal != null) {
        var valueObj = document.getElementById(valueObjId);
        var nameObj = document.getElementById(nameObjId);
        var unitObj = document.getElementById(unitId);
        if (valueObj != null) {
            valueObj.value = returnVal[0];
        }
        if (nameObj != null) {
            nameObj.value = returnVal[1];
        }
        if (unitObj != null) {
            unitObj.value = returnVal[2];
        }
    }
}

/**
 * 树形字典选择
 */
function dicTreeSelectAllByDept(context, valueObjId, nameObjId, categoryid, deptId, unitId) {
    var urlparam = '/sysmanage/dicmanage/dicselectvalue.jsp?selectAll=1&categoryid=' + categoryid + "&deptId=" + deptId;
    var returnVal = modalDialog((context + urlparam), 35, 30);

    if (returnVal != null) {
        var valueObj = document.getElementById(valueObjId);
        var nameObj = document.getElementById(nameObjId);
        var unitObj = document.getElementById(unitId);
        if (valueObj != null) {
            valueObj.value = returnVal[0];
        }
        if (nameObj != null) {
            nameObj.value = returnVal[1];
        }
        if (unitObj != null) {
            unitObj.value = returnVal[2];
        }
    }
}


/**
 * 选择时间
 * context web应用路径
 * objName 对象名
 */
function selectDate(context, objName)
{
    var returnVal = null;
    var obj = document.getElementById(objName);
    var strTmp = obj.value;
    returnVal = window.showModalDialog(context + "/common/selecttime.jsp?date=" + strTmp, null, "dialogHeight:270px;dialogWidth:300px;status:0;help:0;center:1");
    if (returnVal != null)
        obj.value = returnVal;
    else
        obj.value = strTmp;
}


/**
 * 隐藏进度条
 */
function hideprogressbar() {
    if (parent.wait != null) {
        parent.hideWait();
    }
}

/**
 * 用户离开当前页面提示
 * @param exittxt 提示信息内容
 */
function realexit(exittxt) {
    if (exittxt) event.returnValue = exittxt;
}


/**
 * 翻页函数（要求form名为：turnPage）
 * @param str 页面跳转参数
 * @param pageURL 指向页面 一般情况下此项可为""
 */

function goPage(str, pageURL) {
    var form = document.forms["turnPage"];
    //下一页
    if (str == "next") {
        form.j_CurPage.value = parseInt(form.j_CurPage.value) + 1;
    }
    //上一页
    if (str == "prev") {
        form.j_CurPage.value = parseInt(form.j_CurPage.value) - 1 == 0?1:parseInt(form.j_CurPage.value) - 1;

    }
    //首页
    if (str == "first") {
        form.j_CurPage.value = 1;
    }
    //尾页
    if (str == "last") {
        form.j_CurPage.value = form.j_TotalPages.value;
    }
    form.action = pageURL;
    form.submit();
}


/**
 * 得到选中的记录的ID，如果没有已选中的记录则提示“请选择”
 * @param activexName 复选框名称或ID，默认为"cbID"
 */
function getCheckedID(activexName) {
    var result = new Array();
    result[0] = 0;
    //被选中的checkbox个数
    result[1] = "";
    //被选中的checkbox的value列表，以逗号格开
    var defaultActivexName = "cbID";
    if (activexName != null && activexName != "") {
        defaultActivexName = activexName;
    }
    var allElement = document.getElementsByName(defaultActivexName);
    for (var i = 0; i < allElement.length; i++) {
        if (allElement[i].checked) {
            if (result[1] == "") {
                result[1] = allElement[i].value;
            } else {
                result[1] = result[1] + "," + allElement[i].value;
            }
            result[0]++;
        }
    }

    if (result[0] <= 0) {
        alert("请选择!");
    }
    return result[1];
}


/**
 * 对Checkbox的全选及全取消控制
 * @param boolean m true为全选择，false为全取消
 */
function selectAll(m) {
    for (var i = 0; i < document.all.length; i++) {
        var box = document.all[i];
        if (box.type + "" == "checkbox") box.checked = m;
    }
}

/**
 * 得到同名对象的个数，适用于Checkbox一类的对象
 * @param obj 对象
 */
function getLen(obj) {
    if (obj == null)
        return 0;
    if (obj.length == null) {
        obj[0] = obj;
        return 1;
    }
    return obj.length;
}

/**
 * 联动下拉框
 * @param far 父框
 * @param son 显示的子框
 * @param hidson为隐藏的子框
 * @param name 和父框value对应的option属性值
 * @param first_text为指定的第一个option的text
 */
function makesonselect(far, son, hidson, name, first_text) {
    son.innerHTML = "";
    if (getLen(far) == 0 || (getLen(hidson) == 0))
        return;
    else {
        var optionobj = document.createElement("option");

        if (first_text != null && first_text != "") {
            optionobj.value = "-1";
            optionobj.text = first_text;
            son.add(optionobj);
        }

        for (var i = 0; i < getLen(hidson); i++) {
            if (far.options[far.selectedIndex].label ==
                eval('hidson.options[i].' + name)) {
                optionobj = document.createElement("option");
                optionobj.value = hidson.options[i].value;
                optionobj.text = hidson.options[i].text;
                son.add(optionobj);
            }
        }
    }
}


function piselect(far, son, hidson, name, first_text) {
    son.innerHTML = "";
    if (getLen(far) == 0 || (getLen(hidson) == 0))
        return;
    else {
        var optionobj = document.createElement("option");

        if (first_text != null && first_text != "") {
            optionobj.value = "-1";
            optionobj.text = first_text;
            son.add(optionobj);
        }

        for (var i = 0; i < getLen(hidson); i++) {
            if (far.options[far.selectedIndex].label ==
                eval('hidson.options[i].' + name)) {
                optionobj = document.createElement("option");
                optionobj.value = hidson.options[i].value;
                optionobj.label = hidson.options[i].label;
                optionobj.parentId = hidson.options[i].parentId;
                optionobj.text = hidson.options[i].text;
                optionobj.ext1 = hidson.options[i].ext1;
                son.add(optionobj);
            }
        }
    }
}


function carselect(far, son, hidson, name, first_text) {
    son.innerHTML = "";
    if (getLen(far) == 0 || (getLen(hidson) == 0))
        return;
    else {
        var optionobj = document.createElement("option");

        if (first_text != null && first_text != "") {
            optionobj.value = "-1";
            optionobj.text = first_text;
            son.add(optionobj);
        }

        for (var i = 0; i < getLen(hidson); i++) {
            if (far.options[far.selectedIndex].label ==
                eval('hidson.options[i].' + name)) {
                optionobj = document.createElement("option");
                optionobj.value = hidson.options[i].value;
                optionobj.label = hidson.options[i].label;
                optionobj.parentId = hidson.options[i].parentId;
                optionobj.text = hidson.options[i].text;
                son.add(optionobj);
            }
        }
    }
}


/**
 * 设置当前表格某行的背景颜色
 *@author <a href="zhr_sh@skytech.com">zhr_sh</a>
 * @param objTbl  object 当前表格
 * @param rowIndex int 操作行序号
 *@param bgColorValue str 背景颜色
 *@param multi boolean  是否允许多行被修改，false 只修改当前行，修改后，其他置为初始色 true 只修改当前行其他不变
 *@return  void
 */
function changeRowBgColor(objTbl, rowIndex, bgColorValue, multi) {
    var currentRow = objTbl.rows(rowIndex);
    currentRow.bgColor = bgColorValue;
    if (!multi) {
        for (var i = 0; i < objTbl.rows.length; i++) {
            if (i == rowIndex || i == 0)continue;
            objTbl.rows(i).bgColor = "#FFFFFF";
        }
    }
}


/**
 * 获得表格某一格的内容字符串(innerText)
 *@author <a href="zhr_sh@skytech.com">zhr_sh</a>
 *@param tblName     str     表名
 *@param rowIndex int  行序号
 *@param columnIndex int 列序号
 *@return 返回字符串
 */
function getCell(tblName, rowIndex, columnIndex) {
    var tblObject = document.getElementById(tblName);
    if (tblObject == null) {
        return;
    }
    if (rowIndex < 0 && rowIndex > tblObject.rows.length) {
        return;
    }
    if (columnIndex < 0 && columnIndex > tblObject.rows(0).cells.length) {
        return;
    }
    return tblObject.rows(rowIndex).cells(columnIndex).innerText;
}

/**
 * 给已经存在的下拉框设置,同时修改已选项
 *@author <a href="zhr_sh@skytech.com">zhr_sh</a>
 *@param selectObj     obj     下拉框对象
 *@param value 需要设置的值
 *@return void
 */
function setSelectValue(selectObj, value) {
    var optionsArray = selectObj.options;
    for (var i = 0; i < optionsArray.length; i++) {
        if (value == optionsArray[i].value) {
            optionsArray[i].selected = true;
        }
    }
}

function checkYear(obj)
{

    if (isNumber(obj, "", "+", false)) {
        if ((obj.value < 0) || (obj.value > 9999)) {
            obj.value = "";
            obj.focus();
            alert("年份错误，请重新设置");
            return false;

        }
        if (obj.value.length != 4) {
            obj.focus();
            alert("年份必须为四位");

            return false;

            //obj.value = "0" + obj.value;
        }
        return true;
    }
    else
    {
        //alert("时间错误，请重新设置");
        return false;
    }
}

function checkMonth(obj)
{
    if (isNumber(obj, "", "+", false))
    {
        if ((obj.value < 1) || (obj.value > 12))
        {
            obj.value = "";
            obj.focus();
            alert("月份错误，请重新设置");

            return false;

        }
        if (obj.value.length != 2) {
            obj.value = "0" + obj.value;
            return true;
        }
        return true;

    }
    else
    {
        return false;
    }
}
function checkHour(obj)
{
    if (isNumber(obj, "", "+", false))
    {
        if ((obj.value < 0) || (obj.value > 23))
        {

            alert("时间错误，请重新设置");
            obj.value = "";
            obj.focus();
            return false;

        }
        if (obj.value.length == 1) {
            obj.value = "0" + obj.value;
        }

    }
    else
    {
        return false;
    }
}

function checkMinute(obj)
{
    if (isNumber(obj, "", "+", false))
    {
        if ((obj.value < 0) || (obj.value > 59))
        {
            alert("时间错误，请重新设置");
            obj.value = "";
            obj.focus();
            return false;

        }

        if (obj.value.length == 1) {
            //     alert('1');
            obj.value = "0" + obj.value;
        }
    }
    else
    {
        //alert("时间错误，请重新设置");
        return false;
    }
}


function checkTime(obj, obj1, obj2, obj3) {
    obj.value = obj1.value + " " + obj2.value + ":" + obj3.value + ":00";
}

/**
 * @author jk
 * 创建XMLHttpRequest对象
 */
function createXMLHttp() {

    if (typeof XMLHttpRequest != "undefined") {
        return new XMLHttpRequest();
    }
    else if (window.ActiveXObject) {
        var aVersions = ["MSXML2.XMLHttp.5.0",
                "MSXML2.XMLHttp.4.0",
                "MSXML2.XMLHttp.3.0",
                "MSXML2.XMLHttp",
                "Microsoft.XMLHttp"];

        for (var i = 0; i < aVersions.length; i++) {
            try {
                var oXmlHttp = new ActiveXObject(aVersions[i]);
                return oXmlHttp;
            }
            catch (oError) {
                //不做任何处理
            }
        }
        throw new Error("无法创建XMLHttpRequest对象!");
    }
}

/**
 * @author jk
 * JS打印级别、日志记录控制
 */
var JsPrintLevel = new function () {
    this.DEBUG = 0;
    //调试
    this.INFO = 1;
    //信息
    this.WORN = 2;
    //警告
    this.ERROR = 4;
    //错误

    this.LEVEL = this.INFO;
    //系统日志级别
    this.TO_LOG = 1;
    //输出到服务器日志
    this.SYS_OUT = 0;
    //输出到服务器控制台
};

/**
 * @author jk
 * 打印脚本信息到服务器
 */
function P2Serv(msg, level) {
    var oXmlHttp = createXMLHttp();

    //获取服务器根路径
    var context = window.location.href;
    var regex = /http:\/\/[^\/]*\/[^\/]*\//;
    regex = new RegExp(regex);
    context = regex.exec(context);

    var url = context + "jsprint?";

    //界别参数
    var levelParam = "level=";
    if (level == null || typeof level == "undefined") {
        level = JsPrintLevel.LEVEL;
    }
    levelParam += level;

    //如果日志级别小于系统定义不进行日志打印
    if (level > JsPrintLevel.LEVEL) {
        return;
    }

    url += levelParam;

    //alert(url);

    //信息参数
    while (msg.indexOf("/") != -1) {
        msg = msg.replace("/", "{pacthsep}");
    }

    while (msg.indexOf(" ") != -1) {
        msg = msg.replace(" ", "{blank}");
    }

    while (msg.indexOf("&") != -1) {
        msg = msg.replace("&", "{paramsep}");
    }

    var msgParam = "jsmsg=" + msg;
    url += "&" + msgParam;
    //alert(url);

    //是否进行日志
    var toLogParam = "tolog=" + JsPrintLevel.SYS_OUT;
    url += "&" + toLogParam;
    //alert(url);

    oXmlHttp.open("get", url, true);
    oXmlHttp.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
    oXmlHttp.onreadystatechange = function () {
        //alert("MSG SENDED!");
    }
    oXmlHttp.send(null);
}

/**
 * @author jk
 * 设置页眉页脚为空
 */
function PageSetup_Null() {
    var hkey_root,hkey_path,hkey_key;
    hkey_root = "HKEY_CURRENT_USER";
    hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";

    try {
        var RegWsh = new ActiveXObject("WScript.Shell") ;
        hkey_key = "header";
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
        hkey_key = "footer";
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "");
    }
    catch(e) {

    }
}

/**
 * @author jk
 * 设置页眉页脚为默认值
 */
function PageSetup_Default() {
    var hkey_root,hkey_path,hkey_key;
    hkey_root = "HKEY_CURRENT_USER";
    hkey_path = "\\Software\\Microsoft\\Internet Explorer\\PageSetup\\";

    try {
        var RegWsh = new ActiveXObject("WScript.Shell") ;
        hkey_key = "header";
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&w&b页码,&p/&P");
        hkey_key = "footer";
        RegWsh.RegWrite(hkey_root + hkey_path + hkey_key, "&u&b&d");
    }
    catch(e) {

    }
}


/****************判断日期类型是否符合标准****************/
/**************标准为：0000-00-00或者00000000************/
//returnName : 弹出框内容
//obj: 受判断的obj
//nullFlag : 可为空标志。  true：可为空 ；false:不可为空
function isBirthForZ(returnName, obj, nullFlag) {
    var dateValue = obj.value;
    if (nullFlag == true) {
        if (dateValue == "") {
            return true;
        }
    }
    if (nullFlag == false && dateValue == "") {
        alert("请填写" + returnName);
        return false;
    }
    var reg0 = /^[0-9]{6}$/;
    var reg1 = /^[0-9]{4}-[0-9]{1,2}$/;
    if (!dateValue.match(reg0) && !dateValue.match(reg1)) {
        alert(returnName + "格式不正确！");
        obj.focus();
        return false;
    }
    if (dateValue.match(reg0)) {
        dateValue = dateValue.substring(0, 4) + "-" + dateValue.substring(4, 6) + "-01";
    }
    var date = dateValue.split("-");
    dateValue = date[0] + "-" + (date[1].length < 2? 0 + date[1] : date[1]) + "-01";
    //    var now = new Date();
    //    var nowYear = new Number(now.getYear());
    //    year = new Number(dateValue.substring(0, 4));
    //    alert(dateValue);
    if (!isDate(dateValue, "-")) {
        alert(returnName + "格式不正确！");
        obj.focus();
        return false;
    }
    obj.value = dateValue;
    return true;
}

/**
 * @author jk
 *
 * 选择具有权限的用户
 * deptId 部门id
 * opCode 模块操作编号
 * userIdObjId 页面字段id
 * context 应用路径
 */
function selectUserByModelRole(context, deptId, opCode, userIdObjId, needAss, actInfoId) {
    P2Serv("[固定资产指派] 模块操作: " + opCode);

    var userIdObj = document.getElementById(userIdObjId);
    if (userIdObj == null || typeof userIdObj == "undefined") {
        P2Serv("[固定资产指派] userIdObj未找到!");
        return "cancel";
    }

    var actInfo = document.getElementById(actInfoId);
    if (actInfo == null || typeof actInfo == "undefined") {
        P2Serv("[固定资产指派] actInfo未找到!");
        return "cancel";
    }

    var url = "";
    var rv = null;
    if (needAss) {
        url = context + "/assetsmng/selectNextActUser.jsp?deptId=" + deptId + "&opCode=" + opCode + "&needAss=1";
        rv = modalDialog(url, 15, 30);
    }
    else {
        url = context + "/assetsmng/selectNextActUser.jsp?deptId=" + deptId + "&opCode=" + opCode;
        rv = modalDialog(url, 15, 15);
    }


    P2Serv("[固定资产指派] 返回值: " + rv);

    if (rv == null) {
        return "cancel";
    }

    userIdObj.value = rv[0];
    actInfo.value = rv[1];
}

//判断输入框的内容是否符合格式 0.00
//默认数据校验不校验负值，当sign 为 '-' 时可校验负数
//length 字段用来在正则式中表达正整数长度 ， 长度必须为正整数
function checkPointValue(obj, name, sflag, sign) {
    //    alert(length);
    var str = obj.value;
    var flag = false;
    if (!sflag) {            //sflag 判断是否可以为 0。  sflag 为false:不可为0
        if (str == 0 || str == 0.0 || str == 0.00) {
            alert(name + "不能为 " + str + " !");
            return false;
        }
    } else {
        if (str == 0 || str == 0.0 || str == 0.00) {
            return true;
        }
    }
    //判断正负标志位是否正确
    if ((sign != null) && (sign != '-') && (sign != '+'))
    {
        alert('checkPointValue(obj,name,sflag,length,sign)的参数出错：\nsign为null或"-"或"+"');
        return false;
    }
    var reg1 = /^[-|]?[1-9][0-9]{0,12}(\.[0-9]{1,2}){0,1}$/ ;
    var reg2 = /^[-|]?0\.[0-9]{1,2}$/;
    if (sign != '-') {
        reg1 = /^[1-9][0-9]{0,12}(\.[0-9]{1,2}){0,1}$/;
        reg2 = /^0\.[0-9]{1,2}$/;
    } else {
    }
    if (str.match(reg1) || str.match(reg2)) {
        return true;
    } else {
        alert(name + "格式不对!");
        obj.focus();
        return false;
    }
}
/** 输入日期自动加"-"
 *  msg指的是日期中文意思
 *  formObj指的是form表单
 *  obj指的是文本框的name
 */

function checkDate(msg, formObj, obj1) {
    var form = document.getElementById(formObj);

    obj = document.getElementById(obj1).value;
    if (isBlank(obj)) {
        return true;
    }
    //日期格式
    var expr0 = /^[0-9]{8}$/;
    var expr = /^[0-9]{4}-[0-9]{1,2}-[0-9]{1,2}$/;
    if (!obj.match(expr0) && ! obj.match(expr)) {
        alert("日期格式不正确!");
        document.getElementById(obj1).value = "";
        document.getElementById(obj1).focus();
        return false;
    }
    if (obj.match(expr0)) {
        obj = obj.substring(0, 4) + "-" + obj.substring(4, 6) + "-" + obj.substring(6, 8);
    }
    if (!isDate(obj, "-")) {
        alert("日期格式不正确");
        document.getElementById(obj1).value = "";
        document.getElementById(obj1).focus();
        return false;
    }

    var date = obj.split("-");
    date = date[0] + "-" + (date[1].length < 2? 0 + date[1] : date[1]) + "-" + (date[2].length < 2? 0 + date[2] : date[2]);

    document.getElementById(obj1).value = date;
    obj = date;

    var now = new Date();
    var year = now.getFullYear();
    var month = (now.getMonth() + 1) + "";
    date = now.getDate() + "";
    now = year + "-" + (month.length < 2 ? 0 + month : month) + "-" + (date.length < 2 ? 0 + date : date);

    if (now < obj) {
        alert(msg + "不能大于当前日期!");
        document.getElementById(obj1).value = "";
        document.getElementById(obj1).focus();
        return false;
    }
    return true;
}

/**
 * 打开人员选择或部门选择的模式对话框 (只查询处分县局单位)
 * @param url    打开窗口的URL 这里一般是JSP中的request.getContextPath();
 * @param it 对应页面 一般为this
 * @param obj id的保存对象名称
 * @param param url后的参数
 * 1为选择部门且只返回选中的第一个值
 * 2为选择部门返回选中所有值（以“，”拼接）
 * 3为选择人员且只返回选中的第一个值
 * 4为选择人员返回选中所有值（以“，”拼接）
 */

function setValueForChuDept(url, it, obj, param, level) {
    var returnVal = new Array();
    var strTmp = it.value;
    var urlparam;
    if (param == '1') {
        urlparam = '/selectutil/selectgrp.jsp?getvaluetype=singleparam&level=' + level;
    }
    if (param == '2') {
        urlparam = '/selectutil/selectgrp.jsp?getvaluetype=allparam&level=' + level;
    }
    if (param == '3') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=singleparam&level=' + level;
    }
    if (param == '4') {
        urlparam = '/selectutil/selectuser.jsp?getvaluetype=allparam&level=' + level;
    }
    returnVal = modalDialog((url + urlparam), 50, 30);
    if (returnVal != null) {
        it.value = returnVal[0];
        var temp = document.getElementById(obj);
        temp.value = returnVal[1];
    }
}

/**
 * 打开武器库管理员选择或武器库选择的模式对话框
 * @param url    打开窗口的URL 这里一般是JSP中的request.getContextPath();
 * @param quanzongNo 对应页面 全宗号码
 * @param quanzongName 对应页面 全宗名称
 * @param srotNo 对应页面 分类号码
 * @param deptId 对应页面 部门编号
 * @param deptName 对应页面 部门名称
 * @param userId 对应页面 用户编号
 * @param modelCode 操作模块编号
 * @param param 本用户权限级别
 * 1 为 普通用户
 * 2 为 部门用户
 * 3 为 全宗管理员或着档案管理员用户
 * 4 为 超级权利 = 能看到所有分类号，什么都不管
 * 5 为 个人能查看到的所有数据，将 1.2.3 的数据合并
 * 6 为 个人能查看到的所有管理员数据，将 2.3 的数据合并
 */

function setArchivesValue(url, quanzongNo, quanzongName, srotNo, deptId, deptName, userId, modelCode, param) {
    var returnVal = new Array();
    var urlparam = '/sortnoutil/selectsortno.jsp?userId=' + userId + '&modelCode=' + modelCode + '&param=' + param;

    returnVal = modalDialog((url + urlparam), 50, 30);
    if (returnVal != null) {
        if (quanzongNo != null) {
            document.getElementById(quanzongNo).value = returnVal[0];
        }
        if (quanzongName != null) {
            document.getElementById(quanzongName).value = returnVal[1];
        }
        if (srotNo != null) {
            if (returnVal[2] == "-1") {
                returnVal[2] = "";
            }
            document.getElementById(srotNo).value = returnVal[2];
        }
        if (deptId != null) {
            if (returnVal[3] == "-1") {
                returnVal[3] = "";
            }
            document.getElementById(deptId).value = returnVal[3];
        }
        if (deptName != null) {
            if (returnVal[4] == "-1") {
                returnVal[4] = "";
            }
            document.getElementById(deptName).value = returnVal[4];
        }
    }
}

//===判断当前字符串str是否是纯数字，true:包含零，flase:不包含零；
function isOnlyNum(str, flag) {
    var reg ;
    if (flag) {   //包含零
        reg = /^[0-9]\d{0,30}$/;
    } else {
        reg = /^[1-9]\d{0,30}$/;
    }
    if (!str.match(reg)) { //如果字符串不匹配正则式，则返回false,反之则true
        return false;
    }
    return true;
}
/*******************div特效脚本**********************/
function NeatDialog(sHTML, sTitle, bCancel)
{
    top.window.neatDialog = null;
    this.elt = null;
    if (top.document.createElement && top.document.getElementById)
    {
        var dg = top.document.createElement("div");
        dg.className = "neat-dialog";
        var leftmargin = (top.document.body.offsetWidth - 180) / 2;
        var topmargin = (top.document.body.offsetHeight - 30) / 2;

        dg.style.right = leftmargin;
        dg.style.bottom = topmargin;
        sHTML = '<p><div align="center"><img src="../images/wait.gif"/></div></p>';

        dg.innerHTML = sHTML;

        var dbg = top.document.createElement("div");
        dbg.id = "nd-bdg";
        dbg.className = "neat-dialog-bg";

        var dgc = top.document.createElement("div");
        dgc.id = "dgcdgc";
        dgc.className = "neat-dialog-cont";

        dgc.appendChild(dg);
        dgc.appendChild(dbg);
        top.document.body.appendChild(dgc);
        this.elt = dgc;
        dgc.focus();
        top.window.neatDialog = this;
        top.document.body.style.overflow = "visible";
    }
}

NeatDialog.prototype.close = function()
{
    if (this.elt)
    {
        this.elt.style.display = "none";
        this.elt.parentNode.removeChild(this.elt);
    }
    window.neatDialog = null;
    document.body.style.overflow = "";
}

function closeDiv(){
    if (top.document.getElementById("dgcdgc"))
    {
        top.document.getElementById("dgcdgc").style.display = "none";
        top.document.getElementById("dgcdgc").parentNode.removeChild(top.document.getElementById("dgcdgc"));
    }
    top.document.body.style.overflow = "";
}

function openDialog() {
    var sHTML = '正在执行，请稍候···';
    new NeatDialog(sHTML, "欢迎光临!", true);
}

