<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${ctx}/assets/css/view.css" type="text/css" rel="stylesheet"/>

<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <input name="status" id="status" type="text" value="0" hidden="hidden">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="nickname" placeholder="用户名">
        </div>
        <div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
            <input type="text" class="form-control search-query" name="mobile" placeholder="手机号">
        </div>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>

        <div class="col-md-8" style="text-align: right;padding-bottom: unset">
            <button id="staring" class="btn btn-labeled btn-primary" onclick="gorelod('1')"><span class="btn-label icon fa fa-thumbs-o-up"></span>已支付
            </button>
            <button id="ending" class="btn btn-labeled btn-primary" onclick="gorelod('0')"><span class="btn-label icon fa fa-thumbs-o-down"></span>未支付
            </button>
        </div>
    </div>
</div>
<div class="openAppGrid">
    <div id="openAppGrid"></div>
</div>
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
              /*  {field:"activityid",text:"活动编号", sortColumn:"activityid",width:0},*/
                {field:"nickname",text:"昵称", sortColumn:"nickname",width:80},
                {field:"realname", text:"真实姓名"},
                {field:"mobile", text:"手机号"},
                {field:"activity.activityname", text:"活动名称"},
                {field:"activity.activitysdate", text:"活动时间"},
                {field:"activity.activityaddr", text:"活动地点"},
                {field:"ordermoney", text:"订单金额"},
                {field:"paymemo", text:"支付方式"},
                {field:"status", text:"支付状态",formatter:function(index, content, data){
                    if(content==0)
                    {
                        return "未支付";
                    }
                    else
                    {
                        return "已支付";
                    }
             }},
                {field:"id", text:"操作", style:"text-align:center", width: 80, formatter:function(index, content, data){
                    var datzx=data;
                    var editUrl = "admin/activityOrder/updateActivityOrderPage?id=" + content;
                    return "<a href='javascript:goPage(\""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>编辑</i></a>"
                        +"&nbsp;<a href='javascript:tx("+JSON.stringify(data)+");' class='btn btn-xs btn-success add-tooltip'><i class='fa  fa-eye'>提醒</i></a>"

                }}

              /*  {field:"activitysdate", text:"手机号"},
                {field:"activityedate", text:"活动名称"},
                {field:"activityaddr", text:"活动时间"},
                {field:"activityaddr", text:"活动地点"},
                {field:"activityaddr", text:"订单金额"},
                {field:"activityaddr", text:"支付状态"},
                {field:"activityaddr", text:"支付方式"}*!/*/

            ],
            cls: "",
            url: _urlPath + "admin/activityOrder/queryPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });

    function gorelod(obj) {

        $("#status").val(obj);

        $("#openAppGrid").sgrid("reload");


    }
 /*   function  goadd() {
        location.href=_urlPath+"admin/activity/addPage"
    }*/


</script>