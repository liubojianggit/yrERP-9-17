layui.use(['form','layer','jquery'],function(){
    var form = layui.form,
        layer = parent.layer === undefined ? layui.layer : top.layer,
        $ = layui.jquery;

    $(".loginBody .seraph").click(function(){
        layer.msg("这只是做个样式，至于功能，你见过哪个后台能这样登录的？还是老老实实的找管理员去注册吧",{
            time:5000
        });
    })

    //登录按钮
    form.on("submit(login)",function(data){
        //$(this).text("登录中...").attr("disabled","disabled").addClass("layui-disabled");
        /*setTimeout(function(){
            window.location.href = "../../index.html";
        },1000);*/
    	 

    	//$.post(); //一般在这里发送ajax
    	
    	//登录请求
    	  $.ajax({
			type: 'post',
			url: '/u_user/userTable/login',//请求登录验证接口
			dataType : 'json',
			data: $('#form1').serialize(),
			success: function(str){
				if("0" == str){
					alert("账号密码错误，登录失败");
				}else if("1" == str){
					window.location.href = "index";
				}else if("2" == str){
					alert("账号未激活，禁止使用");
				}else if("3" == str){
					alert("验证码输入错误");
				}else if("4" == str){
					alert("请输入验证码");
				}else if("5" == str){
					alert("账号冲突");
				}else if("6" == str){
					alert("账号不存在");
				}else{
					alert("未知错误，请联系管理员");
				}
			}
		});

    	  return false; //阻止表单跳转。如果需要表单跳转，去掉这段即可。
    })

    //表单输入效果
    $(".loginBody .input-item").click(function(e){
        e.stopPropagation();
        $(this).addClass("layui-input-focus").find(".layui-input").focus();
    })
    $(".loginBody .layui-form-item .layui-input").focus(function(){
        $(this).parent().addClass("layui-input-focus");
    })
    $(".loginBody .layui-form-item .layui-input").blur(function(){
        $(this).parent().removeClass("layui-input-focus");
        if($(this).val() != ''){
            $(this).parent().addClass("layui-input-active");
        }else{
            $(this).parent().removeClass("layui-input-active");
        }
    })
})