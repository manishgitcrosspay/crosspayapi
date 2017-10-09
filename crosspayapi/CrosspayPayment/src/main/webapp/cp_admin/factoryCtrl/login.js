angular.module('crossPaymodule')
    .factory('loginFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.Login = function(data){ return $http.post($localStorage.server_baseurl+'adminlogin', data);}
		return factRes;
	}])
    .controller('loginCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'loginFact', 'showAlert', function ($scope,$http, $route, $location, $localStorage, loginFact, showAlert) {
    	delete $localStorage.RateData;
	    delete $localStorage.particularBebeficiary;
	    delete $localStorage.perticular_benef;
    	delete $localStorage.user_id;
		delete $localStorage.first_name;
        $scope.login_check = function () {
            
            var MobileNumber = $scope.email_mob;
            var Passwords = $scope.paswrd.toString();
			
			//alert(MobileNumber);

           /* if(MobileNumber.length<9){
                showAlert.alertPopup("1","Mobile Number Should be 9 or 15 digits long");
               // alert("Mobile Number Should be 9 or 15 digits long");
              }*/
			  
			  
		if(MobileNumber=='' || typeof MobileNumber == "undefined"){
                showAlert.alertPopup("1","Mobile or Email Should Not be Empty");
               // alert("Mobile Number Should be 9 or 15 digits long");
              }else if(Passwords.length<4){
                  showAlert.alertPopup("1","Password length should be 4 digits");
               // alert("Password length should be 4 digits");  
              }else{

                
            	  var isnum = /^\d+$/.test(MobileNumber);
            	if(isnum == true){
            		 loginFact.Login({mobile: $scope.email_mob, password: $scope.paswrd}).then(function(res){
                         console.log(res);
                        console.log(res.data.status);

                    if(res.data.status=='200') {
                             $localStorage.user_id=res.data.user_id;
							  $localStorage.first_name=res.data.first_name;
                             
                             $location.path('transactionunit');
                          
                    } else {
                       
                    	
                    	showAlert.alertPopup("1",res.data.message);

                    	try{
                    		//showAlert.alertPopup("1",response.data.message);
                         if(res.data.OTP!=''){

                         $http.post($localStorage.server_baseurl+'validateOTP', {'otp':res.data.OTP,'mobile':$scope.email_mob}).then(function(res){
                                     console.log("response success");

                                    // alert(response);
                                    console.log("response success"+res.data.status);
                                     if(res.data.status=='200') {

                                               showAlert.alertPopup("0",res.data.message);   
                                               $location.path('transactionunit');
                                               $localStorage.user_id=res.data.user_id;
											   $localStorage.first_name=res.data.first_name;
                                     }

                         },function(resp){console.log(resp);});

                         //  alert(res.data.message);
                    }else{
                        showAlert.alertPopup("1",res.data.message);
                    }
                         
                    	}catch(e){
                    		 showAlert.alertPopup("1",res.data.message);
                    	}
                    	
                    	
                    	
                    }


                });
            	}else{
            		 loginFact.Login({email: $scope.email_mob, password: $scope.paswrd}).then(function(res){
                         console.log(res);
                        console.log(res.data.status);

                    if(res.data.status=='200') {
                             $localStorage.user_id=res.data.user_id;
                             
                             $location.path('transactionunit');
                          
                    } else {
                       
                    	
                    	showAlert.alertPopup("1",res.data.message);

                    	try{
                    		//showAlert.alertPopup("1",response.data.message);
                         if(res.data.OTP!=''){

                         $http.post($localStorage.server_baseurl+'validateOTP', {'otp':res.data.OTP,'mobile':$scope.email_mob}).then(function(res){
                                     console.log("response success");

                                    // alert(response);
                                    console.log("response success"+res.data.status);
                                     if(res.data.status=='200') {

                                               showAlert.alertPopup("0",res.data.message);   
                                               $location.path('transactionunit');
                                                $localStorage.user_id=res.data.user_id;
                                     }

                         },function(resp){console.log(resp);});

                         //  alert(res.data.message);
                    }else{
                        showAlert.alertPopup("1",res.data.message);
                    }
                         
                    	}catch(e){
                    		 showAlert.alertPopup("1",res.data.message);
                    	}
                    	
                    	
                    	
                    }


                });
            	}
               

              }
			
            
			
			
			
			
			
			
        };
    }]);