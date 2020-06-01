function init(){
	$("#yan").click(function(){
		var phone=$("#phone").val();
		if(phone==""){
			alert("请输入正确的手机号码！");
		}else{
			$("#yan").text("60s 后重发");
		}
	});
	$("#login").click(function(){
		var verification_code=$("#yz").val();
		if(verification_code == "123456"){
			window.location.href = "information.html";
		}
		else{
			alert("验证码错误！");
			$("#yz").val("");
		}
	});	
}
$(document).ready(init);