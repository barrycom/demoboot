<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${ctx}/assets/css/view.css" type="text/css" rel="stylesheet"/>
<div class="modal fade" id="myModal" tabindex="-1" align="center" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="phone">
        <div class="phone-content">
            <div class='activity-bg'>
                <div class='activity-content-bg'>

                    <image id="viewlogo" src="http://p2.wmpic.me/article/2017/09/15/1505443129_lZfdpYvH_215x185.jpg" class="slide-image" />

                    </swiper>

                    <!--活动名称价格。。  -->
                    <div class='details-head'>
                        <div class='activity-name'>

                        </div>
                        <div class='activity-price'>
                            <text id="pricetext">¥950</text> /人
                        </div>
                        <div class='activity-addresss'>
                            <image src='${ctx}/assets/images/activity/act_det_icon_time.png'  width="13" height="13"></image><span id="hddate">10月1日-10月8日</span></div>
                        <div class='activity-addresss'>
                            <image src='${ctx}/assets/images/activity/act_det_icon_add.png'  width="15" height="17"></image> <span id="hdaddress">武汉江岸区武汉剧院</span>
                            <image src='${ctx}/assets/images/activity/list_btn_path.png' class='arrow-right'  width="13" height="13"></image>
                        </div>
                    </div>

                    <!--感兴趣的人  -->
                    <div class='watch-people-bg'>
                        新增此处无数据
                        <%--      <text class='watch-text'>他们也感兴趣</text>
                              <div>
                                  <image src='http://p2.wmpic.me/article/2017/09/15/1505443129_lZfdpYvH_215x185.jpg' class='watch-head'></image>
                                  <image src='http://p3.wmpic.me/article/2017/09/19/1505800793_ifRRQepR_215x185.jpg' class='watch-head'></image>
                                  <image src='http://p2.wmpic.me/article/2017/09/15/1505443129_lZfdpYvH_215x185.jpg' class='watch-head'></image>
                                  <image src='http://p3.wmpic.me/article/2017/09/19/1505800793_ifRRQepR_215x185.jpg' class='watch-head'></image>
                                  <text class='watch-head'>216</text>
                              </div>--%>
                    </div>

                    <!--活动详情  -->
                    <div class='activity-info-bg'>
                        <div class='activity-info-title'>活动详情</div>
                        <div id="hdxq">sdasds</div>
                    </div>

                    <!--温馨提示  -->
                    <div class='activity-info-bg'>

                        <div class='activity-info-title'>
                            温馨提示
                        </div>
                        <div class='activity-addresss' id="wxts">
                            1.2米以上儿童需持票入场。每张成人票可协同1名1.2米以下儿童入场。</div>

                    </div>


                </div>
                <!--我要参加  -->




            </div>


            <!--弹出  -->
            <div class='model-bg' hidden='{{model}}' >
                <div class='model-content'>
                    <div class='model-title'>选择数量
                        <div class='model-close' bindtap="modelHide">X</div>
                    </div>
                    <div class='num-bg'>
                        <div class='add'>-</div><div class='num' >2</div><div class='add add-light'>+</div>

                    </div>
                </div>

            </div>
        </div>

    </div>

</div>
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
 <%--   <div class="form-group">
        <label class="col-sm-3 control-label" for="activityedate"><font color="red">*</font>活动结束时间：</label>
        <div class="col-sm-8">

            <input size="16" type="text" id="activityedate" name="activityedate" readonly class="form_datetime" placeholder="请填写活动结束时间">
        </div>
    </div>--%>
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
            <textarea class="form-control" rows="3" id="activityidmemo" name="activityidmemo"></textarea>
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
                <i class="ace-icon fa fa-reply bigger-110"></i>保存
            </button>
            <button class="btn btn-success" type="button"   onclick="showview()">
                <i class="ace-icon fa fa-reply bigger-110"></i>预览
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
/*$("#activityedate").datetimepicker({
    format: 'yyyy-mm-dd hh:ii',

    language: 'zh-CN',
    autoclose:true,
    startDate:new Date()
}).on("click",function(){
    $("#activityedate").datetimepicker("setStartDate",$("#datetimeStart".val()))
});*/

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
            activityidmemo: {
                message: '温馨提示',
                validators: {
                    notEmpty: {
                        message: '温馨提示'
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
        debugger

        var data = $("#submitForm").serialize();
        $("#activityidmemo").val();
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
function showview() {

    $("#viewlogo").attr("src",$("#activityimg").val())
    $(".activity-name").html($("#activityname").val())
    $("#pricetext").html("¥"+$("#activityprice").val())
    $("#hddate").html($("#activitysdate").val()/*+"-"+$("#activityedate").val()*/)
    $("#hdaddress").html($("#activityaddr").val())
    $("#hdxq").html(UE.getEditor('content').getContent());
    $("#wxts").html($("#activityidmemo").html())
    $('#myModal').modal({
        keyboard: true
    })

    $("#base").show();
}
</script>