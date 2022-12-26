package com.example.algamoneyapi.repository.lancamento;

import java.util.List;

import com.example.algamoneyapi.model.Lancamento;

public interface LancamentoRepositoryQuery {
	
	public List<Lancamento> filter(Lancamento filter);

}
