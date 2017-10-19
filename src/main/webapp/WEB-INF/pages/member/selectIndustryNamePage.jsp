<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-3 control-label" for="industryId"><font color="red">*</font>行业：</label>
		<div class="col-sm-8">
			<select class="form-control span2" id="industryId" name="industryId">
				<c:forEach items="${list}" var="industry">
					<option value="${industry.id}">${industry.industryname}</option>
				</c:forEach>
			</select>
		</div>
	</div>
</form>
<script type="text/javascript">
	submit = function(){
        var id = $("#industryId ").val();
        var name = $("#industryId").find("option:selected").text();
        alert(id+" "+name);
	    frmValidate();
	}
</script>