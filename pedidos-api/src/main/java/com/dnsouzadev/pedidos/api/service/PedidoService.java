package com.dnsouzadev.pedidos.api.service;

import com.dnsouzadev.pedidos.api.entity.Pedido;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class PedidoService {

    private final Logger log = LoggerFactory.getLogger(PedidoService.class);

    @Value("${rabbitmq.exchange-name}")
    private String exchangeName;

    private final RabbitTemplate rabbitTemplate;

    public PedidoService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public Pedido enfileirarPedido(Pedido pedido) {
        rabbitTemplate.convertAndSend(exchangeName, "", pedido);
        log.info("Pedido enfileirado: {}", pedido.toString());
        return pedido;
    }

}
