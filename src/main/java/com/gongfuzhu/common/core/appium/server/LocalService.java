package com.gongfuzhu.common.core.appium.server;

import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;
import java.net.URL;


@Log4j2
@Data
public class LocalService {






    private AppiumDriverLocalService service;

    LocalService(){
        try {

            AppiumServiceBuilder builder = new AppiumServiceBuilder();
//        builder.withAppiumJS(new File("C:\\Users\\81461\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
            builder.usingAnyFreePort();
            builder.withArgument(GeneralServerFlag.LOG_LEVEL, "error");
//        builder.withLogFile(new File(System.getProperty("user.dir")+File.separator+"logs"+File.separator+"appiumServer.log"));
            this.service=AppiumDriverLocalService.buildService(builder);
            log.info("启动AppiumServer.........");
            this.service.start();
        }catch (Exception e){
            log.info("AppiumDriverLocalService 启动失败");
            e.printStackTrace();

        }



    }




    public void stopServer() {
        log.info("尝试关闭appiumServer");
        if (service != null) {
            service.stop();
            log.info("关闭AppiumServer");
        }
    }

    public URL getUrl() {

        if (service!= null){

            return service.getUrl();
        }
        return null;
    }


}
