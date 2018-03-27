<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${ctx}/assets/css/view.css" type="text/css" rel="stylesheet"/>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <input name="status" id="status" type="text" value="all" hidden="hidden">
        <div class="col-md-3" style="text-align: left;padding-bottom: unset">
            <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:goPage('admin/activity/addadPage');"><span class="btn-label icon fa fa-plus"></span>添加广告位
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
                {field:"id",text:"广告id"},
                {field:"adUrl",text:"图片链接"},
                {field:"adType", text:"广告位类型",
                    formatter:function(index, content, data){
                    if(content=='0'){
                        return "首页广告位"
                    }else{
                        return "其他"
                    };
                }},
                {field:"adIndex", text:"排序"},
                {field:"id", text:"操作", style:"text-align:center", width: 80, formatter:function(index, content, data){
                    var editUrl = "admin/activity/updatead?id=" + content;
                    var deal ="admin/activity/removead?id="+ content;
                    return "<a href='javascript:goPage(\""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>编辑</i></a>"
                        +"&nbsp;<a href='javascript:delect(\""+deal+"\");' class='btn btn-xs btn-success add-tooltip'><i class='fa  fa-eye'>删除</i></a>"
                    }
                }
            ],
            cls: "",
            url: _urlPath + "admin/activity/adList",
            sort:"id",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
    function delect(url){
        showCfm("是否决定保存操作?", url);
    }

</script>