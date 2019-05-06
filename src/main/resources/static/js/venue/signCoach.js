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

//根据场馆id查找属于这个场馆的教练
venueSelectCoach()
function venueSelectCoach(){
    $.ajax({
        url : "/venue/venueSelectCoach",
        type : "post",
        //dataType :"json",
        contentType : "application/json;charset=utf-8",
        data:{
            venueId:venueId
        },
        dataType:"json",
        success: function(result) {
            var content = "";
            for(var i =0; i <result.length;i++){
                content += "<tr><td><input type='checkbox' data-id="
                    + result[i].coachId+"></td>" +"<td>"
                    + result[i].realName + "</td>" + "<td>"
                    + result[i].coachType + "</td>" + "<td>"
                    + result[i].coachStyle + "</td>" + "<td>"
                    + result[i].userPhone + "</td>"
                    + "<td><button onclick ='venueBreakCoach("+result[i].coachId+")'>"
                    +"解约</button></td>"
                    + "</tr>";
                + "</tr>";
            }
            $("#coaches").html(content);
        }
    });
}

//场馆解约教练
function venueBreakCoach(coachId){
    $.ajax({
        url : "/venue/venueBreakCoach",
        type : "post",
        dataType :"json",
        //contentType : "application/json;charset=utf-8",
        data:{
            coachId:coachId
        },
        success: function(result) {
            alert(result.message)
        }
    });
}
