<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- 引入form标签的标签库 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>文章列表--layui后台管理模板 2.0</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/layui/css/layui.css" media="all" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/css/public.css" media="all" />
</head>
<body class="childrenBody">

<form:form class="layui-form" style="width:80%;" id="form2" method="POST" modelAttribute="wareBO">
    <form:errors path="*"></form:errors>
    <c:if test="${wareBO.ware.id != null }">
        <input type="hidden" name="_method" value="PUT"/>
        <input type="hidden" name="id" value="${wareBO.ware.getId() }">
    </c:if>
    <!-- 上传头像 -->               <!-- div居中 -->
    <div class="layui-upload" align="center">
        <c:if test="${wareBO.ware.id != null }">
            <div class="layui-upload-list">
                <!-- 头像回显的样式，这里是圆形 -->
                <img src="<%=request.getContextPath() %>/userTable/icons/"+${wareBO.ware.id } class="layui-upload-img layui-circle userFaceBtn userAvatar" style="width:200px;height:200px;" id="demo2">
                <p id="demoText"></p>
            </div>
            <input type="hidden" name="headUrl" id="headUrl2" value=""/>
            <button type="button" class="layui-btn" id="test2">修改图片</button>
        </c:if>
        <c:if test="${userBO.user.id == null }">
            <div class="layui-upload-list">
                <!-- 头像回显的样式，这里是圆形 -->
                <img class="layui-upload-img layui-circle userFaceBtn userAvatar" style="width:200px;height:200px;" id="demo1">
                <p id="demoText"></p>
            </div>
            <button type="button" class="layui-btn" id="test1">上传图片</button>
            <!-- 上传头像成功后保存的隐藏框 -->
            <input type="hidden" name="headUrl" id="headUrl" value="">
        </c:if>

    </div>


    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品名：</label>
        <div class="layui-input-block">
            <form:input path="ware.name" class="layui-input"  lay-verify="required" placeholder="请输入商品名"/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品编号</label>
        <div class="layui-input-block">
            <form:input path="ware.code" class="layui-input"  lay-verify="required" placeholder="请输入商品编号"/>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">商品类型</label>
        <div class="layui-input-inline">
            <form:select path="ware.type" items="${addrProvinces }" itemLabel="name" itemValue="id"></form:select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品品牌</label>
        <div class="layui-input-block">
            <form:input path="ware.brand" class="layui-input"  lay-verify="required" placeholder="请输入商品品牌"/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品条码</label>
        <div class="layui-input-block">
            <form:input path="ware.barCode" class="layui-input"  lay-verify="required" placeholder="请输入商品条码"/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品库存</label>
        <div class="layui-input-block">
            <form:input path="ware.totalInventory" class="layui-input"  lay-verify="required" placeholder="请输入商品库存"/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">采购单价</label>
        <div class="layui-input-block">
            <form:input path="ware.inUnitPrice" class="layui-input"  lay-verify="required" placeholder="请输入采购单价"/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">销售单价</label>
        <div class="layui-input-block">
            <form:input path="ware.outUnitPrice" class="layui-input"  lay-verify="required" placeholder="请输入销售单价"/>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">产地</label>
        <div class="layui-input-inline">
            <form:select path="ware.addr" items="${addrProvinces }" itemLabel="name" itemValue="id"></form:select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">商品备注</label>
        <div class="layui-input-block">
            <form:input path="ware.remark" class="layui-input"  lay-verify="required" placeholder="请输入商品备注"/>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <c:if test="${user.id == null }">
                <button class="layui-btn layui-btn-sm" lay-submit lay-filter="addUser">立即添加</button>
            </c:if>
            <c:if test="${user.id != null }">
                <button class="layui-btn layui-btn-sm" lay-submit lay-filter="updateUser">确认修改</button>
            </c:if>
            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
        </div>
    </div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/wareAU.js"></script>
</body>
</html>