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

<form:form class="layui-form" style="width:80%;" id="form2" method="POST" modelAttribute="menu">
<form:errors path="*"></form:errors>
	<c:if test="${menu.id != null }">
		<input type="hidden" name="_method" value="PUT"/>
		<input type="hidden" name="id" value="${menu.getId() }">
	</c:if>	
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">菜单名称</label>
		<div class="layui-input-block">
			<form:input path="name" class="layui-input"  lay-verify="required" placeholder="请输入菜单名"/>
			<!-- <input type="text" name="name" class="layui-input"  lay-verify="required" placeholder="请输入登录名"> -->
		</div>
	</div>
	
	
	<div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
		<label class="layui-form-label">菜单图标</label>
		<c:if test="${menu.id != null }">
		<div class="layui-input-inline">
			<button class="layui-btn" id="updatePic">
  				<!-- <i class="layui-icon">&#xe608;</i> 添加 -->
  				<i class="layui-icon" title="修改图标">&#xe642;</i> 修改
			</button>
			<i id="updatePicId" class="layui-icon" style="font-size: 30px; color: #1E9FFF;">${menu.getPic() }</i>
			<input type="hidden" name="pic" id="updatePicInput" value="${menu.getPic() }">
		</div>
		</c:if>
		<c:if test="${menu.id == null }">
			<div class="layui-input-inline" id="addButtonPic">
			<button class="layui-btn" id="addPic">
  				<i class="layui-icon" title="添加图标">&#xe608;</i> 添加
			</button>
			<i id="addPicId" class="layui-icon" style="font-size: 30px; color: #1E9FFF;"></i>
			<input type="hidden" name="pic" id="addPicInput">
			 
		</div>
		</c:if>
		
	</div>
	<div>
		<table border="1" id="lo" ></table>
	</div>
	
	<div class="magb15 layui-col-md4 layui-form-item layui-col-xs12">
		<label class="layui-form-label">父级菜单</label>
		<div class="layui-input-inline">
		<c:if test="${menu.id == null }">
			<form:select path="pid" id="pidSelect" items="${parentMenu }" itemLabel="name" itemValue="id"></form:select>
		</c:if>
		<c:if test="${menu.id != null }">
			<form:select path="pid" items="${parentMenu }" itemLabel="name" itemValue="id"></form:select>
		</c:if>
		</div>
	</div>
	
	<div class="layui-form-item layui-row layui-col-xs12">
		<label class="layui-form-label">菜单URL</label>
		<div class="layui-input-block">
			<form:input path="url" class="layui-input"  lay-verify="required" placeholder="请输入菜单url"/>
		</div>
	</div>	
		
	
	
	<div class="layui-form-item layui-row layui-col-xs12">
		<div class="layui-input-block">
			<c:if test="${menu.id == null }">
				<button class="layui-btn layui-btn-sm" lay-submit lay-filter="addMenu">立即添加</button>
			</c:if>
			<c:if test="${menu.id != null }">
				<button class="layui-btn layui-btn-sm" lay-submit lay-filter="updateMenu">确认修改</button>
			</c:if>
			<button type="reset" class="layui-btn layui-btn-sm layui-btn-primary">取消</button>
		</div>
	</div>
</form:form>
<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/page/user/menuAdd.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-2.1.0.js"></script>
<script type="text/javascript">
$(document).ready(function(){
	$("#pidSelect").val(0);
})
function openTable(aaa){
 	 $(aaa).css("background-color","#F08080");//还原所有td的颜色
   $('#lo').find("tr").find("td").not(aaa).css("background-color","#CDC9C9");//设置点击td的颜色
   var id = $(aaa).find('input').val();
   $.ajax({
		type: 'post',
		url:'<%=request.getContextPath() %>/menu/getOnePic',
		dataType : 'json',
		data: {"picId":id},
		success: function(onePic){
			for ( var i in onePic) {
				$("#addPicId").html(onePic[i].unicode);
				$("#addPicInput").val(onePic[i].unicode);
				
			}
		}
   });
     };
     
function openUpdateTable(bbb){
 	 $(bbb).css("background-color","#F08080");//还原所有td的颜色
   $('#lo').find("tr").find("td").not(bbb).css("background-color","#CDC9C9");//设置点击td的颜色
   var id = $(bbb).find('input').val();
   $.ajax({
		type: 'post',
		url:'<%=request.getContextPath() %>/menu/getOnePic',
		dataType : 'json',
		data: {"picId":id},
		success: function(onePic){
			for ( var i in onePic) {
				$("#updatePicId").html(onePic[i].unicode);
				$("#updatePicInput").val(onePic[i].unicode);
				
			}
		}
   });
     };
</script>
</body>
</html>