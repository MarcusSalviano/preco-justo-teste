package br.com.preco_justo.controller;

import br.com.preco_justo.domain.Pato.DadosDetalhamentoPato;
import br.com.preco_justo.domain.Venda.DadosDetalhamentoVenda;
import br.com.preco_justo.domain.Venda.DadosVenda;
import br.com.preco_justo.domain.Venda.Venda;
import br.com.preco_justo.domain.Venda.VendaRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/venda")
public class VendaController {
    @Autowired
    VendaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosVenda dados, UriComponentsBuilder uriBuilder) {
        var venda = new Venda(dados);

        repository.save(venda);

        var uri = uriBuilder.path("/venda/{id}").buildAndExpand(venda.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoVenda(venda));

//        return ResponseEntity.ok().body(venda);
    }
}
