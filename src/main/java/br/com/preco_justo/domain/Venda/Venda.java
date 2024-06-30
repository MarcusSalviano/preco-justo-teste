package br.com.preco_justo.domain.Venda;

import br.com.preco_justo.domain.Cliente.Cliente;
import br.com.preco_justo.domain.Pato.Pato;
import jakarta.persistence.*;
import lombok.*;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

@Table(name = "vendas")
@Entity(name = "Venda")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Venda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.DATE)
    private Calendar dataVenda;

    @ManyToOne
    private Cliente cliente;

    @OneToMany
    @JoinTable(name="vendas_patos", inverseJoinColumns=@JoinColumn(name="pato_id"))
    private Set<Pato> patosList;

    public Venda(DadosVenda dados) {
        //this.clienteId = dados.clienteId();
        this.dataVenda = Calendar.getInstance();
        //this.patosIdList = dados.patosIdList();
    }
}
