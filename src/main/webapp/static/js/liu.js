//保存知识 参数为二级目录Id
function save_known(menuIId) {
    var formData = new FormData();

    // console.info($("#main-example-page").attr("name"));
    formData.append("fileDoc", $("#file-doc")[0].files[0]);
    formData.append("menuIId", menuIId);
    formData.append("knownId", $("#Teaching-documents-page").attr("name"));
    formData.append("text", $("#text-known").val());
    formData.append("fileImg", $("#file-img")[0].files[0]);

    // var menuId = $("#edit-example-page").val();
    // var title = $("#title-example").val();
    // var content = $("#content-example").val();
    // var runResult = $("#run-result-example").val();
    // var interpret = $("#interpret-example").val();
    $.ajax({
        url: "/known/save",
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

function cancel_edit_known(known_id) {
    display_div("main-example-page");
}

function display_known(id) {

    display_div("Teaching-documents-page");
    $("#save-konwn").attr("onclick","save_known("+id+")")
    var formData = new FormData();
    formData.append("menuId", id);
    // formData.append("title", $("#title-example").val());
    $.ajax({
        url: "/known/data",
        type: "POST",
        data: formData,
        contentType: false,
        processData: false,//这个很有必要，不然不行
        dataType: "json",
        mimeType: "multipart/form-data",
        success: function (data) {
            layer.msg("请求成功");
            var parent = document.getElementById("images");
            var knownVo = data.data;
            var known = knownVo.known;
            var resources = knownVo.resources;

            $("#display-known-text").text(known.text);
            for (var i=0; i < resources.length;i++){
                if (document.getElementById(resources[i].id) == null) {
                    var img = document.createElement("img");

                    img.setAttribute("id", resources[i].id);
                    // li.setAttribute("onclick", "aj_display_content(" + list[i].id + ")");


                    img.setAttribute("src", resources[i].url);
                    parent.appendChild(img);
                }
            }
            // //     <img src="/upload/33-1.png">
            // //         <img src="/upload/33-1.png">
            // //         <img src="/upload/33-1.png">
            // //         </div>
            //
            $("#doc-address").attr("href", "/file/download?url=" + known.attachment + "&fileName=" + known.name);
            $("#Teaching-documents-page").attr("name", known.id);
            console.info(data);
        },
        error: function () {
            layer.msg("客户端请求有误");
        }
    });
}

function add_known(id) {
    display_div("edit-Teaching-documents");
    $("#edit-Teaching-documents").attr("name", "9999999999");
    $("#save").attr("onclick", "save_example(" + id + ")");

}

function edit_known(known_id) {

    var formData = new FormData();
    formData.append("id", $("#Teaching-documents-page").attr("name"));
    console.info($("#Teaching-documents-page").attr("name"));
    $.ajax({
        url: "/known/get_known_by_id",
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
                display_div("edit-Teaching-documents");
                var known = data.data;
                // $("#title-example").text(example.title);
                // $("#content-example").text(example.content);
                // $("#interpret-example").text(example.interpret);
                // $("#run-result-example").text(example.runResult);
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