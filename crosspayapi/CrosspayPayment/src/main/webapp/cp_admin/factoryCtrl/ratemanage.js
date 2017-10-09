angular.module('crossPaymodule')
    .factory('ratemanageFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.Login = function(data){ return $http.post($localStorage.server_baseurl+'upload/getExchangeRateCurrency', data);}
		return factRes;
	}])
	 .directive('validNumber', function() {
    return {
      require: '?ngModel',
      link: function(scope, element, attrs, ngModelCtrl) {
        if(!ngModelCtrl) {
          return; 
        }

        ngModelCtrl.$parsers.push(function(val) {
          if (angular.isUndefined(val)) {
              var val = '';
          }
          
          var clean = val.replace(/[^-0-9\.]/g, '');
          var negativeCheck = clean.split('-');
			var decimalCheck = clean.split('.');
          if(!angular.isUndefined(negativeCheck[1])) {
              negativeCheck[1] = negativeCheck[1].slice(0, negativeCheck[1].length);
              clean =negativeCheck[0] + '-' + negativeCheck[1];
              if(negativeCheck[0].length > 0) {
              	clean =negativeCheck[0];
              }
              
          }
            
          if(!angular.isUndefined(decimalCheck[1])) {
              decimalCheck[1] = decimalCheck[1].slice(0,6);
              clean =decimalCheck[0] + '.' + decimalCheck[1];
          }

          if (val !== clean) {
            ngModelCtrl.$setViewValue(clean);
            ngModelCtrl.$render();
          }
          return clean;
        });

        element.bind('keypress', function(event) {
          if(event.keyCode === 32) {
            event.preventDefault();
          }
        });
      }
    }
	 })
    .controller('ratemanageCtrl',['$scope','$rootScope','$http', '$route', '$location', '$localStorage', 'ratemanageFact', 'showAlert', function ($scope, $rootScope, $http, $route, $location, $localStorage, ratemanageFact, showAlert) {
		$scope.custDetails = true;
		$rootScope.loading = true;
		$http.post($localStorage.server_baseurl+'upload/getExchangeRateCurrency').then(function(response){
			console.log("result "+response.data);
				$rootScope.loading = false;
				if(response.data.status=="200"){
					console.log("from curr "+response.data.fromCurrency);
					$scope.rateDetailsfrom = response.data.fromCurrency;
					$scope.rateDetailsto = response.data.toCurrency;
				}else $scope.custom_show = $scope.custDetails = false;
			});
			
			$scope.ratemanagement = function (fromcurr,tocurr,InstrumentType) {
				if(fromcurr!==undefined && tocurr!==undefined && InstrumentType!==undefined && fromcurr!==null && tocurr!==null && InstrumentType!==null){
					$rootScope.loading = true;
					$http.post($localStorage.server_baseurl+'upload/getExchangeRateCurrencyValue',{ccyfrom:fromcurr,ccyto:tocurr,instrument_type:InstrumentType}).then(function(response){
						$rootScope.loading = false;
						if(response.data.status=="200"){
							$scope.updateval=response.data.value;
						}else console.log(response.data.status);
					});
				}
			}
			$scope.updaterate = function (fromcurr,tocurr,InstrumentType,value) {
				if(fromcurr!==undefined && tocurr!==undefined && value!==undefined && fromcurr!==null && tocurr!==null && value!==null){
					$rootScope.loading = true;
					$http.post($localStorage.server_baseurl+'upload/updateExchangeRate',{ccyfrom:fromcurr,ccyto:tocurr,instrument_type:InstrumentType,user_id:$localStorage.user_id,rate:value}).then(function(response){
						$rootScope.loading = false;
						if(response.data.status=="200"){
							showAlert.alertPopup("0",response.data.message);
							$scope.InstrumentType=$scope.updateval=$scope.tocurr=$scope.fromcurr=null;
						}else showAlert.alertPopup("1",response.data.message);
					});
				}else showAlert.alertPopup("1","All fields are mandatory");
			}
		
	/* 	$scope.custom_search = function(custom_no,email_phone){
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
		$scope.getRate = function (rate) {
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
           
        }; */
                

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
 