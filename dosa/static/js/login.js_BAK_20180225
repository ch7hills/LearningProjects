
$(document).ready(function(){ 
/*	if (loginRep.length > 0) {
		$("#credentialsError").show();
		$("#errorDisplay").hide();
		document.getElementById('username').value = inputUName; 
	 }
	 else {
		 $("#credentialsError").hide();
		 $("#errorDisplay").hide();
	 }
	$('#password,#username').keypress(function(e){
		var key = e.which;
		if(key == 13)  // the enter key code
		  {
		    $('#loginid').trigger('click');
		    return false;  
		  }
	});*/ 
	$.ajax({
		type: "POST",
		url: '/login/generateOTP/',
		data: {},
		success: function (data){
			if(data){
				console.log(data);
			}
		}
	});
});
function loginValidation()
{
	alert("popopopo");
	var status = true;
	var errorMsg = new Array();
	if($.trim($("#username").val()) == ''){
	    errorMsg.push("Username is required");
	}
	if($.trim($("#password").val()) == ''){
		errorMsg.push("Password is required");
	}
	var htmlData = '';
	if (errorMsg.length != 0) {
		$.each(errorMsg,function(i,v){
			htmlData += '<li>'+v+'</li>';
		});
		status = false
		$("#errorDisplay").show();
		$("#credentialsError").hide();
		$("#inputVal").html(htmlData);
	}
	else {
		$("#errorDisplay").hide();
	}
	return status
}
