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

import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import com.example.algamoneyapi.model.Lancamento;
import com.example.algamoneyapi.repository.filter.LancamentoFilter;

public class LancamentoRepositoryFilterImpl implements LancamentoRepositoryQuery {
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<Lancamento> filter(Lancamento filter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Lancamento> criteria = builder.createQuery(Lancamento.class);
		Root<Lancamento> root = criteria.from(Lancamento.class);
		
		Predicate[] predicates = createRestrictions(filter, builder, root);
		criteria.where(predicates);
				
		TypedQuery<Lancamento> query = manager.createQuery(criteria);		
		return query.getResultList();
	}

	private Predicate[] createRestrictions(Lancamento filter, CriteriaBuilder builder, Root<Lancamento> root) {
		
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		if(!ObjectUtils.isEmpty(filter.getDescicao())) {
		   predicates.add(
				      builder.like(
				    		      builder.lower(root.get("descricao")), "%" + filter.getDescicao().toLowerCase() + "%"
				    		  )
		   );	
		}
		if(filter.getDataVencimento() != null) {
			predicates.add(
					   builder.greaterThanOrEqualTo(
							   root.get("dataVencimento"), filter.getDataVencimento()
					   )
		     );
		}
		if(filter.getDataVencimento() != null) {
		   predicates.add(
				   	  builder.lessThanOrEqualTo(
				   			  root.get("dataVencimento"), filter.getDataVencimento()
				   	  )
		   );	
		}
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	

}
