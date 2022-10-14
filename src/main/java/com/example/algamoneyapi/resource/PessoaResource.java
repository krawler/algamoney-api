package com.example.algamoneyapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
	
	@Autowired
	PessoaRepository repository;
	
	@PostMapping
	public ResponseEntity<Pessoa> create(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response){
		return new ResponseEntity<Pessoa>(HttpStatus.CREATED);
	}
	
	@GetMapping
	public ResponseEntity<Pessoa> getById(@PathVariable Long id){
		return new ResponseEntity<Pessoa>(HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
	public void remove(@PathVariable Pessoa pessoa) {
		 repository.delete(pessoa);
	}
	
}
