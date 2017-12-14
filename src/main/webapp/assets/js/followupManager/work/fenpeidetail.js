jQuery(document).ready(function () {
    initTable($("#detailTableDivID"));
    $("tr[id^=trid_]").click(function () {
        var managerid=$(this).attr("id").split("_")[1];
        var managerName=$(this).attr("id").split("_")[2];
        var jq_tableDIV=$("#detailTableDivID");
        var jq_DateVal=$("#selectFenPeiDetail option:selected").val();
        if(jq_DateVal){
            var url="/sdeyfollowup/getFenPeiDetailForOffice?managerid="+managerid+"&dateStr="+jq_DateVal;
            layerWindow(url, "管理员："+managerName+" 科室分配情况", 400, 500);
        }else{
             layer.msg("未选择日期");
        }
    })
})