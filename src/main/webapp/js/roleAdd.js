function selectAll(aa){
	//全选  
    $(aa).parent().parent().find("td").eq(1).find(":checkbox").prop("checked", true);
}

function unSelect(bb){
	//全不选
	$(bb).parent().parent().find("td").eq(1).find(":checkbox").prop("checked", false);
}
function reverse(cc){
	//反选 
	$(cc).parent().parent().find("td").eq(1).find(":checkbox").each(function(i,o){
		   $(o).prop("checked",!$(o).prop("checked"));
	  });
}

//提交
function getValue(){
  	var str = "";
  	var roleName = $("#demo").find("input[name='name']").val();
  	var roleDesc = $("#demo").find("input[name='desc']").val();
  	var btns = $("#demo").find("tr");   // 获得所有行
	for(var i = 2;i<btns.length;i++) //遍历所得的行，然后每行应选择器选中修改按钮，
	{
	  	obj = $(btns[i]).find("td").eq(1).find(":checkbox");
		 check_val = [];
		    for(k in obj){
		        if(obj[k].checked)
		            check_val.push(obj[k].value);
		    }
		    //alert($(btns[i]).find("td").eq(0).find("input[name='id']").val()+"="+check_val);
		    if(i == btns.length-1){
		    	str += $(btns[i]).find("td").eq(0).find("input[name='id']").val()+"="+check_val;
		    }else{
		    	str += $(btns[i]).find("td").eq(0).find("input[name='id']").val()+"="+check_val+"#";
		    }
	}
  	
  	$.ajax({
		    type: "POST",
            url:"add",
            data : {name:roleName,desc:roleDesc,"str":str},
            error: function() {
				alert("保存失败");
				layui.use(['layer','jquery'],function(){
				    var layer = layui.layer,
				        $ = layui.jquery;
				    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				    	parent.layer.close(index); //再执行关闭
				});
            },
            success: function() {
				alert("保存成功");
				layui.use(['layer','jquery'],function(){
				    var layer = layui.layer,
				        $ = layui.jquery;
				    	var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
				    	parent.layer.close(index); //再执行关闭
				});
	
            }
          }); 
  	
  	
  	
}

/*layui.use(['form','layer','upload','table'],function(){
    var form = layui.form
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery,
        laytpl = layui.laytpl,
        table = layui.table,
        upload = layui.upload,
        
      //获得项目名字path，在js中可以直接用path调用
        strFullPath = window.document.location.href,
      	strPath = window.document.location.pathname,
    	pos = strFullPath.indexOf(strPath),
    	prePath = strFullPath.substring(0, pos),
    	path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
        ;
});*/