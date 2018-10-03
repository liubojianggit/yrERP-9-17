<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>用户中心--layui后台管理模板 2.0</title>
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
<form class="layui-form">
    <blockquote class="layui-elem-quote quoteBox">
        <form class="layui-form" id="searchFormId">
            <div class="layui-inline">
                <div class="layui-input-inline">
                    <div style='width:25%;heigth:50%,padding:0;margin:0;float:left;box-sizing:border-box;'>
                        <input type="text" class="layui-input searchVal" id="purchaseCode" value="" placeholder="请输入订单名称/编号：" />
                    </div>
                    <div style='width:25%;heigth:50%,padding:0;margin:0;float:left;box-sizing:border-box;'>
                        <input type="text" class="layui-input searchVal" id="purchaseWareCode" placeholder="请输入商品名称：" />
                    </div>
                    <div style='width:25%;heigth:50%,padding:0;margin:0;float:left;box-sizing:border-box;'>
                    <select name="city" id="rStates" lay-verify="">
                        <option value="">请选择订单类型</option>
                        <option value="0">驳回</option>
                        <option value="1">交易成功</option>
                        <option value="2">待审核</option>
                        <option value="3">申请退货</option>
                        <option value="4">退货成功</option>
                    </select>
                    </div>
                </div>
                <a class="layui-btn search_btn" data-type="reload">搜索</a>
            </div>
            <div class="layui-inline">
                <a class="layui-btn layui-btn-normal addNews_btn">采购申请</a>
            </div>
            <div class="layui-inline">
               <%-- <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>--%>
                   <a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
            </div>
        </form>
    </blockquote>
    <table id="purchaseList" lay-filter="purchaseList"></table>
    <!--操作-->
    <script type="text/html" id="purchaseBar">
        <a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
        <a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
    </script>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/purchaseOrderList.js"></script>
</body>
</html>