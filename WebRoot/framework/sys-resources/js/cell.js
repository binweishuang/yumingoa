function WebCell(cell)
{
	this.sheet = 0;
	this.lockMenuChecked = false;
	this.cellCtrl = cell;
	this.cellCtrl.Border= 0;
	this.cellCtrl.WorkbookReadonly = true;
	this.cellCtrl.Login("江苏省电信科学技术研究院","","13100105482","2760-0710-0346-7005");
}

/**
	得到Cell控件对象
**/
WebCell.prototype.GetCellCtrl = function()
{
	return this.cellCtrl;
}

/**
	设置Border
**/
WebCell.prototype.SetBorder = function(bd /*int*/)
{
	this.cellCtrl.Border = bd;
}

/**
	设置只读属性
**/
WebCell.prototype.SetReadOnly = function(rd /*true:只读;false:可写*/)
{
	this.cellCtrl.WorkbookReadonly = rd;
}

/**
	是否显示Sheet 标题
**/
WebCell.prototype.ShowSheetLabel = function(hd /*true:显示;false：不显示*/)
{
	if(hd)
	{
		this.cellCtrl.ShowSheetLabel(this.sheet, 1);
	}
	else
	{
		this.cellCtrl.ShowSheetLabel(this.sheet,0);
	}	
}

/**
	设置当前Sheet，从0开始
**/
WebCell.prototype.SetCurrentSheet = function(sheetIndex/*int*/)
{
	this.sheet = sheetIndex;
}

/**
	打开文件
**/
WebCell.prototype.OpenFile = function(fileName /*文件路径相对于当前页面的URL*/)
{
	//alert("to open file!");
	var ret = this.cellCtrl.OpenFile(fileName,"");
	if( ret == -1 )
	{
		alert("文件不存在!");
	}
	else if( ret == -2 )
	{
		alert("文件操作错误!");
	}
	else if( ret == -3 )
	{
		alert("文件格式错误!");
	}
	else if( ret == -4 )
	{
		alert("密码错误!");
	}
	else if( ret == -5 )
	{
		alert("不能打开高版本文件!");
	}
	else if( ret == -6 )
	{
		alert("不能打开特定版本文件!");
	}
	else if( ret == -99 )
	{
		alert("不能下载模板文件!");
	}		
}

/**
	设置单元格值，字符串类型
**/
WebCell.prototype.S = function(col /*列号，从0开始*/, row /*行号，从0开始*/, s/*值*/,note/*备注信息，可选*/)
{
		this.cellCtrl.S(col, row, this.sheet, s);
		this._setNote(col,row,note);
}

WebCell.prototype._setNote = function(col,row,note)
{
	if( note != undefined )
	{
		this.cellCtrl.SetCellNote(col,row,this.sheet,note);
	}
}

/**
	得到备注信息
**/
WebCell.prototype.GetNote = function(col /*列号，从0开始*/, row /*行号*/)
{
	return this.cellCtrl.GetCellNote(col,row,this.sheet);
}

/**
	设置单元格值，数字类型
	
**/
WebCell.prototype.D = function(col /*列号，从0开始*/, row /*行号，从0开始*/, d /*值*/,note /*备注信息，可选*/)
{
		this.cellCtrl.D(col,row, this.sheet, d);
		this._setNote(col,row,note);
}

/**
	设置单元格值，超链接
**/
WebCell.prototype.H = function(col /*列号，从0开始*/, row /*行号*/,txt/*链接文本*/, surl /*链接URL*/,note/*备注信息，可选*/)
{
		this.cellCtrl.H(col,row,this.sheet, txt,surl);
		this._setNote(col,row,note);
}

/**
	新增一行
**/
WebCell.prototype.NewRow=function()
{
	this.cellCtrl.InsertRow(this.cellCtrl.GetRows(this.sheet), 1, 0);
}

/**
	打印
**/
WebCell.prototype.PrintPreview = function()
{
	this.cellCtrl.PrintPreviewEx(0,this.sheet,false);
}

/**
	直接打印
**/
WebCell.prototype.Print = function()
{
	this.cellCtrl.PrintSheet(0,this.sheet);
}

/**
	导出为文件
**/
WebCell.prototype.Export = function()
{
	this.cellCtrl.ExportExcelDlg();
}

/**
	锁行、列
**/
WebCell.prototype.SetFixArea = function(col /*列号，从0开始；如果为-1，表示不锁*/, row /*行号；如果为-1，表示不锁*/)
{
	this.cellCtrl.SetFixedCol(1,col);	
	this.cellCtrl.SetFixedRow(1,row);
}


