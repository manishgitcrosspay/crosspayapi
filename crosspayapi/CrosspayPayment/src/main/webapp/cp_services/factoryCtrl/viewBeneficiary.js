angular.module('crossPaymodule')
    .factory('beneficiaryFact',['$http', '$localStorage', function ($http, $localStorage) {
		var factRes = {};
		factRes.Login = function(data){ return $http.post($localStorage.server_baseurl+'beneficiary/view', data);}
		return factRes;
	}])
    .controller('beneficiaryCtrl',['$rootScope','$scope','$http', '$route', '$location', '$localStorage', 'beneficiaryFact', 'showAlert', function ($rootScope,$scope,$http, $route, $location, $localStorage, beneficiaryFact, showAlert) {
      
    	console.log("agentname dsdf ",$localStorage.agentname);

		$scope.username = $localStorage.first_name;
    	 $scope.agentname = $localStorage.agentname;
		 
		  $rootScope.loading = true;
	 
	 
	  $http.post($localStorage.server_baseurl+'getAgent', {}).then(function(res){
                                 console.log(res)
                                 if(res.data.status=='200') {
									 
									     $scope.payoutdata = res.data.agent;
										  console.log($scope.payoutdata)
										  
										  
									   var arrIndia = [];
									   
									   for(var i=0;i<res.data.agent.length;i++)
									   {
										   if(res.data.agent[i].payout_currencycode=='INR'){
											 
												
												 arrIndia.push({
                                                   agent_name: res.data.agent[i].agent_name,
												   agent_code: res.data.agent[i].agent_code,
                                                   sortable: true,
                                                   resizeable: true
                                                   });
										 
											   
										   }
									   }
									   
									   
									   $scope.payoutdataIndia=arrIndia;
									   
									   
									    var arrPHI = [];
									   
									   for(var i=0;i<res.data.agent.length;i++)
									   {
										   if(res.data.agent[i].payout_currencycode=='PHP'){
											 
												
												 arrPHI.push({
                                                   agent_name: res.data.agent[i].agent_name,
												   agent_code: res.data.agent[i].agent_code,
                                                   sortable: true,
                                                   resizeable: true
                                                   });
										 
											   
										   }
									   }
									   
									   
									   $scope.payoutdataPHP=arrPHI;
									   
									   
									    var arrUSA = [];
									   
									   for(var i=0;i<res.data.agent.length;i++)
									   {
										   if(res.data.agent[i].payout_currencycode=='USA'){
											 
												
												 arrUSA.push({
                                                   agent_name: res.data.agent[i].agent_name,
												   agent_code: res.data.agent[i].agent_code,
                                                   sortable: true,
                                                   resizeable: true
                                                   });
										 
											   
										   }
									   }
									   
									   
									   $scope.payoutdataUSA=arrUSA;
									   
										 
                                 }

                     },function(resp){
						// $rootScope.loading = false;
						 console.log(resp);});
	 
	

       beneficiaryFact.Login({user_id: $localStorage.user_id}).then(function(res){
                     console.log(res);
                    console.log(res.data.status);

                if(res.data.status=='200') {
                        // $localStorage.user_id=res.data.user_id;
                         
                       //  $location.path('dashboard');

                       $scope.bdetails=res.data;
					    $rootScope.loading = false;

                       console.log(res.data);
                      
                } else {
					 $rootScope.loading = false;
                   // showAlert.alertPopup("1",res.data.message);
                     //  alert(res.data.message);
                }
       });
      
       $scope.showPaymentMode = function(event, data){
           delete $localStorage.perticular_benef;
           $scope.perticular_benef=data;
		   
		  var genericvalue = data.country.toUpperCase();
			
			if( genericvalue =='INDIA')
			{
				$scope.pout=$scope.payoutdataIndia;
				
			}else if(genericvalue =='PHILIPPINES'){
				$scope.pout=$scope.payoutdataPHP;
			}else{
				$scope.pout=$scope.payoutdataUSA;
				
			}
			
		  
         $('#account').show();
          $("#div2").show();
         $localStorage.particularBebeficiary=data;
         var rdo = $('input[name=radioName]:checked', '#myForm').val();
         if(rdo == 'Bank'){
        	 $("#div2").hide();
         }
         $('.select1').removeClass('hilight');
         $(event.currentTarget).addClass('hilight');

         $scope.ifscCode = data.ifsc;
         $scope.bankName = data.bank_name;
         $scope.accountNumber = data.account_num;
       }
         $scope.showBank = function(){
			 
			 alert("sbjjsbjsdb");
		 }

       $scope.showCash = function(ifscCode,bankName,accountNumber,perticular_benef,selectedName){
		   
		   
		   
		   
		    
           $localStorage.perticular_benef=perticular_benef;
		   
		 
		 //  alert($scope.genericvalue);
           
          $localStorage.accountSelectedInfo=$('input[name=radioName]:checked', '#myForm').val();
           
          // $localStorage.accountSelectedInfo;
		  
		  

          //alert(selectedName);

           if($('input[name=radioName]:checked', '#myForm').val()=='Bank'){
        	   
        	   

                    /*if(ifscCode=='' || ifscCode=='null') {

                       showAlert.alertPopup("1","Please enter IFSC");
                        
                     } else if(typeof ifscCode == "undefined"){
                        
                          showAlert.alertPopup("1","Please enter IFSC");
                    } else */
						
					
					if(bankName=='' || bankName=='null') {

                       showAlert.alertPopup("1","Please enter Branch Number");
                        
                     } else if(typeof bankName == "undefined"){
                        
                          showAlert.alertPopup("1","Please enter Branch Number");
                    }else if(accountNumber=='' || accountNumber=='null') {

                       showAlert.alertPopup("1","Account Number should be minimum 9 digits");
                        
                     } else if(typeof accountNumber == "undefined"){
                          showAlert.alertPopup("1","Account Number should be minimum 9 digits");
                    }else if(accountNumber.length<9) {
                        showAlert.alertPopup("1","Account Number should be minimum 9 digits");
                         
                      }else{
						
						
						  $scope.ifscCode = perticular_benef.ifsc;
                          $scope.bankName = perticular_benef.bank_name;
                          $scope.accountNumber = perticular_benef.account_num;
						  
						
						  
						  if($scope.ifscCode==ifscCode && $scope.bankName==bankName && $scope.accountNumber==accountNumber){
							  
							  
							  
							  //showAlert.alertPopup("1","You have not change any Account Details");
							//  $location.path('transferdeatils');
							   $location.path('reviewdetails');
							 
							  
						  }else{
							  
							    perticular_benef.ifsc =   $scope.ifscCode;
                                perticular_benef.bank_name = $scope.bankName;
                                perticular_benef.account_num =$scope.accountNumber;
								
								
								var json = {
												 "user_id": $localStorage.user_id,
												 "image":perticular_benef.image,
												 "mobile":perticular_benef.mobile,
												 "first_name":perticular_benef.first_name,
												 "last_name":perticular_benef.last_name,
												 "gender":perticular_benef.gender,
												 "pincode":perticular_benef.pincode,
												 "phone":perticular_benef.phone_num,
												 "country":perticular_benef.country,
												 "call_name":perticular_benef.call_name,
												 "ccycode":perticular_benef.ccycode,
												 "ifsc":ifscCode ,
												 "account_number":accountNumber,
												 "bank_name":bankName,
												 "middle_name": perticular_benef.middle_name,
												 "city":perticular_benef.city,
                                                 "state":perticular_benef.state,
												 "beneficiary_no": perticular_benef.beneficiary_no
												}
												
												// "city":perticular_benef.city,
                                                // "state":perticular_benef.state,
								
								
							  
							   $http.post($localStorage.server_baseurl+'beneficiary/updateBeneficiary', json).then(function(res){
                                 console.log("response success");

                                // alert(response);
                                console.log("response success"+res.data.status);
                                 if(res.data.status=='200') {
									 
									       perticular_benef.ifsc =  ifscCode;
                                           perticular_benef.bank_name = bankName;
                                           perticular_benef.account_num =accountNumber;

                                           //showAlert.alertPopup("0",res.data.message); reviewdetails   
                                          // $location.path('transferdeatils');
										   
										    $location.path('reviewdetails');
                                           // $localStorage.user_id=res.data.user_id;
                                 }

                     },function(resp){console.log(resp);});
							  // showAlert.alertPopup("1","You have change");
						  }
						
                         //$location.path('transferdeatils');
                    }
                        
                
           }else{
			   
			   
			   console.log(selectedName);
			  // alert(selectedName.agent_name);
			   
			   
			   
			   try{
				   
				  // alert(selectedName.agent_name);
				   if(selectedName.agent_name!=''){
					   
				   }else{
					  
				   }
				   
				     $localStorage.agentname=selectedName.agent_name;
					 $localStorage.agentid=selectedName.agent_code;
					console.log("agentname ",$localStorage.agentname);
					 $scope.agentname = $localStorage.agentname;
					// alert($localStorage.agentname);
					// alert($localStorage.agentid);
				   
				    $location.path('reviewdetails');
			   }catch(err){
				   showAlert.alertPopup("1","Please Select Payout-Network. This is Mandatory for Cash Pick-Up transactions."); 
				    
			   }
                //$location.path('transferdeatils');
				

           }

           // alert($('input[name=radioName]:checked', '#myForm').val()); 
       }


        $scope.update = function(benficiary){
		  $localStorage.beneficiary = benficiary;
		  $location.path('updateReceiver');
	  }
       

    

       
        //href="transferdeatils"
       /* $('#myForm input').on('change', function() {
             alert($('input[name=radioName]:checked', '#myForm').val()); 
              $scope.radiotype =$('input[name=radioName]:checked', '#myForm').val();
          });*/
      
       
            


    }]);