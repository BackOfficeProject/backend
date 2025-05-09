package com.backoffice.backoffice.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${smtp.from}")
    private String fromEmail;

    //회원가입 메일
    public void sendEmailAndSaveAuthCode(String to) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, false, "UTF-8");

        helper.setTo(to);
        helper.setSubject("회원가입 완료 안내");
        helper.setText(
                "<h1>회원가입이 완료되었습니다.</h1>" +
                        "<p>회원가입이 성공적으로 완료되었습니다. 이제 서비스를 이용하실 수 있습니다.</p>",
                true
        );
        helper.setFrom(fromEmail);

        mailSender.send(message);


    }

    // 급여 이체 알림 메일
    public void sendSalaryTransferEmailWithPdf(String to, byte[] pdfData) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

        helper.setTo(to);
        helper.setSubject("급여 이체 완료 안내");
        helper.setText(
                "<h1>급여 이체 완료</h1>" +
                        "<p>사원님의 급여 이체가 완료되었습니다.</p>",
                true
        );
        helper.setFrom(fromEmail);

        // PDF 첨부
        helper.addAttachment("급여명세서.pdf", new ByteArrayResource(pdfData));

        mailSender.send(message);
    }
}