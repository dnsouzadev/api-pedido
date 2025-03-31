package com.dnsouzadev.pedidos.notificacao.listener;

import com.dnsouzadev.pedidos.notificacao.entity.Pedido;
import com.dnsouzadev.pedidos.notificacao.service.EmailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private final Logger log = LoggerFactory.getLogger(PedidoListener.class);

    private final EmailService emailService;

    public PedidoListener(EmailService emailService) {
        this.emailService = emailService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void enviarNotificacao(Pedido pedido) {
        log.info("Notificação gerada com sucesso! -> {}", pedido.toString());
        try {
            emailService.enviarEmail(pedido);
            log.info("Email enviado com sucesso para o cliente: {}", pedido.getCliente());
        } catch (Exception e) {
            log.error("Erro ao enviar email para o cliente: {}", pedido.getCliente(), e);
        }
    }
}
