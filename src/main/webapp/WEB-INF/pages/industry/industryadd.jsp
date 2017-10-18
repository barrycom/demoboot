<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<form id="submitForm" class="form-horizontal">
        <div id="cloum">
            <div class="form-group">
                <label class="col-sm-1 control-label">行业名称：</label><%--for="username"--%>
                <div class="col-sm-2">
                    <input class="form-control" type="text" id="industryname_1" name="industryname_1"/>
                    <div id="validation-username" class="validate-error help-block"></div>
                </div>
            </div>
        </div>

    <div class="form-group" style="margin: 50px 59px;">
        <div class="btn-group">
            <button type="button" class="btn btn-primary dropdown-toggle" id="addcloum" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              继续添加
            </button>

        </div>
    </div>



    <div class="form-group" style="margin: 50px 49px;">
        <div class="btn-group">
            <button type="button" class="btn btn-success dropdown-toggle" style="width: 344px;" id="save" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                新增分类
            </button>

        </div>
    </div>


</form>
<script type="text/javascript">
    var count=2;
    var nameStr="";
    $(function () {
    //添加输入框
            $("#addcloum").click(function(){
                if(count<11){
                    var html="<div class=\"form-group\">"
                            +"<label class=\"col-sm-1 control-label\">行业名称：</label><div class=\"col-sm-2\"><input class=\"form-control\" type=\"text\" id=\"industryname_"+count+"\" name=\"industryname_"+count+"\"/>"
                            +"<div id=\"validation-username\" class=\"validate-error help-block\"></div></div></div>";
                    $("#cloum").append(html)
                    count++;
                }else{
                    $.Err("一次最多添加10个标签!");
                }
            })

        //保存标签
        $("#save").click(function(){
                debugger;
            for(var i=1;i<=count-1;i++){

                    if($("#industryname_"+i).length>0){
                        if($("#industryname_"+i).val()!="") {
                            nameStr +=$("#industryname_"+i).val()+",";
                        }
                    }

            }

              if(nameStr!=""){
                    $.ajax({
                        url: _urlPath + "admin/content/industryadd",
                        data: {"industryname":nameStr},
                        type: "post",
                        dataType:"json",
                        success: function (req){
                            if (req.retcode == 1) {
                                goPage("admin/content/industrypage")
                            } else {
                                modalErr(req.retmsg);
                            }
                        }
                    });
              }

        })



    })

</script>