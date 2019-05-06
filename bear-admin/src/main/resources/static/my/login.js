
$(function() {
    validateRule();
    $(".i-checks").iCheck({checkboxClass:"icheckbox_square-green-login"});
	$('.imgcode').click(function() {
		var url = ctx + "captcha/captchaImage?type=math&s=" + Math.random();
		$(".imgcode").attr("src", url);
	});
});

$.validator.setDefaults({
    submitHandler: function() {
		login();
    }
});

function login() {
	$.modal.loading($("#btnSubmit").data("loading"));
	var loginUser = $.common.trim($("input[name='loginUser']").val());
    var loginPwd = $.common.trim($("input[name='loginPwd']").val());
    var validateCode = $("input[name='validateCode']").val();
    var rememberMe = $("input[name='rememberme']").is(':checked');
    $.ajax({
        type: "post",
        url: ctx + "login",
        data: {
            "loginUser": loginUser,
            "loginPwd": loginPwd,
            "validateCode" : validateCode,
            "rememberMe": rememberMe
        },
        success: function(r) {
            if (r.code == 200) {
                location.href = ctx + 'main';
            } else {
            	$.modal.closeLoading();
            	$('.imgcode').click();
            	$(".code").val("");
            	$.modal.msg(r.message);
            }
        }
    });
}

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            loginUser: {
                required: true
            },
            loginPwd: {
                required: true
            }
        },
        messages: {
            loginUser: {
                required: icon + "请输入您的用户名",
            },
            loginPwd: {
                required: icon + "请输入您的密码",
            }
        }
    });
}
