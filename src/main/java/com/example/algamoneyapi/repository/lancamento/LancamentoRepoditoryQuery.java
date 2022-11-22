package com.example.algamoneyapi.repository.lancamento;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;

public interface LancamentoRepoditoryQuery {
	
	public List<Lancamento> filter(LancamentoFilter filter);

}
