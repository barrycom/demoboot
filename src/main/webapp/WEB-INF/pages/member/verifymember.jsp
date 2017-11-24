<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="realname" placeholder="实名搜索">
        </div>
        <%--<div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
            <select class="form-control" name="state" onchange="javascript:formSubmit();">
                <option value="">==状态==</option>
                <option value="0">未审核</option>
                <option value="1">已通过</option>
                <option value="2">已拒绝</option>
            </select>
        </div>--%>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>
        <div class="col-md-9" style="text-align: right;padding-bottom: unset">
            <button id="reging" class="btn btn-labeled btn-primary" onclick="gorelod('0')"><span class="btn-label icon fa fa-plus"></span>未审核
            </button>
            <button id="pass" class="btn btn-labeled btn-primary" onclick="gorelod('1')"><span class="btn-label icon fa fa-plus"></span>已通过
            </button>
            <button id="nopass" class="btn btn-labeled btn-primary" onclick="gorelod('2')"><span class="btn-label icon fa fa-plus"></span>已拒绝
            </button>
        </div>
        <input name="ispass" id="state" type="hidden">
    </div>
</div>
<div class="openAppGrid">
    <div id="openAppGrid"></div>
</div>
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"
                        aria-hidden="true">×
                </button>
                <h4 class="modal-title" id="myModalLabel">
                    身份证照片
                </h4>
            </div>
            <div class="modal-body">
                <img src="" id="cartPhoto" width="400" height="300"/>
            </div>
            <div class="modal-body">
                按下 ESC 按钮退出。
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default"
                        data-dismiss="modal">关闭
                </button>
            </div>
        </div><!-- /.modal-content -->
    </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
<script type="text/javascript">
    $(function (){
        $("#openAppGrid").sgrid({
            columns:[
                {field:"realname",width:80, text:"用户昵称",formatter:function(index, content, data){
                    return data.member.name;
                }},
                {field:"realname",width:80, text:"微信号",formatter:function(index, content, data){
                    return data.member.wxno;
                }},
                {field:"realname",width:80, text:"用户姓名"},
                {field:"cardno",width:100, text:"身份证号"},
                {field:"cardfront",width:80, text:"身份证正面照",formatter:function(index, content, data){
                    return "<img src="+data.cardfront+" height='30px' width='80px' onclick='bigPhoto(\""+content+"\")' />";
                }},
                {field:"cardback",width:80, text:"身份证反面照",formatter:function(index, content, data){
                    return "<img src="+data.cardback+" height='30px' width='80px' onclick='bigPhoto(\""+content+"\")' />";
                }},
                {field:"ispass", width:40,text:"审核状态",formatter:function(index, content, data){
                    if(content == 0){
                        return "<font color='blue'>未审核</font>";
                    }else if(content == 1){
                        return "<font color='blue'>已通过</font>";
                    }else{
                        return "<font color='red'>已拒绝</font>";
                    }
                }},
                {field:"createtime",width:135, text:"申请时间"},
                {field:"id", text:"操作",width:135, style:"text-align:center", formatter:function(index, content, data){
                    var ck = "admin/member/updateMemberPage?type=4&memberid=" + data.memberid;
                    var tg = "admin/member/memberInfoPass/"+content;
                    var jj = "admin/member/memberInfoRefusePage/" + content;

                    var url="";
                    if(data.ispass ==0){
                        url="<a href='javascript:goPage(\""+ck+"\");' data-original-title='查看' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>查看</i></a>"
                        + "&nbsp;<a href='javascript:showCfm(\"确定通过吗\", \""+tg+"\");' data-original-title='通过' class='btn btn-xs btn-success add-tooltip'><i class='fa fa-repeat'>通过</i></a>"
                        + "&nbsp;<a href='javascript:showModal(\"拒绝\", \""+jj+"\");' data-original-title='拒绝' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-repeat'>拒绝</i></a>"
                    }else{
                        url="<a href='javascript:goPage(\""+ck+"\");' data-original-title='查看' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>查看</i></a>";
                    }
                    return url;
                }},
                {field:"remark",width:135, text:"备注信息"}

            ],
            cls: "",
            url: _urlPath + "admin/member/queryMemberInfoPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });

    function bigPhoto(url) {
        $("#cartPhoto").attr("src",url);
        $('#myModal').modal({
            keyboard: true
        });
    }

    function gorelod(obj) {

        $("#state").val(obj);

        $("#openAppGrid").sgrid("reload");


    }


 /*   function  goadd() {
        location.href=_urlPath+"admin/activity/addPage"
    }*/

</script>