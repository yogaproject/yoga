//根据场馆id 查询所有产品的信息
venueAllProduct()
function venueAllProduct(){
    $.ajax({
        url : "/product/venueAllProduct",
        type : "post",
        //dataType :"json",
        // contentType : "application/json;charset=utf-8",
        data:{
            venueId:1
        },
        // dataType:"json",
        success: function(result) {//返回的是产管信息的对象list
            var content = "";
            for(var i =0; i <result.length;i++){
                content += "<tr><td><input type='checkbox' data-id="
                    + result[i].productId+"></td>" +"<td>"
                    + result[i].productType + "</td>" + "<td>"
                    + result[i].productDetail+ "</td>" + "<td>"
                    + "<td><button type='button' class='btn btn-primary' data-toggle='modal' " +
                    "data-target='#selectProductByPid' onclick ='venueSelectProduct("+result[i].productId+")'>"
                    +"产品详情</button></td>"
                    + "</tr>";
                + "</tr>";
            }
            $("#myProducts").html(content);
        }
    });
}
//场馆查询单个产品
function venueSelectProduct(productId) {
    $.ajax({
        url:"/product/venueSelectProduct",
        type:"post",
       // contentType:"application/json;charset=utf-8",
        data:{
            productId:productId,
        },
        success:function (result) {

                var content =
                    "<ul class='list-group' style='text-align:center;font-size:10px;'>"
                    + "<li class='list-group-item' style='text-align:center;'>产品类型: "
                    + result.productType
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>产品价格: "
                    + result.productPrice
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>产品详情: "
                    + result.productDetail
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>总共时长: "
                    + result.totalTime
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>产品等级: "
                    + result.productLevel
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>开始时间: "
                    + result.startTime
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>结束时间: "
                    + result.endTime
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>消耗时间: "
                    + result.consumeTime
                    + "</li>"
                    + "</ul>"
                $("#productInformation").html(content)
                $("#venueId").attr("value", result.venueId)
        }
    })
}