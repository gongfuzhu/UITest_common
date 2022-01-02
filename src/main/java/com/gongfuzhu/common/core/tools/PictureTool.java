package com.gongfuzhu.common.core.tools;



import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;

public class PictureTool {

    /**
     *
     * @param picture 需要处理的图片
     * @param text  水印内容
     * @param color 颜色
     * @param font 字体样式
     */

    public static void addString(File picture,String text,Color color,Font font){

        try {
            BufferedImage TargetImage = ImageIO.read(picture);
            int width = TargetImage.getWidth();
            int height = TargetImage.getHeight();


            BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.drawImage(TargetImage,0,0,width,height,null);
            graphics.setColor(color);
            graphics.setFont(font);

            int length = graphics.getFontMetrics(graphics.getFont()).charsWidth(text.toCharArray(), 0, text.length());


            int x = width -length-10;
            int y = height-20;

            graphics.drawString(text, x, y);
            graphics.dispose();

            FileOutputStream fileOutputStream = new FileOutputStream(picture);
            ImageIO.write(image, "png",fileOutputStream);
            fileOutputStream.flush();
            fileOutputStream.close();


        } catch (IOException e) {
            e.printStackTrace();
        }


    }





}
