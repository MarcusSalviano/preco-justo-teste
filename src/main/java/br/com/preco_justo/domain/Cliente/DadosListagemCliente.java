package br.com.preco_justo.domain.Cliente;

public record DadosListagemCliente(
        Long id,
        String nome,
        String email,
        String cpf
) {

    public DadosListagemCliente (Cliente cliente) {
        this(cliente.getId(), cliente.getNome(), cliente.getEmail(), cliente.getCpf());
    }

}
