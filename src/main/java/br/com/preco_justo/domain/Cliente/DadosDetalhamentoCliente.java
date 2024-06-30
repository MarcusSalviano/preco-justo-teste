package br.com.preco_justo.domain.Cliente;

import java.util.Calendar;

public record DadosDetalhamentoCliente(

        Long id,
        String nome,
        String email,
        String cpf,
        String telefone,
        Calendar nascimento,
        Boolean desconto,
        Endereco endereco

) {
    public DadosDetalhamentoCliente(Cliente cliente){
        this(
                cliente.getId(),
                cliente.getNome(),
                cliente.getEmail(),
                cliente.getCpf(),
                cliente.getTelefone(),
                cliente.getNascimento(),
                cliente.getDesconto(),
                cliente.getEndereco()
        );
    }
}
