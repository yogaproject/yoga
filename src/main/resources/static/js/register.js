//发送注册手机验证码
function sendRegPhoneCode() {
    $.ajax({
        url:"/userPc/sendRegPhoneCode",
        type:"post",
        data:{
            "userPhone":$("#userPhone").val(),
        },
        success:function (data) {
            if (data.message=="请在手机上查收验证码"){
                console.log(data);
                alert(data.message);
            } else {
                alert(data.message);
            }
        }
    });
}
//注册手机
function regByPhone() {
    $.ajax({
        url:"/userPc/regByPhone",
        type:"post",
        data:{
            "userPhone":$("#userPhone").val(),
            "userPwd":$("#userPwd").val(),
            "userVerifyCode":$("#userVerifyCode").val()
        },
        success:function (data) {
            if (data.message=="注册成功"){
                console.log(data);
                alert(data.message);
                window.location.href="./index.html";
            }else {
                alert(data.message);
            }
        }
    });
}

//登录手机
function loginByPhoneAndPwd() {
    $.ajax({
        url:"/userPc/loginByPhoneAndPwd",
        type:"post",
        data:{
            "userPhone":$("#userPhone").val(),
            "userPwd":$("#userPwd").val()
        },
        success:function (data) {
            if (data.message=="登录成功"){
                console.log(data);
                alert(data.message);
                window.location.href="./index.html";
            }else {
                alert(data.message);
            }
        }
    });
}



