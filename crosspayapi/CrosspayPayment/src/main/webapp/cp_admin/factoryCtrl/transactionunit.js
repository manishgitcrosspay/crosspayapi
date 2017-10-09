angular.module('crossPaymodule')
    .factory('transactionunitFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.Login = function(data){ return $http.post($localStorage.server_baseurl+'upload/getExchangeRate', data);}
		return factRes;
	}])
    .controller('transactionunitCtrl',['$scope','$rootScope','$http', '$route', '$location', '$localStorage', 'transactionunitFact', 'showAlert', '$filter', function ($scope, $rootScope, $http, $route, $location, $localStorage, transactionunitFact, showAlert, $filter) {
		$scope.custDetails = true;
		$scope.custom_search = function(custom_no,email_phone){
			$rootScope.loading = true;
			if(custom_no=="" || custom_no===undefined){
				var linkk = "beneficiary/viewBendetailsbyemail";
				var data = isNaN(parseInt(email_phone)) ? {"email" : email_phone} : {"mobile" : email_phone};
			}else{
				var linkk = "beneficiary/viewBendetails";
				var data = {"customerno":custom_no};
			}
			$http.post($localStorage.server_baseurl+linkk, data).then(function(response){
				$rootScope.loading = false;
				if(response.data.status=="200"){
					$scope.custDetails = response.data.customer[0];
					//$localStorage.user_admin = $localStorage.user_id;
					$localStorage.user_id = response.data.customerid;
					
					if(response.data.customer.length > 0)$scope.custom_show = true;
				}else $scope.custom_show = $scope.custDetails = false;
			});
		}
		 $scope.firstDrop = 'GBP';
		$scope.getRate = function (rate,fromRef,toRef) {
            console.log("rate : "+rate+", fromRef : "+fromRef+", toRef : "+toRef);
			var from = fromRef===undefined ? "GBP" : fromRef;
			var to = toRef===undefined ? "INR" : toRef;
			$http.post($localStorage.server_baseurl+'upload/getExchangeRate', {ccyfrom:from, ccyto:to,fromvalue:rate}).then(function(response){
				$scope.RateData = response.data;
				$scope.finalrate = rate;
				$scope.rate1 = $filter('number')($scope.RateData.TotalRate);
				$scope.secondDrop=toRef;
				$localStorage.checkstatus="direct";
			});
        };
		$scope.getRateReverse = function (rate1,fromRef,toRef) {
            console.log("rate1 : "+rate1+", fromRef : "+fromRef+", toRef : "+toRef);
			var from = fromRef===undefined ? "GBP" : fromRef;
			var to = toRef===undefined ? "INR" : toRef;
			$http.post($localStorage.server_baseurl+'upload/getExchangeRateReverse', {ccyfrom:from, ccyto:to,tovalue:rate1}).then(function(response){
				$scope.finalrate = response.data.TotalRate;
				response.data.TotalRate = rate1;
				$scope.RateData = response.data;
				$scope.rate = $filter('number')($scope.finalrate);
				$scope.secondDrop=toRef;
				$localStorage.checkstatus="reverse";
			});
        };
		
    //     $scope.getRate1 = function (rate) {
    //         //alert(rate);
    //         $http.post($localStorage.server_baseurl+'upload/getExchangeRate', {ccyfrom:"INR",
    //                  ccyto:"GBP",fromvalue:rate}).then(function(response){
			 //            console.log(response);

    //                     $scope.RateData = response.data;
    //                    $scope.finalrate =rate;
    //      console.log("finalraterate "+$scope.finalrate);
    //                     	$scope.rate = $scope.RateData.TotalRate;
                        	
    //                     	console.log("rate "+$scope.rate);
    //                    //$localStorage.finalrate = rate;
                        

				// });
    //     };


         $scope.checkRate = function (rate,RatData) {
             if(rate=='0'|| rate=='' || rate==undefined) {
                 showAlert.alertPopup("1","Please Select Correct Rate to Procees");
                         //$localStorage.user_id=res.data.user_id;
                } else if(typeof rate == "undefined"){
                  showAlert.alertPopup("1","Please Select Correct Rate to Procees");
                }else {
					RatData.rate=rate;
                   $localStorage.RateData=RatData;console.log(RatData);
					//$localStorage.RateData=$localStorage.finalrate;
                $location.path('selectreceiver');
                     //  alert(res.data.message);
                }
          //  href="selectreceiver"
        };
                /*transactionunitFact.Login({ccyfrom:"GBP",ccyto:"INR",fromvalue:'2'}).then(function(res){
                  

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