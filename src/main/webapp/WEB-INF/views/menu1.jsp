<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/layui/css/layui.css"/>
    <script type="text/javascript" src="<%=request.getContextPath() %>/layui/layui.js"></script>
</head>
<script type="text/javascript">
    layui.config({
        base: '<%=request.getContextPath() %>/js/',
    })
    layui.use(['iconPicker'], function () {
        var iconPicker = layui.iconPicker;

        iconPicker.render({
            // 选择器，推荐使用input
            elem: '#iconPicker',
            // 数据类型：fontClass/unicode，推荐使用fontClass
            type: 'fontClass',
            // 是否开启搜索：true/false
            search: true,
            // 点击回调
            click: function (data) {
                console.log(data);
            }
        });

        /**
         * 选中图标 （常用于更新时默认选中图标）
         * @param filter lay-filter
         * @param iconName 图标名称，自动识别fontClass/unicode
         */
        iconPicker.checkIcon('iconPicker', 'layui-icon-star-fill');

    });

</script>
<body class="layui-layout-body" style="overflow:auto; padding: 10px">
    <input type="text" id="iconPicker" lay-filter="iconPicker" class="layui-input">
</body>
</html>