<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="name" placeholder="公司名称">
        </div>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>
    </div>
</div>
<div class="openAppGrid">
    <div id="openAppGrid"></div>
</div>
<style>
    .u_img{
        border-radius: 999999px;
        margin-right: 10px;
    }
</style>
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
                {field:"corporatename",width:120, text:"公司名称",formatter:function(index, content, data){
                    return  content;
                }},
                {field:"tradename",width:120, text:"行业",formatter:function(index, content, data){
                    return  content;
                }},
                {field:"regionname",width:120, text:"所在地区",formatter:function(index, content, data){
                    return  content;
                }},
                {field:"id", text:"操作",width:135, style:"text-align:center", formatter:function(index, content, data){
                        var ck = "admin/corporate/mainMemberPage?name=" + data.corporatename;
                        return "<a href='javascript:goPage(\""+ck+"\");' data-original-title='查看' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>查看</i></a>"
                }}

            ],
            cls: "",
            url: _urlPath + "admin/corporate/queryPage",
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