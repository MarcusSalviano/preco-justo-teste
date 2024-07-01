package br.com.preco_justo.domain.Pato;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PatoRepository extends JpaRepository<Pato, Long> {
    @Query("SELECT i FROM Pato i WHERE i.id IN :ids")
    List<Pato> findByIds(@Param("ids") List<Long> ids);
}
