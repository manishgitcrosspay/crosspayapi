angular.module('crossPaymodule')
    .factory('profileFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.Login = function(data){ return $http.post($localStorage.server_baseurl+'viewProfile', data);}
		return factRes;
	}])
    .controller('profileCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'profileFact', 'showAlert', function ($scope,$http, $route, $location, $localStorage, profileFact, showAlert) {
        //delete $localStorage.user_id;

     
          // alert("manish");

                

                profileFact.Login({user_id: $localStorage.user_id}).then(function(res){
                     console.log(res);
                    console.log(res.data.status);

                if(res.data.status=='200') {
                        // $localStorage.user_id=res.data.user_id;
                         

                         $scope.profileData=res.data;
                        // $location.path('dashboard');
                        
                } else {
                 
                       alert(res.data.message);
                }


            });

            $scope.profileUpdate = function () {

              //  alert("Manish Rai");
			  
			  showAlert.alertPopup("0","If any of the above details are incorrect, please email info@crosspayments.com or call +44 (0) 203 096 2255");

                // $location.path('profileupdate');
            };
			
            
			
			
			
			
			
			
      
    }]);