package br.com.preco_justo.domain.Cliente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

import java.util.Calendar;

public record DadosCadastroCliente(
        @NotBlank(message = "{nome.obrigatorio}") String nome,
        @NotBlank(message = "{email.obigatorio}") @Email(message = "{email.invalido}") String email,
        @NotBlank(message = "{cpf.obrigatorio}") @Pattern(regexp = "\\d{3}\\.\\d{3}\\.\\d{3}\\-\\d{2}", message = "{cpf.invalido}")  String cpf,
        @NotNull Calendar nascimento,
        @NotBlank(message = "{telefone.obrigatorio}") String telefone,
        @NotNull Boolean desconto,
        @NotNull(message = "{endereco.obrigatorio}") @Valid DadosEndereco endereco
) {
}
