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
					<input type="text" class="layui-input searchVal" id="userName" placeholder="请输入姓名：" />
					<input type="text" class="layui-input searchVal" id="depaCode" value="" placeholder="请输入部门名称/编号：" />
					<input type="text" class="layui-input searchVal" id="minAge" value="" placeholder="请输入最小年龄：" />
					<input type="text" class="layui-input searchVal" id="maxAge" value="" placeholder="请输入最大年龄" />
				</div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addNews_btn">添加用户</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
			</div>
		</form>
	</blockquote>
	<table id="userList" lay-filter="userList"></table>

	<!-- <a class="layui-btn layui-btn-xs layui-btn-warm" lay-event="usable">已启用</a>-->
	<!--操作		这里的d是固定，可以通过d对象点属性获取到值-->
	<script type="text/html" id="userListBar">
		<a class="layui-btn layui-btn-primary layui-btn-xs" lay-event="auth"><i class="layui-icon"></i>独立权限</a>
		<a class="layui-btn layui-btn-warm layui-btn-xs" lay-event="setRole"><i class="layui-icon"></i>角色设置</a>
		<a class="layui-btn layui-btn-xs" lay-event="edit"><i class="layui-icon">&#xe642;</i>编辑</a>
		<a {{d.states == "1" ? "class='layui-btn layui-btn-xs' lay-event='usable'>已启用" : "class='layui-btn layui-btn-xs layui-btn-danger' lay-event='usable'>已禁用"}}</a>
		<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del"><i class="layui-icon">&#xe640;</i>删除</a>
	</script>
</form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/userList.js"></script>
</body>
</html>