angular.module('crossPaymodule')
    .controller('transferdeatilsCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'showAlert', function ($scope,$http, $route, $location, $localStorage, showAlert) {
      
	  
	  $scope.username = $localStorage.first_name;
        $scope.beneficiary=$localStorage.perticular_benef;
        $scope.RateData=$localStorage.RateData;
        
        $scope.accountdetails= $localStorage.accountSelectedInfo;
		
		 $scope.cashtotal=parseFloat($localStorage.RateData.cashcommission)+parseFloat($localStorage.RateData.rate); 
	     $scope.banktotal=parseFloat($localStorage.RateData.commission)+parseFloat($localStorage.RateData.rate);
		 
		 
		 $localStorage.accountCash= $scope.cashtotal;
		 $localStorage.accountBank= $scope.banktotal;
		 $scope.agentname = $localStorage.agentname;
		 
		 if($scope.accountdetails == 'Cash'){
			 $localStorage.totamount= $scope.cashtotal;
		 }else{
			 $localStorage.totamount= $scope.banktotal;
		 }
		 
		 
		  $scope.paymentmode=function(){
			 $location.path('paymentsmode');
		 }
	 
	 
	    // cosole.log($scope.cashtotal);
    
    }]);