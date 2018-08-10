function stringToMap(str)
{
	var ary = str.split(",");
	var mp = new classMap();
	var len = ary.length;
	for(var i = 0 ; i < len; i++ )
	{
		var a = ary[i];
		var kv = a.split(":");
		if( kv.length > 1 )
		{
			var key = kv[0];
			var vl = kv[1];
			mp.put(key, vl);
		}	
	}
	return mp;
}

function struct(key, value){

  this.key = key;
  this.value = value;

}

function put(key, value){
  
  for (var i = 0; i < this.map.length; i++)
  {
    if ( this.map[i].key === key )
    {
      this.map[i].value = value;
      return;
    }
  }
  
  this.map[this.map.length] = new struct(key, value);

}

function get(key)
{
  for (var i = 0; i < this.map.length; i++)
  {
    if ( this.map[i].key === key )
    {
      return this.map[i].value;
    }
  }
  
  return null;
}

function remove(key)
{
  var v;
  for (var i = 0; i < this.map.length; i++)
  {
    v = this.map.pop();
    if ( v.key === key )
      continue;
      
    this.map.unshift(v);
  }
}

function count(){
  return this.map.length;
}

function isEmpty(){
  return this.map.length <= 0;
}

function classMap() {

  this.map = new Array();

  this.get = get;
  this.put = put;
  this.remove = remove;
  this.count = count;
  this.isEmpty = isEmpty;
}

