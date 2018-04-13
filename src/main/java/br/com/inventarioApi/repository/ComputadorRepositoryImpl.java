package br.com.inventarioApi.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.inventarioApi.filter.ComputadorFilter;
import br.com.inventarioApi.model.Computador;

public class ComputadorRepositoryImpl implements ComputadorRepositoryQuery{

	
	@Autowired
	private EntityManager manager;
	
	
	@Override
	public Page<Computador> filtrar(ComputadorFilter computadorFilter, Pageable pageable) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Computador> criteria = builder.createQuery(Computador.class);
		Root<Computador> root = criteria.from(Computador.class);
		
		
		Predicate[] predicates = criarRestricoes(computadorFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<Computador> query = manager.createQuery(criteria);
		
		adicionarRestricoesPaginacao(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(computadorFilter));
	}


	private Predicate[] criarRestricoes(ComputadorFilter computadorFilter, CriteriaBuilder builder,
			Root<Computador> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		
		if(!StringUtils.isEmpty(computadorFilter.getHd())) {
			predicates.add(builder.like(
					builder.lower(root.get("hd")),"%" + computadorFilter.getHd().toLowerCase() + "%"));
		}
		
		if(computadorFilter.getMarca() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("marca")),"%" + computadorFilter.getMarca().toLowerCase() + "%"));
		}
		
		if(computadorFilter.getModelo() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("modelo")),"%" + computadorFilter.getModelo().toLowerCase() + "%"));
		}
		
		if(computadorFilter.getRam() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("ram")),"%" + computadorFilter.getRam().toLowerCase() + "%"));
		}
		
		if(computadorFilter.getSistemaOperacional() != null) {
			predicates.add(builder.like(
					builder.lower(root.get("sistemaOperacional")),"%" + 
			computadorFilter.getSistemaOperacional().toLowerCase() + "%"));
		}
		
		
		
		return predicates.toArray(new Predicate[predicates.size()]);
	}





	private Long total(ComputadorFilter computadorFilter) {
		
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		
		Root<Computador> root = criteria.from(Computador.class);
		
		
		Predicate[] predicates = criarRestricoes(computadorFilter, builder, root);
		criteria.where(predicates);
		
		criteria.select(builder.count(root));
		
		
		
		return manager.createQuery(criteria).getSingleResult();
	}


	private void adicionarRestricoesPaginacao(TypedQuery<Computador> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
		
	}





















}