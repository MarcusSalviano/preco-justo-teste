package br.com.preco_justo.domain.Venda;

import jakarta.validation.constraints.NotNull;

import java.util.List;

public record DadosVenda (
        @NotNull Long clienteId,
        @NotNull List<Long> patosIdList
) {
}
