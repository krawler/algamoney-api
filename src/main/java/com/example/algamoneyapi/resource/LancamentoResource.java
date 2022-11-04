package com.example.algamoneyapi.resource;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.service.LancamentoService;

@RestController
@RequestMapping("/lancamentos")
public class LancamentoResource {
	
	@Autowired
	private LancamentoService service;
	
	@Autowired
	private LancamentoRepository repository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping
    public ResponseEntity<List<Lancamento>> list(){
        return ResponseEntity.ok(repository.findAll());
    }
	
	@GetMapping("/{id}")
	public ResponseEntity<Lancamento> getById(@PathVariable Long id){
	    Lancamento lancamento = repository.getById(id);
	    return ResponseEntity.status(HttpStatus.ACCEPTED).body(lancamento);
	}
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
	@PostMapping
	public ResponseEntity<Lancamento> create(@Valid @RequestBody Lancamento lancamento, HttpServletResponse response){
		Lancamento savedLancamento = service.save(lancamento);
		publisher.publishEvent(new RecursoCriadoEvent(this, response, savedLancamento.getId()));
		return ResponseEntity.status(HttpStatus.CREATED).body(savedLancamento);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Lancamento> update(@Valid @PathVariable Long id, @RequestBody Lancamento lancamento) {
	    try {
            Lancamento updatedLancamento = service.update(id, lancamento);
            return ResponseEntity.ok(updatedLancamento);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
	
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@DeleteMapping("{id}")
	public ResponseEntity<Lancamento> remove(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
