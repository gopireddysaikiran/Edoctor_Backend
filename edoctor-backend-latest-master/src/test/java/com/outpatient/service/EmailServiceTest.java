package com.outpatient.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {EmailService.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class EmailServiceDiffblueTest {
    @Autowired
    private EmailService emailService;

    @MockBean
    private JavaMailSender javaMailSender;

    /**
     * Test {@link EmailService#sendOtpEmail(String, String, String)}.
     * <p>
     * Method under test: {@link EmailService#sendOtpEmail(String, String, String)}
     */
    @Test
    @DisplayName("Test sendOtpEmail(String, String, String)")
    void testSendOtpEmail() throws MailException {
        // Arrange and Act
        emailService.sendOtpEmail("alice.liddell@example.org", "janedoe", "Otp");
    }
}
