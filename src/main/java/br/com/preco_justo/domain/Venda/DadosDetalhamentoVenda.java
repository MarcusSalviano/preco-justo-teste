package br.com.preco_justo.domain.Venda;

import br.com.preco_justo.domain.Cliente.Cliente;
import br.com.preco_justo.domain.Pato.DadosDetalhamentoPato;
import br.com.preco_justo.domain.Pato.Pato;

import java.util.Calendar;
import java.util.List;

public record DadosDetalhamentoVenda(

        Long id,
        Long idCliente,
        String clienteNome,
        Calendar dataVenda,
        Double valorTotalVenda,
        List<DadosDetalhamentoPato> patosList

) {
    public DadosDetalhamentoVenda(Venda venda){
        this(
                venda.getId(),
                venda.getCliente().getId(),
                venda.getCliente().getNome(),
                venda.getDataVenda(),
                venda.getPatosList().stream().mapToDouble(Pato::getValorVenda).sum(),
                venda.getPatosList().stream().map(DadosDetalhamentoPato::new).toList()
        );
    }
}
