<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<div class="clearfix"></div>
<div class="col-md-5">
    <form class="form-horizontal" id="subForm" method="post" action="sdeyfollowup/saveBlackPhone">
        <div class="form-group">
            <label class="control-label">手机号</label>
            <input name="blackphone" type="text" required checkparam  class="form-control" placeholder="屏蔽的手机号" maxlength="11"/>
        </div>
        <div class="form-group text-center">
            <button class="btn btn-primary-outline waves-effect waves-light" id="subBT" type="submit" form="subform">保存</button>
        </div>
    </form>
</div>
<script language="javascript" type="text/javascript" src="assets/js/followupDisplay/phoneblacklist/add.js"></script>

