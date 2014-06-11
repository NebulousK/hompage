/**
 * ajax모듈 파일
 */

function getXMLHttpRequest(){
	if(window.ActiveXObject){
		try{
			return new ActiveXObject("MSXML2.XMLHTTP");
		}
		catch(e){
			return new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	else if(window.XMLHttpRequest){
		return new XMLHttpRequest();
	}
	else{
		return null;
	}
}

function sendRequest(url, params, callback, method){
	XMLreq = getXMLHttpRequest();
	
	var httpMethod = method ? method : "GET"; // method에 값이 있다면 method값으로 대입 , 값이 없다면 GET으로 대입
	if(httpMethod != "GET" && httpMethod != "POST"){
		httpMethod = "GET";
	}
	
	var httpParams = (params == null || params == "") ? null : params;
	
	var httpUrl = url;
	if(httpMethod == "GET" && httpParams != null){
		httpUrl = httpUrl + "?"+ httpParams;
	}
	
	XMLreq.onreadystatechange = callback;
	XMLreq.open(httpMethod, httpUrl, true);
	XMLreq.setRequestHeader("Content-Type", "application/x-www-form-urlencoded; charaset=UTF-8");//POST방식 일시 쓴다.
	XMLreq.send(httpMethod == "POST" ? httpParams : null);// A ? B : C  --> A면 B, A아니면 C
}

/*function callback(){
	if(XMLreq.readyState == 4){
		if(XMLreq.status == 200){
			alert(XMLreq.responseText);
		}
		else{
			alert(XMLreq.status);
		}
	}
}*/
