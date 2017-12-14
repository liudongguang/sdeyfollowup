<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<div class="table-responsive">
    <table id="initTable" class="table table-striped table-hover table-bordered">
        <thead>
        <tr>
            <th>回复内容</th>
            <th>回复时间</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${objlist}" var="pageobj">
            <tr>
                <td>
                        ${pageobj.content}
                </td>
                <td>
                        ${pageobj.time}
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>