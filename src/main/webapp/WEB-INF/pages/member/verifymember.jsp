<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="realname" placeholder="实名搜索">
        </div>
        <div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
            <select class="form-control" name="state" onchange="javascript:formSubmit();">
                <option value="">==状态==</option>
                <option value="0">未审核</option>
                <option value="1">已通过</option>
                <option value="2">已拒绝</option>
            </select>
        </div>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
        </div>
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
                {field:"realname",width:80, text:"用户名称"},
                {field:"cardfront",width:80, text:"身份证正面照",formatter:function(index, content, data){
                    return "<img src="+data.cardfront+" height='30px' width='80px' onclick='bigPhoto(\""+content+"\")' />";
                }},
                {field:"cardback",width:80, text:"身份证反面照",formatter:function(index, content, data){
                    return "<img src="+data.cardfront+" height='30px' width='80px' onclick='bigPhoto(\""+content+"\")' />";
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
                {field:"createtime",width:135, text:"注册时间"},
                {field:"id", text:"操作",width:135, style:"text-align:center", formatter:function(index, content, data){
                        var delUrl = "admin/member/blockMember/" + content;
                        return "<a href='javascript:showCfm(\"确定屏蔽/取消屏蔽 吗\", \""+delUrl+"\");'  data-original-title='屏蔽/取消' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>屏蔽/取消</i></a>";
                }}

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


 /*   function  goadd() {
        location.href=_urlPath+"admin/activity/addPage"
    }*/

</script>