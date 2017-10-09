var app = angular.module('crossPaymodule')
    .factory('historyFactory',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.retrieve = function(data){ return $http.post($localStorage.server_baseurl+'/transactionHistory', data);}
		return factRes;
	}])
    app.controller('historiesCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'historyFactory', 'showAlert',
	function ($scope,$http, $route, $location, $localStorage, historyFactory, showAlert) {
        $scope.username = $localStorage.first_name;
		 
    		 $http.post($localStorage.server_baseurl+'transactionHistoryview', {'user_id':$localStorage.user_id,'order_no': $localStorage.Orders_id}).then(function(response){
    				console.log(response,"hiii");
    				 if(response.data.status=='200') {
    					 $localStorage.profileDatay=response.data.data[0];
						 //.replace(/(.{4})/g, '$1 ').trim();
						     $scope.txn_no = $localStorage.profileDatay.txn_no;
							 if($localStorage.profileDatay.accout_num == 'Bank Transfer' || $localStorage.profileDatay.accout_num == 'Cash Transaction'){
  // $scope.accout_num = $localStorage.profileDatas.accout_num;
  }else{
   $scope.ifsc = $localStorage.profileDatay.ifsc;
   $scope.accout_num = $localStorage.profileDatay.accout_num.replace(/(.{4})/g, '$1 ').trim();
  }
							// $scope.accout_num = $localStorage.profileDatay.accout_num;
							 $scope.txn_date = $localStorage.profileDatay.txn_date;
							 $scope.name = $localStorage.profileDatay.name;
						//	 $scope.ifsc = $localStorage.profileDatay.ifsc;
							 $scope.total_amount = $localStorage.profileDatay.total_amount;
							 $scope.payee_amount = $localStorage.profileDatay.payee_amount;
							 $scope.order_id =$localStorage.Orders_id.replace(/[^\dA-Z]/g, '0').replace(/(.{4})/g, '$1 ').trim();
							 $scope.account_ccycode = $localStorage.profileDatay.account_ccycode;
							 $scope.payin_ccycode = $localStorage.profileDatay.payin_ccycode;
							 $scope.commission = $localStorage.profileDatay.commission;
							 $scope.payin_amount = $localStorage.profileDatay.payin_amount;
							  $scope.rate = $localStorage.profileDatay.rate;
  $scope.country = $localStorage.profileDatay.country;
  $scope.city = $localStorage.profileDatay.city;
							 
							 
							 
						/*	 $scope.txn_no = $localStorage.profileDaty.txn_no;
  if($localStorage.profileDaty.accout_num == 'Bank Transfer' || $localStorage.profileDatas.accout_num == 'Cash Transaction'){
  // $scope.accout_num = $localStorage.profileDatas.accout_num;
  }else{
   $scope.ifsc = $localStorage.profileDaty.ifsc;
   $scope.accout_num = $localStorage.profileDaty.accout_num.replace(/(.{4})/g, '$1 ').trim();
  }
 // $scope.accout_num = $localStorage.profileDatas.accout_num.replace(/(.{4})/g, '$1 ').trim();
  $scope.txn_date = $localStorage.profileDaty.txn_date;
  $scope.name = $localStorage.profileDaty.name;
 
  $scope.total_amount = $localStorage.profileDaty.total_amount;
  $scope.payee_amount = $localStorage.profileDaty.payee_amount;
  $scope.order_id =$localStorage.order_id.replace(/[^\dA-Z]/g, '0').replace(/(.{4})/g, '$1 ').trim();
  $scope.account_ccycode = $localStorage.profileDaty.account_ccycode;
  $scope.payin_ccycode = $localStorage.profileDaty.payin_ccycode;
  $scope.commission = $localStorage.profileDaty.commission;
  $scope.payin_amount = $localStorage.profileDaty.payin_amount;
  $scope.rate = $localStorage.profileDaty.rate;
  $scope.country = $localStorage.profileDaty.country;
  $scope.city = $localStorage.profileDaty.city;
												*/
                      
                } else {
                	
					
					   showAlert.alertPopup("1",res.data.message);
                }
    			});
		
    	
    	
    	
    }]);

        