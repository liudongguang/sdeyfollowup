<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div>
    发送条数：${page.total}
    回复条数：${stinfo.receiveCount}
    好评数：${stinfo.goodReceiveCount}
    好评率：${stinfo.haopinglvStr}
</div>
<div class="table-responsive">
    <table class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>病人姓名</th>
            <th>病人手机号</th>
            <th>发送时间</th>
            <th>发送内容</th>
            <th>发送状态</th>
            <th>返回信息</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${page.list}" var="obj">
            <tr>
                <td>${obj.brxm}</td>
                <td>${obj.pationtphone}</td>
                <td> <fmt:formatDate value="${obj.sendtime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate></td>
                <td>${obj.messagecontent}</td>
                <td>${obj.sendrsmessage}</td>
                <td>${obj.recervemsg}</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
<input type="hidden" id="manageridID" value="${sessionScope.user.uid}"/>
<!--
分页开始
-->
<div id="pagesDIV" style="margin: 0 auto"></div>
<input id="pageNum" type="hidden" value="${page.pageNum}"/>
<input id="pageSize" type="hidden" value="${page.pageSize}"/>
<input id="pages" type="hidden" value="${page.pages}"/>
<input id="total" type="hidden" value="${page.total}"/>
<input id="loadDataURL" type="hidden" value="/sdeyfollowup/statisticsMessage"/>
<input id="searFormID" type="hidden" value="subForm"/>
<input id="contentVALID" type="hidden" value="msgstatisticsContent"/>
<script language="javascript" type="text/javascript" src="assets/js/jPage-1.2.js"></script>
<script language="javascript" type="text/javascript" src="assets/js/jPageExt.js"></script>
