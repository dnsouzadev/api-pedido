package com.dnsouzadev.pedidos.processador.service;

import com.dnsouzadev.pedidos.processador.entity.ItemPedido;
import com.dnsouzadev.pedidos.processador.entity.Pedido;
import com.dnsouzadev.pedidos.processador.repository.PedidoRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoService {

    private final Logger log = LoggerFactory.getLogger(PedidoService.class);

    private final PedidoRepository pedidoRepository;
    private final ProdutoService produtoService;
    private final ItemPedidoService itemPedidoService;

    public PedidoService(PedidoRepository pedidoRepository, ProdutoService produtoService, ItemPedidoService itemPedidoService) {
        this.pedidoRepository = pedidoRepository;
        this.produtoService = produtoService;
        this.itemPedidoService = itemPedidoService;
    }

    public void save(Pedido pedido) {

        // Salva os produtos
        produtoService.save(pedido.getItens());

        // salva os itens do pedido
        List<ItemPedido> itensPedidos = itemPedidoService.save(pedido.getItens());

        // salvando o pedido
        pedidoRepository.save(pedido);

        // Atualiza os itens do pedido com o pedido que ele faz parte
        itemPedidoService.updatedItemPedido(itensPedidos, pedido);

        // Log de sucesso
        log.info("Pedido salvo com sucesso: {}", pedido.toString());
    }

}
