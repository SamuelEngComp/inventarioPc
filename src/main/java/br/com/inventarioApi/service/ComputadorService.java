package br.com.inventarioApi.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.inventarioApi.model.Computador;
import br.com.inventarioApi.repository.ComputadorRepository;

@Service
public class ComputadorService {
	
	@Autowired
	private ComputadorRepository computadorRepository;
		
	//salvando e alterando
	public Computador salvar(Computador computador) {
		return computadorRepository.save(computador);
	}
	
	//listando
	public List<Computador> listarComputadores(){
		return computadorRepository.findAll();
	}
	
	//deletando
	public void deletar(Integer codigo) {
		
		Computador pcPraDeletar = computadorRepository.findOne(codigo);
		
		if(pcPraDeletar == null) {
			throw new EmptyResultDataAccessException(1);
		}else {
			computadorRepository.delete(codigo);
		}
	}
	
	//atualizando
	public Computador atualizar(Integer codigo, Computador computador) {
		Computador pcPraAtualizar = buscaComputadorPeloCodigo(codigo);
		BeanUtils.copyProperties(computador, pcPraAtualizar,"id");
		return computadorRepository.save(pcPraAtualizar);
	}
	
	
	public Computador buscaComputadorPeloCodigo(Integer codigo) {
		Computador computadorEncontrado = computadorRepository.findOne(codigo);
		if(computadorEncontrado == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return computadorEncontrado;
	}
	
	

}










