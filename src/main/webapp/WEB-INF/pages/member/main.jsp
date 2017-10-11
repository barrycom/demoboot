<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-3" style="text-align: left;padding-bottom: unset">
            <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:goPage('admin/member/addPage');"><span class="btn-label icon fa fa-plus"></span>添加活动
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
                {field:"id",text:"用户ID", sortColumn:"id"},
                {field:"name", text:"用户名称"},
                {field:"mobile", text:"手机号"},
                {field:"wxno", text:"微信号"},
                {field:"state", text:"状态"},
                {field:"ishy", text:"是否购买"},
                {field:"regtime", text:"注册时间"},

            ],
            cls: "",
            url: _urlPath + "admin/member/queryPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });

 /*   function  goadd() {
        location.href=_urlPath+"admin/activity/addPage"
    }*/
</script>