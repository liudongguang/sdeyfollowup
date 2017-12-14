<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<c:if test="${fn:length(ksfpqk) > 0}">
    <div  class="flush-tb">
        <div class="table-responsive">
            <table class="table table-striped table-hover table-bordered">
                <thead>
                <tr>
                    <th>科室名称</th>
                    <th>分配个数</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach items="${ksfpqk}" var="obj">
                    <tr id="${obj.managerid}">
                        <td>${obj.ksname}</td>
                        <td>${obj.workcount}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</c:if>
