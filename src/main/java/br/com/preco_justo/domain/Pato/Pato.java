package br.com.preco_justo.domain.Pato;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Table(name = "patos")
@Entity(name = "Pato")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Pato {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String raca;

    private Double valorVenda;

    @ManyToOne
    @JoinColumn(name = "mae_id")
    private Pato mae;

    @OneToMany(mappedBy = "mae", cascade = CascadeType.REFRESH)
    private List<Pato> filhos = new ArrayList<>();

    public Pato(DadosCadastroPato dados) {
        this.nome = dados.nome();
        this.raca = dados.raca();
    }

    public Pato(Pato dados) {
        this.nome = dados.getNome();
        this.raca = dados.getRaca();
        if(dados.getMae() != null)
            this.mae = new Pato(dados.getMae());
    }

}
