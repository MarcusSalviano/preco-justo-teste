package br.com.preco_justo.domain.Cliente;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Table(name = "clientes")
@Entity(name = "Cliente")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String cpf;
    @Temporal(TemporalType.DATE)
    private Calendar nascimento;
    private String telefone;
    private Boolean desconto;

    @Embedded
    private Endereco endereco;

    public Cliente(DadosCadastroCliente dados) {
        this.nome = dados.nome();
        this.email = dados.email();
        this.cpf = dados.cpf();
        this.nascimento = dados.nascimento();
        this.telefone = dados.telefone();
        this.desconto = dados.desconto();
        this.endereco = new Endereco(dados.endereco());
    }

//    public void atualizarInformacoes(DadosAtualizacaoPaciente dados) {
//        if (dados.nome() != null) {
//            this.nome = dados.nome();
//        }
//        if (dados.telefone() != null) {
//            this.telefone = dados.telefone();
//        }
//        if (dados.endereco() != null) {
//            this.endereco.atualizarInformacoes(dados.endereco());
//        }
//    }
}
