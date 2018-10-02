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

<%--@elvariable id="purchaseOrder" type="aj"--%>
<form:form class="layui-form" style="width:80%;" id="form2" method="POST" modelAttribute="purchaseOrder">
    <form:errors path="*"></form:errors>
    <c:if test="${purchaseOrder.id != null }">
        <input type="hidden" name="_method" value="PUT"/>
        <input type="hidden" name="id" value="${purchaseOrder.id }">
    </c:if>
    <%--后台自动生成--%>
    <%--
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">订单编号</label>
        <div class="layui-input-block">
            <form:input path="code" class="layui-input"  lay-verify="required" placeholder="请输入订单编号："></form:input>
        </div>

    </div>--%>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">申请人姓名</label>
        <div class="layui-input-block">
            <form:input path="" class="layui-input"  lay-verify="required" placeholder="请输入申请人姓名："/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">申请人电话</label>
        <div class="layui-input-block">
            <form:input path="" class="layui-input"  lay-verify="required" placeholder="请输入申请人电话："/>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">采购部门</label>
        <div class="layui-input-inline">
            <%--<form:select path="departmentCode" items="${departmentList }" itemLabel="name" itemValue="id"></form:select>--%>
                <form:select path="departmentCode" items="${departmentList}" cssStyle="width:80px;height: 40px;"></form:select>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">供应商</label>
        <div class="layui-input-inline">
           <%-- <form:select path="" items="${}" itemLabel="" itemValue=""></form:select>--%>
               <form:select path="supplierCode" items="${supplierList}" cssStyle="width:80px;height: 40px;"></form:select>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">商品类型</label>
        <div class="layui-input-inline">
            <%--<form:select path="" items="${}" itemLabel="" itemValue=""></form:select>--%>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">商品品牌 </label>
        <div class="layui-input-inline">
            <%--<form:select path="" items="${}" itemLabel="" itemValue=""></form:select>--%>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">商品名称</label>
        <div class="layui-input-inline">
            <%--<form:select path="" items="${}" itemLabel="" itemValue=""></form:select>--%>
        </div>
    </div>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">采购数量</label>
        <div class="layui-input-block">
            <form:input path="purchaseNumber" class="layui-input"  lay-verify="required" placeholder="请输入商品数量"/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">单价</label>
        <div class="layui-input-block">
            <form:input path="unitPrice" class="layui-input"  lay-verify="required" placeholder="请输入商品单价"/>
        </div>
    </div>
    <%--后台计算 --%>
    <%--
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">总价</label>
            <div class="layui-input-block">
                <form:input path="totalPrice" class="layui-input"  lay-verify="required" placeholder="请输入商品品牌"/>
            </div>
        </div>
    --%>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">收货人</label>
        <div class="layui-input-block">
            <form:input path="consignee" class="layui-input"  lay-verify="required" placeholder="请输入收货人"/>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">收货仓库</label>
        <div class="layui-input-inline">
            <%--<form:select path="depotCode" items="${depotList }" itemLabel="" itemValue=""></form:select>--%>
                <form:select path="depotCode" items="${depotList }" cssStyle="width:80px;height: 40px;"></form:select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <c:if test="${purchaseOrder.id == null }">
                <button class="layui-btn layui-btn-sm" lay-submit lay-filter="addOrder">立即添加</button>
            </c:if>
            <c:if test="${purchaseOrder.id != null }">
                <button class="layui-btn layui-btn-sm" lay-submit lay-filter="updateOrder">确认修改</button>
            </c:if>
            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
        </div>
    </div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/purchaseOrderAU.js"></script>
</body>
</html>