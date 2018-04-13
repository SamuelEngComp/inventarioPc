package br.com.inventarioApi.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.inventarioApi.filter.ComputadorFilter;
import br.com.inventarioApi.model.Computador;

public interface ComputadorRepositoryQuery {
	
	
	public Page<Computador> filtrar(ComputadorFilter computadorFilter, Pageable pageable);

}
