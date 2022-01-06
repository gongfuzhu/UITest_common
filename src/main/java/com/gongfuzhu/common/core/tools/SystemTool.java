package com.gongfuzhu.common.core.tools;

import java.io.File;
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


    public static String getPath(){

        String path = SystemTool.class.getProtectionDomain().getCodeSource().getLocation().getPath();

        if (System.getProperty("os.name").contains("dows")){

             path = path.substring(1, path.length());

        }else if (path.contains("jar")){

            path=path.substring(0,path.lastIndexOf("."));

            return path.substring(0,path.lastIndexOf("/"));
        }


        return path.replace("target/classes/","");



    }

    /**
     * 获取jar包所在路径
     * @return
     */
    public static String getJarPath()
    {
        String path = SystemTool.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try
        {
            path = java.net.URLDecoder.decode(path, "UTF-8"); // 转换处理中文及空格
        }
        catch (java.io.UnsupportedEncodingException e)
        {
            return null;
        }
        return new File(path).getAbsolutePath();
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
