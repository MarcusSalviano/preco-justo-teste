package br.com.preco_justo.domain.Venda;

import br.com.preco_justo.domain.Cliente.Cliente;
import br.com.preco_justo.domain.Pato.Pato;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VendaRepository extends JpaRepository<Venda, Long> {
}
