angular.module('crossPaymodule')
    .factory('paymentmodeFact',['$http', '$localStorage', function ($http, $localStorage) {
  var factRes = {};
  factRes.Transefer = function(data,lin){ return $http.post($localStorage.server_baseurl+lin, data);}
  return factRes;
 }])

    .controller('paymentmodeCtrl',['$scope','$rootScope', '$http', '$route', '$location','paymentmodeFact', '$localStorage', 'showAlert', '$compile', function ($scope,$rootScope,$http, $route, $location,paymentmodeFact,$localStorage, showAlert, $compile) {
   
   
       $scope.username = $localStorage.first_name;
        $scope.beneficiary=$localStorage.perticular_benef;
        $scope.RateData=$localStorage.RateData;
        
        $scope.accountdetails= $localStorage.accountSelectedInfo;
  
   $scope.beneficiary = $localStorage.perticular_benef;
        $scope.RateData = $localStorage.RateData;
  $scope.accountdetails = $localStorage.accountSelectedInfo;
  
   
  $scope.goToHome = function(){
   
   var myIFrame = document.getElementById('payment_reference');
             var content = myIFrame.contentWindow.document.body.innerHTML;
 
   
       // alert($("#payment_reference").contents());
       // alert(document.getElementById("payment_reference").innerHTML);
   
  }
	 /* $(document).on('click','#submit',function(){
		   $('#securbtn').html(
			   `<div class="row">
				<div class="col-lg-12 text-center">
					<button class="btn proceed" style="margin-left:30px" ng-click="jjjjjj();" type="button">Dashboard</button>
				</div>
			</div>`
			);
	  }); */

	$scope.goDashboard = function(){
		$rootScope.loading = true;
		var data = {
			"user_id": $localStorage.user_id,
			"order_id":$localStorage.Orders_id,
			"payeccyCode":$localStorage.RateData.ccyfrom,
			"totalpayingamount":$localStorage.RateData.TotalRate,
			"beneficiary_no":$localStorage.perticular_benef.beneficiary_no,
			"checkstatus":$localStorage.checkstatus
		}
		$http.post($localStorage.server_baseurl+'capturePayment', data).then(function(res){
			$rootScope.loading = false;
			console.log("response success"+res.data.status);
			if(res.data.status=='200')$location.path('bankhistory');
			else if(res.data.status=='300'){
				if(confirm("Are you sure you want to cancel your transaction?"))$location.path('reviewdetails');
			}else showAlert.alertPopup("1",res.data.message);
		},function(error){console.log('error',error);});
	}
  $scope.transferProceed = function(result,banknumber){
	if(result!=undefined || banknumber!=undefined){
   if(result!=undefined && result=='Debit'){
	    if($localStorage.totamount>1000 && $localStorage.RateData.ccyfrom == 'GBP')showAlert.alertPopup("1","The Total Pay-In Amount should not exceed GBP 1000");
		else{
		   $rootScope.loading = true;
		   var obj = {}
		   obj.beneficiary_no = $localStorage.perticular_benef.beneficiary_no;
		   obj.user_id = $localStorage.user_id;
		   obj.payeccyCode = $localStorage.RateData.ccyfrom;
		   obj.paymentmode = "0";
		   obj.totalpayingamount = $localStorage.RateData.TotalRate;
		   obj.count = $localStorage.RateData.rate;
		   obj.accountccycode = $localStorage.RateData.ccyto;
		   obj.checkstatus = $localStorage.checkstatus;
		   if($scope.accountdetails=='Bank')obj.commission = $localStorage.RateData.commission;
		   else{
				obj.commission = $localStorage.RateData.cashcommission;
				obj.agent_code =  $localStorage.agentid;
		   }
			paymentmodeFact.Transefer(obj,$scope.accountdetails=='Bank' ? 'initiateTransaction' : 'initiateCashTransaction').then(function(res){
			$rootScope.loading = false;
			if(res.data.status=='200'){
				$localStorage.Orders_id = res.data.orderreference;
				$scope.ifrmshow=true;
				var appen = `<iframe id="myFrame"
					src="https://payments.securetrading.net/process/payments/choice?sitere
					ference=`+res.data.sitereference+`&mainamount=`+res.data.mainamount+`&currencyiso3a=`+res.data.currencyiso3a+`&version=`+res.data.version+`&st
					profile=default&orderreference=`+res.data.orderreference+`" width="100%" height="600" scrolling="auto"
					style="border:0px;"></iframe>
					<div class="row">
						<div class="col-lg-12 text-center">
							<button class="btn proceed" style="margin-left:30px" ng-click="goDashboard();" type="button">Dashboard</button>
						</div>
					</div>`;
				;
			   $('#secur').html($compile(appen)($scope));
				$('#navDisable').find('a').css("pointer-events","none");
				history.pushState(null, null, document.URL);
				window.addEventListener('popstate', function () {
					history.pushState(null, null, document.URL);
				});
				showAlert.alertPopup("1","Please do not refresh the page until the transaction has been completed");
			}else showAlert.alertPopup("1",res.data.message);
		   });
		}
    }else{
       if(typeof banknumber == "undefined"){
        showAlert.alertPopup("1","Please Enter Bank Reference Id");
       }else{
			$rootScope.loading = true;
			$scope.ifrmshow=true;
        if($localStorage.accountSelectedInfo=='Cash'){
			 $scope.value = $localStorage.accountCash;
			 $scope.valueAmount = $localStorage.RateData.cashcommission;
			 $scope.paymentmode = "0";
        }else{
		   $scope.value = $localStorage.accountBank;
		   $scope.valueAmount = $localStorage.RateData.commission;
		   $scope.paymentmode = "1";          
        }
        
        if($scope.accountdetails=='Bank'){
            $scope.agentCodess =  "";
           }else{
            $scope.agentCodess =  $localStorage.agentid;
           }
       
        
        var json = {
			"user_id": $localStorage.user_id,
			"txnno":banknumber,
			"payeccyCode":$localStorage.RateData.ccyfrom,
			"paymentmode":$scope.paymentmode,
			"totalpayingamount":$localStorage.RateData.TotalRate,
			"count":$localStorage.RateData.rate,
			"accountccycode":$localStorage.RateData.ccyto,
			"commission":$scope.valueAmount,
			"beneficiary_no":$localStorage.perticular_benef.beneficiary_no,
			"agent_code":$scope.agentCodess,
			"ccycode":$localStorage.RateData.ccyfrom,
			"checkstatus":$localStorage.checkstatus
        }
         $http.post($localStorage.server_baseurl+'sbank', json).then(function(res){
			$rootScope.loading = false;
			console.log("response success"+res.data.status);
			 if(res.data.status=='200') {
					// showAlert.alertPopup("0",res.data.message);
					$location.path('bankhistory');
                    $localStorage.Orders_id=res.data.orderreference;
            }
		},function(resp){console.log(resp);});
      
       }
     
     
    }
  }else showAlert.alertPopup("1","Please select any one option to proceed");
   
  }
    
    }]);
 
 function createElem(myForm,namm,value){
  var elem = document.createElement("input");
  elem.setAttribute("type", "hidden");
  elem.setAttribute("value", value);
  elem.setAttribute("name", namm);
  myForm.appendChild(elem);
 }