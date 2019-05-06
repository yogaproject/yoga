//根据场馆id，展示场馆信息
selectVenueVOByVenueId()
function selectVenueVOByVenueId(){
    $.ajax({
        url : "/venue/selectVenueVOByVenueId",
        type : "post",
        dataType :"json",
        //contentType : "application/json;charset=utf-8",
        data:{
            venueId:1,
        },
        success: function(result) {//返回的product对象
            var content ="<div class='container'>"
                +"<h2>图片</h2>"
                +"<img src='"+result.img1+"' class='img-responsive' alt='Cinque Terre' width='304' height='236'>"
                +"</div>"
                +"<ul class='list-group' style='text-align:center;font-size:10px;'>"
                + "<li class='list-group-item' style='text-align:center;'>场馆id: "
                + result.venueId
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>userId: "
                + result.userId
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>场馆名称: "
                + result.realName
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>图片1: "
                + result.img1
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>产管详情: "
                + result.venueDetail
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>联系电话: "
                + result.userPhone
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>邮箱: "
                + result.userEmail
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>微信: "
                + result.userWechat
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>QQ: "
                + result.userQq
                + "</li>"
                + "<li class='list-group-item' style='text-align:center;'>点击量: "
                + result.clicks
                + "</li>"
                + "</ul>"
            $("#venueInformation").html(content)
            $("#venueId").attr("value", result.venueId)
        }
    });
}