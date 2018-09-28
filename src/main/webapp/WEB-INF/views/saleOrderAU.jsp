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


<%--@elvariable id="saleOrderBO" type=""--%>
<form:form  style="width:80%;" id="form2" method="POST" modelAttribute="saleOrderBO">
    <form:errors path="*"></form:errors>
    <c:if test="${saleOrder.id != null }">
        <input type="hidden" name="_method" value="PUT"/>
        <input type="hidden" name="id" value="${saleOrder.id }">
    </c:if>

    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">订单编号</label>
        <div class="layui-input-block">
            <form:input path="saleOrder.code" class="layui-input"  lay-verify="required" placeholder="请输入订单编号："/>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">购买客户</label>
        <div class="layui-input-block">
            <form:input path="saleOrder.customerBuy" class="layui-input"  lay-verify="required" placeholder="请输入购买客户："/>
        </div>
    </div><div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">销售员姓名</label>
        <div class="layui-input-block">
            <form:input path="loginName" class="layui-input"  lay-verify="required" placeholder="请输入销售员姓名："/>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">销售商品的仓库</label>
        <div class="layui-input-inline">
            <form:select path="saleOrder.depotCode" items="${depotList }" itemLabel="name" itemValue="code"></form:select>
        </div>
    </div>
    <div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
        <label class="layui-form-label">销售商品</label>
        <div class="layui-input-inline">
          <form:select path="saleOrder.wareCode" items="${wareList }" itemLabel="name" itemValue="code"></form:select>
        </div>
    </div>
    <div class="layui-form-item layui-row layui-col-xs12">
        <label class="layui-form-label">销售数量</label>
        <div class="layui-input-block">
            <form:input path="saleOrder.number" class="layui-input"  lay-verify="required" placeholder="请输入销售数量"/>
        </div>
    </div>
    <c:if test="${orderType != null }">
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">申请退货人姓名</label>
            <div class="layui-input-block">
                <form:input path="saleOrder.requName" class="layui-input"  lay-verify="required" placeholder="请输入申请退货人姓名"/>
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">申请退货人电话</label>
            <div class="layui-input-block">
                <form:input path="saleOrder.rPhoneNumber" class="layui-input"  lay-verify="required" placeholder="请输入申请退货人电话"/>
            </div>
        </div>
        <div class="layui-form-item layui-row layui-col-xs12">
            <label class="layui-form-label">退货收货人</label>
            <div class="layui-input-block">
                <form:input path="saleOrder.consignee" class="layui-input"  lay-verify="required" placeholder="请输入退货收货人"/>
            </div>
        </div>
    </c:if>

    <div class="layui-form-item layui-row layui-col-xs12">
        <div class="layui-input-block">
            <c:if test="${saleOrderBO.saleOrder.id == null }">
                <button class="layui-btn layui-btn-sm" lay-submit lay-filter="addOrder">立即添加</button>
            </c:if>
            <c:if test="${saleOrderBO.saleOrder.id != null }">
                <button class="layui-btn layui-btn-sm" lay-submit lay-filter="updateOrder">确认修改</button>
            </c:if>
            <button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
        </div>
    </div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/saleOrderAU.js"></script>
</body>
</html>