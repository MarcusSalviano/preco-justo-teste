package br.com.preco_justo.domain.Venda;

import br.com.preco_justo.domain.Cliente.Cliente;
import br.com.preco_justo.domain.Pato.Pato;

import java.util.Calendar;
import java.util.Set;

public record DadosDetalhamentoVenda(

        Long id,
        Cliente cliente,
        Calendar dataVenda,
        Set<Pato> patosList

) {
    public DadosDetalhamentoVenda(Venda venda){
        this(
                venda.getId(),
                venda.getCliente(),
                venda.getDataVenda(),
                venda.getPatosList()
        );
    }
}
