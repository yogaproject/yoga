//定义场馆id
var  venueId = null;
//getVenueId()
function getVenueId(){
    $.ajax({
        url : "/venue/getVenueId",
        type : "post",
        dataType :"json",
        //contentType : "application/json;charset=utf-8",
        data:{
            userId:$("#userId").val()
        },
        success: function(result) {
           venueId = result
        }
    });
}


//找到点击框的id
var id = null;
$("#coaches").click(function(){
    var chks = $("input:checked",this);
    id = chks.attr('data-id');
});


//查找所有教练类型
allCoachType()
function allCoachType(){
    $.ajax({
        url : "/venue/coachType",
        type : "get",
        //dataType :"json",
        contentType : "application/json;charset=utf-8",
        success: function(result) {
            for (var i = 0; i < result.length; i++) {
                var coachType = result[i].coachType;
                var option = $("<option></option>").text(coachType)
                $("#selection1").append(option);
            }
        }
    });
}
//查询所有教练流派
allCoachStyle()
function allCoachStyle(){
    $.ajax({
        url : "/venue/coachStyle",
        type : "get",
        //dataType :"json",
        contentType : "application/json;charset=utf-8",
        success: function(result) {
            for (var i = 0; i < result.length; i++) {
                var coachStyle = result[i].coachStyle;
                var option = $("<option></option>").text(coachStyle)
                $("#selection2").append(option);
            }
        }
    });
}

//通过模糊搜索场馆查询教练
function selectCoachByVagueConditions(){
    $.ajax({
        url : "/venue/vagueConditions",
        type : "post",
        //dataType :"json",
        //contentType : "application/json;charset=utf-8",
        data:{
            coachType:$("#selection1").val(),
            coachStyle:$("#selection2").val(),
            upExpectedSalary:$("#upExpectedSalary").val(),
            downExpectedSalary:$("#downExpectedSalary").val()
        },
       // dataType:"json",
        success: function(result) {
                var content = "";
                for(var i =0; i <result.length;i++){
                    content += "<tr><td><input type='checkbox' data-id="
                        + result[i].coachId+"></td>" +"<td>"
                        + result[i].coachType + "</td>" + "<td>"
                        + result[i].coachStyle + "</td>" + "<td>"
                        + result[i].expectedSalary + "</td>" + "<td>"
                        + result[i].authentication + "</td>"
                        + "<td><button onclick ='applyForSign("+result[i].coachId+")'>"
                        +"申请签约</button></td>"
                        + "</tr>";
                    + "</tr>";
                 }
            $("#coaches").html(content);
        }
    });
}
//根据教练id查询教练详情
    function selectCoachByCoachId(){
        $.ajax({
            url : "/venue/venueSelectCoach",
            type:"post",
            data:{
                coachId:id
            },
            dataType:"json",
        success: function(result) {
            if (id == null) {
                alert("请选择教练")
            } else {
                var content =
                    "<ul class='list-group' style='text-align:center;font-size:10px;'>"
                    + "<li class='list-group-item' style='text-align:center;'>教练姓名: "
                    + result[0].realName
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>教练类型: "
                    + result[0].coachType
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>教练流派: "
                    + result[0].coachStyle
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>教练电话: "
                    + result[0].userPhone
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>教练qq: "
                    + result[0].userQq
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>签约场馆: "
                    + result[0].venueName+"进入场馆"
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>成交次数: "
                    + result[0].dealAccount
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>未完成次数: "
                    + result[0].notCompleted
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>教练详情: "
                    + result[0].coachDetail
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>好评次数: "
                    + result[0].goodComment
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>中评次数: "
                    + result[0].commonComment
                    + "</li>"
                    + "<li class='list-group-item' style='text-align:center;'>差评次数: "
                    + result[0].badComment
                    + "</li>"
                    + "</ul>"
                $("#information").html(content)
                $("#venueId").attr("value", result.venueId)
            }
        }
    });
}
//场馆发起申请，向教练申请签约
function applyForSign(){
    $.ajax({
        url : "/venue/applyForSign",
        type : "post",
        dataType :"json",
        // contentType : "application/json;charset=utf-8",
        data:{
            coachId:id,
            venueId:venueId,
        },
        success: function(result) {
           alert(result)
        }
    });
}
