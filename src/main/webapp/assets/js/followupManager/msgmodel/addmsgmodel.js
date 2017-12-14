jQuery(document).ready(function () {
    var jq_content = $("#mainContent");
    initBackBt($("#backBTID"), jq_content);
    initAjaxForm($("#subForm"), $("#subBT"), function (data) {
        $("#mainContent").empty().html(data);
    }, true, function (form, options) {
        var ajaxOpt = {
            paramurl: $("#basePath").val() + "/sdeyfollowup/checkMinganci",
            paramdata: {"yanzhengStr": $("textarea[name=msgcontent]").val()},
            dataType: 'json',
            callbackFun: function (data) {
                if (data.length == 0) {
                    form.ajaxSubmit(options);
                } else {
                    var ffstr="";
                    for (var index in data) {
                       ffstr+=data[index]+"；"
                    }
                   layer.alert("含有非法字符："+ffstr)
                }
            }
        };
        ajaxRun(ajaxOpt);
    });
});