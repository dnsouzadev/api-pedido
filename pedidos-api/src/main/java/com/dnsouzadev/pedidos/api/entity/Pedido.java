package com.dnsouzadev.pedidos.api.entity;

import com.dnsouzadev.pedidos.api.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Pedido {

    private UUID id = UUID.randomUUID();
    private String cliente;
    private List<ItemPedido> itemPedidos = new ArrayList<>();
    private Double valorTotal;
    private String emailNotificacao;
    private Status status;
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private LocalDateTime dataHora;
}
