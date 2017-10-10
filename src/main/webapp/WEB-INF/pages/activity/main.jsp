<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-3" style="text-align: left;padding-bottom: unset">
            <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:goPage('admin/activity/addPage');"><span class="btn-label icon fa fa-plus"></span>添加活动
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
                {field:"activityid",text:"活动编号", sortColumn:"activityid"},
                {field:"activityimg", text:"主图"},
                {field:"activityname", text:"活动名称"},
                {field:"activitysdate", text:"活动开始时间"},
                {field:"activityedate", text:"活动结束时间"},
                {field:"activityaddr", text:"活动地址"}

            ],
            cls: "",
            url: _urlPath + "admin/activity/queryPage",
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