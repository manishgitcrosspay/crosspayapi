package com.crosspay.payment.service;

import javax.naming.Context;

import org.springframework.stereotype.Service;

import com.crosspay.payment.model.CustomTemplate;
import com.crosspay.payment.model.Receiptpdf;

@Service
public interface EmailService {
	
	public void sendSimpleEmail(String fromAddr, String toAddr, String subject, String message);

	public void sendMimeTypeEmail(String formAddr, String toAddress, String subject, String msg);

	public void sendMailWithTemplate(String templateName, Context vars, String toAddr, String subject);
	
	public void sendmailpdf(String fromAddr, String toAddr, String subject, String message);
	
	public String sendmailpdfdy(Receiptpdf receipt,String template,String fromAddr);
	
	public void sendEmailTemplate(CustomTemplate custom);
}
