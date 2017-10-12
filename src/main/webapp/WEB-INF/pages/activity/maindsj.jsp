<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">

    <div id="searchForm">
        <input name="daytime" id="daytime" type="text" value="one" hidden="hidden">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="activityname" placeholder="活动名称">
        </div>
  <%--      <div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
            <select class="form-control" name="type" onchange="javascript:formSubmit();">
                <option value="">==操作类型==</option>
                <option value="0">操作日志</option>
                <option value="1">异常日志</option>
            </select>
        </div>--%>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>
    </div>
</div>
<ul id="myTab" class="nav nav-tabs">
    <li class="active">
        <a href="#" data-toggle="tab" onclick="gorelod('one')">
            最近一个月
        </a>
    </li>
    <li><a href="#" data-toggle="tab" onclick="gorelod('two')">最近二个月</a></li>
    <li><a href="#" data-toggle="tab" onclick="gorelod('more')">更早以前</a></li>
</ul>
<div class="openAppGrid">
    <div id="openAppGrid"></div>
</div>
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
                {field:"activityid",text:"活动编号", sortColumn:"activityid",width: 80},
                {field:"activityimg",text:"主图",style:"text-align:center",width: 200,formatter:function(index, content, data){
                    return "<img src='" + content + "' height='20px'/>";
                }},
                {field:"activityname", text:"活动名称"},
                {field:"activitysdate", text:"活动开始时间"},
                {field:"activityedate", text:"活动结束时间"},
                {field:"activityaddr", text:"活动地址"},
                {field:"id", text:"操作", style:"text-align:center", width: 80, formatter:function(index, content, data){
                    var editUrl = "admin/activity/updateActivitPage?cz=dsj&id=" + content;
                /*    var delUrl = "admin/activity/delActivit/" + content;*/
                    return "<a href='javascript:yl(\""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='glyphicon glyphicon-search'>预览</i></a>"
                         + "&nbsp;<a href='javascript:sj(\""+content+"\");' class='btn btn-xs btn-success add-tooltip'><i class='glyphicon glyphicon-circle-arrow-up'>上架</i></a>"
                        + "&nbsp;<a href='javascript:goPage(\""+editUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='glyphicon glyphicon-wrench'>编辑</i></a>";
                }}
            ],
            cls: "",
            url: _urlPath + "admin/activity/queryPagedsj",
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
 function sj(id) {
     var delUrl = "admin/activity/edit?id="+id+"&state=1";
     showCfm("确定上架该活动吗？", delUrl);

 }

 function gorelod(s) {

     $("#daytime").val(s);

     $("#openAppGrid").sgrid("reload");
 }
</script>