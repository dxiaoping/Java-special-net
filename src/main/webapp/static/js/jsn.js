add_menufunction ShowLoginText() {
    layer.open({
        type: 1,
        title: "登录",
        area: ["395px", "300px"],
        content: $("#loginBox"),
    });
}
ShowResourceBox
function ShowRegisterText(role) {

    $("#reg").css("display", "none");
    $("#add-teacher").css("display", "none");
    $("#" + role).css("display", "block");

    layer.open({
        type: 1,
        title: "注册",
        area: ["395px", "300px"],
        content: $("#registerBox"),
    });
}

function logout() {
    $.ajax({
        url: "/user/out_login",
        type: "GET",
        data: {},
        success: function (data) {
            if (data.msg == "success") {
                window.location.reload();
                $("#display-user").css("display", "none");
                $("#logout").css("display", "none");
                $("#add-tea").css("display", "none");
                $("#bLogin").css("display", "block");
                $("#bReg").css("display", "block");
                layer.closeAll();
            } else {
                layer.msg(data.msg);
            }
            console.log(data);
        },
        error: function () {
            layer.msg("注销时遇到系统错误");
        }
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

function register(role) {
    var phone = $.trim($("#phone").val());//获取用户名trim是去掉空格
    var password = $.trim($("#password").val());//获取密码
    var name = $.trim($("#name").val());//获取用户名
    $.ajax({
        url: "/user/register",
        type: "GET",
        // 入参
        data: {
            phone: phone,
            password: password,
            name: name,
            role: role
        },
        success: function (data) {
            if (data.msg == "success") {
                layer.msg("注册成功");
                layer.closeAll();
            } else {
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
            phone: phone,
            password: password
        },
        success: function (data) {
            if (data.msg == "success") {
                var user = data.data;
                layer.msg("成功");
                login_state(user);

            } else {
                layer.msg(data.msg);
            }
            console.log(data);
        },
        error: function () {
            layer.alert("登陆时遇到未知错误");
        }
    });
}

function refresh() {
//    获取当前状态展示前台

    $.ajax({
        url: "/content/refresh",
        type: "POST",
        // 入参
        data: {},
        success: function (data) {
            console.log(data);
            var refreshVo = data.data;
            refresh_conternt(refreshVo.contentVo)
            login_state(refreshVo.user);

        },
        error: function () {
            // layer.msg("客户端请求有误");
            layer.alert("刷新时 遇到错误")
        }
    })
}

function refresh_conternt(contentVo) {
    if (contentVo == null) {
        return;
    }
    var jsn_content = contentVo.content;
    if (jsn_content == null) {
        return;
    }
    var jsn_enclo = contentVo.enclosuresList;
    var content_main = document.getElementById("content_main");
    $("#title").text(jsn_content.name);
    content_main.setAttribute("name", jsn_content.id);
    // console.log(jsn_content);

    if (jsn_content.img != null && jsn_content.img != "") {
        // console.log(jsn_content.img);
        display_frame("jsn_img", jsn_content.img);
    }
    if (jsn_content.code != null && jsn_content.code != "") {
        // console.log(jsn_content.code);
        display_frame("jsn_code", jsn_content.code);
    }
    if (jsn_content.text != null && jsn_content.text != "") {
        // console.log(jsn_content.text);
        display_frame("jsn_text", jsn_content.text);
    }
    if (jsn_content.video != null && jsn_content.video != "") {
        // console.log(jsn_content.video);
        display_frame("jsn_video", jsn_content.video);
    }

    if (jsn_enclo != null && jsn_enclo.length > 0) {
        display_enclo(jsn_enclo);
    }
}

function display_frame(frame_id, src_value) {
    var frame = document.getElementById(frame_id);
    frame.contentWindow.location.reload(true);
    $("#" + frame_id).attr("src", src_value + "?_ran=" + Math.floor(Math.random() * 100000));
    // frame.setAttribute("src", src_value);
    // $("#" + frame_id).css('display', 'block');
    $("#" + frame_id).parent().css('display', 'block')
    // frame.contentWindow.location.reload(true);
}

function display_enclo(enclo) {
    var parent = document.getElementById("enclosure");
    for (var i = 0; i < enclo.length; i++) {
        var a = document.createElement("a");
        var br = document.createElement("br");
        a.setAttribute("href", "/file/download?url=" + enclo[i].url + "&fileName=" + enclo[i].name);
        a.innerHTML = enclo[i].name;
        parent.appendChild(br);
        parent.appendChild(a);
    }
}

function display_content(data) {
    window.location.reload();
    var jsn_content = data.data.content;
    var jsn_enclo = data.data.enclosuresList;
    var content_main = document.getElementById("content_main");
    $("#title").text(jsn_content.name);
    content_main.setAttribute("name", jsn_content.id);
    // console.log(jsn_content);

    if (jsn_content.img != null && jsn_content.img != "") {
        // console.log(jsn_content.img);
        display_frame("jsn_img", jsn_content.img);
    }
    if (jsn_content.code != null && jsn_content.code != "") {
        // console.log(jsn_content.code);
        display_frame("jsn_code", jsn_content.code);
    }
    if (jsn_content.text != null && jsn_content.text != "") {
        // console.log(jsn_content.text);
        display_frame("jsn_text", jsn_content.text);
    }
    if (jsn_content.video != null && jsn_content.video != "") {
        // console.log(jsn_content.video);
        display_frame("jsn_video", jsn_content.video);
    }

    if (jsn_enclo != null && jsn_enclo.length > 0) {
        display_enclo(jsn_enclo);
    }
}

function login_state(user) {

    if (user != null) {


        $("#display-user").css("display", "block");
        $("#logout").css("display", "block");
        $("#bLogin").css("display", "none");
        $("#bReg").css("display", "none");

        $("#user").text(user.name);
        if (user.role == 0) {
            $("#add-tea").css("display", "block");
            $("#upload_view1").css("display", "block");
            $("#upload_view2").css("display", "block");

            // for (var id=101;id<=106;id++){
            //     $("#ad"+id).css("display", "block");
            // }
        } else if (user.role == 1) {
            $("#upload_view1").css("display", "block");
            $("#upload_view2").css("display", "block");
            // for (var id=101;id<=106;id++){
            //     $("#ad"+id).css("display", "block");
            // }
        } else {
            $("#upload_view1").css("display", "none");
            $("#upload_view2").css("display", "none");
            // for (var id=101;id<=106;id++){
            //     $("#ad"+id).css("display", "none");
            // }
        }


    } else {
        $("#display-user").css("display", "none");
        $("#logout").css("display", "none");
        $("#bLogin").css("display", "block");
        $("#bReg").css("display", "block");
        // for (var id=101;id<=106;id++){
        //     $("#ad"+id).css("display", "none");
        // }
    }
}

// function add_menu(main_id) {
//     if (main_id == 106) {
//         $("#resourse_url_view").css("display", "block");
//     } else {
//         $("#resourse_url_view").css("display", "none");
//     }
//     layer.open({
//         type: 1,
//         title: "新增菜单",
//         area: ["395px", "300px"],
//         content: $("#addMenuBox"),
//     });
// }

function do_add_menu(main_id) {
    var knowledgeName = $("#knowledge_name").val();
    var href = $("#resourse_url_view").val();
    if (href == null || href ==""){
        href = "#";
    }
    $.ajax({
        url: "/menu/add_menu",
        type: "POST",
        // 入参
        data: {
            knowledgeName:knowledgeName,
            href:href,
            parentMenuId:main_id

        },
        success: function (data) {
            if (data.msg == "success") {
                layer.msg("添加成功");
            } else {
                layer.alert(data.msg);
            }
            refresh();
        },
        error: function () {
            // layer.msg("客户端请求有误");
            layer.alert("新增知识模块时发生请求错误");
        }
    })
}