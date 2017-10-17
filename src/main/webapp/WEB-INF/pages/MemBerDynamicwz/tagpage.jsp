<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <%--<input name="state" id="state" type="text" value="0" hidden="hidden">--%>
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="uname" placeholder="用户名">
        </div>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>
    </div>
</div>
<%--<ul id="myTab" class="nav nav-tabs">
    <li class="active"><a href="#" data-toggle="tab" onclick="gorelod('0')">未使用标签</a></li>
    <li><a href="#" data-toggle="tab" onclick="gorelod('1')">使用中的标签</a></li>
    <li><a href="#" data-toggle="tab" onclick="gorelod('2')">已删除的标签</a></li>
</ul>--%>
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
                    field:"tname",
                    text:"用户",
                    style:"text-align:center"
                }/*,
                {
                    field:"state",
                    text:"状态",
                    style:"text-align:center",
                    formatter:function(index, content, data){
                        if(data.state=="0"){
                            var html="<span class=\"label label-success\">未使用</span>";

                            return html;
                        }else if(data.state=="1"){
                            var html="<span class=\"label label-success\">已使用</span>" ;

                            return html;
                        }else{
                            var html="<span class=\"label label-success\">待删除</span>" ;

                            return html;
                        }
                    }
                }*/,
                {

                    field:"state",
                    text:"操作",
                    style:"text-align:right",
                     formatter:function(index, content, data){
                         var delUrl = "admin/content/tagupdatepage?id="+data.id;
                         var html="<a href='javascript:showModal(\"修改标签\" ,\"" + delUrl + "\")' class='btn btn-primary'>编辑</a>" +
                             "<a href='javascript:delect(\"" + data.id + "\",\"2\")' class='btn btn-success'>删除</a>" ;

                         return html;
                            /*if(data.state=="0"){
                                var html="<a href='javascript:userit(\"" + data.id + "\",\"1\" )' class='btn btn-primary'>使用</a>" +
                                    "<a href='javascript:delect(\"" + data.id + "\",\"2\")' class='btn btn-success'>删除</a>" ;

                                return html;
                            }else if(data.state=="1"){
                                var html="<a href='javascript:userit(\"" + data.id + "\",\"0\" )' class='btn btn-primary'>不使用</a>" +
                                    "<a href='javascript:delect(\"" + data.id + "\",\"2\" )' class='btn btn-success'>删除</a>" ;

                                return html;
                            }else{
                                var html="<a href='javascript:userit(\"" + data.id + "\",\"0\" )' class='btn btn-primary'>恢复</a>" +
                                    "<a href='javascript:delect(\"" + data.id + "\",\"3\" )' class='btn btn-success'>永久删除</a>" ;

                                return html;
                            }*/
                        }
                }

            ],
            cls: "",
            url: _urlPath + "admin/content/tagList",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
     //弹出确认框
    function userit(id,state){
        var delUrl = "admin/content/tagupdate?id="+id+"&state="+ state;
        var title="";
        if(state==0){
            title="是否不使用该标签?"
        }else{
            title="是否使用该标签?"
        }
        showCfm(title, delUrl);

    }

    function delect(id,state){
        var delUrl ="";
        if(state==2){
            delUrl = "admin/content/tagupdate?id="+id+"&state="+ state;
        }else{
            delUrl = "admin/content/tagdelect/"+id;
        }

        showCfm("是否决定删除?", delUrl);
    }

    function gorelod(s) {

        $("#state").val(s);

        $("#openAppGrid").sgrid("reload");
    }

</script>