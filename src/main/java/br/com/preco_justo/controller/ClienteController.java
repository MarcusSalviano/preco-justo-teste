package br.com.preco_justo.controller;

import br.com.preco_justo.domain.Cliente.*;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroCliente dados, UriComponentsBuilder uriBuilder) {
        var cliente = new Cliente(dados);
        repository.save(cliente);

        var uri = uriBuilder.path("/cliente/{id}").buildAndExpand(cliente.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoCliente(cliente));
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemCliente>> lista(@PageableDefault(size = 10, sort = {"nome"}) Pageable paginacao) {
        var page =  repository.findAll(paginacao).map(DadosListagemCliente::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping ("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var cliente = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoCliente(cliente));
    }

    @DeleteMapping ("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        repository.deleteById(id);

        return ResponseEntity.noContent().build();
    }


}
