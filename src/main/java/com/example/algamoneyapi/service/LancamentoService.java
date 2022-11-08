package com.example.algamoneyapi.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.LancamentoRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.service.exception.PessoaInativaOuInexistenteException;

@Service
public class LancamentoService {

	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public Lancamento save(Lancamento lancamento) {
		Optional<Pessoa> pessoa = pessoaRepository.findById(lancamento.getPessoa().getId());
		if(pessoa.isEmpty() || !pessoa.get().isInativo()) {
			throw new PessoaInativaOuInexistenteException();
		}
		
		return lancamentoRepository.save(lancamento);		
	}
	
	public Lancamento update(Long id, Lancamento lancamento) {
	    Lancamento lancamentoRetornado = lancamentoRepository.getById(id);
	    if(lancamentoRetornado == null) {
	       throw new EmptyResultDataAccessException(1); 
	    }
	    BeanUtils.copyProperties(lancamento, lancamentoRetornado, "id");
	    lancamentoRepository.save(lancamentoRetornado);
	    return lancamentoRetornado;
	}
}
