package br.com.preco_justo.domain.Venda;

import br.com.preco_justo.domain.Cliente.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
