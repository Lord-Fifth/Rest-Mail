package com.flytxt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class RestApplication implements CommandLineRunner {

	@Autowired
    private JavaMailSender javaMailSender;
	
	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class, args);
	}

	@Override
    public void run(String... args) throws IOException, MessagingException {

        System.out.println("Sending Email...");

        sendEmail();
		//sendEmailWithAttachment();

        System.out.println("Done");

    }

    void sendEmail() {

	   	int randomNum = ThreadLocalRandom.current().nextInt(999,10000);
    	
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo("aditya.suresh1504@gmail.com", "aditya.suresh98@yahoo.com");

        msg.setSubject("Testing from Spring Boot");
        msg.setText("Hello to the world of Spring Boot \n\n OTP :: " + randomNum);

        javaMailSender.send(msg);

    }
    
    void sendEmailWithAttachment() throws MessagingException, IOException {

        MimeMessage msg = javaMailSender.createMimeMessage();

        // true = multipart message
        MimeMessageHelper helper = new MimeMessageHelper(msg, true);
        helper.setTo("1@gmail.com");

        helper.setSubject("Testing from Spring Boot");

        // default = text/plain
        //helper.setText("Check attachment for image!");

        // true = text/html
        helper.setText("<h1>Check attachment for image!</h1>", true);

        helper.addAttachment("my_photo.png", new ClassPathResource("android.png"));

        javaMailSender.send(msg);

    }
}
