jQuery(document).ready(function () {
    var jq_content = $("#mainContent");
    initBackBt($("#backBTID"), jq_content);
    initAjaxForm($("#subForm"), $("#subBT"), function (data) {
        $("#mainContent").empty().html(data);
    }, true, function (form, options) {
        var ajaxOpt = {
            paramurl: $("#basePath").val() + "/sdeyfollowup/checkBlackPhone",
            paramdata: {"phone": $("input[name=blackphone]").val()},
            dataType: 'json',
            callbackFun: function (data) {
                if (data.errorCode == 1) {
                    options.zzcid = layer.load(0, {
                        shade: [0.8, '#fff']
                        // 0.1透明度的白色背景
                    });
                    form.ajaxSubmit(options);
                } else {
                    layer.alert("手机号已添加！");
                }
            }
        };
        ajaxRun(ajaxOpt);
    });
});