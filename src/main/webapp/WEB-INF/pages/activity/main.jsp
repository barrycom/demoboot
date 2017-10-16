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
                {field:"activityid",text:"活动编号", sortColumn:"activityid",width:0},
                {field:"activitytypename",text:"活动类型", sortColumn:"activitytypename",width:80},

                {field:"activityimg",text:"主图",style:"text-align:center",width: 200,formatter:function(index, content, data){
                    return "<img src='" + content + "' height='20px'/>";
                }},
                {field:"activityname", text:"活动名称"},
                {field:"activitysdate", text:"活动开始时间"},
                {field:"activityedate", text:"活动结束时间"},
                {field:"activityaddr", text:"活动地址"},
                {field:"id", text:"操作", style:"text-align:center", width: 80, formatter:function(index, content, data){
                    var editUrl = "admin/activity/updateActivitPage?cz=all&id=" + content;
                    var delUrl = "admin/activity/delActivit/" + content;
                    return "<a href='javascript:goPage(\""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>修改</i></a>"
                        + "&nbsp;<a href='javascript:showCfm(\"确定删除该记录\", \""+delUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>删除</i></a>";
                }}
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