angular.module('crossPaymodule')
    .factory('registerFact',['$http', '$localStorage', function ($http, $localStorage) {
        var factRes = {};
        //$http.defaults.headers.post["Content-Type"] = "application/json";
		factRes.Register = function(data){ return $http.post($localStorage.server_baseurl+'register', data);}
		return factRes;
	}])
    .controller('registerCtrl',['$scope', '$rootScope', '$route', '$location', '$localStorage', 'registerFact', 'showAlert','$http', function ($scope, $rootScope, $route, $location, $localStorage, registerFact, showAlert,$http) {
        var d = new Date();
		var date = d.getDate().toString();
		var month = (d.getMonth()+1).toString();
		var year = d.getFullYear()-18;
		if(date.length == 1)date = "0"+date;
		if(month.length == 1)month = "0"+month;
		$scope.minDate = "1900-01-01";
        $scope.maxDate = year+"-"+month+"-"+date;
    	$scope.nextpage = function(){
    		 $location.path('login');
    	}
        $scope.otpValidation = function(otp,mobileno){

           /* alert(otp);
            alert(mobileno);
            $http({
                method: 'POST',
                url: $localStorage.server_baseurl+'validateOTP',
                data: {otp:otp,mobile:mobileno},
                headers: {'Content-Type': 'application/json'}
            }).success(function(response){console.log(response);});*/

               $http.post($localStorage.server_baseurl+'validateOTP', {'otp':otp,'mobile':mobileno}).then(function(response){
                                 console.log("response success");

                              //   alert(response);
                                console.log("response success"+response.data.status);
                                 if(response.data.status=='200') {

                                           showAlert.alertPopup("0","Thank you for registering with Crosspay.");   //response.data.message
                                           $location.path('dashboard');
                                            $localStorage.user_id=response.data.user_id;
                                 }

			
		},function(resp){console.log(resp);});
        }
        
        $scope.registration = function (sdata) {
            var err_msg="";
            //$scope.otp;
            $rootScope.loading=true;
            var regForm = $(sdata.target).serializeObject();
			//regForm.user_id=$sessionStorage.user_id;
            console.log(regForm);






            var test_email=/^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
            /*kundan start*/
             var mobilee=/^[1-9][0-9]*$/i;

            if(regForm.Country=="")err_msg = "Choose Your Country";
            else if(regForm.Zipcode=="")err_msg = "Enter Your Post Code";
            else if(regForm.Zipcode.toString().length<5)err_msg = "Post Code should be atleast 5 with alphanumeric & spaces";
            else if(regForm.Address=="")err_msg = "Enter Your Address";
            else if(regForm.city=="")err_msg = "Enter Your City";
            else if(regForm.firstname=="")err_msg = "Enter Your First Name";
            else if(regForm.lastname=="")err_msg = "Enter Your Last Name";
            else if(regForm.email=="")err_msg = "Enter Your Email Address";
            else if(!test_email.test(regForm.email))err_msg = "Enter valid Email Address";
            else if(regForm.countrycode=="")err_msg = "Choose Your Country Code";
            else if(regForm.mobile=="")err_msg = "Enter Your Mobile Number";
            else if(regForm.mobile.toString().length!=10)err_msg = "Enter valid Mobile Number";
              else if(!mobilee.test(regForm.mobile))err_msg = "Mobile no Should Not start with Zero ";
            else if(regForm.password=="")err_msg = "Enter Your Password";
            else if(regForm.password.toString().length!=4)err_msg = "Password should be 4 digits";
            else if(regForm.CPassword=="")err_msg = "Confirm Your Password";
            else if(regForm.CPassword.toString().length!=4)err_msg = "Confirm Password should be 4 digits";
            else if(regForm.password!=regForm.CPassword)err_msg = "Password & Confirm Password Should Be Same";
            else if(regForm.dateofbirth=="")err_msg = "Enter Your Date Of Birth";
            else if(regForm.gender=="")err_msg = "Enter Your Gender";

            /*kundan end*/

            /*if(regForm.Country=="")err_msg = "Choose Your Country";
            else if(regForm.Zipcode=="")err_msg = "Enter Your Post Code";
            else if(regForm.Address=="")err_msg = "Enter Your Address";
            else if(regForm.city=="")err_msg = "Enter Your City";
            else if(regForm.firstname=="")err_msg = "Enter Your First Name";
            else if(regForm.lastname=="")err_msg = "Enter Your Last Name";
            else if(regForm.email=="")err_msg = "Enter Your Email Address";
            else if(!test_email.test(regForm.email))err_msg = "Enter valid Email Address";
            else if(regForm.countrycode=="")err_msg = "Choose Your Country Code";
            else if(regForm.mobile=="")err_msg = "Enter Your Mobile Number";
            else if(regForm.mobile.toString().length!=10)err_msg = "Enter valid Mobile Number";
            else if(regForm.password=="")err_msg = "Enter Your Password";
            else if(regForm.password.toString().length!=4)err_msg = "Password should be 4 digits";
            else if(regForm.CPassword=="")err_msg = "Confirm Your Password";
            else if(regForm.CPassword.toString().length!=4)err_msg = "Confirm Password should be 4 digits";
            else if(regForm.password!=regForm.CPassword)err_msg = "Password & Confirm Password Should Be Same";
            else if(regForm.dateofbirth=="")err_msg = "Enter Your Date Of Birth";
            else if(regForm.gender=="")err_msg = "Enter Your Gender";*/
           // else if(regForm.checkbox===undefined || regForm.checkbox!="on")err_msg = "Accept Terms & Conditions";
            //else if(regForm.CPassword=="")err_msg = "Enter Your Pin Code";
            else{
                registerFact.Register(regForm).then(function(response){
                    $rootScope.loading=false;
                    if(response.status=='200') {
                        if(response.data.status=='200'){
                            //showAlert.alertPopup("0",response.data.message);
                            console.log(response.data.OTP);
                            console.log("response "+regForm.mobile);

                            var mobile=regForm.mobile;
                            console.log("response fghj"+$localStorage.server_baseurl+'validateOTP');
 
                             $scope.otpValidation(response.data.OTP,regForm.mobile);
                           /* $scope.ok= function () { alert();
                                         showAlert.alertPopup("0",response.data.message);   
                                           $location.path('dashboard');
                                 
                                         }*/
                            //$location.path('dashboard');
                            //$route.reload();
                        }else showAlert.alertPopup("1",response.data.message);
                    } else showAlert.alertPopup("1","Something went wrong, Please try again");
                });
            }
            if(err_msg!=""){
				$rootScope.loading=false;
				document.getElementById('error').innerHTML=err_msg;
				//showAlert.alertPopup("1",err_msg);
			}
        };
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