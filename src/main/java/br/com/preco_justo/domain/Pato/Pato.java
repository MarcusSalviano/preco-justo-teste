package br.com.preco_justo.domain.Pato;

import jakarta.persistence.*;
import lombok.*;

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

    @JoinColumn(name = "mae_id", insertable = false, updatable = false)
    @ManyToOne(targetEntity = Pato.class, fetch = FetchType.EAGER)
    private Pato mae;

    @Column(name = "mae_id")
    private Long maeId;

//    @OneToMany(mappedBy = "mae", cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
//    private List<Pato> filhos = new ArrayList<>();

    public Pato(DadosCadastroPato dados) {
        this.nome = dados.nome();
        this.raca = dados.raca();
        this.maeId = dados.mae();
    }

    public Pato(Pato dados) {
        this.nome = dados.getNome();
        this.raca = dados.getRaca();
        if(dados.getMae() != null)
            this.mae = new Pato(dados.getMae());
    }

}
