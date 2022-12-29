function reportStudents(address){
	var request = new XMLHttpRequest();
	var data = "&prefix="+document.getElementById("prefix").value+"&minCredit="+document.getElementById("minCredit").value;
	request.open("GET", address+data);
	request.onreadystatechange = function(){
		if ((request.readyState == 4) && (request.status == 200)){
			var target = document.getElementById("ajaxTarget");
			var resultJson = request.responseText;
	    	if(resultJson.length>0){
				var stdJson =JSON.parse(resultJson);
				var str = "";
				str += "<table><tr style='border : 1px solid black;'><th>Student Name</th><th>Credits taken</th><th>Credits to Graduate</th></tr>";
			    for(var i = 0; i<stdJson.students.length; i++){
					var student = stdJson.students[i];
					str += "<tr><td>"+student.name+"</td>";
					str += "<td>"+student.creditTaken+"</td>";
					str += "<td>"+student.creditGraduate+"</td></tr>";
				}
				str += "</table>";
				target.innerHTML = str;
			} else{
				target.innerHTML = "No matched student";	
			}
		}
	}
	request.send();
}

function validate(){
	return true;
}