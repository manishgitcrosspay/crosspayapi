(function() {
	"use strict";
	var heads = {headers:{'Content-Type': 'application/json'}};
	angular.module('crossPaymodule', ['ngRoute','ngToast','ngStorage','crossPaymodule.route','ngPatternRestrict'])
	 .service('showAlert', ['ngToast','$rootScope', function(ngToast,$rootScope) {
        this.alertPopup = function(errorcode,errormessage) {
        	/*var msg = errorcode == "0" ? "success" : "error";
		   Command:toastr[msg](errormessage)
		   toastr.options = {
			"closeButton": false,
			"newestOnTop": true,
			"progressBar": false,
			"positionClass": "toast-top-right",
			"timeOut": "5000",
			//"showMethod": "fadeIn",
			//"hideMethod": "fadeOut"
		   }*/
		   var type = errorcode == "0" ? "success" : "danger";
		   ngToast.create({
		    className: type,
		    content: errormessage,
		    //content: $sce.trustAsHtml("<a>Click me!</a>"),
		    dismissOnTimeout: 1000,
		    dismissOnClick: true,
		    dismissButton:true,
		     });
        }
    }])
    .factory('LocationGoogleAPI', function($http) {
        var result = {};
        result.getList = function(val) { return $http.get('//maps.googleapis.com/maps/api/geocode/json', { params: { address: val, sensor: false } }).then(function(response) { return response.data.results.map(function(item) { return item.formatted_address; }); }); }
        return result;
    })
	
	.filter('reverse', function() {
  return function(items) {
    return items.slice().reverse();
  };
})
	
	
    .filter('htmlToPlaintext', function() {
        return function(text) {
            return text ? String(text).replace(/<[^>]+>/gm, '') : '';
        };
	})
	.controller('crossPayAppCtrl', ['$scope', '$rootScope', '$location', '$http','$localStorage', '$filter', 'showAlert',
	 function($scope, $rootScope, $location, $http, $localStorage, $filter, showAlert) {
        //$localStorage.server_baseurl="http://54.204.220.251:8080/Crosspayprod/"
		//$localStorage.server_baseurl="https://ws.crosspaymt.com/Crosspayprod/"
        //$localStorage.server_baseurl="https://54.204.220.251:8443/Crosspayprod/"	
		$localStorage.server_baseurl="http://ec2-52-11-197-132.us-west-2.compute.amazonaws.com:6099/Crosspaydev/"	
		//$scope.crosspay = "http://localhost/";
		//$localStorage.client_baseurl ="http://localhost/crosspay/";
		var path = function() {
			return $location.path()
		};
		var a = ["/404", "/login", "/forgetPassword"];
		if (!(path in a)) {
			if (a!='') {
			} else window.location = $localStorage.client_baseurl + 'single';
		}

		// $http.post($localStorage.server_baseurl+'upload/getCountry', {'userid':'2'}).then(function(response){
			//console.log(response,"hiii");
		// });

		  $scope.getRate = function (rate) {
		  	/*rate = rate.replace(/,/g,"");*/
             //alert(rate);
             $http.post($localStorage.server_baseurl+'upload/getExchangeRate', {ccyfrom:"GBP",
                      ccyto:"INR",fromvalue:rate}).then(function(response){
                console.log(response);

                         $scope.RateData = response.data;
                          if(rate.length>7){
                            showAlert.alertPopup("1","Maximum is 1,000,000 GBP");
                        }
                         $scope.finalrate =rate;
                         $scope.finalid ="GBP";
                         console.log("finalraterate "+$scope.finalrate);
                          /* $scope.rate =$filter('number')(rate);*/
                                                  $scope.rate1 = $filter('number')($scope.RateData.TotalRate);
                                                  
                                                  console.log("rate "+$scope.rate1);

     });
         };
         
         $scope.getRate1 = function (rate) {
         	/*rate = rate.replace(/,/g,"")*/
             //alert(rate);
             $http.post($localStorage.server_baseurl+'upload/getExchangeRate', {ccyfrom:"INR",
                      ccyto:"GBP",fromvalue:rate}).then(function(response){
                console.log(response);

                         $scope.RateData = response.data;
                         if(rate.length>7){
                            showAlert.alertPopup("1","Maximum is 1,000,000 INR");
                        }
                         $scope.finalrate =rate;
                         $scope.finalid ="INR";
                         console.log("finalraterate "+$scope.finalrate);
                         /* $scope.rate1 =$filter('number')(rate);*/
                                         $scope.rate =$filter('number')($scope.RateData.TotalRate);
                                         
                                         console.log("rate "+$scope.rate);

     });
         };

         // $scope.login_check();


	}])
		
	.config(['$qProvider', function ($qProvider) {
    $qProvider.errorOnUnhandledRejections(false);
}])


	.directive("navHide", ["$location","$localStorage", function($location,$localStorage) {
		return {
			restrict: "A",
			link: function(scope, element, attrs) {
				var path = function() {
					return $location.path()
				};
				var addBg = function(path) {
					scope.navhidebool = false;
					var arr = ["","/single","/404","/login","/forgetpassword","/register"];
					if(arr.indexOf(path)!==-1)scope.navhidebool = true;else if(!$localStorage.user_id)$location.path('logins');
				};
				addBg(path());
				scope.$watch(path, function(newVal, oldVal) {
					if (angular.equals(newVal, oldVal)) return;
					addBg(path());
				});
			}
		}
	}])
	.directive('loading', function() {
		return {
			restrict: 'E',
			replace: true,
			template: '<div class="loadingg"><div class="lod"></div>' +
				'<div class="sk-wave">' +
				'<div class="sk-rect sk-rect1"></div>' +
				'<div class="sk-rect sk-rect2"></div>' +
				'<div class="sk-rect sk-rect3"></div>' +
				'<div class="sk-rect sk-rect4"></div>' +
				'<div class="sk-rect sk-rect5"></div>' +
				'</div>' +
				'</div>',
			//templateUrl: 'localhost\loading.html',
			link: function(scope, element, attr) {
				scope.$watch('loading', function(val) {
					val ? $(element).show() : $(element).hide();
				});
			}
		}
	});
	function getDateTime() {
		var now = new Date();
		var year = now.getFullYear();
		var month = now.getMonth() + 1;
		var day = now.getDate();
		var hour = now.getHours();
		var minute = now.getMinutes();
		var second = now.getSeconds();
		if (month.toString().length == 1) {
			var month = '0' + month;
		}
		if (day.toString().length == 1) {
			var day = '0' + day;
		}
		if (hour.toString().length == 1) {
			var hour = '0' + hour;
		}
		if (minute.toString().length == 1) {
			var minute = '0' + minute;
		}
		if (second.toString().length == 1) {
			var second = '0' + second;
		}
		var dateTime = day + '-' + month + '-' + year + ' ' + hour + ':' + minute + ':' + second;
		return dateTime;
	}
/*	jQuery.fn.serializeObject = function() {
	  var o = {};
	  var a = this.serializeArray();
	  jQuery.each(a, function() {
	   if (o[this.name]) {
	    if (!o[this.name].push) {
	     o[this.name] = [o[this.name]];
	    }
	    o[this.name].push(this.value || '');
	   } else {
	    o[this.name] = this.value || '';
	   }
	  });
	  return o;
	};*/
})();