package com.example.administrator.aop_aspectj.aspect;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.example.administrator.aop_aspectj.LoginActivity;
import com.example.administrator.aop_aspectj.MyApp;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;

//切面
@Aspect
public class LoginAapect {

    //切入点 ("execution(@注解全类名 * *(..)")
    @Pointcut("execution(@com.example.administrator.aop_aspectj.annotation.LoginTrace *  *(..))")
    public void methodAnnotationWithLoginTrace() {
    }


    //切入点逻辑处理（可以对 切入点 前处理 后处理  前后处理 ）
    //参数--->切入点方法名()--->methodAnnotationWithLoginTrace()
//    @After("methodAnnotationWithLoginTrace()")//切入点后运行 --》使用注解LoginTrace的方法运行完后执行这个方法代码
//    @Before("methodAnnotationWithLoginTrace()")//切入点前运行
    @Before("methodAnnotationWithLoginTrace()")//切入点前 后 都运行(后运行是在ProceedingJoinPoint对象非空时运行)
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        Log.e("zdh", "-------------前");


        if (!MyApp.getInstance().isLogin()){
            Context context = MyApp.getInstance().getApplicationContext();
            context.startActivity(new Intent(context,LoginActivity.class));
        }
        Object proceed=null;
        if (joinPoint != null) {
            //如果使用Around注解需要返回proceed对象，要不然注解方法里面代码不执行，其他返回null
             proceed = joinPoint.proceed();
            Log.e("zdh", "-------------后");
        }

        return proceed;
    }


}
