angular.module('crossPaymodule')
    .factory('profileFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
        factRes.Login = function(data){ return $http.post($localStorage.server_baseurl+'viewProfile', data);}
        factRes.updateUserProfile = function(data){ return $http.post($localStorage.server_baseurl+'updateProfile', data);}
		return factRes;
	}])
    .controller('profileUpdateCtrl',['$scope','$http','$routeParams', '$route', '$location', '$localStorage', 'profileFact', 'showAlert', function ($scope,$http, $route, $location,$routeParams, $localStorage, profileFact, showAlert) {
      $scope.user_id = $localStorage.user_id;
	  $scope.username = $localStorage.first_name;
       $scope.updateProfile=function(){
            var data = JSON.stringify($('.PUpdate').serializeObject());
            var validate = JSON.parse(data);

             // alert(validate.user_id);

            // showAlert.alertPopup("0","I AM HERE");

               //profileFact.updateUserProfile({user_id: $localStorage.user_id,firstname:regForm.fname,middlename:regForm.mname,lastname:regForm.lname,email:regForm.Email,mobile:regForm.mobname,countrycode:'+44',dateofbirth:regForm.dob,gender:regForm.gender,address1:regForm.address1,address:regForm.address2,city:'n/a',state:regForm.county,pincode:regForm.zcode,phone:regForm.pnumber,nationality:regForm.nationality,country:regForm.country}).then(function(res){
              //user_iduser_id data.user_id=$localStorage.user_id;

              

             // alert(data.user_id);


              $http.post($localStorage.server_baseurl+'updateProfile', data).then(function(res){
                        console.log(res);
                                    
                         // showAlert.alertPopup("0",res);
                        
                if(res.data.status=='200') {
                       
                          //  $scope.profileData=res.data;
                             showAlert.alertPopup("0",res.data.message);
                        
                        
                } else {
                 
                      // alert(res.data.message);

                        showAlert.alertPopup("1",res.data.message);
                }


                        $scope.RateData = response.data;

				});

              /*  profileFact.updateUserProfile(regForm).then(function(res){
                   
              showAlert.alertPopup("0",res);
                        
                if(res.data.status=='200') {
                       
                         
                             showAlert.alertPopup("0",res.data.message);
                        
                        
                } else {
                 
                      

                        showAlert.alertPopup("1",res.data.message);
                }


            },function(res){
                alert('error');
                   console.log(res);
            });*/


       }
          
		  profileFact.Login({user_id: $localStorage.user_id}).then(function(res){
                   

                if(res.data.status=='200') {
                       
                        

                         $scope.profileData=res.data;
                        
                        
                } else {
                 
                       showAlert.alertPopup("1",res.data.message);
                }


            });
	
            
			
			
			
			
		  $.fn.serializeObject = function() {
	            var o = {};
	            var a = this.serializeArray();
	            $.each(a, function() {
	                if (o[this.name] !== undefined) {
	                    if (!o[this.name].push) {
	                        o[this.name] = [o[this.name]];
	                    }
	                    o[this.name].push(this.value || '');
	                } else {
	                    o[this.name] = this.value || '';
	                }
	            });
	            return o;
	        };	
			
      
    }]);