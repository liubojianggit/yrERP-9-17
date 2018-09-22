package com.yr.util;

import com.yr.entitys.user.User;
import com.yr.log.service.LogService;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.Table;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Aspect
public class LogAspect {
    public  Integer id = null;

    @Autowired
    private LogService logServiceImpl;

    /**
     * 登录方法切入点
     */
    @Pointcut(value = "execution(* com.yr.login.service.*.login(..))")
    public void loginCell()
    {

    }

    @AfterReturning(value = "loginCell()",argNames = "object",returning = "object")
    public  void loginLog(JoinPoint joinPoint,Object object)
    {
        User user = (User) object;

        if(user==null)
        {
            return;
        }
        //没有参数；
        if (joinPoint.getArgs() == null)
        {
            return;
        }

        //根据用户对象，获取id;
        id = user.getId();

        //获取方法名(获取操作类型);
        String methodName = joinPoint.getSignature().getName();

        //获取操作内容
        String opContent = optionContent(joinPoint.getArgs(),methodName);

        //String tableName = getTableName(joinPoint.getClass());

    }

    /**
     * 使用Java反射来获取被拦截方法(insert、update)的参数值， 将参数值拼接为操作内容
     *
     * @param args
     * @param mName
     * @return
     */
    public String optionContent(Object[] args, String name)
    {
        if (args == null)
        {
            return null;
        }
        StringBuffer rs = new StringBuffer();
        rs.append(name);
        String className = null;
        int index = 1;
        for (Object info : args) {
            // 获取对象类型
            className = info.getClass().getName();
            className = className.substring(className.lastIndexOf(".") + 1);
            rs.append("[参数" + index + "，类型:" + className + "，值:");
            // 获取对象的所有方法
            Method [] methods = info.getClass().getDeclaredMethods();
            // 遍历方法，判断get方法
            for (Method method : methods) {
                String methodName = method.getName();
                // 判断是不是get方法
                if (methodName.indexOf("get") == -1) {// 不是get方法
                    continue;// 不处理
                }
                Object rsValue = null;
                try {
                    // 调用get方法，获取返回值
                    rsValue = method.invoke(info);
                } catch (Exception e) {
                    continue;
                }
                // 将值加入内容中
                rs.append("(" + methodName + ":" + rsValue + ")");
            }
            rs.append("]");
            index++;
        }
        return rs.toString();
    }

    /**
     * 通过获取类上的@Table注解获取表名称
     *
     * @param clazz
     * @return
     */
    public static Map<String, String> getTableName(Class<?> clazz) {
        Map<String, String> map = new ConcurrentHashMap<>();
        Table annotation = clazz.getAnnotation(Table.class);
        String name = annotation.name();
        String className = clazz.getSimpleName();
        map.put("tableName", name);
        map.put("className", className);
        return map;
    }
}
