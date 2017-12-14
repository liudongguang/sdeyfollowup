<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<form id="subForm" action="sdeyfollowup/statisticsMessage" class="form-inline" method="post">
    <div class="form-group" style="margin-left:15px;position: relative;">
        <span>请选择日期：</span>
        <input name="startTime" class="form-control" type="text" id="startTimeID" required  errInfo="填写开始时间"
               value="${param.startTime}">
        —
        <input name="endTime" class="form-control" type="text" id="endTimeID" required errInfo="填写结束时间"
               value="${param.endTime}">
    </div>
    <button id="subBT" type="button" class="btn btn-primary">查询</button>
</form>
<div id="msgstatisticsContent">

</div>
<script language="javascript" type="text/javascript" src="assets/js/main/ajaxForm.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/followupDisplay/messagestatistics/MsgStatistics.js"></script>
