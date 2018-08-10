var exceptedItems = 100;
var itemId = "item";
var tableContainer = "tableContainer";
function relayout()
{
	relayoutTable(2);	
}
function relayoutTable(itemsPerTr)
{
	var tds = new Array();
	for(var i = 1; i < exceptedItems; i++ )
	{
		var div = document.getElementById(itemId + i);	
		if( div )
		{
			tds[tds.length] = div.innerHTML;
			div.removeNode(true);
		}
	}
	var len = tds.length;
	var tb = "<table class='search_table'>";
	for(var i = 1 ; i <= len; i++ )
	{
		if( i%itemsPerTr == 1 )
		{
			tb += "<tr>";
		}
		tb += tds[i-1];
		if( i%itemsPerTr ==0 && i>=itemsPerTr)
		{
			tb += "</tr>";
		}
	}
	var cols = len % itemsPerTr;
	if( cols > 0 )
	{
		cols = (itemsPerTr - cols) * 2;
		tb += "<td colspan='" + cols + "'></td></tr>";
	}
	tb += "</table>";
	var container = document.getElementById(tableContainer);
	if( container )
	{
		container.insertAdjacentHTML("beforeBegin",tb);
		container.removeNode(true);
	}
}