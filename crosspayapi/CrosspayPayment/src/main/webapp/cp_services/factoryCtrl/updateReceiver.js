angular.module('crossPaymodule')
    .factory('updateReceiverFactory',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.update = function(data){ return $http.post($localStorage.server_baseurl+'/beneficiary/updateBeneficiary', data);}
		return factRes;
	}])
    .controller('updateReceiverCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'updateReceiverFactory', 'showAlert',
	function ($scope,$http, $route, $location, $localStorage, updateReceiverFactory, showAlert) {
		
		
		
		
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
		
		
        
		$scope.username = $localStorage.first_name;
		$scope.firstName = $localStorage.beneficiary.first_name;
		$scope.middleName = $localStorage.beneficiary.middle_name ;
		$scope.lastName = $localStorage.beneficiary.last_name ;
		$scope.country = $localStorage.beneficiary.country ;
		
		try{
			
			
			
			if($scope.country.toUpperCase()=='UNITED STATES'){
				
				$scope.ifscunited = $localStorage.beneficiary.ifsc;
				$scope.account_numberunited = $localStorage.beneficiary.account_num;
				$scope.bank_nameunited = $localStorage.beneficiary.branch_name;        
				//$scope.selectedName = $localStorage.beneficiary.bank_name;
				
				
				
			}else if($scope.country.toUpperCase()=='INDIA'){
				
				
				$scope.ifsc = $localStorage.beneficiary.ifsc;
				$scope.account_number = $localStorage.beneficiary.account_num;
				$scope.branchname = $localStorage.beneficiary.branch_name;        
				//$scope.selectedName = $localStorage.beneficiary.bank_name;
				
				
		       
				
				
				
			}else{
				
				//$scope.ifsc = $localStorage.beneficiary.ifsc;
				$scope.account_numberphp = $localStorage.beneficiary.account_num;
				$scope.bank_namephp = $localStorage.beneficiary.branch_name; 
               //
				//$scope.bank_namephp = $localStorage.beneficiary.bank_name;
				
				
				
				}
			
			
			
		}catch(err){
			
		}
		
		
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
		
		$scope.UpdateReceiver = function(selectedName){
			
			
			 try{	
		
		//alert(selectedName.bank_name);
		$scope.bname=selectedName.bank_name;
		
		 }catch(err)
		 {
			 $scope.bname='';
			 
		 }
			
			var country=$("#country").val();
			var state=$("#state").val();
			var fname=$("#fname").val();
			//var mname=$("#mname").val();
			var lname=$("#lname").val();
			var mobile=$("#mobile").val();
				var phone=$("#phone").val();
			var image=$("#file-upload").val();
			
			
			//var ifsc=$("#ifsc").val();
			//var accountno =$("#accountno").val();
			
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
			
		var isChecked = $('#Bank').prop('checked');
			if(mobile=='' &&isChecked!=true)
			{
				$("#mobile_error").html("Please Enter Your Mobile Number");
				return false;
				
			} else {
				
				$("#mobile_error").html(" ");
			}
			if(mobile.length<9 &&isChecked!=true)
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
					
					
					
					
					
									var banknamej='';
				                    var branchnamej='';
				                    var anoj='';
				                    var ifscj='';
				
		  
			
			if(country=='UNITED STATES'){
				
	     		banknamej = banknameunited;
				branchnamej = $scope.bank_nameunited;
				anoj = $scope.account_numberunited;
				ifscj = $scope.ifscunited;
			}else if(country=='INDIA'){
				
				banknamej = bankname;
				branchnamej = $scope.branchname; 
				anoj = $scope.account_number;
				ifscj = $scope.ifsc;
			}else{
				banknamej = banknamephp;
				branchnamej = $scope.bank_namephp;
				anoj = $scope.account_numberphp;
				ifscj = '';
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
					 "pincode":'',
					 "phone":$("#Phone").val(),
					 "country":country,
					 "state":'',
					 "call_name":fname+' '+$("#mname").val()+' '+lname,
					 "ccycode":"GBP",
					 "ifsc":ifscj,
					 "account_number":anoj ,
					 "bank_name":banknamej,
					 "middle_name": $("#mname").val(),
					 "branch_name":branchnamej,
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