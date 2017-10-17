<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<link href="${ctx}/assets/resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/assets/data/styles.css" type="text/css" rel="stylesheet"/>
<link href="${ctx}/assets/files/index/styles.css" type="text/css" rel="stylesheet"/>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="uname" placeholder="用户名">
        </div>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>
    </div>
</div>

<div class="openAppGrid">
    <div id="openAppGrid">
    </div>
</div>
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">

                            <div id="base" class="modal-dialog" style="margin-left: 196px;margin-top: -66px;">
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
                    field:"headimg",
                    text:"用户",
                    style:"text-align:center",
                    width: 150,
                    formatter:function(index, content, data){
                    if("" == data.headimg || null == data.headimg){
                        return null;
                    }else{
                        return "<img src="+data.headimg+" height='30px' class='u_img' />";
                    }
                }},
                {
                    field:"uname",
                    text:"用户名称"},
                {
                     field:"state",
                     text:"状态",
                     formatter:function(index, content, data){
                            if(data.state=="0"){
                                return "<span class=\"label label-success\">正常</span>" ;
                            }else{
                                return "<span class=\"label label-danger\">屏蔽</span>" ;
                            }

                        }
                },
                {field:"dynamicwz", text:"动态内容"},
                {field:"industryid", text:"动态分类"},
                {field:"imgurl",
                 text:"动态图片",
                 formatter:function(index, content, data){
                     var html="";
                    if("" == data.imgurl || null == data.imgurl){
                            return null;
                        }else{
                            var myArray=new Array()
                            myArray=data.imgurl.split(",");
                            myArray.length;
                                for(var i=0;i<myArray.length;i++){
                                    if(i==3){
                                        break;
                                    }else {
                                        html+="<img src="+myArray[i]+" height='30px' class='u_img' />"
                                    }
                                }
                            return html;
                        }
                    }
                },
                {field:"id", text:"操作",style:"text-align:center", width: 80, formatter:function(index, content, data) {

                    return "<a href='javascript:showphone(\"" + data.id + "\" )' class='btn btn-primary'>预览详情</a>"
                        + "&nbsp;<a href='javascript:screening(" + data.id + ");' class='btn btn-success'>屏蔽动态</a>";//showCfm(\"确定屏蔽该动态\", \"" + delUrl + "\")
                }}

            ],
            cls: "",
            url: _urlPath + "admin/content/queryPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });
     //弹出确认框
    function screening(url){
        var delUrl = "admin/content/screening?id=" + url;
        /*showCfm(, delUrl);*/
        $.Cfm("确定屏蔽该动态",function(){
            $.ajax({
                url: _urlPath + delUrl,
                type:"post",
                dataType:"json",
                success: function (req){
                    debugger;
                    var aa=req;
                    if (req.retcode == 1) {
                        $("#openAppGrid").sgrid("refresh");
                        $.Suc(req.retmsg);
                    } else {
                        $.Err(req.retmsg);
                    }
                }
            });
        });
    }

   function showphone(url){
        $.ajax({
            url : '${ctx}/admin/content/showphone?id='+url,
            type : "post",
            dataType : "json",
            success : function(req) {
                debugger
                if (req.retcode == 1) {
                    $("#base").html(req.data);
                } else {
                    $.Err("退出失败！");
                }
            },
            error : function() {
                $.Err("系统异常！");
            }
        });

        $('#myModal').modal({
            keyboard: true
        })

    }


</script>