package com.dnsouzadev.pedidos.processador.listener;

import com.dnsouzadev.pedidos.processador.entity.Pedido;
import com.dnsouzadev.pedidos.processador.entity.enums.Status;
import com.dnsouzadev.pedidos.processador.service.PedidoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class PedidoListener {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    private final PedidoService pedidoService;

    public PedidoListener(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @RabbitListener(queues = "${rabbitmq.queue.name}")
    public void processarPedido(Pedido pedido) {
        pedido.setStatus(Status.PROCESSADO);
        pedidoService.save(pedido);
    }

}
