pos.controller("posClient",function($scope,$http){
	$scope.items=[{}];
	$scope.flag = true;
	$scope.findSubCompany = function(){
		$http.post('company.do').success(function(response){
			if("0"==response||response==null||response==""){
				alert("网络出错,请稍后再试901");
				$scope.subcompanys=null;
				return;
			}else if("1"==response){
				alert("暂无相关数据");
				$scope.subcompanys = null;
				return;
			}else{
				$scope.subcompanys=response;
			}
		});
	};
	
	$scope.findPosUser = function (){
		var cId = $scope.subcompanys[$scope.index].cId;
		$http.get('findUserByCID.do?cId='+cId).success(function(response){
			if("0"==response||response==null||response==""){
				alert("网络出错,请稍后再试902");
				$scope.users= null;
				$scope.index= null;
			}else if("1"==response){
				alert("暂无相关数据!");
				$scope.users= null;
				$scope.index= null;
				return;
			}else{
				$scope.users=response;
			}
		});
	};
	
	$scope.check = function (){
		$scope.flag = false;
		var px = $scope.items;
		for ( var int = 0; int < px.length; int++) {
			if(px[int].pIndexAttitude==""||px[int].pIndexAttitude==null){
				$scope.flag = true;
			}
			if(px[int].pIndexTime==""||px[int].pIndexTime==null){
				$scope.flag = true;
			}
			if(px[int].pIndexCorrect==""||px[int].pIndexCorrect==null){
				$scope.flag = true;
			}
		}
	};
	
	$scope.submit = function(){
		for ( var int = 0; int < $scope.users.length; int++) {
			$scope.items[int].cName=$scope.subcompanys[$scope.index].cName;
			$scope.items[int].subCompanyId=$scope.subcompanys[$scope.index].cId;
			$scope.items[int].pNote=$scope.note;
			$scope.items[int].pUserId=$scope.users[int].uId;
		}
		$http({
			method:'post',
			url:'grade.do',
			data:$scope.items,
			headers:{'Content-Type':'application/x-www-form-urlencoded'},
		}).success(function(response){
			alert(response);
		});
	};
});