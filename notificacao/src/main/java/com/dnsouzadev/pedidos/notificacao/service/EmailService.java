package com.dnsouzadev.pedidos.notificacao.service;

import com.dnsouzadev.pedidos.notificacao.entity.Pedido;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void enviarEmail(Pedido pedido) {
        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("pedidos-api@company.com");
        message.setTo(pedido.getEmailNotificacao());
        message.setSubject("Pedido de compra recebido");
        message.setText(this.gerarMensagem(pedido));
        mailSender.send(message);
    }

    private String gerarMensagem(Pedido pedido) {
        StringBuilder sb = new StringBuilder();
        sb.append("Olá, ").append(pedido.getCliente()).append("!\n\n");
        sb.append("Seu pedido foi recebido com sucesso!\n");
        sb.append("Número do pedido: ").append(pedido.getId()).append("\n");
        sb.append("Valor total: R$ ").append(pedido.getValorTotal()).append("\n");
        sb.append("Data do pedido: ").append(pedido.getDataHora()).append("\n\n");
        sb.append("Agradecemos pela sua compra!");

        return sb.toString();
    }

}
