var req = false;
function initXmlHttpRequest() {
    req = false;
    if(window.XMLHttpRequest) {
    	try {
			req = new XMLHttpRequest();
        } catch(e) {
			req = false;
        }
    } else if(window.ActiveXObject) {
       	try {
        	req = new ActiveXObject("Msxml2.XMLHTTP");
      	} catch(e) {
        	try {
          		req = new ActiveXObject("Microsoft.XMLHTTP");
        	} catch(e) {
          		req = false;
        	}
		}
    }
}

var bExist = false;
function checkUrlExist(url2)
{
	bExist = false;
	if( req )
	{
	   	req.open("HEAD", url2,false);
	 	//req.onreadystatechange=function() {
		  	if (req.readyState==4) {
		   		if (req.status==200) bExist = true;
		    		else if (req.status==404) bExit = false;
		    		else bExist = false;
		  	}
	  	//}
	  	req.send(null)
	}
	
	return bExist;	
}