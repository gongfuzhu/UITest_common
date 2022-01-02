package com.gongfuzhu.common.core.tools;

import ch.ethz.ssh2.ChannelCondition;
import ch.ethz.ssh2.Connection;
import ch.ethz.ssh2.Session;
import ch.ethz.ssh2.StreamGobbler;
import lombok.SneakyThrows;
import lombok.extern.log4j.Log4j2;
import org.apache.commons.compress.utils.IOUtils;
import org.springframework.stereotype.Component;

import java.io.*;

@Component
@Log4j2
public class ShellTool {


    public Connection login(String host, File file) {


        Connection connection = new Connection(host);

        try {

            connection.connect();

            if (connection.authenticateWithPublicKey("root", file, null)) {

                return connection;
            }

            throw new IOException("连接失败");


        } catch (IOException e) {
            e.printStackTrace();
        }


        return connection;
    }


    /**
     * 执行脚本
     *
     * @param cmds
     * @return
     * @throws Exception
     */
    public StringBuilder exec2(String host, File file, String cmds) throws Exception {

        Connection conn = login(host, file);
        InputStream stdOut = null;
        InputStream stdErr = null;
        String outStr = "";
        String outErr = "";
        StringBuilder stringBuilder;
        int ret = -1;
        try {
            Session session = conn.openSession();
            // 建立虚拟终端
            session.requestPTY("bash");
            // 打开一个Shell
            session.startShell();
            stdOut = new StreamGobbler(session.getStdout());
            stdErr = new StreamGobbler(session.getStderr());

            // 准备输入命令
            PrintWriter out = new PrintWriter(session.getStdin());
            // 输入待执行命令
            out.println(cmds);
            out.println("exit");
            // 6. 关闭输入流
            out.close();
            // 7. 等待，除非1.连接关闭；2.输出数据传送完毕；3.进程状态为退出；4.超时
            session.waitForCondition(ChannelCondition.CLOSED | ChannelCondition.EOF | ChannelCondition.EXIT_STATUS, 30000);
            stringBuilder = consumeInputStream(stdOut);
            session.close();/* Close this session */
            conn.close();/* Close the connection */


        } finally {
            if (conn != null) {
                conn.close();
            }
            IOUtils.closeQuietly(stdOut);
            IOUtils.closeQuietly(stdErr);
        }
        return stringBuilder;
    }


    public String execShell(Connection connection, String shell) {

        if (shell.startsWith("rm")) {
            return ShellType.Faile.remark;
        }

        try {
            Session session = connection.openSession();
            session.execCommand(shell);
            session.waitForCondition(ChannelCondition.TIMEOUT, 30000);

            StringBuilder stringBuilder = consumeInputStream(session.getStdout());

            session.close();

            return stringBuilder.toString();

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 消费inputstream，并返回
     */
    @SneakyThrows
    public StringBuilder consumeInputStream(InputStream is) {


        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String s;
        StringBuilder sb = new StringBuilder();
        while ((s = br.readLine()) != null) {
            System.out.println(s);
            sb.append(s);
        }
        return sb;
    }


    enum ShellType {

        Faile("禁止删除");

        String remark;

        ShellType(String remark) {
            this.remark = remark;
        }

    }


}
