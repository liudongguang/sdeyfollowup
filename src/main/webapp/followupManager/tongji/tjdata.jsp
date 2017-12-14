<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div>
    发送条数：${disinfo.statistMsg.sendCount}
    回复条数：${disinfo.statistMsg.receiveCount}
    好评数：${disinfo.statistMsg.goodReceiveCount}
    好评率：${disinfo.statistMsg.haopinglvStr}
</div>
<table class="table table-striped table-hover table-bordered">
    <thead>
    <tr>
        <th>科室</th>
        <th>出院人数</th>
        <th>未登记</th>
        <th>登记率</th>
        <th>回访人数</th>
        <th>未联系上</th>
        <th>回访率</th>
        <th>满意</th>
        <th>满意率</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${disinfo.rsList}" var="obj">
        <tr id="trID_${obj.ksName}">
            <td>${obj.ksName}</td>
            <td>${obj.cyCount}</td>
            <td>${obj.wdjCount}</td>
            <td>${obj.djlv}</td>
            <td>${obj.hfCount}</td>
            <td>${obj.hfwlxsCount}</td>
            <td>${obj.hflv}</td>
            <td>${obj.manyiCount}</td>
            <td>${obj.mylv}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<script language="javascript" type="text/javascript" src="assets/js/followupManager/tongji/tjdata.js"></script>