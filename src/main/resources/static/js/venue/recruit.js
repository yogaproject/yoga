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
                $("#selection3").append(option);
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
                $("#selection4").append(option);
            }
        }
    });
}

//场馆发布招聘信息
function venueAddRecruit(){
    $.ajax({
        url : "/venue/venueAddRecruit",
        type : "post",
        dataType :"json",
        // contentType : "application/json;charset=utf-8",
        data:{
            coachType:$("#selection3").val(),
            coachStyle:$("#selection4").val(),
            salaryDown:$("#downExpectedSalary").val(),
            salaryUp:$("#upExpectedSalary").val()
        },
        success: function(result) {
            alert(result.message)
        }
    });
}
