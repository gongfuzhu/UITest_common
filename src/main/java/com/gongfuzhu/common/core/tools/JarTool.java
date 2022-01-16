package com.gongfuzhu.common.core.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JarTool {
    /**
     * 获取jar绝对路径
     *
     * @return
     */
    public static String getJarPath()
    {
        File file = getFile();
        if (file == null)
            return null;
        return file.getAbsolutePath();
    }

    /**
     * 获取jar目录
     *
     * @return
     */
    public static String getJarDir()
    {
        File file = getFile();
        if (file == null)
            return null;
        return getFile().getParent();
    }

    /**
     * 获取jar包名
     *
     * @return
     */
    public static String getJarName()
    {
        File file = getFile();
        if (file == null)
            return null;
        return getFile().getName();
    }

    /**
     * 获取当前Jar文件
     *
     * @return
     */
    private static File getFile()
    {
        // 关键是这行...
        String path = JarTool.class.getProtectionDomain().getCodeSource().getLocation().getFile();
        try
        {
            path = java.net.URLDecoder.decode(path, "UTF-8"); // 转换处理中文及空格
        }
        catch (java.io.UnsupportedEncodingException e)
        {
            return null;
        }
        return new File(path);
    }

    /**
     * 获取当前目录下的文件
     */
    public static List<String> getFile(String path) {
        File file = new File(path);

        File[] fileList = file.listFiles();
        List<String> list = new ArrayList<String>();
        for (int i = 0; i < fileList.length; i++) {
            if (fileList[i].isFile()) {
                String fileName = fileList[i].getName();
                list.add(fileName);
            }
            /*if (fileList[i].isDirectory()) {
                String fileName = fileList[i].getName();
                System.out.println("目录：" + fileName);
            }*/
        }
        return list;
    }

//    public static void main(String[] args) {
//        System.out.println(getJarPath()+"\\Api.xmls");
//        System.out.println(JarToolUtil.class.getProtectionDomain().getCodeSource().getLocation().getFile());
//    }
}
