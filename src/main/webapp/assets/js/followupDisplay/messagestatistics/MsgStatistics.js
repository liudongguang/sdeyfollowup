initAjaxForm($("#subForm"), $("#subBT"), function (data) {
    $("#msgstatisticsContent").empty().html(data);
},true);
var datepickerOpt = {
    format: 'YYYY-MM-DD',
    showTodayButton: true
}
$('#startTimeID').datetimepicker(datepickerOpt);
$('#endTimeID').datetimepicker(datepickerOpt);
