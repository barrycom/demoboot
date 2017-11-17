<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<link href="${ctx}/assets/css/view.css" type="text/css" rel="stylesheet"/>

<div class="modal fade" id="myModal" tabindex="-1" align="center" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="phone">
        <div class="phone-content">
            <div class='activity-bg'>
                <div class='activity-content-bg'>

                    <image id="viewlogo" src="http://p2.wmpic.me/article/2017/09/15/1505443129_lZfdpYvH_215x185.jpg" class="slide-image" />

                    </swiper>

                    <!--活动名称价格。。  -->
                    <div class='details-head'>
                        <div class='activity-name'>

                        </div>
                        <div class='activity-price'>
                            <text id="pricetext">¥950</text> /人
                        </div>
                        <div class='activity-addresss'>
                            <image src='${ctx}/assets/images/activity/act_det_icon_time.png'  width="13" height="13"></image><span id="hddate">10月1日-10月8日</span></div>
                        <div class='activity-addresss'>
                            <image src='${ctx}/assets/images/activity/act_det_icon_add.png'  width="15" height="17"></image> <span id="hdaddress">武汉江岸区武汉剧院</span>
                            <image src='${ctx}/assets/images/activity/list_btn_path.png' class='arrow-right'  width="13" height="13"></image>
                        </div>
                    </div>

                    <!--感兴趣的人  -->
                    <div class='watch-people-bg'>
                        新增此处无数据
                        <%--      <text class='watch-text'>他们也感兴趣</text>
                              <div>
                                  <image src='http://p2.wmpic.me/article/2017/09/15/1505443129_lZfdpYvH_215x185.jpg' class='watch-head'></image>
                                  <image src='http://p3.wmpic.me/article/2017/09/19/1505800793_ifRRQepR_215x185.jpg' class='watch-head'></image>
                                  <image src='http://p2.wmpic.me/article/2017/09/15/1505443129_lZfdpYvH_215x185.jpg' class='watch-head'></image>
                                  <image src='http://p3.wmpic.me/article/2017/09/19/1505800793_ifRRQepR_215x185.jpg' class='watch-head'></image>
                                  <text class='watch-head'>216</text>
                              </div>--%>
                    </div>

                    <!--活动详情  -->
                    <div class='activity-info-bg'>
                        <div class='activity-info-title'>活动详情</div>
                        <div id="hdxq">sdasds</div>
                    </div>

                    <!--温馨提示  -->
                    <div class='activity-info-bg'>

                        <div class='activity-info-title'>
                            温馨提示
                        </div>
                        <div class='activity-addresss' id="wxts">
                            1.2米以上儿童需持票入场。每张成人票可协同1名1.2米以下儿童入场。</div>

                    </div>


                </div>
                <!--我要参加  -->




            </div>


            <!--弹出  -->
            <div class='model-bg' hidden='{{model}}' >
                <div class='model-content'>
                    <div class='model-title'>选择数量
                        <div class='model-close' bindtap="modelHide">X</div>
                    </div>
                    <div class='num-bg'>
                        <div class='add'>-</div><div class='num' >2</div><div class='add add-light'>+</div>

                    </div>
                </div>

            </div>
        </div>

    </div>

</div>
<div class="page-header" style="padding:10px 20px;margin:-18px 0px 0px">
    <div id="searchForm">
        <input name="status" id="status" type="text" value="all" hidden="hidden">
        <div class="col-md-3" style="text-align: left;padding-bottom: unset">
            <button id="addBtn" class="btn btn-labeled btn-primary" onclick="javascript:goPage('admin/activity/addPage');"><span class="btn-label icon fa fa-plus"></span>添加活动
            </button>

        </div>

        <div class="col-md-9" style="text-align: right;padding-bottom: unset">
            <button id="staring" class="btn btn-labeled btn-primary" onclick="gorelod('starting')"><span class="btn-label icon fa fa-plus"></span>进行中
            </button>
            <button id="ending" class="btn btn-labeled btn-primary" onclick="gorelod('ending')"><span class="btn-label icon fa fa-plus"></span>已过期
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
              /*  {field:"activityid",text:"活动编号", sortColumn:"activityid",width:0},*/
                {field:"activitytypename",text:"活动类型", sortColumn:"activitytypename",width:80},

                {field:"activityimg",text:"活动名称",style:"text-align:center",width: 200,formatter:function(index, content, data){
                    return "<div><img align='left' src='" + content + "' width='40px' height='20px'/>"+data.activityname+"</div>";
                }},
                {field:"activitysdate", text:"活动开始时间"},
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

                    console.log(data.state)
                    var editUrl = "admin/activity/updateActivitPage?cz=all&id=" + content;
              /*      var delUrl = "admin/activity/delActivit/" + content;*/
                    var tyUrl = "admin/activity/sxstatus?id=" + content;
                    var pviewUrl = "admin/activity/view?id=" + content;
                    if(data.state==0) {
                        return "<a href='javascript:goPage(\"" + editUrl + "\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>修改</i></a>"
                            + "&nbsp;<a href='javascript:yl(" + JSON.stringify(data) + ");' class='btn btn-xs btn-success add-tooltip'><i class='fa  fa-eye'>预览</i></a>"
                            + "&nbsp;<a href='javascript:goPage(\"" + pviewUrl + "\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-book'>详情</i></a>"

                            + "&nbsp;<a href='javascript:showCfm(\"确定停用该活动\", \"" + tyUrl + "\");' class='btn btn-xs btn-success add-tooltip'><i class='fa  fa-sort-desc'>启用</i></a>"
                    }
                    else
                    {
                        return "<a href='javascript:goPage(\"" + editUrl + "\");' class='btn btn-xs btn-warning add-tooltip'><i class='fa fa-pencil'>修改</i></a>"
                            + "&nbsp;<a href='javascript:yl(" + JSON.stringify(data) + ");' class='btn btn-xs btn-success add-tooltip'><i class='fa  fa-eye'>预览</i></a>"
                            + "&nbsp;<a href='javascript:goPage(\"" + pviewUrl + "\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-book'>详情</i></a>"

                            + "&nbsp;<a href='javascript:showCfm(\"确定停用该活动\", \"" + tyUrl + "\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-sort-asc'>停用</i></a>"

                    }
                       /* + "&nbsp;<a href='javascript:showCfm(\"确定删除该活动\", \""+delUrl+"\");' class='btn btn-xs btn-danger add-tooltip'><i class='fa fa-times-circle'>删除</i></a>";*/
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
 function goshow() {

 }

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