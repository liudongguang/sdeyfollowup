jQuery(document).ready(function () {
    var datepickerOpt = {
        format: 'YYYY-MM-DD',
        showTodayButton: true
    }
    $('#startTimeID').datetimepicker(datepickerOpt);
    $('#endTimeID').datetimepicker(datepickerOpt);
    initTable($("#mainContent"));
    //////
    initSetData();
    /////

    initAjaxForm($("#subForm"), $("#subBT"), function (data) {
        $("#mainContent").empty().html(data);
    });

    $("#exportHandlerBT").click(function () {
        var startTime=$('#startTimeID').val();
        var endTime=$('#endTimeID').val();
        var ksid=$("#singleSelect option:selected").val();
        if(startTime&&endTime){
            location.href=$("#basePath").val()+"sdeyfollowupAdmin/exportMessage?startTime="+startTime+"&endTime="+endTime+"&ksid="+ksid;
        }else{
            layer.msg("必须选择日期！");
        }

    });
});