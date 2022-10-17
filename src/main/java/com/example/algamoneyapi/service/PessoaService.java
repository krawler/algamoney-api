package com.example.algamoneyapi.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;

@Service
public class PessoaService {
    
    @Autowired
    private PessoaRepository pessoaRepository;
    
    public Pessoa update(Long id, Pessoa pessoa) {
        Pessoa pessoaRetornada = pessoaRepository.findById(id).get();
        if(pessoaRetornada == null) {
            throw new EmptyResultDataAccessException(1);
        }
        BeanUtils.copyProperties(pessoa, pessoaRetornada, "id");
        pessoaRepository.save(pessoaRetornada);
        
        return pessoaRetornada;
    }

}
