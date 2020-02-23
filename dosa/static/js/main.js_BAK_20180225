jQuery(function($) {
	$('.scroll').removeClass('active');
	var preloader = $('.preloader');
	$(window).load(function(){
		preloader.remove();
	});

	var slideHeight = $(window).height();
	$('#home-slider .item').css('height',slideHeight);

	$(window).resize(function(){'use strict',
		$('#home-slider .item').css('height',slideHeight);
	});
	
	$(window).on('scroll', function(){
		if( $(window).scrollTop()>slideHeight ){
			$('.main-nav').addClass('navbar-fixed-top');
		} else {
			$('.main-nav').removeClass('navbar-fixed-top');
		}
	});
	
	
	$(window).scroll(function(event) {
		Scroll();
	});

	$('.navbar-collapse ul li a').on('click', function(e) { 
		 e.preventDefault();
		 var target = $(this).attr("href"); 
		 var flag=0;
		 console.log(target);
		 if(target=="/login/SendGrid" || target != "#"){
			 flag =1;
			 if(target != "#"){
				 window.location.href =target;
			 }
		 }
		 if(flag!=1){
			 var test = $(this.hash).offset().top -5;
			 console.log(test);
			 $('html, body').animate({scrollTop: test}, 1000);
		 }
		return false;
	});

	
	function Scroll() {
		var contentTop      =   [];
		var contentBottom   =   [];
		var winTop      =   $(window).scrollTop();
		var rangeTop    =   200;
		var rangeBottom =   500;
		$('.navbar-collapse').find('.scroll a').each(function(){
			contentTop.push( $( $(this).attr('href') ).offset().top);
			contentBottom.push( $( $(this).attr('href') ).offset().top + $( $(this).attr('href') ).height() );
		})
		$.each( contentTop, function(i){
			if ( winTop > contentTop[i] - rangeTop ){
				$('.navbar-collapse li.scroll')
				.removeClass('active')
				.eq(i).addClass('active');			
			}
		})
	};

	$('#tohash').on('click', function(){
		$('html, body').animate({scrollTop: $(this.hash).offset().top - 5}, 1000);
		return false;
	});
	
	
	new WOW().init();
	
	smoothScroll.init();
	
	
	$('#about-us').bind('inview', function(event, visible, visiblePartX, visiblePartY) {
		if (visible) {
			$.each($('div.progress-bar'),function(){
				$(this).css('width', $(this).attr('aria-valuetransitiongoal')+'%');
			});
			$(this).unbind('inview');
		}
	});

	
	$('#features').bind('inview', function(event, visible, visiblePartX, visiblePartY) {
		if (visible) {
			$(this).find('.timer').each(function () {
				var $this = $(this);
				$({ Counter: 0 }).animate({ Counter: $this.text() }, {
					duration: 2000,
					easing: 'swing',
					step: function () {
						$this.text(Math.ceil(this.Counter));
					}
				});
			});
			$(this).unbind('inview');
		}
	});


	$('#portfolio').on('click','.folio-read-more',function(event){
		event.preventDefault();
		var link = $(this).data('single_url');
		var full_url = '#portfolio-single-wrap',
		parts = full_url.split("#"),
		trgt = parts[1],
		target_top = $("#"+trgt).offset().top;

		$('html, body').animate({scrollTop:target_top}, 600);
		$('#portfolio-single').slideUp(500, function(){
			$(this).load(link,function(){
				$(this).slideDown(500);
			});
		});
	});

	
	$('#portfolio-single-wrap').on('click', '.close-folio-item',function(event) {
		event.preventDefault();
		var full_url = '#portfolio',
		parts = full_url.split("#"),
		trgt = parts[1],
		target_offset = $("#"+trgt).offset(),
		target_top = target_offset.top;
		$('html, body').animate({scrollTop:target_top}, 600);
		$("#portfolio-single").slideUp(500);
	});
	
	

	var form = $('#main-contact-form');
	form.submit(function(event){
		event.preventDefault();
		var form_status = $('<div class="form_status"></div>');
		$.ajax({
			url: $(this).attr('action'),
			beforeSend: function(){
				form.prepend( form_status.html('<p><i class="fa fa-spinner fa-spin"></i> Email is sending...</p>').fadeIn() );
			}
		}).done(function(data){
			form_status.html('<p class="text-success">Thank you for contact us. As early as possible  we will contact you</p>').delay(3000).fadeOut();
		});
	});
	
/*	var latitude = $('#google-map').data('latitude')
	var longitude = $('#google-map').data('longitude')
	function initialize_map() {
		var myLatlng = new google.maps.LatLng(latitude,longitude);
		var mapOptions = {
			zoom: 14,
			scrollwheel: false,
			center: myLatlng
		};
		var map = new google.maps.Map(document.getElementById('google-map'), mapOptions);
		var contentString = '';
		var infowindow = new google.maps.InfoWindow({
			content: '<div class="map-content"><ul class="address">' + $('.address').html() + '</ul></div>'
		});
		var marker = new google.maps.Marker({
			position: myLatlng,
			map: map
		});
		google.maps.event.addListener(marker, 'click', function() {
			infowindow.open(map,marker);
		});
	}
	google.maps.event.addDomListener(window, 'load', initialize_map);*/
	
	$( 'body' ).on( 'click', '#profile_img', function() {
		$( '#id_image' ).trigger( 'click' );
	} );
	$( 'body' ).on( 'change', '#id_image', function() {
		$( "#frmUploadFile" ).ajaxForm( {
			success: function( data ) {
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
	
	$( 'body' ).on( 'click', '#profile_img_gallery', function() {
		$( '#id_image_gallary' ).trigger( 'click' );
	} );
	$( 'body' ).on( 'change', '#id_image_gallary', function() {
		$( "#frmUploadFile_gallary" ).ajaxForm( {
			success: function( data ) {
				if ( data.indexOf( "RestrictedToUpload:" ) >= 0 ) {
					var FileFailedReason = data.split( "RestrictedToUpload:" );
					$('#ErrorModal').html(FileFailedReason[ 1 ]);
					$('#reg_error').trigger( 'click' );
				}
			}
		} ).submit();
	} );
	
	$(document).on("click", "#viewUser", function () {  
		var id = $(this).attr('data-id');
		console.log(id);
		$("#frmgenerateOTP input#uid").val(id); 
		$("#frmverifyOTP input#userid").val(id);
		console.log(isAdmin);
		if(isAdmin !="" && isAdmin !=undefined){
			$('[id*=verifyOTP]').trigger( 'click' );
		}	
	});
	$(document).on("click", "#dopost", function () {  	
		if(isAdmin !="" && isAdmin !=undefined){
			$('[id*=verifyOTP]').trigger( 'click' );
		}		
	});
	$(document).on("click", "[id*=approveUser]", function () {  
		var user = $(this).attr('data-user');
		var id = $(this).attr('data-id');
		console.log(id);
		$.ajax( {
			type: 'GET',
			url: '/login/approve/'+id,
			success: function( data ) {
				window.location.href = "/login/SendGrid/";
			}
		} );
	});
	$(document).on("click", "#register", function () {  
		$.ajax( {
			type: 'POST',
			url: '/login/registration/',
			data: $('#frmregister').serialize(),
			beforeSend: function() {
				if($('#reg_name').val()==""){
					$('#ErrorModal').html("Please enter name");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#reg_email').val()==""){
					$('#ErrorModal').html("Please enter email");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#reg_phone').val()==""){
					$('#ErrorModal').html("Please enter mobile number");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#date_joining').val()=="" || typeof($('#date_joining').val())=="number"){
					$('#ErrorModal').html("Please enter only year of joined");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#date_joining').val()=="" || typeof($('#date_joining').val())=="number"){
					$('#ErrorModal').html("Please enter only year of joined (Ex:1947)");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#date_joining').val().length != 4){
					$('#ErrorModal').html("Please enter valid year of joined (Ex:1947)");
					$('#reg_error').trigger( 'click' );
					return false;
				}
			},
			complete: function() {
			},
			success: function( data ) {
				if(data.check ==""){
					window.location.href = data.url;
				}else if(data.check == "mobile"){
					$('#ErrorModal').html("Mobile number already exist");
					$('#reg_error').trigger( 'click' );
				}else if(data.check == "email"){
					$('#ErrorModal').html("Email already exist");
					$('#reg_error').trigger( 'click' );
				}
			}
		} );
	});
	
	$(document).on("click", "#generateOTP", function () {  
		$.ajax({
			type: "POST",
			url: '/login/generateOTP/',
			data: $('#frmgenerateOTP').serialize(),
			beforeSend: function() {
				$('#otp').hide();
			},
			complete: function() {
			},
			success: function (data){
				if(data){
					$('[id*=enterPhoneClose]').trigger( 'click' );
					if(data.url !=""){
						$('#otp').hide();
						window.location.href = "/load_registration/";
						
					}
					console.log(data.record);
					console.log(data.record.phone);
					$('#otp_phone').val(data.record.phone);
					$('#phone_flag').val(flag);
					$("#frmdopost input#mobile").val(data.record.phone); 
					$('#otp').show();
					$('#displayOTP').trigger( 'click' );
				}
			}
		});
	});
	$(document).on("click", "#verifyOTP", function () { 
		$('#otp_flag').val(flag);
		viewUser();
	});
	$(document).on("click", "#sendEmail", function () {  
		$.ajax({
			type: "POST",
			url: '/login/sendMail/',
			data: $('#main-contact-form').serialize(),
			beforeSend: function() {
			},
			complete: function() {
			},
			success: function (data){
				if(data){
					window.location.href = data.url;
				}
			}
		});
	});
	
	$(document).on("click", "#editAccount", function () {  
		$.ajax( {
			type: 'POST',
			url: '/login/editAccount/',
			data: $('#frm_edit_register').serialize(),
			beforeSend: function() {
				if($('#user_name #user_name').val()==""){
					$('#ErrorModal').html("Please enter name");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#user_email #user_email').val()==""){
					$('#ErrorModal').html("Please enter email");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#phone_number #phone_number').val()==""){
					$('#ErrorModal').html("Please enter mobile number");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#user_doj #user_doj').val()==""){
					$('#ErrorModal').html("Please enter year of joined (Ex:1947)");
					$('#reg_error').trigger( 'click' );
					return false;
				}
				if($('#user_doj #user_doj').val().length !=4){
					$('#ErrorModal').html("Please enter valid year of joined (Ex:1947)");
					$('#reg_error').trigger( 'click' );
					return false;
				}
			},
			complete: function() {
			},
			success: function( data ) {
				window.location.href = data.url;
			}
		} );
	});
	
});
function viewUser(){
	$.ajax({
		type: "POST",
		url: '/login/verifyOTP/',
		data: $('#frmverifyOTP').serialize(),
		beforeSend: function() {
			$('#userView1').hide();
			$("#user_name").html('');
			$("#user_fame").html('');
			$("#user_email").html('');
			$("#user_dob").html('');
			$("#user_doj").html('');
			$("#address").html('');
			$("#most_liked").html('');
			$("#frequency_get_together").html('');
			$("#phone_number").html('');
			$("#other_frequency").html('');
			$("#profile_pic").attr("src",'/static/images/team/2.jpg');
			$("#uploadimg_id").attr("src",'/static/images/team/2.jpg');
			$("input#pic_name").val('/static/images/team/2.jpg');
		},
		complete: function() {
		},
		success: function (data){
			if(data){
				if(data.url !="" && flag == 0){
					window.location.href = data.url;
					return false;
				}else if(data.url !="" && flag == 1){
					window.location.href = "/login/message-board/";
					return false;
				}
				if (flag ==1 && data.url ==""){
					$( 'frmdopost#hidden_dopost').trigger( 'click' );	
					$.ajax({
						type: "POST",
						url: '/login/createpost/',
						data: $('#frmdopost').serialize(),
						beforeSend: function() {
							if($('#frmdopost #post').val()==""){
								$('#ErrorModal').html("Please enter post content");
								$('#reg_error').trigger( 'click' );
								return false;
							}
							if($('#frmdopost #mobile').val()==""){
								$('#ErrorModal').html("Please enter valid mobile number");
								$('#reg_error').trigger( 'click' );
								return false;
							}
						},
						success: function (data){
							window.location.href = "/login/message-board/";
							return false;
						}
					});
			    }
				$('#userView1').show();
				$('#enterOtpClose').trigger( 'click' );
				$('#displayUser').trigger( 'click' );
				$("#user_id").html('<input type="text" name="id" class="form-control" id="user_id" value="'+data.user.id+'" required>');
				$("#user_name").html('<input type="text" name="user_name" class="form-control" id="user_name" value="'+data.user.user_name+'" required>');
				$("#user_fame").html('<input type="text" name="father_name" class="form-control" id="user_fame" value="'+data.user.father_name+'">');
				$("#user_email").html('<input type="text" name="user_email" class="form-control" id="user_email" value="'+data.user.user_email+'" required>');
				$("#user_dob").html('<input type="text" name="user_dob" class="form-control" id="user_dob" value="'+data.user.user_dob+'" >');
				$("#user_doj").html('<input type="text" name="user_doj" class="form-control" id="user_doj" value="'+data.user.user_doj+'" >');
				$("#address").html('<input type="text" name="address" class="form-control" id="address" value="'+data.user.address+'" >');
				$("#most_liked").html('<input type="text" name="most_liked" class="form-control" id="most_liked" value="'+data.user.most_liked+'" >');
				$("#frequency_get_together").html('<input type="text" name="frequency_get_together" class="form-control" id="frequency_get_together" value="'+data.user.frequency_get_together+'">');
				$("#phone_number").html('<input type="text" name="phone_number" class="form-control" id="phone_number" value="'+data.user.phone_number+'" required>');
				$("#other_frequency").html('<input type="text" name="other_frequency" class="form-control" id="other_frequency" value="'+data.user.other_frequency+'">');
				if(data.user.profile_pic !="" && data.user.profile_pic != undefined ){
					$("#uploadimg_id").attr("src",data.user.profile_pic);
					$("input#pic_name").val(data.user.profile_pic);
				}
			}
		}
	});
}
function display_file( data ) {
	if ( data ) {
		var filename="/static/images/team/"+data;
		$("#uploadimg_id").attr("src",filename);
		$("input#pic_name").val(filename);
	}
}
