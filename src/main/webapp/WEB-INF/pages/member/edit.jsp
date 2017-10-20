<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${ctx}/assets/css/view.css" type="text/css" rel="stylesheet"/>
<script type="text/javascript" src="${ctx}/assets/js/util/js/jquery.cityselect.js"></script>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<form id="submitForm" class="form-horizontal">
    <input name="id" value="${member.id}" type="text" hidden="hidden">
    <input name="type" id="type" value="${member.type}" type="text" hidden="hidden">
    <input name="ishy" id="ishy" value="${member.ishy}" type="text" hidden="hidden">
    <input name="state" id="state" value="${member.state}" type="text" hidden="hidden">
    <div id="memberInfo">
    <div class="form-group">
        <label class="col-sm-3 control-label"><font>用户资料</font></label>
    </div>
    <div class="form-group">
        <label class="col-sm-1 control-label" for="name"><font color="red">*</font>用户昵称：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="name" name="name" value="${member.name}" placeholder="请填写用户昵称"/>

        </div>
    </div>

    <div class="form-group">
        <input name="trade" id="trade" value="${member.trade}" type="text" hidden="hidden">
        <label class="col-sm-1 control-label" for="tradename"><font color="red">*</font>行业：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="tradename" name="tradename" value="${member.tradename}" onclick="showTrade();" placeholder="请选择行业"/>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label" for="corporatename"><font color="red">*</font>公司名称：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="corporatename" name="corporatename" value="${member.corporatename}" placeholder="请填写活动名称"/>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label" for="profession"><font color="red">*</font>职位：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="profession" name="profession" value="${member.profession}" placeholder="请填职位"/>

        </div>
    </div>

    <div class="form-group">
        <input name="region" id="region" value="${member.region}" type="text" hidden="hidden">
        <label class="col-sm-1 control-label" for="regionname"><font color="red">*</font>城市：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="regionname" name="regionname" value="${member.regionname}"  placeholder="城市" onclick="showCity()"/>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label"><font>用户联系方式</font></label>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label" for="wxno"><font color="red">*</font>微信号：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="wxno" name="wxno" value="${member.wxno}" placeholder="微信号"/>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label" for="mobile"><font color="red">*</font>手机号：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="mobile" name="mobile" value="${member.mobile}"  placeholder="手机号"/>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-1 control-label" for="email"><font color="red">*</font>Email：</label>
        <div class="col-sm-4">
            <input class="form-control" type="text" id="email" name="email" value="${member.email}" placeholder="Email"/>

        </div>
    </div>
    </div>
    <div id = "vipAllottedTime" style="display: none">
        <div class="form-group">
            <label class="col-sm-3 control-label"><font>会员使用期限</font></label>
        </div>
       <div class="form-group">
            <label class="col-sm-1 control-label" for="viptimestart"><font color="red">*</font>会员使用期限开始时间：</label>
            <div class="col-sm-4">
                <input size="16" type="text" id="viptimestart" name="viptimestart"  value="${member.viptimestart}" readonly class="form_datetime" placeholder="请填写活动开始时间">
            </div>
        </div>
        <div class="form-group">
            <label class="col-sm-1 control-label" for="viptimeend"><font color="red">*</font>会员使用期限结束时间：</label>
            <div class="col-sm-4">
                <input size="16" type="text" id="viptimeend" name="viptimeend" readonly class="form_datetime" value="${member.viptimeend}" placeholder="请填写活动结束时间">
            </div>
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-2 control-label"></label>
        <div class="col-sm-2" style="text-align: center;">

            <button   id="tijiao" class="btn btn-primary" type="submit"name="submit" >
                <i class="ace-icon fa fa-reply bigger-110"></i>确定
            </button>

            <button class="btn btn-warning" type="reset" onclick="javascript:reback();">
                <i class="ace-icon fa fa-reply bigger-110"></i>返回
            </button>
        </div>
    </div>

</form>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">
                </button>
                <h4 class="modal-title" id="myModalLabel">
                   选择行业
                </h4>
            </div>
            <div class="form-group">
                <label class="col-sm-3 control-label" for="industryId"><font color="red">*</font>行业：</label>
                <div class="col-sm-8">
                    <select class="form-control span2" id="industryId" name="industryId">
                        <c:forEach items="${trade}" var="industry">
                            <option value="${industry.id}">${industry.industryname}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="modal-body">
                按下 ESC 按钮退出。
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal" onclick="closeTrade()">确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->


<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel2">
                    选择地区
                </h4>
            </div>
            <div class="form-group">
                <div id="city">
                    <select class="prov"></select>
                    <select class="city" disabled="disabled"></select>
                    <%--<select class="dist" disabled="disabled" style="display:inline;"></select>--%>
                </div>
                <%--<label class="col-sm-3 control-label" for="industryId"><font color="red">*</font>地区：</label>
                <div class="col-sm-8">
                    <select class="form-control span2" id="industryId" name="industryId">
                        <c:forEach items="${trade}" var="industry">
                            <option value="${industry.id}">${industry.industryname}</option>
                        </c:forEach>
                    </select>
                </div>--%>
            </div>
            <div class="modal-body">
                按下 ESC 按钮退出。
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal" onclick="closeCityIdName()">确定
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    window.onload(on());

    function on() {
       var type = $("#type").val();
       var ishy=$("#ishy").val();
       var state=$("#state").val();

        if(type == 3){ // 查看 不可编辑
            if(ishy == 1 && state ==1){   //编辑
                $("#vipAllottedTime").css("display","block");
            }
       }else if(type == 2){   //给购买时间  不可编辑信息
           $("#memberInfo input").attr("disabled","disabled");
            if(state ==1){
                $("#vipAllottedTime").css("display","block");
            }else{
                $("#tijiao").remove();
            }
       }else {
            $("form input").attr("disabled","disabled");
            $("#tijiao").remove();
            if(ishy == 1 && state ==1){
                $("#vipAllottedTime").css("display","block");  //购买和通过审核 才能给时间
            }
       }
    }

    $("#viptimestart").datetimepicker({
        format: 'yyyy-mm-dd',
        language: 'zh-CN',
        autoclose:true,
        startDate:new Date()
    }).on("click",function(){
        $("#viptimestart").datetimepicker("setEndDate",$("#datetimeEnd").val())
    });
    $("#viptimeend").datetimepicker({
        format: 'yyyy-mm-dd',

        language: 'zh-CN',
        autoclose:true,
        startDate:new Date()
    }).on("click",function(){
        $("#viptimeend").datetimepicker("setStartDate",$("#datetimeStart".val()))
    });

    $('#submitForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        submitHandler: function(validator, form, submitButton) {

        }
    }).on("success.form.bv",function(e){

        var data = $("#submitForm").serialize();
        debugger
        $.ajax({
            url: _urlPath + "admin/member/edit",
            dataType: "json",
            type: "post",
            data: data,
            success: function (req){
                debugger;
               if (req.retcode == 1) {
                   alert("保存成功");
                   reback();
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
    function showTrade() {
        $('#myModal').modal({
            keyboard: true
        });
    }
    function closeTrade() {
        var id = $("#industryId ").val();
        var name = $("#industryId").find("option:selected").text();
        $("#trade").val(id);
        $("#tradename").val(name);
    }
    function showCity() {
        $('#myModal2').modal({
            keyboard: true
        });
    }
    function closeCityIdName() {
        var name2 = $("#prov").find("option:selected").text();
        var id = $("#city ").val();
        var name = $("#city").find("option:selected").text();
        $("#regionname").val(name);

        $.ajax({
            url: _urlPath + "admin/member/getCity",
            data: {"name":name},
            type: "post",
            dataType:"json",
            success: function (req){
                $("#region").val(req.data.id);

            },
            error: function(req){
                $("#errDiv").show();
                $("#err").html(req.statusText);
            }
        });
    }

    $(function(){
        $("#city").citySelect({
            nodata:"none",
            required:false
        });
    });

    function reback() {
        debugger;
        var type = $("#type").val();
        if(type == 4){
            goPage("admin/member/mainMemberInfoPage")
        }else if(type == 5){
            goPage("admin/member/vipMainPage")
        }else if(type == 6){
            goPage("admin/member/blockMainPage")
        }else{
            goPage("admin/member/mainPage")
        }

    }
</script>
