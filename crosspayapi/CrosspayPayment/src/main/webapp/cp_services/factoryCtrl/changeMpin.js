angular.module('crossPaymodule')
     .factory('changeFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.ChagePassword = function(data){ return $http.post($localStorage.server_baseurl+'changeMpin', data);}
		
		return factRes;
	}])
   
    .controller('changeMpinCtrl',['$scope','$http', '$route', '$location', '$sessionStorage','$localStorage',  'changeFact', 'showAlert', function ($scope,$http, $route, $location, $sessionStorage,$localStorage, changeFact, showAlert) 
	{
		
		$scope.username = $localStorage.first_name;
		 //Profile Id  
	  
	  $http.post($localStorage.server_baseurl+'viewProfile', {user_id: $localStorage.user_id}).then(function(res){
                                console.log("manish");
								console.log(res);
                                 if(res.data.status=='200') {
									 
									     $localStorage.mobilenumberprofile=res.data.data[0].mobile;
										  console.log($localStorage.mobilenumberprofile);
                                 }

                     },function(resp){console.log(resp);});
	  
	  
	 
	 $scope.changempin = function(oldmpin,newmpin,confirmmpin){
		 console.log("dfghjkl "+oldmpin);
		 
	if(typeof oldmpin == "undefined" || typeof newmpin == "undefined" || typeof confirmmpin == "undefined"){
			 
		 }else{
			 
			 var jsonss = {
				 
				   "mobile" :$localStorage.mobilenumberprofile,
				   "mpin" :oldmpin,
				   "new_mpin" : newmpin
			 }
			 
			 
			 
			 	changeFact.ChagePassword(jsonss).then(function(response){
				
                console.log("manish RAI");
								console.log(response);
                                 if(response.data.status=='200') {
									 
									      showAlert.alertPopup("0",response.data.message);
									      $location.path('dashboard');
                                 } else {
                                        showAlert.alertPopup("1",response.data.message);
                   
                }
            });
			
			 
			 
			 
			
			 
		 }
		 
		 
		 
	 };
		
		
	  }]);