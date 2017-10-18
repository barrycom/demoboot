<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-3 control-label">标签名称：</label><%--for="username"--%>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="tname" name="tname" />
            <div id="validation-username" class="validate-error help-block"></div>
        </div>
    </div>

</form>
<script type="text/javascript">
	submit = function(){
		//frmValidate();
		var data = $("#submitForm").serialize();
		ajaxRequest("admin/content/instrtag", data);
	}
</script>