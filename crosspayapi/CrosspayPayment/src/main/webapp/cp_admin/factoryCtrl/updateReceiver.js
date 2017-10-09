angular.module('crossPaymodule')
    .factory('updateReceiverFactory',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.update = function(data){ return $http.post($localStorage.server_baseurl+'/beneficiary/updateBeneficiary', data);}
		return factRes;
	}])
    .controller('updateReceiverCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'updateReceiverFactory', 'showAlert',
	function ($scope,$http, $route, $location, $localStorage, updateReceiverFactory, showAlert) {
        
		$scope.username = $localStorage.first_name;
		$scope.firstName = $localStorage.beneficiary.first_name;
		$scope.middleName = $localStorage.beneficiary.middle_name ;
		$scope.lastName = $localStorage.beneficiary.last_name ;
		$scope.country = $localStorage.beneficiary.country ;
		$scope.county = $localStorage.beneficiary.state;
		$scope.city = $localStorage.beneficiary.city;
		$scope.image = $localStorage.beneficiary.image;
		 $localStorage.img =  $localStorage.beneficiary.image;
		if($localStorage.beneficiary.gender == 'Male'){
			$('#Male').prop('checked', 'checked');
		}else{
			$('#Female').prop('checked', 'checked');
		}
		
		$('input[name=gender][value='+$localStorage.beneficiary.gender+']').prop('checked', 'checked');
		
		$scope.mobile = $localStorage.beneficiary.mobile;
		$scope.phone = $localStorage.beneficiary.phone_num;
		$scope.pincode = $localStorage.beneficiary.pincode;
		$scope.ifsc = $localStorage.beneficiary.ifsc;
		$scope.bank_name = $localStorage.beneficiary.bank_name;
		$scope.account_number = $localStorage.beneficiary.account_num;
		
		  $scope.JDAgreementIDuploadClicked = function(files) {
			  console.log("files.length  "+files.length);
			  var imagedata = null;
			   var image = null;
		        if (files && files.length) {
		        	var reader = new FileReader();
					   reader.readAsDataURL(files[0]);
					   console.log("base63  "+reader.result);
					   reader.onload = function () {
						   $scope.image = reader.result;
						//   $("#imagedata").val(reader.result);
						 //  image = $scope.image;
						   $scope.imagedata = $scope.image;
						   $localStorage.img = reader.result;
					     console.log("base63 svhs "+reader.result);
					   };
					   reader.onerror = function (error) {
					     console.log('Error: ', error);
					   };
		           /* $scope.JdfileExtension = files[0].name.replace(/^.*\./, '');
		            files[0].convertToBase64(function(base64) {
		                $scope.encoded = base64;
		                console.log("base64  "+$scope.encoded);
		                $scope.JDAgreementIDUpload = base64.substr(base64.lastIndexOf('base64,') + 7);
		            })*/
		        }
		    }
		
		$scope.UpdateReceiver = function(){
			var country=$("#country").val();
			var state=$("#state").val();
			var fname=$("#fname").val();
			//var mname=$("#mname").val();
			var lname=$("#lname").val();
			var mobile=$("#mobile").val();
			var image=$("#file-upload").val();
			var ifsc=$("#ifsc").val();
			var accountno =$("#accountno").val();
			var IBAN=$("#IBAN").val();
			var City=$("#City").val();
			var city=$("#city").val();
			var State1=$("#State1").val();
			var imagedata = null;
			imagedata = $localStorage.img;
			
			//console.log("image "+$localStorage.img);
			//	alert(City);
			//alert(State1);
			
			if(fname=='')
			{
				$("#fname_error").html("Please Enter Your First Name");
				return false;
				
			} else {
				
				$("#fname_error").html(" ");
			}
			
			
			if(lname=='')
			{
				$("#lname_error").html("Please Enter Your Last Name");
				return false;
				
			} else {
				
				$("#lname_error").html(" ");
			}
			
			
			
			if(country=='')
			{
				$("#country_error").html("Please Choose Your Country");
				return false;
				
			} else {
				
				$("#country_error").html(" ");
			}
			
			
			
			if(city=='')
			{
				$("#city_error").html("Please Enter Your City");
				return false;
				
			} else {
				
				$("#city_error").html(" ");
			}
			
			
			
			
			
			/*if(state=='')
			{
				$("#state_error").html("Please Enter Your State");
				return false;
				
			} else {
				
				$("#state_error").html(" ");
			}*/
			
		
			if(mobile=='')
			{
				$("#mobile_error").html("Please Enter Your Mobile Number");
				return false;
				
			} else {
				
				$("#mobile_error").html(" ");
			}
			if(mobile.length<9)
			{
				$("#mobile_error").html("Please Enter Valid Mobile Number");
				return false;
				
			} else {
				
				$("#mobile_error").html(" ");
			}
			
			/*if($("#Ccode").val()=='')
			{
				$("#mobile_error").html("Please Enter Zip Code");
				return false;
				
			} else {
				
				$("#mobile_error").html(" ");
			}*/
			
			/*
			var res=image.substring(image.lastIndexOf('.')+1).toLowerCase();
			
			
			
			
			if(image == '')
				{
					//alert("Please select Your Resume to upload"); 
			$("#resume_error").html("Please select Image to Upload");
			return false; 
			 } else
			 $("#resume_error").html("");  
			 
			 if(res=='png' || res=='jpg' )
			 {
				//alert("Thanks  for uploading your resume.");
				$("#resume_error1").html(''); 
			 }else
			 {

				$("#resume_error1").html("Please Select PNG,JPG Image format");
				return false; 
			 }
			
			*/
			var isChecked = $('#Bank').prop('checked');
					if(isChecked==true)
					{
						//alert("checked");
						if (ifsc=="")
						{
							//alert('enter bank name');
							$("#ifsc_error").html("Please Enter IFSC");
							return false;
						} else {
							
							//alert("Scuess");
							$("#ifsc_error").html("");
						}
						
						
						if (bankname=="")
						{
							$("#account_error").html("Please Enter Bank Name");
							return false;
						} else {
							
							$("#account_error").html(" ");
						}
						
						if (accountno=="")
						{
							$("#account_error").html("Account Number should be minimum 9 digits");
							return false;
						} else if (accountno.length<9){
							$("#account_error").html("Account Number should be minimum 9 digits");
							return false;
						}else {
							
							$("#account_error").html(" ");
						}
						
						
				
					}
			
			var isChecked= $('#Iban').prop('checked');
					if(isChecked==true)
					{
					if(IBAN=="")
								{
								$("#IBAN_error").html("Please Enter IBAN");	
									return false;
								} else {
								$("#IBAN_error").html(" ");		
								}
						
					}
			
			
				var isChecked= $('#cash').prop('checked');
					if(isChecked==true)
					{
						if(City=="")
								{
								$("#City_error").html("Please Enter City");	
									return false;
								} else {
								$("#City_error").html(" ");		
								}
						if(State1=="")
								{
								$("#State1_error").html("Please Enter State");	
									return false;
								} else {
								$("#State1_error").html(" ");		
								}
					}
			
				
			var json = {
					 "user_id": $localStorage.user_id,
					 "email":'',
					 "image":"default",
					 "city":city,
					 "mobile":mobile,
					 "first_name":fname,
					 "last_name":lname,
					 "gender":$("input[type='radio'][name='gender']:checked").val(),
					 "pincode":'crosspay123',
					 "phone":$("#Phone").val(),
					 "country":country,
					 "state":'crosspay123',
					 "call_name":fname+' '+$("#mname").val()+' '+lname,
					 "ccycode":"GBP",
					 "ifsc":ifsc,
					 "account_number":accountno,
					 "bank_name":$("#bankname").val(),
					 "middle_name": $("#mname").val(),
					 "beneficiary_no": $localStorage.beneficiary.beneficiary_no,
					 "image":imagedata
					}
			updateReceiverFactory.update(json).then(function(response){
				if(response.status == "200"){
					 $location.path('managereceiver');
				}
			});
			
		}
    }]);