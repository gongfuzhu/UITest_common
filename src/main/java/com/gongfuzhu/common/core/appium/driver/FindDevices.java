package com.gongfuzhu.common.core.appium.driver;

import com.gongfuzhu.common.core.appium.DriverModel;
import com.gongfuzhu.common.core.appium.android.tool.ADBUtil;
import com.gongfuzhu.common.core.appium.driver.DriverFactory;
import com.gongfuzhu.common.core.appium.driver.manager.GeneralCapabilite;
import com.gongfuzhu.common.core.appium.server.LocalService;
import lombok.extern.log4j.Log4j2;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.File;
import java.util.*;

@Log4j2
@Component
public class FindDevices {

    @Autowired
    private DriverFactory driverFactory;

    @Autowired
    private LocalService localService;

    @Autowired
    private GeneralCapabilite generalCapabilite;


    private List<DriverModel> driverModels = new ArrayList<>();

    private Set<String> devidesList = new HashSet<>();


    /**
     * 获取设备
     * @return
     */
    public DriverModel getDriver() {

        Optional<DriverModel> first = driverModels.stream().filter(it -> !it.isUse()).findFirst();


        if (first.isEmpty()){
            Assert.isTrue(false,"当前没有设备可以操作");
        }
        first.get().setUse(true);
        return first.get();

    }

    /**
     * 查找设备
     * @return
     */

    public List<DriverModel> findAndroidDevices() {
        log.info("当前初始化的driver：{}", driverModels);
        Set<String> result = new HashSet<String>();
        String android = "ANDROID";
        String android_home = System.getenv("ANDROID_HOME");
        if (null == android_home) {
            log.error("请设置android环境变量：ANDROID_HOME");
        }

        File file = new File(android_home + File.separator + "platform-tools");

        Set<String> list = ADBUtil.list(file);

        //新增的设备

        result.clear();
        result.addAll(list);
        result.removeAll(devidesList);
        devidesList.addAll(result);


        try {
            for (String s : result) {
                log.info("添加设备：{}", s);

                DriverModel driverModel = initDriver(android, s);
                driverModels.add(driverModel);
            }
        } catch (Exception e) {

            e.printStackTrace();
        }


        //移除的设备
        result.clear();
        result.addAll(devidesList);
        result.removeAll(list);
        devidesList.removeAll(result);

        for (String s : result) {
            log.info("移除设备：{}", s);
            driverModels.removeIf(it -> it.getUuid() == s);

        }


        return this.driverModels;
    }


    /**
     * 获取设备时间 防止session过期
     */

    public void getTime() {
        if (!driverModels.isEmpty()) {


            driverModels.forEach(it -> it.getAppiumDriver().getDeviceTime());


        }


    }

    private DriverModel initDriver(String platform, String UUID) {
        DriverModel driverModel = new DriverModel();

        DesiredCapabilities capabilite = generalCapabilite.capabilite(platform, UUID);


        driverModel.setAppiumDriver(driverFactory.createInstance(platform, capabilite, localService.getUrl()));
        driverModel.setUuid(UUID);
        driverModel.setPlatform(platform);


        return driverModel;


    }
}
