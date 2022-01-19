package com.gongfuzhu.common.core.annotation.aop;

import com.gongfuzhu.common.core.annotation.Report;
import com.gongfuzhu.common.core.selenium.SeleniumListener;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.catalina.manager.util.SessionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

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

        String jarDir = System.getProperty("user.dir") + File.separator + annotation.fileName();

        long delayTime = annotation.delayTime();
        boolean screnShot = annotation.screnShot();
        String scene = annotation.scene();
        SeleniumListener seleniumListener = null;
        EventFiringWebDriver eventFiringWebDriver = null;
        Object[] args = pjp.getArgs();

        for (int i = 0; i < args.length; i++) {
            if (args[i] instanceof WebDriver) {
                seleniumListener = new SeleniumListener();
                seleniumListener.setTime(delayTime);
                seleniumListener.setScreenshot(screnShot);
                seleniumListener.setSavePath(jarDir);
                eventFiringWebDriver = new EventFiringWebDriver((WebDriver) args[i]);
                eventFiringWebDriver.register(seleniumListener);
                args[i] = eventFiringWebDriver;
            }
        }


        Object proceed = null;

        try {
            proceed = pjp.proceed(args);
        } catch (Throwable e) {
            e.printStackTrace();
            result(jarDir,String.format("场景：%s 执行失败 ",scene));
            return proceed;
        }finally {
            if (null != eventFiringWebDriver) {
                eventFiringWebDriver.unregister(seleniumListener);
            }
        }

        result(jarDir,String.format("场景：%s 执行成功 ",scene));

        return proceed;

    }


    @SneakyThrows
    private void result(String filePath,String text){

        StringBuffer stringBuffer = new StringBuffer(text);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat();
        String format = simpleDateFormat.format(new Date());
        stringBuffer.append(format);
        File file = new File(filePath);
        file.mkdirs();
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file.getPath()+File.separator+"result.txt", true));
        bufferedWriter.newLine();
        bufferedWriter.write(stringBuffer.toString());
        bufferedWriter.close();
    }

}
