package br.com.preco_justo.domain.Pato;

import jakarta.validation.constraints.NotBlank;

public record DadosCadastroPato(
        @NotBlank(message = "{nome.obrigatorio}") String nome,
        String raca,
        Long mae
) {
}
