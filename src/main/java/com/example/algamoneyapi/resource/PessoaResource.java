package com.example.algamoneyapi.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.algamoneyapi.repository.PessoaRepository;

@RestController
@RequestMapping("/pessoa")
public class PessoaResource {
	
	@Autowired
	PessoaRepository repository;	
}
