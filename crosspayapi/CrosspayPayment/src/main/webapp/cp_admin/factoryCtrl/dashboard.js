angular.module('crossPaymodule')
    .factory('dasboardFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.Login = function(data){ return $http.post($localStorage.server_baseurl+'upload/getExchangeRate', data);}
		return factRes;
	}])
    .controller('dasboardCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'dasboardFact', 'showAlert', function ($scope,$http, $route, $location, $localStorage, dasboardFact, showAlert) {
        
        $scope.username = $localStorage.first_name;
		
		//alert($scope.username);

         $scope.getRate = function (rate) {
            //alert(rate);
            $http.post($localStorage.server_baseurl+'upload/getExchangeRate', {ccyfrom:"GBP",
                     ccyto:"INR",fromvalue:rate}).then(function(response){
			            console.log(response);

                        $scope.RateData = response.data;
                        
                     $scope.finalrate =rate;
console.log("finalraterate "+$scope.finalrate);
                        	$scope.rate1 = $scope.RateData.TotalRate;
                        	
                        	console.log("rate "+$scope.rate1);
                        //$localStorage.finalrate = rate;
                        

				});
        };
        
        $scope.getRate1 = function (rate) {
            //alert(rate);
            $http.post($localStorage.server_baseurl+'upload/getExchangeRate', {ccyfrom:"INR",
                     ccyto:"GBP",fromvalue:rate}).then(function(response){
			            console.log(response);

                        $scope.RateData = response.data;
                       $scope.finalrate =rate;
         console.log("finalraterate "+$scope.finalrate);
                        	$scope.rate = $scope.RateData.TotalRate;
                        	
                        	console.log("rate "+$scope.rate);
                       //$localStorage.finalrate = rate;
                        

				});
        };


         $scope.checkRate = function (rate,RatData) {
          // alert(rate);

             if(rate=='0'|| rate=='' || rate=='undefined') {

                 showAlert.alertPopup("1","Please Select Correct Rate to Procees");
                         //$localStorage.user_id=res.data.user_id;
                         
                         
                      
                } else if(typeof rate == "undefined"){
                        
                  showAlert.alertPopup("1","Please Select Correct Rate to Procees");
                }else
                        
                {
                    RatData.rate=rate;
                   $localStorage.RateData=RatData;
             //$localStorage.RateData=$localStorage.finalrate;

                $location.path('selectreceiver');
                     //  alert(res.data.message);
                }

          //  href="selectreceiver"
           
        };
                

                /*dasboardFact.Login({ccyfrom:"GBP",ccyto:"INR",fromvalue:'2'}).then(function(res){
                  

                if(res.data.status=='200') {
                      
                        console.log(res);
                         showAlert.alertPopup("0",res.data.message);

                        $scope.RateData = res.data;

                       
                        
                } else {
                 
                      // alert(res.data.message);
                        showAlert.alertPopup("1",res.data.message);
                }


            });*/

            
            
			
			
			
			
			
			
      
    }]);