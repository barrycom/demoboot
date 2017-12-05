<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-3 control-label" for="remark"><font color="red">*</font>拒绝理由：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="remark" name="remark" placeholder="请填输入拒绝理由"/>
            <div id="validation-username" class="validate-error help-block"></div>
        </div>
        <input type="text" id="id" name="id"  value="${id}"  hidden="hidden"/>
    </div>
</form>
<script type="text/javascript">
	submit = function(){
		frmValidate();
		var data = $("#submitForm").serialize();
		ajaxRequest("admin/member/memberInfoRefuse", data);
	}
</script>