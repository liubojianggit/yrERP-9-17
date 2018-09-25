layui.use(['form','layer','table','laytpl'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        
        //获得项目名字path，在js中可以直接用path调用
        strFullPath = window.document.location.href,
      	strPath = window.document.location.pathname,
    	pos = strFullPath.indexOf(strPath),
    	prePath = strFullPath.substring(0, pos),
    	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
    
    
    ;

    //用户列表
    var tableIns = table.render({
        elem: '#userList',
        url :path+ 'u_user/userTable/list',
        cellMinWidth : 95,
        page : true,
        height : "full-125",
        limits : [10,25,50,100],
        limit : 10,
        id : "userListTable",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            /*		对应实体类的属性			表头x*/
            {type:'numbers',title:'编号',width:50},
            {field: 'user.name', title: '用户名', align:"center",unresize: true},
            /*这里获取的只是头像的路径，但是在前台是需要显示图片的，所以对headUrl进行处理，如果返回的数据需要处理都是用templet:function(d){ return '处理的数据' } */
            {field: 'user.photo', title: '头像',  align:'center',templet:function(d){
                return '<img style="width: 28px;height: 28px;"  src="'+path+"/userTable/icons/"+d.id+'"  class="layui-upload-img layui-circle userFaceBtn userAvatar"/>';
            }},

            {field: 'user.jobNum', title: '工号', align:"center", unresize: true},
            {field: 'user.depaCode', title: '部门编号', align:"center", unresize: true},
            {field: 'user.sex', title: '性别', align:"center", unresize: true},
            {field: 'user.birthday', title: '生日', align:"center", unresize: true},
            {field: 'user.phoneNumber', title: '电话', align:"center", unresize: true},
            {field: 'addr', title: '地址', align:"center", unresize: true},
            //{field: 'status', title: '状态', align:"center", unresize: true},
            {field: 'user.status', title: '用户状态',  align:'center',templet:function(d){
            	d.status == "0" ? $("#abc").text("禁用") : $("#abc").text("启用");
                return d.status == "0" ? "限制使用" : "正常使用";
            }},
            {title: '操作', minWidth:386, templet:'#userListBar',fixed:"right",align:"center"}
        ]]
    });

    //搜索【此功能需要后台配合，所以暂时没有动态效果演示】
    $(".search_btn").on("click",function(){
        if($(".searchVal").val() != ''){
            table.reload("newsListTable",{
                page: {
                    curr: 1 //重新从第 1 页开始
                },
                where: {
                    "user.name": $("#userName").val(),  //搜索的关键字
                    "user.depaCode": $("#depaCode").val(),  //搜索的关键字
                    "minAge": $("#minAge").val(),  //搜索的关键字
                    "maxAge": $("#maxAge").val() //搜索的关键字
                }
            })
        }else{
            layer.msg("请输入搜索的内容");
        }
    });

    //添加用户
    function addUser(){
    	//window.location.href = "user/add";
    	
    	
        var index = layui.layer.open({
            title : "添加用户",
            type : 2,
            content : path+"u_user/userTable/add",//发送请求
            end: function(){
                window.location.href='<%=request.getContextPath() %>/u_user/userTable';
            }
        })
        layui.layer.full(index);
        window.sessionStorage.setItem("index",index);
        //改变窗口大小时，重置弹窗的宽高，防止超出可视区域（如F12调出debug的操作）
        $(window).on("resize",function(){
            layui.layer.full(window.sessionStorage.getItem("index"));
        })
    }
    $(".addNews_btn").click(function(){
        addUser();
    })

    //批量删除
    $(".delAll_btn").click(function(){
        var checkStatus = table.checkStatus('userListTable'),
            data = checkStatus.data,
            newsId = [];
        if(data.length > 0) {
            for (var i in data) {
                newsId.push(data[i].newsId);
            }
            layer.confirm('确定删除选中的用户？', {icon: 3, title: '提示信息'}, function (index) {
                // $.get("删除文章接口",{
                //     newsId : newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                tableIns.reload();
                layer.close(index);
                // })
            })
        }else{
            layer.msg("请选择需要删除的用户");
        }
    })

    //列表操作
    table.on('tool(userList)', function(obj){
        var layEvent = obj.event,
            data = obj.data;
        if(layEvent === 'edit'){ //编辑
            //addUser(data);
            window.location.href = path+"u_user/userTable/"+data.id;
            
        }else if(layEvent === 'auth'){
        	layer.open({
       		 id: 'LAY_indepAuth', //设定一个id，防止重复弹出
                type: 2,
                title:'添加独立权限',
                area: ['800px','500px'],//宽、高
                content: path+'user/gotoIndePermission/'+data.id,//跳到用户独立权限页面
                end : function(index, layero){ 
               	 tableIns.reload("userList",{
                         page: {
                             curr: 1 //重新从第 1 页开始
                         }
                     })
               	}  
       		});
        }else if(layEvent === 'setRole'){//角色设置
        	//Ajax请求，动态控制角色弹出层的回显
        	$.post(path+'role/queryRoleJson', {}, function(str){//获取后台角色列表所有角色,后台返回的json字符串str
            	$.post(path+'user/queryUserRoleJson', {"uid":data.id}, function(uRoleStr){//根据用户id查询用户所拥有的角色,后台返回的json字符串uRoleStr
        		var roleStrTd ="";//弹出层td
        		var roleStrTr = "";	//弹出层tr
        		var checkedStr = "";//控制复选框默认值的标识
        		var temp = 0;
        		for (var i = 0; i < str.length; i++){
        			temp++;
        			for (var j = 0; j < uRoleStr.length; j++){
						if(str[i].id == uRoleStr[j].id){
							checkedStr = "checked='checked'";//因使用了弹出层样式1，拼接类的html标签都必须这样来给标签赋属性，并且靠多个变量/标识来控制需求
						}
        			}
					roleStrTd += "<td style='width: 120px;'><input type='checkbox'"+checkedStr+"value='"+str[i].id+"'/>"+str[i].name+"</td>";
					//初始化checkedStr，目的是为了下一层的循环接着使用时不会出错
					checkedStr = "";
        			if(temp%3 == 0){//取模控制弹出层表格的列数，这里是一行三个列
						roleStrTr += "<tr>"+roleStrTd+"</tr>";
						//因为执行到这里，已经把roleStrTd的内容追加给了roleStrTr,必须初始化roleStrTd，目的是为了实现弹出层表格列数的控制
						roleStrTd = "";
					}
				}
        		//角色的弹出层
	        	layer.open({
	          		 id: 'LAY_updateRole', //设定一个id，防止重复弹出
	                   type: 1,
	                   title:'角色设置',
	                   btnAlign: 'c',
	                   area: ['400px','230px'],//宽、高
	                   content: "<b>角色名称:</b><br><table align='center'>"+roleStrTr+"</table>",
	                   btn: ['确认', '取消']
	           	  	   ,yes: function(index, layero){//layero就是弹出层的html对象
		           	    //按钮【按钮一】的回调
		           		var userRoleStr = data.id+"#";
		           		var obj = $(layero).find("table").find("tr").find(":checkbox");
		           		 for(var k in obj){
		           			if(obj[k].checked){
		           				userRoleStr += obj[k].value+",";
		           			}
		           		  }
		           		  $.ajax({
		             			type: 'post',
		             			url: path+'user/insertUserRole',//后台往user_role表中插入数据
		             			dataType : 'json',
		             			data: {"str":userRoleStr},//userRoleStr是用户拥有的角色的id拼起来的字符串
		             			success: function(str){
		             				
		             				if("0" == str.code){
		             					top.layer.close(index);
		             					tableIns.reload("roleList",{
		             		                  page: {
		             		                      curr: 1 //重新从第 1 页开始
		             		                  }
		             		              })
		             					setTimeout(top.layer.msg(str.msg,{icon:1}),1000); 
		             				}else if("1" == str.code){
		             					top.layer.close(index);
		             					tableIns.reload("roleList",{
		             		                  page: {
		             		                      curr: 1 //重新从第 1 页开始
		             		                  }
		             		              })
		             					setTimeout(top.layer.msg(str.msg,{icon:1}),1000); 
		             				}else{
		             					top.layer.close(index);
		             					tableIns.reload("roleList",{
		             		                  page: {
		             		                      curr: 1 //重新从第 1 页开始
		             		                  }
		             		              })
		             					setTimeout(layer.msg("未知错误，请联系管理员",{icon:2}),1000); 
		             				}
		             			}
		             		});
	           	  	
	           	  }
	           	  ,btn2: function(index, layero){
	           	    //按钮【按钮二】的回调
   					tableIns.reload("roleList",{//刷新指定id的列表
   		                  page: {
   		                      curr: 1 //重新从第 1 页开始
   		                  }
   		              })
       		        	
	           	  }
	           	  ,cancel: function(){ 
	           	    //右上角关闭回调
	           		tableIns.reload("roleList",{//刷新指定id的列表
 		                  page: {
 		                      curr: 1 //重新从第 1 页开始
 		                  }
 		              })
	           	    //return false 开启该代码可禁止点击该按钮关闭
	           	  }
	          	});
        	});
        	});
           }else if(layEvent === 'usable'){ //启用禁用
            var _this = $(this),
            usableText = "是否确定禁用此用户？",
            btnText = "已禁用";
        if(_this.text()=="已禁用"){
            usableText = "是否确定启用此用户？",
            btnText = "已启用";
        }
        layer.confirm(usableText,{
            icon: 3,
            title:'系统提示',
            cancel : function(index){
                layer.close(index);
            }
        },function(index){
            _this.text(btnText);
            layer.close(index);
        },function(index){
            layer.close(index);
        });
        }else if(layEvent === 'del'){ //删除
            layer.confirm('确定删除此用户？',{icon:3, title:'提示信息'},function(index){
            	tableIns.reload();
                layer.close(index);
            	$.ajax({
        			type: 'post',
        			url: path+'u_user/userTable',//请求登录验证接口
        			dataType : 'json',
        			data: {id:obj.data.id,_method:'delete'},
        			success: function(data){
        				
        				if("0" == data.code){
        					layer.msg("删除用户失败",{icon:2});
        				}else if("1" == data.code){
        					layer.msg("删除成功",{icon:2});
        					window.location.href = path+"userList";
                            
        				}else{
        					layer.msg("未知错误，请联系管理员",{icon:2});
        				}
        				/*layer.closeAll("iframe");
						//刷新父页面
						parent.location.reload();*/
        			}
        		});
            	
            	
            	
                // $.get("删除文章接口",{
                //     newsId : data.newsId  //将需要删除的newsId作为参数传入
                // },function(data){
                //    tableIns.reload();
                 //   layer.close(index);
                // })
            	return false;
            });
        }
    });

})