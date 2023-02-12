package com.example.algamoneyapi.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.ObjectUtils;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Lancamento> filter(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = createRestrictions(lancamentoFilter, builder, root);
		criteria.where(predicates);
				
		TypedQuery<Lancamento> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, this.total(lancamentoFilter));
	}

	private Predicate[] createRestrictions(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<Lancamento> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!ObjectUtils.isEmpty(lancamentoFilter.getDescricao())) {
		   predicates.add(
				      builder.like(builder.lower(root.get("descricao")), "%" + lancamentoFilter.getDescricao() .toLowerCase() + "%")
		   );	
		}
		
		if(lancamentoFilter.getDataVencimentoDe() != null) {
			predicates.add(
					   builder.greaterThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe())
		     );
		}
		if(lancamentoFilter.getDataVencimentoDe() != null) {
		   predicates.add(
				   	  builder.lessThanOrEqualTo(root.get("dataVencimento"), lancamentoFilter.getDataVencimentoDe())
		   );	
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDePaginacao(TypedQuery<Lancamento> query, Pageable pageable) {
		int currentPage = pageable.getPageNumber();
		int totalPageRows = pageable.getPageSize();
		int firstRowPage = currentPage * totalPageRows;
		
		query.setFirstResult(firstRowPage);
		query.setMaxResults(totalPageRows);
	}
	
	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = createRestrictions(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
