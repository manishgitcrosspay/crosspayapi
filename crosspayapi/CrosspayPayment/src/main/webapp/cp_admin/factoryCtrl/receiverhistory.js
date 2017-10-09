var app = angular.module('crossPaymodule')
    .factory('historyFactory',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.retrieve = function(data){ return $http.post($localStorage.server_baseurl+'/transactionHistory', data);}
		return factRes;
	}])
    app.controller('historyCtrl',['$scope','$http', '$route', '$location', '$localStorage', 'historyFactory', 'showAlert','$rootScope',
	function ($scope,$http, $route, $location, $localStorage, historyFactory, showAlert,$rootScope) {
		$scope.username = $localStorage.first_name;
        
		console.log("sfdfdsfsdf local ");
		console.log("sfdfdsfsdf local sd "+$localStorage.user_id);
		$http.post($localStorage.server_baseurl+'upload/getCountry', {'userid':'2'}).then(function(response){
			//console.log(response,"hiii");
		});
		  $rootScope.loading = true;
    	historyFactory.retrieve({user_id: $localStorage.user_id}).then(function(res){
    		  $rootScope.loading = false;
             console.log(res);
            console.log(res.data.status);

        if(res.data.status=='200') {
                // $localStorage.user_id=res.data.user_id;
                 

                 $scope.profileData=res.data;
                // $location.path('dashboard');
				                angular.element(document).ready( function () {
                         dTable = $('#dataTables-example')
                         dTable.DataTable();
                  });
                
        } else {
         
               alert(res.data.message);
        }


    });
    	
    	 $scope.viewDetails = function (id) {
    		  $rootScope.loading = true;
    		 $http.post($localStorage.server_baseurl+'transactionHistoryview', {'user_id':$localStorage.user_id,'order_no':id}).then(function(response){
       		  $rootScope.loading = false;	
    			 console.log(response,"hiii");
    				 if(response.data.status=='200') {
    					 $localStorage.profileDatas=response.data.data[0];
    					// $scope.profileDatas =  $scope.profileDatas;
    					// $scope.txnno = $scope.profileDatas.txn_no;
    					 console.log("data",$localStorage.profileDatas);
    					 console.log("data",$localStorage.profileDatas.txn_date);
    					 $localStorage.order_id = id;
                         $location.path('recevierhistory');
                      
                } else {
                	
                }
    			});
    	 }
    }]);
app.controller('historyCtrls',['$scope','$http', '$route', '$location', '$localStorage', 'historyFactory', 'showAlert',
	function ($scope,$http, $route, $location, $localStorage, historyFactory, showAlert) {
	console.log("data sdd "+$localStorage.profileDatas);
	 $scope.txn_no = $localStorage.profileDatas.txn_no;
	 if($localStorage.profileDatas.accout_num == 'Bank Transfer' || $localStorage.profileDatas.accout_num == 'Cash Transaction'){
		// $scope.accout_num = $localStorage.profileDatas.accout_num;
	 }else{
		 $scope.ifsc = $localStorage.profileDatas.ifsc;
		 $scope.accout_num = $localStorage.profileDatas.accout_num.replace(/(.{4})/g, '$1 ').trim();
	 }
	// $scope.accout_num = $localStorage.profileDatas.accout_num.replace(/(.{4})/g, '$1 ').trim();
	 $scope.txn_date = $localStorage.profileDatas.txn_date;
	 $scope.name = $localStorage.profileDatas.name;
	
	 $scope.total_amount = $localStorage.profileDatas.total_amount;
	 $scope.payee_amount = $localStorage.profileDatas.payee_amount;
	 $scope.order_id =$localStorage.order_id.replace(/[^\dA-Z]/g, '0').replace(/(.{4})/g, '$1 ').trim();
	 $scope.account_ccycode = $localStorage.profileDatas.account_ccycode;
	 $scope.payin_ccycode = $localStorage.profileDatas.payin_ccycode;
	 $scope.commission = $localStorage.profileDatas.commission;
	 $scope.payin_amount = $localStorage.profileDatas.payin_amount;
	 $scope.rate = $localStorage.profileDatas.rate;
	 $scope.country = $localStorage.profileDatas.country;
	 $scope.city = $localStorage.profileDatas.city;
	 
}]);

        