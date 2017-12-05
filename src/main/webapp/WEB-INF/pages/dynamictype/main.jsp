<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="dynamicname" placeholder="搜索关键词">
        </div>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>
    </div>
    <div class="col-md-3" style="text-align: left;padding-bottom: unset">
        <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:goPage('admin/content/dynamicaddpage');"><span class="btn-label icon fa fa-plus"></span>添加行业分类</button>
    </div>
</div>
<div class="openAppGrid">
    <div id="openAppGrid">
    </div>
</div>
<style>
    .u_img{
        border-radius: 999999px;
    }
</style>
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
                {
                    field:"dynamicname",
                    text:"行业分类",
                    width:"900",
                    style:"text-align:left"
                },
                {

                    field:"state",
                    text:"操作",
                    style:"text-align:center",
                    formatter:function(index, content, data) {
                        if(data.state=="0"){
                            return "<span class=\"label label-success\">正常</span>" ;
                        }else{
                            return "<span class=\"label label-danger\">屏蔽</span>" ;
                        }

                    }
                },
                {
                    field:"creat_time",
                    text:"创建时间",
                    style:"text-align:center"
                },
                {
                    field:"state",
                    text:"操作",
                    style:"text-align:center",
                    formatter:function(index, content, data){
                        var delUrl = "admin/content/dynamicupdatepage?id="+data.id;
                        var html="";
                        if(content=="0"){
                            html="<a href='javascript:showModal(\"修改标签\" ,\"" + delUrl + "\")' class='btn btn-primary'>编辑</a>" +
                                "<a href='javascript:delect(\"" + data.id + "\",\"1\")' class='btn btn-danger'>停用</a>"
                        }else{
                            html="<a href='javascript:showModal(\"修改标签\" ,\"" + delUrl + "\")' class='btn btn-primary'>编辑</a>" +
                                "<a href='javascript:delect(\"" + data.id + "\",\"0\")' class='btn btn-success'>启用</a>"
                        }
                        return html;
                    }
                }

            ],
            cls: "",
            url: _urlPath + "admin/content/dynamicList",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });

    function delect(id,state){
        var delUrl ="";
            delUrl = "admin/content/dynamicdelect?id="+id+"&state="+ state;
        showCfm("是否决定操作?", delUrl);
    }

    function gorelod(s) {

        $("#state").val(s);

        $("#openAppGrid").sgrid("reload");
    }

</script>