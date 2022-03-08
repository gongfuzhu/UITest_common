package com.gongfuzhu.common.core.tools;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class EmailTool {

//    @Autowired
//    JavaMailSender javaMailSender;


    /**
     * javaMailSender.send(simpleMailMessage);
     * 构建消息
     *
     * @param emailModel
     * @return
     */
    public static SimpleMailMessage buildMessage(EmailModel emailModel) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(emailModel.getFrom());
        simpleMailMessage.setBcc(emailModel.getToUser());
        simpleMailMessage.setCc(emailModel.getCopyname());
        simpleMailMessage.setSubject(emailModel.getTheme());
        simpleMailMessage.setText(emailModel.getContent());
        log.info("邮件信息：{}", simpleMailMessage.getText());

        return simpleMailMessage;
    }


}

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
class EmailModel {
    //发送人
    private String from;
    //接受人
    private String toUser;
    //主题
    private String theme;
    //抄送给谁
    private String copyname;
    //邮件内容
    private String content;
}

