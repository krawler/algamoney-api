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

public class LancamentoRepositoryFilterImpl implements LancamentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<Lancamento> filter(LancamentoFilter filter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);
				
		TypedQuery<Lancamento> query = manager.createQuery(criteria);	
		adicionarRestricoesDePaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, this.total(filter));
	}

	private Predicate[] createRestrictions(LancamentoFilter filter, CriteriaBuilder builder, Root<Lancamento> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!ObjectUtils.isEmpty(filter.getDescricao())) {
		   predicates.add(
				      builder.like(builder.lower(root.get("descricao")), "%" + filter.getDescricao() .toLowerCase() + "%")
		   );	
		}
		
		if(filter.getDataVencimentoDe() != null) {
			predicates.add(
					   builder.greaterThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoDe())
		     );
		}
		if(filter.getDataVencimentoDe() != null) {
		   predicates.add(
				   	  builder.lessThanOrEqualTo(root.get("dataVencimento"), filter.getDataVencimentoDe())
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
	
	private Long total(LancamentoFilter filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		return manager.createQuery(criteria).getSingleResult();
	}
}
