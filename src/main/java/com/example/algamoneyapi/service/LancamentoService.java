package com.example.algamoneyapi.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento save(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getId());
		if(pessoa.isEmpty()) {
			throw new RuntimeException();
		}
		
		return lancamentoRepository.save(lancamento);
		
	}
}
