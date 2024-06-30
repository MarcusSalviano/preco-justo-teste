package br.com.preco_justo.controller;

import br.com.preco_justo.domain.Pato.DadosCadastroPato;
import br.com.preco_justo.domain.Pato.DadosDetalhamentoPato;
import br.com.preco_justo.domain.Pato.Pato;
import br.com.preco_justo.domain.Pato.PatoRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/patos")
public class PatoController {

    @Autowired
    private PatoRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroPato dados, UriComponentsBuilder uriBuilder) {

        var pato = new Pato(dados);
        if(dados.mae() != null) {
            var mae = repository.getReferenceById(dados.mae());
            pato.setMae(mae);
        }

        repository.save(pato);

        var uri = uriBuilder.path("/pato/{id}").buildAndExpand(pato.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoPato(pato));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoPato>> lista(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page =  repository.findAll(paginacao).map(DadosDetalhamentoPato::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping ("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var pato = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoPato(pato));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }
}
