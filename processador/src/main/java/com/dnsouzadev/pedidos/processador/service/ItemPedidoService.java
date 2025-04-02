package com.dnsouzadev.pedidos.processador.service;

import com.dnsouzadev.pedidos.processador.entity.ItemPedido;
import com.dnsouzadev.pedidos.processador.entity.Pedido;
import com.dnsouzadev.pedidos.processador.repository.ItemPedidoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ItemPedidoService {

    private final ItemPedidoRepository itemPedidoRepository;

    public ItemPedidoService(ItemPedidoRepository itemPedidoRepository) {
        this.itemPedidoRepository = itemPedidoRepository;
    }

    public List<ItemPedido> save(List<ItemPedido> itens) {
        return itemPedidoRepository.saveAll(itens);
    }

    public void save(ItemPedido item) {
        itemPedidoRepository.save(item);
    }

    public void updatedItemPedido(List<ItemPedido> itensPedidos, Pedido pedido) {
        itensPedidos.forEach(itemPedido -> {
            itemPedido.setPedido(pedido);
            this.save(itemPedido);
        });
    }
}
