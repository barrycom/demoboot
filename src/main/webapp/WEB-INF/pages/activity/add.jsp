<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<form id="submitForm" class="form-horizontal">
    <div class="form-group">
        <label class="col-sm-3 control-label" for="activityname"><font color="red">*</font>活动名称：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" id="activityname" name="activityname" placeholder="请填写活动名称"/>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label" for="activityname"><font color="red">*</font>活动类型：</label>
        <div class="col-sm-8">
            <div class="btn-group">
                <select class="form-control" name="activitytype" <%--onchange="javascript:formSubmit();"--%>>
                    <option value="">==活动类型==</option>
                    <c:forEach items="${list}" var="types">
                        <option value="${types.id}">${types.typename}</option>
                    </c:forEach>
                </select>
            </div>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label" for="activitysdate"><font color="red">*</font>活动开始时间：</label>
        <div class="col-sm-8">

            <input size="16" type="text" id="activitysdate" name="activitysdate" readonly class="form_datetime" placeholder="请填写活动开始时间">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="activityedate"><font color="red">*</font>活动结束时间：</label>
        <div class="col-sm-8">

            <input size="16" type="text" id="activityedate" name="activityedate" readonly class="form_datetime" placeholder="请填写活动结束时间">
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
        <label class="col-sm-3 control-label" ><font color="red">*</font>经纬度：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="latitude" name="latitude"  placeholder="经度自动生成" />
        </div>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="longitude" name="longitude"  placeholder="纬度自动生成" />
        </div>
        <div id="validation-color" class="validate-error help-block"></div>
    </div>
    </div>.



    <div class="form-group">
        <label class="col-sm-3 control-label" for="activityimg">活动主图：</label>
        <div class="col-sm-8">
            <input type="text" id="activityimg" name="activityimg" class="form-control" readOnly style="margin-bottom: 5px;"/>
            <input id="uploadCover" type="file" class="file">
        </div>
    </div>

    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" for="content"><font color="red">*</font>活动内容：</label>
        <div class="col-sm-8">
            <script id="content" name="activityidcontent" type="text/plain"></script>
        </div>
    </div>

    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" for="content"><font color="red"></font>温馨提示：</label>
        <div class="col-sm-8">
            <textarea class="form-control" rows="3"  name=activityidmemo></textarea>

        </div>
    </div>

    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" for="content"><font color="red">*</font>活动单价：</label>
        <div class="col-sm-8">
            <input type="text" id="activityprice" name="activityprice" placeholder="输入金额">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"></label>
        <div class="col-sm-8" style="text-align: center;">

            <button class="btn btn-primary" type="submit"name="submit">
                <i class="ace-icon fa fa-reply bigger-110"></i>确定
            </button>

            <button class="btn btn-warning" type="reset" onclick="javascript:reback();">
                <i class="ace-icon fa fa-reply bigger-110"></i>返回
            </button>
        </div>
    </div>
</form>
<script type="text/javascript">
initUeditor();
$("#activitysdate").datetimepicker({
    format: 'yyyy-mm-dd hh:ii',
    language: 'zh-CN',
    autoclose:true,
    startDate:new Date()
}).on("click",function(){
    $("#activitysdate").datetimepicker("setEndDate",$("#datetimeEnd").val())
});
$("#activityedate").datetimepicker({
    format: 'yyyy-mm-dd hh:ii',

    language: 'zh-CN',
    autoclose:true,
    startDate:new Date()
}).on("click",function(){
    $("#activityedate").datetimepicker("setStartDate",$("#datetimeStart".val()))
});

uploadImg("uploadCover", "activityimg", "upload/uploadImg.do", "");
$("#activityprice").keyup(function () {
    var reg = $(this).val().match(/\d+\.?\d{0,2}/);
    var txt = '';
    if (reg != null) {
        txt = reg[0];
    }
    $(this).val(txt);
}).change(function () {
    $(this).keypress();
    var v = $(this).val();
    if (/\.$/.test(v))
    {
        $(this).val(v.substr(0, v.length - 1));
    }
});

    $('#submitForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            activityname: {
                message: '活动名称不能为空',
                validators: {
                    notEmpty: {
                        message: '活动名称不能为空'
                    }
                }
            },
            activitysdate: {
                message: '活动起始日期不能为空',
                validators: {
                    notEmpty: {
                        message: '活动起始日期不能为空'
                    }
                }
            },
            activityedate: {
                message: '活动结束日期不能为空',
                validators: {
                    notEmpty: {
                        message: '活动起始日期不能为空'
                    }
                }
            },

            activityaddr: {
                message: '活动地址不能为空',
                validators: {
                    notEmpty: {
                        message: '活动地址不能为空'
                    }
                }
            },
           /* activityimg: {
                    message: '活动主图必须上传',
                    validators: {
                        notEmpty: {
                            message: '活动主图必须上传'
                        }
                    }
            },*/
            /*activityidcontent: {
                message: '活动内容必须填写',
                validators: {
                    notEmpty: {
                        message: '活动内容必须填写'
                    }
                }
            },*/
            activityprice: {
                message: '活动价格必须填写',
                validators: {
                    notEmpty: {
                        message: '活动价格必须填写'
                    }
                }
            }






        },
        submitHandler: function(validator, form, submitButton) {

        }
    }).on("success.form.bv",function(e){

        var data = $("#submitForm").serialize();
        alert(data)
        $.ajax({
            url: _urlPath + "admin/activity/add",
            dataType: "json",
            type: "post",
            data: data,
            success: function (req){
               if (req.retcode == 1) {
                    goPage("admin/activity/mainPage")
                } else {
                   alert(req.retmsg);
                }

            },
            error: function(req){
                $("#errDiv").show();
                $("#err").html(req.statusText);
            }
        });


    });

function reback() {
    goPage('admin/activity/mainPage');

}
</script>