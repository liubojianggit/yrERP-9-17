layui.use(['table'],function(){
	var table = layui.table;

	/*lin  添加*/
    //获得项目名字path，在js中可以直接用path调用
	strFullPath = window.document.location.href,
    strPath = window.document.location.pathname,
        pos = strFullPath.indexOf(strPath),
        prePath = strFullPath.substring(0,pos),
        path = strPath.substring(0, strPath.substr(1).indexOf('/') + 1)+"/";
	;

	//系统日志
    table.render({
        elem: '#logs',
        url : path+'/log/logTable/list',
        cellMinWidth : 95,
        page : true,
        height : "full-20",
        limit : 20,
        limits : [10,15,20,25],
        id : "systemLog",
        cols : [[
            {type: "checkbox", fixed:"left", width:50},
            {field: 'id', title: '序号', width:60, align:"center"},
            {field: 'url', title: '请求地址', width:350},
            {field: 'method', title: '操作方式', align:'center',templet:function(d){
                if(d.method.toUpperCase() == "GET"){
                    return '<span class="layui-blue">'+d.method+'</span>'
                }else{
                    return '<span class="layui-red">'+d.method+'</span>'
                }
            }},
            {field: 'ip', title: '操作IP',  align:'center',minWidth:130},
            {field: 'timeConsuming', title: '耗时', align:'center',templet:function(d){
                return '<span class="layui-btn layui-btn-normal layui-btn-xs">'+d.timeConsuming+'</span>'
            }},
            {field: 'isAbnormal', title: '是否异常', align:'center',templet:function(d){
                if(d.isAbnormal == "正常"){
                    return '<span class="layui-btn layui-btn-green layui-btn-xs">'+d.isAbnormal+'</span>'
                }else{
                    return '<span class="layui-btn layui-btn-danger layui-btn-xs">'+d.isAbnormal+'</span>'
                }
            }},
            {field: 'createEmp',title: '操作人', minWidth:100, templet:'#newsListBar',align:"center"},
            {field: 'createTime', title: '操作时间', align:'center', width:170}
        ]]
    });
 	
})
