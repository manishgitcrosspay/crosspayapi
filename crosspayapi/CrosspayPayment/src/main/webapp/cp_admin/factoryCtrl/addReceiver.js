angular.module('crossPaymodule')
    .factory('addReceiverFactory',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.add = function(data){ return $http.post($localStorage.server_baseurl+'/beneficiary/add', data);}
		return factRes;
	}])
    .controller('addReceiverCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'addReceiverFactory', 'showAlert',
	function ($scope,$http, $route, $location, $localStorage, addReceiverFactory, showAlert) {

  $scope.username = $localStorage.first_name;
  
  
  $http.post($localStorage.server_baseurl+'/upload/getBank', {}).then(function(res){
                          
                                 if(res.data.status=='200') {
									 
									   var count =0;
									   
									   var arr = [];
									   
									   for(var i=0;i<res.data.bank.length;i++)
									   {
										   if(res.data.bank[i].bank_country_code=='IN'){
											   
											   // $scope.payoutdata = res.data.bank[i].bank_name;
												console.log($scope.payoutdata)
												
												 arr.push({
                                                   bank_name: res.data.bank[i].bank_name,
                                                   sortable: true,
                                                   resizeable: true
                                                   });
										 
											   
										   }
									   }
									   
									   $scope.payoutdata=arr;
									 
									 
									 
									 var arrs = [];
									   
									   for(var i=0;i<res.data.bank.length;i++)
									   {
										   if(res.data.bank[i].bank_country_code=='PH'){
											   
											   // $scope.payoutdata = res.data.bank[i].bank_name;
												console.log($scope.payoutdata)
												
												 arrs.push({
                                                   bank_name: res.data.bank[i].bank_name,
                                                   sortable: true,
                                                   resizeable: true
                                                   });
										 
											   
										   }
									   }
									   
									   $scope.payoutdatas=arrs;
									   
									   
									    var arrus = [];
									   
									   for(var i=0;i<res.data.bank.length;i++)
									   {
										   if(res.data.bank[i].bank_country_code=='US'){
											   
											   // $scope.payoutdata = res.data.bank[i].bank_name;
												console.log($scope.payoutdata)
												
												 arrus.push({
                                                   bank_name: res.data.bank[i].bank_name,
                                                   sortable: true,
                                                   resizeable: true
                                                   });
										 
											   
										   }
									   }
									   
									   $scope.payoutdataus=arrus;
									    
										 // console.log($scope.payoutdata)
										 
                                 }

                     },function(resp){
						// $rootScope.loading = false;
						 console.log(resp);});

		  $scope.getbase64 = function(file) {
			  console.log("file dsfhsfgdfj ");
			  console.log("file 2312 "+file);
				   var reader = new FileReader();
				   reader.readAsDataURL(file);
				   console.log("base63  "+reader.result);
				   reader.onload = function () {
					   $scope.image = reader.result;
					   $("#imagedata").val(reader.result);
				     console.log("base63 svhs "+reader.result);
				   };
				   reader.onerror = function (error) {
				     console.log('Error: ', error);
				   };
				}
		  
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
						   image = $scope.image;
							imagedata = $scope.image;
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
    
    	
		$scope.addReceiver = function(selectedName){
			
	
         try{	
		
		//alert(selectedName.bank_name);
		$scope.bname=selectedName.bank_name;
		
		 }catch(err)
		 {
			 $scope.bname='';
			 
		 }
		 
		
		
		
		console.log(selectedName);
		
			var country=$("#country").val();
			var state=$("#state").val();
			var fname=$("#fname").val();
			//var mname=$("#mname").val();
			var lname=$("#lname").val();
			var mobile=$("#mobile").val();
			var image=$("#file-upload").val();
			
				
			//var bankname =$("#bankname").val();
			//var banknameunited=$("#banknameunited").val();
			//var banknamephp=$("#banknamephp").val();
			
				var bankname =$scope.bname;
			var banknameunited=$scope.bname;
			var banknamephp=$scope.bname;
			
			
			var ifsc=$("#ifsc").val();
			var ifscunited=$("#ifscunited").val();
			//var ifscunited=$("#ifscunited").val();
			
			var branchname=$("#bank_name").val();
			var branchnameunited=$("#bank_nameunited").val();
			var branchnamephp=$("#bank_namephp").val();
			
			var accountno =$("#accountno").val();
			var accountnounited =$("#account_numberunited").val();
			var accountnophp =$("#account_numberphp").val();
			
			
			
			
			
			
			var IBAN=$("#IBAN").val();
			var City=$("#City").val();
			var city=$("#city").val();
			
			var State1=$("#State1").val();
			var imagedata = null;
			imagedata = $("#imagedata").val();
			//	alert(City);
			//alert(State1);
			

			
			
			if(fname=='')
			{
				$("#fname_error").html("Please Enter First Name");
				return false;
				
			} else {
				
				$("#fname_error").html(" ");
			}
			
			
			if(lname=='')
			{
				$("#lname_error").html("Please Enter Last Name");
				return false;
				
			} else {
				
				$("#lname_error").html(" ");
			}
			
			
			
			if(country=='')
			{
				$("#country_error").html("Please Select Country");
				return false;
				
			} else {
				
				$("#country_error").html(" ");
			}
			
			if(city=='')
			{
				$("#city_error").html("Please Enter City");
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
				$("#mobile_error").html("Please Enter Mobile Number");
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
						
						
						if(country=='UNITED STATES'){
							
							
							
							if (banknameunited=="")
						{
							//alert('enter bank name');
							$("#bank_error").html("Please Select Bank Name");
							return false;
						} else {
							
							//alert(banknameunited);
							$("#bank_error").html("");
						}
							
						}
						else if(country=='INDIA'){
							
							if (bankname=="")
						{
							//alert('enter bank name');
							$("#bank_error").html("Please Select Bank Name");
							return false;
						} else {
							
							//alert("Scuess");
							$("#bank_error").html("");
						}
							
						}else{
							
							
							//alert(banknamephp);
							if (banknamephp=="")
						{
							//alert('enter bank name');
							$("#bank_error").html("Please Select Bank Name");
							return false;
						} else {
							
							//alert("Scuess");
							$("#bank_error").html("");
						}
						}
						
						
						
						
						if(country=='UNITED STATES'){
						
						if (ifscunited=="")
						{
							//alert('enter bank name');
							$("#ifsc_error").html("Please Enter SWIFT CODE");
							return false;
						} else {
							
							//alert("Scuess");
							$("#ifsc_error").html("");
						}
						}else if(country=='INDIA')
						{
							
							if (ifsc=="")
						{
							//alert('enter bank name');
							$("#ifsc_error").html("Please Enter IFSC");
							return false;
						} else {
							
							//alert("Scuess");
							$("#ifsc_error").html("");
						}
						}
						
						if(country=='UNITED STATES'){
							
							if (branchnameunited=="")
						{
							$("#branch_error").html("Please Enter Branch Name");
							return false;
						} else {
							
							$("#branch_error").html(" ");
						}
							
						}else if(country=='INDIA'){
							
						
						if (branchname=="")
						{
							$("#branch_error").html("Please Enter Branch Name");
							return false;
						} else {
							
							$("#branch_error").html(" ");
						}
						
						}else{
							
							if (branchnamephp=="")
						{
							$("#branch_error").html("Please Enter Branch Name");
							return false;
						} else {
							
							$("#branch_error").html(" ");
						}
							
						}
						
						if(country=='UNITED STATES'){
							
							if (accountnounited=="")
						{
							$("#account_error").html("Account Number should be minimum 9 digits");
							return false;
						}else if (accountnounited.length<9){
							$("#account_error").html("Account Number should be minimum 9 digits");
							return false;
						} else {
							
							$("#account_error").html(" ");
						}
							
						}else if(country=='INDIA'){
						if (accountno=="")
						{
							$("#account_error").html("Account Number should be minimum 9 digits");
							return false;
						}else if (accountno.length<9){
							$("#account_error").html("Account Number should be minimum 9 digits");
							return false;
						} else {
							
							$("#account_error").html(" ");
						}
						}else{
							
							if (accountnophp=="")
						{
							$("#account_error").html("Account Number should be minimum 9 digits");
							return false;
						}else if (accountnophp.length<9){
							$("#account_error").html("Account Number should be minimum 9 digits");
							return false;
						} else {
							
							$("#account_error").html(" ");
						}
							
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
			
				console.log("image data "+imagedata);
				
				
				
				var banknamej='';
				var branchnamej='';
				var anoj='';
				var ifscj='';
				
		  
			
			if(country=='UNITED STATES'){
				banknamej = banknameunited;
				branchnamej = branchnameunited;
				anoj = accountnounited;
				ifscj = ifscunited;
			}else if(country=='INDIA'){
				banknamej = bankname;
				branchnamej = branchname; 
				anoj = accountno;
				ifscj = ifsc;
			}else{
				banknamej = banknamephp;
				branchnamej = branchnamephp;
				anoj = accountnophp;
				ifscj = '';
				}
				
				
  	var json = {
					 "user_id": $localStorage.user_id,
					 "email":'',
					 "mobile":mobile,
					 "first_name":fname,
					 "last_name":lname,
					 "gender":$("input[type='radio'][name='gender']:checked").val(),
					 "pincode":"crosspayzip",
					 "phone":$("#Phone").val(),
					 "country":country,
					 "call_name":'',
					 "ccycode":"INR",
					 "ifsc":ifscj,
					 "city":$("#city").val(),
					 "state":"crosspay",
					 "account_number":anoj,
					 "bank_name":banknamej,
					 "branch_name":branchnamej,
					 "middle_name": $("#mname").val(),
					 "image":$("#imagedata").val()
					}
			console.log(json);
			addReceiverFactory.add(json).then(function(response){
				if(response.data.status == "200"){
					 $location.path('selectreceiver');
					//showAlert.alertPopup("0","Reciever Added SucessFully");
				}else{
					 showAlert.alertPopup("1",response.data.message);
				}
			});
			
		}

		
		
    }]);