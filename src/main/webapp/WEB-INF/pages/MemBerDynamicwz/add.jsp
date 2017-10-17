<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<form id="submitForm" class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-3 control-label" for="rolename"><font color="red">*</font>活动名称：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="rolename" name="rolename" placeholder="请填写角色名称"/>
            <div id="validation-rolename" class="validate-error help-block"></div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label" for="datetimeStart"><font color="red">*</font>活动开始时间：</label>
        <div class="col-sm-8">

            <input size="16" type="text" id="datetimeStart" readonly class="form_datetime">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="datetimeEnd"><font color="red">*</font>活动结束时间：</label>
        <div class="col-sm-8">

            <input size="16" type="text" id="datetimeEnd" readonly class="form_datetime">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="activityaddr"><font color="red">*</font>活动地点：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" onclick="javascript:showModal('地图','admin/activity/selectMap',1200,700);" id="activityaddr" name="activityaddr" placeholder="请选择活动地点"/>
            <div id="validation-activityaddr" class="validate-error help-block"></div>
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label" for="color"><font color="red">*</font>经纬度：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="latitude" name="color" value="" placeholder="经度自动生成" disabled/>
        </div>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="longitude" name="color" value="" placeholder="纬度自动生成" disabled/>
        </div>
        <div id="validation-color" class="validate-error help-block"></div>
    </div>
    </div>
</form>
<script type="text/javascript">
/*    submit = function(){
        frmValidate();
        var data = $("#submitForm").serialize();
        ajaxRequest("admin/role/add", data);
    }*/
$("#datetimeStart").datetimepicker({
    format: 'yyyy-mm-dd hh:ii',

    language: 'zh-CN',
    autoclose:true,
    startDate:new Date()
}).on("click",function(){
    $("#datetimeStart").datetimepicker("setEndDate",$("#datetimeEnd").val())
});
$("#datetimeEnd").datetimepicker({
    format: 'yyyy-mm-dd hh:ii',

    language: 'zh-CN',
    autoclose:true,
    startDate:new Date()
}).on("click",function(){
    $("#datetimeEnd").datetimepicker("setStartDate",$("#datetimeStart".val()))
});
</script>