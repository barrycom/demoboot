<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>

<form id="submitForm" class="form-horizontal" enctype="multipart/form-data" method="post">
    <input name="id" value="${activityType.id}" type="text" hidden="hidden">
    <div class="form-group">
        <label class="col-sm-3 control-label no-padding-right" for="typename">文件名称</label>
        <div class="col-sm-8">
            <input type="text" id="typename" name="typename" value="${activityType.typename}" class="form-control" />
        </div>
    </div>

</form>

<script type="text/javascript">
    //上传文件


    submit = function(){

        var data = $("#submitForm").serialize();
        ajaxRequest("admin/activityType/updateActivityType", data);
    }
</script>