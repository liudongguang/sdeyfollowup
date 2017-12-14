<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${fn:length(managerWork) > 0}">
    <div  class="flush-tb">
        <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>姓名</th>
                    <th>用户名</th>
                    <th>个数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${managerWork}" var="obj">
                    <tr id="trid_${obj.uid}_${obj.name}">
                        <td>${obj.name}</td>
                        <td>${obj.username}</td>
                        <td>${obj.workcount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
<script language="javascript" type="text/javascript" src="assets/js/followupManager/work/fenpeidetail.js"></script>