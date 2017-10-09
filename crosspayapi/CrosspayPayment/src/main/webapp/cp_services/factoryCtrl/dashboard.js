angular.module('crossPaymodule')
    .factory('dasboardFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.Login = function(data){ return $http.post($localStorage.server_baseurl+'upload/getExchangeRate', data);}
		return factRes;
	}])
    .controller('dasboardCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'dasboardFact', 'showAlert','$filter', function ($scope,$http, $route, $location, $localStorage, dasboardFact, showAlert,$filter) {
        $scope.username = $localStorage.first_name;
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
    //        /*  rate = rate.replace(/,/g,"")*/
    //         //alert(rate);
    //         $http.post($localStorage.server_baseurl+'upload/getExchangeRate', {ccyfrom:"INR",
    //                  ccyto:"GBP",fromvalue:rate}).then(function(response){
			 //            console.log(response);

    //                     $scope.RateData = response.data;
    //                     if(rate.length>7){
    //                         showAlert.alertPopup("1","Maximum is 1,000,000 INR");
    //                     }
    //                    $scope.finalrate =rate;
    //      console.log("finalraterate "+$scope.finalrate);
    //                     	/*  $scope.rate1 =$filter('number')(rate);*/
    //                         $scope.rate = $filter('number')($scope.RateData.TotalRate);
                        	
    //                     	console.log("rate "+$scope.rate);
    //                    //$localStorage.finalrate = rate;
                        

				// });
    //     };
				$scope.checkRate = function (rate,RatData) {
					if(rate=='0'|| rate=='' || rate==undefined)showAlert.alertPopup("1","Please Select Correct Rate to Procees");
					else if(typeof rate == "undefined")showAlert.alertPopup("1","Please Select Correct Rate to Procees");
					else{
						RatData.rate=rate;
						$localStorage.RateData=RatData;console.log(RatData);
						$location.path('selectreceiver');
					}
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