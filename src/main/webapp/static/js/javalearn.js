function clean() {
    // $("#user1").val("");
    var fileCode = $("#file-code")[0].files[0];
    if (fileCode == null) {
        layer.msg("fileCode为空");
    } else {
        layer.msg("fileCode将进行更新");
    }
}

function ShowLoginText() {
    layer.open({
        type: 1,
        title: "登录",
        area: ["395px", "300px"],
        content: $("#loginBox"),
    });
}

function ShowResourceBox(menuId) {
    $("#add_resource").attr("onclick", "add_resource(" + menuId + ")");
    layer.open({
        type: 1,
        title: "添加资源",
        area: ["395px", "300px"],
        content: $("#addResourceBox"),
    });
}

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


//1.0.2
function edit(example_id) {

    var formData = new FormData();
    formData.append("id", $("#main-example-page").attr("name"));
    console.info($("#main-example-page").attr("name"));
    $.ajax({
        url: "/example/get_example_by_id",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        // data:{
        //     interpret:"456546",
        //     fileCode:$("#file-code")[0].files[0]
        // },
        success: function (data) {
            layer.msg("请求成功");
            if (data.msg == "success") {
                display_div("edit-example-page");
                var example = data.data;
                $("#title-example").text(example.title);
                $("#content-example").text(example.content);
                $("#interpret-example").text(example.interpret);
                $("#run-result-example").text(example.runResult);
            } else {
                layer.msg(data.msg);
            }

            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}


function cancel_edit(example_id) {
    display_div("main-example-page");
}

function add_menu(main_id) {
    $("#add_menu").attr('onclick', 'do_add_menu(' + main_id + ')');
    layer.open({
        type: 1,
        title: "新增菜单",
        area: ["395px", "300px"],
        content: $("#addMenuBox"),
    });
}

function do_add_menu(main_id) {
    var knowledgeName = $("#knowledge_name").val();
    $.ajax({
        url: "/menu/add_menu",
        type: "POST",
        // 入参
        data: {
            knowledgeName: knowledgeName,
            parentMenuId: main_id

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

function menu_over(id) {
    get_menu_list(id);
    $("#ul" + id).css('display', 'block');
}

function menu_out(id) {
    $("#ul" + id).css('display', 'none');
}


function get_menu_list(id) {
    $.ajax({
        url: "/menu/data",
        type: "GET",
        // 入参
        data: {
            // verifyCode:$("#verifyCode").val()
            menuId: id
        },
        success: function (data) {
            var list = data.data;
            var parent = document.getElementById(id);
            var ul = document.createElement("ul");
            parent.appendChild(ul);
            ul.setAttribute("id", "ul" + id);
            ul.setAttribute("style", "position: absolute;z-index:9999");


            for (var i = 0; i < list.length; i++) {
                // 动态添加
                //防止多次增加
                if (document.getElementById(list[i].id) == null) {
                    var li = document.createElement("li");

                    li.setAttribute("id", list[i].id);
                    // li.setAttribute("onclick", "aj_display_content(" + list[i].id + ")");
                    if (id == 103 || id == 102) {
                        // li.setAttribute("onclick", "get_menuIV(" +id+","+ list[i].id + ")");
                        li.setAttribute("onclick", "get_menuIV(" + id + "," + list[i].id + ")");
                    }
                    if (id == 106) {
                        li.setAttribute("onclick", "get_resources(" + list[i].id + ")");
                    }

                    li.setAttribute("style", "float:none;opacity:0.8;");
                    li.innerHTML = list[i].name;
                    ul.appendChild(li);
                }
            }
            //添加知识点
            if (document.getElementById("li" + id) == null) {
                var li = document.createElement("li");

                li.setAttribute("id", "li" + id);
                li.setAttribute("onclick", "add_menu(" + id + ")");
                //加用户判断
                li.setAttribute("style", "float:none;opacity:0.8;");

                li.innerHTML = "新增";
                ul.appendChild(li);
            }
            // console.log(data);
        },
        error: function () {
            // layer.msg("客户端请求有误");
            alert("客户端请求有误");
        }
    });
}

//获取三级目录或更次级目录或者显示内容
function get_menuIV(id1, id) {

    display_div("menuIV-view");

    $.ajax({
        url: "/menu/data",
        type: "GET",
        // 入参
        data: {
            // verifyCode:$("#verifyCode").val()
            //上级目录Id
            menuId: id
        },
        success: function (data) {
            var list = data.data;
            var ul = document.getElementById("menuIV-view");

            for (var i = 0; i < list.length; i++) {
                // 动态添加
                //防止多次增加
                if (document.getElementById(list[i].id) == null) {
                    var li = document.createElement("li");

                    li.setAttribute("id", list[i].id);
                    // li.setAttribute("onclick", "aj_display_content(" + list[i].id + ")");

                    if (id1 == 103) {
                        li.setAttribute("onclick", "display_example(" + list[i].id + ")");
                    } else {
                        li.setAttribute("onclick", "display_known(" + list[i].id + ")");
                    }

                    li.setAttribute("style", "float:none;opacity:0.8;");
                    li.innerHTML = list[i].name;
                    ul.appendChild(li);
                }
            }
            //添加知识点
            if (document.getElementById("li" + id) == null) {
                var li = document.createElement("li");

                li.setAttribute("id", "li" + id);
                if (id1 == 103) {
                    li.setAttribute("onclick", "add_example(" + id + ")");
                } else {
                    li.setAttribute("onclick", "add_menu(" + id + ")");
                }

                //加用户判断
                li.setAttribute("style", "float:none;opacity:0.8;");

                li.innerHTML = "新增";
                ul.appendChild(li);
            }
            // console.log(data);
        },
        error: function () {
            // layer.msg("客户端请求有误");
            alert("客户端请求有误");
        }
    });
}

function add_example(id) {
    $("#edit-example-page").css("display", "block");
    $("#main-example-page").attr("name", "9999999999");
    $("#save").attr("onclick", "save_example(" + id + ")");
    $("#main-example-page").css("display", "none");
    $("#resource-page").css("display", "none");
}

//保存实例 参数为二级目录Id
function save_example(menuIId) {
    var formData = new FormData();

    formData.append("fileCode", $("#file-code")[0].files[0]);
    formData.append("menuIId", menuIId);

    formData.append("exampleId", $("#main-example-page").attr("name"));
    formData.append("title", $("#title-example").val());
    formData.append("content", $("#content-example").val());
    formData.append("runResult", $("#run-result-example").val());
    formData.append("interpret", $("#interpret-example").val());
    console.info($("#main-example-page").attr("name"));
    console.info($("#title-example").val());
    console.info($("#content-example").val());
    console.info($("#run-result-example").val());
    console.info($("#interpret-example").val());
    console.info(menuIId);
    // var menuId = $("#edit-example-page").val();
    // var title = $("#title-example").val();
    // var content = $("#content-example").val();
    // var runResult = $("#run-result-example").val();
    // var interpret = $("#interpret-example").val();
    $.ajax({
        url: "/example/save",
        type: 'POST',
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            if (data.msg == "success") {
                layer.msg("请求成功 ");
            } else {
                layer.msg(data.msg);
            }
        },
        error: function () {
            // layer.msg("客户端请求有误");
            layer.msg("客户端请求有误");
        }
    });
}

//展示实例，id为三级目录Id
function display_example(id) {

    display_div("main-example-page");
    var formData = new FormData();
    formData.append("menuId", id);
    // formData.append("title", $("#title-example").val());
    $.ajax({
        url: "/example/data",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        // data:{
        //     interpret:"456546",
        //     fileCode:$("#file-code")[0].files[0]
        // },
        success: function (data) {
            layer.msg("请求成功");

            var example = data.data;
            $("#display-title-example").text(example.title);
            $("#display-content-example").text(example.content);
            $("#display-interpret-example").text(example.interpret);
            $("#display-run-result-example").text(example.runResult);
            $("#time-example").text(formatDate(new Date(example.createTime)));
            $("#code-address").attr("href", "/file/download?url=" + example.attachment + "&fileName=" + example.title);
            $("#main-example-page").attr("name", example.id);
            $("#scan-count-example").text(example.scanCount);
            $("#down-count-example").text(example.downloadCount);
            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

//一级二级目录均可
function get_resources(menuId) {

    var formData = new FormData();
    formData.append("menuId", menuId);

    $.ajax({
        url: "/resource/data",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        // data:{
        //     interpret:"456546",
        //     fileCode:$("#file-code")[0].files[0]
        // },
        success: function (data) {
            layer.msg("请求成功");
            display_div("resource-page");
            var resources = data.data;
            var parent = document.getElementById("resource-page");

            for (var i = 0; i < resources.length; i++) {
                if (document.getElementById(resources[i].id) == null) {
                    var div = document.createElement("div");
                    div.setAttribute("id", resources[i].id);
                    div.setAttribute("style", "margin: 1cm 0cm 0cm 1cm;");
                    var a = document.createElement("a");
                    a.setAttribute("href", resources[i].url);
                    a.setAttribute("onclick", "countres(" + resources[i].id + ")");
                    a.setAttribute("href", "/file/download?url=" + resources[i].url + "&fileName=" + resources[i].name);
                    a.setAttribute("style", "left: 700px;position: absolute;white-space : nowrap");
                    a.innerHTML = "下载";
                    var h2 = document.createElement("h2");
                    h2.setAttribute("style", "left: 550px;position: absolute;");
                    h2.innerHTML = "下载次数：";
                    var span = document.createElement("span");
                    span.setAttribute("id", "resource-download-count");
                    span.innerHTML = resources[i].downloadCount;
                    h2.appendChild(span);

                    var h = document.createElement("h2");
                    h.setAttribute("style", "left: 200px;position: absolute;");
                    h.innerHTML = "创建时间：";

                    var span1 = document.createElement("span");
                    span1.setAttribute("id", "resource-download-count");
                    span1.innerHTML = formatDate(new Date(resources[i].createTime));
                    h.appendChild(span1);

                    var br = document.createElement("br");

                    div.innerHTML = resources[i].name + "<br> 描述：" + resources[i].description;
                    div.appendChild(br);
                    div.appendChild(a);
                    div.appendChild(h2);
                    div.appendChild(h);
                    parent.appendChild(div);
                }
            }

            if (document.getElementById("res" + menuId) == null) {
                var upload_div = document.createElement("div");
                upload_div.setAttribute("id", "res" + menuId);
                upload_div.setAttribute("style", "margin: 1cm 0cm 0cm 1cm;")
                var button = document.createElement("button");
                button.setAttribute("id", "show_resource_box");
                button.setAttribute("onclick", "ShowResourceBox(" + menuId + ")");
                button.innerHTML = "上传资源";
                upload_div.appendChild(button);
                parent.appendChild(upload_div);
            }
            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

//更目录不可添加
function add_resource(menuId) {

    var formData = new FormData();
    //二级目录Id
    formData.append("menuId", menuId);
    //资源描述
    formData.append("description", $("#resourse_description").val());
    //文件
    formData.append("fileResource", $("#file-resource")[0].files[0]);
    $.ajax({
        url: "/resource/add",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        // data:{
        //     interpret:"456546",
        //     fileCode:$("#file-code")[0].files[0]
        // },
        success: function (data) {
            layer.msg("请求成功");

            var example = data.data;
            $("#display-title-example").text(example.title);
            $("#display-content-example").text(example.content);
            $("#display-interpret-example").text(example.interpret);
            $("#display-run-result-example").text(example.runResult);
            $("#code-address").attr("href", example.attachment);
            $("#main-example-page").attr("name", example.id);
            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });

}

//负责替换展示div模块
function display_div(div_module) {
    var modules = ["edit-example-page", "main-example-page",
        "resource-page", "menuIV-view", "Teaching-documents-page",
        "edit-Teaching-documents","sb-example-class","sb-example"]

    for (var i = 0; i < modules.length; i++) {
        $("#" + modules[i]).css("display", "none");
    }
    if (div_module == "edit-example-page" || div_module == "main-example-page"
        || div_module == "Teaching-documents-page" || div_module == "edit-Teaching-documents") {
        $("#menuIV-view").css("display", "block");
    }
    $("#" + div_module).css("display", "block");

}

function countplus() {
    var formData = new FormData();
    //二级目录Id
    formData.append("exampleId", $("#main-example-page").attr("name"));
    $.ajax({
        url: "/example/addCount",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            layer.msg("请求成功");
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

function countres(id) {
    var formData = new FormData();
    //二级目录Id
    formData.append("resId", id);
    $.ajax({
        url: "/resource/addCount",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            layer.msg("请求成功");
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

function formatDate(now) {
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var date = now.getDate();
    var hour = now.getHours();
    var minute = now.getMinutes();
    var second = now.getSeconds();
    return year + "-" + month + "-" + date + " " + hour + ":" + minute + ":" + second;
}

//1.0.3
function get_menu2(id) {
    var formData = new FormData();
    //二级目录Id
    formData.append("id", id);
    $.ajax({
        url: "/menu/get_menu2",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            var menu2s = data.data;
            display_div("sb-example-class");
            $("#sb-add-menu").attr("onclick", "sb_add_menu(" + id + ")");
            for (var i = 0; i < menu2s.length; i++) {
                var tbody = document.getElementById("table-menu2")
                var tr = document.createElement("tr");
                tr.setAttribute("id", menu2s[i].id);
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");

                var td4 = document.createElement("td");
                var td5 = document.createElement("td");
                var td6 = document.createElement("td");
                var td7 = document.createElement("td");
                td1.innerHTML = menu2s[i].name;
                td2.innerHTML = menu2s[i].description;
                td4.innerHTML = formatDate(new Date(menu2s[i].createTime));
                td5.innerHTML = menu2s[i].creator;
                td6.innerHTML = "<a href='#'>删除</a>";
                td6.setAttribute("onclick", "sb_delete(" + menu2s[i].id + ")");
                td7.innerHTML = "<a href='#'>修改</a>";
                td7.setAttribute("onclick", "sb_edit(" + menu2s[i].id + ")");
                td1.setAttribute("onclick", "sb_get_example(" + menu2s[i].id + ")");
                td2.setAttribute("onclick", "sb_get_example(" + menu2s[i].id + ")");

                td4.setAttribute("onclick", "sb_get_example(" + menu2s[i].id + ")");
                td5.setAttribute("onclick", "sb_get_example(" + menu2s[i].id + ")");
                tr.appendChild(td1);
                tr.appendChild(td2);

                tr.appendChild(td4);
                tr.appendChild(td5);
                tr.appendChild(td6);
                tr.appendChild(td7);
                tbody.appendChild(tr);
            }
            layer.msg("请求成功");
            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}
function sb_edit(id) {
    $("#sb_edit_menu").attr('onclick', 'sb_do_edit_menu(' + id + ')');
    layer.open({
        type: 1,
        title: "编辑实例教程子模块",
        area: ["530px", "400px"],
        content: $("#sbeditMenuBox"),
    });
    var formData = new FormData();
    formData.append("id", id);
    // formData.append("title", $("#title-example").val());
    $.ajax({
        url: "/menu/getMenu",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            // layer.msg("请求成功");
            var menu = data.data;
            $("#edit_menu_name").text(menu.name);
            $("#edit_menu_description").text(menu.description);
            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });

}
function sb_do_edit_menu(id) {
    var menuName = $("#edit_menu_name").val();
    var menuDescription = $("#edit_menu_description").val();
    $.ajax({
        url: "/menu/sb_edit_menu",
        type: "POST",
        // 入参
        data: {
            menuName: menuName,
            menuDescription: menuDescription,
            id:id
        },
        success: function (data) {
            if (data.msg == "success") {
                layer.msg("修改成功");
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
//获取实例列表
function sb_get_example(id) {
    var formData = new FormData();
    //二级目录Id
    formData.append("id", id);
    $.ajax({
        url: "/example/sb_get_examples",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            display_div("sb-example");
            var examples = data.data;
            $("#sb-add-example").attr("onclick", "sb_add_example(" + id + ")");
            for (var i = 0; i < examples.length; i++) {
                var tbody = document.getElementById("table-example");

                var tr = document.createElement("tr");
                tr.setAttribute("id", examples[i].id);
                var td1 = document.createElement("td");
                var td2 = document.createElement("td");
                var td3 = document.createElement("td");
                var td4 = document.createElement("td");
                var td5 = document.createElement("td");
                var td6 = document.createElement("td");
                var td7 = document.createElement("td");
                var td8 = document.createElement("td");
                td1.innerHTML = examples[i].title;
                td2.innerHTML = examples[i].description;
                td3.innerHTML = examples[i].scanCount;
                td4.innerHTML = examples[i].downloadCount;
                td5.innerHTML = formatDate(new Date(examples[i].createTime));
                td6.innerHTML = examples[i].creator;
                td7.innerHTML = "<a href='#'>删除</a>";
                td7.setAttribute("onclick", "sb_delete_example(" + examples[i].id + ")");
                // td8.innerHTML = "<a href='#'>修改</a>";
                // td8.setAttribute("onclick", "sb_edit(" + examples[i].id + ")");
                td1.setAttribute("onclick", "sb_display_example(" + examples[i].id + ")");
                td2.setAttribute("onclick", "sb_display_example(" + examples[i].id + ")");
                td3.setAttribute("onclick", "sb_display_example(" + examples[i].id + ")");
                td4.setAttribute("onclick", "sb_display_example(" + examples[i].id + ")");
                td5.setAttribute("onclick", "sb_display_example(" + examples[i].id + ")");
                tr.appendChild(td1);
                tr.appendChild(td2);
                tr.appendChild(td3);

                tr.appendChild(td4);
                tr.appendChild(td5);
                tr.appendChild(td6);
                tr.appendChild(td7);
                // tr.appendChild(td8);
                tbody.appendChild(tr);
            }
            layer.msg("请求成功");
            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });

}

function sb_display_example(id) {
    display_div("main-example-page");
    var formData = new FormData();
    formData.append("id", id);
    // formData.append("title", $("#title-example").val());
    $.ajax({
        url: "/example/getExample",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        // data:{
        //     interpret:"456546",
        //     fileCode:$("#file-code")[0].files[0]
        // },
        success: function (data) {
            layer.msg("请求成功");

            var example = data.data;
            $("#display-title-example").text(example.title);
            $("#display-content-example").text(example.content);
            $("#display-interpret-example").text(example.interpret);
            $("#display-run-result-example").text(example.runResult);
            $("#time-example").text(formatDate(new Date(example.createTime)));
            $("#code-address").attr("href", "/file/download?url=" + example.attachment + "&fileName=" + example.title);
            $("#main-example-page").attr("name", example.id);
            $("#scan-count-example").text(example.scanCount);
            $("#down-count-example").text(example.downloadCount);
            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

function sb_delete(id) {
    $.ajax({
        url: "/menu/sb_delete_menu",
        type: "GET",
        // 入参
        data: {
            id:id
        },
        success: function (data) {
            if (data.msg == "success") {
                layer.msg("删除成功");
            } else {
                layer.alert(data.msg);
            }
        },
        error: function () {
            // layer.msg("客户端请求有误");
            layer.alert("新增知识模块时发生请求错误");
        }
    })
}
function sb_delete_example(id) {
    $.ajax({
        url: "/example/sb_delete_example",
        type: "GET",
        // 入参
        data: {
            id:id
        },
        success: function (data) {
            if (data.msg == "success") {
                layer.msg("删除成功");
            } else {
                layer.alert(data.msg);
            }
        },
        error: function () {
            // layer.msg("客户端请求有误");
            layer.alert("新增知识模块时发生请求错误");
        }
    })
}


function sb_add_menu(id) {
    $("#sb_add_menu").attr('onclick', 'sb_do_add_menu(' + id + ')');
    layer.open({
        type: 1,
        title: "新增实例教程子模块",
        area: ["530px", "400px"],
        content: $("#sbaddMenuBox"),
    });
}

function sb_do_add_menu(id) {
    var menuName = $("#menu_name").val();
    var menuDescription = $("#menu_description").val();
    $.ajax({
        url: "/menu/sb_add_menu",
        type: "POST",
        // 入参
        data: {
            menuName: menuName,
            menuDescription: menuDescription,
            parentId:id
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
function sb_add_example(id) {
    display_div("edit-example-page");
    $("#main-example-page").attr("name", "9999999999");
    $("#save").attr("onclick", "save_example(" + id + ")");
}