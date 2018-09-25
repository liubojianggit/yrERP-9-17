<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %><!-- 引入form标签的标签库 -->
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta charset="UTF-8">
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta http-equiv="Access-Control-Allow-Origin" content="*">
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="format-detection" content="telephone=no">
    <title>菜单主页</title>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/layui/css/layui.css"/>
    <style type="text/css">
        body{padding: 10px 30px;}
        .hide{display:none}
    </style>
</head>
<body>
<span class='layui-icon'>&#xe602;</span>           <span class='layui-icon'>&#xe61a;</span>
<button class="layui-btn layui-btn-primary up-all">全部收起</button>
<button class="layui-btn layui-btn-primary down-all">全部展开</button>

<div class="test-tree-table" lay-filter="test1"></div>

<script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery-2.1.0.js"></script>
<script type="text/html" id="barDemo">
    <a class="layui-btn layui-btn-xs" lay-event="edit">编辑</a>
    <a class="layui-btn layui-btn-danger layui-btn-xs" lay-event="del">删除</a>
</script>
<script type="text/javascript">
    /* $(document).ready(function () {
         alert(11111111);

     })*/
    $.ajax({
        type: 'get',
        url: '<%=request.getContextPath() %>/menu/menuTable/list',//请求后台返回菜单的树形json
        dataType : 'json',
        success: function(data){
            layui.config({
                base: '<%=request.getContextPath() %>/js/',
            })
            layui.use(['treetable','form','layer'],function(){
                var $ = layui.$,treetable = layui.treetable;
                treetable.render({
                    elem: '.test-tree-table',
                    data: data,
                    field: 'title',
                    cols: [
                        {
                            field: 'title',
                            title: '菜单名',
                            width: '15%',
                        },
                        {
                            field: 'href',
                            title: 'url',
                            width: '20%',
                        },
                        {
                            field: 'icon',
                            title: '菜单图标',
                            width: '5%',
                        },
                        {
                            field: 'createEmp',
                            title: '创建人',
                            width: '10%',
                        },
                        {
                            field: 'createTime',
                            title: '创建时间',
                            width: '20%',
                        },
                        {
                            field: 'actions',
                            title: '操作',
                            width: '150',
                            data: [
                                '<a lay-filter="add" onclick="a(this)">添加</a>','<a lay-filter="edit">编辑</a>',
                            ],
                        },
                    ]
                });

                // treetable.on('treetable(edit)',function(data){
                //     console.dir(data);
                //     layui.layer.msg(data);
                // })

                //
                // treetable.on('treetable(test1)',function(data){
                //     console.log(o(data.elem).html());
                //     //console.dir(o(data.elem).html());
                // })
                //
                // treetable.on("click", "td [lay-filter='remove']",function(data){
                //     //alert(data.item.id+" == "+data.item.title);
                //     alert(1111);
                //     console.dir(data);
                // })
                //
                // treetable.on("click", "td [lay-filter='edit']",function(data){
                //     alert(data.item.id+" == "+data.item.title);
                //     console.dir(data);
                // })


                /* treetable.on('treetable(test1)',function(data){
                     alert(o(data.elem).html());
                 })*/

                /* treetable.on('treetable(add)',function(data){
                     console.dir(data);
                 })*/

                /*treetable.on('treetable(edit)',function(data){
                    alert(data.item.id+" == "+data.item.title);
                    console.log(data);
                })*/

                /* treetable.on('treetable(delete)',function(data){
                     alert(data);

                 })*/

                $('.up-all').click(function(){
                    treetable.all('up');
                })

                $('.down-all').click(function(){
                    treetable.all('down');
                })
            })
        }
    });
    function a(obj){
        alert(obj.html());
        // var add=obj.find('a[lay-filter=“add”]');

    }
</script>
</body>
</html>