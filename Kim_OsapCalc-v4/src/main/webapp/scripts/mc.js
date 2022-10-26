function doSimpleAjax(address){
	var request = new XMLHttpRequest();
	var data ="";
	data += "principal="+document.getElementById("principal").value+"&";
	data += "interest="+document.getElementById("interest").value+"&";
	data += "period="+document.getElementById("period").value+"&";
	request.open("GET", address+"?"+data);
	request.onreadystatechange = function(){
		handler(request);
	}
	request.send();
}

function validate(){
	var ok = true;
	var p = document.getElementById("principal").value;
	if(isNaN(p) || p<=0){
		alert("Principal invalid!");
		document.getElementById("principal-error").style.color = "red";
		document.getElementById("interest-error").style.color = "black";
		document.getElementById("period-error").style.color = "black";
		ok = false;
	} else{
		document.getElementById("principal-error").style.color = "black";
	}
	
	if(!ok) return false;
	
	p = document.getElementById("interest").value;
	
	if(isNaN(p) || p<=0 || p>=100){
		alert("Invalid. Must be in (0,100).");
		document.getElementById("interest-error").style.color = "red";
		document.getElementById("principal-error").style.color = "black";
		document.getElementById("period-error").style.color = "black";
		ok = false;
	} else{
		document.getElementById("interest-error").style.color = "black";
	}
	
	if(!ok) return false;
	
	p = document.getElementById("period").value;
	
	if(isNaN(p) || p<=0){
		alert("Period invalid!");
		document.getElementById("period-error").style.color = "red";
		document.getElementById("principal-error").style.color = "black";
		document.getElementById("interest-error").style.color = "black";
		ok = false;
	} else{
		document.getElementById("period-error").style.color = "black";
	}
	
	return ok;
}

function handler(request){
	if ((request.readyState == 4) && (request.status == 200)){
		var target = document.getElementById("ajaxTarget");
	    target.innerHTML = request.responseText;
	    var rs =JSON.parse(request.responseText);
	    addPragraphs(target, rs.pr, rs.mp);
	}
}

function addPragraphs(parent, p1, p2){
	parent.innerHTML += "<br><br>Principal:"+p1+"<br><br>MonthlyPayment:"+p2;
}


 