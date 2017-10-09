angular.module('crossPaymodule')
   
    .controller('historyCtrls',['$scope','$http', '$route', '$location', '$localStorage', 'showAlert',
	function ($scope,$http, $route, $location, $localStorage,showAlert) {
		
			 $scope.orderidss=$localStorage.orderids;
			 $scope.profileDatas=$localStorage.profilehistory;
		
     
	
    	
    	
    }]);