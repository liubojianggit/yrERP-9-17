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

<form:form class="layui-form" style="width:80%;" id="form2" method="POST" modelAttribute="userBO">
<form:errors path="*"></form:errors>
<c:if test="${userBO.user.id != null }">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="hidden" name="id" value="${userBO.user.getId() }">
	</c:if>	
 <!-- 上传头像 -->               <!-- div居中 -->
<div class="layui-upload" align="center">
	<c:if test="${userBO.user.id != null }">
		<div class="layui-upload-list">
		<!-- 头像回显的样式，这里是圆形 -->
	    <img src="<%=request.getContextPath() %>/userTable/icons/"+${userBO.user.id } class="layui-upload-img layui-circle userFaceBtn userAvatar" style="width:200px;height:200px;" id="demo2">
	    <p id="demoText"></p>
	  	</div>
	  	<input type="hidden" name="headUrl" id="headUrl2" value=""/>
	  	<button type="button" class="layui-btn" id="test2">修改头像</button>
	</c:if>
	<c:if test="${userBO.user.id == null }">
		<div class="layui-upload-list">
		<!-- 头像回显的样式，这里是圆形 -->
	    	<img class="layui-upload-img layui-circle userFaceBtn userAvatar" style="width:200px;height:200px;" id="demo1">
	    	<p id="demoText"></p>
	  	</div>
	  		<button type="button" class="layui-btn" id="test1">上传头像</button>
	  		<!-- 上传头像成功后保存的隐藏框 -->
  			<input type="hidden" name="headUrl" id="headUrl" value="">
  	</c:if>
  
</div> 


	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">姓名</label>
		<div class="layui-input-block">
			<form:input path="name" class="layui-input"  lay-verify="required" placeholder="请输入登录名"/>
			<!-- <input type="text" name="name" class="layui-input"  lay-verify="required" placeholder="请输入登录名"> -->
		</div>
	</div>
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">工号</label>
		<div class="layui-input-block">
			<form:input path="empno" class="layui-input"  lay-verify="required" placeholder="请输入工号"/>
		</div>
	</div>
	<c:if test="${userBO.user.id == null }">
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">密码</label>
		<div class="layui-input-block">
			<form:input path="passwd" class="layui-input"  lay-verify="required" placeholder="请输入密码"/>
		</div>
	</div>
	</c:if>
	
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">电话</label>
		<div class="layui-input-block">
			<form:input path="phone" class="layui-input"  lay-verify="required" placeholder="请输入手机号码"/>
		</div>
	</div>
	
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">邮箱</label>
		<div class="layui-input-block">
			<form:input path="email" class="layui-input userEmail" lay-verify="email" placeholder="请输入邮箱"/>
		</div>
	</div>
		<div class="magb15 layui-col-md4 layui-col-xs12">
			<label class="layui-form-label">性别</label>
			<div class="layui-input-block">
				<!-- 单选框格式写法，所有name的值都是一样的，其中checked是默认值  -->
				<!-- <input type="radio" name="sex" value="男" title="男" checked>
				<input type="radio" name="sex" value="女" title="女">
				<input type="radio" name="sex" value="保密" title="保密"> -->
				<form:radiobuttons path="sex" items="${sexs }"/>
			</div>
		</div>
		<!-- layui-form-item 换行 -->
		<div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
		<label class="layui-form-label">账号状态</label>
		<div class="layui-input-inline">
			<!-- <select name="status" class="userStatus">
					<option value="0">不启用</option>
					<option value="1">启用</option>
				</select> -->
			<form:select path="status" items="${statusMap }"></form:select>
		</div>
	</div>
		<div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
		<label class="layui-form-label">地址</label>
		<div class="layui-input-inline">
			<!-- <select name="addr" class="userGrade">
					<option value="0">广西</option>
					<option value="1">广东</option>
					<option value="2">湖南</option>
					<option value="3">湖北</option>
					<option value="4">上海</option>
				</select> -->
			<form:select path="addr" items="${addrProvinces }" itemLabel="name" itemValue="id"></form:select>
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
<script type="text/javascript" src="<%=request.getContextPath() %>/page/user/userAdd.js"></script>
</body>
</html>