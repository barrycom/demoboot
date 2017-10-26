<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${ctx}/assets/css/view.css" type="text/css" rel="stylesheet"/>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <input name="status" id="status" type="text" value="all" hidden="hidden">
        <div class="col-md-3" style="text-align: left;padding-bottom: unset">
            <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:showModal('添加类型', 'admin/activityType/addPage');"><span class="btn-label icon fa fa-plus"></span>添加活动类型
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
                {field:"typename", text:"活动类型"},
                {field:"id", text:"操作", style:"text-align:center", width: 80, formatter:function(index, content, data){
                    var datzx=data;
                    var editUrl = "admin/activityType/editPage?id=" + content;
                    return "<a href='javascript:showModal(\"修改类型\", \""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>编辑</i></a>"

                }}
            ],
            cls: "",
            url: _urlPath + "admin/activityType/queryPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });

    function gorelod(obj) {


        $("#openAppGrid").sgrid("reload");


    }
 /*   function  goadd() {
        location.href=_urlPath+"admin/activity/addPage"
    }*/


</script>