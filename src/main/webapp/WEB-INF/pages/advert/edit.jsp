<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<form id="submitForm" class="form-horizontal">
    <input id="id" type="hidden" name="id" value="${advert.id}">
    <div class="form-group">
        <label class="col-sm-3 control-label" for="adUrl">广告位主图：</label>
        <div class="col-sm-8">
            <input type="text" id="adUrl" name="adUrl" class="form-control" value="${advert.adUrl}"  readOnly style="margin-bottom: 5px;"/>
            <input id="uploadCover" type="file" class="file">
        </div>
    </div>
    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" for="adtype"><font color="red">*</font>广告位类型：</label>
        <div class="col-sm-8">
            <select class="form-control" id="adType" name="adType">
                <option value="0" selected>首页广告位</option>
            </select>
        </div>
    </div>
    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" for="adIndex"><font color="red">*</font>广告位链接：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text"  id="adHref" name="adHref" value="${advert.adHref}" placeholder="广告位链接">
        </div>
    </div>
    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" for="adIndex"><font color="red">*</font>广告位排序：</label>
        <div class="col-sm-8">
            <input class="form-control" type="number"  id="adIndex" name="adIndex" value="${advert.adIndex}" placeholder="排序">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"></label>
        <div class="col-sm-8" style="text-align: center;">
            <button class="btn btn-primary" type="submit"name="submit">
                <i class="ace-icon fa fa-reply bigger-110"></i>保存
            </button>
            <button class="btn btn-warning" type="reset" onclick="javascript:reback();">
                <i class="ace-icon fa fa-reply bigger-110"></i>返回
            </button>
        </div>
    </div>
</form>
<script type="text/javascript">

uploadImg("uploadCover", "adUrl", "upload/uploadImg.do","${advert.adUrl}");

    $('#submitForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {
            adUrl: {
                message: '广告位主图不能为空',
                validators: {
                    notEmpty: {
                        message: '广告位主图不能为空'
                    }
                }
            },adHref: {
                message: '广告位链接不能为空',
                validators: {
                    notEmpty: {
                        message: '广告位链接不能为空'
                    }
                }
            },
            adIndex: {
                message: '广告位排序不能为空',
                validators: {
                    notEmpty: {
                        message: '广告位排序不能为空'
                    }
                }
            }
        },
        submitHandler: function(validator, form, submitButton) {

        }
    }).on("success.form.bv",function(e){
        var data = $("#submitForm").serialize();
        $.ajax({
            url: _urlPath + "admin/activity/editad",
            dataType: "json",
            type: "post",
            data: data,
            success: function (req){
               if (req.retcode == 1) {
                   goPage("admin/activity/mainadPage")
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
      goPage("admin/activity/mainadPage")
    }
</script>