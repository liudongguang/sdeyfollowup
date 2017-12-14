<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="row margin-bottom-30">
    <div class="col-md-12">
        <input type="hidden" id="hiddenval_yifenpeiDate" value="${param.lastDate}"/>
        <form class="form-inline">
            <label>请选择日期：</label>
            <select class="form-control" name="yifenpeiDate" id="selectFenPeiDetail">
                <option value="">-----
                    <c:if test="${fn:length(yiFenPeiList)==0}">无可统计日期</c:if>
                    <c:if test="${fn:length(yiFenPeiList)>0}">请选择</c:if>
                    -----
                </option>
                <c:forEach items="${yiFenPeiList}" var="yfpobj">
                    <option value="${yfpobj.importdatestr}">${yfpobj.importdatestr}</option>
                </c:forEach>
            </select>
            <button type="button" class="btn btn-primary" id="tongjiHandlerBT">统计</button>
            <button type="button" class="btn btn-success" id="exportHandlerBT">导出</button>
        </form>
    </div>
</div>

<div class="table-responsive" id="dataContentID">
</div>
<script language="javascript" type="text/javascript" src="assets/js/followupManager/tongji/index.js"></script>
