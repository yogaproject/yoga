//场馆完善场馆信息(没有包含教练安排)
function venuePerfectInformation(){
    $.ajax({
        url : "/venue/venuePerfectInformation",
        type : "post",
        //dataType :"json",
        //contentType : "application/json;charset=utf-8",
        data:{
            venueId:1,//需要场馆id
            userLocation:$("#userLocation").val(),
            realName:$("#realName").val(),
            userPhone:$("#userPhone").val(),
            userQq:$("#userQq").val(),
            img1:$("#avatar").val(),
            venueDetail:$("#venueDetail").val()
        },
        //dataType:"json",
        success: function(result) {
            alert(result)
        }
    });
}
/*
//根据场馆id查找属于这个场馆的教练
venueSelectCoach()
function venueSelectCoach(){
    $.ajax({
        url : "/venue/venueSelectCoach",
        type : "post",
        //dataType :"json",
        contentType : "application/json;charset=utf-8",
        data:{
            venueId:1
        },
        dataType:"json",
        success: function(result) {
            for (var i = 0; i < result.length; i++) {
                var coachType = result[i].realName;
                var option = $("<option></option>").text(coachType)
                $("#selection666").append(option);
            }
        }
    });
}*/
//ajax提交图片上传方法
function uploadImg() {
    if($("#file").val() != "") {
        $.ajaxFileUpload({
            type: "POST",
            url:"/upload/newFile",
            dataType: "json",
            fileElementId:"file",  // 文件的id
            success: function(d){
                if(d.code == 1) {
                    //alert("上传成功");
                    //图片显示
                    $("#avatar").attr("value",d.data);
                    $("#avatarShow").attr("src",d.data);
                }
            },
            error: function () {
                alert("上传失败");
            },
        });
    } else {
        alert("请先选择文件");
    }
}