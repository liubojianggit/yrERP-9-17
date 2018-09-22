<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title></title>
    <link href="../../scripts/layui/css/layui.css" rel="stylesheet" />
    <script src="../../scripts/jquery-1.10.2.min.js"></script>
    <script src="../../scripts/layui/layui.js"></script>
    <script src="<%=request.getContextPath() %>/js/roleAdd.js"></script>
</head>
<body>
<button class="layui-btn layui-btn-xs" onclick="getValue()">提交</button>
	 <div id="demo" class="grid" >
    </div>
    <script id="view" type="text/html">
		<b>角色信息:</b><br>
		<spen>角色名称:</spen><input type="text" name="name"/><br>
		<spen>角色说明:</spen><input type="text" name="desc"/><br><br>
		<b>角色权限表:</b>
        <table class="layui-table">
            <thead>
                <tr>
                    <th>菜单名称</th>
                    <th>权限节点</th>
                    <th>操作</th>
                </tr>
            </thead>
            <tbody>
					
                {{# layui.each(d.rows,function(index,r){ }}
                <tr>
                    <td><i class="layui-icon">{{r.pic}}</i>{{r.name}}<input type="hidden" name="id" value="{{r.id}}"/></td>
                    <td>
					
					{{#  if(r.pid != 0){ }}
						{{# layui.each(d.str,function(index,p){ }}
							{{#  if(r.id == p.m_id){ }}
								{{#  if(p.url == r.url){ }}
								<input type="checkbox" value="{{p.id}}"/>列表
								{{#  } }}

								{{#  if(p.url == "add"){ }}
								<input type="checkbox" value="{{p.id}}"/>新增
								{{#  } }}

								{{#  if(p.url == "delete"){ }}
								<input type="checkbox" value="{{p.id}}"/>编辑
								{{#  } }}

								{{#  if(p.url == "update"){ }}
								<input type="checkbox" value="{{p.id}}"/>删除
								{{#  } }}
							{{#  } }} 
						{{# }); }}
					 {{#  } }} 
					{{#  if(r.pid == 0){ }}
						<p>{{r.url}}</p> 
					{{#  } }} 
					</td>
					{{#  if(r.pid != 0){ }}
						
						<td>
							{{# layui.each(d.str,function(index,p){ }}
								{{#  if(r.id == p.m_id){ }}
									<input type="button" value="全选" onclick="selectAll(this)">
									<input type="button" value="全不选" onclick="unSelect(this)">
									<input type="button" value="反选" onclick="reverse(this)">
								{{# return true; } }}
							{{# }); }}
						</td>
                	{{#  } }}
				</tr>
                {{# }); }}
            </tbody>
        </table>
    </script>
    <script>
        layui.config({
            base: '../../scripts/extend/'
        }).use(['laytpl', 'treegrid'], function () {
            var laytpl = layui.laytpl,
                treegrid = layui.treegrid;
            treegrid.config.render = function (viewid, data) {
                var view = document.getElementById(viewid).innerHTML;
                return laytpl(view).render(data) || '';
            };
			
            var id = 1;
            //var rows = [{"subMenuList":[],"createEmp":"111","createTime":{"date":21,"hours":21,"seconds":59,"month":5,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":30,"time":1529587859000,"day":4},"name":"权限管理","pid":0,"updateTime":{"date":18,"hours":21,"seconds":50,"month":6,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":58,"time":1531922330000,"day":3},"id":1,"pic":"&#xe672;","updateEmp":"刘柏江","url":"11"},{"subMenuList":[],"createEmp":"11","createTime":{"date":21,"hours":21,"seconds":25,"month":5,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":31,"time":1529587885000,"day":4},"name":"菜单管理","pid":1,"updateTime":{"date":21,"hours":21,"seconds":28,"month":5,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":31,"time":1529587888000,"day":4},"id":2,"pic":"&#xe60f;","updateEmp":"11","url":"menu"},{"subMenuList":[],"createEmp":"11","createTime":{"date":21,"hours":21,"seconds":51,"month":5,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":31,"time":1529587911000,"day":4},"name":"账户管理","pid":1,"updateTime":{"date":21,"hours":21,"seconds":55,"month":5,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":31,"time":1529587915000,"day":4},"id":3,"pic":"&#xe613;","updateEmp":"11","url":"userList"},{"subMenuList":[],"createEmp":"11","createTime":{"date":21,"hours":21,"seconds":23,"month":5,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":32,"time":1529587943000,"day":4},"name":"角色管理","pid":1,"updateTime":{"date":17,"hours":19,"seconds":21,"month":6,"nanos":0,"timezoneOffset":-480,"year":118,"minutes":59,"time":1531828761000,"day":2},"id":4,"pic":"","updateEmp":"刘柏江","url":"roleList"}];
            $.ajax({
    			type: 'get',
    			url: '<%=request.getContextPath() %>/menu/menuArray',//请求后台返回菜单的树形json
    			dataType : 'json',
    			success: function(data){
    				//再次请求后台，获取权限字符串
    				$.ajax({
    					type: "POST",
			            url:"<%=request.getContextPath() %>/permission/query",
						dataType : 'json',
			            error: function() {
				
			            },
			            success: function(str) {
							var tree1=treegrid.createNew({
					                elem: 'demo',
					                view: 'view',
					                data: { rows: data,"str" : str },
					                parentid: 'pid',
					                singleSelect: true,
					                
					            });
							
					            tree1.build();
				            
						}
    					
    				})
    				
   				 
    			}
    		});
        });
    </script>
</body>
</html>