package br.com.preco_justo.domain.Pato;

import java.util.List;

public record DadosDetalhamentoPato(

        Long id,
        String nome,
        String raca,
        Double valorVenda,
        Long maeId,
        String maeNome,
        List<DadosDetalhamentoPato> filhos

) {
    public DadosDetalhamentoPato(Pato pato){

        this(
                pato.getId(),
                pato.getNome(),
                pato.getRaca(),
                pato.getValorVenda(),
                pato.getMae() != null ? pato.getMae().getId() : null,
                pato.getMae() != null ? pato.getMae().getNome() : null,
                pato.getFilhos().stream().map(DadosDetalhamentoPato::new).toList()
        );
    }
}
