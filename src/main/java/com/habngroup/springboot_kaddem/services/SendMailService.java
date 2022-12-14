package com.habngroup.springboot_kaddem.services;

import com.habngroup.springboot_kaddem.entities.Mail;
import com.habngroup.springboot_kaddem.repositories.MailRepository;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class SendMailService {

    private final JavaMailSender javaMailSender;


    private final MailRepository mailRepository;

    public SendMailService(JavaMailSender javaMailSender, MailRepository mailRepository) {
        this.javaMailSender = javaMailSender;
        this.mailRepository = mailRepository;
    }

    public long sendMail(Mail mail) throws MailException
    {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setTo(mail.getDestinataire());
        simpleMailMessage.setFrom("aymen.khairoune1@gmail.com");
        simpleMailMessage.setSubject(mail.getObject());
        simpleMailMessage.setText(mail.getMessage());
        javaMailSender.send(simpleMailMessage);
        return mailRepository.save(mail).getId() ;
    }
}
