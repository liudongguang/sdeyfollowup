jQuery(document).ready(function () {
    var datepickerOpt = {
        format: 'YYYY-MM-DD',
        showTodayButton: true
    }
    $('#startTimeID').datetimepicker(datepickerOpt);
    $('#endTimeID').datetimepicker(datepickerOpt);
    initTable($("#mainContent"));
    ///////////////////////
    initAjaxForm($("#subForm"), $("#subBT"), function (data) {
        $("#mainContent").empty().html(data);
    });
    //////
    initSetData();
})