<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<form id="submitForm" class="form-horizontal">
    <div class="panel panel-default">

        <div class="panel-heading">
            用户信息
        </div>
        <div class="panel-body">


    <div class="form-group">
        <input name="id" value="${activityOrder.id}" type="text" hidden="hidden">
        <input name="cz" id="cz" value="${cz}" type="text" hidden="hidden">
        <label class="col-sm-3 control-label" for="activityname"><font color="red">*</font>昵称：</label>
        <div class="col-sm-8">
            <input size="16" class="form-control" type="text" id="nickname" name="nickname" value="${activityOrder.nickname}" placeholder="请填写活动名称"/>

        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label" for="realname"><font color="red">*</font>真实姓名：</label>
        <div class="col-sm-8">

            <input size="16" type="text"  class="form-control" id="realname" name="realname"  value="${activityOrder.realname}"  placeholder="请填写真实姓名">
        </div>
    </div>

    <div class="form-group">
        <label class="col-sm-3 control-label" for="realname"><font color="red">*</font>手机号：</label>
        <div class="col-sm-8">

            <input size="16" type="text"  class="form-control" id="mobile" name="mobile"  value="${activityOrder.mobile}"  placeholder="手机号">
        </div>
    </div>
    </div>
    </div>

    <div class="panel panel-default">

        <div class="panel-heading">
           活动信息
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label class="col-sm-3 control-label" for="activityname"><font color="red">*</font>活动名称：</label>
                <div class="col-sm-8">

                    <input size="16" type="text"  class="form-control" id="activityname" name="activityname"  disabled value="${activityOrder.activity.activityname}"  placeholder="活动名称">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label" for="activitysdate"><font color="red">*</font>活动时间：</label>
                <div class="col-sm-8">

                    <input size="16" type="text"  class="form-control" id="activitysdate" name="activitysdate" disabled  value="${activityOrder.activity.activitysdate}----${activityOrder.activity.activityedate}"  placeholder="活动时间">
                </div>
            </div>


            <div class="form-group">
                <label class="col-sm-3 control-label" for="activityaddr"><font color="red">*</font>活动地点：</label>
                <div class="col-sm-8">

                    <input size="16" type="text"  class="form-control" id="activityaddr" name="activityaddr"  disabled value="${activityOrder.activity.activityaddr}"  placeholder="活动地点">
                </div>
            </div>

        </div>


    </div>

    <div class="panel panel-default">

        <div class="panel-heading">
            支付信息
        </div>
        <div class="panel-body">
            <div class="form-group">
                <label class="col-sm-3 control-label" for="activityname"><font color="red">*</font>订单金额：</label>
                <div class="col-sm-8">

                    <input size="16" type="text"  class="form-control" id="ordermoney" name="ordermoney"   value="${activityOrder.ordermoney}"  placeholder="订单金额">
                </div>
            </div>

            <div class="form-group">
                <label class="col-sm-3 control-label" for="activitysdate"><font color="red">*</font>支付方式</label>
                <div class="col-sm-8">

                    <input size="16" type="text"  class="form-control"  disabled id="paymemo" name="paymemo"  value="${activityOrder.paymemo}"  placeholder="支付方式">
                </div>
            </div>



        </div>

        <c:if test="${activityOrder.status =='0'}">
        <div class="panel panel-default">

            <div class="panel-heading">
             赠送订单
            </div>
            <div class="panel-body">

                <div class="form-group">
                    <label class="col-sm-3 control-label" for="activitysdate"><font color="red">*</font>支付方式</label>
                    <div class="col-sm-8">

                        <select class="form-control span2" name="iszs">
                            <option value="">不增送</option>
                            <option value="微信">微信</option>
                            <option value="支付宝">支付宝</option>
                            <option value="线下">线下</option>
                            <option value="赠送">赠送</option>

                        </select>
                    </div>
                </div>




            </div>



    </div>
        </c:if>
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

/*
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
});*/

    $('#submitForm').bootstrapValidator({
        message: 'This value is not valid',
        feedbackIcons: {
            valid: 'glyphicon glyphicon-ok',
            invalid: 'glyphicon glyphicon-remove',
            validating: 'glyphicon glyphicon-refresh'
        },
        fields: {

        },
        submitHandler: function(validator, form, submitButton) {

        }
    }).on("success.form.bv",function(e){
/*alert($("activityimg").val());*/
        var data = $("#submitForm").serialize();
       // alert(data)
        $.ajax({
            url: _urlPath + "admin/activityOrder/edit",
            dataType: "json",
            type: "post",
            data: data,
            success: function (req){
               if (req.retcode == 1) {


                       goPage("admin/activityOrder/mainPage")


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


            goPage("admin/activityOrder/mainPage")


    }


    function showview() {

        $("#viewlogo").attr("src",$("#activityimg").val())
        $(".activity-name").html($("#activityname").val())
        $("#pricetext").html("¥"+$("#activityprice").val())
        $("#hddate").html($("#activitysdate").val()+"-"+$("#activityedate").val())
        $("#hdaddress").html($("#activityaddr").val())
        $("#hdxq").html(UE.getEditor('content').getContent());
        $("#wxts").html($("#activityidmemo").html())
        $('#myModal').modal({
            keyboard: true
        })

        $("#base").show();
    }
</script>