package com.gongfuzhu.common.core.tools;



import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.springframework.util.DigestUtils;
import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Log4j2
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

    public static String getTempPictuer() throws IOException {

        // 构造URL
        URL url = new URL("https://open.saintic.com/api/bingPic");
        // 打开连接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);

        // 输入流
        InputStream is = conn.getInputStream();
        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        System.out.println(conn.getURL().toString());
        String s = conn.getURL().toString().trim();
        // 输出的文件流
        File tempFile = File.createTempFile(DigestUtils.md5DigestAsHex(s.getBytes()), ".jpg");
        FileOutputStream os = new FileOutputStream(tempFile, true);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
        return tempFile.getPath();
    }

    @SneakyThrows
    public static void savePicture(String uri){


        //new一个URL对象
        URL url = new URL(uri);
        //打开链接
        HttpURLConnection conn = (HttpURLConnection)url.openConnection();
        //设置请求方式为"GET"
        conn.setRequestMethod("GET");
        //超时响应时间为5秒
        conn.setConnectTimeout(5 * 1000);
        //通过输入流获取图片数据
        InputStream inStream = conn.getInputStream();
        //得到图片的二进制数据，以二进制封装得到数据，具有通用性
        byte[] data = readInputStream(inStream);
        //new一个文件对象用来保存图片，默认保存当前工程根目录
        File imageFile = new File("pic20170419.jpg");
        //创建输出流
        FileOutputStream outStream = new FileOutputStream(imageFile);
        //写入数据
        outStream.write(data);
        //关闭输出流
        outStream.close();

        log.info("文件路径：{}",imageFile.getPath());

    }
    public static byte[] readInputStream(InputStream inStream) throws Exception{
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len = 0;
        //使用一个输入流从buffer里把数据读取出来
        while( (len=inStream.read(buffer)) != -1 ){
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }







}
