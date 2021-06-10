package com.rondri.cursomc.services;

import org.springframework.mail.SimpleMailMessage;

import com.rondri.cursomc.domain.Pedido;

public interface EmailService {

	void sendOrderConfirmationEmail(Pedido obj);
	
	void sendEmail(SimpleMailMessage msg);
}
