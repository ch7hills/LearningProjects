jQuery(function($) {
$('#ErrorModal').html("");
$('#ErrorModal').hide();
$('body').on("click", "#register", function () {
	console.log("Hello Registration"+$('#frmregister').serialize());
	console.log("Hello txtName"+$('#txtName').val());
		$.ajax( {
			type: 'POST',
			url: '/register/registration',
			data: $('#frmregister').serialize(),
			async: false,
			beforeSend: function() {
				console.log("Before Send");
				if($('#txtName').val()==""){
					console.log("step1");
					$('#ErrorModal').html("Please enter name");
					$('#ErrorModal').show();
					$('#txtName').focus();
					return false;
				}
				if($('#ddlGender').val()=="0"){
					console.log("step4");
					$('#ErrorModal').html("Please enter gender");
					$('#ErrorModal').show();
					$('#ddlGender').focus();
					return false;
				}
				if($('#txtDob').val()==""){
					console.log("step1");
					$('#ErrorModal').html("Please enter valid Date of birth");
					$('#ErrorModal').show();
					$('#txtDob').focus();
					return false;
				}
				if($('#txtEmail').val()==""){
					console.log("step2");
					$('#ErrorModal').html("Please enter email");
					$('#ErrorModal').show();
					$('#txtEmail').focus();
					return false;
				}
				if(!emailValidate($('#txtEmail').val())){
					console.log("step22");
					$('#ErrorModal').html("Please enter a valid email");
					$('#ErrorModal').show();
					$('#txtEmail').focus();
					return false;
				}
				if($('#txtMobile1').val()==""){
					console.log("step3");
					$('#ErrorModal').html("Please enter mobile number");
					$('#ErrorModal').show();
					$('#txtMobile1').focus();
					return false;
				}
				if($('#txtMobile1').val().length !=10){
					console.log("step3");
					$('#ErrorModal').html("Please enter a valid mobile number");
					$('#ErrorModal').show();
					$('#txtMobile1').focus();
					return false;
				}
				if($('#applicationType').val()=="0"){
					console.log("step4");
					$('#ErrorModal').html("Please valid Application Type");
					$('#ErrorModal').show();
					$('#applicationType').focus();
					return false;
				}
			},
			complete: function() {
				console.log("complete");
			},
			success: function( data ) {
				console.log("Success");
				if(data.check ==""){
					window.location.href = data.url;
				}else if(data.check == "mobile"){
					$('#ErrorModal').html("Mobile number already exist");
					$('#ErrorModal').show();
				}else if(data.check == "email"){
					$('#ErrorModal').html("Email already exist");
					$('#ErrorModal').show();
				}
			}
		});
	});

$( 'body' ).on( 'click', '#profile_img', function() {
	$( '#id_image' ).trigger( 'click' );
	console.log("profile_img");
} );
/*$( 'body' ).on( 'click', '#renwalPay', function() {
	alert("Payment");
	$.ajax( {
		type: 'POST',
		url: '/renuwal/payment',
		data: $('#frmregister').serialize(),
		async: false,
		success:function(data){
			window.location.href = data.url;
		}
	});
} );*/
$( 'body' ).on( 'change', '#id_image', function() {
	console.log("id_image");
	$( "#frmUploadFile" ).ajaxForm( {
		success: function( data ) {
			console.log("frmUploadFile");
			if ( data.indexOf( "RestrictedToUpload:" ) >= 0 ) {
				var FileFailedReason = data.split( "RestrictedToUpload:" );
				$('#ErrorModal').html(FileFailedReason[ 1 ]);
				$('#reg_error').trigger( 'click' );
			} else {
				display_file(data);
			}
		}
	} ).submit();
} );
});
function display_file( data ) {
	console.log(data);
	if ( data ) {
		var filename="/static/images/team/"+data;
		$("#uploadimg_id").attr("src",filename);
		$("input#pic_name").val(filename);
	}
}
function emailValidate(email){
    var check = "" + email;
    if((check.search('@')>=0)&&(check.search(/\./)>=0)){
        if(check.search('@')<check.split('@')[1].search(/\./)+check.search('@')){ return true;}
        else {return false;}
    }else {return false;}
}