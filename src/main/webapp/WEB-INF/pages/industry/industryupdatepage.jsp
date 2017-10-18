<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
	<input name="id" value="${industry.id}" type="text" hidden="hidden">

    <div class="form-group">
        <label class="col-sm-3 control-label">行业名称：</label><%--for="username"--%>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="industryname" name="industryname" value="${industry.industryname}"/>
            <div id="validation-username" class="validate-error help-block"></div>
        </div>
    </div>

</form>
<script type="text/javascript">
	submit = function(){
		//frmValidate();
		var data = $("#submitForm").serialize();
		ajaxRequest("admin/content/industryupdate", data);
	}
</script>