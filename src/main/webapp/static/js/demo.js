function ShowLoginText() {
    layer.open({
        type: 1,
        title: "登录",
        area: ["395px", "300px"],
        content: $("#loginBox"),
    });
}
function ShowRegisterText() {
    layer.open({
        type: 1,
        title: "注册",
        area: ["395px", "300px"],
        content: $("#registerBox"),
    });
}



function Login() {
    var phone = $.trim($("#InputUsername").val());//获取用户名trim是去掉空格
    var password = $.trim($("#InputUserPwd").val());//获取密码
    if (phone == "" || password == "") {
        layer.alert("用户名或者密码不能为空!", {
            title: "提示",
            icon: 5,
        });
    }
    else {
        do_login();
        // layer.alert(user name);
    }
}

function register() {
    var phone = $.trim($("#phone").val());//获取用户名trim是去掉空格
    var password = $.trim($("#password").val());//获取密码
    var name = $.trim($("#name").val());//获取用户名
    $.ajax({
        url: "/user/register",
        type: "GET",
        // 入参
        data: {
            phone:phone,
            password:password,
            name:name
        },
        success: function (data) {
            if (data.msg=="success"){
                layer.msg("注册成功");
                layer.closeAll();
            }else {
                layer.msg(data.msg);
            }
            console.log(data);
        },
        error: function () {
            layer.msg("注册时遇到系统错误");
        }
    });
}
function do_login() {
    var phone = $.trim($("#InputUsername").val());//获取用户名trim是去掉空格
    var password = $.trim($("#InputUserPwd").val());//获取密码
    $.ajax({
        url: "/user/login",
        type: "GET",
        // 入参
        data: {
            phone:phone,
            password:password
        },
        success: function (data) {
            if (data.msg=="success"){
                layer.msg("成功");

            }else {
                layer.msg(data.msg);
            }
            console.log(data);
        },
        error: function () {
            alert("客户端请求有误")
        }
    });
}