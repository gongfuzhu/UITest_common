package com.gongfuzhu.common.core.tools;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
@Log4j2
public class SendEmail {


    @Autowired
    JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;
    @Value("${spring.mail.to-user}")
    private String toUser;
    @Value("${spring.mail.theme}")
    private String theme;
    @Value("${spring.mail.copyname}")
    private String copyname;


    public void send(String content){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setFrom(from);
        simpleMailMessage.setBcc(toUser);
        simpleMailMessage.setCc(copyname);
        simpleMailMessage.setSubject(theme);
        simpleMailMessage.setText(content);
        log.info("邮件信息：{}",simpleMailMessage.getText());
        mailSender.send(simpleMailMessage);

    }
}
