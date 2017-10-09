angular.module('crossPaymodule.route', ['ngRoute','oc.lazyLoad'])
	.config(function($routeProvider,$locationProvider,$ocLazyLoadProvider) {
		$locationProvider.html5Mode(true);
		//$locationProvider.hashPrefix('');
		$ocLazyLoadProvider.config({
            debug: false,
            events: true
        });
		$routeProvider.when("/",{redirectTo: "single"}).when("/404",{templateUrl: "pages/404.html"}).otherwise({redirectTo: "/404"});
		$routeProvider.when("/single", {
			templateUrl: "pages/index.html",
			controller: "crossPayAppCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js','js/jqBootstrapValidation.js','js/contact_me.js']
							
						})
					}).then(function() {
						//setTimeout(function(){
						
						return $ocLazyLoad.load({
							name: "crossPaymodule",
							files: ['css/agency.min.css','js/agency.min.js']
						})
						//},2000);
					})
				}
			}
		}).when("/forgetpassword", {
   templateUrl: "pages/forgetpassword.html",
   controller : "forgotpasswordCtrl",
    resolve: {
    loadMyDirectives: function($ocLazyLoad) {
     return $ocLazyLoad.load([])
     .then(function() {
      return $ocLazyLoad.load({
       name: 'crossPaymodule',
       serie: true, // load files in series
       files: ['factoryCtrl/forgotPassword.js','js/jqBootstrapValidation.js','js/contact_me.js','js/agency.min.js','css/agency.min.css']
      })
     })
    }
   }
  }).when("/logins", {
			templateUrl: "pages/login.html",
			controller : "loginCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/login.js','js/jqBootstrapValidation.js','js/contact_me.js','js/agency.min.js','css/agency.min.css']
						})
					}) /*.then(function() {
						return $ocLazyLoad.load({
							name: "crossPaymodule",
							files: [
								'css/agency.min.css',
								'css/style.css'
							]
						})
					})*/
				}
			}
		}).when("/forgetpasswordverificaion", {
   templateUrl: "pages/forgetpasswordverificaion.html",
   controller : "forgotpasswordCtrl",
    resolve: {
    loadMyDirectives: function($ocLazyLoad) {
     return $ocLazyLoad.load([])
     .then(function() {
      return $ocLazyLoad.load({
       name: 'crossPaymodule',
       serie: true, // load files in series
       files: ['factoryCtrl/forgotPassword.js','js/jqBootstrapValidation.js','js/contact_me.js','js/agency.min.js','css/agency.min.css']
      })
     })
    }
   }
  }).when("/registers", {
			templateUrl: "pages/register.html",
			controller : "registerCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/register.js','js/jqBootstrapValidation.js','js/contact_me.js','js/agency.min.js','css/agency.min.css','css/style.css']
						})
					})
				}
			}
		}).when("/dashboard", {
			templateUrl: "pages/dashboard.html",
			controller : "dasboardCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/dashboard.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/updateReceiver", {
			templateUrl: "pages/updateReceiver.html",
			controller: "updateReceiverCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['dist/css/material_style.css','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js', 'factoryCtrl/updateReceiver.js']
						})
					})
				}
			}
		}).when("/selectreceiver", {
			templateUrl: "pages/select_receiver.html",
			controller : "beneficiaryCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/viewBeneficiary.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/mpinchange", {
			templateUrl: "pages/mpinchange.html",
			controller : "changeMpinCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/changeMpin.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/transferdeatils", {
			templateUrl: "pages/transfer_deatils.html",
			controller:"transferdeatilsCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/transferDetails.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/paymentsmode", {    //payment_mode.html
			templateUrl: "pages/payment_mode.html",
			controller:"paymentmodeCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/paymentmode.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/reviewdetails", {    //payment_mode.html
			templateUrl: "pages/paymentreview.html",
			controller:"transferdeatilsCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/transferDetails.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/profile", {
			templateUrl: "pages/profile.html",
			controller : "profileCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/profile.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/managereceiver", {
			templateUrl: "pages/manage_receiver.html",
			controller : "beneficiaryCtrl",
			resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/viewBeneficiary.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/support", {
			templateUrl: "pages/support.html",
			controller : "dasboardCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/dashboard.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/recevierhistory", {
   templateUrl: "pages/recevierhistory.html",
   controller: "historyCtrls",
    resolve: {
    loadMyDirectives: function($ocLazyLoad) {
     return $ocLazyLoad.load([])
     .then(function() {
      return $ocLazyLoad.load({
       name: 'crossPaymodule',
       serie: true, // load files in series
       files: ['factoryCtrl/receiverhistory.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
      })
     })
    }
   }
  }).when("/history", {
   templateUrl: "pages/history.html",
   controller: "historyCtrl",
    resolve: {
    loadMyDirectives: function($ocLazyLoad) {
     return $ocLazyLoad.load([])
     .then(function() {
      return $ocLazyLoad.load({
       name: 'crossPaymodule',
       serie: true, // load files in series
       files: ['factoryCtrl/receiverhistory.js','vendor/datatables-plugins/dataTables.bootstrap.css','vendor/datatables-responsive/dataTables.responsive.css','vendor/datatables/js/jquery.dataTables.min.js','vendor/datatables-plugins/dataTables.bootstrap.min.js','vendor/datatables-responsive/dataTables.responsive.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js','https://ajax.googleapis.com/ajax/libs/angularjs/1.6.4/angular.min.js']
      })
     })
    }
   }
  }).when("/paymentin", {
			templateUrl: "pages/paymentin.html",
			controller: "crossPayAppCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['dist/css/material_style.css']
						})
					})
				}
			}
		})		
		.when("/addreceiver", {
			templateUrl: "pages/add_receiver.html",
			controller: "crossPayAppCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/addReceiver.js','dist/css/material_style.css','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		})
		.when("/profileupdate", {
			templateUrl: "pages/profile_update.html",
			controller: "profileUpdateCtrl",
			 resolve: {
				loadMyDirectives: function($ocLazyLoad) {
					return $ocLazyLoad.load([])
					.then(function() {
						return $ocLazyLoad.load({
							name: 'crossPaymodule',
							serie: true, // load files in series
							files: ['factoryCtrl/profileUpdate.js','dist/css/material_style.css','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
						})
					})
				}
			}
		}).when("/bankhistory", {
			   templateUrl: "pages/bankhistory.html",
			   controller: "historiesCtrl",
			    resolve: {
			    loadMyDirectives: function($ocLazyLoad) {
			     return $ocLazyLoad.load([])
			     .then(function() {
			      return $ocLazyLoad.load({
			       name: 'crossPaymodule',
			       serie: true, // load files in series
			       files: ['factoryCtrl/bankhistory.js','dist/css/sb-admin-2.css','dist/js/sb-admin-2.js']
			      })
			     })
			    }
			   }
			  })
    })