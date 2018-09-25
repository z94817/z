pos.controller("posServer",function($scope,$http){
	$scope.sel1 = function(){
		var datetime = $scope.datetime;
		if(datetime == null||datetime == "undefined"||datetime == ""){
			var date=new Date;
			datetime = date.getFullYear()+""+(date.getMonth()+1);
			$scope.showYear = date.getFullYear()+"年"; 
			$scope.showMonth = (date.getMonth()+1)+"月";
		}else{
			if(datetime=="总表"){
				$scope.showYear = null;
				$scope.showMonth = "总";
			}else{
				$scope.showYear = datetime.substring(0,4)+"年";
				$scope.showMonth = datetime.substring(4)+"月";
			}
		}
		$http.get('findUserAndGrade.do?datetime='+datetime).success(function(response){
			if("0"==response||response==null||response==""){
				alert("网络出错,请稍后再试902");
			}else if("1" == response){
				alert("暂无相关数据");
				return;
			}else{
				$scope.sel3();
				$scope.UserWithGrade=response;
				for ( var int = 0; int < $scope.UserWithGrade.length; int++) {
					var user = $scope.UserWithGrade[int];
					var attitude = null;
					var time = null;
					var correct = null;
					for ( var i = 0; i < user.grades.length; i++) {
						var grade = user.grades[i];
						attitude+=grade.pIndexAttitude;
						time+=grade.pIndexTime;
						correct+=grade.pIndexCorrect;
					}
					if(attitude!=null&&attitude!=""&&attitude!="NaN"&&attitude!=0){
						var score = ((attitude+time+correct)/(user.grades.length*3)).toFixed(2);
						attitude = (attitude/user.grades.length).toFixed(2);
						time = (time/user.grades.length).toFixed(2);
						correct = (correct/user.grades.length).toFixed(2);
						user.attitude = attitude;
						user.time = time;
						user.correct = correct;
						user.score = score;
						user.num = user.grades.length;
					}else{
						user.attitude = "-";
						user.time =  "-";
						user.correct =  "-";
						user.score =  "暂无数据";
					}
				}
			}
		});
	};
	$scope.sel2 = function(){
		$http.post('findSubCompanyAndUser.do').success(function(response){
			if("0"==response||response==null||response==""){
				alert("网络出错,请稍后再试902");
				$scope.SubCompanyAndUser = null;
			}else if("1"==response){
				alert("暂无相关数据");
				$scope.SubCompanyAndUser = null;
				return;
			}else{
				$scope.SubCompanyAndUser=response;
			}
		});
		$scope.sel4();
	};
	$scope.sel3 = function(){
		$http.post('company.do').success(function(response){
			if("0"==response||response==null||response==""){
				alert("网络出错,请稍后再试903");
				$scope.subcompanys = null;
			}else if("1"==response){
				alert("暂无相关数据");
				$scope.subcompanys = null;
				return;
			}else{
				$scope.subcompanys=response;
			}
		});
	};
	$scope.sel4 = function(){
		$http.get('findUser.do').success(function(response){
			if("0"==response||response==null||response==""){
				alert("网络出错,请稍后再试904");
				$scope.users=null;
			}else if("1"==response){
				alert("暂无相关数据");
				$scope.users=null;
				return;
			}else{
				$scope.users=response;
			}
		});
	};
	
	$scope.updateSubCompanys = function(subcompany){
		$scope.updateSubCompany = angular.copy(subcompany);
	};
	
	$scope.saveSubCompanys = function(){
		if($scope.updateSubCompany.cName==""||$scope.updateSubCompany.cName==null||$scope.updateSubCompany.cName=="undefined"){
			alert("信息不能为空!");
			return;
		}
		$http.get('saveSubCompanys.do?cName='+$scope.updateSubCompany.cName+'&cId='+$scope.updateSubCompany.cId).success(function(response){
			alert(response);
			$scope.sel3();
		});
	};
	
	$scope.deleteSubCompanys = function(cId){
		if(confirm('确认删除?')){
			$http.get('deleteSubCompany.do?cId='+cId).success(function(response){
				alert(response);
				$scope.sel3();
			});
		}
	};
	
	$scope.updateUsers = function(user){
		$scope.updateUser = angular.copy(user);
	};
	
//	$scope.updateUserImg = function(){
//		var file = document.getElementById('pic').files[0];
//        //判断文件大小
//        if(file.size >= 2*1024*1024){
//          alert('上传失败,请选择小于2M的图片');
//          return;
//        }
//        alert(file.size+"阔以");
//        var URL = window.URL;
//        var imgURL = URL.createObjectURL(file);
//        document.getElementById("userImg").src=imgURL;
//	};
	
	$scope.saveUsers = function(){
		var file = document.getElementById('pic').files[0];
		var formData = new FormData();
		var uName = $scope.updateUser.uName;
		var uWork = $scope.updateUser.uWork;
		var uId = $scope.updateUser.uId;
		var uImg = $scope.updateUser.uImg;
		if(uId=="undefined"||uId==null||uId==""){
			if(uName=="undefined"||uName==null||uName==""||file=="undefined"||file==null||file==""||uWork=="undefined"||uWork==null||uWork==""){
				alert('信息不能为空!');
				return;
			}
		}else{
			if(uName=="undefined"||uName==null||uName==""||uWork=="undefined"||uWork==null||uWork==""){
				alert('信息不能为空!');
				return;
			}
		}
		formData.append("uName", uName);//其他需要上传的字段
		formData.append("uWork", uWork);//其他需要上传的字段
		formData.append("uId", uId);//其他需要上传的字段
		formData.append("uImg", uImg);//其他需要上传的字段
		formData.append("file", file);//文件
		$http({
	           url:'saveUsers.do',
	           method:"post",
	           headers: {'Content-Type': undefined},//使用angular上传一定要加上这一句，不然传给后台的是空的。
	           data: formData,
		 }).success(function (response) {
			 $scope.sel4();
			 alert(response);
		 });
	};
	
	$scope.deleteUsers = function(uId){
		if(confirm('确认删除?')){
			$http.get('deleteUsers.do?uId='+uId).success(function(response){
				$scope.sel4();
				alert(response);
			});
		}
	};
	
	$scope.saveSubCompanyAndUser = function (index){
		$scope.scANDu = angular.copy($scope.SubCompanyAndUser[index]);
		$scope.scWITHOUTu = angular.copy($scope.users);
		for ( var int2 = 0; int2 < $scope.scWITHOUTu.length; int2++) {
			var nouser = $scope.scWITHOUTu[int2];
			for ( var int = 0; int < $scope.scANDu.users.length; int++) {
				var inuser = $scope.scANDu.users[int];
				if(inuser.uId==nouser.uId){
					$scope.scWITHOUTu.splice(int2,1);
					int2--;
				}
			}
		}
	};
	
	$scope.addScAndU = function(cId,uId){
		$http.get('saveSubCompanyAndUser.do?cId='+cId+'&uId='+uId).success(function(response){
			$scope.sel2();
			for ( var int = 0; int < $scope.users.length; int++) {
				var inuser = $scope.users[int];
				if(uId==inuser.uId){
					$scope.scANDu.users.splice(0,0,inuser);
				}
			}
			for ( var int = 0; int < $scope.scWITHOUTu.length; int++) {
				var nouser = $scope.scWITHOUTu[int];
				if(uId==nouser.uId){
					$scope.scWITHOUTu.splice(int,1);
				}
			}
			alert(response);
		});
	};
	
	$scope.deleteSubCompanyAndUser = function(cId,uId){
		if(confirm('确认删除?')){
			$http.get('deleteSubCompanyAndUser.do?cId='+cId+'&uId='+uId).success(function(response){
				$scope.sel2();
				alert(response);
			});
		};
	};
	
	$scope.checkTips = function(index){
		$scope.checkUser = $scope.UserWithGrade[index];
	};
	
	$scope.sel1All = function(){
		$scope.datetime = "总表";
		$scope.sel1();
	};
});