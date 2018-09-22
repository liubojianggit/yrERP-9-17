<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- 引入form标签的标签库 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link href="<%=request.getContextPath() %>/scripts/layui/css/layui.css" rel="stylesheet" />
    <script src="<%=request.getContextPath() %>/scripts/jquery-1.10.2.min.js"></script>
    <script src="<%=request.getContextPath() %>/scripts/layui/layui.js"></script>
</head>
<script type="text/javascript">

	//提交
	function getValue(roleId){
	  	var str = roleId+"#";
	  	var btns = $("#demo").find("tr");   // 获得所有行
		for(var i = 2;i<btns.length;i++) //遍历所得的行，然后每行应选择器选中修改按钮，
		{
		  	obj = $(btns[i]).find("td").eq(1).find(":checkbox");
			    for(k in obj){
			        if(obj[k].checked)
			        str += obj[k].value+",";
			    }
			    
		}
	  	alert(str);
	  	
	  	$.ajax({
			    type: "POST",
	            url:"<%=request.getContextPath()%>/permission/updateRole_PermissionTable",
	            data : {"str":str},
	            error: function(request) {
		
	            },
	            success: function() {
		
	            }
	          });
	  	
	  	
	  	
	}
	
	function updateMenu(up){
		//alert($(up).parent().parent().find("td").find("input").val());
		window.location.href ="<%=request.getContextPath() %>/menu/update/"+$(up).parent().parent().find("td").find("input").val();
	}
	function deleteMenu(de){
		//alert($(de).parent().parent().find("td").find("input").val());
        	$.ajax({
    			type: 'post',
    			url: '<%=request.getContextPath() %>/menu/menuTable',//请求登录验证接口
    			dataType : 'json',
    			data: {id:$(de).parent().parent().find("td").find("input").val(),_method:'delete'},
    			success: function(data){
    				
    				if("0" == data.code){
        				alert("删除用户失败");
    				}else if("1" == data.code){
    					alert("删除成功");
                        
    				}else{
    					alert("未知错误，请联系管理员");
    				}
    			}
    		});
	}
	
</script>
<body>
<br>
	<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" class="layui-input searchVal" placeholder="请输入搜索的内容" />
				</div>
				<a class="layui-btn search_btn" data-type="reload">搜索</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-normal addNews_btn" href = "<%=request.getContextPath() %>/menu/add">添加菜单</a>
			</div>
			<div class="layui-inline">
				<a class="layui-btn layui-btn-danger layui-btn-normal delAll_btn">批量删除</a>
			</div>
    <div id="demo" class="grid">
    </div>
    <script id="view" type="text/html">
        <table class="layui-table">
            <thead>
                <tr>
                    <th>名称</th>
                    <th>图标</th>
					<th>url</th>
					<th>创建人</th>
					<th style="width: 50px;">操作</th>
                </tr>
            </thead>
            <tbody>
					
                {{# layui.each(d.rows,function(index,r){ }}
                <tr>
                    <td>{{r.name}}<input type="hidden" name="id" value="{{r.id}}"/></td>
					<td><i class="layui-icon">{{r.pic}}</i></td>
					<td>{{r.url}}</td>
					<td>{{r.createEmp}}</td>
					<td style="width: 50px;"><button class="layui-btn" onclick="updateMenu(this)"><i class="layui-icon">&#xe642;</i> 修改</button>
						<button class="layui-btn" onclick="deleteMenu(this)"><i class="layui-icon">&#xe640;</i> 删除</button>
					</td>
				</tr>
                {{# }); }}
            </tbody>
        </table>
    </script>
    <script>
    layui.config({
        base: '<%=request.getContextPath() %>/scripts/extend/'
    }).use(['laytpl', 'treegrid'], function () {
        var laytpl = layui.laytpl,
            treegrid = layui.treegrid;
        treegrid.config.render = function (viewid, data) {
            var view = document.getElementById(viewid).innerHTML;
            return laytpl(view).render(data) || '';
        };
        $.ajax({
			type: 'get',
			url: '<%=request.getContextPath() %>/menu/menuTable',//请求后台返回菜单的树形json
			dataType : 'json',
			success: function(data){
				//这个属性的控件必须放在这里，我也不知道为什么
				var tree1=treegrid.createNew({
			              elem: 'demo',
			              view: 'view',
			              data: { rows: data},
			              parentid: 'pid',
			              singleSelect: true,
			                
			          });
			          tree1.build();
			}
					
				
		});
    });
    </script>
</body>
</html>