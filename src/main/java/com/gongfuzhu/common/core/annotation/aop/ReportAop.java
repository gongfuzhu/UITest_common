package com.gongfuzhu.common.core.annotation.aop;

import com.gongfuzhu.common.core.annotation.Report;
import com.gongfuzhu.common.core.selenium.SeleniumListener;
import com.gongfuzhu.common.core.tools.JarTool;
import lombok.extern.log4j.Log4j2;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.stereotype.Component;

import java.io.File;
import java.lang.reflect.Method;
import java.util.UUID;

@Component
@Aspect
@Log4j2
public class ReportAop {
    private ThreadLocal<String> batchThreadLocal = ThreadLocal.withInitial(() -> null);



    @Pointcut(value = "@annotation(com.gongfuzhu.common.core.annotation.Report)")
    public void point() {
    }

    @Around("point()")
    public Object doAround(ProceedingJoinPoint pjp) {

        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        if (method == null) {
            return null;
        }

        Report annotation = method.getAnnotation(Report.class);

        String jarDir =System.getProperty("user.dir")+File.separator+annotation.fileName();

        long delayTime = annotation.delayTime();
        SeleniumListener seleniumListener = new SeleniumListener();
        EventFiringWebDriver eventFiringWebDriver =null;
        Object[] args = pjp.getArgs();

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof WebDriver){
                seleniumListener.setTime(delayTime);
                seleniumListener.setScreenshot(true);
                seleniumListener.setSavePath(jarDir);
                eventFiringWebDriver = new EventFiringWebDriver((WebDriver) args[1]);
                eventFiringWebDriver.register(seleniumListener);
                args[i]=eventFiringWebDriver;

            }
        }

        String s = pjp.getThis().toString();
        String[] split = s.split("@");
//        for (Object arg : pjp.getArgs()) {
//
//        }


//
        log.info("方法名：{}", signature.toString());
//
//
        log.info("方法名称：{}",split[0]);
//
//
//        log.info("请求数据：{}",JsonUtil.toJson(pjp.getArgs()));

        Object proceed = null;

        try {
            proceed = pjp.proceed(args);
        } catch (Throwable e) {
            e.printStackTrace();
            log.info("出现异常！！！");
        }

        if (null != eventFiringWebDriver){

            eventFiringWebDriver.unregister(seleniumListener);
        }

        return proceed;

    }

//    public void resetNo(){
////        if ( null !=no ){
////            no =null;
////
////        }
//
//        if (null != batchThreadLocal.get()){
//            batchThreadLocal.remove();
//        }
//    }


//    public void colseNoThreea(){
//        if (null != noThread.get()){
//            noThread.remove();
//        }
//    }
}
