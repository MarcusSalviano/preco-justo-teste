package br.com.preco_justo.domain.Pato;

public record DadosDetalhamentoPato(

        Long id,
        String nome,
        String raca,
        Long maeId,
        String nomeMae

) {
    public DadosDetalhamentoPato(Pato pato){
        this(
                pato.getId(),
                pato.getNome(),
                pato.getRaca(),
                pato.getMaeId(),
                pato.getMae() != null?pato.getMae().getNome():""
        );
    }
}
