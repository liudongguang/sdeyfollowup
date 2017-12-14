jQuery(document).ready(function () {
    $("#tongjiHandlerBT").click(function () {
        var jq_DateVal=$("#selectFenPeiDetail option:selected").val();
        if(jq_DateVal){
            var url="/sdeyfollowup/handlerSFTongji?importDateStr="+jq_DateVal;
            jumpPageNoAuthority(url,$("#dataContentID"));
        }else{
            layer.msg("未选择日期");
        }
    })
    $("#exportHandlerBT").click(function () {
        var jq_DateVal=$("#selectFenPeiDetail option:selected").val();
        if(jq_DateVal){
            location.href=$("#basePath").val()+"sdeyfollowup/exportHandlerSFTongji?importDateStr="+jq_DateVal;
        }else{
            layer.msg("未选择日期");
        }

    });
    initSetData();
    var jq_DateVal=$("#selectFenPeiDetail option:selected").val();
    if(jq_DateVal){
        var url="/sdeyfollowup/handlerSFTongji?importDateStr="+jq_DateVal;
        jumpPageNoAuthority(url,$("#dataContentID"));
    }
});