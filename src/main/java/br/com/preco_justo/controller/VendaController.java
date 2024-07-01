package br.com.preco_justo.controller;

import br.com.preco_justo.domain.Cliente.ClienteRepository;
import br.com.preco_justo.domain.Pato.Pato;
import br.com.preco_justo.domain.Pato.PatoRepository;
import br.com.preco_justo.domain.Venda.DadosDetalhamentoVenda;
import br.com.preco_justo.domain.Venda.DadosVenda;
import br.com.preco_justo.domain.Venda.Venda;
import br.com.preco_justo.domain.Venda.VendaRepository;
import br.com.preco_justo.service.VendaService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/vendas")
public class VendaController {
    @Autowired
    VendaRepository repository;

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    PatoRepository patoRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosVenda dados, UriComponentsBuilder uriBuilder) {

        var venda = new Venda(dados);
        var cliente = clienteRepository.findById(dados.clienteId());
        List<Pato> listaPatos = patoRepository.findByIds(dados.patosIdList());

        if(cliente.isPresent())
        {
            venda.setCliente(cliente.get());
            VendaService.calculaValorPatos(listaPatos, cliente.get().getDesconto());
            venda.setPatosList(listaPatos);
        }
        repository.save(venda);

        var uri = uriBuilder.path("/vendas/{id}").buildAndExpand(venda.getId()).toUri();

        return ResponseEntity.created(uri).body(new DadosDetalhamentoVenda(venda));
    }

    @GetMapping
    public ResponseEntity<Page<DadosDetalhamentoVenda>> lista(@PageableDefault(size = 10, sort = {"id"}) Pageable paginacao) {
        var page =  repository.findAll(paginacao).map(DadosDetalhamentoVenda::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping ("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var venda = repository.getReferenceById(id);

        return ResponseEntity.ok(new DadosDetalhamentoVenda(venda));
    }
}
