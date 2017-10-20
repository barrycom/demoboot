<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="name" placeholder="用户名">
        </div>
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="mobile" placeholder="手机号">
        </div>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>

        <div class="col-md-5" style="text-align: right;padding-bottom: unset">
            <button id="reging" class="btn btn-labeled btn-primary" onclick="gorelod('0')"><span class="btn-label icon fa fa-plus"></span>屏蔽用户
            </button>
            <button id="pass" class="btn btn-labeled btn-primary" onclick="gorelod('1')"><span class="btn-label icon fa fa-plus"></span>屏蔽会员
            </button>
        </div>
        <input name="ishy" id="ishy" type="hidden">
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
                {field:"id",width:40,text:"用户ID", sortColumn:"id"},
                {field:"name",width:80, text:"用户名称",formatter:function(index, content, data){
                    return "<img src="+data.headimg+" height='30px' class='u_img'/>" + content;
                }},
                {field:"mobile",width:100, text:"手机号"},
                {field:"wxno",width:80, text:"微信号"},
                {field:"state", width:40,text:"审核状态",formatter:function(index, content, data){
                    return 1 == content ? "<font color='blue'>已审核</font>" : "<font color='red'>未审核</font>";
                }},
                {field:"ishy",width:50, text:"是否购买",formatter:function(index, content, data){
                    return 1 == content ? "<font color='blue'>已购买</font>" : "<font color='red'>未购买</font>";
                }},
                {field:"isblock",width:40, text:"屏蔽状态",formatter:function(index, content, data){
                    return 0 == content ? "<font color='blue'>正常</font>" : "<font color='red'>屏蔽</font>";
                }},
                {field:"regtime",width:135, text:"注册时间"},
                {field:"id", text:"操作",width:135, style:"text-align:center", formatter:function(index, content, data){
                    var ck = "admin/member/updateMemberPage?type=6&memberid=" + content;
                    var sj = "admin/member/updateMemberPage?type=2&memberid=" + content;
                    var bj = "admin/member/updateMemberPage?type=3&memberid=" + content;

                    var delUrl = "admin/member/blockMember/" + content;
                    return "<a href='javascript:goPage(\""+ck+"\");' data-original-title='查看' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>查看</i></a>"
                        +"&nbsp;<a href='javascript:showCfm(\"确定屏蔽/取消屏蔽 吗\", \""+delUrl+"\");'  data-original-title='屏蔽/取消' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>屏蔽/取消</i></a>";
                }}

            ],
            cls: "",
            url: _urlPath + "admin/member/queryPage/3",
            data:{"state":"1"},
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });

    function gorelod(obj) {

        $("#ishy").val(obj);

        $("#openAppGrid").sgrid("reload");


    }

    /*   function  goadd() {
     location.href=_urlPath+"admin/activity/addPage"
     }*/
</script>