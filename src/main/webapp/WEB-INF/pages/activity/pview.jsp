<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
    <title>事实上</title>


</head>
<body>
<form id="submitForm" class="form-horizontal">
    <div class="form-group">
        <input name="id" value="${activity.id}" type="text" hidden="hidden">
        <input name="cz" id="cz" value="${cz}" type="text" hidden="hidden">
        <label class="col-sm-3 control-label" for="activityname"><font color="red">*</font>活动名称：</label>
        <div class="col-sm-8">
            <input class="form-control" type="text" disabled id="activityname" name="activityname" value="${activity.activityname}" placeholder="请填写活动名称"/>

        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="activitysdate"><font color="red">*</font>活动开始时间：</label>
        <div class="col-sm-8">

            <input size="16" type="text" id="activitysdate" disabled name="activitysdate"  value="${activity.activitysdate}" readonly class="form_datetime" placeholder="请填写活动开始时间">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="activityedate"><font color="red">*</font>活动结束时间：</label>
        <div class="col-sm-8">

            <input size="16" type="text" id="activityedate" disabled name="activityedate" readonly class="form_datetime" value="${activity.activityedate}" placeholder="请填写活动结束时间">
        </div>
    </div>
    <div class="form-group">
        <label class="col-sm-3 control-label" for="activityaddr"><font color="red">*</font>活动地点：</label>
        <div class="col-sm-8">
            <input class="form-control" value="${activity.activityaddr}" disabled type="text" id="activityaddr" name="activityaddr" placeholder="请选择活动地点"/>
            <div id="validation-activityaddr" class="validate-error help-block"></div>
        </div>
    </div>

    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" for="content"><font color="red">*</font>活动内容：</label>
        <div class="col-sm-8">
            <script id="content" name="activityidcontent"  type="text/plain">${activity.activityidcontent}</script>
        </div>
    </div>

    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" ><font color="red">*</font>参与人：</label>
        <div class="col-sm-8">
            <c:choose>
                <c:when test="${(activity.membersList)!= null && fn:length(activity.membersList) > 0}">
                    <c:forEach items="${activity.membersList}" var="item">
                        <img style='width: 40px;height: 40px' onclick="goUser('${item.id}')" src='${item.headimg}'>

                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <label class="col-sm-1 control-label" for="activityaddr"><font color="red"></font>暂无</label>

                </c:otherwise>
            </c:choose>
        </div>
    </div>


    <div class="form-group" style="padding-left: unset; padding-right: unset;">
        <label class="col-sm-3 control-label" ><font color="red">*</font>浏览记录：</label>
        <div class="col-sm-8">
          <%--  <c:if test="${(activity.viewmembersList)!= null && fn:length(activity.viewmembersList) > 0}">
            <c:forEach items="${activity.viewmembersList}" var="item">
                <img style='width: 40px;height: 40px' onclick="goUser('${item.id}')" src='${item.headimg}'>

            </c:forEach>
            </c:if>--%>
            <c:choose>
                <c:when test="${(activity.viewmembersList)!= null && fn:length(activity.viewmembersList) > 0}">
                    <c:forEach items="${activity.viewmembersList}" var="item">
                        <img style='width: 40px;height: 40px' onclick="goUser('${item.id}')" src='${item.headimg}'>

                    </c:forEach>
                </c:when>
                <c:otherwise>
                    <label class="col-sm-1 control-label" for="activityaddr"><font color="red"></font>暂无</label>

                </c:otherwise>
            </c:choose>
        </div>

    </div>

    <div class="form-group">
        <label class="col-sm-2 control-label"></label>
        <div class="col-sm-8" style="text-align: center;">

            <button class="btn btn-warning" type="reset" onclick="javascript:reback();">
                <i class="ace-icon fa fa-reply bigger-110"></i>返回
            </button>
        </div>
    </div>
</form>
</body>
<script>

    initUeditor();

    function goUser(id) {
       // alert(id)
    }


    function reback() {

            goPage("admin/activity/mainPage")


    }
</script>
</html>