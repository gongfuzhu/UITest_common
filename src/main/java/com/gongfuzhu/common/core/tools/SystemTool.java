package com.gongfuzhu.common.core.tools;

import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class SystemTool {


    /**
     * 随机获得可用的端口
     * @return
     */

    public static int getFreeProt() {

        int port;
        Random random = new Random();

        boolean A=true;

        while (A){
            port = random.nextInt(1000)+5000;

            if (!chekPort(port)) {
                return port;
            }

        }

        return 0;
    }


    private static boolean chekPort(int port) {

        boolean flag = false;
        String host = "localhost";

        try {
            new Socket(host, port);
            flag = true;
        } catch (IOException e) {
        }

        return flag;
    }






}
