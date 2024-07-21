package com.devteria.notification.controller;

import com.devteria.notification.dto.ApiResponse;
import com.devteria.notification.dto.request.Recipient;
import com.devteria.notification.dto.request.SendEmailRequest;
import com.devteria.notification.dto.response.EmailResponse;
import com.devteria.notification.service.EmailService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author DatNv
 */
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class EmailController {
    private static final Logger log = LoggerFactory.getLogger(EmailController.class);
    EmailService emailService;

    @PostMapping("/email/send")
    public ApiResponse<EmailResponse> sendEmail(@RequestBody SendEmailRequest request) {

        return ApiResponse.<EmailResponse>builder().result(emailService.sendEmail(request)).build();
    }

//    @KafkaListener(topics = "onboard-successful")
//    public void listen(String message) {
//        log.info("Message received: {}", message);
//    }

    @KafkaListener(topics = "onboard-successful")
    public ApiResponse<EmailResponse> sendEmailKafka(String message) {
        SendEmailRequest request = SendEmailRequest.builder()
                .to(Recipient.builder().email("datnv11.fpoly@gmail.com").name("DatNV").build())
                .subject("hello")
                .htmlContent("Dat様. こんにちは。このメールは新規ユーザーが登録したことをお知らせ!\n" + message)
                .build();
        return ApiResponse.<EmailResponse>builder().result(emailService.sendEmail(request)).build();
    }
}
