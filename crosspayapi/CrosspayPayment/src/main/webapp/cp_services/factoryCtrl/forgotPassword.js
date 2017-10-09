angular.module('crossPaymodule')
    .factory('forgotpasswordFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.ForgotPassword = function(data){ return $http.post($localStorage.server_baseurl+'forgotMpin', data);}
		factRes.verifyOTP = function(data){ return $http.post($localStorage.server_baseurl+'validateForgotOTP', data);}
		return factRes;
	}])
    .controller('forgotpasswordCtrl',['$scope','$http', '$route', '$location', '$sessionStorage', 'forgotpasswordFact', 'showAlert', function ($scope,$http, $route, $location, $sessionStorage, forgotpasswordFact, showAlert) 
	{
		$scope.frgtOTP=$sessionStorage.OTP;
    
        $scope.forgotpassword = function () {
			
			
			
            
			forgotpasswordFact.ForgotPassword({mobile: $scope.mobile, email: $scope.email}).then(function(response){
				
                console.log(response);
                if(response.status=='200' && response.data.status=='200') {
					if($scope.mobile==''){
						$sessionStorage.mob_email=$scope.email;
						$sessionStorage.mob_email_ref="email";
						$sessionStorage.OTP=response.data.OTP;
					}else{
						$sessionStorage.mob_email=$scope.mobile;
						$sessionStorage.mob_email_ref="mobile";
						$sessionStorage.OTP=response.data.OTP;
						
					}
                     
					 
                        $location.path('forgetpasswordverificaion');

                } else {
                   showAlert.alertPopup("1",response.data.message);
                   
                }
            });
			
			
			
			
			
			
        };
		
		
		 $scope.verify_OTP = function () {
			 
				if($sessionStorage.mob_email_ref="mobile"){
					var data = {mobile: $sessionStorage.mob_email, otp: $scope.frgtOTP};
				}else{
					var data = {email: $sessionStorage.mob_email, otp: $scope.frgtOTP};
				}
						
						
						
			forgotpasswordFact.verifyOTP(data).then(function(response){
				
                console.log(response);
                if(response.status=='200') {
                   
					  
					  
					   showAlert.alertPopup("0","Your Mpin is"+response.data.MPIN);
                       $location.path('');
                    
                } else {
                    showAlert.alertPopup("1",response.data.message);
                   
                }
            });
			
			};
		
		
	  }]);