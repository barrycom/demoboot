<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${ctx}/assets/css/view.css" type="text/css" rel="stylesheet"/>

<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <input name="status" id="status" type="text" value="all" hidden="hidden">
        <div class="col-md-2" style="width: 200px">
            <input type="text" class="form-control search-query" name="username" placeholder="用户名">
        </div>
        <div class="col-md-2" style="padding-bottom: 0px;width: 200px;">
            <input type="text" class="form-control search-query" name="mobile" placeholder="手机号">
        </div>
        <div class="col-md-1" style="width: 105px;">
            <button id="searchBtn" class="btn btn-labeled btn-info" onclick="javascript:formSubmit();"><span class="btn-label icon fa fa-search"></span>搜索</button>
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
              /*  {field:"activityid",text:"活动编号", sortColumn:"activityid",width:0},*/
                {field:"activitytypename",text:"昵称", sortColumn:"activitytypename",width:80},
                {field:"activitysdate", text:"真实姓名"},
                {field:"activityedate", text:"活动结束时间"},
                {field:"activityaddr", text:"活动地址"},
                {field:"membersList", text:"参与人",
                formatter:function(index, content, data){
if(data.membersList!="")
{
   var obj=data.membersList;
   var dhtml="";
   for(var i=0;i<obj.length;i++)
   {
    dhtml+= "<img style='width: 30px;height: 30px' src='"+obj[i].headimg+"'>&nbsp;"
   }
    return dhtml
}else
{
    return "暂无参与人"
}

        }},
                {field:"viewmembersList", text:"浏览记录",
                    formatter:function(index, content, data){
                        if(data.viewmembersList!="")
                        {
                            var obj=data.viewmembersList;
                            var dhtml="";
                            for(var i=0;i<obj.length;i++)
                            {
                                dhtml+= "<img style='width: 30px;height: 30px' src='"+obj[i].headimg+"'>&nbsp;"
                            }
                            return dhtml
                        }else
                        {
                            return "暂无浏览"
                        }

                    }},

                {field:"createtime", text:"发布时间"},
                {field:"id", text:"操作", style:"text-align:center", width: 80, formatter:function(index, content, data){
                    var datzx=data;
                    var editUrl = "admin/activity/updateActivitPage?cz=all&id=" + content;
                    var delUrl = "admin/activity/delActivit/" + content;
                    var pviewUrl = "admin/activity/view?id=" + content;
                    return "<a href='javascript:goPage(\""+editUrl+"\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>修改</i></a>"
                    +"&nbsp;<a href='javascript:yl("+JSON.stringify(data)+");' class='btn btn-xs btn-success add-tooltip'><i class='glyphicon glyphicon-search'>预览</i></a>"
                        + "&nbsp;<a href='javascript:goPage(\""+pviewUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>详情</i></a>"
                        + "&nbsp;<a href='javascript:showCfm(\"确定删除该记录\", \""+delUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times'>删除</i></a>";
                }}
            ],
            cls: "",
            url: _urlPath + "admin/activity/queryPage",
            sort:"id",
            order:"desc",
            pagination:true,
            onLoad:function(){
                $(".add-tooltip").tooltip();
            }
        });
    });

    function gorelod(obj) {

        $("#status").val(obj);

        $("#openAppGrid").sgrid("reload");


    }
 /*   function  goadd() {
        location.href=_urlPath+"admin/activity/addPage"
    }*/

 function yl(data) {
     console.log(JSON.stringify(data));
   /*  data.activityimg)*/
     $("#viewlogo").attr("src",data.activityimg)
     $(".activity-name").html(data.activityname)
     $("#pricetext").html("¥"+data.activityprice)
     $("#hddate").html(data.activitysdate+"-"+data.activityedate)
     $("#hdaddress").html(data.activityaddr)
     $("#hdxq").html(data.activityidcontent);
     $("#wxts").html(data.activityidmemo)
     $('#myModal').modal({
         keyboard: true
     })

     $("#base").show();

 }
</script>