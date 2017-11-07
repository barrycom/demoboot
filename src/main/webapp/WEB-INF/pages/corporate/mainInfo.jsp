<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-6" style="width: 400px">
            公司名称：
            <input type="text" class="form-control search-query" disabled name="corporatename" value="${member.corporatename}" placeholder="公司名称">
        </div>
    </div>
    <div class="col-md-6" style="width: 400px">
        行业：
        <input type="text" class="form-control search-query" disabled  value="${member.tradename}" placeholder="行业">
    </div>
    <div class="col-md-6" style="width: 400px">
        地区：
        <input type="text" class="form-control search-query" disabled  value="${member.regionname}" placeholder="地区">
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
                {field:"name",width:150, text:"用户名称",formatter:function(index, content, data){
                    return "<img src="+data.headimg+" height='30px' class='u_img'/>" + content;
                }},
                {field:"mobile",width:100, text:"手机号"},
                {field:"wxno",width:80, text:"微信号"},
                {field:"state", width:40,text:"审核状态",formatter:function(index, content, data){
                    return 1 == content ? "<font color='blue'>已审核</font>" : "<font color='red'>未审核</font>";
                }}

            ],
            cls: "",
            url: _urlPath + "admin/corporate/queryMemberPage",
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