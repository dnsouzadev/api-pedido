package com.dnsouzadev.pedidos.processador.service;

import com.dnsouzadev.pedidos.processador.entity.ItemPedido;
import com.dnsouzadev.pedidos.processador.repository.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProdutoService {

    private final ProdutoRepository produtoRepository;

    public ProdutoService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    public void save(List<ItemPedido> itens) {

        itens.forEach(itemPedido -> {
            produtoRepository.save(itemPedido.getProduto());
        });
    }
}
